package com.latinhouse.backend.domain;

import com.latinhouse.backend.application.port.out.CreateMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final CreateMemberPort createMemberPort;

    public Member addMember(AddMemberDomainRequest req) {
        return createMemberPort.create(req);
    }
}
