package com.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FlywayMigrationRunner {

    public static void main(String[] args) {
        SpringApplication.run(FlywayMigrationRunner.class, args);
    }
}
