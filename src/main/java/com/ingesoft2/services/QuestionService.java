package com.ingesoft2.services;

import java.util.List;

import com.ingesoft2.models.Question;
import com.ingesoft2.repositories.QuestionRepository;

import org.springframework.stereotype.Service;

@Service
public class QuestionService {
  
  private final QuestionRepository questionRepository;


  public QuestionService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }


  public List<Question> getQuestions(){
    return questionRepository.findAll();
  }
}
