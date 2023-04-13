package org._2ndelement.autorunner.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String name;

    private VerifyCodeProperties verifyCode;

    @Data
    public static class VerifyCodeProperties {

        private int expiration;

        private EmailProperties email;

    }

    @Data
    public static class EmailProperties {
        private String subject;
    }


}

