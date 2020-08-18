package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.*;
import com.jf.entity.ApprovalApplicationCustomExample.ApprovalApplicationCustomCriteria;
import com.jf.entity.ApprovalApplicationLogExample.Criteria;
import com.jf.entity.SysStaffInfoCustomExample.SysStaffInfoCustomCriteria;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class ApprovalProcessManagementController extends BaseController{
	
	@Resource
	public ProcedureService procedureService;
	
	@Resource
	public SysStaffInfoService sysStaffInfoService;
	
	@Resource
	public ProcedureNodeService procedureNodeService;
	
	@Resource
	public ApprovalApplicationService approvalApplicationService;
	
	@Resource
	private ApprovalApplicationPicService approvalApplicationPicService;
	
	@Resource
	private NodeApproverService nodeApproverService;
	
	@Resource
	private ApprovalApplicationNodeService approvalApplicationNodeService;
	
	@Resource
	private ApprovalApplicationLogService approvalApplicationLogService;

	@Resource
	private InformationService informationService;
	
	
	//添加流程
	@RequestMapping("/approvalProcessManagement/approvalProcess.shtml")
	public ModelAndView approvalProcess(HttpServletRequest request) {
		String rtPage = "/approvalProcessManagement/approvalProcessList";
		return new ModelAndView(rtPage);
	}
	
	
	//添加流程的列表数据
	@RequestMapping("/approvalProcessManagement/approvalProcessListData.shtml")
	@ResponseBody
	public Map<String, Object> approvalProcessListData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProcedureExample procedureExample = new ProcedureExample();
		procedureExample.setOrderByClause("create_date desc");
		procedureExample.createCriteria().andDelFlagEqualTo("0");
		
		procedureExample.setLimitSize(page.getLimitSize());
		procedureExample.setLimitStart(page.getLimitStart());
		
		int totalCount = procedureService.countCustomByExample(procedureExample);
		List<ProcedureCustom> procedureList = procedureService.selectCustomsByExample(procedureExample);
		resMap.put("Rows", procedureList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	 
	//添加和修改  添加流程
	@RequestMapping("/approvalProcessManagement/addProcedure.shtml")
	public ModelAndView addProcedure(HttpServletRequest request) {
		String rtPage = "/approvalProcessManagement/addProcedure";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty(id)){
			resMap.put("id", id);
			ProcedureCustom procedureCustom = procedureService.selectCustomByPrimaryKey(Integer.parseInt(id));
			resMap.put("procedureCustom", procedureCustom);
			 String copiersIds = procedureCustom.getCopiers();//抄送人id
			 resMap.put("copiersIds", copiersIds);
			 String[] copiersArr = copiersIds.split(",");
			List<Integer> asList = new ArrayList<>();//抄送人StaffIds
			resMap.put("asList", asList);
			for (int i = 0; i < copiersArr.length; i++) {
				asList.add(Integer.parseInt(copiersArr[i]));
			}
			 SysStaffInfoCustomExample example = new SysStaffInfoCustomExample();
			 SysStaffInfoCustomCriteria createCriteria = example.createCriteria();
			 createCriteria.andStaffIdIn(asList);
			 createCriteria.andStatusEqualTo("A");
			List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(example);
			resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		}
		return new ModelAndView(rtPage,resMap);
	}
	

	// 获取平台系统用户
	@RequestMapping(value = "/approvalProcessManagement/getSysStaffInfoList.shtml")
	@ResponseBody
	public Map<String, Object> getplatformcontact(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		
		// 对接人数据
		SysStaffInfoCustomExample sysStaffInfoExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomCriteria createCriteria = sysStaffInfoExample.createCriteria();
		createCriteria.andStatusEqualTo("A");
		
		Object jsonvalue = request.getParameter("condition");
		if (jsonvalue != null) {
			JSONArray jsonArray = JSONArray.fromObject(jsonvalue);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String searchvalue = jsonObject.getString("value");
				String searchitem = jsonObject.getString("field");
				if (!StringUtil.isEmpty(searchvalue)) {
					if (searchitem.equals("staffName")) {
						createCriteria.andStaffNameLike("%" + searchvalue+ "%");
					}
				}
			}
		}
		
		sysStaffInfoExample.setLimitSize(page.getLimitSize());
		sysStaffInfoExample.setLimitStart(page.getLimitStart());
		
		totalCount = sysStaffInfoService.countByCustomExample(sysStaffInfoExample);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoExample);
		
		resMap.put("Rows", sysStaffInfoCustomList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	

	//保存我的流程
	@RequestMapping(value = "/approvalProcessManagement/saveProcedure.shtml")
	@ResponseBody
	public Map<String, Object> saveProcedure(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		String code = null;
 		String msg =null;
		try{
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String name = request.getParameter("name");
			procedureService.saveProcedure(request,staffID);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
	
		return resMap ;
	}
	
	
	//启用流程
	@RequestMapping(value = "/approvalProcessManagement/enableProcedure.shtml")
	@ResponseBody
	public Map<String, Object> enableProcedure(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			Procedure procedure = procedureService.selectByPrimaryKey(Integer.parseInt(id));
			procedure.setStatus("1");
			procedureService.updateByPrimaryKeySelective(procedure);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	//删除流程
	@RequestMapping(value = "/approvalProcessManagement/delProcedure.shtml")
	@ResponseBody
	public Map<String, Object> delProcedure(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			Procedure procedure = procedureService.selectByPrimaryKey(Integer.parseInt(id));
			procedure.setDelFlag("1");
			procedureService.updateByPrimaryKeySelective(procedure);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	
	//禁用流程
	@RequestMapping(value = "/approvalProcessManagement/prohibitProcedure.shtml")
	@ResponseBody
	public Map<String, Object> prohibitProcedure(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			Procedure procedure = procedureService.selectByPrimaryKey(Integer.parseInt(id));
			procedure.setStatus("0");
			procedureService.updateByPrimaryKeySelective(procedure);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	
	
	
	//添加流程节点
	@RequestMapping("/approvalProcessManagement/addProcedureNode.shtml")
	public ModelAndView addProcedureNode(HttpServletRequest request) {
		String rtPage = "/approvalProcessManagement/addProcedureNode";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String procedureId = request.getParameter("procedureId");
		resMap.put("procedureId", procedureId);
		if(!StringUtil.isEmpty(procedureId)){//流程主键id
			ProcedureCustom procedureCustom = procedureService.selectCustomByPrimaryKey(Integer.parseInt(procedureId));
			resMap.put("procedureCustom", procedureCustom);
			
			 String copiersIds = procedureCustom.getCopiers();//抄送人id
			 resMap.put("copiersIds", copiersIds);
			 String[] copiersArr = copiersIds.split(",");
			List<Integer> asList = new ArrayList<>();//抄送人StaffIds
			resMap.put("asList", asList);
			for (int i = 0; i < copiersArr.length; i++) {
				asList.add(Integer.parseInt(copiersArr[i]));
			}
/*			 SysStaffInfoCustomExample example = new SysStaffInfoCustomExample();
			 SysStaffInfoCustomCriteria createCriteria = example.createCriteria();
			 createCriteria.andStaffIdIn(asList);
			 createCriteria.andStatusEqualTo("A");
			List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(example);//抄送人集合
			resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);*/
			List<SysStaffInfoCustom> sysStaffInfoCustomList = getStaffNameList(asList);
			resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
			
			//一个流程对应的所有节点
			ProcedureNodeExample procedureNodeExample = new ProcedureNodeExample();
			procedureNodeExample.setOrderByClause("seq_no");
			procedureNodeExample.createCriteria().andProcedureIdEqualTo(Integer.parseInt(procedureId)).andDelFlagEqualTo("0");
			List<ProcedureNodeCustom> procedureNodeList = procedureNodeService.selectCustomsByExample(procedureNodeExample);
			if(procedureNodeList!=null && procedureNodeList.size()>0){
				for(ProcedureNodeCustom pn : procedureNodeList){
					if(!"0".equals(pn.getApproverType())){
					String approvers = pn.getApprovers();
					String[] split = approvers.split(",");
					List<Integer> approversIdList = new ArrayList<>();//审批人StaffIds
					List<String> approversNameList = new ArrayList<>();//审批人名字集合 
					
					for (int i = 0; i < split.length; i++) {
						approversIdList.add(Integer.parseInt(split[i]));
					}
					
					List<SysStaffInfoCustom> approversList = getStaffNameList(approversIdList);
					for(SysStaffInfoCustom sic : approversList){
						approversNameList.add(sic.getStaffName());//将审批人名字放入实体集合
					}
					pn.setStaffNameList(approversNameList);
					}
				}
			}
			resMap.put("procedureNodeList", procedureNodeList);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	
	//获取系统人员集合
	public List<SysStaffInfoCustom> getStaffNameList(List<Integer> asList){
		 SysStaffInfoCustomExample example = new SysStaffInfoCustomExample();
		 SysStaffInfoCustomCriteria createCriteria = example.createCriteria();
		 createCriteria.andStaffIdIn(asList);
		 createCriteria.andStatusEqualTo("A");
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(example);
		return sysStaffInfoCustomList;
	}
	
	
	
	//添加节点
	@RequestMapping("/approvalProcessManagement/addNode.shtml")
	public ModelAndView addNode(HttpServletRequest request) {
		String rtPage = "/approvalProcessManagement/addNode";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String procedureId = request.getParameter("procedureId");
		String seq = request.getParameter("seq");
		if(!StringUtil.isEmpty(seq)){
			resMap.put("seq", seq);
		}
		if(!StringUtil.isEmpty(procedureId)){//流程主键id
			resMap.put("procedureId", procedureId);
			Procedure procedure = procedureService.selectByPrimaryKey(Integer.parseInt(procedureId));
			resMap.put("procedure", procedure);
		}
		String procedureNodeId = request.getParameter("procedureNodeId");//节点ID
		if(!StringUtil.isEmpty(procedureNodeId)){
			ProcedureNode procedureNode = procedureNodeService.selectByPrimaryKey(Integer.parseInt(procedureNodeId));
			resMap.put("procedureNode", procedureNode);
			resMap.put("approverType", procedureNode.getApproverType());
			if(!"0".equals(procedureNode.getApproverType())){
			String approvers = procedureNode.getApprovers();//审批人IDs
			 resMap.put("approvers", approvers);
			 String[] approversArr = approvers.split(",");
			List<Integer> asList = new ArrayList<>();//抄送人StaffIds
			resMap.put("asList", asList);
			for (int i = 0; i < approversArr.length; i++) {
				asList.add(Integer.parseInt(approversArr[i]));
			}
			
			List<SysStaffInfoCustom> sysStaffInfoCustomList = getStaffNameList(asList);//抄送人集合
			resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
			}
/*			 SysStaffInfoCustomExample example = new SysStaffInfoCustomExample();
			 SysStaffInfoCustomCriteria createCriteria = example.createCriteria();
			 createCriteria.andStaffIdIn(asList);
			 createCriteria.andStatusEqualTo("A");
			List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(example);*/
		}else { 
			resMap.put("approverType", 0);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
		//保存我的流程节点
		@RequestMapping(value = "/approvalProcessManagement/saveAddNode.shtml")
		@ResponseBody
		public Map<String, Object> saveAddNode(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			String code = null;
	 		String msg =null;
			try{
				String staffID = this.getSessionStaffBean(request).getStaffID();//创建人
				String name = request.getParameter("name");//节点名称
				String type = request.getParameter("type");//节点类型
				String approverType = request.getParameter("approverType");//审批人类型
				String procedureId = request.getParameter("procedureId");//流程id
				String seq = request.getParameter("seq");//节点顺序
				String staffIds = request.getParameter("staffIds");//审核人IDs
				String staIdsString="";
				if(!StringUtil.isEmpty(staffIds)){
					JSONArray staffIdsList = JSONArray.fromObject(staffIds);				
					List<String> staffList = (List<String>) JSONArray.toCollection(staffIdsList, String.class);
					for (int i = 0; i < staffList.size(); i++) {
						if(i==0){
							staIdsString+=staffList.get(i);
						}else{
							staIdsString+=","+staffList.get(i);
						}
					}
				}
				String procedureNodeId = request.getParameter("procedureNodeId");//节点 ID
				if(!StringUtil.isEmpty(procedureNodeId)){//修改节点
					ProcedureNode procedureNode = procedureNodeService.selectByPrimaryKey(Integer.parseInt(procedureNodeId));
					procedureNode.setName(name);
					procedureNode.setType(type);
					procedureNode.setApproverType(approverType);
					procedureNode.setApprovers(staIdsString);
					procedureNode.setUpdateDate(new Date());
					procedureNode.setUpdateBy(Integer.parseInt(staffID));
					procedureNodeService.updateByPrimaryKeySelective(procedureNode);
				}else{//新添加节点
					ProcedureNode procedureNode = new ProcedureNode();
					procedureNode.setName(name);
					procedureNode.setType(type);
					procedureNode.setApproverType(approverType);
					procedureNode.setProcedureId(Integer.parseInt(procedureId));
					procedureNode.setApprovers(staIdsString);
					procedureNode.setSeqNo(Integer.parseInt(seq));//节点顺序
					procedureNode.setCreateDate(new Date());
					procedureNode.setCreateBy(Integer.parseInt(staffID));
					procedureNodeService.insertSelective(procedureNode);
					
				}
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "保存失败，请稍后重试");
				return resMap;
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
		
			return resMap ;
		}
	
		/**
		 * 上移节点
		 * @param request
		 * @param response
		 * @param paramMap
		 * @return
		 */
		@RequestMapping(value = "/approvalProcessManagement/moveUp.shtml")
		@ResponseBody
		public Map<String, Object> moveUp(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			try {
				String prevId = request.getParameter("prevId");//上一个节点
				String procedureNodeId = request.getParameter("procedureNodeId");//选中节点
				if(!StringUtil.isEmpty(prevId) && !StringUtil.isEmpty(procedureNodeId)){
					ProcedureNode prevProcedureNode = procedureNodeService.selectByPrimaryKey(Integer.parseInt(prevId));
					ProcedureNode procedureNode = procedureNodeService.selectByPrimaryKey(Integer.parseInt(procedureNodeId));
					
					if(prevProcedureNode.getSeqNo()!=null && procedureNode.getSeqNo()!=null){
						int prevSeqNo = prevProcedureNode.getSeqNo();
						int SeqNo = procedureNode.getSeqNo();
						prevProcedureNode.setSeqNo(SeqNo);
						procedureNode.setSeqNo(prevSeqNo);
					}
					procedureNodeService.updateByPrimaryKeySelective(prevProcedureNode);
					procedureNodeService.updateByPrimaryKeySelective(procedureNode);
				}
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "操作失败，请稍后重试");
				return resMap;
			}
			return resMap;
		}
		
		
		
		/**
		 * 下移节点
		 * @param request
		 * @param response
		 * @param paramMap
		 * @return
		 */
		@RequestMapping(value = "/approvalProcessManagement/moveDown.shtml")
		@ResponseBody
		public Map<String, Object> moveDown(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			try {
				String nextId = request.getParameter("nextId");//上一个节点
				String procedureNodeId = request.getParameter("procedureNodeId");//选中节点
				if(!StringUtil.isEmpty(nextId) && !StringUtil.isEmpty(procedureNodeId)){
					ProcedureNode nextProcedureNode = procedureNodeService.selectByPrimaryKey(Integer.parseInt(nextId));
					ProcedureNode procedureNode = procedureNodeService.selectByPrimaryKey(Integer.parseInt(procedureNodeId));
					
					if(nextProcedureNode.getSeqNo()!=null && procedureNode.getSeqNo()!=null){
						int nextSeqNo = nextProcedureNode.getSeqNo();
						int SeqNo = procedureNode.getSeqNo();
						nextProcedureNode.setSeqNo(SeqNo);
						procedureNode.setSeqNo(nextSeqNo);
					}
					procedureNodeService.updateByPrimaryKeySelective(nextProcedureNode);
					procedureNodeService.updateByPrimaryKeySelective(procedureNode);
				}
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "操作失败，请稍后重试");
				return resMap;
			}
			return resMap;
		}
		/**
		 * 删除节点
		 * @param request
		 * @param response
		 * @param paramMap
		 * @return
		 */
		@RequestMapping(value = "/approvalProcessManagement/delNode.shtml")
		@ResponseBody
		public Map<String, Object> delNode(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			try {
				String procedureNodeId = request.getParameter("procedureNodeId");//选中节点
				if(!StringUtil.isEmpty(procedureNodeId)){
				procedureNodeService.delNode(Integer.parseInt(procedureNodeId));	
				}
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "操作失败，请稍后重试");
				return resMap;
			}
			return resMap;
		}
		
		
		
		/**
		 * 我的审批
		 * @param request
		 * @param response
		 * @param paramMap
		 * @return
		 */
		@RequestMapping("/approvalProcessManagement/myApproval.shtml")
		public ModelAndView goodsMerchandise(HttpServletRequest request) {
			String rtPage = "/approvalProcessManagement/myApprovalList";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("myApproval", true);
			return new ModelAndView(rtPage,resMap);
		}
		
		//待我审批的
		@RequestMapping("/approvalProcessManagement/examinedApprovedToMeList.shtml")
		public ModelAndView examinedApprovedToMe(HttpServletRequest request){
			String rtPage = "/approvalProcessManagement/examinedApprovedToMeList";
			return new ModelAndView(rtPage);
		}
		
		//待我审批的的数据
		@RequestMapping("/approvalProcessManagement/examinedApprovedToMeListDate.shtml")
		@ResponseBody
		public Map<String, Object> examinedApprovedToMeListDate(HttpServletRequest request , Page page){
			Map<String, Object> resMap = new HashMap<String, Object>();
			
			HashMap<String, Object> parMap = new HashMap<String, Object>();
			String staffID = this.getSessionStaffBean(request).getStaffID();//审批人ID
			if(!StringUtil.isEmpty(staffID)){
				parMap.put("staffId", Integer.parseInt(staffID));
			}
			if(!StringUtil.isEmpty(request.getParameter("applicationName"))){//标题名称
				parMap.put("applicationName",request.getParameter("applicationName"));
				
			}
			if(!StringUtil.isEmpty(request.getParameter("createBy"))){//流程名称(类型)
				parMap.put("createBy",request.getParameter("createBy"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))){//状态
				parMap.put("status",request.getParameter("status"));
			}
			
			parMap.put("orderBy", " ORDER BY (select aa.commit_date from  bu_approval_application aa where aan.approval_application_id = aa.id  and aa.del_flag = '0' ) DESC");
			parMap.put("pageLimit", " limit " + page.getLimitStart() + ", "+ page.getLimitSize());
			
			List<ApprovalApplicationNodeCustom> applicationNodeCustoms = approvalApplicationNodeService.findToExamineCustom(parMap);
			int totalCount = approvalApplicationNodeService.countToExamineCustom(parMap);
			
			resMap.put("Rows", applicationNodeCustoms);
			resMap.put("Total", totalCount);
			return resMap;
		}
		
		
		//审核
		@RequestMapping("/approvalProcessManagement/approval.shtml")
		public ModelAndView Approval(HttpServletRequest request){
			String rtPage = "/approvalProcessManagement/approval";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String approvalApplicationNodeId = request.getParameter("approvalApplicationNodeId");//审批申请节点id
			if(!StringUtil.isEmpty(approvalApplicationNodeId)){
				String staffID = this.getSessionStaffBean(request).getStaffID();//审核人
				//审核人
				NodeApproverCustomExample nodeApproverCustomExample = new NodeApproverCustomExample();
				nodeApproverCustomExample.createCriteria().andDelFlagEqualTo("0").andNodeIdEqualTo(Integer.parseInt(approvalApplicationNodeId)).andApproverEqualTo(Integer.parseInt(staffID));
				List<NodeApproverCustom> noApproverCustoms = nodeApproverService.selectCustomByExample(nodeApproverCustomExample );
				if(noApproverCustoms!=null && noApproverCustoms.size()>0){
					NodeApproverCustom nodeApproverCustom = noApproverCustoms.get(0);
					resMap.put("nodeApproverCustom", nodeApproverCustom);
				
				//审核节点表
				ApprovalApplicationNode approvalApplicationNode = approvalApplicationNodeService.selectByPrimaryKey(nodeApproverCustom.getNodeId());
				resMap.put("approvalApplicationNode", approvalApplicationNode);
				//审核表
				 ApprovalApplicationCustom approvalApplicationCustom = approvalApplicationService.selectCustomByPrimaryKey(approvalApplicationNode.getApprovalApplicationId());
				resMap.put("approvalApplicationCustom", approvalApplicationCustom);
				
				//需要审核的节点
				List<String> typeList = new ArrayList<>();
				ApprovalApplicationNodeExample auditNodeExample = new ApprovalApplicationNodeExample();
				auditNodeExample.setOrderByClause("seq_no");
				auditNodeExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(approvalApplicationNode.getApprovalApplicationId()).andNeedApprovalEqualTo("1");
				List<ApprovalApplicationNode> auditNodeList = approvalApplicationNodeService.selectByExample(auditNodeExample );
				resMap.put("auditNodeList", auditNodeList);
				
				//备用节点
				HashMap<String, Object> parHsMap = new HashMap<>();
				parHsMap.put("approvalApplicationId", approvalApplicationNode.getApprovalApplicationId());
				parHsMap.put("seqNo", approvalApplicationNode.getSeqNo());
				int minCoreSeqNo = approvalApplicationNodeService.selectMinCoreSeqNo(parHsMap);//未审核的核心节点seq
				
				ApprovalApplicationNodeExample standbyExample = new ApprovalApplicationNodeExample();
				standbyExample.setOrderByClause("seq_no");
				com.jf.entity.ApprovalApplicationNodeExample.Criteria createCriteria = standbyExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0");
				createCriteria.andApprovalApplicationIdEqualTo(approvalApplicationNode.getApprovalApplicationId());
				createCriteria.andTypeEqualTo("2");
				
				if(minCoreSeqNo!=0){//当前节点和未审核的seq节点直接的备用节点
					createCriteria.andSeqNoBetween(approvalApplicationNode.getSeqNo(), minCoreSeqNo);
				}else{//最后一个核心节点之后的备用节点
					createCriteria.andSeqNoGreaterThanOrEqualTo(approvalApplicationNode.getSeqNo());
				}
				List<ApprovalApplicationNode> standbyNodeList = approvalApplicationNodeService.selectByExample(standbyExample );
				resMap.put("standbyNodeList", standbyNodeList);
			
				
				//图片
				ApprovalApplicationPicExample approvalApplicationPicExample = new ApprovalApplicationPicExample();
				approvalApplicationPicExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(approvalApplicationCustom.getId());
				List<ApprovalApplicationPic> approvalApplicationPicList = approvalApplicationPicService.selectByExample(approvalApplicationPicExample );
				resMap.put("approvalApplicationPicList",approvalApplicationPicList);
				}
			}
			return new ModelAndView(rtPage,resMap);
		}
		
		
		//保存审核
		@RequestMapping(value = "/approvalProcessManagement/saveApproval.shtml")
		@ResponseBody
		public Map<String, Object> saveApproval(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			String code = null;
	 		String msg =null;
			try{
				String staffID = this.getSessionStaffBean(request).getStaffID();//审核人
				String auditNode = request.getParameter("auditNode");//审核结果
				if("1".equals(auditNode)){//审核通过
					approvalApplicationNodeService.savePassApprovalAndLog(request,staffID);
				}
				if("2".equals(auditNode)){//审核驳回
					approvalApplicationNodeService.saveRejectApprovalAndLog(request,staffID);
				}
			
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "保存失败，请稍后重试");
				return resMap;
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
		
			return resMap ;
		}
		
		
		
		//查看审核(主表bu_approval_application_node)
		@RequestMapping("/approvalProcessManagement/watchApproval.shtml")
		public ModelAndView watchApproval(HttpServletRequest request){
			String rtPage = "/approvalProcessManagement/watchApproval";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String approvalApplicationNodeId = request.getParameter("approvalApplicationNodeId");//审批申请节点id
			if(!StringUtil.isEmpty(approvalApplicationNodeId)){
				String staffID = this.getSessionStaffBean(request).getStaffID();//审核人
				//审核人
				NodeApproverCustomExample nodeApproverCustomExample = new NodeApproverCustomExample();
				nodeApproverCustomExample.createCriteria().andDelFlagEqualTo("0").andNodeIdEqualTo(Integer.parseInt(approvalApplicationNodeId)).andApproverEqualTo(Integer.parseInt(staffID));
				List<NodeApproverCustom> noApproverCustoms = nodeApproverService.selectCustomByExample(nodeApproverCustomExample );
				if(noApproverCustoms!=null && noApproverCustoms.size()>0){
					NodeApproverCustom nodeApproverCustom = noApproverCustoms.get(0);
					resMap.put("nodeApproverCustom", nodeApproverCustom);
				
				//审核节点表
				ApprovalApplicationNode approvalApplicationNode = approvalApplicationNodeService.selectByPrimaryKey(nodeApproverCustom.getNodeId());
				resMap.put("approvalApplicationNode", approvalApplicationNode);
				//审核表
				 ApprovalApplicationCustom approvalApplicationCustom = approvalApplicationService.selectCustomByPrimaryKey(approvalApplicationNode.getApprovalApplicationId());
				resMap.put("approvalApplicationCustom", approvalApplicationCustom);
				
				//需要审核的节点
				ApprovalApplicationNodeCustomExample auditNodeExample = new ApprovalApplicationNodeCustomExample();
				auditNodeExample.setOrderByClause("seq_no");
				auditNodeExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(approvalApplicationNode.getApprovalApplicationId()).andNeedApprovalEqualTo("1");
				List<ApprovalApplicationNodeCustom> auditNodeList = approvalApplicationNodeService.selectCustomByExample(auditNodeExample);
				//查询每个流程需要审核的最大节点seq,将maxseq设置到log中
				 int maxSeq = approvalApplicationNodeService.selectNowMaxSeqNo(approvalApplicationNode.getApprovalApplicationId());
				 for(ApprovalApplicationNodeCustom aanode : auditNodeList){
					 aanode.setMaxSeq(maxSeq);
				 }
				resMap.put("auditNodeList", auditNodeList);
				
				//图片
				ApprovalApplicationPicExample approvalApplicationPicExample = new ApprovalApplicationPicExample();
				approvalApplicationPicExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(approvalApplicationCustom.getId());
				List<ApprovalApplicationPic> approvalApplicationPicList = approvalApplicationPicService.selectByExample(approvalApplicationPicExample );
				resMap.put("approvalApplicationPicList",approvalApplicationPicList);
				
				
				
				//操作日志
				ApprovalApplicationLogExample approvalApplicationLogExample = new ApprovalApplicationLogExample();
				approvalApplicationLogExample.setOrderByClause("create_date desc");
				Criteria createCriteria = approvalApplicationLogExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0");
				createCriteria.andApprovalApplicationIdEqualTo(approvalApplicationCustom.getId());
				List<ApprovalApplicationLogCustom> approvalApplicationLogs = approvalApplicationLogService.selectCustomByExample(approvalApplicationLogExample);
				resMap.put("approvalApplicationLogs",approvalApplicationLogs);
				}
			}
			return new ModelAndView(rtPage,resMap);
		}
	
		
		/**
		 * 
		 * 抄送我的
		 * 
		 */
		@RequestMapping("/approvalProcessManagement/copyApprovedToMe.shtml")
		public ModelAndView copyApprovedToMe(HttpServletRequest request){
			String rtPage = "/approvalProcessManagement/copyApprovedToMeList";
			Map<String, Object> resMap = new HashMap<String, Object>();
			//流程名称
			ProcedureExample procedureExample = new ProcedureExample();
			procedureExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
			List<Procedure> procedures = procedureService.selectByExample(procedureExample );
			resMap.put("procedures", procedures);
			return new ModelAndView(rtPage,resMap);
		}
		
		//抄送我的 的数据
		@RequestMapping("/approvalProcessManagement/copyApprovedToMeListDate.shtml")
		@ResponseBody
		public Map<String, Object> copyApprovedToMeListDate(HttpServletRequest request , Page page){
			Map<String, Object> resMap = new HashMap<String, Object>();
			
			String staffID = this.getSessionStaffBean(request).getStaffID();//抄送人ID
			
			ApprovalApplicationCustomExample approvalApplicationCustomExample = new ApprovalApplicationCustomExample();
			ApprovalApplicationCustomCriteria createCriteria = approvalApplicationCustomExample.createCriteria();
			createCriteria.andDelFlagEqualTo("0");
			approvalApplicationCustomExample.setOrderByClause("commit_date DESC");
			
			if(!StringUtil.isEmpty(staffID)){
				createCriteria.andCopiersEqualTo(Integer.parseInt(staffID));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("applicationName"))){//标题名称
				createCriteria.andNameLike("%"+request.getParameter("applicationName")+"%");
			} 
			
			if(!StringUtil.isEmpty(request.getParameter("createBy"))){//创建人
				createCriteria.andCreateByLike("%"+request.getParameter("createBy")+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("procedureType"))){//流程名称(类型)
				createCriteria.andProcedureIdEqualTo(Integer.parseInt(request.getParameter("procedureType")));
			}
			
			approvalApplicationCustomExample.setLimitSize(page.getLimitSize());
			approvalApplicationCustomExample.setLimitStart(page.getLimitStart());
			
			int totalCount = approvalApplicationService.countCustomsByExample(approvalApplicationCustomExample);
			List<ApprovalApplicationCustom> approvalApplicationCustoms = approvalApplicationService.selectCustomsByExample(approvalApplicationCustomExample);
			
			resMap.put("Rows", approvalApplicationCustoms);
			resMap.put("Total", totalCount);
			return resMap;
		}
		
		
		//查看审核(主表bu_approval_application)
		@RequestMapping("/approvalProcessManagement/watchApprovalApplication.shtml")
		public ModelAndView watchApprovalApplication(HttpServletRequest request){
			String rtPage = "/approvalProcessManagement/watchApproval";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String applicationId = request.getParameter("applicationId");//审批流程id
			if(!StringUtil.isEmpty(applicationId)){
				String staffID = this.getSessionStaffBean(request).getStaffID();//审核人
				//审核表
				 ApprovalApplicationCustom approvalApplicationCustom = approvalApplicationService.selectCustomByPrimaryKey(Integer.parseInt(applicationId));
				resMap.put("approvalApplicationCustom", approvalApplicationCustom);
				
				//需要审核的节点
				ApprovalApplicationNodeCustomExample auditNodeExample = new ApprovalApplicationNodeCustomExample();
				auditNodeExample.setOrderByClause("seq_no");
				auditNodeExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(Integer.parseInt(applicationId)).andNeedApprovalEqualTo("1");
				List<ApprovalApplicationNodeCustom> auditNodeList = approvalApplicationNodeService.selectCustomByExample(auditNodeExample);
				//查询每个流程需要审核的最大节点seq,将maxseq设置到log中
				 int maxSeq = approvalApplicationNodeService.selectNowMaxSeqNo(Integer.parseInt(applicationId));
				 for(ApprovalApplicationNodeCustom aanode : auditNodeList){
					 aanode.setMaxSeq(maxSeq);
				 }
				 resMap.put("auditNodeList", auditNodeList);
				 
				//图片
				ApprovalApplicationPicExample approvalApplicationPicExample = new ApprovalApplicationPicExample();
				approvalApplicationPicExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(approvalApplicationCustom.getId());
				List<ApprovalApplicationPic> approvalApplicationPicList = approvalApplicationPicService.selectByExample(approvalApplicationPicExample );
				resMap.put("approvalApplicationPicList",approvalApplicationPicList);
				
				
				//操作日志
				ApprovalApplicationLogExample approvalApplicationLogExample = new ApprovalApplicationLogExample();
				approvalApplicationLogExample.setOrderByClause("create_date desc");
				Criteria createCriteria = approvalApplicationLogExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0");
				createCriteria.andApprovalApplicationIdEqualTo(approvalApplicationCustom.getId());
				List<ApprovalApplicationLogCustom> approvalApplicationLogs = approvalApplicationLogService.selectCustomByExample(approvalApplicationLogExample);
				resMap.put("approvalApplicationLogs",approvalApplicationLogs);

				//判断审核图片内容不为空但是是空格也不要展示按钮
				for (ApprovalApplicationLogCustom approvalApplicationLog : approvalApplicationLogs) {
					if (approvalApplicationLog.getApprovalPic()!=null) {
						String trim = approvalApplicationLog.getApprovalPic().replace("&nbsp;","").replace("<br />","").trim();
						resMap.put("tag", trim);
						}
					}
				}
			return new ModelAndView(rtPage,resMap);
		}
		
		
		
		
		/**
		 * 全部审批流程
		 * 
		 * 
		 */
		@RequestMapping("/approvalProcessManagement/allApprovedProcess.shtml")
		public ModelAndView allApprovedProcess(HttpServletRequest request){
			String rtPage = "/approvalProcessManagement/allApprovedProcessList";
			Map<String, Object> resMap = new HashMap<String, Object>();
			//流程名称
			ProcedureExample procedureExample = new ProcedureExample();
			procedureExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
			List<Procedure> procedures = procedureService.selectByExample(procedureExample );
			resMap.put("procedures", procedures);
			return new ModelAndView(rtPage,resMap);
		}
		
		//全部审批流程 的数据
		@RequestMapping("/approvalProcessManagement/allApprovedProcessListDate.shtml")
		@ResponseBody
		public Map<String, Object> allApprovedProcessListDate(HttpServletRequest request , Page page){
			Map<String, Object> resMap = new HashMap<String, Object>();
			
			String staffID = this.getSessionStaffBean(request).getStaffID();//抄送人ID
			
			ApprovalApplicationCustomExample approvalApplicationCustomExample = new ApprovalApplicationCustomExample();
			ApprovalApplicationCustomCriteria createCriteria = approvalApplicationCustomExample.createCriteria();
			createCriteria.andDelFlagEqualTo("0");
			approvalApplicationCustomExample.setOrderByClause("commit_date DESC");
			
			if(!StringUtil.isEmpty(request.getParameter("applicationName"))){//标题名称
				createCriteria.andNameLike("%"+request.getParameter("applicationName")+"%");
			} 
			
			if(!StringUtil.isEmpty(request.getParameter("createBy"))){//创建人
				createCriteria.andCreateByLike("%"+request.getParameter("createBy")+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("procedureType"))){//流程名称(类型)
				createCriteria.andProcedureIdEqualTo(Integer.parseInt(request.getParameter("procedureType")));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status"))){//申请单状态
				createCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			
			approvalApplicationCustomExample.setLimitSize(page.getLimitSize());
			approvalApplicationCustomExample.setLimitStart(page.getLimitStart());
			
			int totalCount = approvalApplicationService.countCustomsByExample(approvalApplicationCustomExample);
			List<ApprovalApplicationCustom> approvalApplicationCustoms = approvalApplicationService.selectCustomsByExample(approvalApplicationCustomExample);
			
			resMap.put("Rows", approvalApplicationCustoms);
			resMap.put("Total", totalCount);
			return resMap;
		}
		
		
		
		/**
		 * 我的申请
		 * @param request
		 * @param response
		 * @param paramMap
		 * @return
		 */
		@RequestMapping("/approvalProcessManagement/myApplication.shtml")
		public ModelAndView myApplication(HttpServletRequest request){
			String rtPage = "/approvalProcessManagement/myApplicationList";
			Map<String, Object> resMap = new HashMap<String, Object>();
			ProcedureExample procedureExample = new ProcedureExample();
			procedureExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
			List<Procedure> procedureList = procedureService.selectByExample(procedureExample );
			resMap.put("procedureList", procedureList);
			
			return new ModelAndView(rtPage,resMap);
		}
		
		
		//我的申请的数据
		@RequestMapping("/approvalProcessManagement/myApplicationDate.shtml")
		@ResponseBody
		public Map<String, Object> myApplicationDate(HttpServletRequest request , Page page){
			Map<String, Object> resMap = new HashMap<String, Object>();
			
			ApprovalApplicationCustomExample approvalApplicationExample = new ApprovalApplicationCustomExample();
			ApprovalApplicationCustomCriteria createCriteria = approvalApplicationExample.createCriteria();
			createCriteria.andDelFlagEqualTo("0");
			approvalApplicationExample.setOrderByClause("create_date desc");
			
			String staffID = this.getSessionStaffBean(request).getStaffID();
			if(!StringUtil.isEmpty(staffID)){
				createCriteria.andCreateByEqualTo(Integer.parseInt(staffID));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("name"))){//标题名称
				createCriteria.andNameLike("%"+(request.getParameter("name"))+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("procedureId"))){//流程名称(类型)
				createCriteria.andprocedureNameEqualTo(Integer.parseInt(request.getParameter("procedureId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))){//状态
				createCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			
			approvalApplicationExample.setLimitSize(page.getLimitSize());
			approvalApplicationExample.setLimitStart(page.getLimitStart());
			
			
			Integer totalCount = approvalApplicationService.countCustomsByExample(approvalApplicationExample);
			List<ApprovalApplicationCustom> ApprovalApplicationCustomList = approvalApplicationService.selectCustomsByExample(approvalApplicationExample);
			
			resMap.put("Rows", ApprovalApplicationCustomList);
			resMap.put("Total", totalCount);
			return resMap;
		}
	
		
		//创建或修改我的申请
		@RequestMapping("/approvalProcessManagement/createMyApplication.shtml")
		public ModelAndView createMyApplication(HttpServletRequest request){
			String rtPage = "/approvalProcessManagement/createMyApplication";
			Map<String, Object> resMap = new HashMap<String, Object>();
			
			//流程名称
			ProcedureExample procedureExample = new ProcedureExample();
			procedureExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
			List<Procedure> procedureList = procedureService.selectByExample(procedureExample );
			resMap.put("procedureList", procedureList);
			
			//修改
			String approvalApplicationId = request.getParameter("approvalApplicationId");//审核申请表ID
			if(!StringUtil.isEmpty(approvalApplicationId)){
				ApprovalApplication approvalApplication = approvalApplicationService.selectByPrimaryKey(Integer.parseInt(approvalApplicationId));
				resMap.put("approvalApplication", approvalApplication);
				
				Procedure procedure = procedureService.selectByPrimaryKey(approvalApplication.getProcedureId());
				//抄送人
				String[] copiesArr = procedure.getCopiers().split(",");
				List<Integer> asList = new ArrayList<>();
				for (int i = 0; i < copiesArr.length; i++) {
					asList.add(Integer.parseInt(copiesArr[i]));
				}
				List<SysStaffInfoCustom> staffNameList = getStaffNameList(asList);
				resMap.put("staffNameList", staffNameList);
				
				//节点
				ProcedureNodeExample procedureNodeExample = new ProcedureNodeExample();
				procedureNodeExample.setOrderByClause("seq_no");
				procedureNodeExample.createCriteria().andDelFlagEqualTo("0").andProcedureIdEqualTo(approvalApplication.getProcedureId());
				List<ProcedureNode> procedureNodeList = procedureNodeService.selectByExample(procedureNodeExample );
				resMap.put("procedureNodeList", procedureNodeList);
				
				
				//图片
				ApprovalApplicationPicExample approvalApplicationPicExample = new ApprovalApplicationPicExample();
				approvalApplicationPicExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(Integer.parseInt(approvalApplicationId));
				List<ApprovalApplicationPic> approvalApplicationPicList = approvalApplicationPicService.selectByExample(approvalApplicationPicExample );
				resMap.put("approvalApplicationPicList",approvalApplicationPicList);

				//操作日志
				ApprovalApplicationLogExample approvalApplicationLogExample = new ApprovalApplicationLogExample();
				approvalApplicationLogExample.setOrderByClause("create_date desc");
				Criteria createCriteria = approvalApplicationLogExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(Integer.parseInt(approvalApplicationId));
				List<ApprovalApplicationLogCustom> approvalApplicationLogs = approvalApplicationLogService.selectCustomByExample(approvalApplicationLogExample);
				resMap.put("approvalApplicationLogs",approvalApplicationLogs);

				//判断审核图片内容不为空但是是空格也不要展示按钮
				for (ApprovalApplicationLogCustom approvalApplicationLog : approvalApplicationLogs) {
					if (approvalApplicationLog.getApprovalPic()!=null) {
						String trim = approvalApplicationLog.getApprovalPic().replace("&nbsp;","").replace("<br />","").trim();
						resMap.put("tag", trim);
					}
					//修改界面如果日志有驳回记录即展示审核记录过程
					if (approvalApplicationLog.getOperation().equals("驳回")){
						resMap.put("recode",true);
					}
				}
			}
			
			return new ModelAndView(rtPage,resMap);
		}
	
		
		//获取节点和抄送人
		@RequestMapping(value = "/approvalProcessManagement/getNodeAndCopies.shtml")
		@ResponseBody
		public Map<String, Object> getNodeAndCopies(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			try {
				String procedureId = request.getParameter("procedureId");//流程ID
				if("".equals(procedureId) || procedureId==null){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "请选择流程名称");
					return resMap;
				}
				Procedure procedure = procedureService.selectByPrimaryKey(Integer.parseInt(procedureId));
				//抄送人
				String[] copiesArr = procedure.getCopiers().split(",");
				List<Integer> asList = new ArrayList<>();
				for (int i = 0; i < copiesArr.length; i++) {
					asList.add(Integer.parseInt(copiesArr[i]));
				}
				List<SysStaffInfoCustom> staffNameList = getStaffNameList(asList);
				resMap.put("staffNameList", staffNameList);
				
				//节点
				ProcedureNodeExample procedureNodeExample = new ProcedureNodeExample();
				procedureNodeExample.setOrderByClause("seq_no");
				procedureNodeExample.createCriteria().andDelFlagEqualTo("0").andProcedureIdEqualTo(Integer.parseInt(procedureId));
				List<ProcedureNode> procedureNodeList = procedureNodeService.selectByExample(procedureNodeExample );
				resMap.put("procedureNodeList", procedureNodeList);

			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "操作失败，请稍后重试");
				return resMap;
			}
			return resMap;
		}
		
		//保存我的申请
		@RequestMapping(value = "/approvalProcessManagement/saveMyApplication.shtml")
		@ResponseBody
		public Map<String, Object> saveMyApplication(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			String code = null;
	 		String msg =null;
			try{
				String staffID = this.getSessionStaffBean(request).getStaffID();//创建人
				String approvalApplicationId = request.getParameter("approvalApplicationId");//申请表ID
				String graphicContent = request.getParameter("content");//富文本内容
				String picPaths = request.getParameter("picPaths");//图片集合
				String attachmentPath = request.getParameter("attachmentPath");//附件路径
				String attachmentName = request.getParameter("attachmentName");//附件名字
				String procedureId = request.getParameter("procedureId");//流程id
				String name = request.getParameter("name");//标题名称
				String linkUrl = request.getParameter("linkUrl");//连接url
				List<String> picPathList = new ArrayList<>();
				if(!StringUtil.isEmpty(picPaths)){
				JSONArray picPathsJson = JSONArray.fromObject(picPaths);				
				 picPathList = (List<String>) JSONArray.toCollection(picPathsJson, String.class);
				}
				
				if(!StringUtil.isEmpty(approvalApplicationId)){//存在
					ApprovalApplication approvalApplication = approvalApplicationService.selectByPrimaryKey(Integer.parseInt(approvalApplicationId));
					approvalApplication.setContent(graphicContent);
					approvalApplication.setFilePath(attachmentPath);
					approvalApplication.setEnclosureName(attachmentName);
					approvalApplication.setLinkUrl(linkUrl);
					approvalApplication.setProcedureId(Integer.parseInt(procedureId));
					approvalApplication.setName(name);
					approvalApplication.setStatus("0");//未提审
					approvalApplication.setUpdateBy(Integer.parseInt(staffID));
					approvalApplication.setUpdateDate(new Date());
					approvalApplicationService.updateApplicatoinAndLog(approvalApplication,staffID,picPathList);
				}else{//不存在
					ApprovalApplication  application = new ApprovalApplication();
					application.setContent(graphicContent);
					application.setFilePath(attachmentPath);
					application.setEnclosureName(attachmentName);
					application.setLinkUrl(linkUrl);
					application.setProcedureId(Integer.parseInt(procedureId));
					application.setName(name);
					application.setStatus("0");//未提审
					application.setCreateBy(Integer.parseInt(staffID));
					application.setCreateDate(new Date());
					approvalApplicationService.insertApplicatoinAndLog(application, staffID,picPathList);
				}
				
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "保存失败，请稍后重试");
				return resMap;
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
		
			return resMap ;
		}
		
		
		//删除我的申请审批
		@RequestMapping(value = "/approvalProcessManagement/delMyApplication.shtml")
		@ResponseBody
		public Map<String, Object> delMyApplication(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			try {
				String staffID = this.getSessionStaffBean(request).getStaffID();//创建人
				String approvalApplicationId = request.getParameter("approvalApplicationId");//审核申请ID
				ApprovalApplication approvalApplication = approvalApplicationService.selectByPrimaryKey(Integer.parseInt(approvalApplicationId));//审核申请表
				approvalApplicationService.delMyApplication(approvalApplication,staffID);
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "操作失败，请稍后重试");
				return resMap;
			}
			return resMap;
		}
		
		//提审我的申请审批
		@RequestMapping(value = "/approvalProcessManagement/arraignment.shtml")
		@ResponseBody
		public Map<String, Object> arraignment(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			try {
				String staffID = this.getSessionStaffBean(request).getStaffID();//创建人
				String approvalApplicationId = request.getParameter("approvalApplicationId");//审核申请ID
				ApprovalApplication approvalApplication = approvalApplicationService.selectByPrimaryKey(Integer.parseInt(approvalApplicationId));//审核申请表
				approvalApplicationService.arraignment(approvalApplication,staffID);
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "操作失败，请稍后重试");
				return resMap;
			}
			return resMap;
		}
		
		//恢复我的申请审批
		@RequestMapping(value = "/approvalProcessManagement/recovery.shtml")
		@ResponseBody
		public Map<String, Object> recovery(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			try {
				String staffID = this.getSessionStaffBean(request).getStaffID();//创建人
				String approvalApplicationId = request.getParameter("approvalApplicationId");//审核申请ID
				ApprovalApplication approvalApplication = approvalApplicationService.selectByPrimaryKey(Integer.parseInt(approvalApplicationId));//审核申请表
				approvalApplicationService.recovery(approvalApplication,staffID);
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "操作失败，请稍后重试");
				return resMap;
			}
			return resMap;
		}
		
		
	
		//日志的查看(主表bu_approval_application_log)
		@RequestMapping("/approvalProcessManagement/auditView.shtml")
		public ModelAndView auditView(HttpServletRequest request){
			String rtPage = "/approvalProcessManagement/auditView";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String LogId = request.getParameter("LogId");//日志ID
			if(!StringUtil.isEmpty(LogId)){
				//操作日志
				ApprovalApplicationLog applicationLog = approvalApplicationLogService.selectByPrimaryKey(Integer.parseInt(LogId));
				resMap.put("applicationLog",applicationLog);;
				
				}
			return new ModelAndView(rtPage,resMap);
		}
	
		/**
		 * 创建说明
		 * instructions
		 * */
		@RequestMapping("/approvalProcessManagement/creatInstructions.shtml")
		public ModelAndView instructions(HttpServletRequest request){
			String rtPage = "/approvalProcessManagement/creatInstructionsView";
			Map<String, Object> resMap = new HashMap<String, Object>();

			InformationExample informationExample = new InformationExample();
			//bu_information的status 1启用 2停用 informationType是id   delflag删除标志
			informationExample.createCriteria().andIdEqualTo(266).andStatusEqualTo("1").andDelFlagEqualTo("0");
			List<Information> informationList = informationService.selectByExampleWithBLOBs(informationExample);
			if(!informationList.isEmpty()){
				resMap.put("information", informationList.get(0));
			}
			return new ModelAndView(rtPage,resMap);
		}


	/**
	 * 审批说明
	 * instructions
	 * */
	@RequestMapping("/approvalProcessManagement/approvalInstructions.shtml")
	public ModelAndView approvalInstructions(HttpServletRequest request){
		String rtPage = "/approvalProcessManagement/approvalInstructionsView";
		Map<String, Object> resMap = new HashMap<String, Object>();

		InformationExample informationExample = new InformationExample();
		//bu_information的status 1启用 2停用 informationType是id   delflag删除标志
		informationExample.createCriteria().andIdEqualTo(267).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<Information> informationList = informationService.selectByExampleWithBLOBs(informationExample);
		if(!informationList.isEmpty()){
			resMap.put("information", informationList.get(0));
		}
		return new ModelAndView(rtPage,resMap);
	}
}
