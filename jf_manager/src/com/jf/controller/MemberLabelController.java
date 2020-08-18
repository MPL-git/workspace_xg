package com.jf.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MemberInfoCustom;
import com.jf.entity.MemberInfoCustomExample;
import com.jf.entity.MemberLabel;
import com.jf.entity.MemberLabelCustom;
import com.jf.entity.MemberLabelExample;
import com.jf.entity.MemberLabelRelation;
import com.jf.entity.MemberLabelRelationCustom;
import com.jf.entity.MemberLabelRelationExample;
import com.jf.entity.MemberLabelRule;
import com.jf.entity.MemberLabelRuleCustom;
import com.jf.entity.MemberLabelRuleCustomExample;
import com.jf.entity.MemberLabelRuleExample;
import com.jf.entity.MemberLabelType;
import com.jf.entity.MemberLabelTypeCustom;
import com.jf.entity.MemberLabelTypeCustomExample;
import com.jf.entity.MemberLabelTypeExample;
import com.jf.entity.StaffBean;
import com.jf.service.MemberInfoService;
import com.jf.service.MemberLabelGroupRelationService;
import com.jf.service.MemberLabelGroupService;
import com.jf.service.MemberLabelRelationService;
import com.jf.service.MemberLabelRuleService;
import com.jf.service.MemberLabelService;
import com.jf.service.MemberLabelTypeService;
import com.jf.vo.Page;

@Controller
public class MemberLabelController extends BaseController {
	
	private static final long serialVersionUID = 1L;
		
	@Resource
	private MemberLabelService memberLabelService;
	
	@Resource
	private MemberLabelGroupService memberLabelGroupService;
	
	@Resource
	private MemberLabelGroupRelationService memberLabelGroupRelationService;
	
	@Resource
	private MemberLabelTypeService memberLabelTypeService;
	
	@Resource
	private MemberLabelRelationService memberLabelRelationService;
	
	@Resource
	private MemberLabelRuleService memberLabelRuleService;
	
	@Resource
	private MemberInfoService memberInfoService;
	
	
	//用户标签类型
	@RequestMapping("/memberLabel/typeList.shtml")
	public ModelAndView typeList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/memberLabel/typeList");
		return m;
	}
	
	//用户标签类型数据
	@ResponseBody
	@RequestMapping("/memberLabel/typeListdata.shtml")
	public Map<String, Object> typeListdata(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MemberLabelTypeCustom> dataList = null;
		Integer totalCount = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			MemberLabelTypeCustomExample memberLabelTypeCustomExample=new MemberLabelTypeCustomExample();
			MemberLabelTypeCustomExample.MemberLabelTypeCustomCriteria memberLabelTypeCustomCriteria=memberLabelTypeCustomExample.createCriteria();
			memberLabelTypeCustomCriteria.andDelFlagEqualTo("0");
			memberLabelTypeCustomExample.setOrderByClause("status desc,update_date desc");
			
			if (!StringUtil.isEmpty(request.getParameter("labelTypeName"))) {
				memberLabelTypeCustomCriteria.andLabelTypeNameLike("%"+request.getParameter("labelTypeName")+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("update_begin")) && !StringUtil.isEmpty(request.getParameter("update_end"))) {
				memberLabelTypeCustomCriteria.andUpdateDateBetween(sdf.parse(request.getParameter("update_begin")+" 00:00:00"),sdf.parse(request.getParameter("update_end")+ " 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) {
				String status=request.getParameter("status");
				if (status.equals("0")) {
					memberLabelTypeCustomCriteria.andStatusEqualTo(status);
				}else if (status.equals("1")) {
					memberLabelTypeCustomCriteria.andStatusEqualTo(status);
				}
			}
			memberLabelTypeCustomExample.setLimitSize(page.getLimitSize());
			memberLabelTypeCustomExample.setLimitStart(page.getLimitStart());
			dataList = memberLabelTypeService.selectMemberLabelTypeCustomExample(memberLabelTypeCustomExample);
			totalCount = memberLabelTypeService.countMemberLabelTypeCustomExample(memberLabelTypeCustomExample);
			
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	       //添加或修改用户标签类型
			@RequestMapping(value = "/memberLabel/typeAdd.shtml")
			public ModelAndView addkeywordhomoionym(HttpServletRequest request) {
				String rtPage = "/memberLabel/typeAdd";	
				Map<String, Object> resMap = new HashMap<String, Object>();
                String id=request.getParameter("Id");
                if (id!=null && id!="") {
					MemberLabelType memberLabelType=memberLabelTypeService.selectByPrimaryKey(Integer.valueOf(id));
					resMap.put("memberLabelType", memberLabelType);
					
					MemberLabelExample memberLabelExample=new MemberLabelExample();
					memberLabelExample.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdEqualTo(memberLabelType.getId());
					List<MemberLabel> memberLabelList=memberLabelService.selectByExample(memberLabelExample);
					if (memberLabelList!=null && memberLabelList.size()>0) {
						resMap.put("memberLabelList", JSONArray.fromObject(memberLabelList));
					}
					
				}
                
				return new ModelAndView(rtPage, resMap);
		    }
			
			//保存用户标签类型
			@ResponseBody
			@RequestMapping(value = "/memberLabel/addmemberLabelTyle.shtml")
			public  Map<String, Object> addmemberLabelTyle(HttpServletRequest request,MemberLabelType memberLabelType,String labelName,String ml) {
				Map<String, Object> resMap = new HashMap<String, Object>();					
				String returnCode = "0000";
				String returnMsg = "操作成功！";
				try {
					StaffBean staffBean = this.getSessionStaffBean(request);
					Integer staffId = Integer.valueOf(staffBean.getStaffID());
					Date date = new Date();
					
					if (memberLabelType.getId()==null) {//添加
						memberLabelType.setCreateBy(staffId);
						memberLabelType.setCreateDate(date);
						memberLabelType.setUpdateDate(date);
						memberLabelType.setStatus("1");
						memberLabelType.setDelFlag("0");
						memberLabelTypeService.insertSelective(memberLabelType);
						
						String[] labelNames=labelName.split(",");
						for (String labelNameString : labelNames) {
							 MemberLabel memberLabel=new MemberLabel();
							 memberLabel.setLabelTypeId(memberLabelType.getId());
							 memberLabel.setLabelName(labelNameString);
							 memberLabel.setStatus("0");
							 memberLabel.setCreateBy(staffId);
							 memberLabel.setCreateDate(date);
							 memberLabel.setUpdateDate(date);
							 memberLabel.setDelFlag("0");
							 memberLabelService.insert(memberLabel);
						}
						
					}else {//编辑
						
						 MemberLabelType memberLabelType2=memberLabelTypeService.selectByPrimaryKey(memberLabelType.getId());
						 memberLabelType2.setLabelTypeName(memberLabelType.getLabelTypeName());
						 memberLabelType2.setRemarks(memberLabelType.getRemarks());
						 memberLabelType2.setUpdateBy(staffId);
						 memberLabelType2.setUpdateDate(date);
						 memberLabelTypeService.updateByPrimaryKeySelective(memberLabelType2);
						
						String[] mls = ml.split(",");		
						
						List<Integer> list=new ArrayList<Integer>();
						for(String mlsStr : mls) {
						    String[] memberLabeid = mlsStr.split("_");
						  if (!memberLabeid[0].equals("undefined")) {
							list.add(Integer.valueOf(memberLabeid[0]));		
						  }
						}
						
						MemberLabelExample memberLabelExample=new MemberLabelExample();
						memberLabelExample.createCriteria().andDelFlagEqualTo("0").andIdNotIn(list).andLabelTypeIdEqualTo(memberLabelType.getId());
						List<MemberLabel> memberLabels=memberLabelService.selectByExample(memberLabelExample);
						if (memberLabels.size()>0) {//编辑删除
							for (MemberLabel memberLabel : memberLabels) {
								MemberLabel memberLabel2=memberLabelService.selectByPrimaryKey(memberLabel.getId());
								memberLabel2.setUpdateBy(staffId);
								memberLabel2.setUpdateDate(date);
								memberLabel2.setDelFlag("1");
								memberLabelService.updateByPrimaryKeySelective(memberLabel2);
							}
						}
						
						
						for(String mlsStr : mls) {
							String[] memberLabelTyle = mlsStr.split("_");
							if (!memberLabelTyle[0].equals("undefined")) {//编辑修改
								MemberLabel memberLabel=memberLabelService.selectByPrimaryKey(Integer.valueOf(memberLabelTyle[0]));
								if (memberLabel.getLabelName().equals(memberLabelTyle[1])) {
									continue;
								}else if (!memberLabel.getLabelName().equals(memberLabelTyle[1])) {
									MemberLabel memberLabel1=new MemberLabel();
									memberLabel1.setId(memberLabel.getId());
									memberLabel1.setLabelName(memberLabelTyle[1]);
									memberLabel1.setUpdateBy(staffId);
									memberLabel1.setUpdateDate(date);
									memberLabelService.updateByPrimaryKeySelective(memberLabel1);
									
								}
								
							}else {//编辑添加
								 MemberLabel memberLabel1=new MemberLabel();
								 memberLabel1.setLabelTypeId(memberLabelType.getId());
								 memberLabel1.setLabelName(memberLabelTyle[1]);
								 memberLabel1.setStatus("0");
								 memberLabel1.setCreateBy(staffId);
								 memberLabel1.setCreateDate(date);
								 memberLabel1.setUpdateDate(date);
								 memberLabel1.setDelFlag("0");
								 memberLabelService.insert(memberLabel1);
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
			
			
			//修改用户标签类型状态
			@RequestMapping(value = "/memberLabel/updatememberLabelType.shtml")
			@ResponseBody
			public Map<String, Object> updatememberLabelType(HttpServletRequest request,HttpServletResponse response,String id,String status){
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("returnCode", "0000");
				resMap.put("returnMsg", "成功");
				try {
					String memberLabelTypeid=request.getParameter("id");
					StaffBean staffBean = this.getSessionStaffBean(request);
					int staffId=Integer.valueOf(staffBean.getStaffID());
					
					MemberLabelType memberLabelType=memberLabelTypeService.selectByPrimaryKey(Integer.valueOf(memberLabelTypeid));
					memberLabelType.setUpdateBy(staffId);
					memberLabelType.setUpdateDate(new Date());
					if (status.equals("1")) {
						memberLabelType.setStatus("1");
					}else if (status.equals("0")) {
						memberLabelType.setStatus("0");
					}
					memberLabelTypeService.updateByPrimaryKey(memberLabelType);
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", e.getMessage());
				}
				
				return resMap;
			}
			
			
			
			//用户标签规则
			@RequestMapping("/memberLabel/ruleList.shtml")
			public ModelAndView ruleList(HttpServletRequest request) {
				ModelAndView m = new ModelAndView("/memberLabel/ruleList");
				return m;
			}
			
			//用户标签规则数据
			@ResponseBody
			@RequestMapping("/memberLabel/ruleListdata.shtml")
			public Map<String, Object> ruleListdata(HttpServletRequest request, Page page) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				List<MemberLabelRuleCustom> dataList = null;
				Integer totalCount = 0;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					MemberLabelRuleCustomExample memberLabelRuleCustomExample=new MemberLabelRuleCustomExample();
					MemberLabelRuleCustomExample.MemberLabelRuleCustomCriteria memberLabelRuleCustomCriteria=memberLabelRuleCustomExample.createCriteria();
					memberLabelRuleCustomCriteria.andDelFlagEqualTo("0");
					memberLabelRuleCustomExample.setOrderByClause("update_date desc");
					
					if (!StringUtil.isEmpty(request.getParameter("update_begin")) && !StringUtil.isEmpty(request.getParameter("update_end"))) {
						memberLabelRuleCustomCriteria.andUpdateDateBetween(sdf.parse(request.getParameter("update_begin")+" 00:00:00"),sdf.parse(request.getParameter("update_end")+ " 23:59:59"));
					}
					if (!StringUtil.isEmpty(request.getParameter("status"))) {
						memberLabelRuleCustomCriteria.andMemberLabelStatusEqualTo(request.getParameter("status"));
					}
					if (!StringUtil.isEmpty(request.getParameter("memberlabelTypeName"))) {
						memberLabelRuleCustomCriteria.andMemberlabelTypeNameLikeTo(request.getParameter("memberlabelTypeName"));
					}
					memberLabelRuleCustomExample.setLimitSize(page.getLimitSize());
					memberLabelRuleCustomExample.setLimitStart(page.getLimitStart());
					dataList =memberLabelRuleService.selectMemberLabelRuleCustomExample(memberLabelRuleCustomExample);
					totalCount =memberLabelRuleService.countMemberLabelRuleCustomExample(memberLabelRuleCustomExample); 
					
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return resMap;
			}
			
			
			//获取用户标签
			@RequestMapping(value = "/memberLabel/memberLabelType.shtml")
			@ResponseBody
			public Map<String, Object> getMemberLabelType(HttpServletRequest request) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("returnCode", "0000");
				resMap.put("returnMsg", "成功");
				try {
					if (StringUtil.isEmpty(request.getParameter("memberLabelTypeid"))) {
						resMap.put("returnCode", "9999");
						resMap.put("returnMsg", "失败");
					}else if (!StringUtil.isEmpty(request.getParameter("memberLabelTypeid"))) {
						String memberLabelTypeid = request.getParameter("memberLabelTypeid");
						MemberLabelRuleCustomExample memberLabelRuleCustomExample=new MemberLabelRuleCustomExample();
						memberLabelRuleCustomExample.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdEqualTo(Integer.valueOf(memberLabelTypeid));
						List<MemberLabelRuleCustom> memberLabelRuleCustoms=memberLabelRuleService.selectMemberLabelRuleCustomExample(memberLabelRuleCustomExample);
						List<Integer> lisIntegers=new ArrayList<Integer>();
						if (memberLabelRuleCustoms!=null && memberLabelRuleCustoms.size()>0) {
							for (MemberLabelRuleCustom memberLabelRuleCustom : memberLabelRuleCustoms) {
								lisIntegers.add(memberLabelRuleCustom.getLabelId());
							}
							
							MemberLabelExample memberLabelExample=new MemberLabelExample();
							memberLabelExample.createCriteria().andDelFlagEqualTo("0").andIdNotIn(lisIntegers).andLabelTypeIdEqualTo(Integer.valueOf(memberLabelTypeid));
							List<MemberLabel> memberLabels=memberLabelService.selectByExample(memberLabelExample);
							if (memberLabels!=null && memberLabels.size()>0) {
								resMap.put("memberLabels", memberLabels);
							}
							
						}else {
							MemberLabelExample memberLabelExample=new MemberLabelExample();
							memberLabelExample.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdEqualTo(Integer.valueOf(memberLabelTypeid));
							List<MemberLabel> memberLabels=memberLabelService.selectByExample(memberLabelExample);
							if (memberLabels!=null && memberLabels.size()>0) {
								resMap.put("memberLabels", memberLabels);
							}
							resMap.put("returnCode", "9999");
							resMap.put("returnMsg", "失败");
							
						}					
					}
							
				} catch (Exception e) {
					e.printStackTrace();
				}
				return resMap;
			}
			
			
			//添加或编辑用户标签规则
			@RequestMapping(value = "/memberLabel/ruleAdd.shtml")
			public ModelAndView ruleAdd(HttpServletRequest request) {
				String rtPage = "/memberLabel/ruleAdd";	
				Map<String, Object> resMap = new HashMap<String, Object>();	
				
				/*MemberLabelTypeExample memberLabelTypeExample=new MemberLabelTypeExample();//用户标签类型
				MemberLabelTypeExample.Criteria memberLabelTypeCriteria=memberLabelTypeExample.createCriteria();
				memberLabelTypeCriteria.andDelFlagEqualTo("0");
				List<MemberLabelType> memberLabelTypeList=memberLabelTypeService.selectByExample(memberLabelTypeExample);
				
				List<Integer> typeList=new ArrayList<Integer>();
				for (MemberLabelType memberLabelType : memberLabelTypeList) {
					MemberLabelRuleExample memberLabelRuleExample=new MemberLabelRuleExample();
					memberLabelRuleExample.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdEqualTo(memberLabelType.getId());
					List<MemberLabelRule> memberLabelRules=memberLabelRuleService.selectByExample(memberLabelRuleExample);
					if (memberLabelRules!=null && memberLabelRules.size()>0) {
						List<Integer> labelIdList=new ArrayList<Integer>();
						for (MemberLabelRule memberLabelRule : memberLabelRules) {
							labelIdList.add(memberLabelRule.getLabelId());
						}
						
						MemberLabelExample memberLabelExample=new MemberLabelExample();
						memberLabelExample.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdEqualTo(memberLabelType.getId());
						List<MemberLabel> memberLabels=memberLabelService.selectByExample(memberLabelExample);
						List<Integer> labelIdList1=new ArrayList<Integer>();
						for (MemberLabel memberLabel : memberLabels) {
							labelIdList1.add(memberLabel.getId());
						}
						
						if (labelIdList.size()==labelIdList1.size()){
							
							typeList.add(memberLabelType.getId());
							
					  }
					
					}    
					
				}
				
					MemberLabelTypeCustomExample memberLabelTypeCustomExample=new MemberLabelTypeCustomExample();
					MemberLabelTypeCustomExample.MemberLabelTypeCustomCriteria memberLabelTypeCustomCriteria=memberLabelTypeCustomExample.createCriteria();
					memberLabelTypeCustomCriteria.andDelFlagEqualTo("0");
					if (typeList!=null && typeList.size()>0) {
						memberLabelTypeCustomCriteria.andIdNotIn(typeList);
					}
					List<MemberLabelTypeCustom> memberLabelTypes=memberLabelTypeService.selectMemberLabelTypeCustomExample(memberLabelTypeCustomExample);
					
					resMap.put("memberLabelTypeList", JSONArray.fromObject(memberLabelTypes));		
			 */
				
                String id=request.getParameter("Id");
                if (id!=null && id!="") {
					MemberLabelRule memberLabelRule=memberLabelRuleService.selectByPrimaryKey(Integer.valueOf(id));
					resMap.put("memberLabelRule", memberLabelRule);
					
					/*MemberLabel memberLabel=memberLabelService.selectByPrimaryKey(memberLabelRule.getLabelId());
					resMap.put("memberLabel", memberLabel);*/
					
					MemberLabelExample memberLabelExample=new MemberLabelExample();
					memberLabelExample.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdEqualTo(memberLabelRule.getLabelTypeId());
					List<MemberLabel> memberLabels=memberLabelService.selectByExample(memberLabelExample);
					resMap.put("memberLabelList", memberLabels);
					
					
					MemberLabelTypeCustomExample memberLabelTypeCustomExample1=new MemberLabelTypeCustomExample();
					MemberLabelTypeCustomExample.MemberLabelTypeCustomCriteria memberLabelTypeCustomCriteria1=memberLabelTypeCustomExample1.createCriteria();
					memberLabelTypeCustomCriteria1.andDelFlagEqualTo("0");
					List<MemberLabelTypeCustom> memberLabelTypes1=memberLabelTypeService.selectMemberLabelTypeCustomExample(memberLabelTypeCustomExample1);
					resMap.put("memberLabelTypeList", memberLabelTypes1);		
					
				}else {
					MemberLabelTypeExample memberLabelTypeExample=new MemberLabelTypeExample();//用户标签类型
					MemberLabelTypeExample.Criteria memberLabelTypeCriteria=memberLabelTypeExample.createCriteria();
					memberLabelTypeCriteria.andDelFlagEqualTo("0");
					List<MemberLabelType> memberLabelTypeList=memberLabelTypeService.selectByExample(memberLabelTypeExample);
					
					List<Integer> typeList=new ArrayList<Integer>();
					for (MemberLabelType memberLabelType : memberLabelTypeList) {
						MemberLabelRuleExample memberLabelRuleExample=new MemberLabelRuleExample();
						memberLabelRuleExample.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdEqualTo(memberLabelType.getId());
						List<MemberLabelRule> memberLabelRules=memberLabelRuleService.selectByExample(memberLabelRuleExample);
						if (memberLabelRules!=null && memberLabelRules.size()>0) {
							List<Integer> labelIdList=new ArrayList<Integer>();
							for (MemberLabelRule memberLabelRule : memberLabelRules) {
								labelIdList.add(memberLabelRule.getLabelId());
							}
							
							MemberLabelExample memberLabelExample=new MemberLabelExample();
							memberLabelExample.createCriteria().andDelFlagEqualTo("0").andLabelTypeIdEqualTo(memberLabelType.getId());
							List<MemberLabel> memberLabels=memberLabelService.selectByExample(memberLabelExample);
							List<Integer> labelIdList1=new ArrayList<Integer>();
							for (MemberLabel memberLabel : memberLabels) {
								labelIdList1.add(memberLabel.getId());
							}
							
							if (labelIdList.size()==labelIdList1.size()){
								
								typeList.add(memberLabelType.getId());
								
						  }
						
						}    
						
					}
					
						MemberLabelTypeCustomExample memberLabelTypeCustomExample=new MemberLabelTypeCustomExample();
						MemberLabelTypeCustomExample.MemberLabelTypeCustomCriteria memberLabelTypeCustomCriteria=memberLabelTypeCustomExample.createCriteria();
						memberLabelTypeCustomCriteria.andDelFlagEqualTo("0");
						if (typeList!=null && typeList.size()>0) {
							memberLabelTypeCustomCriteria.andIdNotIn(typeList);
						}
						List<MemberLabelTypeCustom> memberLabelTypes=memberLabelTypeService.selectMemberLabelTypeCustomExample(memberLabelTypeCustomExample);
						
						resMap.put("memberLabelTypeList", JSONArray.fromObject(memberLabelTypes));		
				}
                
				return new ModelAndView(rtPage, resMap);
		    }
			
	
			//保存用户标签规则
			@ResponseBody
			@RequestMapping(value = "/memberLabel/addmemberLabelRule.shtml")
			public  Map<String, Object> addmemberLabelRule(HttpServletRequest request,MemberLabelRule memberLabelRule,String id) {
				Map<String, Object> resMap = new HashMap<String, Object>();					
				String returnCode = "0000";
				String returnMsg = "操作成功！";
				try {
					StaffBean staffBean = this.getSessionStaffBean(request);
					Integer staffId = Integer.valueOf(staffBean.getStaffID());
					Date date = new Date();
					
					if (memberLabelRule.getId()==null) {//添加
						memberLabelRule.setCreateBy(staffId);
						memberLabelRule.setCreateDate(date);
						memberLabelRule.setUpdateBy(staffId);
						memberLabelRule.setUpdateDate(date);
						memberLabelRuleService.insertSelective(memberLabelRule);
							
					}else {//编辑
						  
						
						MemberLabelRule memberLabelRule2=memberLabelRuleService.selectByPrimaryKey(Integer.valueOf(id));
						memberLabelRule2.setUpdateBy(staffId);
						memberLabelRule2.setUpdateDate(date);					
						memberLabelRule2.setDelFlag("1");				       			   
						memberLabelRuleService.updateByPrimaryKey(memberLabelRule2);
						
						
						memberLabelRule.setCreateBy(staffId);
						memberLabelRule.setCreateDate(date);
						memberLabelRule.setUpdateBy(staffId);
						memberLabelRule.setUpdateDate(date);
						memberLabelRuleService.insertSelective(memberLabelRule);
					   
					   MemberLabel memberLabel=memberLabelService.selectByPrimaryKey(memberLabelRule.getLabelId());
					   memberLabel.setUpdateBy(staffId);
					   memberLabel.setUpdateDate(date);
					   memberLabel.setStatus("0");
					   memberLabelService.updateByPrimaryKeySelective(memberLabel);
						
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
			
			
			
			//删除用户标签规则状态
			@RequestMapping(value = "/memberLabel/updatememberLabelRule.shtml")
			@ResponseBody
			public Map<String, Object> updatememberLabelRule(HttpServletRequest request,HttpServletResponse response,String id){
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("returnCode", "0000");
				resMap.put("returnMsg", "成功");
				try {
					String ruleId=request.getParameter("id");
					StaffBean staffBean = this.getSessionStaffBean(request);
					int staffId=Integer.valueOf(staffBean.getStaffID());
					
					MemberLabelRule memberLabelRule=memberLabelRuleService.selectByPrimaryKey(Integer.valueOf(ruleId));
					memberLabelRule.setUpdateBy(staffId);
					memberLabelRule.setUpdateDate(new Date());
					memberLabelRule.setDelFlag("1");
					memberLabelRuleService.updateByPrimaryKeySelective(memberLabelRule);
					
					 MemberLabelRelationExample memberLabelRelationExample=new MemberLabelRelationExample();//同步删除会员资料用户标签列表
					 memberLabelRelationExample.createCriteria().andDelFlagEqualTo("0").andLabelIdEqualTo(memberLabelRule.getLabelId()).andLabelTypeIdEqualTo(memberLabelRule.getLabelTypeId());
			         memberLabelRelationService.deleteByExample(memberLabelRelationExample);
					
				} catch (Exception e) {
					e.printStackTrace();
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", e.getMessage());
				}
				
				return resMap;
			}
			
			
			
			//修改用户标签状态
			@RequestMapping(value = "/memberLabel/updateMemberlabelStatus.shtml")
			@ResponseBody
			public Map<String, Object> updateMemberlabelStatus(HttpServletRequest request,HttpServletResponse response,String id,String status){
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("returnCode", "0000");
				resMap.put("returnMsg", "成功");
				try {
					String ruleId=request.getParameter("id");
					StaffBean staffBean = this.getSessionStaffBean(request);
					int staffId=Integer.valueOf(staffBean.getStaffID());
					
					MemberLabelRule memberLabelRule=memberLabelRuleService.selectByPrimaryKey(Integer.valueOf(ruleId));
			        
					MemberLabel memberLabel=memberLabelService.selectByPrimaryKey(memberLabelRule.getLabelId());
					memberLabel.setUpdateBy(staffId);
					memberLabel.setUpdateDate(new Date());
					if (status.equals("1")) {
						memberLabel.setStatus("1");
					}
					memberLabelService.updateByPrimaryKeySelective(memberLabel);

					
				} catch (NumberFormatException e) {
					e.printStackTrace();
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", e.getMessage());
				}
				
				return resMap;
			}
			
			
			
			
			//查看会员
			@RequestMapping(value = "/memberLabel/seemeMemberIdList.shtml")
			public ModelAndView seemeMemberIdList(HttpServletRequest request) {
				String rtPage = "/memberLabel/getMemberIdList";	
				Map<String, Object> resMap = new HashMap<String, Object>();
                resMap.put("labelRuleId", request.getParameter("labelRuleId"));
                
				return new ModelAndView(rtPage, resMap);
		    }
			
			//会员列表数据
			@ResponseBody
			@RequestMapping("/memberLabel/getMemberIdList.shtml")
			public Map<String, Object> getMemberIdList(HttpServletRequest request, Page page) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				List<MemberLabelRuleCustom> dataList = null;
				Integer totalCount = 0;
				try {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					if(!StringUtils.isEmpty(request.getParameter("labelRuleId")) ) {
						paramMap.put("labelRuleId", request.getParameter("labelRuleId"));
					}
					if(!StringUtils.isEmpty(request.getParameter("memeberId")) ) {
						paramMap.put("memeberId", request.getParameter("memeberId"));
					}
					if(!StringUtils.isEmpty(request.getParameter("nick")) ) {
						paramMap.put("nick", request.getParameter("nick"));
					}
					if(!StringUtils.isEmpty(request.getParameter("createDateBegin")) ) {
						paramMap.put("createDateBegin", request.getParameter("createDateBegin")+" 00:00:00" );
					}
					if(!StringUtils.isEmpty(request.getParameter("createDateEnd")) ) {
						paramMap.put("createDateEnd", request.getParameter("createDateEnd")+" 23:59:59");
					}
					paramMap.put("orderByClause", " a.id desc");
					paramMap.put("limitStart", page.getLimitStart());
					paramMap.put("limitSize", page.getLimitSize());
					dataList = memberLabelRuleService.getMemberLabelList(paramMap);
					totalCount = memberLabelRuleService.getMemberLabelCount(paramMap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resMap.put("Rows", dataList);
				resMap.put("Total", totalCount);
				return resMap;
			}
			
				
}
