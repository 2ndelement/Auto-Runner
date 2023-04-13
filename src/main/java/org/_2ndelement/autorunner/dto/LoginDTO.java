package org._2ndelement.autorunner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDTO {
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

}
