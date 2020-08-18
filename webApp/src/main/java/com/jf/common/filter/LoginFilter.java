package com.jf.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.ErrorConst;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MemberInfo;
import com.jf.service.MemberInfoService;

import net.sf.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 登录验证
 *
 * @author
 * @since 2015-04-02
 */
public class LoginFilter implements Filter {

    private MemberInfoService memberInfoService;

    private int tokenInvalidTime = 525600;//token失效时长，以分钟为单位

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        try {
            String uri = request.getRequestURI();
            if (!uri.contains("api/y/loginOut")) {
                if (request.getSession().getAttribute(BaseDefine.MEMBER_INFO) == null) {

                    //用于接口测试使用，需要传memberId与token
                    if (checkIsTestRequest(request)) {
                        chain.doFilter(request, response);
                        return;
                    }

                    JSONObject resultJson = new JSONObject();
                    resultJson.put("returnCode", "1001");
                    resultJson.put("returnMsg", "请登录");
                    response.getWriter().write(resultJson.toString());
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject resultJson = new JSONObject();
            resultJson.put("returnCode", ErrorConst.FILTER_ERROR_CODE);
            resultJson.put("returnMsg", ErrorConst.FILTER_ERROR_MSG);
            response.getWriter().write(resultJson.toString());
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean checkIsTestRequest(HttpServletRequest request) {
        JSONObject requestPRM = (JSONObject) request.getAttribute("reqPRM");
        JSONObject reqDataJson = requestPRM.getJSONObject("reqData");// 获取请求参数
        String token = requestPRM.optString("token");
        String memberId = reqDataJson.optString("memberId");
        if (memberInfoService == null) {
            ServletContext servletContext = request.getSession().getServletContext();
            WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
            memberInfoService = (MemberInfoService) appContext.getBean("memberInfoService");
        }
        boolean isTestRequest = !StringUtil.isBlank(memberId) && !StringUtil.isBlank(token) && memberInfoService.queryAccessToken(Integer.valueOf(memberId), token) != null;
        if (isTestRequest) {
            MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(Integer.valueOf(memberId));
            request.getSession().setAttribute(BaseDefine.MEMBER_INFO, memberInfo);
        }
        return isTestRequest;
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
