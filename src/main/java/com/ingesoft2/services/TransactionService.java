package com.ingesoft2.services;

import java.util.List;

import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.models.Transaction;
import com.ingesoft2.repositories.TransactionRepository;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  
  private TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository){
    this.transactionRepository = transactionRepository;
  }

  public List<Transaction> getTransactions(){
    return transactionRepository.findAll();
  }

  public List<Transaction> findByPerson(PersonDTO person){
    return transactionRepository.findByBuyerPerson(person);
}

  public Transaction insert(Transaction p) {

    try
    {
      return transactionRepository.save(p);
    }
    catch(Exception e)
    {
      /*Implementar logging sobre el insert de un cartshopItem*/
      return p;
    }

  }
}
