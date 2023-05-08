package com.ukrposhta.dto.parent;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TechnologyDto {
    @NotBlank
    private String name;
}
