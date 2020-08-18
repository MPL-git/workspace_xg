package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.dao.MchtBrandRateChangeCustomMapper;
import com.jf.dao.MchtBrandRateChangeMapper;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import com.jf.vo.ResponseMsg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("serial")
@Controller
public class CooperationChangeApplyController extends BaseController {

	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private CooperationChangeApplyService cooperationChangeApplyService;

	@Autowired
	private CooperationChangeUploadPicService cooperationChangeUploadPicService;

	@Autowired
	private CooperationChangeApplyLogService cooperationChangeApplyLogService;

	@Autowired
	private MchtInfoChangeLogService mchtInfoChangeLogService;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;

	@Autowired
	private PlatformContactService platformContactService;

	@Autowired
	private MchtPlatformContactService mchtPlatformContactService;

	@Autowired
	private MchtProductTypeService mchtProductTypeService;

	@Autowired
	private MchtDepositService mchtDepositService;

	@Autowired
	private MchtBrandRateChangeMapper mchtBrandRateChangeMapper;
	
	@Autowired
	private MchtProductBrandService mchtProductBrandService;
	
	@Autowired
	private MchtContractService mchtContractService;
	
	@Autowired
	private SysStaffProductTypeService sysStaffProductTypeService;
	
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	
	@Autowired
	private MchtBrandRateChangeCustomMapper mchtBrandRateChangeCustomMapper;
	/**
	 * 
	 * @Title    
	 * @Description 招商审核列表  
	 * @author yjc
	 * @date 2019年1月18日15:55:05
	 */
	@RequestMapping("/cooperationChangeApply/zsCheckList.shtml")
	public ModelAndView zsCheckList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cooperationChangeApply/zsCheckList");
		//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
		String staffID = this.getSessionStaffBean(request).getStaffID();
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		m.addObject("isManagement", isManagement);
		m.addObject("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);
		
		SysStaffProductTypeCustomExample staffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		staffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(staffProductTypeCustomExample);
		m.addObject("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
		
		SysStaffRoleExample e = new SysStaffRoleExample();
		e.createCriteria().andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(103);
	 	int count = sysStaffRoleService.countByExample(e);
	 	m.addObject("canCheck", count);
		return m;
	}
	
	/**
	 * 
	 * @Title    
	 * @Description (招商审核 )   
	 * @author yjc
	 * @date 2019年1月18日15:56:42
	 */
	@ResponseBody
	@RequestMapping("/cooperationChangeApply/zsCheckData.shtml")
	public Map<String, Object> zsCheckData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CooperationChangeApplyCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SysStaffProductTypeCustomExample staffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
			staffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(staffProductTypeCustomExample);
			if(sysStaffProductTypeCustomList==null || sysStaffProductTypeCustomList.size()==0){
				return resMap;
			}
			CooperationChangeApplyCustomExample e = new CooperationChangeApplyCustomExample();
			CooperationChangeApplyCustomExample.CooperationChangeApplyCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			String beginCreateDate = request.getParameter("beginCreateDate");
			String endCreateDate = request.getParameter("endCreateDate");
			String mchtCode = request.getParameter("mchtCode");
			String changeApplyType = request.getParameter("changeApplyType");
			String auditStatus = request.getParameter("auditStatus");
			String productTypeId = request.getParameter("productTypeId");
			String platContactStaffId = request.getParameter("platContactStaffId");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(beginCreateDate)){
				c.andCreateDateGreaterThanOrEqualTo(sdf.parse(beginCreateDate+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(endCreateDate)){
				c.andCreateDateLessThanOrEqualTo(sdf.parse(endCreateDate+" 23:59:59"));
			}
			if(!StringUtils.isEmpty(mchtCode)){
				c.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(changeApplyType)){
				c.andChangeApplyTypeEqualTo(changeApplyType);
			}
			if(!StringUtils.isEmpty(auditStatus)){
				c.andZsAuditStatusEqualTo(auditStatus);
			}
			if(!StringUtils.isEmpty(productTypeId)){
				c.andMchtProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}
			if(!StringUtils.isEmpty(platContactStaffId)){
				c.andPlatContactStaffIdEqualTo(Integer.parseInt(platContactStaffId));
			}
			totalCount = cooperationChangeApplyService.countByCustomExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			dataList = cooperationChangeApplyService.selectByCustomExample(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title    
	 * @Description 招商审核/查看   
	 * @author yjc
	 * @date 2019年1月19日09:42:07
	 */
	@RequestMapping("/cooperationChangeApply/toZsCheck.shtml")
	public ModelAndView toZsCheck(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("cooperationChangeApply/toZsCheck");
		String id = request.getParameter("id");
		CooperationChangeApplyCustomExample e = new CooperationChangeApplyCustomExample();
		e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
		List<CooperationChangeApplyCustom> cooperationChangeApplyCustoms = cooperationChangeApplyService.selectByCustomExample(e);
		CooperationChangeApplyCustom cooperationChangeApplyCustom = cooperationChangeApplyCustoms.get(0);
		MchtBrandRateChangeExample mbrce = new MchtBrandRateChangeExample();
		mbrce.createCriteria().andDelFlagEqualTo("0").andCooperationChangeApplyIdEqualTo(cooperationChangeApplyCustom.getId()).andMchtIdEqualTo(cooperationChangeApplyCustom.getMchtId());
		List<MchtBrandRateChange> mchtBrandRateChanges = mchtBrandRateChangeMapper.selectByExample(mbrce);
		model.addObject("mchtBrandRateChanges", mchtBrandRateChanges);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(cooperationChangeApplyCustom.getMchtId());
		if(mchtInfo.getChangeApplyType().indexOf("1")>=0){//1.店铺名称变更
			model.addObject("shopNameAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("2")>=0){//2.店铺主营类目变更
			model.addObject("mchtProductTypeAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("3")>=0){//3.品牌技术服务费变更
			model.addObject("popCommissionRateAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("4")>=0){//4.保证金变更
			model.addObject("depositAuth", 1);
		}
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(cooperationChangeApplyCustom.getMchtId()).andStatusEqualTo("1");
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
		model.addObject("mchtProductBrands", mchtProductBrands);
		model.addObject("mchtInfo", mchtInfo);
		model.addObject("shopTypeStatusList",DataDicUtil.getStatusList("BU_MCHT_INFO", "SHOP_TYPE"));
		model.addObject("cooperationChangeApply", cooperationChangeApplyCustom);
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		model.addObject("productTypes", productTypes);
		model.addObject("isView", request.getParameter("isView"));
		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		mchtProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId()).andIsMainEqualTo("1");
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mchtProductTypeExample);
		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
			ProductType productType = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
			model.addObject("productTypeName", productType.getName());
		}
		//变更函日志
		CooperationChangeApplyLogCustomExample logExample = new CooperationChangeApplyLogCustomExample();
		logExample.createCriteria().andDelFlagEqualTo("0").andCooperationChangeApplyIdEqualTo(cooperationChangeApplyCustom.getId());
		logExample.setOrderByClause("id desc");
		List<CooperationChangeApplyLogCustom> cooperationChangeApplyLogList = cooperationChangeApplyLogService.selectByCustomExample(logExample);
        if(cooperationChangeApplyLogList.size() > 0){
        	model.addObject("cooperationChangeApplyLogList",cooperationChangeApplyLogList);
		}
		return model;
	}
	
	/**
	 * 保存招商审核
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/cooperationChangeApply/zsCheck.shtml")
	@ResponseBody
	public Map<String, Object> zsCheck(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String shopType = request.getParameter("shopType");
			String businessFirms = request.getParameter("businessFirms");
			String brand = request.getParameter("brand");
			String productType = request.getParameter("productType");
			String shopName = request.getParameter("shopName");
			String productTypeId = request.getParameter("productTypeId");
			String deposit = request.getParameter("deposit");
			String mchtBrandRateChangeJsonStr = request.getParameter("mchtBrandRateChangeJsonStr");
			String zsAuditStatus = request.getParameter("zsAuditStatus");
			String zsAuditRemarks = request.getParameter("zsAuditRemarks");
			CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
			cooperationChangeApply.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cooperationChangeApply.setUpdateDate(new Date());
			if(!StringUtils.isEmpty(shopType)){
				cooperationChangeApply.setShopType(shopType);
			}
			if(!StringUtils.isEmpty(businessFirms)){
				cooperationChangeApply.setBusinessFirms(businessFirms);
			}
			if(!StringUtils.isEmpty(brand)){
				cooperationChangeApply.setBrand(brand);
			}
			if(!StringUtils.isEmpty(productType)){
				cooperationChangeApply.setProductType(productType);
			}
			if(!StringUtils.isEmpty(shopName)){
				cooperationChangeApply.setShopName(shopName);
			}
			if(!StringUtils.isEmpty(productTypeId)){
				cooperationChangeApply.setProductTypeId(Integer.parseInt(productTypeId));
			}
			if(!StringUtils.isEmpty(deposit)){
				cooperationChangeApply.setDeposit(new BigDecimal(deposit));
			}
			cooperationChangeApply.setZsAuditStatus(zsAuditStatus);
			cooperationChangeApply.setZsAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cooperationChangeApply.setZsAuditRemarks(zsAuditRemarks);
			CooperationChangeApplyLog cooperationChangeApplyLog = new CooperationChangeApplyLog();
			cooperationChangeApplyLog.setStatus("2");
			cooperationChangeApplyLog.setRemarks(zsAuditRemarks);
			if(zsAuditStatus.equals("1")){//通过
				cooperationChangeApply.setZsAuditDate(new Date());
				cooperationChangeApply.setFwAuditStatus("0");
				cooperationChangeApplyLog.setStatus("1");
				cooperationChangeApplyLog.setRemarks(zsAuditRemarks);
			}
			cooperationChangeApplyService.save(cooperationChangeApply,mchtBrandRateChangeJsonStr);
			//插入变更函日志

			cooperationChangeApplyLog.setCooperationChangeApplyId(cooperationChangeApply.getId());
			cooperationChangeApplyLog.setType("0");//招商审核
			cooperationChangeApplyLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            cooperationChangeApplyLog.setCreateDate(new Date());
            cooperationChangeApplyLogService.insertSelective(cooperationChangeApplyLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title    
	 * @Description 法务审核列表  
	 * @author yjc
	 * @date 2019年1月18日15:55:05
	 */
	@RequestMapping("/cooperationChangeApply/fwCheckList.shtml")
	public ModelAndView fwCheckList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cooperationChangeApply/fwCheckList");
		//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
		String staffID = this.getSessionStaffBean(request).getStaffID();
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		m.addObject("isManagement", isManagement);
		m.addObject("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);
		
		SysStaffProductTypeCustomExample staffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		staffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(staffProductTypeCustomExample);
		m.addObject("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
		
		SysStaffRoleExample e = new SysStaffRoleExample();
		e.createCriteria().andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(104);
	 	int count = sysStaffRoleService.countByExample(e);
	 	m.addObject("canCheck", count);
		return m;
	}
	
	/**
	 * 
	 * @Title    
	 * @Description (法务审核)
	 * @author yjc
	 * @date 2019年1月18日15:56:42
	 */
	@ResponseBody
	@RequestMapping("/cooperationChangeApply/fwCheckData.shtml")
	public Map<String, Object> fwCheckData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CooperationChangeApplyCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SysStaffProductTypeCustomExample staffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
			staffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(staffProductTypeCustomExample);
			if(sysStaffProductTypeCustomList==null || sysStaffProductTypeCustomList.size()==0){
				return resMap;
			}
			CooperationChangeApplyCustomExample e = new CooperationChangeApplyCustomExample();
			CooperationChangeApplyCustomExample.CooperationChangeApplyCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andZsAuditStatusEqualTo("1");//1.法务通过
			String beginZsAuditDate = request.getParameter("beginZsAuditDate");
			String endZsAuditDate = request.getParameter("endZsAuditDate");
			String mchtCode = request.getParameter("mchtCode");
			String changeApplyType = request.getParameter("changeApplyType");
			String auditStatus = request.getParameter("auditStatus");
			String productTypeId = request.getParameter("productTypeId");
			String platContactStaffId = request.getParameter("platContactStaffId");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(beginZsAuditDate)){
				c.andZsAuditDateGreaterThanOrEqualTo(sdf.parse(beginZsAuditDate+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(endZsAuditDate)){
				c.andZsAuditDateLessThanOrEqualTo(sdf.parse(endZsAuditDate+" 23:59:59"));
			}
			if(!StringUtils.isEmpty(mchtCode)){
				c.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(changeApplyType)){
				c.andChangeApplyTypeEqualTo(changeApplyType);
			}
			if(!StringUtils.isEmpty(auditStatus)){
				if(auditStatus.equals("1")){
					c.andFwAuditStatusEqualTo(auditStatus);
				}
				if(auditStatus.equals("2")){
					c.andFwAuditStatusEqualTo(auditStatus);
				}
				if(auditStatus.equals("3")){
					c.andUploadStatusEqualTo("0");
				}
				if(auditStatus.equals("4")){
					c.andUploadStatusEqualTo("1");
					c.andFwAuditStatusEqualTo("0");
				}
			}
			if(!StringUtils.isEmpty(productTypeId)){
				c.andMchtProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}
			if(!StringUtils.isEmpty(platContactStaffId)){
				c.andPlatContactStaffIdEqualTo(Integer.parseInt(platContactStaffId));
			}
			totalCount = cooperationChangeApplyService.countByCustomExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			dataList = cooperationChangeApplyService.selectByCustomExample(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title    
	 * @Description 法务审核/查看   
	 * @author yjc
	 * @date 2019年1月19日09:42:07
	 */
	@RequestMapping("/cooperationChangeApply/toFwCheck.shtml")
	public ModelAndView toFwCheck(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("cooperationChangeApply/toFwCheck");
		String id = request.getParameter("id");
		CooperationChangeApplyCustomExample e = new CooperationChangeApplyCustomExample();
		e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
		List<CooperationChangeApplyCustom> cooperationChangeApplyCustoms = cooperationChangeApplyService.selectByCustomExample(e);
		CooperationChangeApplyCustom cooperationChangeApplyCustom = cooperationChangeApplyCustoms.get(0);
		MchtBrandRateChangeExample mbrce = new MchtBrandRateChangeExample();
		mbrce.createCriteria().andDelFlagEqualTo("0").andCooperationChangeApplyIdEqualTo(cooperationChangeApplyCustom.getId()).andMchtIdEqualTo(cooperationChangeApplyCustom.getMchtId());
		List<MchtBrandRateChange> mchtBrandRateChanges = mchtBrandRateChangeMapper.selectByExample(mbrce);
		model.addObject("mchtBrandRateChanges", mchtBrandRateChanges);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(cooperationChangeApplyCustom.getMchtId());
		if(mchtInfo.getChangeApplyType().indexOf("1")>=0){//1.店铺名称变更
			model.addObject("shopNameAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("2")>=0){//2.店铺主营类目变更
			model.addObject("mchtProductTypeAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("3")>=0){//3.品牌技术服务费变更
			model.addObject("popCommissionRateAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("4")>=0){//4.保证金变更
			model.addObject("depositAuth", 1);
		}
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(cooperationChangeApplyCustom.getMchtId()).andStatusEqualTo("1");
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
		model.addObject("mchtProductBrands", mchtProductBrands);
		model.addObject("mchtInfo", mchtInfo);
		model.addObject("shopTypeStatusList",DataDicUtil.getStatusList("BU_MCHT_INFO", "SHOP_TYPE"));
		model.addObject("cooperationChangeApply", cooperationChangeApplyCustom);
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		model.addObject("productTypes", productTypes);
		model.addObject("isView", request.getParameter("isView"));
		//变更函日志
		CooperationChangeApplyLogCustomExample logExample = new CooperationChangeApplyLogCustomExample();
		logExample.createCriteria().andDelFlagEqualTo("0").andCooperationChangeApplyIdEqualTo(cooperationChangeApplyCustom.getId());
		logExample.setOrderByClause("id desc");
		List<CooperationChangeApplyLogCustom> cooperationChangeApplyLogList = cooperationChangeApplyLogService.selectByCustomExample(logExample);
		if(cooperationChangeApplyLogList.size() > 0){
			model.addObject("cooperationChangeApplyLogList",cooperationChangeApplyLogList);
		}
		return model;
	}
	
	/**
	 * 保存法务审核
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/cooperationChangeApply/fwCheck.shtml")
	@ResponseBody
	public Map<String, Object> fwCheck(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String fwAuditStatus = request.getParameter("fwAuditStatus");
			String fwAuditRemarks = request.getParameter("fwAuditRemarks");
			String staffID = this.getSessionStaffBean(request).getStaffID();
			CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
			cooperationChangeApply.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cooperationChangeApply.setUpdateDate(new Date());
			cooperationChangeApply.setFwAuditStatus(fwAuditStatus);
			cooperationChangeApply.setFwAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cooperationChangeApply.setFwAuditRemarks(fwAuditRemarks);
			cooperationChangeApply.setFwAuditDate(new Date());
			CooperationChangeApplyLog cooperationChangeApplyLog = new CooperationChangeApplyLog();
			cooperationChangeApplyLog.setStatus("2");
			cooperationChangeApplyLog.setRemarks(fwAuditRemarks);
			if(fwAuditStatus.equals("1")){//通过
				cooperationChangeApply.setSendStatus("0");
				cooperationChangeApply.setArchiveStatus("0");
				cooperationChangeApplyService.createContract(cooperationChangeApply);
				//商家基础资料插入日志
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(cooperationChangeApply.getMchtId());
				if(mchtInfo.getChangeApplyType().indexOf("1")>=0){//1.店铺名称变更
					MchtInfoChangeLogCustom mchtInfoChangeLog = new MchtInfoChangeLogCustom();
					mchtInfoChangeLog.setMchtId(cooperationChangeApply.getMchtId());
					mchtInfoChangeLog.setCreateBy(Integer.parseInt(staffID));
					mchtInfoChangeLog.setCreateDate(cooperationChangeApply.getFwAuditDate());
					mchtInfoChangeLog.setCreatorName(this.getSessionStaffBean(request).getStaffName());
					mchtInfoChangeLog.setLogType("变更函");
					mchtInfoChangeLog.setLogName("店铺名称变更");
					mchtInfoChangeLog.setBeforeChange(cooperationChangeApply.getPreShopName());
					mchtInfoChangeLog.setAfterChange(cooperationChangeApply.getShopName());
					mchtInfoChangeLog.setRemarks(cooperationChangeApply.getFwAuditRemarks());
					mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);
				}
				if(mchtInfo.getChangeApplyType().indexOf("2")>=0){//2.店铺主营类目变更
					MchtInfoChangeLogCustom mchtInfoChangeLog = new MchtInfoChangeLogCustom();
					mchtInfoChangeLog.setMchtId(cooperationChangeApply.getMchtId());
					mchtInfoChangeLog.setCreateBy(Integer.parseInt(staffID));
					mchtInfoChangeLog.setCreateDate(cooperationChangeApply.getFwAuditDate());
					mchtInfoChangeLog.setCreatorName(this.getSessionStaffBean(request).getStaffName());
					mchtInfoChangeLog.setLogType("变更函");
					mchtInfoChangeLog.setLogName("店铺主营类目变更");
					mchtInfoChangeLog.setBeforeChange(cooperationChangeApply.getPreProductType());
					mchtInfoChangeLog.setAfterChange(cooperationChangeApply.getProductType());
					mchtInfoChangeLog.setRemarks(cooperationChangeApply.getFwAuditRemarks());
					mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);
				}
				if(mchtInfo.getChangeApplyType().indexOf("3")>=0){//3.品牌技术服务费变更
					MchtInfoChangeLogCustom mchtInfoChangeLog = new MchtInfoChangeLogCustom();
					mchtInfoChangeLog.setMchtId(cooperationChangeApply.getMchtId());
					mchtInfoChangeLog.setCreateBy(Integer.parseInt(staffID));
					mchtInfoChangeLog.setCreateDate(cooperationChangeApply.getFwAuditDate());
					mchtInfoChangeLog.setCreatorName(this.getSessionStaffBean(request).getStaffName());
					mchtInfoChangeLog.setLogType("变更函");
					mchtInfoChangeLog.setLogName("品牌技术服务费变更");
					mchtInfoChangeLog.setRemarks(cooperationChangeApply.getFwAuditRemarks());
					MchtBrandRateChangeExample rateChangeExample = new MchtBrandRateChangeExample();
					rateChangeExample.createCriteria().andCooperationChangeApplyIdEqualTo(cooperationChangeApply.getId());
					List<MchtBrandRateChangeCustom> rateChangeList = mchtBrandRateChangeCustomMapper.selectByExample(rateChangeExample);
					String itemStr = "";
					if(rateChangeList.size()>0){
						for (MchtBrandRateChangeCustom item: rateChangeList) {
                            itemStr += "," + item.getProductBrandName();
						}
					}
					mchtInfoChangeLog.setBeforeChange(itemStr);
					mchtInfoChangeLog.setAfterChange(itemStr);
					mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);
				}
				if(mchtInfo.getChangeApplyType().indexOf("4")>=0){//4.保证金变更
					MchtInfoChangeLogCustom mchtInfoChangeLog = new MchtInfoChangeLogCustom();
					mchtInfoChangeLog.setMchtId(cooperationChangeApply.getMchtId());
					mchtInfoChangeLog.setCreateBy(Integer.parseInt(staffID));
					mchtInfoChangeLog.setCreateDate(cooperationChangeApply.getFwAuditDate());
					mchtInfoChangeLog.setCreatorName(this.getSessionStaffBean(request).getStaffName());
					mchtInfoChangeLog.setLogType("变更函");
					mchtInfoChangeLog.setLogName("保证金变更");
					mchtInfoChangeLog.setBeforeChange("-");
					mchtInfoChangeLog.setAfterChange("-");
					mchtInfoChangeLog.setRemarks(cooperationChangeApply.getFwAuditRemarks());
					mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);
				}
				//1.通过，将申请变更函中的有变更的信息写入相应的字段1、主营类目2、技术服务费率3、店铺名称4、保证金
				cooperationChangeApplyService.updateMchtRelevant(cooperationChangeApply);
				//插入变更函
				cooperationChangeApplyLog.setType("1");//法务审核
				cooperationChangeApplyLog.setStatus("1");
				cooperationChangeApplyLog.setRemarks(fwAuditRemarks);
			}else{
				cooperationChangeApply.setUploadStatus("0");
				cooperationChangeApplyService.updateByPrimaryKeySelective(cooperationChangeApply);
			}
			cooperationChangeApplyLog.setCooperationChangeApplyId(cooperationChangeApply.getId());
			cooperationChangeApplyLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cooperationChangeApplyLog.setCreateDate(new Date());
			cooperationChangeApplyLogService.insertSelective(cooperationChangeApplyLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	/**
	 * 法务审核上传图片页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/cooperationChangeApply/toUpload.shtml")
	public ModelAndView toUpload(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("/cooperationChangeApply/toUpload");
		String id = request.getParameter("id");
		CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
		model.addObject("cooperationChangeApply", cooperationChangeApply);
		return model;
	}

	/**
	 * 法务审核上传图片
	 */
	@RequestMapping("/cooperationChangeApply/toUploadPicture.shtml")
	@ResponseBody
	public ResponseMsg toUploadPicture(HttpServletRequest request){
		try {
			String id = request.getParameter("id");
			CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
			JSONArray cooperationChangeUploadPics = StringUtil.isEmpty(request.getParameter("cooperationChangeUploadPics"))?null:JSONArray.fromObject(request.getParameter("cooperationChangeUploadPics"));
			cooperationChangeApply.setUploadStatus("1");
			cooperationChangeApply.setFwAuditStatus("0");
			if(cooperationChangeUploadPics.size() >0) {
				cooperationChangeApplyService.toUploadPicture(cooperationChangeApply, cooperationChangeUploadPics);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 
	 * @Title    
	 * @Description 变更函归档管理列表  
	 * @author yjc
	 * @date 2019年1月18日15:55:05
	 */
	@RequestMapping("/cooperationChangeApply/archiveList.shtml")
	public ModelAndView archiveList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cooperationChangeApply/archiveList");
		//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
		String staffID = this.getSessionStaffBean(request).getStaffID();
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		m.addObject("isManagement", isManagement);
		m.addObject("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		m.addObject("productTypes", productTypes);
		
		SysStaffRoleExample e = new SysStaffRoleExample();
		e.createCriteria().andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(104);
	 	int count = sysStaffRoleService.countByExample(e);
	 	m.addObject("canCheck", count);
		return m;
	}
	
	/**
	 * 
	 * @Title    
	 * @Description (处理归档)   
	 * @author yjc
	 * @date 2019年1月18日15:56:42
	 */
	@ResponseBody
	@RequestMapping("/cooperationChangeApply/archiveData.shtml")
	public Map<String, Object> archiveData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CooperationChangeApplyCustom> dataList = null;
		Integer totalCount = 0;
		try {
			CooperationChangeApplyCustomExample e = new CooperationChangeApplyCustomExample();
			CooperationChangeApplyCustomExample.CooperationChangeApplyCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andFwAuditStatusEqualTo("1");
			String beginFwAuditDate = request.getParameter("beginFwAuditDate");
			String endFwAuditDate = request.getParameter("endFwAuditDate");
			String mchtCode = request.getParameter("mchtCode");
			String changeApplyType = request.getParameter("changeApplyType");
			String archiveStatus = request.getParameter("archiveStatus");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(beginFwAuditDate)){
				c.andFwAuditDateGreaterThanOrEqualTo(sdf.parse(beginFwAuditDate+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(endFwAuditDate)){
				c.andFwAuditDateLessThanOrEqualTo(sdf.parse(endFwAuditDate+" 23:59:59"));
			}
			if(!StringUtils.isEmpty(mchtCode)){
				c.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(changeApplyType)){
				c.andChangeApplyTypeEqualTo(changeApplyType);
			}
			if(!StringUtils.isEmpty(archiveStatus)){
				c.andArchiveStatusEqualTo(archiveStatus);
			}
			totalCount = cooperationChangeApplyService.countByCustomExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			dataList = cooperationChangeApplyService.selectByCustomExample(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title    
	 * @Description 归档处理页面 
	 * @author yjc
	 * @date 2019年1月19日09:42:07
	 */
	@RequestMapping("/cooperationChangeApply/toArchive.shtml")
	public ModelAndView toArchive(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("cooperationChangeApply/toArchive");
		String id = request.getParameter("id");
		CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
		model.addObject("cooperationChangeApply", cooperationChangeApply);
		return model;
	}
	
	/**
	 * 归档处理
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/cooperationChangeApply/archive.shtml")
	@ResponseBody
	public Map<String, Object> archive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String archiveStatus = request.getParameter("archiveStatus");
			String archiveRemarks = request.getParameter("archiveRemarks");
			CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
			cooperationChangeApply.setArchiveRemarks(archiveRemarks);
			cooperationChangeApply.setArchiveBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cooperationChangeApply.setArchiveStatus(archiveStatus);
			cooperationChangeApply.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cooperationChangeApply.setUpdateDate(new Date());
			CooperationChangeApplyLog cooperationChangeApplyLog = new CooperationChangeApplyLog();
			if(archiveStatus.equals("1")){
				cooperationChangeApply.setArchiveDate(new Date());
                cooperationChangeApplyService.updateByPrimaryKeySelective(cooperationChangeApply);
				cooperationChangeApplyLog.setStatus("1");
				cooperationChangeApplyLog.setRemarks(archiveRemarks);
			}else{//驳回
				cooperationChangeApply.setSendStatus("0");//0.未寄出
				cooperationChangeApplyService.updateByPrimaryKeySelective(cooperationChangeApply);
				cooperationChangeApplyLog.setStatus("2");
				cooperationChangeApplyLog.setRemarks(archiveRemarks);
			}
			//插入变更函
			cooperationChangeApplyLog.setCooperationChangeApplyId(cooperationChangeApply.getId());
			cooperationChangeApplyLog.setType("2");//归档
			cooperationChangeApplyLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cooperationChangeApplyLog.setCreateDate(new Date());
			cooperationChangeApplyLogService.insertSelective(cooperationChangeApplyLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	@RequestMapping("/cooperationChangeApply/export.shtml")
	public void export(HttpServletRequest request,HttpServletResponse response) {
		try {
			CooperationChangeApplyCustomExample e = new CooperationChangeApplyCustomExample();
			CooperationChangeApplyCustomExample.CooperationChangeApplyCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andFwAuditStatusEqualTo("1");
			c.andFwAuditDateIsNotNull();
			String beginFwAuditDate = request.getParameter("beginFwAuditDate");
			String endFwAuditDate = request.getParameter("endFwAuditDate");
			String mchtCode = request.getParameter("mchtCode");
			String changeApplyType = request.getParameter("changeApplyType");
			String archiveStatus = request.getParameter("archiveStatus");
			String productTypeId = request.getParameter("productTypeId");
			String platContactStaffId = request.getParameter("platContactStaffId");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(beginFwAuditDate)){
				c.andFwAuditDateGreaterThanOrEqualTo(sdf.parse(beginFwAuditDate+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(endFwAuditDate)){
				c.andFwAuditDateLessThanOrEqualTo(sdf.parse(endFwAuditDate+" 23:59:59"));
			}
			if(!StringUtils.isEmpty(mchtCode)){
				c.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(changeApplyType)){
				c.andChangeApplyTypeEqualTo(changeApplyType);
			}
			if(!StringUtils.isEmpty(archiveStatus)){
				c.andArchiveStatusEqualTo(archiveStatus);
			}
			if(!StringUtils.isEmpty(productTypeId)){
				c.andMchtProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}
			if(!StringUtils.isEmpty(platContactStaffId)){
				c.andPlatContactStaffIdEqualTo(Integer.parseInt(platContactStaffId));
			}
			List<CooperationChangeApplyCustom> dataList = cooperationChangeApplyService.selectByCustomExample(e);
			String[] titles = {"申请变更时间","商家序号","招商对接人","公司名称","协议编号","类目变更前","类目变更后","变更的品牌","技术服务费率变更前","技术服务费率变更后",	"店铺名称变更前","店铺名称变更后","法务通过时间","法务审核状态","备注"};
			ExcelBean excelBean = new ExcelBean("导出变更函.xls", "导出变更函", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(CooperationChangeApplyCustom cooperationChangeApplyCustom : dataList){
				String fwAuditStatusDesc = "";
				if("0".equals(cooperationChangeApplyCustom.getFwAuditStatus())){
					fwAuditStatusDesc = "待审";
				}else if("1".equals(cooperationChangeApplyCustom.getFwAuditStatus())){
					fwAuditStatusDesc = "通过";
				}else if("2".equals(cooperationChangeApplyCustom.getFwAuditStatus())){
					fwAuditStatusDesc = "驳回";
				}
				MchtBrandRateChangeExample mbrce = new MchtBrandRateChangeExample();
				mbrce.createCriteria().andDelFlagEqualTo("0").andCooperationChangeApplyIdEqualTo(cooperationChangeApplyCustom.getId());
				List<MchtBrandRateChangeCustom> mchtBrandRateChangeCustomList = mchtBrandRateChangeCustomMapper.selectByExample(mbrce);
				if(mchtBrandRateChangeCustomList!=null && mchtBrandRateChangeCustomList.size()>0){
					for(MchtBrandRateChangeCustom mchtBrandRateChangeCustom:mchtBrandRateChangeCustomList){
						String[] data = {
								sdf.format(cooperationChangeApplyCustom.getCreateDate()),
								cooperationChangeApplyCustom.getMchtCode(),
								cooperationChangeApplyCustom.getZsName(),
								cooperationChangeApplyCustom.getCompanyName(),
								cooperationChangeApplyCustom.getContractCode(),
								cooperationChangeApplyCustom.getProductTypeName(),
								cooperationChangeApplyCustom.getNewProductTypeName(),
								mchtBrandRateChangeCustom.getProductBrandName(),
								mchtBrandRateChangeCustom.getCommissionRate()==null?"":mchtBrandRateChangeCustom.getCommissionRate().toString(),
								mchtBrandRateChangeCustom.getPopCommissionRate()==null?"":mchtBrandRateChangeCustom.getPopCommissionRate().toString(),
								cooperationChangeApplyCustom.getOldShopName(),
								cooperationChangeApplyCustom.getShopName(),
								cooperationChangeApplyCustom.getFwAuditDate()==null?"":sdf.format(cooperationChangeApplyCustom.getFwAuditDate()),
								fwAuditStatusDesc,
								cooperationChangeApplyCustom.getArchiveRemarks()==null?"":cooperationChangeApplyCustom.getArchiveRemarks()
						};
						datas.add(data);
					}
				}else{
					String[] data = {
							sdf.format(cooperationChangeApplyCustom.getCreateDate()),
							cooperationChangeApplyCustom.getMchtCode(),
							cooperationChangeApplyCustom.getZsName(),
							cooperationChangeApplyCustom.getCompanyName(),
							cooperationChangeApplyCustom.getContractCode(),
							cooperationChangeApplyCustom.getProductTypeName(),
							cooperationChangeApplyCustom.getNewProductTypeName(),
							"",
							"",
							"",
							cooperationChangeApplyCustom.getOldShopName(),
							cooperationChangeApplyCustom.getShopName(),
							cooperationChangeApplyCustom.getFwAuditDate()==null?"":sdf.format(cooperationChangeApplyCustom.getFwAuditDate()),
							fwAuditStatusDesc,
							cooperationChangeApplyCustom.getArchiveRemarks()==null?"":cooperationChangeApplyCustom.getArchiveRemarks()
					};
					datas.add(data);
				}

			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/cooperationChangeApply/preview.shtml")
	public void preview(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
		String filePath = cooperationChangeApply.getFilePath();
		InputStream stream = CooperationChangeApplyController.class.getResourceAsStream("/base_config.properties");
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
		//设置响应内容类型为PDF类型
		response.setContentType("application/pdf");
		ServletOutputStream sos = response.getOutputStream();
		//不在网页中打开，而是直接下载该文件，下载后的文件名为“Example.pdf”
		// response.setHeader("Content-disposition", "attachment;filename=Example.pdf");
		File file = new File(defaultPath+filePath);
		response.setContentLength((int) file.length());
		FileInputStream fis = new FileInputStream(file);

		byte[] buffer = new byte[1024*1024];
		int readBytes = -1;
		while((readBytes = fis.read(buffer, 0, 1024*1024)) != -1){
			sos.write(buffer, 0, 1024*1024);
		}
		if(sos!=null){
			sos.close();
		}
		if(fis!=null){
			fis.close();
		} 
	}

	@RequestMapping("cooperationChangeApply/toCheck.shtml")
	public ModelAndView toCheck(HttpServletRequest request){
		ModelAndView model = new ModelAndView("cooperationChangeApply/toCheck");
		String id = request.getParameter("id");
		CooperationChangeUploadPicExample cooperationChangeUploadPicExample = new CooperationChangeUploadPicExample();
		cooperationChangeUploadPicExample.createCriteria().andDelFlagEqualTo("0").andCooperationChangeApplyIdEqualTo(Integer.parseInt(id)).andStatusEqualTo("0");
		List<CooperationChangeUploadPic> picList = cooperationChangeUploadPicService.selectByExample(cooperationChangeUploadPicExample);
		model.addObject("id", id);
		model.addObject("cooperationChangeUploadPics",picList);
		return model;
	}

	/**
	 * 保存法务审核(已上传待审)
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("cooperationChangeApply/checkCooperationChangeApply.shtml")
	@ResponseBody
	public Map<String, Object> checkCooperationChangeApply(HttpServletRequest request){
		HashMap<String, Object> resMap = new HashMap<>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
		String id = request.getParameter("id");
		String staffID = this.getSessionStaffBean(request).getStaffID();
		String status = request.getParameter("status");
		String processInnerRemarks = request.getParameter("processInnerRemarks");
		String remarks = request.getParameter("remarks");

			CooperationChangeApplyLog cooperationChangeApplyLog = new CooperationChangeApplyLog();
			CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
			if (!StringUtil.isEmpty(status) && status.equals("1")) {

				cooperationChangeApply.setFwAuditStatus("1");
				cooperationChangeApply.setFwAuditDate(new Date());
				cooperationChangeApply.setFwAuditBy(Integer.parseInt(staffID));
				cooperationChangeApply.setFwAuditRemarks(processInnerRemarks);
				cooperationChangeApplyService.updateByPrimaryKeySelective(cooperationChangeApply);
				CooperationChangeUploadPicExample cooperationChangeUploadPicExample = new CooperationChangeUploadPicExample();
				cooperationChangeUploadPicExample.createCriteria().andCooperationChangeApplyIdEqualTo(cooperationChangeApply.getId()).andStatusEqualTo("0");
				List<CooperationChangeUploadPic> changeUploadPics = cooperationChangeUploadPicService.selectByExample(cooperationChangeUploadPicExample);
				if (changeUploadPics.size() > 0) {
					for (CooperationChangeUploadPic pic : changeUploadPics) {
						pic.setCooperationChangeApplyId(cooperationChangeApply.getId());
						pic.setStatus("1");
						cooperationChangeUploadPicService.updateByPrimaryKeySelective(pic);
					}
				}
				//商家基础资料插入日志
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(cooperationChangeApply.getMchtId());
				if(mchtInfo.getChangeApplyType().indexOf("1")>=0){//1.店铺名称变更
					MchtInfoChangeLogCustom mchtInfoChangeLog = new MchtInfoChangeLogCustom();
					mchtInfoChangeLog.setMchtId(cooperationChangeApply.getMchtId());
					mchtInfoChangeLog.setCreateBy(Integer.parseInt(staffID));
					mchtInfoChangeLog.setCreateDate(cooperationChangeApply.getFwAuditDate());
					mchtInfoChangeLog.setCreatorName(this.getSessionStaffBean(request).getStaffName());
					mchtInfoChangeLog.setLogType("变更函");
					mchtInfoChangeLog.setLogName("店铺名称变更");
					mchtInfoChangeLog.setBeforeChange(cooperationChangeApply.getPreShopName());
					mchtInfoChangeLog.setAfterChange(cooperationChangeApply.getShopName());
					mchtInfoChangeLog.setRemarks(cooperationChangeApply.getFwAuditRemarks());
					mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);
				}
				if(mchtInfo.getChangeApplyType().indexOf("2")>=0){//2.店铺主营类目变更
					MchtInfoChangeLogCustom mchtInfoChangeLog = new MchtInfoChangeLogCustom();
					mchtInfoChangeLog.setMchtId(cooperationChangeApply.getMchtId());
					mchtInfoChangeLog.setCreateBy(Integer.parseInt(staffID));
					mchtInfoChangeLog.setCreateDate(cooperationChangeApply.getFwAuditDate());
					mchtInfoChangeLog.setCreatorName(this.getSessionStaffBean(request).getStaffName());
					mchtInfoChangeLog.setLogType("变更函");
					mchtInfoChangeLog.setLogName("店铺主营类目变更");
					mchtInfoChangeLog.setBeforeChange(cooperationChangeApply.getPreProductType());
					mchtInfoChangeLog.setAfterChange(cooperationChangeApply.getProductType());
					mchtInfoChangeLog.setRemarks(cooperationChangeApply.getFwAuditRemarks());
					mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);
				}
				if(mchtInfo.getChangeApplyType().indexOf("3")>=0){//3.品牌技术服务费变更
					MchtInfoChangeLogCustom mchtInfoChangeLog = new MchtInfoChangeLogCustom();
					mchtInfoChangeLog.setMchtId(cooperationChangeApply.getMchtId());
					mchtInfoChangeLog.setCreateBy(Integer.parseInt(staffID));
					mchtInfoChangeLog.setCreateDate(cooperationChangeApply.getFwAuditDate());
					mchtInfoChangeLog.setCreatorName(this.getSessionStaffBean(request).getStaffName());
					mchtInfoChangeLog.setLogType("变更函");
					mchtInfoChangeLog.setLogName("品牌技术服务费变更");
					mchtInfoChangeLog.setRemarks(cooperationChangeApply.getFwAuditRemarks());
					MchtBrandRateChangeExample rateChangeExample = new MchtBrandRateChangeExample();
					rateChangeExample.createCriteria().andCooperationChangeApplyIdEqualTo(cooperationChangeApply.getId());
					List<MchtBrandRateChangeCustom> rateChangeList = mchtBrandRateChangeCustomMapper.selectByExample(rateChangeExample);
					String itemStr = "";
					if(rateChangeList.size()>0){
						for (MchtBrandRateChangeCustom item: rateChangeList) {
							itemStr += "," + item.getProductBrandName();
						}
					}
					mchtInfoChangeLog.setBeforeChange(itemStr);
					mchtInfoChangeLog.setAfterChange(itemStr);
					mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);


				}
				if(mchtInfo.getChangeApplyType().indexOf("4")>=0){//4.保证金变更
					MchtInfoChangeLogCustom mchtInfoChangeLog = new MchtInfoChangeLogCustom();
					mchtInfoChangeLog.setMchtId(cooperationChangeApply.getMchtId());
					mchtInfoChangeLog.setCreateBy(Integer.parseInt(staffID));
					mchtInfoChangeLog.setCreateDate(cooperationChangeApply.getFwAuditDate());
					mchtInfoChangeLog.setCreatorName(this.getSessionStaffBean(request).getStaffName());
					mchtInfoChangeLog.setLogType("变更函");
					mchtInfoChangeLog.setLogName("保证金变更");
					mchtInfoChangeLog.setBeforeChange("-");
					mchtInfoChangeLog.setAfterChange("-");
					mchtInfoChangeLog.setRemarks(cooperationChangeApply.getFwAuditRemarks());
					mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);
				}
				//插入变更函
				cooperationChangeApplyLog.setStatus("1");
				cooperationChangeApplyLog.setRemarks(processInnerRemarks);
				//1.通过，将申请变更函中的有变更的信息写入相应的字段1、主营类目2、技术服务费率3、店铺名称4、保证金
				cooperationChangeApplyService.updateMchtRelevant(cooperationChangeApply);
			}
			if (!StringUtil.isEmpty(status) && status.equals("2")) {
				cooperationChangeApply.setFwAuditStatus("2");
				cooperationChangeApply.setFwAuditDate(new Date());
				cooperationChangeApply.setFwAuditBy(Integer.parseInt(staffID));
				cooperationChangeApply.setFwAuditRemarks(remarks);
				cooperationChangeApply.setUploadStatus("0");
				cooperationChangeApplyService.updateByPrimaryKeySelective(cooperationChangeApply);
				CooperationChangeUploadPicExample cooperationChangeUploadPicExample = new CooperationChangeUploadPicExample();
				cooperationChangeUploadPicExample.createCriteria().andCooperationChangeApplyIdEqualTo(cooperationChangeApply.getId()).andStatusEqualTo("0");
				List<CooperationChangeUploadPic> changeUploadPics = cooperationChangeUploadPicService.selectByExample(cooperationChangeUploadPicExample);
				if (changeUploadPics.size() > 0) {
					for (CooperationChangeUploadPic pic : changeUploadPics) {
						pic.setCooperationChangeApplyId(cooperationChangeApply.getId());
						pic.setStatus("1");
						cooperationChangeUploadPicService.updateByPrimaryKeySelective(pic);
					}
				}
				//插入变更函
				cooperationChangeApplyLog.setStatus("2");
				cooperationChangeApplyLog.setRemarks(remarks);
			}
			cooperationChangeApplyLog.setCooperationChangeApplyId(cooperationChangeApply.getId());
			cooperationChangeApplyLog.setType("1");//法务审核
			cooperationChangeApplyLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cooperationChangeApplyLog.setCreateDate(new Date());
			cooperationChangeApplyLogService.insertSelective(cooperationChangeApplyLog);
		}catch (Exception e){
			e.printStackTrace();
			resMap.put("returnCode", "9999");
			resMap.put("returnMsg", "审核状态异常");
		}
		return resMap;
	}



    
}
