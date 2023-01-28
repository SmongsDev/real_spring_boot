package com.example.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guestbook.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
  
}
