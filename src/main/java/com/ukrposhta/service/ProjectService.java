package com.ukrposhta.service;

import com.ukrposhta.model.Project;
import java.util.List;
import java.util.Set;

public interface ProjectService extends GenericService<Project> {
    Project addManagers(Long id, List<Long> managerIds);

    Project addProgrammers(Long id, List<Long> engineerIds);

    Project updateStatus(Long id, String status);

    Set<Project> findAllByProgrammerId(Long id);

    Set<Project> findAllByManagerId(Long id);

    Project update(Project project);
}
