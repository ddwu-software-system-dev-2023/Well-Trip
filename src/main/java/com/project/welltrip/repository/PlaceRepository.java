package com.project.welltrip.repository;

import com.project.welltrip.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * written by nayeon
 * date: 23.06.18
 */
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
