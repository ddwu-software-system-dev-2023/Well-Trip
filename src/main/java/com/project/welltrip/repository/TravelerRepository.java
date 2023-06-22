package com.project.welltrip.repository;

import com.project.welltrip.domain.Traveler;
import com.project.welltrip.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * written by nayeon
 * date: 23.06.18
 */
public interface TravelerRepository extends JpaRepository<Traveler, Long> {
    @Query("select t from Traveler t inner join fetch t.user u inner join fetch t.travel tr where u.id = :userId and tr.id = :travelId")
    Optional<Traveler> findTraveler(@Param("userId") Long userId, @Param("travelId") Long travelId);

    @Query("select t.user from Traveler t inner join t.travel tr where tr.id = :travelId")
    List<User> findUser(@Param("travelId") Long travelId);
}
