package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo extends HasId {
    private LocalDateTime dateTime;

    private String description;

    private int calories;

    private boolean excess;

    public MealTo() {
    }

    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public MealTo(Meal meal, boolean excess) {
        this(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + getId() +
                ",ateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
