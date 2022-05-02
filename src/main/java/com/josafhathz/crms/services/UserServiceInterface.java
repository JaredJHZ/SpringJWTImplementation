package com.josafhathz.crms.services;

import com.josafhathz.crms.dtos.UserDTO;
import com.josafhathz.crms.models.RoleModel;
import com.josafhathz.crms.models.UserModel;

import java.util.List;

public interface UserServiceInterface {
    UserModel saveUser(UserDTO userDTO);
    RoleModel saveRole(RoleModel role);
    void addRoleToUser(String username , String roleName);
    UserModel getUser(String username);
    List<UserModel> getUsers();
}
