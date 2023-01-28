package com.example.guestbook.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.guestbook.entity.Member;

@SpringBootTest
public class MemberRepositoryTests {
  @Autowired
  private MemberRepository memberRepository;

  @Test
  public void insertMember(){
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member member = Member.builder()
            .email("user"+i + "@aaa.com")
            .password("1111")
            .name("USER"+i)
            .build();

      memberRepository.save(member);
    });
  }
}
