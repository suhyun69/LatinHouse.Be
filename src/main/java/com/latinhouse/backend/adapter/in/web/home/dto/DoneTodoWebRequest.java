package com.latinhouse.backend.adapter.in.web.home.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoneTodoWebRequest {
    private Long no;
    private String email;
    private Boolean isAdmin;
}
