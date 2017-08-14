package com.ctyk.mobile.template.exception;

import com.alibaba.fastjson.JSONObject;
import com.ctyk.mobile.template.utils.ExceptionUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


/**
 * 全局异常处理
 * Created by wei.yang on 2017/3/8.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = Logger.getLogger(ExceptionHandlerController.class);

    private static JSONObject exception1001;
    private static JSONObject exception1;

    static {
        exception1001 = new JSONObject();
        exception1001.put("status", 1001);
        exception1001.put("description", "Unexpected error.");
    }

    static {
        exception1 = new JSONObject();
        exception1.put("status", 1);
        exception1.put("description", "Data format error.");
    }

    /**
     * 未处理异常
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView processException(Exception e) {
        logger.info(ExceptionUtil.errorInfo(e));
        return new ModelAndView(new MappingJackson2JsonView(), exception1001);
    }

    /**
     * 数据格式异常
     */
    @ExceptionHandler(DataFormatException.class)
    public ModelAndView processDataFormatException(Exception e) {
        if (e != null) {
            exception1.put("description", e.getMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), exception1);
    }
}
