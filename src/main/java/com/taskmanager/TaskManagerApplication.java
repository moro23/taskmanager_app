package com.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bootstrap class for the Task Manager REST API.
 *
 * <p>Starts the embedded servlet container, initializes the Spring
 * application context, and triggers Flyway database migrations.</p>
 *
 * @see <a href="https://docs.spring.io/spring-boot/reference/">Spring Boot Reference</a>
 */
@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }
}