package com.jf.common.ext.skd;

import com.alibaba.fastjson.JSONObject;
import com.jf.common.ext.util.HttpKit;
import com.jf.common.ext.util.JsonKit;
import com.jf.common.ext.util.StrKit;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TrackingioClient {

	private static Logger logger = Logger.getLogger(TrackingioClient.class);

	public static String METHOD_GET = "get";
	public static String METHOD_POST = "post";

    private String hostName="log.trackingio.com";
    private String format="json";
    private String protocol = "http";	//HTTP请求协议类型: "http" / "https"
    private String method = METHOD_POST;		//请求方法
    private Map<String, String> params = new HashMap<String, String>();
    private String postData = null;
	private String apiPath;
    
    /**
     * 执行API调用
     * @return 返回服务器响应内容
     */
    public String invoke(){
        //拼成URL
        String url = protocol + "://" + hostName + apiPath;

		logger.debug("正在进行访问热云接口[" + url + "], 参数：" + JsonKit.toJson(params));

        // 发送请求
        String resp;
        if(method.equals(METHOD_POST)){
        	resp = HttpKit.postJson(url, params, postData, null);
        }else{
        	resp = HttpKit.get(url, params);
        }
        //处理返回的请求包
        return resp;
    }


    public void setApiPath(String apiPath){
        this.apiPath = apiPath;
    }

    public void addParams(String key, String value) {
        params.put(key, value);
    }
    
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setPostData(String postData) {
		this.postData = postData;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

    public static boolean isSucc(String response){
        logger.debug("response:" + response);
        boolean b = false;
        JSONObject jsonObject = JSONObject.parseObject(response);
        if(StrKit.notBlank(jsonObject.getString("status")) && jsonObject.getString("status").equals("0")){
            b = true;
        }else{
            logger.error(response);
        }
        return b;
    }
}
