package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.request.ManagerRequestDto;
import com.ukrposhta.dto.responce.ManagerResponseDto;
import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.mapper.RequestDtoMapper;
import com.ukrposhta.mapper.ResponseDtoMapper;
import com.ukrposhta.model.Manager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ManagerMapper implements
        RequestDtoMapper<ManagerRequestDto, Manager>,
        ResponseDtoMapper<ManagerResponseDto, Manager> {
    private final EmployeeMapper employeeMapper;

    @Override
    public Manager toModel(ManagerRequestDto requestDto) {
        if (requestDto == null) {
            throw new InvalidValueException("Manager request dto can't be null.");
        }
        Manager manager = new Manager(employeeMapper.toModel(requestDto));
        manager.setCategory(Manager.Category
                .getCategory(requestDto.getCategory().toLowerCase()));
        return manager;
    }

    @Override
    public ManagerResponseDto toDto(Manager manager) {
        if (manager == null) {
            throw new InvalidValueException("Manager can't be null.");
        }
        ManagerResponseDto responseDto = new ManagerResponseDto(employeeMapper.toDto(manager));
        if (manager.getCategory() != null) {
            responseDto.setCategory(manager.getCategory().name());
        }
        return responseDto;
    }
}
