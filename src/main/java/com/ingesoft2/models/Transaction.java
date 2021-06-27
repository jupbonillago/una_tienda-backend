package com.ingesoft2.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
  
  @Id
  @SequenceGenerator(name = "TRANSACTION_TRANSACTIONID_GENERATOR", sequenceName = "public.transaction_transaction_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "TRANSACTION_TRANSACTIONID_GENERATOR", strategy = GenerationType.SEQUENCE)
  @Column(name = "transaction_id")
  private Integer id;

  
  @ManyToOne
  @JoinColumn(name = "person_id")
  private PersonDTO buyerPerson;

  @OneToOne
  @JoinColumn(name = "postId")
  private Post postId;

  @Column(name = "stock_price")
  private Integer stockPrice;

  @Column(name = "quantity")
  private Integer quantity;




  public Transaction() {
  }

  public Transaction(Integer id, PersonDTO buyerPerson, Post postId, Integer stockPrice, Integer quantity) {
    this.id = id;
    this.buyerPerson = buyerPerson;
    this.postId = postId;
    this.stockPrice = stockPrice;
    this.quantity = quantity;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PersonDTO getBuyerPerson() {
    return this.buyerPerson;
  }

  public void setBuyerPerson(PersonDTO buyerPerson) {
    this.buyerPerson = buyerPerson;
  }

  public Post getPostId() {
    return this.postId;
  }

  public void setPostId(Post postId) {
    this.postId=postId;
  }

  public Integer getStockPrice() {
    return this.stockPrice;
  }

  public void setStockPrice(Integer stockPrice) {
    this.stockPrice = stockPrice;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Transaction id(Integer id) {
    this.id = id;
    return this;
  }

  public Transaction buyerPerson(PersonDTO buyerPerson) {
    this.buyerPerson = buyerPerson;
    return this;
  }

  public Transaction postId(Post postId) {
    this.postId = postId;
    return this;
  }

  public Transaction stockPrice(Integer stockPrice) {
    this.stockPrice = stockPrice;
    return this;
  }

  public Transaction quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        return Objects.equals(id, transaction.id) && Objects.equals(buyerPerson, transaction.buyerPerson) && Objects.equals(postId, transaction.postId) && Objects.equals(stockPrice, transaction.stockPrice) && Objects.equals(quantity, transaction.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, buyerPerson, postId, stockPrice, quantity);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", buyerPerson='" + getBuyerPerson() + "'" +
      ", postId='" + getPostId() + "'" +
      ", stockPrice='" + getStockPrice() + "'" +
      ", quantity='" + getQuantity() + "'" +
      "}";
  }
    
}
