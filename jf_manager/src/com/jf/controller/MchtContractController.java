package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseController;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.DateTimeUtil;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.*;
import com.jf.controller.command.CMchtContractArchiveCommit;
import com.jf.controller.command.CMchtContractList;
import com.jf.controller.command.CMchtInfoList;
import com.jf.controller.command.CMchtInfoStatExpire;
import com.jf.entity.*;
import com.jf.entity.MchtContractCustomExample.MchtContractCustomCriteria;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MchtContractController extends ABaseController {
	private static final long serialVersionUID = 1L;

	@Resource
	private SysStaffInfoService sysStaffInfoService;
	@Resource
	private SysStaffProductTypeService sysStaffProductTypeService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private MchtContractService mchtContractService;
	@Resource
	private StatusService statusService;
	@Resource
	private ExpressService expressService;
	@Resource
	private MchtContractPicService mchtContractPicService;
	@Resource
	private PlatformContactService platformContactService;
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private MchtBrandAptitudeService mchtBrandAptitudeService;
	@Resource
	private MchtBrandAptitudePicService mchtBrandAptitudePicService;
	@Resource
	private MchtPlatformAuthPicService mchtPlatformAuthPicService;
	@Resource
	private MchtBrandInspectionPicService mchtBrandInspectionPicService;
	@Resource
	private MchtBrandInvoicePicService mchtBrandInvoicePicService;
	@Resource
	private MchtBrandOtherPicService mchtBrandOtherPicService;
	@Resource
	private MchtBlPicService mchtBlPicService;
	@Resource
	private MchtPlatformContactService mchtPlatformContactService;
	@Resource
	private SysStaffRoleService sysStaffRoleService;
	@Resource
	private MchtCloseApplicationService mchtCloseApplicationService;
	@Resource
	private AreaService areaService;
	@Resource
	private MchtContactService mchtContactService;
	@Resource
	private MchtBlPicChgService mchtBlPicChgService;
	@Resource
	private ContractRenewLogService contractRenewLogService;
	@Resource
	private MchtUserService mchtUserService;
	@Resource
	private PlatformMsgService platformMsgService;
	@Resource
	private MchtBrandChgService mchtBrandChgService;
	@Resource
	private MchtInfoChgService mchtInfoChgService;
	@Resource
	private MchtLicenseChgService mchtLicenseChgService;
	/**
	 * 线上合同审核
	 */
	@RequestMapping(value = "/mchtContract/listAuditWait.shtml")
	public ModelAndView listAuditWait(HttpServletRequest request) {
		String page = "/mchtContract/listAuditWait";
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("mcht_type", statusService.querytStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			data.put("role107", true);
		}else{
			data.put("role107", false);
		}
		return new ModelAndView(page, data);
	}

	/**
	 * 商家资料归档审核
	 */
	@RequestMapping(value = "/mchtContract/listArchiveProcessing.shtml")
	public ModelAndView listArchiveProcessing(HttpServletRequest request) {
		String page = "/mchtContract/listArchiveProcessing";
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("mchtInfoArchiveStatus", "0");
		data.put("archiveStatusNotEqual", "1");
		List<SysStatus> mchtInfoStatusList = DataDicUtil.getStatusList("BU_MCHT_INFO", "STATUS");
		data.put("mchtInfoStatusList", mchtInfoStatusList);
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			data.put("role107", true);
		}else{
			data.put("role107", false);
		}
		return new ModelAndView(page, data);
	}

	/**
	 * 商家资料归档审核列表数据
	 */
	@RequestMapping(value = "/mchtContract/listArchiveProcessingData.shtml")
	@ResponseBody
	public String listArchiveProcessingData(HttpServletRequest request,Page page) {
		Map<String, Object> data = new HashMap<String, Object>();
		MchtContractCustomExample mcce = new MchtContractCustomExample();
		mcce.setOrderByClause("audit_date asc,id desc");
		MchtContractCustomExample.MchtContractCustomCriteria c = mcce.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andContractTypeEqualTo("1");
		c.andMchtStatusIn("0,1,2");
		c.andNotArchiveEqualTo();
		String mchtCode = request.getParameter("mcht_code");
		if(!StringUtils.isEmpty(mchtCode)){
			c.andMchtCodeByEqualTo(mchtCode);
		}
		String mchtName = request.getParameter("mcht_name");
		if(!StringUtils.isEmpty(mchtName)){
			c.andMchtNameLike(mchtName);
		}
		String contractCode = request.getParameter("contractCode");
		if(!StringUtils.isEmpty(contractCode)){
			c.andContractCodeEqualTo(contractCode);
		}
		String isMchtSend = request.getParameter("isMchtSend");
		if(!StringUtils.isEmpty(isMchtSend)){
			c.andIsMchtSendEqualTo(isMchtSend);
		}
		String archiveStatus = request.getParameter("archiveStatus");
		if(!StringUtils.isEmpty(archiveStatus)){
			c.andArchiveStatusEqualTo(archiveStatus);
		}
		String noArchiveFlag = request.getParameter("noArchiveFlag");
		if(!StringUtils.isEmpty(noArchiveFlag) && "1".equals(noArchiveFlag)){
			List<String> values = new ArrayList<String>();
			values.add("0");
			values.add("2");
			values.add("4");
			c.andArchiveStatusIn(values);
		}
		String companyInfoArchiveStatus = request.getParameter("companyInfoArchiveStatus");
		if(!StringUtils.isEmpty(companyInfoArchiveStatus)){
			c.andCompanyInfoArchiveStatusEqualTo(companyInfoArchiveStatus);
		}
		String mchtBrandArchiveStatus = request.getParameter("mchtBrandArchiveStatus");
		if(!StringUtils.isEmpty(mchtBrandArchiveStatus)){
			c.andMchtBrandArchiveStatusEqualTo(mchtBrandArchiveStatus);
		}
		String isMyFawu = request.getParameter("is_my_fawu");
		if(!StringUtils.isEmpty(isMyFawu)){
			Integer staffID = Integer.parseInt(this.getSessionStaffBean().getStaffID());
			c.andIsMyFawuEqualTo(staffID);
		}
		String settledType = request.getParameter("settledType");
		if(!StringUtils.isEmpty(settledType)){
			c.andSettledTypeEqualTo(settledType);
		}
		String mchtInfoStatus = request.getParameter("mchtInfoStatus");
		if(!StringUtils.isEmpty(mchtInfoStatus)){
			c.andMchtStatusByEqualTo(mchtInfoStatus);
		}
		int total = mchtContractService.countCustomByExample(mcce);
		mcce.setLimitStart(page.getLimitStart());
		mcce.setLimitSize(page.getLimitSize());
		List<MchtContractCustom> mchtContractCustoms = mchtContractService.selectCustomByExample(mcce);
		for(MchtContractCustom mchtContractCustom:mchtContractCustoms){
			MchtProductBrandExample mpbe = new MchtProductBrandExample();
			mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtContractCustom.getMchtId()).andAuditStatusEqualTo("3");
			List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
			String html = "";
			for(MchtProductBrand mchtProductBrand:mchtProductBrands){
				String tmp = "";
				String archiveStatusDesc = "";
				if(!StringUtils.isEmpty(mchtProductBrand.getArchiveStatus()) && mchtProductBrand.getArchiveStatus().equals("3")){
					archiveStatusDesc = "<span style='color:red;'>【已齐全】</span>";
				}else{
					archiveStatusDesc = "<span style='color:red;'>【未齐全】</span>";
				}
				tmp+=archiveStatusDesc+"【"+mchtProductBrand.getProductBrandName()+"】"+"<a href='javascript:;' onclick='viewMchtProductBrandPics("+mchtProductBrand.getId()+");'>【查看】</a><br>";
				html+=tmp;
			}
			mchtContractCustom.setMchtBrandArchiveStatusHtml(html);
		}
		data.put("Rows", mchtContractCustoms);
		data.put("Total", total);
		return json(data);
	}
	
	/**
	 * 不签约待关闭
	 */
	@RequestMapping(value = "/mchtContract/listArchiveClose.shtml")
	public ModelAndView listArchiveClose() {
		String page = "/mchtContract/listArchiveClose";
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("mcht_type", statusService.querytStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
		return new ModelAndView(page, data);
	}

	/**
	 * 
	 * @MethodName contractExpire
	 * @Description TODO(合同到期页面)
	 * @author chengh
	 * @date 2019年7月29日 下午8:24:14
	 */
	@RequestMapping(value = "/mchtContract/contractExpireIndex.shtml")
	public ModelAndView contractExpire(HttpServletRequest request) {
		String rtPage = "/mchtContract/contractExpireIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String staffID = this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
		resMap.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		resMap.put("isManagement", isManagement);
		resMap.put("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		Date date = new Date();
		resMap.put("lastYearStart", DateTimeUtil.getFirstdayOfYear(date));
		resMap.put("lastYearEnd", DateTimeUtil.getLastdayOfYear(date));
		return new ModelAndView(rtPage,resMap);
	}


	 /**
	 *
	 * @MethodName contractExpireList
	 * @Description TODO(合同到期页面列表)
	 * @author chengh
	 * @date 2019年7月30日 下午7:22:24
	 */
	 /*
	@RequestMapping(value = "/mchtContract/contractExpireList")
	@ResponseBody
	public Map<String, Object> contractExpireList1(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<MchtInfoCustom> mchtInfoCustoms = new ArrayList<MchtInfoCustom>();
		try {
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtContractCustomExample mchtContractCustomExample = new MchtContractCustomExample();
			MchtInfoCustomCriteria criteria = mchtInfoCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
			//最新合同到期时间<=当前时间+90天且续签状态=待申请OR开放续签入口
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = DateUtil.getDateAfter(new Date(), 90);
			String endDate = format.format(date);
			criteria.andRenewProStatus(endDate);
			//商家序列
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				criteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			//名称
			if (!StringUtil.isEmpty(request.getParameter("companyOrShop"))) {
				criteria.andCompanyOrShopNameLike(request.getParameter("companyOrShop"));
			}
			//类目
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				criteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
			}
			//对接人
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				criteria.andPlatContactStaffIdEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));
			}
			//距离合同到期时间
			String nowDate = format.format(new Date());
			if(!StringUtil.isEmpty(request.getParameter("agreementBeginDate"))){
				criteria.andAgreementBeginDateCustomGreaterThan(request.getParameter("agreementBeginDate"),nowDate);
			}
			if(!StringUtil.isEmpty(request.getParameter("agreementEndDate"))){
				criteria.andAgreementBeginDateCustomLessThan(request.getParameter("agreementEndDate"),nowDate);
			}
			
			totalCount = mchtInfoService.countByExample(mchtInfoCustomExample);
			mchtInfoCustomExample.setLimitSize(page.getLimitSize());
			mchtInfoCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtInfoCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}*/

	/**
	 * 合同到期列表：原先sql无法拓展出当前合同的下一份合同编号及相关审核状态
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/mchtContract/contractExpireList")
	@ResponseBody
	public Map<String, Object> contractExpireList(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//商家序列
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				paramMap.put("mchtCode",request.getParameter("mchtCode"));
			}
			//名称
			if (!StringUtil.isEmpty(request.getParameter("companyOrShop"))) {
				paramMap.put("companyOrShop",request.getParameter("companyOrShop"));
			}
			//类目
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				paramMap.put("productTypeId",Integer.valueOf(request.getParameter("productTypeId")));
			}
			//对接人
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				paramMap.put("platContactStaffId",Integer.valueOf(request.getParameter("platContactStaffId")));
			}
			//商家合作状态
			if (!StringUtil.isEmpty(request.getParameter("mchtInfoStatus"))) {
				paramMap.put("mchtInfoStatus",request.getParameter("mchtInfoStatus"));
			}
			//合同归档状态
			if (!StringUtil.isEmpty(request.getParameter("archiveStatus"))) {
				paramMap.put("archiveStatus",request.getParameter("archiveStatus"));
			}
			//续签进度状态
			if (!StringUtil.isEmpty(request.getParameter("renewProStatus"))) {
				paramMap.put("renewProStatus",request.getParameter("renewProStatus"));
			}
			//合同审核状态
			if (!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
				paramMap.put("auditStatus",request.getParameter("auditStatus"));
			}
			//新合同审核状态
			if (!StringUtil.isEmpty(request.getParameter("newAuditStatus"))) {
				paramMap.put("newAuditStatus",request.getParameter("newAuditStatus"));
			}
			//合同到期日期
			if (!StringUtil.isEmpty(request.getParameter("endDate1"))) {
				paramMap.put("endDate1",request.getParameter("endDate1")+" 00:00:00");
			}
			if (!StringUtil.isEmpty(request.getParameter("endDate2"))) {
				paramMap.put("endDate2",request.getParameter("endDate2")+" 23:59:59");
			}

			totalCount=mchtContractService.countExpireListByExample(paramMap);
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			resList = mchtContractService.selectExpireListByExample(paramMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", resList);
		resMap.put("Total", totalCount);
		return resMap;
	}


	/**
	 * 
	 * @MethodName zsConfirmRenewalIndex
	 * @Description TODO(招商确认续签页面)
	 * @author chengh
	 * @date 2019年7月31日 下午2:14:38
	 */
	@RequestMapping(value = "/mchtContract/zsConfirmRenewalIndex.shtml")
	public ModelAndView zsConfirmRenewalIndex(HttpServletRequest request) {
		String rtPage = "/mchtContract/zsConfirmRenewalIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String staffID = this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
		resMap.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		resMap.put("isManagement", isManagement);
		resMap.put("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @MethodName contractExpireList
	 * @Description TODO(招商确认续签列表)
	 * @author chengh
	 * @date 2019年7月30日 下午7:22:24
	 */
	@RequestMapping(value = "/mchtContract/zsConfirmRenewalList.shtml")
	@ResponseBody
	public Map<String, Object> zsConfirmRenewalList(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<MchtContractCustom> mchtInfoCustoms = new ArrayList<MchtContractCustom>();
		try {
			MchtContractCustomExample mchtContractCustomExample = new MchtContractCustomExample();
			MchtContractCustomCriteria criteria = mchtContractCustomExample.createCriteria();
			//合同续签状态=续签申请（招商确认）、确认续签（招商确认）、不予续签（招商操作）、已生成合同（法务操作）
			criteria.andDelFlagEqualTo("0");
			//按照商家提交续签的需求时间排序
			mchtContractCustomExample.setOrderByClause(" log_create_date asc");
			//商家序列
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				criteria.andMchtCodeEqualTo(request
						.getParameter("mchtCode"));
			}
			//名称
			if (!StringUtil.isEmpty(request.getParameter("companyOrShop"))) {
				criteria.andcompanyOrShopNameLike(request.getParameter("companyOrShop"));
			}
			//类目
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				criteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
			}
			//对接人
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				criteria.andPlatContactStaffIdEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));
			}
			//招商确认状态
			if (!StringUtil.isEmpty(request.getParameter("renewProStatusZS"))) {
				criteria.andRenewProStatusEqualTo(request.getParameter("renewProStatusZS"));
			}
			//法务确认状态
			if (!StringUtil.isEmpty(request.getParameter("renewProStatusFW"))) {
				criteria.andRenewProStatusEqualTo(request.getParameter("renewProStatusFW"));
			}
			//商家合作状态
			if (!StringUtil.isEmpty(request.getParameter("mchtInfoStatus"))) {
				criteria.andMchtInfoStatusEqualTo(request.getParameter("mchtInfoStatus"));
			}
			
			totalCount = mchtContractService.countByExampleCustom(mchtContractCustomExample);
			mchtContractCustomExample.setLimitSize(page.getLimitSize());
			mchtContractCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoCustoms = mchtContractService.selectByExampleCustom(mchtContractCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtInfoCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName checkOrExamine
	 * @Description TODO(查看或审核招商、法务确认、运营确认页面)
	 * @author chengh
	 * @date 2019年8月1日 下午7:26:05
	 */
	@RequestMapping(value = "/mchtContract/checkOrExamine.shtml")
	public ModelAndView checkOrExamine(HttpServletRequest request,Model model) {
		String rtPage = "/mchtContract/checkOrExamine";
		int id = Integer.valueOf(request.getParameter("id"));
		model.addAttribute("id",id);
		MchtContractCustomExample mchtContractCustomExample = new MchtContractCustomExample();
		mchtContractCustomExample.createCriteria().andIdEqualTo(id).andDelFlagEqualTo("0");
		//招商确认或者法务确认标识
		String type = request.getParameter("type");
		model.addAttribute(type);
		MchtContractCustom mchtContract = new MchtContractCustom();
		//招商
		if(org.apache.commons.lang.StringUtils.equals(type, "1")){
			mchtContract = mchtContractService.selectByExampleCustom(mchtContractCustomExample).get(0);
		}
		//法务
		if(org.apache.commons.lang.StringUtils.equals(type, "2")){
			mchtContract = mchtContractService.selectByExampleCustomFW(mchtContractCustomExample).get(0);
			//公司资质归档情况
			MchtInfoChgExample mchtInfoChgExample = new MchtInfoChgExample();
			mchtInfoChgExample.setOrderByClause("id desc");
			mchtInfoChgExample.createCriteria().andMchtIdEqualTo(mchtContract.getMchtId()).andStatusEqualTo("3").andDelFlagEqualTo("0");
			List<MchtInfoChgCustom> mchtInfoChgCustoms = mchtInfoChgService.selectMchtInfoChgCustomByExample(mchtInfoChgExample);
			model.addAttribute("mchtInfoChgCustoms",mchtInfoChgCustoms);
			//品牌资质归档情况
			MchtBrandChgExample mchtBrandChgExample = new MchtBrandChgExample();
			mchtBrandChgExample.setOrderByClause("id desc");
			mchtBrandChgExample.createCriteria().andMchtIdEqualTo(mchtContract.getMchtId()).andAuditStatusEqualTo("3").andDelFlagEqualTo("0");
			List<MchtBrandChgCustom> mchtBrandChgCustoms = mchtBrandChgService.selectByExampleCustom(mchtBrandChgExample);
			model.addAttribute("mchtBrandChgCustoms",mchtBrandChgCustoms);
			//经营许可证归档情况
			MchtLicenseChgCustomExample mchtLicenseChgExample = new MchtLicenseChgCustomExample();
			mchtLicenseChgExample.setOrderByClause("id desc");
			mchtLicenseChgExample.createCriteria().andMchtIdEqualTo(mchtContract.getMchtId()).andDelFlagEqualTo("0");
			List<MchtLicenseChgCustom> mchtLicenseChgCustoms = mchtLicenseChgService.selectMchtLicenseChgCustomByExample(mchtLicenseChgExample);
			model.addAttribute("mchtLicenseChgCustoms",mchtLicenseChgCustoms);
		}
		//运营
		if(org.apache.commons.lang.StringUtils.equals(type, "3")){
			mchtContract = mchtContractService.selectByExampleCustomNotRenew(mchtContractCustomExample).get(0);
		}
		model.addAttribute("mchtContract",mchtContract);
		//商家ID
		Integer mchtId = mchtContract.getMchtId();
		//商家销量情况、商家财务信息
		MchtInfoExample mchtInfoExample = new MchtInfoExample();
		mchtInfoExample.createCriteria().andIdEqualTo(mchtId).andDelFlagEqualTo("0");
		Map<String,Object> mchtInfo = mchtInfoService.selectZSRenewalDetailByExample(mchtInfoExample);
		model.addAttribute("mchtInfo",mchtInfo);
		
		String staffId = String.valueOf(this.getSessionStaffBean(request).getStaffID());		
		//招商
		if(org.apache.commons.lang.StringUtils.equals(type, "1")){
			String zsStaffId = String.valueOf(mchtInfo.get("zsStaffId"));
			String assistantId = String.valueOf(mchtInfo.get("assistantId"));
			boolean zsTAG = true;
			if(!org.apache.commons.lang.StringUtils.equals(zsStaffId,staffId) && !org.apache.commons.lang.StringUtils.equals(assistantId,staffId)){
				zsTAG = false;
			}
			//招商对接人或者协助人标识
			model.addAttribute("zsTAG",zsTAG);
		}
		//法务
		boolean fwTAG = true;
		if(org.apache.commons.lang.StringUtils.equals(type, "2")){
			String fwStaffId = String.valueOf(mchtInfo.get("fwStaffId"));
			String fwAssistantId = String.valueOf(mchtInfo.get("fwAssistantId"));		
			if(!org.apache.commons.lang.StringUtils.equals(fwStaffId,staffId) && !org.apache.commons.lang.StringUtils.equals(fwAssistantId,staffId)){
				fwTAG = false;
			}
			//法务对接人或者协助人标识
			model.addAttribute("fwTAG",fwTAG);
		}
		//运营
		boolean yyTAG = true;
		if(org.apache.commons.lang.StringUtils.equals(type, "3")){
			String yyStaffId = String.valueOf(mchtInfo.get("yyStaffId"));
			String yyAssistantId = String.valueOf(mchtInfo.get("yyAssistantId"));		
			if(!org.apache.commons.lang.StringUtils.equals(yyStaffId,staffId) && !org.apache.commons.lang.StringUtils.equals(yyAssistantId,staffId)){
				yyTAG = false;
			}
			//法务对接人或者协助人标识
			model.addAttribute("yyTAG",yyTAG);
		}
		//如果是法务确认并且当前操作人是法务，则对合同开始时间、结束时间进行初始化
		if(fwTAG && org.apache.commons.lang.StringUtils.endsWith(type, "2")){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(mchtContract.getEndDate());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			String beginDate = "";
			String endDate = "";
			//如果只是查看，就不要多加一年
			if(org.apache.commons.lang.StringUtils.equals(mchtContract.getRenewProStatus(), "3")){	
				beginDate = format.format(calendar.getTime());
				calendar.setTime(calendar.getTime());
				calendar.add(Calendar.YEAR, 1);
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				endDate = format.format(calendar.getTime());			
			}else{
				beginDate = format.format(mchtContract.getBeginDate());
				endDate = format.format(mchtContract.getEndDate());
			}	
			model.addAttribute("beginDate",beginDate);
			model.addAttribute("endDate",endDate);
		}
		ContractRenewLogExample contractRenewLogExample = new ContractRenewLogExample();
		contractRenewLogExample.createCriteria().andContractIdEqualTo(id).andDelFlagEqualTo("0");
		List<ContractRenewLogCustom> contractRenewLogs = contractRenewLogService.selectCustomByExample(contractRenewLogExample);
		model.addAttribute("contractRenewLogs",contractRenewLogs);
		return new ModelAndView(rtPage);
	}
	
	/**
	 * 
	 * @MethodName auditSubmit
	 * @Description TODO(招商提交审核)
	 * @author chengh
	 * @date 2019年8月1日 下午7:35:50
	 */
	@RequestMapping(value = "/mchtContract/auditSubmit.shtml")
	@ResponseBody
	public Map<String, Object> auditSubmit(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		String paString = request.getParameter("id");
		Integer idInteger = Integer.valueOf(paString.substring(1,paString.length()-1));
		MchtContract mchtContract=mchtContractService.selectByPrimaryKey(idInteger);
		resMap = mchtContractService.audit(request,Integer.valueOf(this.getSessionStaffBean(request).getStaffID()),mchtContract);
		
		if(org.apache.commons.lang.StringUtils.equals(mchtContract.getRenewProStatus(), "4")){
			// 发送短信给商家端主账号
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtContract.getMchtId());
			String mobile = mchtUserService.selectMobileByMchtId(mchtContract.getMchtId());
			String content = "【醒购】您【" + mchtInfo.getMchtCode() + "】的续签申请被驳回，请登记平台查看原因";
			SmsUtil.send(mobile, content, "4");
	       
			 // 发送短信给商家店铺负责人
            mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtContract.getMchtId(), 1);
            if(!StringUtils.isEmpty(mobile)){
            	SmsUtil.send(mobile, content, "4");
            }
            // 发送短信给商家运营专员
            mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtContract.getMchtId(), 2);
            if(!StringUtils.isEmpty(mobile)){
            	SmsUtil.send(mobile, content, "4");
            }
	         
			//不予续签需要发送驳回短信
			String title = "关于续签申请通知";
			String content1 = "您的续签申请被驳回，请在合同续签栏目中查看原因";
			platformMsgService.save(mchtContract.getMchtId(), title, content1, "TZ");
		}	
		return resMap;
	}

	/**
	 * 
	 * @MethodName fwAuditSubmit
	 * @Description TODO(法务提交审核)
	 * @author chengh
	 * @date 2019年8月1日 下午7:35:50
	 */
	@RequestMapping(value = "/mchtContract/fwAuditSubmit.shtml")
	@ResponseBody
	public Map<String, Object> fwAuditSubmit(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		String paString = request.getParameter("id");
		Integer idInteger = Integer.valueOf(paString.substring(1,paString.length()-1));
		MchtContract mchtContract=mchtContractService.selectByPrimaryKey(idInteger);
		resMap = mchtContractService.fwAudit(request,Integer.valueOf(this.getSessionStaffBean(request).getStaffID()),mchtContract);
		
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtContract.getMchtId());
		// 发送短信给平台招商员
	    String mobile = mchtPlatformContactService.findMobile(mchtInfo.getId(), "1");
	    String content = "【醒购】您对接的商家【" + mchtInfo.getMchtCode() + "】的续签合同已通过，请及时通知商家上传合同进行线上审核。";
	    SmsUtil.send(mobile, content, "4");
	    
		// 发送短信给商家端主账号	
		String mobile1 = mchtUserService.selectMobileByMchtId(mchtContract.getMchtId());
		String content1 = "【醒购】您【" + mchtInfo.getMchtCode() + "】的续签合同审核已通过，请及时登录商家后台上传续签合同";
		SmsUtil.send(mobile1, content1, "4");
			
		 // 发送短信给商家店铺负责人
        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtContract.getMchtId(), 1);
        if(StrKit.notBlank(mobile)){
        	SmsUtil.send(mobile, content1, "4");
        }
        // 发送短信给商家运营专员
        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtContract.getMchtId(), 2);
        if(StrKit.notBlank(mobile)){
        	SmsUtil.send(mobile, content1, "4");
        }
        
		//站内信
		String title = "关于续签合同审核通知";
		String content2 = "您的续签合同审核已通过，请及时上传续签合同";
		platformMsgService.save(mchtContract.getMchtId(), title, content2, "TZ");
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName yyAuditSubmit
	 * @Description TODO(运营提交审核)
	 * @author chengh
	 * @date 2019年8月6日 上午9:23:49
	 */
	@RequestMapping(value = "/mchtContract/yyAuditSubmit.shtml")
	@ResponseBody
	public Map<String, Object> yyAuditSubmit(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		String paString = request.getParameter("id");
		Integer idInteger = Integer.valueOf(paString.substring(1,paString.length()-1));
		MchtContract mchtContract=mchtContractService.selectByPrimaryKey(idInteger);
		resMap = mchtContractService.yyAudit(request,Integer.valueOf(this.getSessionStaffBean(request).getStaffID()),mchtContract);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName reNewRebuildMchtContract
	 * @Description TODO(续签合同重新生成)
	 * @author chengh
	 * @date 2019年8月3日 上午9:54:18
	 */
	@ResponseBody
	@RequestMapping(value = "/mchtContract/reNewRebuildMchtContract.shtml")
	public Map<String, Object> reNewRebuildMchtContract(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String resultCode = "";
		String parameter = request.getParameter("id");
		int mchtContractId = Integer.valueOf(parameter.substring(1,parameter.length()-1));
		//查询续签合同xinx
		MchtContract mchtContract = mchtContractService.selectByPrimaryKey(mchtContractId);
		String mchtId = String.valueOf(mchtContract.getMchtId());
		List<Integer> platformContactIds = new ArrayList<Integer>();
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria pcec = pce.createCriteria();
		pcec.andDelFlagEqualTo("0");
		pcec.andContactTypeEqualTo("7");// 法务对接人
		pcec.andStatusEqualTo("0");
		pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
		if (platformContacts != null && platformContacts.size() > 0) {// 是法务对接人
			platformContactIds.add(platformContacts.get(0).getId());
			PlatformContactExample e = new PlatformContactExample();
			PlatformContactExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("0");
			c.andAssistantIdEqualTo(platformContacts.get(0).getId());
			List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
			if(platformContactList!=null && platformContactList.size()>0){
				for(PlatformContact platformContact:platformContactList){
					platformContactIds.add(platformContact.getId());
				}
			}
		}
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(platformContactIds.size() == 0){
			if(sysStaffRoleList==null || sysStaffRoleList.size()==0){
				resultCode = "999";
				map.put("resultCode", resultCode);
				map.put("resultMsg", "只有该商家的法务对接人及对接人的协助人才可以重新生成");
				return map;
			}
		}
		if(platformContactIds.size()>0){
			MchtPlatformContactExample mpce = new MchtPlatformContactExample();
			mpce.createCriteria().andMchtIdEqualTo(Integer.parseInt(mchtId)).andDelFlagEqualTo("0").andStatusEqualTo("1").andPlatformContactIdIn(platformContactIds);
			List<MchtPlatformContact> mchtPlatformContacts = mchtPlatformContactService.selectByExample(mpce);
			if(mchtPlatformContacts==null || mchtPlatformContacts.size() == 0){
				if(sysStaffRoleList==null || sysStaffRoleList.size()==0){
					resultCode = "999";
					map.put("resultCode", resultCode);
					map.put("resultMsg", "只有该商家的法务对接人及对接人的协助人才可以重新生成");
					return map;
				}
			}
		}
		//续签合同重新生成
		mchtContractService.rebuildMchtContract(request,Integer.parseInt(this.getSessionStaffBean(request).getStaffID()),mchtContract);
		map.put("resultCode", resultCode);
		return map;
	} 
	
	/**
	 * 
	 * @MethodName zsConfirmRenewalIndex
	 * @Description TODO(法务确认页面)
	 * @author chengh
	 * @date 2019年7月31日 下午2:14:38
	 */
	@RequestMapping(value = "/mchtContract/fwConfirmRenewalIndex.shtml")
	public ModelAndView fwConfirmRenewalIndex(HttpServletRequest request) {
		String rtPage = "/mchtContract/fwConfirmRenewalIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String staffID = this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
		resMap.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		resMap.put("isManagement", isManagement);
		resMap.put("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @MethodName contractExpireList
	 * @Description TODO(法务确认续签列表)
	 * @author chengh
	 * @date 2019年7月30日 下午7:22:24
	 */
	@RequestMapping(value = "/mchtContract/fwConfirmRenewalList.shtml")
	@ResponseBody
	public Map<String, Object> fwConfirmRenewalList(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<MchtContractCustom> mchtInfoCustoms = new ArrayList<MchtContractCustom>();
		try {
			MchtContractCustomExample mchtContractCustomExample = new MchtContractCustomExample();
			MchtContractCustomCriteria criteria = mchtContractCustomExample.createCriteria();
			//续签状态=确认续签（招商确认)、已生成合同
			criteria.andDelFlagEqualTo("0");
			//按照招商提交时的需求时间排序
			mchtContractCustomExample.setOrderByClause(" zs_log_create_date DESC");
			//商家序列
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				criteria.andMchtCodeEqualTo(request
						.getParameter("mchtCode"));
			}
			//名称
			if (!StringUtil.isEmpty(request.getParameter("companyOrShop"))) {
				criteria.andcompanyOrShopNameLike(request.getParameter("companyOrShop"));
			}
			//类目
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				criteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
			}
			//对接人
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				criteria.andPlatContactStaffIdEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));
			}
			//法务确认状态
			if (!StringUtil.isEmpty(request.getParameter("renewProStatusFW"))) {
				criteria.andRenewProStatusEqualTo(request.getParameter("renewProStatusFW"));
			}
			
			totalCount = mchtContractService.countByExampleCustomFW(mchtContractCustomExample);
			mchtContractCustomExample.setLimitSize(page.getLimitSize());
			mchtContractCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoCustoms = mchtContractService.selectByExampleCustomFW(mchtContractCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtInfoCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName renewalContract
	 * @Description TODO(重新生成合同页面)
	 * @author chengh
	 * @date 2019年8月2日 下午8:20:49
	 */
	@RequestMapping(value = "/mchtContract/renewalContract.shtml")
	public ModelAndView renewalContract(HttpServletRequest request) {
		String rtPage = "/mchtContract/renewalContract";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Integer id = Integer.valueOf(request.getParameter("id"));
		MchtContract mchtContract = mchtContractService.selectByPrimaryKey(id);
		MchtInfoExample mchtInfoExample = new MchtInfoExample();
		mchtInfoExample.createCriteria().andIdEqualTo(mchtContract.getMchtId()).andDelFlagEqualTo("0");
		Map<String,Object> mchtInfo = mchtInfoService.selectZSRenewalDetailByExample(mchtInfoExample);
		resMap.put("mchtInfo", mchtInfo);
		resMap.put("beginDate", format.format(mchtContract.getBeginDate()));
		resMap.put("endDate", format.format(mchtContract.getEndDate()));		
		resMap.put("mchtContract", mchtContract);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * 合同已归档
	 */
	@RequestMapping(value = "/mchtContract/listArchivePass.shtml")
	public ModelAndView listArchivePass(HttpServletRequest request) {
		String page = "/mchtContract/listArchivePass";
		Map<String, Object> data = new HashMap<String, Object>();
		//data.put("mcht_type", statusService.querytStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		data.put("isManagement", isManagement);
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria pcec = pce.createCriteria();
		pcec.andDelFlagEqualTo("0");
		pcec.andContactTypeEqualTo("1");//招商对接人
		pcec.andStatusEqualTo("0");
		pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
		if(platformContacts!=null && platformContacts.size()>0){//是招商对接人
			data.put("myContactId", platformContacts.get(0).getId());
			PlatformContactExample e = new PlatformContactExample();
			PlatformContactExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("0");
			c.andAssistantIdEqualTo(platformContacts.get(0).getId());
			List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
			data.put("platformContacts", platformContactList);
			data.put("isAssistant", true);
		}else{
			PlatformContactCustomExample zsPlatformExample = new PlatformContactCustomExample();
			zsPlatformExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
			List<PlatformContactCustom> zsPlatformContactCustoms = platformContactService.selectPlatformContactCustomByExample(zsPlatformExample);
			data.put("zsPlatformContactCustomList", zsPlatformContactCustoms);
			data.put("isAssistant", false);
		}
		data.put("mchtInfoStatus", "1");
		data.put("archiveStatus", "1");
		boolean hasAuth = false;
		PlatformContactExample e=new PlatformContactExample();
		List<String> contactTypeList = new ArrayList<String>();
		contactTypeList.add("1");//招商
		contactTypeList.add("7");//法务
		e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID())).andContactTypeIn(contactTypeList);
		List<PlatformContact> platformContactList=platformContactService.selectByExample(e);
		if(platformContactList.size()>0){
			hasAuth = true;
		}
		data.put("hasAuth", hasAuth);
		return new ModelAndView(page, data);
	}

	/**
	 * 合同续签中
	 * 合同分类=续签 && 合同归档状态！=通过已归档
	 */
	@RequestMapping(value = "/mchtContract/listRenewArchiveProcessing.shtml")
	public ModelAndView listRenewArchiveProcessing() {
		String page = "/mchtContract/listRenewArchiveProcessing";
		Map<String, Object> data = new HashMap<String, Object>();
		//data.put("mcht_type", statusService.querytStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
		return new ModelAndView(page, data);
	}
	
	/**
	 * 全部合同
	 */
	@RequestMapping(value = "/mchtContract/listMchtContract.shtml")
	public ModelAndView listMchtContract() {
		String page = "/mchtContract/listMchtContract";
		Map<String, Object> data = new HashMap<String, Object>();
		
		StaffBean currentUser = getSessionStaffBean();
		
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(currentUser.getStaffID()));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
		data.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);

		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(currentUser.getStaffID());
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		data.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(currentUser.getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		data.put("role107", sysStaffRoleList!=null && sysStaffRoleList.size()>0);
		
		return new ModelAndView(page, data);
	}

	/**
	 * 合同列表数据
	 */
	@RequestMapping(value = "/mchtContract/listMchtContractData.shtml")
	@ResponseBody
	public String listMchtContractData() {
		return json(doAction(new CMchtContractList()));
	}

	/**
	 * 合同列表数据
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mchtContract/exportMchtContractData.shtml")
	public void exportMchtContractData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("cmd", "export");
		List<MchtContract> list = (List<MchtContract>)doAction(new CMchtContractList()).get("list");
		
		String[] titles = { "开店日期", "商家序号", "公司名称", "店铺名称", "合同开始日期", "合同结束日期",
				"合同归档状态", "合同编号", "内部备注", "驳回原因", "法务对接人", "招商对接人" };
		ExcelBean excelBean = new ExcelBean("导出合同.xls", "导出合同", titles);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<String[]> datas = new ArrayList<String[]>();
		for(MchtContract info:list){
			MchtInfo mchtInfo = info.get("mchtInfo");
			PlatformContact platformMerchantsContact = info.get("platformMerchantsContact");
			PlatformContact platformFawuContact = info.get("platformFawuContact");
			
			// 开店日期
			String cooperateBeginDate = "";
			if (mchtInfo.getCooperateBeginDate() != null) {
				cooperateBeginDate = df.format(mchtInfo.getCooperateBeginDate());
			}
			
			// 合同开始日期
			String contractBeginDate = "";
			if (info.getBeginDate() != null) {
				contractBeginDate = df.format(info.getBeginDate());
			}
			
			// 合同结束日期
			String contractEndDate = "";
			if (info.getEndDate() != null) {
				contractEndDate = df.format(info.getEndDate());
			}
			
			String[] data = {
				cooperateBeginDate,
				mchtInfo.getMchtCode(),
				mchtInfo.getCompanyName(),
				mchtInfo.getShopName(),
				contractBeginDate,
				contractEndDate,
				info.get("archiveStatusStr"),
				info.getContractCode(),
				info.getFwInnerRemarks(),
				info.getRemarks(),
				platformFawuContact != null ? platformFawuContact.getContactName() : "",
						platformMerchantsContact != null ? platformMerchantsContact.getContactName() : "",
			};
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	}

	/**
	 * 
	 * @MethodName export
	 * @Description TODO(导出)
	 * @author chengh
	 * @date 2019年8月5日 下午1:49:22
	 */
	@RequestMapping(value = "/mchtContract/exports.shtml")
	@ResponseBody
	public void exports(HttpServletResponse response) {
		Map<String, Object> mchtContarcts = doAction(new CMchtContractList());
		List<Map<String, Object>> list = (List<Map<String, Object>>) mchtContarcts.get("Rows");
		try{
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
		String[] titles = { "开通日期", "商家序号", "公司名称","店铺名称","合同开始日期","合同结束日期","合同归档状态","合同编号","备注","法务对接人","招商对接人"};
		ExcelBean excelBean = new ExcelBean("续签合同归档列表.xls",
				"续签合同归档列表", titles);
		List<String[]> datas = new ArrayList<String[]>();
		for(Map<String, Object> map : list) {
			MchtInfo mchtInfo = (MchtInfo) map.get("mchtInfo");
			PlatformContact platformContact = (PlatformContact) map.get("platformFawuContact");
			PlatformContact platformContact1 = (PlatformContact) map.get("platformMerchantsContact");
			String platformFawuContact = "";
			String platformMerchantsContact = "";
			if(platformContact == null){
				platformFawuContact = "";
			}else{
				platformFawuContact = platformContact.getContactName();
			}
			
			if(platformContact1 == null){
				platformMerchantsContact = "";
			}else{
				platformMerchantsContact = platformContact1.getContactName();
			}
			String[] data = {
				format.format(map.get("createDate")),
				mchtInfo.getMchtCode(),
				mchtInfo.getCompanyName(),
				mchtInfo.getShopName(),
				format.format(map.get("beginDate")),
				format.format(map.get("endDate")),
				(String) map.get("archiveStatusStr"),
				(String) map.get("contractCode"),
				(String) map.get("fwInnerRemarks"),
				platformFawuContact,
				platformMerchantsContact
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
	 * 
	 * @MethodName reNewContractOnlineAuditIndex
	 * @Description TODO(续签合同线上审核页面)
	 * @author chengh
	 * @date 2019年8月3日 下午1:56:47
	 */
	@RequestMapping(value = "/mchtContract/reNewContractOnlineAuditIndex.shtml")
	public ModelAndView reNewContractOnlineAuditIndex(HttpServletRequest request) {
		String rtPage = "/mchtContract/reNewContractOnlineAuditIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String staffID = this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
		resMap.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		resMap.put("isManagement", isManagement);
		resMap.put("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @MethodName reNewContractOnlineAuditList
	 * @Description TODO(续签合同线上审核数据)
	 * @author chengh
	 * @date 2019年8月3日 下午2:18:40
	 */
	@RequestMapping(value = "/mchtContract/reNewContractOnlineAuditList.shtml")
	@ResponseBody
	public Map<String, Object> reNewContractOnlineAuditList(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<MchtContractCustom> mchtInfoCustoms = new ArrayList<MchtContractCustom>();
		try {
			MchtContractCustomExample mchtContractCustomExample = new MchtContractCustomExample();
			MchtContractCustomCriteria criteria = mchtContractCustomExample.createCriteria();
			//续签状态是待申请的且合同类型是续签的
			//商家序号
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				criteria.andMchtCodeEqualTo(request
						.getParameter("mchtCode"));
			}
			//名称
			if (!StringUtil.isEmpty(request.getParameter("companyOrShop"))) {
				criteria.andcompanyOrShopNameLike(request.getParameter("companyOrShop"));
			}
			//类目
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				criteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
			}
			//对接人
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				criteria.andPlatContactStaffIdEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));
			}
			//线上合同状态
			if (!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
				criteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
			}			
			totalCount = mchtContractService.countByExampleCustomOnlineFW(mchtContractCustomExample);
			mchtContractCustomExample.setLimitSize(page.getLimitSize());
			mchtContractCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoCustoms = mchtContractService.selectByExampleCustomOnlineFW(mchtContractCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtInfoCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName modifyAuditRemarks
	 * @Description TODO(修改线上审核备注页面)
	 * @author chengh
	 * @date 2019年8月3日 下午5:15:06
	 */
	@RequestMapping(value = "/mchtContract/modifyAuditRemarks.shtml")
	public ModelAndView modifyAuditRemarks(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		MchtContract mchtContract = mchtContractService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		resMap.put("mchtContract", mchtContract);
		return new ModelAndView("/mchtContract/modifyAuditRemarks",resMap);
	}
	
	/**
	 * 
	 * @MethodName modify
	 * @Description TODO(修改备注)
	 * @author chengh
	 * @date 2019年7月23日 上午11:20:56
	 */
	@RequestMapping(value = "/mchtContract/modify.shtml")
	@ResponseBody
	public Map<String, Object> modify(HttpServletRequest request,MchtContract mchtContract) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = "提交成功！";
		try {
			mchtContractService.updateByPrimaryKeySelective(mchtContract);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put("statusCode", code);
		map.put("message", msg);
		return map;
	}
	
	/**
	 * 
	 * @MethodName shopSuspendedIndex
	 * @Description TODO(店铺待暂停页面)
	 * @author chengh
	 * @date 2019年8月5日 下午7:31:15
	 */
	@RequestMapping(value = "/mchtContract/shopSuspendedIndex.shtml")
	public ModelAndView shopSuspendedIndex(HttpServletRequest request) {
		String rtPage = "/mchtContract/shopSuspendedIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String staffID = this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
		resMap.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		resMap.put("isManagement", isManagement);
		resMap.put("staffID", staffID);
		
		SysStaffRoleExample staffRoleExample = new SysStaffRoleExample();
		staffRoleExample.createCriteria().andStaffIdEqualTo(Integer.valueOf(staffID)).andRoleIdEqualTo(122).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoles = sysStaffRoleService.selectByExample(staffRoleExample);
		if(!sysStaffRoles.isEmpty()){
			resMap.put("roleId", sysStaffRoles.get(0).getRoleId());
		}	
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @MethodName shopSuspendedList
	 * @Description TODO(店铺待暂停列表)
	 * @author chengh
	 * @date 2019年8月5日 下午7:28:58
	 */
	@RequestMapping(value = "/mchtContract/shopSuspendedList.shtml")
	@ResponseBody
	public Map<String, Object> shopSuspendedList(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<MchtContractCustom> mchtInfoCustoms = new ArrayList<MchtContractCustom>();
		try {
			MchtContractCustomExample mchtContractCustomExample = new MchtContractCustomExample();
			mchtContractCustomExample.setOrderByClause(" log_create_date_5 desc");
			MchtContractCustomCriteria criteria = mchtContractCustomExample.createCriteria();
			//商家最新合同续签状态=确认不续签（店铺暂停）
			criteria.andDelFlagEqualTo("0");
			//商家序列
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				criteria.andMchtCodeEqualTo(request
						.getParameter("mchtCode"));
			}
			//名称
			if (!StringUtil.isEmpty(request.getParameter("companyOrShop"))) {
				criteria.andcompanyOrShopNameLike(request.getParameter("companyOrShop"));
			}
			//类目
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				criteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
			}
			//对接人
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				criteria.andPlatContactStaffIdEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));
			}
			//商家合作状态
			if (!StringUtil.isEmpty(request.getParameter("mchtInfoStatus"))) {
				criteria.andMchtInfoStatusEqualTo(request.getParameter("mchtInfoStatus"));
			}
			totalCount = mchtContractService.countByExampleCustomSuspended(mchtContractCustomExample);
			mchtContractCustomExample.setLimitSize(page.getLimitSize());
			mchtContractCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoCustoms = mchtContractService.selectByExampleCustomSuspended(mchtContractCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtInfoCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName soonStop
	 * @Description TODO(立即暂停)
	 * @author chengh
	 * @date 2019年7月22日 下午3:05:25
	 */
	@RequestMapping(value = "/mchtContract/soonStop.shtml")
	@ResponseBody
	public Map<String, Object> soonStop(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			MchtInfo mi = new MchtInfo();
			mi.setStatus("2");
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			MchtInfoExample e = new MchtInfoExample();
			e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtId);
			mchtInfoService.updateByExampleSelective(mi, e);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName notRenewalIndex
	 * @Description TODO(商家不续签页面)
	 * @author chengh
	 * @date 2019年8月5日 下午7:31:15
	 */
	@RequestMapping(value = "/mchtContract/notRenewalIndex.shtml")
	public ModelAndView notRenewalIndex(HttpServletRequest request) {
		String rtPage = "/mchtContract/notRenewalIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String staffID = this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
		resMap.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		resMap.put("isManagement", isManagement);
		resMap.put("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @MethodName notRenewalList
	 * @Description TODO(商家不续签管理列表)
	 * @author chengh
	 * @date 2019年8月5日 下午7:28:58
	 */
	@RequestMapping(value = "/mchtContract/notRenewalList.shtml")
	@ResponseBody
	public Map<String, Object> notRenewalList(HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<MchtContractCustom> mchtInfoCustoms = new ArrayList<MchtContractCustom>();
		try {
			MchtContractCustomExample mchtContractCustomExample = new MchtContractCustomExample();
			MchtContractCustomCriteria criteria = mchtContractCustomExample.createCriteria();
			//合同续签状态=不再续签、不予续签（招商操作）
			criteria.andDelFlagEqualTo("0");
			//商家序列
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				criteria.andMchtCodeEqualTo(request
						.getParameter("mchtCode"));
			}
			//名称
			if (!StringUtil.isEmpty(request.getParameter("companyOrShop"))) {
				criteria.andcompanyOrShopNameLike(request.getParameter("companyOrShop"));
			}
			//类目
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				criteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
			}
			//对接人
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				criteria.andPlatContactStaffIdEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));
			}
			//确认状态
			if (!StringUtil.isEmpty(request.getParameter("renewProStatusFW"))) {
				criteria.andRenewProStatusEqualTo(request.getParameter("renewProStatusFW"));
			}
			//不续签确认方
			if (!StringUtil.isEmpty(request.getParameter("type"))) {
				criteria.andRenewProStatusEqualTo(request.getParameter("type"));
			}
			
			totalCount = mchtContractService.countByExampleCustomNotRenew(mchtContractCustomExample);
			mchtContractCustomExample.setLimitSize(page.getLimitSize());
			mchtContractCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoCustoms = mchtContractService.selectByExampleCustomNotRenew(mchtContractCustomExample);
			//按照提交时间（商家提交与招商确认不续签时间）倒叙排序。
			for (MchtContractCustom mchtContractCustom : mchtInfoCustoms) {
				if(mchtContractCustom.getLogCreateDate1() != null && mchtContractCustom.getLogCreateDate4() != null){
					if(mchtContractCustom.getLogCreateDate1().compareTo(mchtContractCustom.getLogCreateDate4())>0){
						mchtContractCustom.setLogCreateDate(mchtContractCustom.getLogCreateDate1());
					}else{
						mchtContractCustom.setLogCreateDate(mchtContractCustom.getLogCreateDate4());
					}
				}else if(mchtContractCustom.getLogCreateDate1() != null){
					mchtContractCustom.setLogCreateDate(mchtContractCustom.getLogCreateDate1());
				}else{
					mchtContractCustom.setLogCreateDate(mchtContractCustom.getLogCreateDate4());
				}
			}
			Collections.sort(mchtInfoCustoms, new Comparator<MchtContractCustom>() {
				@Override
				public int compare(MchtContractCustom o1, MchtContractCustom o2) {
					// TODO Auto-generated method stub
	                return o1.getLogCreateDate().compareTo(o2.getLogCreateDate());
				}			  
	        });
			Collections.reverse(mchtInfoCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtInfoCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 商家列表数据
	 */
	@RequestMapping(value = "/mchtContract/listMchtInfoData.shtml")
	@ResponseBody
	public String listMchtInfoData() {

		return json(doAction(new CMchtInfoList()));
	}

	/**
	 * 到期合同统计
	 */
	@RequestMapping(value = "/mchtContract/statExpire.shtml")
	public ModelAndView statExpire() {
		String page = "/mchtContract/statExpire";
		Map<String, Object> data = new HashMap<String, Object>();
		//data.put("mcht_type", statusService.querytStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
		return new ModelAndView(page, data);
	}

	/**
	 * 到期合同统计数据
	 */
	@RequestMapping(value = "/mchtContract/statExpireData.shtml")
	@ResponseBody
	public String statExpireData() {

		return json(doAction(new CMchtInfoStatExpire()));
	}

	/**
	 * 直接关店
     */
	@RequestMapping(value = "/mchtContract/closeShop.shtml")
	@ResponseBody
	public String closeShop() {

		return json();
	}

	/**
	 * 申请关店
     */
	@RequestMapping(value = "/mchtContract/requestCloseShop.shtml")
	@ResponseBody
	public String requestCloseShop() {

		return json();
	}

	/**
	 * 合同查看
	 */
	@RequestMapping(value = "/mchtContract/viewMchtContract.shtml")
	public ModelAndView viewMchtContract(HttpServletRequest request) {
		String page = "/mchtContract/viewMchtContract";
		int id = getParaToInt("id");
		Assert.moreThanZero(id, "合同ID不能为空");

		Map<String, Object> data = new HashMap<String, Object>();

		MchtContract mchtContract = mchtContractService.findById(id);
		mchtContract.put("auditStatusStr", Const.getMchtContractAuditStatusStr(mchtContract.getAuditStatus()));	//合同审核状态
		mchtContract.put("archiveStatusStr", Const.getMchtContractArchiveStatusStr(mchtContract.getArchiveStatus()));	//合同归档状态
		mchtContract.put("renewStatusStr", Const.getMchtContractRenewStatusStr(mchtContract.getRenewStatus()));	//合同续签状态
		mchtContract.put("contractTypeStr", Const.getMchtContractTypeStr(mchtContract.getContractType()));	//签约分类
		mchtContract.put("renewProStatusStr",Const.getMchtContractRenewProStatusStr(mchtContract.getRenewProStatus()));//续签进度
		mchtContract.put("isMchtSendStr", Const.getMchtContractSendStatusStr(mchtContract.getIsMchtSend()));	//合同商家寄件状态
		if(mchtContract.getMchtExpressId() != null){
			mchtContract.put("mchtExpressName", expressService.selectByPrimaryKey(mchtContract.getMchtExpressId()).getName());	//商家寄件快递名称
		}

		mchtContract.put("isPlatformSendStr", Const.getMchtContractSendStatusStr(mchtContract.getIsPlatformSend()));	//平台寄回状态
		if(mchtContract.getPlatformExpressId() != null){
			mchtContract.put("platformExpressName", expressService.selectByPrimaryKey(mchtContract.getPlatformExpressId()).getName());	//平台寄回快递名称
		}
		if(mchtContract.getArchiveBy() != null){
			mchtContract.put("archiveStaffName",  sysStaffInfoService.selectByPrimaryKey(mchtContract.getArchiveBy()).getStaffName());
		}
		data.put("mchtContract", mchtContract);

		// 上份合同
		if(Const.MCHT_CONTRACT_TYPE_RENEW.equals(mchtContract.getContractType()) && mchtContract.getLastContractId() != null && mchtContract.getLastContractId() > 0){
			MchtContract lastMchtContract = mchtContractService.findById(mchtContract.getLastContractId());
			data.put("lastMchtContract", lastMchtContract);
		}
		// 续签合同
		if(mchtContract.getRenewStatus().equals(Const.MCHT_CONTRACT_RENEW_STATUS_YES) && mchtContract.getLastContractId()!=null){
			MchtContract renewMchtContract = mchtContractService.findByLastContractId(mchtContract.getId());
			if(renewMchtContract != null){
				renewMchtContract.put("archiveStatusStr", Const.getMchtContractArchiveStatusStr(renewMchtContract.getArchiveStatus()));	//合同归档状态
				data.put("renewMchtContract", renewMchtContract);
			}
		}

		MchtInfo mchtInfo = mchtInfoService.findById(mchtContract.getMchtId());
		mchtInfo.put("mchtTypeStr", Const.getMchtTypeStr(mchtInfo.getMchtType()));	//合作模式
		mchtInfo.put("statusStr", Const.getMchtStatusStr(mchtInfo.getStatus()));	//合作状态
		mchtInfo.put("totalAuditStatusStr", Const.getMchtTotalAuditStatusStr(mchtInfo.getTotalAuditStatus()));	//总资质状态
		data.put("mchtInfo", mchtInfo);
		
		PlatformContactExample e = new PlatformContactExample();
		e.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andStatusEqualTo("0").andContactTypeEqualTo("7");
		List<PlatformContact> platformContacts = platformContactService.selectByExample(e);
		if(platformContacts!=null && platformContacts.size()>0){
			data.put("isFw", 1);
		}else{
			data.put("isFw", 0);
		}
		//判断是否是新合同查看
		data.put("isNew", request.getParameter("isNew"));
		return new ModelAndView(page, data);
	}


	/**
	 * 合同审核
	 */
	@RequestMapping(value = "/mchtContract/audit.shtml")
	public ModelAndView audit() {
		String page = "/mchtContract/audit";
		int id = getParaToInt("id");
		Assert.moreThanZero(id, "请输入合同ID");
		MchtContract model = mchtContractService.findById(id);
		Assert.notNull(model, "合同不存在");

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", model);
		MchtContractPicExample mcpe = new MchtContractPicExample();
		mcpe.createCriteria().andDelFlagEqualTo("0").andContractIdEqualTo(id);
		List<MchtContractPic> mchtContractPics = mchtContractPicService.selectByExample(mcpe);
		data.put("mchtContractPics", mchtContractPics);
		if(StringUtils.isBlank(getPara("renewType"))){
			data.put("renewType", "0");
		}else{
			data.put("renewType",  getPara("renewType"));
		}
		data.put("isNeedUpload", "1");//是否上传标识

		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(model.getMchtId());
		if ("2".equals(mchtInfo.getMchtType())){
			data.put("mchtTag","POP");
		}else {
			if ("1".equals(mchtInfo.getIsManageSelf())){
				data.put("mchtTag","自营SPOP");
				String isNeedUpload = model.getIsNeedUpload();//自营SPOP商家可以选择是否需要上传,无需上传,页面不用展示
				if ("0".equals(isNeedUpload)){
					data.put("isNeedUpload", "0");
				}
			}else {
				data.put("mchtTag","SPOP");
			}
		}

		return new ModelAndView(page, data);
	}

	/**
	 * 合同审核提交
	 */
	@RequestMapping(value = "/mchtContract/auditCommit.shtml")
	@ResponseBody
	public String auditCommit() {
		StaffBean currentUser = getSessionStaffBean();

		MchtContract param = getBean(MchtContract.class, "model");
		Assert.moreThanZero(param.getId(), "请输入合同ID");
		Assert.notBlank(param.getAuditStatus(), "请填写审核状态");
		if(param.getAuditStatus().equals(Const.MCHT_CONTRACT_AUDIT_STATUS_REJECT)){
			Assert.notBlank(param.getRemarks(), "请填写驳回原因");
		}

		MchtContract model = mchtContractService.findById(param.getId());
		Assert.notNull(model, "合同不存在");
		if(!Const.MCHT_CONTRACT_AUDIT_STATUS_WAIT.equals(model.getAuditStatus())){
			throw new BizException("不是待审核状态的合同不能进行审核");
		}

		if(param.getAuditStatus().equals(Const.MCHT_CONTRACT_AUDIT_STATUS_PASS)){
			//此处判断是入驻时的线上审核还是续签时的线上审核
			if(org.apache.commons.lang.StringUtils.equals("1", getPara("renewType"))){
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(model.getMchtId());
				mchtContractService.renewAuditPass(currentUser.getStaffID(), model.getId(), param.getRemarks());
				// 发送短信给商家端主账号	
				String mobile1 = mchtUserService.selectMobileByMchtId(model.getMchtId());
				String content1 = "【醒购】您【" + mchtInfo.getMchtCode() + "】的续签合同审核已通过，请及时登录平台查看寄件内容并寄往平台";
				SmsUtil.send(mobile1, content1, "4");
				// 发送短信给商家店铺负责人
		        String mobile2 = mchtUserService.selectMchtContactMobileByMchtId(model.getMchtId(), 1);
		        if(!StringUtils.isEmpty(mobile2)){
		        	SmsUtil.send(mobile2, content1, "4");
		        }
		        // 发送短信给商家运营专员
		        String mobile3 = mchtUserService.selectMchtContactMobileByMchtId(model.getMchtId(), 2);
		        if(!StringUtils.isEmpty(mobile3)){
		        	SmsUtil.send(mobile3, content1, "4");
		        }
				//站内信
				String title = "关于续签合同线上审核通知";
				String content2 = "您续签合同线上审核已通过，请及时查看寄件内容并寄往平台";
				platformMsgService.save(model.getMchtId(), title, content2, "TZ");
			}else{
				mchtContractService.auditPass(currentUser.getStaffID(), model.getId(), param.getRemarks());
			}
		}else if(param.getAuditStatus().equals(Const.MCHT_CONTRACT_AUDIT_STATUS_REJECT)){
			if(org.apache.commons.lang.StringUtils.equals("1", getPara("renewType"))){
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(model.getMchtId());
				mchtContractService.renewAuditReject(currentUser.getStaffID(), model.getId(), param.getRemarks());
				// 发送短信给商家端主账号	
				String mobile1 = mchtUserService.selectMobileByMchtId(model.getMchtId());
				String content1 = "【醒购】您【" + mchtInfo.getMchtCode() + "】的续签合同线上审核被驳回，请及时登录平台查看原因并重新上传";
				SmsUtil.send(mobile1, content1, "4");
				// 发送短信给商家店铺负责人
		        String mobile2 = mchtUserService.selectMchtContactMobileByMchtId(model.getMchtId(), 1);
		        if(!StringUtils.isEmpty(mobile2)){
		        	SmsUtil.send(mobile2, content1, "4");
		        }
		        // 发送短信给商家运营专员
		        String mobile3 = mchtUserService.selectMchtContactMobileByMchtId(model.getMchtId(), 2);
		        if(!StringUtils.isEmpty(mobile3)){
		        	SmsUtil.send(mobile3, content1, "4");
		        }
				//站内信
				String title = "关于续签合同线上审核通知";
				String content2 = "您续签合同线上审核被驳回，请及前往合同续签栏目查看原因并重新上传。";
				platformMsgService.save(model.getMchtId(), title, content2, "TZ");
			}
			mchtContractService.auditReject(currentUser.getStaffID(), model.getId(), param.getRemarks());
		}

		return json();
	}

	/**
	 * 
	 * @MethodName modifyfwInnerRemarks
	 * @Description TODO(修改法务备注页面)
	 * @author chengh
	 * @date 2019年8月5日 下午3:31:56
	 */
	@RequestMapping(value = "/mchtContract/modifyFwInnerRemarks.shtml")
	public ModelAndView modifyFwInnerRemarks(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		MchtContract mchtContract = mchtContractService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		resMap.put("mchtContract", mchtContract);
		List<Integer> platformContactIds = new ArrayList<Integer>();
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria pcec = pce.createCriteria();
		pcec.andDelFlagEqualTo("0");
		pcec.andContactTypeEqualTo("7");// 法务对接人
		pcec.andStatusEqualTo("0");
		pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
		if (platformContacts != null && platformContacts.size() > 0) {// 是法务对接人
			platformContactIds.add(platformContacts.get(0).getId());
			PlatformContactExample e = new PlatformContactExample();
			PlatformContactExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("0");
			c.andAssistantIdEqualTo(platformContacts.get(0).getId());
			List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
			if(platformContactList!=null && platformContactList.size()>0){
				for(PlatformContact platformContact:platformContactList){
					platformContactIds.add(platformContact.getId());
				}
			}
		}
		if(platformContactIds.size() == 0){
			SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
			List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
			if(sysStaffRoleList==null || sysStaffRoleList.size() == 0){
				resMap.put("isView", 1);
			}
		}
		if(platformContactIds.size() > 0){
			MchtPlatformContactExample mpce = new MchtPlatformContactExample();
			mpce.createCriteria().andMchtIdEqualTo(mchtContract.getMchtId()).andDelFlagEqualTo("0").andStatusEqualTo("1").andPlatformContactIdIn(platformContactIds);
			List<MchtPlatformContact> mchtPlatformContacts = mchtPlatformContactService.selectByExample(mpce);
			if(mchtPlatformContacts==null || mchtPlatformContacts.size() == 0){
				resMap.put("isView", 1);
			}
		}
		return new ModelAndView("/mchtContract/modifyFwInnerRemarks",resMap);
	}
	
	/**
	 * 
	 * @MethodName modifyFwInnerRemark
	 * @Description TODO(修改法无备注)
	 * @author chengh
	 * @date 2019年7月23日 上午11:20:56
	 */
	@RequestMapping(value = "/mchtContract/modifyFwInnerRemark.shtml")
	@ResponseBody
	public Map<String, Object> modifyFwInnerRemark(HttpServletRequest request,MchtContract mchtContract) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = "提交成功！";
		try {
			mchtContractService.updateByPrimaryKeySelective(mchtContract);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put("statusCode", code);
		map.put("message", msg);
		return map;
	}
	
	/**
	 * 合同归档
	 */
	@RequestMapping(value = "/mchtContract/archive.shtml")
	public ModelAndView archive(HttpServletRequest request) {
		String page = "/mchtContract/archive";
		int id = getParaToInt("id");
		Assert.moreThanZero(id, "请输入合同ID");
		MchtContract model = mchtContractService.findById(id);
		Assert.notNull(model, "合同不存在");



		Map<String, Object> data = new HashMap<String, Object>();
		//合同
		data.put("model", model);

		// 已归档合同数
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", model.getMchtId());
		queryObject.addQuery("archiveStatus", Const.MCHT_CONTRACT_ARCHIVE_STATUS_PASS);
		int archivePassCount = mchtContractService.count(queryObject);
		data.put("archivePassCount", archivePassCount);

		// 快递列表
		data.put("expressList", expressService.selectByExample(new ExpressExample()));
		data.put("remarks", model.getRemarks());
		String mchtId = request.getParameter("mchtId");
		if(StringUtils.isEmpty(mchtId)){
			mchtId = model.getMchtId().toString();
		}
		List<Integer> platformContactIds = new ArrayList<Integer>();
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria pcec = pce.createCriteria();
		pcec.andDelFlagEqualTo("0");
		pcec.andContactTypeEqualTo("7");// 法务对接人
		pcec.andStatusEqualTo("0");
		pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
		if (platformContacts != null && platformContacts.size() > 0) {// 是法务对接人
			platformContactIds.add(platformContacts.get(0).getId());
			PlatformContactExample e = new PlatformContactExample();
			PlatformContactExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("0");
			c.andAssistantIdEqualTo(platformContacts.get(0).getId());
			List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
			if(platformContactList!=null && platformContactList.size()>0){
				for(PlatformContact platformContact:platformContactList){
					platformContactIds.add(platformContact.getId());
				}
			}
		}
		if(platformContactIds.size() == 0){
			SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
			List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
			if(sysStaffRoleList==null || sysStaffRoleList.size() == 0){
				data.put("isView", 1);
			}
		}
		if(platformContactIds.size() > 0){
			MchtPlatformContactExample mpce = new MchtPlatformContactExample();
			mpce.createCriteria().andMchtIdEqualTo(Integer.parseInt(mchtId)).andDelFlagEqualTo("0").andStatusEqualTo("1").andPlatformContactIdIn(platformContactIds);
			List<MchtPlatformContact> mchtPlatformContacts = mchtPlatformContactService.selectByExample(mpce);
			if(mchtPlatformContacts==null || mchtPlatformContacts.size() == 0){
				data.put("isView", 1);
			}
		}
		
		// todelete
		data.put("isView", null);
		return new ModelAndView(page, data);
	}

	/**
	 * 合同归档提交
	 */
	@RequestMapping(value = "/mchtContract/archiveCommit.shtml")
	@ResponseBody
	public String archiveCommit() {

		return json(doAction(new CMchtContractArchiveCommit()));
	}

	/**
	 * 合同续签编辑
	 */
	@RequestMapping(value = "/mchtContract/renew.shtml")
	public ModelAndView renew() {
		String page = "/mchtContract/renew";
		int id = getParaToInt("id");
		MchtContract model = mchtContractService.findById(id);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", model);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date newContractBeginDate = DateUtil.getDateAfter(model.getEndDate(), 1);
		Date newContractEndDate = DateUtil.getMonthsAgo(newContractBeginDate,12);
		data.put("newContractBeginDate", sdf.format(newContractBeginDate));
		data.put("newContractEndDate", sdf.format(newContractEndDate));
		data.put("oldEndDate", sdf.format(model.getEndDate()));
		return new ModelAndView(page, data);
	}

	/**
	 * 合同续签提交
	 */
	@RequestMapping(value = "/mchtContract/renewCommit.shtml")
	@ResponseBody
	public String renewCommit() {
		StaffBean currentUser = getSessionStaffBean();
		MchtContract model = getBean(MchtContract.class, "model");
		Assert.moreThanZero(model.getId(), "合同ID不能为空");
		if(!Const.MCHT_CONTRACT_RENEW_STATUS_YES.equals(model.getRenewStatus()) && !Const.MCHT_CONTRACT_RENEW_STATUS_NO.equals(model.getRenewStatus())){
			throw new BizException("合同续签状态不对");
		}
		Assert.notBlank(model.getRenewRemarks(), "续签说明不能为空");
		if(Const.MCHT_CONTRACT_RENEW_STATUS_YES.equals(model.getRenewStatus())){
			Date beginDate = getParaToDate("newContractBeginDate");
			Date endDate = getParaToDate("newContractEndDate");
			mchtContractService.renewYes(currentUser.getStaffID(), model.getId(), model.getRenewRemarks(), beginDate, endDate);
		}else{
			mchtContractService.renewNo(currentUser.getStaffID(), model.getId(), model.getRenewRemarks());
		}

		return json();
	}


	/**
	 * 合同扫描图片
	 */
	@RequestMapping(value = "/mchtContract/viewContractPic.shtml")
	@ResponseBody
	public String viewContractPic() {
		int id = getParaToInt("id");
		MchtContract mchtContract = mchtContractService.findById(id);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("picList", mchtContractPicService.listByContractId(mchtContract.getId()));
		return json(data);
	}

	/**
	 * 查看合同扫描图片
	 */
	@RequestMapping(value = "/mchtContract/viewContractPicPage.shtml")
	public ModelAndView viewContractPicPage() {
		String page = "/mchtContract/viewContractPic";
		int id = getParaToInt("id");
		MchtContract mchtContract = mchtContractService.findById(id);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("picList", mchtContractPicService.listByContractId(mchtContract.getId()));
		return new ModelAndView(page, data);
	}

	/**
	 * 合同PDF文件下载
	 */
	@RequestMapping(value = "/mchtContract/downloadPDF.shtml")
	public void downloadPDF(HttpServletResponse response) {
		int id = getParaToInt("id");
		MchtContract mchtContract = mchtContractService.findById(id);

		if(mchtContract.getFilePath() != null){
			download(mchtContract.getContractCode() + ".pdf", mchtContract.getFilePath(), response);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/mchtContract/rebuildMchtContract.shtml")
	public Map<String, Object> rebuildMchtContract(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String resultCode = "";
		String mchtContractId = request.getParameter("mchtContractId");
		String mchtId = request.getParameter("mchtId");
		List<Integer> platformContactIds = new ArrayList<Integer>();
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria pcec = pce.createCriteria();
		pcec.andDelFlagEqualTo("0");
		pcec.andContactTypeEqualTo("7");// 法务对接人
		pcec.andStatusEqualTo("0");
		pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
		if (platformContacts != null && platformContacts.size() > 0) {// 是法务对接人
			platformContactIds.add(platformContacts.get(0).getId());
			PlatformContactExample e = new PlatformContactExample();
			PlatformContactExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("0");
			c.andAssistantIdEqualTo(platformContacts.get(0).getId());
			List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
			if(platformContactList!=null && platformContactList.size()>0){
				for(PlatformContact platformContact:platformContactList){
					platformContactIds.add(platformContact.getId());
				}
			}
		}
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(platformContactIds.size() == 0){
			if(sysStaffRoleList==null || sysStaffRoleList.size()==0){
				resultCode = "999";
				map.put("resultCode", resultCode);
				map.put("resultMsg", "只有该商家的法务对接人及对接人的协助人才可以重新生成");
				return map;
			}
		}
		if(platformContactIds.size()>0){
			MchtPlatformContactExample mpce = new MchtPlatformContactExample();
			mpce.createCriteria().andMchtIdEqualTo(Integer.parseInt(mchtId)).andDelFlagEqualTo("0").andStatusEqualTo("1").andPlatformContactIdIn(platformContactIds);
			List<MchtPlatformContact> mchtPlatformContacts = mchtPlatformContactService.selectByExample(mpce);
			if(mchtPlatformContacts==null || mchtPlatformContacts.size() == 0){
				if(sysStaffRoleList==null || sysStaffRoleList.size()==0){
					resultCode = "999";
					map.put("resultCode", resultCode);
					map.put("resultMsg", "只有该商家的法务对接人及对接人的协助人才可以重新生成");
					return map;
				}
			}
		}
		try {
			if(!StringUtil.isEmpty(mchtContractId) && !StringUtil.isEmpty(mchtId)) {
				Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
				mchtContractService.rebuildMchtContract(staffId, Integer.valueOf(mchtContractId), Integer.valueOf(mchtId));
				resultCode = "200";
			}else {
				resultCode = "999";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = "999";
		}
		map.put("resultCode", resultCode);
		return map;
	} 
	
	/**
	 * 合同到期统计--发短信
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtContract/sendSms.shtml")
	@ResponseBody
	public Map<String, Object> sendSms(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		String yearMonth = request.getParameter("yearMonth");
		String beginDate = yearMonth+"-01 00:00:00";
		String endDate = yearMonth+"-"+DateUtil.getDayMouth(yearMonth)+" 23:59:59";
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("beginDate", beginDate);
		paramMap.put("endDate", endDate);
		List<HashMap<String,Object>> list  = mchtContractService.getMchtContacts(paramMap);
		List<JSONObject> sendList = new ArrayList<JSONObject>();
		for(int i=0;i<list.size();i++){
			HashMap<String,Object> eachMap = list.get(i);
			String mobile = "";
			if(eachMap.get("yy_mobile")!=null){
				mobile = eachMap.get("yy_mobile").toString();
			}else if(eachMap.get("ds_mobile")!=null){
				mobile = eachMap.get("ds_mobile").toString();
			}
			if(!StringUtils.isEmpty(mobile)){
				JSONObject param = new JSONObject();
				//特定参数
				JSONObject reqData=new JSONObject();
				reqData.put("mobile", mobile);
				if(eachMap.get("mobile")!=null){
					if(eachMap.get("qq")!=null){
						reqData.put("content", "尊敬的商家，您在醒购入驻的合同即将到期，请及时与招商人员联系洽谈续签事宜，联系电话："+eachMap.get("mobile").toString()+"，QQ："+eachMap.get("qq").toString());
					}else{
						reqData.put("content", "尊敬的商家，您在醒购入驻的合同即将到期，请及时与招商人员联系洽谈续签事宜，联系电话："+eachMap.get("mobile").toString());
					}
				}else if(eachMap.get("qq")!=null){
					reqData.put("content", "尊敬的商家，您在醒购入驻的合同即将到期，请及时与招商人员联系洽谈续签事宜，联系QQ："+eachMap.get("qq").toString());
				}else{
					reqData.put("content", "尊敬的商家，您在醒购入驻的合同即将到期，请及时与招商人员联系洽谈续签事宜");
				}
				reqData.put("smsType", "4");//4.到期统计
				param.put("reqData", reqData);
				sendList.add(param);
			}
		}
		if(sendList!=null && sendList.size()>0){
			int sendSuccessCount = mchtContractService.sendSms(sendList);
			if(sendSuccessCount>0){
				resMap.put("sendSuccessCount", sendSuccessCount);
			}else{
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "发送失败，请稍后重试");
			}
		}
		return resMap;
	}
	
	
	/**
	 * 修改寄回单号
	 */
	@RequestMapping(value = "/mchtContract/mchtContractExprelist.shtml")
	public ModelAndView changeStatupage(HttpServletRequest request,HttpServletResponse response){
			String resPage = "/mchtContract/mchtContractExpre";
			HashMap<String, Object> resMap = new HashMap<String, Object>();	
			String ID=request.getParameter("Id");
			resMap.put("id", ID);			
			//终止合同还是合作合同标识
			String contractType = request.getParameter("contractType");
			//查询商家合同表或商家关店申请表
			if(org.apache.commons.lang.StringUtils.equals(request.getParameter("contractType"), "1")){//商家合同表
				  MchtContractExample mchtContractExample=new MchtContractExample();
			      mchtContractExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(ID));
			      List<MchtContract> mchtContracts = mchtContractService.selectByExample(mchtContractExample);
			      if(!mchtContracts.isEmpty()){
			    	  resMap.put("contact", mchtContracts.get(0)) ;
			      }			     
			}else if(org.apache.commons.lang.StringUtils.equals(request.getParameter("contractType"), "2")){//商家关店申请表
					MchtCloseApplicationExample mchtCloseApplicationExample = new MchtCloseApplicationExample();
					mchtCloseApplicationExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(ID));
					List<MchtCloseApplication> mchtCloseApplications = mchtCloseApplicationService.selectByExample(mchtCloseApplicationExample);
					if(!mchtCloseApplications.isEmpty()){
						resMap.put("contact", mchtCloseApplications.get(0)) ;
					}
			}

			//是未寄回填写还是修改		
			resMap.put("isPlatformSend",request.getParameter("isPlatformSend"));
			resMap.put("contractType", contractType);
			ExpressExample expressExample = new ExpressExample();
			expressExample.createCriteria().andDelFlagEqualTo("0");
			List<Express> express = expressService.selectByExample(expressExample);
			resMap.put("expressList", express);
			return new ModelAndView(resPage, resMap);
		}
	  
	@ResponseBody
	@RequestMapping(value = "/mchtContract/updatemchtexpres.shtml")
	public ModelAndView updateSingleProductActivity(HttpServletRequest request, Integer id, String platformExpressNo,String expressnameid) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg =null;
		try {
			//contractType为空或等于1，修改商家合同表
			if(org.apache.commons.lang.StringUtils.equals(request.getParameter("contractType"), "1")){
		      MchtContractExample mchtContractExample=new MchtContractExample();
		      mchtContractExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
		      MchtContract  mchtContract=new MchtContract();
		      mchtContract.setPlatformExpressNo(platformExpressNo);
			  mchtContract.setPlatformExpressId(Integer.valueOf(expressnameid));
			  mchtContract.setIsPlatformSend("1");
			  mchtContractService.updateByExampleSelective(mchtContract,mchtContractExample);
			}else{
				MchtCloseApplicationExample mchtCloseApplicationExample = new MchtCloseApplicationExample();
				mchtCloseApplicationExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
				MchtCloseApplication mchtCloseApplication = new MchtCloseApplication();
				mchtCloseApplication.setPlatformExpressId(Integer.valueOf(expressnameid));
				mchtCloseApplication.setPlatformExpressNo(platformExpressNo);
				mchtCloseApplication.setIsPlatformSend("1");
				mchtCloseApplicationService.updateByExampleSelective(mchtCloseApplication, mchtCloseApplicationExample);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	//变更旧数据状态平台是否寄回
	@ResponseBody
	@RequestMapping(value = "/mchtContract/updateMchtContractData.shtml")
	public ModelAndView updateMchtContractData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg =null;
		try {
		      MchtContractExample mchtContractExample=new MchtContractExample();
		      mchtContractExample.createCriteria().andDelFlagEqualTo("0");
		      List<MchtContract> lsiContracts=mchtContractService.selectByExample(mchtContractExample);
		      MchtContract  mchtContracts=new MchtContract();
		      for (MchtContract mchtContract : lsiContracts) {
				   if (mchtContract.getPlatformExpressNo()!=null && mchtContract.getPlatformExpressId()!=null && mchtContract.getIsPlatformSend().equals("0")) {				
					   mchtContracts.setIsPlatformSend("1");
					   mchtContracts.setId(mchtContract.getId());
					   mchtContractService.updateByPrimaryKeySelective(mchtContracts);
				}
			}
		      
		   code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		   msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();	  
		} catch (Exception e) {
		   code = StateCode.JSON_AJAX_ERROR.getStateCode();
		   msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
	 }
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	//生成合同
	@RequestMapping(value = "/mchtContract/addMchtContract.shtml")
	public String addMchtContract(Model model,HttpServletRequest request) throws ParseException {
		Integer mchtId= Integer.valueOf(request.getParameter("mchtId"));
		model.addAttribute("mchtId", mchtId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate = sdf.format(new Date());
		model.addAttribute("beginDate", beginDate);
		String year = beginDate.substring(0, 4);
		String limitDate = year+"-07-31";
		String endDate = "";
		if(sdf.parse(beginDate).before(sdf.parse(limitDate))){
			endDate = year+"-12-31";
		}else{
			String y = String.valueOf((Integer.parseInt(year)+1));
			endDate = DateUtil.getLastDayOfMonth(Integer.parseInt(y), 8);//写死8.31
		}
		model.addAttribute("endDate", endDate);
		return "/mchtContract/addMchtContract";	
	}
	
	@RequestMapping(value = "/mchtContract/saveMchtContract.shtml")
	@ResponseBody
	public Map<String, Object> saveMchtContract(Model model,HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			String staffId = staffBean.getStaffID();
			String mchtId = request.getParameter("mchtId");
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			MchtContract mchtContract = new MchtContract();
			mchtContract.setMchtId(Integer.parseInt(mchtId));
			mchtContract.setContractCode(mchtContractService.genContractCode(Integer.parseInt(mchtId)));	//合同编号
			mchtContract.setBeginDate(sdf.parse(beginDate));
			mchtContract.setEndDate(sdf.parse(endDate));

			mchtContract.setContractType(Const.MCHT_CONTRACT_TYPE_RENEW);	//续签
			mchtContract.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);
			mchtContract.setRenewStatus(Const.MCHT_CONTRACT_RENEW_STATUS_WAIT);
			mchtContract.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);	//审核状态：未上传
			mchtContract.setCreateBy(Integer.valueOf(staffId));
			mchtContractService.save(mchtContract);
			//生成PDF
			mchtContractService.createPDF(mchtContract.getId());
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	@RequestMapping(value = "/mchtContract/saveRenewMchtContract.shtml")
	@ResponseBody
	public Map<String, Object> saveRenewMchtContract(Model model,HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			String staffId = staffBean.getStaffID();
			String mchtId = request.getParameter("mchtId");
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			MchtContract mchtContract = new MchtContract();
			mchtContract.setMchtId(Integer.parseInt(mchtId));
			mchtContract.setContractCode(mchtContractService.genContractCode(Integer.parseInt(mchtId)));	//合同编号
			mchtContract.setBeginDate(sdf.parse(beginDate));
			mchtContract.setEndDate(sdf.parse(endDate));
			
			mchtContract.setContractType(Const.MCHT_CONTRACT_TYPE_RENEW);	//新签合同
			mchtContract.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);
			mchtContract.setRenewStatus(Const.MCHT_CONTRACT_RENEW_STATUS_WAIT);
			mchtContract.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);	//审核状态：未上传
			mchtContract.setCreateBy(Integer.valueOf(staffId));
			mchtContractService.save(mchtContract);
			//生成PDF
			mchtContractService.createPDF(mchtContract.getId());
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//不签约
	@RequestMapping(value = "/mchtContract/notCooperate.shtml")
	@ResponseBody
	public Map<String, Object> notCooperate(Model model,HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			String staffId = staffBean.getStaffID();
			String id = request.getParameter("id");
			MchtContract mchtContract = mchtContractService.selectByPrimaryKey(Integer.parseInt(id));
			mchtContract.setArchiveStatus("4");
			mchtContract.setUpdateBy(Integer.parseInt(staffId));
			mchtContract.setUpdateDate(new Date());
			mchtContractService.updateByPrimaryKeySelective(mchtContract);
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtContract.getMchtId());
			mchtInfo.setStatus("3");
			mchtInfo.setStatusDate(new Date());
			mchtInfo.setUpdateBy(Integer.parseInt(staffId));
			mchtInfo.setUpdateDate(new Date());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//上传合同页面
	@RequestMapping(value = "/mchtContract/toUpload.shtml")
	public String toUpload(Model model,HttpServletRequest request) throws ParseException {
		String id= request.getParameter("id");
		model.addAttribute("id", id);
		MchtContract mchtContract = mchtContractService.selectByPrimaryKey(Integer.valueOf(id));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtContract.getMchtId());
		model.addAttribute("isNeedUpload",mchtContract.getIsNeedUpload());
		if ("1".equals(mchtInfo.getMchtType()) && "1".equals(mchtInfo.getIsManageSelf())){//自营SPOP才显示无需上传
			if ("1".equals(mchtInfo.getIsManageSelf())){
				model.addAttribute("isManageSelf", "1");
			}
			return "/mchtContract/toUpload";
		}
		return "/mchtContract/toUploadPop";
	}
	
	/**
	 * 合同扫描件上传【提交审核】
	 */
	@RequestMapping(value = "/mchtContract/contractPicUpload")
	@ResponseBody
	public Map<String, Object> contractPicUpload(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtContract mchtContract = mchtContractService.selectByPrimaryKey(id);
		String contractPics = request.getParameter("contractPics");
		String isSpecial = request.getParameter("isSpecial");
		String isNeedUpload = request.getParameter("isNeedUpload");
		mchtContractService.contractPicUpload(mchtContract,contractPics,isSpecial,isNeedUpload);
		return resMap;
	}
	
	/**
	 * 资质归档情况
	 */
	@RequestMapping(value = "/mchtContract/mchtInfoArchive.shtml")
	public ModelAndView mchtInfoArchive(HttpServletRequest request) {
		String page = "/mchtContract/mchtInfoArchive";
		Map<String, Object> data = new HashMap<String, Object>();
		Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		data.put("mchtInfo", mchtInfo);
		
		MchtBlPicExample mbpe = new MchtBlPicExample();
		mbpe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
		List<MchtBlPic> mchtBlPics = mchtBlPicService.selectByExample(mbpe);
		if(mchtBlPics!=null && mchtBlPics.size()>0){
			data.put("mchtBlPics", mchtBlPics);
		}
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andStatusEqualTo("1");
		List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.selectByCustomExample(mpbe);
		for (MchtProductBrandCustom mchtProductBrandCustom : mchtProductBrandCustoms) {
			Integer mchtProductBrandId = mchtProductBrandCustom.getId();
			MchtBrandAptitudeExample mbae = new MchtBrandAptitudeExample();
			mbae.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(mbae);
			for (MchtBrandAptitudeCustom mchtBrandAptitudeCustom : mchtBrandAptitudeCustoms) {
				Integer mchtBrandAptitudeId = mchtBrandAptitudeCustom.getId();
				MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
				mbape.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeId);
				List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
				mchtBrandAptitudeCustom.setMchtBrandAptitudePicList(mchtBrandAptitudePics);
			}
			mchtProductBrandCustom.setMchtBrandAptitudeCustoms(mchtBrandAptitudeCustoms);

			MchtPlatformAuthPicExample mpape = new MchtPlatformAuthPicExample();
			mpape.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService.selectByExample(mpape);
			mchtProductBrandCustom.setMchtPlatformAuthPicList(mchtPlatformAuthPics);

			MchtBrandInvoicePicExample mbipe = new MchtBrandInvoicePicExample();
			mbipe.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicService.selectByExample(mbipe);
			mchtProductBrandCustom.setMchtBrandInvoicePicList(mchtBrandInvoicePics);

			MchtBrandInspectionPicExample mbipe2 = new MchtBrandInspectionPicExample();
			mbipe2.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicService.selectByExample(mbipe2);
			mchtProductBrandCustom.setMchtBrandInspectionPicList(mchtBrandInspectionPics);

			MchtBrandOtherPicExample mbope = new MchtBrandOtherPicExample();
			mbope.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicService.selectByExample(mbope);
			mchtProductBrandCustom.setMchtBrandOtherPicList(mchtBrandOtherPics);
		}
		data.put("mchtProductBrandCustoms", mchtProductBrandCustoms);
		data.put("mchtId", mchtId);
		return new ModelAndView(page, data);
	}
	
	//资料归档情况--全部已归操作
	@RequestMapping(value = "/mchtContract/picsArchive.shtml")
	@ResponseBody
	public Map<String, Object> picsArchive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			Integer mchtProductBrandId = Integer.parseInt(request.getParameter("mchtProductBrandId"));
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String picType = request.getParameter("picType");
			mchtContractService.mchtContractPicsArchive(mchtId,mchtProductBrandId,id,staffID,picType);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
		
	//资料归档情况--单个已归操作
	@RequestMapping(value = "/mchtContract/picArchive.shtml")
	@ResponseBody
	public Map<String, Object> picArchive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String picType = request.getParameter("picType");
			String archiveStatus = request.getParameter("archiveStatus");
			mchtContractService.picArchive(mchtId,id,staffID,picType,archiveStatus);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	//校验是否是法务及其协助人对接的商家
	@RequestMapping(value = "/mchtContract/checkFwPlatformContact.shtml")
	@ResponseBody
	public Map<String, Object> checkFwPlatformContact(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtId = request.getParameter("mchtId");
			List<Integer> platformContactIds = new ArrayList<Integer>();
			PlatformContactExample pce = new PlatformContactExample();
			PlatformContactExample.Criteria pcec = pce.createCriteria();
			pcec.andDelFlagEqualTo("0");
			pcec.andContactTypeEqualTo("7");// 法务对接人
			pcec.andStatusEqualTo("0");
			pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
			if (platformContacts != null && platformContacts.size() > 0) {// 是法务对接人
				platformContactIds.add(platformContacts.get(0).getId());
				PlatformContactExample e = new PlatformContactExample();
				PlatformContactExample.Criteria c = e.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andStatusEqualTo("0");
				c.andAssistantIdEqualTo(platformContacts.get(0).getId());
				List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
				if(platformContactList!=null && platformContactList.size()>0){
					for(PlatformContact platformContact:platformContactList){
						platformContactIds.add(platformContact.getId());
					}
				}
			}
			if(platformContactIds.size() == 0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "只有该商家的法务对接人及对接人的协助人才可以操作");
				return resMap;
			}
			MchtPlatformContactExample mpce = new MchtPlatformContactExample();
			mpce.createCriteria().andMchtIdEqualTo(Integer.parseInt(mchtId)).andDelFlagEqualTo("0").andStatusEqualTo("1").andPlatformContactIdIn(platformContactIds);
			List<MchtPlatformContact> mchtPlatformContacts = mchtPlatformContactService.selectByExample(mpce);
			if(mchtPlatformContacts==null || mchtPlatformContacts.size() == 0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "只有该商家的法务对接人及对接人的协助人才可以操作");
				return resMap;
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	//校验是否是招商及其协助人对接的商家
	@RequestMapping(value = "/mchtContract/checkZsPlatformContact.shtml")
	@ResponseBody
	public Map<String, Object> checkZsPlatformContact(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtId = request.getParameter("mchtId");
			List<Integer> platformContactIds = new ArrayList<Integer>();
			PlatformContactExample pce = new PlatformContactExample();
			PlatformContactExample.Criteria pcec = pce.createCriteria();
			pcec.andDelFlagEqualTo("0");
			pcec.andContactTypeEqualTo("1");// 招商对接人
			pcec.andStatusEqualTo("0");
			pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
			if (platformContacts != null && platformContacts.size() > 0) {// 是招商对接人
				platformContactIds.add(platformContacts.get(0).getId());
				PlatformContactExample e = new PlatformContactExample();
				PlatformContactExample.Criteria c = e.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andStatusEqualTo("0");
				c.andAssistantIdEqualTo(platformContacts.get(0).getId());
				List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
				if(platformContactList!=null && platformContactList.size()>0){
					for(PlatformContact platformContact:platformContactList){
						platformContactIds.add(platformContact.getId());
					}
				}
			}
			if(platformContactIds.size() == 0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "只有该商家的招商对接人及对接人的协助人才可以操作");
				return resMap;
			}
			MchtPlatformContactExample mpce = new MchtPlatformContactExample();
			mpce.createCriteria().andMchtIdEqualTo(Integer.parseInt(mchtId)).andDelFlagEqualTo("0").andStatusEqualTo("1").andPlatformContactIdIn(platformContactIds);
			List<MchtPlatformContact> mchtPlatformContacts = mchtPlatformContactService.selectByExample(mpce);
			if(mchtPlatformContacts==null || mchtPlatformContacts.size() == 0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "只有该商家的招商对接人及对接人的协助人才可以操作");
				return resMap;
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
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
	@RequestMapping("/mchtContract/export.shtml")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			MchtContractCustomExample example = new MchtContractCustomExample();
			example.setOrderByClause("audit_date asc,id desc");
			MchtContractCustomExample.MchtContractCustomCriteria criteria = example.createCriteria();
			criteria.andContractTypeEqualTo("1");//1.新签
			criteria.andMchtStatusIn("0,1,2");//商家合作状态!=关闭
			criteria.andDelFlagEqualTo("0");
			
			String mchtCode = request.getParameter("mchtCode");
			String mchtName = request.getParameter("mchtName");
			String contractCode = request.getParameter("contractCode");
			String isMchtSend = request.getParameter("isMchtSend");
			String archiveStatus = request.getParameter("archiveStatus");
			String mchtInfoArchiveStatus = request.getParameter("mchtInfoArchiveStatus");
			String isMyFawu = request.getParameter("isMyFawu");
			if(!StringUtil.isEmpty(mchtCode)){
				criteria.andMchtCodeByEqualTo(mchtCode);
			}
			if(!StringUtil.isEmpty(mchtName)){
				criteria.andMchtNameLike(mchtName);
			}
			
			if(!StringUtil.isEmpty(contractCode)){
				criteria.andContractCodeEqualTo(contractCode);
			}
			if(!StringUtil.isEmpty(isMchtSend)){
				criteria.andIsMchtSendEqualTo(isMchtSend);
			}
			if (!StringUtil.isEmpty(archiveStatus)) {
				criteria.andArchiveStatusEqualTo(archiveStatus);
			}
			if (!StringUtil.isEmpty(mchtInfoArchiveStatus)) {
				criteria.andMchtArchiveStatusEqualTo(mchtInfoArchiveStatus);
			}
			if(!StringUtil.isEmpty(isMyFawu)){
				PlatformContactExample pce = new PlatformContactExample();
				pce.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean().getStaffID())).andContactTypeEqualTo("7");
				List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
				if(platformContacts!=null && platformContacts.size()>0){
					criteria.andPlatformContactIdEqualTo(platformContacts.get(0).getId());
				}
			}

			List<MchtContractCustom> mchtContractCustoms = mchtContractService.selectCustomByExample(example);
			String[] titles = {"招商对接人","入驻类型","商家序号","公司名称","合同归档状态","法人身份证","营业执照副本","资质归档情况"};
			ExcelBean excelBean = new ExcelBean("导出商家资料归档审核.xls",
					"导出商家资料归档审核", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtContractCustom mchtContractCustom:mchtContractCustoms){
				String mchtInfoArchiveStatusDesc = "";
				if(StringUtils.isEmpty(mchtContractCustom.getMchtInfoArchiveStatus()) || mchtContractCustom.getMchtInfoArchiveStatus().equals("0")){
					mchtInfoArchiveStatusDesc = "未齐全";
				}else{
					mchtInfoArchiveStatusDesc = "已归档";
				}
				String settledTypeDesc = "";
				if(mchtContractCustom.getSettledType().equals("1")){
					settledTypeDesc = "公司企业";
				}else if(mchtContractCustom.getSettledType().equals("2")){
					settledTypeDesc = "个体商户";
				}
				String[] data = {
					mchtContractCustom.getZsContact(),
					settledTypeDesc,
					mchtContractCustom.getMchtCode(),
					mchtContractCustom.getCompanyName(),
					mchtContractCustom.getArchiveStatusDesc(),
					"`"+mchtContractCustom.getCorporationIdcardNo(),
					"`"+mchtContractCustom.getUscc(),
					mchtInfoArchiveStatusDesc
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
	 * 公司资质归档
	 */
	@RequestMapping(value = "/mchtContract/viewCompanyInfoArchive.shtml")
	public ModelAndView viewCompanyInfoArchive(HttpServletRequest request) {
		String page = "/mchtContract/viewCompanyInfoArchive";
		Map<String, Object> data = new HashMap<String, Object>();
		Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		data.put("mchtInfo", mchtInfo);
		
		MchtBlPicExample mbpe = new MchtBlPicExample();
		mbpe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
		List<MchtBlPic> mchtBlPics = mchtBlPicService.selectByExample(mbpe);
		if(mchtBlPics!=null && mchtBlPics.size()>0){
			data.put("mchtBlPics", mchtBlPics);
		}
		data.put("mchtId", mchtId);
		// hideRadio为隐藏radio标识，为1时页面只能作展示用
		if (!StringUtils.isEmpty(request.getParameter("hideRadio"))) {
			data.put("hideRadio", request.getParameter("hideRadio"));
		}
		return new ModelAndView(page, data);
	}
	
	/**
	 * 公司资质归档(更新)
	 */
	@RequestMapping(value = "/mchtContract/viewCompanyInfoArchiveChg.shtml")
	public ModelAndView viewCompanyInfoArchiveChg(HttpServletRequest request) {
		String page = "/mchtContract/viewCompanyInfoArchiveChg";
		Map<String, Object> data = new HashMap<String, Object>();
		Integer mchtContractId = Integer.parseInt(request.getParameter("chgId"));
		MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(mchtContractId);
		data.put("mchtInfoChg", mchtInfoChg);
		data.put("mchtContractId",mchtContractId);
		return new ModelAndView(page, data);
	}
	
	//公司资料归档--已归操作
	@RequestMapping(value = "/mchtContract/companyInfoPicArchive.shtml")
	@ResponseBody
	public Map<String, Object> companyInfoPicArchive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String picType = request.getParameter("picType");
			String archiveStatus = request.getParameter("archiveStatus");
			mchtContractService.companyInfoPicArchive(mchtId,id,staffID,picType,archiveStatus);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 公司资质归档(更新表)
	 */
	@RequestMapping(value = "/mchtContract/viewCompanyInfoArchiveChgs.shtml")
	public ModelAndView viewCompanyInfoArchiveChgs(HttpServletRequest request) {
		String page = "/mchtContract/viewCompanyInfoArchiveChg";
		Map<String, Object> data = new HashMap<String, Object>();
		Integer mchtContractId = Integer.parseInt(request.getParameter("chgId"));
		MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(mchtContractId);
		data.put("mchtInfoChg", mchtInfoChg);
		
		MchtBlPicChgExample mbpe = new MchtBlPicChgExample();
		mbpe.createCriteria().andDelFlagEqualTo("0").andMchtInfoChgIdEqualTo(mchtContractId);
		List<MchtBlPicChg> mchtBlPicsChg = mchtBlPicChgService.selectByExample(mbpe);
		if(mchtBlPicsChg!=null && mchtBlPicsChg.size()>0){
			data.put("mchtBlPicsChg", mchtBlPicsChg);
		}
		data.put("mchtContractId", mchtContractId);
/*		// hideRadio为隐藏radio标识，为1时页面只能作展示用
		if (!StringUtils.isEmpty(request.getParameter("hideRadio"))) {
			data.put("hideRadio", request.getParameter("hideRadio"));
		}*/
		return new ModelAndView(page, data);
	}
	
	//公司资料归档--已归操作（更新表）
	@RequestMapping(value = "/mchtContract/companyInfoPicArchiveChg.shtml")
	@ResponseBody
	public Map<String, Object> companyInfoPicArchiveChg(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtContractId = Integer.parseInt(request.getParameter("mchtContractId"));
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String picType = request.getParameter("picType");
			String archiveStatus = request.getParameter("archiveStatus");
			mchtContractService.companyInfoPicArchiveChg(mchtContractId,id,staffID,picType,archiveStatus);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 经营许可证归档页面
	 */
	@RequestMapping(value = "/mchtContract/toLicenseArchiveStatus.shtml")
	public ModelAndView toLicenseArchiveStatus(HttpServletRequest request) {
		String page = "mchtContract/toLicenseArchiveStatus";
		Map<String, Object> data = new HashMap<String, Object>();
		String mchtId = request.getParameter("mchtId");
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(mchtId));
		data.put("mchtId", mchtId);
		data.put("businessLicensePic", mchtInfo.getBusinessLicensePic());
		data.put("licenseArchiveStatus", mchtInfo.getLicenseArchiveStatus());
		return new ModelAndView(page, data);
	}
	
	//经营许可证归档
	@RequestMapping(value = "/mchtContract/licenseArchiveStatus.shtml")
	@ResponseBody
	public Map<String, Object> licenseArchiveStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			MchtInfo mi = new MchtInfo();
			mi.setLicenseArchiveStatus(request.getParameter("licenseArchiveStatus"));
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtId);
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}

	//平台合同回寄确认页面
	@RequestMapping(value = "/mchtContract/contractReturn.shtml")
	public String contractReturn(HttpServletRequest request) {
		return "mchtContract/contractReturn";
	}
	
	@RequestMapping(value = "/mchtContract/contractReturnList.shtml")
	@ResponseBody
	public Map<String, Object> contractReturnList(HttpServletRequest request,Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		//商家序号
		if(org.apache.commons.lang.StringUtils.isNotBlank(request.getParameter("mchtCode"))){
			map.put("mchtCode", request.getParameter("mchtCode"));
		}
		//名称
		if(org.apache.commons.lang.StringUtils.isNotBlank(request.getParameter("name"))){
			map.put("name", request.getParameter("name"));
		}
		//平台寄回状态
		if(org.apache.commons.lang.StringUtils.isNotBlank(request.getParameter("isPlatformSend"))){
			map.put("isPlatformSend", request.getParameter("isPlatformSend"));
		}
		//合同类型
		if(org.apache.commons.lang.StringUtils.isNotBlank(request.getParameter("contractType"))){
			map.put("contractType", request.getParameter("contractType"));
		}
		int limitStart = (page.getPage()-1)*page.getLimitSize();
		int limitSize = page.getLimitSize();
		map.put("limitStart", limitStart);
		map.put("limitSize", limitSize);
		int totalCount = mchtContractService.countByContractReturnList(map);
		List<PlatformContractReturnLis> list = mchtContractService.contractReturnList(map);
		//收集MchtId
		List<Integer> mchtIdList = new ArrayList<Integer>();
		for (PlatformContractReturnLis platformContractReturnLis : list) {
			mchtIdList.add(platformContractReturnLis.getMchtId());
		}
		List<MchtContact> mchtContactList = mchtContactService.selectByExampleGroupBy(mchtIdList);
			Map<Integer,Object> mchtContactMap = new HashMap<Integer, Object>();
			for (MchtContact mchtContact : mchtContactList) {
				mchtContactMap.put(mchtContact.getMchtId(), mchtContact);
			}
			for (PlatformContractReturnLis platformContractReturnLis : list) {
				if(mchtContactMap.containsKey(platformContractReturnLis.getMchtId())){
					String address = "";
					MchtContact mchtContact = (MchtContact) mchtContactMap.get(platformContractReturnLis.getMchtId());
					List<Area> areas = areaService.getAddress(mchtContact);
					for (Area area : areas) {
						address +=area.getAreaName();
					}
					address += mchtContact.getAddress();
					platformContractReturnLis.setAddress(address);
				}
			}
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
	
	/**
	 * 编辑法务内部备注
	 */
	@RequestMapping(value = "/mchtContract/editFwInnerRemarks.shtml")
	public ModelAndView editFwInnerRemarks() {
		String page = "/mchtContract/editFwInnerRemarks";
		Map<String, Object> data = new HashMap<String, Object>();
		
		int id = getParaToInt("id");
		Assert.notZero(id, "id不能为空");
		
		MchtContract model = mchtContractService.findById(id);
		data.put("model", model);
		
		return new ModelAndView(page, data);
	}

	/**
	 * 保存法务内部备注
	 */
	@RequestMapping(value = "/mchtContract/saveFwInnerRemarks.shtml")
	@ResponseBody
	public String saveFwInnerRemarks() {
		MchtContract info = this.getParaToBean("model", MchtContract.class);
		Assert.notZero(info.getId(), "id不能为空");
		
		MchtContract model = mchtContractService.findById(info.getId());
		model.setFwInnerRemarks(info.getFwInnerRemarks());
		model.setUpdateBy(Integer.parseInt(getSessionStaffBean().getStaffID()));
		mchtContractService.update(model);
		
		return json();
	}
}
