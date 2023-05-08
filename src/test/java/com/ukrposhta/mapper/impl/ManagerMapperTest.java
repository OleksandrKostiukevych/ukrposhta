package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.parent.EmployeeResponseDto;
import com.ukrposhta.dto.request.ManagerRequestDto;
import com.ukrposhta.dto.responce.ManagerResponseDto;
import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.model.Manager;
import com.ukrposhta.model.parent.Employee;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ManagerMapperTest {
    private ManagerMapper managerMapper;
    private EmployeeMapper employeeMapper;
    private final TestDataGenerator dataGenerator = new TestDataGenerator();

    @BeforeEach
    void beforeEach() {
        dataGenerator.generateEmployeeData();
        dataGenerator.generateManagerData();
        employeeMapper = mock(EmployeeMapper.class);
        managerMapper = new ManagerMapper(employeeMapper);
    }

    @Test
    void toModel_validInput_ok() {
        Employee employee = dataGenerator.getEmployee();
        employee.setId(1L);
        employee.setHiringDate(LocalDate.now());
        ManagerRequestDto managerRequestDto = dataGenerator.getManagerRequestDto();
        when(employeeMapper.toModel(managerRequestDto)).thenReturn(employee);
        Manager actual = managerMapper.toModel(managerRequestDto);
        Manager expected = dataGenerator.getManager();
        expected.setId(1L);
        expected.setHiringDate(LocalDate.now());
        assertEquals(expected, actual, "Manager request dto should be mapped to model.");
    }

    @Test
    void toModel_nullInput_notOk() {
        assertThrows(InvalidValueException.class, () -> managerMapper.toModel(null));
    }

    @Test
    void toDto_validInput_ok() {
        EmployeeResponseDto employeeResponseDto = dataGenerator.getEmployeeResponseDto();
        employeeResponseDto.setId(1L);
        employeeResponseDto.setHiringDate(LocalDate.now());
        Manager manager = dataGenerator.getManager();
        when(employeeMapper.toDto(manager))
                .thenReturn(employeeResponseDto);
        ManagerResponseDto actual = managerMapper.toDto(manager);
        ManagerResponseDto expected = dataGenerator.getManagerResponseDto();
        expected.setId(1L);
        expected.setHiringDate(LocalDate.now());
        assertEquals(expected, actual, "Manager should be mapped to dto.");
    }

    @Test
    void toDto_nullValues_ok() {
        when(employeeMapper.toDto(Mockito.any()))
                .thenReturn(new EmployeeResponseDto());
        ManagerResponseDto actual = managerMapper.toDto(new Manager());
        ManagerResponseDto expected = new ManagerResponseDto();
        assertEquals(expected, actual, "Manager should be mapped to dto.");
    }

    @Test
    void toDto_nullInput_notOk() {
        assertThrows(InvalidValueException.class, () -> managerMapper.toDto(null));
    }
}
