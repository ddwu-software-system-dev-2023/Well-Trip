package com.project.welltrip.service;

import com.project.welltrip.domain.User;
import com.project.welltrip.dto.UserDto;
import com.project.welltrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * written by jiruen
 * date: 23.06.05
 */

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 추가
    public User insertUser (UserDto userDto) {
        User result = userRepository.findByEmail(userDto.getUser().getEmail());
        if(result == null) {
            userRepository.save(userDto.toEntity());
        }
        return null;
    }

    // userId로 사용자 찾기
    public User getUser(Long userId) {
        Optional<User> result = userRepository.findById(userId);
        if(result.isPresent())
            return result.get();
        return null;
    }




}
