package com.jf.common.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 字符过滤
 * 
 * @author
 * @since 2015-04-02
 */
public class CharFilter implements Filter {
	

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest)req;
		 String uri = request.getRequestURI();
		 if(!uri.contains("infomation/saveinformation.shtml") && !uri.contains("/work/addworkremarks.shtml")
				 && !uri.contains("/work/addreplys.shtml") && !uri.contains("/approvalProcessManagement/saveMyApplication.shtml")
				 && !uri.contains("/approvalProcessManagement/saveApproval.shtml")&&!uri.contains("service/common/fileUpload.shtml") 
				 && !uri.contains("/customPage/saveDecorateModule.shtml")){
			 CharFilterRequest wrapRequest= new CharFilterRequest(request,request.getParameterMap());
			 chain.doFilter(wrapRequest, resp);
		 }else{
			 chain.doFilter(request, resp);
		 }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	

}
