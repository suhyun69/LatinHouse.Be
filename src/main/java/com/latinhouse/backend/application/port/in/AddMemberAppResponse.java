package com.latinhouse.backend.application.port.in;

import com.latinhouse.backend.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddMemberAppResponse {
    private String email;

    public AddMemberAppResponse(Member member) {
        this.email = member.getEmail();
    }
}
