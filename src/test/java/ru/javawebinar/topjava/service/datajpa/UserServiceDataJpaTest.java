package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.meals;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.USER_MATCHER;

@ActiveProfiles(Profiles.DATAJPA)
public class UserServiceDataJpaTest extends UserServiceTest {
    @Test
    public void getWithMeal(){
        User user = service.getWithMeal(USER_ID);
        USER_MATCHER.assertMatch(user, UserTestData.user);
        List<Meal> mealList = user.getMeals();
        Collections.sort(user.getMeals(), Comparator.comparing(Meal::getDateTime).reversed());
        MEAL_MATCHER.assertMatch(mealList,meals);
    }
}
