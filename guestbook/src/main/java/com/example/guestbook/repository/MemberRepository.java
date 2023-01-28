package com.example.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guestbook.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
  
}
