package com.jf.common.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jf.common.constant.BaseDefine;
import com.jf.common.utils.Base64;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.WeixinUtil;
import com.jf.entity.WeixinOauthRedirectUrl;
import com.jf.service.WeixinOauthRedirectUrlService;

import net.sf.json.JSONObject;

/**
 * 登录验证
 * 
 * @author
 * @since 2015-04-02
 */
public class WeixinFilter implements Filter {

	private WeixinOauthRedirectUrlService weixinOauthRedirectUrlService;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		
		String uri = request.getRequestURI();
		if(uri.contains("/xgbuy/views/")||uri.contains("/test/weixin/testweixin")){
			String ua = request.getHeader("user-agent").toLowerCase();
			if (ua.indexOf("micromessenger")>0&&(uri.contains("/test/weixin/testweixin")||request.getSession().getAttribute("openId")==null||"".equals(request.getSession().getAttribute("openId"))||request.getSession().getAttribute(BaseDefine.MEMBER_INFO)==null)) {
				String redirectUrl=request.getRequestURL().toString();
				JSONObject json = new JSONObject();
				if(!StringUtil.isEmpty(request.getQueryString())){
					String queryString = request.getQueryString();
					redirectUrl=redirectUrl+"?"+queryString;
					//加密解密字符串
					String str="";
					if(queryString.contains("redirect_url")){
							str = decodeOrEnCodeStr(queryString.substring(queryString.lastIndexOf("redirect_url=")+13));
							String param = str.substring(str.lastIndexOf("?")+1, str.length());
							json = buileParamsJson(param);
					}
				}
				
				if(uri.contains("/test/weixin/testweixin")){
					redirectUrl= "http://www.baidu.com";
					request.getSession().setAttribute("redirectUrl", redirectUrl);	
				}
				
				if (weixinOauthRedirectUrlService == null) {
					ServletContext servletContext = request.getSession().getServletContext();
					WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
					weixinOauthRedirectUrlService = (WeixinOauthRedirectUrlService)appContext.getBean("weixinOauthRedirectUrlService");
				}
				
				WeixinOauthRedirectUrl oauthRedirectUrl=new WeixinOauthRedirectUrl();
				oauthRedirectUrl.setDelFlag("0");
				oauthRedirectUrl.setRedirectUrl(redirectUrl);
				weixinOauthRedirectUrlService.insertSelective(oauthRedirectUrl);
				String state=oauthRedirectUrl.getId().toString();
				String callBackUrl;
				String serverName=request.getServerName();
				if("mtest.xgbuy.cc".equals(serverName)||"mtest.xinggoubuy.com".equals(serverName)||"mtest.xinggoumall.com".equals(serverName)||"m.xinggoubuy.com".equals(serverName)||"m.xinggoumall.com".equals(serverName)){
					state=state+"88888"+serverName;
					if(request.getServerPort()!=80&&request.getServerPort()!=443){
						state=state+":"+request.getServerPort();
					}
					state=state.replace(".", "0");//微信state,只能用字母和数字字符
					state=state.replace(":", "1");
					callBackUrl= request.getScheme()+"://m.xgbuy.cc/webApp/weixin/inner/weixinOauthCallback";
				}else{
					callBackUrl= request.getScheme()+"://" + request.getServerName() + request.getContextPath()+"/weixin/weixinOauthCallback";
				}
				callBackUrl=URLEncoder.encode(callBackUrl,"utf-8");
				System.out.println("state1------:"+state);
				if(!JsonUtils.isEmpty(json, "invitationCode")){
					state+= "88888"+json.getString("invitationCode");
					System.out.println("state2------:"+state);
				}
				response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeixinUtil.appId+"&redirect_uri="+callBackUrl+"&response_type=code&scope=snsapi_userinfo&state="+state.toString()+"#wechat_redirect");
				return;
			}
			
		}
		chain.doFilter(request, response);
	}

	private String decodeOrEnCodeStr(String queryString) {
		String str = "";
		try {
			str = new String(Base64.decode(queryString));
//			System.out.println(queryString+":解密成功："+str);
		} catch (Exception e) {
			str = queryString;
//			System.out.println(queryString+":解密失败");
		}
		return str;
	}

	public JSONObject buileParamsJson(String param) {
		JSONObject json = new JSONObject();
		if(!StringUtil.isBlank(param)){
			String[] paramStr = param.split("&");
			for (String str : paramStr) {
				if(!StringUtil.isBlank(str)){
					String[] listStr = str.split("=");
					if(listStr.length >= 2){
						json.put(listStr[0], listStr[1]);
					}
				}
			}
		}
		return json;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	public static void main(String[] args) {
		String string = "redirect_url=YWN0aXZpdHkvbm92YXBsYW4vcGFnZXMvaW52aXRlL3NoYXJlL2luZGV4Lmh0bWw/aW52aXRhdGlvbkNvZGU9MTIyNDEmZnJvbT1zaW5nbGVtZXNzYWdl";
		System.out.println(string.substring(string.lastIndexOf("redirect_url=")+13));
	}
	
	
}
