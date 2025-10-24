package com.latinhouse.backend.adapter.out.persistence.profile.repository;

import com.latinhouse.backend.adapter.out.persistence.profile.entity.ProfileJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileJpaEntity, String> {
    Optional<ProfileJpaEntity> findById(String id);
    List<ProfileJpaEntity> findByEmail(String email);
}
