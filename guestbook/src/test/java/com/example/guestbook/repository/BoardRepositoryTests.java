package com.example.guestbook.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.guestbook.entity.Board;
import com.example.guestbook.entity.Member;

@SpringBootTest
public class BoardRepositoryTests {
  @Autowired
  private BoardRepository boardRepository;

  @Test
  public void insertBoard(){
    IntStream.rangeClosed(1, 100).forEach(i ->{
      Member member = Member.builder().email("user"+i + "@aaa.com").build();

      Board board = Board.builder()
            .title("Title..."+i)
            .content("Content...."+i)
            .writer(member)
            .build();

      boardRepository.save(board);
    });
  }

  @Transactional
  @Test
  public void testRead1(){
    Optional<Board> result = boardRepository.findById(100L);

    Board board = result.get();

    System.out.println(board);
    System.out.println(board.getWriter());
  }

  @Test
  public void testReadWithWriter(){
    Object result = boardRepository.getBoardWithWrtier(100L);

    Object[] arr = (Object[])result;

    System.out.println("--------------------------");
    System.out.println(Arrays.toString(arr));
  }
  
  @Test
  public void testGetBoardWithReply(){
    List<Object[]> result = boardRepository.getBoardWithReply(100L);

    for (Object[] arr : result){
      System.out.println(Arrays.toString(arr));
    }
  }
}
