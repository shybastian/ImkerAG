package com.example.imkercloudserver.service.impl;

import java.util.Collections;
import java.util.Random;

import com.example.imkercloudserver.repository.BeehiveRepository;
import com.example.imkercloudserver.repository.UserRepository;
import com.example.imkercloudserver.repository.entity.ActivityType;
import com.example.imkercloudserver.repository.entity.Beehive;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.BusinessService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService{
    private final BeehiveRepository beehiveRepository;
    private final UserRepository userRepository;
    public void createRandomBeehives(User user,Long nrBeehives){
        for(int i = 0; i < nrBeehives; i++)
        {
            Beehive b = new Beehive();
            b.setPopulationNr(GenerateRandomNumberLong(50,300));
            b.setTemperature(GenerateRandomNumberInt(10,60));
            b.setWeight(GenerateRandomNumberInt(5,25));
            b.setActivityType(ActivityType.values()[GenerateRandomNumberInt(0,ActivityType.values().length)]);
           // b.setUsers(Collections.singletonList(user));
            b = beehiveRepository.save(b);
            user.getBeehives().add(b);
            
        }
        userRepository.save(user);
       
        
    }
    private Long GenerateRandomNumberLong(int low, int high){
    Random r = new Random();
    return Long.valueOf(r.nextInt(high-low) + low);
    }
    private Integer GenerateRandomNumberInt(int low, int high){
        Random r = new Random();
        return r.nextInt(high-low) + low;
    }
}
