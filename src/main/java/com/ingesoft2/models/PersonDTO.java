package com.ingesoft2.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

@Table(name = "person",uniqueConstraints={@UniqueConstraint(columnNames={"username"})})

public class PersonDTO {

    @Id
    @SequenceGenerator(name = "PERSON_PERSONID_GENERATOR", sequenceName = "public.person_person_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "PERSON_PERSONID_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "person_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "role_id", updatable = false)
    private Role roleId;


    @Column(name = "name")

    private String name;
    

    @Column(name = "username")

    private String username;
    

    @Column(name = "email")

    private String email;
    
    
    @Column(name = "password")

    private String password;
    
    
    @Column(name = "photo")

    private String photo;
    
    
    @Column(name = "location")

    private String location;
    
    @Column(name = "paypal_id")
    private String paypalId;


    @JsonIgnore 
    @OneToMany( mappedBy = "sellerId" )
    private List<Post> posts;

    @OneToMany(mappedBy = "buyerPerson")
    private List<Transaction> buyerTransactions;

    @OneToMany(mappedBy = "personRequester")
    private List<RecoverPasswordToken> recoverTokens;

    /* @OneToMany(mappedBy = "sellerPerson")
     private List<Transaction> sellerTransactions;  Chicos esto nos serviria para obtener las ventas de la persona facilmente :) att: Cesar*/

    @OneToOne(mappedBy = "personCartshop")
    private Cartshop cartshop;

    @OneToMany(mappedBy = "reviewByPerson")
    private List<Review> reviews;

    @OneToMany(mappedBy = "personAsk")
    private List<Question> questions;

    @OneToMany(mappedBy = "personAnswer")
    private List<Answer> answers;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPaypalId() {
        return this.paypalId;
    }

    public void setPaypalId(String paypalId) {
        this.paypalId = paypalId;
    }

    public PersonDTO id(Integer id) {
        this.id = id;
        return this;
    }

    public PersonDTO roleId(Role roleId) {
        this.roleId = roleId;
        return this;
    }

    public PersonDTO name(String name) {
        this.name = name;
        return this;
    }

    public PersonDTO username(String username) {
        this.username = username;
        return this;
    }

    public PersonDTO email(String email) {
        this.email = email;
        return this;
    }

    public PersonDTO password(String password) {
        this.password = password;
        return this;
    }

    public PersonDTO photo(String photo) {
        this.photo = photo;
        return this;
    }

    public PersonDTO location(String location) {
        this.location = location;
        return this;
    }

    public PersonDTO paypalId(String paypalId) {
        this.paypalId = paypalId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PersonDTO)) {
            return false;
        }
        PersonDTO person = (PersonDTO) o;

        return Objects.equals(id, person.id) && Objects.equals(roleId, person.roleId) && Objects.equals(name, person.name) && Objects.equals(username, person.username) && Objects.equals(email, person.email) && Objects.equals(password, person.password) && Objects.equals(photo, person.photo) && Objects.equals(location, person.location) && Objects.equals(paypalId, person.paypalId);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, name, username, email, password, photo, location, paypalId);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", roleId='" + getRoleId() + "'" +
            ", name='" + getName() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", location='" + getLocation() + "'" +
            ", paypalId='" + getPaypalId() + "'" +

            "}";
    }
    
}

