package com.ctyk.mobile.template.module.hook;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * 容器关闭时候比较有用
 * Created by wei.yang on 2017/3/8.
 */
public class CloseHook implements ServletContextListener {

    private final Logger logger = Logger.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // do nothing
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}
