package com.jf.common.utils;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

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
	 * 根据appId和secret获取accessToken
	 * 
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	public static String getAccessToken() {
		WeixinAccessToken weixinAccessToken;
		WeixinAccessTokenExample weixinAccessTokenExample = new WeixinAccessTokenExample();
		weixinAccessTokenExample.createCriteria().andDelFlagEqualTo("0").andAppIdEqualTo(appId);
		List<WeixinAccessToken> weixinAccessTokens = weixinAccessTokenService.selectByExample(weixinAccessTokenExample);
		if (weixinAccessTokens == null || weixinAccessTokens.size() == 0) {
			weixinAccessToken = new WeixinAccessToken();
			weixinAccessToken.setAppId(appId);
			weixinAccessToken.setCreateDate(new Date());
			weixinAccessTokenService.insertSelective(weixinAccessToken);
		} else {
			weixinAccessToken = weixinAccessTokens.get(0);
		}

		if (weixinAccessToken.getUpdateDate() == null || (new Date().getTime() / 1000) > (weixinAccessToken.getUpdateDate().getTime() / 1000 + 300)) {// 超过5分钟就更新，token的过期时间为两小时
			String result = HttpUtil.sendGetRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret);
			JSONObject resultJson = JSONObject.fromObject(result);
			if (resultJson.has("access_token")) {
				weixinAccessToken.setAccessToken(resultJson.getString("access_token"));
				weixinAccessToken.setUpdateDate(new Date());
				weixinAccessTokenService.updateByPrimaryKeySelective(weixinAccessToken);
			} else {
				logger.error("获取微信AccessToken出错：" + resultJson);
			}
		}

		return weixinAccessToken.getAccessToken();
	}

	public static String getJsapiTicket() {

		WeixinJsapiTicket weixinJsapiTicket;
		WeixinJsapiTicketExample weixinJsapiTicketExample = new WeixinJsapiTicketExample();
		weixinJsapiTicketExample.createCriteria().andDelFlagEqualTo("0").andAppIdEqualTo(appId);
		List<WeixinJsapiTicket> weixinJsapiTickets = weixinJsapiTicketService.selectByExample(weixinJsapiTicketExample);
		if (weixinJsapiTickets == null || weixinJsapiTickets.size() == 0) {
			weixinJsapiTicket = new WeixinJsapiTicket();
			weixinJsapiTicket.setAppId(appId);
			weixinJsapiTicket.setCreateDate(new Date());
			weixinJsapiTicketService.insertSelective(weixinJsapiTicket);
		} else {
			weixinJsapiTicket = weixinJsapiTickets.get(0);
		}

		if (weixinJsapiTicket.getUpdateDate() == null || (new Date().getTime() / 1000) > (weixinJsapiTicket.getUpdateDate().getTime() / 1000 + 300)) {// 超过5分钟就更新,
																																						// ticket的过期时间为两小时
			String result = HttpUtil.sendGetRequest("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getAccessToken() + "&type=jsapi");
			JSONObject resultJson = JSONObject.fromObject(result);

			if (resultJson.has("errcode") && "0".equals(resultJson.getString("errcode"))) {
				weixinJsapiTicket.setJsapiTicket(resultJson.getString("ticket"));
				weixinJsapiTicket.setUpdateDate(new Date());
				weixinJsapiTicketService.updateByPrimaryKeySelective(weixinJsapiTicket);
			} else {
				logger.error("获取微信JsapiTicket出错：" + resultJson);
			}
		}

		return weixinJsapiTicket.getJsapiTicket();
	}

	/**
	 * 根据小程序appId和secret获取accessToken
	 * 
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	public static String getXcxAccessToken() {
		WeixinAccessToken weixinAccessToken;
		WeixinAccessTokenExample weixinAccessTokenExample = new WeixinAccessTokenExample();
		weixinAccessTokenExample.createCriteria().andDelFlagEqualTo("0").andAppIdEqualTo(xcxAppId);
		List<WeixinAccessToken> weixinAccessTokens = weixinAccessTokenService.selectByExample(weixinAccessTokenExample);
		if (weixinAccessTokens == null || weixinAccessTokens.size() == 0) {
			weixinAccessToken = new WeixinAccessToken();
			weixinAccessToken.setAppId(xcxAppId);
			weixinAccessToken.setCreateDate(new Date());
			weixinAccessTokenService.insertSelective(weixinAccessToken);
		} else {
			weixinAccessToken = weixinAccessTokens.get(0);
		}

		if (weixinAccessToken.getUpdateDate() == null || (new Date().getTime() / 1000) > (weixinAccessToken.getUpdateDate().getTime() / 1000 + 300)) {// 超过5分钟就更新，token的过期时间为两小时
			String result = HttpUtil.sendGetRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + xcxAppId + "&secret=" + xcxAppSecret);
			JSONObject resultJson = JSONObject.fromObject(result);
			if (resultJson.has("access_token")) {
				weixinAccessToken.setAccessToken(resultJson.getString("access_token"));
				weixinAccessToken.setUpdateDate(new Date());
				weixinAccessTokenService.updateByPrimaryKeySelective(weixinAccessToken);
			} else {
				logger.error("获取微信AccessToken出错：" + resultJson);
			}
		}

		return weixinAccessToken.getAccessToken();
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