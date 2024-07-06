package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> listUsers();

    void create(User user);

    void delete(Long id);

    void update(User user);

    User findById(Long id);
}
