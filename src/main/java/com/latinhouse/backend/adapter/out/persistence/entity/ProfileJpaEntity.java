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
@Table(name = "profiles")
@Data
@Builder // mapToJpaEntity
@NoArgsConstructor
@AllArgsConstructor
public class ProfileJpaEntity extends BaseEntity {

    @Id
    private String email;
    private String password;
    private String profileId;
    private String nickname;
    private String sex;
    private Boolean isInstructor;
}
