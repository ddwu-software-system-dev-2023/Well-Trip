package com.project.welltrip.repository;

import com.project.welltrip.domain.Comment;
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
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c inner join fetch c.post p inner join fetch c.writer u where p.id = :postId and u.id = :userId")
    Optional<Comment> findComment(@Param("postId") Long postId, @Param("userId") Long userId);

    @Query("select c from Comment c inner join fetch c.post p where p.id = :postId")
    List<Comment> findByPost(@Param("postId") Long postId);
}
