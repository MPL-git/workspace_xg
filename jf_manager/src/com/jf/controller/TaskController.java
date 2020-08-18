package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaExample;
import com.jf.entity.DecorateModuleCustom;
import com.jf.entity.DecorateModuleExample;
import com.jf.entity.MemberLabelGroup;
import com.jf.entity.MemberLabelGroupCustomExample;
import com.jf.entity.ModuleItemExample;
import com.jf.entity.StateCode;
import com.jf.entity.Task;
import com.jf.entity.TaskActivitySelection;
import com.jf.entity.TaskActivitySelectionExample;
import com.jf.entity.TaskCustom;
import com.jf.entity.TaskCustomExample;
import com.jf.entity.TaskExample.Criteria;
import com.jf.entity.TaskLog;
import com.jf.service.DecorateAreaService;
import com.jf.service.DecorateModuleService;
import com.jf.service.MemberLabelGroupService;
import com.jf.service.ModuleItemService;
import com.jf.service.TaskActivitySelectionService;
import com.jf.service.TaskService;
import com.jf.vo.Page;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

@Controller
@RequestMapping
public class TaskController extends BaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private TaskService taskService;
	
	@Resource
	private DecorateAreaService decorateAreaService;
	
	@Resource
	private DecorateModuleService decorateModuleService;
	
	@Resource
	private MemberLabelGroupService memberLabelGroupService;
	
	@Resource
	private ModuleItemService moduleItemService;
	
	@Resource
	private TaskActivitySelectionService taskActivitySelectionService;

	//任务精选列表
	@RequestMapping(value = "/task/taskList.shtml")
	public ModelAndView taskList(HttpServletRequest request ) {	
		ModelAndView m = new ModelAndView();
		m.setViewName("/task/taskList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = sdf.format(new Date());
		String dateBegin = dateEnd.substring(0,7)+"-01";
		m.addObject("dateEnd", dateEnd);
		m.addObject("dateBegin", dateBegin);
		ResourceBundle resource = ResourceBundle.getBundle("base_config");
		String mUrl = resource.getString("mUrl");
		m.addObject("previewUrl", mUrl+"/xgbuy/views/activity/nest/decorate/brand_decorate_activesel.html?id=");
		return m;
	}
	
	//获取任务精选数据
	@ResponseBody
	@RequestMapping(value="/task/getTaskList.shtml")
	public Map<String, Object> getTaskList(HttpServletRequest request,Page page){
		Map<String,Object> map = new HashMap<String, Object>();
		List<TaskCustom> list = new ArrayList<TaskCustom>();
		Integer totalCount = 0;
		try{
			//查询任务精选数据
			TaskCustomExample example = new TaskCustomExample();
			example.setOrderByClause("id desc");
			Criteria criteria = example.createCriteria();
			example.setLimitSize(page.getLimitSize());
			example.setLimitStart(page.getLimitStart());
			criteria.andDelFlagEqualTo("0");
			criteria.andTypeEqualTo("2");//活动精选
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(request.getParameter("name"))){
				criteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			if(!StringUtils.isEmpty(request.getParameter("status"))){
				criteria.andStatusEqualTo(request.getParameter("status"));
			}
			if (!StringUtil.isEmpty(request.getParameter("create_date_begin"))) { // 创建日期
				criteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("create_date_begin") + " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("create_date_end"))) {
				criteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("create_date_end") + " 23:59:59"));
			}
			totalCount = taskService.countByExample(example);
			list = taskService.selectByExample(example);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = new ArrayList();
			totalCount = 0;
		}
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
	
	//删除任务精选
	@ResponseBody
	@RequestMapping(value = "/task/deleteTask.shtml")
	public Map<String, Object> deleteTask(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		Integer id = Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
		int staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		try {
			Task task = new Task();
			task.setId(id);
			task.setStatus("7");//7.取消
			task.setDelFlag("1");;
			task.setUpdateBy(staffID);
			task.setUpdateDate(date);
			
			//存入buTaskLog
			TaskLog taskLog = new TaskLog();
			taskLog.setTaskId(id);
			taskLog.setOperatorType("0");
			taskLog.setStatus("4");
			taskLog.setCreateBy(staffID);
			taskLog.setCreateDate(date);
			taskLog.setDelFlag("0");
			
			taskService.editTask(task,taskLog);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put(this.JSON_RESULT_CODE,code);
		map.put(this.JSON_RESULT_MESSAGE, msg);
		return map;
	}
	
	//选择分组
	@RequestMapping(value = "/task/addMemberLabelGroup.shtml")
	public ModelAndView addMemberLabelGroup(HttpServletRequest request ) {	
		ModelAndView m = new ModelAndView();
		m.setViewName("/task/addMemberLabelGroup");
		return m;
	}
	
	//提审任务精选
	@ResponseBody
	@RequestMapping(value = "/task/arraigned.shtml")
	public Map<String, Object> arraigned(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		Integer id = Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
		int staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		try {
			Task task = new Task();
			task.setId(id);
			task.setStatus("1");//1.待审核
			task.setUpdateBy(staffID);
			task.setUpdateDate(date);
			
			//存入buTaskLog
			TaskLog taskLog = new TaskLog();
			taskLog.setTaskId(id);
			taskLog.setOperatorType("0");
			taskLog.setStatus("0");
			taskLog.setCreateBy(staffID);
			taskLog.setCreateDate(date);
			taskLog.setDelFlag("0");
			
			taskService.editTask(task,taskLog);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put(this.JSON_RESULT_CODE,code);
		map.put(this.JSON_RESULT_MESSAGE, msg);
		return map;
	}
	
    //详情的任务精选初始页面
	@RequestMapping(value = "/task/taskDetails.shtml")
	public ModelAndView taskDetails(HttpServletRequest request) {
		String rtPage = "/task/taskDetails";
		String id=request.getParameter("Id");		
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (id!=null && id!="") {
			TaskCustomExample e = new TaskCustomExample();
			e.createCriteria().andIdEqualTo(Integer.parseInt(id)).andDelFlagEqualTo("0");
			List<TaskCustom> list = taskService.detailAuditSelectByExample(e);
			TaskCustom taskCustom = list.get(0);
			resMap.put("taskCustom", taskCustom);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			resMap.put("sendDate", sdf.format(taskCustom.getSendDate()));
			resMap.put("createDate", sdf.format(taskCustom.getCreateDate()));
			if(taskCustom.getYyAuditDate()!=null){
				resMap.put("yyAuditDate", sdf.format(taskCustom.getYyAuditDate()));
			}
			if(taskCustom.getFwAuditDate()!=null){
				resMap.put("fwAuditDate", sdf.format(taskCustom.getFwAuditDate()));
			}
			if(taskCustom.getFilePath()!=null){
				resMap.put("excelFilePathName", taskCustom.getFilePath().split("/")[3]);
			}
			if(taskCustom.getSendValues()!=null){
				resMap.put("sendValues", taskCustom.getSendValues().replaceAll(",", "\r\n"));
			}
			if(taskCustom.getLabelGroupIds()!=null){
				String[] labelGroupIdsArr = taskCustom.getLabelGroupIds().split(",");
				Integer[] labelGroupIds= (Integer[]) ConvertUtils.convert(labelGroupIdsArr, Integer.class);
				MemberLabelGroupCustomExample example = new MemberLabelGroupCustomExample();
				example.createCriteria().andIdIn(Arrays.asList(labelGroupIds)).andDelFlagEqualTo("0");
				List<MemberLabelGroup> memberLabelGroups = memberLabelGroupService.selectByExample(example);
				resMap.put("memberLabelGroups", memberLabelGroups);
			}
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	//编辑或添加的任务精选初始页面
	@RequestMapping(value = "/task/taskEdit.shtml")
	public ModelAndView taskEdit(HttpServletRequest request) {
		String rtPage = "/task/taskEdit";
		String id=request.getParameter("Id");		
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (id!=null && id!="") {
			TaskCustomExample e = new TaskCustomExample();
			e.createCriteria().andIdEqualTo(Integer.parseInt(id)).andDelFlagEqualTo("0");
			List<TaskCustom> list = taskService.selectByExample(e);
			TaskCustom taskCustom = list.get(0);
			resMap.put("taskCustom", taskCustom);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			resMap.put("sendDate", sdf.format(taskCustom.getSendDate()));
			if(taskCustom.getFilePath()!=null){
				resMap.put("excelFilePathName", taskCustom.getFilePath().split("/")[3]);	
			}
			if(taskCustom.getSendValues()!=null){
			resMap.put("sendValues", taskCustom.getSendValues().replaceAll(",", "\r\n"));
			}
			if(taskCustom.getLabelGroupIds()!=null){
			String[] labelGroupIdsArr = taskCustom.getLabelGroupIds().split(",");
			Integer[] labelGroupIds= (Integer[]) ConvertUtils.convert(labelGroupIdsArr, Integer.class);
			MemberLabelGroupCustomExample example = new MemberLabelGroupCustomExample();
			example.createCriteria().andIdIn(Arrays.asList(labelGroupIds)).andDelFlagEqualTo("0");
			List<MemberLabelGroup> memberLabelGroups = memberLabelGroupService.selectByExample(example);
			resMap.put("memberLabelGroups", memberLabelGroups);
			}
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 编辑任务精选
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/task/confirmTaskEdit.shtml")
	public ModelAndView confirmTaskEdit(HttpServletRequest request, MultipartFile filePath ) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			Task task = new Task();
			if(!StringUtils.isEmpty(request.getParameter("id"))){
				String id = request.getParameter("id");	
				 task = taskService.selectByPrimaryKey(Integer.parseInt(id));
			}
			if(!StringUtils.isEmpty(request.getParameter("name"))){
				String name = request.getParameter("name");	
				task.setName(name);
			}
			String coverPic = "";
			if(!StringUtils.isEmpty(request.getParameter("coverPic"))){
				coverPic = request.getParameter("coverPic");
			}
			if(!StringUtils.isEmpty(request.getParameter("content"))){
				String content = request.getParameter("content");
				task.setContent(content);
			}
			if(!StringUtils.isEmpty(request.getParameter("sendMode"))){
				String sendMode = request.getParameter("sendMode");
				task.setSendMode(sendMode);
				if(sendMode.equals("0")){
					task.setSendDate(new Date());
				}else if(sendMode.equals("1")){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					task.setSendDate(sdf.parse(request.getParameter("sendDate")));
				}
			}
			if(!StringUtils.isEmpty(request.getParameter("taskExplain"))){
				String taskExplain = request.getParameter("taskExplain");
				task.setTaskExplain(taskExplain);
			}
			if(!StringUtils.isEmpty(request.getParameter("sendValues"))){
				task.setSendValues(request.getParameter("sendValues").replaceAll("\r\n", ","));
			}
			if(!StringUtils.isEmpty(request.getParameter("labelGroupIds"))){
				task.setLabelGroupIds(request.getParameter("labelGroupIds"));
			}
			if(!filePath.isEmpty()){
				String excelFilePath = FileUtil.saveFile(filePath.getInputStream(), filePath.getOriginalFilename(), 14, 0);
				task.setFilePath(excelFilePath);
			}
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			taskService.confirmTaskEdit(task,staffID,coverPic);
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
	 * 装修页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/task/design.shtml")
	public ModelAndView design(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/task/design");
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		if(decorateAreas!=null && decorateAreas.size()>0){
			m.addObject("decorateAreaId", decorateAreas.get(0).getId());
			DecorateModuleExample dme = new DecorateModuleExample();
			dme.setOrderByClause("ifnull(t.seq_no,99999) asc,t.id desc");
			DecorateModuleExample.Criteria dmec = dme.createCriteria();
			dmec.andDelFlagEqualTo("0");
			dmec.andDecorateAreaIdEqualTo(decorateAreas.get(0).getId());
			List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
			for (DecorateModuleCustom decorateModuleCustom : decorateModuleCustoms) {
				if(decorateModuleCustom.getModuleType().equals("2")){//商品
					ModuleItemExample mie = new ModuleItemExample();
					mie.setOrderByClause("IFNULL(seq_no,99999) ASC,id desc");
					ModuleItemExample.Criteria miec = mie.createCriteria();
					miec.andDelFlagEqualTo("0");
					miec.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
					miec.andItemTypeEqualTo("1");//1.商品 2.特卖
					int count = moduleItemService.countByExample(mie);
					decorateModuleCustom.setCount(count);
				}else if(decorateModuleCustom.getModuleType().equals("3")){//特卖
					ModuleItemExample mie = new ModuleItemExample();
					mie.setOrderByClause("IFNULL(seq_no,99999) ASC,id desc");
					ModuleItemExample.Criteria miec = mie.createCriteria();
					miec.andDelFlagEqualTo("0");
					miec.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
					miec.andItemTypeEqualTo("2");//1.商品 2.特卖
					int count = moduleItemService.countByExample(mie);
					decorateModuleCustom.setCount(count);
				}
			}
			m.addObject("decorateModuleCustoms", decorateModuleCustoms);
		}
		Integer taskId = Integer.parseInt(request.getParameter("taskId"));
		ResourceBundle resource = ResourceBundle.getBundle("base_config"); 
		String mUrl = resource.getString("mUrl");
		TaskActivitySelectionExample taskActivitySelectionExample = new TaskActivitySelectionExample();
		taskActivitySelectionExample.createCriteria().andTaskIdEqualTo(taskId).andDelFlagEqualTo("0");
		List<TaskActivitySelection> TaskActivitySelections = taskActivitySelectionService.selectByExample(taskActivitySelectionExample);
		m.addObject("previewUrl", mUrl+"/xgbuy/views/activity/nest/decorate/brand_decorate_activesel.html?id="+TaskActivitySelections.get(0).getId());
		//将任务精选的状态修改为待提审
		Task task = new Task();
		task.setId(taskId);
		task.setStatus("0");//待提审
		taskService.updateByPrimaryKeySelective(task);
		return m;
	}
	
	/**
	 * 运营审核列表页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/task/yyAuditTaskList.shtml")
	public ModelAndView yyAduitTaskList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/task/yyAuditTaskList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = sdf.format(new Date());
		String dateBegin = dateEnd.substring(0,7)+"-01";
		m.addObject("dateEnd", dateEnd);
		m.addObject("dateBegin", dateBegin);
		ResourceBundle resource = ResourceBundle.getBundle("base_config");
		String mUrl = resource.getString("mUrl");
		m.addObject("previewUrl", mUrl+"/xgbuy/views/activity/nest/decorate/brand_decorate_activesel.html?id=");
		return m;
	}
	
	/**
	 * 法务审核列表页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/task/fwAuditTaskList.shtml")
	public ModelAndView fwAduitTaskList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/task/fwAuditTaskList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = sdf.format(new Date());
		String dateBegin = dateEnd.substring(0,7)+"-01";
		m.addObject("dateEnd", dateEnd);
		m.addObject("dateBegin", dateBegin);
		ResourceBundle resource = ResourceBundle.getBundle("base_config");
		String mUrl = resource.getString("mUrl");
		m.addObject("previewUrl", mUrl+"/xgbuy/views/activity/nest/decorate/brand_decorate_activesel.html?id=");
		return m;
	}
	
	/**
	 * 审核页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/task/taskAudit.shtml")
	public ModelAndView taskAudit(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/task/taskAudit");
		String id=request.getParameter("Id");
		if (id!=null && id!="") {
			TaskCustomExample e = new TaskCustomExample();
			e.createCriteria().andIdEqualTo(Integer.parseInt(id)).andDelFlagEqualTo("0");
			List<TaskCustom> list = taskService.detailAuditSelectByExample(e);
			TaskCustom taskCustom = list.get(0);
			m.addObject("taskCustom", taskCustom);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			m.addObject("sendDate", sdf.format(taskCustom.getSendDate()));
			m.addObject("createDate", sdf.format(taskCustom.getCreateDate()));
			if(taskCustom.getYyAuditDate()!=null){
				m.addObject("yyAuditDate", sdf.format(taskCustom.getYyAuditDate()));
			}
			if(taskCustom.getFilePath()!=null){
				m.addObject("excelFilePathName", taskCustom.getFilePath().split("/")[3]);	
			}
			if(taskCustom.getSendValues()!=null){
				m.addObject("sendValues", taskCustom.getSendValues().replaceAll(",", "\r\n"));
			}
			if(taskCustom.getLabelGroupIds()!=null){
			String[] labelGroupIdsArr = taskCustom.getLabelGroupIds().split(",");
			Integer[] labelGroupIds= (Integer[]) ConvertUtils.convert(labelGroupIdsArr, Integer.class);
			MemberLabelGroupCustomExample example = new MemberLabelGroupCustomExample();
			example.createCriteria().andIdIn(Arrays.asList(labelGroupIds)).andDelFlagEqualTo("0");
			List<MemberLabelGroup> memberLabelGroups = memberLabelGroupService.selectByExample(example);
			m.addObject("memberLabelGroups", memberLabelGroups);
			}
		}
		m.addObject("type", request.getParameter("type"));//1.运营审核 2.法务审核		
		return m;
	}
	
	/**
	 * 提交任务审核
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/task/confirmTaskAudit.shtml")
	@ResponseBody
	public Map<String, Object> confirmTaskAudit(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Task task = new Task();
			String type = "";
			String auditStatus = "";
			String statusRemarks = "";
			if(!StringUtils.isEmpty(request.getParameter("id"))){
				String id = request.getParameter("id");	
				 task = taskService.selectByPrimaryKey(Integer.parseInt(id));
			}else{
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "创建失败，请稍后重试");
				return resMap;
			}
			if(!"1".equals(task.getStatus()) && !"2".equals(task.getStatus())){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "当前任务状态已改变");
				return resMap;
			}
			if(!StringUtils.isEmpty(request.getParameter("type"))){
				type = request.getParameter("type");	
			}
			if(!StringUtils.isEmpty(request.getParameter("auditStatus"))){
				auditStatus = request.getParameter("auditStatus");	
			}
			if(!StringUtils.isEmpty(request.getParameter("statusRemarks"))){
				statusRemarks = request.getParameter("statusRemarks");	
			}
			
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			taskService.confirmTaskAudit(task,type,auditStatus,statusRemarks,staffID);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "创建成功");
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "创建失败，请稍后重试");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 下载Excel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value ="/task/downLoadUserCodeExcel.shtml")
	public void downLoadUserCodeExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String filePath = request.getParameter("filePath");
		/*String fileName = request.getParameter("fileName");*/
		InputStream stream = OrderController.class.getResourceAsStream("/base_config.properties");
		String defaultPath=null;
		try {
			Properties properties = new Properties();
			properties.load(stream);
			defaultPath = properties.getProperty("annex.filePath");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(defaultPath==null){
			return;
		}
		File file = new File(defaultPath+filePath);
		//如果文件不存在
		if(!file.exists()){
		    return;
		}
		//设置响应头，控制浏览器下载该文件
		String downloadFileName = "";
		String agent = request.getHeader("USER-AGENT");  
        if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
        {
            downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(filePath.getBytes("UTF-8")))) + "?=";    
        }
        else
        {
            downloadFileName =  java.net.URLEncoder.encode(filePath, "UTF-8");
        }
		response.setHeader("content-disposition", "attachment;filename=" + downloadFileName);
		//读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream(defaultPath+filePath);
		//创建输出流
		OutputStream out = response.getOutputStream();
		//缓存区
		byte buffer[] = new byte[1024];
		int len = 0;
		//循环将输入流中的内容读取到缓冲区中
		while((len = in.read(buffer)) > 0){
		    out.write(buffer, 0, len);
		}
		//关闭
		in.close();
		out.flush();
		out.close(); 
	}
	
}
