package sample.adapter.out.persistence.mapper;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonJpaEntity;
import com.latinhouse.backend.common.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.common.mapper.EntityToDomainStrategy;
import com.latinhouse.backend.domain.lesson.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sample.adapter.out.persistence.entity.SampleJpaEntity;
import sample.domain.Sample;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SamplePersistenceMapper {

    private final List<DomainToEntityStrategy<?, ?>> domainToEntityStrategies;
    private final List<EntityToDomainStrategy<?, ?>> entityToDomainStrategies;

    public SampleJpaEntity toEntity(Sample sample) { return dispatchDomainToEntity(sample, SampleJpaEntity.class); }

    public Sample toDomain(SampleJpaEntity entity) {
        return dispatchEntityToDomain(entity, Sample.class);
    }

    private <D, E> E dispatchDomainToEntity(D domain, Class<E> entityType) {
        var s = (DomainToEntityStrategy<D, E>) domainToEntityStrategies.stream()
                .filter(st -> st.domainToEntitySupports(domain.getClass(), entityType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No DomainToEntityStrategy for %s -> %s".formatted(domain.getClass().getSimpleName(), entityType.getSimpleName())));
        return s.toEntity(domain);
    }

    @SuppressWarnings("unchecked")
    private <E, D> D dispatchEntityToDomain(E entity, Class<D> domainType) {
        var s = (EntityToDomainStrategy<E, D>) entityToDomainStrategies.stream()
                .filter(st -> st.entityToDomainSupports(entity.getClass(), domainType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No EntityToDomainStrategy for %s -> %s".formatted(entity.getClass().getSimpleName(), domainType.getSimpleName())));
        return s.toDomain(entity);
    }
}
