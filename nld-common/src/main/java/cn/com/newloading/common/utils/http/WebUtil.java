package cn.com.newloading.common.utils.http;

import cn.com.newloading.common.config.RestTemplateConfig;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class WebUtil {

    @Autowired
    private LoadBalancerClient client;
    @Autowired
    private RestTemplateConfig restTemplateConfig;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String HTTP = "http://";
    private final String F = "=";
    private final String W = "?";
    private final String H = "&";
    private final String M = ":";
    private final String X = "/";

    /**
     * 调用服务方式1
     * 没有通过eureka来调用，直接路径请求
     * @param url
     * @param paramMap
     * @return
     */
    public String doRequest(String url,Map<String,String> paramMap){
        StringBuffer buffer = new StringBuffer();
        buffer.append(url);
        buffer.append(getParams(paramMap));
        logger.info("======发起请求======");
        logger.info(buffer.toString());
        String response = new RestTemplate().getForObject(buffer.toString(),String.class);
        logger.info("======返回结果======");
        logger.info(response);
        return response;
    }

    /**
     * 调用微服务方式2
     * 通过服务名来调用，根据算法选取某个服务，并访问某个服务的网络位置
     * @param serverName
     * @param addr
     * @param paramMap
     * @param responseType
     * @param <T>
     * @return
     */
    public <T> T doRequest(String serverName, String addr, Map<String,String> paramMap,Class<T> responseType){
        ServiceInstance serviceInstance = client.choose(serverName);
        logger.info("serviceInstance.getPort()");
        StringBuffer buffer = new StringBuffer();
        buffer.append(HTTP).append(serviceInstance.getHost()).append(M).append(serviceInstance.getPort());
        buffer.append(addr);
        buffer.append(getParams(paramMap));
        logger.info("======发起请求======");
        logger.info(buffer.toString());
        String response = new RestTemplate().getForObject(buffer.toString(),String.class);
        logger.info("======返回结果======");
        logger.info(response);
        JSONObject resJson = JSONObject.parseObject(response);
        JSONObject responseObj = (JSONObject) resJson.getJSONObject("data");
        return responseObj.getObject("responseObj",responseType);
    }

    /**
     * 微服务调用方式3
     * 配置自动注入对象
     * @param serverName
     * @param addr
     * @param paramMap
     * @param responseType
     * @param <T>
     * @return
     */
    public <T> T doReq(String serverName, String addr, Map<String,String> paramMap,Class<T> responseType){
        StringBuffer buffer = new StringBuffer();
        if(addr.indexOf("/") == 0){
            buffer.append(HTTP).append(serverName).append(addr);
        }else {
            buffer.append(HTTP).append(serverName).append(X).append(addr);
        }
        buffer.append(getParams(paramMap));
        logger.info("======发起请求======");
        logger.info(buffer.toString());
        String response = restTemplateConfig.getRestTemplate().getForObject(buffer.toString(),String.class);
        logger.info("======返回结果======");
        logger.info(response);
        JSONObject resJson = JSONObject.parseObject(response);
        JSONObject responseObj = (JSONObject) resJson.getJSONObject("data");
        return responseObj.getObject("responseObj",responseType);
    }

    /**
     * 请求参数字符串拼接
     * @param paramMap
     * @return
     */
    private String getParams(Map<String,String> paramMap){
        StringBuffer paramBuffer = new StringBuffer();
        if(paramMap != null && paramMap.size() > 0){
            paramBuffer.append(W);
            for (String name : paramMap.keySet()){
                paramBuffer.append(name).append(F).append(paramMap.get(name)).append(H);
            }
        }else{
            return "";
        }
        return paramBuffer.toString();
    }
}
