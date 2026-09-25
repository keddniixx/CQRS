package com.server.cservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = "com.server")
@ComponentScan(basePackages = "com.server")
@EnableJpaRepositories(basePackages = "com.server")
public class CServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CServiceApplication.class, args);
    }

}
