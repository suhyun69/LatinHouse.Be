package com.latinhouse.backend.adapter.out.persistence.repository;

import com.latinhouse.backend.adapter.out.persistence.entity.ProfileJpaEntity;
import com.latinhouse.backend.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileJpaEntity, String> {
    @Query("select p from ProfileJpaEntity p where p.profileId = :profileId order by p.updatedAt desc limit 1")
    Optional<ProfileJpaEntity> findByProfileId(@Param("profileId") String profileId);
}
