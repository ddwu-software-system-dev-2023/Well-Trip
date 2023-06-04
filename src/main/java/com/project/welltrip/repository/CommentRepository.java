package com.project.welltrip.repository;

import com.project.welltrip.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * written by nayeon
 * date: 23.06.03
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
