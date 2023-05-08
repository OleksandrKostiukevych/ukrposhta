package com.ukrposhta.dto.parent;

import com.ukrposhta.lib.FutureDate;
import com.ukrposhta.util.DateTimePatternUtil;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@EqualsAndHashCode
public class ProjectDto {
    @NotBlank
    private String description;
    @FutureDate(message = "Deadline can't be a past date.")
    @DateTimeFormat(pattern = DateTimePatternUtil.DATE_PATTERN)
    private LocalDate deadline;
}
