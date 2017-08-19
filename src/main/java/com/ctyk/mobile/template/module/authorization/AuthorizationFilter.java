package com.ctyk.mobile.template.module.authorization;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接口授权
 * Created by wei.yang on 2017/8/19.
 */
public class AuthorizationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AuthorizationFilter.class);

    private FilterWhiteConfig filterWhiteConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init filter :\t" + filterConfig.getFilterName());
        filterWhiteConfig = new FilterWhiteConfig(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        boolean pass = filterWhiteConfig.doFilter(httpServletRequest, httpServletResponse);
        if (!pass) {
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("destroy filter");
    }
}
