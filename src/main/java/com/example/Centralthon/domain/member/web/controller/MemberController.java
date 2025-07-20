package com.example.Centralthon.domain.member.web.controller;

import com.example.Centralthon.domain.member.entity.Member;
import com.example.Centralthon.domain.member.service.MemberService;
import com.example.Centralthon.domain.member.web.dto.LoginReq;
import com.example.Centralthon.domain.member.web.dto.LoginRes;
import com.example.Centralthon.domain.member.web.dto.ProfileRes;
import com.example.Centralthon.domain.member.web.dto.SignUpReq;
import com.example.Centralthon.global.response.SuccessResponse;
import com.example.Centralthon.global.security.CustomMemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<?>> signup(@RequestBody @Valid SignUpReq signupReq) {
        memberService.signUp(signupReq);

        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.created(null));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<?>> login(@RequestBody @Valid LoginReq loginReq){
        LoginRes loginRes = memberService.login(loginReq);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(loginRes));
    }

    // 인가 테스트용 API
    @GetMapping("/profile")
    public ResponseEntity<ProfileRes> getProfile(@AuthenticationPrincipal CustomMemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        return ResponseEntity
                .ok(new ProfileRes(member.getNickName(), member.getRole().getStringRole()));
    }
}
