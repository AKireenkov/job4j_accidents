package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final AtomicInteger nextId = new AtomicInteger(1);

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>() {
        {
            put(1, new Accident(1,
                    "Парковка в неположеном месте",
                    "Припарковался на газоне",
                    "Ул. Московская д.1",
                    new AccidentType(1, "Две машины"),
                    Set.of(new Rule(1, "Статья. 1"), new Rule(3, "Статья. 3"))));
            put(2, new Accident(2,
                    "Превышение скорости",
                    "Превышение от 20 до 40 км/ч",
                    "Ул. Демидова д.26",
                    new AccidentType(2, "Машина и человек"),
                    Set.of(new Rule(2, "Статья. 2"), new Rule(5, "Статья. 5"),
                            new Rule(1, "Статья. 1"), new Rule(8, "Статья. 8")
                    )));
        }
    };

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public Accident create(Accident accident) {
        accident.setId(nextId.getAndIncrement());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    public boolean update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (id, oldAccident) -> new Accident(
                oldAccident.getId(), accident.getName(),
                accident.getAddress(), accident.getText(), accident.getType(),
                accident.getRules())) != null;
    }
}
