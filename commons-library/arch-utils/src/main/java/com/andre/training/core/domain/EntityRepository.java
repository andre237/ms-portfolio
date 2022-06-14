package com.andre.training.core.domain;

import java.util.Set;

public interface EntityRepository<E, I> {

    E save(E entity);
    E findById(I id);
    Set<E> findAll();

}