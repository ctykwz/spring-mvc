package com.ctyk.mobile.template.controller.swagger;

import com.ctyk.mobile.template.module.filter.SwaggerUIFilter;
import com.google.common.base.Strings;
import com.ctyk.mobile.template.config.PropertiesConfig;
import com.ctyk.mobile.template.model.response.CommonResponse;
import com.ctyk.mobile.template.utils.RandomValidateCodeUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * swagger-ui登陆页面
 * Created by wei.yang on 2016/12/19.
 */
@Controller
public class SwaggerLoginController {

    private String username;

    private String password;

    private boolean local;

    @Autowired
    public SwaggerLoginController(PropertiesConfig propertiesConfig) {
        Assert.notNull(propertiesConfig, "配置文件为空");
        this.username = propertiesConfig.getSwaggerLoginName();
        this.password = propertiesConfig.getSwaggerLoginPassword();
        this.local = "admin".equals(password);
    }

    /**
     * 展示登陆页面
     *
     * @return 页面
     */
    @RequestMapping(value = "swagger/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        return new ModelAndView("swagger");
    }

    /**
     * 请求验证码，并将验证码写到session中
     */
    @RequestMapping(value = "swagger/verify/code", method = RequestMethod.GET)
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @ResponseBody
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
        randomValidateCode.getRandCode(request, response);
    }

    /**
     * 登陆验证
     */
    @RequestMapping(value = "swagger/login/check", method = RequestMethod.POST)
    @ApiOperation(value = "登陆验证", notes = "登陆验证", response = CommonResponse.class)
    @ResponseBody
    public CommonResponse loginCheck(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("verifyCode") String verifyCode,
                                     HttpSession session) {
        String currentVerifyCode = (String) session.getAttribute(RandomValidateCodeUtil.RANDOM_CODE_KEY);
        if (local) {
            session.setAttribute(SwaggerUIFilter.SWAGGER_LOGIN_FLAG, username);
            return CommonResponse.ok();
        }

        if (Strings.isNullOrEmpty(currentVerifyCode)) {
            return CommonResponse.error();
        }
        if (this.username.equals(username)
                && this.password.equals(password)
                && currentVerifyCode.equals(verifyCode.toLowerCase())) {
            session.setAttribute(SwaggerUIFilter.SWAGGER_LOGIN_FLAG, username);
            return CommonResponse.ok();
        }
        return CommonResponse.error();
    }
}
