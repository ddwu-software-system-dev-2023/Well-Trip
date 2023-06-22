package com.project.welltrip.dto;

import com.project.welltrip.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long commentId;

    private User writer;

    private Post post;

    private LocalDateTime createdDate;

    @NotBlank(message = "내용을 입력해야 합니다")
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .writer(writer)
                .post(post)
                .content(content)
                .build();
    }
}
