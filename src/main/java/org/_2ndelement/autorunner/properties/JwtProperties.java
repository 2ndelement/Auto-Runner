package org._2ndelement.autorunner.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jwt")

public class JwtProperties {
    /**
     * 过期时间
     */
    private long expiration;
    /**
     * 密钥
     */
    private String secret;

}
