package com.example.imkercloudserver.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @ToString.Exclude
    @OrderBy(value = "dateTime")
    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Notification> notifications;

    @ToString.Exclude
    @OrderBy(value = "id")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "USERS_BEEHIVES",
            joinColumns = @JoinColumn(name = "FK_USER"),
            inverseJoinColumns = @JoinColumn(name = "FK_BEEHIVE")
    )
    private Set<Beehive> beehives;
}
