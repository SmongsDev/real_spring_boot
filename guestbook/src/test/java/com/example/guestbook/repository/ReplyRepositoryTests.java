package com.example.guestbook.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.guestbook.entity.Board;
import com.example.guestbook.entity.Reply;

@SpringBootTest
public class ReplyRepositoryTests {
  @Autowired
  private ReplyRepository replyRepository;

  @Test
  public void insertReply(){
    IntStream.rangeClosed(1, 300).forEach(i ->{
      long bno = (long)(Math.random() * 100) + 1;

      Board board = Board.builder().bno(bno).build();

      Reply reply = Reply.builder()
            .text("Reply...."+ i)
            .board(board)
            .replayer("guest")
            .build();
          
      replyRepository.save(reply);
    }); 
  }

  @Test
  public void readReply1(){
    Optional<Reply> result = replyRepository.findById(1L);

    Reply reply = result.get();

    System.out.println(reply);
    System.out.println(reply.getBoard());
  }
}