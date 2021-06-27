package com.ingesoft2.repositories;

import com.ingesoft2.models.Cartshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartshopRepository extends JpaRepository<Cartshop, Integer>{

    Cartshop findById(int id);

    Cartshop findByPersonCartshopId(int pid);
}
