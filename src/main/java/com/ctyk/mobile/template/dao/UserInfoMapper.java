package com.ctyk.mobile.template.dao;

import org.springframework.stereotype.Repository;

/**
 * user_info
 * Created by wei.yang on 2017/8/12.
 */
@Repository
public interface UserInfoMapper {

    /**
     * 获取用户数
     *
     * @return 用户数
     */
    int obtainUserCount();
}
