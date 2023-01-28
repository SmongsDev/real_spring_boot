package com.example.guestbook.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;

  private String title;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member writer;
}
