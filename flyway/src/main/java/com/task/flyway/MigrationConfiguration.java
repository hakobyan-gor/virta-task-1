package com.task.flyway;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Configuration
public class MigrationConfiguration {

    final Logger logger = LoggerFactory.getLogger(MigrationConfiguration.class);

    final List<Consumer<Flyway>> options;

    public MigrationConfiguration(MigrationProperties migrationProperties) {
        List<String> options = migrationProperties.getOptions();
        this.options = new ArrayList<>(options.size());
        options.forEach(option -> {
            switch (option) {
                case "migrate" -> this.options.add(Flyway::migrate);
                case "clean" -> this.options.add(Flyway::clean);
                default -> logger.warn("Unknown Flyway option " + option);
            }

        });
    }

    @Bean
    FlywayMigrationStrategy flywayMigrationStrategy() {
        return (flyway) -> {
            options.forEach(option -> option.accept(flyway));
        };
    }
}
