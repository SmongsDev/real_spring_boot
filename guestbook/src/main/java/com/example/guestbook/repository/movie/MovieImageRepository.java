package com.example.guestbook.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guestbook.entity.movie.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long>{
  
}
