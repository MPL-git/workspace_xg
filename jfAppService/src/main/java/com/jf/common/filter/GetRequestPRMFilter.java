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
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.jf.common.utils.StringUtil;

import net.sf.json.JSONObject;

/**
 * 登录验证
 * 
 * @author
 * @since 2015-04-02
 */
public class GetRequestPRMFilter implements Filter {
	private static final Logger logger = Logger.getLogger(GetRequestPRMFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setContentType("text/html; charset=UTF-8");
		String paramStr=getParamString(request);
		logger.debug("-----------------获取的ip:-----"+StringUtil.getIpAddr(request)+"---------------------");
		logger.debug("----------------request url and  param--------------------");
		logger.debug("url: " +request.getRequestURI());		
		logger.debug(paramStr);

		
		JSONObject paramJson=JSONObject.fromObject(paramStr);
		req.setAttribute("reqPRM", paramJson);
		
		long startTime = System.currentTimeMillis();

		chain.doFilter(request, response);

		long endTime = System.currentTimeMillis();
		logger.debug("本次请求[" + request.getRequestURI() + "]处理时间：[" + (endTime - startTime) + "]毫秒");
		
	}

	

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	
	private String getParamString(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		try {
			inputStream = request.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			inputStream = null;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				inputStreamReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}
	
}
