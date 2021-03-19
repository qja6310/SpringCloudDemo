package cn.com.newloading.ribbon.controller;

import cn.com.newloading.ribbon.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ribbon")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 调用微服务方式第一种方式，直接调用微服务,不经过注册中心列表
     * @param s
     * @return
     */
    @RequestMapping("/test1")
    @ResponseBody
    public String test1(String s){
        Map<String,String> param = new HashMap<>();
        param.put("s",s);
        String res = testService.test1(param);
        return res;
    }

    @Autowired
    private TestService testService;

    @RequestMapping("/test2")
    @ResponseBody
    public String test2(String s){
        Map<String,String> param = new HashMap<>();
        param.put("s",s);
        Map res = testService.test2(param);
        return res.get("user").toString();
    }

    @RequestMapping("/test3")
    @ResponseBody
    public String test3(String s){
        Map<String,String> param = new HashMap<>();
        param.put("s",s);
        Map map = testService.test3(param);
        return map.get("user").toString();
    }
}
