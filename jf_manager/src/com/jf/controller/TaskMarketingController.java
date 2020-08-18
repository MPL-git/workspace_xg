package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MemberLabelGroup;
import com.jf.entity.MemberLabelGroupExample;
import com.jf.entity.StateCode;
import com.jf.entity.Task;
import com.jf.entity.TaskLog;
import com.jf.entity.TaskLogCustom;
import com.jf.entity.TaskLogCustomExample;
import com.jf.entity.TaskMarketing;
import com.jf.entity.TaskMarketingCustom;
import com.jf.entity.TaskMarketingCustomExample;
import com.jf.service.MemberLabelGroupService;
import com.jf.service.MemberLabelRelationService;
import com.jf.service.TaskLogService;
import com.jf.service.TaskMarketingService;
import com.jf.service.TaskService;
import com.jf.vo.Page;
import com.vdurmont.emoji.EmojiParser;

@SuppressWarnings("serial")
@Controller
public class TaskMarketingController extends BaseController {

	@Autowired
	private TaskMarketingService taskMarketingService;
	
	@Autowired
	private MemberLabelGroupService memberLabelGroupService;
	
	@Autowired
	private MemberLabelRelationService memberLabelRelationService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskLogService taskLogService;
	
	/**
	 * 
	 * @MethodName: taskMarketingManager
	 * @Description: (描述)
	 * @author Pengl
	 * @date 2019年8月15日 上午11:47:11
	 */
	@RequestMapping("/taskMarketing/taskMarketingManager.shtml")
	public ModelAndView taskMarketingManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView();
		m.addObject("taskStatusList", DataDicUtil.getStatusList("BU_TASK", "STATUS"));
		if("1".equals(request.getParameter("type")) ) { //创建任务
			m.setViewName("/taskMarketing/getTaskMarketingCreateList");
		}else if("2".equals(request.getParameter("type"))) { //任务审核
			m.setViewName("/taskMarketing/getTaskMarketingAuditList");
		}else if("3".equals(request.getParameter("type"))) { //任务审批
			m.setViewName("/taskMarketing/getTaskMarketingApproveList");
		}else if("4".equals(request.getParameter("type"))) { //任务执行
			m.setViewName("/taskMarketing/getTaskMarketingExecuteList");
		}else if("5".equals(request.getParameter("type"))) { //任务记录
			m.setViewName("/taskMarketing/getTaskMarketingRecordList");
		}else if("6".equals(request.getParameter("type"))) { //已取消任务
			m.setViewName("/taskMarketing/getTaskMarketingCancelList");
		}
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/taskMarketing/getTaskMarketingList.shtml")
	public Map<String, Object> getTaskMarketingList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<TaskMarketingCustom> dataList = null;
		Integer totalCount = 0;
		try {
			TaskMarketingCustomExample taskMarketingCustomExample = new TaskMarketingCustomExample();
			TaskMarketingCustomExample.TaskMarketingCustomCriteria taskMarketingCustomCriteria = taskMarketingCustomExample.createCriteria();
			taskMarketingCustomCriteria.andCustomSQL(" t.del_flag = '0'");
			if(!StringUtils.isEmpty(request.getParameter("taskName")) ) {
				taskMarketingCustomCriteria.andCustomSQL(" a.name like concat('%', '"+ request.getParameter("taskName") +"', '%')");
			}
			if(!StringUtils.isEmpty(request.getParameter("taskStatus")) ) {
				taskMarketingCustomCriteria.andCustomSQL(" a.status = '"+ request.getParameter("taskStatus") +"'");
			}else {
				taskMarketingCustomCriteria.andCustomSQL(" a.status != '7'");
			}
			if(!StringUtils.isEmpty(request.getParameter("createDateBegin")) ) {
				taskMarketingCustomCriteria.andCustomSQL(" a.create_date >= '"+ request.getParameter("createDateBegin")+" 00:00:00'");
			}
			if(!StringUtils.isEmpty(request.getParameter("createDateEnd")) ) {
				taskMarketingCustomCriteria.andCustomSQL(" a.create_date <= '"+ request.getParameter("createDateEnd")+" 23:59:59'");
			}
			taskMarketingCustomExample.setOrderByClause(" t.id desc");
			taskMarketingCustomExample.setLimitStart(page.getLimitStart());
			taskMarketingCustomExample.setLimitSize(page.getLimitSize());
			totalCount = taskMarketingService.countByCustomExample(taskMarketingCustomExample);
			dataList = taskMarketingService.selectByCustomExample(taskMarketingCustomExample);
			for(TaskMarketingCustom taskMarketingCustom : dataList ) {
				taskMarketingCustom.setIosTitle(taskMarketingCustom.getIosTitle()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getIosTitle()));
				taskMarketingCustom.setAndroidTitle(taskMarketingCustom.getAndroidTitle()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getAndroidTitle()));
				taskMarketingCustom.setTaskContent(taskMarketingCustom.getTaskContent()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getTaskContent()));
				taskMarketingCustom.setTaskTaskExplain(taskMarketingCustom.getTaskTaskExplain()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getTaskTaskExplain()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName: eidtTaskMarketingManager
	 * @Description: (编辑营销消息)
	 * @author Pengl
	 * @date 2019年8月15日 下午2:15:10
	 */
	@RequestMapping("/taskMarketing/eidtTaskMarketingManager.shtml")
	public ModelAndView eidtTaskMarketingManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/taskMarketing/eidtTaskMarketing");
		String taskMarketingId = request.getParameter("taskMarketingId");
		if(!StringUtils.isEmpty(taskMarketingId) ) {
			TaskMarketingCustom taskMarketingCustom = taskMarketingService.selectByCustomPrimaryKey(Integer.parseInt(taskMarketingId));
			if(!StringUtils.isEmpty(taskMarketingCustom.getTaskLabelGroupIds()) ) {
				String[] labelGroupIds = taskMarketingCustom.getTaskLabelGroupIds().split(",");
				List<Integer> labelGroupIdList = new ArrayList<Integer>();
				for(String labelGroupId : labelGroupIds) {
					labelGroupIdList.add(Integer.parseInt(labelGroupId));
				}
				MemberLabelGroupExample memberLabelGroupExample = new MemberLabelGroupExample();
				memberLabelGroupExample.createCriteria().andDelFlagEqualTo("0").andIdIn(labelGroupIdList);
				List<MemberLabelGroup> memberLabelGroupList = memberLabelGroupService.selectByExample(memberLabelGroupExample);
				m.addObject("memberLabelGroupList", memberLabelGroupList);
			}
			taskMarketingCustom.setTaskSendValues(taskMarketingCustom.getTaskSendValues()==null?"":taskMarketingCustom.getTaskSendValues().replaceAll(",", "\r\n"));
			taskMarketingCustom.setIosTitle(taskMarketingCustom.getIosTitle()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getIosTitle()));
			taskMarketingCustom.setAndroidTitle(taskMarketingCustom.getAndroidTitle()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getAndroidTitle()));
			taskMarketingCustom.setTaskContent(taskMarketingCustom.getTaskContent()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getTaskContent()));
			taskMarketingCustom.setTaskTaskExplain(taskMarketingCustom.getTaskTaskExplain()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getTaskTaskExplain()));
			m.addObject("taskMarketingCustom", taskMarketingCustom);
		}
		m.addObject("taskMarketingLinkTypeList", DataDicUtil.getStatusList("BU_TASK_MARKETING", "LINK_TYPE"));
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/taskMarketing/eidtTaskMarketing.shtml")
	public ModelAndView eidtTaskMarketing(HttpServletRequest request, MultipartFile taskFilePath ) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String staffId = this.getSessionStaffBean(request).getStaffID();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int sendMemberCount = 0;
			String filePath = null;
			//导入文件会员数
			if(!taskFilePath.isEmpty() ) {
				filePath = FileUtil.saveFile(taskFilePath.getInputStream(), taskFilePath.getOriginalFilename(), 14, 0);
				Workbook wb = null;
				//根据文件后缀（xls/xlsx）进行判断
                if (taskFilePath.getOriginalFilename().toLowerCase().endsWith(".xls") ) {
                    wb = new HSSFWorkbook(taskFilePath.getInputStream());
                }else if (taskFilePath.getOriginalFilename().toLowerCase().endsWith(".xlsx") ){
                    wb = new XSSFWorkbook(taskFilePath.getInputStream());
                }
                if(wb != null ) {
                	for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                		Sheet sheet = wb.getSheetAt(i);
                		if(sheet != null && sheet.getPhysicalNumberOfRows() > 1) {
                			sendMemberCount += sheet.getPhysicalNumberOfRows()-1;
                		}
					}
                }
			}else {
				if(!StringUtils.isEmpty(request.getParameter("fileName")) ) {
					filePath = request.getParameter("fileName");
					if(!StringUtils.isEmpty(request.getParameter("id")) ) {
						TaskMarketingCustom taskMarketingCustom = taskMarketingService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("id")));
						sendMemberCount = taskMarketingCustom.getSendMemberCount();
						String sendValues = taskMarketingCustom.getTaskSendValues();
						if(!StringUtils.isEmpty(sendValues) ) {
							String[] vals = sendValues.split(",");
							sendMemberCount -= vals.length;
						}
					}
				}
			}
			//手动导入会员数
			String taskSendValues = request.getParameter("taskSendValues").trim();
			if(!StringUtils.isEmpty(taskSendValues) ) {
				taskSendValues = taskSendValues.replaceAll("\r\n", ",");
				String[] vs = taskSendValues.split(",");
				sendMemberCount += vs.length;
			}
			//用户标签分组会员数
			String taskLabelGroupIds = request.getParameter("taskLabelGroupIds");
			if(!StringUtils.isEmpty(taskLabelGroupIds) ) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("labelGroupIds", taskLabelGroupIds);
				Integer memberLabelRelationCustomCoount = memberLabelRelationService.selectMemberLabelRelationCount(paramMap);
				sendMemberCount += memberLabelRelationCustomCoount;
			}
			String taskContent = EmojiParser.parseToAliases(request.getParameter("taskContent")==null?"":request.getParameter("taskContent"));
			String taskSendMode = request.getParameter("taskSendMode");
			//任务短信
			TaskMarketing taskMarketing = new TaskMarketing();
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id) ) {
				taskMarketing.setId(Integer.parseInt(id));
				taskMarketing.setUpdateBy(Integer.parseInt(staffId));
				taskMarketing.setUpdateDate(date);
			}else {
				taskMarketing.setCreateBy(Integer.parseInt(staffId));
				taskMarketing.setCreateDate(date);
			}
			taskMarketing.setSendMemberCount(sendMemberCount);
			if(!StringUtils.isEmpty(request.getParameter("iosContext")) && !StringUtils.isEmpty(request.getParameter("iosTitle")) ) {
				taskMarketing.setIosContext(request.getParameter("iosContext"));
				taskMarketing.setIosTitle(EmojiParser.parseToAliases(request.getParameter("iosTitle")==null?"":request.getParameter("iosTitle")));
			}
			if(!StringUtils.isEmpty(request.getParameter("androidContext")) && !StringUtils.isEmpty(request.getParameter("androidTitle")) ) {
				taskMarketing.setAndroidContext(request.getParameter("androidContext"));
				taskMarketing.setAndroidTitle(EmojiParser.parseToAliases(request.getParameter("androidTitle")==null?"":request.getParameter("androidTitle")));
			}
			if(!StringUtils.isEmpty(request.getParameter("bigPushDuration")) ) {
				taskMarketing.setBigPushDuration(Integer.parseInt(request.getParameter("bigPushDuration")));
			}
			if(!StringUtils.isEmpty(request.getParameter("bigPushDuration")) ) {
				taskMarketing.setBigPushDuration(Integer.parseInt(request.getParameter("bigPushDuration")));
			}
			if(!StringUtils.isEmpty(request.getParameter("linkType")) ) {
				taskMarketing.setLinkType(request.getParameter("linkType"));
			}
			if(!StringUtils.isEmpty(request.getParameter("linkUrl")) ) {
				taskMarketing.setLinkUrl(request.getParameter("linkUrl"));
			}
			//任务
			Task task = new Task();
			task.setSendChannel("1");
			task.setName(request.getParameter("taskName"));
			task.setTaskExplain(EmojiParser.parseToAliases(request.getParameter("taskTaskExplain")==null?"":request.getParameter("taskTaskExplain")));
			task.setContent(taskContent);
			task.setSendMode(taskSendMode);
			if("1".equals(taskSendMode) && !StringUtils.isEmpty(request.getParameter("taskSendDate")) ) {
				task.setSendDate(sdf.parse(request.getParameter("taskSendDate")));
			}
			task.setSendType("0");
			task.setFilePath(filePath);
			if(!StringUtils.isEmpty(taskSendValues) ) {
				task.setSendValues(taskSendValues);
			}
			task.setLabelGroupIds(request.getParameter("taskLabelGroupIds"));
			
			taskMarketingService.eidtTaskSms(taskMarketing, task);
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	/**
	 * 
	 * @MethodName: auditTask
	 * @Description: (审核)
	 * @author Pengl
	 * @date 2019年8月15日 下午2:36:06
	 */
	@ResponseBody
	@RequestMapping("/taskMarketing/auditTask.shtml")
	public Map<String, Object> auditTask(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			Date date = new Date();
			String taskId = request.getParameter("taskId");
			if(!StringUtils.isEmpty(taskId) ) {
				Task task = taskService.selectByPrimaryKey(Integer.parseInt(taskId));
				if(task != null ) {
					String staffID = this.getSessionStaffBean(request).getStaffID();
					String status = request.getParameter("status");
					String remarks = request.getParameter("remarks");
					task.setStatus(status);
					task.setUpdateBy(Integer.parseInt(staffID));
					task.setUpdateDate(date);
					
					TaskLog taskLog = new TaskLog();
					taskLog.setTaskId(task.getId());
					if("1".equals(status) ) { //待审核
						taskLog.setOperatorType("0");
						taskLog.setStatus(status);
					}else if("7".equals(status) ) { //已取消
						taskLog.setOperatorType("4");
						taskLog.setStatus("4");
					}else if("0".equals(status) ) { //恢复任务
						taskLog.setOperatorType("0");
						taskLog.setStatus("0");
					}
					taskLog.setCreateBy(Integer.parseInt(staffID));
					taskLog.setCreateDate(date);
					taskLog.setRemarks(remarks);
					taskLog.setDelFlag("0");
					taskService.auditTask(task, taskLog);
				}
			}
		} catch (Exception e) {
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 
	 * @MethodName: viewTaskMarketing
	 * @Description: (任务详情)
	 * @author Pengl
	 * @date 2019年8月15日 下午2:40:13
	 */
	@RequestMapping("/taskMarketing/viewTaskMarketing.shtml")
	public ModelAndView viewTaskMarketing(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/taskMarketing/viewTaskMarketing");
		String taskMarketingId = request.getParameter("taskMarketingId");
		if(!StringUtils.isEmpty(taskMarketingId) ) {
			TaskMarketingCustom taskMarketingCustom = taskMarketingService.selectByCustomPrimaryKey(Integer.parseInt(taskMarketingId));
			if(!StringUtils.isEmpty(taskMarketingCustom.getTaskLabelGroupIds()) ) {
				String[] labelGroupIds = taskMarketingCustom.getTaskLabelGroupIds().split(",");
				List<Integer> labelGroupIdList = new ArrayList<Integer>();
				for(String labelGroupId : labelGroupIds) {
					labelGroupIdList.add(Integer.parseInt(labelGroupId));
				}
				MemberLabelGroupExample memberLabelGroupExample = new MemberLabelGroupExample();
				memberLabelGroupExample.createCriteria().andDelFlagEqualTo("0").andIdIn(labelGroupIdList);
				List<MemberLabelGroup> memberLabelGroupList = memberLabelGroupService.selectByExample(memberLabelGroupExample);
				m.addObject("memberLabelGroupList", memberLabelGroupList);
			}
			taskMarketingCustom.setTaskSendValues(taskMarketingCustom.getTaskSendValues()==null?"":taskMarketingCustom.getTaskSendValues().replaceAll(",", "\n"));
			taskMarketingCustom.setIosTitle(taskMarketingCustom.getIosTitle()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getIosTitle()));
			taskMarketingCustom.setAndroidTitle(taskMarketingCustom.getAndroidTitle()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getAndroidTitle()));
			taskMarketingCustom.setTaskContent(taskMarketingCustom.getTaskContent()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getTaskContent()));
			taskMarketingCustom.setTaskTaskExplain(taskMarketingCustom.getTaskTaskExplain()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getTaskTaskExplain()));
			m.addObject("taskMarketingCustom", taskMarketingCustom);
			List<TaskLogCustom> taskLogCustoms = new ArrayList<TaskLogCustom>();
			if(!"0".equals(taskMarketingCustom.getTaskStatus()) ) {
				TaskLogCustomExample taskLogCustomExample = new TaskLogCustomExample();
				taskLogCustomExample.createCriteria().andDelFlagEqualTo("0").andTaskIdEqualTo(taskMarketingCustom.getTaskId());
				taskLogCustomExample.setOrderByClause(" t.id asc");
				List<TaskLogCustom> taskLogCustomList = taskLogService.selectByCustomExample(taskLogCustomExample);
				for(TaskLogCustom taskLogCustom : taskLogCustomList) {
					if("0".equals(taskLogCustom.getOperatorType()) && "1".equals(taskLogCustom.getStatus()) ) {
						taskLogCustoms.removeAll(taskLogCustoms);
					}
					taskLogCustoms.add(taskLogCustom);
				}
			}
			m.addObject("taskLogCustomList", taskLogCustoms);
			m.addObject("taskMarketingLinkTypeList", DataDicUtil.getStatusList("BU_TASK_MARKETING", "LINK_TYPE"));
		}
		return m;
	}
	
	/**
	 * 
	 * @MethodName: auditOrApproveTaskManager
	 * @Description: (审核或审批)
	 * @author Pengl
	 * @date 2019年8月15日 下午2:42:38
	 */
	@RequestMapping("/taskMarketing/auditOrApproveTaskManager.shtml")
	public ModelAndView auditOrApproveTaskManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/taskMarketing/auditOrApproveTask");
		String taskMarketingId = request.getParameter("taskMarketingId");
		if(!StringUtils.isEmpty(taskMarketingId) ) {
			TaskMarketingCustom taskMarketingCustom = taskMarketingService.selectByCustomPrimaryKey(Integer.parseInt(taskMarketingId));
			if(!StringUtils.isEmpty(taskMarketingCustom.getTaskLabelGroupIds()) ) {
				String[] labelGroupIds = taskMarketingCustom.getTaskLabelGroupIds().split(",");
				List<Integer> labelGroupIdList = new ArrayList<Integer>();
				for(String labelGroupId : labelGroupIds) {
					labelGroupIdList.add(Integer.parseInt(labelGroupId));
				}
				MemberLabelGroupExample memberLabelGroupExample = new MemberLabelGroupExample();
				memberLabelGroupExample.createCriteria().andDelFlagEqualTo("0").andIdIn(labelGroupIdList);
				List<MemberLabelGroup> memberLabelGroupList = memberLabelGroupService.selectByExample(memberLabelGroupExample);
				m.addObject("memberLabelGroupList", memberLabelGroupList);
			}
			taskMarketingCustom.setTaskSendValues(taskMarketingCustom.getTaskSendValues()==null?"":taskMarketingCustom.getTaskSendValues().replaceAll(",", "\n"));
			taskMarketingCustom.setIosTitle(taskMarketingCustom.getIosTitle()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getIosTitle()));
			taskMarketingCustom.setAndroidTitle(taskMarketingCustom.getAndroidTitle()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getAndroidTitle()));
			taskMarketingCustom.setTaskContent(taskMarketingCustom.getTaskContent()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getTaskContent()));
			taskMarketingCustom.setTaskTaskExplain(taskMarketingCustom.getTaskTaskExplain()==null?"":EmojiParser.parseToUnicode(taskMarketingCustom.getTaskTaskExplain()));
			m.addObject("taskMarketingCustom", taskMarketingCustom);
			List<TaskLogCustom> taskLogCustoms = new ArrayList<TaskLogCustom>();
			if(!"0".equals(taskMarketingCustom.getTaskStatus()) ) {
				TaskLogCustomExample taskLogCustomExample = new TaskLogCustomExample();
				taskLogCustomExample.createCriteria().andDelFlagEqualTo("0").andTaskIdEqualTo(taskMarketingCustom.getTaskId());
				taskLogCustomExample.setOrderByClause(" t.id asc");
				List<TaskLogCustom> taskLogCustomList = taskLogService.selectByCustomExample(taskLogCustomExample);
				for(TaskLogCustom taskLogCustom : taskLogCustomList) {
					if("0".equals(taskLogCustom.getOperatorType()) && "1".equals(taskLogCustom.getStatus()) ) {
						taskLogCustoms.removeAll(taskLogCustoms);
					}
					taskLogCustoms.add(taskLogCustom);
				}
			}
			m.addObject("taskLogCustomList", taskLogCustoms);
			m.addObject("taskMarketingLinkTypeList", DataDicUtil.getStatusList("BU_TASK_MARKETING", "LINK_TYPE"));
		}
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/taskMarketing/auditOrApproveTask.shtml")
	public ModelAndView auditOrApproveTask(HttpServletRequest request ) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			Date date = new Date();
			String taskId = request.getParameter("taskId");
			if(!StringUtils.isEmpty(taskId) ) {
				Task task = taskService.selectByPrimaryKey(Integer.parseInt(taskId));
				if(task != null ) {
					String staffID = this.getSessionStaffBean(request).getStaffID();
					String status = request.getParameter("status");
					String remarks = request.getParameter("remarks");
					task.setStatus(status);
					task.setUpdateBy(Integer.parseInt(staffID));
					task.setUpdateDate(date);
					
					TaskLog taskLog = new TaskLog();
					taskLog.setTaskId(task.getId());
					if("2".equals(status) ) { //待审批
						taskLog.setOperatorType("1");
						taskLog.setStatus("1");
					}else if("3".equals(status) ) { //待执行
						taskLog.setOperatorType("2");
						taskLog.setStatus("1");
					}else if("4".equals(status) ) { //已执行
						taskLog.setOperatorType("3");
						taskLog.setStatus("3");
					}else if("5".equals(status) ) { //审核驳回
						taskLog.setOperatorType("1");
						taskLog.setStatus("2");
						taskLog.setRemarks(remarks);
					}else if("6".equals(status) ) { //审批驳回
						taskLog.setOperatorType("2");
						taskLog.setStatus("2");
						taskLog.setRemarks(remarks);
					}
					taskLog.setCreateBy(Integer.parseInt(staffID));
					taskLog.setCreateDate(date);
					taskLog.setDelFlag("0");
					taskService.auditTask(task, taskLog);
				}
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	
	
}
