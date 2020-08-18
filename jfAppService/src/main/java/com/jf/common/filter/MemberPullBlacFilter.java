package com.jf.common.filter;

import com.jf.common.base.ArgException;
import com.jf.common.base.CharFilterRequest;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.constant.ErrorConst;
import com.jf.common.utils.StringUtil;
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
import java.util.Map;

/**
 * 公共过滤器，会员拉黑过滤
 *
 * @author
 * @since 2015-04-02
 */
public class MemberPullBlacFilter implements Filter {
    private MemberInfoService memberInfoService;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        CharFilterRequest wrapRequest = new CharFilterRequest(request, request.getParameterMap());
        try {
            if (memberInfoService == null) {
                ServletContext servletContext = request.getSession().getServletContext();
                WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
                memberInfoService = (MemberInfoService) appContext.getBean("memberInfoService");
            }
            String uri = request.getRequestURI();
            String type = "";
            JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
            JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
            String system = reqPRM.getString("system");
            Integer version = reqPRM.getInt("version");
            if ((system.equals(Const.ANDROID) && version > 49) || (system.equals(Const.IOS) && version > 52)) {
                Integer memberId = null;
                if (reqDataJson.containsKey("memberId") && !StringUtil.isBlank(reqDataJson.getString("memberId"))) {
                    memberId = reqDataJson.getInt("memberId");
                }
                if (memberId != null) {
                    //addMemberComment 评价与追平
                    if (uri.contains("addMemberComment")) {
                        type = "1";
                    } else if (uri.contains("submitOrderPayment20170811")
                            || uri.contains("submitAfterPayment")
                            || uri.contains("submitDepositOrderPayment")
                            || uri.contains("submitDepositAfterPayment")) {
                        type = "2";
                    }
                    if (!StringUtil.isBlank(type)) {
                        MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
                        Map<String, String> map = memberInfoService.checkMemberStatus(memberInfo, system, version, type);
                        if ("false".equals(map.get("success"))) {
                            JSONObject resultJson = new JSONObject();
                            resultJson.put("success", map.get("success"));
                            resultJson.put("errorMsg", map.get("errorMsg"));
                            resultJson.put("mobile", map.get("mobile"));
                            resultJson.put("returnCode", ResponseMsg.ERROR_4006);
                            response.getWriter().write(resultJson.toString());
                            return;
                        }
                    }
                }
            }
        } catch (ArgException argE) {
            JSONObject resultJson = new JSONObject();
            resultJson.put("returnCode", ErrorConst.FILTER_ERROR_CODE);
            resultJson.put("returnMsg", argE.getMessage());
            response.getWriter().write(resultJson.toString());
            return;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject resultJson = new JSONObject();
            resultJson.put("returnCode", ErrorConst.FILTER_ERROR_CODE);
            resultJson.put("returnMsg", ErrorConst.FILTER_ERROR_MSG);
            response.getWriter().write(resultJson.toString());
            return;
        }

        chain.doFilter(wrapRequest, resp);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
