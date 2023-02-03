package com.example.guestbook.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.guestbook.entity.Board;

public interface SearchBoardRepository {
  Board search1();

  Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
