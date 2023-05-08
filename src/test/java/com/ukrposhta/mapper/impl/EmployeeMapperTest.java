package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.parent.EmployeeRequestDto;
import com.ukrposhta.dto.parent.EmployeeResponseDto;
import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.model.parent.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeMapperTest {
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final TestDataGenerator dataGenerator = new TestDataGenerator();

    @BeforeEach
    void beforeEach() {
        dataGenerator.generateEmployeeData();
    }

    @Test
    void toModel_nullInput_notOk() {
        assertThrows(InvalidValueException.class, () -> employeeMapper.toModel(null));
    }

    @Test
    void toModel_nullValues_notOk() {
        EmployeeRequestDto requestDtoWithNullValues = new EmployeeRequestDto();
        assertThrows(InvalidValueException.class,
                () -> employeeMapper.toModel(requestDtoWithNullValues));
    }

    @Test
    void toModel_invalidEnumValues_notOk() {
        EmployeeRequestDto employeeRequestDto = dataGenerator.getEmployeeRequestDto();
        employeeRequestDto.setGender("Invalid");
        assertThrows(InvalidValueException.class, () -> employeeMapper.toModel(employeeRequestDto));
        employeeRequestDto.setGender("Male");
        employeeRequestDto.setStatus("Invalid");
        assertThrows(InvalidValueException.class, () -> employeeMapper.toModel(employeeRequestDto));
    }

    @Test
    void toDto_validInput_ok() {
        EmployeeResponseDto actual = employeeMapper.toDto(dataGenerator.getEmployee());
        EmployeeResponseDto expected = dataGenerator.getEmployeeResponseDto();
        assertEquals(expected, actual, "Employee should be mapped to dto.");
    }

    @Test
    void toDto_nullInput_notOk() {
        assertThrows(InvalidValueException.class, () -> employeeMapper.toDto(null));
    }

    @Test
    void toDto_nullEnumValues_ok() {
        Employee employee = dataGenerator.getEmployee();
        employee.setGender(null);
        employee.setStatus(null);
        EmployeeResponseDto actual = employeeMapper.toDto(employee);
        EmployeeResponseDto expected = dataGenerator.getEmployeeResponseDto();
        expected.setGender(null);
        expected.setStatus(null);
        assertEquals(expected, actual, "Employee should be mapped to dto.");
    }
}
