package com.example.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO{
  private long mno;

  private String title;

  @Builder.Default
  private List<MovieImageDTO> imageDTOList = new ArrayList<>();

  private double avg;

  private int reviewCnt;

  private LocalDateTime regDate;

  private LocalDateTime modDate;
}