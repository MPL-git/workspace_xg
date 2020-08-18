package com.jf.controller;

import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.TaskSendMemberService;
import com.jf.service.TaskService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Controller
public class TaskSendMemberController extends BaseController {

	@Autowired
	private TaskSendMemberService taskSendMemberService;
	
	@Autowired
	private TaskService taskService;
	
	/**
	 * 
	 * @MethodName: taskSendMemberManager
	 * @Description: (发送详情)
	 * @author Pengl
	 * @date 2019年8月9日 下午3:43:51
	 */
	@RequestMapping("/taskSendMember/taskSendMemberManager.shtml")
	public ModelAndView taskSendMemberManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/taskSendMember/getTaskSendMemberList");
		m.addObject("taskSendMemeberList", DataDicUtil.getStatusList("BU_TASK_SEND_MEMEBER", "STATUS"));
		m.addObject("taskId", request.getParameter("taskId"));
		String sendChannel = "0";
		if(!StringUtils.isEmpty(request.getParameter("taskId")) ) {
			Task task = taskService.selectByPrimaryKey(Integer.parseInt(request.getParameter("taskId")));
			sendChannel = task==null?"0":task.getSendChannel();
		}
		m.addObject("sendChannel", sendChannel);
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/taskSendMember/getTaskSendMemberList.shtml")
	public Map<String, Object> getTaskSendMemberList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<TaskSendMemberCustom> dataList = null;
		Integer totalCount = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			TaskSendMemberCustomExample taskSendMemberCustomExample = new TaskSendMemberCustomExample();
			TaskSendMemberCustomExample.TaskSendMemberCustomCriteria taskSendMemberCustomCriteria = taskSendMemberCustomExample.createCriteria();
			taskSendMemberCustomCriteria.andDelFlagEqualTo("0").andTaskIdEqualTo(Integer.parseInt(request.getParameter("taskId")));
			Task task = taskService.selectByPrimaryKey(Integer.parseInt(request.getParameter("taskId")));

			TaskSendMemberExample taskSendMemberExample = new TaskSendMemberExample();
			taskSendMemberExample.createCriteria().andDelFlagEqualTo("0").andTaskIdEqualTo(task.getId()).andStatusEqualTo("2");
			taskSendMemberExample.setOrderByClause(" send_date asc");
			taskSendMemberExample.setLimitStart(0);
			taskSendMemberExample.setLimitSize(1);
			List<TaskSendMember> taskSendMembers = taskSendMemberService.selectByExample(taskSendMemberExample);

			if(taskSendMembers != null && taskSendMembers.size() > 0 ) {
				if(!StringUtils.isEmpty(request.getParameter("memberId"))
						|| !StringUtils.isEmpty(request.getParameter("memberNick"))
						|| !StringUtils.isEmpty(request.getParameter("memberMobile"))
						) {
					taskSendMemberCustomCriteria.andMemberSQL(request.getParameter("memberId"), request.getParameter("memberNick"), request.getParameter("memberMobile"), task.getSendType());
				}
				String date = sdf.format(taskSendMembers.get(0).getSendDate());
				Calendar cal = Calendar.getInstance();
				cal.setTime(taskSendMembers.get(0).getSendDate());
				cal.add(Calendar.DAY_OF_MONTH, 7);
				String newDate = sdf.format(cal.getTime());
				if(!StringUtils.isEmpty(request.getParameter("loginStatus")) ) {
					taskSendMemberCustomCriteria.andLoginStatus(request.getParameter("loginStatus"), task.getSendType(), date,newDate);
				}
				if(!StringUtils.isEmpty(request.getParameter("orderStatus")) ) {
					taskSendMemberCustomCriteria.andOrderStatus(request.getParameter("orderStatus"), task.getSendType(), date,newDate);
				}
				if(!StringUtils.isEmpty(request.getParameter("status")) ) {
					taskSendMemberCustomCriteria.andStatusEqualTo(request.getParameter("status"));
				}
				if(!StringUtils.isEmpty(request.getParameter("startSendDate")) ) {
					taskSendMemberCustomCriteria.andSendDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("startSendDate")+" 00:00:00"));
				}
				if(!StringUtils.isEmpty(request.getParameter("endSendDate")) ) {
					taskSendMemberCustomCriteria.andSendDateLessThanOrEqualTo(sdf.parse(request.getParameter("endSendDate")+" 23:59:59"));
				}
				taskSendMemberCustomExample.setOrderByClause(" t.send_date desc");
				taskSendMemberCustomExample.setLimitStart(page.getLimitStart());
				taskSendMemberCustomExample.setLimitSize(page.getLimitSize());
				totalCount = taskSendMemberService.countByCustomExample(taskSendMemberCustomExample);
				dataList = taskSendMemberService.getTaskSendMemberList(taskSendMemberCustomExample, date);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	
}
