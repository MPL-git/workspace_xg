package com.jf.controller;

import java.util.ArrayList;
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
import com.jf.service.MemberActivityFootprintService;
import com.jf.service.MemberFootprintService;
import com.jf.service.MemberShopFootprintService;

import net.sf.json.JSONObject;

/**
  * 足迹 
  * @author  chenwf: 
  * @date 创建时间：2017年4月21日 上午9:25:30 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class MemberFootprintController extends BaseController{
	
	@Resource
	private MemberFootprintService memberFootprintService;
	@Resource
	private MemberActivityFootprintService memberActivityFootprintService;
	@Resource
	private MemberShopFootprintService memberShopFootprintService;
	
	/**
	 * 
	 * 方法描述 ：获取我的足迹列表 
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 上午10:16:16 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberFootprintList")
	@ResponseBody
	public ResponseMsg getMemberFootprintList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			String memberId = getMemberIdStr(request);//会员标识id
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			int reqSource = reqDataJson.optInt("reqSource");
			int currentProductId = reqDataJson.optInt("currentProductId");
			Integer pageSize = reqDataJson.optInt("pageSize");
			if (pageSize <= 0) {
				pageSize = Const.RETURN_SIZE_10;//页数
			}
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			String type = "1";//1商品2店铺3特卖
			if(reqDataJson.containsKey("type") && !StringUtil.isBlank(reqDataJson.getString("type"))){
				type = reqDataJson.getString("type");
			}
			if("1".equals(type)){
				dataList = memberFootprintService.getMemberFootprintList(memberId, currentPage, pageSize, reqSource, currentProductId);
			}else if("2".equals(type)){
				dataList = memberShopFootprintService.getMemberShopFootprintList(memberId,currentPage,pageSize);
			}else if("3".equals(type)){
				dataList = memberActivityFootprintService.getMemberActivityFootprintList(memberId,currentPage,pageSize);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：添加我的足迹
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月22日 下午7:37:13 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addMemberFootprint")
	@ResponseBody
	public ResponseMsg addMemberFootprint(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"productId"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);//会员标识id
			Integer productId = reqDataJson.getInt("productId");//商品id
			String type = "1";//1商品2店铺3特卖
			if(reqDataJson.containsKey("type") && !StringUtil.isBlank(reqDataJson.getString("type"))){
				type = reqDataJson.getString("type");
			}
			if("1".equals(type)){
				memberFootprintService.addMemberFootprint(memberId,productId);
			}else if("2".equals(type)){
				memberShopFootprintService.addMemberShopFootprint(memberId, productId);
			}else if("3".equals(type)){
				memberActivityFootprintService.addMemberActivityFootprint(memberId, productId);
			}
			
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
	 * 方法描述 ：删除我的足迹
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月23日 下午3:27:42 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/deleteMemberFootprint")
	@ResponseBody
	public ResponseMsg deleteMemberFootprint(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);//会员标识id

			String type = "1";//1商品2店铺3特卖
			if(reqDataJson.containsKey("type") && !StringUtil.isBlank(reqDataJson.getString("type"))){
				type = reqDataJson.getString("type");
			}
			if("1".equals(type)){
				memberFootprintService.deleteMemberFootprint(memberId);
			}else if("2".equals(type)){
				memberShopFootprintService.deleteMemberShopFootprint(memberId);
			}else if("3".equals(type)){
				memberActivityFootprintService.deleteMemberActivityFootprint(memberId);
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
