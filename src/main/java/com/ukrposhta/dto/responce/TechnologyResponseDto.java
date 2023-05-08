package com.ukrposhta.dto.responce;

import com.ukrposhta.dto.parent.TechnologyDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class TechnologyResponseDto extends TechnologyDto {
    private Long id;
}
