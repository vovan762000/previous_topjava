package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealsUtil {
    public static final int CALORIES_PER_DAY = 2000;
    public static AtomicInteger id = new AtomicInteger(0);
    public static ConcurrentHashMap<Integer, Meal> mapOfMeals = new ConcurrentHashMap<>();
    static {
        final int MEAL1_ID = id.incrementAndGet();
        final int MEAL2_ID = id.incrementAndGet();
        final int MEAL3_ID = id.incrementAndGet();
        final int MEAL4_ID = id.incrementAndGet();
        final int MEAL5_ID = id.incrementAndGet();
        final int MEAL6_ID = id.incrementAndGet();
        final int MEAL7_ID = id.incrementAndGet();

        mapOfMeals.put(MEAL1_ID,new Meal(MEAL1_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        mapOfMeals.put(MEAL2_ID,new Meal(MEAL2_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        mapOfMeals.put(MEAL3_ID,new Meal(MEAL3_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        mapOfMeals.put(MEAL4_ID,new Meal(MEAL4_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        mapOfMeals.put(MEAL5_ID,new Meal(MEAL5_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        mapOfMeals.put(MEAL6_ID,new Meal(MEAL6_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        mapOfMeals.put(MEAL7_ID,new Meal(MEAL7_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }


    public static void main(String[] args) {
        List<MealTo> mealsTo = filteredByStreams(getMeals(), LocalTime.of(7, 0), LocalTime.of(12, 0), CALORIES_PER_DAY);
        mealsTo.forEach(System.out::println);
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, int caloriesPerDay) {
        return filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<Meal> getMeals(){
        return new ArrayList<>(mapOfMeals.values());
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal, excess);
    }
}
