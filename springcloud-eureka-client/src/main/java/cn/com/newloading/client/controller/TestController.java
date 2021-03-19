package cn.com.newloading.client.controller;

import cn.com.newloading.client.model.User;
import cn.com.newloading.common.utils.date.DateUtil;
import cn.com.newloading.common.utils.http.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest request){
        String s = request.getParameter("s");
        logger.info("入参【"+s+"】");
        logger.info(DateUtil.dateToString(new Date(),"yyyy-MM-dd"));
        String res = "入参【"+s+"】";
        Map<String,Object> map = new HashMap<>();
        map.put("res",res);
        User user = new User("1","张三");
        map.put("user",user);
        return ResponseUtil.success(map);
    }
}
