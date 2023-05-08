package com.ukrposhta.mapper;

public interface RequestDtoMapper<D, T> {
    T toModel(D requestDto);
}
