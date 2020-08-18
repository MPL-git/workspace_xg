package com.jf.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ResponseMsg;
import com.jf.controller.base.BaseController;
import com.jf.service.HomeService;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年10月20日 上午10:11:43 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class HomeController extends BaseController{
	@Resource
	private HomeService homeService;
	/**
	 * 
	 * 方法描述 ：获取主页装修信息
	 * 1：首页tab栏 2：首页风格 3：首页广告 4：装修1 5：装修2
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月25日 下午6:15:36 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getHomeCategory")
	@ResponseBody
	public ResponseMsg getHomeCategory(HttpServletRequest request) {
		try {
			Map<String,Object> map = homeService.getHomeCategory(request);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	@RequestMapping(value = "/api/n/getFourCategories")
	@ResponseBody
	public ResponseMsg getFourCategories(HttpServletRequest request) {
		try {
			List<Map<String,Object>> list = homeService.getFourCategories();
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,list);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
}
