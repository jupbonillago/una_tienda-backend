package com.ingesoft2.services;

import java.util.List;
import java.util.Optional;

import com.ingesoft2.models.CartshopItem;
import com.ingesoft2.repositories.CartshopItemRepository;

import org.springframework.stereotype.Service;

@Service
public class CartshopItemService {
  
  private final CartshopItemRepository cartshopItemRepository;


  public CartshopItemService(CartshopItemRepository cartshopItemRepository) {
    this.cartshopItemRepository = cartshopItemRepository;
  }

  public CartshopItem insert(CartshopItem p) {

    try
    {
      return cartshopItemRepository.save(p);
    }
    catch(Exception e)
    {
      /*Implementar logging sobre el insert de un cartshopItem*/
      return p;
    }

  }


  public List<CartshopItem> getItems(){
    return cartshopItemRepository.findAll();
  }

  public List<CartshopItem> findByCartshop(int cartshopId){
    return cartshopItemRepository.findByCartshopId(cartshopId);
  }

  public CartshopItem getByID (Integer id){
      Optional<CartshopItem> result = cartshopItemRepository.findById(id);
    if (result.isPresent()) {
      return result.get();
    } else {
      return null;
    }
  }

  public boolean delete(Integer id) {
    try {
      cartshopItemRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      /*Implementar logging sobre el delete de un cartshopItem*/
      return false;
    }
  }

  public boolean update(CartshopItem p) {
    try {
      cartshopItemRepository.save(p);
      return true;
    } catch (Exception e) {
      /*Implementar logging sobre el update de un cartshopItem*/
      return false;
    }
  }
}
