package com.florina.greenpanion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String lastName;
    private String firstName;
    private String email;
    private String state;
    private String city;
    private String password;
    private Integer points;
}
