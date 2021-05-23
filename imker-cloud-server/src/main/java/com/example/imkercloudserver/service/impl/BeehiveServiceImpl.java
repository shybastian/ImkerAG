package com.example.imkercloudserver.service.impl;

import com.example.imkercloudserver.repository.BeehiveRepository;
import com.example.imkercloudserver.repository.entity.Beehive;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.BeehiveService;
import com.example.imkercloudserver.service.MailService;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BeehiveServiceImpl implements BeehiveService {
    private final BeehiveRepository beehiveRepository;
    private final MailService mailService;
    private List <Long> notifiedBeehives = new ArrayList<>();


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
    public Integer modifyTemperature(Long id, boolean toAdd) {
        Beehive entity = this.beehiveRepository.getOne(id);
        if (toAdd) {
            entity.setTemperature(entity.getTemperature() + 1);
        } else {
            entity.setTemperature(entity.getTemperature() - 1);
        }
        this.beehiveRepository.save(entity);
        return entity.getTemperature();
    }

    @Override
    public Integer modifyWeight(final Long id, boolean toAdd){
        Beehive entity = this.beehiveRepository.getOne(id);
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
    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void checkWeight() {
        List <Beehive> list = this.beehiveRepository.findAll();
        for(Beehive b : list){
            if(b.getWeight() > 10 && !notifiedBeehives.contains(b.getId())){
                List<User> users = b.getUsers();
                mailService.SendMail(b.getWeight(),users);
                notifiedBeehives.add(b.getId());
            }
        

        }
        
    }
}
