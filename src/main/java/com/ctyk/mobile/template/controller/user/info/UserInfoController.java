package com.ctyk.mobile.template.controller.user.info;

import com.ctyk.mobile.template.services.UserInfoService;
import com.ctyk.mobile.template.model.response.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Response<Integer> obtainView() {
        return new Response<Integer>().setData(userInfoService.obtainCount());
    }
}
