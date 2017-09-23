package edu.sjsu.cmpe282.service;

import java.util.List;

public interface ICRUDService<T, ID> {

    T create(T ctxCreated);

    T delete(ID id);

    List<T> findAll();

    T findById(ID id);

    T update(T ctxUpdated);
}
