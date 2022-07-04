package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    Meal findByIdAndUserId(int id,int userId);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int userId);
    @Modifying
    @Query(value = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    @Transactional
    int delete(@Param("id") int id,@Param("userId") int userId);

    List<Meal> findAllByDateTimeGreaterThanEqualAndDateTimeLessThanAndUserIdOrderByDateTimeDesc(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

}
