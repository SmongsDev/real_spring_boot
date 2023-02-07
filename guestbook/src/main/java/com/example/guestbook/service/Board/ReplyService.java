package com.example.guestbook.service.Board;

import java.util.List;

import com.example.guestbook.dto.ReplyDTO;
import com.example.guestbook.entity.Board;
import com.example.guestbook.entity.Reply;

public interface ReplyService {
  Long register(ReplyDTO replyDTO);
  
  List<ReplyDTO> getList(Long bno);

  void modify(ReplyDTO replyDTO);
  
  void remove(Long rno);

  default Reply dtoToEntity(ReplyDTO replyDTO){
    Board board = Board.builder().bno(replyDTO.getBno()).build();

    Reply reply = Reply.builder()
          .rno(replyDTO.getRno())
          .text(replyDTO.getText())
          .replayer(replyDTO.getReplyer())
          .board(board)
          .build();
    return reply;
  }

  default ReplyDTO entityToDTO(Reply reply){

    ReplyDTO dto = ReplyDTO.builder()
            .rno(reply.getRno())
            .text(reply.getText())
            .replyer(reply.getReplayer())
            .regDate(reply.getRegDate())
            .modDate(reply.getModDate())
            .build();
    return dto;
  }
}
