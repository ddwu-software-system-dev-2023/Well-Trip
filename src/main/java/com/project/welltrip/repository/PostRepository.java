package com.project.welltrip.repository;

import com.project.welltrip.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * written by nayeon
 * date: 23.06.03
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
