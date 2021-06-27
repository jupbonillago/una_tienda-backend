package com.ingesoft2.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.models.Post;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
  Post findById(int id);

  @Query("FROM post WHERE seller_id = :person")
  List<Post> findBySellerId(PersonDTO person);
}

