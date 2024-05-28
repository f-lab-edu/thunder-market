package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입_성공() {
        String userId = "test01";
        String password = "password";

        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);

        userRepository.save(user);

        User savedUser = userRepository.findByUserIdAndPassword(userId, password);
        Assertions.assertThat(user).isEqualTo(savedUser);
    }
}
