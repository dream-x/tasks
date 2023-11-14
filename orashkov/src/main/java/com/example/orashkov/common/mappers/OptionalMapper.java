package com.example.orashkov.common.mappers;


import com.example.orashkov.common.exceptions.EntityNotFoundException;

import java.util.Optional;

public class OptionalMapper {

    public static <T> T getDto(Optional<T> optionalDto) {
        if (optionalDto.isPresent()) {
            return optionalDto.get();
        } else {
            throw new EntityNotFoundException("Entity not found!");
        }
    }
}
