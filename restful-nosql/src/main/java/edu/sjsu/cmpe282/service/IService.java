package edu.sjsu.cmpe282.service;

import java.util.List;

public interface IService<T, ID> {

    T create(T t);

    T delete(ID id);

    List<T> findAll();

    T findById(ID id);

    T update(T t);
}
