package com.project.welltrip.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.List;

/**
 * written by jiruen
 * date: 23.06.05
 */

@SuppressWarnings("serial")
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자
@SequenceGenerator(
        name = "USER_SEQ_GENERATOR",
        sequenceName = "USER_SEQ", // 시퀸스 명
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
@Table(name = "userInfo")
@SecondaryTable(name = "LOGIN",
        pkJoinColumns = @PrimaryKeyJoinColumn(
                name = "userId", referencedColumnName = "userId"))
public class User implements Serializable {

    @Id
    @Column(name="userId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    private Long id; // User Id

    @NotEmpty
    @Column(name = "email", table = "LOGIN", unique = true)
    private String email; // 이메일

    @NotEmpty
    @Size(min=8, max=12)
    @Column(name = "password", table = "LOGIN")
    private String password; // 비밀번호

    @NotEmpty
    private String firstName; // 이름

    @NotEmpty
    private String lastName; // 성

    @NotEmpty
    private LocalDateTime createdDate; // 생성일자

    @NotEmpty
    private LocalDateTime birthDate; // 생년월일

//    private boolean certificationCheck; // 이메일 인증 확인

    @NotBlank
    @Pattern(regexp="^01\\d{1}\\d{3,4}\\d{4}$")
    private String phone; // 휴대전화

    @OneToMany
    @JoinColumn(name="userId")
    private List<Follow> follows = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="userId")
    private List<Dm> dms = new ArrayList<>();

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}