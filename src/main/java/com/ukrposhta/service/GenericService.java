package com.ukrposhta.service;

public interface GenericService<E> {
    E save(E entity);

    E findById(Long id);

    void delete(Long id);
}
