package com.ukrposhta.dto.parent;

import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeResponseDto extends EmployeeDto {
    private Long id;
    private LocalDate hiringDate;

    public EmployeeResponseDto(EmployeeResponseDto employeeResponseDto) {
        super(employeeResponseDto);
        this.id = employeeResponseDto.getId();
        this.hiringDate = employeeResponseDto.getHiringDate();
    }
}
