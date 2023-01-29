package com.example.guestbook.service.Board;

import com.example.guestbook.dto.BoardDTO;
import com.example.guestbook.entity.Board;
import com.example.guestbook.entity.Member;

public interface BoardService {
  
  Long register(BoardDTO dto);

  default Board dtoToEntity(BoardDTO dto){
    Member member = Member.builder().email(dto.getWriterEmail()).build();

    Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
    return board;
  }
}
