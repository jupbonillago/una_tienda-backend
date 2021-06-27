package com.ingesoft2.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cartshop")
public class Cartshop {

  @Id
  @SequenceGenerator(name = "CARTSHOP_CARTSHOPID_GENERATOR", sequenceName = "public.cartshop_cartshop_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "CARTSHOP_CARTSHOPID_GENERATOR", strategy = GenerationType.SEQUENCE)
  @Column(name = "cartshop_id")
  private Integer id;

  @OneToOne
  @JoinColumn(name = "person_id")
  private PersonDTO personCartshop;

  @Column(name = "total")
  private Integer total;

  @Column(name = "state")
  private String state;


  public Cartshop() {
  }

  public Cartshop(Integer id, PersonDTO personCartshop, Integer total, String state) {
    this.id = id;
    this.personCartshop = personCartshop;
    this.total = total;
    this.state = state;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PersonDTO getPersonCartshop() {
    return this.personCartshop;
  }

  public void setPersonCartshop(PersonDTO personCartshop) {
    this.personCartshop = personCartshop;
  }

  public Integer getTotal() {
    return this.total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Cartshop id(Integer id) {
    this.id = id;
    return this;
  }

  public Cartshop personCartshop(PersonDTO personCartshop) {
    this.personCartshop = personCartshop;
    return this;
  }

  public Cartshop total(Integer total) {
    this.total = total;
    return this;
  }

  public Cartshop state(String state) {
    this.state = state;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cartshop)) {
            return false;
        }
        Cartshop cartshop = (Cartshop) o;
        return Objects.equals(id, cartshop.id) && Objects.equals(personCartshop, cartshop.personCartshop) && Objects.equals(total, cartshop.total) && Objects.equals(state, cartshop.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personCartshop, total, state);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", personCartshop='" + getPersonCartshop() + "'" +
      ", total='" + getTotal() + "'" +
      ", state='" + getState() + "'" +
      "}";
  }
  
}
