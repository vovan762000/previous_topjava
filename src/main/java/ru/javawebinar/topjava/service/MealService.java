package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<MealTo> getAll(int userId) {
        return MealsUtil.getTos(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public void delete(int id,int userId){
        checkNotFoundWithId(repository.delete(id,userId),id);
    }

    public Meal update(Meal meal,int userId){
       return checkNotFoundWithId(repository.save(meal,userId), meal.getId());
    }

    public Meal create(Meal meal,int userId){
        return repository.save(meal, userId);
    }

    public List<MealTo> getFiltered(LocalDateTime start, LocalDateTime end, int userId){
        return (List<MealTo>) repository.getFiltered(start,end,userId);
    }

}