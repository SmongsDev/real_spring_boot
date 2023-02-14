package com.example.guestbook.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.guestbook.entity.movie.M_Member;
import com.example.guestbook.repository.movie.M_MemberRepository;
import com.example.guestbook.repository.movie.ReviewRepository;

@SpringBootTest
public class M_MemberRepositoryTests {
  
  @Autowired
  private M_MemberRepository memberRepository;

  @Autowired
  private ReviewRepository reviewRepository;

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

  @Commit
  @Transactional
  @Test
  public void testDeleteMember(){
    Long mid = 1L;

    M_Member member = M_Member.builder().mid(mid).build();
    
    reviewRepository.deleteByMember(member);
    memberRepository.deleteById(mid);

  }
}
