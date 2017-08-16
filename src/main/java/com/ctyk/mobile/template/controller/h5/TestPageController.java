package com.ctyk.mobile.template.controller.h5;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试
 * Created by wei.yang on 2017/8/16.
 */
@Controller
@Api(value = "freemarker 试用")
public class TestPageController {

    @RequestMapping(value = "test/page",method = RequestMethod.GET)
    public ModelAndView testPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.getModelMap().addAttribute("username","wei");
        modelAndView.getModelMap().addAttribute("password","111111");
        modelAndView.setViewName("test");
        return modelAndView;
    }
}
