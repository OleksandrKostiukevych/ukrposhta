package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.parent.EmployeeRequestDto;
import com.ukrposhta.dto.parent.EmployeeResponseDto;
import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.mapper.RequestDtoMapper;
import com.ukrposhta.mapper.ResponseDtoMapper;
import com.ukrposhta.model.parent.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements RequestDtoMapper<EmployeeRequestDto, Employee>,
        ResponseDtoMapper<EmployeeResponseDto, Employee> {
    @Override
    public Employee toModel(EmployeeRequestDto requestDto) {
        checkRequestDtoForValidValues(requestDto);
        Employee employee = new Employee();
        employee.setFullName(requestDto.getFullName());
        employee.setEmail(requestDto.getEmail());
        employee.setGender(Employee.Gender.getGender(requestDto.getGender().toLowerCase()));
        employee.setBirthDate(requestDto.getBirthDate());
        employee.setSalary(requestDto.getSalary());
        employee.setStatus(Employee.Status.getStatus(requestDto.getStatus().toLowerCase()));
        return employee;
    }

    @Override
    public EmployeeResponseDto toDto(Employee employee) {
        checkEmployeeForValidValues(employee);
        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setId(employee.getId());
        responseDto.setFullName(employee.getFullName());
        responseDto.setEmail(employee.getEmail());
        if (employee.getGender() != null) {
            responseDto.setGender(employee.getGender().name());
        }
        responseDto.setBirthDate(employee.getBirthDate());
        responseDto.setSalary(employee.getSalary());
        if (employee.getStatus() != null) {
            responseDto.setStatus(employee.getStatus().name());
        }
        responseDto.setHiringDate(employee.getHiringDate());
        return responseDto;
    }

    private void checkRequestDtoForValidValues(EmployeeRequestDto requestDto) {
        if (requestDto == null
                || requestDto.getStatus() == null
                || requestDto.getGender() == null) {
            throw new InvalidValueException("Request object, status or gender can't be null.");
        }
    }

    private void checkEmployeeForValidValues(Employee employee) {
        if (employee == null) {
            throw new InvalidValueException("Employee can't be null.");
        }
    }
}
