package com.jf.common.filter;

import com.jf.common.constant.Const;
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
            String token = "";
            String memberId = "";
            if (reqDataJson.containsKey("memberId")) {
                memberId = reqDataJson.getString("memberId");
            }
            if (!StringUtil.isBlank(requestPRM.getString("token"))) {
                token = requestPRM.getString("token");
            }
            AppAccessToken appAccessToken = null;
            MemberInfo memberInfo = null;

            if (!StringUtil.isBlank(memberId)) {
                appAccessToken = memberInfoService.queryAccessToken(Integer.valueOf(memberId), token);
            }
            if (appAccessToken == null && !uri.contains("/api/y/addMemberFavorites") && !uri.contains("/api/y/getSobotInfo") && !uri.contains("/api/y/getCatalogList")) {
                JSONObject resultJson = new JSONObject();
                resultJson.put("returnCode", ErrorConst.NOT_LOGIN_CODE);
                resultJson.put("returnMsg", ErrorConst.NOT_LOGIN_MSG);
                response.getWriter().write(resultJson.toString());
                return;
            }
            if (!StringUtil.isBlank(memberId)) {
                memberInfo = memberInfoService.selectByPrimaryKey(Integer.valueOf(memberId));
            }
            if (!uri.contains("api/y/loginOut") && !uri.contains("/api/y/addMemberFavorites") && !uri.contains("/api/y/getSobotInfo") && !uri.contains("/api/y/getCatalogList")) {
                if (appAccessToken == null || new Date().after(DateUtil.addMinute(appAccessToken.getUpdateDate(), tokenInvalidTime))) {
                    if (memberInfo == null || "1".equals(memberInfo.getType()) || "2".equals(memberInfo.getType())) {
                        JSONObject resultJson = new JSONObject();
                        resultJson.put("returnCode", ErrorConst.LOGIN_INVALID_CODE);
                        resultJson.put("returnMsg", ErrorConst.LOGIN_INVALID_MSG);
                        response.getWriter().write(resultJson.toString());
                        return;
                    }
                }
            }
            //拦截用来处理第三方登入旧数据
            if (memberInfo != null && memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_THIRD) && !StringUtil.isBlank(memberInfo.getWeixinId())) {
                if (requestPRM.containsKey("version") && requestPRM.containsKey("system")) {
                    Integer version = requestPRM.getInt("version");
                    String system = requestPRM.getString("system");
                    if ((version > 15 && system.equals(Const.ANDROID)) || (version > 16 && system.equals(Const.IOS))) {
                        if (StringUtil.isBlank(memberInfo.getWeixinUnionid())) {
                            JSONObject resultJson = new JSONObject();
                            resultJson.put("returnCode", ErrorConst.LOGIN_INVALID_CODE);
                            resultJson.put("returnMsg", ErrorConst.LOGIN_INVALID_MSG);
                            response.getWriter().write(resultJson.toString());
                            return;
                        }
                    }
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

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
