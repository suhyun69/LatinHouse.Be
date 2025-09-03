package com.latinhouse.backend.application.service;

import com.latinhouse.backend.application.port.in.AddMemberAppRequest;
import com.latinhouse.backend.application.port.in.AddMemberAppResponse;
import com.latinhouse.backend.application.port.in.SignupUseCase;
import com.latinhouse.backend.domain.AddMemberDomainRequest;
import com.latinhouse.backend.domain.Member;
import com.latinhouse.backend.domain.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Signup implements SignupUseCase {

    private final MemberService memberService;

    @Override
    @Transactional
    public AddMemberAppResponse addByEmail(AddMemberAppRequest appReq) {
        AddMemberDomainRequest req = AddMemberDomainRequest.from(appReq);
        Member member = memberService.addMember(req);

        return new AddMemberAppResponse(member);
    }
}
