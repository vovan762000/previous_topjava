package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/profile/meals",produces = MediaType.APPLICATION_JSON_VALUE)
public class MealUIController extends AbstractMealController {

    @GetMapping
    @ResponseStatus
    public List<MealTo> getAll(){
        return super.getAll();
    }
    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
    @Override
    @PutMapping("/{id}")
    public void update(Meal meal,@PathVariable int id) {
        super.update(meal,id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                       @RequestParam String description,
                       @RequestParam int calories) {
        super.create(new Meal(dateTime,description,calories));
    }

    @GetMapping("/filter")
    public List<MealTo> getBetween(@RequestParam LocalDate startDate,
                                   @RequestParam LocalTime startTime,
                                   @RequestParam LocalDate endDate,
                                   @RequestParam LocalTime endTime) {
        return super.getBetween(startDate,startTime,endDate,endTime);
    }
}
