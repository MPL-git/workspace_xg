package com.jf.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import net.sf.json.JSONObject;

import com.jf.common.base.CharFilterRequest;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;

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
         if(request.getAttribute("reqPRM")!=null){
        	 JSONObject paramJson=(JSONObject)request.getAttribute("reqPRM");
        	 for (Object key:paramJson.keySet()) {
				String value=paramJson.getString(key.toString());
				if(!StringUtil.isEmpty(value)){
					value=StringUtil.escapeHtmlAndIllegal(value);//过滤禁用符号
					value=StringUtil.filterEmoji(value);//过滤表情符
					paramJson.put(key, value);
				}
			}
 
         }
         chain.doFilter(wrapRequest, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
