package com.jf.common.ext.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor.
 * @author hdl
 */
public interface Interceptor {
	void intercept(HttpServletRequest request, HttpServletResponse response);
}

