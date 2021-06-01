package com.example.imkercloudserver.service.impl;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.BeehiveRepository;
import com.example.imkercloudserver.repository.UserRepository;
import com.example.imkercloudserver.repository.entity.Beehive;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.BeehiveService;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BeehiveServiceImpl implements BeehiveService {
    private final BeehiveRepository beehiveRepository;
    private final UserRepository userRepository;


    @Override
    public Set<Long> getIds() {
        final Set<Long> set = new HashSet<>();
        this.beehiveRepository.findAll().forEach(b -> set.add(b.getId()));
        return set;
    }

    @Override
    public Set<Beehive> findAll() {
        return Sets.newHashSet(this.beehiveRepository.findAll());
    }

    @Override
    public Optional<Beehive> findById(final Long id) {
        return this.beehiveRepository.findById(id);
    }

    @Override
    public Set<Beehive> findAllBelongingToUserId(final Long userId) throws BusinessException {
        final Optional<User> optional = this.userRepository.findById(userId);
        if (optional.isPresent()) {
            return optional.get().getBeehives();
        } else {
            throw new BusinessException("There was no user with id: " + userId + "found!");
        }
    }

    @Override
    public Long modifyPopulationNr(final Long id, final boolean toAdd) {
        final Beehive entity = this.beehiveRepository.getOne(id);
        if (toAdd) {
            entity.setPopulationNr(entity.getPopulationNr() + 1);
        } else {
            entity.setPopulationNr(entity.getPopulationNr() - 1);
        }
        this.beehiveRepository.save(entity);
        return entity.getPopulationNr();
    }

    @Override
    public Integer modifyTemperature(final Long id, final boolean toAdd) {
        final Beehive entity = this.beehiveRepository.getOne(id);
        if (toAdd) {
            entity.setTemperature(entity.getTemperature() + 1);
        } else {
            entity.setTemperature(entity.getTemperature() - 1);
        }
        this.beehiveRepository.save(entity);
        return entity.getTemperature();
    }

    @Override
    public Integer modifyWeight(final Long id, final boolean toAdd) {
        final Beehive entity = this.beehiveRepository.getOne(id);
        if (toAdd) {
            entity.setWeight(entity.getWeight() + 1);
        } else {
            entity.setWeight(entity.getWeight() - 1);
        }
        this.beehiveRepository.save(entity);
        return entity.getWeight();

    }

    @Override
    public Beehive save(final Beehive beehive) {
        return this.beehiveRepository.save(beehive);
    }

    @Override
    public Long saveEmptyBeehive() {
        return this.beehiveRepository.save(new Beehive()).getId();
    }

    @Override
    public Beehive update(final Beehive beehive) {
        final Beehive entity = this.beehiveRepository.getOne(beehive.getId());
        this.mapModelToEntity(beehive, entity);
        this.beehiveRepository.save(entity);
        return entity;
    }

    @Override
    public void deleteById(final Long id) {
        this.beehiveRepository.deleteById(id);
    }

    private void mapModelToEntity(final Beehive model, final Beehive entity) {
        if (model != null && entity != null) {
            if (model.getTemperature() != null) {
                entity.setTemperature(model.getTemperature());
            }
            if (model.getWeight() != null) {
                entity.setWeight(model.getWeight());
            }
            if (model.getPopulationNr() != null) {
                entity.setPopulationNr(model.getPopulationNr());
            }
            if (model.getActivityType() != null) {
                entity.setActivityType(model.getActivityType());
            }
        }
    }
}
