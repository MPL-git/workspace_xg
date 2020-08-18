package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ResponseMsg;
import com.jf.service.LandingPageService;
import com.jf.vo.request.GetLandingPageRequest;
import com.jf.vo.request.LandingPageRequest;
import com.jf.vo.response.LandingPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Pengl
 * @create 2020-06-28 上午 9:58
 */
@Controller
public class LandingPageController {

	@Autowired
	private LandingPageService landingPageService;

	@Autowired
	private AppContext appContext;

	/**
	 * 获取落地页信息
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/n/landingPage/getLandingPage")
	public ResponseMsg getLandingPage() {
		GetLandingPageRequest getLandingPageRequest = appContext.getRequest(GetLandingPageRequest.class);
		LandingPageResponse landingPageResponse = landingPageService.getLandingPage(getLandingPageRequest);
		return ResponseMsg.success(landingPageResponse);
	}

	/**
	 * 记录落地页访问信息
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/n/landingPage/saveLandingPageH5Info")
	public ResponseMsg saveLandingPageH5Info(HttpServletRequest request) {
		LandingPageRequest landingPageRequest = appContext.getRequest(LandingPageRequest.class);
		Map<String, Object> map = landingPageService.saveLandingPageH5Info(request, landingPageRequest);
		return ResponseMsg.success(map);
	}

}
