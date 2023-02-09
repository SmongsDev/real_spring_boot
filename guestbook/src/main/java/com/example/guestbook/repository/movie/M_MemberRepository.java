package com.example.guestbook.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.guestbook.entity.movie.M_Member;

public interface M_MemberRepository extends JpaRepository<M_Member, Long>{
  
}
