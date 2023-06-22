package com.project.welltrip.repository;

import com.project.welltrip.domain.Scrap;
import com.project.welltrip.domain.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * written by nayeon
 * date: 23.06.03
 */
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    @Query("select s from Scrap s  inner join fetch s.post p inner join fetch s.user u where p.id = :postId and u.id = :userId")
    Optional<Scrap> findScrap(@Param("postId") Long postId, @Param("userId") Long userId);
}
