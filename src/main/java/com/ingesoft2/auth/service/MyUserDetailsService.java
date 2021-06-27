package com.ingesoft2.auth.service;


import com.ingesoft2.auth.model.MyUserDetails;
import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.services.PersonService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService{

  private PersonService personService;

  public MyUserDetailsService(PersonService personService){
    this.personService = personService;
  }

  public UserDetails loadUserByUsername(String username){
    PersonDTO person = personService.findByUsername(username);
    if(person == null){
      throw new UsernameNotFoundException("");
    }
    return new MyUserDetails(person);
  }
}
