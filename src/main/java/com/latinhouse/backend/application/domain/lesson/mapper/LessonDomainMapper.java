package com.latinhouse.backend.application.domain.lesson.mapper;

import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.service.AddLessonCommand;
import com.latinhouse.backend.application.domain.lesson.service.UpdateLessonCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonDomainMapper {

    /*
        command -> domain
     */

    private final List<CommandToDomainStrategy<?, ?>> commandToDomainStrategies;

    public Lesson toDomain(AddLessonCommand cmd) {
        return dispatchCommandToDomain(cmd, Lesson.class);
    }

    public Lesson toDomain(UpdateLessonCommand cmd) {
        return dispatchCommandToDomain(cmd, Lesson.class);
    }

    @SuppressWarnings("unchecked")
    private <C, D> D dispatchCommandToDomain(C cmd, Class<D> domainType) {
        var s = (CommandToDomainStrategy<C, D>) commandToDomainStrategies.stream()
                .filter(st -> st.supports(cmd.getClass(), domainType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No commandToDomainStrategy for %s -> %s".formatted(cmd.getClass().getSimpleName(), domainType.getSimpleName())));
        return s.toDomain(cmd);
    }
}
