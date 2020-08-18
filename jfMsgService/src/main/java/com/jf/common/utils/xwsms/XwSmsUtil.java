package com.jf.common.utils.xwsms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.esms.common.util.Interceptor;
import com.jf.common.constant.Const;
import com.jf.common.utils.PostMsgXw;
import com.jf.controller.SmsController;

public class XwSmsUtil {

	private static final Logger logger = Logger.getLogger(SmsController.class);
	
	public static String xw_account;
	public static String xw_send_interface_pwd;
	
	static {
		try {
			Configuration configuration = new PropertiesConfiguration("sms_config.properties");
			
			xw_account = configuration.getString("xw_task_sms_account");
			xw_send_interface_pwd = configuration.getString("xw_task_sms_send_interface_pwd");
			
		} catch (Exception e) {
			throw new RuntimeException("[加载配置文件失败]", e);
		}
	}
	
	public static GsmsResponse sendSmsXw(List<XwMessageData> xwMessageDataList) {
		if(xwMessageDataList == null || xwMessageDataList.size() == 0 ) {
			return null;
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
			pack.setMsgType(MTPack.MsgType.SMS); // 消息类型：短信（SMS）、彩信（MMS）
			pack.setDistinctFlag(false); // 是否过滤重复号码
			ArrayList<MessageData> msgs = new ArrayList<MessageData>(); // 条数信息
			
			// 单发, 一号码一内容
			pack.setSendType(MTPack.SendType.GROUP); // 发送类型：群发（MASS）、组发（GROUP）、单发是以上两者的特例
			for(XwMessageData xwMessageData : xwMessageDataList ) {
				msgs.add(new MessageData(xwMessageData.getPhone(), xwMessageData.getContent(), xwMessageData.getCustomMsgID()));
			}
			pack.setMsgs(msgs);
			
			Account taskAccount = new Account(xw_account, xw_send_interface_pwd);
			GsmsResponse resp = pm.post(taskAccount, pack);
			
			logger.info("营销中心玄武短信任务推送返回结果：" + resp.toString());
			
			return resp;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
