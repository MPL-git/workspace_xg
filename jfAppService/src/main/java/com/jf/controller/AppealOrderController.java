package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.controller.base.BaseController;
import com.jf.entity.AppealOrder;
import com.jf.service.AppealLogPicService;
import com.jf.service.AppealLogService;
import com.jf.service.AppealOrderService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月10日 下午3:00:28 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class AppealOrderController extends BaseController{
	
	@Resource
	private AppealOrderService appealOrderService;
	
	@Resource
	private AppealLogPicService appealLogPicService;
	
	@Resource
	private AppealLogService appealLogService;
	
	/**
	 * 
	 * 方法描述 ：投诉申请介入
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月10日 下午3:08:25 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/applicationForComplaint")
	@ResponseBody
	public ResponseMsg applicationForComplaint(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId","appealOrderId"};
			this.requiredStr(obj,request);
			
			Integer memberId = reqDataJson.getInt("memberId");//会员idid
			Integer appealOrderId = reqDataJson.getInt("appealOrderId");//投诉订单id
			
			AppealOrder appealOrder = new AppealOrder();
			appealOrder.setId(appealOrderId);
			appealOrder.setServiceSponsorType("2");//用户介入
			appealOrder.setUpdateBy(memberId);
			appealOrder.setUpdateDate(new Date());
			appealOrderService.updateByPrimaryKeySelective(appealOrder);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：取消投诉
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月15日 下午5:04:12 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/deleteComplaint")
	@ResponseBody
	public ResponseMsg deleteComplaint(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId","appealOrderId"};
			this.requiredStr(obj,request);
			
			Integer memberId = reqDataJson.getInt("memberId");//会员idid
			Integer appealOrderId = reqDataJson.getInt("appealOrderId");//投诉订单id
			
			AppealOrder appealOrder = new AppealOrder();
			appealOrder.setId(appealOrderId);
			appealOrder.setStatus("5");
			appealOrder.setUpdateBy(memberId);
			appealOrder.setUpdateDate(new Date());
			appealOrderService.updateByPrimaryKeySelective(appealOrder);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：投诉添加留言
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月10日 下午3:38:33 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addComplaintLogMessage")
	@ResponseBody
	public ResponseMsg addComplaintLogMessage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId","memberName","appealOrderId","content"};
			this.requiredStr(obj,request);
			
			Integer memberId = reqDataJson.getInt("memberId");//会员idid
			Integer appealOrderId = reqDataJson.getInt("appealOrderId");//投诉订单id
			
			String pic = reqDataJson.getString("pic");//图片
			String content = reqDataJson.getString("content");//留言内容
			String memberName = reqDataJson.getString("memberName");//留言内容
			//日志表
			appealOrderService.updateAppealOrder(appealOrderId, content, memberId, memberName,pic);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
}
