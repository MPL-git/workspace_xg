package com.jf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.entity.MemberInfo;
import com.jf.service.MemberInfoService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年11月14日 下午3:38:37 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class WecharController extends BaseController{
	@Resource
	private MemberInfoService memberInfoService;
	
	@RequestMapping(value = "/n/getWecharLoginInfo")
	@ResponseBody
	public String getWecharLoginInfo(HttpServletRequest request){
		try {
			String code = request.getParameter("code");
			List<Object> list = accessToken(code);
			String openId = list.get(1).toString();
			request.getSession().setAttribute(BaseDefine.OPENID, openId);
			System.out.println("微信:"+openId);
			return "";
		}catch (ArgException args){
			return "";
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public List<Object> accessToken(String code){
		try {
			List<Object> list = new ArrayList<Object>();
	        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx6bc48834aedc7f7d&secret=af7efb56791cae1658d216a56865368f&code=" + code + "&grant_type=authorization_code";
	        HttpClient client = new DefaultHttpClient();
	        HttpPost post = new HttpPost(url);
	        HttpResponse res = client.execute(post);
	        if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            HttpEntity entity = res.getEntity();
	            String str = org.apache.http.util.EntityUtils.toString(entity, "utf-8");
	            ObjectMapper mapper=new ObjectMapper();
	            Map<String,Object> jsonOb=mapper.readValue(str, Map.class);
	            list.add(jsonOb.get("access_token"));
	            list.add(jsonOb.get("openid"));
	        }
	        return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
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
			
			Object[] obj = {"weixinUnionId"};
			this.requiredStr(obj, request);
			String weixinUnionId = reqDataJson.getString("weixinUnionId");
			Integer memberId = getMemberId(request);
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
