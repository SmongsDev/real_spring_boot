package com.example.guestbook.entity.movie;

import javax.persistence.*;

import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long inum;

  private String uuid;

  private String imgName;

  private String path;

  @ManyToOne(fetch = FetchType.LAZY)
  private Movie movie;
}
