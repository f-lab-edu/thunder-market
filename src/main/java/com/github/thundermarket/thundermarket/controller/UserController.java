package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.Util.Email;
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

    @GetMapping("/api/v1/users")
    public ResponseEntity<?> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/api/v1/auth/join")
    public ResponseEntity<?> join(@RequestBody User user) {
        if(!user.isEmailValid()) {
            return new ResponseEntity<>(Email.NOT_VALID_EMAIL_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.join(user), HttpStatus.OK);
    }
}
