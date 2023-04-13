package org._2ndelement.autorunner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org._2ndelement.autorunner.dto.LoginDTO;
import org._2ndelement.autorunner.dto.UserDTO;
import org._2ndelement.autorunner.properties.JwtProperties;
import org._2ndelement.autorunner.response.Response;
import org._2ndelement.autorunner.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth", description = "用户认证")
public class AuthController {
    private final UserService userService;
    private final JwtProperties jwtProperties;


    @Operation(summary = "用户注册")
    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, @RequestParam String verifyCode) {
        userService.register(userDTO.convert(), verifyCode);
        return ResponseEntity.ok().body(Response.ok());
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        String token = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        Map<String, Object> data = Map.of("access_token", token, "expires_in", jwtProperties.getExpiration());
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return ResponseEntity.ok().headers(headers).body(Response.ok(data));
    }
}
