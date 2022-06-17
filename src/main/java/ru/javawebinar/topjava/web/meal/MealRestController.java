package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.DateTimeUtil.*;
import static ru.javawebinar.topjava.util.MealsUtil.getTos;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id) {
        log.info("get meal with id = {}", id);
        return service.get(id, SecurityUtil.authUserId());
    }

    public List<MealTo> getAll() {
        log.info("get all meals");
        return getTos(service.getAll(SecurityUtil.authUserId()),SecurityUtil.authUserCaloriesPerDay());
    }

    public void delete(int id) {
        log.info("delete meal with id = {}", id);
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create new meal {}", meal);
        return service.create(meal, SecurityUtil.authUserId());
    }

    public Meal update(Meal meal) {
        log.info("update meal with id = {}", meal.getId());
        return service.update(meal, SecurityUtil.authUserId());
    }

    public List<MealTo> getFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("get meals from date {} to date {} from time {} to time {}", startDate, endDate, startTime, endTime);
        List<Meal> meals = service.getFiltered(getStartOfDay(startDate), getEndOfDay(endDate), SecurityUtil.authUserId());
        return getTos(meals, SecurityUtil.authUserCaloriesPerDay())
                .stream()
                .filter(m->isBetweenHalfOpen(m.getDateTime().toLocalTime(),startTime,endTime))
                .collect(Collectors.toList());
    }
}