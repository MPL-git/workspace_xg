package com.jf.controller.base;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.jf.common.base.ArgException;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月19日 下午6:08:50 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class BaseController {
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
	
	public static void main(String[] args) {
		Object[] obj = {"memberId","mobile"};
	}
}
