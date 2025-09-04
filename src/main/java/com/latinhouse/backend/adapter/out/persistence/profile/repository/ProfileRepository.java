package com.latinhouse.backend.adapter.out.persistence.profile.repository;

import com.latinhouse.backend.adapter.out.persistence.profile.entity.ProfileJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileJpaEntity, String> {
    @Query(value = """
      select * from profiles
       where profile_id = :profileId
       order by updated_at desc
       limit 1
    """, nativeQuery = true)
    Optional<ProfileJpaEntity> findByProfileId(@Param("profileId") String profileId);
}
