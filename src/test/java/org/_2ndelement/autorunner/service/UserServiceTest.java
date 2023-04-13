package org._2ndelement.autorunner.service;

import org._2ndelement.autorunner.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
@Sql("classpath:db/schema.sql")
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void register() {
    }

    @Test
    void login() {
    }

    @Test
    void exist() {
        String username = "admin";
        String email = "123@example.com";
        assertThat(userService.exist("username", username)).isFalse();
        assertThat(userService.exist("username", email)).isFalse();
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword("123456");
        userService.save(user);
        assertThat(userService.exist("username", username)).isTrue();
        assertThat(userService.exist("email", email)).isTrue();
    }
}