package org.axonframework.samples.conferences;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventLogger {

    private static final Logger logger = LoggerFactory.getLogger(EventLogger.class);

    @EventHandler
    public void on(Object object) {
        logger.info("Handling event: {}", object.getClass());
    }
}
