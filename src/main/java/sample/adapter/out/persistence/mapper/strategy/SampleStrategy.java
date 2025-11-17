package sample.adapter.out.persistence.mapper.strategy;

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
public class SampleStrategy implements
        DomainToEntityStrategy<Sample, SampleJpaEntity>,
        EntityToDomainStrategy<SampleJpaEntity, Sample> {

    @Override
    public boolean domainToEntitySupports(Class<?> c, Class<?> d) {
        return Sample.class.isAssignableFrom(c)
                && SampleJpaEntity.class.isAssignableFrom(d);
    }

    @Override
    public SampleJpaEntity toEntity(Sample sample) {
        return SampleJpaEntity.builder()
                .id(sample.getId())
                .build();
    }

    @Override
    public boolean entityToDomainSupports(Class<?> c, Class<?> d) {
        return SampleJpaEntity.class.isAssignableFrom(c)
                && Sample.class.isAssignableFrom(d);
    }

    @Override
    public Sample toDomain(SampleJpaEntity entity) {
        return Sample.builder()
                .id(entity.getId())
                .build();
    }
}
