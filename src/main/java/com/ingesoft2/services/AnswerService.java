package com.ingesoft2.services;

import java.util.List;

import com.ingesoft2.models.Answer;
import com.ingesoft2.repositories.AnswerRepository;

import org.springframework.stereotype.Service;

@Service
public class AnswerService {
  
  private final AnswerRepository answerRepository;

  public AnswerService(AnswerRepository answerRepository){
    this.answerRepository = answerRepository;
  }

  public List<Answer> getAnswers(){
    return answerRepository.findAll();
  }
}
