package com.example.guestbook.service.Board;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.guestbook.dto.BoardDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResultDTO;
import com.example.guestbook.entity.Board;
import com.example.guestbook.entity.Member;
import com.example.guestbook.repository.BoardRepository;
import com.example.guestbook.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

  private final BoardRepository repository;

  private final ReplyRepository replyRepository;

  @Override
  public Long register(BoardDTO dto) {
    log.info(dto);

    Board board = dtoToEntity(dto);

    repository.save(board);

    return board.getBno();
  }
  
  @Override
  public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO){
    log.info(pageRequestDTO);

    Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0], (Member)en[1], (Long)en[2]));

    Page<Object[]> result = repository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

    return new PageResultDTO<>(result, fn);
  }

  @Override
  public BoardDTO get(Long bno){
    Object result = repository.getBoardByBno(bno);

    log.info(result);
    Object[] arr = (Object[])result;
    log.info(arr);
    return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
  }

  @Transactional
  @Override
  public void removeWithReplies(Long bno){
    replyRepository.deleteByBno(bno);

    repository.deleteById(bno);
  }

  @Transactional
  @Override
  public void modify(BoardDTO boardDTO){
    Board board = repository.getOne(boardDTO.getBno());

    board.changeTitle(boardDTO.getTitle());
    board.changeContent(boardDTO.getContent());

    repository.save(board);
  }
}
