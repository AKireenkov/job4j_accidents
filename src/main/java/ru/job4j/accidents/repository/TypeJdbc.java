package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TypeJdbc {
    private final JdbcTemplate jdbc;

    public Collection<AccidentType> findAll() {
        return jdbc.query("""
                        SELECT * FROM types
                        """,
                (rs, row) -> setType(rs));
    }

    public Optional<AccidentType> findById(int id) {
        return jdbc.query("""
                        SELECT * FROM types
                        WHERE id = ?
                        """,
                (rs, row) -> setType(rs),
                id).stream().findFirst();
    }


    public AccidentType setType(ResultSet rs) throws SQLException {
        AccidentType type = new AccidentType();
        type.setId(rs.getInt("id"));
        type.setName(rs.getString("name"));
        return type;
    }
}
