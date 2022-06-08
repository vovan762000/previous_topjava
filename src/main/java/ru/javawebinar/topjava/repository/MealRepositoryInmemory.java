package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.NotFoundException;

import java.util.List;
import java.util.Optional;

import static ru.javawebinar.topjava.util.MealsUtil.id;
import static ru.javawebinar.topjava.util.MealsUtil.mapOfMeals;


public class MealRepositoryInmemory implements Repository<Meal, Integer> {

    @Override
    public Meal getById(Integer id) throws NotFoundException {
        return Optional.of(mapOfMeals.get(id)).orElseThrow(() -> new NotFoundException("Meal not found", id));
    }

    @Override
    public List<Meal> getAll() {
        return MealsUtil.getMeals();
    }

    @Override
    public boolean save(Meal meal) {
        return mapOfMeals.put(id.incrementAndGet(), new Meal(meal)) != null;
    }

    @Override
    public boolean update(Meal meal) {
        Meal updatedMeal = new Meal(meal);
        return mapOfMeals.put(meal.getId(), updatedMeal) != null;
    }

    @Override
    public boolean delete(Integer id) throws NotFoundException {
        return mapOfMeals.remove(getById(id)) != null;
    }
}
