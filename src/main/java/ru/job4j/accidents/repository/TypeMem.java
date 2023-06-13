package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;

@Repository
public class TypeMem {

    List<AccidentType> types = List.of(
            new AccidentType(1, "Две машины"),
            new AccidentType(2, "Машина и человек"),
            new AccidentType(3, "Машина и велосипед")
    );

    public List<AccidentType> findAllTypes() {
        return types;
    }

    public AccidentType findById(int id) {
        return types.stream().filter(t -> t.id == id).findFirst().get();
    }
}
