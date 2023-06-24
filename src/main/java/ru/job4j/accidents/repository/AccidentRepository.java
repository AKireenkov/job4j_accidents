package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    default Collection<Accident> findAllAccidents() {
        List<Accident> accidents = new ArrayList<>();
        findAll().forEach(accidents::add);
        return accidents;
    }
}