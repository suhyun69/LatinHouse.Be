package com.latinhouse.backend.application.port.out;

import com.latinhouse.backend.domain.AddMemberDomainRequest;
import com.latinhouse.backend.domain.Member;

public interface CreateMemberPort {
    Member create(AddMemberDomainRequest appReq);
}
