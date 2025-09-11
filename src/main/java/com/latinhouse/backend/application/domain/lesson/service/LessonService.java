package com.latinhouse.backend.application.domain.lesson.service;

import com.latinhouse.backend.application.domain.lesson.Contact;
import com.latinhouse.backend.application.domain.lesson.Discount;
import com.latinhouse.backend.application.domain.lesson.Option;
import com.latinhouse.backend.application.port.out.lesson.CreateLessonPort;
import com.latinhouse.backend.application.port.out.lesson.ReadLessonPort;
import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.mapper.LessonDomainMapper;
import com.latinhouse.backend.application.port.out.lesson.UpdateLessonPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonDomainMapper lessonDomainMapper;
    private final CreateLessonPort createLessonPort;
    private final ReadLessonPort readLessonPort;
    private final UpdateLessonPort updateLessonPort;

    public Lesson addLesson(AddLessonCommand cmd) {
        return createLessonPort.create(lessonDomainMapper.toDomain(cmd));
    }

    public List<Lesson> search() { return readLessonPort.findAll(); }
    public Optional<Lesson> getLesson(Long lessonNo) { return readLessonPort.findById(lessonNo); }

    public Lesson updateLesson(Lesson lesson, UpdateLessonCommand cmd) {

        lesson.setTitle(cmd.getTitle());
        lesson.setGenre(cmd.getGenre());
        lesson.setInstructorLo(cmd.getInstructorLo());
        lesson.setInstructorLa(cmd.getInstructorLa());

        lesson.getOptions().forEach(o -> o.setIsActive(false));
        for (UpdateLessonCommand.Option reqOpt : cmd.getOptions()) {
            Option matched = lesson.getOptions().stream()
                    .filter(opt -> Objects.equals(opt.getSeq(), reqOpt.getSeq()))
                    .findFirst()
                    .orElse(null);
            if (matched != null) {
                matched.setStartDateTime(reqOpt.getStartDateTime());
                matched.setEndDateTime(reqOpt.getEndDateTime());
                matched.setRegion(reqOpt.getRegion());
                matched.setLocation(reqOpt.getLocation());
                matched.setLocationUrl(reqOpt.getLocationUrl());
                matched.setPrice(reqOpt.getPrice());
                matched.setIsActive(true);
            }
            else {
                Option newOpt = Option.builder()
                        .startDateTime(reqOpt.getStartDateTime())
                        .endDateTime(reqOpt.getEndDateTime())
                        .region(reqOpt.getRegion())
                        .location(reqOpt.getLocation())
                        .locationUrl(reqOpt.getLocationUrl())
                        .price(reqOpt.getPrice())
                        .isActive(true)
                        .build();
                lesson.addOption(newOpt);
            }
        }

        lesson.setBank(cmd.getBank());
        lesson.setAccountNumber(cmd.getAccountNumber());
        lesson.setAccountOwner(cmd.getAccountOwner());

        lesson.getDiscounts().forEach(d -> d.setIsActive(false));
        for (UpdateLessonCommand.Discount reqDis : cmd.getDiscounts()) {
            Discount matched = lesson.getDiscounts().stream()
                    .filter(opt -> Objects.equals(opt.getSeq(), reqDis.getSeq()))
                    .findFirst()
                    .orElse(null);
            if (matched != null) {
                matched.setType(reqDis.getType());
                matched.setCondition(reqDis.getCondition());
                matched.setAmount(reqDis.getAmount());
                matched.setIsActive(true);
            }
            else {
                Discount newDis = Discount.builder()
                        .type(reqDis.getType())
                        .condition(reqDis.getCondition())
                        .amount(reqDis.getAmount())
                        .isActive(true)
                        .build();
                lesson.addDiscount(newDis);
            }
        }

        lesson.setMaxDiscountAmount(cmd.getMaxDiscountAmount());

        lesson.getContacts().forEach(c -> c.setIsActive(false));
        for (UpdateLessonCommand.Contact reqCon : cmd.getContacts()) {
            Contact matched = lesson.getContacts().stream()
                    .filter(con -> Objects.equals(con.getSeq(), reqCon.getSeq()))
                    .findFirst()
                    .orElse(null);
            if (matched != null) {
                matched.setType(reqCon.getType());
                matched.setName(reqCon.getName());
                matched.setAddress(reqCon.getAddress());
                matched.setIsActive(true);
            }
            else {
                Contact newCon = Contact.builder()
                        .type(reqCon.getType())
                        .name(reqCon.getName())
                        .address(reqCon.getAddress())
                        .isActive(true)
                        .build();
                lesson.addContact(newCon);
            }
        }

        lesson.setIsActive(cmd.getIsActive());

        return updateLessonPort.update(lesson);
    }
}
