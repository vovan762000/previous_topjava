package ru.javawebinar.topjava;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MatcherFactory<T> {
    public static<T> AssertMath<T> usingIgnoringFieldsComparator(String... ignoringFields) {
        return new AssertMath<T>(ignoringFields);
    }

    public record AssertMath<T>(String... ignoringFields) {

        public void assertMatch(T actual, T expected) {
            assertThat(actual).usingRecursiveComparison().ignoringFields(ignoringFields).isEqualTo(expected);
        }

        public void assertMatch(Iterable<T> actual, T... expected) {
            assertMatch(actual, Arrays.asList(expected));
        }

        public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
            assertThat(actual).usingRecursiveComparison().ignoringFields(ignoringFields).isEqualTo(expected);
        }
    }
}
