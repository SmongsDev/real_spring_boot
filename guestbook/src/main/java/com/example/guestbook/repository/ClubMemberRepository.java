package com.example.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.guestbook.entity.ClubMember;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String>{
  
}