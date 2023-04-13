package org._2ndelement.autorunner.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class MailServiceTest {
    @Autowired
    private MailService mailService;
    private final String testToMail = "2781372804@qq.com";

    @Test
    void sendMail() {
        String subject = "test";
        String content = "test";
        mailService.sendMail(testToMail, subject, content);
    }

    @Test
    void sendVerifyCode() {
        String code = "123456";
        mailService.sendVerifyCode(testToMail, code);
    }
}