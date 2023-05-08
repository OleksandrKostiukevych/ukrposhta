package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.request.TechnologyRequestDto;
import com.ukrposhta.dto.responce.TechnologyResponseDto;
import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.mapper.RequestDtoMapper;
import com.ukrposhta.mapper.ResponseDtoMapper;
import com.ukrposhta.model.Technology;
import org.springframework.stereotype.Component;

@Component
public class TechnologyMapper implements RequestDtoMapper<TechnologyRequestDto, Technology>,
        ResponseDtoMapper<TechnologyResponseDto, Technology> {
    @Override
    public Technology toModel(TechnologyRequestDto requestDto) {
        if (requestDto == null) {
            throw new InvalidValueException("Request dto can't be null.");
        }
        Technology technology = new Technology();
        technology.setName(requestDto.getName());
        return technology;
    }

    @Override
    public TechnologyResponseDto toDto(Technology technology) {
        if (technology == null) {
            throw new InvalidValueException("Technology can't be null.");
        }
        TechnologyResponseDto responseDto = new TechnologyResponseDto();
        responseDto.setId(technology.getId());
        responseDto.setName(technology.getName());
        return responseDto;
    }
}
