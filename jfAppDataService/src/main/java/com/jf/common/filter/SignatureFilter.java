package com.jf.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
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

import net.sf.json.JSONObject;

import com.jf.common.utils.DateUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.SignatureUtil;

/**
 * 登录验证
 * 
 * @author
 * @since 2015-04-02
 */
public class SignatureFilter implements Filter {
	
	private static ArrayList<String> signatureList=new ArrayList<String>();

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		JSONObject paramJson = (JSONObject)request.getAttribute("reqPRM");
		if(!paramJson.getString("version").equals("0") ) { //版本为0 是H5 不做签名验证
			Map<String, String> paramMap = new HashMap<String, String>();
			if(!JsonUtils.isEmpty(paramJson, "token") ) {
				paramMap.put("token", paramJson.getString("token"));
			}
			String timeStamp = paramJson.getString("timeStamp");
			String nonceStr = paramJson.getString("nonceStr");
			paramMap.put("timeStamp", timeStamp);
			paramMap.put("nonceStr", nonceStr);
			paramMap.put("reqData", paramJson.getString("reqData"));
			String signature = paramJson.getString("signature");
			String signatureCheckStr = timeStamp+nonceStr+signature;
			if(signatureList.contains(signatureCheckStr) ) {
				JSONObject resultJson = new JSONObject();
				resultJson.put("returnCode", "4004");
				resultJson.put("returnMsg", "您的手速太快了");
				response.getWriter().write(resultJson.toString());
				return;
			} else {
				if(signatureList.size() < 2000 ) {//控制list数量为2000
					signatureList.add(signatureCheckStr);
				}else{
					signatureList.add(signatureCheckStr);
					signatureList.remove(0);
				}
			}
			if(signatureList.size() > 4000 ) {//防止超高并发导致无限增长
				signatureList.clear();
			}
			if(!SignatureUtil.checkSignature(paramMap, signature) ) {
				JSONObject resultJson = new JSONObject();
				resultJson.put("returnCode", "3001");
				resultJson.put("returnMsg", "出错啦");
				response.getWriter().write(resultJson.toString());
				return;
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
	
		public static void main(String[] args) {
			long b=new Date().getTime();
			long a=new Date().getTime()/1000;
			System.out.println(b);
			System.out.println(a);
			long x=1532403715*1000l;
			Date date =new Date(x);
//			Date date =new Date(a*1000);
			System.out.println(DateUtil.getStandardDateTime(new Date(b)));
			System.out.println(DateUtil.getStandardDateTime(date));
		}
}
