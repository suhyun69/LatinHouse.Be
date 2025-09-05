package com.latinhouse.backend.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    // 생성자 private 처리: 유틸 클래스는 인스턴스화 금지
    private DateTimeUtil() {}

    /**
     * yyyy-MM-dd 형식의 date와 HH:mm 형식의 time을 합쳐 LocalDateTime 생성
     *
     * @param date yyyy-MM-dd 형식
     * @param time HH:mm 형식
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String date, String time) {
        try {
            LocalDate localDate = LocalDate.parse(date, DATE_FORMATTER);
            LocalTime localTime = LocalTime.parse(time, TIME_FORMATTER);
            return LocalDateTime.of(localDate, localTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    String.format("Invalid date/time format. date='%s', time='%s'", date, time), e
            );
        }
    }
}