package com.ctyk.mobile.template.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * test
 * Created by wei.yang on 2017/8/14.
 */
@Service
public class TestService {

    private static final Logger logger = Logger.getLogger(TestService.class);

    private AtomicInteger count = new AtomicInteger(0);

    public TestService() {
    }

    /**
     * 打印数字
     */
    public void print() {
        logger.info(String.format("test service:\t%s", count.incrementAndGet()));
    }
}
