package com.jf.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.jf.common.base.CharFilterRequest;

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
         CharFilterRequest wrapRequest= new CharFilterRequest(request,request.getParameterMap());
         chain.doFilter(wrapRequest, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	

}
