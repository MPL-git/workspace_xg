package com.jf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ResponseMsg;
import com.jf.service.CouponService;

@Controller
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@ResponseBody
    @RequestMapping("/coupon/addGrantQuantity")
	public ResponseMsg addGrantQuantity(HttpServletRequest request) {
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		try {
			System.out.println(request.getParameter("activity"));
			couponService.addGrantQuantity(request.getParameter("activity"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseMsg.setReturnCode(ResponseMsg.ERROR);
		}
		return responseMsg;
	}
	
}
