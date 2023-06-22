package com.project.welltrip.repository;

import com.project.welltrip.domain.Comment;
import com.project.welltrip.domain.Like;
import com.project.welltrip.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * written by nayeon
 * date: 23.06.03
 */
public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("select l from Like l  inner join fetch l.post p inner join fetch l.user u where p.id = :postId and u.id = :userId")
    Optional<Like> findLike(@Param("postId") Long postId, @Param("userId") Long userId);

    @Query("select l from Like l inner join fetch l.post p where p.id = :postId")
    List<Like> findByPost(@Param("postId") Long postId);
}
