package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final MatcherFactory.AssertMath<Meal> MEAL_MATHER = MatcherFactory.usingIgnoringFieldsComparator();

    public static final int MEAL1_ID = START_SEQ + 3;
    public static final int MEAL2_ID = START_SEQ + 4;
    public static final int MEAL3_ID = START_SEQ + 5;
    public static final int MEAL4_ID = START_SEQ + 6;
    public static final int MEAL5_ID = START_SEQ + 7;
    public static final int MEAL6_ID = START_SEQ + 8;
    public static final int MEAL7_ID = START_SEQ + 9;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL2_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL3_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL4_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL5 = new Meal(MEAL5_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL6 = new Meal(MEAL6_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL7 = new Meal(MEAL7_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL1);
        updated.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 30, 9, 0));
        updated.setDescription("Обновленный завтрак");
        updated.setCalories(999);
        return updated;
    }

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, Month.JUNE, 21, 10, 0), "Новый завтрак", 500);
    }

}
