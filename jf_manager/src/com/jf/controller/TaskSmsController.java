package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.constant.Const;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.BlackList;
import com.jf.entity.BlackListCustom;
import com.jf.entity.BlackListCustomExample;
import com.jf.entity.BlackListExample;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoCustomExample;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.MemberLabelGroup;
import com.jf.entity.MemberLabelGroupExample;
import com.jf.entity.StateCode;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.SysStatus;
import com.jf.entity.Task;
import com.jf.entity.TaskLog;
import com.jf.entity.TaskLogCustom;
import com.jf.entity.TaskLogCustomExample;
import com.jf.entity.TaskSendMember;
import com.jf.entity.TaskSendMemberExample;
import com.jf.entity.TaskSms;
import com.jf.entity.TaskSmsCustom;
import com.jf.entity.TaskSmsCustomExample;
import com.jf.service.BlackListService;
import com.jf.service.CouponService;
import com.jf.service.MemberInfoService;
import com.jf.service.MemberLabelGroupService;
import com.jf.service.MemberLabelRelationService;
import com.jf.service.SysParamCfgService;
import com.jf.service.TaskLogService;
import com.jf.service.TaskSendMemberService;
import com.jf.service.TaskService;
import com.jf.service.TaskSmsService;
import com.jf.vo.Page;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
@Controller
public class TaskSmsController extends BaseController {

	@Autowired
	private TaskSmsService taskSmsService;
	
	@Autowired
	private MemberLabelGroupService memberLabelGroupService;
	
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	@Autowired
	private MemberLabelRelationService memberLabelRelationService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskLogService taskLogService;
	
	@Autowired
	private BlackListService blackListService;
	
	@Autowired
	private MemberInfoService memberInfoService;

	@Autowired
	private TaskSendMemberService taskSendMemberService;

	@Autowired
	private CouponService couponService;

	private static boolean TASK_AUDIT_STATUS = true;

	/**
	 * 
	 * @MethodName: taskSmsCreateListManager
	 * @Description: (创建短信任务列表)
	 * @author Pengl
	 * @date 2019年8月2日 下午3:25:16
	 */
	@RequestMapping("/taskSms/taskSmsCreateListManager.shtml")
	public ModelAndView taskSmsCreateListManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView();
		m.addObject("taskStatusList", DataDicUtil.getStatusList("BU_TASK", "STATUS"));
		m.addObject("taskSendChannelList", DataDicUtil.getStatusList("BU_TASK", "SEND_CHANNEL"));
		m.addObject("smsType", request.getParameter("type"));
		if("1".equals(request.getParameter("type")) ) { //创建任务
			m.setViewName("/taskSms/getTaskSmsCreateList");
		}else if("2".equals(request.getParameter("type"))) { //任务审核
			m.setViewName("/taskSms/getTaskSmsAuditList");
		}else if("3".equals(request.getParameter("type"))) { //任务审批
			m.setViewName("/taskSms/getTaskSmsApproveList");
		}else if("4".equals(request.getParameter("type"))) { //任务执行
			m.setViewName("/taskSms/getTaskSmsExecuteList");
		}else if("5".equals(request.getParameter("type"))) { //任务记录
			m.setViewName("/taskSms/getTaskSmsRecordList");
		}else if("6".equals(request.getParameter("type"))) { //已取消任务
			m.setViewName("/taskSms/getTaskSmsCancelList");
		}else if("7".equals(request.getParameter("type"))) { //拉黑名单
			m.setViewName("/taskSms/getBlackList");
		}
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/taskSms/getTaskSmsCreateList.shtml")
	public Map<String, Object> getTaskSmsCreateList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<TaskSmsCustom> dataList = null;
		Integer totalCount = 0;
		try {
			TaskSmsCustomExample taskSmsCustomExample = new TaskSmsCustomExample();
			TaskSmsCustomExample.TaskSmsCustomCriteria taskSmsCustomCriteria = taskSmsCustomExample.createCriteria();
			taskSmsCustomCriteria.andCustomSQL(" t.del_flag = '0'");
			if(!StringUtils.isEmpty(request.getParameter("taskSendChannel")) ) {
				taskSmsCustomCriteria.andCustomSQL(" a.send_channel = '"+ request.getParameter("taskSendChannel") +"'");
			}
			if(!StringUtils.isEmpty(request.getParameter("taskName")) ) {
				taskSmsCustomCriteria.andCustomSQL(" a.name like concat('%', '"+ request.getParameter("taskName") +"', '%')");
			}
			if(!StringUtils.isEmpty(request.getParameter("taskStatus")) ) {
				taskSmsCustomCriteria.andCustomSQL(" a.status = '"+ request.getParameter("taskStatus") +"'");
			}else {
				taskSmsCustomCriteria.andCustomSQL(" a.status != '7'");
			}
			if(!StringUtils.isEmpty(request.getParameter("createDateBegin")) ) {
				taskSmsCustomCriteria.andCustomSQL(" a.create_date >= '"+ request.getParameter("createDateBegin")+" 00:00:00'");
			}
			if(!StringUtils.isEmpty(request.getParameter("createDateEnd")) ) {
				taskSmsCustomCriteria.andCustomSQL(" a.create_date <= '"+ request.getParameter("createDateEnd")+" 23:59:59'");
			}
			taskSmsCustomExample.setOrderByClause(" t.id desc");
			taskSmsCustomExample.setLimitStart(page.getLimitStart());
			taskSmsCustomExample.setLimitSize(page.getLimitSize());
			totalCount = taskSmsService.countByCustomExample(taskSmsCustomExample);
			dataList = taskSmsService.selectByCustomExample(taskSmsCustomExample);
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
	 * @MethodName: eidtTaskSmsManager
	 * @Description: (编辑短信任务)
	 * @author Pengl
	 * @date 2019年8月2日 下午3:39:30
	 */
	@RequestMapping("/taskSms/eidtTaskSmsManager.shtml")
	public ModelAndView eidtTaskSmsManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/taskSms/eidtTaskSms");
		List<SysStatus> sysStatusList = DataDicUtil.getStatusList("BU_TASK", "SEND_CHANNEL");
		for(SysStatus sysStatus : sysStatusList ) {
			SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
			switch (sysStatus.getStatusValue()){
				case Const.XW_SMS_SEND_CHANNEL:
					sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.XW_SEND_PRICE);
					break;
				case Const.XY_SMS_SEND_CHANNEL:
					sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.XY_SEND_PRICE);
					break;
				case Const.MW_SMS_SEND_CHANNEL:
					sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.MW_SEND_PRICE);
					break;
				default:
					continue;
			}
			List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
			if(sysParamCfgList != null && sysParamCfgList.size() > 0 ) {
				sysStatus.setRemark(sysParamCfgList.get(0).getParamValue());
			}
		}
		m.addObject("taskSendChannelList", sysStatusList);
		String taskSmsId = request.getParameter("taskSmsId");
		if(!StringUtils.isEmpty(taskSmsId) ) {
			TaskSmsCustom taskSmsCustom = taskSmsService.selectByCustomPrimaryKey(Integer.parseInt(taskSmsId));
			if(!StringUtils.isEmpty(taskSmsCustom.getTaskLabelGroupIds()) ) {
				String[] labelGroupIds = taskSmsCustom.getTaskLabelGroupIds().split(",");
				List<Integer> labelGroupIdList = new ArrayList<Integer>();
				for(String labelGroupId : labelGroupIds) {
					labelGroupIdList.add(Integer.parseInt(labelGroupId));
				}
				MemberLabelGroupExample memberLabelGroupExample = new MemberLabelGroupExample();
				memberLabelGroupExample.createCriteria().andDelFlagEqualTo("0").andIdIn(labelGroupIdList);
				List<MemberLabelGroup> memberLabelGroupList = memberLabelGroupService.selectByExample(memberLabelGroupExample);
				m.addObject("memberLabelGroupList", memberLabelGroupList);
			}
			taskSmsCustom.setTaskSendValues(taskSmsCustom.getTaskSendValues()==null?"":taskSmsCustom.getTaskSendValues().replaceAll(",", "\r\n"));
			m.addObject("taskSmsCustom", taskSmsCustom);
		}
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/taskSms/eidtTaskSms.shtml")
	public ModelAndView eidtTaskSms(HttpServletRequest request, MultipartFile taskFilePath ) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String staffId = this.getSessionStaffBean(request).getStaffID();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int sendMemberCount = 0;
			int sendCount = 0;
			String filePath = null;
			String taskSendType = request.getParameter("taskSendType");
			String taskSendValues = "";
			if(!StringUtil.isEmpty(taskSendType) && !"2".equals(taskSendType) ) {
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
							TaskSmsCustom taskSmsCustom = taskSmsService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("id")));
							sendMemberCount = taskSmsCustom.getSendMemberCount();
							String sendValues = taskSmsCustom.getTaskSendValues();
							if(!StringUtils.isEmpty(sendValues) ) {
								String[] vals = sendValues.split(",");
								sendMemberCount -= vals.length;
							}
						}
					}
				}
				//手动导入会员数
				taskSendValues = request.getParameter("taskSendValues").trim();
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
			}else {
				MemberInfoCustomExample memberInfoCustomExample = new MemberInfoCustomExample();
				MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria = memberInfoCustomExample.createCriteria();
				memberInfoCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("A").andMobileIsNotNull();
				memberInfoCustomCriteria.andNotBlack();
				memberInfoCustomCriteria.andNotPaichuMobile();
				sendMemberCount = memberInfoService.countMemberInfoCustomByExample(memberInfoCustomExample);
			}
			String taskSendMode = request.getParameter("taskSendMode");
			String isGiveCoupon = request.getParameter("isGiveCoupon");
			if ("1".equals(isGiveCoupon)) {
				Coupon coupon = couponService.selectByPrimaryKey(Integer.valueOf(request.getParameter("couponId")));
				int surplusQuantity = coupon.getGrantQuantity() - coupon.getRecQuantity();
				if(sendMemberCount > surplusQuantity){
					throw new ArgException("优惠券数量不足，请重新设置！");
				}
			}
			String taskContent = request.getParameter("taskContent").trim();
			if(!StringUtils.isEmpty(taskContent) && taskContent.length() > 64 && taskContent.length() <= 132 ) {
				sendCount = sendMemberCount*2;
			}else if(!StringUtils.isEmpty(taskContent) && taskContent.length() > 132 ){
				sendCount = sendMemberCount*3;
			}else{
				sendCount = sendMemberCount;
			}

			//任务短信
			TaskSms taskSms = new TaskSms();
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id) ) {
				taskSms.setId(Integer.parseInt(id));
				taskSms.setUpdateBy(Integer.parseInt(staffId));
				taskSms.setUpdateDate(date);
			}else {
				taskSms.setCreateBy(Integer.parseInt(staffId));
				taskSms.setCreateDate(date);
			}
			taskSms.setSendMemberCount(sendMemberCount);
			taskSms.setSendCount(sendCount);
			taskSms.setIsGiveCoupon(isGiveCoupon);
			String couponId = request.getParameter("couponId");
			if(!StringUtils.isEmpty(couponId) ){
				taskSms.setCouponId(Integer.valueOf(couponId));
			}
			String price = request.getParameter("price");
			if(!StringUtils.isEmpty(price) ) {
				taskSms.setTotalSendAmount(new BigDecimal(price).multiply(new BigDecimal(sendCount)).setScale(2, BigDecimal.ROUND_DOWN));
			}
			//任务
			Task task = new Task();
			task.setSendChannel(request.getParameter("taskSendChannel"));
			task.setName(request.getParameter("taskName"));
			task.setTaskExplain(request.getParameter("taskTaskExplain"));
			task.setContent(taskContent);
			task.setSendMode(taskSendMode);
			if("1".equals(taskSendMode) && !StringUtils.isEmpty(request.getParameter("taskSendDate")) ) {
				task.setSendDate(sdf.parse(request.getParameter("taskSendDate")));
			}
			task.setSendType(request.getParameter("taskSendType"));
			task.setSendCount(Integer.parseInt(request.getParameter("taskSendCount")));
			task.setFilePath(filePath);
			if(!StringUtils.isEmpty(taskSendValues) ) {
				task.setSendValues(taskSendValues);
			}
			task.setLabelGroupIds(request.getParameter("taskLabelGroupIds"));
			
			taskSmsService.eidtTaskSms(taskSms, task);
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (ArgException e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = e.getMessage();
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
	 * @date 2019年8月5日 上午11:22:08
	 */
	@ResponseBody
	@RequestMapping("/taskSms/auditTask.shtml")
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
	 * @MethodName: viewTaskSmsManager
	 * @Description: (任务详情)
	 * @author Pengl
	 * @date 2019年8月5日 下午1:35:24
	 */
	@RequestMapping("/taskSms/viewTaskSms.shtml")
	public ModelAndView viewTaskSmsManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/taskSms/viewTaskSms");
		List<SysStatus> sysStatusList = DataDicUtil.getStatusList("BU_TASK", "SEND_CHANNEL");
		for(SysStatus sysStatus : sysStatusList ) {
			SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
			switch (sysStatus.getStatusValue()){
				case Const.XW_SMS_SEND_CHANNEL:
					sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.XW_SEND_PRICE);
					break;
				case Const.XY_SMS_SEND_CHANNEL:
					sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.XY_SEND_PRICE);
					break;
				case Const.MW_SMS_SEND_CHANNEL:
					sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.MW_SEND_PRICE);
					break;
				default:
					continue;
			}
			List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
			if(sysParamCfgList != null && sysParamCfgList.size() > 0 ) {
				sysStatus.setRemark(sysParamCfgList.get(0).getParamValue());
			}
		}
		m.addObject("taskSendChannelList", sysStatusList);
		String taskSmsId = request.getParameter("taskSmsId");
		if(!StringUtils.isEmpty(taskSmsId) ) {
			TaskSmsCustom taskSmsCustom = taskSmsService.selectByCustomPrimaryKey(Integer.parseInt(taskSmsId));
			if(!StringUtils.isEmpty(taskSmsCustom.getTaskLabelGroupIds()) ) {
				String[] labelGroupIds = taskSmsCustom.getTaskLabelGroupIds().split(",");
				List<Integer> labelGroupIdList = new ArrayList<Integer>();
				for(String labelGroupId : labelGroupIds) {
					labelGroupIdList.add(Integer.parseInt(labelGroupId));
				}
				MemberLabelGroupExample memberLabelGroupExample = new MemberLabelGroupExample();
				memberLabelGroupExample.createCriteria().andDelFlagEqualTo("0").andIdIn(labelGroupIdList);
				List<MemberLabelGroup> memberLabelGroupList = memberLabelGroupService.selectByExample(memberLabelGroupExample);
				m.addObject("memberLabelGroupList", memberLabelGroupList);
			}
			taskSmsCustom.setTaskSendValues(taskSmsCustom.getTaskSendValues()==null?"":taskSmsCustom.getTaskSendValues().replaceAll(",", "\n"));
			m.addObject("taskSmsCustom", taskSmsCustom);
			List<TaskLogCustom> taskLogCustoms = new ArrayList<TaskLogCustom>();
			if(!"0".equals(taskSmsCustom.getTaskStatus()) ) {
				TaskLogCustomExample taskLogCustomExample = new TaskLogCustomExample();
				taskLogCustomExample.createCriteria().andDelFlagEqualTo("0").andTaskIdEqualTo(taskSmsCustom.getTaskId());
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
		}
		return m;
	}
	
	/**
	 * 
	 * @MethodName: auditOrApproveTaskManager
	 * @Description: (审核或审批)
	 * @author Pengl
	 * @date 2019年8月5日 下午4:16:07
	 */
	@RequestMapping("/taskSms/auditOrApproveTaskManager.shtml")
	public ModelAndView auditOrApproveTaskManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/taskSms/auditOrApproveTask");
		List<SysStatus> sysStatusList = DataDicUtil.getStatusList("BU_TASK", "SEND_CHANNEL");
		for(SysStatus sysStatus : sysStatusList ) {
			if("0".equals(sysStatus.getStatusValue()) ) {
				SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
				sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.XW_SEND_PRICE);
				List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
				if(sysParamCfgList != null && sysParamCfgList.size() > 0 ) {
					sysStatus.setRemark(sysParamCfgList.get(0).getParamValue());
				}
			}
			if("2".equals(sysStatus.getStatusValue()) ) {
				SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
				sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.XY_SEND_PRICE);
				List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
				if(sysParamCfgList != null && sysParamCfgList.size() > 0 ) {
					sysStatus.setRemark(sysParamCfgList.get(0).getParamValue());
				}
			}
		}
		m.addObject("taskSendChannelList", sysStatusList);
		String taskSmsId = request.getParameter("taskSmsId");
		if(!StringUtils.isEmpty(taskSmsId) ) {
			TaskSmsCustom taskSmsCustom = taskSmsService.selectByCustomPrimaryKey(Integer.parseInt(taskSmsId));
			if(!StringUtils.isEmpty(taskSmsCustom.getTaskLabelGroupIds()) ) {
				String[] labelGroupIds = taskSmsCustom.getTaskLabelGroupIds().split(",");
				List<Integer> labelGroupIdList = new ArrayList<Integer>();
				for(String labelGroupId : labelGroupIds) {
					labelGroupIdList.add(Integer.parseInt(labelGroupId));
				}
				MemberLabelGroupExample memberLabelGroupExample = new MemberLabelGroupExample();
				memberLabelGroupExample.createCriteria().andDelFlagEqualTo("0").andIdIn(labelGroupIdList);
				List<MemberLabelGroup> memberLabelGroupList = memberLabelGroupService.selectByExample(memberLabelGroupExample);
				m.addObject("memberLabelGroupList", memberLabelGroupList);
			}
			taskSmsCustom.setTaskSendValues(taskSmsCustom.getTaskSendValues()==null?"":taskSmsCustom.getTaskSendValues().replaceAll(",", "\n"));
			m.addObject("taskSmsCustom", taskSmsCustom);
			List<TaskLogCustom> taskLogCustoms = new ArrayList<TaskLogCustom>();
			if(!"0".equals(taskSmsCustom.getTaskStatus()) ) {
				TaskLogCustomExample taskLogCustomExample = new TaskLogCustomExample();
				taskLogCustomExample.createCriteria().andDelFlagEqualTo("0").andTaskIdEqualTo(taskSmsCustom.getTaskId());
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
			m.addObject("smsType", request.getParameter("smsType"));
		}
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/taskSms/auditOrApproveTask.shtml")
	public ModelAndView auditOrApproveTask(HttpServletRequest request ) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			Date date = new Date();
			String taskId = request.getParameter("taskId");
			if(!StringUtils.isEmpty(taskId) && TASK_AUDIT_STATUS ) {
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
						TASK_AUDIT_STATUS = false;
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
					if("4".equals(request.getParameter("status")) ) {
						TASK_AUDIT_STATUS = true;
					}
				}
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}else {
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = "有任务正在处理中，请稍后操作！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
			if("4".equals(request.getParameter("status")) ) {
				TASK_AUDIT_STATUS = true;
			}
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * @MethodName: getBlackList
	 * @Description: (短信黑名单)
	 * @author Pengl
	 * @date 2019年8月14日 上午11:13:41
	 */
	@ResponseBody
	@RequestMapping("/taskSms/getBlackList.shtml")
	public Map<String, Object> getBlackList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<BlackListCustom> dataList = null;
		Integer totalCount = 0;
		try {
			BlackListCustomExample blackListCustomExample = new BlackListCustomExample();
			BlackListCustomExample.BlackListCustomCriteria blackListCustomCriteria = blackListCustomExample.createCriteria();
			blackListCustomCriteria.andDelFlagEqualTo("0").andBlackTypeEqualTo("4");
			if(!StringUtils.isEmpty(request.getParameter("memberId")) ) {
				blackListCustomCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			if(!StringUtils.isEmpty(request.getParameter("memberMobile")) ) {
				blackListCustomCriteria.andMemberMobileLike("%"+request.getParameter("memberMobile")+"%");
			}
			blackListCustomExample.setOrderByClause(" t.id desc");
			blackListCustomExample.setLimitStart(page.getLimitStart());
			blackListCustomExample.setLimitSize(page.getLimitSize());
			totalCount = blackListService.countByCustomExample(blackListCustomExample);
			dataList = blackListService.selectByCustomExample(blackListCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	@RequestMapping("/taskSms/addBlackManager.shtml")
	public ModelAndView addBlackManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/taskSms/addBlack");
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/taskSms/addBlack.shtml")
	public ModelAndView addBlack(HttpServletRequest request ) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String memberId = request.getParameter("memberId");
			String memberMobile = request.getParameter("memberMobile");
            Pattern pattern = Pattern.compile("[0-9]*");
            if(!pattern.matcher(memberMobile).matches()){
                resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
                resMap.put(this.JSON_RESULT_MESSAGE, "此用户不存在！");
                return new ModelAndView(rtPage, resMap);
            }
			if(StringUtils.isEmpty(memberId) ) {
				MemberInfoExample memberInfoExample = new MemberInfoExample();
				memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(memberMobile);
				memberInfoExample.setLimitSize(1);
				List<MemberInfo> memberInfoList = memberInfoService.selectByExample(memberInfoExample);
				if(memberInfoList != null && memberInfoList.size() > 0 ) {
					BlackList blackList = new BlackList();
					blackList.setMemberId(memberInfoList.get(0).getId());
					blackList.setBlackType("4");
					blackList.setCreateBy(Integer.parseInt(staffID));
					blackList.setCreateDate(date);
					blackList.setDelFlag("0");
					blackListService.insertSelective(blackList);
					code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
					msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
				}else {
					code = StateCode.JSON_AJAX_ERROR.getStateCode();
					msg = "此用户不存在！";
				}
			}else{
                if(!pattern.matcher(memberId).matches() ){
                    resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
                    resMap.put(this.JSON_RESULT_MESSAGE, "此用户不存在！");
                    return new ModelAndView(rtPage, resMap);
                }
				MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(Integer.parseInt(memberId));
				if(memberInfo != null ) {
                    if (memberMobile.equals(memberInfo.getMobile())) {
                        BlackList blackList = new BlackList();
                        blackList.setMemberId(memberInfo.getId());
                        blackList.setBlackType("4");
                        blackList.setCreateBy(Integer.parseInt(staffID));
                        blackList.setCreateDate(date);
                        blackList.setDelFlag("0");
                        blackListService.insertSelective(blackList);
                        code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
                        msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
                    }else {
                        code = StateCode.JSON_AJAX_ERROR.getStateCode();
                        msg = "添加失败,手机号与用户ID不匹配!";
                    }
				}else {
					code = StateCode.JSON_AJAX_ERROR.getStateCode();
					msg = "此用户不存在！";
				}
			}
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
	 * @MethodName: delBlack
	 * @Description: (移出)
	 * @author Pengl
	 * @date 2019年8月14日 下午2:01:33
	 */
	@ResponseBody
	@RequestMapping("/taskSms/delBlack.shtml")
	public Map<String, Object> delBlack(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id) ) {
				Date date = new Date();
				String staffID = this.getSessionStaffBean(request).getStaffID();
				String[] ids = id.split(",");
				List<Integer> idList = new ArrayList<Integer>();
				for(String blackId : ids ) {
					idList.add(Integer.parseInt(blackId));
				}
				BlackList blackList = new BlackList();
				blackList.setUnblackBy(Integer.parseInt(staffID));
				blackList.setUpdateDate(date);
				blackList.setDelFlag("1");
				BlackListExample blackListExample = new BlackListExample();
				blackListExample.createCriteria().andDelFlagEqualTo("0").andIdIn(idList);
				blackListService.updateByExampleSelective(blackList, blackListExample);
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
	 * @MethodName: outBlack
	 * @Description: (导出)
	 * @author Pengl
	 * @date 2019年8月14日 下午2:12:43
	 */
	@RequestMapping("/taskSms/outBlack.shtml")
	public void outBlack(HttpServletRequest request, HttpServletResponse response) {
		try {
			BlackListCustomExample blackListCustomExample = new BlackListCustomExample();
			BlackListCustomExample.BlackListCustomCriteria blackListCustomCriteria = blackListCustomExample.createCriteria();
			blackListCustomCriteria.andDelFlagEqualTo("0").andBlackTypeEqualTo("4");
			if(!StringUtils.isEmpty(request.getParameter("memberId")) ) {
				blackListCustomCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			if(!StringUtils.isEmpty(request.getParameter("memberMobile")) ) {
				blackListCustomCriteria.andMemberMobileLike("%"+request.getParameter("memberMobile")+"%");
			}
			if(!StringUtils.isEmpty(request.getParameter("ids")) ) {
                Integer[] intArr = (Integer[]) ConvertUtils.convert(request.getParameter("ids").split(","), Integer.class);
				blackListCustomCriteria.andIdIn( Arrays.asList((Integer[]) ConvertUtils.convert(request.getParameter("ids").split(","), Integer.class)));
			}
			blackListCustomExample.setOrderByClause(" t.id desc");
			List<BlackListCustom> blackListCustomList = blackListService.selectByCustomExample(blackListCustomExample);
			String[] titles = {"用户ID", "用户昵称", "手机号"};
			ExcelBean excelBean = new ExcelBean("导出短信黑名单.xls","导出短信黑名单", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(BlackListCustom blackListCustom : blackListCustomList) {
				String[] data = {
						blackListCustom.getMemberId()+"\t",
						blackListCustom.getMemberNick()+"\t",
						blackListCustom.getMemberMobile()+"\t"
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @MethodName: inBlackManager
	 * @Description: (导入)
	 * @author Pengl
	 * @date 2019年8月14日 下午2:19:02
	 */
	@RequestMapping("/taskSms/inBlackManager.shtml")
	public ModelAndView inBlackManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/taskSms/inBlack");
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/taskSms/inBlack.shtml")
	public ModelAndView inBlack(HttpServletRequest request, MultipartFile excelFile ) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String staffId = this.getSessionStaffBean(request).getStaffID();
			int excelRows = 0;
			//导入文件会员数
			if(!excelFile.isEmpty() ) {
				Workbook wb = null;
				//根据文件后缀（xls/xlsx）进行判断
                if (excelFile.getOriginalFilename().toLowerCase().endsWith(".xls") ) {
                    wb = new HSSFWorkbook(excelFile.getInputStream());
                }else if (excelFile.getOriginalFilename().toLowerCase().endsWith(".xlsx") ){
                    wb = new XSSFWorkbook(excelFile.getInputStream());
                }
                if(wb != null ) {
                	for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                		Sheet sheet = wb.getSheetAt(i);
                		if(sheet != null && sheet.getPhysicalNumberOfRows() > 1) {
                			excelRows += sheet.getPhysicalNumberOfRows()-1;
                		}
					}
                }
			}
			if(excelRows > 5000 ) {
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = "导入文件超过五千行！";
			}else if(excelRows > 0 && excelRows <= 5000 ) {
				Set<String> set = new HashSet<String>();
				List<ArrayList<String>> dataList = ExcelUtils.setCellRead(excelFile.getInputStream(), excelFile.getOriginalFilename(), 1, "0", 1);
				//1表示取excel表格的前1列
				Pattern pattern = Pattern.compile("^1[3|5|7|8][0-9]{9}$");
				for (ArrayList<String> arrayList : dataList) {
					if(pattern.matcher(arrayList.get(0)).matches()){
						set.add(arrayList.get(0));
					}
				}
				Map<String, Object> map = blackListService.inBlack(Integer.parseInt(staffId), excelRows, set);
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = "成功<span style=\"color:red;\">"+map.get("successNum")+"</span>条，失败<span style=\"color:red;\">"+map.get("loserNum")+"</span>条！";
			}else {
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}
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
	 * getDetailedInformation
	 * 获取已执行任务7天内登录用户数,7天内付款用户,7天内付款订单数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/taskSms/getDetailedInformation.shtml")
	@ResponseBody
	public Map<String, Object> getDetailedInformation(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			String sendType = request.getParameter("sendType");
			String taskId = request.getParameter("taskId");
			String type = request.getParameter("type");
			if(StringUtils.isEmpty(sendType) || StringUtils.isEmpty(taskId) || StringUtils.isEmpty(type)){
				resMap.put("code", "4004");
				resMap.put("msg", "参数异常");
				return resMap;
			}
			TaskSendMemberExample taskSendMemberExample = new TaskSendMemberExample();
			taskSendMemberExample.createCriteria().andDelFlagEqualTo("0").andTaskIdEqualTo(Integer.parseInt(taskId)).andStatusEqualTo("2");
			taskSendMemberExample.setOrderByClause(" send_date asc");
			taskSendMemberExample.setLimitStart(0);
			taskSendMemberExample.setLimitSize(1);
			List<TaskSendMember> taskSendMembers = taskSendMemberService.selectByExample(taskSendMemberExample);
			if(taskSendMembers == null || taskSendMembers.size() <= 0){
				resMap.put("code", "4004");
				resMap.put("msg", "未找到成功发送短信的记录!");
				return resMap;
			}
			paramMap.put("firstSendDate", taskSendMembers.get(0).getSendDate());
			paramMap.put("sendType", sendType);
			paramMap.put("taskId", taskId);
			Integer reQuantityRate = taskSmsService.getDetailedInformation(paramMap,type);
			resMap.put("reQuantityRate", reQuantityRate);
			resMap.put("code", "200");
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("code", "4004");
			resMap.put("msg", "获取数据失败");
		}
		return resMap;
	}

	/**
	 * 校验优惠券ID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/taskSms/checkCouponId.shtml")
	@ResponseBody
	public Map<String, Object> checkCouponId(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String couponId = request.getParameter("couponId");
			Date date = sdf.parse(request.getParameter("date"));
			CouponExample example = new CouponExample();
			CouponExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(Integer.valueOf(couponId));
			criteria.andStatusEqualTo("1");//启用中
			criteria.andDelFlagEqualTo("0");
			criteria.andRecBeginDateLessThanOrEqualTo(date);
			criteria.andRecEndDateGreaterThanOrEqualTo(date);
			List<Coupon> coupons = couponService.selectByExample(example);
			if (CollectionUtils.isEmpty(coupons)) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "优惠券输入有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "优惠券输入有误");
		}
		return resMap;
	}

}
