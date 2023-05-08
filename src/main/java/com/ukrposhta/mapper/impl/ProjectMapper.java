package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.request.ProjectRequestDto;
import com.ukrposhta.dto.responce.ProjectResponseDto;
import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.mapper.RequestDtoMapper;
import com.ukrposhta.mapper.ResponseDtoMapper;
import com.ukrposhta.model.Manager;
import com.ukrposhta.model.Programmer;
import com.ukrposhta.model.Project;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements RequestDtoMapper<ProjectRequestDto, Project>,
        ResponseDtoMapper<ProjectResponseDto, Project> {
    @Override
    public Project toModel(ProjectRequestDto requestDto) {
        if (requestDto == null) {
            throw new InvalidValueException("Project request dto can't be null.");
        }
        Project project = new Project();
        project.setDescription(requestDto.getDescription());
        project.setDeadline(requestDto.getDeadline());
        return project;
    }

    @Override
    public ProjectResponseDto toDto(Project project) {
        if (project == null) {
            throw new InvalidValueException("Project can't be null.");
        }
        ProjectResponseDto responseDto = new ProjectResponseDto();
        responseDto.setId(project.getId());
        responseDto.setDescription(project.getDescription());
        responseDto.setDeadline(project.getDeadline());
        if (project.getStatus() != null) {
            responseDto.setStatus(project.getStatus().name());
        }
        if (project.getManagers() != null) {
            responseDto.setManagersIds(project.getManagers().stream()
                    .map(Manager::getId)
                    .collect(Collectors.toSet()));
        }
        if (project.getProgrammers() != null) {
            responseDto.setProgrammerIds(project.getProgrammers().stream()
                    .map(Programmer::getId)
                    .collect(Collectors.toSet()));
        }
        return responseDto;
    }
}
