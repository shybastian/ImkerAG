package com.example.imkercloudserver.repository.entity;

import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "NOTIFICATIONS")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOTIFICATION_TYPE")
    @Enumerated(value = EnumType.STRING)
    private NotificationType notificationType;

    @Column(name = "NOTIFICATION_READ_STATUS_TYPE")
    @Enumerated(value = EnumType.STRING)
    private NotificationReadStatusType notificationReadStatusType;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "NOTIFICATION_DATE")
    private DateTime dateTime;

    @ToString.Exclude
    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "FK_USER")
    private User user;

    @ToString.Exclude
    @ManyToOne(
            targetEntity = Beehive.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "FK_BEEHIVE")
    private Beehive beehive;
}
