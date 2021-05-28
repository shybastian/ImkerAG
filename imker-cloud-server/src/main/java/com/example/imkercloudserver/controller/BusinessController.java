package com.example.imkercloudserver.controller;

import com.example.imkercloudserver.controller.dto.BeehiveDTO;
import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.mapper.BeehiveMapper;
import com.example.imkercloudserver.repository.entity.Beehive;
import com.example.imkercloudserver.service.BeehiveService;
import com.example.imkercloudserver.service.BusinessService;
import com.example.imkercloudserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;
    private final BeehiveMapper beehiveMapper;
    private final UserService userService;
    private final BeehiveService beehiveService;

    @PostMapping("/initialSetup")
    public ResponseEntity<?> initialSetup(@RequestParam(value = "nrBeehives", required = false, defaultValue = "10") final Long numberOfBeehives) throws BusinessException {
        Set<Beehive> beehiveSet = this.businessService.createRandomBeehives(numberOfBeehives);
        this.businessService.assignBeehivesToUsers(beehiveSet);
        beehiveSet = this.beehiveService.findAll();
        final List<BeehiveDTO> beehiveDTOS = new ArrayList<>();
        beehiveSet.forEach(beehive -> beehiveDTOS.add(this.beehiveMapper.toDto(beehive)));
        return ResponseEntity.ok(beehiveDTOS);
    }

    @GetMapping
    public ResponseEntity<?> getHashedPassword(@RequestParam(value = "password") final String password) {
        this.userService.hashPassword(password);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
