package com.josafhathz.crms.repositories;

import com.josafhathz.crms.models.RoleModel;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleModel, Integer> {

    RoleModel findByRoleName(String name);
}
