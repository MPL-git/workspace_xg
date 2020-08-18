package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jf.common.base.BaseService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jf.common.constant.BaseDefine;
import com.jf.common.utils.DateUtil;
import com.jf.dao.WeixinConfigMapper;
import com.jf.entity.WeixinConfig;
import com.jf.entity.WeixinConfigExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月5日 上午11:13:28 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WeixinConfigService extends BaseService<WeixinConfig, WeixinConfigExample> {
	@Autowired
	private WeixinConfigMapper weixinConfigMapper;

	@Autowired
	public void setWeixinConfigMapper(WeixinConfigMapper weixinConfigMapper) {
		this.setDao(weixinConfigMapper);
		this.weixinConfigMapper = weixinConfigMapper;
	}

	/*public String getJsapiTicket(HttpServletRequest request){
		try {
			String accessToken = getAccessToken(code, request)
	        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
	        HttpClient client = new DefaultHttpClient();
	        HttpPost post = new HttpPost(url);
	        HttpResponse res = client.execute(post);
	        if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            HttpEntity entity = res.getEntity();
	            String str = org.apache.http.util.EntityUtils.toString(entity, "utf-8");
	            ObjectMapper mapper=new ObjectMapper();
	            Map<String,Object> jsonOb=mapper.readValue(str, Map.class);
	            jsapiTicket = jsonOb.get("ticket").toString();
	        }
	        return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
    }*/

	public String getAccessToken(String code, HttpServletRequest request){
		String accessToken = "";
		try {
			Date currentDate = new Date();
			Map<String,Object> accessTokenMap = (Map<String, Object>) request.getSession().getAttribute(BaseDefine.ACCESSTOKEN);
			if(accessTokenMap != null){
				accessToken = (String) accessTokenMap.get("accessToken");
				Date expiresTime = (Date) accessTokenMap.get("expiresTime");
				if(DateUtil.addSecond(expiresTime, 7000).before(currentDate)){
					//accessToken过期，重新拉取
					String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx6bc48834aedc7f7d&secret=af7efb56791cae1658d216a56865368f&code=" + code + "&grant_type=authorization_code";
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url);
					HttpResponse res = client.execute(post);
					if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						HttpEntity entity = res.getEntity();
						String str = org.apache.http.util.EntityUtils.toString(entity, "utf-8");
						ObjectMapper mapper=new ObjectMapper();
						Map<String,Object> jsonOb=mapper.readValue(str, Map.class);
						request.getSession().setAttribute(BaseDefine.OPENID, jsonOb.get("openid"));
						Map<String,Object> map = new HashMap<>();
						map.put("accessToken", jsonOb.get("access_token"));
						map.put("expiresTime", currentDate);
						request.getSession().setAttribute(BaseDefine.ACCESSTOKEN, map);
						accessToken = (String) jsonOb.get("access_token");
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
    }
	
}
