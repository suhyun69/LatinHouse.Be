package com.latinhouse.backend.adapter.out.persistence.lesson.mapper;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.ContactJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.DiscountJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.OptionJpaEntity;
import com.latinhouse.backend.domain.lesson.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonMapper {
    public LessonJpaEntity mapToJpaEntity(Lesson lesson) {
        return LessonJpaEntity.builder()
                .no(lesson.getNo())
                .title(lesson.getTitle())
                .genre(lesson.getGenre())
                .instructorLo(lesson.getInstructorLo())
                .instructorLa(lesson.getInstructorLa())
                .options(mapToOptionTs(lesson.getOptions()))
                .bank(lesson.getBank())
                .accountNumber(lesson.getAccountNumber())
                .accountOwner(lesson.getAccountOwner())
                .discounts(new ArrayList<>())
                .maxDiscountAmount(lesson.getMaxDiscountAmount())
                .contacts(new ArrayList<>())
                .build();
    }

    public Lesson mapToDomainEntity(LessonJpaEntity lessonT) {
        return Lesson.builder()
                .no(lessonT.getNo())
                .title(lessonT.getTitle())
                .genre(lessonT.getGenre())
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

    private List<Option> mapToOptions(List<OptionJpaEntity> list) {
        if (list == null) return List.of();
        List<Option> out = new ArrayList<>(list.size());
        for (OptionJpaEntity x : list) {
            if (x == null) continue;
            out.add(Option.builder()
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

    private List<Discount> mapToDiscounts(List<DiscountJpaEntity> list) {
        if (list == null) return List.of();
        List<Discount> out = new ArrayList<>(list.size());
        for (DiscountJpaEntity x : list) {
            if (x == null) continue;
            out.add(Discount.builder()
                    .type(DiscountType.valueOf(x.getType()))
                    .condition(x.getCondition())
                    .amount(x.getAmount())
                    .build());
        }
        return out;
    }

    private List<Contact> mapToContacts(List<ContactJpaEntity> list) {
        if (list == null) return List.of();
        List<Contact> out = new ArrayList<>(list.size());
        for (ContactJpaEntity x : list) {
            if (x == null) continue;
            out.add(Contact.builder()
                    .type(ContactType.valueOf(x.getType()))
                    .name(x.getName())
                    .address(x.getAddress())
                    .build());
        }
        return out;
    }

    private static List<OptionJpaEntity> mapToOptionTs(List<Option> list) {
        if (list == null) return List.of();
        List<OptionJpaEntity> out = new ArrayList<>(list.size());
        for (Option x : list) {
            if (x == null) continue;
            OptionJpaEntity e = OptionJpaEntity.builder()
                    .seq(x.seq())
                    .startDateTime(x.startDateTime())
                    .endDateTime(x.endDateTime())
                    .region(x.region())
                    .place(x.place())
                    .placeUrl(x.placeUrl())
                    .price(x.price())
                    .build();
            out.add(e);
        }
        return out;
    }

    private static List<DiscountJpaEntity> toEntityDiscounts(List<Discount> list) {
        if (list == null) return List.of();
        List<DiscountJpaEntity> out = new ArrayList<>(list.size());
        for (Discount x : list) {
            if (x == null) continue;
            DiscountJpaEntity e = DiscountJpaEntity.builder()
                    .seq(x.seq())
                    .type(x.type())
                    .condition(x.condition())
                    .amount(x.amount())
                    .build();
            out.add(e);
        }
        return out;
    }

    private static List<ContactJpaEntity> toEntityContacts(List<Contact> list) {
        if (list == null) return List.of();
        List<ContactJpaEntity> out = new ArrayList<>(list.size());
        for (Contact x : list) {
            if (x == null) continue;
            ContactJpaEntity e = ContactJpaEntity.builder()
                    .seq(x.seq())
                    .type(x.type())
                    .name(x.name())
                    .address(x.address())
                    .build();
            out.add(e);
        }
        return out;
    }
}
