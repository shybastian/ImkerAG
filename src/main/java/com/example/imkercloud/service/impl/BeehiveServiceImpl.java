package com.example.imkercloud.service.impl;

import com.example.imkercloud.repository.BeehiveRepository;
import com.example.imkercloud.repository.entity.Beehive;
import com.example.imkercloud.service.BeehiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeehiveServiceImpl implements BeehiveService {
    private final BeehiveRepository beehiveRepository;

    @Override
    public List<Beehive> findAll() {
        return this.beehiveRepository.findAll();
    }

    @Override
    public Optional<Beehive> findById(final Long id) {
        return this.beehiveRepository.findById(id);
    }

    @Override
    public Long modifyPopulationNr(Long id, boolean toAdd) {
        Beehive entity = this.beehiveRepository.getOne(id);
        if (toAdd) {
            entity.setPopulationNr(entity.getPopulationNr() + 1);
        } else {
            entity.setPopulationNr(entity.getPopulationNr() - 1);
        }
        this.beehiveRepository.save(entity);
        return entity.getPopulationNr();
    }

    @Override
    public Beehive save(final Beehive beehive) {
        return this.beehiveRepository.save(beehive);
    }

    @Override
    public Beehive update(final Beehive beehive) {
        Beehive entity = this.beehiveRepository.getOne(beehive.getId());
        mapModelToEntity(beehive, entity);
        this.beehiveRepository.save(entity);
        return entity;
    }

    @Override
    public void deleteById(final Long id) {
        this.beehiveRepository.deleteById(id);
    }

    private void mapModelToEntity(Beehive model, Beehive entity) {
        if (model != null && entity != null) {
            entity.setTemperature(model.getTemperature());
            entity.setWeight(model.getWeight());
            entity.setPopulationNr(model.getPopulationNr());
            entity.setActivityType(model.getActivityType());
        }
    }
}
