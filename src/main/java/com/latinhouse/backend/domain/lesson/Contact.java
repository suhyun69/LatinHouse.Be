package com.latinhouse.backend.domain.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private Long no;
    private ContactType type;
    private String name;
    private String address;
}