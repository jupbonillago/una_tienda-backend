package com.ingesoft2.repositories;

import com.ingesoft2.models.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{
  
}
