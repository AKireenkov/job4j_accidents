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

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>() {
        {
            put(1, new Accident(1,
                    "Парковка в неположеном месте",
                    "Припарковался на газоне",
                    "Ул. Московская д.1"));
            put(2, new Accident(2,
                    "Превышение скорости",
                    "Превышение от 20 до 40 км/ч",
                    "Ул. Демидова д.26"));
        }
    };

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
        accidents.putIfAbsent(accident.getId(), accident);
        return accident;
    }
}
