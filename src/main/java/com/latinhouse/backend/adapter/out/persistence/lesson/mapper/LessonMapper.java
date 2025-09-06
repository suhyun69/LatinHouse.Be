package com.latinhouse.backend.adapter.out.persistence.lesson.mapper;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.ContactT;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.DiscountT;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonT;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.OptionT;
import com.latinhouse.backend.domain.lesson.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonMapper {
    public LessonT mapToEntity(Lesson lesson) {
        return LessonT.builder()
                .no(lesson.getNo())
                .title(lesson.getTitle())
                .genre(lesson.getGenre().getCode())
                .instructorLo(lesson.getInstructorLo())
                .instructorLa(lesson.getInstructorLa())
                .options(mapToOptionTs(lesson.getOptions()))
                .bank(lesson.getBank())
                .accountNumber(lesson.getAccountNumber())
                .accountOwner(lesson.getAccountOwner())
                .discounts(mapToDiscountTs(lesson.getDiscounts()))
                .maxDiscountAmount(lesson.getMaxDiscountAmount())
                .contacts(mapToContactTs(lesson.getContacts()))
                .build();
    }

    public Lesson mapToDomain(LessonT lessonT) {
        return Lesson.builder()
                .no(lessonT.getNo())
                .title(lessonT.getTitle())
                .genre(Genre.of(lessonT.getGenre()))
                .instructorLo(lessonT.getInstructorLo())
                .instructorLa(lessonT.getInstructorLa())
                .options(mapToOptions(lessonT.getOptions()) )
                .bank(lessonT.getBank())
                .accountNumber(lessonT.getAccountNumber())
                .accountOwner(lessonT.getAccountOwner())
                .discounts(mapToDiscounts(lessonT.getDiscounts()))
                .maxDiscountAmount(lessonT.getMaxDiscountAmount())
                .contacts(mapToContacts(lessonT.getContacts()))
                .build();
    }

    private List<Option> mapToOptions(List<OptionT> list) {
        if (list == null) return List.of();
        List<Option> out = new ArrayList<>(list.size());
        for (OptionT x : list) {
            if (x == null) continue;
            out.add(Option.builder()
                    .seq(x.getSeq())
                    .startDateTime(x.getStartDateTime())
                    .endDateTime(x.getEndDateTime())
                    .region(Region.of(x.getRegion()))
                    .location(x.getLocation())
                    .locationUrl(x.getLocationUrl())
                    .price(x.getPrice())
                    .build());
        }
        return out;
    }

    private List<Discount> mapToDiscounts(List<DiscountT> list) {
        if (list == null) return List.of();
        List<Discount> out = new ArrayList<>(list.size());
        for (DiscountT x : list) {
            if (x == null) continue;
            out.add(Discount.builder()
                    .seq(x.getSeq())
                    .type(DiscountType.of(x.getType()))
                    .condition(x.getCondition())
                    .amount(x.getAmount())
                    .build());
        }
        return out;
    }

    private List<Contact> mapToContacts(List<ContactT> list) {
        if (list == null) return List.of();
        List<Contact> out = new ArrayList<>(list.size());
        for (ContactT x : list) {
            if (x == null) continue;
            out.add(Contact.builder()
                    .seq(x.getSeq())
                    .type(ContactType.of(x.getType()))
                    .name(x.getName())
                    .address(x.getAddress())
                    .build());
        }
        return out;
    }

    private static List<OptionT> mapToOptionTs(List<Option> list) {
        if (list == null) return List.of();
        List<OptionT> out = new ArrayList<>(list.size());
        for (Option x : list) {
            if (x == null) continue;
            OptionT e = OptionT.builder()
                    .seq(x.getSeq())
                    .startDateTime(x.getStartDateTime())
                    .endDateTime(x.getEndDateTime())
                    .region(x.getRegion() != null ? x.getRegion().getCode() : null)
                    .location(x.getLocation())
                    .locationUrl(x.getLocationUrl())
                    .price(x.getPrice())
                    .build();
            out.add(e);
        }
        return out;
    }

    private static List<DiscountT> mapToDiscountTs(List<Discount> list) {
        if (list == null) return List.of();
        List<DiscountT> out = new ArrayList<>(list.size());
        for (Discount x : list) {
            if (x == null) continue;
            DiscountT e = DiscountT.builder()
                    .seq(x.getSeq())
                    .type(x.getType() != null ? x.getType().getCode() : null)
                    .condition(x.getCondition())
                    .amount(x.getAmount())
                    .build();
            out.add(e);
        }
        return out;
    }

    private static List<ContactT> mapToContactTs(List<Contact> list) {
        if (list == null) return List.of();
        List<ContactT> out = new ArrayList<>(list.size());
        for (Contact x : list) {
            if (x == null) continue;
            ContactT e = ContactT.builder()
                    .seq(x.getSeq())
                    .type(x.getType() != null ? x.getType().getCode() : null)
                    .name(x.getName())
                    .address(x.getAddress())
                    .build();
            out.add(e);
        }
        return out;
    }
}
