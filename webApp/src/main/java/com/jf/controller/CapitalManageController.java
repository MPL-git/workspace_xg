package com.jf.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.jf.entity.MemberInfo;
import com.jf.service.MemberAccountDtlService;
import com.jf.service.NovaPlanService;
import com.jf.service.WithdrawOrderService;

import net.sf.json.JSONObject;
/**
 * 资金管理（提现）
 * @author chenwf
 *
 */
@Controller
public class CapitalManageController extends BaseController{
	@Resource
	private WithdrawOrderService withdrawOrderService;
	@Resource
	private MemberAccountDtlService memberAccountDtlService;
	@Resource
	private NovaPlanService novaPlanService;
	
	/**
	 * 申请提现(分润金额)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/applyCashWithdraw")
	@ResponseBody
	public ResponseMsg applyCashWithdrawal(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Object[] obj = {"alipayAccount","alipayName","amount"};
			this.requiredStr(obj, request);
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			withdrawOrderService.applyCashWithdraw(reqPRM,reqDataJson,memberInfo);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	
	/**
	 * 申请提现
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberBalanceDtl")
	@ResponseBody
	public ResponseMsg getMemberBalanceDtl(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Object[] obj = {"type"};
			this.requiredStr(obj, request);
			Integer pageSize = Const.RETURN_SIZE_10;
			reqDataJson.put("pageSize", pageSize);
			String type = reqDataJson.getString("type");
			Integer memberId = getMemberId(request);
			reqDataJson.put("memberId", memberId);
			List<Map<String, Object>> dataList = new ArrayList<>();
			if("1".equals(type)){
				dataList = memberAccountDtlService.getMemberBalanceDtl(reqPRM,reqDataJson);
			}else if("2".equals(type)){
				dataList = withdrawOrderService.getMemberWithdrawOrderDtl(reqPRM,reqDataJson);
			}
			Map<String, Object> map = new HashMap<>();
			map.put("dataList", dataList);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	
	/**
	 * 阅读新星攻略，获取150积分
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addReadStrategyIntegral")
	@ResponseBody
	public ResponseMsg addReadStrategyIntegral(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			novaPlanService.addReadStrategyIntegral(memberInfo);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 申请提现
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberAccountInfo")
	@ResponseBody
	public ResponseMsg getMemberAccountInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			Map<String, Object> map = memberAccountDtlService.getMemberAccountInfo(reqPRM,reqDataJson,memberInfo);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
}
