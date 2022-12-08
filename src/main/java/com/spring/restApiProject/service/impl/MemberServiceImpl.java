package com.spring.restApiProject.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.restApiProject.domain.entity.Member;
import com.spring.restApiProject.repository.MemberRepository;
import com.spring.restApiProject.service.MemberService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public String addMember(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return "성공하였습니다. 회원 아이디 :" + member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
