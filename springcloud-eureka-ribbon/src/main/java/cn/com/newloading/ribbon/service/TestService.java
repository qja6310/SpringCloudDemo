package cn.com.newloading.ribbon.service;

import cn.com.newloading.common.service.BaseService;
import cn.com.newloading.common.utils.http.WebUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestService extends BaseService {

    @Autowired
    private WebUtil webUtil;

    public String test1(Map<String,String> params){
        String url = "http://localhost:8701/client/test";
        return (String) webUtil.doRequest(url,params);
    }

    public Map test2(Map<String,String> params){
        return (Map) webUtil.doRequest(EUREKA_CLIENT,"client/test",params,Map.class);
    }
    public Map test3(Map<String,String> params){
        return (Map) webUtil.doReq(EUREKA_CLIENT,"client/test",params,Map.class);
    }
}
