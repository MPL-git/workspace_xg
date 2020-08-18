package com.jf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.BaseConfigProperties;
import com.jf.common.base.ResponseMsg;

@Controller
public class TestController extends BaseController{
	
	@RequestMapping("/test/testjni")
	@ResponseBody
	public ResponseMsg testjni(Model model, HttpServletRequest request) {
		System.out.println("1111------");
		String[] aa=new BaseConfigProperties().getDatabaseConfig();
		for (int i = 0; i < aa.length; i++) {
			System.out.println(aa[i]);
		}
		System.out.println("2222-------");
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,"yyy");
	}
	
}
