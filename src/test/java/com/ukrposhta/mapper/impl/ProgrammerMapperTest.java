package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.parent.EmployeeResponseDto;
import com.ukrposhta.dto.request.ProgrammerRequestDto;
import com.ukrposhta.dto.responce.ProgrammerResponseDto;
import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.model.Programmer;
import com.ukrposhta.model.Technology;
import com.ukrposhta.model.parent.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammerMapperTest {
    private ProgrammerMapper programmerMapper;
    private EmployeeMapper employeeMapper;
    private final TestDataGenerator dataGenerator = new TestDataGenerator();

    @BeforeEach
    void setUp() {
        dataGenerator.generateTechnologyData();
        dataGenerator.generateEmployeeData();
        dataGenerator.generateProgrammerData();
        employeeMapper = mock(EmployeeMapper.class);
        programmerMapper = new ProgrammerMapper(employeeMapper);
    }

    @Test
    public void toModel_validInput_ok() {
        Employee employee = dataGenerator.getEmployee();
        employee.setId(1L);
        employee.setHiringDate(LocalDate.now());
        ProgrammerRequestDto programmerRequestDto = dataGenerator.getProgrammerRequestDto();
        when(employeeMapper.toModel(programmerRequestDto))
                .thenReturn(employee);
        Programmer actual = programmerMapper.toModel(programmerRequestDto);
        Programmer expected = dataGenerator.getProgrammer();
        expected.setId(1L);
        expected.setHiringDate(LocalDate.now());
        assertEquals(expected, actual, "Programmer request dto should be mapped to model.");
    }

    @Test
    public void toModel_invalidEnumValues_notOk() {
        Employee employee = dataGenerator.getEmployee();
        when(employeeMapper.toModel(Mockito.any())).thenReturn(employee);
        ProgrammerRequestDto programmerRequestDto = dataGenerator.getProgrammerRequestDto();
        programmerRequestDto.setLevel("Invalid");
        assertThrows(InvalidValueException.class,
                () -> programmerMapper.toModel(programmerRequestDto));
        programmerRequestDto.setLevel(null);
        assertThrows(InvalidValueException.class,
                () -> programmerMapper.toModel(programmerRequestDto));
        programmerRequestDto.setLevel("JUNIOR");
        programmerRequestDto.setType("Invalid");
        assertThrows(InvalidValueException.class,
                () -> programmerMapper.toModel(programmerRequestDto));
        programmerRequestDto.setType(null);
        assertThrows(InvalidValueException.class,
                () -> programmerMapper.toModel(programmerRequestDto));
    }

    @Test
    public void toModel_nullInput_notOk() {
        assertThrows(InvalidValueException.class, () -> programmerMapper.toModel(null));
    }

    @Test
    public void toDto_validInput_ok() {
        EmployeeResponseDto employeeResponseDto = dataGenerator.getEmployeeResponseDto();
        employeeResponseDto.setId(1L);
        employeeResponseDto.setHiringDate(LocalDate.now());
        Programmer programmer = dataGenerator.getProgrammer();
        Technology technology = dataGenerator.getTechnology();
        technology.setId(1L);
        programmer.addTechnology(technology);
        when(employeeMapper.toDto(programmer))
                .thenReturn(employeeResponseDto);
        ProgrammerResponseDto actual = programmerMapper.toDto(programmer);
        ProgrammerResponseDto expected = dataGenerator.getProgrammerResponseDto();
        expected.setId(1L);
        expected.setHiringDate(LocalDate.now());
        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        expected.setTechnologyIds(ids);
        assertEquals(expected, actual, "Programmer should be mapped to dto.");
    }

    @Test
    public void toDto_nullInput_notOk() {
        assertThrows(InvalidValueException.class, () -> programmerMapper.toDto(null));
    }

    @Test
    public void toDto_nullEnumValues_ok() {
        Programmer programmer = dataGenerator.getProgrammer();
        programmer.setType(null);
        programmer.setLevel(null);
        when(employeeMapper.toDto(programmer))
                .thenReturn(dataGenerator.getEmployeeResponseDto());
        ProgrammerResponseDto actual = programmerMapper.toDto(programmer);
        ProgrammerResponseDto expected = dataGenerator.getProgrammerResponseDto();
        expected.setTechnologyIds(new HashSet<>());
        expected.setType(null);
        expected.setLevel(null);
        assertEquals(expected, actual, "Programmer should be mapped to dto");
    }
}
