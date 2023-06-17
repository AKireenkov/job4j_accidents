package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Accident create(Accident accident) {
        jdbc.update("""
                        INSERT INTO accidents (name, text, address, rule_id, type_id)
                        values (?, ?, ?, ?, ?)
                        """,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getRules().stream().findFirst().get().getId(),
                accident.getType().getId());
        return accident;
    }

    public List<Accident> findAll() {
        return jdbc.query("""
                        SELECT * FROM accidents a
                        LEFT JOIN rules r ON a.rule_id = r.id
                        LEFT JOIN types t ON a.type_id = t.id
                        """,
                (rs, row) -> setAccident(rs));
    }

    public Optional<Accident> findById(int id) {
        return jdbc.query("""
                        SELECT * FROM accidents a
                        LEFT JOIN rules r ON a.rule_id = r.id
                        LEFT JOIN types t ON a.type_id = t.id
                        WHERE id = ?
                        """,
                (rs, row) -> setAccident(rs),
                id
        ).stream().findFirst();
    }

    public boolean update(Accident accident) {
        return jdbc.update("UPDATE accidents SET name = ?, text = ?, address = ?, type_id = ?, rules_id = ? WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType(),
                accident.getRules(),
                accident.getId()) > 0;
    }

    public Accident setAccident(ResultSet rs) throws SQLException {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setRules(
                Set.of(new Rule(rs.getInt("rule_id"), rs.getString("r.name")))
        );
        accident.setType(new AccidentType(rs.getInt("type_id"), rs.getString("t.name")));
        return accident;
    }
}