package sample.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.adapter.out.persistence.entity.SampleJpaEntity;

public interface SampleRepository extends JpaRepository<SampleJpaEntity, String> {
}
