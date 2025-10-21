package com.latinhouse.backend.adapter.out.persistence.todo.repository;

import com.latinhouse.backend.adapter.out.persistence.todo.entity.TodoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoJpaEntity, String> {
    Optional<TodoJpaEntity> findByNo(Long no);
}
