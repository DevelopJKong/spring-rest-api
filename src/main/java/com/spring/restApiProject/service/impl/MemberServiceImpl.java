package com.spring.restApiProject.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.spring.restApiProject.authorization.JwtTokenConfig;
import com.spring.restApiProject.domain.entity.Member;
import com.spring.restApiProject.repository.MemberRepository;
import com.spring.restApiProject.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Map<String, Object> join(Member member) {
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("ok", true);
        return result;
    }

    @Override
    public Map<String, Object> login(String email, String password) {

        Member memberData = memberRepository.findByEmail(email);
        String encodedPassword = memberData.getPassword();
        Boolean isPasswordCheck = passwordEncoder.matches(password, encodedPassword);

        if (isPasswordCheck.equals(false)) {
            throw new IllegalArgumentException("잘못 된 패스워드 입니다");
        }

        String token = JwtTokenConfig.issuedToken(email);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("ok", true);
        result.put("token", token);
        return result;
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
