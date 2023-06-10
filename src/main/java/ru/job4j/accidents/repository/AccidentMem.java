package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {

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
}
