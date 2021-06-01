package com.example.imkercloudserver.service;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.entity.Beehive;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public interface BeehiveService {
    Set<Long> getIds();

    Set<Beehive> findAll();

    Set<Beehive> findAllBelongingToUserId(final Long userId) throws BusinessException;

    Optional<Beehive> findById(final Long id);

    Long modifyPopulationNr(final Long id, boolean toAdd);

    Integer modifyTemperature(final Long id, boolean toAdd);

    Integer modifyWeight(final Long id, boolean toAdd);

    Beehive save(final Beehive beehive);

    Long saveEmptyBeehive();

    Beehive update(final Beehive beehive);

    void deleteById(final Long id);

//    void checkWeight() throws BusinessException;

}
