package com.latinhouse.backend.adapter.out.persistence;

import com.latinhouse.backend.adapter.out.persistence.entity.MemberJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.mapper.MemberMapper;
import com.latinhouse.backend.adapter.out.persistence.repository.MemberRepository;
import com.latinhouse.backend.application.port.out.CreateMemberPort;
import com.latinhouse.backend.domain.AddMemberDomainRequest;
import com.latinhouse.backend.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements CreateMemberPort {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    @Override
    public Member create(AddMemberDomainRequest req) {

        MemberJpaEntity memberT = MemberJpaEntity.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .role(req.getRole())
                .build();
        return memberMapper.mapToDomainEntity(memberRepository.save(memberT));
    }
}
