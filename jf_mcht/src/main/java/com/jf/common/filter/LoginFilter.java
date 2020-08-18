package com.jf.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jf.common.constant.BaseDefine;
import com.jf.entity.MchtUser;

/**
 * 登录验证
 * 
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		
		// 未登录则跳转登录页面
		if ((session.getAttribute(BaseDefine.MCHT_USER) == null||session.getAttribute(BaseDefine.MCHT_INFO) == null)&&!uri.contains("/getCaptcha") && !uri.contains("/toLogin")&& !uri.contains("/login")&&!uri.contains("/static/")&& !uri.contains("/getMobileValidateCode")&& !uri.contains("/checkExisit")&& !uri.contains("/saveFindPassword")&& !uri.contains("/live/auth")&&!uri.contains("/designTaskOrder/aliPayNotifyDesignUrl")) {
			java.io.PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<script>");
			out.println("window.open ('"+request.getContextPath()+"/toLogin','_top')");
			out.println("</script>");
			out.println("</html>");
			return;
		} else {
			//如果密码为初始密码123456，则跳到修改密码页面
			if(session.getAttribute(BaseDefine.MCHT_USER) !=null&&((MchtUser)session.getAttribute(BaseDefine.MCHT_USER)).getPassword().equals("e10adc3949ba59abbe56e057f20f883e")&& !uri.contains("/static/") && !uri.contains("/toLogin") && !uri.contains("/loginOut")&&!uri.contains("/mchtUser/checkUserCode")&&!uri.contains("/mchtUser/checkUserMobile")&&!uri.contains("/common/getMobileValidateCode")&&!uri.contains("/mchtUser/setUserInfoCommit")&&!uri.contains("/designTaskOrder/aliPayNotifyDesignUrl")){
			java.io.PrintWriter out = response.getWriter();
		    out.println("<html>");
		    out.println("<script>");
		    out.println("window.open ('"+request.getContextPath()+"/toLogin','_top')");
		    out.println("</script>");
		    out.println("</html>");
		    return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
