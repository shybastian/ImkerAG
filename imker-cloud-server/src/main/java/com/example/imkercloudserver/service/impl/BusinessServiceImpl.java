package com.example.imkercloudserver.service.impl;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.BeehiveRepository;
import com.example.imkercloudserver.repository.NotificationRepository;
import com.example.imkercloudserver.repository.UserRepository;
import com.example.imkercloudserver.repository.entity.*;
import com.example.imkercloudserver.service.BusinessService;
import com.example.imkercloudserver.service.DecisionMakingService;
import com.example.imkercloudserver.service.MailService;
import com.example.imkercloudserver.service.impl.types.EMailSubjectType;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final MailService mailService;
    private final DecisionMakingService decisionMakingService;

    private final NotificationRepository notificationRepository;
    private final BeehiveRepository beehiveRepository;
    private final UserRepository userRepository;

    @Override
    public void issueNotifications() throws BusinessException, FileNotFoundException {
        final List<Beehive> allBeehives = this.beehiveRepository.findAll();
        for (final Beehive beehive : allBeehives) {
            this.checkBeehive(beehive);
        }
    }

    @Override
    public Set<Beehive> getAllBeehives() {
        return new HashSet<>(this.beehiveRepository.findAll());
    }

    public Set<Beehive> createRandomBeehives(final Long nrBeehives) {
        this.cleanUpBeehivesReferences();

        final Set<Beehive> beehives = new HashSet<>();
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

    @Override
    public void clearBeehives() {
        this.cleanUpBeehivesReferences();
    }

    @Override
    public void shakeTheBeehivesUp() {
        final List<Beehive> list = this.beehiveRepository.findAll();
        for (final Beehive b : list) {
            b.setPopulationNr(Long.valueOf(this.generateRandomNumber(50, 300)));
            b.setTemperature(this.generateRandomNumber(10, 60));
            b.setWeight(this.generateRandomNumber(5, 25));
            b.setActivityType(ActivityType.values()[this.generateRandomNumber(0, ActivityType.values().length)]);
        }
        this.beehiveRepository.saveAll(list);
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
            user.getNotifications().clear();
        }
        this.userRepository.saveAll(users);
        this.notificationRepository.deleteAll(this.notificationRepository.findAll());
        this.beehiveRepository.deleteAll(this.beehiveRepository.findAll());
    }

    private void checkBeehive(final Beehive beehive) throws BusinessException, FileNotFoundException {
        for (final User user : beehive.getUsers()) {
            final List<Notification> notifications = new ArrayList<>();
            if (beehive.getWeight() > 10) {
                final Notification notification = new Notification();
                notification.setNotificationType(NotificationType.INFO);
                notification.setNotificationReadStatusType(NotificationReadStatusType.UNREAD);
                notification.setMessage(EMailSubjectType.WEIGHT_TOO_HIGH.message);
                notification.setDateTime(new Date(new DateTime().getMillis()));
                notification.setBeehive(beehive);
                notification.setUser(user);

                notifications.add(notification);
            }
            if (beehive.getTemperature() > 35) {
                final Notification notification = new Notification();
                notification.setNotificationType(NotificationType.INFO);
                notification.setNotificationReadStatusType(NotificationReadStatusType.UNREAD);
                notification.setMessage(EMailSubjectType.TEMPERATURE_TOO_HIGH.message);
                notification.setDateTime(new Date(new DateTime().getMillis()));
                notification.setBeehive(beehive);
                notification.setUser(user);

                notifications.add(notification);
            }
            if (beehive.getTemperature() < 35) {
                final Notification notification = new Notification();
                notification.setNotificationType(NotificationType.INFO);
                notification.setNotificationReadStatusType(NotificationReadStatusType.UNREAD);
                notification.setMessage(EMailSubjectType.TEMPERATURE_TOO_LOW.message);
                notification.setDateTime(new Date(new DateTime().getMillis()));
                notification.setBeehive(beehive);
                notification.setUser(user);

                notifications.add(notification);
            }
            if (beehive.getActivityType() == ActivityType.HYPERACTIVE) {
                final Notification notification = new Notification();
                notification.setNotificationType(NotificationType.INFO);
                notification.setNotificationReadStatusType(NotificationReadStatusType.UNREAD);
                notification.setMessage(EMailSubjectType.HYPERACTIVE.message);
                notification.setDateTime(new Date(new DateTime().getMillis()));
                notification.setBeehive(beehive);
                notification.setUser(user);

                notifications.add(notification);
            }
            if (beehive.getActivityType() == ActivityType.SLOW) {
                final Notification notification = new Notification();
                notification.setNotificationType(NotificationType.INFO);
                notification.setNotificationReadStatusType(NotificationReadStatusType.UNREAD);
                notification.setMessage(EMailSubjectType.HYPERACTIVE.message);
                notification.setDateTime(new Date(new DateTime().getMillis()));
                notification.setBeehive(beehive);
                notification.setUser(user);

                notifications.add(notification);
            }
            final List<Notification> addedNotifications = this.notificationRepository.saveAll(notifications);
            user.getNotifications().addAll(addedNotifications);

            if (this.decisionMakingService.shouldEmailBeSent(beehive)) {
                this.mailService.sendMailToUser(user.getEmail(), beehive.getId(), EMailSubjectType.GENERAL);
            }
        }
    }
}
