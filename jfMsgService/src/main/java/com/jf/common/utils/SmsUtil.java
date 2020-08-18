package com.jf.common.utils;

import com.alibaba.fastjson.JSON;
import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.esms.common.util.Interceptor;
import com.jf.common.constant.Const;
import com.jf.common.ext.skd.KdApi;
import com.jf.common.utils.mwsms.MwSmsUtil;
import com.jf.common.utils.xysms.XySmsUtil;
import com.jf.entity.Sms;
import net.sf.json.JSONObject;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SmsUtil {
	private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);

	public static String webPower_url;
	public static String webPower_UserName;
	public static String webPower_UserPwd;
	public static String defaul_smsPlatform;
	public static String webPower_campaignID;
	public static String xw_account;
	public static String xw_send_interface_pwd;

	static {
		try {
			Configuration configuration = new PropertiesConfiguration("sms_config.properties");
			webPower_url = configuration.getString("webPower_url");
			webPower_UserName = configuration.getString("webPower_UserName");
			webPower_UserPwd = configuration.getString("webPower_UserPwd");
			webPower_campaignID = configuration.getString("webPower_campaignID");
			
			xw_account = configuration.getString("xw_account");
			xw_send_interface_pwd = configuration.getString("xw_send_interface_pwd");
			
			
			defaul_smsPlatform = configuration.getString("defaul_smsPlatform");

		} catch (Exception e) {
			throw new RuntimeException("[加载配置文件失败]", e);
		}
	}

	public static Sms sendSms(Sms sms) {
		if (sms.getSmsPlatform() == null || "".equals(sms.getSmsPlatform())) {
			sms.setSmsPlatform(defaul_smsPlatform);
		}

//		if("1".equals(sms.getSmsType())) { //验证码类型使用webpower
//			sms.setSmsPlatform("1");
//		}else { //其他类型使用玄武科技
//			sms.setSmsPlatform("3");
//		}
		
		/*if("2".equals(sms.getSmsPlatform())&&!"1".equals(sms.getSmsType())){//只有验证码类型的短信才发快递鸟
			sms.setSmsPlatform("1");
		}*/
		
		
		if (Const.SMS_PLATFORM_WEBPOWER.equals(sms.getSmsPlatform())) {// 1 为webpower
			sendSmsWebPower(sms);
		}
		if (Const.SMS_PLATFORM_KDN.equals(sms.getSmsPlatform())) {// 2 为快递鸟
			sendSmsKdn(sms);
		}
		if (Const.SMS_PLATFORM_XW.equals(sms.getSmsPlatform())) {// 3 玄武科技
			sendSmsXw(sms);
		}
		//STORY #1770
		if (Const.SMS_PLATFORM_SHXY.equals(sms.getSmsPlatform())) {// 4 上海歆阳
			sendSmsSHXY(sms);
		}
		//STORY #1956
		if (Const.SMS_PLATFORM_MW.equals(sms.getSmsPlatform())) {// 5 上海歆阳
			sendSmsMw(sms);
		}

		return sms;
	}

	public static Sms sendSmsWebPower(Sms sms) {

		if (StringUtil.isEmpty(sms.getMobile())) {
			return sms;
		}

		Map<String, String> headers = new HashMap<String, String>();
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String user = base64Encoder.encode((webPower_UserName + ":" + webPower_UserPwd).getBytes());
		headers.put("Authorization", "Basic " + user);
		headers.put("Content-type", "application/json");
		JSONObject smsObject = new JSONObject();
		smsObject.put("mobile", sms.getMobile());
		smsObject.put("campaignID", webPower_campaignID);
		smsObject.put("content", sms.getContent());
		String result = HttpUtil.sendPostRequest(webPower_url + "/single_sms", smsObject.toString(), null, headers);
		JSONObject resultObject=JSONObject.fromObject(result);
		System.out.println(resultObject);
		sms.setCommitResult(resultObject.getString("status"));
		if("OK".equals(resultObject.getString("status"))){
			sms.setSeqNum(resultObject.getString("messageID"));
			sms.setSendStatus("1");
		}else{
			sms.setSendStatus("2");
		}
		return sms;
	}
	
	public static Sms sendSmsKdn(Sms sms) {
		
		if (StringUtil.isEmpty(sms.getMobile())) {
			return sms;
		}
		String result=null;
		try {	
		String requestData="";
			if("1".equals(sms.getSmsType())){
				
				String code=sms.getContent().substring(13, 19);
				requestData="{'Detail':[{'ReceiverMobile':'"+sms.getMobile()+"','Data':'{Code:"+code+"}'}],'TemplateType':7001}";
			}else{
//				setkdnSmsTemplate(sms.getContent());
//				requestData="{'Detail':[{'ReceiverMobile':'"+sms.getMobile()+"'}],'TemplateType':7}";
			}
				
		
		
		
		Map<String, String> params = new HashMap<String, String>();

		params.put("RequestData", KdApi.urlEncoder(requestData, "UTF-8"));
		
		params.put("EBusinessID", KdApi.EBusinessID);
		params.put("RequestType", "8101");
		String dataSign=KdApi.encrypt(requestData,KdApi.AppKey, "UTF-8");
		params.put("DataSign", KdApi.urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "2");
		
		result=KdApi.sendPost(KdApi.SUBSCRIBE_URL, params);	
		
		JSONObject resultObject=JSONObject.fromObject(result);
		sms.setCommitResult(resultObject.getString("Success"));
		if("true".equals(resultObject.getString("Success"))){
			sms.setSeqNum(resultObject.getString("OrderCode"));
			sms.setSendStatus("1");
		}else{
			sms.setSendStatus("2");
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sms;
	}
	
	/**
	 * 
	 * @Title sendSmsXw   
	 * @Description TODO(玄武科技短息发送)   
	 * @author pengl
	 * @date 2018年9月27日 下午6:26:38
	 */
	public static Sms sendSmsXw(Sms sms) {
		if (StringUtil.isEmpty(sms.getMobile())) {
			return sms;
		}
		try {
			PostMsg pm = PostMsgXw.getInstance();
			pm.setInterceptor(new Interceptor() {
				public void beforeMtSend(long waitConnTime, long loginTime, MTPack pack) {
					// 加上自定义的日志输出
					System.out.println("MT before send [" + pack.getBatchID() 
							+ "] wait:" + waitConnTime 
							+ ", login:" + loginTime 
							+ ", now:" + System.currentTimeMillis());
				}
			});
			pm.getCmHost().setHost(Const.XW_IP_A, Const.XW_PORT_A); // 设置网关的IP和port，用于发送信息
			pm.getWsHost().setHost(Const.XW_IP_B, Const.XW_PORT_B); // 设置网关的IP和port，用于获取账号信息、上行、状态报告等等
			
			MTPack pack = new MTPack();
			pack.setBatchID(UUID.randomUUID()); // 信息批次唯一序号
			pack.setBatchName(sms.getMobile()); // 批次名称
			pack.setMsgType(MTPack.MsgType.SMS); // 消息类型：短信（SMS）、彩信（MMS）
			pack.setDistinctFlag(false); // 是否过滤重复号码
			ArrayList<MessageData> msgs = new ArrayList<MessageData>(); // 条数信息
			
			// 单发, 一号码一内容
			pack.setSendType(MTPack.SendType.GROUP); // 发送类型：群发（MASS）、组发（GROUP）、单发是以上两者的特例
			msgs.add(new MessageData(sms.getMobile(), sms.getContent()));
			pack.setMsgs(msgs);
			
			Account ac = new Account(xw_account, xw_send_interface_pwd);
			GsmsResponse resp = pm.post(ac, pack);
			sms.setCommitResult(resp.getMessage());
			if(0 == resp.getResult()) { // 成功
				sms.setSeqNum(resp.getUuid().toString());
				sms.setSendStatus("1");
			}else {
				sms.setSendStatus("2");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sms;
	}
	
	/**
	 * //STORY #1770
	 * @Title sendSmsSHXY   
	 * @Description TODO(上海歆阳短信发送)   
	 * @author yjc
	 * @date 2019年11月29日14:09:07
	 */
	public static Sms sendSmsSHXY(Sms sms) {
		if (StringUtil.isEmpty(sms.getMobile())) {
			return sms;
		}
		try {
			String sendResult = XySmsUtil.SendSysXySms(sms.getMobile(), Const.MESSAGE_SIGNATURES + sms.getContent(), "", "");
			if (!sendResult.startsWith("-") && sendResult.length() > 0) { //成功
				sms.setCommitResult("成功");
				sms.setSeqNum(sendResult);
				sms.setSendStatus("1");
			} else {
				sms.setSendStatus("2");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sms;
	}

	/**
	 * 上海歆阳短信发送
	 * @author XDD
	 * @date 2020-06-04 10:28:33
	 */
	public static Sms sendSmsMw(Sms sms) {
		if (StringUtil.isEmpty(sms.getMobile())) {
			return sms;
		}
		try {
			String result = MwSmsUtil.sendMwSms(sms.getMobile(), sms.getContent(), null, Const.MW_SYS_ACCOUNT, Const.MW_SINGLE_SEND);
			if (!StringUtil.isEmpty(result)) {
				com.alibaba.fastjson.JSONObject resultObj = JSON.parseObject(result);
				if(resultObj.getInteger("result") == 0){//成功
					sms.setCommitResult("成功");
					sms.setSeqNum(resultObj.getString("msgid"));
					sms.setSendStatus("1");
				}else {
					sms.setSendStatus("2");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sms;
	}
	
	private static String setkdnSmsTemplate(String messageTemplate){

		String result=null;
		try {
		String requestData= "{'Detail':[{'TemplateType':'7','MessageTemplate':'"+messageTemplate+"','IsOpen':'1'}]}";
//		System.out.println(requestData);
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", KdApi.urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", KdApi.EBusinessID);
		params.put("RequestType", "8102");
		String dataSign=KdApi.encrypt(requestData, KdApi.AppKey, "UTF-8");
		params.put("DataSign", KdApi.urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "2");
		result=KdApi.sendPost(KdApi.SUBSCRIBE_URL, params);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(result);
		return result;
	}

	/*public static void main(String[] args) {
		String[] mobiles={"15506911155",
				"13860485484",
				"18106908227",
				"15006039376",
				"13655082627",
				"15606954882",
				"18805063668",
				"15160041520",
				"18650906706",
				"18965209824",
				"18859256589",
				"18960405803",
				"15260725607",
				"18695693060",
				"18850253277",
				"15280064110",
				"13395024151",
				"15392199316",
				"13395029764",
				"15005979782",
				"13799548793"};
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < mobiles.length; i++) {
				Sms sms=new Sms();
				sms.setMobile(mobiles[i]);
				sms.setContent("告诉你个秘密，你的验证码是"+StringUtil.getRamCode(6)+",千万不要告诉别人哟！");
				sms.setSmsType("1");
				SmsUtil.sendSms(sms);
			}
		}
		for (int i = 0; i < mobiles.length; i++) {
			Sms sms=new Sms();
			sms.setMobile("15006039376");
			sms.setContent("您申请的换货单，商家已发货，请及时的关注物流的变化。");
			sms.setSmsType("1");
			SmsUtil.sendSms(sms);
		}
	}*/
	
	/**
	 * 
	 * @Title xwTest   
	 * @Description TODO(玄武科技短息发送——测试)   
	 * @author pengl
	 * @date 2018年9月28日 下午2:54:53
	 */
	/*@Test
	public void xwTest() {
		
		List<Sms> smsList = new ArrayList<Sms>();
		
		Sms smsA = new Sms();
		smsA.setMobile("13655082627");
		smsA.setContent("告诉你个秘密，测试数据，请勿理会！");
		smsA.setSmsPlatform("3");
		
		Sms smsB = new Sms();
		smsB.setMobile("18695693060");
		smsB.setContent("告诉你个秘密，测试数据，请勿理会！");
		smsB.setSmsPlatform("3");
		
		Sms smsC = new Sms();
		smsC.setMobile("15280064110");
		smsC.setContent("告诉你个秘密，测试数据，请勿理会！");
		smsC.setSmsPlatform("3");
		
		Sms smsD = new Sms();
		smsD.setMobile("15392199316");
		smsD.setContent("告诉你个秘密，测试数据，请勿理会！");
		smsD.setSmsPlatform("3");
		
		Sms smsE = new Sms();
		smsE.setMobile("13799548793");
		smsE.setContent("告诉你个秘密，测试数据，请勿理会！");
		smsE.setSmsPlatform("3");
		
		smsList.add(smsA);
		smsList.add(smsB);
		smsList.add(smsC);
		smsList.add(smsD);
		smsList.add(smsE);
		
		for (Sms a : smsList) {
			Sms smsResult = SmsUtil.sendSms(a);
			System.out.println(JSONObject.fromObject(smsResult).toString());
		}
	}*/
	
}
