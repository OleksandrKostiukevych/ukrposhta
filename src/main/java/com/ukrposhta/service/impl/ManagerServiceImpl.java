package com.ukrposhta.service.impl;

import com.ukrposhta.exception.NoRecordFoundException;
import com.ukrposhta.model.Manager;
import com.ukrposhta.repository.ManagerRepository;
import com.ukrposhta.service.ManagerService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager update(Manager manager) {
        Manager managerFromDb = findById(manager.getId());
        manager.setHiringDate(managerFromDb.getHiringDate());
        return managerRepository.save(manager);
    }

    @Override
    public Manager findById(Long id) {
        return managerRepository.findById(id).orElseThrow(
                () -> new NoRecordFoundException("Unable to find manager by id: " + id));
    }

    @Override
    public void delete(Long id) {
        managerRepository.removeProjects(id);
        managerRepository.deleteById(id);
    }

    @Override
    public List<Manager> findAllByIds(List<Long> ids) {
        return managerRepository.findAllById(ids);
    }
}
