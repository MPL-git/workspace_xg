package com.jf.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import org.apache.commons.collections.CollectionUtils;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.utils.JdomParseXmlUtils;
import com.jf.common.utils.RandCharsUtils;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.WeixinUtil;
import com.jf.common.utils.wecharConfigUtil;
import com.jf.common.utils.wechar.WXSignUtils;
import com.jf.entity.MemberMobileWeixinMap;
import com.jf.entity.MemberMobileWeixinMapExample;
import com.jf.entity.MemberSupplementarySignIn;
import com.jf.entity.MemberSupplementarySignInExample;
import com.jf.entity.WeixinGzhMemberBind;
import com.jf.entity.WeixinGzhMemberBindExample;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderExample;
import com.jf.entity.WithdrawOrderStatusLog;
import com.jf.entity.WxRedpack;
import com.jf.entity.WxRedpackExample;
import com.jf.entity.pay.WxRedPacketRecordRes;
import com.jf.entity.pay.WxRedPacketReq;
import com.jf.entity.pay.WxRedPacketRes;
import com.jf.service.CouponService;
import com.jf.service.MemberCouponService;
import com.jf.service.MemberMobileWeixinMapService;
import com.jf.service.MemberSupplementarySignInService;
import com.jf.service.WeixinGzhMemberBindService;
import com.jf.service.WithdrawOrderService;
import com.jf.service.WithdrawOrderStatusLogService;
import com.jf.service.WxRedpackService;

/**
  * 签到提现 	
  * @author  chenwf: 
  * @date 创建时间：2018年6月11日 下午4:37:13 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@RegCondition
@Component
public class MemberSignInWithdrawCashTask {
	private static Logger logger = LoggerFactory.getLogger(MemberSignInWithdrawCashTask.class);
	@Resource
	private WxRedpackService wxRedpackService;
	@Resource
	private WeixinGzhMemberBindService weixinGzhMemberBindService;
	@Resource
	private WithdrawOrderService withdrawOrderService;
	@Resource
	private CouponService couponService;
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private WithdrawOrderStatusLogService withdrawOrderStatusLogService;
	@Resource
	private MemberSupplementarySignInService memberSupplementarySignInService;
	@Autowired
	private MemberMobileWeixinMapService memberMobileWeixinMapService;

	
	/**
	 * 
	 * 方法描述 ：邀请享补签卡，结束后状态改为失败状态
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月12日 下午3:07:49 
	 * @version
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public synchronized void closeSupplementaryStatus(){
		logger.info("邀请享补签卡任务截止:start");
		Date currentDate = new Date();
		MemberSupplementarySignInExample memberSupplementarySignInExample = new MemberSupplementarySignInExample();
		memberSupplementarySignInExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andEndTimeLessThanOrEqualTo(currentDate);
		List<MemberSupplementarySignIn> list = memberSupplementarySignInService.selectByExample(memberSupplementarySignInExample);
		if(CollectionUtils.isNotEmpty(list)){
			for (MemberSupplementarySignIn memberSupplementarySignIn : list) {
				try {
					memberSupplementarySignInService.closeSupplementaryStatus(memberSupplementarySignIn);
				} catch (Exception e) {
					e.printStackTrace();
					logger.info(e.getMessage());
					logger.info("邀请享补签卡任务截止error:end");
				}
			}
		}
    	logger.info("邀请享补签卡任务截止:end");
    }
	
	/**
	 * 
	 * 方法描述 ：平台审核通过（1优惠券直接领取 2微信红包插入到WxRedpack）
	 * @author  chenwf: 
	 * @date 创建时间：2018年8月8日 下午6:24:59 
	 * @version
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public synchronized void saveWxRedpackOrSendCoupon(){
    	try {
	        logger.info("saveWxRedpackOrSendCoupon:start");
	        Date currentDate = new Date();
	        WithdrawOrderExample orderExample = new WithdrawOrderExample();
	        orderExample.createCriteria().andStatusEqualTo("2").andDelFlagEqualTo("0").andWithdrawMethodEqualTo("0");
	        List<WithdrawOrder> withdrawOrders = withdrawOrderService.selectByExample(orderExample);
	        if(CollectionUtils.isNotEmpty(withdrawOrders)){
	        	for (WithdrawOrder withdrawOrder : withdrawOrders) {
	        		//1优惠券 2微信红包
	        		String withdrawType = withdrawOrder.getWithdrawType();
	        		Integer memberId = withdrawOrder.getMemberId();
	        		//状态（任何状态都需判断用户的余额是否足够）1 申请中2审核通过(此状态需冻结用户金额)4 提现成功（此状态扣除金额，解除冻结）5 审核不通过6 提现失败（此状态需解除冻结）
	        		if("1".equals(withdrawType)){
	        			memberCouponService.addReceiveWithdrawCoupon(memberId,withdrawOrder.getCouponId(),withdrawOrder);
	        		}else if("2".equals(withdrawType)) {
	        			//微信红包表
	        			withdrawOrder.setStatus("3");//提现中
	        			withdrawOrder.setUpdateBy(memberId);
	        			withdrawOrder.setUpdateDate(currentDate);
	        			withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
	        			//4:新增流水日志
	        			WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
	        			log.setStatus("3");
	        			log.setWithdrawOrderId(withdrawOrder.getId());
	        			log.setCreateBy(memberId);
	        			log.setCreateDate(currentDate);
	        			log.setDelFlag("0");
	        			withdrawOrderStatusLogService.insertSelective(log);
	        			
						WxRedpack redpack = new WxRedpack();
						redpack.setWithdrawOrderId(withdrawOrder.getId());
						redpack.setMemberId(memberId);
						redpack.setStatus("0");//状态0 未发放1 已发放待用户领取2 用户已领取（发放成功）3 发放失败
						redpack.setMchBillno(withdrawOrder.getOrderCode());
						redpack.setAmount(withdrawOrder.getAmount());
						redpack.setCreateBy(memberId);
						redpack.setCreateDate(currentDate);
						redpack.setDelFlag("0");
						wxRedpackService.insertSelective(redpack);
						//此处不在做处理，定时任务在去跑发送红包给用户
					}else{
						continue;
					}
				}
	        }
	        logger.info("saveWxRedpackOrSendCoupon:end");
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.info(e.getMessage());
			logger.info("saveWxRedpackOrSendCoupon:error:end");
		}
    }
	/**
	 * 发送红包
	 * 方法描述 ：
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月12日 下午3:07:49 
	 * @version
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public synchronized void memberSignInWithdrawCash(){
    	try {
	        logger.info("发送红包:start");
	        WxRedpackExample wxRedpackExample = new WxRedpackExample();
			wxRedpackExample.createCriteria().andStatusEqualTo("0").andDelFlagEqualTo("0");
			List<WxRedpack> wxRedpacks = wxRedpackService.selectByExample(wxRedpackExample);
			if(CollectionUtils.isNotEmpty(wxRedpacks)){
				for (WxRedpack wxRedpack : wxRedpacks) {
					submintWxRedPacked(wxRedpack);
				}
			}
	        logger.info("发送红包:end");
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.info(e.getMessage());
			logger.info("发送红包error:end");
		}
    }
	/**
	 * 用户是否领取红包
	 * 方法描述 ：
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月12日 下午3:07:57 
	 * @version
	 */
	@Scheduled(cron="0 0/30 * * * ?")
	public synchronized void memberWithdrawCashSuccess(){
    	try {
	        logger.info("查询红包领取:start");
	        List<String> status = new ArrayList<>();
	        status.add("2");
	        status.add("5");
	        WxRedpackExample wxRedpackExample = new WxRedpackExample();
			wxRedpackExample.createCriteria().andStatusIn(status).andDelFlagEqualTo("0");
			List<WxRedpack> wxRedpacks = wxRedpackService.selectByExample(wxRedpackExample);
			if(CollectionUtils.isNotEmpty(wxRedpacks)){
				for (WxRedpack wxRedpack : wxRedpacks) {
					findMemberReceiveRedPacket(wxRedpack);
				}
			}
	        logger.info("查询红包领取:end");
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.info(e.getMessage());
			logger.info("查询红包领取error:end");
		}
    }
	
	private void findMemberReceiveRedPacket(WxRedpack wxRedpack) {
		try {
			//公众号信息
			String appId = WeixinUtil.appId;
			String mchId = WeixinUtil.mchtId;
			String certPath = wecharConfigUtil.getProperty("WXGZH_CERT_PATH");
			String key = WeixinUtil.key;
			WxRedPacketReq req = new WxRedPacketReq();
			req.setNonce_str(RandCharsUtils.getRandomString(32));
			req.setMch_billno(wxRedpack.getMchBillno());
			req.setMch_id(mchId);
			req.setWxappid(appId);
			req.setBill_type("MCHT");
			SortedMap<String, Object> reqParams = new TreeMap<String, Object>();
			reqParams.put("appid", req.getWxappid());
			reqParams.put("mch_id", req.getMch_id());
			reqParams.put("nonce_str", req.getNonce_str());
			reqParams.put("mch_billno", req.getMch_billno());
			reqParams.put("bill_type", req.getBill_type());
			String sign = WXSignUtils.createSign("UTF-8", reqParams,key);
			req.setSign(sign);
			//获取xml信息
			String xmlInfo = "<xml>"+
					"<sign><![CDATA["+req.getSign()+"]]></sign>"+
					"<mch_billno><![CDATA["+req.getMch_billno()+"]]></mch_billno>"+
					"<mch_id><![CDATA["+req.getMch_id()+"]]></mch_id>"+
					"<appid><![CDATA["+req.getWxappid()+"]]></appid>"+
					"<bill_type><![CDATA["+req.getBill_type()+"]]></bill_type> "+
					"<nonce_str><![CDATA["+req.getNonce_str()+"]]></nonce_str>"+
					"</xml> ";
			String wxUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo";
			String result = OrderRefundTask.doRefund(wxUrl, xmlInfo,mchId,certPath);
			logger.info(result);
			System.out.println(result);
			List<Element> list = JdomParseXmlUtils.getWXPayResultElement(result);
			WxRedPacketRecordRes res = buildWxRedPacketRecordRes(list);
			if(res.getReturn_code().equals("SUCCESS") && res.getResult_code().equals("SUCCESS")){
				wxRedpackService.updateWxRedPacket(res,req,wxRedpack,"SUCCESS");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void submintWxRedPacked(WxRedpack wxRedpack) {
		try {
			//公众号信息
			WeixinGzhMemberBindExample gzhExample = new WeixinGzhMemberBindExample();
			gzhExample.createCriteria().andMemberIdEqualTo(wxRedpack.getMemberId()).andDelFlagEqualTo("0");
			List<WeixinGzhMemberBind> gzhMemberBinds = weixinGzhMemberBindService.selectByExample(gzhExample);
			String openId = "";
			if(CollectionUtils.isNotEmpty(gzhMemberBinds)){
				openId = gzhMemberBinds.get(0).getOpenId();
			}else{
				//根据会员id 找不到该用户绑定微信的openid
				//查找是否关联
				MemberMobileWeixinMapExample memberMobileWeixinMapExample = new MemberMobileWeixinMapExample();
				memberMobileWeixinMapExample.createCriteria().andMobileMemberIdEqualTo(wxRedpack.getMemberId()).andDelFlagEqualTo("0");
				List<MemberMobileWeixinMap> weixinMaps = memberMobileWeixinMapService.selectByExample(memberMobileWeixinMapExample);
				if(CollectionUtils.isNotEmpty(weixinMaps)){
					gzhExample = new WeixinGzhMemberBindExample();
					gzhExample.createCriteria().andMemberIdEqualTo(weixinMaps.get(0).getWeixinMemberId()).andDelFlagEqualTo("0");
					gzhMemberBinds = weixinGzhMemberBindService.selectByExample(gzhExample);
					if(CollectionUtils.isNotEmpty(gzhMemberBinds)){
						openId = gzhMemberBinds.get(0).getOpenId();
					}
				}
			}
			String appId = WeixinUtil.appId;
			String mchId = WeixinUtil.mchtId;
			String certPath = wecharConfigUtil.getProperty("WXGZH_CERT_PATH");
			String key = WeixinUtil.key;
			WxRedPacketReq req = new WxRedPacketReq();
			req.setNonce_str(RandCharsUtils.getRandomString(32));
			req.setMch_billno(wxRedpack.getMchBillno());
			req.setMch_id(mchId);
			req.setWxappid(appId);
			req.setSend_name("醒购");
			req.setRe_openid(openId);
			req.setTotal_amount(wxRedpack.getAmount().multiply(new BigDecimal("100")).intValue());
			req.setTotal_num(1);
			req.setWishing("感谢您参加醒购签到活动");
			req.setClient_ip("121.196.208.202");
			req.setAct_name("签到领取红包活动");
			req.setRemark("连续签到，更多惊喜");
			req.setScene_id("PRODUCT_1");
			WxRedPacketRes res = new WxRedPacketRes();
			if(!StringUtil.isBlank(openId)){
				SortedMap<String, Object> reqParams = new TreeMap<String, Object>();
				reqParams.put("wxappid", req.getWxappid());
				reqParams.put("mch_id", req.getMch_id());
				reqParams.put("nonce_str", req.getNonce_str());
				reqParams.put("mch_billno", req.getMch_billno());
				reqParams.put("send_name", req.getSend_name());
				reqParams.put("re_openid", req.getRe_openid());
				reqParams.put("total_amount", req.getTotal_amount());
				reqParams.put("total_num", req.getTotal_num());
				reqParams.put("wishing", req.getWishing());
				reqParams.put("client_ip", req.getClient_ip());
				reqParams.put("act_name", req.getAct_name());
				reqParams.put("remark", req.getRemark());
				reqParams.put("scene_id", req.getScene_id());
				String sign = WXSignUtils.createSign("UTF-8", reqParams,key);
				req.setSign(sign);
				
				//获取xml信息
				String xmlInfo = xmlInfo(req);
				String wxUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
				String result = OrderRefundTask.doRefund(wxUrl, xmlInfo,mchId,certPath);
				logger.info(result);
				System.out.println(result);
				List<Element> list = JdomParseXmlUtils.getWXPayResultElement(result);
				res = buildWxRedPacketRes(list);
				if(res.getReturn_code().equals("SUCCESS") && res.getResult_code().equals("SUCCESS")){
					wxRedpackService.updateWithdrawOrderInfo(res,req,wxRedpack,"SUCCESS");
				}else{
					wxRedpackService.updateWithdrawOrderInfo(res,req,wxRedpack,"FAIL");
				}
			}else{
				res.setErr_code("未获取到openid");
				res.setErr_code_des("未获取到openid");
				res.setResult_code("FAIL");
				res.setReturn_code("FAIL");
				res.setReturn_msg("未获取到openid");
				wxRedpackService.updateWithdrawOrderInfo(res,req,wxRedpack,"FAIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private WxRedPacketRes buildWxRedPacketRes(List<Element> list) {
		WxRedPacketRes result = null;
		if (list != null && list.size() > 0) {
			result = new WxRedPacketRes();
			for (Element element : list) {
				if ("return_code".equals(element.getName())) {
					result.setReturn_code(element.getText());
				}
				
				if ("return_msg".equals(element.getName())) {
					result.setReturn_msg(element.getText());
				}
				
				if ("result_code".equals(element.getName())) {
					result.setResult_code(element.getText());
				}
				
				if ("err_code".equals(element.getName())) {
					result.setErr_code(element.getText());
				}

				if ("err_code_des".equals(element.getName())) {
					result.setErr_code_des(element.getText());
				}
				
				if ("sign".equals(element.getName())) {
					result.setSign(element.getText());
				}
				
				if ("mch_billno".equals(element.getName())) {
					result.setMch_billno(element.getText());
				}

				if ("mch_id".equals(element.getName())) {
					result.setMch_id(element.getText());
				}
				
				if ("wxappid".equals(element.getName())) {
					result.setWxappid(element.getText());
				}
				
				if ("re_openid".equals(element.getName())) {
					result.setRe_openid(element.getText());
				}

				if ("total_amount".equals(element.getName())) {
					result.setTotal_amount(Integer.valueOf(element.getText()));
				}
				
				if ("send_listid".equals(element.getName())) {
					result.setSend_listid(element.getText());
				}
				
				
			}
		}
		return result;
	}

	private String xmlInfo(WxRedPacketReq req) {
		if(req!=null){
			StringBuffer bf = new StringBuffer();
			bf.append("<xml>");

			bf.append("<sign><![CDATA[");
			bf.append(req.getSign());
			bf.append("]]></sign>");
			
			bf.append("<mch_billno><![CDATA[");
			bf.append(req.getMch_billno());
			bf.append("]]></mch_billno>");
			
			bf.append("<mch_id><![CDATA[");
			bf.append(req.getMch_id());
			bf.append("]]></mch_id>");
			
			bf.append("<nonce_str><![CDATA[");
			bf.append(req.getNonce_str());
			bf.append("]]></nonce_str>");
			
			bf.append("<wxappid><![CDATA[");
			bf.append(req.getWxappid());
			bf.append("]]></wxappid>");

			bf.append("<send_name><![CDATA[");
			bf.append(req.getSend_name());
			bf.append("]]></send_name>");
			
			bf.append("<re_openid><![CDATA[");
			bf.append(req.getRe_openid());
			bf.append("]]></re_openid>");
			
			bf.append("<total_amount><![CDATA[");
			bf.append(req.getTotal_amount());
			bf.append("]]></total_amount>");
			
			bf.append("<total_num><![CDATA[");
			bf.append(req.getTotal_num());
			bf.append("]]></total_num>");
			
			bf.append("<wishing><![CDATA[");
			bf.append(req.getWishing());
			bf.append("]]></wishing>");
			
			bf.append("<client_ip><![CDATA[");
			bf.append(req.getClient_ip());
			bf.append("]]></client_ip>");
			
			bf.append("<act_name><![CDATA[");
			bf.append(req.getAct_name());
			bf.append("]]></act_name>");
			
			bf.append("<remark><![CDATA[");
			bf.append(req.getRemark());
			bf.append("]]></remark>");
			
			bf.append("<scene_id><![CDATA[");
			bf.append(req.getScene_id());
			bf.append("]]></scene_id>");

			bf.append("</xml>");
			return bf.toString();
		}
		return "";
	}
	private WxRedPacketRecordRes buildWxRedPacketRecordRes(List<Element> list) {
		WxRedPacketRecordRes result = null;
		if (list != null && list.size() > 0) {
			result = new WxRedPacketRecordRes();
			for (Element element : list) {
				if ("return_code".equals(element.getName())) {
					result.setReturn_code(element.getText());
				}
				
				if ("return_msg".equals(element.getName())) {
					result.setReturn_msg(element.getText());
				}
				
				if ("result_code".equals(element.getName())) {
					result.setResult_code(element.getText());
				}
				
				if ("err_code".equals(element.getName())) {
					result.setErr_code(element.getText());
				}

				if ("err_code_des".equals(element.getName())) {
					result.setErr_code_des(element.getText());
				}
				
				if ("status".equals(element.getName())) {
					result.setStatus(element.getText());
				}
			}
		}
		return result;
	}
}
