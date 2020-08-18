package com.jf.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.RandCharsUtils;
import com.jf.common.utils.WeixinUtil;
import com.jf.dao.WeixinJsapiTicketMapper;
import com.jf.entity.WeixinJsapiTicket;
import com.jf.entity.WeixinJsapiTicketExample;

/**
 * @author chenwf:
 * @date 创建时间：2017年9月5日 上午11:13:28
 * @version 1.0
 * @parameter
 * @return
 */
@Service
@Transactional
public class WeixinJsapiTicketService extends BaseService<WeixinJsapiTicket, WeixinJsapiTicketExample> {
	@Autowired
	private WeixinJsapiTicketMapper weixinJsapiTicketMapper;

	@Autowired
	public void setWeixinJsapiTicketMapper(WeixinJsapiTicketMapper weixinJsapiTicketMapper) {
		this.setDao(weixinJsapiTicketMapper);
		this.weixinJsapiTicketMapper = weixinJsapiTicketMapper;
	}


	public Map<String, String> getWxShareSignature(String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = RandCharsUtils.getRandomString(16);
		String timestamp = System.currentTimeMillis() / 1000 + "";
		String jsapi_ticket = WeixinUtil.getJsapiTicket();
		String appId = WeixinUtil.appId;
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
//		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("url", url);
		ret.put("appId", appId);
		//ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

}
