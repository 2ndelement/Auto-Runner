package org._2ndelement.autorunner.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org._2ndelement.autorunner.entity.User;

/**
 * @author 27813
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2023-04-11 00:42:47
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param user       注册用户信息
     * @param verifyCode 验证码
     * @return 注册结果
     */
    boolean register(User user, String verifyCode) throws IllegalArgumentException;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果jwt
     */
    String login(String username, String password);

    /**
     * 判断某列的值是否存在
     *
     * @param column 列名
     * @param value  值
     * @return 是否存在
     */
    boolean exist(String column, Object value);

    /**
     * 验证邮箱验证码
     *
     * @param email      邮箱
     * @param verifyCode 验证码
     * @return 是否正确
     */
    boolean verifyCode(String email, String verifyCode);
}
