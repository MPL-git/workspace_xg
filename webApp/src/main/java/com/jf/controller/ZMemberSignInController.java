package com.jf.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MemberInfo;
import com.jf.service.AssistanceDtlService;
import com.jf.service.MemberCumulativeSignInService;
import com.jf.service.MemberSignInService;
import com.jf.service.MemberSignInSettingService;
import com.jf.service.MemberSupplementarySignInService;
import com.jf.service.OrderService;
import com.jf.service.SignInSettingService;
import com.jf.service.SupplementarySignInSettingService;

import net.sf.json.JSONObject;

/**
  * 会员签到
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:39:28 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class ZMemberSignInController extends BaseController{
	@Resource
	private MemberSignInService memberSignInService;
	@Resource
	private MemberSignInSettingService memberSignInSettingService;
	@Resource
	private MemberCumulativeSignInService memberCumulativeSignInService;
	@Resource
	private SupplementarySignInSettingService supplementarySignInSettingService;
	@Resource
	private MemberSupplementarySignInService memberSupplementarySignInService;
	@Resource
	private AssistanceDtlService assistanceDtlService;
	@Resource
	private OrderService orderService;
	@Resource
	private SignInSettingService signInSettingService;
	
	/**
	 * 
	 * 方法描述 ：签到页面
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月17日 下午2:45:58 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMemberSignInPageH5")
	@ResponseBody
	public ResponseMsg getMemberSignInPage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer memberId = null;
			if(reqDataJson.containsKey("memberId") && !StringUtil.isBlank(reqDataJson.getString("memberId"))){
				memberId = reqDataJson.getInt("memberId");
			}
			Map<String,Object> map = memberSignInService.getMemberSignInPage(memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	@RequestMapping(value = "/api/n/getSignInBroadcastContentH5")
	@ResponseBody
	public ResponseMsg getSignInBroadcastContent(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			List<String> dataList = memberSignInService.getSignInBroadcastContent(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：签到
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月17日 下午2:46:13 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addMemberSignInH5")
	@ResponseBody
	public ResponseMsg addMemberSignIn(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer memberId = reqDataJson.getInt("memberId");
			Map<String, Object> map = memberSignInService.addMemberSignIn(memberId,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：分享好友签到弹窗页面
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月18日 下午6:03:28 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getShareFriendSignInH5")
	@ResponseBody
	public ResponseMsg getShareFriendSignIn(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer memberId = reqDataJson.getInt("memberId");
			Map<String,Object> map = memberSignInService.getShareFriendSignIn(memberId,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取签到或者提现记录
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月18日 下午6:09:09 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getSignInOrDrawRecordsH5")
	@ResponseBody
	public ResponseMsg getSignInRecords(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"type","currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Integer memberId = reqDataJson.getInt("memberId");
			List<Map<String,Object>> dataList = memberSignInService.getSignInOrDrawRecords(memberId,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：领取好友助力签到红包
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月19日 上午11:16:13 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addReceiveExtraAmountH5")
	@ResponseBody
	public ResponseMsg addReceiveExtraAmount(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer memberId = reqDataJson.getInt("memberId");
			memberSignInService.addReceiveExtraAmount(memberId,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：提现页面
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月19日 上午11:42:34 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getWithdrawCashtraPageH5")
	@ResponseBody
	public ResponseMsg getWithdrawCashtraPage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);

			Integer memberId = reqDataJson.getInt("memberId");
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String,Object> map = memberSignInService.getWithdrawCashtraPage(memberId,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：提现
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月25日 下午12:41:58 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addMemberWithdrawCashH5")
	@ResponseBody
	public ResponseMsg addMemberWithdrawCashH5(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"withdrawCashId","memberId"};
			this.requiredStr(obj,request);

			Integer memberId = reqDataJson.getInt("memberId");
			Map<String, Object> map = memberSignInService.addMemberWithdrawCash(memberId,reqDataJson,reqPRM);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	
	
	/**
	 * 
	 * 方法描述 ：签到首页
	 * @author  chenwf: 
	 * @date 创建时间：2018年12月04日 下午12:41:58 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSignInHomePageH5")
	@ResponseBody
	public ResponseMsg getSignInHomePageH5(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer memberId = null;
			if(reqDataJson.containsKey("memberId") && !StringUtil.isBlank(reqDataJson.getString("memberId"))){
				memberId = reqDataJson.getInt("memberId");
			}
			Map<String, Object> map = signInSettingService.getSignInHomePage(memberId,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：会员签到(H5暂时禁用)
	 * @author  chenwf: 
	 * @date 创建时间：2018年12月04日 下午12:41:58 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addMemberNewSignInH5")
	@ResponseBody
	public ResponseMsg addMemberNewSignInH5(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"signInSettingId"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			Map<String, Object> map = memberSignInSettingService.addMemberNewSignIn(memberId,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"手速快了点");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：签到领取奖品结算页面
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月22日 上午10:01:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addTiredSignGiftH5")
	@ResponseBody
	public ResponseMsg addTiredSignGiftH5 (HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"cumulativeSignInSettingId", "type"};
			this.requiredStr(obj, request);
			Integer memberId = getMemberId(request);
			memberCumulativeSignInService.addTiredSignGift(reqPRM,reqDataJson,memberId,request);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "手速快了点");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：我领取的补签任务
	 * @author  chenwf: 
	 * @date 创建时间：2018年12月07日 上午10:01:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getMySupplementarySignTaskListH5")
	@ResponseBody
	public ResponseMsg getMySupplementarySignTaskListH5 (HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer memberId = getMemberId(request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Integer currentPage = 0;
			if(reqDataJson.containsKey("currentPage") && !StringUtil.isBlank(reqDataJson.getString("currentPage"))){
				currentPage = reqDataJson.getInt("currentPage");
			}
			Map<String, Object> map = memberSupplementarySignInService.getMySupplementarySignTaskList(memberId,currentPage,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "手速快了点");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取我的助力详情
	 * @author  chenwf: 
	 * @date 创建时间：2018年12月07日 上午10:01:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getMyAssistanceDtlListH5")
	@ResponseBody
	public ResponseMsg getMyAssistanceDtlListH5 (HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberSupplementarySignInId"};
			this.requiredStr(obj, request);
			Integer memberId = getMemberId(request);
			List<Map<String, Object>> dataList = assistanceDtlService.getMyAssistanceDtlList(reqDataJson,memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataList);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "手速快了点");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取我的补签分享页面
	 * @author  chenwf: 
	 * @date 创建时间：2018年12月07日 上午10:01:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getMySupplementarySignSharePageH5")
	@ResponseBody
	public ResponseMsg getMySupplementarySignSharePageH5 (HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberSupplementarySignInId"};
			this.requiredStr(obj, request);
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			Map<String, Object> map = memberSupplementarySignInService.getMySupplementarySignSharePage(reqDataJson,memberInfo);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "手速快了点");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：领取补签任务
	 * @author  chenwf: 
	 * @date 创建时间：2018年12月07日 上午10:01:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addSupplementarySignTaskH5")
	@ResponseBody
	public ResponseMsg addSupplementarySignTaskH5 (HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"supplementarySignInSettingId"};
			this.requiredStr(obj, request);
			Integer memberId = getMemberId(request);
			memberSupplementarySignInService.addSupplementarySignTask(memberId,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "手速快了点");
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：好友助力
	 * @author  chenwf: 
	 * @date 创建时间：2018年12月07日 上午10:01:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addMemberAssistanceDtlH5")
	@ResponseBody
	public ResponseMsg addMemberAssistanceDtlH5 (HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberSupplementarySignInId"};
			this.requiredStr(obj, request);
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			assistanceDtlService.addMemberAssistanceDtl(memberInfo,reqDataJson,reqPRM);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "手速快了点");
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：签到领取奖品计算页面信息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月22日 上午10:01:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getSignInPayInfoH5")
	@ResponseBody
	public ResponseMsg getSignInPayInfoH5 (HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Integer memberId = getMemberId(request);
			Map<String, Object> map = orderService.getSignInPayInfo(reqPRM,reqDataJson,memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage(),"提交订单失败");
		}
	}
	
}
