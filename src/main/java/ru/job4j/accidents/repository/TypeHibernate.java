package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TypeHibernate {

    private final CrudRepository crudRepository;

    public Collection<AccidentType> findAll() {
        return crudRepository.query("FROM Type", AccidentType.class);
    }

    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional("FROM Type WHERE id = :id",
                AccidentType.class,
                Map.of("id", id));
    }

    public AccidentType setType(ResultSet rs) throws SQLException {
        AccidentType type = new AccidentType();
        type.setId(rs.getInt("id"));
        type.setName(rs.getString("name"));
        return type;
    }
}
