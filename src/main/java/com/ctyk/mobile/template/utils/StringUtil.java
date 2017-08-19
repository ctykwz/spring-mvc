package com.ctyk.mobile.template.utils;

import java.util.Random;

/**
 * 字符串
 * Created by wei.yang on 2017/8/19.
 */
public class StringUtil {

    private static final String CHAR_REPOSITORY = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 生成一定长度的字符串
     *
     * @param len 长度
     * @return 字符串
     */
    public static String randomString(int len) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(CHAR_REPOSITORY.charAt(new Random().nextInt(CHAR_REPOSITORY.length() - 1)));
        }
        return builder.toString();
    }

    /**
     * 给字符串添加一定长度的前缀
     *
     * @param origin   原始字符串
     * @param element  添加的元素
     * @param totalLen 总长度
     * @return 需要的字符串
     */
    public static String addSuffix(String origin, char element, int totalLen) {
        if (origin.length() >= totalLen) {
            return origin;
        }
        return String.format(String.format("%%%ds", totalLen), origin).replaceAll(" ", String.valueOf(element));
    }
}
