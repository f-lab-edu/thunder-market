package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.Util.EmailValidator;
import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/auth/join")
    public ResponseEntity<?> join(@RequestBody User user) {
        if(!EmailValidator.isValid(user.getEmail())) {
            return new ResponseEntity<>(EmailValidator.NOT_VALID_EMAIL_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.join(user), HttpStatus.OK);
    }
}
