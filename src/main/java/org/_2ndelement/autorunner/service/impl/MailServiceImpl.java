package org._2ndelement.autorunner.service.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org._2ndelement.autorunner.Constants;
import org._2ndelement.autorunner.properties.AppProperties;
import org._2ndelement.autorunner.service.MailService;
import org._2ndelement.autorunner.service.UserService;
import org._2ndelement.autorunner.utils.VerifyCodeGenerator;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;
    private final UserService userService;
    private final RedisTemplate<String, String> redisTemplate;
    private final VerifyCodeGenerator codeGenerator;

    private final AppProperties appProperties;
    private final MailProperties mailProperties;

    /**
     * 发送邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override

    public void sendMail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("发送邮件失败", e);
        }
    }

    /**
     * 发送验证码, 无约束
     *
     * @param to   收件人
     * @param code 验证码
     */
    @Override
    public void sendVerifyCode(String to, String code) {
        Context context = new Context();
        context.setVariable("code", code);
        context.setVariable("platform", appProperties.getName());
        int expiration = appProperties.getVerifyCode().getExpiration();
        int minutes = expiration / 60;
        int seconds = expiration % 60;
        String expireMessage = minutes + "分" + seconds + "秒";
        context.setVariable("expire", expireMessage);
        String content = templateEngine.process("verify-code.html", context);
        sendMail(to, appProperties.getVerifyCode().getEmail().getSubject(), content);
    }

    /**
     * 发送验证码, 有验证频次限制和邮箱是否已注册的约束
     *
     * @param email 邮箱
     */
    @Override
    public void sendVerifyCode(String email) {
        if (userService.exist("email", email)) {
            throw new IllegalArgumentException("邮箱已被注册");
        }
        String isSentKey = Constants.VERIFY_LIMIT_KEY + email;
        String emailCodeKey = Constants.VERIFY_KEY + email;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(isSentKey))) {
            throw new IllegalArgumentException("验证码已发送，请稍后再试");
        }
        String code = codeGenerator.generateCode();
        redisTemplate.opsForValue().set(emailCodeKey, code, 5, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(isSentKey, "1", 60, TimeUnit.SECONDS);
        sendVerifyCode(email, code);
    }

}
