package com.ingesoft2.personTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.models.RecoverPasswordToken;
import com.ingesoft2.services.PersonService;
import com.ingesoft2.services.RecoverPasswordTokenService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecoverPasswordTests {
  
  @Autowired
  PersonService personService;

  @Autowired
  RecoverPasswordTokenService recoverPasswordTokenService;

  @Test
  void getToken(){
    PersonDTO person = new PersonDTO();
    person.setId(1);
    person.setUsername("test1");
    person.setPassword("qwertyuiop");
    assertEquals(RecoverPasswordToken.class, recoverPasswordTokenService.getJWToken(person).getClass());
  }

  @Test
  void getUsernameByToken(){
    PersonDTO person = new PersonDTO();
    person.setId(1);
    person.setUsername("test1");
    person.setPassword("qwertyuiop");
    RecoverPasswordToken token = recoverPasswordTokenService.getJWToken(person);
    assertEquals(person.getUsername(), token.getPersonRequester().getUsername());
  }
}
