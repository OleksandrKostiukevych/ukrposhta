package com.ukrposhta.dto.parent;

import com.ukrposhta.lib.Email;
import com.ukrposhta.lib.FullName;
import com.ukrposhta.lib.PastDate;
import com.ukrposhta.util.DateTimePatternUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeDto {
    @FullName
    private String fullName;
    @Email
    private String email;
    @NotBlank
    private String gender;
    @PastDate(message = "Birth date can't be a future date.")
    @DateTimeFormat(pattern = DateTimePatternUtil.DATE_PATTERN)
    private LocalDate birthDate;
    @NotNull
    private BigDecimal salary;
    @NotBlank
    private String status;

    public EmployeeDto(EmployeeResponseDto employeeResponseDto) {
        this.fullName = employeeResponseDto.getFullName();
        this.email = employeeResponseDto.getEmail();
        this.gender = employeeResponseDto.getGender();
        this.birthDate = employeeResponseDto.getBirthDate();
        this.salary = employeeResponseDto.getSalary();
        this.status = employeeResponseDto.getStatus();
    }
}
