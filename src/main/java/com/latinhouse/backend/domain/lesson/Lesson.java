package com.latinhouse.backend.domain.lesson;

import com.latinhouse.backend.domain.lesson.command.AddLessonCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    private Long no;
    private String title;
    private Genre genre;
    private String instructorLa;
    private String instructorLo;
    private List<Option> options;
    private String bank;
    private String accountNumber;
    private String accountOwner;
    private List<Discount> discounts;
    private BigDecimal maxDiscountAmount;
    private List<Contact> contacts;

    public static Lesson from(AddLessonCommand command) {
        return Lesson.builder()
                .title(command.getTitle())
                .genre(command.getGenre())
                .instructorLa(command.getInstructorLa())
                .instructorLo(command.getInstructorLo())
                .options(command.getOptions())
                .bank(command.getBank())
                .accountNumber(command.getAccountNumber())
                .accountOwner(command.getAccountOwner())
                .discounts(command.getDiscounts())
                .maxDiscountAmount(command.getMaxDiscountAmount())
                .contacts(command.getContacts())
                .build();
    }
}
