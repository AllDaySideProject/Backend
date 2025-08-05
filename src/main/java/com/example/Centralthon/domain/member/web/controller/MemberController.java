package com.example.Centralthon.domain.member.web.controller;

import com.example.Centralthon.domain.member.service.MemberService;
import com.example.Centralthon.domain.member.web.dto.LoginReq;
import com.example.Centralthon.domain.member.web.dto.LoginRes;
import com.example.Centralthon.domain.member.web.dto.SignUpReq;
import com.example.Centralthon.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<Void>> signup(@RequestBody @Valid SignUpReq signupReq) {
        memberService.signUp(signupReq);

        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.created(null));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<LoginRes>> login(@RequestBody @Valid LoginReq loginReq){
        LoginRes loginRes = memberService.login(loginReq);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(loginRes));
    }

}
