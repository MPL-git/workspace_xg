package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.AppealOrder;
import com.jf.entity.AppealOrderCustom;
import com.jf.entity.AppealOrderCustomExample;
import com.jf.entity.AppealOrderExample;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderExample;
import com.jf.entity.Comment;
import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.CustomerServiceOrderCustomExample;
import com.jf.entity.ImpeachHandleLog;
import com.jf.entity.ImpeachHandleLogExample;
import com.jf.entity.ImpeachMember;
import com.jf.entity.ImpeachMemberCustom;
import com.jf.entity.ImpeachMemberCustomExample;
import com.jf.entity.ImpeachMemberProof;
import com.jf.entity.ImpeachMemberProofExample;
import com.jf.entity.InterventionOrderCustom;
import com.jf.entity.InterventionOrderCustomExample;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtStatisticalInfo;
import com.jf.entity.MchtStatisticalInfoExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoCustom;
import com.jf.entity.MemberInfoCustomExample;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderCustom;
import com.jf.entity.SubOrderCustomExample;
import com.jf.entity.SubOrderExample;
import com.jf.entity.SysOrganization;
import com.jf.entity.SysOrganizationExample;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoExample;
import com.jf.entity.SysStaffProductTypeCustom;
import com.jf.entity.SysStaffProductTypeCustomExample;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;
import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;
import com.jf.service.AppealOrderService;
import com.jf.service.BaseService;
import com.jf.service.BusinessCirclesAppealOrderService;
import com.jf.service.CombineOrderService;
import com.jf.service.CommentService;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.ImpeachHandleLogService;
import com.jf.service.ImpeachMemberProofService;
import com.jf.service.ImpeachMemberService;
import com.jf.service.InterventionOrderService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtStatisticalInfoService;
import com.jf.service.MemberInfoService;
import com.jf.service.PlatformContactService;
import com.jf.service.SubOrderService;
import com.jf.service.SysOrganizationService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.service.SysStaffRoleService;
import com.jf.service.SysStatusService;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
public class ImpeachController extends BaseController {
	
	private static final long serialVersionUID = 1L;

	private static final BaseService<AppealOrder, AppealOrderExample> customerServiceRecordsFileService = null;
	
	@Resource
	public ImpeachMemberService impeachMemberService;
	
	@Resource
	public ImpeachHandleLogService impeachHandleLogService;
	
	@Resource
	private ImpeachMemberProofService impeachMemberProofService;
	
	@Resource
	private SysStatusService sysStatusService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private MemberInfoService memberInfoService;
	
	@Resource
	private SysStaffProductTypeService sysstaffproductTypeService;
	
	@Resource
	private PlatformContactService platformContactService;
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Resource
	private BusinessCirclesAppealOrderService businessCirclesAppealOrderService;
	
	@Resource
	private InterventionOrderService interventionOrderService;
	
	@Resource
	private AppealOrderService appealOrderService;
	
	@Resource
	private SysOrganizationService sysOrganizationService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private SysStaffRoleService sysStaffRoleService;
	
	@Resource
	private MchtStatisticalInfoService mchtStatisticalInfoService;

	@Resource
	private CommentService commentService;
	
	
	//我处理的举报
	@RequestMapping(value = "/imPeach/imPeachMemberList.shtml")
	public ModelAndView imPeachMemberList(HttpServletRequest request) {
		  String rtPage = "/imPeach/impeachMemberlist";
		  Map<String,Object> resMap = new HashMap<String,Object>();
		  String imPeachType=request.getParameter("imPeachType");
		  List<SysStatus> impeachListMemberTypeList = DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "TYPE");
		  resMap.put("impeachListMemberTypeList", impeachListMemberTypeList);
		  resMap.put("getCommissionerauditbyList", impeachMemberService.getCommissionerauditbyList());
		  resMap.put("getFwauditbyList", impeachMemberService.getFwauditbyList());
		  resMap.put("getReceiverbyList", impeachMemberService.getReceiverbyList());
		  
		    if (imPeachType.equals("2") || imPeachType.equals("3") || imPeachType.equals("4") || imPeachType.equals("6")) {
		    	String staffID = this.getSessionStaffBean(request).getStaffID();
		    	SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
		    	SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
		    	sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));
		    	List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
		    	resMap.put("sysStaffProductTypeList", JSONArray.fromObject(sysStaffProductTypeList));
		    	
		    	//钟表运营部状态，只获取主营类目为钟表 
				String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
				if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
					String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
					if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
						sysstaffProductTypeexCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
					}
				}
				resMap.put("isCwOrgStatus", isCwOrgStatus);
				
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());
				
				resMap.put("staffId", staffId);
				
				PlatformContactExample platformContactExample=new PlatformContactExample();
				platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId).andContactTypeEqualTo("7");
				List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExample);
				if (platformContacts!=null && platformContacts.size()>0) {
					resMap.put("faWu", true);
				}
				
				/*PlatformContactExample platformContactExample1=new PlatformContactExample();
				platformContactExample1.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId).andContactTypeEqualTo("6");
				List<PlatformContact> platformContactS=platformContactService.selectByExample(platformContactExample1);
				if (platformContactS!=null && platformContactS.size()>0) {
					resMap.put("kefu", true);
				}*/
				SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
				sysStaffInfoExample.createCriteria().andStatusEqualTo("A").andOrgIdEqualTo(36).andStaffIdEqualTo(staffId);
				List<SysStaffInfo> kfStaffInfos=sysStaffInfoService.selectByExample(sysStaffInfoExample);
				if (kfStaffInfos!=null && kfStaffInfos.size()>0) {
					resMap.put("kfStaffInfos", true);
					
				}
				
				
				//获取控建角色
				SysStaffRoleExample sysStaffRoleExample=new SysStaffRoleExample();
				sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(120);
			    List<SysStaffRole> sysStaffRolelist=sysStaffRoleService.selectByExample(sysStaffRoleExample);
			    if (sysStaffRolelist!=null && sysStaffRolelist.size()>0) {
			    	resMap.put("roleId",sysStaffRolelist.get(0).getRoleId());
				}
					
			}
		  resMap.put("imPeachType", imPeachType);
		 return new ModelAndView(rtPage,resMap);
				
	}
	
	    //我处理的举报数据
		@RequestMapping(value = "/imPeach/impeachMemberdata.shtml")
		@ResponseBody
		public Map<String, Object> impeachMemberdata(HttpServletRequest request,Page page) {	
			Map<String,Object> resMap = new HashMap<String,Object>(); 
			Integer totalCount =0;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			try {
				 ImpeachMemberCustomExample impeachMemberCustomExample=new ImpeachMemberCustomExample();
				 ImpeachMemberCustomExample.ImpeachMemberCustomCriteria impeachMemberCustomeCriteria=impeachMemberCustomExample.createCriteria();
				 impeachMemberCustomeCriteria.andDelFlagEqualTo("0");
				 impeachMemberCustomExample.setOrderByClause("imm.create_date desc");
				    String staffID = this.getSessionStaffBean(request).getStaffID();
				    SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
			    	SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
			    	sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));
			    	List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
			    	String sysStaffProductType="";
			    	for (SysStaffProductTypeCustom sysStaffProductTypeCustom : sysStaffProductTypeList) {
						  if ("".equals(sysStaffProductType)) {
							  sysStaffProductType+=sysStaffProductTypeCustom.getProductTypeId().toString();
						}else {
							sysStaffProductType+=","+sysStaffProductTypeCustom.getProductTypeId().toString();
						}
					}
				 if (request.getParameter("imPeachType").equals("1")) {//我处理的举报
					 impeachMemberCustomeCriteria.andCreateReceiverBy(staffId);		
				 }else if (request.getParameter("imPeachType").equals("2")) {//待领取的商家举报
					 impeachMemberCustomeCriteria.andSourceEqualTo("0");
					 impeachMemberCustomeCriteria.andStatusEqualTo("0");
					  
				    	if (!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
				    		impeachMemberCustomeCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replace(";", ","));
						}else if (StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
							impeachMemberCustomeCriteria.andProductTypeIdIn(sysStaffProductType);
						}
				    	
				    	
				 }else if (request.getParameter("imPeachType").equals("3")) {//待初审的商家举报
					 impeachMemberCustomeCriteria.andStatusEqualTo("1");
					   if (!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
				    		impeachMemberCustomeCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replace(";", ","));
						}else if (StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
							impeachMemberCustomeCriteria.andProductTypeIdIn(sysStaffProductType);
						}
					
				}else if (request.getParameter("imPeachType").equals("4") && StringUtil.isEmpty(request.getParameter("status"))) {//待复审的商家举报
					      List<String> statusList=new ArrayList<String>();
					      statusList.add("2");
					      statusList.add("4");
					      statusList.add("5");
					      impeachMemberCustomeCriteria.andStatusIn(statusList);
					     
				 }else if (request.getParameter("imPeachType").equals("5") && StringUtil.isEmpty(request.getParameter("status"))) {//待结案的商家举报
				      List<String> statusList=new ArrayList<String>();
				      statusList.add("4");
				      statusList.add("6");
				      statusList.add("7");
				      impeachMemberCustomeCriteria.andStatusIn(statusList);
				     
			     }else if (request.getParameter("imPeachType").equals("6")) {
			    	 if (!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
				    	 impeachMemberCustomeCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replace(";", ","));
					}
				}
				 if (request.getParameter("imPeachType").equals("1") || request.getParameter("imPeachType").equals("2") || request.getParameter("imPeachType").equals("6")) {
					 if (!StringUtil.isEmpty(request.getParameter("starcreateDate")) && !StringUtil.isEmpty(request.getParameter("endcreateDate"))) {
						 impeachMemberCustomeCriteria.andCreateDateBetween(dateFormat.parse(request.getParameter("starcreateDate")+" 00:00:00"), dateFormat.parse(request.getParameter("endcreateDate")+" 23:59:59"));
					 }	
				}else if (request.getParameter("imPeachType").equals("4")) {
					if (!StringUtil.isEmpty(request.getParameter("starcreateDate")) && !StringUtil.isEmpty(request.getParameter("endcreateDate"))) {
						 impeachMemberCustomeCriteria.andCommissionerAuditDateBetween(dateFormat.parse(request.getParameter("starcreateDate")+" 00:00:00"), dateFormat.parse(request.getParameter("endcreateDate")+" 23:59:59"));
					 }	
				}else if (request.getParameter("imPeachType").equals("5")) {
					if (!StringUtil.isEmpty(request.getParameter("starcreateDate")) && !StringUtil.isEmpty(request.getParameter("endcreateDate"))) {
						 impeachMemberCustomeCriteria.andFwAuditDateBetween(dateFormat.parse(request.getParameter("starcreateDate")+" 00:00:00"), dateFormat.parse(request.getParameter("endcreateDate")+" 23:59:59"));
					 }
				}
				 if (!StringUtil.isEmpty(request.getParameter("code"))) {
					 impeachMemberCustomeCriteria.andCodeEqualTo(request.getParameter("code"));
				 }
				 if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
					 impeachMemberCustomeCriteria.andMchtCode(request.getParameter("mchtCode"));
				 }
				 if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
					 impeachMemberCustomeCriteria.andMchtNameLikeTo(request.getParameter("mchtName"));
				 }
				
				 if (!StringUtil.isEmpty(request.getParameter("type"))) {
					 impeachMemberCustomeCriteria.andTypeEqualTo(request.getParameter("type"));
				 }
				 if (!StringUtil.isEmpty(request.getParameter("scene"))) {
					 impeachMemberCustomeCriteria.andSceneEqualTo(request.getParameter("scene"));
				 }
				 if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
					 impeachMemberCustomeCriteria.andSubOrderCode(request.getParameter("subOrderCode"));
				 }
				 if (!StringUtil.isEmpty(request.getParameter("status"))) {
					 impeachMemberCustomeCriteria.andStatusEqualTo(request.getParameter("status"));
				 }
				 if (!StringUtil.isEmpty(request.getParameter("source"))) {
					 if (request.getParameter("source").equals("0")) {
						 impeachMemberCustomeCriteria.andSourceEqualTo("0");
					 }else if (request.getParameter("source").equals("1")) {
						 impeachMemberCustomeCriteria.andSourceEqualTo("1");
						
					}
				 }
				 if (!StringUtil.isEmpty(request.getParameter("commissionerAuditBy"))) {
					 impeachMemberCustomeCriteria.andCommissionerAuditBy(Integer.valueOf(request.getParameter("commissionerAuditBy")));
				 }
				 if (!StringUtil.isEmpty(request.getParameter("fwAuditBy"))) {
					 impeachMemberCustomeCriteria.andFwAuditBy(Integer.valueOf(request.getParameter("fwAuditBy")));
				 }
				 if (!StringUtil.isEmpty(request.getParameter("memberId"))) {
					 impeachMemberCustomeCriteria.andMemberIdsLike("%"+request.getParameter("memberId")+"%");
				 }
				 totalCount = impeachMemberService.countByImpeachMemberCustomExample(impeachMemberCustomExample);
				 impeachMemberCustomExample.setLimitStart(page.getLimitStart());
				 impeachMemberCustomExample.setLimitSize(page.getLimitSize());
				 List<ImpeachMemberCustom> impeachMemberCustoms = impeachMemberService.selectByImpeachMemberCustomExample(impeachMemberCustomExample);
				
				resMap.put("Rows", impeachMemberCustoms);
				resMap.put("Total", totalCount);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resMap;
		}
		
		/**
		 * 获取举报场景
		 * @param request
		 * @param response
		 * @param paramMap
		 * @return
		 */
		@RequestMapping(value = "/impeach/getimpeachMember.shtml")
		@ResponseBody
		public Map<String, Object> getimpeachMember(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "成功");
			try {
				String type = request.getParameter("type");
				List<SysStatus> sysStatusList=null;
				SysStatusExample example = new SysStatusExample();
				SysStatusExample.Criteria criteria = example.createCriteria();
				criteria.andTableNameEqualTo("BU_IMPEACH_MEMBER");
				if (type.equals("1")) {		
					criteria.andColNameEqualTo("SCENE1");
					sysStatusList = sysStatusService.selectByExample(example);
					resMap.put("sceneList", sysStatusList);
				}else if (type.equals("2")) {
					criteria.andColNameEqualTo("SCENE2");
					sysStatusList = sysStatusService.selectByExample(example);
					resMap.put("sceneList", sysStatusList);
				}else if (type.equals("3")) {
					criteria.andColNameEqualTo("SCENE3");
					 sysStatusList = sysStatusService.selectByExample(example);
					resMap.put("sceneList", sysStatusList);
				}else if (StringUtil.isEmpty(type)) {
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resMap;
		}
		
		
		/**
		 * 添加举报界面
		 * @param request
		 * @param response
		 * @param paramMap
		 * @return
		 */
		@RequestMapping(value = "/impeach/addimpeachMember.shtml")
		public ModelAndView addimpeachMember(HttpServletRequest request) {
			String rtPage = "/imPeach/addimpeachMember";
			Map<String, Object> resMap = new HashMap<String, Object>();
			List<SysStatus> impeachListMemberTypeList = DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "TYPE");
			resMap.put("impeachListMemberTypeList", impeachListMemberTypeList);
			
			List<SysStatus> caseCloselist = DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "CASE_CLOSE_DESC");
			resMap.put("caseCloselist", caseCloselist);
			
			return new ModelAndView(rtPage,resMap);
		}
		
		
		       //保存举报单
				@ResponseBody
				@RequestMapping(value = "/imPeach/editimPeach.shtml")
				public  Map<String, Object> editimPeach(HttpServletRequest request,ImpeachMember impeachMember,String commissionerInnerRemarks,String orde,String needLimit,String caseCloseDesc1,String filePathList,String limitMemberAction) {
					Map<String, Object> resMap = new HashMap<String, Object>();					
					String returnCode = "0000";
					String returnMsg = "操作成功！";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						Integer staffId = Integer.valueOf(staffBean.getStaffID());
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
						String batchno = format.format(date);
						int x=(int)(Math.random()*900000)+100000;  
						String serial = batchno + x; //时间后面加六位随机数;
						if (impeachMember.getId()==null) {
							List<String> suborderCodelList=new ArrayList<String>();
							String[] suborder=orde.split(",");
							for (String suborderCode : suborder) {
								SubOrderExample subOrderExample=new SubOrderExample();
								subOrderExample.createCriteria().andDelFlagEqualTo("0").andSubOrderCodeEqualTo(suborderCode);
								int subCuont=subOrderService.countByExample(subOrderExample);
								if (subCuont>0) {
									suborderCodelList.add(suborderCode);						
								}else if (subCuont<=0) {									
									resMap.put("returnCode", "9999");
									resMap.put("returnMsg", "相关订单中添加有误的子订单号,请重新添加~!");
									return resMap;
								}
							}
							SubOrderExample subOrderExample=new SubOrderExample();
							subOrderExample.createCriteria().andDelFlagEqualTo("0").andSubOrderCodeIn(suborderCodelList);
							List<SubOrder> subOrders=subOrderService.selectByExample(subOrderExample);
								   List<Integer> combineOrderid=new ArrayList<Integer>();
								   String subOrderS="";
							   for (SubOrder subOrder : subOrders) {
								   combineOrderid.add(subOrder.getCombineOrderId());
								   if ("".equals(subOrderS)) {
									   subOrderS+=subOrder.getId().toString();
								   }else {
									
									   subOrderS+=","+subOrder.getId().toString();
								}
								
							    }
								CombineOrderExample combineOrderExample=new CombineOrderExample();
								combineOrderExample.createCriteria().andDelFlagEqualTo("0").andIdIn(combineOrderid);
								List<CombineOrder> combineOrders=combineOrderService.selectByExample(combineOrderExample);
								List<Integer> memberidlisIntegers=new ArrayList<Integer>();
								 String memberID="";
								if (combineOrders!=null && combineOrders.size()>0) {
									for (CombineOrder combineOrderids : combineOrders) {
										memberidlisIntegers.add(combineOrderids.getMemberId());
										if ("".equals(memberID)) {
											memberID+=combineOrderids.getMemberId().toString();
										}else {
											memberID+=","+combineOrderids.getMemberId().toString();
										}
									}
								}
								if (needLimit.equals("1")) {
									impeachMember.setStatus("2");
									
								}else if (needLimit.equals("0")) {
									impeachMember.setStatus("7");
									impeachMember.setCaseCloseDesc(caseCloseDesc1);
									impeachMember.setEndAuditBy(staffId);
									impeachMember.setEndAuditDate(new Date());
									impeachMember.setEndInnerRemarks(commissionerInnerRemarks);
									
									MemberInfoExample memberInfoExample =new MemberInfoExample();
									memberInfoExample.createCriteria().andDelFlagEqualTo("0").andIdIn(memberidlisIntegers);
									List<MemberInfo> memberInfos=memberInfoService.selectByExample(memberInfoExample);
									if (memberInfos!=null && memberInfos.size()>0) {
										for (MemberInfo memberInfo : memberInfos) {
											 MemberInfo memberInfo2=memberInfoService.selectByPrimaryKey(memberInfo.getId());
											 memberInfo2.setUpdateBy(staffId);
											 memberInfo2.setUpdateDate(date);
											 memberInfo2.setStatus("P");
											 memberInfoService.updateByPrimaryKey(memberInfo2);
											 
										}
									}
																								
								}
								
								impeachMember.setCode(serial);
								impeachMember.setMemberIds(memberID);
								impeachMember.setSubOrderIds(subOrderS);
								impeachMember.setCreateBy(staffId);
								impeachMember.setCreateDate(date);
								impeachMember.setSource("1");
								impeachMember.setDelFlag("0");
								impeachMemberService.insertSelective(impeachMember);
								
								String[] filePathListS=filePathList.split(",");									
								for (String filePath : filePathListS) {			
									ImpeachMemberProof impeachMemberProof=new ImpeachMemberProof();
									impeachMemberProof.setCreateBy(staffId);
									impeachMemberProof.setCreateDate(date);
									impeachMemberProof.setImpeachMemberId(impeachMember.getId());
									impeachMemberProof.setFileName(filePath.substring(0,filePath.indexOf("/")));
									impeachMemberProof.setFilePath(filePath.substring(filePath.indexOf("/")));
									impeachMemberProof.setIsAdd("0");
									impeachMemberProof.setUploadSource("1");
									impeachMemberProof.setDelFlag("0");
									impeachMemberProofService.insert(impeachMemberProof);
									
									
								}
																													
								ImpeachHandleLog impeachHandleLog=new ImpeachHandleLog();
								impeachHandleLog.setCreateBy(staffId);
								impeachHandleLog.setCreateDate(date);
								impeachHandleLog.setImpeachMemberId(impeachMember.getId());
								if (impeachMember.getStatus().equals("2")) {
									impeachHandleLog.setStatus("1");
								}else{
									impeachHandleLog.setStatus(impeachMember.getStatus());
									
								}
								impeachHandleLog.setContent(impeachMember.getCommissionerInnerRemarks());
								impeachHandleLog.setDelFlag("0");
								impeachHandleLogService.insert(impeachHandleLog);
								
							
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
				
				
				// 保存客服专员领取人
				@RequestMapping(value = "/imPeach/imPeachkf.shtml")
				@ResponseBody
				public Map<String, Object> imPeachkf(HttpServletRequest request,Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "成功");
					try {
						String id = request.getParameter("id");
						ImpeachMember impeachMember = impeachMemberService.selectByPrimaryKey(Integer.parseInt(id));
						impeachMember.setCommissionerAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						impeachMember.setStatus("1");
						impeachMember.setReceiverDate(new Date());
						impeachMemberService.updateByPrimaryKey(impeachMember);	
						//插入举报日志
						ImpeachHandleLog impeachHandleLog=new ImpeachHandleLog();
					    impeachHandleLog.setImpeachMemberId(Integer.valueOf(id));
					    impeachHandleLog.setStatus("2");
					    impeachHandleLog.setDelFlag("0");
					    impeachHandleLog.setCreateDate(new Date());
					    impeachHandleLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					    impeachHandleLogService.insert(impeachHandleLog);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return resMap;
				}
				
				
				//保存法务领取人
				@RequestMapping(value = "/imPeach/imPeachfawu.shtml")
				@ResponseBody
				public Map<String, Object> imPeachfawu(HttpServletRequest request,Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "成功");
					try {
						String id = request.getParameter("id");
						ImpeachMember impeachMember = impeachMemberService.selectByPrimaryKey(Integer.parseInt(id));
						impeachMember.setFwAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						impeachMember.setReceiverDate(new Date());
						impeachMemberService.updateByPrimaryKey(impeachMember);
						resMap.put("fawuStaffName", this.getSessionStaffBean(request).getStaffName());
					} catch (Exception e) {
						e.printStackTrace();
					}
					return resMap;
				}
	

				/**
				 * 查看详情
				 * @param request
				 * @param response
				 * @param paramMap
				 * @return
				 */
				@RequestMapping(value = "/imPeach/impeachExamine.shtml")
				public ModelAndView impeachExamine(HttpServletRequest request) {
					String rtPage = "/imPeach/seeimPeachExamine";
					Map<String, Object> resMap = new HashMap<String, Object>();
				    
					int impeachmemberId=Integer.valueOf(request.getParameter("id"));
					ImpeachMemberCustom impeachMemberCustom=impeachMemberService.selectByImpeachMemberCustomPrimaryKey(impeachmemberId);
					resMap.put("impeachMemberCustom", impeachMemberCustom);
					
					String[] subOrderIds=impeachMemberCustom.getSubOrderIds().split(",");
					List<Integer> suborderidList=new ArrayList<Integer>();
					for (String suborderid : subOrderIds) {
						suborderidList.add(Integer.valueOf(suborderid));
					}
					SubOrderCustomExample subOrderCustomExample=new SubOrderCustomExample();
					subOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andIdIn(suborderidList);
					List<SubOrderCustom> subOrders=subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
					resMap.put("subOrders", subOrders);
					
					ImpeachHandleLogExample impeachHandleLogExample=new ImpeachHandleLogExample();
					impeachHandleLogExample.createCriteria().andDelFlagEqualTo("0").andImpeachMemberIdEqualTo(impeachMemberCustom.getId());
					impeachHandleLogExample.setOrderByClause("create_date desc");
					List<ImpeachHandleLog> impeachHandleLogs=impeachHandleLogService.selectByExample(impeachHandleLogExample);
					resMap.put("impeachHandleLogs", impeachHandleLogs);
								
					String[] MemberIds=impeachMemberCustom.getMemberIds().split(",");
					List<Integer> MemberIdsList=new ArrayList<Integer>();
					for (String MemberId : MemberIds) {
						MemberIdsList.add(Integer.valueOf(MemberId));
					}
					
					MemberInfoCustomExample memberInfoCustomExample=new MemberInfoCustomExample();
					memberInfoCustomExample.createCriteria().andDelFlagEqualTo("0").andIdIn(MemberIdsList);
					List<MemberInfoCustom> memberInfoCustoms=memberInfoService.selectMemberIdByExample(memberInfoCustomExample);
					resMap.put("memberInfoCustoms", memberInfoCustoms);
					
				    StaffBean staffBean = this.getSessionStaffBean(request); 
				   
					SysStaffInfo staffInfo=sysStaffInfoService.selectByPrimaryKey(impeachMemberCustom.getCreateBy());
					if (impeachMemberCustom.getSource().equals("1")) {
						resMap.put("StaffID", staffInfo.getStaffId());
						resMap.put("Name", staffInfo.getStaffName());
						resMap.put("MobilePhone", staffInfo.getMobilePhone());	
						
						 SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
					     sysOrganizationExample.createCriteria().andOrgIdEqualTo(staffInfo.getOrgId()).andStatusEqualTo("A");
					     List<SysOrganization> sysOrganizations=sysOrganizationService.selectByExample(sysOrganizationExample);
					     if (sysOrganizations.size()>0) {
					    	 resMap.put("OrgName", sysOrganizations.get(0).getOrgName());
						 }
					}
					
				   
				   MchtInfoCustom mchtInfoCustom=mchtInfoService.selectMchtInfoCustomById(impeachMemberCustom.getMchtId());
				   resMap.put("mchtInfoCustom", mchtInfoCustom);
				   
				   if (impeachMemberCustom.getSource().equals("0")) {
					   MchtStatisticalInfoExample mchtStatisticalInfoExample=new MchtStatisticalInfoExample();
					   mchtStatisticalInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(impeachMemberCustom.getMchtId());
					   List<MchtStatisticalInfo> mchtStatisticalInfos=mchtStatisticalInfoService.selectByExample(mchtStatisticalInfoExample);
					   if(mchtStatisticalInfos!=null && mchtStatisticalInfos.size()>0){
						   resMap.put("mchtStatisticalInfos", mchtStatisticalInfos.get(0));
					   }

					
				   }
				   
				    List<SysStatus> caseCloselist = DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "CASE_CLOSE_DESC");
					resMap.put("caseCloselist", caseCloselist);
					
					 List<SysStatus> rejectReasonList = DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "REJECT_REASON");
				     resMap.put("rejectReasonList", rejectReasonList);
				     
				    ImpeachMemberProofExample impeachMemberProofExample=new ImpeachMemberProofExample();
				    impeachMemberProofExample.createCriteria().andDelFlagEqualTo("0").andImpeachMemberIdEqualTo(impeachMemberCustom.getId());
				    List<ImpeachMemberProof> impeachMemberProofs=impeachMemberProofService.selectByExample(impeachMemberProofExample);
				    resMap.put("impeachMemberProofs", impeachMemberProofs);
				    
				   
				    PlatformContactExample platformContactExample=new PlatformContactExample();
					platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffBean.getStaffID())).andContactTypeEqualTo("7");
					List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExample);
					if (platformContacts!=null && platformContacts.size()>0) {
						resMap.put("fW", true);
					}
				    
	
				     String imPeachType=request.getParameter("imPeachType");
				     resMap.put("imPeachType", imPeachType);
				     if (imPeachType.equals("6") && (impeachMemberCustom.getStatus().equals("7") || impeachMemberCustom.getStatus().equals("1") || impeachMemberCustom.getStatus().equals("2") || impeachMemberCustom.getStatus().equals("3") || impeachMemberCustom.getStatus().equals("4") || impeachMemberCustom.getStatus().equals("5") || impeachMemberCustom.getStatus().equals("6"))) {
				    	  rtPage = "/imPeach/seeimPeachExamine";
					 }
				     /*if (imPeachType.equals("1") && (impeachMemberCustom.getStatus().equals("2") || impeachMemberCustom.getStatus().equals("3") || impeachMemberCustom.getStatus().equals("4") || impeachMemberCustom.getStatus().equals("7") || impeachMemberCustom.getStatus().equals("8"))) {
				    	  rtPage = "/imPeach/imPeachExamine";
					 }*/
				     if (imPeachType.equals("1") || imPeachType.equals("3") && (impeachMemberCustom.getStatus().equals("1") || impeachMemberCustom.getStatus().equals("5") || impeachMemberCustom.getStatus().equals("6"))) {
				    	  rtPage = "/imPeach/imPeachExamine";
					 }
				    
					 Integer staffId = Integer.valueOf(staffBean.getStaffID());
				     if (impeachMemberCustom.getStatus().equals("1") && !impeachMemberCustom.getCommissionerAuditBy().equals(staffId)) {
			
				    	 rtPage = "/imPeach/seeimPeachExamine";
					 }
				     
				     if ((imPeachType.equals("4") && impeachMemberCustom.getStatus().equals("5")) || (imPeachType.equals("5") && impeachMemberCustom.getStatus().equals("6"))) {
				    	 rtPage = "/imPeach/seeimPeachExamine";
					 }
				     if (imPeachType.equals("4") || imPeachType.equals("5")) {
				    	 rtPage = "/imPeach/imPeachExamine";
					 }
				     
				     if (imPeachType.equals("1") && impeachMemberCustom.getStatus().equals("2")) {
				    	 rtPage = "/imPeach/seeimPeachExamine";
					 }
				     
					return new ModelAndView(rtPage,resMap);
				}
				
				
				
				//获取会员订单数据
				@ResponseBody
				@RequestMapping(value = "/imPeach/subOrderListdata.shtml")
				public ModelAndView subOrderListdata(HttpServletRequest request){
					    ModelAndView m = new ModelAndView();
					     String memberId=request.getParameter("memberId");
					     System.out.println(memberId);
			             String state=request.getParameter("state");
			             
			               if (state.equals("1")) {   
			            	   String suborderList="";
			            	   SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
			            	   SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
			            	   subOrderCustomCriteria.andDelFlagEqualTo("0").andCreateByEqualTo(Integer.valueOf(memberId));
			            	   List<SubOrderCustom> subOrderCustoms=subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
			            	   
			            	   for (SubOrderCustom subOrderCustom : subOrderCustoms) {
			            		   if("".equals(suborderList)) {
			            			   suborderList = subOrderCustom.getId().toString();
			            		   }else {
			            			   suborderList += "," + subOrderCustom.getId().toString();
			            		   }	
			            	   }								 
			            	   
			            	   m.addObject("suborderList", suborderList);	 
			            	   m.setViewName("/imPeach/suborderList");	
							
						}
					
					if (state.equals("2")) {
						CustomerServiceOrderCustomExample customerServiceOrderCustomExample=new CustomerServiceOrderCustomExample();
						CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria customCriteria=customerServiceOrderCustomExample.createCriteria();
						customCriteria.andDelFlagEqualTo("0").andCreateByEqualTo(Integer.valueOf(memberId));
						List<CustomerServiceOrderCustom> customerServiceOrderCustoms=customerServiceOrderService.selectCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
						String customerServiceOrderlist="";
						if (customerServiceOrderCustoms.size()>0) {
							for (CustomerServiceOrderCustom customerServiceOrderCustom : customerServiceOrderCustoms) {
								if ("".equals(customerServiceOrderlist)) {
									customerServiceOrderlist=customerServiceOrderCustom.getId().toString();
								}else {
									customerServiceOrderlist += "," + customerServiceOrderCustom.getId().toString();
								}	
								
								
							}
						}
						
						List<Map<String, Object>> getproStatuslist =businessCirclesAppealOrderService.getproStatus(memberId);
						
						m.addObject("customerServiceOrderlist",customerServiceOrderlist);
						m.addObject("proStatuslist",getproStatuslist);
						m.setViewName("/businessCirclesAppealOrder/customerServiceOrderList");	
						
					}
				
				
				if (state.equals("3")) {
					InterventionOrderCustomExample interventionOrderCustomExample=new InterventionOrderCustomExample();
					InterventionOrderCustomExample.InterventionOrderCustomCriteria interventionOrderCustomCriteria=interventionOrderCustomExample.createCriteria();
					interventionOrderCustomCriteria.andDelFlagEqualTo("0").andCreateByEqualTo(Integer.valueOf(memberId));
					List<InterventionOrderCustom> interventionOrderCustoms=interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
					String interventionOrderList="";
					if (interventionOrderCustoms.size()>0) {
						for (InterventionOrderCustom interventionOrderCustom : interventionOrderCustoms) {
							if ("".equals(interventionOrderList)) {
								interventionOrderList=interventionOrderCustom.getId().toString();
							}else {
								interventionOrderList += "," + interventionOrderCustom.getId().toString();
							}	
							
							
						}
					}
					
					List<Map<String, Object>> statuslist =businessCirclesAppealOrderService.getStatus(memberId);
					
					m.addObject("interventionOrderList",interventionOrderList);
					m.addObject("statuslist",statuslist);
					m.setViewName("/businessCirclesAppealOrder/interventionOrderList");	
					
				}
			
			
			if (state.equals("4")) {		
				AppealOrderCustomExample appealOrderCustomExample=new AppealOrderCustomExample();
				AppealOrderCustomExample.AppealOrderCustomCriteria appealOrderCustomCriteria=appealOrderCustomExample.createCriteria();
				appealOrderCustomCriteria.andDelFlagEqualTo("0").andCreateByEqualTo(Integer.valueOf(memberId));
				List<AppealOrderCustom> appealOrderCustoms=appealOrderService.selectAppealOrderCustomByExample(appealOrderCustomExample);		 
				String appealOrderList="";
				if (appealOrderCustoms.size()>0) {
					for (AppealOrderCustom appealOrderCustom : appealOrderCustoms) {
						if ("".equals(appealOrderList)) {
							appealOrderList=appealOrderCustom.getId().toString();
						}else {
							appealOrderList += "," + appealOrderCustom.getId().toString();
						}	
						
						
					}
				}
				
				List<Map<String, Object>> statuslist =businessCirclesAppealOrderService.getappealOrderstatus(memberId);
				
				m.addObject("appealOrderList",appealOrderList);
				m.addObject("statuslist",statuslist);
				m.setViewName("/businessCirclesAppealOrder/appealOrderList");	
			}
			
			if (state.equals("5")) {
				MemberInfoCustom memberInfoCustom=memberInfoService.selectMemberInfoCustomByPrimaryKey(Integer.valueOf(memberId));
				m.addObject("memberInfoCustom",memberInfoCustom);
				m.setViewName("/imPeach/detail");	
			}
					
			return m;
					
		}
				
				//法务复审
				@ResponseBody
				@RequestMapping("/imPeach/fwupdateStatus.shtml")
				public Map<String, Object> fwupdateStatus(HttpServletRequest request) {
					Map<String, Object> map = new HashMap<String, Object>();
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
					    String id=request.getParameter("id");
					    String status=request.getParameter("status");
					    String fwRejectReason=request.getParameter("fwRejectReason");
					    String fwInnerRemarks=request.getParameter("fwInnerRemarks");
					    
					    ImpeachMember impeachMember=impeachMemberService.selectByPrimaryKey(Integer.valueOf(id));
					    impeachMember.setStatus(status);
					    if (!StringUtil.isEmpty(fwRejectReason)) {
					    	impeachMember.setFwRejectReason(fwRejectReason);
						}else if (!StringUtil.isEmpty(fwInnerRemarks)) {
							impeachMember.setFwInnerRemarks(fwInnerRemarks);
						}else {
							impeachMember.setFwInnerRemarks(null);
						}
					    impeachMember.setFwAuditDate(new Date()); 
					    impeachMember.setFwAuditBy(staffId);
					    impeachMemberService.updateByPrimaryKey(impeachMember);
					    
					    ImpeachHandleLog impeachHandleLog=new ImpeachHandleLog();
					    impeachHandleLog.setImpeachMemberId(Integer.valueOf(id));
					    if (status.equals("4")) {
					    	impeachHandleLog.setStatus("5");
							if (!StringUtil.isEmpty(fwInnerRemarks)) {
								impeachHandleLog.setContent("内部备注："+fwInnerRemarks);
							}
						}else if (status.equals("5")) {
							impeachHandleLog.setStatus("6");
							impeachHandleLog.setContent("驳回原因："+fwRejectReason);
						}
					    impeachHandleLog.setDelFlag("0");
					    impeachHandleLog.setCreateDate(new Date());
					    impeachHandleLog.setCreateBy(staffId);
					    impeachHandleLogService.insert(impeachHandleLog);
						map.put("code", "200");
					} catch (ArgException e) {
						e.printStackTrace();
						map.put("code", "999");
						map.put("message", e.getMessage());
					} catch (Exception e) {
						e.printStackTrace();
						map.put("code", "999");
					}
					return map;
				}
				
				
				
				//客服专员审核
				@ResponseBody
				@RequestMapping("/imPeach/kfupdateStatus.shtml")
				public Map<String, Object> kfupdateStatus(HttpServletRequest request) {
					Map<String, Object> map = new HashMap<String, Object>();
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
					    String id=request.getParameter("id");
					    String status1=request.getParameter("status1");
					    String needLimit=request.getParameter("needLimit");
					    String limitMemberAction=request.getParameter("limitMemberAction");
					    String caseCloseDesc=request.getParameter("caseCloseDesc");
					    String caseCloseDescS=request.getParameter("caseCloseDescS");
					    String rejectReason=request.getParameter("rejectReason");
					    String commissionerInnerRemarks=request.getParameter("commissionerInnerRemarks");
					    String status156=request.getParameter("status156");
					    String filePathList=request.getParameter("filePathList");
					    String source=request.getParameter("source");
					    
					    if (needLimit.equals("0") || needLimit.equals("1")) {
							ImpeachMember impeachMember=impeachMemberService.selectByPrimaryKey(Integer.valueOf(id));
							
							String[] MemberIds=impeachMember.getMemberIds().split(",");
							List<Integer> MemberIdsList=new ArrayList<Integer>();
							for (String MemberId : MemberIds) {
								MemberIdsList.add(Integer.valueOf(MemberId));
							}
							
							MemberInfoExample memberInfoExample =new MemberInfoExample();
							memberInfoExample.createCriteria().andDelFlagEqualTo("0").andIdIn(MemberIdsList);
							List<MemberInfo> memberInfos=memberInfoService.selectByExample(memberInfoExample);
							if (memberInfos!=null && memberInfos.size()>0) {
								for (MemberInfo memberInfo : memberInfos) {
									 MemberInfo memberInfo2=memberInfoService.selectByPrimaryKey(memberInfo.getId());
									 memberInfo2.setUpdateBy(staffId);
									 memberInfo2.setUpdateDate(new Date());
									 if (needLimit.equals("0")) {			
										 memberInfo2.setStatus("P");
									 }/*else if (needLimit.equals("1")) {
										 memberInfo2.setLimitFunction(limitMemberAction);
									}*/
									 memberInfoService.updateByPrimaryKey(memberInfo2);
									 
								}
							}
						}
					    
					    ImpeachMember impeachMember=impeachMemberService.selectByPrimaryKey(Integer.valueOf(id));
					    if (status1!=null) {
					    	if (status1.equals("2") && needLimit.equals("0")) {
					    		impeachMember.setStatus("7");
								//若果该举报单存在评价单ID,隐藏该评论
								if(!StringUtils.isEmpty(impeachMember.getCommentId())){
									Comment comment = new Comment();
									comment.setId(impeachMember.getCommentId());
									comment.setIsShow("0");
									commentService.updateByPrimaryKeySelective(comment);
								}
					    	}else {
					    		impeachMember.setStatus(status1);
					    		Date date=new Date();
					    		Calendar calendar = Calendar.getInstance();
					    		calendar.setTime(date);
					    		calendar.add(Calendar.DAY_OF_MONTH, +7);//今天的时间加7天
					    		date = calendar.getTime();
					    		impeachMember.setLastEditDate(date);
					    	}
							  	
						}
					    
					   if (source!=null && !source.equals("0")) {		
						   if(status156.equals("5") || status156.equals("6")){
							   impeachMember.setStatus("2");
						   }
					   }
					    impeachMember.setNeedLimit(needLimit);
					    impeachMember.setLimitMemberAction(limitMemberAction);
					    impeachMember.setRejectReason(rejectReason);
					    if (!StringUtil.isEmpty(commissionerInnerRemarks)) {
					    	impeachMember.setCommissionerInnerRemarks(commissionerInnerRemarks);
						}else {
							impeachMember.setCommissionerInnerRemarks(null);
						}
					    if (!StringUtil.isEmpty(caseCloseDesc)) {
					    	impeachMember.setCaseCloseDesc(caseCloseDesc);
						}
					    if (!StringUtil.isEmpty(caseCloseDescS)) {
					    	impeachMember.setCaseCloseDesc(caseCloseDescS);
						}
					    impeachMember.setCommissionerAuditBy(staffId);
					    impeachMember.setCommissionerAuditDate(new Date());
					    impeachMemberService.updateByPrimaryKey(impeachMember);
					    
					    
					 if (status1!=null) {
					    ImpeachHandleLog impeachHandleLog=new ImpeachHandleLog();
					    impeachHandleLog.setImpeachMemberId(Integer.valueOf(id));
					    	if (status1.equals("2")) {
					    		impeachHandleLog.setStatus("4");
					    		if (!StringUtil.isEmpty(commissionerInnerRemarks)) {
					    			impeachHandleLog.setContent("内部备注："+commissionerInnerRemarks);
					    		}
					    		
					    	}else if (status1.equals("3")) {
					    		impeachHandleLog.setStatus("3");
					    		if (!StringUtil.isEmpty(commissionerInnerRemarks)) {
					    			impeachHandleLog.setContent("内部备注："+commissionerInnerRemarks);
					    		}
					    		
					    		if (rejectReason.equals("1")) {
					    			impeachHandleLog.setContent("驳回原因：您好，您提供的录音不清晰，请重新提供。"+"\n"+"内部备注："+commissionerInnerRemarks);					
					    		}else if (rejectReason.equals("2")) {
					    			impeachHandleLog.setContent("驳回原因：您好，您提供的照片不清晰，请重新提供。"+"\n"+"内部备注："+commissionerInnerRemarks);
					    		}else if (rejectReason.equals("3")) {
					    			impeachHandleLog.setContent("驳回原因：您好，您提供的视频未能落实客户寄回的快递单号信息，请重新提供。"+"\n"+"内部备注："+commissionerInnerRemarks);
					    		}else if (rejectReason.equals("4")) {
					    			impeachHandleLog.setContent("驳回原因：您好，您提供的视频不完整，请重新提供。"+"\n"+"内部备注："+commissionerInnerRemarks);
					    		}
					    	}
					    	
					    	impeachHandleLog.setDelFlag("0");
					    	impeachHandleLog.setCreateBy(staffId);
					    	impeachHandleLog.setCreateDate(new Date());
					    	impeachHandleLogService.insert(impeachHandleLog);
					    		    	
							
						}
					   
					     //是否新增凭证
					     ImpeachMemberProofExample impeachMemberProofExample=new ImpeachMemberProofExample();
		    			 impeachMemberProofExample.createCriteria().andDelFlagEqualTo("0").andImpeachMemberIdEqualTo(Integer.valueOf(id)).andIsAddEqualTo("1");
		    			 List<ImpeachMemberProof> impeachMemberProof1=impeachMemberProofService.selectByExample(impeachMemberProofExample);
		    			 if (impeachMemberProof1.size()>0) {
		    				 ImpeachMemberProof impeachMemberProof=new ImpeachMemberProof();
		    				 impeachMemberProof.setIsAdd("0");
		    				 impeachMemberProof.setUpdateBy(staffId);
		    				 impeachMemberProof.setUpdateDate(new Date());
		    				 impeachMemberProofService.updateByExampleSelective(impeachMemberProof, impeachMemberProofExample);
						  }
					   
					     if (!StringUtil.isEmpty(filePathList)) {
					    	 String[] filePathListS=filePathList.split(",");			    	 
					    	 for (String filePath : filePathListS) {
					    		 ImpeachMemberProof impeachMemberProof=new ImpeachMemberProof();
					    		 impeachMemberProof.setCreateBy(staffId);
					    		 impeachMemberProof.setCreateDate(new Date());
					    		 impeachMemberProof.setImpeachMemberId(Integer.valueOf(id));
					    		 impeachMemberProof.setFileName(filePath.substring(0,filePath.indexOf("/")));
					    		 impeachMemberProof.setFilePath(filePath.substring(filePath.indexOf("/")));
					    		 if (!status156.equals("2")) {
					    			 impeachMemberProof.setIsAdd("1");
					    		 
								  }
					    		 impeachMemberProof.setUploadSource("1");
					    		 impeachMemberProof.setDelFlag("0");
					    		 impeachMemberProofService.insert(impeachMemberProof);
					    		 
					    		 
					    	 }
							
						}
						map.put("code", "200");
					} catch (ArgException e) {
						e.printStackTrace();
						map.put("code", "999");
						map.put("message", e.getMessage());
					} catch (Exception e) {
						e.printStackTrace();
						map.put("code", "999");
					}
					return map;
				}
				

				//平台结案
				@ResponseBody
				@RequestMapping("/imPeach/endupdateStatus.shtml")
				public Map<String, Object> endupdateStatus(HttpServletRequest request) {
					Map<String, Object> map = new HashMap<String, Object>();
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
					    String id=request.getParameter("id");
					    String status2=request.getParameter("status2");
					    String endRejectReason=request.getParameter("endRejectReason");
					    String endInnerRemarks=request.getParameter("endInnerRemarks");
					    String endlimitMemberActionS=request.getParameter("endlimitMemberActionS");
					    String endcaseCloseDesc=request.getParameter("endcaseCloseDesc");
					    
					    ImpeachMember impeachMember=impeachMemberService.selectByPrimaryKey(Integer.valueOf(id));
					    impeachMember.setStatus(status2);
					    if (!StringUtil.isEmpty(endRejectReason)) {
					    	impeachMember.setEndRejectReason(endRejectReason);
						}else if (!StringUtil.isEmpty(endInnerRemarks)) {
							impeachMember.setEndInnerRemarks(endInnerRemarks);
						}
					    
					    if (StringUtil.isEmpty(endInnerRemarks)) {
							impeachMember.setEndInnerRemarks(null);
							impeachMember.setFwInnerRemarks(null);
						}
					    
					    if (!StringUtil.isEmpty(endlimitMemberActionS)) {
					    	impeachMember.setLimitMemberAction(endlimitMemberActionS);
						}
					    if (!StringUtil.isEmpty(endcaseCloseDesc)) {
					    	impeachMember.setCaseCloseDesc(endcaseCloseDesc);;
						}
					    if (status2.equals("7")) {//通过限制用户行为
					    	    ImpeachMember impeachMember1=impeachMemberService.selectByPrimaryKey(Integer.valueOf(id));		
								String[] MemberIds=impeachMember1.getMemberIds().split(",");
								List<Integer> MemberIdsList=new ArrayList<Integer>();
								for (String MemberId : MemberIds) {
									MemberIdsList.add(Integer.valueOf(MemberId));
								}
								
								MemberInfoExample memberInfoExample =new MemberInfoExample();
								memberInfoExample.createCriteria().andDelFlagEqualTo("0").andIdIn(MemberIdsList);
								List<MemberInfo> memberInfos=memberInfoService.selectByExample(memberInfoExample);
								if (memberInfos!=null && memberInfos.size()>0) {
									for (MemberInfo memberInfo : memberInfos) {
										 MemberInfo memberInfo2=memberInfoService.selectByPrimaryKey(memberInfo.getId());
										 memberInfo2.setUpdateBy(staffId);
										 memberInfo2.setUpdateDate(new Date());
										 memberInfo2.setStatus("P");
										 memberInfo2.setLimitFunction(endlimitMemberActionS);
										 memberInfoService.updateByPrimaryKey(memberInfo2);
										 
									}
								}

								//若果该举报单存在评价单ID,隐藏该评论
								if(!StringUtils.isEmpty(impeachMember1.getCommentId())){
									Comment comment = new Comment();
									comment.setId(impeachMember1.getCommentId());
									comment.setIsShow("0");
									commentService.updateByPrimaryKeySelective(comment);
								}
							impeachMember.setEndAuditBy(staffId); 
							impeachMember.setEndAuditDate(new Date());
						}
					    impeachMemberService.updateByPrimaryKey(impeachMember);
					    
					    ImpeachHandleLog impeachHandleLog=new ImpeachHandleLog();
					    impeachHandleLog.setImpeachMemberId(Integer.valueOf(id));
					    if (status2.equals("7")) {
					    	impeachHandleLog.setStatus("7");
							if (!StringUtil.isEmpty(endInnerRemarks)) {
								impeachHandleLog.setContent("内部备注："+endInnerRemarks);
							}
						}else if (status2.equals("6")) {
							impeachHandleLog.setStatus("8");
							impeachHandleLog.setContent("驳回原因："+endRejectReason);
						}
					    impeachHandleLog.setDelFlag("0");
					    impeachHandleLog.setCreateDate(new Date());
					    impeachHandleLog.setCreateBy(staffId);
					    impeachHandleLogService.insert(impeachHandleLog);
						map.put("code", "200");
					} catch (ArgException e) {
						e.printStackTrace();
						map.put("code", "999");
						map.put("message", e.getMessage());
					} catch (Exception e) {
						e.printStackTrace();
						map.put("code", "999");
					}
					return map;
				}
				
				
				
				//获取附件
				@RequestMapping(value ="/imPeach/checkFileExists.shtml")
				@ResponseBody
				public Map<String, Object> checkFileExists(HttpServletRequest request) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", "200");
					map.put("msg", "操作成功！");
					String attachmentPath = request.getParameter("attachmentPath");
					if(attachmentPath != null){
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
				@RequestMapping(value ="/imPeach/downLoadAttachment.shtml")
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
				
				
				
				//批量变更处理人界面
				@RequestMapping("/impeach/modifyHandlerimpeachMember.shtml")
				public ModelAndView changeImpeachMember(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/imPeach/changeImpeachMember");
					SysStaffInfoExample sysStaffInfoExample=new SysStaffInfoExample();
					sysStaffInfoExample.createCriteria().andStatusEqualTo("A").andOrgIdEqualTo(63);
					List<SysStaffInfo> kfStaffInfos=sysStaffInfoService.selectByExample(sysStaffInfoExample);
					m.addObject("kfStaffInfos", kfStaffInfos);
					
					SysStaffInfoExample sysStaffInfoExample1=new SysStaffInfoExample();
					sysStaffInfoExample1.createCriteria().andStatusEqualTo("A").andOrgIdEqualTo(56);
					List<SysStaffInfo> fawuStaffInfos=sysStaffInfoService.selectByExample(sysStaffInfoExample1);
					m.addObject("fawuStaffInfos", fawuStaffInfos);
					
					return m;
				}
				
		        //获取变更人数据
				@RequestMapping(value = "/impeach/getchangeAuditby.shtml")
				@ResponseBody
				public Map<String, Object> getchangeAuditby(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "成功");
					try {
						String type = request.getParameter("type");
						if (type.equals("1")) {
							resMap.put("CommissionerauditbyList",impeachMemberService.getCommissionerauditbyList());
						}else if (type.equals("2")) {
							resMap.put("FwauditbyList",impeachMemberService.getFwauditbyList());
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return resMap;
				}
				
		
				//更新处理人数据
				@RequestMapping("/imPeach/changeimPeach.shtml")
				public ModelAndView changeimPeach(HttpServletRequest request,String type,Integer auditby, Integer commissionerAuditby, Integer fwAuditby) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = "";
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						Integer staffId = Integer.valueOf(staffBean.getStaffID());
						Date date = new Date();
				
						if (type!=null && auditby!=null && (commissionerAuditby!=null || fwAuditby!=null)) {			
							if (type.equals("1")) {
								ImpeachMemberCustomExample impeachMemberCustomExample=new ImpeachMemberCustomExample();
								impeachMemberCustomExample.createCriteria().andDelFlagEqualTo("0").andCommissionerAuditByEqualTo(auditby);
								List<ImpeachMemberCustom>  impeachMemberCustoms=impeachMemberService.selectByImpeachMemberCustomExample(impeachMemberCustomExample);
								if (impeachMemberCustoms!=null && impeachMemberCustoms.size()>0) {
									for (ImpeachMemberCustom impeachMemberCustom : impeachMemberCustoms) {
										  ImpeachMember impeachMember=impeachMemberService.selectByPrimaryKey(impeachMemberCustom.getId());
										  impeachMember.setCommissionerAuditBy(commissionerAuditby);
										  impeachMember.setUpdateBy(staffId);
										  impeachMember.setUpdateDate(date);
										  impeachMemberService.updateByPrimaryKey(impeachMember);
										  
									}
								}
							}else if (type.equals("2")) {
								ImpeachMemberCustomExample impeachMemberCustomExample=new ImpeachMemberCustomExample();
								impeachMemberCustomExample.createCriteria().andDelFlagEqualTo("0").andFwAuditByEqualTo(auditby);
								List<ImpeachMemberCustom>  impeachMemberCustoms=impeachMemberService.selectByImpeachMemberCustomExample(impeachMemberCustomExample);
								if (impeachMemberCustoms!=null && impeachMemberCustoms.size()>0) {
									for (ImpeachMemberCustom impeachMemberCustom : impeachMemberCustoms) {
										  ImpeachMember impeachMember=impeachMemberService.selectByPrimaryKey(impeachMemberCustom.getId());
										  impeachMember.setFwAuditBy(fwAuditby);
										  impeachMember.setUpdateBy(staffId);
										  impeachMember.setUpdateDate(date);
										  impeachMemberService.updateByPrimaryKey(impeachMember);
										  
									}
								}
							 
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
