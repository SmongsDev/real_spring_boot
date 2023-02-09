package com.example.guestbook.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guestbook.entity.movie.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
  
}
