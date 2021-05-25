package com.example.imkercloudserver.service;

import com.example.imkercloudserver.repository.entity.User;

import org.springframework.stereotype.Service;

@Service
public interface BusinessService {
    public void createRandomBeehives(User user,Long nrBeehives);
    
}
