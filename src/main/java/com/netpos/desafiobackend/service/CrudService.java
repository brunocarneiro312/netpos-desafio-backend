package com.netpos.desafiobackend.service;

import com.netpos.desafiobackend.error.GenericError;

import java.util.List;

/**
 * @author brunocarneiro
 */
public interface CrudService<T> {

    T save(T t) throws GenericError;

    T update(T t) throws GenericError;

    T delete(Integer id) throws GenericError;

    T findById(Integer id) throws GenericError;

    List<T> findAll() throws GenericError;

}
