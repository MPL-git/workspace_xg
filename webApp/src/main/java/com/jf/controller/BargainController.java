package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.entity.MemberInfo;
import com.jf.service.CutLinkClickLogService;
import com.jf.service.CutPriceOrderDtlService;
import com.jf.service.CutPriceOrderService;
import com.jf.service.SingleProductActivityService;
import com.jf.vo.request.FindAssistanceCutCompleteLogRequest;
import com.jf.vo.response.AssistanceCutCompleteLogView;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 单品砍价
 *
 * @author chenwf
 */
@Controller
public class BargainController extends BaseController{
	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private CutPriceOrderDtlService cutPriceOrderDtlService;
	@Resource
	private CutPriceOrderService cutPriceOrderService;
	@Resource
	private CutLinkClickLogService cutLinkClickLogService;
	@Autowired
	private AppContext appContext;
	
	/**
	 * 
	 * 方法描述 ：砍价商品列表
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getBargainGoodsList")
	@ResponseBody
	public ResponseMsg getBargainGoodsList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage","type"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String,Object> map = singleProductActivityService.getBargainGoodsList(reqDataJson,pageSize,memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：砍价商品头部图与轮播信息
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getBargainGoodsTopInfo")
	@ResponseBody
	public ResponseMsg getBargainGoodsTopInfo(HttpServletRequest request){
		try {
			Map<String,Object> map = singleProductActivityService.getBargainGoodsTopInfo();
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：我的砍价商品(砍价，助力享免单)
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMyBargainGoodsInfo")
	@ResponseBody
	public ResponseMsg getMyBargainGoodsInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage","type"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String,Object> map = singleProductActivityService.getMyCutGoodsInfo(memberId,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：我的助力大减价列表
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMyAssistanceGoodsTaskList")
	@ResponseBody
	public ResponseMsg getMyAssistanceGoodsTaskList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage","type"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String,Object> map = singleProductActivityService.getMyAssistanceGoodsTaskList(memberId,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：我的分享砍价商品
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMyCutShareInfo")
	@ResponseBody
	public ResponseMsg getMyCutShareInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage","cutOrderId"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String,Object> map = singleProductActivityService.getMyCutShareInfo(memberId,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：砍一刀
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addMemberCutOrderInfo")
	@ResponseBody
	public ResponseMsg addMemberCutOrderInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Integer memberId = getMemberId(request);
			Map<String,Object> map = singleProductActivityService.addMemberCutOrderInfo(memberId,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 邀请免费拿，帮助好友完成任务
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addFriendFinishTask")
	@ResponseBody
	public ResponseMsg addFriendFinishTask(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"cutOrderId"};
			this.requiredStr(obj,request);
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			cutPriceOrderDtlService.addFriendFinishTask(memberInfo,reqDataJson,reqPRM);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：我的分享(邀请享免单分享页面接口)
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMyInviteShareInfo")
	@ResponseBody
	public ResponseMsg getMyInviteShareInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"cutOrderId"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			Map<String,Object> map = singleProductActivityService.getMyInviteShareInfo(memberId,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：我的分享(助力大减价分享页面接口)
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMyAssistanceCutPriceShareInfo")
	@ResponseBody
	public ResponseMsg getMyAssistanceCutPriceShareInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"cutOrderId"};
			this.requiredStr(obj,request);
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			Map<String,Object> map = cutPriceOrderService.getMyAssistanceCutPriceShareInfo(memberInfo,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取邀请明细
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getInviteDetail")
	@ResponseBody
	public ResponseMsg getInviteDetail(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"cutOrderId","currentPage"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			Integer pageSize = Const.RETURN_SIZE_10;
			List<Map<String, Object>> dataList = singleProductActivityService.getInviteDetail(memberId,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataList);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：助力大减价分享链接点击日志
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addCutLinkClickLog")
	@ResponseBody
	public ResponseMsg addCutLinkClickLog(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Integer memberId = getMemberId(request);
			cutLinkClickLogService.addCutLinkClickLog(memberId,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *	助力减价 成交记录列表
	 */
	@RequestMapping(value = "/api/n/findAssistanceCutCompleteLog")
	@ResponseBody
	public ResponseMsg findAssistanceCutCompleteLog(){
		FindAssistanceCutCompleteLogRequest request = appContext.getRequestAndValidate(FindAssistanceCutCompleteLogRequest.class);
		List<AssistanceCutCompleteLogView> viewList = cutPriceOrderService.findAssistanceCutCompleteLog(request);
		return ResponseMsg.success(viewList);
	}

}
