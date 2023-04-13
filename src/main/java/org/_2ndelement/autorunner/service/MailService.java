package org._2ndelement.autorunner.service;

import org.springframework.stereotype.Service;

@Service
public interface MailService {
    /**
     * 发送邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendMail(String to, String subject, String content);

    /**
     * @param to   收件人
     * @param code 验证码
     */
    void sendVerifyCode(String to, String code);

    void sendVerifyCode(String email);
}
