package ru.job4j.accidents.repository;


import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RuleHibernate {

    private final SessionFactory sf;

    public Collection<Rule> findAll() {
        try (Session session = sf.openSession()){
           return session.createQuery("FROM Rule", Rule.class).list();
        }
    }

    public Optional<Rule> findById(int id){
        try (Session session = sf.openSession()){
            return session.createQuery("FROM Rule WHERE id = :id", Rule.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        }
    }

    public Rule setRule(ResultSet rs) throws SQLException {
        Rule rule = new Rule();
        rule.setId(rs.getInt("id"));
        rule.setName(rs.getString("name"));
        return rule;
    }
}
