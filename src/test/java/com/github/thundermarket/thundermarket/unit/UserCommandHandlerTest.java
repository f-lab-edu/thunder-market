package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.config.PasswordEncoderStub;
import com.github.thundermarket.thundermarket.config.TestUtils;
import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.service.UserCommandHandler;
import com.github.thundermarket.thundermarket.config.UserFakeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static com.github.thundermarket.thundermarket.config.TestUtils.createUser;

@Transactional
class UserCommandHandlerTest {

    @Test
    void 회원가입_성공() {
        UserFakeRepository userFakeRepository = new UserFakeRepository();
        PasswordEncoderStub passwordEncoderStub = new PasswordEncoderStub();
        UserCommandHandler userService = new UserCommandHandler(userFakeRepository, passwordEncoderStub);
        User user = createUser(1L, "test01@email.com", "password");

        User savedUser = userService.join(user);

        Assertions.assertThat(user).isEqualTo(savedUser);
    }
}
