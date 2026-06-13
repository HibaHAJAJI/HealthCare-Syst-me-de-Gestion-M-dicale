package org.example.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.healthcare")
public class HealthCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthCareApplication.class, args);
    }

}
