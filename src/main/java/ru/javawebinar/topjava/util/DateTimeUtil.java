package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T lt, T start, T end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) <= 0;
    }

    public static boolean isBetween(LocalDateTime ldt, LocalDateTime start, LocalDateTime end) {
        return isBetweenHalfOpen(ldt.toLocalDate(), start.toLocalDate() != null ? start.toLocalDate() : LocalDate.MIN, end.toLocalDate() != null ? end.toLocalDate() : LocalDate.MAX) &&
                isBetweenHalfOpen(ldt.toLocalTime(), start.toLocalTime() != null ? start.toLocalTime() : LocalTime.MIN, end.toLocalTime() != null ? end.toLocalTime() : LocalTime.MAX);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

