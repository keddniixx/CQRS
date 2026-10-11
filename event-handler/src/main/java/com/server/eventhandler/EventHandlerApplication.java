package com.server.eventhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.server")
@ComponentScan(basePackages = "com.server")
@EnableJpaRepositories(basePackages = "com.server.*")
public class EventHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventHandlerApplication.class, args);
    }

}
