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
@Table(name = "category")
public class Category {
  @Id
  @SequenceGenerator(name = "CATEGORY_CATEGORY_ID_GENERATOR", sequenceName = "public.category_category_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "CATEGORY_CATEGORY_ID_GENERATOR", strategy = GenerationType.SEQUENCE)
  @Column(name = "category_id")
  private Integer id;

  @Column(name = "name")

  private String name;

  @Column(name = "image")

  private String image;  


  public Category() {
  }

  public Category(Integer id, String name, String image) {
    this.id = id;
    this.name = name;
    this.image = image;
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

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Category id(Integer id) {
    this.id = id;
    return this;
  }

  public Category name(String name) {
    this.name = name;
    return this;
  }

  public Category image(String image) {
    this.image = image;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(image, category.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, image);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", image='" + getImage() + "'" +
      "}";
  }

}
