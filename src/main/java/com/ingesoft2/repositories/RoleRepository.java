package com.ingesoft2.repositories;


import com.ingesoft2.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findById(int id);
}
