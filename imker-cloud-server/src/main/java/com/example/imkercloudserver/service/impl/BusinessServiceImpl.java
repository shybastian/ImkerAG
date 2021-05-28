package com.example.imkercloudserver.service.impl;

import com.example.imkercloudserver.repository.BeehiveRepository;
import com.example.imkercloudserver.repository.UserRepository;
import com.example.imkercloudserver.repository.entity.ActivityType;
import com.example.imkercloudserver.repository.entity.Beehive;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final BeehiveRepository beehiveRepository;
    private final UserRepository userRepository;

    public Set<Beehive> createRandomBeehives(Long nrBeehives) {
        this.cleanUpBeehivesReferences();

        final Set<Beehive> beehives = new HashSet<>();
        if (nrBeehives < 10) nrBeehives = 10L;
        for (int i = 0; i < nrBeehives; i++) {
            beehives.add(this.beehiveRepository.save(this.createBeehive()));
        }
        return beehives;
    }

    @Override
    public void assignBeehivesToUsers(final Set<Beehive> beehiveSet) {
        final List<User> users = this.userRepository.findAll();
        final int size = users.size();
        for (final Beehive beehive : beehiveSet) {
            users.get(this.generateRandomNumber(size)).getBeehives().add(beehive);
        }
        this.userRepository.saveAll(users);
    }

    private Beehive createBeehive() {
        final Beehive beehive = new Beehive();
        beehive.setPopulationNr(Long.valueOf(this.generateRandomNumber(50, 300)));
        beehive.setTemperature(this.generateRandomNumber(10, 60));
        beehive.setWeight(this.generateRandomNumber(5, 25));
        beehive.setActivityType(ActivityType.values()[this.generateRandomNumber(0, ActivityType.values().length)]);
        return beehive;
    }

    private Integer generateRandomNumber(final int low, final int high) {
        final Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    private Integer generateRandomNumber(final int bound) {
        final Random random = new Random();
        return random.nextInt(bound);
    }

    private void cleanUpBeehivesReferences() {
        final List<User> users = this.userRepository.findAll();
        for (final User user : users) {
            user.getBeehives().clear();
        }
        this.userRepository.saveAll(users);
        this.beehiveRepository.deleteAll(this.beehiveRepository.findAll());
    }
}
