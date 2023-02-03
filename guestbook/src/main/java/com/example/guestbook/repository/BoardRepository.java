package com.example.guestbook.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.guestbook.entity.Board;
import com.example.guestbook.repository.search.SearchBoardRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository{
  
  @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
  Object getBoardWithWrtier(@Param("bno") Long bno);

  @Query("select b, r from Board b left join Reply r on r.board = b where b.bno =:bno")
  List<Object[]> getBoardWithReply(@Param("bno") Long bno);

  @Query(value = "select b, w, count(r) "+
            "from Board b "+
            "left join b.writer w "+
            "left join Reply r on r.board = b "+
            "group by b",
            countQuery = "select count(b) from Board b")
  Page<Object[]> getBoardWithReplyCount(Pageable pageable);

  @Query("select b, w, count(r) "+
        "from Board b "+
        "left join b.writer w "+
        "left outer join Reply r on r.board = b "+
        "where b.bno =:bno")
  Object getBoardByBno(@Param("bno") Long bno);
}
