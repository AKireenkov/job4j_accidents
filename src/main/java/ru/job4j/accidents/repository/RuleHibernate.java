package ru.job4j.accidents.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RuleHibernate {

    private final CrudRepository crudRepository;

    public Collection<Rule> findAll() {
        return crudRepository.query("FROM Rule", Rule.class);
    }

    public Optional<Rule> findById(int id) {
        return crudRepository.optional("FROM Rule WHERE id = :id",
                Rule.class,
                Map.of("id", id));
    }

    public Rule setRule(ResultSet rs) throws SQLException {
        Rule rule = new Rule();
        rule.setId(rs.getInt("id"));
        rule.setName(rs.getString("name"));
        return rule;
    }
}
