package com.project.welltrip.repository;

import com.project.welltrip.domain.Place;
import com.project.welltrip.domain.PlaceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PlaceRepositoryTest {

    @Autowired PostRepository postRepository;
    @Autowired PlaceRepository placeRepository;

    @Test
    public void 장소추가() throws Exception {

        Place place = new Place(null, PlaceType.RESTAURANT, "제나키친", "월요일 휴무", new ArrayList<>(), 127.0416965, 37.6034023);
        placeRepository.save(place);
    }

}