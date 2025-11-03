package com.latinhouse.backend.domain.lesson.command;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.domain.lesson.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddLessonCommand extends SelfValidating<AddLessonCommand> {

    @NotBlank(message = "title cannot be blank.")
    String title;

    @NotNull(message = "genre cannot be null.")
    Genre genre;

    String instructorLa;

    String instructorLo;

    List<Option> options;

    String bank;

    String accountNumber;

    String accountOwner;

    List<Discount> discounts;

    BigDecimal maxDiscountAmount;

    List<Contact> contacts;

    @Builder
    public AddLessonCommand(String title, Genre genre, String instructorLa, String instructorLo,
                            List<Option> options, String bank, String accountNumber, String accountOwner,
                            List<Discount> discounts, BigDecimal maxDiscountAmount, List<Contact> contacts) {
        this.title = title;
        this.genre = genre;
        this.instructorLa = instructorLa;
        this.instructorLo = instructorLo;
        this.options = options;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.discounts = discounts;
        this.maxDiscountAmount = maxDiscountAmount;
        this.contacts = contacts;

        this.validateSelf();
    }
}
