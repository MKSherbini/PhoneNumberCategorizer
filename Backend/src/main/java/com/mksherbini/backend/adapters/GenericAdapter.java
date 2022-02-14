package com.mksherbini.backend.adapters;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericAdapter<T, R> {
    R adaptOrmToDto(T orm);

    default List<R> adaptOrmToDto(List<T> orms) {
        return orms.stream().map(this::adaptOrmToDto).collect(Collectors.toList());
    }
}
