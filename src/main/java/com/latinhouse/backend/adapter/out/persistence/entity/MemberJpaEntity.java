package com.latinhouse.backend.adapter.out.persistence.entity;

import com.latinhouse.backend.common.BaseEntity;
import com.latinhouse.backend.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Data
@Builder // mapToJpaEntity
@NoArgsConstructor
@AllArgsConstructor
public class MemberJpaEntity extends BaseEntity {

    @Id
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
