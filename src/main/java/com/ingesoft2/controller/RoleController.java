package com.ingesoft2.controller;

import java.util.List;

import com.ingesoft2.pojo.RoleSingleton;
import com.ingesoft2.pojo.RolesPOJO;
import com.ingesoft2.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/roles")
public class RoleController {
  
  private RoleService roleService;


  @Autowired
  public RoleController(RoleService roleService){
    this.roleService = roleService;
  }


  @GetMapping(value = {"/roles"})
  public List<RolesPOJO> gRoles(){
    RolesPOJO roles = new RolesPOJO();//Solo creo el objeto para poder llamar la función
    return roles.rolesPOJO(RoleSingleton.getInstance(roleService.getAllRoles()).getRoles()); //Con esto se retorna la lista de roles POJO

}
}
