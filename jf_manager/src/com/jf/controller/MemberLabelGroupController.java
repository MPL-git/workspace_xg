package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MemberLabel;
import com.jf.entity.MemberLabelExample;
import com.jf.entity.MemberLabelGroup;
import com.jf.entity.MemberLabelGroupCustom;
import com.jf.entity.MemberLabelGroupCustomExample;
import com.jf.entity.MemberLabelGroupRelation;
import com.jf.entity.MemberLabelGroupRelationCustom;
import com.jf.entity.MemberLabelGroupRelationExample;
import com.jf.entity.MemberLabelType;
import com.jf.entity.MemberLabelTypeExample;
import com.jf.entity.StaffBean;
import com.jf.service.MemberLabelGroupRelationService;
import com.jf.service.MemberLabelGroupService;
import com.jf.service.MemberLabelService;
import com.jf.service.MemberLabelTypeService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class MemberLabelGroupController extends BaseController {

	@Autowired
	private MemberLabelGroupService memberLabelGroupService;
	
	@Autowired
	private MemberLabelGroupRelationService memberLabelGroupRelationService;
	
	@Resource
	private MemberLabelTypeService memberLabelTypeService;
	
	@Resource
	private MemberLabelService memberLabelService;
	
	/**
	 * 
	 * @MethodName: memberLabelGroupListManager
	 * @Description: (选择分组)
	 * @author Pengl
	 * @date 2019年8月7日 上午10:06:08
	 */
	@RequestMapping("/memberLabelGroup/memberLabelGroupListManager.shtml")
	public ModelAndView memberLabelGroupListManager(HttpServletRequest request,HttpServletResponse response ) {
		ModelAndView m = new ModelAndView("/memberLabel/memberLabelGroup/getMemberLabelGroupList");
		m.addObject("memberLabelGroupIds", request.getParameter("memberLabelGroupIds"));
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/memberLabelGroup/getMemberLabelGroupList.shtml")
	public Map<String, Object> getMemberLabelGroupList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MemberLabelGroupCustom> dataList = null;
		Integer totalCount = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			MemberLabelGroupCustomExample memberLabelGroupCustomExample = new MemberLabelGroupCustomExample();
			MemberLabelGroupCustomExample.MemberLabelGroupCustomCriteria memberLabelGroupCustomCriteria = memberLabelGroupCustomExample.createCriteria();
			memberLabelGroupCustomCriteria.andDelFlagEqualTo("0");
			String memberLabelGroupIds = request.getParameter("memberLabelGroupIds");
			if(!StringUtils.isEmpty(memberLabelGroupIds) ) {
				List<Integer> memberLabelGroupIdList = new ArrayList<Integer>();
				String[] groupIds = memberLabelGroupIds.split(",");
				for(String groupId : groupIds) {
					memberLabelGroupIdList.add(Integer.parseInt(groupId));
				}
				memberLabelGroupCustomCriteria.andIdNotIn(memberLabelGroupIdList);
			}
			if(!StringUtils.isEmpty(request.getParameter("labelGroupName")) ) {
				memberLabelGroupCustomCriteria.andLabelGroupNameLike("%"+request.getParameter("labelGroupName")+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) {
			    /*memberLabelGroupCustomCriteria.andMemberLabelStatusEqualTo(request.getParameter("status"));*/
				memberLabelGroupCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtils.isEmpty(request.getParameter("createDateBegin")) ) {
				memberLabelGroupCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(request.getParameter("createDateEnd")) ) {
				memberLabelGroupCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			memberLabelGroupCustomExample.setOrderByClause(" t.update_date desc");
			memberLabelGroupCustomExample.setLimitStart(page.getLimitStart());
			memberLabelGroupCustomExample.setLimitSize(page.getLimitSize());
			totalCount = memberLabelGroupService.countMemberLabelGroupCustomExample(memberLabelGroupCustomExample);
			dataList = memberLabelGroupService.selectMemberLabelGroupCustomExample(memberLabelGroupCustomExample);
			for(MemberLabelGroupCustom memberLabelGroupCustom : dataList) {
				List<Map<String, Object>> memberLabelGroupRelationList = memberLabelGroupRelationService.getMemberLabelGroupRelationMap(memberLabelGroupCustom.getId());
				for(Map<String, Object> memberLabelGroupRelationMap : memberLabelGroupRelationList ) {
					if(memberLabelGroupRelationMap != null ) {
						if(memberLabelGroupCustom.getLabelTypeName() != null ) {
							memberLabelGroupCustom.setLabelTypeName(memberLabelGroupCustom.getLabelTypeName() + "&" + memberLabelGroupRelationMap.get("label_type_name") + "=" + memberLabelGroupRelationMap.get("label_names"));
						}else {
							memberLabelGroupCustom.setLabelTypeName(memberLabelGroupRelationMap.get("label_type_name") + "=" + memberLabelGroupRelationMap.get("label_names"));
						}
					}
				}
				if (memberLabelGroupCustom.getStatDate()==null || memberLabelGroupCustom.getStatDate().before(memberLabelGroupCustom.getUpdateDate())) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("labelGroupId", memberLabelGroupCustom.getId());
					Integer memberStatNum=memberLabelGroupService.selectMemberCount(paramMap);

					MemberLabelGroup memberLabelGroup=memberLabelGroupService.selectByPrimaryKey(memberLabelGroupCustom.getId());
					memberLabelGroup.setStatNum(memberStatNum);
					memberLabelGroup.setStatDate(new Date());
					memberLabelGroupService.updateByPrimaryKey(memberLabelGroup);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	//用户标签分组
	@RequestMapping("/memberLabel/groupList.shtml")
	public ModelAndView groupList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/memberLabel/groupList");
		return m;
	}
	
	//用户标签分组数据
	@ResponseBody
	@RequestMapping("/memberLabel/groupListdata.shtml")
	public Map<String, Object> groupListdata(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MemberLabelGroupCustom> dataList = null;
		Integer totalCount = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			MemberLabelGroupCustomExample memberLabelGroupCustomExample=new MemberLabelGroupCustomExample();
			MemberLabelGroupCustomExample.MemberLabelGroupCustomCriteria memberLabelGroupCustomCriteria=memberLabelGroupCustomExample.createCriteria();
			memberLabelGroupCustomCriteria.andDelFlagEqualTo("0");
			memberLabelGroupCustomExample.setOrderByClause("update_date desc");
			
			if (!StringUtil.isEmpty(request.getParameter("labelGroupName"))) {
				memberLabelGroupCustomCriteria.andLabelGroupNameLike("%"+request.getParameter("labelGroupName")+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("update_begin")) && !StringUtil.isEmpty(request.getParameter("update_end"))) {
				memberLabelGroupCustomCriteria.andUpdateDateBetween(sdf.parse(request.getParameter("update_begin")+" 00:00:00"),sdf.parse(request.getParameter("update_end")+ " 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) {
				/*memberLabelGroupCustomCriteria.andMemberLabelStatusEqualTo(request.getParameter("status"));*/
				memberLabelGroupCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			memberLabelGroupCustomExample.setLimitSize(page.getLimitSize());
			memberLabelGroupCustomExample.setLimitStart(page.getLimitStart());
			dataList =memberLabelGroupService.selectMemberLabelGroupCustomExample(memberLabelGroupCustomExample);
			totalCount =memberLabelGroupService.countMemberLabelGroupCustomExample(memberLabelGroupCustomExample); 
			for(MemberLabelGroupCustom memberLabelGroupCustom : dataList) {
				List<Map<String, Object>> memberLabelGroupRelationList = memberLabelGroupRelationService.getMemberLabelGroupRelationMap(memberLabelGroupCustom.getId());
				for(Map<String, Object> memberLabelGroupRelationMap : memberLabelGroupRelationList ) {
					if(memberLabelGroupRelationMap != null ) {
						if(memberLabelGroupCustom.getLabelTypeName() != null ) {
							memberLabelGroupCustom.setLabelTypeName(memberLabelGroupCustom.getLabelTypeName() + "&" + memberLabelGroupRelationMap.get("label_type_name") + "=" + memberLabelGroupRelationMap.get("label_names"));
						}else {
							memberLabelGroupCustom.setLabelTypeName(memberLabelGroupRelationMap.get("label_type_name") + "=" + memberLabelGroupRelationMap.get("label_names"));
						}
					}
				}
				
				if (memberLabelGroupCustom.getStatDate()==null || memberLabelGroupCustom.getStatDate().before(memberLabelGroupCustom.getUpdateDate())) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("labelGroupId", memberLabelGroupCustom.getId());
					Integer memberStatNum=memberLabelGroupService.selectMemberCount(paramMap);
					
					MemberLabelGroup memberLabelGroup=memberLabelGroupService.selectByPrimaryKey(memberLabelGroupCustom.getId());
					memberLabelGroup.setStatNum(memberStatNum);
					memberLabelGroup.setStatDate(new Date());
					memberLabelGroupService.updateByPrimaryKey(memberLabelGroup);
					
					
					
				}
			
			}
			
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	//添加或编辑用户标签分组
	@RequestMapping(value = "/memberLabel/groupAdd.shtml")
	public ModelAndView groupAdd(HttpServletRequest request) {
		String rtPage = "/memberLabel/groupAdd";	
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		MemberLabelTypeExample memberLabelTypeExample=new MemberLabelTypeExample();//用户标签类型
		MemberLabelTypeExample.Criteria memberLabelTypeCriteria=memberLabelTypeExample.createCriteria();
		memberLabelTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MemberLabelType> memberLabelTypeList=memberLabelTypeService.selectByExample(memberLabelTypeExample);
		if (memberLabelTypeList!=null && memberLabelTypeList.size()>0) {
			resMap.put("memberLabelTypeList", JSONArray.fromObject(memberLabelTypeList));
			
		}
		
        String id=request.getParameter("Id");
        if (id!=null && id!="") {
			MemberLabelGroup memberLabelGroup=memberLabelGroupService.selectByPrimaryKey(Integer.valueOf(id));
			resMap.put("memberLabelGroup", memberLabelGroup);
			
			MemberLabelGroupRelationExample memberLabelGroupRelationExample=new MemberLabelGroupRelationExample();
			memberLabelGroupRelationExample.createCriteria().andDelFlagEqualTo("0").andLabelGroupIdEqualTo(memberLabelGroup.getId()).andTypeEqualTo("0");
			List<MemberLabelGroupRelation> memberLabelGroupRelations=memberLabelGroupRelationService.selectByExample(memberLabelGroupRelationExample);
			Set<Integer> LabelTypeIdList = new TreeSet<Integer>();
			List<Integer> LabelTypeIDList=new ArrayList<Integer>();
			List<Integer> labelIDlist=new ArrayList<Integer>();
			for (MemberLabelGroupRelation memberLabelGroupRelation : memberLabelGroupRelations) {
				LabelTypeIdList.add(memberLabelGroupRelation.getLabelTypeId());
				LabelTypeIDList.add(memberLabelGroupRelation.getLabelTypeId());
				labelIDlist.add(memberLabelGroupRelation.getLabelId());
				
			}
			resMap.put("LabelTypeIdList", LabelTypeIdList);
			
			MemberLabelExample memberLabelExample=new MemberLabelExample();
			memberLabelExample.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdIn(LabelTypeIDList).andIdIn(labelIDlist);
			List<MemberLabel> memberLabels=memberLabelService.selectByExample(memberLabelExample);
			if (memberLabels!=null && memberLabels.size()>0) {
				resMap.put("memberLabels", memberLabels);
			
			}
			
			
			MemberLabelGroupRelationExample memberLabelGroupRelationExample1=new MemberLabelGroupRelationExample();
			memberLabelGroupRelationExample1.createCriteria().andDelFlagEqualTo("0").andLabelGroupIdEqualTo(memberLabelGroup.getId()).andTypeEqualTo("1");
			List<MemberLabelGroupRelation> memberLabelGroupRelations1=memberLabelGroupRelationService.selectByExample(memberLabelGroupRelationExample1);
			if (memberLabelGroupRelations1!=null && memberLabelGroupRelations1.size()>0) {
				Set<Integer> LabelTypeIdList1 = new TreeSet<Integer>();
				List<Integer> LabelTypeIDList1=new ArrayList<Integer>();
				List<Integer> labelIDlist1=new ArrayList<Integer>();
				for (MemberLabelGroupRelation memberLabelGroupRelation : memberLabelGroupRelations1) {
					LabelTypeIdList1.add(memberLabelGroupRelation.getLabelTypeId());
					LabelTypeIDList1.add(memberLabelGroupRelation.getLabelTypeId());
					labelIDlist1.add(memberLabelGroupRelation.getLabelId());
					
				}
				resMap.put("LabelTypeIdList1", LabelTypeIdList1);
				
				MemberLabelExample memberLabelExample1=new MemberLabelExample();
				memberLabelExample1.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdIn(LabelTypeIDList1).andIdIn(labelIDlist1);
				List<MemberLabel> memberLabels1=memberLabelService.selectByExample(memberLabelExample1);
				if (memberLabels1!=null && memberLabels1.size()>0) {
					resMap.put("memberLabels1", memberLabels1);
					
				}
				
			}
						
		}
        
		return new ModelAndView(rtPage, resMap);
    }
	

	//获取用户标签
	@RequestMapping(value = "/memberLabel/getMemberLabelType.shtml")
	@ResponseBody
	public Map<String, Object> getMemberLabelType(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if (StringUtil.isEmpty(request.getParameter("memberLabelTypeid"))) {
				resMap.put("returnCode", "9999");
				resMap.put("returnMsg", "失败");
			}else {
				String memberLabelTypeid = request.getParameter("memberLabelTypeid");
				MemberLabelExample memberLabelExample=new MemberLabelExample();
				memberLabelExample.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdEqualTo(Integer.valueOf(memberLabelTypeid));
				List<MemberLabel> memberLabels=memberLabelService.selectByExample(memberLabelExample);
				if (memberLabels!=null && memberLabels.size()>0) {
					resMap.put("memberLabels", memberLabels);
				}
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	
	
	//修改用户标签分组状态
	@RequestMapping(value = "/memberLabel/updatememberLabelGroup.shtml")
	@ResponseBody
	public Map<String, Object> updatememberLabelGroup(HttpServletRequest request,HttpServletResponse response,String id,String status){
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			Date date=new Date();
			
			MemberLabelGroup memberLabelGroup=memberLabelGroupService.selectByPrimaryKey(Integer.valueOf(id));
			if (status.equals("0")) {
				memberLabelGroup.setStatus(status);
			}else if (status.equals("1")) {
				memberLabelGroup.setStatus(status);
			}
			memberLabelGroup.setUpdateBy(staffId);
			memberLabelGroup.setUpdateDate(date);
			memberLabelGroupService.updateByPrimaryKeySelective(memberLabelGroup);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		}
		
		return resMap;
	}
	
	
	
	    //保存用户分组
		@ResponseBody
		@RequestMapping(value = "/memberLabel/addmemberLabelGroup.shtml")
		public  Map<String, Object> addmemberLabelTyle(HttpServletRequest request,String id,String labelGroupName,String type0,String type1,String remarks) {
			Map<String, Object> resMap = new HashMap<String, Object>();					
			String returnCode = "0000";
			String returnMsg = "操作成功！";
			try {
				StaffBean staffBean = this.getSessionStaffBean(request);
				Integer staffId = Integer.valueOf(staffBean.getStaffID());
				Date date = new Date();
				
				if (StringUtil.isEmpty(id)) {//添加
					MemberLabelGroup memberLabelGroup=new MemberLabelGroup();
					memberLabelGroup.setCreateBy(staffId);
					memberLabelGroup.setCreateDate(date);
					memberLabelGroup.setStatus("1");
					memberLabelGroup.setDelFlag("0");
					memberLabelGroup.setUpdateDate(date);
					memberLabelGroup.setLabelGroupName(labelGroupName);
					memberLabelGroup.setRemarks(remarks);
					memberLabelGroupService.insertSelective(memberLabelGroup);
					
					if (type0.equals("0") && !StringUtil.isEmpty(request.getParameter("jsonMemberlabelgroup"))) {
						MemberLabelGroupRelation memberLabelGroupRelation=new MemberLabelGroupRelation();
						String jsonMemberlabelgroup = request.getParameter("jsonMemberlabelgroup");
						JSONArray jsonMemberlabelgroupLsit = JSONArray.fromObject(jsonMemberlabelgroup);
						for (int i = 0; i < jsonMemberlabelgroupLsit.size(); i++) {
							memberLabelGroupRelation.setCreateBy(staffId);
							memberLabelGroupRelation.setCreateDate(date);
							memberLabelGroupRelation.setDelFlag("0");
							memberLabelGroupRelation.setLabelGroupId(memberLabelGroup.getId());
							memberLabelGroupRelation.setLabelTypeId(jsonMemberlabelgroupLsit.getJSONObject(i).getInt("labelTypeId"));
							memberLabelGroupRelation.setLabelId(jsonMemberlabelgroupLsit.getJSONObject(i).getInt("memberLabelid"));
							memberLabelGroupRelation.setType("0");
							memberLabelGroupRelationService.insert(memberLabelGroupRelation);
							
						}
						
					}
					
					if (type1.equals("1") && !StringUtil.isEmpty(request.getParameter("jsonMemberlabelgroup1"))) {
						MemberLabelGroupRelation memberLabelGroupRelation=new MemberLabelGroupRelation();
						String jsonMemberlabelgroup1 = request.getParameter("jsonMemberlabelgroup1");
						JSONArray jsonMemberlabelgroupLsit1 = JSONArray.fromObject(jsonMemberlabelgroup1);
						for (int i = 0; i < jsonMemberlabelgroupLsit1.size(); i++) {
							memberLabelGroupRelation.setCreateBy(staffId);
							memberLabelGroupRelation.setCreateDate(date);
							memberLabelGroupRelation.setDelFlag("0");
							memberLabelGroupRelation.setLabelGroupId(memberLabelGroup.getId());
							memberLabelGroupRelation.setLabelTypeId(jsonMemberlabelgroupLsit1.getJSONObject(i).getInt("labelTypeId1"));
							memberLabelGroupRelation.setLabelId(jsonMemberlabelgroupLsit1.getJSONObject(i).getInt("memberLabelid1"));
							memberLabelGroupRelation.setType("1");
							memberLabelGroupRelationService.insert(memberLabelGroupRelation);
							
						}
					}
					
					
				}else {//编辑
					
					MemberLabelGroup memberLabelGroup=memberLabelGroupService.selectByPrimaryKey(Integer.valueOf(id));
					memberLabelGroup.setUpdateBy(staffId);
					memberLabelGroup.setUpdateDate(date);
					memberLabelGroup.setLabelGroupName(labelGroupName);
					memberLabelGroup.setRemarks(remarks);
					memberLabelGroupService.updateByPrimaryKeySelective(memberLabelGroup);
					
					if (type0.equals("0") && !StringUtil.isEmpty(request.getParameter("jsonMemberlabelgroup"))) {
						
						    MemberLabelGroupRelationExample memberLabelGroupRelationExample=new MemberLabelGroupRelationExample();
						    MemberLabelGroupRelationExample.Criteria meCriteria=memberLabelGroupRelationExample.createCriteria();
						    meCriteria.andDelFlagEqualTo("0").andLabelGroupIdEqualTo(memberLabelGroup.getId()).andTypeEqualTo(type0);
						
							MemberLabelGroupRelation memberLabelGroupRelation=new MemberLabelGroupRelation();
							memberLabelGroupRelation.setUpdateBy(staffId);
							memberLabelGroupRelation.setUpdateDate(date);
							memberLabelGroupRelation.setDelFlag("1");
							memberLabelGroupRelationService.updateByExampleSelective(memberLabelGroupRelation, memberLabelGroupRelationExample);
							
							
							MemberLabelGroupRelation memberLabelGroupRelation1=new MemberLabelGroupRelation();
							String jsonMemberlabelgroup = request.getParameter("jsonMemberlabelgroup");
							JSONArray jsonMemberlabelgroupLsit = JSONArray.fromObject(jsonMemberlabelgroup);
							for (int i = 0; i < jsonMemberlabelgroupLsit.size(); i++) {
								memberLabelGroupRelation1.setCreateBy(staffId);
								memberLabelGroupRelation1.setCreateDate(date);
								memberLabelGroupRelation1.setDelFlag("0");
								memberLabelGroupRelation1.setLabelGroupId(memberLabelGroup.getId());
								memberLabelGroupRelation1.setLabelTypeId(jsonMemberlabelgroupLsit.getJSONObject(i).getInt("labelTypeId"));
								memberLabelGroupRelation1.setLabelId(jsonMemberlabelgroupLsit.getJSONObject(i).getInt("memberLabelid"));
								memberLabelGroupRelation1.setType("0");
								memberLabelGroupRelationService.insert(memberLabelGroupRelation1);
								
							}
					
						
					}
					
					
					if (type1.equals("1") && !StringUtil.isEmpty(request.getParameter("jsonMemberlabelgroup1"))) {
						
					    MemberLabelGroupRelationExample memberLabelGroupRelationExample=new MemberLabelGroupRelationExample();
					    MemberLabelGroupRelationExample.Criteria meCriteria=memberLabelGroupRelationExample.createCriteria();
					    meCriteria.andDelFlagEqualTo("0").andLabelGroupIdEqualTo(memberLabelGroup.getId()).andTypeEqualTo(type1);
					
						MemberLabelGroupRelation memberLabelGroupRelation=new MemberLabelGroupRelation();
						memberLabelGroupRelation.setUpdateBy(staffId);
						memberLabelGroupRelation.setUpdateDate(date);
						memberLabelGroupRelation.setDelFlag("1");
						memberLabelGroupRelationService.updateByExampleSelective(memberLabelGroupRelation, memberLabelGroupRelationExample);
						
						
						MemberLabelGroupRelation memberLabelGroupRelation1=new MemberLabelGroupRelation();
						String jsonMemberlabelgroup = request.getParameter("jsonMemberlabelgroup1");
						JSONArray jsonMemberlabelgroupLsit = JSONArray.fromObject(jsonMemberlabelgroup);
						for (int i = 0; i < jsonMemberlabelgroupLsit.size(); i++) {
							memberLabelGroupRelation1.setCreateBy(staffId);
							memberLabelGroupRelation1.setCreateDate(date);
							memberLabelGroupRelation1.setDelFlag("0");
							memberLabelGroupRelation1.setLabelGroupId(memberLabelGroup.getId());
							memberLabelGroupRelation1.setLabelTypeId(jsonMemberlabelgroupLsit.getJSONObject(i).getInt("labelTypeId1"));
							memberLabelGroupRelation1.setLabelId(jsonMemberlabelgroupLsit.getJSONObject(i).getInt("memberLabelid1"));
							memberLabelGroupRelation1.setType("1");
							memberLabelGroupRelationService.insert(memberLabelGroupRelation1);
							
						}
				
				   }
					
				}
				
										
			} catch (Exception e) {
				e.printStackTrace();						
				returnCode = "4004";
				returnMsg = "系统错误！";
			}
			
			resMap.put("returnCode", returnCode);
			resMap.put("returnMsg", returnMsg);
			return resMap;
		}
	
	
}
