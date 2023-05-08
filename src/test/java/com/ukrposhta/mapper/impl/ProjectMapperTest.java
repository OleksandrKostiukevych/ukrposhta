package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.request.ProjectRequestDto;
import com.ukrposhta.dto.responce.ProjectResponseDto;
import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.model.Programmer;
import com.ukrposhta.model.Manager;
import com.ukrposhta.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectMapperTest {
    private final ProjectMapper projectMapper = new ProjectMapper();
    private final TestDataGenerator dataGenerator = new TestDataGenerator();

    @BeforeEach
    void setUp() {
        dataGenerator.generateEmployeeData();
        dataGenerator.generateProjectData();
        dataGenerator.generateProgrammerData();
        dataGenerator.generateManagerData();
    }

    @Test
    void toModel_validInput_ok() {
        Project actual = projectMapper.toModel(dataGenerator.getProjectRequestDto());
        Project expected = dataGenerator.getProject();
        expected.setStatus(null);
        assertEquals(expected, actual, "Project request dto should be mapped to model");
    }

    @Test
    void toModel_nullInput_notOk() {
        assertThrows(InvalidValueException.class, () -> projectMapper.toModel(null));
    }

    @Test
    void toModel_nullValues_ok() {
        ProjectRequestDto projectRequestDto = dataGenerator.getProjectRequestDto();
        projectRequestDto.setDeadline(null);
        projectRequestDto.setDescription(null);
        Project actual = projectMapper.toModel(projectRequestDto);
        Project expected = dataGenerator.getProject();
        expected.setDescription(null);
        expected.setDeadline(null);
        expected.setStatus(null);
        assertEquals(expected, actual, "Project request dto should be mapped to model.");
    }

    @Test
    void toDto_validInput_ok() {
        Project project = dataGenerator.getProject();
        project.setId(1L);
        Programmer programmer = dataGenerator.getProgrammer();
        programmer.setId(1L);
        project.addProgrammer(programmer);
        Manager manager = dataGenerator.getManager();
        manager.setId(1L);
        project.addManager(manager);
        ProjectResponseDto actual = projectMapper.toDto(project);
        ProjectResponseDto expected = dataGenerator.getProjectResponseDto();
        expected.setId(1L);
        expected.setManagersIds(Set.of(1L));
        expected.setProgrammerIds(Set.of(1L));
        assertEquals(expected, actual, "Project should be mapped to dto");
    }

    @Test
    void toDto_nullSets_ok() {
        Project project = dataGenerator.getProject();
        project.setProgrammers(null);
        project.setManagers(null);
        ProjectResponseDto actual = projectMapper.toDto(project);
        ProjectResponseDto expected = dataGenerator.getProjectResponseDto();
        assertEquals(expected, actual, "Project should be mapped to dto.");
    }

    @Test
    void toDto_nullInput_notOk() {
        assertThrows(InvalidValueException.class, () -> projectMapper.toDto(null));
    }
}
