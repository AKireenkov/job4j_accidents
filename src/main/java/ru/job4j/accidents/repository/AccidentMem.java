package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final AtomicInteger nextId = new AtomicInteger(0);

    List<AccidentType> types = List.of(
            new AccidentType(1, "Две машины"),
            new AccidentType(2, "Машина и человек"),
            new AccidentType(3, "Машина и велосипед")
    );

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>() {
        {
            put(1, new Accident(1,
                    "Парковка в неположеном месте",
                    "Припарковался на газоне",
                    "Ул. Московская д.1",
                    types.get(0)));
            put(2, new Accident(2,
                    "Превышение скорости",
                    "Превышение от 20 до 40 км/ч",
                    "Ул. Демидова д.26",
                    types.get(2)));
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
        accidents.put(accident.getId(), accident);
        return accident;
    }

    public List<AccidentType> findAllTypes() {
        return types;
    }
}
