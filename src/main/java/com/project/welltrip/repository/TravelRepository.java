package com.project.welltrip.repository;

import com.project.welltrip.domain.Travel;
import com.project.welltrip.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * written by nayeon
 * date: 23.06.03
 */
public interface TravelRepository extends JpaRepository<Travel, Long> {
}
