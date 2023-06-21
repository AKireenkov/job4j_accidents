package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final CrudRepository crudRepository;

    public Accident save(Accident accident) {
        crudRepository.run(session -> session.save(accident));
        return accident;
    }

    public List<Accident> getAll() {
        return crudRepository.query("FROM Accident", Accident.class);
    }

    public Optional<Accident> findById(int id) {
        return crudRepository.optional("FROM Accident", Accident.class,
                Map.of("id", id));
    }

    public void update(Accident accident) {
        crudRepository.run(session -> session.update(accident));
    }
}
