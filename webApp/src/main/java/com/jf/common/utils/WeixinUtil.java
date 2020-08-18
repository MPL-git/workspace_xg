package com.jf.common.utils;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.jf.entity.WeixinAccessToken;
import com.jf.entity.WeixinAccessTokenExample;
import com.jf.entity.WeixinConfig;
import com.jf.entity.WeixinConfigExample;
import com.jf.entity.WeixinJsapiTicket;
import com.jf.entity.WeixinJsapiTicketExample;
import com.jf.service.WeixinAccessTokenService;
import com.jf.service.WeixinConfigService;
import com.jf.service.WeixinJsapiTicketService;

import sun.misc.BASE64Decoder;

@Component
@Lazy(false)
public class WeixinUtil {
    private static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);
	
	private static WeixinConfigService weixinConfigService;
	private static WeixinAccessTokenService weixinAccessTokenService;
	private static WeixinJsapiTicketService weixinJsapiTicketService;
	
	public static String appId;
	public static String appSecret;
	public static String mchtId;
	public static String key;
	
	public static String xcxAppId;
	public static String xcxAppSecret;
	
	
	/**
	 * 获取微信accessToken
	 */
		public static String getAccessToken() {
			WeixinAccessTokenExample weixinAccessTokenExample=new WeixinAccessTokenExample();
			weixinAccessTokenExample.createCriteria().andDelFlagEqualTo("0").andAppIdEqualTo(appId);
			List<WeixinAccessToken> weixinAccessTokens=weixinAccessTokenService.selectByExample(weixinAccessTokenExample);
			if(weixinAccessTokens==null||weixinAccessTokens.size()==0){
				return null;
			}else{
				return weixinAccessTokens.get(0).getAccessToken();
			}
		}
		
	/**
	 * 获取微信JsapiTicket
	 * @return
	 */
		public static String getJsapiTicket(){
			 
			WeixinJsapiTicketExample weixinJsapiTicketExample=new WeixinJsapiTicketExample();
			weixinJsapiTicketExample.createCriteria().andDelFlagEqualTo("0").andAppIdEqualTo(appId);
			List<WeixinJsapiTicket> weixinJsapiTickets=weixinJsapiTicketService.selectByExample(weixinJsapiTicketExample);
			if(weixinJsapiTickets==null||weixinJsapiTickets.size()==0){
				return null;
			}else{
				return weixinJsapiTickets.get(0).getJsapiTicket();
			}
			
		}
		
		
		public static String xcxAesDecrypt(String encryptedData,String key,String iv){
			BASE64Decoder base64Decoder=new BASE64Decoder();
			byte[] cryptedBytes=Base64.decodeBase64(encryptedData);
			byte[] keyBytes=Base64.decodeBase64(key);
			byte[] ivBytes=Base64.decodeBase64(iv);
			String result=null;
			try {
				result= new String(AESUtil.decryptAES(cryptedBytes, keyBytes, ivBytes, "utf-8"),"utf-8");
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error("微信小程序信息解密失败");
			}
			return result;
		}
	

	@Resource
	public synchronized void setWeixinConfigService(WeixinConfigService weixinConfigService) {
		WeixinUtil.weixinConfigService = weixinConfigService;
		WeixinConfigExample weixinConfigExample=new WeixinConfigExample();
		weixinConfigExample.createCriteria().andDelFlagEqualTo("0");
		List<WeixinConfig> weixinConfigs=weixinConfigService.selectByExample(weixinConfigExample);
		appId=weixinConfigs.get(0).getAppId();
		appSecret=weixinConfigs.get(0).getAppSecret();
		mchtId=weixinConfigs.get(0).getMchtId();
		key=weixinConfigs.get(0).getSignKey();
		xcxAppId=weixinConfigs.get(0).getXcxAppId();
		xcxAppSecret=weixinConfigs.get(0).getXcxAppSecret();
	}
	
	@Resource
	public synchronized void setWeixinAccessTokenService(WeixinAccessTokenService weixinAccessTokenService) {
		WeixinUtil.weixinAccessTokenService = weixinAccessTokenService;
	}

	@Resource
	public synchronized void setWeixinJsapiTicketService(WeixinJsapiTicketService weixinJsapiTicketService) {
		WeixinUtil.weixinJsapiTicketService = weixinJsapiTicketService;
	}
	
	/**
	 * 获取微信小程序accessToken
	 */
	public static String getXcxAccessToken() {
		WeixinAccessTokenExample weixinAccessTokenExample = new WeixinAccessTokenExample();
		weixinAccessTokenExample.createCriteria().andDelFlagEqualTo("0").andAppIdEqualTo(xcxAppId);
		List<WeixinAccessToken> weixinAccessTokens = weixinAccessTokenService.selectByExample(weixinAccessTokenExample);
		if (weixinAccessTokens == null || weixinAccessTokens.size() == 0) {
			return null;
		} else {
			return weixinAccessTokens.get(0).getAccessToken();
		}
	}
	
}