package org._2ndelement.autorunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
class AutoRunnerApplicationTests {
    @Value("${platform.name}")
    private String platformName;
    @Value("${platform.verify-code.expire}")
    private int expireTime;
    @Value("${platform.verify-code.title}")
    private String subject;
    @Autowired
    private TemplateEngine templateEngine;
    private static final Logger logger = LogManager.getLogger(AutoRunnerApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    void verifyPage() {
        String code = "123456";
        Context context = new Context();
        context.setVariable("code", code);
        context.setVariable("platform", platformName);
        int minutes = expireTime / 60;
        int seconds = expireTime % 60;
        String expireMessage = minutes + "分" + seconds + "秒";
        context.setVariable("expire", expireMessage);
        String content = templateEngine.process("verify-code.html", context);
        logger.info(content);
    }

}
