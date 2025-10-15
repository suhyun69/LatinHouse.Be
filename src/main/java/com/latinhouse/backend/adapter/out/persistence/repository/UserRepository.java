package com.latinhouse.backend.adapter.out.persistence.repository;

import com.latinhouse.backend.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity, String> {
}
