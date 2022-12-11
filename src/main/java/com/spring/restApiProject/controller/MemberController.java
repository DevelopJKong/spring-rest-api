package com.spring.restApiProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restApiProject.domain.dto.request.LoginDto;
// import com.spring.restApiProject.domain.dto.request.AddMemberDto;
import com.spring.restApiProject.domain.dto.response.ResponseHandler;
import com.spring.restApiProject.domain.entity.Member;
import com.spring.restApiProject.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "/join")
    public ResponseEntity<?> join(@RequestBody Member joinRequest) {
        try {
            log.info("회원가입 로직 실행");
            return ResponseHandler.generateResponse("회원가입 성공", HttpStatus.OK,
                    memberService.join(joinRequest));
        } catch (Exception e) {
            log.error("회원가입 실패" + e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginRequest) {
        try {
            log.info("로그인 로직 실행");
            return ResponseHandler.generateResponse("로그인", HttpStatus.OK,
                    memberService.login(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (Exception e) {
            log.error("로그인 실패" + e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
