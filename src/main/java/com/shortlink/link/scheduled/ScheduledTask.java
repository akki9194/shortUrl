package com.shortlink.link.scheduled;

/**
 * <b>Created by Akash Hirke</b>
 * <b>Scheduled Job class</b>
 */

import com.shortlink.link.Services.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTask {

    @Autowired
    LinkService linkService;

    /**
     * <b>This job is execute in every 5 to deactivated the tiny urls.</b>
     */
    @Scheduled(cron = "0 */5 * * * *")
    public void checkAndExpireLinks() {
        linkService.expireLinks();
    }
}
