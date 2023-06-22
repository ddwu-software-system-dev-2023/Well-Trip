package com.project.welltrip.service;

import com.project.welltrip.domain.*;
import com.project.welltrip.dto.CommentDto;
import com.project.welltrip.dto.PostCreateDto;
import com.project.welltrip.dto.PostDto;
import com.project.welltrip.dto.TravelDto;
import com.project.welltrip.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * written by nayeon
 * date: 23.06.22
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ScrapRepository scrapRepository;
    private final PlaceRepository placeRepository;
    private final TravelRepository travelRepository;
    private final CommentRepository commentRepository;

    // 글 작성
    public Post write(Long userId, PostCreateDto postCreateDto) {
        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();
        postCreateDto.setWriter(user);

        if (postCreateDto.getTravelId() != null) {
            Place place = placeRepository.findById(postCreateDto.getPlaceId()).get();
            postCreateDto.setPlace(place);
        }

        if (postCreateDto.getTravelId() != null) {
            Travel travel = travelRepository.findById(postCreateDto.getTravelId()).get();
            postCreateDto.setTravel(travel);
        }

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

    // 글 상세보기
    @Transactional(readOnly = true)
    public PostDto findOne(Long postId) {
        Post post = postRepository.findById(postId).get();
        PostDto postDto = PostDto.builder().post(post).build();
        return postDto;
    }

    // 글 수정
    public PostCreateDto updatePost(Long postId, PostCreateDto postDto) {
        Post post = postRepository.findById(postId).get();
        post.updatePost(postDto);
        return postDto;
    }

    // 글 삭제
    public Long deletePost(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }

    // 스크랩 등록
    public Scrap createScrap(Long userId, Long postId) {
        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postId).get();
        // 이미 스크랩을 한 게시물이라면 X
        Optional<Scrap> tmp = scrapRepository.findScrap(postId, userId);
        if (!tmp.isPresent()) {
            Scrap scrap = new Scrap(null, user, post);
            Scrap saved = scrapRepository.save(scrap);
            return saved;
        } else {
            return null;
        }
    }

    // 스크랩 취소
    public Long deleteScrap(Long postId, Long userId) {
        Scrap scrap = scrapRepository.findScrap(postId, userId).get();
        scrapRepository.delete(scrap);
        return scrap.getId();
    }

    // 댓글 등록
    public Comment createComment(Long postId, Long userId, CommentDto commentDto) {
        Optional<User> findUser = userRepository.findById(userId);
        User user = findUser.get();
        Post post = postRepository.findById(postId).get();
        commentDto.setWriter(user);
        commentDto.setPost(post);

        Comment comment = commentRepository.save(commentDto.toEntity());

        return comment;
    }

    // 댓글 취소
    public Long deleteComment(Long postId, Long userId) {
        Comment comment = commentRepository.findComment(postId, userId).get();
        commentRepository.delete(comment);
        return comment.getId();
    }


}
