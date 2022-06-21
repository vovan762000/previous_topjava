package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.NOT_FOUND;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL1_ID, USER_ID);
        assertMatch(meal, MEAL1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_ID, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(MEAL1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_ID, USER_ID));
    }

    @Test
    public void deleteNotFound(){
        assertThrows(NotFoundException.class,()->service.delete(MEAL1_ID, ADMIN_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL2_ID, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = service.getBetweenInclusive(LocalDate.of(2020, Month.JANUARY, 31),LocalDate.of(2020, Month.JANUARY, 31),USER_ID);
        assertMatch(meals,MEAL7,MEAL6,MEAL5,MEAL4);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        assertMatch(meals, MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated,USER_ID);
        assertMatch(service.get(MEAL1_ID,USER_ID),updated);
    }

    @Test
    public void updatedNotOwn(){
        Meal updated = getUpdated();
        assertThrows(NotFoundException.class,()->service.update(updated,ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(),USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created,newMeal);
        assertMatch(service.get(newId,USER_ID),newMeal);
    }

    @Test
    public void duplicateDateTimeCreate(){
        Meal created = getNew();
        created.setDateTime(MEAL1.getDateTime());
        assertThrows(DataAccessException.class,()->service.create(created,USER_ID));
    }

    @Test
    public void getBetweenNullDate(){
        List<Meal> meals = service.getBetweenInclusive(null,null,USER_ID);
        assertMatch(meals,MEAL7,MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1);
    }
}