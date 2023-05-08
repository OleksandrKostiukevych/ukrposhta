package com.ukrposhta.dto.responce;

import com.ukrposhta.dto.parent.EmployeeResponseDto;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProgrammerResponseDto extends EmployeeResponseDto {
    private String level;
    private String type;
    private Set<Long> technologyIds;

    public ProgrammerResponseDto(EmployeeResponseDto employeeResponseDto) {
        super(employeeResponseDto);
    }
}
