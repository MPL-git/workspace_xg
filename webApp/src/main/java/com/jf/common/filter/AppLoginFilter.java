package com.jf.common.filter;

import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.ErrorConst;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.AppAccessToken;
import com.jf.entity.MemberInfo;
import com.jf.service.MemberInfoService;
import net.sf.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * app登录验证
 *
 * @author
 * @since 2015-04-02
 */
public class AppLoginFilter implements Filter {

    private MemberInfoService memberInfoService;

    private int tokenInvalidTime = 525600;//token失效时长，以分钟为单位

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();

        try {
            if (memberInfoService == null) {
                ServletContext servletContext = request.getSession().getServletContext();
                WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
                memberInfoService = (MemberInfoService) appContext.getBean("memberInfoService");
            }
            JSONObject requestPRM = (JSONObject) request.getAttribute("reqPRM");
            JSONObject reqDataJson = requestPRM.getJSONObject("reqData");// 获取请求参数
            // 查询用户是否登陆
            Integer memberId = 0;
            String token = "";
            if (!StringUtil.isBlank(reqDataJson.getString("memberId"))) {
                memberId = Integer.valueOf(reqDataJson.getString("memberId"));
            }
            if (!StringUtil.isBlank(requestPRM.getString("token"))) {
                token = requestPRM.getString("token");
            }
            AppAccessToken appAccessToken = null;
            if (!StringUtil.isBlank(token) && memberId != null) {
                appAccessToken = memberInfoService.queryAccessToken(memberId, token);
            }
            MemberInfo info = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
            if (info == null) {
                if (appAccessToken == null) {
                    printError(response, ErrorConst.NOT_LOGIN_CODE, ErrorConst.NOT_LOGIN_MSG);
                    return;
                } else {
                    info = memberInfoService.selectByPrimaryKey(memberId);
                    request.getSession().setAttribute(BaseDefine.MEMBER_INFO, info);
                }
            } else {
                if (!memberId.toString().equals(info.getId().toString())) {
                    if (appAccessToken == null) {
                        printError(response, ErrorConst.NOT_LOGIN_CODE, ErrorConst.NOT_LOGIN_MSG);
                        return;
                    }
                    info = memberInfoService.selectByPrimaryKey(memberId);
                    request.getSession().setAttribute(BaseDefine.MEMBER_INFO, info);
                }

            }
            if (appAccessToken == null) {
                printError(response, ErrorConst.NOT_LOGIN_CODE, ErrorConst.NOT_LOGIN_MSG);
                return;
            }
            if (!uri.contains("api/z/loginOut")) {
                if ( new Date().after(DateUtil.addMinute(appAccessToken.getUpdateDate(), tokenInvalidTime))) {
                    MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
                    if ("1".equals(memberInfo.getType()) || "2".equals(memberInfo.getType())) {
                        printError(response, ErrorConst.LOGIN_INVALID_CODE, ErrorConst.LOGIN_INVALID_MSG);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            printError(response, ErrorConst.FILTER_ERROR_CODE, ErrorConst.FILTER_ERROR_MSG);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    private void printError(HttpServletResponse response, String code, String msg) throws IOException {
        JSONObject resultJson = new JSONObject();
        resultJson.put("returnCode", code);
        resultJson.put("returnMsg", msg);
        response.getWriter().write(resultJson.toString());
    }

}
