package ru.javawebinar.topjava.util;

public class NotFoundException extends Exception{
    private int id;

    public NotFoundException(String message, int id) {
        super(message);
        this.id = id;
    }
}
