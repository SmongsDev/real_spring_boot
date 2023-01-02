package com.example.guestbook.service;

import com.example.guestbook.dto.GuestbookDTO;

public interface GuestbookService {
  Long register(GuestbookDTO dto);
}
