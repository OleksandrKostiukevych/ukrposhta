package com.ukrposhta.controller;

import com.ukrposhta.dto.request.ProgrammerRequestDto;
import com.ukrposhta.dto.responce.ProgrammerResponseDto;
import com.ukrposhta.dto.responce.ProjectResponseDto;
import com.ukrposhta.mapper.impl.ProgrammerMapper;
import com.ukrposhta.mapper.impl.ProjectMapper;
import com.ukrposhta.model.Programmer;
import com.ukrposhta.service.ProgrammerService;
import com.ukrposhta.service.ProjectService;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/programmers")
public class ProgrammerController {
    private final ProgrammerService programmerService;
    private final ProgrammerMapper programmerMapper;
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PostMapping
    public ProgrammerResponseDto add(@RequestBody @Valid ProgrammerRequestDto requestDto) {
        return programmerMapper.toDto(programmerService.save(programmerMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    public ProgrammerResponseDto update(@PathVariable Long id,
                                        @RequestBody @Valid ProgrammerRequestDto requestDto) {
        Programmer programmer = programmerMapper.toModel(requestDto);
        programmer.setId(id);
        return programmerMapper.toDto(programmerService.update(programmer));
    }

    @PostMapping("/{id}/technologies")
    public ProgrammerResponseDto addTechnologies(@PathVariable Long id,
                                                 @RequestBody List<Long> technologyIds) {
        return programmerMapper.toDto(programmerService.addTechnologies(id, technologyIds));
    }

    @GetMapping("/{id}")
    public ProgrammerResponseDto get(@PathVariable Long id) {
        return programmerMapper.toDto(programmerService.findById(id));
    }

    @GetMapping("/{id}/projects")
    public Set<ProjectResponseDto> getProjects(@PathVariable Long id) {
        return projectService.findAllByProgrammerId(id).stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toSet());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        programmerService.delete(id);
    }
}
