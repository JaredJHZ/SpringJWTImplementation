package com.josafhathz.crms.repositories;

import com.josafhathz.crms.models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Integer> {

    public UserModel findByUsername(String username);
}
