package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtil {
    public static final List<User> users = Arrays.asList(
            new User("user1", "user1@gmaail.com", "user1", Role.USER),
            new User("user2", "user2@gmaail.com", "user2", Role.USER),
            new User("admin", "admin@gmaail.com", "admin", Role.USER, Role.ADMIN)
    );
}
