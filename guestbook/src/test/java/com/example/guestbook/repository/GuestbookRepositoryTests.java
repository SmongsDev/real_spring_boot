package com.example.guestbook.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.guestbook.entity.Guestbook;
import com.example.guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
public class GuestbookRepositoryTests {
  @Autowired
  private GuestbookRepository guestbookRepository;

  @Test
  public void insertDummies(){
    IntStream.rangeClosed(1, 300).forEach(i -> {
      Guestbook guestbook = Guestbook.builder()
              .title("Title...." + i)
              .content("Content...." + i)
              .writer("user" + (i % 10))
              .build();
      System.out.println(guestbookRepository.save(guestbook));
    });
  }

  @Test
  public void updateTest(){
    Optional<Guestbook> result = guestbookRepository.findById(300L);

    if(result.isPresent()){
      Guestbook guestbook = result.get(); // 객체 반환

      guestbook.changeTitle("Changed Title....");
      guestbook.changeContent("Changed Content...");

      guestbookRepository.save(guestbook);
    }
  }

  @Test
  public void testQuery1(){
    Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

    QGuestbook qGuestbook = QGuestbook.guestbook;

    String keyword = "1";

    BooleanBuilder builder = new BooleanBuilder();

    BooleanExpression expression = qGuestbook.title.contains(keyword);

    builder.and(expression);

    Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

    result.stream().forEach(guestbook -> {
      System.out.println(guestbook);
    });
  }
}