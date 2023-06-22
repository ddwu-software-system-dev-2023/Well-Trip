package com.project.welltrip.service;

import java.util.Date;
import java.util.List;
import com.project.welltrip.domain.User;
import com.project.welltrip.dto.UserDto;
import com.project.welltrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * written by jiruen
 * date: 23.06.05
 */

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    // [C] 사용자 추가
    @Transactional
    public void insertUser (UserDto userDto) throws Exception {
        User result = userRepository.findByEmail(userDto.getUser().getEmail());
        userRepository.save(userDto.toEntity());
//        if(result == null)
//            userRepository.save(userDto.toEntity());
//        else
//            throw new Exception("중복 회원입니다.");
    }

    // [R] userId로 사용자 찾기
    public User getUser(Long userId) {
        Optional<User> result = userRepository.findById(userId);
        if(result.isPresent())
            return result.get();
        return null;
    }


    // [R] user email로 사용자 찾기
    public User getUserByEmail (String email) {
        User result = userRepository.findByEmail(email);
        if(result != null)
            return result;
        return null;
    }

    // [R] user email과 password로 사용자 찾기 (로그인 시 사용)
    public User getUserByEmailAndPassword(String email, String password) {
        User result = userRepository.findByEmailAndPassword(email, password);
        if(result != null)
            return result;
        return null;
    }

    public User findByFirstNameAndLastNameAndPhone(String firstName, String lastName, String phone) {
        User result = userRepository.findByFirstNameAndLastNameAndPhone(firstName, lastName, phone);
        if(result != null)
            return result;
        return null;
    }

    public User findByEmailAndPhoneAndBirthDate(String email, String phone, Date birthDate) {
        User result = userRepository.findByEmailAndPhoneAndBirthDate(email, phone, birthDate);
        if(result != null)
            return result;
        return null;
    }

    // [R] 사용자 전체 찾기
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    // [R] 현재 로그인된 사용자 제외한 사용자 전체 찾기
    public List<User> findAll2(long userId) {
        List<User> userList = userRepository.findByIdNot(userId);
        return userList;
    }

    //[U] 회원 정보 수정
    public void updateUser(User old, User update) {
        User result = userRepository.findById(old.getId()).get();

        result.setFirstName(update.getFirstName());
        result.setLastName(update.getLastName());
        result.setBirthDate(update.getBirthDate());
        result.setPhone(update.getPhone());
        result.setPassword(update.getPassword());

    }



    // [D] 사용자 삭제
    @Transactional
    public void deleteUser (User user) throws Exception {
        Optional<User> result = userRepository.findById(user.getId());
        if(result.isPresent()) {
            result.get().getDms().stream().forEach(dm -> {
                dm.setUser(null);
            });
            result.get().getFollows().stream().forEach(follow -> {
                follow.setUser(null);
            });
            userRepository.delete(user);
        }
        else
            throw new Exception("회원 삭제 불가합니다.");
    }


    public List<User> getAllByEmailNot(String email){
        List<User> result = userRepository.findAllByEmailNot(email);
        return result;
    }

    public List<User> getAllByEmailIsAndEmailNot(String keyword, String email){
        List<User> result = userRepository.findAllByEmailIsAndEmailNot(keyword, email);
        return result;
    }

}
