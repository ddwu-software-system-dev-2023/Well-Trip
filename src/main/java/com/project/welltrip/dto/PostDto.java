package com.project.welltrip.dto;

import com.project.welltrip.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostDto {

    private Long id;

    private String title;

    private String content;

    @Builder
    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
