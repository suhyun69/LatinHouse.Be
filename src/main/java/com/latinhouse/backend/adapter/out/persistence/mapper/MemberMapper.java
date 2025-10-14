package com.latinhouse.backend.adapter.out.persistence.mapper;

import com.latinhouse.backend.adapter.out.persistence.entity.MemberJpaEntity;
import com.latinhouse.backend.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {
    public MemberJpaEntity mapToJpaEntity(Member member) {
        return MemberJpaEntity.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
    }

    public Member mapToDomainEntity(MemberJpaEntity userT) {
        return Member.builder()
                .email(userT.getEmail())
                .password(userT.getPassword())
                .role(userT.getRole())
                .build();
    }
}
