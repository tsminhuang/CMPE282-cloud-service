package edu.sjsu.cmpe282.service;

public interface IUpdateFromRestCtx<T> {

    boolean update(T dbCtx, T newCtx);
}
