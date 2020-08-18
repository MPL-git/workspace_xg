package com.jf.common.utils.mwsms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jf.common.constant.Const;
import com.jf.controller.SmsController;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MwSmsUtil {

	private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

	private static final String FIXED_STR = "00000000";
	private static final String SINGLE_SEND = "/sms/v2/std/SINGLE_SEND";
	private static final String BATCH_SEND = "/sms/v2/std/BATCH_SEND";
	/**
	 * 系统账号
	 */
	private static String mw_sys_url;
	private static String mw_sys_user_id;
	private static String mw_sys_password_prefix;
	/**
	 * 营销账号
	 */
	private static String mw_task_url;
	private static String mw_task_user_id;
	private static String mw_task_password_prefix;

	static {
		try {
			Configuration configuration = new PropertiesConfiguration("sms_config.properties");

			mw_sys_url = configuration.getString("mw_url");
			mw_sys_user_id = configuration.getString("mw_user_id");
			mw_sys_password_prefix = new StringBuffer().append(mw_sys_user_id).append(FIXED_STR).append(configuration.getString("mw_user_password")).toString();

			mw_task_url = configuration.getString("mw_task_url");
			mw_task_user_id = configuration.getString("mw_task_user_id");
			mw_task_password_prefix = new StringBuffer().append(mw_task_user_id).append(FIXED_STR).append(configuration.getString("mw_task_user_password")).toString();
			
		} catch (Exception e) {
			throw new RuntimeException("[加载配置文件失败]", e);
		}
	}

	public static void main(String[] args){
		String s = MwSmsUtil.sendMwSms("15239516807", "623千万补贴！耐克99，古驰158起，每满300减30，再送啊！ https://dwz.cn/7AJzqbdh 退订回T","134536", Const.MW_TASK_ACCOUNT, Const.MW_SINGLE_SEND);
		System.out.println(s);
	}

	/**
	 * 方法名称：sendMwSms(发送短信)
	 * @param mobile :短信接收的手机号：只能填一个手机号
	 * @param content :最大支持1000个字(含签名)，发送时请预留至少10个字符的签名长度，一个字母或一个汉字都视为一个字符
	 * @param custId :用户自定义流水号
	 * @param accountType :账户类型 1系统账户 2营销账户
	 * @param sendType :发送类型 1单个发送 2批量发送(电话号逗号分割)
	 * @return result :0：成功 非0：失败. 实例 :{"result":0,"msgid":9223372036854775808,"custid":"b3d0a2783d31b21b8573"}
	 */
	public static String sendMwSms(String mobile, String content, String custId, String accountType, String sendType){
		String date = new SimpleDateFormat("MMDDHHMMSS").format(new Date());
		String url = "";
		// 创建请求内容
		JSONObject jsonObject = new JSONObject();
		try {
			if (Const.MW_SYS_ACCOUNT.equals(accountType)) {
				jsonObject.put("userid", mw_sys_user_id);
				jsonObject.put("pwd", MD5(mw_sys_password_prefix + date, "UTF-8"));
				url = Const.MW_SINGLE_SEND.equals(sendType) ? mw_sys_url+SINGLE_SEND : mw_sys_url+BATCH_SEND;
			}else if(Const.MW_TASK_ACCOUNT.equals(accountType)){
				jsonObject.put("userid", mw_task_user_id);
				jsonObject.put("pwd", MD5(mw_task_password_prefix + date, "UTF-8"));
				url = Const.MW_SINGLE_SEND.equals(sendType) ? mw_task_url+SINGLE_SEND : mw_task_url+BATCH_SEND;
			}else {
				return null;
			}
			if (custId != null) {
				jsonObject.put("custid", custId);
			}
			jsonObject.put("mobile", mobile);
			jsonObject.put("content", URLEncoder.encode(content + Const.MESSAGE_SIGNATURES, "GBK"));
			jsonObject.put("timestamp", date);
		}catch (Exception e){
			logger.info("梦网短信-构建请求内容异常");
			e.printStackTrace();
		}
		return sendPost(url, jsonObject.toString());
	}

	/**
	 *  发送post请求
	 * @param url :请求路径
	 * @param json :请求json体
	 * @return post请求的返回值(json格式)
	 */
	private static String sendPost(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse response = null;

		String resultString = "";

		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);

			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);

			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);

			resultString = EntityUtils.toString(response.getEntity(), "UTF-8");

		} catch (Exception e) {
			logger.info("梦网短信-发送失败;路径:{};内容:{}", url, JSON.parseObject(json).getString("content"));
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				logger.info("梦网短信-发送失败;");
				e.printStackTrace();
			}
		}

		return resultString;
	}

	/**
	 * MD5加密
	 * @param str 内容
	 * @param charset 编码方式
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static String MD5(String str, String charset) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes(charset));
		byte[] result = md.digest();
		StringBuffer sb = new StringBuffer(32);
		for (int i = 0; i < result.length; i++) {
			int val = result[i] & 0xff;
			if (val <= 0xf) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString().toLowerCase();
	}
}
