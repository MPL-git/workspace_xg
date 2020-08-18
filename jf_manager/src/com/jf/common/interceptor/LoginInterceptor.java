package com.jf.common.interceptor;

import com.jf.controller.BaseController;
import com.jf.entity.StaffBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
	private String[] noLoginURI = {
			"/login.shtml",
			"/orgMng/logsaveupdate_pwd.shtml",
			"/orgMng/logupdate_pwd.shtml",
			"/appMng/version/downloadIndex.shtml",
			"/appMng/version/download.shtml",
			"/system/getCaptcha.shtml",        //生成登录图片验证码
			"/system/sendSmsManager.shtml",    //发送短信验证
			"/system/sendSms.shtml",           //发送验证码
			"/system/clearValidCode.shtml",    //清除验证码
			"/system/mobilePhoneVerify.shtml",   //手机验证
			"/async/video/xcxCode/regenerate.shtml"
	};
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String contextPath = request.getContextPath();
		String URI = request.getRequestURI().substring(contextPath.length());
		
		for(String s : noLoginURI) {
			if(s.equals(URI)) return true;  //如果是免登录页面则直接放过
		}

		HttpSession session = request.getSession();
		Object object = session.getAttribute(BaseController.userSessionKey);
		if(object != null && object instanceof StaffBean) return true;  //说明已经登录过
		
		//否则转向登录页面
		response.sendRedirect(request.getContextPath()+"/");
		return false;
	}

}
