package com.ukrposhta.dto.responce;

import com.ukrposhta.dto.parent.ProjectDto;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProjectResponseDto extends ProjectDto {
    private Long id;
    private String status;
    private Set<Long> programmerIds;
    private Set<Long> managersIds;
}
