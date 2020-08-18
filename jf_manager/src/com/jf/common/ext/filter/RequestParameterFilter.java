package com.jf.common.ext.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jf.common.ext.util.ArgKit;
import com.jf.common.ext.util.Util;

/**
 * 把所有请求参数都复制一份到ArgKit，记录请求时间
 * 
 * @author hdl
 *
 */
public class RequestParameterFilter implements Filter {

	private static final Log logger = LogFactory.getLog(RequestParameterFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String uri = request.getRequestURI();
		// 未登录则跳转登录页面
		if (!uri.contains(".shtml")) {
			chain.doFilter(req, resp);
			return;
		}

			logger.debug("\n-------------------------------------------request start-------------------------------------------");
			logger.debug("request ip: " + Util.getRealIp(request));
			logger.debug("user agent: " + request.getHeader("User-Agent"));
			logger.debug("referer: " + request.getHeader("referer"));

		Map<String, Object> argMap = new HashMap<String, Object>();

		handleHeaders(request, argMap);
		handleParams(request, argMap);

		ArgKit.setArgsMap(argMap);

		try {
			long startTime = System.currentTimeMillis();

			chain.doFilter(req, resp);

			long endTime = System.currentTimeMillis();
			logger.debug("本次请求[" + uri + "]处理时间：[" + (endTime - startTime) + "]毫秒");

		} finally {
			ArgKit.removeArgsMap();
		}

		if (logger.isInfoEnabled()) {
			logger.debug("\n-------------------------------------------request   end-------------------------------------------\n");
		}

	}

	@Override
	public void destroy() {

	}

	/**
	 * 处理参数
	 * 
	 * @param request
	 * @param argMap
	 */
	private void handleParams(HttpServletRequest request, Map<String, Object> argMap) {
		Map<String, String[]> paraMap = request.getParameterMap();
		for (Iterator<String> iter = paraMap.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
//			String[] values = paraMap.get(name);
			String[] values=request.getParameterValues(name);
			String value = values[0];
			if (values.length > 1) {
				StringBuilder sb = new StringBuilder("{");
				for (int i = 0; i < values.length; i++) {
					if (i > 0)
						sb.append(",");
					sb.append(values[i]);
				}
				sb.append("}");

				value = sb.toString();
			}

			request.setAttribute(name, value);
			argMap.put(name, value);
		}
	}

	/**
	 * 处理头部信息
	 * 
	 * @param request
	 */
	private void handleHeaders(HttpServletRequest request, Map<String, Object> argMap) {
		String argValue = null;
		String header = null;

		Enumeration<?> headers = request.getHeaderNames();
		try {
			while (headers.hasMoreElements()) {
				header = headers.nextElement().toString();
				argValue = request.getHeader(header);

				// 有值才处理
				if (argValue != null) {
					argMap.put(header, argValue);
				}
			}
			
		} catch (Exception e) {
			logger.error("header name: " + header + ", value: " + argValue);
		}
	}

}
