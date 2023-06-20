package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final SessionFactory sf;

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Accident", Accident.class)
                    .list();
        }
    }

    public Optional<Accident> findById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Accident WHERE id = :id", Accident.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        }
    }

    public boolean update(Accident accident) {
        try (Session session = sf.openSession()) {
            return session.createQuery("""
                            UPDATE Accident SET name = :name, text = :text,
                             address = :address, type_id = :typeId,
                             WHERE id = :id
                            """, Accident.class)
                    .setParameter("id", accident.getId())
                    .setParameter("name", accident.getName())
                    .setParameter("text", accident.getText())
                    .setParameter("address", accident.getAddress())
                    .setParameter("typeId", accident.getType().id)
                    .executeUpdate() > 0;
        }
    }
}
