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
@Table(name = "BEEHIVES")
public class Beehive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "TEMPERATURE")
    private Integer temperature;

    @Column(name = "POPULATION_NR")
    private Long populationNr;

    @Column(name = "ACTIVITY")
    @Enumerated(value = EnumType.STRING)
    private ActivityType activityType;

    @ManyToMany(
            mappedBy = "beehives",
            targetEntity = User.class,
            fetch = FetchType.LAZY
    )
    private List<User> users;
}
