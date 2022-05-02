package com.josafhathz.crms.services;

import com.josafhathz.crms.dtos.UserDTO;
import com.josafhathz.crms.models.RoleModel;
import com.josafhathz.crms.models.UserModel;
import com.josafhathz.crms.repositories.RoleRepository;
import com.josafhathz.crms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserServiceInterface, UserDetailsService {



    @Autowired
    private final UserRepository userRepo;
    @Autowired
    private final RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserModel saveUser(UserDTO userDTO) {
        UserModel newUser = new UserModel();
        newUser.setEmail(userDTO.getEmail());
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        newUser.setUsername(userDTO.getUsername());
        return userRepo.save(newUser);
    }

    @Override
    public RoleModel saveRole(RoleModel role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UserModel user = userRepo.findByUsername(username);
        RoleModel role = roleRepo.findByRoleName(roleName);
        user.getRoleModels().add(role);
    }

    @Override
    public UserModel getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<UserModel> getUsers() {
        return (List<UserModel>) userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found on db");


        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoleModels().forEach(
                role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                }
        );

        return new User(user.getUsername(), user.getPassword(), authorities);

    }
}
