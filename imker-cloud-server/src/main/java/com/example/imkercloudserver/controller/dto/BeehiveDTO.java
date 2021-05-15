package com.example.imkercloudserver.controller.dto;

import com.example.imkercloudserver.repository.entity.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeehiveDTO implements Serializable {
    private static final long serialVersionUID = 2312589774055897011L;

    private Long id;
    private Integer weight;
    private Integer temperature;
    private Long populationNr;
    private ActivityType activityType;
}
