package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        String email = "test01@email.com";
        String password = "password";
        User user = new User(email, password);

        userRepository.save(user);

        User savedUser = userRepository.findByEmailAndPassword(email, password);
        Assertions.assertThat(user).isEqualTo(savedUser);
    }

    @Test
    public void 전체_회원_조회() {
        String email = "test01@email.com";
        String password = "password";
        User user = new User(email, password);

        userRepository.save(user);

        List<User> allUsers = userRepository.findAll();
        Assertions.assertThat(allUsers.getFirst().getEmail()).isEqualTo(email);
    }
}
