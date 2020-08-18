package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.controller.base.BaseController;
import com.jf.entity.MemberInfo;
import com.jf.service.MemberInfoService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年1月2日 上午10:15:13 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class WecharController extends BaseController{
	
	@Resource
	private MemberInfoService memberInfoService;
	
	/**
	 * 
	 * 方法描述 ：绑定微信
	 * @author  chenwf: 
	 * @date 创建时间：2018年1月2日 上午10:19:11 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/bindingWeChat")
	@ResponseBody
	public ResponseMsg bindingWeChat(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"weixinUnionId","memberId"};
			this.requiredStr(obj, request);
			String weixinUnionId = reqDataJson.getString("weixinUnionId");
			Integer memberId = reqDataJson.getInt("memberId");
			MemberInfo memberInfo = memberInfoService.updateweixinUnionId(weixinUnionId,memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,memberInfo);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
