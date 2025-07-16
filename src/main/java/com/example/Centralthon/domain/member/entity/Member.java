package com.example.Centralthon.domain.member.entity;

import com.example.Centralthon.domain.member.entity.enums.MemberRole;
import com.example.Centralthon.domain.member.web.dto.SignUpReq;
import com.example.Centralthon.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBERS")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public static Member toEntity(SignUpReq signUpReq, PasswordEncoder passwordEncoder) {

        return Member.builder()
                .email(signUpReq.getEmail())
                .password(passwordEncoder.encode(signUpReq.getPassword()))
                .nickName(signUpReq.getNickName())
                .role(MemberRole.USER)
                .build();
    }
}
