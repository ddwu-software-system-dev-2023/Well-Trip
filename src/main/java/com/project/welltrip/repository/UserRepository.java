package com.project.welltrip.repository;

import com.project.welltrip.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * written by jiruen
 * date: 23.06.05
 */

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 user 찾기
    User findByEmail(String email);

    // 이름으로 user 찾기
    User findByFirstName(String firstName);

    // 성으로 user 찾기
    User findByLastName(String lastName);

    User findByFirstNameAndLastName(String firstName, String lastName);


}
