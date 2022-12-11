package com.spring.restApiProject.domain.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID", nullable = false)
    @Comment("회원 아이디")
    private Long id;

    @Column(name = "NAME", nullable = false)
    @Comment("회원 이름")
    private String name;

    @Column(name = "EMAIL", nullable = false)
    @Comment("회원 이메일")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @Comment("회원 비밀번호")
    private String password;

    @Column(name = "VERIFIED_STATUS")
    @Comment("이메일 인증 상태")
    private Boolean verified;

    @Embedded
    private Address address;

}
