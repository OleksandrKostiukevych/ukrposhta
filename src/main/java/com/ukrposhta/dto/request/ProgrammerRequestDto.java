package com.ukrposhta.dto.request;

import com.ukrposhta.dto.parent.EmployeeRequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgrammerRequestDto extends EmployeeRequestDto {
    @NotBlank
    private String level;
    @NotBlank
    private String type;
}
