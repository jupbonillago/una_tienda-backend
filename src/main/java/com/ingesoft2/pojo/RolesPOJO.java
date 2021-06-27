package com.ingesoft2.pojo;

import java.util.ArrayList;
import java.util.List;

import com.ingesoft2.models.Role;

public class RolesPOJO {
    private Integer id; 
    private String name;

    public List<RolesPOJO> rolesPOJO(List<Role> roles) {
        //Lo pongo a recibir una lista ya que de momento el controlador que lo llama requiere una lista como objetos de salida de la función
        List<RolesPOJO> roles2= new ArrayList<>();
        for(int i = 0; i < roles.size();i++){

            roles2.add( new RolesPOJO() );

        }
        for(int i = 0; i < roles.size();i++){

            roles2.get(i).setId(roles.get(i).getId());
            roles2.get(i).setName(roles.get(i).getName());
        }
        return roles2;
      }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
