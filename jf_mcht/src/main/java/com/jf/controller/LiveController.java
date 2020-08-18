package com.jf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ResponseMsg;

@Controller
public class LiveController extends BaseController{
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(LiveController.class);
	 @RequestMapping("/live/auth")
	 @ResponseBody
	 public ResponseMsg auth(HttpServletRequest request, HttpServletResponse response) {
		 	String userName = request.getParameter("userName");
		 	String password	= request.getParameter("password");
		 	ResponseMsg responseMsg = new ResponseMsg();
	        try {
	           if ("888888".equals(password)){
	        	    responseMsg.setReturnCode("0000");
	        	    responseMsg.setReturnMsg("用户"+userName+"直播授权成功");
	        	    logger.info("用户"+userName+"直播授权成功");
	            }else{
	            	responseMsg.setReturnCode("4004");
		        	responseMsg.setReturnMsg("用户"+userName+"直播授权失败");
	            	logger.info("用户"+userName+"直播授权失败,用户输入的密码为：" + password);
	                response.setHeader("liveAuth","authCode error");
	                response.setStatus(500);
	            }
	        } catch (Exception e) {
	        	logger.info("",e);
	            response.setHeader("error","sys error");
	            response.setStatus(500);
	        }
	        return responseMsg;
	    }

}
