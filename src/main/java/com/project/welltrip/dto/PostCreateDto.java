package com.project.welltrip.dto;

import com.project.welltrip.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDto {

    private Long postId;

    private User writer;

    @NotBlank(message = "제목을 입력해야 합니다")
    private String title;

    @NotBlank(message = "내용을 입력해야 합니다")
    private String content;

    private Long placeId;
    private Long travelId;

    private Place place;

    private Travel travel;

    public Post toEntity() {
        return Post.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .place(place)
                .travel(travel)
                .build();
    }
}
