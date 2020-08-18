package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.service.ToutiaoAdvertiserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TouTiaoInterfaceController {

	@Autowired
	private ToutiaoAdvertiserInfoService toutiaoAdvertiserInfoService;
	
	/**
	 * 
	 * @Title oauthBack   
	 * @Description TODO(头条推广授权)   
	 * @author pengl
	 * @date 2018年8月10日 下午5:52:28
	 */
	@ResponseBody
    @RequestMapping("/toutiao/oauthBack")
	public ResponseMsg oauthBack(HttpServletRequest request) {
		return toutiaoAdvertiserInfoService.oauthBack(request);
	}
	
	/**
	 * 
	 * @Title accessToken   
	 * @Description TODO(头条推广获取access_token)   
	 * @author pengl
	 * @date 2018年8月11日 上午11:06:46
	 */
	@ResponseBody
	@RequestMapping("/toutiao/accessToken")
	public ResponseMsg accessToken(HttpServletRequest request) {
		return toutiaoAdvertiserInfoService.accessToken(request);
	}
	
	/**
	 * 
	 * @Title refreshToken   
	 * @Description TODO(刷新access_token)   
	 * @author pengl
	 * @date 2018年8月11日 上午11:11:54
	 */
	@ResponseBody
	@RequestMapping("/toutiao/refreshToken")
	public ResponseMsg refreshToken(HttpServletRequest request) {
		String tokenId = request.getParameter("tokenId");
		return toutiaoAdvertiserInfoService.refreshToken(tokenId);
	}
	
	/**
	 * 
	 * @Title advertiserInfoList   
	 * @Description TODO(广告主信息)   
	 * @author pengl
	 * @date 2018年8月14日 下午3:22:59
	 *//*
	@ResponseBody
	@RequestMapping("/toutiao/advertiserInfoList")
	public ResponseMsg advertiserInfoList(HttpServletRequest request) {
		return toutiaoAdvertiserInfoService.advertiserInfoList();
	}
	
	*//**
	 * 
	 * @Title campaignGetList   
	 * @Description TODO(获取广告组（新）)   
	 * @author pengl
	 * @date 2018年8月11日 下午2:35:26
	 *//*
	@ResponseBody
	@RequestMapping("/toutiao/campaignGetList")
	public ResponseMsg campaignGetList() {
		return toutiaoAdvertiserInfoService.campaignGetList();
	}
	
	*//**
	 * 
	 * @Title adGetList   
	 * @Description TODO(获取广告计划（新）)   
	 * @author pengl
	 * @date 2018年8月13日 下午2:43:46
	 *//*
	@ResponseBody
	@RequestMapping("/toutiao/adGetList")
	public ResponseMsg adGetList() {
		return toutiaoAdvertiserInfoService.adGetList();
	}*/
	
	
	
	
	
	
	
	
	
	
}
