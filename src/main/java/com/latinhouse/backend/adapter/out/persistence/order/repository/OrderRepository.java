package com.latinhouse.backend.adapter.out.persistence.order.repository;

import com.latinhouse.backend.adapter.out.persistence.order.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, Long> {
    Optional<OrderJpaEntity> findById(String id);
}
