package com.example.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDTO<DTO, EN> {
  
  private List<DTO> dtoList;

  private int totalPage;

  private int page;

  private int size;

  private int start, end;

  private boolean prev, next;

  private List<Integer> pageList;

  public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){ 
    dtoList = result.stream().map(fn).collect(Collectors.toList());

    totalPage = result.getTotalPages();

    makePageList(result.getPageable());
  }

  private void makePageList(Pageable pageable){
    this.page = pageable.getPageNumber() + 1;
    this.size = pageable.getPageSize();

    int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

    start = tempEnd - 9;  // 시작 페이지 수

    prev = start > 1; // 이전 여부
    
    end = totalPage > tempEnd ? tempEnd : totalPage; // 마지막 페이지 수

    next = totalPage > tempEnd; // 다음 여부

    pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
  }
}
