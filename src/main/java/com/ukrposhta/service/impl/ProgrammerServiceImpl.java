package com.ukrposhta.service.impl;

import com.ukrposhta.exception.NoRecordFoundException;
import com.ukrposhta.model.Programmer;
import com.ukrposhta.repository.ProgrammerRepository;
import com.ukrposhta.service.ProgrammerService;
import com.ukrposhta.service.TechnologyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProgrammerServiceImpl implements ProgrammerService {
    private final ProgrammerRepository programmerRepository;
    private final TechnologyService technologyService;

    @Override
    public Programmer save(Programmer programmer) {
        return programmerRepository.save(programmer);
    }

    @Override
    public Programmer update(Programmer programmer) {
        Programmer programmerFromDb = findById(programmer.getId());
        programmer.setHiringDate(programmerFromDb.getHiringDate());
        return programmerRepository.save(programmer);
    }

    @Override
    public Programmer findById(Long id) {
        return programmerRepository.findById(id).orElseThrow(
                () -> new NoRecordFoundException("Unable to find engineer by id: " + id));
    }

    @Override
    public void delete(Long id) {
        programmerRepository.removeProjects(id);
        programmerRepository.removeTechnologies(id);
        programmerRepository.deleteById(id);
    }

    @Override
    public Programmer addTechnologies(Long id, List<Long> technologyIds) {
        Programmer programmer = findById(id);
        programmer.addAllTechnologies(technologyService.findAllByIds(technologyIds));
        return programmerRepository.save(programmer);
    }

    @Override
    public List<Programmer> findAllByIds(List<Long> ids) {
        return programmerRepository.findAllById(ids);
    }
}
