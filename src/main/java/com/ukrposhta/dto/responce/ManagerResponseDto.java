package com.ukrposhta.dto.responce;

import com.ukrposhta.dto.parent.EmployeeResponseDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ManagerResponseDto extends EmployeeResponseDto {
    private String category;

    public ManagerResponseDto(EmployeeResponseDto employeeResponseDto) {
        super(employeeResponseDto);
    }
}
