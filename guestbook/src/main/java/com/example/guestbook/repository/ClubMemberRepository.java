package com.example.guestbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.guestbook.entity.ClubMember;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String>{
  @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
  @Query("SELECT m FROM ClubMember m WHERE m.fromSocial = :social and m.email = :email")
  Optional<ClubMember> findByEmail(@Param("email") String email, boolean social);
}