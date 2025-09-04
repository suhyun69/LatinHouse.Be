package com.latinhouse.backend.adapter.in.web.dto;

import com.latinhouse.backend.application.port.in.dto.AddProfileAppResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddProfileWebResponse {
    private String profileId;
}
