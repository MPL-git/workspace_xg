package com.jf.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.entity.MemberInfo;
import com.jf.service.NovaPlanService;

import net.sf.json.JSONObject;

@Controller
public class ZNovaPlanController extends BaseController{
	@Resource
	private NovaPlanService novaPlanService;
	
	/**
	 * 会员开通新星计划
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addMemberOpenNovaPlanH5")
	@ResponseBody
	public ResponseMsg addMemberOpenNovaPlanH5(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			Map<String, Object> map = novaPlanService.addMemberOpenNovaPlan(memberInfo,request);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 新星攻略（新星攻略问答列表）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getNovaPlanHelpCenterH5")
	@ResponseBody
	public ResponseMsg getNovaPlanHelpCenterH5(HttpServletRequest request){
		try {
			Map<String, Object> map = novaPlanService.getNovaPlanHelpCenter(getMemberId(request));
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
