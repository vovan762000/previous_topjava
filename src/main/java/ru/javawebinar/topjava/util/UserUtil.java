package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserUtil {
    public static final AtomicInteger counter = new AtomicInteger(0);
    public static final int USER_ID = counter.incrementAndGet();
    public static final int ADMIN_ID = counter.incrementAndGet();
    public static final List<User> users = Arrays.asList(
            new User(ADMIN_ID,"admin", "admin@gmaail.com", "admin", Role.USER, Role.ADMIN),
            new User(USER_ID,"user", "user@gmaail.com", "user", Role.USER)
    );
}
