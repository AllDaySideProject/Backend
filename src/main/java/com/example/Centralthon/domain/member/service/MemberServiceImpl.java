package com.example.Centralthon.domain.member.service;

import com.example.Centralthon.domain.member.entity.Member;
import com.example.Centralthon.domain.member.entity.enums.MemberRole;
import com.example.Centralthon.domain.member.exception.MemberAlreadyExistException;
import com.example.Centralthon.domain.member.exception.MemberErrorCode;
import com.example.Centralthon.domain.member.repository.MemberRepository;
import com.example.Centralthon.domain.member.web.dto.SignUpReq;
import com.example.Centralthon.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void signUp(SignUpReq signUpReq) {
        if(memberRepository.existsByMemberId(signUpReq.getMemberId())){
            throw new MemberAlreadyExistException(MemberErrorCode.MEMBER_ID_ALREADY_EXIST);
        }

        if (memberRepository.existsByNickName(signUpReq.getNickName())) {
            throw new MemberAlreadyExistException(MemberErrorCode.MEMBER_NICKNAME_ALREADY_EXIST);
        }

        Member member = Member.builder()
                .memberId(signUpReq.getMemberId())
                .password(passwordEncoder.encode(signUpReq.getPassword()))
                .nickName(signUpReq.getNickName())
                .role(MemberRole.USER)
                .build();

        memberRepository.save(member);
    }
}
