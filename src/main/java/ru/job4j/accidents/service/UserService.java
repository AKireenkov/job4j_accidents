package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository users;

    public Optional<User> save(User user) {
        Optional<User> result = Optional.of(user);
        try {
            users.save(user);
        } catch (Exception ex) {
            result = Optional.empty();
        }
        return result;
    }
}
