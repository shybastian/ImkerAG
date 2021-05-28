package com.example.imkercloudserver.service.impl;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.BeehiveRepository;
import com.example.imkercloudserver.repository.UserRepository;
import com.example.imkercloudserver.repository.entity.Beehive;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.BeehiveService;
import com.example.imkercloudserver.service.MailService;
import com.example.imkercloudserver.service.impl.types.EMailSubjectType;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BeehiveServiceImpl implements BeehiveService {
    private final BeehiveRepository beehiveRepository;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final List<Long> notifiedBeehives;


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
            entity.setTemperature(model.getTemperature());
            entity.setWeight(model.getWeight());
            entity.setPopulationNr(model.getPopulationNr());
            entity.setActivityType(model.getActivityType());
        }
    }

//    @Scheduled(fixedDelay = 86400000 / 2)
//    @Transactional
//    @Override
//    public void checkWeight() throws BusinessException {
//        final List<Beehive> list = this.beehiveRepository.findAll();
//        for (final Beehive b : list) {
//            if (!this.notifiedBeehives.contains(b.getId())) {
//                if (b.getWeight() > 10) {
//                    final Set<User> users = b.getUsers();
//                    this.mailService.sendMailToMultipleUsers(EMailSubjectType.WEIGHT_TOO_HIGH, Optional.of(b.getWeight()), users);
//
//                }
//                if (b.getTemperature() > 35) {
//                    final Set<User> users = b.getUsers();
//                    this.mailService.sendMailToMultipleUsers(EMailSubjectType.TEMPERATURE_TOO_HIGH, Optional.of(b.getTemperature()), users);
//
//                }
//                if (b.getPopulationNr() > 200) {
//                    final Set<User> users = b.getUsers();
//                    this.mailService.sendMailToMultipleUsers(EMailSubjectType.POPULATION_TOO_HIGH, Optional.of(b.getPopulationNr()), users);
//
//                }
//                if (b.getActivityType() == ActivityType.HYPERACTIVE) {
//                    final Set<User> users = b.getUsers();
//                    this.mailService.sendMailToMultipleUsers(EMailSubjectType.HYPERACTIVE, Optional.empty(), users);
//
//                }
//                this.notifiedBeehives.add(b.getId());
//            }
//        }
//    }

    @Scheduled(fixedDelay = 86400000)
    //every day 
    public void resetNotifiedBeehives() {
        this.notifiedBeehives.clear();
    }

    @Override
    public void sendMailToMultipleUsers(final EMailSubjectType type, final Optional<Number> sufix, final Set<User> users) throws BusinessException {
        this.mailService.sendMailToMultipleUsers(type, sufix, users);
    }
}
