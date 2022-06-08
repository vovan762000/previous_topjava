package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.util.NotFoundException;

import java.util.List;

public interface Repository<T, ID> {
    T getById(ID id) throws NotFoundException;

    List<T> getAll();

    boolean save(T object);

    boolean update(T object);

    boolean delete(ID id) throws NotFoundException;
}
