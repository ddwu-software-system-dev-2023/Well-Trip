package com.project.welltrip.repository;

import java.util.List;
import com.project.welltrip.domain.Follow;
import com.project.welltrip.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findByUser_IdAndFollower(long userId, long follower);

    // 사용자가 친구 신청한 리스트
    List<Follow> findByUser_Id(long userId);

    // 사용자가 친구 신청한 리스트
    List<Follow> findByUser(User user);

    // 사용자를 친구 신청한 리스트
    List<Follow> findByFollower(long userId);

    // 사용자가 친구 신청한 수
    int countByUser_Id(long userId);

    // 사용자를 친구 신청한 수
    int countByFollower(long userId);

    int countByFollowerAndUser_Id(long follower, long userId);

    // 사용자가 친구 신청한 목록(팔로잉)
//    @Query("select user from User user where user.id = (select f.follower " +
//            "from User u join Follow f on u.id = f.user.id " +
//            "where f.user.id = ?1)")
//    List<User> findFollowingList(long userId);

    @Query("select user from User user where user.id = (select f.follower " +
            "from User u join Follow f on u.id = f.user.id " +
            "where f.user.id = ?1) and user.email like ?2")
    List<User> findFollowingListByEmail(long userId, String keyword);

    // 사용자를 친구 신청한 목록 (팔로우)
//    @Query("select u from User u join Follow f on u = f.user " +
//            "where f.follower = ?1")
//    List<User> findFollowerList(long userId);

    @Query("select u from User u join Follow f on u = f.user " +
            "where f.follower = ?1 and u.email like ?2")
    List<User> findFollowerListByEmail(long userId, String keyword);

//    Follow deleteByFollowId(long followId);

}
