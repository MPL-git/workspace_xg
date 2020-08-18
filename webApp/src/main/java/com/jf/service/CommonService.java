package com.jf.service;

import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.SysParamCfg;
import com.jf.entity.dto.AdDTO;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommonService {
	private static Logger logger = LoggerFactory.getLogger(CommonService.class);

	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	public JSONObject sendSms(String mobile, String content, String smsType, String sourceClient, String ip) {
		JSONObject result = new JSONObject();
		try {
			JSONObject param=new JSONObject();
			JSONObject reqData=new JSONObject();
			reqData.put("mobile", mobile);
			reqData.put("content", content);
			reqData.put("smsType", smsType);
			reqData.put("sourceClient", sourceClient);
			reqData.put("ip", ip);
			param.put("reqData", reqData);
			//调用短信接口
			result=JSONObject.fromObject(HttpUtil.sendPostRequest(Config.getProperty("msgServerUrl")+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
		} catch (Exception e) {
			logger.info("短信发送失败");
		}
		return result;
	}

	public String getCloumnLinkUrl(String linkValue, String type) {
		if("1".equals(type)){//签到
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/newsign/index.html";
		}else if("2".equals(type)){//砍价
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/cutprice/index.html";
		}else if("3".equals(type)){//邀请免单
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/freeprice/index.html";
		}else if("4".equals(type)){//自建页面
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?pageid="+linkValue;
		}else if("5".equals(type)){//信息管理
			if(!StringUtil.isBlank(linkValue)){
				linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=link/information.html?id="+linkValue+"&type=";
			}
		}else if("6".equals(type)){//新星协议
			if(!StringUtil.isBlank(linkValue)){
				linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/novaplan/pages/create/index.html?id="+linkValue+"&type=";
			}
		}else if("7".equals(type)){//拉新页面
			if(!StringUtil.isBlank(linkValue)){
				linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/novaplan/pages/invite/share/index.html?invitationCode="+linkValue;
			}
		}else if("8".equals(type)){//新星攻略
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/novaplan/pages/strategy/index.html";
		}else if("9".equals(type)){//店长攻略
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/shopowner/pages/strategy/index.html";
		}
		return linkValue;
	}

	public String buildH5Page(String type) {
		StringBuffer sbf = new StringBuffer();
		if("1".equals(type)){//新星计划分润余额规则
			sbf.append("<HTML>");
			sbf.append("<BODY style='font-size: 26px;'>");
			sbf.append("<style>");
			sbf.append("</style>");
			sbf.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' >");
			sbf.append("推广分润：下级会员订单完成后，按【醒购新星计划协议】给予上级会员的推广分润金额</br>");
			sbf.append("计算公式：推广分润=订单实付金额*推广分润比率</br>");
			sbf.append("预估分润：根据商品醒购价初步预估，当使用优惠券/积分时，将相应扣除收益，实际根据系统结算为准。");
			sbf.append("</BODY>");
			sbf.append("</HTML>");
		}else if("2".equals(type)){
			sbf.append("<HTML>");
			sbf.append("<BODY style='color: #fff;'>");
			sbf.append("<style>");
			sbf.append("");
			sbf.append("</style>");
			sbf.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' >");
			sbf.append("1、邀请二维码/链接中已经包含您的邀请信息，可分享二维码或链接给好友</br>");
			sbf.append("2、好友通过您的邀请链接进入醒购商城注册会员后，Ta将永久成为您的下级会员，未来Ta产生的订单您都有佣金奖励，会自动计入您的待结算余额中！</br>");
			sbf.append("</BODY>");
			sbf.append("</HTML>");
		}
		return sbf.toString();
	}

	public Map<String, Object> buildAdMap(AdDTO adDTO) {
		String adId = adDTO.getAdId();
		String linkType = adDTO.getLinkType();
		String linkValue = adDTO.getLinkValue();
		String linkUrl = adDTO.getLinkUrl();
		Integer linkId = adDTO.getLinkId();
		String type = adDTO.getType();
		if("2".equals(type)){
			if("6".equals(linkType)){
				linkValue = linkId+"";
			}else if("7".equals(linkType)){
				linkValue = linkId+"";
			}else if("4".equals(linkType)){
				linkValue = linkUrl;
			}else{
				if(linkId == null || linkId == 0){
					linkValue = linkUrl;
				}else{
					linkValue = linkId+"";
				}
			}
				
		}
		if("7".equals(linkType)){
			linkType = "4";
			linkValue = getCloumnLinkUrl(linkValue, "4");
		}else if("6".equals(linkType)){
			if("7".equals(linkValue)){
				linkType = "4";
				linkValue = getCloumnLinkUrl(linkValue, "1");
			}else if("8".equals(linkValue)){
				linkType = "4";
				linkValue = getCloumnLinkUrl(linkValue, "2");
			}else if("9".equals(linkValue)){
				linkType = "4";
				linkValue = getCloumnLinkUrl(linkValue, "3");
			}
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", adId);
		map.put("linkType", linkType);
		map.put("linkValue", linkValue);
		map.put("pic", StringUtil.getPic(adDTO.getPic(), ""));
		return map;
	}

	/**
	 * 获取特殊商家编码
	 * 这些商家具体特殊抽佣比例，他们的商品暂不支持使用平台优惠和积分抵扣，也无法获得积分
	 */
	public List<String> listSpecMchtCode(){
		SysParamCfg sysParamCfg = sysParamCfgService.findByCode("MCHT_SPEC_MCHT_CODE");
		if(sysParamCfg != null && StrKit.notBlank(sysParamCfg.getParamValue())){
			String[] values = sysParamCfg.getParamValue().split(",");
			return Arrays.asList(values);
		}
		return new ArrayList<>();
	}

	public void fillWithXCXShareInfo(Map<String, Object> data, String template, Object... params) {
		data.put("originalId", Config.getProperty("originalId"));
		data.put("xcxShareType", Config.getProperty("xcxShareType"));
		String wxPath = StringUtil.buildMsg(template, params);
		data.put("wxPath", wxPath);
		data.put("webPageUrl", Config.getProperty("mUrl") + "/xgbuy/views/index.html?redirect_url=" + wxPath);
	}
}
