package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TypeHibernate {
    private final SessionFactory sf;

    public Collection<AccidentType> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Type", AccidentType.class)
                    .list();
        }
    }

    public Optional<AccidentType> findById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery("""
                            FROM Type
                            WHERE id = :id
                            """, AccidentType.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        }
    }


    public AccidentType setType(ResultSet rs) throws SQLException {
        AccidentType type = new AccidentType();
        type.setId(rs.getInt("id"));
        type.setName(rs.getString("name"));
        return type;
    }
}
