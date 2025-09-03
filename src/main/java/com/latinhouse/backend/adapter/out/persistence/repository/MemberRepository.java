package com.latinhouse.backend.adapter.out.persistence.repository;

import com.latinhouse.backend.adapter.out.persistence.entity.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, String> {
}
