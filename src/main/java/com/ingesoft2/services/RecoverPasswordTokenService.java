package com.ingesoft2.services;

import java.util.Date;
import java.util.List;

import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.models.RecoverPasswordToken;
import com.ingesoft2.repositories.RecoverPasswordTokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class RecoverPasswordTokenService {
  
  private final RecoverPasswordTokenRepository recoverPasswordTokenRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  private String superSecretKey = "soft-eng-ii";
  private Long tokenExpirationTime = (long) 3600*1000;


  public RecoverPasswordTokenService(RecoverPasswordTokenRepository recoverPasswordTokenRepository) {
    this.recoverPasswordTokenRepository = recoverPasswordTokenRepository;
  }

  public List<RecoverPasswordToken> getAll(){
    return recoverPasswordTokenRepository.findAll();
  }

  public List<RecoverPasswordToken> getTokensByPerson(PersonDTO person){
    return recoverPasswordTokenRepository.findByPersonRequester(person);
  }

  public RecoverPasswordToken getTokenByTokenString(String token){
    return recoverPasswordTokenRepository.findByToken(token);
  }

  public RecoverPasswordToken getJWToken(PersonDTO person) {
    String tokenString = Jwts.builder().setIssuedAt(new Date()).setSubject(person.getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime))
        .signWith(SignatureAlgorithm.HS512, superSecretKey).compact();
    RecoverPasswordToken token = new RecoverPasswordToken(person, tokenString, new Date().getTime());
    recoverPasswordTokenRepository.save(token);
    return token;
  }

  public String getUsername(String token){
    return Jwts.parser().setSigningKey(superSecretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public Integer isExpired(RecoverPasswordToken token){
    Date expirationTime = Jwts.parser().setSigningKey(superSecretKey).parseClaimsJws(token.getToken()).getBody().getExpiration();
    return new Date().compareTo(expirationTime);
  }

  public String encryptPassword(String password) {
    return passwordEncoder.encode(password);
  }
}
