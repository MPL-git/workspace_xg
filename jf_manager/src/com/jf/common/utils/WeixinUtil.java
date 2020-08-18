package com.jf.common.utils;

import java.util.List;

import javax.annotation.Resource;

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

@Component
@Lazy(false)
public class WeixinUtil {

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
		WeixinAccessTokenExample weixinAccessTokenExample = new WeixinAccessTokenExample();
		weixinAccessTokenExample.createCriteria().andDelFlagEqualTo("0").andAppIdEqualTo(appId);
		List<WeixinAccessToken> weixinAccessTokens = weixinAccessTokenService.selectByExample(weixinAccessTokenExample);
		if (weixinAccessTokens == null || weixinAccessTokens.size() == 0) {
			return null;
		} else {
			return weixinAccessTokens.get(0).getAccessToken();
		}
	}

	/**
	 * 获取微信JsapiTicket
	 * 
	 * @return
	 */
	public static String getJsapiTicket() {

		WeixinJsapiTicketExample weixinJsapiTicketExample = new WeixinJsapiTicketExample();
		weixinJsapiTicketExample.createCriteria().andDelFlagEqualTo("0").andAppIdEqualTo(appId);
		List<WeixinJsapiTicket> weixinJsapiTickets = weixinJsapiTicketService.selectByExample(weixinJsapiTicketExample);
		if (weixinJsapiTickets == null || weixinJsapiTickets.size() == 0) {
			return null;
		} else {
			return weixinJsapiTickets.get(0).getJsapiTicket();
		}

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

	@Resource
	public synchronized void setWeixinConfigService(WeixinConfigService weixinConfigService) {
		WeixinUtil.weixinConfigService = weixinConfigService;
		WeixinConfigExample weixinConfigExample = new WeixinConfigExample();
		weixinConfigExample.createCriteria().andDelFlagEqualTo("0");
		List<WeixinConfig> weixinConfigs = weixinConfigService.selectByExample(weixinConfigExample);
		appId = weixinConfigs.get(0).getAppId();
		appSecret = weixinConfigs.get(0).getAppSecret();
		mchtId = weixinConfigs.get(0).getMchtId();
		key = weixinConfigs.get(0).getSignKey();
		xcxAppId = weixinConfigs.get(0).getXcxAppId();
		xcxAppSecret = weixinConfigs.get(0).getXcxAppSecret();
	}

	@Resource
	public synchronized void setWeixinAccessTokenService(WeixinAccessTokenService weixinAccessTokenService) {
		WeixinUtil.weixinAccessTokenService = weixinAccessTokenService;
	}

	@Resource
	public synchronized void setWeixinJsapiTicketService(WeixinJsapiTicketService weixinJsapiTicketService) {
		WeixinUtil.weixinJsapiTicketService = weixinJsapiTicketService;
	}

}