package com.example.guestbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.guestbook.entity.Board;
import com.example.guestbook.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
  @Modifying
  @Query("delete from Reply r where r.board.bno =:bno")
  void deleteByBno(@Param("bno") Long bno);

  List<Reply> getRepliesByBoardOrderByRno(Board board);
}
