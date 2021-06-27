package com.ingesoft2.controller;

import java.util.List;

import com.ingesoft2.models.RecoverPasswordToken;
import com.ingesoft2.services.RecoverPasswordTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recover-password")
public class RecoverPasswordTokenController {
  
  private RecoverPasswordTokenService recoverPasswordTokenService;

  @Autowired
  public RecoverPasswordTokenController(RecoverPasswordTokenService recoverPasswordTokenService){
    this.recoverPasswordTokenService = recoverPasswordTokenService;
  }

  @PostMapping(value = "/validate-token/{tokenString}")
  public ResponseEntity<String> getTokens(@PathVariable String tokenString){
     List<RecoverPasswordToken> list = recoverPasswordTokenService.getAll();
     RecoverPasswordToken token = recoverPasswordTokenService.getTokenByTokenString(tokenString);
     if(list.contains(token) && (recoverPasswordTokenService.isExpired(token) <= 0)){
       return ResponseEntity.ok().body("Token exists.");
     }else{
       return ResponseEntity.badRequest().body("Token doesn't exists.");
     }

  }
}
