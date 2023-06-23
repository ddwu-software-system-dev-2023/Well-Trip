package com.project.welltrip.controller;

import com.project.welltrip.domain.*;
import com.project.welltrip.dto.CommentDto;
import com.project.welltrip.dto.PostCreateDto;
import com.project.welltrip.dto.PostDto;
import com.project.welltrip.dto.TravelDto;
import com.project.welltrip.repository.CommentRepository;
import com.project.welltrip.repository.LikeRepository;
import com.project.welltrip.repository.PlaceRepository;
import com.project.welltrip.repository.PostRepository;
import com.project.welltrip.service.PostService;
import com.project.welltrip.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * written by nayeon
 * date: 23.06.22
 */
@Controller
@RequiredArgsConstructor
@SessionAttributes("userSession")
public class PostController {
    private final PostService postService;
    private final TravelService travelService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PlaceRepository placeRepository;
    private final LikeRepository likeRepository;

    // 게시물 목록 보기
    @GetMapping("/posts")
    public String view(HttpServletRequest request, Model model) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = null;
        if (userSession != null) {
            user = userSession.getUser();
        }

        List<PostDto> postDtos = postService.findAll();
        model.addAttribute("posts", postDtos);
        if (user == null) {
            model.addAttribute("userId", 0);
        } else {
            model.addAttribute("userId", user.getId());
        }
        return "post/postList";
    }

    // 내가 스크랩한 글 보기
    @GetMapping("/posts/scrap/{userId}")
    public String getMyScrap(HttpServletRequest request, @PathVariable("userId") Long userId, Model model) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        if (userSession == null) {
            return "redirect:/login";
        }
        User user = userSession.getUser();

        List<PostDto> postDtos = postService.findMyScrap(user.getId());
        model.addAttribute("posts", postDtos);
        if (user == null) {
            model.addAttribute("userId", 0);
        } else {
            model.addAttribute("userId", user.getId());
        }
        return "post/postList";
    }


    // 내가 작성한 글 보기
    @GetMapping("/posts/my/{userId}")
    public String getMyPost(HttpServletRequest request, @PathVariable("userId") Long userId, Model model) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        if (userSession == null) {
            return "redirect:/login";
        }
        User user = userSession.getUser();

        List<PostDto> postDtos = postService.findMyPost(user.getId());
        model.addAttribute("posts", postDtos);
        if (user == null) {
            model.addAttribute("userId", 0);
        } else {
            model.addAttribute("userId", user.getId());
        }
        return "post/postList";
    }


    // 게시물 등록
    @GetMapping("/posts/new")
    public String createForm(HttpServletRequest request, Model model) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        if (userSession == null) {
            return "redirect:/login";
        }
        User user = userSession.getUser();

        List<Place> places = placeRepository.findAll();
        // TODO 로그인한 유저 id로 바꾸기
        List<TravelDto> travels = travelService.getMyTravel(user.getId());

        model.addAttribute("places", places);
        model.addAttribute("travels", travels);
        model.addAttribute("postForm", new PostCreateDto());
        return "post/createPostForm";
    }

    @PostMapping("posts/new")
    public String create(HttpServletRequest request, @Valid PostCreateDto postForm, BindingResult result) {

        if (result.hasErrors()) {
            return "post/createPostForm";
        }

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        if (userSession == null) {
            return "redirect:/login";
        }
        User user = userSession.getUser();

        PostCreateDto postCreateDto = new PostCreateDto(postForm.getPostId(), null, postForm.getTitle(), postForm.getContent(), postForm.getPlaceId(), postForm.getTravelId(), null, null);

        if (postForm.getPostId() == null) { // 추가
            // TODO: userId를 현재 로그인한 유저의 아이디로 변경
            postService.write(user.getId(), postCreateDto);
        } else { // 수정
            postService.updatePost(postCreateDto.getPostId(), postCreateDto);
        }

        return "redirect:/posts";
    }

    // 글 상세보기
    @GetMapping("posts/{postId}")
    public String viewDetail(HttpServletRequest request, @PathVariable("postId") Long postId, Model model) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = null;
        if (userSession != null) {
            user = userSession.getUser();
        }

        PostDto postDto = postService.findOne(postId);
        List<Comment> comments = commentRepository.findByPost(postId);
        CommentDto commentDto = new CommentDto();
        List<Like> likes = likeRepository.findByPost(postId);

        model.addAttribute("post", postDto);
        model.addAttribute("comments", comments);
        model.addAttribute("commentForm", commentDto);
        model.addAttribute("likeCount", likes.size());
        if (user == null) {
            model.addAttribute("userId", 0);
        } else {
            model.addAttribute("userId", user.getId());
        }
        return "post/postDetail";
    }

    // 글 수정
    @GetMapping("posts/{postId}/edit")
    public String editPost(HttpServletRequest request, @PathVariable("postId") Long postId, Model model) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        Post post = postRepository.findById(postId).get();
        Long placeId = null;
        Long travelId = null;
        if (post.getPlace() != null) {
            placeId = post.getPlace().getId();
        }
        if (post.getTravel() != null) {
            travelId = post.getTravel().getId();
        }
        PostCreateDto postDto = new PostCreateDto(postId, post.getWriter(), post.getTitle(), post.getContent(), placeId, travelId, post.getPlace(), post.getTravel());

        List<Place> places = placeRepository.findAll();
        // TODO 로그인한 유저 id로 바꾸기
        List<TravelDto> travels = travelService.getMyTravel(user.getId());

        model.addAttribute("places", places);
        model.addAttribute("travels", travels);
        model.addAttribute("postForm", postDto);
        return "post/createPostForm";
    }

    // 글 삭제
    @GetMapping("posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId) {
        Long deleted = postService.deletePost(postId);
        return "redirect:/posts";
    }

    // 댓글 등록
    @PostMapping("posts/{postId}/comment")
    public String addComment(HttpServletRequest request, @PathVariable("postId") Long postId, @Valid CommentDto commentForm, BindingResult result) {
        if (result.hasErrors()) {
            return "post/createPostForm";
        }

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        if (userSession == null) {
            return "redirect:/login";
        }
        User user = userSession.getUser();

        CommentDto commentDto = new CommentDto(commentForm.getCommentId(), null, null, commentForm.getCreatedDate(), commentForm.getContent());

        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        postService.createComment(postId, user.getId(), commentForm);
        return "redirect:/posts/{postId}";
    }

    // 댓글 삭제
    @GetMapping("posts/{postId}/comment/delete")
    public String deleteComment(HttpServletRequest request, @PathVariable("postId") Long postId) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        postService.deleteComment(postId, user.getId());
        return "redirect:/posts/{postId}";
    }

    // 스크랩 등록
    @GetMapping("posts/{postId}/scrap")
    public String createScrap(HttpServletRequest request, @PathVariable("postId") Long postId) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        if (userSession == null) {
            return "redirect:/login";
        }
        User user = userSession.getUser();

        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        Scrap scrap = postService.createScrap(user.getId(), postId);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("posts/{postId}/scrap/delete")
    public String deleteScrap(HttpServletRequest request, @PathVariable("postId") Long postId) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        postService.deleteScrap(postId, user.getId());
        return "redirect:/posts/{postId}";
    }

    // 좋아요 추가
    @GetMapping("posts/{postId}/like")
    public String createLike(HttpServletRequest request, @PathVariable("postId") Long postId) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        if (userSession == null) {
            return "redirect:/login";
        }
        User user = userSession.getUser();

        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        Like like = postService.createLike(user.getId(), postId);
        return "redirect:/posts/{postId}";
    }

    // 좋아요 취소
    @GetMapping("posts/{postId}/like/delete")
    public String deleteLike(HttpServletRequest request, @PathVariable("postId") Long postId) {
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        User user = userSession.getUser();

        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        postService.deleteLike(postId, user.getId());
        return "redirect:/posts/{postId}";
    }


}
