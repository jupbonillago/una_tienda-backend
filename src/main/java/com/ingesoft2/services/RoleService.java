package com.ingesoft2.services;

import java.util.List;

import com.ingesoft2.models.Role;
import com.ingesoft2.repositories.RoleRepository;

import org.springframework.stereotype.Service;

@Service
public class RoleService {

  private final RoleRepository roleRepository;


  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }


  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }

  public Role findById(int id) {
    return roleRepository.findById(id);
  }

}
