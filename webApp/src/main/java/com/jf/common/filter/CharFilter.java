package com.jf.common.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jf.common.base.CharFilterRequest;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;

import net.sf.json.JSONObject;

/**
 * 字符过滤
 * 
 * @author
 * @since 2015-04-02
 */
public class CharFilter implements Filter {
	private static final Logger logger = Logger.getLogger(CharFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest)req;
//		 logger.debug("-------------获取的ip------"+StringUtil.getIpAddr(request));
//		 logger.debug("-------------X-Real-IP------"+request.getHeader("X-Real-IP"));
//		 logger.debug("-------------X-Forwarded-For------"+request.getHeader("X-Forwarded-For"));
//		 logger.debug("-------------Proxy-Client-IP------"+request.getHeader("Proxy-Client-IP"));
//		 logger.debug("-------------WL-Proxy-Client-IP------"+request.getHeader("WL-Proxy-Client-IP"));
//		 logger.debug("-------------HTTP_CLIENT_IP------"+request.getHeader("HTTP_CLIENT_IP"));
//		 logger.debug("-------------HTTP_X_FORWARDED_FOR------"+request.getHeader("HTTP_X_FORWARDED_FOR"));
//		 logger.debug("-------------RemoteAddr------"+StringUtil.getIpAddr(request));
		 
         CharFilterRequest wrapRequest= new CharFilterRequest(request,request.getParameterMap());
         if(request.getAttribute("reqPRM")!=null){
        	 JSONObject paramJson=(JSONObject)request.getAttribute("reqPRM");
        	//兼容ios 版本
     		if(paramJson.containsKey("system") && !StringUtil.isBlank(paramJson.getString("system")) && paramJson.getString("system").equals(Const.IOS)){
     			if(paramJson.containsKey("version") && !StringUtil.isBlank(paramJson.getString("version"))){
     				String version = paramJson.getString("version");
     				boolean isInt = Pattern.compile("^[1-9]+\\d*$").matcher(version).find();
     		        if(!isInt){
     		        	Map<String,String> map = getVersionList();
     		        	if(map.containsKey(version)){
     		        		paramJson.put("version", map.get(version));
     		        	}
     		        }
     			}
     		} 
         }
         chain.doFilter(wrapRequest, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	private Map<String,String> getVersionList() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("1.0.2", "7");
		map.put("1.0.3", "7");
		map.put("1.0.4", "7");
		map.put("1.0.5", "7");
		map.put("1.0.6", "7");
		map.put("1.0.7", "8");
		map.put("1.0.8", "9");
		map.put("1.0.9", "11");
		map.put("1.1.0", "12");
		map.put("1.1.1", "13");
		map.put("1.1.2", "14");
		map.put("1.1.3", "15");
		map.put("1.1.4", "16");
		map.put("1.1.5", "16");
		map.put("1.1.6", "19");
		map.put("1.1.7", "20");
		map.put("1.1.8", "21");
		map.put("1.1.9", "22");
		map.put("1.2.0", "23");
		map.put("2.0.1", "24");
		map.put("2.0.2", "25");
		map.put("2.0.3", "26");
		map.put("2.0.4", "27");
		map.put("2.0.5", "28");
		map.put("2.0.6", "29");
		map.put("2.0.7", "30");
		map.put("2.0.8", "31");
		map.put("2.0.9", "32");
		map.put("2.1.0", "33");
		map.put("2.1.1", "34");
		map.put("2.1.2", "35");
		map.put("2.1.3", "36");
		map.put("2.1.4", "37");
		map.put("2.1.5", "38");
		map.put("2.1.6", "39");
		map.put("2.1.7", "40");
		map.put("2.1.8", "41");
		map.put("2.1.9", "42");
		//之后的版本这边可以不用加，因为ios传过来的是value值
		return map;
	}
}
