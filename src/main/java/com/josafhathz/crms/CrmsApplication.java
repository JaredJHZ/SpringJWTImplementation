package com.josafhathz.crms;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
