package com.project.welltrip.dto;

import com.project.welltrip.domain.Place;
import com.project.welltrip.domain.Post;
import com.project.welltrip.domain.Travel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {

    private Long id;

    private String title;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private String content;

    private Place place;

    private Travel travel;

    @Builder
    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.createdDate = post.getCreatedDate();
        this.lastModifiedDate = post.getLastModifiedDate();
        this.content = post.getContent();
        this.place = post.getPlace();
        this.travel = post.getTravel();
    }
}
