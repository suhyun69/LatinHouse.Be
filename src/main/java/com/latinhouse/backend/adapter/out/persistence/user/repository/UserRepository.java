package com.latinhouse.backend.adapter.out.persistence.user.repository;

import com.latinhouse.backend.adapter.out.persistence.user.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpaEntity, String> {
    Optional<UserJpaEntity> findById(String email);
    Optional<UserJpaEntity> findByProfileId(String profileId);
}
