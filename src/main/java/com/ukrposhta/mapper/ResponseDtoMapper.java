package com.ukrposhta.mapper;

public interface ResponseDtoMapper<D, T> {
    D toDto(T t);
}
