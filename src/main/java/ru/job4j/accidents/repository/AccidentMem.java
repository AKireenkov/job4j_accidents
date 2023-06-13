package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Accident create(Accident accident) {
        int id = nextId.get();
        if (id == nextId.get()) {
            accident.setId(id);
        }
        accidents.put(accident.getId(), accident);
        return accident;
    }
}
