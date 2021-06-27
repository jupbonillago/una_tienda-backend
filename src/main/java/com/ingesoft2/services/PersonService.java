package com.ingesoft2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.repositories.PersonRepository;

@Service
public class PersonService {
    
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findAllPeople() {
        return personRepository.findAll();
    }

    public PersonDTO findByUsername(String username){
        return personRepository.findByUsername(username);
    }

    public PersonDTO insert(PersonDTO p) {
    	
    	try 
    	{
    		return personRepository.save(p);
    	}
    	catch(Exception e) 
    	{
            
    		/*Implementar logging sobre el insert de un person*/
    		return p;
    	}
        
    }

    public boolean delete(Integer id) {
        try {
            personRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            /*Implementar logging sobre el insert de un person*/
            return false;
        }
    }

    public PersonDTO findById(Integer id) {
        Optional<PersonDTO> result = personRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public PersonDTO findByEmail(String email) {
        return personRepository.findByEmail(email);
        }
    

    public boolean update(PersonDTO p) {
        try {
            personRepository.save(p);
            return true;
        } catch (Exception e) {
            /*Implementar logging sobre el insert de un person*/
            return false;
        }
    }
}
