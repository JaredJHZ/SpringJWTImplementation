package com.josafhathz.crms.controllers;

import com.josafhathz.crms.dtos.UserDTO;
import com.josafhathz.crms.models.RoleModel;
import com.josafhathz.crms.models.UserModel;
import com.josafhathz.crms.repositories.UserRepository;
import com.josafhathz.crms.services.UserServiceImplementation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserServiceImplementation userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/users/save")
    public ResponseEntity<UserModel> saveUser(@RequestBody UserDTO userDTO) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(userDTO));
    }

    @PostMapping("/role/save")
    public ResponseEntity<RoleModel> saveRole (@RequestBody RoleModel role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser (@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserDTO user) {

        this.userService.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

//    @RequestMapping("/login")
//    public  ResponseEntity<String> loginUser(@RequestBody UserDTO user) {
//        UserModel loggedUserModel = this.userRepo.findByUsername(user.getUsername());
//
//    }



 }

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
