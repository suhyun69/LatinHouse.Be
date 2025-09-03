package com.latinhouse.backend.adapter.out.persistence.entity;

import com.latinhouse.backend.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
