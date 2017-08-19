package com.ctyk.mobile.template.module.authorization;

import com.alibaba.fastjson.JSONObject;
import com.ctyk.mobile.template.utils.ExceptionUtil;
import com.google.common.base.Strings;
import com.google.common.io.Resources;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 文件解析及具体规则制定：
 * 按照目前所写，需要在配置文件中配置需要添加授权的api，不同环境的配置可以不一样，在本地，可添加到名单中
 * Created by wei.yang on 2017/8/19.
 */
class FilterWhiteConfig {

    private static final String AUTHORIZATION_ERROR_DESCRIPTION = "your have no authorization to access this api.";

    private static final Logger logger = Logger.getLogger(FilterWhiteConfig.class);

    private static final String CONTENT_TYPE = "application/json;charset=utf-8";

    private static final String FILTER_WHITE_LIST = "filterWhiteList";

    private static final int AUTHORIZATION_ERROR_STATUS = 10086;

    private static final String COMPANY_ID = "ctyk";

    private static final String DEFAULT_CHARSET = "UTF-8";

    private Set<String> filterWhiterList;

    FilterWhiteConfig(FilterConfig filterConfig) {
        String initParam = filterConfig.getInitParameter(FILTER_WHITE_LIST);
        logger.info(String.format("loading config file:\t%s", initParam));
        URL filterWhiteListFile = Resources.getResource(initParam);
        initFilterList(filterWhiteListFile);
    }

    /**
     * 过滤url
     *
     * @param request  请求
     * @param response 响应
     * @return 是否允许通过filter
     */
    boolean doFilter(HttpServletRequest request, HttpServletResponse response) {
        String requestServletPath = request.getServletPath();
        logger.info("request servlet path:\t" + requestServletPath);
        if (Strings.isNullOrEmpty(requestServletPath)) {
            return false;
        }
        if (requestServletPath.startsWith("swagger")
                || requestServletPath.startsWith("druid")
                || filterWhiterList.contains(requestServletPath)) {
            return true;
        }
        String companyId = request.getHeader("companyId");
        logger.info("companyId:\t" + companyId);
        //具体过滤规则需要根据实际来做
        if (!Strings.isNullOrEmpty(companyId)) {
            if (companyId.equalsIgnoreCase(COMPANY_ID)) {
                return true;
            }
        }
        return errorResponse(response, AUTHORIZATION_ERROR_STATUS, AUTHORIZATION_ERROR_DESCRIPTION);
    }

    /**
     * 解析xml文件
     *
     * @param filterWhiterListFile 白名单文件
     */
    private void initFilterList(URL filterWhiterListFile) {
        filterWhiterList = new HashSet<>();
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(filterWhiterListFile);
        } catch (DocumentException e) {
            return;
        }
        Iterator iterator = document.getRootElement().elementIterator();
        logger.info("document name :\t" + document.getText());
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            Iterator iterator1 = element.elementIterator();
            logger.info("element name:\t" + element.getName());
            while (iterator1.hasNext()) {
                Element element1 = (Element) iterator1.next();
                String url = element1.getTextTrim();
                filterWhiterList.add(url);
                logger.info("tag name:\t" + element1.getName() + "\n" + "url:\t" + url);
            }
        }
    }

    /**
     * 返回错误信息
     *
     * @param response    响应
     * @param status      状态码
     * @param description 描述
     * @return 是否通过filter chain
     */
    private boolean errorResponse(HttpServletResponse response, int status, String description) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("description", description);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(DEFAULT_CHARSET);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.append(jsonObject.toJSONString());
        } catch (IOException e) {
            ExceptionUtil.errorInfo(e);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
        return false;
    }
}
