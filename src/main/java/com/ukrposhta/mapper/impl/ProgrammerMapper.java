package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.request.ProgrammerRequestDto;
import com.ukrposhta.dto.responce.ProgrammerResponseDto;
import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.mapper.RequestDtoMapper;
import com.ukrposhta.mapper.ResponseDtoMapper;
import com.ukrposhta.model.Programmer;
import com.ukrposhta.model.Technology;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProgrammerMapper implements
        RequestDtoMapper<ProgrammerRequestDto, Programmer>,
        ResponseDtoMapper<ProgrammerResponseDto, Programmer> {
    private final EmployeeMapper employeeMapper;

    @Override
    public Programmer toModel(ProgrammerRequestDto requestDto) {
        checkRequestForValidValues(requestDto);
        Programmer programmer = new Programmer(employeeMapper.toModel(requestDto));
        programmer.setLevel(Programmer.Level
                .getLevel(requestDto.getLevel().toLowerCase()));
        programmer.setType(Programmer.Type
                .getType(requestDto.getType().toLowerCase()));
        return programmer;
    }

    @Override
    public ProgrammerResponseDto toDto(Programmer programmer) {
        checkProgrammerForValidValues(programmer);
        ProgrammerResponseDto responseDto =
                new ProgrammerResponseDto(employeeMapper.toDto(programmer));
        if (programmer.getLevel() != null) {
            responseDto.setLevel(programmer.getLevel().name());
        }
        if (programmer.getType() != null) {
            responseDto.setType(programmer.getType().name());
        }
        if (programmer.getTechnologies() != null) {
            responseDto.setTechnologyIds(programmer.getTechnologies().stream()
                    .map(Technology::getId)
                    .collect(Collectors.toSet()));
        }
        return responseDto;
    }

    private void checkRequestForValidValues(ProgrammerRequestDto requestDto) {
        if (requestDto == null
                || requestDto.getLevel() == null
                || requestDto.getType() == null) {
            throw new InvalidValueException("Request, level or type can't be null.");
        }
    }

    private void checkProgrammerForValidValues(Programmer programmer) {
        if (programmer == null) {
            throw new InvalidValueException("Programmer can't be null");
        }
    }
}
