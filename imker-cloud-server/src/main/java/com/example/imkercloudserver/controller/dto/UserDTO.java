package com.example.imkercloudserver.controller.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 8978401066503119439L;

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
