package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.controller.base.BaseController;
import com.jf.entity.CommentDrawSetting;
import com.jf.entity.CommentDrawSettingExample;
import com.jf.service.CommentDrawService;
import com.jf.service.CommentDrawSettingService;
import com.jf.service.CommentService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月28日 下午3:18:53 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class CommentController extends BaseController{
	@Resource
	private CommentService commentService;
	@Autowired
	private CommentDrawService commentDrawService;
	@Autowired
	private CommentDrawSettingService commentDrawSettingService;
	
	/**
	 * 
	 * 方法描述 ：获取子订单下可以评价的订单明细id
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月30日 下午2:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getCanCommentOrderDtl")
	@ResponseBody
	public ResponseMsg getCanCommentOrderDtl(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"subOrderId"};
			this.requiredStr(obj,request);
			
			Map<String,Object> map = commentService.getCanCommentOrderDtl(reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：添加评价
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月28日 下午3:25:48 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addMemberComment")
	@ResponseBody
	public ResponseMsg addMemberComment(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"dataList","subOrderId","type"};
			this.requiredStr(obj,request);
			
			Map<String,Object> map = commentService.addMemberComment(reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：评价抽奖
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月29日 下午3:54:05 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addMemberCommentDraw")
	@ResponseBody
	public ResponseMsg addMemberCommentDraw(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"subOrderId"};
			this.requiredStr(obj,request);
			
			commentDrawService.addMemberCommentDraw(reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：查看评价
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月29日 下午3:54:18 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getOrderCommentList")
	@ResponseBody
	public ResponseMsg getOrderCommentList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"subOrderId"};
			this.requiredStr(obj,request);
			
			Map<String,Object> map = commentService.getOrderCommentList(reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取追评商品信息
	 * @author  chenwf: 
	 * @date 创建时间：2018年7月2日 下午4:07:26 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getAppendCommentProduct")
	@ResponseBody
	public ResponseMsg getAppendCommentProduct(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"orderDtlId"};
			this.requiredStr(obj,request);
			
			Map<String,Object> map = commentService.getAppendCommentProduct(reqDataJson);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取天天乐购抽奖规则
	 * @author  chenwf: 
	 * @date 创建时间：2018年7月5日 上午11:25:56 
	 * @version
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/api/n/getLuckDrawRule")
	@ResponseBody
	public ResponseMsg getLuckDrawRule(HttpServletRequest request){
		try {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd");
			String activityDateStr = "";
			CommentDrawSettingExample commentDrawSettingExample = new CommentDrawSettingExample();
			commentDrawSettingExample.createCriteria().andStatusEqualTo("1").andBeginDateLessThanOrEqualTo(date)
			.andEndDateGreaterThanOrEqualTo(date).andDelFlagEqualTo("0");
			commentDrawSettingExample.setLimitStart(0);
			commentDrawSettingExample.setLimitSize(1);
			commentDrawSettingExample.setOrderByClause("id desc");
			List<CommentDrawSetting> drawSettings = commentDrawSettingService.selectByExample(commentDrawSettingExample);
			if(CollectionUtils.isNotEmpty(drawSettings)){
				activityDateStr = format.format(drawSettings.get(0).getBeginDate())+"至"+format.format(drawSettings.get(0).getEndDate());
			}else{
				//获取当前月第一天：
		        Calendar c = Calendar.getInstance();    
		        c.add(Calendar.MONTH, 0);
		        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		        String first = format.format(c.getTime());
		        //获取当前月最后一天
		        Calendar ca = Calendar.getInstance();    
		        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		        String last = format.format(ca.getTime());
				activityDateStr = first+"至"+last;
			}
			List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("title", "活动时间");
			dataMap.put("content", activityDateStr);
			dataList.add(dataMap);
			dataMap = new HashMap<String, String>();
			dataMap.put("title", "活动内容");
			dataMap.put("content", "醒购用户于活动期间对已购买商品进行评价，即有机会参与刮奖一次，有机会获得最高8888积分的奖励。");
			dataList.add(dataMap);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：获取商品的全部评价信息
	 * @author  chenwf: 
	 * @date 创建时间：2018年7月5日 上午11:25:56 
	 * @version
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/api/n/getUserProductAllComment")
	@ResponseBody
	public ResponseMsg getUserProductAllComment(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"productId"};
			this.requiredStr(obj,request);
			Integer productId = reqDataJson.getInt("productId");
			Integer currentPage = reqDataJson.getInt("currentPage");
			List<Integer> commentIdList = reqDataJson.getJSONArray("commentIdList");
			Integer pageSize = Const.RETURN_SIZE_20;
			Map<String, Object> map = commentService.getUserProductAllComment(productId, "2", currentPage, pageSize,commentIdList);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	/**
	 * 修改订单评价单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateOrderComment")
	@ResponseBody
	public ResponseMsg updateOrderComment(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"dataList","subOrderId","mchtScore","wuliuScore"};
			this.requiredStr(obj,request);
			commentService.updateOrderComment(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
}
