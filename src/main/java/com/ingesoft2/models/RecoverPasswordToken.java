package com.ingesoft2.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "recover_token")
public class RecoverPasswordToken {
  
  @Id
  @SequenceGenerator(name = "RECOVER_RECOVERID_GENERATOR", sequenceName = "public.recover_token_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "RECOVER_RECOVERID_GENERATOR", strategy = GenerationType.SEQUENCE)
  @Column(name = "token_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private PersonDTO personRequester;

  @Column(name = "token")
  private String token;

  @Column(name = "generated_at")
  private long generatedAt;




  public RecoverPasswordToken() {
  }

  public RecoverPasswordToken(PersonDTO personRequester, String token, long generatedAt) {
    this.personRequester = personRequester;
    this.token = token;
    this.generatedAt = generatedAt;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PersonDTO getPersonRequester() {
    return this.personRequester;
  }

  public void setPersonRequester(PersonDTO personRequester) {
    this.personRequester = personRequester;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public long getGeneratedAt() {
    return this.generatedAt;
  }

  public void setGeneratedAt(long generatedAt) {
    this.generatedAt = generatedAt;
  }

  public RecoverPasswordToken id(Integer id) {
    this.id = id;
    return this;
  }

  public RecoverPasswordToken personRequester(PersonDTO personRequester) {
    this.personRequester = personRequester;
    return this;
  }

  public RecoverPasswordToken token(String token) {
    this.token = token;
    return this;
  }

  public RecoverPasswordToken generatedAt(long generatedAt) {
    this.generatedAt = generatedAt;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RecoverPasswordToken)) {
            return false;
        }
        RecoverPasswordToken recoverPasswordToken = (RecoverPasswordToken) o;
        return Objects.equals(id, recoverPasswordToken.id) && Objects.equals(personRequester, recoverPasswordToken.personRequester) && Objects.equals(token, recoverPasswordToken.token) && generatedAt == recoverPasswordToken.generatedAt;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personRequester, token, generatedAt);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", personRequester='" + getPersonRequester() + "'" +
      ", token='" + getToken() + "'" +
      ", generatedAt='" + getGeneratedAt() + "'" +
      "}";
  }
  
    
}
