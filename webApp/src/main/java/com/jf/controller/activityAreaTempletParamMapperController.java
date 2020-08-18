package com.jf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.Config;
import com.jf.common.utils.StringUtil;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaTempletParam;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityAreaTempletParamService;
import com.jf.service.CombineOrderService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年11月9日 上午9:24:36 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class activityAreaTempletParamMapperController extends BaseController{
	@Resource
	private ActivityAreaTempletParamService activityAreaTempletParamService;
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private ActivityAreaService activityAreaService;
	/**
	 * 
	 * 方法描述 ：获取会场模板JS
	 * @author  chenwf: 
	 * @date 创建时间：2018年2月11日 上午9:29:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getActivityAreaTempletCode")
	@ResponseBody
	public ResponseMsg getActivityAreaTempletCode(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"suffix","activityAreaId"};
			this.requiredStr(obj, request);
			String suffix = reqDataJson.getString("suffix");
			Integer activityAreaId = reqDataJson.getInt("activityAreaId");
			QueryObject queryObject = new QueryObject();
			queryObject.addQuery("suffix", suffix);
			queryObject.addQuery("activityAreaId", activityAreaId);
			List<ActivityAreaTempletParam> activityAreaTempletParams = activityAreaTempletParamService.findListByQuery(queryObject);
			Map<String,String> map = new HashMap<String,String>();
			String code = "";
			if(CollectionUtils.isNotEmpty(activityAreaTempletParams)){
				code = activityAreaTempletParams.get(0).getCode();
			}
			map.put("code", code);
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
	 * 方法描述 ：获取会场模板
	 * @author  chenwf: 
	 * @date 创建时间：2018年1月19日 下午2:17:05 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getActivityAreaTemplateByAreaId")
	@ResponseBody
	public ResponseMsg getActivityAreaTemplateByAreaId(HttpServletRequest request){
		try {
			Integer activityAreaId = null;
			if(!StringUtil.isBlank(request.getParameter("activityAreaId"))){
				activityAreaId = Integer.parseInt(request.getParameter("activityAreaId"));
			}else{
				JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
				JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
				
				Object[] obj = {"activityAreaId"};
				this.requiredStr(obj, request);
				activityAreaId = reqDataJson.getInt("activityAreaId");
			}
			String areaUrl = "";
			String memberId = this.getMemberIdStr(request);
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
			if(activityArea != null && !StringUtil.isBlank(activityArea.getTempletSuffix())){
				areaUrl = Config.getProperty("mUrl")+activityArea.getTempletSuffix()+"?activityAreaId="+activityArea.getId()+"&currentPage=0&pageSize=10&version=1.0&memberId=";
				if(!StringUtil.isBlank(memberId)){
					areaUrl += memberId;
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("areaUrl", areaUrl);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
