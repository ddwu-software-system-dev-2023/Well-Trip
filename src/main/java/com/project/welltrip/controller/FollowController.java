package com.project.welltrip.controller;

import java.time.LocalDateTime;
import java.util.List;
import com.project.welltrip.domain.Follow;
import com.project.welltrip.domain.User;
import com.project.welltrip.service.FollowService;
import com.project.welltrip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @RequestMapping(value = {"/follow/followerList", "/follow/followingList"})
    public ModelAndView followList(HttpServletRequest request){

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        ModelAndView mv = new ModelAndView();

        if(request.getServletPath().equals("/follow/followerList")) {
            mv.setViewName("/follow/followerList");

            // 사용자를 친구 신청한 목록
            List<User> followerList = followService.getFollowerList(user.getId());
            mv.addObject("followerList", followerList);
        }
        else {
            mv.setViewName("/follow/followingList");

            // 사용자가 친구 신청한 목록
            List<User> followingList = followService.getFollowingList(user.getId());
            mv.addObject("followingList", followingList);

        }
        mv.addObject("userEmail", user.getEmail());

        List<User> test = userService.findAll2(user.getId());
        mv.addObject("testList", test);

        int following = followService.countFollowing(user.getId());
        int follower = followService.countFollower(user.getId());

        mv.addObject("followerCount", follower);
        mv.addObject("followingCount", following);

        return mv;
    }

    @RequestMapping(value={"/follow/unfollow/{followId}", "/follow/delete/{followId}"})
    public String deleteFollow(HttpServletRequest request, @PathVariable long followId){
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        if(request.getServletPath().equals("/follow/unfollow/"+followId)) {
            Follow follow = followService.findByUser_IdAndFollower(user.getId(), followId);
            followService.deleteFollow(follow.getFollowId());
            return "redirect:/follow/followingList";
        }
        else {
            Follow follow = followService.findByUser_IdAndFollower(followId, user.getId());
            followService.deleteFollow(follow.getFollowId());
            return "redirect:/follow/followerList";
        }
    }

    @RequestMapping("/follow/newFollow/{userId}")
    public String testAdd(HttpServletRequest request, @PathVariable long userId){
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        Follow follow = new Follow();
        follow.setFollowDate(LocalDateTime.now());
        follow.setFollower(userId);
        follow.setUser(user);

        followService.insertFollow(userId, follow);

        System.out.println("followId: "+userId);
        return "redirect:/follow/followerList";
    }


}
