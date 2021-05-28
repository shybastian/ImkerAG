package com.example.imkercloudserver.service;

import com.example.imkercloudserver.repository.entity.Beehive;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BusinessService {
    Set<Beehive> createRandomBeehives(Long nrBeehives);

    void assignBeehivesToUsers(final Set<Beehive> beehiveSet);
}
