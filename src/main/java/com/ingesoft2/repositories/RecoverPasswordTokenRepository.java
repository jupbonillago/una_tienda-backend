package com.ingesoft2.repositories;

import java.util.List;

import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.models.RecoverPasswordToken;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoverPasswordTokenRepository  extends JpaRepository<RecoverPasswordToken, Integer>{
  @Query("FROM recover_token WHERE person_id = ?person")
  List<RecoverPasswordToken> findByPersonRequester(PersonDTO person);

  @Query("FROM recover_token WHERE token = ?token")
  RecoverPasswordToken findByToken(String token);

}
