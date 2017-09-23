package edu.sjsu.cmpe282.service;

import java.util.List;

public interface IRestCrudService<T, ID> {

    T create(T newCtx);

    T delete(ID id);

    List<T> findAll();

    T findById(ID id);

    T update(ID id, T newCtx);
}
