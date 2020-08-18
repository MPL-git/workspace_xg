package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.service.DeliveryOvertimeSpecialCnfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Pengl
 * @create 2020-02-28 上午 11:21
 */
@Controller
public class DeliveryOvertimeSpecialCnfController {

	@Autowired
	private DeliveryOvertimeSpecialCnfService deliveryOvertimeSpecialCnfService;

	@ResponseBody
	@RequestMapping("/deliveryOvertimeSpecialCnf/specialAreaDeliveryLastDate")
	public synchronized ResponseMsg specialAreaDeliveryLastDate(HttpServletRequest request) {
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		try {
			deliveryOvertimeSpecialCnfService.specialAreaDeliveryLastDate();
		} catch (Exception e) {
			e.printStackTrace();
			responseMsg.setReturnCode(ResponseMsg.ERROR);
			responseMsg.setReturnMsg(e.getMessage());
		}
		return responseMsg;
	}

	@ResponseBody
	@RequestMapping("/deliveryOvertimeSpecialCnf/deliveryLastDate")
	public synchronized ResponseMsg deliveryLastDate(HttpServletRequest request) {
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		try {
			String beginPayDate = request.getParameter("beginPayDate");
			String endPayDate = request.getParameter("endPayDate");
			String deliveryDate = request.getParameter("deliveryDate");
			if(!StringUtil.isEmpty(beginPayDate) && !StringUtil.isEmpty(endPayDate) && !StringUtil.isEmpty(deliveryDate)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date bPayDate = sdf.parse(beginPayDate);
				Date ePayDate = sdf.parse(endPayDate);
				Date dDate = sdf.parse(deliveryDate);
				deliveryOvertimeSpecialCnfService.deliveryLastDate(bPayDate, ePayDate, dDate);
			}else {
				responseMsg.setReturnCode(ResponseMsg.ERROR);
				responseMsg.setReturnMsg("手动修改不涉及特殊地区发货时间：请传入付款时间范围，传参beginPayDate具体付款开始时间与endPayDate具体付款结束时间，传入承诺最后发货时间deliveryDate，时间格式yyyy-MM-dd HH:mm:ss。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMsg.setReturnCode(ResponseMsg.ERROR);
			responseMsg.setReturnMsg(e.getMessage());
		}
		return responseMsg;
	}


}
