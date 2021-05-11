package com.example.imkercloud.service;

import com.example.imkercloud.repository.entity.Beehive;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BeehiveService {
    List<Beehive> findAll();

    Optional<Beehive> findById(final Long id);

    Long modifyPopulationNr(final Long id, boolean toAdd);

    Integer modifyTemperature(final Long id, boolean toAdd);

    Beehive save(final Beehive beehive);

    Beehive update(final Beehive beehive);

    void deleteById(final Long id);
}
