package com.ctyk.mobile.template.net;

import com.alibaba.fastjson.JSONObject;
import com.ctyk.mobile.template.utils.NetWorkUtil;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * net
 * Created by wei.yang on 2017/8/19.
 */
public class NetTest {

    private static final String URL = "http://localhost:8080/test";

    private static final String CONTENT_TYPE = "application/json;charset=utf-8";

    private static final String COMPANY_ID = "ctyk";

    @Test
    public void test() throws NoSuchAlgorithmException, IOException, KeyManagementException {
        JSONObject params = new JSONObject();
        //不带授权
        System.out.println(NetWorkUtil.openUrl(URL, CONTENT_TYPE, params, "POST"));
        JSONObject attrs = new JSONObject();
        //授权码不对
        attrs.put("companyId", "wei");
        System.out.println(NetWorkUtil.openUrlWithAttrs(URL, CONTENT_TYPE, params, "POST", attrs));
        attrs.clear();
        //正确的授权码
        attrs.put("companyId",COMPANY_ID);
        System.out.println(NetWorkUtil.openUrlWithAttrs(URL, CONTENT_TYPE, params, "POST", attrs));
        //api只支持POST请求
        System.out.println(NetWorkUtil.openUrlWithAttrs(URL, CONTENT_TYPE, params, "GET", attrs));
    }

}
