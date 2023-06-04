package com.project.welltrip.repository;

import com.project.welltrip.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
}
