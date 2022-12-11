package com.spring.restApiProject.service;

import java.util.Map;

import com.spring.restApiProject.domain.entity.Member;

public interface MemberService {

    Map<String, Object> join(Member member);
    Map<String, Object> login(String email, String password);

}
