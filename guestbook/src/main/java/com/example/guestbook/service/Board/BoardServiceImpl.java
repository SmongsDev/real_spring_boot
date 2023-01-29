package com.example.guestbook.service.Board;

import org.springframework.stereotype.Service;

import com.example.guestbook.dto.BoardDTO;
import com.example.guestbook.entity.Board;
import com.example.guestbook.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

  private final BoardRepository repository;

  @Override
  public Long register(BoardDTO dto) {
    log.info(dto);

    Board board = dtoToEntity(dto);

    repository.save(board);

    return board.getBno();
  }
  
}
