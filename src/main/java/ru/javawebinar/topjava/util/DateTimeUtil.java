package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final LocalDateTime MIN_DATE_TIME = LocalDateTime.of(0, 1, 1, 0, 0);
    private static final LocalDateTime MAX_DATE_TIME = LocalDateTime.of(3000, 1, 1, 0, 0);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T lt, T start, T end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) <= 0;
    }

    public static LocalDateTime getStartOfDay(LocalDate start) {
        return start != null ? start.atStartOfDay() : MIN_DATE_TIME;
    }

    public static LocalDateTime getEndOfDay(LocalDate end) {
        return end != null ? end.atStartOfDay().plusDays(1) : MAX_DATE_TIME;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

