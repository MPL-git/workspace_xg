package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ResponseMsg;
import com.jf.service.LandingPageService;
import com.jf.vo.request.LandingPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pengl
 * @create 2020-06-28 下午 2:48
 */
@Controller
public class LandingPageController {

	@Autowired
	private LandingPageService landingPageService;
	@Autowired
	private AppContext appContext;

	/**
	 * 记录落地页渠道信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/n/landingPage/saveLandingPage")
	public ResponseMsg saveLandingPage(HttpServletRequest request) {
		LandingPageRequest landingPageRequest = appContext.getRequest(LandingPageRequest.class);
		landingPageService.saveLandingPage(request, landingPageRequest);
		return ResponseMsg.success();
	}



}
