package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.constant.SysConfig;
import com.jf.common.utils.*;
import com.jf.dao.MchtInfoChangeLogMapper;
import com.jf.entity.*;
import com.jf.entity.MemberInfoCustomExample.MemberInfoCustomCriteria;
import com.jf.service.*;
import com.jf.vo.MchtVo;
import com.jf.vo.Page;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MchtSettledController extends BaseController {
	
	private static final Log logger = LogFactory.getLog(MchtInfoService.class);
	
	@Resource
	private MchtSettledApplyService mchtSettledApplyService;
	
	@Resource
	private MchtSettledApplyPicService mchtSettledApplyPicService;
	
	@Resource
	private PlatformContactService platformContactService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private MchtUserService mchtUserService;
	
	@Resource
	private ZsProductTypeService zsProductTypeService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;
	
	@Resource
	private MchtSettledApplyRecordService mchtSettledApplyrecordService;
	
	@Autowired
	private SysStaffProductTypeService sysStaffProductTypeService;
	
	@Resource
	private SysStaffRoleService sysStaffRoleService;
	
	@Resource
	private MemberExtendService memberExtendService;
	
	@Resource
	private MemberInfoService memberInfoService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主管企业入驻列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/director/list.shtml")
	public ModelAndView mchtSettledDirectorList(HttpServletRequest request) {
		String rtPage = "/mchtSettled/directorList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("applyStatus", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "STATUS"));
		resMap.put("sourceTypes", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "SOURCE_TYPE"));
		
		PlatformContactExample platformContactExample=new PlatformContactExample();
		PlatformContactExample.Criteria platformContactCriteria=platformContactExample.createCriteria();
		platformContactCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExample);
		resMap.put("platformContacts", platformContacts);
		
		String staffId = this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample staffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		staffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffId));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(staffProductTypeCustomExample);
		resMap.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 主管企业入驻列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/director/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtSettledDirectorData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String staffId = this.getSessionStaffBean(request).getStaffID();
			
			MchtSettledApplyCustomExample mchtSettledApplyCustomExample = new MchtSettledApplyCustomExample();
			MchtSettledApplyCustomExample.MchtSettledApplyCustomCriteria mchtSettledApplyCustomCriteria = mchtSettledApplyCustomExample.createCriteria();
			mchtSettledApplyCustomCriteria.andDelFlagEqualTo("0").andCustomerSourceNotEqualTo("2");//1 主管分配  2 个人开发	
			mchtSettledApplyCustomCriteria.andIsimportNotEqualTo("1");//是否导入1.是，0.否
//			mchtSettledApplyCustomCriteria.andMchtShowScope(Integer.parseInt(staffId)); //本人负责类目 和 分配给本人的商家
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				mchtSettledApplyCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				mchtSettledApplyCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				mchtSettledApplyCustomCriteria.andStatusEqualTo(status);
			}
			if(!StringUtil.isEmpty(request.getParameter("clientType")) ){
				mchtSettledApplyCustomCriteria.andClientTypeEqualTo(request.getParameter("clientType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId")) ){
				String platformContactId = request.getParameter("platformContactId");
				if(platformContactId.equals("-1")){
					mchtSettledApplyCustomCriteria.andPlatformContactIdIsNull();
				}else{
					mchtSettledApplyCustomCriteria.andPlatformContactIdEqualTo(Integer.valueOf(platformContactId));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("companyName")) ){
				String companyName=request.getParameter("companyName");
				mchtSettledApplyCustomCriteria.andCompanyNameLike("%"+companyName+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("settledType"))) {
				mchtSettledApplyCustomCriteria.andSettledTypeEqualTo(request.getParameter("settledType"));
			}
			
			mchtSettledApplyCustomExample.setOrderByClause("a.id desc");
			mchtSettledApplyCustomExample.setLimitStart(page.getLimitStart());
			mchtSettledApplyCustomExample.setLimitSize(page.getLimitSize());
			totalCount = mchtSettledApplyService.countMchtSettledApplyCustomByExample(mchtSettledApplyCustomExample);
			List<MchtSettledApplyCustom> mchtSettledApplyCustoms = mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyCustomExample);
		
			resMap.put("Rows", mchtSettledApplyCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/mchtSettled/director/export.shtml")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			MchtSettledApplyExample mchtSettledApplyExample=new MchtSettledApplyExample();
			MchtSettledApplyExample.Criteria mchtSettledApplyCriteria=mchtSettledApplyExample.createCriteria();
			mchtSettledApplyCriteria.andDelFlagEqualTo("0").andCustomerSourceNotEqualTo("2");	
			mchtSettledApplyExample.setOrderByClause("a.id desc");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				mchtSettledApplyCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				mchtSettledApplyCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				mchtSettledApplyCriteria.andStatusEqualTo(status);
			}
			List<MchtSettledApplyCustom> mchtSettledApplyCustoms=mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyExample);
			String[] titles = { "招商对接人", "入驻申请时间","来源","公司","注册资本","主营类目", "品牌","联系人","联系电话","QQ","提交状态"};
			ExcelBean excelBean = new ExcelBean("导出主管分配专员列表.xls",
					"导出主管分配专员列表", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtSettledApplyCustom mchtSettledApplyCustom:mchtSettledApplyCustoms){
				String sourceDesc ="";
				if (!StringUtils.isEmpty(mchtSettledApplyCustom.getSourceType()) && mchtSettledApplyCustom.getSourceType().equals("9")){
					sourceDesc = mchtSettledApplyCustom.getSourceRemark();
            	}else{
            		sourceDesc = mchtSettledApplyCustom.getSourceTypeDesc();
            	}
				String[] data = {
					mchtSettledApplyCustom.getPlatformContactName(),
					df.format(mchtSettledApplyCustom.getCreateDate()),
					sourceDesc,
					mchtSettledApplyCustom.getCompanyName(),
					mchtSettledApplyCustom.getRegCapital()==null?"/":mchtSettledApplyCustom.getRegCapital()+"万"+mchtSettledApplyCustom.getRegFeeTypeDesc(),
					mchtSettledApplyCustom.getProductTypeMain(),
					mchtSettledApplyCustom.getProductBrandMain(),
					mchtSettledApplyCustom.getContactName(),
					mchtSettledApplyCustom.getPhone(),
					mchtSettledApplyCustom.getQq(),
					mchtSettledApplyCustom.getStatusDesc()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 分配招商负责人
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/allot/list.shtml")
	public ModelAndView mchtSettledAllotList(Model model,HttpServletRequest request) {
		String rtPage = "/mchtSettled/allotList";
		Map<String, Object> resMap = new HashMap<String, Object>();
			
		    String ids=request.getParameter("ids");
		
			PlatformContactExample platformContactExample=new PlatformContactExample();
			PlatformContactExample.Criteria platformContactCriteria=platformContactExample.createCriteria();
			platformContactCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
			
			List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExample);
			
			resMap.put("platformContacts", platformContacts);
			resMap.put("ids", ids);		
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 对接人分配保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/allot/submit.shtml")
	public ModelAndView mchtSettledAllotSubmit(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
			
			List <String> idsStr=Arrays.asList(request.getParameter("ids").split(","));
			List<Integer> ids =new ArrayList<Integer>(idsStr.size());
			
			for(int i=0,j=idsStr.size();i<j;i++){
				ids.add(Integer.parseInt(idsStr.get(i)));   
	        } 
			
			MchtSettledApply mchtSettledApply=new MchtSettledApply();
			mchtSettledApply.setPlatformContactId(platformContactId);
			mchtSettledApply.setCustomerSource("1");//1.主管分配  2.个人开发
			mchtSettledApply.setUpdateBy(staffId);
			mchtSettledApply.setUpdateDate(new Date());
			
			MchtSettledApplyExample mchtSettledApplyExample=new MchtSettledApplyExample();
			MchtSettledApplyExample.Criteria mchtSettledApplyCriteria=mchtSettledApplyExample.createCriteria();
			mchtSettledApplyCriteria.andDelFlagEqualTo("0").andStatusNotEqualTo("1").andIdIn(ids);
			
			mchtSettledApplyService.updateByExampleSelective(mchtSettledApply, mchtSettledApplyExample);
			
		    //添加商家入驻日志
            for (int i = 0; i < idsStr.size(); i++) {
                MchtSettledApplyRecord mchtSettledApplyrecord=new MchtSettledApplyRecord();
            	mchtSettledApplyrecord.setMchtSettledApplyId(ids.get(i));
                mchtSettledApplyrecord.setRecord("主管分配");
				mchtSettledApplyrecord.setCreateDate(new Date());
				mchtSettledApplyrecord.setCreateBy(staffId);
				mchtSettledApplyrecord.setDelFlag("0");
				mchtSettledApplyrecordService.insertSelective(mchtSettledApplyrecord);
			}
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);

		return new ModelAndView(rtPage, resMap);
	}
	
	//取消黑名单
	@RequestMapping(value = "/mchtSettled/unCancelMchtSettled.shtml")
	@ResponseBody
	public Map<String, Object> delSuccess(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = ""; 
		try {    
			String parameter = request.getParameter("Ids");
			String status = request.getParameter("status");
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());

			MchtSettledApplyExample mchtSettledApplyExample = new MchtSettledApplyExample();
			MchtSettledApplyExample.Criteria createCriteria = mchtSettledApplyExample.createCriteria();
			createCriteria.andIdEqualTo(Integer.valueOf(parameter));
			MchtSettledApply mchtSettledApply=new MchtSettledApply();
			mchtSettledApply.setUpdateBy(staffId);
			mchtSettledApply.setUpdateDate(new Date());
	        if (status.equals("0")) {
			   createCriteria.andStatusEqualTo("3");
			   mchtSettledApply.setStatus("0");
		}
			mchtSettledApplyService.updateByExampleSelective(mchtSettledApply, mchtSettledApplyExample);
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	    //批量删除
		@RequestMapping(value = {"/mchtSettled/delete.shtml","/mchtSettled/recover.shtml","/mchtSettled/uncooperative.shtml","/mchtSettled/unMchtSettled.shtml"})
		@ResponseBody
		public Map<String, Object> updateSuccess(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = null;
			String msg = ""; 
			try {    
				String parameter = request.getParameter("Ids");
				String status = request.getParameter("status");
				List <String> idsStr=Arrays.asList(parameter.split(","));
				List<Integer> ids =new ArrayList<Integer>(idsStr.size());
				for(int i=0;i<idsStr.size();i++){
					ids.add(Integer.parseInt(idsStr.get(i)));
				}

				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());

				MchtSettledApplyExample mchtSettledApplyExample = new MchtSettledApplyExample();
				MchtSettledApplyExample.Criteria createCriteria = mchtSettledApplyExample.createCriteria();
				createCriteria.andIdIn(ids);
				MchtSettledApply mchtSettledApply=new MchtSettledApply();
				mchtSettledApply.setUpdateBy(staffId);
				mchtSettledApply.setUpdateDate(new Date());
				if (status.equals("1")) {
					//删除
					createCriteria.andStatusNotEqualTo("1");
					mchtSettledApply.setDelFlag("1");
				}else if(status.equals("0")) {
					//恢复
					mchtSettledApply.setDelFlag("0");
				}else if(status.equals("2")){
					//不合作
					createCriteria.andStatusEqualTo("0");
					mchtSettledApply.setStatus("2");
				}else if (status.equals("3")) {
					//加入黑名单
					createCriteria.andStatusEqualTo("0");
					mchtSettledApply.setStatus("3");
				}
				mchtSettledApplyService.updateByExampleSelective(mchtSettledApply, mchtSettledApplyExample);
				
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				  e.printStackTrace();
				 code = StateCode.JSON_AJAX_ERROR.getStateCode();
				 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return resMap;
		}
	
	/**
	 * 专员企业入驻列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/commissioner/list.shtml")
	public ModelAndView mchtSettledCommissionerList(HttpServletRequest request) {
		String rtPage = "/mchtSettled/commissionerList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		
//		resMap.put("applyStatus", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "STATUS"));
		resMap.put("sourceTypes", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "SOURCE_TYPE"));
		resMap.put("depositTypes", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "DEPOSIT_TYPE"));
		
//		PlatformContactExample platformContactExample=new PlatformContactExample();
//		PlatformContactExample.Criteria platformContactCriteria=platformContactExample.createCriteria();
//		platformContactCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
//		
//		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExample);
//		resMap.put("platformContacts", platformContacts);
		
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			resMap.put("role107", true);
		}else{
			resMap.put("role107", false);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 专员企业入驻列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/commissioner/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtSettledCommissionerData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			
			MchtSettledApplyCustomExample mchtSettledApplyCustomExample=new MchtSettledApplyCustomExample();
			MchtSettledApplyCustomExample.MchtSettledApplyCustomCriteria mchtSettledApplyCustomCriteria=mchtSettledApplyCustomExample.createCriteria();
			mchtSettledApplyCustomCriteria.andDelFlagEqualTo("0").andCustomerSourceEqualTo("1");//1.主管分配,2.个人开发
			mchtSettledApplyCustomCriteria.andIsimportEqualTo("0");//是否导入:1.是 0.否
//			mchtSettledApplyCustomCriteria.andStaffIdEqualTo(staffId);
			mchtSettledApplyCustomExample.setOrderByClause("a.id desc");
			mchtSettledApplyCustomCriteria.andStatusEqualTo("4");//招商：确认合作
			mchtSettledApplyCustomCriteria.andDepositConfirmStatusEqualTo("1");//保证金：确认合作
			PlatformContactExample platformContactExample=new PlatformContactExample();
			PlatformContactExample.Criteria criteria=platformContactExample.createCriteria();
			criteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId);
			List<PlatformContact> palContacts=platformContactService.selectByExample(platformContactExample);
			if (palContacts !=null && palContacts.size()>0) {
				mchtSettledApplyCustomCriteria.andPlatformContactIdEqualTo(palContacts.get(0).getId());
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				mchtSettledApplyCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				mchtSettledApplyCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("deposit_confirm_date_begin")) ){
				mchtSettledApplyCustomCriteria.andDepositConfirmDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("deposit_confirm_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("deposit_confirm_date_end")) ){
				mchtSettledApplyCustomCriteria.andDepositConfirmDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("deposit_confirm_date_end")+" 23:59:59"));
			}
			
//			if(!StringUtil.isEmpty(request.getParameter("status")) ){
//				String status=request.getParameter("status");
//				mchtSettledApplyCustomCriteria.andStatusEqualTo(status);
//			}
			
			if(!StringUtil.isEmpty(request.getParameter("sourceType")) ){
				String sourceType=request.getParameter("sourceType");
				mchtSettledApplyCustomCriteria.andSourceTypeEqualTo(sourceType);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("depositType")) ){
				String depositType=request.getParameter("depositType");
				mchtSettledApplyCustomCriteria.andDepositTypeEqualTo(depositType);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("companyName")) ){
				String companyName=request.getParameter("companyName");
				mchtSettledApplyCustomCriteria.andCompanyNameLike("%"+companyName+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("settledType")) ){
				String settledType=request.getParameter("settledType");
				mchtSettledApplyCustomCriteria.andSettledTypeEqualTo(settledType);
			}

			if(!StringUtil.isEmpty(request.getParameter("isManageSelf")) ){
				String isManageSelf=request.getParameter("isManageSelf");
				mchtSettledApplyCustomCriteria.andIsManageSelfEqualTo(isManageSelf);
			}
			
			totalCount = mchtSettledApplyService.countMchtSettledApplyCustomByExample(mchtSettledApplyCustomExample);
			
			mchtSettledApplyCustomExample.setLimitStart(page.getLimitStart());
			mchtSettledApplyCustomExample.setLimitSize(page.getLimitSize());
			List<MchtSettledApplyCustom> mchtSettledApplyCustoms=mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyCustomExample);
		
			resMap.put("Rows", mchtSettledApplyCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/mchtSettled/commissioner/export.shtml")
	public void commissionerExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			
			MchtSettledApplyCustomExample mchtSettledApplyCustomExample=new MchtSettledApplyCustomExample();
			MchtSettledApplyCustomExample.MchtSettledApplyCustomCriteria mchtSettledApplyCustomCriteria=mchtSettledApplyCustomExample.createCriteria();
			mchtSettledApplyCustomCriteria.andDelFlagEqualTo("0");
			mchtSettledApplyCustomCriteria.andStaffIdEqualTo(staffId);
			mchtSettledApplyCustomExample.setOrderByClause("a.id desc");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				mchtSettledApplyCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				mchtSettledApplyCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				mchtSettledApplyCustomCriteria.andStatusEqualTo(status);
			}
			
			List<MchtSettledApplyCustom> mchtSettledApplyCustoms=mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyCustomExample);
			String[] titles = {"入驻申请时间","来源","公司","注册资本","主营类目", "品牌","联系人","联系电话","QQ","提交状态"};
			ExcelBean excelBean = new ExcelBean("导出专员审核列表.xls",
					"导出专员审核列表", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtSettledApplyCustom mchtSettledApplyCustom:mchtSettledApplyCustoms){
				String sourceDesc = "";
				if (mchtSettledApplyCustom.getSourceType().equals("9")){
					sourceDesc = mchtSettledApplyCustom.getSourceRemark();
            	}else{
            		sourceDesc = mchtSettledApplyCustom.getSourceTypeDesc();
            	}
				String statusDesc = "";
				if (mchtSettledApplyCustom.getStatus().equals("1")){
					statusDesc = mchtSettledApplyCustom.getStatusDesc();
				}else{
					statusDesc = "添加";
				}
				String[] data = {
					df.format(mchtSettledApplyCustom.getCreateDate()),
					sourceDesc,
					mchtSettledApplyCustom.getCompanyName(),
					mchtSettledApplyCustom.getRegCapital()==null?"/":mchtSettledApplyCustom.getRegCapital()+"万"+mchtSettledApplyCustom.getRegFeeTypeDesc(),
					mchtSettledApplyCustom.getProductTypeMain(),
					mchtSettledApplyCustom.getProductBrandMain(),
					mchtSettledApplyCustom.getContactName(),
					mchtSettledApplyCustom.getPhone(),
					mchtSettledApplyCustom.getQq(),
					statusDesc,
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 入驻申请查看 */
	@RequestMapping(value = "/mchtSettled/view.shtml")
	public ModelAndView mchtSettledView(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mchtSettled/view";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		int id=Integer.valueOf(request.getParameter("id"));
		
		//获取id对应的申请信息
		MchtSettledApplyCustom mchtSettledApplyCustom = mchtSettledApplyService.selectMchtSettledApplyCustomByPrimaryKey(id);
		resMap.put("mchtSettledApplyCustom", mchtSettledApplyCustom);
		if(!StringUtils.isEmpty(mchtSettledApplyCustom.getZsProductTypeIds())){
			String[] zsProductTypeIdsArray = mchtSettledApplyCustom.getZsProductTypeIds().split(",");
			List<Integer> zsProductTypeIdList = new ArrayList<Integer>();
			for(String zsProductTypeId:zsProductTypeIdsArray){
				zsProductTypeIdList.add(Integer.parseInt(zsProductTypeId));
			}
			ZsProductType zsProductType = zsProductTypeService.selectByPrimaryKey(zsProductTypeService.selectByPrimaryKey(zsProductTypeIdList.get(0)).getParentId());
			ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(zsProductType.getProductTypeIds()));
			resMap.put("productTypeName", productType.getName());
			List<String> zsProductTypeDetails = zsProductTypeService.getZsProductTypeDetails(zsProductTypeIdList);
			resMap.put("zsProductTypeDetails", zsProductTypeDetails);
		}
		
		MchtSettledApplyPicExample mchtSettledApplyPicExample=new MchtSettledApplyPicExample();
		mchtSettledApplyPicExample.createCriteria().andSettledApplyIdEqualTo(id).andDelFlagEqualTo("0").andTypeEqualTo("1");
		List<MchtSettledApplyPic> mchtSettledApplyPics=mchtSettledApplyPicService.selectByExample(mchtSettledApplyPicExample);
		List<Map<String, Object>> mchtSettledApplyPicList=new ArrayList<Map<String, Object>>();
		for(MchtSettledApplyPic mchtSettledApplyPic:mchtSettledApplyPics){
			Map<String, Object> pic=new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtSettledApplyPic.getPic());
			mchtSettledApplyPicList.add(pic);
		}
		resMap.put("mchtSettledApplyPics", mchtSettledApplyPicList);
		resMap.put("applyStatus", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "STATUS"));
		resMap.put("regFeeTypes", DataDicUtil.getStatusList("BU_MCHT_INFO", "REG_FEE_TYPE"));
		PlatformContactExample example = new PlatformContactExample();
		PlatformContactExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		c.andStatusEqualTo("0");
		c.andContactTypeEqualTo("1");
		List<PlatformContact> platformContacts = platformContactService.selectByExample(example);
		if(platformContacts!=null && platformContacts.size()>0){
			resMap.put("isZs", true);
		}
		MchtSettledApplyPicExample e=new MchtSettledApplyPicExample();
		e.createCriteria().andSettledApplyIdEqualTo(id).andDelFlagEqualTo("0").andTypeEqualTo("2");
		List<MchtSettledApplyPic> productPics=mchtSettledApplyPicService.selectByExample(e);
		List<Map<String, Object>> productPicList=new ArrayList<Map<String, Object>>();
		for(MchtSettledApplyPic productPic:productPics){
			Map<String, Object> pic=new HashMap<String, Object>();
			pic.put("PICTURE_PATH", productPic.getPic());
			productPicList.add(pic);
		}
		resMap.put("productPicList", productPicList);
		return new ModelAndView(rtPage, resMap);
	}
	
	/* 入驻申请添加备注 */
	@RequestMapping(value = "/mchtSettled/editRemark.shtml")
	public ModelAndView mchtSettledEditRemark(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mchtSettled/editRemark";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		int id=Integer.valueOf(request.getParameter("id"));
		
		//获取id对应的申请信息
		MchtSettledApplyCustom mchtSettledApplyCustom = mchtSettledApplyService.selectMchtSettledApplyCustomByPrimaryKey(id);
		resMap.put("mchtSettledApplyCustom", mchtSettledApplyCustom);
			
		MchtSettledApplyPicExample mchtSettledApplyPicExample=new MchtSettledApplyPicExample();
		mchtSettledApplyPicExample.createCriteria().andSettledApplyIdEqualTo(id).andDelFlagEqualTo("0");
		List<MchtSettledApplyPic> mchtSettledApplyPics=mchtSettledApplyPicService.selectByExample(mchtSettledApplyPicExample);
		List<Map<String, Object>> mchtSettledApplyPicList=new ArrayList<Map<String, Object>>();
		for(MchtSettledApplyPic mchtSettledApplyPic:mchtSettledApplyPics){
			Map<String, Object> pic=new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtSettledApplyPic.getPic());
			mchtSettledApplyPicList.add(pic);
		}
			resMap.put("mchtSettledApplyPics", mchtSettledApplyPicList);
		
		return new ModelAndView(rtPage, resMap);
	}
	
	//保存入驻申请备注
	@RequestMapping(value = "/mchtSettled/editRemarkSave.shtml")
	@ResponseBody
 	public ModelAndView mchtSettledEditRemarkSave(HttpServletRequest request){
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			int id=Integer.valueOf(request.getParameter("id"));
			String remarks=request.getParameter("remarks");

			//保存备注
			MchtSettledApply mchtSettledApply=new MchtSettledApply();
			mchtSettledApply.setId(id);
			mchtSettledApply.setRemarks(remarks);
			mchtSettledApply.setUpdateBy(staffId);
			mchtSettledApply.setUpdateDate(new Date());
			mchtSettledApplyService.updateByPrimaryKeySelective(mchtSettledApply);

			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/* 入驻申请添加或编辑 */
	@RequestMapping(value = "/mchtSettled/edit.shtml")
	public ModelAndView mchtSettledEdit(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mchtSettled/edit";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		if (!StringUtil.isEmpty(request.getParameter("id"))) {
			//获取id对应的申请信息
			MchtSettledApply mchtSettledApply = mchtSettledApplyService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
			resMap.put("mchtSettledApply", mchtSettledApply);
			
			
			MchtSettledApplyPicExample mchtSettledApplyPicExample=new MchtSettledApplyPicExample();
			mchtSettledApplyPicExample.createCriteria().andSettledApplyIdEqualTo(mchtSettledApply.getId()).andDelFlagEqualTo("0");
			List<MchtSettledApplyPic> mchtSettledApplyPics=mchtSettledApplyPicService.selectByExample(mchtSettledApplyPicExample);
			List<Map<String, Object>> mchtSettledApplyPicList=new ArrayList<Map<String, Object>>();
			for(MchtSettledApplyPic mchtSettledApplyPic:mchtSettledApplyPics){
				Map<String, Object> pic=new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtSettledApplyPic.getPic());
				mchtSettledApplyPicList.add(pic);
			}
			resMap.put("mchtSettledApplyPics", mchtSettledApplyPicList);
		}
		
		resMap.put("applyStatus", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "STATUS"));
		resMap.put("regFeeTypes", DataDicUtil.getStatusList("BU_MCHT_INFO", "REG_FEE_TYPE"));
		
		return new ModelAndView(rtPage, resMap);
	}
	
	//保存沽加或编辑数据
	@RequestMapping(value = "/mchtSettled/editSave.shtml")
	@ResponseBody
 	public ModelAndView mchtSettledEditSave(HttpServletRequest request,MchtSettledApply mchtSettledApply,String mchtSettledApplyPics){
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			
			if(mchtSettledApply.getId() == null){
				//添加数据
				mchtSettledApply.setSourceType("4");
				mchtSettledApply.setSourceRemark("后台添加");
				mchtSettledApply.setCreateBy(staffId);
				mchtSettledApplyService.insertMchtSettledApply(mchtSettledApply, mchtSettledApplyPics);
			}else{
				MchtSettledApply msa = mchtSettledApplyService.selectByPrimaryKey(mchtSettledApply.getId());
				PlatformContactExample example = new PlatformContactExample();
				PlatformContactExample.Criteria c = example.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andStaffIdEqualTo(staffId);
				c.andStatusEqualTo("0");
				c.andContactTypeEqualTo("1");
				List<PlatformContact> platformContacts = platformContactService.selectByExample(example);
				if(platformContacts!=null && platformContacts.size()>0){
					if(!platformContacts.get(0).getId().equals(msa.getPlatformContactId())){
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = "只有招商本人才可以提交！";
						resMap.put(this.JSON_RESULT_CODE, code);
						resMap.put(this.JSON_RESULT_MESSAGE, msg);
						return new ModelAndView(rtPage, resMap);
					}
				}else{
					code = StateCode.JSON_AJAX_ERROR.getStateCode();
					msg = "只有招商本人才可以提交！";
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return new ModelAndView(rtPage, resMap);
				}
				
				//更新数据
				mchtSettledApply.setUpdateBy(staffId);
				mchtSettledApply.setUpdateDate(new Date()); 
				mchtSettledApplyService.updateMchtSettledApply(mchtSettledApply, mchtSettledApplyPics);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 入驻申请回车站列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/recycle/list.shtml")
	public ModelAndView mchtSettledRecycleList(HttpServletRequest request) {
		String rtPage = "/mchtSettled/recycle";
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("sourceTypes", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "SOURCE_TYPE"));
		
		PlatformContactExample platformContactExample=new PlatformContactExample();
		PlatformContactExample.Criteria platformContactCriteria=platformContactExample.createCriteria();
		platformContactCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
		
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExample);
		
		resMap.put("platformContacts", platformContacts);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 入驻申请回车站列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/recycle/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtSettledRecycleData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			
			MchtSettledApplyExample mchtSettledApplyExample=new MchtSettledApplyExample();
			MchtSettledApplyExample.Criteria mchtSettledApplyCriteria=mchtSettledApplyExample.createCriteria();
			mchtSettledApplyCriteria.andDelFlagEqualTo("1").andCustomerSourceEqualTo("1");//1.主管分配,2个人开发
			mchtSettledApplyCriteria.andIsimportEqualTo("0");//是否导入:1.是  0.否
			mchtSettledApplyExample.setOrderByClause("a.id desc");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				mchtSettledApplyCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				mchtSettledApplyCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("sourceType")) ){
				String sourceType=request.getParameter("sourceType");
				mchtSettledApplyCriteria.andSourceTypeEqualTo(sourceType);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("platformContactId")) ){
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				mchtSettledApplyCriteria.andPlatformContactIdEqualTo(platformContactId);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("companyName")) ){
				String companyName=request.getParameter("companyName");
				mchtSettledApplyCriteria.andCompanyNameLike("%"+companyName+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("settledType")) ){
				String settledType = request.getParameter("settledType");
				mchtSettledApplyCriteria.andSettledTypeEqualTo(settledType);
			}
			totalCount = mchtSettledApplyService.countMchtSettledApplyCustomByExample(mchtSettledApplyExample);
			
			mchtSettledApplyExample.setLimitStart(page.getLimitStart());
			mchtSettledApplyExample.setLimitSize(page.getLimitSize());
			List<MchtSettledApplyCustom> mchtSettledApplyCustoms=mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyExample);
		
			resMap.put("Rows", mchtSettledApplyCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/* 入驻申请查看 */
	@RequestMapping(value = "/mchtSettled/confirm.shtml")
	public ModelAndView mchtSettledConfirm(HttpServletRequest request) {
		String rtPage = "/mchtSettled/confirm";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("settledApplyId", request.getParameter("settledApplyId"));
		resMap.put("platformContactId", request.getParameter("platformContactId"));
		MchtSettledApply mchtSettledApply = mchtSettledApplyService.selectByPrimaryKey(Integer.valueOf(request.getParameter("settledApplyId")));
		resMap.put("mchtSettledApply", mchtSettledApply);
		resMap.put("isView", request.getParameter("isView"));
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 商家入驻提交
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
		@RequestMapping(value = "/mchtSettled/submit.shtml")
	public ModelAndView mchtSettledSubmit(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg = "";
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer settledApplyId=Integer.valueOf(request.getParameter("settledApplyId"));
			Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));

			String companyName=request.getParameter("companyName");
			String mchtType=request.getParameter("mchtType");
			String depositType=request.getParameter("depositType");
			String contractDeposit=request.getParameter("contractDeposit");
			String brandDeposit=request.getParameter("brandDeposit");
			String selectContractDeposit=request.getParameter("selectContractDeposit");
			String selectBrandDeposit=request.getParameter("selectBrandDeposit");
			String mobile=request.getParameter("mobile");
			String remarks=request.getParameter("remarks");
	        String userCode="";
			String isManageSelf = request.getParameter("isManageSelf");//是否自营

			//插入商家主表信息
			MchtInfo mchtInfo=new MchtInfo();
			MchtSettledApply msa = mchtSettledApplyService.selectByPrimaryKey(settledApplyId);
			mchtInfo.setSettledType(msa.getSettledType());
			mchtInfo.setShortName(companyName);
			mchtInfo.setCompanyName(companyName);
			mchtInfo.setMchtType(mchtType);
			mchtInfo.setRemarks(remarks);
			mchtInfo.setDepositType(depositType);
			//是否自营插入商家信息表
			if(!StringUtil.isEmpty(isManageSelf)) {
				mchtInfo.setIsManageSelf(isManageSelf);
			}
			//spop自营时插入商家的店铺类型
			if ("1".equals(isManageSelf)&& "1".equals(mchtType)){
				mchtInfo.setShopType("8");
			}
			if(!StringUtil.isEmpty(contractDeposit)) {
				mchtInfo.setContractDeposit(new BigDecimal(contractDeposit));
			}
			if(!StringUtil.isEmpty(brandDeposit)) {
				mchtInfo.setBrandDeposit(new BigDecimal(brandDeposit));
			}
			mchtInfo.setSelectContractDeposit(selectContractDeposit);
			mchtInfo.setSelectBrandDeposit(selectBrandDeposit);
			mchtInfo.setZsAuditStatus("0");//未确认
			mchtInfo.setZsTotalAuditStatus("4");//4.未提交
			mchtInfo.setCreateBy(staffId);
			mchtInfo.setCreateDate(new Date());
			//创建商家用户
	        String tmpCode=mobile;
			for (int i=0; i<20; i++){
				MchtUserExample mchtUserExample=new MchtUserExample();
				mchtUserExample.createCriteria().andUserCodeEqualTo(tmpCode);
				List<MchtUser> mchtUsers=mchtUserService.selectByExample(mchtUserExample);
				if (mchtUsers.size()>0){
					Random rnd = new Random();
					int num = 100 + rnd.nextInt(900);
					tmpCode+="-"+num;
					continue;
				}else{
					userCode=tmpCode;
					break;
				}
			}
			
			if ("".equals(userCode)){
				userCode = String.valueOf(System.currentTimeMillis());
			}
			
			MchtUser mchtUser=new MchtUser();
			mchtUser.setUserCode(userCode);
			String password=SecurityUtil.md5Encode("123456");
			mchtUser.setPassword(password);
			mchtUser.setMobile(mobile);
			mchtUser.setIsPrimary("1");
			mchtUser.setStatus("0");
			
			//创建商家平台对接人
			MchtPlatformContact mchtPlatformContact=new MchtPlatformContact();
			mchtPlatformContact.setPlatformContactId(platformContactId);
			mchtPlatformContact.setStatus("1");
			
			MchtVo mchtVo=new MchtVo();
			mchtVo.setMchtInfo(mchtInfo);
			mchtVo.setMchtUser(mchtUser);
			mchtVo.setMchtPlatformContact(mchtPlatformContact);
			mchtInfoService.saveMcht(mchtVo);
			
			//更新入驻状态
			MchtSettledApply mchtSettledApply=new MchtSettledApply();
			mchtSettledApply.setId(settledApplyId);
			mchtSettledApply.setStatus("1");
			mchtSettledApply.setUpdateBy(staffId);
			mchtSettledApply.setUpdateDate(new Date());
			mchtSettledApply.setMchtId(mchtVo.getMchtInfo().getId());
			mchtSettledApply.setCompanyName(companyName);
			mchtSettledApply.setPhone(mobile);
			mchtSettledApplyService.updateByPrimaryKeySelective(mchtSettledApply);
			
			JSONObject param=new JSONObject();
			//特定参数
			JSONObject reqData=new JSONObject();
			reqData.put("mobile", mobile);
			reqData.put("content", "恭喜您的商家账号添加成功，用户名："+userCode+"，初始密码：123456，您可以安排提交入驻资质以便开通醒购店铺。 ");
			reqData.put("smsType", "2");
			param.put("reqData", reqData);
			JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
			if(!"0000".equals(result.getString("returnCode"))){
				logger.info("商家成功提交入驻，短信发送商家联系人失败！！！！！");
			}
			
			PlatformContact platformContact=platformContactService.selectByPrimaryKey(platformContactId);
			if (!"".equals(platformContact.getMobile())){
				reqData.put("mobile", platformContact.getMobile());
				param.put("reqData", reqData);
				JSONObject result2=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				if(!"0000".equals(result2.getString("returnCode"))){
					logger.info("商家成功提交入驻，短信发送平台招商对接人失败！！！！！");
				}
			}else{
				logger.info("商家成功提交入驻，未找到平台招商对接人手机号，短信未发送！！！！！");
			}
			
			MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
			mchtInfoChangeLog.setDelFlag("0");
			mchtInfoChangeLog.setCreateDate(new Date());
			mchtInfoChangeLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfoChangeLog.setMchtId(mchtVo.getMchtInfo().getId());
			mchtInfoChangeLog.setLogType("创建商家");
			mchtInfoChangeLog.setLogName(companyName);
			mchtInfoChangeLog.setBeforeChange("-");
			mchtInfoChangeLog.setAfterChange("-");
			mchtInfoChangeLog.setCreatorType("1");
			String mchtTypeDesc = "";
			if(mchtType.equals("1")){
				mchtTypeDesc = "开放联营";
			}else{
				mchtTypeDesc = "开放POP";
			}
			String depositTypeDesc = "";
			if(depositType.equals("1")){
				depositTypeDesc = "可货款抵扣";
			}else{
				depositTypeDesc = "不可货款抵扣";
			}
			mchtInfoChangeLog.setRemarks(companyName+"-"+mchtTypeDesc+"-"+depositTypeDesc);
			mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);

		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 招商信息待确认
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/infoConfirm/list.shtml")
	public ModelAndView mchtSettledInfoConfirmList(HttpServletRequest request) {
		String rtPage = "/mchtSettled/infoConfirmList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("applyStatus", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "STATUS"));
		resMap.put("sourceTypes", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "SOURCE_TYPE"));
		resMap.put("depositConfirmStatusList", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "DEPOSIT_CONFIRM_STATUS"));
		if(!StringUtils.isEmpty(request.getParameter("status"))){
			resMap.put("status", request.getParameter("status"));
		}else{
			resMap.put("status", "0");
		}
		String staffID = this.getSessionStaffBean(request).getStaffID();
		SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(Integer.parseInt(staffID));
		if(sysStaffInfo.getIsManagement().equals("1")){//管理层
			PlatformContactExample example = new PlatformContactExample();
			PlatformContactExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("0");
			c.andContactTypeEqualTo("1");
			List<PlatformContactCustom> platformContactCustoms = platformContactService.selectPlatformContactCustomByExample(example);
			resMap.put("platformContactCustoms", platformContactCustoms);
		}
		if (!sysStaffInfo.getOrgId().equals(25)) {//不是招商(部门ID!=25)
			resMap.put("OrgId25", 25);
		}
		resMap.put("isManagement", sysStaffInfo.getIsManagement());
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			resMap.put("role107", true);
		}else{
			resMap.put("role107", false);
		}
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 招商信息待确认数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/infoConfirm/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtSettledInfoConfirmData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			
			MchtSettledApplyExample mchtSettledApplyExample=new MchtSettledApplyExample();
			MchtSettledApplyExample.Criteria mchtSettledApplyCriteria=mchtSettledApplyExample.createCriteria();
			mchtSettledApplyCriteria.andDelFlagEqualTo("0").andCustomerSourceEqualTo("1");//1.主管分配 2.个人开发
			mchtSettledApplyCriteria.andIsimportNotEqualTo("1");//是否导入1.是  0.否
			mchtSettledApplyExample.setOrderByClause("a.id desc");
			String platformContactId = request.getParameter("platformContactId");
			if(!StringUtils.isEmpty(platformContactId)){
				if(platformContactId.equals("-1")){//全部
					mchtSettledApplyCriteria.andPlatformContactIdIsNotNull();
				}else{
					mchtSettledApplyCriteria.andPlatformContactIdEqualTo(Integer.parseInt(platformContactId));
				}
			}else{
				PlatformContactExample pce = new PlatformContactExample();
				pce.setOrderByClause("id desc");
				PlatformContactExample.Criteria pcec = pce.createCriteria();
				pcec.andDelFlagEqualTo("0");
				pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				pcec.andStatusEqualTo("0");//0.正常
				List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
				if(platformContacts!=null && platformContacts.size()>0){
					mchtSettledApplyCriteria.andPlatformContactIdEqualTo(platformContacts.get(0).getId());
				}else {
					mchtSettledApplyCriteria.andPlatformContactIdEqualTo(0);
				}
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				mchtSettledApplyCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				mchtSettledApplyCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				mchtSettledApplyCriteria.andStatusEqualTo(status);
			}
				
			if(!StringUtil.isEmpty(request.getParameter("sourceType")) ){
				String sourceType=request.getParameter("sourceType");
				if(sourceType.equals("-1")){
					mchtSettledApplyCriteria.andClientTypeEqualTo("1");
				}else{
					mchtSettledApplyCriteria.andSourceTypeEqualTo(sourceType);
				}
			}
				
			if(!StringUtil.isEmpty(request.getParameter("companyName")) ){
				String companyName=request.getParameter("companyName");
				mchtSettledApplyCriteria.andCompanyNameLike("%"+companyName+"%");
			}
				
			if(!StringUtil.isEmpty(request.getParameter("depositConfirmStatus")) ){
				String depositConfirmStatus=request.getParameter("depositConfirmStatus");
				mchtSettledApplyCriteria.andDepositConfirmStatusEqualTo(depositConfirmStatus);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("settledType")) ){
				String settledType=request.getParameter("settledType");
				mchtSettledApplyCriteria.andSettledTypeEqualTo(settledType);
			}
				
			totalCount = mchtSettledApplyService.countMchtSettledApplyCustomByExample(mchtSettledApplyExample);
				
			mchtSettledApplyExample.setLimitStart(page.getLimitStart());
			mchtSettledApplyExample.setLimitSize(page.getLimitSize());
			List<MchtSettledApplyCustom> mchtSettledApplyCustoms=mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyExample);
				
			resMap.put("Rows", mchtSettledApplyCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/* 公司信息确认 */
	@RequestMapping(value = "/mchtSettled/confirmCooperate.shtml")
	public ModelAndView mchtSettledConfirmCooperate(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mchtSettled/confirmCooperate";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("id", request.getParameter("id"));
		resMap.put("status", request.getParameter("status"));
		MchtSettledApply mchtSettledApply = mchtSettledApplyService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("mchtSettledApply", mchtSettledApply);
		return new ModelAndView(rtPage, resMap);
	}
	
	/* 加入黑名单 */
	@RequestMapping(value = "/mchtSettled/joinInBlacklist.shtml")
	public ModelAndView mchtSettledJoinInBlacklist(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mchtSettled/joinInBlacklist";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("id", request.getParameter("id"));
		resMap.put("status", request.getParameter("status"));
		return new ModelAndView(rtPage, resMap);
	}
	
	/* 取消黑名单 */
	@RequestMapping(value = "/mchtSettled/cancelTheBlacklist.shtml")
	public ModelAndView mchtSettledCancelTheBlacklist(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mchtSettled/cancelTheBlacklist";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("id", request.getParameter("id"));
		MchtSettledApply mchtSettledApply = mchtSettledApplyService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("remarks", mchtSettledApply.getRemarks());
		resMap.put("status", request.getParameter("status"));
		return new ModelAndView(rtPage, resMap);
	}
	
	//招商确认状态更改
	@RequestMapping(value = "/mchtSettled/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = ""; 
		try {    
			String id = request.getParameter("id");
			String status = request.getParameter("status");
			String mchtType = request.getParameter("mchtType");
			String productSituation = request.getParameter("productSituation");
			String otherChannelLink = request.getParameter("otherChannelLink");
			String logistics = request.getParameter("logistics");
			String teamSituation = request.getParameter("teamSituation");
			String companySituation = request.getParameter("companySituation");
			String remarks = request.getParameter("remarks");
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());

			MchtSettledApplyExample mchtSettledApplyExample = new MchtSettledApplyExample();
			MchtSettledApplyExample.Criteria createCriteria = mchtSettledApplyExample.createCriteria();
			createCriteria.andIdEqualTo(Integer.valueOf(id));
			MchtSettledApply mchtSettledApply=new MchtSettledApply();
			mchtSettledApply.setUpdateBy(staffId);
			mchtSettledApply.setUpdateDate(new Date());
			mchtSettledApply.setMchtType(mchtType);
			mchtSettledApply.setProductSituation(productSituation);
			mchtSettledApply.setOtherChannelLink(otherChannelLink);
			mchtSettledApply.setLogistics(logistics);
			mchtSettledApply.setTeamSituation(teamSituation);
			mchtSettledApply.setCompanySituation(companySituation);
			mchtSettledApply.setRemarks(remarks);
	        if(status.equals("0")){
	        	createCriteria.andStatusEqualTo("3");
	        	mchtSettledApply.setStatus("0");
	        }else if(status.equals("2")){
	        	createCriteria.andStatusEqualTo("0");
				mchtSettledApply.setStatus("2");
	        }else if(status.equals("3")){
	        	createCriteria.andStatusEqualTo("0");
				mchtSettledApply.setStatus("3");
	        }else if(status.equals("4")){
	        	createCriteria.andStatusEqualTo("0");
				mchtSettledApply.setStatus("4");
				mchtSettledApply.setInfoConfirmDate(new Date());
				mchtSettledApply.setInfoConfirmBy(staffId);
				mchtSettledApply.setDepositConfirmStatus("0");
	        }
			mchtSettledApplyService.updateByExampleSelective(mchtSettledApply, mchtSettledApplyExample);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 保证金待确认
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/depositConfirm/list.shtml")
	public ModelAndView mchtSettledDepositConfirmList(HttpServletRequest request) {
		String rtPage = "/mchtSettled/depositConfirmList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("sourceTypes", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "SOURCE_TYPE"));
		resMap.put("depositConfirmStatusList", DataDicUtil.getStatusList("BU_MCHT_SETTLED_APPLY", "DEPOSIT_CONFIRM_STATUS"));
		if(!StringUtils.isEmpty(request.getParameter("depositConfirmStatus"))){
			resMap.put("depositConfirmStatus", request.getParameter("depositConfirmStatus"));
		}else{
			resMap.put("depositConfirmStatus", "0");
		}
		//提交信息确认人（100天的集合）
		List<HashMap<String, Object>> infoConfirmByList = mchtSettledApplyService.getInfoConfirmBy();
		resMap.put("infoConfirmByList", infoConfirmByList);
		
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			resMap.put("role107", true);
		}else{
			resMap.put("role107", false);
		}
		return new ModelAndView(rtPage,resMap);
	}

	/**
	 * 保证金待确认数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/depositConfirm/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtSettledDepositConfirmData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			
			MchtSettledApplyExample mchtSettledApplyExample=new MchtSettledApplyExample();
			MchtSettledApplyExample.Criteria mchtSettledApplyCriteria=mchtSettledApplyExample.createCriteria();
			mchtSettledApplyCriteria.andDelFlagEqualTo("0").andCustomerSourceEqualTo("1");//1.主管分配 2.个人开发
			mchtSettledApplyCriteria.andIsimportEqualTo("0");//是否导入:1.是 0.否
			mchtSettledApplyExample.setOrderByClause("a.id desc");
			mchtSettledApplyCriteria.andStatusEqualTo("4");
			mchtSettledApplyCriteria.andInfoConfirmByIsNotNull();
			mchtSettledApplyCriteria.andInfoConfirmDateIsNotNull();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				mchtSettledApplyCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				mchtSettledApplyCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("info_confirm_date_begin")) ){
				mchtSettledApplyCriteria.andInfoConfirmDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("info_confirm_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("info_confirm_date_end")) ){
				mchtSettledApplyCriteria.andInfoConfirmDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("info_confirm_date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("sourceType")) ){
				String sourceType=request.getParameter("sourceType");
				mchtSettledApplyCriteria.andSourceTypeEqualTo(sourceType);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("companyName")) ){
				String companyName=request.getParameter("companyName");
				mchtSettledApplyCriteria.andCompanyNameLike("%"+companyName+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("depositConfirmStatus")) ){
				String depositConfirmStatus=request.getParameter("depositConfirmStatus");
				mchtSettledApplyCriteria.andDepositConfirmStatusEqualTo(depositConfirmStatus);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("infoConfirmBy")) ){
				String infoConfirmBy=request.getParameter("infoConfirmBy");
				mchtSettledApplyCriteria.andInfoConfirmByEqualTo(Integer.parseInt(infoConfirmBy));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("settledType")) ){
				String settledType=request.getParameter("settledType");
				mchtSettledApplyCriteria.andSettledTypeEqualTo(settledType);
			}
			
			totalCount = mchtSettledApplyService.countMchtSettledApplyCustomByExample(mchtSettledApplyExample);
			
			mchtSettledApplyExample.setLimitStart(page.getLimitStart());
			mchtSettledApplyExample.setLimitSize(page.getLimitSize());
			List<MchtSettledApplyCustom> mchtSettledApplyCustoms=mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyExample);
			
			//获取控建角色
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());		
				
			SysStaffRoleExample sysStaffRoleExample=new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(staffID).andRoleIdEqualTo(78);
		    List<SysStaffRole> sysStaffRolelist=sysStaffRoleService.selectByExample(sysStaffRoleExample);
		    if (sysStaffRolelist!=null && sysStaffRolelist.size()>0) {
		    	for (MchtSettledApplyCustom mchtSettledApplyCustom : mchtSettledApplyCustoms) {	
		    		mchtSettledApplyCustom.setRoleId(sysStaffRolelist.get(0).getRoleId());
		    	}
			}
						
			resMap.put("Rows", mchtSettledApplyCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/* 确认保证金额度 */
	@RequestMapping(value = "/mchtSettled/confirmDeposit.shtml")
	public ModelAndView confirmDeposit(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mchtSettled/confirmDeposit";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		MchtSettledApplyCustom mchtSettledApplyCustom = mchtSettledApplyService.selectMchtSettledApplyCustomByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("mchtSettledApplyCustom", mchtSettledApplyCustom);
		//角色ID78的才可以控住合作模式类型
		String staffId = this.getSessionStaffBean(request).getStaffID();
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.valueOf(staffId));
		List<SysStaffRole> SysStaffRoles = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		for (SysStaffRole sysStaffRole:SysStaffRoles) {
			if(sysStaffRole.getRoleId().equals(78)){
				resMap.put("TAG", true);
			}
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	//保证金确认状态更改
	@RequestMapping(value = "/mchtSettled/confirmDepositStatus.shtml")
	@ResponseBody
	public Map<String, Object> confirmDepositStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = ""; 
		try {
			/*PlatformContactExample example = new PlatformContactExample();
			PlatformContactExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			c.andStatusEqualTo("0");
			c.andContactTypeEqualTo("1");
			List<PlatformContact> platformContacts = platformContactService.selectByExample(example);
			if(platformContacts == null || platformContacts.size() == 0){
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = "只有招商人员才可以确认保证金！";
				resMap.put(this.JSON_RESULT_CODE, code);
				resMap.put(this.JSON_RESULT_MESSAGE, msg);
				return resMap;
			}*/
			String id = request.getParameter("id");
			String depositConfirmStatus = request.getParameter("depositConfirmStatus");
			String depositType = request.getParameter("depositType");
			String contractDeposit = request.getParameter("contractDeposit");
			String brandDeposit = request.getParameter("brandDeposit");
			String feeRate = request.getParameter("feeRate");
			String selectContractDeposit = request.getParameter("selectContractDeposit");
			String selectBrandDeposit = request.getParameter("selectBrandDeposit");
			String mchtType = request.getParameter("mchtType");
			String isManageSelf = request.getParameter("isManageSelf");
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			MchtSettledApplyExample mchtSettledApplyExample = new MchtSettledApplyExample();
			MchtSettledApplyExample.Criteria createCriteria = mchtSettledApplyExample.createCriteria();
			createCriteria.andIdEqualTo(Integer.valueOf(id));
			MchtSettledApply mchtSettledApply=new MchtSettledApply();
			mchtSettledApply.setUpdateBy(staffId);
			mchtSettledApply.setUpdateDate(new Date());
			mchtSettledApply.setDepositConfirmStatus(depositConfirmStatus);
			mchtSettledApply.setMchtType(mchtType);
			//保存是否自营字段  POP入驻没有这个字段
			if (!StringUtil.isEmpty(isManageSelf) && "1".equals(mchtType)){
				mchtSettledApply.setIsManageSelf(isManageSelf);
			}else {
				mchtSettledApply.setIsManageSelf("0");
			}
			if(depositConfirmStatus.equals("1")){//合作
				mchtSettledApply.setDepositType(depositType);
				mchtSettledApply.setFeeRate(new BigDecimal(feeRate));
				mchtSettledApply.setSelectContractDeposit(selectContractDeposit);
				if("1".equals(selectContractDeposit)) {
					mchtSettledApply.setContractDeposit(new BigDecimal(contractDeposit));
				}
				mchtSettledApply.setSelectBrandDeposit(selectBrandDeposit);
				if("1".equals(selectBrandDeposit)) {
					mchtSettledApply.setBrandDeposit(new BigDecimal(brandDeposit));
				}
			}
			mchtSettledApply.setDepositConfirmDate(new Date());
			mchtSettledApply.setDepositConfirmBy(staffId);
			mchtSettledApplyService.updateByExampleSelective(mchtSettledApply, mchtSettledApplyExample);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 *备注不合作原因页面
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettled/toNotCooperate.shtml")
	public ModelAndView toZsConfirm(HttpServletRequest request) {
		String rtPage = "/mchtSettled/toNotCooperate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String mchtSettledApplyId = request.getParameter("id");
			resMap.put("id", mchtSettledApplyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	//确认不合作
	@RequestMapping(value = "/mchtSettled/notCooperate.shtml")
	@ResponseBody
	public Map<String, Object> notCooperate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id=Integer.valueOf(request.getParameter("id"));
			String remarks = request.getParameter("remarks");
			MchtSettledApply mchtSettledApply = mchtSettledApplyService.selectByPrimaryKey(id);
			mchtSettledApply.setRemarks(remarks);
			mchtSettledApply.setStatus("2");//不合作
			mchtSettledApply.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtSettledApply.setUpdateDate(new Date());
			mchtSettledApplyService.updateByPrimaryKeySelective(mchtSettledApply);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//我的开发记录
	@RequestMapping(value = "/mchtSettled/mchtSettledApplyRecordList.shtml")
	public ModelAndView mchtSettledApplyRecordList(HttpServletRequest request) {
		String rtPage = "/mchtSettled/mchtSettledApplyRecordList";
		Map<String, Object> resMap = new HashMap<String, Object>();			
		return new ModelAndView(rtPage,resMap);
	}
	
	//开发记录列表数据
	@RequestMapping(value = "/mchtSettled/mchtSettledAPPlyRecord/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtSettledAPPlyRecord(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			
			MchtSettledApplyCustomExample mchtSettledApplyCustomExample=new MchtSettledApplyCustomExample();
			MchtSettledApplyCustomExample.MchtSettledApplyCustomCriteria mchtSettledApplyCustomCriteria=mchtSettledApplyCustomExample.createCriteria();
			mchtSettledApplyCustomCriteria.andDelFlagEqualTo("0").andCustomerSourceBetween("1", "2");
			mchtSettledApplyCustomCriteria.andStaffIdEqualTo(staffId);
			mchtSettledApplyCustomExample.setOrderByClause("a.id desc");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				mchtSettledApplyCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				mchtSettledApplyCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}		
			if(!StringUtil.isEmpty(request.getParameter("companyName")) ){
				String companyName=request.getParameter("companyName");
				mchtSettledApplyCustomCriteria.andCompanyNameLike("%"+companyName+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("customerSource"))) {
				mchtSettledApplyCustomCriteria.andCustomerSourceEqualTo(request.getParameter("customerSource"));
			}
			if (!StringUtil.isEmpty(request.getParameter("settledType"))) {
				mchtSettledApplyCustomCriteria.andSettledTypeEqualTo(request.getParameter("settledType"));
			}
			totalCount = mchtSettledApplyService.countMchtSettledApplyCustomByExample(mchtSettledApplyCustomExample);	
			mchtSettledApplyCustomExample.setLimitStart(page.getLimitStart());
			mchtSettledApplyCustomExample.setLimitSize(page.getLimitSize());
			List<MchtSettledApplyCustom> mchtSettledApplyCustoms=mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyCustomExample);
		
			resMap.put("Rows", mchtSettledApplyCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//添加商家
	@RequestMapping(value = "/mchtSettled/editmchtSettledAPPlyRecord.shtml")
	public ModelAndView editmchtSettledAPPlyRecord(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mchtSettled/editmchtSettledAPPlyRecord";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();	
		PlatformContactExample example = new PlatformContactExample();
		PlatformContactExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		c.andStatusEqualTo("0");
		c.andContactTypeEqualTo("1");
		List<PlatformContact> platformContacts = platformContactService.selectByExample(example);
		if(platformContacts!=null && platformContacts.size()>0){
			resMap.put("isZs", true);//招商对接人才能添加
		}	
		return new ModelAndView(rtPage, resMap);
	}
	
	    //保存添加商家
		@RequestMapping(value = "/mchtSettled/addmchtSettledAPPlyRecord.shtml")
		@ResponseBody
	 	public ModelAndView addmchtSettledAPPlyRecord(HttpServletRequest request,MchtSettledApply mchtSettledApply){
			String rtPage = "/success/success";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = null;
			String msg =null;
			try {
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());
				
                PlatformContactExample platformContactExample=new PlatformContactExample();
                PlatformContactExample.Criteria platformContactCriteria=platformContactExample.createCriteria();
                platformContactCriteria.andDelFlagEqualTo("0").andContactTypeEqualTo("1").andStatusEqualTo("0");
                platformContactCriteria.andStaffIdEqualTo(staffId);
                List<PlatformContact> platformcontactId = platformContactService.selectByExample(platformContactExample);
				
                mchtSettledApply.setPlatformContactId(platformcontactId.get(0).getId());
                mchtSettledApply.setCustomerSource("2");//1.主管分配  2.个人开发
				mchtSettledApply.setCreateDate(new Date());
				mchtSettledApply.setCreateBy(staffId);
				mchtSettledApply.setDelFlag("0");
				mchtSettledApplyService.insertSelective(mchtSettledApply);

				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				  e.printStackTrace();
				 code = StateCode.JSON_AJAX_ERROR.getStateCode();
				 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage, resMap);
		}
		
		//添加开发记录
		@RequestMapping(value = "/mchtSettled/addmchtSettledApplyRecord.shtml")
		public ModelAndView addmchtSettledApplyRecordl(HttpServletRequest request) {
			String rtPage = "/mchtSettled/editRecord";// 重定向
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("settledApplyId", request.getParameter("settledApplyId"));				
			return new ModelAndView(rtPage, resMap);
		}
		
		 //保存开发记录
		@RequestMapping(value = "/mchtSettled/editRecord.shtml")
		@ResponseBody
	 	public ModelAndView editRecord(HttpServletRequest request,MchtSettledApplyRecord mchtSettledApplyrecord,String settledApplyId){
			String rtPage = "/success/success";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = null;
			String msg =null;
			try {
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());
				
                PlatformContactExample platformContactExample=new PlatformContactExample();
                PlatformContactExample.Criteria platformContactCriteria=platformContactExample.createCriteria();
                platformContactCriteria.andDelFlagEqualTo("0").andContactTypeEqualTo("1").andStatusEqualTo("0");
                platformContactCriteria.andStaffIdEqualTo(staffId);
                List<PlatformContact> platformcontactId = platformContactService.selectByExample(platformContactExample);
				
					mchtSettledApplyrecord.setMchtSettledApplyId(Integer.valueOf(settledApplyId));
					mchtSettledApplyrecord.setCreateDate(new Date());
					mchtSettledApplyrecord.setCreateBy(platformcontactId.get(0).getStaffId());
					mchtSettledApplyrecord.setDelFlag("0");
					mchtSettledApplyrecordService.insertSelective(mchtSettledApplyrecord);					
				

				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				  e.printStackTrace();
				 code = StateCode.JSON_AJAX_ERROR.getStateCode();
				 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage, resMap);
		}
		
		//查看更多开发记录
		@RequestMapping(value = "/mchtSettled/mchtSettledApplyRecordselect.shtml")
		public ModelAndView mchtSettledApplyRecordselect(HttpServletRequest request) {
			String rtPage = "/mchtSettled/RecordList";// 重定向
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("settledApplyId", request.getParameter("settledApplyId"));				
			return new ModelAndView(rtPage, resMap);
		}
		
		//查看更多开发记录数据
		@RequestMapping(value = "/mchtSettled/mchtSettledApplyrecordData.shtml")
		@ResponseBody
		public Map<String, Object> mchtSettledApplyrecordData(HttpServletRequest request,Page page) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			Integer totalCount = 0;
			try {
				
				MchtSettledApplyRecordCustomExample mchtSettledApplyRecordCustomExample=new MchtSettledApplyRecordCustomExample();
				MchtSettledApplyRecordCustomExample.Criteria mchtSettledApplyRecordCriteria=mchtSettledApplyRecordCustomExample.createCriteria();
				mchtSettledApplyRecordCriteria.andDelFlagEqualTo("0");
				mchtSettledApplyRecordCustomExample.setOrderByClause("id desc");
				if (!StringUtil.isEmpty(request.getParameter("settledApplyId"))) {
					Integer settledApplyId=Integer.valueOf(request.getParameter("settledApplyId"));
					mchtSettledApplyRecordCriteria.andMchtSettledApplyIdEqualTo(settledApplyId);
				}
				totalCount = mchtSettledApplyrecordService.countMchtSettledApplyRecordCustomExample(mchtSettledApplyRecordCustomExample);	
				mchtSettledApplyRecordCustomExample.setLimitStart(page.getLimitStart());
				mchtSettledApplyRecordCustomExample.setLimitSize(page.getLimitSize());
				List<MchtSettledApplyRecordCustom> mchtSettledApplyRecordCustoms=mchtSettledApplyrecordService.selectMchtSettledApplyRecordCustomExample(mchtSettledApplyRecordCustomExample);
			
				resMap.put("Rows", mchtSettledApplyRecordCustoms);
				resMap.put("Total", totalCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resMap;
		}
		
		//商务开发记录
		@RequestMapping(value = "/mchtSettled/mchtSettledApplyRecordwhole.shtml")
		public ModelAndView mchtSettledApplyRecordwhole(HttpServletRequest request) {
			String rtPage = "/mchtSettled/mchtSettledApplyRecordwhole";
			Map<String, Object> resMap = new HashMap<String, Object>();	
			 PlatformContactExample platformContactExample=new PlatformContactExample();
             PlatformContactExample.Criteria platformContactCriteria=platformContactExample.createCriteria();
             platformContactCriteria.andDelFlagEqualTo("0").andContactTypeEqualTo("1").andStatusEqualTo("0");
             List<PlatformContact> platformcontactId = platformContactService.selectByExample(platformContactExample);
             resMap.put("platformcontactId", platformcontactId);
			 return new ModelAndView(rtPage,resMap);
		}
		
		
		//全部开发记录列表数据
		@RequestMapping(value = "/mchtSettled/mchtSettledAPPlyRecordwhole/data.shtml")
		@ResponseBody
		public Map<String, Object> mchtSettledAPPlyRecordwhole(HttpServletRequest request,Page page) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			Integer totalCount = 0;
			try {
							
				MchtSettledApplyCustomExample mchtSettledApplyCustomExamples=new MchtSettledApplyCustomExample();
				MchtSettledApplyCustomExample.MchtSettledApplyCustomCriteria mchtSettledApplyCustomCriteria=mchtSettledApplyCustomExamples.createCriteria();
				mchtSettledApplyCustomCriteria.andDelFlagEqualTo("0");
				mchtSettledApplyCustomExamples.setOrderByClause("a.id desc");
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
					mchtSettledApplyCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
				}
				
				if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
					mchtSettledApplyCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
				}		
				if(!StringUtil.isEmpty(request.getParameter("companyName")) ){
					String companyName=request.getParameter("companyName");
					mchtSettledApplyCustomCriteria.andCompanyNameLike("%"+companyName+"%");
				}
				if (!StringUtil.isEmpty(request.getParameter("customerSource"))) {
					mchtSettledApplyCustomCriteria.andCustomerSourceEqualTo(request.getParameter("customerSource"));
				}
				if (!StringUtil.isEmpty(request.getParameter("platformcontactId"))) {
					mchtSettledApplyCustomCriteria.andPlatformContactIdEqualTo(Integer.valueOf(request.getParameter("platformcontactId")));
				}
				if (!StringUtil.isEmpty(request.getParameter("settledType"))) {
					mchtSettledApplyCustomCriteria.andSettledTypeEqualTo(request.getParameter("settledType"));
				}
				totalCount = mchtSettledApplyService.countMchtSettledApplyCustomByExample(mchtSettledApplyCustomExamples);	
				mchtSettledApplyCustomExamples.setLimitStart(page.getLimitStart());
				mchtSettledApplyCustomExamples.setLimitSize(page.getLimitSize());
				List<MchtSettledApplyCustom> mchtSettledApplyCustoms=mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyCustomExamples);
			
				resMap.put("Rows", mchtSettledApplyCustoms);
				resMap.put("Total", totalCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resMap;
		}
		
		//重新分配招商负责人
		@RequestMapping(value = "/mchtSettled/distribution/list.shtml")
		public ModelAndView distribution(Model model,HttpServletRequest request) {
			String rtPage = "/mchtSettled/distributionList";
			Map<String, Object> resMap = new HashMap<String, Object>();
				
			    String ids=request.getParameter("ids");
			
				PlatformContactExample platformContactExample=new PlatformContactExample();
				PlatformContactExample.Criteria platformContactCriteria=platformContactExample.createCriteria();
				platformContactCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
				
				List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExample);
				
				resMap.put("platformContacts", platformContacts);
				resMap.put("ids", ids);	
			
						
				SysStaffInfo sysStaffInfo=sysStaffInfoService.selectByPrimaryKey(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				if (sysStaffInfo.getIsManagement().equals("1")) {
					
					PlatformContactExample example = new PlatformContactExample();
					PlatformContactExample.Criteria c = example.createCriteria();
					c.andDelFlagEqualTo("0");
					c.andStaffIdEqualTo(sysStaffInfo.getStaffId());
					c.andStatusEqualTo("0");
					c.andContactTypeEqualTo("1");
					List<PlatformContact> platformContact = platformContactService.selectByExample(example);
					if(platformContact!=null && platformContact.size()>0){
						resMap.put("isZs", true);//招商管理层才能重新分配
					}
									
			}		
			
			return new ModelAndView(rtPage,resMap);
		}
			
	    //重新对接人分配保存
		@RequestMapping(value = "/mchtSettled/distribution/submit.shtml")
		public ModelAndView distributionSubmit(HttpServletRequest request) {
			String rtPage = "/success/success";
			Map<String, Object> resMap = new HashMap<String, Object>(); 
			String code = null;
			String msg =null;
			try {
				Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				
				List <String> idsStr=Arrays.asList(request.getParameter("ids").split(","));
				List<Integer> ids =new ArrayList<Integer>(idsStr.size());
				
				for(int i=0,j=idsStr.size();i<j;i++){
					ids.add(Integer.parseInt(idsStr.get(i)));   
		        } 
				
				MchtSettledApply mchtSettledApply=new MchtSettledApply();
				mchtSettledApply.setPlatformContactId(platformContactId);
				mchtSettledApply.setUpdateBy(staffId);
				mchtSettledApply.setUpdateDate(new Date());
				
				MchtSettledApplyExample mchtSettledApplyExample=new MchtSettledApplyExample();
				MchtSettledApplyExample.Criteria mchtSettledApplyCriteria=mchtSettledApplyExample.createCriteria();
				mchtSettledApplyCriteria.andDelFlagEqualTo("0").andStatusNotEqualTo("1").andIdIn(ids);
				
				mchtSettledApplyService.updateByExampleSelective(mchtSettledApply, mchtSettledApplyExample);
				
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				  e.printStackTrace();
				 code = StateCode.JSON_AJAX_ERROR.getStateCode();
				 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);

			return new ModelAndView(rtPage, resMap);
		}
		
		
		        //选择批量导出
				@RequestMapping(value = "/mchtSettled/mchtSettledApplyRecordselectivity.shtml")
				public void mchtSettledApplyRecordselectivity(HttpServletRequest request, String id, HttpServletResponse response) throws Exception {
					try {
						
					     String[] split=id.split(",");
					
					     List<String[]> datas = new ArrayList<String[]>();
					     SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					for (int i = 0; i < split.length; i++) {			
						 int mchtsettledApplyRecordid=Integer.valueOf(split[i]);	
						 
						    MchtSettledApplyCustomExample mchtSettledApplyCustomExamples=new MchtSettledApplyCustomExample();
							MchtSettledApplyCustomExample.MchtSettledApplyCustomCriteria mchtSettledApplyCustomCriteria=mchtSettledApplyCustomExamples.createCriteria();
							mchtSettledApplyCustomCriteria.andDelFlagEqualTo("0").andIdEqualTo(mchtsettledApplyRecordid);
							mchtSettledApplyCustomExamples.setOrderByClause("a.id desc");
							
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
								mchtSettledApplyCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
							}
							
							if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
								mchtSettledApplyCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
							}		
							if(!StringUtil.isEmpty(request.getParameter("companyName")) ){
								String companyName=request.getParameter("companyName");
								mchtSettledApplyCustomCriteria.andCompanyNameLike("%"+companyName+"%");
							}
							if (!StringUtil.isEmpty(request.getParameter("customerSource"))) {
								mchtSettledApplyCustomCriteria.andCustomerSourceEqualTo(request.getParameter("customerSource"));
							}
							if (!StringUtil.isEmpty(request.getParameter("platformcontactId"))) {
								mchtSettledApplyCustomCriteria.andPlatformContactIdEqualTo(Integer.valueOf(request.getParameter("platformcontactId")));
							}
						
							List<MchtSettledApplyCustom> mchtSettledApplyCustoms=mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyCustomExamples);
					
					      for (MchtSettledApplyCustom mchtSettledApplyCustom : mchtSettledApplyCustoms) {
					    	   String customerSource="";
					    	   String links="";	
					    	   String recoRds="";
					    	   if (mchtSettledApplyCustom.getCustomerSource().equals("1")) {
					    		   customerSource="主管分配";
							   }else if (mchtSettledApplyCustom.getCustomerSource().equals("2")) {
								   customerSource="个人开发";
							   }
					    	   if (!StringUtil.isEmpty(mchtSettledApplyCustom.getTmallShop())) {
					    		   links += " 天猫: "+ mchtSettledApplyCustom.getTmallShop()+ "\n";
							   }
					    	   if (!StringUtil.isEmpty(mchtSettledApplyCustom.getTaobaoShop())) {
					    		   links += " 淘宝: "+mchtSettledApplyCustom.getTaobaoShop()+ "\n";
							   }
					    	   if (!StringUtil.isEmpty(mchtSettledApplyCustom.getJdShop())) {
					    		   links += " 京东: "+mchtSettledApplyCustom.getJdShop()+ "\n";
							   }
					    	   if (!StringUtil.isEmpty(mchtSettledApplyCustom.getVipsShop())) {
					    		   links += " 唯品会: "+mchtSettledApplyCustom.getVipsShop()+ "\n";
							   }
					    	   if (!StringUtil.isEmpty(mchtSettledApplyCustom.getOtherShop())) {
					    		   links += " 其他: "+mchtSettledApplyCustom.getOtherShop()+ "\n";
							   }
					    	   MchtSettledApplyRecordCustomExample mchtSettledApplyRecordCustomExample=new MchtSettledApplyRecordCustomExample();
					    	   mchtSettledApplyRecordCustomExample.createCriteria().andDelFlagEqualTo("0").andMchtSettledApplyIdEqualTo(mchtSettledApplyCustom.getId());
					    	   mchtSettledApplyRecordCustomExample.setOrderByClause("id desc");
					    	   List<MchtSettledApplyRecordCustom> mchtSettledApplyRecordCustom=mchtSettledApplyrecordService.selectMchtSettledApplyRecordCustomExample(mchtSettledApplyRecordCustomExample);
					    	   if (mchtSettledApplyRecordCustom.size()>0) {
					    		   for (MchtSettledApplyRecordCustom mchtSettledApplyRecordCustom2 : mchtSettledApplyRecordCustom) {
					    			   recoRds +=(dateFormat2.format(mchtSettledApplyRecordCustom2.getCreateDate())+"  "+mchtSettledApplyRecordCustom2.getRecord())+ "\n";
									  
								  }
					    		  
							   }
					    	   String[] data = {
					    			    dateFormat.format(mchtSettledApplyCustom.getCreateDate()),
					    			    mchtSettledApplyCustom.getPlatformContactName()==null?"":mchtSettledApplyCustom.getPlatformContactName(),
					    			    customerSource,
					    			    mchtSettledApplyCustom.getProductTypeMain()==null?"":mchtSettledApplyCustom.getProductTypeMain(),
					    			    mchtSettledApplyCustom.getProductBrandMain()==null?"":mchtSettledApplyCustom.getProductBrandMain(),
					    			    links,
					    			    mchtSettledApplyCustom.getContactName()==null?"":mchtSettledApplyCustom.getContactName(),
					    			    mchtSettledApplyCustom.getContactJob()==null?"":mchtSettledApplyCustom.getContactJob(),
					    			    mchtSettledApplyCustom.getPhone()==null?"":mchtSettledApplyCustom .getPhone(),
					    			    mchtSettledApplyCustom.getQq()==null?"":mchtSettledApplyCustom.getQq(),
					    			    mchtSettledApplyCustom.getWechat()==null?"":mchtSettledApplyCustom.getWechat(),		
					    			    mchtSettledApplyCustom.getCompanyTel()==null?"":mchtSettledApplyCustom.getCompanyTel(),	
					    			    mchtSettledApplyCustom.getEmail()==null?"":mchtSettledApplyCustom.getEmail(),
					    			    recoRds
					    			    /*(mchtSettledApplyCustom.getCreateDateMax()==null?"":("["+dateFormat.format(mchtSettledApplyCustom.getCreateDateMax())+"]")) + (mchtSettledApplyCustom.getRecoRd()==null?"":mchtSettledApplyCustom.getRecoRd())*/		
					    			   				    			   
					    	   };
					    	   datas.add(data);
						  }				
				   }
					String[] titles = { "创建时间", "招商专员", "客户 来源", "经营品类","品牌名称","在做渠道店铺链接","联系人","职务","手机","QQ","微信号","公司座机","企业邮箱","开发情况记录"};
					ExcelBean excelBean = new ExcelBean(dateFormat2.format(new Date())+"商务开发记录表.xls", "商务开发记录", titles);
					excelBean.setDataList(datas);
					ExcelUtils.export(excelBean,response);			
				  } catch (Exception e) {
					  e.printStackTrace();
			}
				
     }
				
				//批量导出
				@RequestMapping(value = "/mchtSettled/export/mchtSettledApplyRecordData.shtml")
				public void mchtSettledApplyRecordData(HttpServletRequest request,HttpServletResponse response) throws Exception {
					try {
			
					        List<String[]> datas = new ArrayList<String[]>();
					        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
																				 
						    MchtSettledApplyCustomExample mchtSettledApplyCustomExamples=new MchtSettledApplyCustomExample();
							MchtSettledApplyCustomExample.MchtSettledApplyCustomCriteria mchtSettledApplyCustomCriteria=mchtSettledApplyCustomExamples.createCriteria();
							mchtSettledApplyCustomCriteria.andDelFlagEqualTo("0");
							mchtSettledApplyCustomExamples.setOrderByClause("a.id desc");
							
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
								mchtSettledApplyCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
							}
							
							if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
								mchtSettledApplyCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
							}		
							if(!StringUtil.isEmpty(request.getParameter("companyName")) ){
								String companyName=request.getParameter("companyName");
								mchtSettledApplyCustomCriteria.andCompanyNameLike("%"+companyName+"%");
							}
							if (!StringUtil.isEmpty(request.getParameter("customerSource"))) {
								mchtSettledApplyCustomCriteria.andCustomerSourceEqualTo(request.getParameter("customerSource"));
							}
							if (!StringUtil.isEmpty(request.getParameter("platformcontactId"))) {
								mchtSettledApplyCustomCriteria.andPlatformContactIdEqualTo(Integer.valueOf(request.getParameter("platformcontactId")));
							}
						
						List<MchtSettledApplyCustom> mchtSettledApplyCustoms=mchtSettledApplyService.selectMchtSettledApplyCustomByExample(mchtSettledApplyCustomExamples);
					       
						String[] titles = { "创建时间", "招商专员", "客户 来源", "经营品类","品牌名称","在做渠道店铺链接","联系人","职务","手机","QQ","微信号","公司座机","企业邮箱","开发情况记录"};
						ExcelBean excelBean = new ExcelBean(dateFormat2.format(new Date())+"商务开发记录表.xls", "商务开发记录", titles);
						
					      for (MchtSettledApplyCustom mchtSettledApplyCustom : mchtSettledApplyCustoms) {
					    	   String customerSource="";
					    	   String linksString="";
					    	   String recoRd="";
					    	   if (mchtSettledApplyCustom.getCustomerSource().equals("1")) {
					    		   customerSource="主管分配";
							   }else if (mchtSettledApplyCustom.getCustomerSource().equals("2")) {
								   customerSource="个人开发";
							   }
					    	   if (!StringUtil.isEmpty(mchtSettledApplyCustom.getTmallShop())) {
					    		   linksString += " 天猫: "+ mchtSettledApplyCustom.getTmallShop()+ "\n";
							   }
					    	   if (!StringUtil.isEmpty(mchtSettledApplyCustom.getTaobaoShop())) {
					    		   linksString += " 淘宝: "+mchtSettledApplyCustom.getTaobaoShop()+ "\n";
							   }
					    	   if (!StringUtil.isEmpty(mchtSettledApplyCustom.getJdShop())) {
					    		   linksString += " 京东: "+mchtSettledApplyCustom.getJdShop()+ "\n";
							   }
					    	   if (!StringUtil.isEmpty(mchtSettledApplyCustom.getVipsShop())) {
					    		   linksString += " 唯品会: "+mchtSettledApplyCustom.getVipsShop()+ "\n";
							   }
					    	   if (!StringUtil.isEmpty(mchtSettledApplyCustom.getOtherShop())) {
					    		   linksString += " 其他: "+mchtSettledApplyCustom.getOtherShop()+ "\n";
							   }	
					    	   
					    	   MchtSettledApplyRecordCustomExample mchtSettledApplyRecordCustomExample=new MchtSettledApplyRecordCustomExample();
					    	   mchtSettledApplyRecordCustomExample.createCriteria().andDelFlagEqualTo("0").andMchtSettledApplyIdEqualTo(mchtSettledApplyCustom.getId());
					    	   mchtSettledApplyRecordCustomExample.setOrderByClause("id desc");
					    	   List<MchtSettledApplyRecordCustom> mchtSettledApplyRecordCustom=mchtSettledApplyrecordService.selectMchtSettledApplyRecordCustomExample(mchtSettledApplyRecordCustomExample);
					    	   if (mchtSettledApplyRecordCustom.size()>0) {
					    		   for (MchtSettledApplyRecordCustom mchtSettledApplyRecordCustom2 : mchtSettledApplyRecordCustom) {
					    			   recoRd +=(dateFormat2.format(mchtSettledApplyRecordCustom2.getCreateDate())+"  "+mchtSettledApplyRecordCustom2.getRecord())+ "\n";
									  
								  }
					    		  
							   }
					    	   		    	   
					    	  String[] data = {
					    			    dateFormat.format(mchtSettledApplyCustom.getCreateDate()),
					    			    mchtSettledApplyCustom.getPlatformContactName()==null?"":mchtSettledApplyCustom.getPlatformContactName(),
					    			    customerSource,
					    			    mchtSettledApplyCustom.getProductTypeMain()==null?"":mchtSettledApplyCustom.getProductTypeMain(),
					    			    mchtSettledApplyCustom.getProductBrandMain()==null?"":mchtSettledApplyCustom.getProductBrandMain(),
					    			    linksString,
					    			    mchtSettledApplyCustom.getContactName()==null?"":mchtSettledApplyCustom.getContactName(),
					    			    mchtSettledApplyCustom.getContactJob()==null?"":mchtSettledApplyCustom.getContactJob(),
					    			    mchtSettledApplyCustom.getPhone()==null?"":mchtSettledApplyCustom .getPhone(),
					    			    mchtSettledApplyCustom.getQq()==null?"":mchtSettledApplyCustom.getQq(),
					    			    mchtSettledApplyCustom.getWechat()==null?"":mchtSettledApplyCustom.getWechat(),	 		
					    			    mchtSettledApplyCustom.getCompanyTel()==null?"":mchtSettledApplyCustom.getCompanyTel(),
					    			    mchtSettledApplyCustom.getEmail()==null?"":mchtSettledApplyCustom.getEmail(), 		
					    			    recoRd		
					    			  /* (mchtSettledApplyCustom.getCreateDateMax()==null?"":("["+dateFormat.format(mchtSettledApplyCustom.getCreateDateMax())+"]")) + (mchtSettledApplyCustom.getRecoRd()==null?"":mchtSettledApplyCustom.getRecoRd())*/
					    			   				    			   
					    	   };					    	
					    	  
					    	   datas.add(data);
						  }				
					      excelBean.setDataList(datas);
					      ExcelUtils.export(excelBean,response);							      
			
				    } catch (Exception e) {
					  e.printStackTrace();
			}
				
     }
				//导入界面
				@RequestMapping(value = "/mchtSettled/editmchtSettledAPPlyRecordwhole.shtml")
				public ModelAndView toConfirm(HttpServletRequest request) {
					String rtPage = "/mchtSettled/mchtSettledApplyRecordData";
					Map<String, Object> resMap = new HashMap<String, Object>();
					
					PlatformContactExample platformContactExample=new PlatformContactExample();
					PlatformContactExample.Criteria platformContactCriteria=platformContactExample.createCriteria();
					platformContactCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
					
					List<PlatformContact> platformContact=platformContactService.selectByExample(platformContactExample);
					
					resMap.put("platformContact", platformContact);
					return new ModelAndView(rtPage,resMap);
				}			
				
				//导入表格数据保存
				@RequestMapping(value = "/mchtSettled/mchtSettledApplyRecordDataList.shtml")
				@ResponseBody
				public Map<String, Object> save(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					try {
						String platformcontactId = request.getParameter("platformcontactId");
						String excelFilePath = request.getParameter("excelFilePath");
						
						InputStream stream = MchtSettledController.class.getResourceAsStream("/base_config.properties");
			  	        Properties properties = new Properties();
					    properties.load(stream);
					    String defaultPath = properties.getProperty("annex.filePath");
					    stream.close();
						File file = new File(defaultPath+excelFilePath);
						List<ArrayList<String>> dataList = ExcelUtils.swread(file, file.getName(), 14,"0");
						if(dataList == null || dataList.size() < 1) {
							resMap.put("returnCode", "4004");
							resMap.put("returnMsg", "Excel表格没有数据，请重新导入");
							return resMap;
						}
						
						Date date = new Date();
						List<MchtSettledApply> mchtSettledApplyList=new ArrayList<MchtSettledApply>();
						int total = dataList.size()-1;
						int successTotal = 0;
						int errorTotal = 0;
					    String recordsString="";
						for (int i = 1; i < dataList.size(); i++) {
							
							List<String> data = dataList.get(i);				
							String platFormcontact=data.get(0);
							String customersource=data.get(1);                            
							String companyname=data.get(2);
							String producttype=data.get(3);
							String productbrand=data.get(4);
						    String channellink=data.get(5);
						    String contactname=data.get(6);
						    String contactjob=data.get(7);
						    String phone=data.get(8);
						    String qq=data.get(9);
						    String wechat=data.get(10);
						    String companyTel=data.get(11);
						    String email=data.get(12);
							String record=data.get(13);
							recordsString=record;
							
							String customerSource="";
							if (customersource.equals("主管分配")) {
								customerSource="1";										
							}else if (customersource.equals("个人开发")) {
								customerSource="2";
							}	
							
							
							if(StringUtils.isEmpty(platFormcontact) && StringUtils.isEmpty(customersource) && StringUtils.isEmpty(companyname) && StringUtils.isEmpty(producttype) && StringUtils.isEmpty(productbrand) && StringUtils.isEmpty(channellink) && StringUtils.isEmpty(contactname) && StringUtil.isEmpty(contactjob) && StringUtils.isEmpty(phone) && StringUtil.isEmpty(qq) && StringUtil.isEmpty(wechat) && StringUtils.isEmpty(companyTel) && StringUtil.isEmpty(email) && StringUtil.isEmpty(record)){
					        	errorTotal++;
					        	continue;
					         } 
							       
							      MchtSettledApplyExample meApplyExample=new MchtSettledApplyExample();
							      meApplyExample.createCriteria().andDelFlagEqualTo("0");
							      List<MchtSettledApply> mchtSettledApplies=mchtSettledApplyService.selectByExample(meApplyExample);
							      if (mchtSettledApplies.size()>0) {
							    	  for (MchtSettledApply mchtSettledApply : mchtSettledApplies) {
										  if (Integer.valueOf(platformcontactId).equals(mchtSettledApply.getPlatformContactId()) && customerSource.equals(mchtSettledApply.getCustomerSource()) && companyname.equals(mchtSettledApply.getCompanyName()) && producttype.equals(mchtSettledApply.getProductTypeMain()) && productbrand.equals(mchtSettledApply.getProductBrandMain()) && contactname.equals(mchtSettledApply.getContactName()) && contactjob.equals(mchtSettledApply.getContactJob()) && phone.equals(mchtSettledApply.getPhone()) && qq.equals(mchtSettledApply.getQq()) && wechat.equals(mchtSettledApply.getWechat()) && companyTel.equals(mchtSettledApply.getCompanyTel()) && email.equals(mchtSettledApply.getEmail())){
											  resMap.put("returnCode", "4004");
											  resMap.put("returnMsg", "已存在重复数据，请重新导入!");
											  return resMap;								  
											
										}									  
										
									}
									
								  }
							
									MchtSettledApply  mchtSettledApply=new MchtSettledApply();
									mchtSettledApply.setCreateDate(new Date());									
								    mchtSettledApply.setCustomerSource(customerSource);																																	
									mchtSettledApply.setCompanyName(companyname);
									mchtSettledApply.setProductTypeMain(producttype);
									mchtSettledApply.setProductBrandMain(productbrand);									
									
								    if (channellink.equals("https://www.tmall.com/")) {
								    	mchtSettledApply.setTmallShop(channellink);
									}
								    if (channellink.equals("https://www.taobao.com/")) {
								    	mchtSettledApply.setTaobaoShop(channellink);
									}
								    if (channellink.equals("https://www.jd.com/")) {
								    	mchtSettledApply.setJdShop(channellink);
									}
								    if (channellink.equals("https://www.vip.com/")) {
								    	mchtSettledApply.setWechat(channellink);
									}
								    if (!channellink.equals("https://www.tmall.com/") && !channellink.equals("https://www.taobao.com/") && !channellink.equals("https://www.jd.com/") && !channellink.equals("https://www.vip.com/")) {
								    	mchtSettledApply.setOtherShop(channellink);
									}
									
								    mchtSettledApply.setContactName(contactname);
								    mchtSettledApply.setContactJob(contactjob);
								    mchtSettledApply.setPhone(phone);
								    mchtSettledApply.setQq(qq);
								    mchtSettledApply.setWechat(wechat);
								    mchtSettledApply.setCompanyTel(companyTel);
								    mchtSettledApply.setEmail(email);
									mchtSettledApply.setPlatformContactId(Integer.valueOf(platformcontactId));
									mchtSettledApply.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
									mchtSettledApply.setCreateDate(date);					
									mchtSettledApply.setIsimport("1");
									mchtSettledApply.setDelFlag("0");									
									mchtSettledApplyList.add(mchtSettledApply);
																		
						    }
						
						if (mchtSettledApplyList!=null && mchtSettledApplyList.size()>0) {
							
							for (MchtSettledApply mchtSettledApply1 : mchtSettledApplyList) {
								
								mchtSettledApplyService.insertSelective(mchtSettledApply1);
								
							if (!StringUtil.isEmpty(recordsString)) {
								MchtSettledApplyRecord mchtSettledApplyRecord=new  MchtSettledApplyRecord();
								mchtSettledApplyRecord.setMchtSettledApplyId(mchtSettledApply1.getId());
								mchtSettledApplyRecord.setRecord(recordsString);
								mchtSettledApplyRecord.setCreateBy(Integer.valueOf(platformcontactId));
								mchtSettledApplyRecord.setCreateDate(date);
								mchtSettledApplyRecord.setDelFlag("0");
								
								mchtSettledApplyrecordService.insertSelective(mchtSettledApplyRecord);
								
							  }
								
							}
							
							successTotal=mchtSettledApplyList.size();
						}
										
						
						resMap.put("total", total);
						resMap.put("successTotal", successTotal);
						resMap.put("errorTotal", errorTotal);
						resMap.put("returnCode", "0000");
						resMap.put("returnMsg", "确认成功");
					} catch (Exception e) {
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "导入失败，请稍后重试");
						e.printStackTrace();
					}
					return resMap;
				}	
				
				
				//下载开发记录模板
				@RequestMapping(value = "/mchtSettled/mchtSettledApplyRecordDatat.shtml")
				public void mchtSettledApplyRecordDatat(HttpServletRequest request,HttpServletResponse response) throws Exception {
					try {
							       
					    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
																				 								       
						String[] titles = {"招商人员", "客户 来源", "公司名称", "品类","品牌名称","在做渠道店铺链接","联系人","职务","手机","QQ", "微信", "公司座机", "企业邮箱","开发情况记录"};
						ExcelBean excelBean = new ExcelBean(dateFormat2.format(new Date())+"下载开发记录模板.xls", "开发记录模板", titles);					    
					    ExcelUtils.export(excelBean,response);							      
			
				    } catch (Exception e) {
					  e.printStackTrace();
			}
				
      }
				
				
				//招商会员开发记录
				@RequestMapping("/mchtSettled/canvassDevelopmentRecords.shtml")
				  public ModelAndView canvassDevelopmentRecords(HttpServletRequest request){
				    String rtPage = "/mchtSettled/canvassDevelopmentRecordsList";
				    Map resMap = new HashMap();

				    String staffID = getSessionStaffBean(request).getStaffID();
				    String isManagement = getSessionStaffBean(request).getIsManagement();
				    resMap.put("isManagement", isManagement);
				    resMap.put("staffID", staffID);
				    SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
				    SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
				    sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
				    List sysStaffInfoCustomList = this.sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
				    resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);

				    return new ModelAndView(rtPage, resMap);
				  }

				  @RequestMapping("/mchtSettled/canvassDevelopmentRecordsData.shtml")
				  @ResponseBody
				  public Map<String, Object> canvassDevelopmentRecordsData(HttpServletRequest request, Page page){
				    Map resMap = new HashMap();
				    
				    try{
				      String platContactStaffId = request.getParameter("platContactStaffId");
				      String mchtSettledApplyStatus = request.getParameter("mchtSettledApplyStatus");
				      String memberId = request.getParameter("memberId");
				      String memberMobile = request.getParameter("memberMobile");
				      String memberNick = request.getParameter("memberNick");
				      String memberCreateDate = request.getParameter("memberCreateDate");
				      String memberEndDate = request.getParameter("memberEndDate");
				      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				     MemberInfoCustomExample memberInfoCustomExample = new MemberInfoCustomExample();
				     MemberInfoCustomCriteria memberInfoCreateCriteria = memberInfoCustomExample.createCriteria();
				     memberInfoCreateCriteria.andDelFlagEqualTo("0");
				     memberInfoCreateCriteria.andDontShowZsPlatformContactIdIsNull();//没有对接人的不显示
						
				      if (!StringUtil.isEmpty(platContactStaffId)) {
				    	  PlatformContactExample example  =new PlatformContactExample();
				    	  example.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(platContactStaffId)).andStatusEqualTo("0");
						List<PlatformContact> platformContactList = platformContactService.selectByExample(example);
						if(platformContactList!=null && platformContactList.size()>0){
							//createCriteria.andZsPlatformContactIdEqualTo(platformContactList.get(0).getId());
							memberInfoCreateCriteria.andZsPlatformContactIdEqualTo(platformContactList.get(0).getId());
						}
				      }
				      if (!StringUtil.isEmpty(memberId)) {
				    	  memberInfoCreateCriteria.andIdEqualTo(Integer.parseInt(memberId));
				      }
					 if (!StringUtil.isEmpty(memberMobile)) {
						 memberInfoCreateCriteria.andMobileEqualTo(memberMobile);
					 }
					 if (!StringUtil.isEmpty(memberNick)) {
						 memberInfoCreateCriteria.andNickEqualTo(memberNick);
					 }
					 if (!StringUtil.isEmpty(memberCreateDate)) {
						 //createCriteria.andMemberCreateDateGreaterThanOrEqualTo(memberCreateDate+" 00:00:00");
						 memberInfoCreateCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(memberCreateDate+" 00:00:00"));
					 }
					 if (!StringUtil.isEmpty(memberEndDate)) {
						 //createCriteria.andMemberEndLessThanOrEqualTo(memberEndDate+" 59:59:59");
						 memberInfoCreateCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(memberEndDate+" 59:59:59"));
					 }
				      
				      if ("1".equals(mchtSettledApplyStatus)) {
				        //createCriteria.andMchtSettledApplyIdIsNotNull();
				        memberInfoCreateCriteria.andMchtSettledApplyIdIsNotNull();
				      }
				      if ("2".equals(mchtSettledApplyStatus)) { 
				        //createCriteria.andMchtSettledApplyIdIsNull();
				        memberInfoCreateCriteria.andMchtSettledApplyIdIsNull();
				      }
				     
				     
				     memberInfoCustomExample.setLimitStart(page.getLimitStart());
				      memberInfoCustomExample.setLimitSize(page.getLimitSize());
				      
				     Integer totalCount = memberInfoService.countByCustomExample(memberInfoCustomExample);
					List<MemberInfoCustom> MemberInfoCustomList = memberInfoService.selectByCustomExample(memberInfoCustomExample );
					

				      resMap.put("Rows", MemberInfoCustomList);
				      resMap.put("Total", totalCount);
				    }
				    catch (Exception e) {
				      e.printStackTrace();
				    }
				    return resMap;
				  }

				  @RequestMapping("/mchtSettled/detailsOfInvitingBusiness.shtml")
				  public void detailsOfInvitingBusiness(HttpServletRequest request, HttpServletResponse response) throws Exception{
				    try {
					      String platContactStaffId = request.getParameter("platContactStaffId");
					      String mchtSettledApplyStatus = request.getParameter("mchtSettledApplyStatus");
					      String memberId = request.getParameter("memberId");
					      String memberMobile = request.getParameter("memberMobile");
					      String memberNick = request.getParameter("memberNick");
					      String memberCreateDate = request.getParameter("memberCreateDate");
					      String memberEndDate = request.getParameter("memberEndDate");
					      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
					      
					      MemberInfoCustomExample memberInfoCustomExample = new MemberInfoCustomExample();
						  MemberInfoCustomCriteria memberInfoCreateCriteria = memberInfoCustomExample.createCriteria();
						  memberInfoCreateCriteria.andDelFlagEqualTo("0");
						     
								
						      if (!StringUtil.isEmpty(platContactStaffId)) {
						    	  PlatformContactExample example  =new PlatformContactExample();
						    	  example.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(platContactStaffId)).andStatusEqualTo("0");
								List<PlatformContact> platformContactList = platformContactService.selectByExample(example);
								if(platformContactList!=null && platformContactList.size()>0){
									memberInfoCreateCriteria.andZsPlatformContactIdEqualTo(platformContactList.get(0).getId());
								}
						      }
						      if (!StringUtil.isEmpty(memberId)) {
						    	  memberInfoCreateCriteria.andIdEqualTo(Integer.parseInt(memberId));
						      }
							 if (!StringUtil.isEmpty(memberMobile)) {
								 memberInfoCreateCriteria.andMobileEqualTo(memberMobile);
							 }
							 if (!StringUtil.isEmpty(memberNick)) {
								 memberInfoCreateCriteria.andNickEqualTo(memberNick);
							 }
							 if (!StringUtil.isEmpty(memberCreateDate)) {
								 memberInfoCreateCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(memberCreateDate+" 00:00:00"));
							 }
							 if (!StringUtil.isEmpty(memberEndDate)) {
								 memberInfoCreateCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(memberEndDate+" 59:59:59"));
							 }
						      
						      if ("1".equals(mchtSettledApplyStatus)) {
						        memberInfoCreateCriteria.andMchtSettledApplyIdIsNotNull();
						      }
						      if ("2".equals(mchtSettledApplyStatus)) { 
						        memberInfoCreateCriteria.andMchtSettledApplyIdIsNull();
						      }
						     
						  	List<MemberInfoCustom> MemberInfoCustomList = memberInfoService.selectByCustomExample(memberInfoCustomExample );
				      String[] titles = { "会员ID", "会员昵称", "会员联系电话", "会员注册时间", "是否申请入驻", "所属专员" };
				      ExcelBean excelBean = new ExcelBean("导出招商会员开发记录列表.xls", 
				        "导出招商会员开发记录列表", titles);
				      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				      List datas = new ArrayList();
				      for (MemberInfoCustom memberInfoCustom : MemberInfoCustomList) {
				        String sourceDesc = "";
				        if (!StringUtils.isEmpty(memberInfoCustom.getMchtSettledApplyId()))
				          sourceDesc = "是";
				        else {
				          sourceDesc = "否";
				        }
				        String[] data = { 
				          memberInfoCustom.getId()+"",
				          memberInfoCustom.getNick(), 
				          memberInfoCustom.getMobile(), 
				          df.format(memberInfoCustom.getCreateDate()), 
				          sourceDesc, 
				          memberInfoCustom.getZsName() };

				        datas.add(data);
				      }
				      excelBean.setDataList(datas);
				      ExcelUtils.export(excelBean, response);
				    } catch (Exception e) {
				      e.printStackTrace();
				    }
				  }

				  @RequestMapping("/mchtSettled/statusConfirmation.shtml")
				  @ResponseBody
				  public ModelAndView statusConfirmation(HttpServletRequest request, Model model)
				  {
				    String rtPage = "/mchtSettled/statusConfirmation";
				    Map resMap = new HashMap();
				    String mchtSettledApplyId = request.getParameter("mchtSettledApplyId");
				    try {
				      if (!StringUtil.isEmpty(mchtSettledApplyId)) {
				        MchtSettledApply mchtSettledApply = (MchtSettledApply)this.mchtSettledApplyService.selectByPrimaryKey(Integer.valueOf(Integer.parseInt(mchtSettledApplyId)));
				        String status = mchtSettledApply.getStatus();
				        Integer mchtId = mchtSettledApply.getMchtId();
				        String depositConfirmStatus = mchtSettledApply.getDepositConfirmStatus();
				        if(mchtId==null){
				        String confirmationStatus = "1";
				        resMap.put("confirmationStatus", confirmationStatus);
				        if ("4".equals(status)) {
				          confirmationStatus = "2";
				          resMap.put("confirmationStatus", confirmationStatus);
				          if ("1".equals(depositConfirmStatus)) {
				            confirmationStatus = "3";
				            resMap.put("confirmationStatus", confirmationStatus);
				          		}
				        	}
				        }else {
				        	 String confirmationStatus = "4";
				        	 resMap.put("confirmationStatus", confirmationStatus);
						}
				        
				      }
				    }
				    catch (Exception e)
				    {
				      e.printStackTrace();
				    }
				    return new ModelAndView(rtPage, resMap);
				  }
				}
