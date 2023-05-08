package com.ukrposhta.service.impl;

import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.exception.NoRecordFoundException;
import com.ukrposhta.model.Manager;
import com.ukrposhta.model.Programmer;
import com.ukrposhta.model.Project;
import com.ukrposhta.model.parent.Employee;
import com.ukrposhta.repository.ProjectRepository;
import com.ukrposhta.service.ManagerService;
import com.ukrposhta.service.ProgrammerService;
import com.ukrposhta.service.ProjectService;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProgrammerService programmerService;
    private final ManagerService managerService;

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project update(Project project) {
        Project projectFromDb = findById(project.getId());
        project.setStatus(projectFromDb.getStatus());
        return projectRepository.save(project);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(
                () -> new NoRecordFoundException("Unable to find project by id: " + id));
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project addManagers(Long id, List<Long> managerIds) {
        Project project = findById(id);
        List<Manager> managers = managerService.findAllByIds(managerIds);
        checkForValidStatus(project, managers);
        project.addAllManagers(managers);
        return projectRepository.save(project);
    }

    @Override
    public Project addProgrammers(Long id, List<Long> engineerIds) {
        Project project = findById(id);
        List<Programmer> programmers = programmerService.findAllByIds(engineerIds);
        checkForValidStatus(project, programmers);
        project.addAllProgrammers(programmers);
        return projectRepository.save(project);
    }

    @Override
    public Project updateStatus(Long id, String status) {
        Project project = findById(id);
        project.setStatus(Project.Status.getStatus(status));
        return projectRepository.save(project);
    }

    @Override
    public Set<Project> findAllByProgrammerId(Long id) {
        return projectRepository.findAllByProgrammerId(id);
    }

    @Override
    public Set<Project> findAllByManagerId(Long id) {
        return projectRepository.findAllByManagerId(id);
    }

    private void checkForValidStatus(Project project, List<? extends Employee> employees) {
        if (employees.stream().anyMatch(manager ->
                manager.getStatus().equals(Employee.Status.FIRED)
                        || manager.getStatus().equals(Employee.Status.VACATION))) {
            throw new InvalidValueException(
                    "Unable to add employee with status Fired to project.");
        } else if (project.getStatus().equals(Project.Status.FINISHED)) {
            throw new InvalidValueException(
                    "Unable to add employee to project with status Finished.");
        }
    }
}
