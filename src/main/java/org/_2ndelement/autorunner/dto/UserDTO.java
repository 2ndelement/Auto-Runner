package org._2ndelement.autorunner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org._2ndelement.autorunner.entity.User;

import java.io.Serial;
import java.io.Serializable;


@Data
public class UserDTO implements Convert<User>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,32}$", message = "用户名格式不正确")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)[a-zA-Z0-9~!@#$%^&*?._-]{8,32}$", message = "密码格式不正确")
    private String password;

    /**
     * @return 转换为User对象
     */
    @Override
    public User convert() {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
