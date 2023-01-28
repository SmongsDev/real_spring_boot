package com.example.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guestbook.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
  
}
