package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RuleJdbc {

    private final JdbcTemplate jdbc;

    public Collection<Rule> findAll() {
        return jdbc.query("""
                        SELECT * FROM rules
                        """,
                (rs, row) -> setRule(rs));
    }

    public Optional<Rule> findById(int id) {
        return jdbc.query("""
                        SELECT * FROM rules
                        WHERE id = ?
                        """,
                (rs, row) -> setRule(rs),
                id).stream().findFirst();
    }


    public Rule setRule(ResultSet rs) throws SQLException {
        Rule rule = new Rule();
        rule.setId(rs.getInt("id"));
        rule.setName(rs.getString("name"));
        return rule;
    }
}
