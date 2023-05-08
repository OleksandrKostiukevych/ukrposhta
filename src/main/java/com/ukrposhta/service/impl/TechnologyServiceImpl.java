package com.ukrposhta.service.impl;

import com.ukrposhta.exception.NoRecordFoundException;
import com.ukrposhta.model.Technology;
import com.ukrposhta.repository.TechnologyRepository;
import com.ukrposhta.service.TechnologyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TechnologyServiceImpl implements TechnologyService {
    private final TechnologyRepository technologyRepository;

    @Override
    public Technology save(Technology technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public Technology findById(Long id) {
        return technologyRepository.findById(id).orElseThrow(
                () -> new NoRecordFoundException("Unable to find technology by id: " + id));
    }

    @Override
    public void delete(Long id) {
        technologyRepository.deleteProgrammers(id);
        technologyRepository.deleteById(id);
    }

    @Override
    public List<Technology> findAllByIds(List<Long> ids) {
        return technologyRepository.findAllById(ids);
    }
}
