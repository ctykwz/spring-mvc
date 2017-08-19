package com.ctyk.mobile.template.string;

import com.ctyk.mobile.template.utils.StringUtil;
import org.junit.Test;

/**
 * random len string
 * Created by wei.yang on 2017/8/19.
 */
public class StringTest {

    private static final int DEFAULT_LEN = 5;

    @Test
    public void test() {
        System.out.println(StringUtil.randomString(DEFAULT_LEN));
        System.out.println(StringUtil.addSuffix("afff", 'd', 15));
    }
}
