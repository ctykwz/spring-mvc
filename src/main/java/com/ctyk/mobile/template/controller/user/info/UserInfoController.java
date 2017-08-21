package com.ctyk.mobile.template.controller.user.info;

import com.ctyk.mobile.template.model.response.Response;
import com.ctyk.mobile.template.services.UserInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * null
 * Created by wei.yang on 2017/5/18.
 */
@Controller
public class UserInfoController {

    private UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
        Assert.notNull(this.userInfoService, "userInfoService can not be null");
    }

    /**
     * 获取用户数量
     *
     * @return 用户数量
     */
    @RequestMapping(value = "test", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "返回用户数量")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "正常情况", response = Response.class)
    })
    public Response<Integer> obtainUserCount() {
        return new Response<Integer>().setData(userInfoService.obtainCount());
    }

    /**
     * 获取用户数量
     */
    @RequestMapping(value = "test/redirect", method = RequestMethod.POST, produces = "application/json",
            consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "返回用户数量")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "正常情况", response = Response.class)
    })
    public void obtainViewRedirect() throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.reset();
        response.sendRedirect("https://www.baidu.com/");
    }
}
