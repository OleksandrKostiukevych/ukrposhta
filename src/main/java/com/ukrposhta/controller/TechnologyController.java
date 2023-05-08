package com.ukrposhta.controller;

import com.ukrposhta.dto.request.TechnologyRequestDto;
import com.ukrposhta.dto.responce.TechnologyResponseDto;
import com.ukrposhta.mapper.impl.TechnologyMapper;
import com.ukrposhta.model.Technology;
import com.ukrposhta.service.TechnologyService;
import jakarta.validation.Valid;
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
@RequestMapping("/technologies")
public class TechnologyController {
    private final TechnologyService technologyService;
    private final TechnologyMapper technologyMapper;

    @PostMapping
    public TechnologyResponseDto add(@RequestBody @Valid TechnologyRequestDto requestDto) {
        return technologyMapper.toDto(technologyService.save(technologyMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    public TechnologyResponseDto update(@PathVariable Long id,
                                        @RequestBody @Valid TechnologyRequestDto requestDto) {
        Technology technology = technologyMapper.toModel(requestDto);
        technology.setId(id);
        return technologyMapper.toDto(technologyService.save(technology));
    }

    @GetMapping("/{id}")
    public TechnologyResponseDto get(@PathVariable Long id) {
        return technologyMapper.toDto(technologyService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        technologyService.delete(id);
    }
}
