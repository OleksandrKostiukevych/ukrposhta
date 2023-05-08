package com.ukrposhta.controller;

import com.ukrposhta.dto.request.ManagerRequestDto;
import com.ukrposhta.dto.responce.ManagerResponseDto;
import com.ukrposhta.dto.responce.ProjectResponseDto;
import com.ukrposhta.mapper.impl.ManagerMapper;
import com.ukrposhta.mapper.impl.ProjectMapper;
import com.ukrposhta.model.Manager;
import com.ukrposhta.service.ManagerService;
import com.ukrposhta.service.ProjectService;
import jakarta.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;
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
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerService managerService;
    private final ManagerMapper managerMapper;
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PostMapping
    public ManagerResponseDto add(@RequestBody @Valid ManagerRequestDto requestDto) {
        return managerMapper.toDto(managerService.save(managerMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    public ManagerResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid ManagerRequestDto requestDto) {
        Manager manager = managerMapper.toModel(requestDto);
        manager.setId(id);
        return managerMapper.toDto(managerService.update(manager));
    }

    @GetMapping("/{id}")
    public ManagerResponseDto get(@PathVariable Long id) {
        return managerMapper.toDto(managerService.findById(id));
    }

    @GetMapping("/{id}/projects")
    public Set<ProjectResponseDto> getProjects(@PathVariable Long id) {
        return projectService.findAllByManagerId(id).stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toSet());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        managerService.delete(id);
    }
}
