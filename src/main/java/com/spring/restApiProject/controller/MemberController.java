package com.spring.restApiProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.spring.restApiProject.domain.dto.request.AddMemberDto;
import com.spring.restApiProject.domain.dto.response.ResponseHandler;
import com.spring.restApiProject.domain.entity.Member;
import com.spring.restApiProject.service.MemberService;

@RestController
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "/join")
    public ResponseEntity<?> addMember(@RequestBody Member addMemberRequest) {
        try {
            return ResponseHandler.generateResponse("회원가입 성공", HttpStatus.OK,
                    memberService.addMember(addMemberRequest));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
