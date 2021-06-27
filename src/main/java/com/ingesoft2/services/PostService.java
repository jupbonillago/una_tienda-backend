package com.ingesoft2.services;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.models.Post;
import com.ingesoft2.repositories.PostRepository;


@Service
public class PostService {
    
    
    private final PostRepository postRepository;


    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> getPostsByPerson(PersonDTO person){
        return postRepository.findBySellerId(person);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post insert(Post p) {
    	
    	try 
    	{
    		return postRepository.save(p);
    	}
    	catch(Exception e) 
    	{
    		/*Implementar logging sobre el insert de un post*/
    		return p;
    	}
        
    }

    public boolean delete(Integer id) {
        try {
            postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            /*Implementar logging sobre el delete de un post*/
            return false;
        }
    }

    public Post getByID (Integer id){
        Optional<Post> result = postRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public boolean update(Post p) {
        try {
            postRepository.save(p);
            return true;
        } catch (Exception e) {
            /*Implementar logging sobre el update de un post*/
            return false;
        }
    }
}
