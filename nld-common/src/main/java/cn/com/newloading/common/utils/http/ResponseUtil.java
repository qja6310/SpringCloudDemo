package cn.com.newloading.common.utils.http;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseUtil {

    private static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    private static final String SUC_CODE = "200";
    private static final String SUC_MSG = "成功";
    private static final String ERR_CODE = "500";
    private static final String ERR_MSG = "失败";

    /**
     * 成功返回数据
     * @param obj
     * @return
     */
    public static String success(Object obj){
        JSONObject resJson = new JSONObject();
        JSONObject dataJson = new JSONObject();
        dataJson.put("responseObj",obj);
        resJson = setSuccessOrFail(resJson,SUC_CODE,SUC_MSG);
        resJson.put("data",dataJson);
        String content = JSONObject.toJSONString(resJson);
        logger.info("返回数据：" + content);
        return content;
    }

    /**
     * 报错是返回
     * @param e
     * @param code  返回错误码不可为200
     * @param msg
     * @return
     */
    public static String error(Exception e,String code,String msg){
        JSONObject resJson = new JSONObject();
        resJson = setSuccessOrFail(resJson,code,msg);
        if(e != null){
            JSONObject dataJson = new JSONObject();
            dataJson.put("exception",e.toString());
            resJson.put("data",dataJson);
        }
        String content = JSONObject.toJSONString(resJson);
        logger.info("返回数据：" + content);
        return content;
    }

    /**
     * 返回成功或者失败的标识
     * @param jsonObject
     * @param code
     * @param msg
     * @return
     */
    private static JSONObject setSuccessOrFail(JSONObject jsonObject,String code,String msg){
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        return jsonObject;
    }
}
