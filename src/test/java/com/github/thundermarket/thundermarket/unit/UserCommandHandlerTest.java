package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.service.UserCommandHandler;
import com.github.thundermarket.thundermarket.testDouble.UserFakeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
class UserCommandHandlerTest {

    private User createUser(String email, String password) {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }

    @Test
    void 회원가입_성공() {
        UserFakeRepository userFakeRepository = new UserFakeRepository();
        UserCommandHandler userService = new UserCommandHandler(userFakeRepository);
        User user = createUser("test01@email.com", "password");

        User savedUser = userService.join(user);

        Assertions.assertThat(user).isEqualTo(savedUser);
    }
}
