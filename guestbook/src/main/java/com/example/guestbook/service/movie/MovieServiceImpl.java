package com.example.guestbook.service.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import com.example.guestbook.dto.MovieDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResultDTO;
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

  @Override
  public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {

    Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

    Page<Object[]> result = movieRepository.getListPage(pageable);

    Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
            (Movie) arr[0],
            (List<MovieImage>) (Arrays.asList((MovieImage) arr[1])),
            (Double) arr[2],
            (Long) arr[3])
    );
    return new PageResultDTO<>(result,fn);
  }

  @Override
  public MovieDTO getMovie(Long mno) {

    List<Object[]> result = movieRepository.getMovieWithAll(mno);

    Movie movie = (Movie) result.get(0)[0] ; // Movie 엔티티는 가장 앞에 존재 - 모든 Row가 동일한 값이다

    List<MovieImage> movieImageList = new ArrayList<>(); // 영화의 이미지개수만큼 MovieImage 객체 필요

    result.forEach(arr ->{
      MovieImage movieImage = (MovieImage) arr[1];
      movieImageList.add(movieImage);
    });

    Double avg = (Double) result.get(0)[2]; //평균 평점 - 모든 Row가 동일한 값
    Long reviewCnt = (Long) result.get(0)[3]; //리뷰 개수 - 모든 Row가 동일한 값

    return entitiesToDTO(movie,movieImageList,avg,reviewCnt);
  }
}