package com.example.Centralthon.global.jwt;

import com.example.Centralthon.domain.member.entity.Member;
import com.example.Centralthon.domain.member.exception.InvalidCredentialsException;
import com.example.Centralthon.domain.member.exception.MemberNotFoundException;
import com.example.Centralthon.domain.member.repository.MemberRepository;
import com.example.Centralthon.global.security.CustomMemberDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final MemberRepository memberRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key key;

    @PostConstruct
    public void init() { this.key = Keys.hmacShaKeyFor(secretKey.getBytes()); }

    public String createToken(Member member) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(member.getEmail())
                .claim("nickname", member.getNickName())
                .claim("role", member.getRole())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(key)
                .compact();
    }

    // 토큰 유효성 검사 + 만료 검사
    public boolean validateToken(String token) {
        try{
            getClaims(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    // Claim 파싱
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        String email = claims.getSubject();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        CustomMemberDetails memberDetails = new CustomMemberDetails(member);

        return new UsernamePasswordAuthenticationToken(
                memberDetails,
                null,
                memberDetails.getAuthorities()
        );
    }
}
