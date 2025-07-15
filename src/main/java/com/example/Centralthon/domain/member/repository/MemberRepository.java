package com.example.Centralthon.domain.member.repository;

import com.example.Centralthon.domain.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByMemberId( String memberId);

    boolean existsByNickName( String nickName);
}
