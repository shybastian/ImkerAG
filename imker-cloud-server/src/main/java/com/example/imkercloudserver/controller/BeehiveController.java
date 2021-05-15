package com.example.imkercloudserver.controller;

import com.example.imkercloudserver.controller.dto.BeehiveDTO;
import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.mapper.BeehiveMapper;
import com.example.imkercloudserver.repository.entity.ActivityType;
import com.example.imkercloudserver.repository.entity.Beehive;
import com.example.imkercloudserver.service.BeehiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/beehives")
@CrossOrigin
@RequiredArgsConstructor
public class BeehiveController {
    private final BeehiveMapper beehiveMapper;
    private final BeehiveService beehiveService;

    @GetMapping
    public ResponseEntity<Set<BeehiveDTO>> getAllBeehives() throws BusinessException {
        System.out.println("Getting Details of All Beehives");
        final Set<BeehiveDTO> beehiveDTOS = new HashSet<>();
        this.beehiveService.findAll().forEach(beehive -> beehiveDTOS.add(this.beehiveMapper.toDto(beehive)));
        return ResponseEntity.ok(beehiveDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeehiveDTO> getBeehiveById(@PathVariable("id") final Long id) throws BusinessException {
        System.out.println("Getting Details off Beehive with ID: " + id);
        Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            return ResponseEntity.ok(this.beehiveMapper.toDto(optionalBeehive.get()));
        } else {
            BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<BeehiveDTO> saveBeehive(@RequestBody final BeehiveDTO beehiveDTO) throws BusinessException {
        System.out.println("Adding new Beehive to the Database\n Beehive to be added:\n" + beehiveDTO.toString());
        final Beehive beehive = this.beehiveMapper.toEntity(beehiveDTO);
        final BeehiveDTO addedBeehiveDTO = this.beehiveMapper.toDto(this.beehiveService.save(beehive));
        return ResponseEntity.ok(addedBeehiveDTO);
    }

    @PutMapping
    public ResponseEntity<BeehiveDTO> updateBeehive(@RequestBody final BeehiveDTO beehiveDTO) throws BusinessException {
        final Beehive beehive = this.beehiveMapper.toEntity(beehiveDTO);
        final BeehiveDTO updatedBeehiveDTO = this.beehiveMapper.toDto(this.beehiveService.update(beehive));
        return ResponseEntity.ok(updatedBeehiveDTO);
    }

    @GetMapping("/{id}/temperature")
    public ResponseEntity<Integer> getBeehiveTemperatureById(@PathVariable("id") final Long id) throws BusinessException {
        System.out.println("Getting Temperature of Beehive with ID: " + id);
        Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            System.out.println("Temperature of Beehive: " + id + ", is: " + optionalBeehive.get().getTemperature());
            return ResponseEntity.ok(optionalBeehive.get().getTemperature());
        } else {
            BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/weight")
    public ResponseEntity<Integer> getBeehiveWeightById(@PathVariable("id") final Long id) throws BusinessException {
        System.out.println("Getting Weight of Beehive with ID: " + id);
        Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            System.out.println("Weight of Beehive: " + id + ", is: " + optionalBeehive.get().getWeight());
            return ResponseEntity.ok(optionalBeehive.get().getWeight());
        } else {
            BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/population")
    public ResponseEntity<Long> getBeehivePopulationById(@PathVariable("id") final Long id) throws BusinessException {
        System.out.println("Getting Population Number of Beehive with ID: " + id);
        Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            System.out.println("Population Number of Beehive: " + id + ", is: " + optionalBeehive.get().getPopulationNr());
            return ResponseEntity.ok(optionalBeehive.get().getPopulationNr());
        } else {
            BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/activity")
    public ResponseEntity<ActivityType> getBeehiveActivityById(@PathVariable("id") final Long id) throws BusinessException {
        System.out.println("Getting Activity of Beehive with ID: " + id);
        Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            System.out.println("Activity of Beehive: " + id + ", is: " + optionalBeehive.get().getActivityType());
            return ResponseEntity.ok(optionalBeehive.get().getActivityType());
        } else {
            BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/population")
    public ResponseEntity<Long> modifyBeehivePopulationById(
            @PathVariable("id") final Long id,
            @RequestParam(value = "add", required = false, defaultValue = "true") final Boolean add) {
        System.out.println("Called population manipulation API. Add value? " + add);
        return ResponseEntity.ok(this.beehiveService.modifyPopulationNr(id, add));
    }

    @PostMapping("/{id}/temperature")
    public ResponseEntity<Integer> modifyBeehiveTemperatureById(
            @PathVariable("id") final Long id,
            @RequestParam(value = "toHeat", required = false, defaultValue = "true") final Boolean toHeat) {
        System.out.println("Called Temperature Manipulation API. Add value? " + toHeat);
        return ResponseEntity.ok(this.beehiveService.modifyTemperature(id, toHeat));
    }
}
