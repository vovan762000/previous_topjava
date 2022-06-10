package ru.javawebinar.topjava.util;

public class NotFoundException extends RuntimeException{
    private int id;

    public NotFoundException(String message, int id) {
        super(message);
        this.id = id;
    }
}
