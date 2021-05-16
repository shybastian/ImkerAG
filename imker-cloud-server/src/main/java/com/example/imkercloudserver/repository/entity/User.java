package com.example.imkercloudserver.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Notification> notifications;

    @ManyToMany(
            targetEntity = Beehive.class,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "USERS_BEEHIVES",
            joinColumns = @JoinColumn(name = "FK_USER"),
            inverseJoinColumns = @JoinColumn(name = "FK_BEEHIVE")
    )
    private List<Beehive> beehives;
}
