package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import com.github.thundermarket.thundermarket.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    void 회원가입_성공() {
        String userId = "test01";
        String password = "password";

        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);

        User savedUser = userService.join(user);

        Assertions.assertThat(user).isEqualTo(savedUser);
    }
}