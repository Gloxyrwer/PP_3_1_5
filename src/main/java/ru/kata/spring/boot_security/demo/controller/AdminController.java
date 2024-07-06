package ru.kata.spring.boot_security.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/table")
    public ResponseEntity<List<User>> viewUsers() {
        log.info("Получение всех пользователей");
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        log.info("Удаление пользователя с id: {}", id);
        userService.delete(id);
        log.info("Пользователь с id: {} удален", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user) {
        log.info("Редактирование пользователя с id: {}", user.getId());
        userService.update(user);
        log.info("Пользователь с id: {} редактирован", user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        log.info("Создание пользователя с именем: {}", user.getUsername());
        userService.create(user);
        log.info("Пользователь с именем: {} создан", user.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        log.info("Получение пользователя с id: {}", id);
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/auth")
    public ResponseEntity<User> getApiAuthUser(@AuthenticationPrincipal User user) {
        log.info("Получение аутентифированного пользователя");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}