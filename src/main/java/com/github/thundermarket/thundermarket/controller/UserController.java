package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.Util.Email;
import com.github.thundermarket.thundermarket.aspect.Authenticated;
import com.github.thundermarket.thundermarket.aspect.SessionUserParam;
import com.github.thundermarket.thundermarket.constant.SessionConst;
import com.github.thundermarket.thundermarket.domain.SessionUser;
import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.service.UserCommandHandler;
import com.github.thundermarket.thundermarket.service.UserQueryHandler;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserQueryHandler userQueryHandler;
    private final UserCommandHandler userCommandHandler;

    public UserController(UserQueryHandler userQueryHandler, UserCommandHandler userCommandHandler) {
        this.userQueryHandler = userQueryHandler;
        this.userCommandHandler = userCommandHandler;
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers() {
        return new ResponseEntity<>(userQueryHandler.findAllUsers(), HttpStatus.OK);
    }

    @Authenticated
    @PostMapping("/auth/join")
    public ResponseEntity<?> join(@RequestBody User user) {
        if(!user.isEmailValid()) {
            return new ResponseEntity<>(Email.NOT_VALID_EMAIL_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userCommandHandler.join(user), HttpStatus.OK);
    }

    @Authenticated
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
        User savedUser = userQueryHandler.checkCredential(user);
        if(savedUser == null) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
        session.setAttribute(SessionConst.SESSION_USER,
                SessionUser.builder()
                        .id(savedUser.getId())
                        .email(savedUser.getEmail())
                        .build());
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity<SessionUser> mypage(@SessionUserParam SessionUser sessionUser) {
        return new ResponseEntity<>(sessionUser, HttpStatus.OK);
    }
}
