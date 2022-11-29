package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;

import java.util.List;
import java.util.Optional;

public interface OfficerDAO<T> {
    T save(T officer);

    Optional<T> findById(Integer id);

    List<T> findAll();

    long count();

    void delete(T officer);

    boolean existsById(Integer id);
}
