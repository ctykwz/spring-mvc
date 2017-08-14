package com.ctyk.mobile.template.module.dds;

import com.google.common.base.Strings;

/**
 * 动态数据源
 * Created by wei.yang on 2017/8/12.
 */
public class CustomerContextHolder {

    private static final String MAIN_DATA_SOURCE = "mainDataSource";

    private static final String VICE_DATA_SOURCE = "mainDataSource";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 必须设置一个默认数据源
     */
    static String getCustomerType() {
        String datasource = contextHolder.get();
        if (Strings.isNullOrEmpty(datasource)) {
            return MAIN_DATA_SOURCE;
        }
        return datasource;
    }

    /**
     * 设置数据源
     */
    private static void setContextHolder(String customerType) {
        contextHolder.set(customerType);
    }

    /**
     * 清除数据
     */
    public static void clearContextHolder() {
        contextHolder.remove();
    }

    /**
     * 设置主数据源
     */
    public static void setMainDataSource() {
        setContextHolder(MAIN_DATA_SOURCE);
    }

    /**
     * 设置副数据源
     */
    public static void setViceDataSource() {
        setContextHolder(VICE_DATA_SOURCE);
    }
}
