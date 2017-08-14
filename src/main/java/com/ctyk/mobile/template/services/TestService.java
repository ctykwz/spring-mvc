package com.ctyk.mobile.template.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * test
 * Created by wei.yang on 2017/8/14.
 */
@Service
public class TestService {

    private AtomicInteger count=new AtomicInteger(0);

    public TestService() {
    }

    /**
     * 打印数字
     */
    public void print(){
        System.out.println(count.incrementAndGet());
    }
}
