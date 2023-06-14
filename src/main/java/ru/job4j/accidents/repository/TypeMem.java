package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;

@Repository
public class TypeMem {

    private final Map<Integer, AccidentType> types = Map.of(
            1, new AccidentType(1, "Две машины"),
            2, new AccidentType(2, "Машина и человек"),
            3, new AccidentType(3, "Машина и велосипед")
    );

    public Collection<AccidentType> findAll() {
        return types.values();
    }

    public AccidentType findById(int id) {
        return types.get(id);
    }
}
