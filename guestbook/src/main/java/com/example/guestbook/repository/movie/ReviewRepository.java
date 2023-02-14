package com.example.guestbook.repository.movie;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.guestbook.entity.movie.M_Member;
import com.example.guestbook.entity.movie.Movie;
import com.example.guestbook.entity.movie.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
  
  @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
  List<Review> findByMovie(Movie movie);

  @Modifying //insert,update,delete 쿼리에서 벌크 연산시 사용한다
  @Query("delete from Review mr where mr.member = :member") // 비효율을 막기위해 where절 지정
  void deleteByMember(@Param("member") M_Member member);
}
