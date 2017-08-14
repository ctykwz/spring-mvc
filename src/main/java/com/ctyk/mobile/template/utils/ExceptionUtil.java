package com.ctyk.mobile.template.utils;

import com.ctyk.mobile.template.exception.ExceptionHandlerController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常处理
 * Created by wei.yang on 2017/4/18.
 */
public class ExceptionUtil {

    private static final Logger logger = LogManager.getLogger(ExceptionHandlerController.class);

    public static String errorInfo(Exception e) {
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;
        try {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            printWriter.flush();
            stringWriter.flush();
        } finally {
            if (stringWriter != null) {
                try {
                    stringWriter.close();
                } catch (IOException e1) {
                    logger.info(e1);
                }
            }
            if (printWriter != null) {
                printWriter.close();
            }
        }
        return stringWriter.toString();
    }
}
