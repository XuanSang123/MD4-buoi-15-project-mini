package org.example.miniproject.dao;

import java.util.List;

public interface IGeneric <T, E>{
    void save(T t);
    void update(T t);
    void delete(E id);
    T findById(E id);
    List<T> findAll();
}
