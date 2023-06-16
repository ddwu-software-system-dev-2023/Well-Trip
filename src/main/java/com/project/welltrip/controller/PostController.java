package com.project.welltrip.controller;

import com.project.welltrip.dto.PostCreateDto;
import com.project.welltrip.dto.PostDto;
import com.project.welltrip.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * written by nayeon
 * date: 23.06.04
 */
@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

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
        model.addAttribute("postForm", new PostCreateDto());
        return "post/createPostForm";
    }

    @PostMapping("posts/new")
    public String create(@Valid PostCreateDto postForm, BindingResult result) {

        if (result.hasErrors()) {
            return "post/createPostForm";
        }

        PostCreateDto postCreateDto = new PostCreateDto(null, postForm.getTitle(), postForm.getContent());

        // TODO: userId를 현재 로그인한 유저의 아이디로 변경
        postService.write(1L, postCreateDto);

        return "redirect:/posts";
    }

}
