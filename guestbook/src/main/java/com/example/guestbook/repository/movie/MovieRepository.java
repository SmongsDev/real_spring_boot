package com.example.guestbook.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guestbook.entity.movie.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
  
}
