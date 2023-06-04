package com.project.welltrip.service;

import com.project.welltrip.domain.Post;
import com.project.welltrip.domain.UserInfo;
import com.project.welltrip.dto.PostCreateDto;
import com.project.welltrip.dto.PostDto;
import com.project.welltrip.repository.PostRepository;
import com.project.welltrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * written by nayeon
 * date: 23.06.03
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 글 작성
    public Post write(Long userId, PostCreateDto postCreateDto) {
        Optional<UserInfo> findUser = userRepository.findById(userId);
        UserInfo userInfo = findUser.get();
        postCreateDto.setWriter(userInfo);
        Post post = postRepository.save(postCreateDto.toEntity());
        return post;
    }

    // 글 확인
    public List<PostDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            PostDto postDto = PostDto.builder().post(post).build();
            postDtos.add(postDto);
        }
        return postDtos;
    }


}
