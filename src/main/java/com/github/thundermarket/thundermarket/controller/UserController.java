package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.Util.Email;
import com.github.thundermarket.thundermarket.aspect.Authenticated;
import com.github.thundermarket.thundermarket.aspect.SessionUserParam;
import com.github.thundermarket.thundermarket.constant.SessionConst;
import com.github.thundermarket.thundermarket.domain.SessionUser;
import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/users")
    public ResponseEntity<?> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @Authenticated
    @PostMapping("/api/v1/auth/join")
    public ResponseEntity<?> join(@RequestBody User user) {
        if(!user.isEmailValid()) {
            return new ResponseEntity<>(Email.NOT_VALID_EMAIL_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.join(user), HttpStatus.OK);
    }

    @Authenticated
    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
        User savedUser = userService.checkCredential(user);
        if(savedUser == null) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
        session.setAttribute(SessionConst.SESSION_USER,
                new SessionUser.Builder()
                        .withId(savedUser.getId())
                        .withEmail(savedUser.getEmail())
                        .build());
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    @GetMapping("/api/v1/mypage")
    public ResponseEntity<SessionUser> mypage(@SessionUserParam SessionUser sessionUser) {
        return new ResponseEntity<>(sessionUser, HttpStatus.OK);
    }
}
