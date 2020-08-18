package com.jf.service.jixin;

import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.StringUtil;
import com.jf.service.jixin.util.SecurityUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 即信云通讯 - 相关接口
 *
 * @author luoyb
 * Created on 2020/6/17
 */
@Service
public class JiXinService {

    private static Logger logger = LoggerFactory.getLogger(JiXinService.class);

    @Value("${jx.server.url}")
    private String serverUrl; //玄武服务端接入地址
    @Value("${jx.login.token.path}")
    private String loginTokenPath; //一键登录获取手机号码path
    @Value("${jx.account.sid}")
    private String accountSid;
    @Value("${jx.auth.token}")
    private String authToken;
    @Value("${jx.version}")
    private String version;

    @Value("${jx.ios.app.id}")
    private String iosAppId;
    @Value("${jx.ios.app.key}")
    private String iosAppKey;
    @Value("${jx.ios.app.secret}")
    private String iosAppSecret;
    @Value("${jx.android.app.id}")
    private String androidAppId;
    @Value("${jx.android.app.key}")
    private String androidAppKey;
    @Value("${jx.android.app.secret}")
    private String androidAppSecret;

    /**
     * 一键登录获取手机号
     *
     * @param token    需要解析的凭证值
     * @param authCode 电信校验码，在电信获取号码时验证
     * @param carrier  运营商（移动：mobile， 联通：unicom，电信：telecom）
     * @param system   起调系统：IOS、Android
     */
    public String getPhoneNumber(String token, String authCode, String carrier, String system) {
        JiXinAppConfig appConfig = getAppConfig(system);
        String msgId = CommonUtil.getRandomStringByLength(32).toLowerCase(); // 标识请求的随机数即可(1-36位)
        String systemTime = SecurityUtil.generateFullTimestamp(new Date()); //时间戳：精确到毫秒，共17位，格式：20121227180001165
        String strictCheck = "0"; //暂时填写"0"，填写“1”时，将对服务器IP白名单进行强校验（后续将强制要求IP强校验）
        String expandParams = ""; //扩展参数
        String sign = SecurityUtil.generateLoginTokenSign(appConfig.getAppId(), version, msgId, systemTime, strictCheck, token, appConfig.getAppSecret());

        Map<String, String> params = new LinkedHashMap<>(8);
        params.put("msgid", msgId);
        params.put("systemtime", systemTime);
        params.put("strictcheck", strictCheck);
        params.put("appid", appConfig.getAppId());
        params.put("expandparams", expandParams);
        params.put("token", token);
        params.put("authCode", authCode);
        params.put("sign", sign);
        params.put("carrier", carrier);
        JSONObject requestContent = JSONObject.fromObject(params);
        try {
            logger.debug("即信-获取手机号接口请求参数：{}", requestContent.toString());
            String responseContent = execute(serverUrl + loginTokenPath, requestContent.toString());
            logger.debug("即信-获取手机号接口响应参数：{}", responseContent);
            JSONObject response = JSONObject.fromObject(responseContent);
            if (response.has("code") && response.getString("code").equals("0")) {
                return response.getJSONObject("data").getString("msisdn");
            }
            logger.warn("即信-获取手机号接口调用异常，响应结果：{}", responseContent);
        } catch (Exception e) {
            logger.error("即信-获取手机号接口调用失败:{}", e.getMessage(), e);
        }
        return null;
    }

    private void initConfigForTest() {
        serverUrl = "https://www.139130.com/ytx-api/v1.0.0"; //玄武服务端接入地址
        loginTokenPath = "/loginToken/validate"; //一键登录获取手机号码path
        accountSid = "9a840d10b26cb9a2b7ed0981c21dc75f";
        authToken = "5649f8a92b18cafb006ca078c90e1ea8";
        version = "v1.0.0";
        iosAppId = "5b3124337943f4041e05fe29c31db80e";
        iosAppKey = "8d46ac75239421abe5858fdc31462ab2";
        iosAppSecret = "3ef3449d445fcde1f890282a177b68cc";
    }

    /**
     * @param system 起调系统：IOS、Android
     */
    private JiXinAppConfig getAppConfig(String system) {
//        initConfigForTest();

        JiXinAppConfig config = new JiXinAppConfig();
        if ("IOS".equalsIgnoreCase(system)) {
            config.setAppId(iosAppId);
            config.setAppKey(iosAppKey);
            config.setAppSecret(iosAppSecret);
        } else if ("Android".equalsIgnoreCase(system)) {
            config.setAppId(androidAppId);
            config.setAppKey(androidAppKey);
            config.setAppSecret(androidAppSecret);
        }else{
            logger.warn("未知调用系统：{}", system);
            throw new BizException("参数异常");
        }
        return config;
    }

    /**
     * 发送HTTP请求
     */
    private String execute(String url, String requestContent) throws Exception {
        HttpURLConnection conn = buildPostConnection(url, accountSid, authToken);
        try {
            conn.connect();
            try (OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream())) {
                out.write(requestContent);
            }
            StringBuilder response = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                response.append(tmp);
            }
            return response.toString();
        } finally {
            conn.disconnect();
        }
    }

    /**
     * 创建HttpURLConnection </br>
     * <pre>
     * 1. url追加sig={sigParameter}参数
     * 2. 请求头添加：
     *      Content-Type：application/json;charset=utf-8
     *      Accept：application/json;charset=utf-8
     *      Authorization：Base64(accountSid+:+timestamp)
     * </pre>
     */
    private HttpURLConnection buildPostConnection(String url, String accountSid, String authToken) throws IOException {
        String timestamp = SecurityUtil.generateTimeStamp(new Date());
        url = generateURL(url, accountSid, authToken, timestamp);
        logger.debug("即信-请求URL：{}", url);
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);

        //POST方式
        conn.setRequestMethod("POST");
        //请求和响应报文格式指定为application/json;charset=utf-8
        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        conn.setRequestProperty("Accept", "application/json;charset=utf-8");
        //请求头添加Authorization
        String authorization = SecurityUtil.generateAuthorization(accountSid, timestamp);
        conn.setRequestProperty("Authorization", authorization);
        return conn;
    }

    /**
     * 生成url:格式为http://{ip}:{port}/{domain}/{path}?sig={sigParameter}
     * path: 服务地址
     * sig: 签名信息
     */
    private String generateURL(String url, String accountSid, String authToken, String timestamp) {
        return StringUtil.buildMsg("{}?sig={}", url, SecurityUtil.generateSigParameter(accountSid, authToken, timestamp));
    }
}
