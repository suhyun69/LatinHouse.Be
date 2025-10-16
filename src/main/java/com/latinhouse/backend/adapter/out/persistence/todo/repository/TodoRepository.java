package com.latinhouse.backend.adapter.out.persistence.todo.repository;

import com.latinhouse.backend.adapter.out.persistence.todo.entity.TodoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoJpaEntity, String> {
}
