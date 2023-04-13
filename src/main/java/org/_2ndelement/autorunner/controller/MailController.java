package org._2ndelement.autorunner.controller;

import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org._2ndelement.autorunner.response.Response;
import org._2ndelement.autorunner.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@Validated
public class MailController {
    private final MailService mailService;

    @PostMapping("/send-verify-code")
    public ResponseEntity<?> sendVerifyCode(@RequestParam @Email(message = "邮箱格式不正确") String email) {
        mailService.sendVerifyCode(email);
        return ResponseEntity.ok().body(Response.ok());
    }
}
