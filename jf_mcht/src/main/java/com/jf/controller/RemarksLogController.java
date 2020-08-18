package com.jf.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.jf.service.RemarksLogService;

@Controller
public class RemarksLogController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(RemarksLogController.class);


	@Resource
	private RemarksLogService remarksLogService;
	
	
}
