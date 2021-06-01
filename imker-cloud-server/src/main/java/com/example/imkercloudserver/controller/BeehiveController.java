package com.example.imkercloudserver.controller;

import com.example.imkercloudserver.controller.dto.BeehiveDTO;
import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.mapper.BeehiveMapper;
import com.example.imkercloudserver.repository.entity.ActivityType;
import com.example.imkercloudserver.repository.entity.Beehive;
import com.example.imkercloudserver.service.BeehiveService;
import com.example.imkercloudserver.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/beehives")
@RequiredArgsConstructor
public class BeehiveController {
    private final BeehiveMapper beehiveMapper;
    private final BeehiveService beehiveService;
    private final MailService mailService;


    @GetMapping
    public ResponseEntity<Set<BeehiveDTO>> getAllBeehives(
            @RequestParam(value = "userId", required = false) final Long userId) throws BusinessException {
        final Set<BeehiveDTO> beehiveDTOS = new HashSet<>();
        if (userId != null) {
            this.beehiveService.findAllBelongingToUserId(userId)
                    .forEach(beehive -> beehiveDTOS.add(this.beehiveMapper.toDto(beehive)));
        } else {
            this.beehiveService.findAll()
                    .forEach(beehive -> beehiveDTOS.add(this.beehiveMapper.toDto(beehive)));
        }
        return ResponseEntity.ok(beehiveDTOS);
    }

    @GetMapping("/test")
    public String test() {
        final String one = "Test";
        final String two = "Sebi";
        final String result = one + two + 2 + 2;
        final String resultTwo = 2 + 2 + one + two + 2 + 2;
        return resultTwo;
    }

    @GetMapping("/total")
    public int getNumberOfBeehives() {
        return this.beehiveService.findAll().size();
    }

    @GetMapping("/asString")
    public String getAllBeehivesIdAsString() {
        final Set<Long> longSet = this.beehiveService.getIds();
        final StringBuilder builder = new StringBuilder();
        for (final Long id : longSet) {
            builder.append(id).append(",");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeehiveDTO> getBeehiveById(
            @PathVariable("id") final Long id) throws BusinessException {
        final Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            return ResponseEntity.ok(this.beehiveMapper.toDto(optionalBeehive.get()));
        } else {
            final BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
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

    @PostMapping("/empty")
    public Long saveBeehive() {
        return this.beehiveService.saveEmptyBeehive();
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
        final Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            System.out.println("Temperature of Beehive: " + id + ", is: " + optionalBeehive.get().getTemperature());
            return ResponseEntity.ok(optionalBeehive.get().getTemperature());
        } else {
            final BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/weight")
    public ResponseEntity<Integer> getBeehiveWeightById(@PathVariable("id") final Long id) throws BusinessException {
        System.out.println("Getting Weight of Beehive with ID: " + id);
        final Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            System.out.println("Weight of Beehive: " + id + ", is: " + optionalBeehive.get().getWeight());
            return ResponseEntity.ok(optionalBeehive.get().getWeight());
        } else {
            final BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/population")
    public ResponseEntity<Long> getBeehivePopulationById(@PathVariable("id") final Long id) throws BusinessException {
        System.out.println("Getting Population Number of Beehive with ID: " + id);
        final Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            System.out.println("Population Number of Beehive: " + id + ", is: " + optionalBeehive.get().getPopulationNr());
            return ResponseEntity.ok(optionalBeehive.get().getPopulationNr());
        } else {
            final BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/activity")
    public ResponseEntity<ActivityType> getBeehiveActivityById(@PathVariable("id") final Long id) throws BusinessException {
        System.out.println("Getting Activity of Beehive with ID: " + id);
        final Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
        if (optionalBeehive.isPresent()) {
            System.out.println("Activity of Beehive: " + id + ", is: " + optionalBeehive.get().getActivityType());
            return ResponseEntity.ok(optionalBeehive.get().getActivityType());
        } else {
            final BusinessException exception = new BusinessException("Beehive with ID: " + id + " does not exist!");
            return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/modify")
    public void modifyBeehiveAttributes(
            @PathVariable("id") final Long id,
            @RequestParam(value = "weight", required = false) final Integer weight,
            @RequestParam(value = "temperature", required = false) final Integer temperature,
            @RequestParam(value = "nrPopulation", required = false) final Integer nrPopulation,
            @RequestParam(value = "activity", required = false) final ActivityType activityType
    ) {
        final Beehive toUpdate = new Beehive(id, weight, temperature, Long.valueOf(nrPopulation), activityType, null);
        this.beehiveService.update(toUpdate);
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

    @PostMapping("/{id}/weight")
    public ResponseEntity<Integer> modifyBeehiveWeightById(
            @PathVariable("id") final Long id,
            @RequestParam(value = "toHigh", required = false, defaultValue = "true") final Boolean toHigh) {
        System.out.println("Called Weight Manipulation API. Add value? " + toHigh);
        return ResponseEntity.ok(this.beehiveService.modifyWeight(id, toHigh));
    }

//    @PostMapping("/{id}/email")
//    public ResponseEntity<Integer> sendEmail(
//            @PathVariable("id") final Long id) throws BusinessException {
//        System.out.println("Called Email Manipulation API");
//        final Optional<Beehive> optionalBeehive = this.beehiveService.findById(id);
//        if (optionalBeehive.isPresent()) {
//            final Beehive b = optionalBeehive.get();
//            this.mailService.sendMailToMultipleUsers(EMailSubjectType.WEIGHT_TOO_HIGH, Optional.of(b.getWeight()), b.getUsers());
//        } else {
//            throw new BusinessException("Beehive id " + id + "was not found");
//
//        }
//
//        return ResponseEntity.noContent().build();
//    }

}
