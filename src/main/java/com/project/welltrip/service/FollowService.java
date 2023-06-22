package com.project.welltrip.service;

import com.project.welltrip.domain.Follow;
import com.project.welltrip.domain.User;
import com.project.welltrip.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    // [C] Follow insert
    @Transactional
    public void insertFollow (long followee, Follow follow) {
        if (followRepository.countByFollowerAndUser_Id(followee, follow.getUser().getId()) == 0)
            followRepository.save(follow);
    }

    // [R] Following 리스트 반환
    public List<User> getFollowingList(long userId) {
        List<User> result = followRepository.findFollowingList(userId);
        if(!result.isEmpty()) {
            return result;
        }
        return null;
    }

//    public List<User> test(long userId) {
//        List<User> result = followRepository.findFolloweeUser(userId);
//        if(!result.isEmpty()) {
//            return result;
//        }
//        return null;
//    }

    // [R] Follower 리스트 반환
    public List<User> getFollowerList(long userId){
        List<User> result = followRepository.findFollowerList(userId);
        if(!result.isEmpty()) {
            return result;
        }
        return null;
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
