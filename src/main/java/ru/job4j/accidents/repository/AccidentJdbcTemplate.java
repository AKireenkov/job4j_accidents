package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.config.AccidentRowMapper;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Accident create(Accident accident) {
        jdbc.update("""
                        INSERT INTO accidents (name, text, address, type_id)
                        values (?, ?, ?, ?, ?)
                        """,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId());
        return accident;
    }

    public Collection<Accident> findAll() {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        jdbc.query(
                """
                        SELECT * FROM accidents AS ac 
                        LEFT JOIN types AS t ON ac.type_id = t.id 
                        LEFT JOIN accidents_and_rules AS ar ON ac.id = ar.accident_id 
                        LEFT JOIN rules AS r ON ar.rule_id = r.id
                        """,
                new AccidentRowMapper(accidentMap)
        );
        return accidentMap.values();
    }

    public Optional<Accident> findById(int id) {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        jdbc.query("""
                        SELECT * FROM accidents AS ac
                        LEFT JOIN types AS t ON ac.type_id = t.id
                        LEFT JOIN accidents_and_rules AS ar ON ac.id = ar.accident_id
                        LEFT JOIN rules AS r ON ar.rule_id = r.id
                        WHERE id = ?
                         """,
                new AccidentRowMapper(accidentMap),
                id
        );
        return accidentMap.values().stream().findFirst();
    }

    public boolean update(Accident accident) {
        return jdbc.update("UPDATE accidents SET name = ?, text = ?, address = ?, type_id = ? WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType(),
                accident.getId()) > 0;
    }
}