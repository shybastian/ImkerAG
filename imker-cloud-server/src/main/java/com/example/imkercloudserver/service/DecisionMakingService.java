package com.example.imkercloudserver.service;

import com.example.imkercloudserver.repository.entity.Beehive;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public interface DecisionMakingService {
    boolean shouldEmailBeSent(Beehive beehive) throws FileNotFoundException;
}
