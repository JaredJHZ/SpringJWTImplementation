package com.josafhathz.crms;

import com.josafhathz.crms.dtos.UserDTO;
import com.josafhathz.crms.models.RoleModel;
import com.josafhathz.crms.services.UserServiceInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class CrmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmsApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserServiceInterface userService) {
//        return args -> {
//          userService.saveRole(new RoleModel(null, "ADMIN"));
//          userService.saveRole(new RoleModel(null, "USER"));
//          userService.saveUser(new UserDTO("xd@gmail.com",null, "YEIDEN", "xd","xDDD"));
//          userService.addRoleToUser("xd", "ADMIN");
//        };
//    }
}
