package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypeJdbc;

import java.util.Collection;

@Service
@AllArgsConstructor
public class TypeService {

    private final TypeJdbc typeRepository;

    public Collection<AccidentType> findAll() {
        return typeRepository.findAll();
    }
}
