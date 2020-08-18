package com.jf.common.ext.skd;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TrackingioApi {

	private static Logger logger = Logger.getLogger(TrackingioApi.class);

	public static String APPKEY_IOS = "d340815e21218ffde3a36c3ddc362f9f";
	public static String APPKEY_ANDROID = "a6fd6662a214d38e2b4c046dfac19f45";

	/**
	 * 上报用户付费信息
	 * 接口文档：http://doc.trackingio.com/sdkwen-dang/restfu-fei-bao-song-wen-dang.html
	 * content接收参数如下
		 context.put("_deviceid", "");	// 设备ID，最长128位，IOS：内容填写idfa的值。Android：内容填写imei的值，无则填写anroidid的
		 context.put("_transactionid", "");	//交易流水号，请确保唯一
		 context.put("_paymenttype", "");	//支付类型，最长64位，例如支付宝(alipay)，银联(unionpay)，微信支付（weixinpay）， FREE（FREE表示不统计付费）
		 context.put("_currencytype", "CNY");	// 货币类型，按照国际标准组织ISO 4217中规范的3位字母，例如CNY人民币、USD
		 context.put("_currencyamount", "");	//支付的真实货币金额，人民币单位：元
		 context.put("_idfa", "");	// ios必填
		 context.put("_imei", "");	// android必填
		 context.put("_androidid", "");	// android必填
		 context.put("_mac", "");	// android必填
		 context.put("_ip", "");
		 context.put("_ipv6", "");
		 context.put("_tz", "+8");
		 context.put("_rydevicetype", "");	// 设备类型如iphone5s、sansung-GT9300
		 context.put("_ryosversion", "");	// 投放adwords渠道必填，设备系统的版本，例如10.1.2，请务必只传版本号，不要附加其他内容，如version
		 context.put("_app_version", "");	// 投放adwords渠道必填，app版本例如：1.0.0
		 context.put("_lib_version", "");	// 投放adwords渠道必填，SDK版本，默认1.0.0
		 context.put("_timestamp", "");		// 投放adwords渠道必填，当前13位时间戳
	 */
	public static boolean commitPayInfo(String appId, String who, Map<String, Object> context) {
		TrackingioClient sdk = new TrackingioClient();
		sdk.setApiPath("/receive/tkio/payment");

		JSONObject param = new JSONObject();
		param.put("appid", appId);
		param.put("who", who);
		param.put("context", context);
		sdk.setPostData(param.toJSONString());
		return TrackingioClient.isSucc(sdk.invoke());
	}


	public static void main(String[] args) {

		Map<String, Object> context = new HashMap<>();
		context.put("_deviceid", "9E56B8CD-877F-41BB-86F7-D528D0706E81");	// 设备ID，最长128位，IOS：内容填写idfa的值。Android：内容填写imei的值，无则填写anroidid的
		context.put("_transactionid", "testcombilecode111");	//交易流水号，请确保唯一
		context.put("_paymenttype", "alipay");	//支付类型，最长64位，例如支付宝(alipay)，银联(unionpay)，微信支付（weixinpay）， FREE（FREE表示不统计付费）
		context.put("_currencytype", "CNY");	// 货币类型，按照国际标准组织ISO 4217中规范的3位字母，例如CNY人民币、USD
		context.put("_currencyamount", 1.02f);	//支付的真实货币金额，人民币单位：元
		context.put("_idfa", "9E56B8CD-877F-41BB-86F7-D528D0706E81");	// ios必填
		//context.put("_imei", "");	// android必填
		//context.put("_androidid", "");	// android必填
		//context.put("_mac", "");	// android必填
		context.put("_ip", "192.168.10.1");
		context.put("_ipv6", "");
		context.put("_tz", "+8");
		context.put("_rydevicetype", "iphone5s");	// 设备类型如iphone5s、sansung-GT9300

		commitPayInfo(APPKEY_IOS, "1", context);
	}


}