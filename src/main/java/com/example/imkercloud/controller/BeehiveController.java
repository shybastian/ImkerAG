package com.example.imkercloud.controller;

import com.example.imkercloud.controller.dto.BeehiveDTO;
import com.example.imkercloud.exception.BusinessException;
import com.example.imkercloud.mapper.BeehiveMapper;
import com.example.imkercloud.repository.entity.ActivityType;
import com.example.imkercloud.repository.entity.Beehive;
import com.example.imkercloud.service.BeehiveService;
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
        final Set<BeehiveDTO> beehiveDTOS = new HashSet<>();
        this.beehiveService.findAll().forEach(beehive -> beehiveDTOS.add(this.beehiveMapper.toDto(beehive)));
        return ResponseEntity.ok(beehiveDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeehiveDTO> getBeehiveById(@PathVariable("id") final Long id) throws BusinessException {
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
        Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            return ResponseEntity.ok(optionalBeehive.get().getTemperature());
        } else {
            BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/weight")
    public ResponseEntity<Integer> getBeehiveWeightById(@PathVariable("id") final Long id) throws BusinessException {
        Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            return ResponseEntity.ok(optionalBeehive.get().getWeight());
        } else {
            BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/population")
    public ResponseEntity<Long> getBeehivePopulationById(@PathVariable("id") final Long id) throws BusinessException {
        Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            return ResponseEntity.ok(optionalBeehive.get().getPopulationNr());
        } else {
            BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/activity")
    public ResponseEntity<ActivityType> getBeehiveActivityById(@PathVariable("id") final Long id) throws BusinessException {
        Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            return ResponseEntity.ok(optionalBeehive.get().getActivityType());
        } else {
            BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/population")
    public ResponseEntity<Long> modifyBeehivePopulationById(
            @PathVariable("id") final Long id,
            @RequestParam(value = "add", required = false, defaultValue = "true") final Boolean add) {
        return ResponseEntity.ok(this.beehiveService.modifyPopulationNr(id, add));
    }
}
