package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class WorkController extends BaseController {
	
	private static final long serialVersionUID = 1L;
		
	@Resource
	private WoRkService woRkService;
	
	@Resource
	private WoRkHistoryService woRkHistoryService;
	
	@Resource
	private WorkRecordService workRecordService;
	
	@Resource
	private AttachmentHistoryService attachmentHistoryService;
	
	@Resource
	private AttachmentService attachmentService;

	@Resource
	private SysOrganizationService sysOrganizationService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
		
	@Resource
	private InterventionOrderService interventionOrderService;
	
	@Resource
	private AppealOrderService appealOrderService;
	
	@Resource
	private SubOrderService subOrderService;

	@Resource
	private MchtInfoService mchtInfoService;
	
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	
	//我创建的工单
	@RequestMapping("/work/myWorkList.shtml")
	public ModelAndView myWorkList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/woRk/myWorkList");
		
		StaffBean staffBean = this.getSessionStaffBean(request);
		Integer staffId = Integer.valueOf(staffBean.getStaffID());
		m.addObject("staffId",staffId);
		
		WoRkCustomExample woRkCustomExample = new WoRkCustomExample();
		WoRkCustomExample.WoRkCustomCriteria woRkCustomCriteria = woRkCustomExample.createCriteria();
		woRkCustomCriteria.andDelFlagEqualTo("0");
		woRkCustomCriteria.andCreateByEqualTo(staffId);
		List<WoRkCustom> woRkCustom=woRkService.selectByCustomExample(woRkCustomExample);	
		if (woRkCustom!=null && woRkCustom.size()>0) {
			
			List<Integer> woIntegers=new ArrayList<Integer>();
			for (WoRkCustom woRkCustom2 : woRkCustom) {
				woIntegers.add(woRkCustom2.getStaffId());
			}						
			SysStaffInfoExample staffInfoExample=new SysStaffInfoExample();
			staffInfoExample.createCriteria().andStaffIdIn(woIntegers);
			List<SysStaffInfo> sysStaffInfos=sysStaffInfoService.selectByExample(staffInfoExample);
			m.addObject("sysStaffInfos",sysStaffInfos);								
			
		}
		return m;
	}
	
	//我创建工单数据列表
	@ResponseBody
	@RequestMapping("/work/data.shtml")
	public Map<String, Object> getmyWorkList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<WoRkCustom> dataList = null;
		Integer totalCount = 0;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			
			WoRkCustomExample woRkCustomExample = new WoRkCustomExample();
			WoRkCustomExample.WoRkCustomCriteria woRkCustomCriteria = woRkCustomExample.createCriteria();
			woRkCustomCriteria.andDelFlagEqualTo("0").andCreateByEqualTo(staffId);
			woRkCustomExample.setOrderByClause("w.create_date desc");
			
			if (!StringUtil.isEmpty(request.getParameter("workType"))) {
				String workType=request.getParameter("workType");
				if (workType.equals("1")) {
					woRkCustomCriteria.andWorkTypeEqualTo("1");
				}
				if (workType.equals("2")) {
					woRkCustomCriteria.andWorkTypeEqualTo("2");
				}
				if (workType.equals("3")) {
					woRkCustomCriteria.andWorkTypeEqualTo("3");
				}
				if (workType.equals("4")) {
					woRkCustomCriteria.andWorkTypeEqualTo("4");
				}
				if (workType.equals("5")) {
					woRkCustomCriteria.andWorkTypeEqualTo("5");
				}
				if (workType.equals("6")) {
					woRkCustomCriteria.andWorkTypeEqualTo("6");
				}
				if (workType.equals("7")) {
					woRkCustomCriteria.andWorkTypeEqualTo("7");
				}
				
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) {
				String status=request.getParameter("status");
				if (status.equals("0")) {
					woRkCustomCriteria.andStatusEqualTo("0");
					woRkCustomCriteria.andStatusBehaviorBetween("1", "3");
					
				}
				if (status.equals("1")) {
					woRkCustomCriteria.andStatusEqualTo("1");
					
				}
				if (status.equals("2")) {
					woRkCustomCriteria.andStatusEqualTo("2");
					
				}
				
			}
			if (!StringUtil.isEmpty(request.getParameter("titleContent"))) {
				woRkCustomCriteria.andTitleContentLike("%"+request.getParameter("titleContent")+"%");
				
			}
			if (!StringUtil.isEmpty(request.getParameter("id"))) {
				woRkCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			if (!StringUtil.isEmpty(request.getParameter("staffId"))) {
				woRkCustomCriteria.andStaffIdEqualTo(Integer.valueOf(request.getParameter("staffId")));
			}
			if (!StringUtil.isEmpty(request.getParameter("startCreateDate")) && !StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				woRkCustomCriteria.andCreateDateBetween(sdf.parse(request.getParameter("startCreateDate")+" 00:00:00"), sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));				
			}
			woRkCustomExample.setLimitStart(page.getLimitStart());
			woRkCustomExample.setLimitSize(page.getLimitSize());
			totalCount = woRkService.countByCustomExample(woRkCustomExample);
			dataList = woRkService.selectByCustomExample(woRkCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	    //新增工单界面
		@RequestMapping("/work/addwork.shtml")
		public ModelAndView addwork(HttpServletRequest request) {
			ModelAndView m = new ModelAndView("/woRk/addWork");
			SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
			sysOrganizationExample.createCriteria().andStatusEqualTo("A");
			List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
			m.addObject("sysOrganizationlist",sysOrganizationlist);
			
			// 知识产权投诉受理模块 创建工单
			if(!StringUtil.isEmpty(request.getParameter("rightAppealId"))){
				String rightAppealId = request.getParameter("rightAppealId");
				m.addObject("relevantCode", rightAppealId);// 相关单号
				m.addObject("relevantType", "7");// 相关类型
				m.addObject("workType", "7");// 所属类型
				m.addObject("urgentDegree", "4");// 紧急程度
			}
			
			return m;
		}
		
		//获取指派人
		@ResponseBody
		@RequestMapping("/work/staffIdinfolist.shtml")
		public Map<String, Object> staffIdinfolist(HttpServletRequest request) {
			Map<String, Object> map = new HashMap<String, Object>();
			String code = "200";
			String msg = "操作成功！";
			try {
				String orgId = request.getParameter("orgId");
				if(!StringUtil.isEmpty(orgId)) {
					SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
					sysStaffInfoExample.createCriteria().andStatusEqualTo("A").andOrgIdEqualTo(Integer.valueOf(orgId));
					List<SysStaffInfo> sysStaffInfolist=sysStaffInfoService.selectByExample(sysStaffInfoExample);
					map.put("sysStaffInfolist", sysStaffInfolist);
					
				}else {
					code = "9999";
					msg = "指派部门不能为空！";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				code = "9999";
				msg = "系统错误！";
			}
			map.put("code", code);
			map.put("msg", msg);
			return map;
		}
		
		
		        //添加工单数据
				@ResponseBody
				@RequestMapping("/work/addworklist.shtml")
				public ModelAndView addworklist(HttpServletRequest request, WoRk work,String attachmentName, String attachmentPath) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = "";
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						Integer staffId = Integer.valueOf(staffBean.getStaffID());
						Date date = new Date();
						if(work.getId() == null) {
						   work.setStatus("0");
						   work.setStatusBehavior("1");
						   work.setDelFlag("0");
						   work.setCreateBy(staffId);
						   work.setCreateDate(date);
						   
						   if (work.getRelevantType().equals("1")) {
							   InterventionOrderCustomExample interventionOrderCustomExample=new InterventionOrderCustomExample();
							   interventionOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andInterventionCodeEqualTo(work.getRelevantCode());
							   List<InterventionOrderCustom> interventionOrderCustom=interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
							   if (interventionOrderCustom!=null && interventionOrderCustom.size()>0) {
								   work.setRelevantId(interventionOrderCustom.get(0).getId());
								
							    }else {
							        code = StateCode.JSON_AJAX_ERROR.getStateCode();
							    	msg = "相关单号和类型不符或者无此相关单号！";
									resMap.put(this.JSON_RESULT_CODE, code);
									resMap.put(this.JSON_RESULT_MESSAGE, msg);
									return new ModelAndView(rtPage, resMap);
									
								}
							   
						   }		   
						   if (work.getRelevantType().equals("2")) {
							   AppealOrderExample appealOrderExample=new AppealOrderExample();
							   appealOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(work.getRelevantCode());
							   List<AppealOrder> appealOrder=appealOrderService.selectByExample(appealOrderExample);
							   if (appealOrder!=null && appealOrder.size()>0) {
								   work.setRelevantId(appealOrder.get(0).getId());
								
							   }else {
								    code = StateCode.JSON_AJAX_ERROR.getStateCode();
								    msg = "相关单号和相关类型不符或者无此相关单号！";
									resMap.put(this.JSON_RESULT_CODE, code);
									resMap.put(this.JSON_RESULT_MESSAGE, msg);
									return new ModelAndView(rtPage, resMap);
							 }
						   }
						   if (work.getRelevantType().equals("4")) {
							   SubOrderExample subOrderExample=new SubOrderExample();
							   subOrderExample.createCriteria().andDelFlagEqualTo("0").andSubOrderCodeEqualTo(work.getRelevantCode());
							   List<SubOrder> subOrder=subOrderService.selectByExample(subOrderExample);
							   if (subOrder!=null && subOrder.size()>0) {
								   work.setRelevantId(subOrder.get(0).getId());
								
							   }else {
								    code = StateCode.JSON_AJAX_ERROR.getStateCode();
								    msg = "相关单号和相关类型不符或者无此相关单号！";
									resMap.put(this.JSON_RESULT_CODE, code);
									resMap.put(this.JSON_RESULT_MESSAGE, msg);
									return new ModelAndView(rtPage, resMap);
							  }
						   }
						   if (work.getRelevantType().equals("5")) {
							   CustomerServiceOrderExample customerServiceOrderExample=new CustomerServiceOrderExample();
							   customerServiceOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(work.getRelevantCode());
							   List<CustomerServiceOrder> customerServiceOrder=customerServiceOrderService.selectByExample(customerServiceOrderExample);
							   if (customerServiceOrder!=null && customerServiceOrder.size()>0) {
								   work.setRelevantId(customerServiceOrder.get(0).getId());
								 
							   }else {
								    code = StateCode.JSON_AJAX_ERROR.getStateCode();
								    msg = "相关单号和相关类型不符或者无此相关单号！";
									resMap.put(this.JSON_RESULT_CODE, code);
									resMap.put(this.JSON_RESULT_MESSAGE, msg);
									return new ModelAndView(rtPage, resMap);
							  }
						   }
						   
						   woRkService.insertSelective(work);
						   
						   
						   
						   Attachment attachment=new Attachment();
						   attachment.setWorkId(work.getId());
						   attachment.setAttachmentName(attachmentName);
						   attachment.setAttachmentPath(attachmentPath);
						   attachment.setDelFlag("0");
						   attachment.setCreateBy(staffId);
						   attachment.setCreateDate(date);
						   attachmentService.insertSelective(attachment);
						   						   
						   
						   WorkHistory workHistory=new WorkHistory();
						   workHistory.setWorkId(work.getId());
						   workHistory.setOrgId(work.getOrgId());
						   workHistory.setStaffId(work.getStaffId());
						   workHistory.setWorkType(work.getWorkType());
						   workHistory.setStatus(work.getStatus());
						   workHistory.setStatusBehavior(work.getStatusBehavior());
						   workHistory.setUrgentDegree(work.getUrgentDegree());
						   workHistory.setCloseReason(work.getCloseReason());
						   workHistory.setTitleContent(work.getTitleContent());
						   workHistory.setRelevantType(work.getRelevantType());
						   workHistory.setRelevantCode(work.getRelevantCode());
						   workHistory.setRelevantId(work.getRelevantId());
						   workHistory.setDescribeContent(work.getDescribeContent());
						   workHistory.setCreateBy(work.getCreateBy());
						   workHistory.setCreateDate(work.getCreateDate());
						   workHistory.setDelFlag("0");
						   woRkHistoryService.insertSelective(workHistory);
						   
						   SysStaffInfo sysStaffInfo=sysStaffInfoService.selectByPrimaryKey(staffId);
						   SysStaffInfo sysStaffInfos=sysStaffInfoService.selectByPrimaryKey(work.getStaffId());
						   
						   WorkRecord workRecord=new WorkRecord();
						   workRecord.setWorkHistoryId(workHistory.getId());
						   workRecord.setWorkId(work.getId());
						   workRecord.setOrgId(work.getOrgId());
						   workRecord.setStaffId(work.getStaffId());
						   workRecord.setRecordStatus("1");
						   workRecord.setRecordTitle("由"+sysStaffInfo.getStaffName()+"创建工单并指派给"+sysStaffInfos.getStaffName());
						   workRecord.setCreateBy(staffId);
						   workRecord.setCreateDate(date);
						   workRecord.setDelFlag("0");
						   workRecordService.insertSelective(workRecord);
						   
						   
						   AttachmentHistory attachmentHistory=new AttachmentHistory();
						   attachmentHistory.setWorkHistoryId(workHistory.getId());
						   attachmentHistory.setAttachmentName(attachmentName);
						   attachmentHistory.setAttachmentPath(attachmentPath);
						   attachmentHistory.setCreateBy(staffId);
						   attachmentHistory.setCreateDate(date);
						   attachmentHistory.setDelFlag("0");
						   attachmentHistoryService.insertSelective(attachmentHistory);
						   						   					   
							
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
				
				
				//工单详情界面
				@RequestMapping(value = "/work/viewWork.shtml")
				public ModelAndView viewWork(HttpServletRequest request) {
					String rtPage = "/woRk/viewWork";// 重定向
					Map<String, Object> resMap = new HashMap<String, Object>();
					try {
				
						WoRk woRklist = woRkService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
						resMap.put("woRklist", woRklist);						
						
						//获取指派部门
						SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
						sysOrganizationExample.createCriteria().andStatusEqualTo("A");
						List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
						resMap.put("sysOrganizationlist",sysOrganizationlist);
						
					    if (woRklist.getStaffId()!=null) {//获取指派人
					    	SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
							sysStaffInfoExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(woRklist.getStaffId());
							List<SysStaffInfo> sysStaffInfolist=sysStaffInfoService.selectByExample(sysStaffInfoExample);
							resMap.put("sysStaffInfolist", sysStaffInfolist);
					    	
						}
						
					    AttachmentExample attachmentExample=new AttachmentExample();
					    attachmentExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(woRklist.getId());
					    List<Attachment> attachmentList=attachmentService.selectByExample(attachmentExample);
					    resMap.put("attachmentName", attachmentList.get(0).getAttachmentName());
					    resMap.put("attachmentPath", attachmentList.get(0).getAttachmentPath());
					    
					   /* WorkHistoryExample workHistoryExample=new WorkHistoryExample();
					    workHistoryExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(woRklist.getId());
					    List<WorkHistory> workHistorylist=woRkHistoryService.selectByExample(workHistoryExample);
					    
					    WorkRecordExample workRecordExample=new WorkRecordExample();
					    workRecordExample.createCriteria().andDelFlagEqualTo("0").andWorkHistoryIdEqualTo(workHistorylist.get(0).getId());
					    List<WorkRecord> workRecord=workRecordService.selectByExample(workRecordExample);
					    resMap.put("workRecord", workRecord);*/
					    
					    WorkRecordExample workRecordExample=new WorkRecordExample();
					    workRecordExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(woRklist.getId());
					    List<WorkRecord> workRecord=workRecordService.selectByExample(workRecordExample);
					    resMap.put("workRecord", workRecord);
					    

					} catch (Exception e) {
						e.printStackTrace();
					}
					return new ModelAndView(rtPage, resMap);
				}
				
				//全部工单详情界面
				@RequestMapping(value = "/work/wholeviewWork.shtml")
				public ModelAndView wholeviewWork(HttpServletRequest request) {
					String rtPage = "/woRk/wholeviewWork";// 重定向
					Map<String, Object> resMap = new HashMap<String, Object>();
					try {
				
						WoRk woRklist = woRkService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
						resMap.put("woRklist", woRklist);						
						
						//获取指派部门
						SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
						sysOrganizationExample.createCriteria().andStatusEqualTo("A");
						List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
						resMap.put("sysOrganizationlist",sysOrganizationlist);
						
					    if (woRklist.getStaffId()!=null) {//获取指派人
					    	SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
							sysStaffInfoExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(woRklist.getStaffId());
							List<SysStaffInfo> sysStaffInfolist=sysStaffInfoService.selectByExample(sysStaffInfoExample);
							resMap.put("sysStaffInfolist", sysStaffInfolist);
					    	
						}
						
					    AttachmentExample attachmentExample=new AttachmentExample();
					    attachmentExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(woRklist.getId());
					    List<Attachment> attachmentList=attachmentService.selectByExample(attachmentExample);
					    resMap.put("attachmentName", attachmentList.get(0).getAttachmentName());
					    resMap.put("attachmentPath", attachmentList.get(0).getAttachmentPath());
					    					    
					    WorkRecordExample workRecordExample=new WorkRecordExample();
					    workRecordExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(woRklist.getId());
					    List<WorkRecord> workRecord=workRecordService.selectByExample(workRecordExample);
					    resMap.put("workRecord", workRecord);
					    

					} catch (Exception e) {
						e.printStackTrace();
					}
					return new ModelAndView(rtPage, resMap);
				}
				
				//获取附件
				@RequestMapping(value = "/work/checkFileExists.shtml")
				@ResponseBody
				public Map<String, Object> checkFileExists(HttpServletRequest request) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", "200");
					map.put("msg", "操作成功！");
					String attachmentPath = request.getParameter("attachmentPath");
					if(!StringUtils.isEmpty(attachmentPath)){
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
							map.put("code", "4004");
							map.put("msg", "文件目录不存在");
							return map;
						}
						File file = new File(defaultPath+attachmentPath);
						//如果文件不存在
						if(!file.exists()){
							map.put("code", "4004");
							map.put("msg", "附件不存在或已被删除");
							return map;
						}
					}else{
						map.put("code", "4004");
						map.put("msg", "附件不存在或已被删除");
						return map;
					}
					return map;
				}
				
				//下载附件
				@RequestMapping(value = "/wrok/downLoadAttachment.shtml")
				public void downLoadAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception {
					String filePath = request.getParameter("filePath");
					String fileName = request.getParameter("fileName");
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
			            downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";    
			        }
			        else
			        {
			            downloadFileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
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
				
				
				//添加备注界面
				@RequestMapping(value = "/work/addRemarks.shtml")
				public ModelAndView addRemarks(HttpServletRequest request) {
					String rtPage = "/woRk/addRemarks";// 重定向
					Map<String, Object> resMap = new HashMap<String, Object>();
					try {
					
						WorkHistoryExample workHistoryExample =new WorkHistoryExample();
						workHistoryExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(Integer.valueOf(request.getParameter("id")));
						List<WorkHistory> workHistorylist=woRkHistoryService.selectByExample(workHistoryExample);
										
						WorkHistory workHistory=woRkHistoryService.selectByPrimaryKey(workHistorylist.get(0).getId());
						resMap.put("workHistory", workHistory);
						
						WoRk woRk=woRkService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
					    resMap.put("woRk", woRk);						
												
						WorkRecordExample workRecordExample=new WorkRecordExample();
						workRecordExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(Integer.valueOf(request.getParameter("id")));
						workRecordExample.setOrderByClause("id desc");
						List<WorkRecord> worekrecordList=workRecordService.selectByExample(workRecordExample);
						resMap.put("worekrecordorgid", worekrecordList.get(0).getOrgId());
						resMap.put("worekrecordstaffid", worekrecordList.get(0).getStaffId());
						
						//获取指派部门
						SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
						sysOrganizationExample.createCriteria().andStatusEqualTo("A");
						List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
						resMap.put("sysOrganizationlist",sysOrganizationlist);
						
					    if (workHistorylist.get(0).getStaffId()!=null) {//获取指派人
					    	SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
							sysStaffInfoExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(worekrecordList.get(0).getStaffId());
							List<SysStaffInfo> sysStaffInfolist=sysStaffInfoService.selectByExample(sysStaffInfoExample);
							resMap.put("sysStaffInfolist", sysStaffInfolist);
					    	
						}
										    

					} catch (Exception e) {
						e.printStackTrace();
					}
					return new ModelAndView(rtPage, resMap);
				}
				
				//添加备注
				@ResponseBody
				@RequestMapping("/work/addworkremarks.shtml")
				public ModelAndView addworkremarks(HttpServletRequest request, WorkRecord workRecord, String id,String workid) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = "";
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						Integer staffId = Integer.valueOf(staffBean.getStaffID());
						Date date = new Date();				
							
						 SysStaffInfo sysStaffInfo=sysStaffInfoService.selectByPrimaryKey(staffId);	
						 SysStaffInfo sysStaffInfos=sysStaffInfoService.selectByPrimaryKey(workRecord.getStaffId());
						 
						  workRecord.setWorkHistoryId(Integer.valueOf(id));	
						  workRecord.setWorkId(Integer.valueOf(workid));
						  workRecord.setRecordStatus("5");
						  workRecord.setRecordTitle("由"+sysStaffInfo.getStaffName()+"添加备注并指派给"+sysStaffInfos.getStaffName());
						  workRecord.setCreateBy(staffId);
						  workRecord.setCreateDate(date);
						  workRecord.setDelFlag("0");
						  workRecordService.insertSelective(workRecord); 		
						  
						  WorkHistory workHistory=woRkHistoryService.selectByPrimaryKey(workRecord.getWorkHistoryId());
						  workHistory.setStatus("0");
						  workHistory.setStatusBehavior("3");
						  workHistory.setUpdateBy(staffId);
						  workHistory.setUpdateDate(date);
						  woRkHistoryService.updateByPrimaryKeySelective(workHistory);
						  						  
						  WoRk woRk=woRkService.selectByPrimaryKey(workHistory.getWorkId());
						  woRk.setOrgId(workRecord.getOrgId());
						  woRk.setStaffId(workRecord.getStaffId());
						  woRk.setStatus("0");
						  woRk.setStatusBehavior("3");
						  woRk.setUpdateBy(staffId);
						  woRk.setUpdateDate(date);
						  woRkService.updateByPrimaryKey(woRk);
						  
							
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
				
				
				//添加回复界面
				@RequestMapping(value = "/work/addReply.shtml")
				public ModelAndView addreply(HttpServletRequest request) {
					String rtPage = "/woRk/addReply";// 重定向
					Map<String, Object> resMap = new HashMap<String, Object>();
					try {
					
						WorkHistoryExample workHistoryExample =new WorkHistoryExample();
						workHistoryExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(Integer.valueOf(request.getParameter("id")));
						List<WorkHistory> workHistorylist=woRkHistoryService.selectByExample(workHistoryExample);
										
						WorkHistory workHistory=woRkHistoryService.selectByPrimaryKey(workHistorylist.get(0).getId());
						resMap.put("workHistory", workHistory);
						
						WoRk woRk=woRkService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
						resMap.put("woRk", woRk);
												
						/*WorkRecordExample workRecordExample=new WorkRecordExample();
						workRecordExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(Integer.valueOf(request.getParameter("id")));
						workRecordExample.setOrderByClause("id desc");
						List<WorkRecord> worekrecordList=workRecordService.selectByExample(workRecordExample);*/
						
						SysStaffInfoExample sysStaffInfoExamples=new SysStaffInfoExample();
						sysStaffInfoExamples.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(woRk.getCreateBy());
						List<SysStaffInfo> sysStaffInfoOrgIdlist=sysStaffInfoService.selectByExample(sysStaffInfoExamples);
						
						resMap.put("sysStaffInfoOrgIdlist", sysStaffInfoOrgIdlist.get(0).getOrgId());
						
						resMap.put("worekrecordstaffid", woRk.getCreateBy());
										
						
						//获取指派部门
						SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
						sysOrganizationExample.createCriteria().andStatusEqualTo("A");
						List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
						resMap.put("sysOrganizationlist",sysOrganizationlist);
						
					    if (woRk.getCreateBy()!=null) {//获取指派人
					    	SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
							sysStaffInfoExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(woRk.getCreateBy());
							List<SysStaffInfo> sysStaffInfolist=sysStaffInfoService.selectByExample(sysStaffInfoExample);
							resMap.put("sysStaffInfolist", sysStaffInfolist);
					    	
						}
										    

					} catch (Exception e) {
						e.printStackTrace();
					}
					return new ModelAndView(rtPage, resMap);
				}	
				
				
				//添加回复
				@ResponseBody
				@RequestMapping("/work/addreplys.shtml")
				public ModelAndView addreplys(HttpServletRequest request, WorkRecord workRecord, String id,String workid) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = "";
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						Integer staffId = Integer.valueOf(staffBean.getStaffID());
						Date date = new Date();				
							
						 SysStaffInfo sysStaffInfo=sysStaffInfoService.selectByPrimaryKey(staffId);	
						 SysStaffInfo sysStaffInfos=sysStaffInfoService.selectByPrimaryKey(workRecord.getStaffId());
						 
						  workRecord.setWorkHistoryId(Integer.valueOf(id));	
						  workRecord.setWorkId(Integer.valueOf(workid));
						  workRecord.setRecordStatus("3");
						  workRecord.setRecordTitle("由"+sysStaffInfo.getStaffName()+"回复工单并指派给"+sysStaffInfos.getStaffName());
						  workRecord.setCreateBy(staffId);
						  workRecord.setCreateDate(date);
						  workRecord.setDelFlag("0");
						  workRecordService.insertSelective(workRecord); 		
						  
						  WorkHistory workHistory=woRkHistoryService.selectByPrimaryKey(workRecord.getWorkHistoryId());
						  workHistory.setStatus("1");
						  workHistory.setUpdateBy(staffId);
						  workHistory.setUpdateDate(date);
						  woRkHistoryService.updateByPrimaryKeySelective(workHistory);
						  
						  
						  WoRk woRk=woRkService.selectByPrimaryKey(workHistory.getWorkId());
						  woRk.setOrgId(workRecord.getOrgId());
						  woRk.setStaffId(workRecord.getStaffId());
						  woRk.setStatus("1");
						  woRk.setUpdateBy(staffId);
						  woRk.setUpdateDate(date);
						  woRkService.updateByPrimaryKey(woRk);
						  
							
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
	     
				
				//重新指派界面
				@RequestMapping(value = "/work/changeAssign.shtml")
				public ModelAndView changeAssign(HttpServletRequest request) {
					String rtPage = "/woRk/changeAssign";// 重定向
					Map<String, Object> resMap = new HashMap<String, Object>();
					try {
					
						WorkHistoryExample workHistoryExample =new WorkHistoryExample();
						workHistoryExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(Integer.valueOf(request.getParameter("id")));
						List<WorkHistory> workHistorylist=woRkHistoryService.selectByExample(workHistoryExample);
										
						WorkHistory workHistory=woRkHistoryService.selectByPrimaryKey(workHistorylist.get(0).getId());
						resMap.put("workHistory", workHistory);
						
						WoRk woRk=woRkService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
						resMap.put("woRk", woRk);
						
						WorkRecordExample workRecordExample=new WorkRecordExample();
						workRecordExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(Integer.valueOf(request.getParameter("id")));
						workRecordExample.setOrderByClause("id desc");
						List<WorkRecord> worekrecordList=workRecordService.selectByExample(workRecordExample);
						resMap.put("worekrecordorgid", worekrecordList.get(0).getOrgId());

						//获取指派部门
						SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
						sysOrganizationExample.createCriteria().andStatusEqualTo("A");
						List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
						resMap.put("sysOrganizationlist",sysOrganizationlist);
											
								               												    

					} catch (Exception e) {
						e.printStackTrace();
					}
					return new ModelAndView(rtPage, resMap);
				}
				
				//重新指派
				@ResponseBody
				@RequestMapping("/work/changeassign.shtml")
				public ModelAndView changeassign(HttpServletRequest request, WorkRecord workRecord, String id,String urgentDegree,String workid) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = "";
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						Integer staffId = Integer.valueOf(staffBean.getStaffID());
						Date date = new Date();				
							
						 SysStaffInfo sysStaffInfo=sysStaffInfoService.selectByPrimaryKey(staffId);	
						 SysStaffInfo sysStaffInfos=sysStaffInfoService.selectByPrimaryKey(workRecord.getStaffId());
						 
						  workRecord.setWorkHistoryId(Integer.valueOf(id));
						  workRecord.setWorkId(Integer.valueOf(workid));
						  workRecord.setRecordStatus("6");
						  workRecord.setRecordTitle("由"+sysStaffInfo.getStaffName()+"重新指派"+sysStaffInfos.getStaffName());
						  workRecord.setCreateBy(staffId);
						  workRecord.setCreateDate(date);
						  workRecord.setDelFlag("0");
						  workRecordService.insertSelective(workRecord); 		
						  
						  WorkHistory workHistory=woRkHistoryService.selectByPrimaryKey(workRecord.getWorkHistoryId());
						  
						  WoRk woRk=woRkService.selectByPrimaryKey(workHistory.getWorkId());
						  woRk.setOrgId(workRecord.getOrgId());
						  woRk.setStaffId(workRecord.getStaffId());
						  woRk.setUrgentDegree(urgentDegree);
						  woRk.setUpdateBy(staffId);
						  woRk.setUpdateDate(date);
						  woRkService.updateByPrimaryKey(woRk);
						  
							
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
				
				//关闭工单界面
				@RequestMapping(value = "/work/closeWork.shtml")
				public ModelAndView closeWork(HttpServletRequest request) {
					String rtPage = "/woRk/closeWork";// 重定向
					Map<String, Object> resMap = new HashMap<String, Object>();
					
					WorkHistoryExample workHistoryExample =new WorkHistoryExample();
					workHistoryExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(Integer.valueOf(request.getParameter("id")));
					List<WorkHistory> workHistorylist=woRkHistoryService.selectByExample(workHistoryExample);
									
					WorkHistory workHistory=woRkHistoryService.selectByPrimaryKey(workHistorylist.get(0).getId());
					resMap.put("workHistory", workHistory);
					
					WoRk woRk=woRkService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
					resMap.put("woRk", woRk);
					
					return new ModelAndView(rtPage, resMap);
				}
				
				//关闭工单
				@ResponseBody
				@RequestMapping("/work/clsoework.shtml")
				public ModelAndView clsoework(HttpServletRequest request, WorkRecord workRecord, String id,String closeReason,String recordContent,String workid) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = "";
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						Integer staffId = Integer.valueOf(staffBean.getStaffID());
						Date date = new Date();				
							
						 SysStaffInfo sysStaffInfo=sysStaffInfoService.selectByPrimaryKey(staffId);	
						 
						 WorkHistory workHistory=woRkHistoryService.selectByPrimaryKey(Integer.valueOf(id));
						 
						 workHistory.setCloseReason(closeReason);
						 workHistory.setRemarks(recordContent);
						 workHistory.setStatus("2");
						 workHistory.setUpdateBy(staffId);
						 workHistory.setUpdateDate(date);
						 woRkHistoryService.updateByPrimaryKeySelective(workHistory);
						 
						 WoRk woRk=woRkService.selectByPrimaryKey(workHistory.getWorkId());
						 woRk.setCloseReason(closeReason);
						 woRk.setRemarks(recordContent);
						 woRk.setStatus("2");
						 woRk.setUpdateBy(staffId);
						 woRk.setUpdateDate(date);
						 woRkService.updateByPrimaryKeySelective(woRk);
						  
						  workRecord.setWorkHistoryId(Integer.valueOf(id));	
						  workRecord.setWorkId(Integer.valueOf(workid));
						  workRecord.setOrgId(woRk.getOrgId());
						  workRecord.setStaffId(woRk.getStaffId());
						  workRecord.setRecordStatus("4");
						  if (woRk.getCloseReason().equals("1")) {
							  workRecord.setRecordTitle("由"+sysStaffInfo.getStaffName()+"关闭工单原因已完成");
							
						  }
						  if (woRk.getCloseReason().equals("2")) {
							  workRecord.setRecordTitle("由"+sysStaffInfo.getStaffName()+"关闭工单原因已终止");
							
						  }
						  if (woRk.getCloseReason().equals("3")) {
							  workRecord.setRecordTitle("由"+sysStaffInfo.getStaffName()+"关闭工单原因已作废");
							
						  }
						  workRecord.setCreateBy(staffId);
						  workRecord.setCreateDate(date);
						  workRecord.setDelFlag("0");
						  workRecordService.insertSelective(workRecord); 		
						  					  						  						
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
				
				
				//变更工单界面
				@RequestMapping(value = "/work/changeWork.shtml")
				public ModelAndView changeWork(HttpServletRequest request) {
					String rtPage = "/woRk/changeWork";// 重定向
					Map<String, Object> resMap = new HashMap<String, Object>();
					
					WorkHistoryExample workHistoryExample =new WorkHistoryExample();
					workHistoryExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(Integer.valueOf(request.getParameter("id")));
					List<WorkHistory> workHistorylist=woRkHistoryService.selectByExample(workHistoryExample);
								
					
					WoRk woRk=woRkService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
					resMap.put("woRk", woRk);
					
					AttachmentExample attachmentExample=new AttachmentExample();
					attachmentExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(woRk.getId());
					List<Attachment> attachmentList=attachmentService.selectByExample(attachmentExample);
					resMap.put("attachmentName", attachmentList.get(0).getAttachmentName());
					resMap.put("attachmentPath", attachmentList.get(0).getAttachmentPath());
										
					WorkRecordExample workRecordExample=new WorkRecordExample();
					workRecordExample.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(Integer.valueOf(request.getParameter("id")));
					workRecordExample.setOrderByClause("id desc");
					List<WorkRecord> worekrecordList=workRecordService.selectByExample(workRecordExample);
					resMap.put("worekrecordorgid", worekrecordList.get(0).getOrgId());
					resMap.put("worekrecordstaffid", worekrecordList.get(0).getStaffId());
					

					
					//获取指派部门
					SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
					sysOrganizationExample.createCriteria().andStatusEqualTo("A");
					List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
					resMap.put("sysOrganizationlist",sysOrganizationlist);
					
				    if (workHistorylist.get(0).getStaffId()!=null) {//获取指派人
				    	SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
						sysStaffInfoExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(worekrecordList.get(0).getStaffId());
						List<SysStaffInfo> sysStaffInfolist=sysStaffInfoService.selectByExample(sysStaffInfoExample);
						resMap.put("sysStaffInfolist", sysStaffInfolist);
				    	
					}
					return new ModelAndView(rtPage, resMap);
				}
				
				 //变更工单数据
				@ResponseBody
				@RequestMapping("/work/addchangeWork.shtml")
				public ModelAndView addchangeWork(HttpServletRequest request, WorkHistory workHistory,String attachmentName, String attachmentPath,String workid) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = "";
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						Integer staffId = Integer.valueOf(staffBean.getStaffID());
						Date date = new Date();
		                   
						   workHistory.setWorkId(Integer.valueOf(workid));
						   workHistory.setStatus("0");
						   workHistory.setStatusBehavior("2");
						   workHistory.setCreateBy(staffId);
						   workHistory.setCreateDate(date);
						   workHistory.setDelFlag("0");
						    
						   if (workHistory.getRelevantType().equals("1")) {
							   InterventionOrderCustomExample interventionOrderCustomExample=new InterventionOrderCustomExample();
							   interventionOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andInterventionCodeEqualTo(workHistory.getRelevantCode());
							   List<InterventionOrderCustom> interventionOrderCustom=interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
							   if (interventionOrderCustom!=null && interventionOrderCustom.size()>0) {							
								   workHistory.setRelevantId(interventionOrderCustom.get(0).getId());
							  }else {
								    code = StateCode.JSON_AJAX_ERROR.getStateCode();
							    	msg = "相关单号和类型不符或者无此相关单号！";
									resMap.put(this.JSON_RESULT_CODE, code);
									resMap.put(this.JSON_RESULT_MESSAGE, msg);
									return new ModelAndView(rtPage, resMap);
							 }
							   
						   }		   
						   if (workHistory.getRelevantType().equals("2")) {
							   AppealOrderExample appealOrderExample=new AppealOrderExample();
							   appealOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(workHistory.getRelevantCode());
							   List<AppealOrder> appealOrder=appealOrderService.selectByExample(appealOrderExample);
							   if (appealOrder!=null && appealOrder.size()>0) {
								   workHistory.setRelevantId(appealOrder.get(0).getId());
								
							  }else {
								    code = StateCode.JSON_AJAX_ERROR.getStateCode();
							    	msg = "相关单号和类型不符或者无此相关单号！";
									resMap.put(this.JSON_RESULT_CODE, code);
									resMap.put(this.JSON_RESULT_MESSAGE, msg);
									return new ModelAndView(rtPage, resMap);
							}
						   }
						   if (workHistory.getRelevantType().equals("4")) {
							   SubOrderExample subOrderExample=new SubOrderExample();
							   subOrderExample.createCriteria().andDelFlagEqualTo("0").andSubOrderCodeEqualTo(workHistory.getRelevantCode());
							   List<SubOrder> subOrder=subOrderService.selectByExample(subOrderExample);
							   if (subOrder!=null && subOrder.size()>0) {
								   workHistory.setRelevantId(subOrder.get(0).getId());
								
							 }else {
								    code = StateCode.JSON_AJAX_ERROR.getStateCode();
							    	msg = "相关单号和类型不符或者无此相关单号！";
									resMap.put(this.JSON_RESULT_CODE, code);
									resMap.put(this.JSON_RESULT_MESSAGE, msg);
									return new ModelAndView(rtPage, resMap);
							}
						   }
						   if (workHistory.getRelevantType().equals("5")) {
							   CustomerServiceOrderExample customerServiceOrderExample=new CustomerServiceOrderExample();
							   customerServiceOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(workHistory.getRelevantCode());
							   List<CustomerServiceOrder> customerServiceOrder=customerServiceOrderService.selectByExample(customerServiceOrderExample);
							   if (customerServiceOrder!=null && customerServiceOrder.size()>0) {
								   workHistory.setRelevantId(customerServiceOrder.get(0).getId());
								
							}else {
								code = StateCode.JSON_AJAX_ERROR.getStateCode();
						    	msg = "相关单号和类型不符或者无此相关单号！";
								resMap.put(this.JSON_RESULT_CODE, code);
								resMap.put(this.JSON_RESULT_MESSAGE, msg);
								return new ModelAndView(rtPage, resMap);
								
							 }
						   }
						   
						   woRkHistoryService.insertSelective(workHistory);
						   
						   WoRk woRk=woRkService.selectByPrimaryKey(Integer.valueOf(workid));
						   woRk.setOrgId(workHistory.getOrgId());
						   woRk.setStaffId(workHistory.getStaffId());
						   woRk.setWorkType(workHistory.getWorkType());
						   woRk.setStatus("0");
						   woRk.setStatusBehavior("2");
						   woRk.setUrgentDegree(workHistory.getUrgentDegree());
						   woRk.setCloseReason(workHistory.getCloseReason());
						   woRk.setTitleContent(workHistory.getTitleContent());
						   woRk.setRelevantType(workHistory.getRelevantType());
						   woRk.setRelevantCode(workHistory.getRelevantCode());
						   woRk.setRelevantId(workHistory.getRelevantId());
						   woRk.setDescribeContent(workHistory.getDescribeContent());
						   woRk.setUpdateBy(staffId);
						   woRk.setUpdateDate(date);
						   woRkService.updateByPrimaryKeySelective(woRk);
						   
						   
						   AttachmentExample attachment=new AttachmentExample();
						   attachment.createCriteria().andDelFlagEqualTo("0").andWorkIdEqualTo(Integer.valueOf(workid));
						   
						   Attachment attachment2=new Attachment();
						   attachment2.setAttachmentName(attachmentName);
						   attachment2.setAttachmentPath(attachmentPath);
						   attachment2.setCreateBy(staffId);
						   attachment2.setCreateDate(date);
						   attachmentService.updateByExampleSelective(attachment2, attachment);
						   
						   
						   SysStaffInfo sysStaffInfo=sysStaffInfoService.selectByPrimaryKey(staffId);
						   SysStaffInfo sysStaffInfos=sysStaffInfoService.selectByPrimaryKey(workHistory.getStaffId());
						   
						   WorkRecord workRecord=new WorkRecord();
						   workRecord.setWorkHistoryId(workHistory.getId());
						   workRecord.setWorkId(Integer.valueOf(workid));
						   workRecord.setOrgId(workHistory.getOrgId());
						   workRecord.setStaffId(workHistory.getStaffId());
						   workRecord.setRecordStatus("2");
						   workRecord.setRecordTitle("由"+sysStaffInfo.getStaffName()+"变更工单并指派给"+sysStaffInfos.getStaffName());
						   workRecord.setCreateBy(staffId);
						   workRecord.setCreateDate(date);
						   workRecord.setDelFlag("0");
						   workRecordService.insertSelective(workRecord);
						   
						   
						   AttachmentHistory attachmentHistory=new AttachmentHistory();
						   attachmentHistory.setWorkHistoryId(workHistory.getId());
						   attachmentHistory.setAttachmentName(attachmentName);
						   attachmentHistory.setAttachmentPath(attachmentPath);
						   attachmentHistory.setCreateBy(staffId);
						   attachmentHistory.setCreateDate(date);
						   attachmentHistory.setDelFlag("0");
						   attachmentHistoryService.insertSelective(attachmentHistory);
						   						   					   

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
				
				
				//查看工单记录详情
				@RequestMapping(value = "/work/workhistoryRecord.shtml")
				public ModelAndView workhistoryRecord(HttpServletRequest request) {
					String rtPage = "/woRk/seeWork";// 重定向
					Map<String, Object> resMap = new HashMap<String, Object>();
															
					WorkRecordExample workRecordExample=new WorkRecordExample();
					workRecordExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(request.getParameter("id")));
					List<WorkRecord> worekrecordList=workRecordService.selectByExample(workRecordExample);
					

					 WorkHistory workHistorylist=woRkHistoryService.selectByPrimaryKey(worekrecordList.get(0).getWorkHistoryId());
					 resMap.put("workHistorylist", workHistorylist);
					
					AttachmentHistoryExample attachmentHistoryExample=new AttachmentHistoryExample();
					attachmentHistoryExample.createCriteria().andDelFlagEqualTo("0").andWorkHistoryIdEqualTo(workHistorylist.getId());
				    List<AttachmentHistory> attachmentHistorylist=attachmentHistoryService.selectByExample(attachmentHistoryExample);
				    resMap.put("attachmentName", attachmentHistorylist.get(0).getAttachmentName());
				    resMap.put("attachmentPath", attachmentHistorylist.get(0).getAttachmentPath());
				    
				    
				    //获取指派部门
					SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
					sysOrganizationExample.createCriteria().andStatusEqualTo("A");
					List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
					resMap.put("sysOrganizationlist",sysOrganizationlist);
					
				    if (workHistorylist.getStaffId()!=null) {//获取指派人
				    	SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
						sysStaffInfoExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(workHistorylist.getStaffId());
						List<SysStaffInfo> sysStaffInfolist=sysStaffInfoService.selectByExample(sysStaffInfoExample);
						resMap.put("sysStaffInfolist", sysStaffInfolist);
				    	
					}
                    
					return new ModelAndView(rtPage, resMap);
				}
				
				
				//全部工单
				@RequestMapping("/work/wholeWorkList.shtml")
				public ModelAndView wholeWorkList(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/woRk/wholeWorkList");			
					m.addObject("staffidList",woRkService.getstaffidList());
					m.addObject("staffidListc",woRkService.getstaffidListc());	
					
					StaffBean staffBean = this.getSessionStaffBean(request);
					Integer staffId = Integer.valueOf(staffBean.getStaffID());
					m.addObject("staffId",staffId);
					return m;
				}
								
				//全部工单数据列表
				@ResponseBody
				@RequestMapping("/work/wholeWorkdata.shtml")
				public Map<String, Object> wholeWorkdata(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					List<WoRkCustom> dataList = null;
					Integer totalCount = 0;
					try {
						
						WoRkCustomExample woRkCustomExample = new WoRkCustomExample();
						WoRkCustomExample.WoRkCustomCriteria woRkCustomCriteria = woRkCustomExample.createCriteria();
						woRkCustomCriteria.andDelFlagEqualTo("0");
						woRkCustomExample.setOrderByClause("w.create_date desc");
						
						if (!StringUtil.isEmpty(request.getParameter("workType"))) {
							String workType=request.getParameter("workType");
							if (workType.equals("1")) {
								woRkCustomCriteria.andWorkTypeEqualTo("1");
							}
							if (workType.equals("2")) {
								woRkCustomCriteria.andWorkTypeEqualTo("2");
							}
							if (workType.equals("3")) {
								woRkCustomCriteria.andWorkTypeEqualTo("3");
							}
							if (workType.equals("4")) {
								woRkCustomCriteria.andWorkTypeEqualTo("4");
							}
							if (workType.equals("5")) {
								woRkCustomCriteria.andWorkTypeEqualTo("5");
							}
							if (workType.equals("6")) {
								woRkCustomCriteria.andWorkTypeEqualTo("6");
							}
							if (workType.equals("7")) {
								woRkCustomCriteria.andWorkTypeEqualTo("7");
							}
							
						}
						if (!StringUtil.isEmpty(request.getParameter("status"))) {
							String status=request.getParameter("status");
							if (status.equals("0")) {
								woRkCustomCriteria.andStatusEqualTo("0");
								woRkCustomCriteria.andStatusBehaviorBetween("1", "3");
								
							}
							if (status.equals("1")) {
								woRkCustomCriteria.andStatusEqualTo("1");
								
							}
							if (status.equals("2")) {
								woRkCustomCriteria.andStatusEqualTo("2");
								
							}
							
						}
						if (!StringUtil.isEmpty(request.getParameter("titleContent"))) {
							woRkCustomCriteria.andTitleContentLike("%"+request.getParameter("titleContent")+"%");
							
						}
						if (!StringUtil.isEmpty(request.getParameter("id"))) {
							woRkCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
						}
						if (!StringUtil.isEmpty(request.getParameter("staffId"))) {
							woRkCustomCriteria.andStaffIdEqualTo(Integer.valueOf(request.getParameter("staffId")));
						}
						if (!StringUtil.isEmpty(request.getParameter("createBy"))) {
							woRkCustomCriteria.andCreateByEqualTo(Integer.valueOf(request.getParameter("createBy")));
						}
						if (!StringUtil.isEmpty(request.getParameter("startCreateDate")) && !StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
							woRkCustomCriteria.andCreateDateBetween(sdf.parse(request.getParameter("startCreateDate")+" 00:00:00"), sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));				
						}
						if (!StringUtil.isEmpty(request.getParameter("relevantCode"))) {
							woRkCustomCriteria.andRelevantCodeEqualTo(request.getParameter("relevantCode"));
						}
						woRkCustomExample.setLimitStart(page.getLimitStart());
						woRkCustomExample.setLimitSize(page.getLimitSize());
						totalCount = woRkService.countByCustomExample(woRkCustomExample);
						dataList = woRkService.selectByCustomExample(woRkCustomExample);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				//我处理的工单
				@RequestMapping("/work/myhandleWork.shtml")
				public ModelAndView myhandleWork(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/woRk/myhandleWork");			
					m.addObject("staffidListc",woRkService.getstaffidListc());	
					
					StaffBean staffBean = this.getSessionStaffBean(request);
					Integer staffId = Integer.valueOf(staffBean.getStaffID());
					m.addObject("staffId",staffId);

					
					WoRkCustomExample woRkCustomExample = new WoRkCustomExample();
					WoRkCustomExample.WoRkCustomCriteria woRkCustomCriteria = woRkCustomExample.createCriteria();
					woRkCustomCriteria.andDelFlagEqualTo("0");
					woRkCustomCriteria.andstaffIdEqualTo(staffId);
					List<WoRkCustom> woRkCustom=woRkService.selectByCustomExample(woRkCustomExample);	
					if (woRkCustom!=null && woRkCustom.size()>0) {
						
						List<Integer> woIntegers=new ArrayList<Integer>();
						for (WoRkCustom woRkCustom2 : woRkCustom) {
							woIntegers.add(woRkCustom2.getStaffId());
						}						
						SysStaffInfoExample staffInfoExample=new SysStaffInfoExample();
						staffInfoExample.createCriteria().andStaffIdIn(woIntegers);
						List<SysStaffInfo> sysStaffInfos=sysStaffInfoService.selectByExample(staffInfoExample);
						m.addObject("sysStaffInfos",sysStaffInfos);								
						
					}
															
					return m;
				}
				
				
				//我处理的工单数据列表
				@ResponseBody
				@RequestMapping("/work/myhandleWorkdata.shtml")
				public Map<String, Object> myhandleWorkdata(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					List<WoRkCustom> dataList = null;
					Integer totalCount = 0;
					try {

						WoRkCustomExample woRkCustomExample = new WoRkCustomExample();
						WoRkCustomExample.WoRkCustomCriteria woRkCustomCriteria = woRkCustomExample.createCriteria();
						woRkCustomCriteria.andDelFlagEqualTo("0");
						woRkCustomExample.setOrderByClause("w.create_date desc");
						
						if (!StringUtil.isEmpty(request.getParameter("workType"))) {
							String workType=request.getParameter("workType");
							if (workType.equals("1")) {
								woRkCustomCriteria.andWorkTypeEqualTo("1");
							}
							if (workType.equals("2")) {
								woRkCustomCriteria.andWorkTypeEqualTo("2");
							}
							if (workType.equals("3")) {
								woRkCustomCriteria.andWorkTypeEqualTo("3");
							}
							if (workType.equals("4")) {
								woRkCustomCriteria.andWorkTypeEqualTo("4");
							}
							if (workType.equals("5")) {
								woRkCustomCriteria.andWorkTypeEqualTo("5");
							}
							if (workType.equals("6")) {
								woRkCustomCriteria.andWorkTypeEqualTo("6");
							}
							if (workType.equals("7")) {
								woRkCustomCriteria.andWorkTypeEqualTo("6");
							}
							
						}
						if (!StringUtil.isEmpty(request.getParameter("status"))) {
							String status=request.getParameter("status");
							if (status.equals("0")) {
								woRkCustomCriteria.andStatusEqualTo("0");
								woRkCustomCriteria.andStatusBehaviorBetween("1", "3");
								
							}
							if (status.equals("1")) {
								woRkCustomCriteria.andStatusEqualTo("1");
								
							}
							if (status.equals("2")) {
								woRkCustomCriteria.andStatusEqualTo("2");
								
							}
							
						}
						if (!StringUtil.isEmpty(request.getParameter("titleContent"))) {
							woRkCustomCriteria.andTitleContentLike("%"+request.getParameter("titleContent")+"%");
							
						}
						if (!StringUtil.isEmpty(request.getParameter("id"))) {
							woRkCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
						}
						if (!StringUtil.isEmpty(request.getParameter("staffId"))) {
							woRkCustomCriteria.andstaffIdEqualTo(Integer.valueOf(request.getParameter("staffId")));
							
						}
						if (!StringUtil.isEmpty(request.getParameter("createBy"))) {
							woRkCustomCriteria.andCreateByEqualTo(Integer.valueOf(request.getParameter("createBy")));
						}
						if (!StringUtil.isEmpty(request.getParameter("startCreateDate")) && !StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
							woRkCustomCriteria.andCreateDateBetween(sdf.parse(request.getParameter("startCreateDate")+" 00:00:00"), sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));				
						}
						woRkCustomExample.setLimitStart(page.getLimitStart());
						woRkCustomExample.setLimitSize(page.getLimitSize());
						totalCount = woRkService.countByCustomExample(woRkCustomExample);
						dataList = woRkService.selectByCustomExample(woRkCustomExample);
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
	 * @Title exportCouponExchangeCodeList
	 * @Description TODO(导出excel)
	 * @author pengl
	 * @date 2018年2月9日 下午6:36:13
	 */
	@RequestMapping("/work/exportAllWorkOrders.shtml")
	public void exportAllWorkOrders(HttpServletRequest request, HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String startCreateDate = request.getParameter("startCreateDate");//创建日期
			String endCreateDate = request.getParameter("endCreateDate");
			String workType = request.getParameter("workType");//工单类型
			String status = request.getParameter("status");//问题状态
			String titleContent = request.getParameter("titleContent");//标题
			String id = request.getParameter("id");//工单编号
			String createBy = request.getParameter("createBy");//工单创建者
			String staffId = request.getParameter("staffId");//当前指派

			WoRkCustomExample woRkCustomExample = new WoRkCustomExample();
			WoRkCustomExample.WoRkCustomCriteria woRkCustomCriteria = woRkCustomExample.createCriteria();
			woRkCustomCriteria.andDelFlagEqualTo("0");
			woRkCustomExample.setOrderByClause("w.create_date desc");

			if (!StringUtil.isEmpty(workType)) {
				if (workType.equals("1")) {
					woRkCustomCriteria.andWorkTypeEqualTo("1");
				}
				if (workType.equals("2")) {
					woRkCustomCriteria.andWorkTypeEqualTo("2");
				}
				if (workType.equals("3")) {
					woRkCustomCriteria.andWorkTypeEqualTo("3");
				}
				if (workType.equals("4")) {
					woRkCustomCriteria.andWorkTypeEqualTo("4");
				}
				if (workType.equals("5")) {
					woRkCustomCriteria.andWorkTypeEqualTo("5");
				}
				if (workType.equals("6")) {
					woRkCustomCriteria.andWorkTypeEqualTo("6");
				}
				if (workType.equals("7")) {
					woRkCustomCriteria.andWorkTypeEqualTo("7");
				}

			}
			if (!StringUtil.isEmpty(status)) {
				if (status.equals("0")) {
					woRkCustomCriteria.andStatusEqualTo("0");
					woRkCustomCriteria.andStatusBehaviorBetween("1", "3");

				}
				if (status.equals("1")) {
					woRkCustomCriteria.andStatusEqualTo("1");

				}
				if (status.equals("2")) {
					woRkCustomCriteria.andStatusEqualTo("2");

				}

			}
			if (!StringUtil.isEmpty(titleContent)) {
				woRkCustomCriteria.andTitleContentLike("%"+titleContent+"%");
			}
			if (!StringUtil.isEmpty(id)) {
				woRkCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			if (!StringUtil.isEmpty(staffId)) {
				woRkCustomCriteria.andStaffIdEqualTo(Integer.valueOf(request.getParameter("staffId")));
			}
			if (!StringUtil.isEmpty(createBy)) {
				woRkCustomCriteria.andCreateByEqualTo(Integer.valueOf(request.getParameter("createBy")));
			}
			if (!StringUtil.isEmpty(startCreateDate) && !StringUtil.isEmpty(endCreateDate)) {
				woRkCustomCriteria.andCreateDateBetween(sdf.parse(startCreateDate+" 00:00:00"), sdf.parse(endCreateDate+" 23:59:59"));
			}

			List<WoRkCustom> woRkCustoms = woRkService.selectByCustomExample(woRkCustomExample);


			String[] titles = {"工单编号","相关单号","工单标题","工单具体描述","商家序号","店铺名称"};
			ExcelBean excelBean = new ExcelBean("导出全部工单.xls","导出全部工单", titles);
			List<String[]> datas = new ArrayList<String[]>();

			for (WoRkCustom woRkCustom : woRkCustoms) {
				String mchtCode="";//商家序号
				String mchtName="";//店铺名称
				String describeContent="";//去除标签的具体描述

				String describeContentHtml = woRkCustom.getDescribeContent();//获取数据库内容
				Document doc = Jsoup.parse(describeContentHtml);
				String text = doc.text();//将特殊符号转成标签
				Document parse = Jsoup.parse(text);
				describeContent = parse.text();//提取纯文本

				String relevantType = woRkCustom.getRelevantType();//相关单类型
				Integer relevantId = woRkCustom.getRelevantId();//相关单号ID
				if("1".equals(relevantType) && relevantId!=null){//介入单
					InterventionOrder interventionOrder = interventionOrderService.selectByPrimaryKey(relevantId);
					MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(interventionOrder.getMchtId());
					if(mchtInfo==null){
						continue;
					}
					mchtCode  = mchtInfo.getMchtCode();
					mchtName = mchtInfo.getShopName();
				}else if("2".equals(relevantType) && relevantId!=null ){//投诉单
					AppealOrder appealOrder = appealOrderService.selectByPrimaryKey(relevantId);
					MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(appealOrder.getMchtId());
					if(mchtInfo==null){
						continue;
					}
					mchtCode  = mchtInfo.getMchtCode();
					mchtName = mchtInfo.getShopName();

				}else if("4".equals(relevantType) && relevantId!=null ){//子订单
					SubOrder subOrder = subOrderService.selectByPrimaryKey(relevantId);
					MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(subOrder.getMchtId());
					if(mchtInfo==null){
						continue;
					}
					mchtCode  = mchtInfo.getMchtCode();
					mchtName = mchtInfo.getShopName();
				}
				else if("5".equals(relevantType ) && relevantId!=null){//售后单
					CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(relevantId);
					SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
					MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(subOrder.getMchtId());
					if(mchtInfo==null){
						continue;
					}
					mchtCode  = mchtInfo.getMchtCode();
					mchtName = mchtInfo.getShopName();
				}

				String[] data = {
						woRkCustom.getId()+" ",
						woRkCustom.getRelevantCode()+" ",
						woRkCustom.getTitleContent()+" ",
						describeContent+" ",
						mchtCode,
						mchtName,
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

}
