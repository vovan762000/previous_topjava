package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepositoryInmemory;
import ru.javawebinar.topjava.repository.Repository;
import ru.javawebinar.topjava.util.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

@WebServlet("/meals")
public class MealServlet extends HttpServlet {
    private Repository<Meal, Integer> repository;
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        repository = new MealRepositoryInmemory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action != null ? action : "") {
            case "delete" -> {
                int mealId = getId(req, "id");
                try {
                    repository.delete(mealId);
                    log.info("delete meal id = {}", mealId);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
                resp.sendRedirect("meals");
            }
            case "edit" -> {
                int mealId = getId(req, "id");
                try {
                    req.setAttribute("meal", repository.getById(mealId));
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/meal.jsp").forward(req, resp);
            }
            case "add" -> {
                req.getRequestDispatcher("/meal.jsp").forward(req, resp);
            }
            default -> {
                req.setAttribute("meals", getAllMeals());
                log.info("get all meals");
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Meal newMeal = new Meal();
        newMeal.setDateTime(LocalDateTime.parse(req.getParameter("dateTime")));
        newMeal.setDescription(req.getParameter("description"));
        newMeal.setCalories(Integer.parseInt(req.getParameter("calories")));
        int newId;
        if (req.getParameter("id").isEmpty()) {
            newId = id.incrementAndGet();
            log.info("add new meal");
        } else {
            newId = getId(req, "id");
            log.info("update meal with id = {}", newId);
        }
        newMeal.setId(newId);
        repository.save(newMeal);
        req.setAttribute("meals", getAllMeals());
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    private int getId(HttpServletRequest req, String id) {
        return Integer.parseInt(req.getParameter(id));
    }

    private List<MealTo> getAllMeals() {
        return filteredByStreams(repository.getAll(), CALORIES_PER_DAY);
    }
}
