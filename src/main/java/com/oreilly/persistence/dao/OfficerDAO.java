package com.oreilly.persistence.dao;

import java.util.List;
import java.util.Optional;

// allow Type Officer and type OfficerEntity implementations from the same interface
public interface OfficerDAO<T> {
    T save(T officer);
    Optional<T> findById(Integer id);
    List<T> findAll();
    long count();
    void delete(T officer);
    boolean existsById(Integer id);
}
