package com.ukrposhta.service;

import com.ukrposhta.model.Manager;
import java.util.List;

public interface ManagerService extends GenericService<Manager> {
    List<Manager> findAllByIds(List<Long> ids);

    Manager update(Manager manager);
}
