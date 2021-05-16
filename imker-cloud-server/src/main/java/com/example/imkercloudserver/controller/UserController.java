package com.example.imkercloudserver.controller;

import com.example.imkercloudserver.controller.dto.UserDTO;
import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.mapper.UserMapper;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody final UserDTO userDTO) throws BusinessException {
        System.out.println("Logging user: " + userDTO.getUsername());
        final Optional<User> optionalUser = this.userService.logIn(this.userMapper.toEntity(userDTO));
        return optionalUser.map(user -> ResponseEntity.ok(this.userMapper.toDto(user))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody final UserDTO userDTO) throws BusinessException {
        System.out.println("Saving user: " + userDTO.toString());
        final UserDTO user = this.userMapper.toDto(this.userService.save(this.userMapper.toEntity(userDTO)));
        return ResponseEntity.ok(user);

    }
}
