package com.ukrposhta.service;

import com.ukrposhta.model.Technology;
import java.util.List;

public interface TechnologyService extends GenericService<Technology> {
    List<Technology> findAllByIds(List<Long> ids);
}
