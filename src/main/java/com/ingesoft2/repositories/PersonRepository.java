package com.ingesoft2.repositories;


import com.ingesoft2.models.PersonDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonDTO, Integer> {
	PersonDTO findByUsername(String username);
	PersonDTO findByEmail(String email);
}