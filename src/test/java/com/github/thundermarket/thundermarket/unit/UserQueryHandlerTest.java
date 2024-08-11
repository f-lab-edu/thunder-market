package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.service.UserQueryHandler;
import com.github.thundermarket.thundermarket.testDouble.UserFakeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
class UserQueryHandlerTest {

    private User createUser(String email, String password) {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
    @Test
    public void 전체_회원_조회() {
        int expectedAllUsers = 2;
        UserFakeRepository userFakeRepository = new UserFakeRepository();
        UserQueryHandler userService = new UserQueryHandler(userFakeRepository);
        userFakeRepository.save(createUser("test01@email.com", "password"));
        userFakeRepository.save(createUser("test02@email.com", "password2"));

        List<User> allUsers = userService.findAllUsers();

        Assertions.assertThat(allUsers.size()).isEqualTo(expectedAllUsers);
    }
}
