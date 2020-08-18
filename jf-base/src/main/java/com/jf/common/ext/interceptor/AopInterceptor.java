package com.jf.common.ext.interceptor;

import com.jf.common.ext.aop.Interceptor;
import com.jf.common.ext.aop.InterceptorBuilder;
import com.jf.common.ext.aop.Interceptors;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 切面拦截
 * @author hdl
 */
public class AopInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod))    return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Interceptors interceptors = handlerMethod.getMethodAnnotation(Interceptors.class);
        Interceptor[] methodInters = new InterceptorBuilder().createInterceptors(interceptors);

        if (methodInters.length == 0)    return true;

        for(Interceptor interceptor : methodInters){
            interceptor.intercept(request, response);
        }
        return super.preHandle(request, response, handler);
    }


}