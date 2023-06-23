package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository users;

    public String save(User user) {
        String redirect = "redirect:/login";
        try {
            users.save(user);
        } catch (Exception ex) {
            redirect = "reg";
        }
        return redirect;
    }
}
