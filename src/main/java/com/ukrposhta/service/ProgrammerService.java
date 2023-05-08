package com.ukrposhta.service;

import com.ukrposhta.model.Programmer;
import java.util.List;

public interface ProgrammerService extends GenericService<Programmer> {
    Programmer addTechnologies(Long id, List<Long> technologyIds);

    List<Programmer> findAllByIds(List<Long> ids);

    Programmer update(Programmer programmer);
}
