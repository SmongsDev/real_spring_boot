package com.example.guestbook.service;

import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestbookDTO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService{
  @Override
  public Long register(GuestbookDTO dto){
    return null;
  }
}
