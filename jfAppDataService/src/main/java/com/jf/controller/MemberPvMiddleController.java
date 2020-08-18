package com.jf.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.controller.base.BaseController;
import com.jf.service.MemberPvMiddleService;

@Controller
public class MemberPvMiddleController extends BaseController {

	@Autowired
	private MemberPvMiddleService memberPvMiddleService;
	
	/**
	 * 
	 * @MethodName: sysConfig
	 * @Description: (系统配置参数)
	 * @author Pengl
	 * @date 2019年5月30日 上午10:44:29
	 */
	@ResponseBody
	@RequestMapping("/api/n/sysConfig")
	public ResponseMsg sysConfig(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			Map<String, Object> map = memberPvMiddleService.sysConfig(reqPRM);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "数据请求失败");
		}
	}
	
	/**
	 * 
	 * @MethodName: memberPvCommit
	 * @Description: (用户浏览记录上报接口)
	 * @author Pengl
	 * @date 2019年5月30日 下午4:35:07
	 */
	@ResponseBody
	@RequestMapping("/api/n/memberPvCommit")
	public ResponseMsg memberPvCommit(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			memberPvMiddleService.memberPvCommit(reqPRM);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "数据请求失败");
		}
	}
	
	/**
	 * 
	 * @MethodName: memberPvDtlCommit
	 * @Description: (用户浏览明细上报接口)
	 * @author Pengl
	 * @date 2019年5月30日 下午4:53:12
	 */
	@ResponseBody
	@RequestMapping("/api/n/memberPvDtlCommit")
	public ResponseMsg memberPvDtlCommit(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			memberPvMiddleService.memberPvDtlCommit(reqPRM);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "数据请求失败");
		}
	}
	
	/**
	 * 
	 * @MethodName: memberActionCommit
	 * @Description: (用户行为明细上报接口)
	 * @author Pengl
	 * @date 2019年5月31日 下午7:58:45
	 */
	@ResponseBody
	@RequestMapping("/api/n/memberActionCommit")
	public ResponseMsg memberActionCommit(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			memberPvMiddleService.memberActionCommit(reqPRM);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "数据请求失败");
		}
	}
	
}
