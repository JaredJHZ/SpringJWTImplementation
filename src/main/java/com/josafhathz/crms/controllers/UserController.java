package com.josafhathz.crms.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.josafhathz.crms.dtos.UserDTO;
import com.josafhathz.crms.models.RoleModel;
import com.josafhathz.crms.models.UserModel;
import com.josafhathz.crms.repositories.UserRepository;
import com.josafhathz.crms.services.UserServiceImplementation;
import com.josafhathz.crms.utilities.JWTUtilities;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String refreshToken = authorizationHeader.substring("Bearer".length());

        log.info("OLAAA");
        log.info(authorizationHeader);
        log.info(refreshToken);

        DecodedJWT token = JWTUtilities.decodeTokenIfValid(refreshToken);
        UserModel userAuthenticated = this.userService.getUser(token.getSubject());
        Collection<SimpleGrantedAuthority> authorities = JWTUtilities.getAuthoritiesAsArrayList(token.getClaim("roles").asArray(String.class));
        User user = new User(userAuthenticated.getUsername(), userAuthenticated.getPassword(), authorities);
        String newAccessToken = JWTUtilities.createJWTToken(user, 10, request.getRequestURL().toString());
        Map<String, String> tokens = new HashMap<String, String>();
        tokens.put("access_token", newAccessToken);
        tokens.put("refresh_token", refreshToken);
        return ResponseEntity.ok(tokens);
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
