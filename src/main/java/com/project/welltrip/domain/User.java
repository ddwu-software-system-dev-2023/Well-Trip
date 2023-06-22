package com.project.welltrip.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;

@SuppressWarnings("serial")
@Setter
@Getter
@Entity
@NoArgsConstructor // 기본 생성자
//@RequiredArgsConstructor // 반드시 할당받아야 하는 필드들에 대한 생성자
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

    @NotEmpty(message = "* 이메일은 필수 정보입니다.")
    @Column(name = "email", table = "LOGIN", unique = true)
    private String email; // 이메일

    @NotEmpty(message = "* 비밀번호는 필수 정보입니다.")
//    @Size(min=8, max=12, message = "* 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @Size(min=8, max=20, message = "* 8 ~ 20자인 비밀번호를 사용하세요.")
    @Column(name = "password", table = "LOGIN")
    private String password; // 비밀번호

    @NotEmpty(message = "* 영문 이름(First name)은 필수 정보입니다.")
    private String firstName; // 이름

    @NotEmpty(message = "* 영문 성(Last name)은 필수 정보입니다.")
    private String lastName; // 성

    @NotBlank(message = "* 휴대전화 번호는 필수 정보입니다.")
    @Pattern(regexp = "^01\\d{1}\\d{3,4}\\d{4}$", message = "* 형식은 ex) 010XXXXXXXX 입니다.")
    private String phone; // 휴대전화

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="userId")
    private List<Follow> follows = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="userId")
    private List<Dm> dms = new ArrayList<>();

    private LocalDateTime createdDate; // 생성일자

    @NotNull(message = "* 생년월일은 필수 정보입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date birthDate; // 생년월일

//    private boolean certificationEmailCheck; // 이메일 인증 확인

    public User(String email) {
        this.email = email;
    }

    // login test
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Builder
    public User(Long id, String email, String password, String firstName, String lastName, String phone, LocalDateTime createdDate, Date birthDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.createdDate = createdDate;
        this.birthDate = birthDate;
    }
}