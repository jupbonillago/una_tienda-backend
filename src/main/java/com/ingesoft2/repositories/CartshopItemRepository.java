package com.ingesoft2.repositories;

import com.ingesoft2.models.CartshopItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartshopItemRepository extends JpaRepository<CartshopItem, Integer>{
    List<CartshopItem> findByCartshopId(int id);

    CartshopItem findCartshopItemById(int id);

    CartshopItem findById(int id);

}