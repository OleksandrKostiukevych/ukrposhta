package com.ukrposhta.mapper.impl;

import com.ukrposhta.dto.request.TechnologyRequestDto;
import com.ukrposhta.dto.responce.TechnologyResponseDto;
import com.ukrposhta.exception.InvalidValueException;
import com.ukrposhta.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TechnologyMapperTest {
    private final TechnologyMapper technologyMapper = new TechnologyMapper();
    private final TestDataGenerator dataGenerator = new TestDataGenerator();

    @BeforeEach
    void setUp() {
        dataGenerator.generateTechnologyData();
    }

    @Test
    void toModel_validInput_ok() {
        TechnologyRequestDto technologyRequestDto = dataGenerator.getTechnologyRequestDto();
        Technology actual = technologyMapper.toModel(technologyRequestDto);
        Technology expected = dataGenerator.getTechnology();
        assertEquals(expected, actual, "Technology request dto should be mapped to model.");
    }

    @Test
    void toModel_nullInput_notOk() {
        assertThrows(InvalidValueException.class, () -> technologyMapper.toModel(null));
    }

    @Test
    void toModel_nullValues_ok() {
        Technology actual = technologyMapper.toModel(new TechnologyRequestDto());
        Technology expected = new Technology();
        assertEquals(expected, actual, "Technology request dto should be mapped to model.");
    }

    @Test
    void toDto_validInput_ok() {
        Technology technology = dataGenerator.getTechnology();
        technology.setId(1L);
        TechnologyResponseDto actual = technologyMapper.toDto(technology);
        TechnologyResponseDto expected = dataGenerator.getTechnologyResponseDto();
        expected.setId(1L);
        assertEquals(expected, actual, "Technology should be mapped to dto.");
    }

    @Test
    void toDto_nullInput_notOk() {
        assertThrows(InvalidValueException.class, () -> technologyMapper.toDto(null));
    }

    @Test
    void toDto_nullValues_ok() {
        TechnologyResponseDto actual = technologyMapper.toDto(new Technology());
        TechnologyResponseDto expected = new TechnologyResponseDto();
        assertEquals(expected, actual, "Technology should be mapped to dto.");
    }
}
