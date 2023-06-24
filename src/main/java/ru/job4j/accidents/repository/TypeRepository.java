package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface TypeRepository extends CrudRepository<AccidentType, Integer> {

    default Collection<AccidentType> findAllTypes() {
        List<AccidentType> types = new ArrayList<>();
        findAll().forEach(types::add);
        return types;
    }
}