package com.ctyk.mobile.template.module.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 拦截swagger-ui页面，需要登录
 * Created by wei.yang on 2017/3/8.
 */
public class SwaggerUIFilter implements Filter {

    public static final String SWAGGER_LOGIN_FLAG = "SWAGGER_LOGIN_OK";

    private static final Logger logger = LogManager.getLogger(SwaggerUIFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Filter configuration init .");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        Object object = session.getAttribute(SWAGGER_LOGIN_FLAG);
        if (object == null) {
            // 没有登陆
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "swagger/login");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("Filter destroyed .");
    }
}
