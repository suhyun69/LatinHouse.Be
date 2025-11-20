package com.latinhouse.backend.application.checkout;

import com.latinhouse.backend.application.checkout.mapper.CheckoutAppMapper;
import com.latinhouse.backend.common.exception.ForbiddenException;
import com.latinhouse.backend.common.exception.NotFoundException;
import com.latinhouse.backend.domain.lesson.Discount;
import com.latinhouse.backend.domain.lesson.DiscountType;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.Option;
import com.latinhouse.backend.domain.lesson.service.LessonService;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.domain.order.service.OrderService;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.Sex;
import com.latinhouse.backend.domain.profile.service.ProfileService;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.port.in.checkout.CheckoutUseCase;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppResponse;
import com.latinhouse.backend.port.in.checkout.dto.PaymentAppRequest;
import com.latinhouse.backend.port.in.checkout.dto.PaymentAppResponse;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckoutUseCaseImpl implements CheckoutUseCase {

    private final CheckoutAppMapper checkoutAppMapper;
    private final OrderService orderService;
    private final LessonService lessonService;
    private final ProfileService profileService;

    @Override
    public GetCheckoutAppResponse getCheckout(String id) {

        Order order = orderService.getOrder(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        Lesson lesson = lessonService.getLessonByOption(order.getLessonOptionNo())
                .orElseThrow(() -> new NotFoundException("Lesson not found"));

        Option lessonOption = lesson.getOptions().stream()
                .filter(o -> o.getNo().equals(order.getLessonOptionNo()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("LessonOption not found"));

        Profile instructorLo = Optional.ofNullable(lesson.getInstructorLo())
                .filter(StringUtils::isNotBlank)
                .map(profileId -> {
                    Profile profile = profileService.getProfile(profileId)
                            .orElseThrow(() -> new NotFoundException("instructorLo not found"));
                    if (!profile.getIsInstructor()) throw new ForbiddenException("instructorLo is not instructor");
                    return profile;
                })
                .orElse(null);

        Profile instructorLa = Optional.ofNullable(lesson.getInstructorLa())
                .filter(StringUtils::isNotBlank)
                .map(profileId -> {
                    Profile profile = profileService.getProfile(profileId)
                            .orElseThrow(() -> new NotFoundException("instructorLa not found"));
                    if (!profile.getIsInstructor()) throw new ForbiddenException("instructorLa is not instructor");
                    return profile;
                })
                .orElse(null);

        GetCheckoutAppResponse appRes = checkoutAppMapper.toAppRes(order, GetCheckoutAppResponse.class);
        appRes.setLessonTitle(lesson.getTitle());
        appRes.setLessonNo(lesson.getNo());
        appRes.setGenre(lesson.getGenre());
        appRes.setInstructorLoNickname(Optional.ofNullable(instructorLo).map(Profile::getNickname).orElse(null));
        appRes.setInstructorLoId(Optional.ofNullable(instructorLo).map(Profile::getId).orElse(null));
        appRes.setInstructorLaNickname(Optional.ofNullable(instructorLa).map(Profile::getNickname).orElse(null));
        appRes.setInstructorLaId(Optional.ofNullable(instructorLa).map(Profile::getId).orElse(null));
        appRes.setStartDateTime(lessonOption.getStartDateTime());
        appRes.setEndDateTime(lessonOption.getEndDateTime());
        appRes.setRegion(lessonOption.getRegion());
        appRes.setLocation(lessonOption.getLocation());
        appRes.setPrice(lessonOption.getPrice());
        appRes.setDiscounts(
            lesson.getDiscounts().stream()
                .map(d -> GetCheckoutAppResponse.Discount.builder()
                    .no(d.getNo())
                    .type(d.getType())
                    .condition(d.getCondition())
                    .amount(d.getAmount())
                    .build()
                )
                .toList() // Java 16 이상
        );

        return appRes;
    }

    @Override
    public PaymentAppResponse payment(PaymentAppRequest appReq, User user) {

        Order order = orderService.getOrder(appReq.getCheckoutId())
                .orElseThrow(() -> new NotFoundException("Order not found"));

        Lesson lesson = lessonService.getLessonByOption(order.getLessonOptionNo())
                .orElseThrow(() -> new NotFoundException("Lesson not found"));

        Option lessonOption = lesson.getOptions().stream()
                .filter(o -> o.getNo().equals(order.getLessonOptionNo()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("LessonOption not found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        List<Discount> lessonDiscounts = lesson.getDiscounts().stream()
                .filter(d -> appReq.getDiscounts().contains(d.getNo()))
                .filter(d -> {
                    if (d.getCondition() == null) return false;
                    if(d.getType().equals(DiscountType.Earlybird)) {
                        LocalDate conditionDate;
                        try {
                            conditionDate = LocalDate.parse(d.getCondition(), formatter);
                        } catch (Exception e) {
                            return false;
                        }
                        return conditionDate.isBefore(today);
                    }
                    else if(d.getType().equals(DiscountType.Sex)) {
                        return Sex.valueOf(d.getCondition()).equals(user.getSex());
                    }
                    return false;
                })
                .toList();

        return PaymentAppResponse.builder().build();
    }
}
