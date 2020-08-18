package com.jf.controller.base;

import com.alibaba.fastjson.JSONArray;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Constant;
import com.jf.common.ext.util.JsonKit;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.Config;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.common.vo.CurrentMember;
import net.sf.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月19日 下午6:08:50 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class BaseController {
	
	public static String CONTEXTPATH = Config.getProperty("CONTEXTPATH");
	
	/**
	 * 
	 * 方法描述 ：request请求字段验证
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月22日 上午10:18:48 
	 * @version
	 * @param obj
	 * @param request
	 * @throws ArgException
	 */
	public void requiredStr(Object[] obj, HttpServletRequest request) throws ArgException{
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		if(obj != null){
			for(Object object : obj) {
				if(JsonUtils.isEmpty(reqDataJson, object.toString())){
					throw new ArgException("reqData字段"+object+"不能为空");
				}
				if(StringUtil.isBlank(reqDataJson.getString(object.toString()))){
					throw new ArgException(object+"不能为空");
				}
			}
		}
	}
	
	/**
	 * 
	 * 方法描述 ：null值更改为""
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月22日 上午10:23:19 
	 * @version
	 * @param obj
	 * @return
	 */
	public Object setStrByNull(Object obj){
		if(obj == null){
			obj = "";
		}
		return obj;
	}
	
	public JSONObject getParams(){
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		JSONObject reqPRM = (JSONObject)request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		return reqDataJson;
	}

	public String getSystem(){
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		JSONObject reqPRM = (JSONObject)request.getAttribute("reqPRM");
		return reqPRM.getString("system");
	}

	public int getVersion(){
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		JSONObject reqPRM = (JSONObject)request.getAttribute("reqPRM");
		return reqPRM.optInt("version");
	}

	public <T> List<T> getParaToList(JSONObject reqDataJson, String name, Class<T> clazz) {
		String str = reqDataJson.getString(name);
		if (StrKit.isBlank(str) || str.equals("[]")) {
			return new ArrayList<>();
		}

		return JSONArray.parseArray(str, clazz);
	}

	public <T> T getParaToBean(JSONObject reqDataJson, String name, Class<T> clazz) {
		String str = reqDataJson.getString(name);
		if (StrKit.isBlank(str)) {
			return null;
		} else {
			return com.alibaba.fastjson.JSONObject.parseObject(str, clazz);
		}
	}


	public String page(String page) {
		return page;
	}

	public String page(Map<String, Object> data, String page) {
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Set<String> keySet = data.keySet();
		for (String key : keySet) {
			request.setAttribute(key, data.get(key));
		}

		return page;
	}

	public String json() {
		Map<String, Object> renderMap = new HashMap<String, Object>();
		renderMap.put("returnCode", ResponseMsg.SUCCESS);
		renderMap.put("returnMsg", ResponseMsg.SUCCESS_MSG);
		return JsonKit.toJson(renderMap);
	}

	public String json(Map<String, Object> data) {
		Map<String, Object> renderMap = new HashMap<String, Object>();
		renderMap.put("returnCode", ResponseMsg.SUCCESS);
		renderMap.put("returnMsg", ResponseMsg.SUCCESS_MSG);
		renderMap.put("returnData", data);
		return JsonKit.toJson(renderMap);
	}


}
