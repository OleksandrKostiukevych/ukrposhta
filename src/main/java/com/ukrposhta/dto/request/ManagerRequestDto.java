package com.ukrposhta.dto.request;

import com.ukrposhta.dto.parent.EmployeeRequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ManagerRequestDto extends EmployeeRequestDto {
    @NotBlank
    private String category;
}
