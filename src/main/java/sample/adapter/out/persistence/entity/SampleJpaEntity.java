package sample.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sample")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleJpaEntity {

    @Id
    private String id;
}
