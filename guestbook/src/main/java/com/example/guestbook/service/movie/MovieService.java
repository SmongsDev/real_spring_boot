package com.example.guestbook.service.movie;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.guestbook.dto.MovieDTO;
import com.example.guestbook.dto.MovieImageDTO;
import com.example.guestbook.entity.movie.Movie;
import com.example.guestbook.entity.movie.MovieImage;

public interface MovieService{
  Long register(MovieDTO movieDTO);

  default Map<String, Object> dtoToEntity(MovieDTO movieDTO){
    Map<String, Object> entityMap = new HashMap<>();

    Movie movie = Movie.builder()
            .mno(movieDTO.getMno())
            .title(movieDTO.getTitle())
            .build();

    entityMap.put("movie", movie);

    List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

    if(imageDTOList != null && imageDTOList.size() > 0) {
      List<MovieImage> moiveImageList = imageDTOList.stream().map(movieImageDTO -> {
        MovieImage movieImage = MovieImage.builder()
                .path(movieImageDTO.getPath())
                .imgName(movieImageDTO.getImgName())
                .uuid(movieImageDTO.getUuid())
                .movie(movie)
                .build();
        return movieImage;
      }).collect(Collectors.toList());
      entityMap.put("imgList", moiveImageList);
    }
    return entityMap;
  }
}