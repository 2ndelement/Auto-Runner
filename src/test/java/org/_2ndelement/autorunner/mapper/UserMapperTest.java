package org._2ndelement.autorunner.mapper;

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
@Sql("classpath:db/schema.sql")
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    void testInsert() {
        User user = new User();
        user.setEmail("2ndelement@qq.com");
        user.setPassword("123456");
        user.setUsername("2ndelement");
        userMapper.insert(user);
        assertThat(user.getId()).isNotNull();
    }
}