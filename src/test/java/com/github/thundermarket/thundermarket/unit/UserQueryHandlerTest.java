package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.config.PasswordEncoderStub;
import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.service.UserQueryHandler;
import com.github.thundermarket.thundermarket.config.UserFakeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.thundermarket.thundermarket.config.TestUtils.createUser;

@Transactional
class UserQueryHandlerTest {

    @Test
    public void 전체_회원_조회() {
        int expectedAllUsers = 2;
        UserFakeRepository userFakeRepository = new UserFakeRepository();
        PasswordEncoderStub passwordEncoderStub = new PasswordEncoderStub();
        UserQueryHandler userService = new UserQueryHandler(userFakeRepository, passwordEncoderStub);
        userFakeRepository.save(createUser(1L, "test01@email.com", "password"));
        userFakeRepository.save(createUser(2L, "test02@email.com", "password2"));

        List<User> allUsers = userService.findAllUsers();

        Assertions.assertThat(allUsers.size()).isEqualTo(expectedAllUsers);
    }
}
