package com.jf.common.interceptor;

import com.jf.common.ext.aop.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hdl on 2017/4/8 0008.
 * @author hdl
 */
public class LoginInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void intercept(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        logger.info("Controller级别拦截器，可以配在Controller方法上");

    }


}
