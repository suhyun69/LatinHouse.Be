package com.latinhouse.backend.adapter.out.persistence.user.repository;

import com.latinhouse.backend.adapter.out.persistence.user.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity, String> {
}
