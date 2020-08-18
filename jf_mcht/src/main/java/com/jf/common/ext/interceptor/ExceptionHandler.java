package com.jf.common.ext.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jf.common.base.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 异常拦截处理
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    private static final String REST_HEADER = "x-requested-with";
    private static final String REST_IDENTITY = "XMLHttpRequest";

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String errorCode = ResponseMsg.ERROR;
        String errorMessage = "未知错误，请联系客服处理";

        logger.error(ex.getMessage(), ex);
        if (isRestRequest(request)) {
            response.setHeader("Accept", "application/json;charset=UTF-8");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try {
                response.getWriter().write(JSON.toJSONString(ResponseMsg.error(errorCode, errorMessage)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        Map<String, Object> renderMap = Maps.newHashMap();
        renderMap.put("success", false);
        renderMap.put("code", errorCode);
        renderMap.put("message", errorMessage);
        return new ModelAndView("exception/error", renderMap);
    }

    private boolean isRestRequest(HttpServletRequest request) {
        String ajaxHeader = request.getHeader(REST_HEADER);
        return StringUtils.hasText(ajaxHeader) && REST_IDENTITY.equalsIgnoreCase(ajaxHeader);
    }
}  