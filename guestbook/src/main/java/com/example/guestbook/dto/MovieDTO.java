package com.example.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}