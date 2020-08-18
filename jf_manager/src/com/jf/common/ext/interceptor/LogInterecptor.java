package com.jf.common.ext.interceptor;

import com.jf.common.ext.aop.Interceptor;
import com.jf.common.ext.aop.InterceptorBuilder;
import com.jf.common.ext.aop.Interceptors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 请求日志
 * 
 * @author hdl
 *
 */
public class LogInterecptor extends HandlerInterceptorAdapter {

	private static final Log logger = LogFactory.getLog(LogInterecptor.class);

	private static final ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod))    return true;

		HandlerMethod handlerMethod = (HandlerMethod) handler;


		StringBuilder sb = new StringBuilder("\nSpring action report -------- ").append(sdf.get().format(new Date())).append(" ------------------------------\n");
		Class<?> cc = handlerMethod.getBeanType();
		sb.append("Controller  : ").append(cc.getName()).append(".(").append(cc.getSimpleName()).append(".java:1)");
		sb.append("\nMethod      : ").append(handlerMethod.getMethod().getName()).append("\n");

		// 过滤打上传图片不打印日志
		if(cc.getSimpleName().equals("CommonController")){
			return true;
		}

		Interceptors interceptors = handlerMethod.getMethodAnnotation(Interceptors.class);
		Interceptor[] inters = new InterceptorBuilder().createInterceptors(interceptors);
		if (inters.length > 0) {
			sb.append("Interceptor : ");
			for (int i=0; i<inters.length; i++) {
				if (i > 0)
					sb.append("\n              ");
				Interceptor inter = inters[i];
				Class<? extends Interceptor> ic = inter.getClass();
				sb.append(ic.getName()).append(".(").append(ic.getSimpleName()).append(".java:1)");
			}
			sb.append("\n");
		}

		// print all parameters
		Enumeration<String> e = request.getParameterNames();
		if (e.hasMoreElements()) {
			sb.append("Parameter   : ");
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				String[] values = request.getParameterValues(name);
				if (values.length == 1) {
					sb.append(name).append("=").append(values[0]);
				}
				else {
					sb.append(name).append("[]={");
					for (int i=0; i<values.length; i++) {
						if (i > 0)
							sb.append(",");
						sb.append(values[i]);
					}
					sb.append("}");
				}
				sb.append("  ");
			}
			sb.append("\n");
		}
		sb.append("--------------------------------------------------------------------------------");
		logger.debug(sb.toString());

		return true;
	}



}
