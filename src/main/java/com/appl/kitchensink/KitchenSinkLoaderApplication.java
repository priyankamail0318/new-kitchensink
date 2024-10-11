package com.appl.kitchensink;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.appl.kitchensink")
@EnableJpaRepositories(basePackages = "com.appl.kitchensink.repository")
@EntityScan(basePackages = "com.appl.kitchensink.model")
@Slf4j
public class KitchenSinkLoaderApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        log.info("Kitchen Sink Application Started ... ");
        SpringApplication.run(KitchenSinkLoaderApplication.class, args);
    }
}
