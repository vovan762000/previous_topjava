package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController {

    private final MealRestController controller;

    public JspMealController(MealRestController controller) {
        this.controller = controller;
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id) {
        controller.delete(Integer.parseInt(id));
        return "redirect:/meals";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model) {
        final Meal meal = controller.get(Integer.parseInt(id));
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("/filter")
    public String filter(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals", controller.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

    @PostMapping("")
    public String createOrUpdate() {
        return null;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("meals", controller.getAll());
        return "meals";
    }

}
