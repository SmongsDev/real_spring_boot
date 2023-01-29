package com.example.guestbook.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

  private Long bno;

  private String title;

  private String content;

  private String writerEmail;

  private String writerName;

  private LocalDateTime regDate;

  private LocalDateTime modDate;

  private int replyCount;
  
}
