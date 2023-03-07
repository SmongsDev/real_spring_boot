package com.example.guestbook.repository.movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.example.guestbook.entity.movie.Movie;

import com.example.guestbook.entity.movie.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>, QuerydslPredicateExecutor<Movie>{
  
  @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) "
    + "from Movie m left join MovieImage mi on mi.movie = m "
    + "left join Review r on r.movie = m group by m")
  Page<Object[]> getListPage(Pageable pageable);

  @Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct(r))" +
    " from Movie m left outer join MovieImage mi on mi.movie = m " +
    " left outer join Review r on r.movie = m " +
    " where m.mno = :mno group by mi")
  List<Object[]> getMovieWithAll(@Param("mno") Long mno);
}
