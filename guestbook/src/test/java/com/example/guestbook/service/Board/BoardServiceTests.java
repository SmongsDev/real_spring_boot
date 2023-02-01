package com.example.guestbook.service.Board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.guestbook.dto.BoardDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResultDTO;

@SpringBootTest
public class BoardServiceTests {
  
  @Autowired
  private BoardService boardService;

  @Test
  public void testRegister(){
    BoardDTO dto = BoardDTO.builder()
            .title("Test.")
            .content("Test....")
            .writerEmail("user55@aaa.com")
            .build();
    Long bno = boardService.register(dto);
  }

  @Test
  public void testList(){
    PageRequestDTO pageRequestDTO = new PageRequestDTO();

    PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

    for(BoardDTO boardDTO : result.getDtoList()){
      System.out.println(boardDTO);
    }
  }

  @Test
  public void testGet(){
    Long bno = 100L;

    BoardDTO boardDTO = boardService.get(bno);

    System.out.println(boardDTO);
  }

  @Test
  public void testRemove(){
    Long bno = 1L;

    boardService.removeWithReplies(bno);
  }

  @Test
  public void testModify(){
    BoardDTO boardDTO = BoardDTO.builder()
            .bno(2L)
            .title("변경합니다.")
            .content("내용 변경합니다.")
            .build();
          
    boardService.modify(boardDTO);
  }
}
