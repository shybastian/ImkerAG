package com.example.imkercloudserver.controller;

import lombok.RequiredArgsConstructor;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.service.BusinessService;
import com.example.imkercloudserver.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;
    private final UserService userService;
    @PostMapping("/random/{userid}/{nrbeehives}")
    public ResponseEntity<Integer> getBeehiveById(@PathVariable("userid") final Long userid,@PathVariable("nrbeehives") final Long nrbeehives) throws BusinessException {
       System.out.println(userid+" "+nrbeehives);
       businessService.createRandomBeehives(userService.findById(userid).get(), nrbeehives);
       return ResponseEntity.ok(0);

    }

}
