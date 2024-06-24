package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.InMemoryUserRepository;
import com.github.thundermarket.thundermarket.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserServiceTest {

    InMemoryUserRepository userRepository = new InMemoryUserRepository();
    UserService userService = new UserService(userRepository);

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    private User createUser(String email, String password) {
        return new User.Builder()
                .withEmail(email)
                .withPassword(password)
                .build();
    }

    @Test
    void 회원가입_성공() {
        User user = createUser("test01@email.com", "password");

        User savedUser = userService.join(user);

        Assertions.assertThat(user).isEqualTo(savedUser);
    }

    @Test
    public void 전체_회원_조회() {
        int expectedAllUsers = 2;
        User user = createUser("test01@email.com", "password");
        User user2 = createUser("test02@email.com", "password2");

        userService.join(user);
        userService.join(user2);

        List<User> allUsers = userService.findAllUsers();
        Assertions.assertThat(allUsers.size()).isEqualTo(expectedAllUsers);
    }
}