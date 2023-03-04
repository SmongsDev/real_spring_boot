package com.example.guestbook.service.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.guestbook.dto.MovieDTO;
import com.example.guestbook.entity.movie.Movie;
import com.example.guestbook.entity.movie.MovieImage;
import com.example.guestbook.repository.movie.MovieImageRepository;
import com.example.guestbook.repository.movie.MovieRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
  private final MovieRepository movieRepository;

  private final MovieImageRepository imageRepository;

  @Transactional
  @Override
  public Long register(MovieDTO movieDTO){
    Map<String, Object> entityMap = dtoToEntity(movieDTO);
    Movie movie = (Movie) entityMap.get("movie");
    List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

    movieRepository.save(movie);

    movieImageList.forEach(movieImage -> {
      imageRepository.save(movieImage);
    });
    return movie.getMno();
  }
}