package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository users;

    public Optional<User> save(User user) {
        Optional<User> result = Optional.empty();
        try {
            users.save(user);
            result = Optional.of(user);
        } catch (Exception ex) {
            log.error("User not saved !");
        }
        return result;
    }
}
