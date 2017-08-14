package com.ctyk.mobile.template.services;

import com.ctyk.mobile.template.dao.UserInfoMapper;
import com.ctyk.mobile.template.module.dds.CustomerContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * test
 * Created by wei.yang on 2017/5/18.
 */
@Service
public class UserInfoService {

    private static final Logger logger = LogManager.getLogger(UserInfoService.class);

    private UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    /**
     * 获取用户数量
     *
     * @return 用户数量
     */
    public int obtainCount() {
        CustomerContextHolder.setMainDataSource();
        int count = userInfoMapper.obtainUserCount();
        CustomerContextHolder.clearContextHolder();
        int num=userInfoMapper.obtainUserCount();
        logger.info("num:\t"+num);
        logger.info("count:\t" + count);
        return count;
    }
}
