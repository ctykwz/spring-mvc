package com.ctyk.mobile.template.cron;

import com.ctyk.mobile.template.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * cron demo
 * 注：spring-context中需要配置，否则定时任务不会生效
 * Created by wei.yang on 2017/8/14.
 */
@Component
public class TestCron {

    private TestService testService;

    @Autowired
    public TestCron(TestService testService) {
        this.testService = testService;
        Assert.notNull(this.testService, "testService must not be null.");
    }

    /**
     * minute, hour, day of month, month and day of week.
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void printSchedule() {
        testService.print();
    }
}
