package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityCustom;
import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageCustom;
import com.jf.entity.SysAppMessageExample;
import com.jf.entity.SysAppMessageExt;
import com.jf.entity.SysAppMessageExtExample;
import com.jf.entity.SysAppMessageMember;
import com.jf.entity.SysAppMessageMemberExample;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityService;
import com.jf.service.CustomAdPageService;
import com.jf.service.SysAppMessageMemberService;
import com.jf.service.SysAppMessageService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月15日 上午10:28:45 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class SysAppMessageController extends BaseController	{
	
	@Resource
	private SysAppMessageService sysAppMessageService;
	
	@Resource
	private SysAppMessageMemberService sysAppMessageMemberService;
	@Resource
	private ActivityService activityService;
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private CustomAdPageService customAdPageService;
	
	/**
	 * 
	 * 方法描述 ：获取会员消息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月15日 下午2:50:15 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getAppMemberMessage")
	@ResponseBody
	public ResponseMsg getAppMessage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId"};
			this.requiredStr(obj,request);
			
			Integer memberId = reqDataJson.getInt("memberId");//会员id
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("memberId", memberId);
			Map<String,Object> Rows = new HashMap<String,Object>();
			Map<String,Object> systemMsgMap = new HashMap<String,Object>();
			Map<String,Object> transactionMsgMap = new HashMap<String,Object>();
			//获取当前时间
			String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
			//昨天时间
			String lastDate = DateUtil.getFormatDate(DateUtil.addDay(new Date(), -1), "yyyyMMdd");
			String systemContent = "";
			String systemDate = "";
			long sysTime = 0;
			long tranTime = 0;
			List<SysAppMessageCustom> systemMsgs = sysAppMessageService.getSystemMsg(params);
			if(CollectionUtils.isNotEmpty(systemMsgs)){
				SysAppMessage systemMsg = systemMsgs.get(0);
				systemContent = systemMsg.getContent();
				//获取的创建时间
				String createDate = DateUtil.getFormatDate(systemMsg.getCreateDate(), "yyyyMMdd");
				if(createDate.equals(currentDate)){
					systemDate = DateUtil.getFormatDate(systemMsg.getCreateDate(), "HH:mm");
				}else if(createDate.equals(lastDate)){
					systemDate = "昨天";
				}else{
					systemDate = DateUtil.getFormatDate(systemMsg.getCreateDate(), "yyyy年MM月dd日");
				}
				sysTime = systemMsg.getCreateDate().getTime();
			}
			systemMsgMap.put("systemContent", systemContent);
			systemMsgMap.put("systemDate", systemDate);
			systemMsgMap.put("sysTime", sysTime);
			String transactionContent = "";
			String transactionDate = "";
			List<SysAppMessageCustom> transactionMsgs = sysAppMessageService.getTransactionMsg(params);
			if(CollectionUtils.isNotEmpty(transactionMsgs)){
				SysAppMessage transactionMsg = transactionMsgs.get(0);
				transactionContent = transactionMsg.getContent();
				//获取的创建时间
				String createDate = DateUtil.getFormatDate(transactionMsg.getCreateDate(), "yyyyMMdd");
				if(createDate.equals(currentDate)){
					transactionDate = DateUtil.getFormatDate(transactionMsg.getCreateDate(), "HH:mm");
				}else if(createDate.equals(lastDate)){
					transactionDate = "昨天";
				}else{
					transactionDate = DateUtil.getFormatDate(transactionMsg.getCreateDate(), "yyyy年MM月dd日");
				}
				transactionMsgMap.put("createDate", transactionDate);
				tranTime = transactionMsg.getCreateDate().getTime();
			}
			transactionMsgMap.put("transactionContent", transactionContent);
			transactionMsgMap.put("transactionDate",transactionDate);
			transactionMsgMap.put("tranTime",tranTime);
			//类型 1 首页主题管 2 日常营销入口 3 商品主题馆 4 现金签到 5 砍价免费拿 6 购物车 7 消息 8 商品未上架
			Map<String,Object> adPageMap = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_7,null);
			String customAdPageUrl = adPageMap.get("customAdPageUrl").toString();
			Rows.put("customAdPageUrl", customAdPageUrl);
			Rows.put("system", systemMsgMap);
			Rows.put("transaction", transactionMsgMap);


			// 消息中心增加活动精选
			SysAppMessageExt activitySelection = sysAppMessageService.findLatest(memberId, "3");
			Map<String, Object> map;
			// 兼容安卓精选活动消息没有判空
			if(activitySelection == null){
				map = new HashMap<>();
				map.put("msgDate", "");
				map.put("activitySelectionTime", 0);
			}else{
				map = sysAppMessageService.transToMap(activitySelection);
			}
			Rows.put("activitySelection", map);

			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,Rows);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：根据类型获取消息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月15日 下午3:17:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getAppMemberMessageByType")
	@ResponseBody
	public ResponseMsg getAppMessageByType(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"pageSize","currentPage","memberId","type"};
			this.requiredStr(obj,request);
			
			Integer memberId = reqDataJson.getInt("memberId");//会员id
			//消息类型(1系统消息 2交易物流)
			String type = reqDataJson.getString("type");
			Integer pageSize = Integer.valueOf(reqDataJson.getString("pageSize"));//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			
			if(currentPage == null){
				currentPage = 0;
			}
			if(pageSize == null){
				pageSize = 5;
			}
			Map<String,Object> Rows = new HashMap<String,Object>();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("memberId", memberId);
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			if(type.equals("1")){
				List<Map<String,Object>> systemMsgMapList = new ArrayList<Map<String,Object>>();
				List<SysAppMessageCustom> systemMsgs = sysAppMessageService.getSystemMsg(params);
				Map<String,Object> productParamsMap = new HashMap<String,Object>();
				if(CollectionUtils.isNotEmpty(systemMsgs)){
					for (SysAppMessage systemMsg : systemMsgs) {
						Map<String,Object> systemMsgMap = new HashMap<String,Object>();
						String title = DataDicUtil.getStatusDesc("SYS_APP_MESSAGE", "TITLE", systemMsg.getTitle());
						//业务类型(1子订单 2售后单 3投诉单 4会场 5活动 6商品详情)
						String linkType = systemMsg.getLinkType();
						Integer linkId = systemMsg.getLinkId();
						String areaUrl = "";
						String activityAreaName = "";
						String activityAreaType = "";
						productParamsMap.put("productId", linkId);
						systemMsgMap.put("id",systemMsg.getId());
						systemMsgMap.put("type",systemMsg.getType());
						systemMsgMap.put("title",title);
						//systemMsgMap.put("expressNo",systemMsg.getExpressNo());
						systemMsgMap.put("LinkType",linkType);
						systemMsgMap.put("linkId",linkId);
						systemMsgMap.put("memberId",systemMsg.getMemberId());
						systemMsgMap.put("pushFlag",systemMsg.getPushFlag());
						systemMsgMap.put("content", systemMsg.getContent());
						String msgDate = "";
						//获取的创建时间
						String createDate = DateUtil.getFormatDate(systemMsg.getCreateDate(), "yyyyMMdd");
						//获取当前时间
						String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
						//昨天时间 = 当前时间-1
						String lastDate = DateUtil.getFormatDate(DateUtil.addDay(systemMsg.getCreateDate(), -1), "yyyyMMdd");
						if(createDate.equals(currentDate)){
							msgDate = DateUtil.getFormatDate(systemMsg.getCreateDate(), "HH:mm");
						}else if(createDate.equals(lastDate)){
							msgDate = "昨天";
						}else{
							msgDate = DateUtil.getFormatDate(systemMsg.getCreateDate(), "yyyy年MM月dd日");
						}
						systemMsgMap.put("createDate", msgDate);
						if(linkType.equals("6")){//商品详情
							ActivityCustom activityCustom = activityService.selectActivityAreaInfo(productParamsMap);
							Integer activityAreaId = 0;
							if(activityCustom != null){
								activityAreaId =  activityCustom.getActivityAreaId();
							}
							systemMsgMap.put("activityAreaId",activityAreaId);
						}else if(linkType.equals("4")){//
							ActivityArea activityArea = activityAreaService.findModel(linkId);
							systemMsgMap.put("activityAreaId",activityArea.getId());
							if(!StringUtil.isBlank(activityArea.getTempletSuffix())){
								areaUrl = Config.getProperty("mUrl")+activityArea.getTempletSuffix()+"?activityAreaId="+activityArea.getId()+"&currentPage=0&pageSize=10&memberId=";
							}
							if(!StringUtil.isBlank(activityArea.getName())){
								activityAreaName = activityArea.getName();
							}
							if(!StringUtil.isBlank(activityArea.getType())){
								activityAreaType = activityArea.getType();
							}
						}else{
							systemMsgMap.put("activityAreaId","");
						}
						systemMsgMap.put("areaUrl",areaUrl);
						systemMsgMap.put("activityAreaName",activityAreaName);
						systemMsgMap.put("activityAreaType",activityAreaType);
						systemMsgMapList.add(systemMsgMap);
					}
				}
				Rows.put("sysAppMessage", systemMsgMapList);
				Rows.put("totalPage", systemMsgMapList.size());
			}else if(type.equals("2")){
				List<Map<String,Object>> transactionMsgMapList = new ArrayList<Map<String,Object>>();
				List<SysAppMessageCustom> transactionMsgs = sysAppMessageService.getTransactionMsg(params);
				if(CollectionUtils.isNotEmpty(transactionMsgs)){
					for (SysAppMessage transactionMsg : transactionMsgs) {
						Map<String,Object> transactionMsgMap = new HashMap<String,Object>();
						transactionMsgMap.put("id",transactionMsg.getId());
						transactionMsgMap.put("type",transactionMsg.getType());
						String title = DataDicUtil.getStatusDesc("SYS_APP_MESSAGE", "TITLE", transactionMsg.getTitle());
						transactionMsgMap.put("title",title);
						transactionMsgMap.put("expressNo",transactionMsg.getExpressNo());
						transactionMsgMap.put("LinkType",transactionMsg.getLinkType());
						transactionMsgMap.put("linkId",transactionMsg.getLinkId());
						transactionMsgMap.put("memberId",transactionMsg.getMemberId());
						transactionMsgMap.put("pushFlag",transactionMsg.getPushFlag());
						if(!StringUtil.isBlank(transactionMsg.getExpressNo())){
							transactionMsgMap.put("content", transactionMsg.getContent()+"\n物流单号:"+transactionMsg.getExpressNo());
						}else{
							transactionMsgMap.put("content", transactionMsg.getContent());
						}
						String msgDate = "";
						//获取的创建时间
						String createDate = DateUtil.getFormatDate(transactionMsg.getCreateDate(), "yyyyMMdd");
						//获取当前时间
						String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
						//昨天时间
						String lastDate = DateUtil.getFormatDate(DateUtil.addDay(new Date(), -1), "yyyyMMdd");
						if(createDate.equals(currentDate)){
							msgDate = DateUtil.getFormatDate(transactionMsg.getCreateDate(), "HH:mm");
						}else if(createDate.equals(lastDate)){
							msgDate = "昨天";
						}else{
							msgDate = DateUtil.getFormatDate(transactionMsg.getCreateDate(), "yyyy年MM月dd日");
						}
						transactionMsgMap.put("createDate", msgDate);
						transactionMsgMapList.add(transactionMsgMap);
					}
				}
				Rows.put("sysAppMessage", transactionMsgMapList);
				Rows.put("totalPage", transactionMsgMapList.size());
			}else if(type.equals("3")){
				SysAppMessageExtExample example = new SysAppMessageExtExample();
				SysAppMessageExtExample.SysAppMessageExtCriteria criteria = example.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andMemberIdEqualTo(memberId);
				criteria.andTypeEqualTo(type);

				QueryObject queryObject = example.getQueryObject();
				queryObject.setPageSize(pageSize);
				queryObject.setPageNumber(currentPage);
				queryObject.addSort("id", "desc");

				Rows.put("page", sysAppMessageService.paginate(example));
			}

			//Rows.put("totalPage", transactionMsgMapList.size());
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG, pageSize, Rows);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：取消消息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月17日 下午2:13:49 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/deleteAppMessageByType")
	@ResponseBody
	public ResponseMsg deleteAppMessageByType(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId","type"};
			this.requiredStr(obj,request);
			
			Integer memberId = reqDataJson.getInt("memberId");//会员id
			//消息类型(1系统消息 2交易物流)
			String type = reqDataJson.getString("type");
			Date date = new Date();
			if(type.equals("1")){
				List<Integer> appMessageIdList = new ArrayList<Integer>();
				SysAppMessageMemberExample sysAppMessageMemberExample = new SysAppMessageMemberExample();
				sysAppMessageMemberExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
				List<SysAppMessageMember> sysAppMessageMembers = sysAppMessageMemberService.selectByExample(sysAppMessageMemberExample);
				
				if(sysAppMessageMembers!=null&&sysAppMessageMembers.size()>0){
					for (SysAppMessageMember sysAppMessageMember : sysAppMessageMembers) {
						appMessageIdList.add(sysAppMessageMember.getAppMessageId());
					}
					SysAppMessageMember sysAppMessageMember = new SysAppMessageMember();
					sysAppMessageMember.setDelFlag("1");
					sysAppMessageMember.setUpdateBy(memberId);
					sysAppMessageMember.setUpdateDate(date);
					sysAppMessageMember.setRemarks("服务端删除");
					sysAppMessageMemberService.updateByExampleSelective(sysAppMessageMember, sysAppMessageMemberExample);
				}
			}else{
				SysAppMessageExample sysAppMessageExample = new SysAppMessageExample();
				sysAppMessageExample.createCriteria().andTypeEqualTo(type).andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
				SysAppMessage sysAppMessage = new SysAppMessage();
				sysAppMessage.setDelFlag("1");
				sysAppMessage.setUpdateBy(memberId);
				sysAppMessage.setUpdateDate(date);
				sysAppMessage.setRemarks("服务端删除");
				sysAppMessageService.updateByExampleSelective(sysAppMessage, sysAppMessageExample);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
