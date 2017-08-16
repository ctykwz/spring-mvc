package com.ctyk.mobile.template.module.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * freemarker
 * Created by wei.yang on 2017/8/16.
 */
public class MFreeMarkerView extends FreeMarkerView {

    private static final String BASE_URL = "baseUrl";

    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        String baseUrl = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort() + ""
                + request.getContextPath();
        model.put(BASE_URL, baseUrl);
        super.exposeHelpers(model, request);
    }
}
