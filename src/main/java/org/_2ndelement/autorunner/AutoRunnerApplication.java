package org._2ndelement.autorunner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ConfigurationPropertiesScan("org._2ndelement.autorunner.properties")
@MapperScan("org._2ndelement.autorunner.mapper")
public class AutoRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoRunnerApplication.class, args);
    }

}
