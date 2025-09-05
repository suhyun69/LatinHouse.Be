package com.latinhouse.backend.domain.lesson;

import com.latinhouse.backend.application.port.out.lesson.CreateLessonPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final CreateLessonPort createLessonPort;

    public Lesson addLesson(AddLessonCommand cmd) {

        List<Option> options = Optional.ofNullable(cmd.getOptions()).orElse(List.of()).stream()
                .map(LessonService::convertTo)
                .toList();
        List<Discount> discounts = Optional.ofNullable(cmd.getDiscounts()).orElse(List.of()).stream()
                .map(LessonService::convertTo)
                .toList();
        List<Contact> contacts = Optional.ofNullable(cmd.getContacts()).orElse(List.of()).stream()
                .map(LessonService::convertTo)
                .toList();

        Lesson lesson = Lesson.builder()
                .title(cmd.getTitle())
                .genre(cmd.getGenre())
                .instructorLo(cmd.getInstructorLo())
                .instructorLa(cmd.getInstructorLa())
                .options(options)
                .bank(cmd.getBank())
                .accountNumber(cmd.getAccountNumber())
                .accountOwner(cmd.getAccountOwner())
                .discounts(discounts)
                .maxDiscountAmount(cmd.getMaxDiscountAmount())
                .contacts(contacts)
                .build();
        return createLessonPort.create(lesson);
    }

    private static Option convertTo(AddLessonCommand.Option o) {
        return Option.builder()
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(o.getRegion())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static Discount convertTo(AddLessonCommand.Discount d) {
        return Discount.builder()
                .type(d.getType())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static Contact convertTo(AddLessonCommand.Contact c) {
        return Contact.builder()
                .type(c.getType())
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }
}
