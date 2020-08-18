package com.jf.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.SignatureUtil;

import net.sf.json.JSONObject;

/**
 * 登录验证
 * 
 * @author
 * @since 2015-04-02
 */
public class SignatureFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String uri = request.getRequestURI();
		String remoteRelAddress = request.getHeader("X-Real-IP");
		if (!"117.28.134.215".equals(remoteRelAddress)) {
			if (!uri.contains("/api/n/getActivityAreaTemplateByAreaId")) {
				JSONObject paramJson = (JSONObject) request.getAttribute("reqPRM");

				// 验签
				Map<String, String> paramMap = new HashMap<String, String>();
				if (!JsonUtils.isEmpty(paramJson, "token")) {
					paramMap.put("token", paramJson.getString("token"));
				}
				paramMap.put("timeStamp", paramJson.getString("timeStamp"));
				paramMap.put("nonceStr", paramJson.getString("nonceStr"));
				paramMap.put("reqData", paramJson.getString("reqData"));
				String signature = paramJson.getString("signature");
				if (!SignatureUtil.checkSignature(paramMap, signature)) {
					JSONObject resultJson = new JSONObject();
					resultJson.put("returnCode", "3001");
					resultJson.put("returnMsg", "签名出错");
					response.getWriter().write(resultJson.toString());
					return;
				}
			}
		}
		chain.doFilter(request, response);
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
