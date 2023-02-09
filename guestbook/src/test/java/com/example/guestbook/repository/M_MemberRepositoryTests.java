package com.example.guestbook.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.guestbook.entity.movie.M_Member;
import com.example.guestbook.repository.movie.M_MemberRepository;

@SpringBootTest
public class M_MemberRepositoryTests {
  
  @Autowired
  private M_MemberRepository memberRepository;

  @Test
  public void insertMembers(){
    IntStream.rangeClosed(1, 100).forEach(i -> {
      M_Member member = M_Member.builder()
              .email("r" + i + "@zerock.org")
              .pw("1111")
              .nickname("reviewer" + i)
              .build();
      memberRepository.save(member);
    });
  }
}
