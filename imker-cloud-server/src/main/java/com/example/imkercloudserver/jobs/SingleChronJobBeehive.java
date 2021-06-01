package com.example.imkercloudserver.jobs;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.entity.*;
import com.example.imkercloudserver.service.BeehiveService;
import com.example.imkercloudserver.service.DecisionMakingService;
import com.example.imkercloudserver.service.MailService;
import com.example.imkercloudserver.service.NotificationService;
import com.example.imkercloudserver.service.impl.types.EMailSubjectType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SingleChronJobBeehive {
    private final BeehiveService beehiveService;
    private final NotificationService notificationService;
    private final MailService mailService;
    private final DecisionMakingService decisionMakingService;

    private final Set<Beehive> notifiedBeehives;

    @Scheduled(fixedDelay = 86400000)
    //every day
    public void resetNotifiedBeehives() {
        this.notifiedBeehives.clear();
    }

    //    @Scheduled(fixedDelay = 86400000 / 2, initialDelay = 60000)
//    @Transactional
    public void checkWeight() throws BusinessException {
        final Set<Beehive> beehives = this.beehiveService.findAll();
        for (final Beehive beehive : beehives) {
//            if (!this.notifiedBeehives.contains(beehive)) {
//                final Set<Notification> notifications = new HashSet<>();
//                final Set<String> emails = new HashSet<>();
//                final
//            }
            if (!this.notifiedBeehives.contains(beehive)) {
                if (beehive.getWeight() > 10) {
                    final Set<User> users = beehive.getUsers();
                    this.mailService.sendMailToMultipleUsers(EMailSubjectType.WEIGHT_TOO_HIGH, Optional.of(beehive.getWeight()), users);
//                    for (final User u : users)
//                        this.buildNotificationForUser(NotificationType.INFO, EMailSubjectType.WEIGHT_TOO_HIGH, u, beehive);
                }
                if (beehive.getTemperature() > 35) {
                    final Set<User> users = beehive.getUsers();
                    this.mailService.sendMailToMultipleUsers(EMailSubjectType.TEMPERATURE_TOO_HIGH, Optional.of(beehive.getTemperature()), users);
//                    for (final User u : users)
//                        this.buildNotificationForUser(NotificationType.WARNING, EMailSubjectType.TEMPERATURE_TOO_HIGH, u, beehive);
                }
                if (beehive.getTemperature() < 35) {
                    final Set<User> users = beehive.getUsers();
                    this.mailService.sendMailToMultipleUsers(EMailSubjectType.TEMPERATURE_TOO_LOW, Optional.of(beehive.getTemperature()), users);
//                    for (final User u : users)
//                        this.buildNotificationForUser(NotificationType.WARNING, EMailSubjectType.TEMPERATURE_TOO_LOW, u, beehive);
                }
//                if (beehive.getPopulationNr() > 200) {
//                    final Set<User> users = beehive.getUsers();
//                    this.mailService.sendMailToMultipleUsers(EMailSubjectType.POPULATION_TOO_HIGH, Optional.of(beehive.getPopulationNr()), users);
//                }
                if (beehive.getActivityType() == ActivityType.HYPERACTIVE) {
                    final Set<User> users = beehive.getUsers();
                    this.mailService.sendMailToMultipleUsers(EMailSubjectType.HYPERACTIVE, Optional.empty(), users);
//                    for (final User u : users)
//                        this.buildNotificationForUser(NotificationType.CRITICAL, EMailSubjectType.HYPERACTIVE, u, beehive);
                }
                if (beehive.getActivityType() == ActivityType.SLOW) {
                    final Set<User> users = beehive.getUsers();
                    this.mailService.sendMailToMultipleUsers(EMailSubjectType.SLOW, Optional.empty(), users);
//                    for (final User u : users)
//                        this.buildNotificationForUser(NotificationType.CRITICAL, EMailSubjectType.SLOW, u, beehive);
                }
                this.notifiedBeehives.add(beehive);
            }
        }
    }

    private void buildNotificationForUser(final NotificationType type, final EMailSubjectType eMailType, final User user, final Beehive beehive) {
        Notification notification = new Notification();
        notification.setNotificationType(type);
        notification.setNotificationReadStatusType(NotificationReadStatusType.UNREAD);
        //notification.setDateTime(new Timestamp(DateTime.now().getMillis()));
        notification.setMessage(eMailType.message);
        notification.setBeehive(beehive);
        notification.setUser(user);
        notification = this.notificationService.save(notification);
        user.getNotifications().add(notification);
        //return notification;
    }

    private Notification checkWeight(final Beehive beehive) {
        if (beehive.getWeight() <= 10) {
            return null;
        }
        Notification notification = new Notification();
        if (beehive.getWeight() > 10) {
            notification.setNotificationType(NotificationType.INFO);
            notification.setMessage(EMailSubjectType.WEIGHT_TOO_HIGH.message);
            //notification.setDateTime(DateTime.now());
            notification.setNotificationReadStatusType(NotificationReadStatusType.UNREAD);
            notification.setBeehive(beehive);
            notification = this.notificationService.save(notification);
            final Set<User> users = beehive.getUsers();
            for (final User user : users) {
                user.getNotifications().add(notification);
            }
            return notification;
        }
        return null;
    }

    private Notification checkTemperature(final Beehive beehive) {
        if (beehive.getTemperature() == 35) {
            return null;
        }
        Notification notification = new Notification();
        if (beehive.getTemperature() > 35) {
            notification.setNotificationType(NotificationType.WARNING);
            notification.setMessage(EMailSubjectType.TEMPERATURE_TOO_HIGH.message);
            //notification.setDateTime(DateTime.now());
            notification.setNotificationReadStatusType(NotificationReadStatusType.UNREAD);

            notification.setBeehive(beehive);
            notification = this.notificationService.save(notification);
            final Set<User> users = beehive.getUsers();
            for (final User user : users) {
                user.getNotifications().add(notification);
            }
            return notification;
        }
        if (beehive.getTemperature() < 35) {
            notification.setNotificationType(NotificationType.WARNING);
            notification.setMessage(EMailSubjectType.TEMPERATURE_TOO_LOW.message);
            //notification.setDateTime(DateTime.now());
            notification.setNotificationReadStatusType(NotificationReadStatusType.UNREAD);
            notification.setBeehive(beehive);
            notification = this.notificationService.save(notification);
            final Set<User> users = beehive.getUsers();
            for (final User user : users) {
                user.getNotifications().add(notification);
            }
            return notification;
        }
        return null;
    }


//    private Notification emitNotification() {
//
//    }

}
