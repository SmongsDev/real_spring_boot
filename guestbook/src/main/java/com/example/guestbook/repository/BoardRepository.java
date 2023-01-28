package com.example.guestbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.guestbook.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
  
  @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
  Object getBoardWithWrtier(@Param("bno") Long bno);

  @Query("select b, r from board b left join reply r on r.board = b where b.bno =:bno")
  List<Object[]> getBoardWithReply(@Param("bno") Long bno);
}
