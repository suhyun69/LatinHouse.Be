package com.latinhouse.backend.adapter.out.persistence.user.entity;

import com.latinhouse.backend.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder // mapToJpaEntity
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity extends BaseEntity {

    @Id
    private String email;
    private String password;
    private String role;
}
