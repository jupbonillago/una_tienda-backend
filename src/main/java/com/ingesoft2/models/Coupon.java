package com.ingesoft2.models;

import java.sql.Date;
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
@Table(name = "coupon")
public class Coupon {
  @Id
  @SequenceGenerator(name = "COUPON_COUPONID_GENERATOR", sequenceName = "public.coupon_coupon_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "COUPON_COUPONID_GENERATOR", strategy = GenerationType.SEQUENCE)
  @Column(name = "coupon_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category categoryCouponId;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @Column(name = "amount")
  private Integer amount;

  @Column(name = "code")
  private String code;



  public Coupon() {
  }

  public Coupon(Integer id, Category categoryCouponId, Date startDate, Date endDate, Integer amount, String code) {
    this.id = id;
    this.categoryCouponId = categoryCouponId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.amount = amount;
    this.code = code;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Category getCategoryCouponId() {
    return this.categoryCouponId;
  }

  public void setCategoryCouponId(Category categoryCouponId) {
    this.categoryCouponId = categoryCouponId;
  }

  public Date getStartDate() {
    return this.startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return this.endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Integer getAmount() {
    return this.amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Coupon id(Integer id) {
    this.id = id;
    return this;
  }

  public Coupon categoryCouponId(Category categoryCouponId) {
    this.categoryCouponId = categoryCouponId;
    return this;
  }

  public Coupon startDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  public Coupon endDate(Date endDate) {
    this.endDate = endDate;
    return this;
  }

  public Coupon amount(Integer amount) {
    this.amount = amount;
    return this;
  }

  public Coupon code(String code) {
    this.code = code;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coupon)) {
            return false;
        }
        Coupon coupon = (Coupon) o;
        return Objects.equals(id, coupon.id) && Objects.equals(categoryCouponId, coupon.categoryCouponId) && Objects.equals(startDate, coupon.startDate) && Objects.equals(endDate, coupon.endDate) && Objects.equals(amount, coupon.amount) && Objects.equals(code, coupon.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoryCouponId, startDate, endDate, amount, code);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", categoryCouponId='" + getCategoryCouponId() + "'" +
      ", startDate='" + getStartDate() + "'" +
      ", endDate='" + getEndDate() + "'" +
      ", amount='" + getAmount() + "'" +
      ", code='" + getCode() + "'" +
      "}";
  }

}