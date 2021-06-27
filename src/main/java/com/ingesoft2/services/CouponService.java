package com.ingesoft2.services;

import java.util.List;

import com.ingesoft2.models.Coupon;
import com.ingesoft2.repositories.CouponRepository;

import org.springframework.stereotype.Service;

@Service
public class CouponService {
  
  private final CouponRepository couponRepository;


  public CouponService(CouponRepository couponRepository) {
    this.couponRepository = couponRepository;
  }


  public List<Coupon> getCoupons(){
    return couponRepository.findAll();
  }
}
