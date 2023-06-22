package com.project.welltrip.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.project.welltrip.domain.Follow;
import com.project.welltrip.domain.User;
import com.project.welltrip.service.FollowService;
import com.project.welltrip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @RequestMapping(value = {"/follow/followerList", "/follow/followingList",
    "/my-page/follow/followerList", "/my-page/follow/followingList"})
    public ModelAndView followList(HttpServletRequest request){

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        ModelAndView mv = new ModelAndView();

        if(request.getServletPath().equals("/follow/followerList")
        || request.getServletPath().equals("/my-page/follow/followerList")) {
            mv.setViewName("/follow/followerList");

            // 사용자를 친구 신청한 목록
            List<User> followerList = followService.getFollowerList(user.getId());
            mv.addObject("followerList", followerList);
        }
        else {
            mv.setViewName("/follow/followingList");

            // 사용자가 친구 신청한 목록
            List<User> followingList = followService.getFollowingList(user.getId());


            System.out.println(followingList);
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

    @PostMapping(value={"/follow/followerSearch", "/follow/followingSearch"})
    public ModelAndView search(HttpServletRequest request,
                               @RequestParam("keyword") String keyword){

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        ModelAndView mv = new ModelAndView();
        mv.addObject("searchDiv", true);

        if(request.getServletPath().equals("/follow/followerSearch")) {
            if (keyword == "null") {
                mv.setViewName("/follow/followerList");
                return mv;
            }

            mv.setViewName("/follow/followerList");

            List<User> searchFollowerList = followService.getFollowerListByList(user.getId(), keyword);
            if(searchFollowerList == null)
                searchFollowerList = null;
            mv.addObject("searchFollowerList", searchFollowerList);
        }
        else {

            if (keyword == "null") {
                mv.setViewName("/follow/followingList");
                return mv;
            }

            mv.setViewName("/follow/followingList");

            // 사용자가 친구 신청한 목록
            List<User> searchFollowingList = followService.getFollowingListByEmail(user.getId(), keyword);
            if(searchFollowingList == null)
                searchFollowingList = null;

            mv.addObject("searchFollowingList", searchFollowingList);

        }

        int following = followService.countFollowing(user.getId());
        int follower = followService.countFollower(user.getId());

        mv.addObject("keyword", keyword);
        mv.addObject("userEmail", user.getEmail());
        mv.addObject("followerCount", follower);
        mv.addObject("followingCount", following);

        return mv;

    }
//
//    @PostMapping(value={"/follow/followSearch"})
//    public String searchFollow(){
//        return "";
//    }

    @GetMapping("/follow/exploreFollow")
    public ModelAndView exploreFollowForm(HttpServletRequest request){
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        ModelAndView mv = new ModelAndView();

        List<User> userList = userService.getAllByEmailNot(user.getEmail());
        mv.addObject("userList", userList);

        mv.setViewName("follow/followExploreForm");
        return mv;
    }

    @PostMapping("/follow/exploreFollow")
    public ModelAndView exploreFollow(HttpServletRequest request,
                                @RequestParam("keyword") String keyword) {

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        ModelAndView mv = new ModelAndView();

        List<User> userList;

        if(keyword == null)
            userList = userService.getAllByEmailNot(user.getEmail());
        else
            userList = userService.getAllByEmailIsAndEmailNot(keyword, user.getEmail());

        mv.addObject("keyword", keyword);
        mv.addObject("userList", userList);
        mv.setViewName("follow/followExploreForm");
        return mv;

    }


}
