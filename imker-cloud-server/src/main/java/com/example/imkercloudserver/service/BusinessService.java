package com.example.imkercloudserver.service;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.entity.Beehive;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Set;

@Service
public interface BusinessService {
    Set<Beehive> getAllBeehives();

    Set<Beehive> createRandomBeehives(Long nrBeehives);

    void assignBeehivesToUsers(final Set<Beehive> beehiveSet);

    void clearBeehives();

    void shakeTheBeehivesUp();

    void issueNotifications() throws BusinessException, FileNotFoundException;
}
