package com.jf.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.dao.XiaonengCustomerServiceMapper;
import com.jf.entity.XiaonengCustomerService;
import com.jf.entity.XiaonengCustomerServiceExample;

@Controller
public class XiaoNengCustomerServiceController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(XiaoNengCustomerServiceController.class);

	@Resource
	private XiaonengCustomerServiceMapper xiaonengCustomerServiceMapper;
	
	/**
	 * 客服中心
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/xiaoNengCustomerService/index")
	public String index(Model model, HttpServletRequest request) {
		if(this.getSessionMchtInfo(request).getXiaonengId()!=null){
			XiaonengCustomerServiceExample e = new XiaonengCustomerServiceExample();
			XiaonengCustomerServiceExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andIdEqualTo(this.getSessionMchtInfo(request).getXiaonengId());
			c.andStatusEqualTo("1");
			List<XiaonengCustomerService> list = xiaonengCustomerServiceMapper.selectByExample(e);
			if(list!=null && list.size()>0){
				model.addAttribute("enterpriseNo", "JE"+list.get(0).getBusId());
			}
		}
		return "xiaoNengCustomerService/index";
	}
}
