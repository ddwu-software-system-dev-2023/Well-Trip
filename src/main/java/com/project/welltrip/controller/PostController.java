package com.project.welltrip.controller;

import com.project.welltrip.domain.*;
import com.project.welltrip.dto.CommentDto;
import com.project.welltrip.dto.PostCreateDto;
import com.project.welltrip.dto.PostDto;
import com.project.welltrip.dto.TravelDto;
import com.project.welltrip.repository.CommentRepository;
import com.project.welltrip.repository.PlaceRepository;
import com.project.welltrip.repository.PostRepository;
import com.project.welltrip.service.PostService;
import com.project.welltrip.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * written by nayeon
 * date: 23.06.22
 */
@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final TravelService travelService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PlaceRepository placeRepository;

//    @ModelAttribute("postDto")
//    public PostDto formBackingObject() {
//        PostDto postDto = PostDto.builder().build();
//        return postDto;
//    }

    @GetMapping("/posts")
    public String view(Model model) {
        List<PostDto> postDtos = postService.findAll();
        model.addAttribute("posts", postDtos);
        return "post/postList";
    }


    @GetMapping("/posts/new")
    public String createForm(Model model) {
        List<Place> places = placeRepository.findAll();
        // TODO 로그인한 유저 id로 바꾸기
        List<TravelDto> travels = travelService.getMyTravel(1L);

        model.addAttribute("places", places);
        model.addAttribute("travels", travels);
        model.addAttribute("postForm", new PostCreateDto());
        return "post/createPostForm";
    }

    @PostMapping("posts/new")
    public String create(@Valid PostCreateDto postForm, BindingResult result) {

        if (result.hasErrors()) {
            return "post/createPostForm";
        }

        PostCreateDto postCreateDto = new PostCreateDto(postForm.getPostId(), null, postForm.getTitle(), postForm.getContent(), postForm.getPlaceId(), postForm.getTravelId(), null, null);

        if (postForm.getPostId() == null) { // 추가
            // TODO: userId를 현재 로그인한 유저의 아이디로 변경
            postService.write(9L, postCreateDto);
        } else { // 수정
            // TODO: userId를 현재 로그인한 유저의 아이디로 변경
            postService.updatePost(postCreateDto.getPostId(), postCreateDto);
        }

        return "redirect:/posts";
    }

    // 글 상세보기
    @GetMapping("posts/{postId}")
    public String viewDetail(@PathVariable("postId") Long postId, Model model) {
        PostDto postDto = postService.findOne(postId);
        List<Comment> comments = commentRepository.findByPost(postId);
        CommentDto commentDto = new CommentDto();

        model.addAttribute("post", postDto);
        model.addAttribute("comments", comments);
        model.addAttribute("commentForm", commentDto);
        return "post/postDetail";
    }

    // 글 수정
    @GetMapping("posts/{postId}/edit")
    public String editPost(@PathVariable("postId") Long postId, Model model) {
        Post post = postRepository.findById(postId).get();
        PostCreateDto postDto = new PostCreateDto(postId, post.getWriter(), post.getTitle(), post.getContent(), post.getPlace().getId(), post.getTravel().getId(), post.getPlace(), post.getTravel());

        List<Place> places = placeRepository.findAll();
        // TODO 로그인한 유저 id로 바꾸기
        List<TravelDto> travels = travelService.getMyTravel(1L);

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
    public String addComment(@PathVariable("postId") Long postId, @Valid CommentDto commentForm, BindingResult result) {
        if (result.hasErrors()) {
            return "post/createPostForm";
        }

        CommentDto commentDto = new CommentDto(commentForm.getCommentId(), null, null, commentForm.getCreatedDate(), commentForm.getContent());

        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        postService.createComment(postId, 9L, commentForm);
        return "redirect:/posts/{postId}";
    }

    // 댓글 삭제
    @GetMapping("posts/{postId}/comment/delete")
    public String deleteComment(@PathVariable("postId") Long postId) {
        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        postService.deleteComment(postId, 9L);
        return "redirect:/posts/{postId}";
    }

    // 스크랩 등록
    @GetMapping("posts/{postId}/scrap")
    public String createScrap(@PathVariable("postId") Long postId) {
        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        Scrap scrap = postService.createScrap(9L, postId);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("posts/{postId}/scrap/delete")
    public String deleteScrap(@PathVariable("postId") Long postId) {
        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        postService.deleteScrap(postId, 9L);
        return "redirect:/posts/{postId}";
    }

}
