package org.axonframework.samples.conferences;

import org.axonframework.config.EventHandlingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConferencesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferencesApplication.class, args);
    }

    @Autowired
    public void enableTracking(EventHandlingConfiguration configuration) {
        configuration.usingTrackingProcessors();
    }
}
