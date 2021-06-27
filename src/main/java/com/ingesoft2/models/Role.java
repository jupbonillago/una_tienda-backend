package com.ingesoft2.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
  @Id

  @SequenceGenerator(name = "ROLE_ROLEID_GENERATOR", sequenceName = "public.role_role_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "ROLE_ROLEID_GENERATOR", strategy = GenerationType.SEQUENCE)
  @Column(name = "role_id")
  private Integer id;

  @Column(name = "name")
  private String name;


  public Role() {
  }

  public Role(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Role id(Integer id) {
    this.id = id;
    return this;
  }

  public Role name(String name) {
    this.name = name;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      "}";
  }

  }
