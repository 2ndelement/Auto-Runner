package org._2ndelement.autorunner.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org._2ndelement.autorunner.Constants;
import org._2ndelement.autorunner.entity.User;
import org._2ndelement.autorunner.mapper.UserMapper;
import org._2ndelement.autorunner.properties.JwtProperties;
import org._2ndelement.autorunner.service.UserService;
import org._2ndelement.autorunner.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author 27813
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-04-11 00:42:47
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final RedisTemplate<String, String> redisTemplate;
    private final JwtUtils jwtUtils;
    private final JwtProperties jwtProperties;
    private ZSetOperations<String, String> zSetOps; // ZSet操作对象


    @PostConstruct
    public void init() {
        zSetOps = redisTemplate.opsForZSet();
    }

    @Override
    public boolean register(User user, String verifyCode) throws IllegalArgumentException {
        if (exist("username", user.getUsername())) {
            throw new IllegalArgumentException("用户名已存在");
        }
        if (exist("email", user.getEmail())) {
            throw new IllegalArgumentException("邮箱已存在");
        }
        if (!verifyCode(user.getEmail(), verifyCode)) {
            throw new IllegalArgumentException("验证码错误");
        }
        return save(user);
    }

    private boolean allowRequest(String key) {
        long now = Instant.now().getEpochSecond();
        zSetOps.add(key, String.valueOf(now), now);
        zSetOps.removeRangeByScore(key, 0, now - Constants.WINDOW_SIZE);
        return zSetOps.zCard(key) <= Constants.MAX_REQUESTS;
    }

    @Override
    public String login(String username, String password) {
        if (!allowRequest(Constants.LOGIN_KEY + username)) {
            throw new IllegalArgumentException("请求过于频繁");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username).eq("password", password);
        User user = getBaseMapper().selectOne(wrapper);
        if (user == null) {
            throw new IllegalArgumentException("用户名或密码错误");
        }
        String token = jwtUtils.generateToken(user.getId());
        redisTemplate.opsForValue()
                .set(Constants.TOKEN_KEY + user.getUsername(), token, jwtProperties.getExpiration(), TimeUnit.SECONDS);
        return token;
    }


    /**
     * 判断某列的值是否存在
     *
     * @param column 列名
     * @param value  值
     * @return 是否存在
     */
    @Override
    public boolean exist(String column, Object value) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(column, value);
        return getBaseMapper().exists(wrapper);
    }

    /**
     * 验证邮箱验证码
     *
     * @param email      邮箱
     * @param verifyCode 验证码
     * @return 是否正确
     */
    @Override
    public boolean verifyCode(String email, String verifyCode) {
        String code = redisTemplate.opsForValue().get(Constants.VERIFY_KEY + email);
        if (code != null && code.equals(verifyCode)) {
            redisTemplate.delete(Constants.VERIFY_KEY + email);
            return true;
        }
        return false;
    }
}




