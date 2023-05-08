package com.ukrposhta.controller;

import com.ukrposhta.dto.request.ProjectRequestDto;
import com.ukrposhta.dto.responce.ProjectResponseDto;
import com.ukrposhta.mapper.impl.ProjectMapper;
import com.ukrposhta.model.Project;
import com.ukrposhta.service.ProjectService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PostMapping
    public ProjectResponseDto add(@RequestBody @Valid ProjectRequestDto requestDto) {
        return projectMapper.toDto(projectService.save(projectMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    public ProjectResponseDto update(@PathVariable Long id,
            @RequestBody @Valid ProjectRequestDto requestDto) {
        Project project = projectMapper.toModel(requestDto);
        project.setId(id);
        return projectMapper.toDto(projectService.update(project));
    }

    @PutMapping("/{id}/status")
    public ProjectResponseDto updateStatus(@PathVariable Long id,
            @RequestBody String status) {
        return projectMapper.toDto(projectService.updateStatus(id, status));
    }

    @PostMapping("/{id}/programmers")
    public ProjectResponseDto addProgrammers(@PathVariable Long id,
                                             @RequestBody List<Long> engineerIds) {
        return projectMapper.toDto(projectService.addProgrammers(id, engineerIds));
    }

    @PostMapping("/{id}/managers")
    public ProjectResponseDto addManagers(@PathVariable Long id,
            @RequestBody List<Long> managerIds) {
        return projectMapper.toDto(projectService.addManagers(id, managerIds));
    }

    @GetMapping("/{id}")
    public ProjectResponseDto get(@PathVariable Long id) {
        return projectMapper.toDto(projectService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}
