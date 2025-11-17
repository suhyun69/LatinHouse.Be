package com.latinhouse.backend.application.checkout;

import com.latinhouse.backend.application.checkout.mapper.CheckoutAppMapper;
import com.latinhouse.backend.common.exception.ForbiddenException;
import com.latinhouse.backend.common.exception.NotFoundException;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.Option;
import com.latinhouse.backend.domain.lesson.service.LessonService;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.domain.order.service.OrderService;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.service.ProfileService;
import com.latinhouse.backend.port.in.checkout.CheckoutUseCase;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppRequest;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppResponse;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
}
