package com.project.welltrip.service;

import com.project.welltrip.domain.Follow;
import com.project.welltrip.domain.User;
import com.project.welltrip.repository.FollowRepository;
import com.project.welltrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    // [C] Follow insert
    @Transactional
    public void insertFollow (long followee, Follow follow) {
        if (followRepository.countByFollowerAndUser_Id(followee, follow.getUser().getId()) == 0)
            followRepository.save(follow);
    }

    // [R] Following 리스트 반환
    public List<User> getFollowingList(long userId) {

        List<Follow> resultList = followRepository.findByUser_Id(userId);
        List<User> userList = new ArrayList<>();

        for (Follow result : resultList ) {
             User user = userRepository.findUser(result.getFollower());
             if (user != null)
                 userList.add(user);
        }

        return userList;

//        List<User> result = followRepository.findFollowingList(userId);
//        if(!result.isEmpty()) {
//            return result;
//        }
//        return null;
    }

    public List<User> getFollowingListByEmail(long userId, String email) {

        List<User> userList = getFollowingList(userId);
        List<User> result = new ArrayList<>();

        if(userList == null)
            return null;

        for (User user : userList )
            if (user.getEmail().equals(email))
                result.add(user);
        return result;

//        List<User> result = followRepository.findFollowingListByEmail(userId, email);
//        if(!result.isEmpty()) {
//            return result;
//        }
//        return null;
    }


    // [R] Follower 리스트 반환
    public List<User> getFollowerList(long userId){
//        List<User> result = followRepository.findFollowerList(userId);
//        if(!result.isEmpty()) {
//            return result;
//        }
//        return null;

        List<Follow> resultList = followRepository.findByFollower(userId);
        List<User> userList = new ArrayList<>();

        for (Follow result : resultList ) {
            User user = userRepository.findUser(result.getUser().getId());
            if (user != null)
                userList.add(user);
        }
        return userList;
    }

    public List<User> getFollowerListByList(long userId, String email){

        List<User> userList = getFollowerList(userId);
        List<User> result = new ArrayList<>();

        if(userList == null)
            return null;

        for (User user : userList )
            if (user.getEmail().equals(email))
                result.add(user);

        return result;

//        List<User> result = followRepository.findFollowerListByEmail(userId, email);
//        if(!result.isEmpty()) {
//            return result;
//        }
//        return null;
    }

    // [R] Following 수 반환
    public int countFollowing(long userId){
        return followRepository.countByUser_Id(userId);
    }

    // [R] Follower 수 반환
    public int countFollower(long userId){
        return followRepository.countByFollower(userId);
    }

    public Follow findByUser_IdAndFollower(long userId, long follower) {
        return followRepository.findByUser_IdAndFollower(userId, follower);
    };



    // [D] Following 수 반환
    public void deleteFollow(long followId){
        Optional<Follow> result = followRepository.findById(followId);
        if (result.isPresent())
            followRepository.deleteById(followId);

    }


}
