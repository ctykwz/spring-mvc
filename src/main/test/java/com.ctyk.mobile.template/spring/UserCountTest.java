package com.ctyk.mobile.template.spring;

import com.ctyk.mobile.template.services.UserInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 主要是关于spring自带测试组件的使用
 * 注：servlet版本要在3.0之上
 * Created by wei.yang on 2017/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(value = "/spring/spring-root.xml")
public class UserCountTest {

    private static final Logger logger = LogManager.getLogger(UserCountTest.class);

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void userCountTest() {
        logger.info("user count:\t" + userInfoService.obtainCount());
    }
}
