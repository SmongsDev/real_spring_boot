package com.example.guestbook.service;

import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestbookDTO;
import com.example.guestbook.entity.Guestbook;
import com.example.guestbook.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor // 의존성 자동 주입
public class GuestbookServiceImpl implements GuestbookService{
  
  private final GuestbookRepository repository; // 반드시 final로 선언
  
  @Override
  public Long register(GuestbookDTO dto){
    log.info("DTO------------------------");
    log.info(dto);

    Guestbook entity = dtoToEntity(dto);

    log.info(entity);

    repository.save(entity);

    return entity.getGno();
  }
}
