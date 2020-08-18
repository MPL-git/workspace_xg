package com.jf.common.ext.interceptor;

import com.jf.common.ext.core.Result;
import com.jf.common.ext.exception.BizException;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常拦截处理
 */
public class ExceptionHandler implements HandlerExceptionResolver {
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        Integer errorCode = Result.ERROR_CODE_FAILURE;
        String errorMessage = Result.ERROR_MESSAGE_UNKNOWN_EXCEPTION;

        if(ex instanceof BizException) {
            BizException bizException = (BizException)ex;
            errorCode = bizException.getErrorCode();
            errorMessage = ex.getMessage();
        }
        ex.printStackTrace();
        logger.error(ex.getMessage());
        Map<String, Object> renderMap = new HashMap<String, Object>();
        renderMap.put("success", false);
        renderMap.put("code", errorCode);
        renderMap.put("message", errorMessage);

        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
            if(responseBody!=null){
                response.setContentType("application/json; charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                try {
                    OutputStream out = response.getOutputStream();
                    out.write(JSONObject.toJSONString(renderMap).getBytes("UTF-8"));
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }

        return new ModelAndView("exception/error", renderMap);
    }
}  