package com.josafhathz.crms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

}
