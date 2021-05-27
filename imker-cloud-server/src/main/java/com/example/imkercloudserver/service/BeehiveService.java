package com.example.imkercloudserver.service;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.entity.Beehive;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.impl.types.EMailSubjectType;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BeehiveService {
    List<Beehive> findAll();

    Optional<Beehive> findById(final Long id);

    Long modifyPopulationNr(final Long id, boolean toAdd);

    Integer modifyTemperature(final Long id, boolean toAdd);
    
    Integer modifyWeight(final Long id, boolean toAdd);

    Beehive save(final Beehive beehive);

    Beehive update(final Beehive beehive);

    void deleteById(final Long id);

    void checkWeight() throws BusinessException;

    void sendMailToMultipleUsers(EMailSubjectType type, Optional<Number> sufix, List<User> users) throws BusinessException;
}
