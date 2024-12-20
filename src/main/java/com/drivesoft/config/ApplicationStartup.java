package com.drivesoft.config;

import com.drivesoft.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationStartedEvent> {
    private final UserService userService;


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("Application started. Creating admin user.");

        try {
            userService.createAdminUser();
        } catch (Exception e) {
            log.warn("Skipping admin user creation: {}", e.getMessage());
        }
    }
}
