package com.jf.controller;

import com.jf.controller.base.BaseController;
import com.jf.service.TaskActivitySelectionService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TaskActivitySelectionController extends BaseController{

	@Autowired
	private TaskActivitySelectionService taskActivitySelectionService;


	/**
	 * 精选活动详情
	 */
	@RequestMapping(value = "/api/n/activitySelection/detail")
	@ResponseBody
	public String detail() {

		JSONObject params = getParams();
		int activitySelectionId = params.optInt("id");

		Map<String, Object> data = new HashMap<>();
		data.put("activitySelection", taskActivitySelectionService.findDetailById(activitySelectionId));

		return json(data);
	}
}
