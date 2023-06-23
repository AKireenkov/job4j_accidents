package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    public Collection<AccidentType> findAll() {
        List<AccidentType> types = new ArrayList<>();
        typeRepository.findAll().forEach(types::add);
        return types;
    }
}
