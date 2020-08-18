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
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.service.SportService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月21日 下午3:19:51 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class ZSportController extends BaseController{
	@Resource
	private SportService sportService;
	
	/**
	 * 
	 * 方法描述 ：世界杯竞猜首页
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月22日 下午2:23:54 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getSportListH5")
	@ResponseBody
	public ResponseMsg getSportList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer memberId = getMemberId(request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String,Object> map = sportService.getSportList(memberId,reqDataJson,pageSize);
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
	 * 方法描述 ：添加竞猜
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月22日 上午11:30:38 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/addSportGuessH5")
	@ResponseBody
	public ResponseMsg addSportGuess(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"type","integral","memberId"};
			this.requiredStr(obj,request);
			Integer memberId = reqDataJson.getInt("memberId");
			sportService.addSportGuess(memberId,reqDataJson);
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
	 * 方法描述 ：竞猜记录
	 * @author  chenwf: 
	 * @date 创建时间：2018年5月22日 上午11:30:26 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/z/getSportGuessRecordH5")
	@ResponseBody
	public ResponseMsg getSportGuessRecord(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer memberId = reqDataJson.getInt("memberId");
			Integer pageSize = Const.RETURN_SIZE_10;
			List<Map<String, Object>> dataList = sportService.getSportGuessRecord(memberId,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	@RequestMapping(value = "/api/n/getIntegralTaskH5")
	@ResponseBody
	public ResponseMsg getIntegralTask(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer memberId = null;
			if(!StringUtil.isBlank(reqDataJson.getString("memberId"))){
				memberId = reqDataJson.getInt("memberId");
			}
			List<Map<String, Object>> dataList = sportService.getIntegralTask(memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	
	@RequestMapping(value = "/api/z/addMemberIntegralTaskH5")
	@ResponseBody
	public ResponseMsg addMemberIntegralTaskH5(HttpServletRequest request){
		try {
			Integer memberId = getMemberId(request);
			
			sportService.addMemberIntegralTask(memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
}
