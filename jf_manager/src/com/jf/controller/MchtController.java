package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.bean.PDFCreaterMchtCloseApplication;
import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.*;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.entity.CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria;
import com.jf.entity.MchtContactExample.Criteria;
import com.jf.entity.MchtFeedbackCustomExample.MchtFeedbackCustomCriteria;
import com.jf.entity.MchtInfoCustomExample.MchtInfoCustomCriteria;
import com.jf.entity.MchtProductBrandCustomExample.MchtProductBrandCustomCriteria;
import com.jf.entity.MchtProductTypeCustomExample.MchtProductTypeCustomCriteria;
import com.jf.entity.MchtUserCustomExample.MchtUserCustomCriteria;
import com.jf.service.*;
import com.jf.vo.MchtVo;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
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
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MchtController extends BaseController {
	private static final Log logger = LogFactory.getLog(MchtController.class);

	@Resource
	private MchtInfoService mchtInfoService;

	@Resource
	private StatusService statusService;

	@Resource
	private SysStatusService sysStatusService;

	@Resource
	private ProductTypeService productTypeService;

	@Resource
	private MchtContactService mchtContactService;

	@Resource
	private MchtUserService mchtUserService;

	@Resource
	private PlatformContactService platformContactService;

	@Resource
	private MchtPlatformContactService mchtPlatformContactService;

	@Resource
	private MchtProductTypeService mchtProductTypeService;

	@Resource
	private MchtProductBrandService mchtProductBrandService;

	@Resource
	private MchtDepositService mchtDepositService;

	@Resource
	private MchtDepositDtlService mchtDepositDtlService;

	@Resource
	private ProductBrandService productBrandService;

	@Resource
	private MchtBlPicService mchtBlPicService;

	@Resource
	private MchtBrandAptitudePicService mchtBrandAptitudePicService;

	@Resource
	private MchtPlatformAuthPicService mchtPlatformAuthPicService;

	@Resource
	private MchtTaxInvoiceInfoService mchtTaxInvoiceInfoService;

	@Resource
	private MchtInfoChgService mchtInfoChgService;

	@Resource
	private MchtBlPicChgService mchtBlPicChgService;

	@Resource
	private MchtBankAccountService mchtBankAccountService;

	@Resource
	private BankService bankService;

	@Resource
	private MchtBrandAptitudePicChgServer mchtBrandAptitudePicChgServer;

	@Resource
	private MchtPlatformAuthPicChgServer mchtPlatformAuthPicChgServer;

	@Resource
	private MchtTaxInvoiceInfoChgService mchtTaxInvoiceInfoChgService;

	@Resource
	private MchtBrandChgService mchtBrandChgService;

	@Resource
	private MchtBrandInspectionPicServer mchtBrandInspectionPicServer;

	@Resource
	private MchtBrandInvoicePicServer mchtBrandInvoicePicServer;

	@Resource
	private MchtBrandOtherPicServer mchtBrandOtherPicServer;

	@Resource
	private MchtBrandInspectionPicChgServer mchtBrandInspectionPicChgServer;

	@Resource
	private MchtBrandInvoicePicChgServer mchtBrandInvoicePicChgServer;

	@Resource
	private MchtBrandOtherPicChgServer mchtBrandOtherPicChgServer;

	@Resource
	private MchtInfoChangeLogService mchtInfoChangeLogService;

	@Resource
	private MchtBankAccountHisService mchtBankAccountHisService;

	@Resource
	private MchtContractService mchtContractService;

	@Resource
	private XiaonengCustomerserviceService xiaonengcustomerserviceService;

	@Resource
	private MchtSettledApplyService mchtSettledApplyService;

	@Resource
	private ZsProductTypeService zsProductTypeService;

	@Resource
	private MchtSettledApplyPicService mchtSettledApplyPicService;

	@Resource
	private MchtFeedbackService mchtFeedbackService;

	@Resource
	private MchtFeedbackPicService mchtFeedbackPicService;

	@Resource
	private MchtBrandAptitudeService mchtBrandAptitudeService;

	@Resource
	private MchtBrandProductTypeService mchtBrandProductTypeService;
	
	@Resource
	private MchtBrandAptitudeChgService mchtBrandAptitudeChgService;
	
	@Resource
	private MchtBrandAptitudePicChgMapper mchtBrandAptitudePicChgMapper;
	
	@Resource
	private MchtPlatformAuthPicChgMapper mchtPlatformAuthPicChgMapper;
	
	@Resource
	private MchtBrandInvoicePicChgMapper mchtBrandInvoicePicChgMapper;
	
	@Resource
	private MchtBrandInspectionPicChgMapper mchtBrandInspectionPicChgMapper;
	
	@Resource
	private MchtBrandOtherPicChgMapper mchtBrandOtherPicChgMapper;
	
	@Resource
	private MchtBrandChgProductTypeService mchtBrandChgProductTypeService;

	@Resource
	private MchtBrandChangeAgreementPicMapper mchtBrandChangeAgreementPicMapper;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private SingleProductActivityService singleProductActivityService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private SysStaffProductTypeService sysStaffProductTypeService;
	
	@Resource
	private StaffMchtcontactPermissionService staffMchtcontactPermissionService;
	
	@Resource
	private MchtCloseApplicationService mchtCloseApplicationService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;
	
	@Autowired
	private MchtOptimizeService mchtOptimizeService;
	
	@Resource
	private MchtCloseApplicationRemarksMapper mchtCloseApplicationRemarksMapper;
	
	@Resource
	private MchtCloseApplicationRemarksCustomMapper mchtCloseApplicationRemarksCustomMapper;
	
	@Resource
	private MchtSettleOrderService mchtSettleOrderService;
	
	@Resource
	private ActivityAreaService activityAreaService;
	
	@Resource
	private AppealOrderService appealOrderService;
	
	@Resource
	private InterventionOrderService interventionOrderService;
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	
	@Autowired
	private SysRoleInfoService sysRoleInfoService;
	
	@Autowired
	private CooperationChangeApplyService cooperationChangeApplyService;
	
	@Resource
	private MchtViewlogService mchtViewlogService;
	
	@Resource
	private MchtLicenseChgMapper mchtLicenseChgMapper;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private PlatformMsgService platformMsgService;
	
	@Resource
	private MchtCloseApplicationPicService mchtCloseApplicationPicService;

    @Resource
    private CouponService couponService;
	
	@Resource
	private ProductUpperLowerLogService productUpperLowerLogService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商家录入
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/toAddMcht.shtml")
	public String toAddMcht(Model model, HttpServletRequest request) {
		String rtPage = "/mcht/toAddMcht";// 重定向
		model.addAttribute("mchtTypeStatus",
				DataDicUtil.getStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
		model.addAttribute("mchtInfoStatus",
				DataDicUtil.getStatusList("BU_MCHT_INFO", "STATUS"));
		model.addAttribute("mchtContactTypes",
				DataDicUtil.getStatusList("BU_MCHT_CONTACT", "CONTACT_TYPE"));

		return rtPage;
	}

	// 校验商家录入信息
	@RequestMapping(value = "/mcht/validateMchtInfo.shtml")
	@ResponseBody
	public Map<String, Object> validateMchtInfo(HttpServletRequest request,
			MchtVo mchtVo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {

			boolean hasErrorMsg = false;
			String errorMsg = "";

			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			mchtInfoExample
					.createCriteria()
					.andShortNameEqualTo(
							mchtVo.getMchtInfo().getShortName().trim())
					.andStatusNotEqualTo("3");

			int count = mchtInfoService.countByExample(mchtInfoExample);
			if (count > 0) {
				errorMsg = errorMsg + ";商家简称已经存在";
				hasErrorMsg = true;
			}

			mchtInfoExample = new MchtInfoExample();
			mchtInfoExample
					.createCriteria()
					.andShopNameEqualTo(
							mchtVo.getMchtInfo().getShopName().trim())
					.andStatusNotEqualTo("3");
			count = mchtInfoService.countByExample(mchtInfoExample);
			if (count > 0) {
				errorMsg = errorMsg + ";店铺名已经存在";
				hasErrorMsg = true;
			}

			mchtInfoExample = new MchtInfoExample();
			mchtInfoExample
					.createCriteria()
					.andShopCatalogEqualTo(
							mchtVo.getMchtInfo().getShopCatalog().trim())
					.andStatusNotEqualTo("3");
			count = mchtInfoService.countByExample(mchtInfoExample);
			if (count > 0) {
				errorMsg = errorMsg + ";店铺目录已经存在";
				hasErrorMsg = true;
			}

			MchtUserExample mchtUserExample = new MchtUserExample();
			mchtUserExample.createCriteria().andUserCodeEqualTo(
					mchtVo.getMchtUser().getUserCode().trim());
			count = mchtUserService.countByExample(mchtUserExample);
			if (count > 0) {
				errorMsg = errorMsg + ";用户名已经存在";
				hasErrorMsg = true;
			}

			if (hasErrorMsg) {
				throw new ArgException(errorMsg.substring(1));
			}

		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 商家录入保存
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtSave.shtml")
	public ModelAndView mchtSave(HttpServletRequest request, MchtVo mchtVo) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {

			String userCode = mchtVo.getMchtUser().getUserCode();
			if (StringUtil.isEmpty(userCode.trim())) {
				throw new ArgException("用户名不能为空");
			}

			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			mchtInfoExample
					.createCriteria()
					.andShortNameEqualTo(
							mchtVo.getMchtInfo().getShortName().trim())
					.andStatusNotEqualTo("3");

			int count = mchtInfoService.countByExample(mchtInfoExample);
			if (count > 0) {
				throw new ArgException("商家简称已经存在");
			}

			mchtInfoExample = new MchtInfoExample();
			mchtInfoExample
					.createCriteria()
					.andShopNameEqualTo(
							mchtVo.getMchtInfo().getShopName().trim())
					.andStatusNotEqualTo("3");
			count = mchtInfoService.countByExample(mchtInfoExample);
			if (count > 0) {
				throw new ArgException("店铺名已经存在");
			}

			mchtInfoExample = new MchtInfoExample();
			mchtInfoExample
					.createCriteria()
					.andShopCatalogEqualTo(
							mchtVo.getMchtInfo().getShopCatalog().trim())
					.andStatusNotEqualTo("3");
			count = mchtInfoService.countByExample(mchtInfoExample);
			if (count > 0) {
				throw new ArgException("店铺目录已经存在");
			}

			MchtUserExample mchtUserExample = new MchtUserExample();
			mchtUserExample.createCriteria().andUserCodeEqualTo(
					mchtVo.getMchtUser().getUserCode().trim());
			count = mchtUserService.countByExample(mchtUserExample);
			if (count > 0) {
				throw new ArgException("用户名已经存在");
			}

			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request)
					.getStaffID());
			Date now = new Date();

			mchtVo.getMchtInfo().setCompanyRegName(
					mchtVo.getMchtInfo().getCompanyName());
			mchtVo.getMchtInfo().setIsCompanyInfPerfect("0");
			mchtVo.getMchtInfo().setCreateBy(staffId);
			mchtVo.getMchtInfo().setCreateDate(now);

			mchtVo.getMchtUser().setUserName(
					mchtVo.getMchtContact().getContactName());
			mchtVo.getMchtUser().setStatus("0");
			mchtVo.getMchtUser().setCreateBy(staffId);
			mchtVo.getMchtUser().setCreateDate(now);
			mchtVo.getMchtUser().setIsPrimary("1");

			mchtVo.getMchtContact().setMobile(mchtVo.getMchtUser().getMobile());
			mchtVo.getMchtContact().setEmail(mchtVo.getMchtUser().getEmail());
			mchtVo.getMchtContact().setIsPrimary("1");
			mchtVo.getMchtContact().setCreateBy(staffId);
			mchtVo.getMchtContact().setCreateDate(now);

			mchtInfoService.saveMcht(mchtVo);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (ArgException e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = e.getMessage();
		}

		catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);

		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 商家列表首页
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/index.shtml")
	public ModelAndView mchtIndex(HttpServletRequest request) {
		String rtPage = "mcht/index";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			resMap.put("mcht_type", statusService.querytStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
			resMap.put("status", statusService.querytStatusList("BU_MCHT_INFO", "STATUS"));
			resMap.put("is_company_inf_perfect", statusService.querytStatusList("BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT"));
			resMap.put("is_manage_self", statusService.querytStatusList("BU_MCHT_INFO", "IS_MANAGE_SELF"));
			resMap.put("gradeList", DataDicUtil.getTableStatus("BU_MCHT_INFO", "GRADE"));
			resMap.put("audit_risk", statusService.querytStatusList("BU_MCHT_OPTIMIZE", "AUDIT_RISK"));
			resMap.put("degree_adaptability", statusService.querytStatusList("BU_MCHT_OPTIMIZE", "DEGREE_ADAPTABILITY"));

			String staffID = this.getSessionStaffBean(request).getStaffID();
			SysStaffProductTypeCustomExample staffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
			staffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0")
				.andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(staffProductTypeCustomExample);
			resMap.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);

			boolean hasAuth = false;
			PlatformContactExample e = new PlatformContactExample();
			List<String> contactTypeList = new ArrayList<String>();
			contactTypeList.add("1");// 招商
			contactTypeList.add("7");// 法务
			e.createCriteria()
					.andDelFlagEqualTo("0")
					.andStatusEqualTo("0")
					.andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()))
					.andContactTypeIn(contactTypeList);
			List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
			if (platformContactList.size() > 0) {
				hasAuth = true;
			}
			resMap.put("hasAuth", hasAuth);
			
			//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
			String isManagement = this.getSessionStaffBean(request).getIsManagement();
			resMap.put("isManagement", isManagement);
			resMap.put("staffID", staffID);
			SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
			SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
			sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
			List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
			resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
			
			//本对接人类型
			PlatformContactExample pContactExample = new PlatformContactExample();
			PlatformContactExample.Criteria pContactCriteria = pContactExample.createCriteria();
			pContactCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
			pContactExample.setOrderByClause(" id desc");
			pContactExample.setLimitSize(1);
			List<PlatformContact> pContactList = platformContactService.selectByExample(pContactExample);
			if(pContactList != null && pContactList.size() > 0) {
				request.getSession().setAttribute("contactType", pContactList.get(0).getContactType());
			}else {
				request.getSession().setAttribute("contactType", "000");
			}
			
			//获取控建角色
			SysStaffRoleExample sysStaffRoleExample=new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(86);
		    List<SysStaffRole> sysStaffRolelist=sysStaffRoleService.selectByExample(sysStaffRoleExample);
		    if (sysStaffRolelist!=null && sysStaffRolelist.size()>0) {
		    	resMap.put("role86",sysStaffRolelist.get(0).getRoleId());
			}
		    resMap.put("settledType", request.getParameter("settledType"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 商家列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/list.shtml")
	@ResponseBody
	public Map<String, Object> mchtList(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount = "0";
		try {
			paramMap = this.setPageParametersLiger(request, paramMap);
			if (!StringUtil.isEmpty(request.getParameter("endYear"))
					|| !StringUtil.isEmpty(request.getParameter("endMonth"))) {
				paramMap.put("orderByClause", "a.agreement_end_date desc,a.id desc");
			} else {
				paramMap.put("orderByClause", "a.cooperate_begin_date desc,a.id desc");
			}
			if (!StringUtil.isEmpty(request.getParameter("in_blacklist")) && request.getParameter("in_blacklist").equals("1")) {
				paramMap.put("in_blacklist", "1");
			}
			if (!StringUtil.isEmpty(request.getParameter("in_blacklist")) && request.getParameter("in_blacklist").equals("0")) {
				paramMap.put("in_blacklist", "0");
			}
			// 当主营类目、对接人为空时，限制搜索范围为： 本人负责的类目 与 本人对接的商家 并集
			if(StringUtil.isEmpty(request.getParameter("productTypeId")) && StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				paramMap.put("productTypeContactIsNull", this.getSessionStaffBean(request).getStaffID());
			}
			if (!StringUtil.isEmpty(request.getParameter("cooperateBeginDateBegin"))){
				paramMap.put("cooperateBeginDateBegin", request.getParameter("cooperateBeginDateBegin")+" 00:00:00");
			}
			if (!StringUtil.isEmpty(request.getParameter("cooperateBeginDateEnd"))){
				paramMap.put("cooperateBeginDateEnd", request.getParameter("cooperateBeginDateEnd")+" 23:59:59");
			}
			if (!StringUtil.isEmpty(request.getParameter("closeDateBegin"))){
				paramMap.put("closeDateBegin", request.getParameter("closeDateBegin")+" 00:00:00");
			}
			if (!StringUtil.isEmpty(request.getParameter("closeDateEnd"))){
				paramMap.put("closeDateEnd", request.getParameter("closeDateEnd")+" 23:59:59");
			}
			paramMap.put("MIN_NUM", page.getLimitStart());
			paramMap.put("MAX_NUM", page.getLimitSize());
			totalCount = mchtInfoService.queryMchtCount(paramMap);
			dataList = mchtInfoService.selectMchtInfoList(paramMap);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
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
	@SuppressWarnings("unchecked")
	@RequestMapping("/mcht/export.shtml")
	public void export(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) throws Exception {
		try {
			List<?> dataList = null;
			try {
				paramMap.put("orderByClause", "a.id desc");
				dataList = mchtInfoService.selectMchtInfoList(paramMap);
			} catch (Exception e) {
				e.printStackTrace();
				dataList = new ArrayList<Map<String, Object>>();
			}
			String[] titles = { "商家序号", "商家类型", "商家简称", "店铺名", "开通品类", "开通品牌",
					"合作状态", "公司信息状态", "品牌资质", "税务资料状态", "用户名", "商家运营对接人姓名",
					"平台运营对接人", "平台招商对接人", "合作开始日期" };
			ExcelBean excelBean = new ExcelBean("导出商家列表.xls", "导出商家列表", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for (int i = 0; i < dataList.size(); i++) {
				HashMap<String, Object> dataMap = (HashMap<String, Object>) dataList
						.get(i);
				String brandQualification = "";
				if (!String.valueOf(dataMap.get("audit_status_num_3")).equals(
						"0")) {// (3:已完善 )
					brandQualification = "已完善 "
							+ dataMap.get("audit_status_num_3").toString();
				} else {

				}
				if (!dataMap.get("audit_status_num_0_4").toString().equals("0")) {
					brandQualification = "未完善/驳回"
							+ dataMap.get("audit_status_num_0_4").toString();
				}
				if (!dataMap.get("audit_status_num_1_2").toString().equals("0")) {
					brandQualification = "待审核/待定"
							+ dataMap.get("audit_status_num_1_2").toString();
				}
				String[] data = {
						dataMap.get("mcht_code").toString(),
						dataMap.get("is_manage_self_desc").toString()
								+ dataMap.get("mcht_type_desc").toString(),
						dataMap.get("short_name").toString(),
						dataMap.get("shop_name").toString(),
						dataMap.get("mcht_product_type") == null ? "" : dataMap
								.get("mcht_product_type").toString(),
						dataMap.get("mcht_product_brand") == null ? ""
								: dataMap.get("mcht_product_brand").toString(),
						dataMap.get("status_desc").toString(),
						dataMap.get("is_company_inf_perfect_desc").toString(),
						brandQualification,
						dataMap.get("tax_invoice_audit_status_desc").toString(),
						dataMap.get("user_code") == null ? "" : dataMap.get(
								"user_code").toString(),
						dataMap.get("mcht_contact_name") == null ? "" : dataMap
								.get("mcht_contact_name").toString(),
						dataMap.get("yy_contact_name") == null ? "" : dataMap
								.get("yy_contact_name").toString(),
						dataMap.get("zs_contact_name") == null ? "" : dataMap
								.get("zs_contact_name").toString(),
						dataMap.get("cooperate_begin_date") == null ? ""
								: dateFormat.format(dataMap
										.get("cooperate_begin_date")) };
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商家基础资料页
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBaseInfoEdit.shtml")
	public String mchtBaseInfoEdit(Model model, HttpServletRequest request) {
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
		MchtInfoCustom mchtInfo = mchtInfoService
				.selectMchtInfoCustomById(mchtId);
		MchtContactExample mchtContactExample = new MchtContactExample();
		mchtContactExample.createCriteria().andMchtIdEqualTo(mchtId)
				.andIsPrimaryEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause("id desc");
		List<MchtContact> mchtContacts = mchtContactService
				.selectByExample(mchtContactExample);
		MchtUserExample mchtUserExample = new MchtUserExample();
		mchtUserExample.createCriteria().andMchtIdEqualTo(mchtId)
				.andIsPrimaryEqualTo("1").andDelFlagEqualTo("0");
		List<MchtUser> mchtUsers = mchtUserService
				.selectByExample(mchtUserExample);

		model.addAttribute("mchtInfo", mchtInfo);
		
		MchtContact mchtContact;
		if (mchtContacts != null && mchtContacts.size() > 0) {
			mchtContact = mchtContacts.get(0);
		} else {
			mchtContact = new MchtContact();
		}
		model.addAttribute("mchtContact", mchtContact);

		MchtUser mchtUser;
		if (mchtUsers != null && mchtUsers.size() > 0) {
			mchtUser = mchtUsers.get(0);
		} else {
			mchtUser = new MchtUser();
		}
		model.addAttribute("mchtUser", mchtUser);

		// 年检有效期是否到期
		Date now = new Date();
		if (mchtInfo.getYearlyInspectionDate() != null
				&& now.getTime() > mchtInfo.getYearlyInspectionDate().getTime()) {
			model.addAttribute("yearlyInspectionInvalid", "1");
		} else {
			model.addAttribute("yearlyInspectionInvalid", "0");
		}

		// 营业执照(兼容旧数据)
		MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtInfo.getId());
		List<MchtBlPic> mchtBlPics = mchtBlPicService
				.selectByExample(mchtBlPicExample);
		model.addAttribute("mchtBlPics", mchtBlPics);

		// 对接人
		MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
		mchtPlatformContactExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId).andStatusEqualTo("1");
		mchtPlatformContactExample.setOrderByClause("create_date desc");
		List<MchtPlatformContactCustom> mchtPlatformContactCustoms = mchtPlatformContactService
				.selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
		// 招商对接人
		for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
			if ("1".equals(mchtPlatformContactCustom.getContactType())) {
				model.addAttribute("zsMchtPlatformContact",
						mchtPlatformContactCustom);
				model.addAttribute("myContactId",mchtPlatformContactCustom.getPlatformContactId());
				break;
			}
		}

		// 运营对接人
		for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
			if ("2".equals(mchtPlatformContactCustom.getContactType())) {
				model.addAttribute("yyMchtPlatformContact",
						mchtPlatformContactCustom);

				PlatformContactExample platformContactExamples = new PlatformContactExample();
				platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(mchtPlatformContactCustom.getStaffId());
				List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExamples);
				if (platformContacts != null && platformContacts.size() > 0) {
					  if (platformContacts.get(0).getAssistantId()!=null) {
						  Integer assistantId = platformContacts.get(0).getAssistantId();
						  PlatformContactExample platformContactExample = new PlatformContactExample();
						  platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andIdEqualTo(assistantId);
						  List<PlatformContact> platformAssistantContact = platformContactService.selectByExample(platformContactExample);
						  if (platformAssistantContact!=null && platformAssistantContact.size()>0) {
							  model.addAttribute("assistantId",platformAssistantContact.get(0).getStaffId());
						  }
					}
				}
				break;
			}
		}
		// 法务对接人
		for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
			if ("7".equals(mchtPlatformContactCustom.getContactType())) {
				model.addAttribute("fwMchtPlatformContact",
						mchtPlatformContactCustom);
				break;
			}
		}
		// 财务对接人
		for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
			if ("5".equals(mchtPlatformContactCustom.getContactType())) {
				model.addAttribute("cwMchtPlatformContact",
						mchtPlatformContactCustom);
				break;
			}
		}

		// 税务审核状态
		MchtTaxInvoiceInfoExample mchtTaxInvoiceInfoExample = new MchtTaxInvoiceInfoExample();
		mchtTaxInvoiceInfoExample.createCriteria().andMchtIdEqualTo(mchtId)
				.andDelFlagEqualTo("0");
		List<MchtTaxInvoiceInfo> mchtTaxInvoiceInfos = mchtTaxInvoiceInfoService
				.selectByExample(mchtTaxInvoiceInfoExample);
		if (mchtTaxInvoiceInfos == null || mchtTaxInvoiceInfos.size() == 0) {
			model.addAttribute("taxInvoiceStatus", 0);
			model.addAttribute("taxInvoiceStatusDesc", DataDicUtil
					.getStatusDesc("BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS",
							"0"));
		} else {
			model.addAttribute("taxInvoiceStatus", mchtTaxInvoiceInfos.get(0)
					.getAuditStatus());
			model.addAttribute("taxInvoiceStatusDesc", DataDicUtil
					.getStatusDesc("BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS",
							mchtTaxInvoiceInfos.get(0).getAuditStatus()));
			model.addAttribute("taxInvoiceId", mchtTaxInvoiceInfos.get(0)
					.getId());
		}

		// 银行审核状态

		MchtBankAccountCustomExample mchtBankAccountCustomExample = new MchtBankAccountCustomExample();
		mchtBankAccountCustomExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId);
		mchtBankAccountCustomExample.setOrderByClause("id desc");

		List<MchtBankAccountCustom> mchtBankAccountCustoms = mchtBankAccountService
				.selectMchtBankAccountCustomByExample(mchtBankAccountCustomExample);

		if (mchtBankAccountCustoms != null && mchtBankAccountCustoms.size() > 0) {
			model.addAttribute("mchtBankAccount", mchtBankAccountCustoms.get(0));
		} else {
			model.addAttribute("mchtBankAccount", new MchtBankAccountCustom());
		}

		// 最新合同
		MchtContract mchtContract = new MchtContract();
		MchtContractExample mchtContractExample = new MchtContractExample();
		mchtContractExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId);
		mchtContractExample.setOrderByClause("id desc");
		List<MchtContract> mchtContracts = mchtContractService
				.selectByExample(mchtContractExample);
		boolean isContractExpired = false;
		if (mchtContracts != null && mchtContracts.size() > 0) {
			mchtContract = mchtContracts.get(0);
			if (mchtContract.getEndDate().getTime() < (new Date()).getTime()) {
				isContractExpired = true;
			}
		} else {
			isContractExpired = true;
		}
		model.addAttribute("mchtContract", mchtContract);
		model.addAttribute(
				"mchtContractAuditStatusDesc",
				mchtContract.getAuditStatus() == null ? "" : DataDicUtil
						.getStatusDesc("BU_MCHT_CONTRACT", "AUDIT_STATUS",
								mchtContract.getAuditStatus()));
		model.addAttribute(
				"mchtContractArchiveStatusDesc",
				mchtContract.getArchiveStatus() == null ? "" : DataDicUtil
						.getStatusDesc("BU_MCHT_CONTRACT", "ARCHIVE_STATUS",
								mchtContract.getArchiveStatus()));
		model.addAttribute("isContractExpired", isContractExpired);
		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		mchtProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andIsMainEqualTo("1");
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mchtProductTypeExample);
		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
			model.addAttribute("mchtProductType", mchtProductTypes.get(0));
			ProductType productType = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
			model.addAttribute("productTypeName", productType.getName());
		}
		//二级类目
		if(mchtInfo.getProductType2Id()!=null && mchtInfo.getProductType2Id()!=0){
		ProductTypeExample e1 = new ProductTypeExample();
		ProductTypeExample.Criteria c1 = e1.createCriteria();
		c1.andDelFlagEqualTo("0");
		c1.andStatusEqualTo("1");
		c1.andIdEqualTo(mchtInfo.getProductType2Id());
		List<ProductType> productTypes2 = productTypeService.selectByExample(e1);
		model.addAttribute("productType2Name", productTypes2.get(0).getName());
		}
		
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria pcec = pce.createCriteria();
		pcec.andDelFlagEqualTo("0");
		pcec.andContactTypeEqualTo("1");// 招商对接人
		pcec.andStatusEqualTo("0");
		pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> pcs = platformContactService.selectByExample(pce);
		if(pcs!=null && pcs.size()>0){
			model.addAttribute("zsContactId", pcs.get(0).getId());
			String isManagement = this.getSessionStaffBean(request).getIsManagement();
			if(isManagement.equals("1")){
				model.addAttribute("zsManagement", 1);
			}
		}
		
		PlatformContactExample pceFw = new PlatformContactExample();
		PlatformContactExample.Criteria pcecFw = pceFw.createCriteria();
		pcecFw.andDelFlagEqualTo("0");
		pcecFw.andContactTypeEqualTo("7");// 法务对接人
		pcecFw.andStatusEqualTo("0");
		pcecFw.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> pcsFw = platformContactService.selectByExample(pceFw);
		if(pcsFw!=null && pcsFw.size()>0){
			model.addAttribute("fwContactId", pcsFw.get(0).getId());
			MchtPlatformContactExample e = new MchtPlatformContactExample();
			e.createCriteria().andDelFlagEqualTo("0").andPlatformContactIdEqualTo(pcsFw.get(0).getId()).andMchtIdEqualTo(mchtId);
			List<MchtPlatformContact> mchtPlatformContacts = mchtPlatformContactService.selectByExample(e);
			if(mchtPlatformContacts!=null && mchtPlatformContacts.size()>0){
				model.addAttribute("mchtFwContactId", mchtPlatformContacts.get(0).getPlatformContactId());
			}
		}
		
		SysRoleInfoExample srie = new SysRoleInfoExample();
		srie.createCriteria().andStatusEqualTo("A").andRoleNameEqualTo("总经办");
		List<SysRoleInfo> sysRoleInfos = sysRoleInfoService.selectByExample(srie);
		if(sysRoleInfos!=null && sysRoleInfos.size()>0){
			SysStaffRoleExample ssre = new SysStaffRoleExample();
			ssre.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(sysRoleInfos.get(0).getRoleId());
			List<SysStaffRole> sysStaffRoles = sysStaffRoleService.selectByExample(ssre);
			if(sysStaffRoles!=null && sysStaffRoles.size()>0){
				model.addAttribute("generalOffice", 1);
			}
		}
		
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(108);
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			model.addAttribute("zsMarked", 1);
		}else{
			model.addAttribute("zsMarked", 0);
		}
		

		List<Integer> roleIds = new ArrayList<Integer>();
		roleIds.add(1);
		roleIds.add(43);
		SysStaffRoleExample sysStaffRoleExample1 = new SysStaffRoleExample();
		sysStaffRoleExample1.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdIn(roleIds).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList1 = sysStaffRoleService.selectByExample(sysStaffRoleExample1);
		if(sysStaffRoleList1!=null && sysStaffRoleList1.size()>0){
			model.addAttribute("role431", true);
		}else{
			model.addAttribute("role431", false);
		}


		//插入一条查看商家基础资料日志
		MchtViewlog mchtViewlog=new MchtViewlog();
		mchtViewlog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		mchtViewlog.setCreateDate(new Date());
		mchtViewlog.setDelFlag("0");
		mchtViewlog.setMchtId(mchtId);
		mchtViewlogService.insert(mchtViewlog);
		
		MchtLicenseChgExample e = new MchtLicenseChgExample();
		e.setOrderByClause("id DESC");
		e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
		List<MchtLicenseChg> mchtLicenseChgs = mchtLicenseChgMapper.selectByExample(e);
		if(mchtLicenseChgs!=null && mchtLicenseChgs.size()>0){
			model.addAttribute("mchtLicenseChg", mchtLicenseChgs.get(0));
		}
		return "mcht/mchtBaseInfoEdit";
	}

	@RequestMapping(value = "/mcht/changeMchtStatus.shtml")
	public String changeMchtStatus(Model model, HttpServletRequest request) {
		model.addAttribute("mchtId",Integer.valueOf(request.getParameter("mchtId")));
		model.addAttribute("status", request.getParameter("status"));
		model.addAttribute("mchtInfoStatus",DataDicUtil.getStatusList("BU_MCHT_INFO", "STATUS"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.valueOf(request.getParameter("mchtId")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(mchtInfo.getLastCloseDate()!=null){
			model.addAttribute("lastCloseDate", sdf.format(mchtInfo.getLastCloseDate()));
		}
		model.addAttribute("lastCloseRemarks", mchtInfo.getLastCloseRemarks());
		model.addAttribute("remarks", mchtInfo.getRemarks());
		model.addAttribute("closeReason", mchtInfo.getCloseReason());
		List<SysStatus> closeReasonList = DataDicUtil.getStatusList("BU_MCHT_CLOSE_APPLICATION", "CLOSE_REASON");
		model.addAttribute("closeReasonList", closeReasonList);
		return "/mcht/changeMchtStatus";
	}

	@RequestMapping(value = "/mcht/changeShopNameAuditStatus.shtml")
	public String changeShopNameAuditStatus(Model model,
			HttpServletRequest request) {

		MchtInfoCustom mchtInfoCustom = mchtInfoService
				.selectMchtInfoCustomById(Integer.valueOf(request
						.getParameter("id")));

		if ("1".equals(mchtInfoCustom.getShopNameAuditStatus())) {
			MchtInfo mchtInfo4Update = new MchtInfo();
			mchtInfo4Update.setShopNameAuditStatus("2");
			mchtInfo4Update.setId(mchtInfoCustom.getId());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo4Update);
			mchtInfoCustom.setShopNameAuditStatus("2");
		}

		model.addAttribute("mchtInfo", mchtInfoCustom);

		model.addAttribute("shopTypeStatusList",
				DataDicUtil.getStatusList("BU_MCHT_INFO", "SHOP_TYPE"));
		model.addAttribute("shopTypeStatusList",
				DataDicUtil.getStatusList("BU_MCHT_INFO", "SHOP_TYPE"));
		model.addAttribute("shopNameAuditStatus", DataDicUtil.getStatusList(
				"BU_MCHT_INFO", "SHOP_NAME_AUDIT_STATUS"));
		MchtSettledApplyExample msae = new MchtSettledApplyExample();
		msae.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfoCustom.getId());
		List<MchtSettledApply> mchtSettledApplys = mchtSettledApplyService.selectByExample(msae);
		if(mchtSettledApplys!=null && mchtSettledApplys.size()>0){
			model.addAttribute("feeRate", mchtSettledApplys.get(0).getFeeRate());
		}
		return "/mcht/changeShopNameAuditStatus";
	}

	@RequestMapping(value = "/mcht/changeShopNameAuditStatusCommit.shtml")
	@ResponseBody
	public Map<String, Object> changeShopNameAuditStatusCommit(Model model,
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if (StringUtil.isEmpty(request.getParameter("mchtId"))) {
				throw new ArgException("商家不存在");
			}
			Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));

			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if (mchtInfo == null) {
				throw new ArgException("商家不存在");
			}

			String shopNameAuditStatus = request.getParameter("shopNameAuditStatus");
			MchtInfo mchtInfo4update = new MchtInfo();
			mchtInfo4update.setId(mchtId);
			mchtInfo4update.setShopNameAuditStatus(shopNameAuditStatus);
			mchtInfo4update.setShopNameAuditRemarks(request.getParameter("shopNameAuditRemarks"));
			mchtInfo4update.setContractDeposit(new BigDecimal(request.getParameter("contractDeposit")));
			mchtInfo4update.setFeeRate(new BigDecimal(request.getParameter("feeRate")));
			mchtInfo4update.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo4update.setUpdateDate(new Date());
			mchtInfoService.changeShopNameAuditStatusCommit(mchtInfo4update);
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	@RequestMapping(value = "/mcht/mchtStatusChangeSubmit.shtml")
	@ResponseBody
	public Map<String, Object> mchtStatusChangeSubmit(Model model,
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if (StringUtil.isEmpty(request.getParameter("mchtId"))) {
				throw new ArgException("商家不存在");
			}
			Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));

			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if (mchtInfo == null) {
				throw new ArgException("商家不存在");
			}

			String status = request.getParameter("status");
			MchtInfo mchtInfo4update = new MchtInfo();
			mchtInfo4update.setId(mchtId);
			mchtInfo4update.setStatus(status);

			if (!StringUtil.isEmpty(request.getParameter("remarks"))) {
				mchtInfo4update
						.setRemarks(mchtInfo.getRemarks() == null ? request
								.getParameter("remarks") : (mchtInfo
								.getRemarks() + ";" + request
								.getParameter("remarks")));
			}

			// 选正常，并且原开店日期为空，就要把开店日期更为今天

			if ("1".equals(status) && mchtInfo.getCooperateBeginDate() == null) {
				mchtInfo4update.setCooperateBeginDate(new Date());
			}
			
			if(!mchtInfo.getStatus().equals(status)){
				mchtInfo4update.setStatusDate(new Date());
			}
			String closeDate = request.getParameter("closeDate");
			if(!StringUtil.isEmpty(closeDate)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				mchtInfo4update.setStatusDate(sdf.parse(closeDate+" 00:00:00"));
				mchtInfo4update.setLastCloseDate(sdf.parse(closeDate+" 00:00:00"));
				mchtInfo4update.setLastCloseRemarks(request.getParameter("lastCloseRemarks"));
			}
			String closeReason = request.getParameter("closeReason");
			if(!StringUtil.isEmpty(closeReason)){
				mchtInfo4update.setCloseReason(closeReason);
			}
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo4update);
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "备注最多只能1024个字节");
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 修改商家用户信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/changeMchtUser.shtml")
	public String changeMchtUser(Model model, HttpServletRequest request) {
		MchtUser mchtUser = mchtUserService.selectByPrimaryKey(Integer
				.valueOf(request.getParameter("mchtUserId")));
		model.addAttribute("mchtUser", mchtUser);
		return "/mcht/changeMchtUser";
	}

	@RequestMapping(value = "/mcht/mchtUserChangeSubmit.shtml")
	@ResponseBody
	public Map<String, Object> mchtUserChangeSubmit(Model model,
			HttpServletRequest request, MchtUser mchtUser) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if (mchtUser.getId() == null) {
				throw new ArgException("用户Id不能为空");
			}

			MchtUserExample mchtUserExample = new MchtUserExample();
			mchtUserExample.createCriteria().andIdNotEqualTo(mchtUser.getId())
					.andUserCodeEqualTo(mchtUser.getUserCode());

			int count = mchtUserService.countByExample(mchtUserExample);
			if (count > 0) {
				throw new ArgException("用户名已经存在");
			}

			if (StringUtil.isEmpty(mchtUser.getPassword())) {
				mchtUser.setPassword(null);
			} else {
				mchtUser.setPassword(SecurityUtil.md5Encode(mchtUser
						.getPassword()));
			}
			mchtUser.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(
					request).getStaffID()));
			mchtUser.setUpdateDate(new Date());
			mchtUserService.updateByPrimaryKeySelective(mchtUser);
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 修改商家基础信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/changeMchtBaseInfo.shtml")
	public String changeMchtBaseInfo(Model model, HttpServletRequest request) {
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
		model.addAttribute("mchtInfo",
				mchtInfoService.selectByPrimaryKey(mchtId));
		model.addAttribute("mchtTypeStatus",
				DataDicUtil.getStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
		return "/mcht/changeMchtBaseInfo";
	}

	@RequestMapping(value = "/mcht/mchtBaseInfoChangeSubmit.shtml")
	@ResponseBody
	public Map<String, Object> mchtBaseInfoChangeSubmit(Model model,
			HttpServletRequest request, MchtInfo mchtInfo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String errorMsg = "";
			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			mchtInfoExample.createCriteria().andIdNotEqualTo(mchtInfo.getId())
					.andShortNameEqualTo(mchtInfo.getShortName().trim());
			int count = mchtInfoService.countByExample(mchtInfoExample);
			if (count > 0) {
				errorMsg = errorMsg + "；商家简称已经存在";
			}
			mchtInfoExample = new MchtInfoExample();
			mchtInfoExample.createCriteria().andIdNotEqualTo(mchtInfo.getId())
					.andShopCatalogEqualTo(mchtInfo.getShopCatalog().trim());
			count = mchtInfoService.countByExample(mchtInfoExample);
			if (count > 0) {
				errorMsg = errorMsg + "；店铺目录已经存在";
			}

			mchtInfoExample = new MchtInfoExample();
			mchtInfoExample.createCriteria().andIdNotEqualTo(mchtInfo.getId())
					.andShopNameEqualTo(mchtInfo.getShopName());
			count = mchtInfoService.countByExample(mchtInfoExample);
			if (count > 0) {
				errorMsg = errorMsg + "；店铺名称已经存在";
			}

			if (errorMsg.length() > 0) {
				throw new ArgException(errorMsg.substring(1));
			}

			mchtInfo.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(
					request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 修改商家资质公司信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/changeMchtCompanyInfo.shtml")
	public String changeMchtCompanyInfo(Model model, HttpServletRequest request) {
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
		model.addAttribute("mchtInfo",
				mchtInfoService.selectByPrimaryKey(mchtId));
		model.addAttribute("companyTypeStatus",
				DataDicUtil.getStatusList("BU_MCHT_INFO", "COMPANY_TYPE"));
		model.addAttribute("regFeeTypeStatus",
				DataDicUtil.getStatusList("BU_MCHT_INFO", "REG_FEE_TYPE"));
		model.addAttribute("isCompanyInfPerfectStatus", DataDicUtil
				.getStatusList("BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT"));
		// 营业执照副本
		MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId);
		List<MchtBlPic> mchtBlPicList = mchtBlPicService
				.selectByExample(mchtBlPicExample);
		List<Map<String, Object>> mchtBlPics = new ArrayList<Map<String, Object>>();
		for (MchtBlPic mchtBlPic : mchtBlPicList) {
			Map<String, Object> pic = new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtBlPic.getPic());
			mchtBlPics.add(pic);
		}
		model.addAttribute("mchtBlPics", mchtBlPics);

		return "/mcht/changeMchtCompanyInfo";
	}

	@RequestMapping(value = "/mcht/mchtCompanyInfoChangeSubmit.shtml")
	@ResponseBody
	public Map<String, Object> mchtCompanyInfoChangeSubmit(Model model,
			HttpServletRequest request, MchtInfo mchtInfo, String mchtBlPics) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			mchtInfo.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(
					request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			mchtInfoService.updateMchtCompanyInfo(mchtInfo, mchtBlPics);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	// 商家经营品类
	@RequestMapping(value = "/mcht/mchtProductTypeData.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductTypeData(HttpServletRequest request,
			Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
		int totalCount = 0;
		List<MchtProductTypeCustom> mchtProductTypeCustoms = new ArrayList<MchtProductTypeCustom>();
		try {
			MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
			mchtProductTypeExample.createCriteria().andDelFlagEqualTo("0")
					.andMchtIdEqualTo(mchtId);
			totalCount = mchtProductTypeService
					.countByExample(mchtProductTypeExample);
			mchtProductTypeCustoms = mchtProductTypeService
					.getMchtProductTypeCustomsByMchtId(mchtId, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtProductTypeCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}

	// 商家授权品牌
	@RequestMapping(value = "/mcht/mchtProductBrandData.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductBrandData(HttpServletRequest request,
			Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		int totalCount = 0;
		List<MchtProductBrandCustom> mchtProductBrandCustoms = new ArrayList<MchtProductBrandCustom>();
		try {
			MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
			mchtProductBrandExample.createCriteria().andDelFlagEqualTo("0")
					.andMchtIdEqualTo(mchtId);
			totalCount = mchtProductBrandService
					.countByExample(mchtProductBrandExample);
			mchtProductBrandCustoms = mchtProductBrandService
					.getMchtProductBrandCustomsByMchtId(mchtId, page);
			for (MchtProductBrandCustom mchtProductBrandCustom : mchtProductBrandCustoms) {
				mchtProductBrandCustom.setMchtStatus(mchtInfo.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtProductBrandCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}


	// 品牌初始化
	@RequestMapping(value = "/mcht/brandInitialization.shtml")
	@ResponseBody
	public Map<String, Object> brandInitialization(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtProductBrandId = Integer.valueOf(request.getParameter("mchtProductBrandId"));
			MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mchtProductBrandId);
			mchtProductBrand.setStatus("0");//运营审核状态
			mchtProductBrand.setZsAuditStatus("1");//招商审核状态(待审)
			mchtProductBrand.setAuditStatus("0");//法务审核状态
			mchtProductBrand.setBrandSource("2");//品牌标识(追加品牌)
			mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	// 商家信息改变日志
	@RequestMapping(value = "/mcht/mchtInfoChangeLogData.shtml")
	@ResponseBody
	public Map<String, Object> mchtInfoChangeLogData(
			HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
		int totalCount = 0;
		List<MchtInfoChangeLogCustom> mchtInfoChangeLogCustoms = new ArrayList<MchtInfoChangeLogCustom>();
		try {
			MchtInfoChangeLogExample mchtInfoChangeLogExample = new MchtInfoChangeLogExample();
			mchtInfoChangeLogExample.createCriteria().andDelFlagEqualTo("0")
					.andMchtIdEqualTo(mchtId);
			totalCount = mchtInfoChangeLogService
					.countByExample(mchtInfoChangeLogExample);
			mchtInfoChangeLogExample.setLimitSize(page.getLimitSize());
			mchtInfoChangeLogExample.setLimitStart(page.getLimitStart());
			mchtInfoChangeLogExample.setOrderByClause("id desc");
			mchtInfoChangeLogCustoms = mchtInfoChangeLogService
					.selectMchtInfoChangeLogCustomByExample(mchtInfoChangeLogExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtInfoChangeLogCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * 获取商家品牌资质图
	 * 
	 * @param time
	 * @return
	 */
	@RequestMapping(value = "/mcht/getMchtBrandPic.shtml")
	@ResponseBody
	public List<MchtBrandAptitudePic> getMchtBrandPic(HttpServletRequest request) {
		MchtBrandAptitudePicExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
		MchtBrandAptitudePicExample.Criteria criteria = mchtBrandAptitudePicExample
				.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtProductBrandIdEqualTo(Integer.valueOf(request
				.getParameter("mchtProductBrandId")));
		List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService
				.selectByExample(mchtBrandAptitudePicExample);
		return mchtBrandAptitudePics;
	}

	/**
	 * 获取商家授权平台资质图
	 * 
	 * @param time
	 * @return
	 */
	@RequestMapping(value = "/mcht/getPlatfromAuthPic.shtml")
	@ResponseBody
	public List<MchtPlatformAuthPic> getPlatfromAuthPic(
			HttpServletRequest request) {
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		MchtPlatformAuthPicExample.Criteria criteria = mchtPlatformAuthPicExample
				.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtProductBrandIdEqualTo(Integer.valueOf(request
				.getParameter("mchtProductBrandId")));
		List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService
				.selectByExample(mchtPlatformAuthPicExample);
		return mchtPlatformAuthPics;
	}

	/**
	 * 获取商家品牌资质图缓存表
	 * 
	 * @param time
	 * @return
	 */
	@RequestMapping(value = "/mcht/getMchtBrandPicChg.shtml")
	@ResponseBody
	public List<MchtBrandAptitudePicChg> getMchtBrandPicChg(
			HttpServletRequest request) {
		MchtBrandAptitudePicChgExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicChgExample();
		MchtBrandAptitudePicChgExample.Criteria criteria = mchtBrandAptitudePicExample
				.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andBrandChgIdEqualTo(Integer.valueOf(request
				.getParameter("mchtProductBrandId")));
		List<MchtBrandAptitudePicChg> selectByExample = mchtBrandAptitudePicChgServer
				.selectByExample(mchtBrandAptitudePicExample);
		return selectByExample;
	}

	/**
	 * 获取商家授权平台资质图缓存表
	 * 
	 * @param time
	 * @return
	 */
	@RequestMapping(value = "/mcht/getPlatfromAuthPicChg.shtml")
	@ResponseBody
	public List<MchtPlatformAuthPicChg> getPlatfromAuthPicChg(
			HttpServletRequest request) {
		MchtPlatformAuthPicChgExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicChgExample();
		MchtPlatformAuthPicChgExample.Criteria criteria = mchtPlatformAuthPicExample
				.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andBrandChgIdEqualTo(Integer.valueOf(request
				.getParameter("mchtProductBrandId")));
		List<MchtPlatformAuthPicChg> selectByExample = mchtPlatformAuthPicChgServer
				.selectByExample(mchtPlatformAuthPicExample);
		return selectByExample;
	}

	/**
	 * 获取商家营业执照图
	 * 
	 * @param time
	 * @return
	 */
	@RequestMapping(value = "/mcht/getMchtBlPic.shtml")
	@ResponseBody
	public List<MchtBlPic> getMchtBlPic(HttpServletRequest request) {
		// 营业执照
		MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
		mchtBlPicExample
				.createCriteria()
				.andDelFlagEqualTo("0")
				.andMchtIdEqualTo(
						Integer.valueOf(request.getParameter("mchtId")));
		List<MchtBlPic> mchtBlPics = mchtBlPicService
				.selectByExample(mchtBlPicExample);
		return mchtBlPics;
	}

	/**
	 * 获取商家营业执照图修改图
	 * 
	 * @param time
	 * @return
	 */
	@RequestMapping(value = "/mcht/getMchtBlPicChg.shtml")
	@ResponseBody
	public List<MchtBlPicChg> getMchtBlPicChg(HttpServletRequest request) {
		MchtBlPicChgExample mchtBlPicChgExample = new MchtBlPicChgExample();
		mchtBlPicChgExample
				.createCriteria()
				.andDelFlagEqualTo("0")
				.andMchtInfoChgIdEqualTo(
						Integer.valueOf(request.getParameter("mchtInfoChgId")));
		return mchtBlPicChgService.selectByExample(mchtBlPicChgExample);
	}

	/**
	 * 修改商家资质信息审核状态
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/changeIsCompanyInfPerfect.shtml")
	public String changeMchtAuditStatus(Model model, HttpServletRequest request) {
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));

		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);

		if ("2".equals(mchtInfo.getIsCompanyInfPerfect())) {
			MchtInfo mchtInfo4Update = new MchtInfo();
			mchtInfo4Update.setIsCompanyInfPerfect("3");
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo4Update);
			mchtInfo.setIsCompanyInfPerfect("3");
		}

		model.addAttribute("mchtInfo", mchtInfo);
		model.addAttribute("isCompanyInfPerfectStatus", DataDicUtil
				.getStatusList("BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT"));

		return "/mcht/changeIsCompanyInfPerfect";
	}

	@RequestMapping(value = "/mcht/isCompanyInfPerfectChangeSubmit.shtml")
	@ResponseBody
	public Map<String, Object> isCompanyInfPerfectChangeSubmit(Model model,
			HttpServletRequest request, MchtInfo mchtInfo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			mchtInfo.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(
					request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			mchtInfoService.isCompanyInfPerfectChange(mchtInfo);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 添加商户经营品类
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/addMchtProductType.shtml")
	public String addMchtProductType(Model model, HttpServletRequest request) {
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0")
				.andParentIdEqualTo(1);
		List<ProductType> productTypes = productTypeService
				.selectByExample(productTypeExample);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("mchtProductTypeStatus",
				DataDicUtil.getStatusList("BU_MCHT_PRODUCT_TYPE", "STATUS"));
		model.addAttribute("mchtId", request.getParameter("mchtId"));
		return "/mcht/addMchtProductType";
	}

	/**
	 * 修改商户经营品类
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/editMchtProductType.shtml")
	public String editMchtProductType(Model model, HttpServletRequest request) {
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0")
				.andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService
				.selectByExample(productTypeExample);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("mchtProductTypeStatus",
				DataDicUtil.getStatusList("BU_MCHT_PRODUCT_TYPE", "STATUS"));
		// model.addAttribute("mchtId", request.getParameter("mchtId"));
		if (!StringUtil.isEmpty(request.getParameter("mchtProductTypeId"))) {
			model.addAttribute("mchtProductType", mchtProductTypeService
					.selectByPrimaryKey(Integer.valueOf(request
							.getParameter("mchtProductTypeId"))));
		} else {
			MchtProductType mchtProductType = new MchtProductType();
			mchtProductType.setMchtId(Integer.valueOf(request
					.getParameter("mchtId")));
			model.addAttribute("mchtProductType", mchtProductType);
		}
		model.addAttribute("canEdit", request.getParameter("canEdit"));
		return "/mcht/addMchtProductType";
	}

	@RequestMapping(value = "/mcht/mchtProductTypeSaveSubmit.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductTypeSaveSubmit(Model model,
			HttpServletRequest request, MchtProductType mchtProductType) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {

			if (mchtProductType.getId() != null) {

				mchtProductType.setUpdateBy(Integer.valueOf(this
						.getSessionStaffBean(request).getStaffID()));
				mchtProductType.setUpdateDate(new Date());
				mchtProductTypeService
						.updateByPrimaryKeySelective(mchtProductType);
			} else {
				MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
				mchtProductTypeExample
						.createCriteria()
						.andDelFlagEqualTo("0")
						.andMchtIdEqualTo(mchtProductType.getMchtId())
						.andProductTypeIdEqualTo(
								mchtProductType.getProductTypeId());
				if (mchtProductTypeService
						.countByExample(mchtProductTypeExample) > 0) {
					throw new ArgException("已添加过此品类");
				}
				mchtProductType.setCreateBy(Integer.valueOf(this
						.getSessionStaffBean(request).getStaffID()));
				mchtProductType.setCreateDate(new Date());
				mchtProductTypeService.insertSelective(mchtProductType);
			}

		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	@RequestMapping(value = "/mcht/mchtProductTypeDelSubmit.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductTypeDelSubmit(Model model,
			HttpServletRequest request, MchtProductType mchtProductType) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if (StringUtil.isEmpty(request.getParameter("mchtProductTypeId"))) {
				throw new ArgException("不可删除");
			}
			mchtProductTypeService.deleteByPrimaryKey(Integer.valueOf(request
					.getParameter("mchtProductTypeId")));
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 修改商家经营品牌
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/editMchtProductBrand.shtml")
	public String editMchtProductBrand(Model model, HttpServletRequest request) {
		ProductBrand productBrand = new ProductBrand();
		MchtProductBrandCustom mchtProductBrand;
		String mchtProductBrandId = request.getParameter("mchtProductBrandId");
		if (!StringUtil.isEmpty(mchtProductBrandId)) {
			mchtProductBrand = mchtProductBrandService.getMchtProductbrandInfoCustomByID(Integer.valueOf(mchtProductBrandId));
			if(mchtProductBrand.getProductBrandId()!=null){
				productBrand = productBrandService.selectByPrimaryKey(mchtProductBrand.getProductBrandId());
			}

			// 资质
			MchtBrandAptitudeExample example = new MchtBrandAptitudeExample();
			MchtBrandAptitudeExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(example);
			for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
				MchtBrandAptitudePicExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
				MchtBrandAptitudePicExample.Criteria criteria = mchtBrandAptitudePicExample.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
				List<MchtBrandAptitudePic> mchtBrandAptitudePicList = mchtBrandAptitudePicService.selectByExample(mchtBrandAptitudePicExample);
				List<Map<String, Object>> mchtBrandAptitudePics = new ArrayList<Map<String, Object>>();
				for (MchtBrandAptitudePic mchtBrandAptitudePic : mchtBrandAptitudePicList) {
					Map<String, Object> pic = new HashMap<String, Object>();
					pic.put("PICTURE_PATH", mchtBrandAptitudePic.getPic());
					mchtBrandAptitudePics.add(pic);
				}
				mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePics);
			}
			model.addAttribute("mchtBrandAptitudeCustoms", mchtBrandAptitudeCustoms);
			if(mchtBrandAptitudeCustoms == null || mchtBrandAptitudeCustoms.size() == 0){
				MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
				MchtBrandAptitudePicExample.Criteria mbapec = mbape.createCriteria();
				mbapec.andDelFlagEqualTo("0");
				mbapec.andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
				List<MchtBrandAptitudePic> mchtBrandAptitudePicList = mchtBrandAptitudePicService.selectByExample(mbape);
				List<Map<String, Object>> mchtBrandAptitudePics = new ArrayList<Map<String, Object>>();
				for (MchtBrandAptitudePic mchtBrandAptitudePic : mchtBrandAptitudePicList) {
					Map<String, Object> pic = new HashMap<String, Object>();
					pic.put("PICTURE_PATH", mchtBrandAptitudePic.getPic());
					mchtBrandAptitudePics.add(pic);
				}
				model.addAttribute("mchtBrandAptitudePics", mchtBrandAptitudePics);
			}
			MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
			MchtPlatformAuthPicExample.Criteria mchtPlatformAuthCriteria = mchtPlatformAuthPicExample.createCriteria();
			mchtPlatformAuthCriteria.andDelFlagEqualTo("0");
			mchtPlatformAuthCriteria.andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			List<MchtPlatformAuthPic> mchtPlatformAuthPicList = mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample);
			List<Map<String, Object>> mchtPlatformAuthPics = new ArrayList<Map<String, Object>>();
			for (MchtPlatformAuthPic mchtPlatformAuthPic : mchtPlatformAuthPicList) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtPlatformAuthPic.getPic());
				mchtPlatformAuthPics.add(pic);
			}

			model.addAttribute("mchtPlatformAuthPics", mchtPlatformAuthPics);

			MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
			mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			List<MchtBrandInspectionPic> mchtBrandInspectionPicList = mchtBrandInspectionPicServer.selectByExample(mchtBrandInspectionPicExample);
			List<Map<String, Object>> mchtBrandInspectionPics = new ArrayList<Map<String, Object>>();
			for (MchtBrandInspectionPic mchtBrandInspectionPic : mchtBrandInspectionPicList) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandInspectionPic.getPic());
				mchtBrandInspectionPics.add(pic);
			}
			model.addAttribute("mchtBrandInspectionPics",mchtBrandInspectionPics);

			MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
			mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			List<MchtBrandInvoicePic> mchtBrandInvoicePicList = mchtBrandInvoicePicServer.selectByExample(mchtBrandInvoicePicExample);
			List<Map<String, Object>> mchtBrandInvoicePics = new ArrayList<Map<String, Object>>();
			for (MchtBrandInvoicePic mchtBrandInvoicePic : mchtBrandInvoicePicList) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandInvoicePic.getPic());
				mchtBrandInvoicePics.add(pic);
			}
			model.addAttribute("mchtBrandInvoicePics",mchtBrandInvoicePics);

			MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
			mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			List<MchtBrandOtherPic> mchtBrandOtherPicList = mchtBrandOtherPicServer.selectByExample(mchtBrandOtherPicExample);
			List<Map<String, Object>> mchtBrandOtherPics = new ArrayList<Map<String, Object>>();
			for (MchtBrandOtherPic mchtBrandOtherPic : mchtBrandOtherPicList) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandOtherPic.getPic());
				mchtBrandOtherPics.add(pic);
			}
			model.addAttribute("mchtBrandOtherPics", mchtBrandOtherPics);
			
			MchtBrandProductTypeExample mbpte = new MchtBrandProductTypeExample();
			mbpte.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId()).andTLevelEqualTo(2);
			List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms = mchtBrandProductTypeService.selectCustomByExample(mbpte);
			model.addAttribute("mchtBrandProductTypeCustoms", mchtBrandProductTypeCustoms);
		} else {
			mchtProductBrand = new MchtProductBrandCustom();
			mchtProductBrand.setMchtId(Integer.valueOf(request.getParameter("mchtId")));
		}

		model.addAttribute("mchtProductBrand", mchtProductBrand);
		model.addAttribute("productBrand", productBrand);
		model.addAttribute("aptitudeTypeStatus", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE"));
		model.addAttribute("mchtProductBrandStatus",DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "STATUS"));
		model.addAttribute("auditStatus", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "AUDIT_STATUS"));
		model.addAttribute("priceModelStatusDesc", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "PRICE_MODEL"));

		model.addAttribute("mchtInfo", mchtInfoService.selectByPrimaryKey(mchtProductBrand.getMchtId()));
		model.addAttribute("view", request.getParameter("view"));
		PlatformContactExample pce = new PlatformContactExample();
		pce.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andStatusEqualTo("0");
		List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
		if(platformContacts!=null && platformContacts.size()>0){
			model.addAttribute("contactType", platformContacts.get(0).getContactType());
		}
		SysRoleInfoExample srie = new SysRoleInfoExample();
		srie.createCriteria().andStatusEqualTo("A").andRoleNameEqualTo("总经办");
		List<SysRoleInfo> sysRoleInfos = sysRoleInfoService.selectByExample(srie);
		if(sysRoleInfos!=null && sysRoleInfos.size()>0){
			SysStaffRoleExample ssre = new SysStaffRoleExample();
			ssre.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(sysRoleInfos.get(0).getRoleId());
			List<SysStaffRole> sysStaffRoles = sysStaffRoleService.selectByExample(ssre);
			if(sysStaffRoles!=null && sysStaffRoles.size()>0){
				model.addAttribute("generalOffice", 1);
			}
		}
		SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		if ("0".equals(sysStaffInfo.getCanViewQualification())&& "3".equals(mchtProductBrand.getAuditStatus())){
			return "/mcht/noPermissionView";
		}
		return "/mcht/editMchtProductBrand";
	}

	/**
	 * 审核商家经营品牌
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/auditMchtProductBrand.shtml")
	public String auditMchtProductBrand(Model model, HttpServletRequest request) {
		ProductBrand productBrand = new ProductBrand();
		MchtProductBrandCustom mchtProductBrand;
		String mchtProductBrandId = request.getParameter("mchtProductBrandId");
		if (!StringUtil.isEmpty(mchtProductBrandId)) {
			mchtProductBrand = mchtProductBrandService.getMchtProductbrandInfoCustomByID(Integer.valueOf(mchtProductBrandId));
			if(mchtProductBrand.getProductBrandId()!=null){
				productBrand = productBrandService.selectByPrimaryKey(mchtProductBrand.getProductBrandId());
			}
			// 资质
			MchtBrandAptitudeExample example = new MchtBrandAptitudeExample();
			MchtBrandAptitudeExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(example);
			for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
				MchtBrandAptitudePicExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
				MchtBrandAptitudePicExample.Criteria criteria = mchtBrandAptitudePicExample.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
				List<MchtBrandAptitudePic> mchtBrandAptitudePicList = mchtBrandAptitudePicService.selectByExample(mchtBrandAptitudePicExample);
				List<Map<String, Object>> mchtBrandAptitudePics = new ArrayList<Map<String, Object>>();
				for (MchtBrandAptitudePic mchtBrandAptitudePic : mchtBrandAptitudePicList) {
					Map<String, Object> pic = new HashMap<String, Object>();
					pic.put("PICTURE_PATH", mchtBrandAptitudePic.getPic());
					mchtBrandAptitudePics.add(pic);
				}
				mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePics);
			}
			model.addAttribute("mchtBrandAptitudeCustoms", mchtBrandAptitudeCustoms);
			
			if(mchtBrandAptitudeCustoms == null || mchtBrandAptitudeCustoms.size() == 0){
				MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
				MchtBrandAptitudePicExample.Criteria mbapec = mbape.createCriteria();
				mbapec.andDelFlagEqualTo("0");
				mbapec.andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
				List<MchtBrandAptitudePic> mchtBrandAptitudePicList = mchtBrandAptitudePicService.selectByExample(mbape);
				List<Map<String, Object>> mchtBrandAptitudePics = new ArrayList<Map<String, Object>>();
				for (MchtBrandAptitudePic mchtBrandAptitudePic : mchtBrandAptitudePicList) {
					Map<String, Object> pic = new HashMap<String, Object>();
					pic.put("PICTURE_PATH", mchtBrandAptitudePic.getPic());
					mchtBrandAptitudePics.add(pic);
				}
				model.addAttribute("mchtBrandAptitudePics", mchtBrandAptitudePics);
			}
			MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
			MchtPlatformAuthPicExample.Criteria mchtPlatformAuthCriteria = mchtPlatformAuthPicExample.createCriteria();
			mchtPlatformAuthCriteria.andDelFlagEqualTo("0");
			mchtPlatformAuthCriteria.andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			List<MchtPlatformAuthPic> mchtPlatformAuthPicList = mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample);
			List<Map<String, Object>> mchtPlatformAuthPics = new ArrayList<Map<String, Object>>();
			for (MchtPlatformAuthPic mchtPlatformAuthPic : mchtPlatformAuthPicList) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtPlatformAuthPic.getPic());
				mchtPlatformAuthPics.add(pic);
			}

			model.addAttribute("mchtPlatformAuthPics", mchtPlatformAuthPics);

			MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
			mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			List<MchtBrandInspectionPic> mchtBrandInspectionPicList = mchtBrandInspectionPicServer.selectByExample(mchtBrandInspectionPicExample);
			List<Map<String, Object>> mchtBrandInspectionPics = new ArrayList<Map<String, Object>>();
			for (MchtBrandInspectionPic mchtBrandInspectionPic : mchtBrandInspectionPicList) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandInspectionPic.getPic());
				mchtBrandInspectionPics.add(pic);
			}
			model.addAttribute("mchtBrandInspectionPics",mchtBrandInspectionPics);

			MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
			mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			List<MchtBrandInvoicePic> mchtBrandInvoicePicList = mchtBrandInvoicePicServer.selectByExample(mchtBrandInvoicePicExample);
			List<Map<String, Object>> mchtBrandInvoicePics = new ArrayList<Map<String, Object>>();
			for (MchtBrandInvoicePic mchtBrandInvoicePic : mchtBrandInvoicePicList) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandInvoicePic.getPic());
				mchtBrandInvoicePics.add(pic);
			}
			model.addAttribute("mchtBrandInvoicePics",mchtBrandInvoicePics);

			MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
			mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			List<MchtBrandOtherPic> mchtBrandOtherPicList = mchtBrandOtherPicServer.selectByExample(mchtBrandOtherPicExample);
			List<Map<String, Object>> mchtBrandOtherPics = new ArrayList<Map<String, Object>>();
			for (MchtBrandOtherPic mchtBrandOtherPic : mchtBrandOtherPicList) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandOtherPic.getPic());
				mchtBrandOtherPics.add(pic);
			}
			model.addAttribute("mchtBrandOtherPics", mchtBrandOtherPics);
			
			MchtBrandProductTypeExample mbpte = new MchtBrandProductTypeExample();
			mbpte.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId()).andTLevelEqualTo(2);
			List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms = mchtBrandProductTypeService.selectCustomByExample(mbpte);
			model.addAttribute("mchtBrandProductTypeCustoms", mchtBrandProductTypeCustoms);
			
		} else {
			mchtProductBrand = new MchtProductBrandCustom();
			mchtProductBrand.setMchtId(Integer.valueOf(request.getParameter("mchtId")));
		}

		model.addAttribute("mchtProductBrand", mchtProductBrand);
		model.addAttribute("productBrand", productBrand);
		model.addAttribute("aptitudeTypeStatus", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE"));
		model.addAttribute("mchtProductBrandStatus",DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "STATUS"));
		model.addAttribute("auditStatus", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "AUDIT_STATUS"));
		model.addAttribute("priceModelStatusDesc", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "PRICE_MODEL"));
		model.addAttribute("mchtInfo", mchtInfoService.selectByPrimaryKey(mchtProductBrand.getMchtId()));
		model.addAttribute("view", request.getParameter("view"));
		return "/mcht/auditMchtProductBrand";
	}

	@RequestMapping(value = "/mcht/mchtProductBrandSaveSubmit.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductBrandSaveSubmit(Model model,HttpServletRequest request, MchtProductBrand mchtProductBrand) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
//			String view = request.getParameter("view");
//			if((!StringUtils.isEmpty(view) && view.equals("3")) || this.getSessionStaffBean(request).getStaffID().equals("1")){//admin 修改运营状态
//				if (mchtProductBrand.getId() != null) {
//					MchtProductBrand mpb = mchtProductBrandService.selectByPrimaryKey(mchtProductBrand.getId());
//					if(!mpb.getStatus().equals(mchtProductBrand.getStatus())){
//						mchtProductBrand.setStatusBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
//						mchtProductBrand.setStatusDate(new Date());
//					}
//					mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
//				}
//				return resMap;
//			}
			String mchtPlatformAuthPics = request.getParameter("mchtPlatformAuthPics");
			String mchtBrandInvoicePics = request.getParameter("mchtBrandInvoicePics");
			String mchtBrandInspectionPics = request.getParameter("mchtBrandInspectionPics");
			String mchtBrandOtherPics = request.getParameter("mchtBrandOtherPics");
			String mchtBrandAptitudeJsonStr = request.getParameter("mchtBrandAptitudeJsonStr");
			if (mchtProductBrand.getId() != null) {
				if (mchtProductBrand.getProductBrandId() != null) {
					MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
					mchtProductBrandExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtProductBrand.getMchtId()).andProductBrandIdEqualTo(mchtProductBrand.getProductBrandId()).andIdNotEqualTo(mchtProductBrand.getId());
					if (mchtProductBrandService.countByExample(mchtProductBrandExample) > 0) {
						throw new ArgException("选择的品牌已授权过");
					}
				}
				if(StringUtils.isEmpty(mchtProductBrand.getAuditStatus())){//招商审核
					mchtProductBrand.setIsZsAuditRecommit("0");
					mchtProductBrand.setZsAuditDate(new Date());
					mchtProductBrand.setZsAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					if(mchtProductBrand.getZsAuditStatus().equals("2")){//招商审核通过。
						mchtProductBrand.setAuditStatus("1");
						mchtProductBrand.setCommitAuditDate(new Date());
						mchtProductBrand.setIsAuditRecommit("0");
					}
				}else{
					mchtProductBrand.setAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					mchtProductBrand.setAuditDate(new Date());
					if(mchtProductBrand.getAuditStatus().equals("3")){//法务审核通过
						MchtProductBrand mpb = mchtProductBrandService.selectByPrimaryKey(mchtProductBrand.getId());
						if(mpb.getBrandSource().equals("1") && mpb.getStatus().equals("0")){//入驻品牌,申请中
							mchtProductBrand.setStatus("1");
						}
						mchtProductBrand.setStatusDate(new Date());
						mchtProductBrand.setStatusBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						mchtProductBrand.setArchiveStatus("0");//未寄出
					}
				}
				mchtProductBrand.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				mchtProductBrand.setUpdateDate(new Date());
				mchtProductBrandService.updateMchtProductBrand(mchtPlatformAuthPics,mchtBrandInvoicePics,mchtBrandInspectionPics,mchtBrandOtherPics,mchtBrandAptitudeJsonStr,mchtProductBrand);
			} else {
				MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
				mchtProductBrandExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtProductBrand.getMchtId()).andProductBrandIdEqualTo(mchtProductBrand.getProductBrandId());
				if (mchtProductBrandService.countByExample(mchtProductBrandExample) > 0) {
					throw new ArgException("选择的品牌已授权过");
				}
				mchtProductBrand.setCreateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				mchtProductBrand.setCreateDate(new Date());
				mchtProductBrandService.insertMchtBrandInfo(mchtProductBrand,mchtBrandAptitudeJsonStr, mchtPlatformAuthPics);
			}

		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	@RequestMapping(value = "/mcht/mchtProductBrandSaveSubmitAndCopy.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductBrandSaveSubmitAndCopyChg(Model model,HttpServletRequest request, MchtProductBrand mchtProductBrand) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
//			String view = request.getParameter("view");
//			if((!StringUtils.isEmpty(view) && view.equals("3")) || this.getSessionStaffBean(request).getStaffID().equals("1")){//admin 修改运营状态
//				if (mchtProductBrand.getId() != null) {
//					MchtProductBrand mpb = mchtProductBrandService.selectByPrimaryKey(mchtProductBrand.getId());
//					if(!mpb.getStatus().equals(mchtProductBrand.getStatus())){
//						mchtProductBrand.setStatusBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
//						mchtProductBrand.setStatusDate(new Date());
//					}
//					mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
//				}
//				return resMap;
//			}
			String mchtPlatformAuthPics = request.getParameter("mchtPlatformAuthPics");
			String mchtBrandInvoicePics = request.getParameter("mchtBrandInvoicePics");
			String mchtBrandInspectionPics = request.getParameter("mchtBrandInspectionPics");
			String mchtBrandOtherPics = request.getParameter("mchtBrandOtherPics");
			String mchtBrandAptitudeJsonStr = request.getParameter("mchtBrandAptitudeJsonStr");
			if (mchtProductBrand.getId() != null) {
				if (mchtProductBrand.getProductBrandId() != null) {
					MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
					mchtProductBrandExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtProductBrand.getMchtId()).andProductBrandIdEqualTo(mchtProductBrand.getProductBrandId()).andIdNotEqualTo(mchtProductBrand.getId());
					if (mchtProductBrandService.countByExample(mchtProductBrandExample) > 0) {
						throw new ArgException("选择的品牌已授权过");
					}
				}
				if(StringUtils.isEmpty(mchtProductBrand.getAuditStatus())){//招商审核
					mchtProductBrand.setIsZsAuditRecommit("0");
					mchtProductBrand.setZsAuditDate(new Date());
					mchtProductBrand.setZsAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					if(mchtProductBrand.getZsAuditStatus().equals("2")){//招商审核通过。
						mchtProductBrand.setAuditStatus("1");
						mchtProductBrand.setCommitAuditDate(new Date());
						mchtProductBrand.setIsAuditRecommit("0");
					}
				}else{
					mchtProductBrand.setAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					mchtProductBrand.setAuditDate(new Date());
					if(mchtProductBrand.getAuditStatus().equals("3")){//法务审核通过
						MchtProductBrand mpb = mchtProductBrandService.selectByPrimaryKey(mchtProductBrand.getId());
						if(mpb.getBrandSource().equals("1") && mpb.getStatus().equals("0")){//入驻品牌,申请中
							mchtProductBrand.setStatus("1");
						}
						mchtProductBrand.setStatusDate(new Date());
						mchtProductBrand.setStatusBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						mchtProductBrand.setArchiveStatus("0");//未寄出
					}
				}
				mchtProductBrand.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				mchtProductBrand.setUpdateDate(new Date());
				if(mchtProductBrand.getAuditStatus().equals("3")){//法务审核时将数据拷入更新表
					mchtProductBrandService.updateMchtProductBrandAndCopy(mchtPlatformAuthPics,mchtBrandInvoicePics,mchtBrandInspectionPics,mchtBrandOtherPics,mchtBrandAptitudeJsonStr,mchtProductBrand);	
				}else {
					mchtProductBrandService.updateMchtProductBrand(mchtPlatformAuthPics,mchtBrandInvoicePics,mchtBrandInspectionPics,mchtBrandOtherPics,mchtBrandAptitudeJsonStr,mchtProductBrand);
				}
			} else {
				MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
				mchtProductBrandExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtProductBrand.getMchtId()).andProductBrandIdEqualTo(mchtProductBrand.getProductBrandId());
				if (mchtProductBrandService.countByExample(mchtProductBrandExample) > 0) {
					throw new ArgException("选择的品牌已授权过");
				}
				mchtProductBrand.setCreateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				mchtProductBrand.setCreateDate(new Date());
				mchtProductBrandService.insertMchtBrandInfo(mchtProductBrand,mchtBrandAptitudeJsonStr, mchtPlatformAuthPics);
			}
			
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 商家公司信息申请更新审核
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtCompanyInfoChangeApplyAudit.shtml")
	public String mchtCompanyInfoChangeApplyAudit(Model model,
			HttpServletRequest request) {
		// 审核状态
		List<SysStatus> isCompanyInfPerfectStatusDesc = DataDicUtil
				.getTableStatus("BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT");
		// 商家类型
		List<SysStatus> mchtTypeStatusDesc = DataDicUtil.getTableStatus(
				"BU_MCHT_INFO", "MCHT_TYPE");
		// 是否自营
		List<SysStatus> isManageStatusDesc = DataDicUtil.getTableStatus(
				"BU_MCHT_INFO", "IS_MANAGE_SELF");

		// 合作状态
		List<SysStatus> cooperateStatus = DataDicUtil.getTableStatus(
				"BU_MCHT_INFO", "STATUS");

		// 审核状态
		List<SysStatus> statusDesc = DataDicUtil.getTableStatus(
				"BU_MCHT_INFO_CHG", "STATUS");
		model.addAttribute("isCompanyInfPerfectStatusDesc",
				isCompanyInfPerfectStatusDesc);
		model.addAttribute("mchtTypeStatusDesc", mchtTypeStatusDesc);
		model.addAttribute("isManageStatusDesc", isManageStatusDesc);
		model.addAttribute("cooperateStatus", cooperateStatus);
		model.addAttribute("statusDesc", statusDesc);

		return "/mcht/mchtCoInfoChgApplayAuditIndex";
	}

	@ResponseBody
	@RequestMapping(value = "/mcht/updateAuditStatus.shtml")
	public Map<String, Object> updateAuditStatus(HttpServletRequest request,
			Integer mchtProductBrandId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			MchtProductBrand mchtProductBrand4Update = new MchtProductBrand();
			mchtProductBrand4Update.setAuditStatus("2");
			mchtProductBrand4Update.setId(mchtProductBrandId);
			mchtProductBrand4Update.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			mchtProductBrand4Update.setUpdateDate(new Date());
			mchtProductBrandService.updateMchtProductBrand(mchtProductBrand4Update);
			map.put("code", "200");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", "999");
		}
		return map;
	}

	/**
	 * 商家公司信息申请更新审核列表数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtCompanyInfoChangeApplyData.shtml")
	@ResponseBody
	public Map<String, Object> mchtCompanyInfoChangeApplyData(
			HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<MchtInfoChgCustom> mchtInfoChgCustoms = new ArrayList<MchtInfoChgCustom>();
		try {
			MchtInfoChgCustomExample mchtInfoChgCustomExample = new MchtInfoChgCustomExample();
			MchtInfoChgCustomExample.MchtInfoChgCustomCriteria mchtCriteria = mchtInfoChgCustomExample
					.createCriteria();
			mchtCriteria.andDelFlagEqualTo("0");
			mchtCriteria
					.andWhereClause(" EXISTS(select id from bu_mcht_info mi where mi.id=a.mcht_id and mi.del_flag='0' and mi.status in ('1','2'))");
			if (!StringUtil.isEmpty(request.getParameter("status"))) {
				mchtCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if (!StringUtil.isEmpty(request.getParameter("companyName"))) {
				mchtCriteria.andCompanyNameLike("%"
						+ request.getParameter("companyName") + "%");
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtCriteria.andMchtCodeEqualTo(request
						.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtType"))) {
				mchtCriteria.andMchtTypeEqualTo(request
						.getParameter("mchtType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtStatus"))) {
				mchtCriteria.andMchtStatusEqualTo(request
						.getParameter("mchtStatus"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtIsManageSelf"))) {
				mchtCriteria.andMchtIsManageSelfEqualTo(request
						.getParameter("mchtIsManageSelf"));
			}

			if ("1".equals(request.getParameter("is_my_audit"))) {
				PlatformContactExample platformContactExample = new PlatformContactExample();
				platformContactExample
						.createCriteria()
						.andDelFlagEqualTo("0")
						.andStaffIdEqualTo(
								Integer.valueOf(this.getSessionStaffBean(
										request).getStaffID()))
						.andContactTypeEqualTo("7").andStatusEqualTo("0");
				List<PlatformContact> platformContacts = platformContactService
						.selectByExample(platformContactExample);
				if (platformContacts != null && platformContacts.size() > 0) {
					mchtCriteria.andPlatformContactIdEqualTo(platformContacts
							.get(0).getId());
				} else {
					mchtCriteria.andPlatformContactIdEqualTo(99999999);
				}
			}
			if (!StringUtil.isEmpty(request.getParameter("settledType"))) {
				mchtCriteria.andSettledTypeEqualTo(request.getParameter("settledType"));
			}
			totalCount = mchtInfoChgService
					.countMchtInfoChgCustomByExample(mchtInfoChgCustomExample);
			mchtInfoChgCustomExample
					.setOrderByClause(" commit_date asc, id asc");
			mchtInfoChgCustomExample.setLimitSize(page.getLimitSize());
			mchtInfoChgCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoChgCustoms = mchtInfoChgService
					.selectMchtInfoChgCustomByExample(mchtInfoChgCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtInfoChgCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * 修改商家资质公司信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtInfoChgApplyAudit.shtml")
	public String mchtInfoChgApplyAudit(Model model, HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		MchtInfoChgCustom mchtInfoChgCustom = mchtInfoChgService
				.selectMchtInfoChgCustomByPrimaryKey(id);
		MchtInfoCustom mchtInfoCustom = mchtInfoService
				.selectMchtInfoCustomById(mchtInfoChgCustom.getMchtId());

/*		if ("1".equals(mchtInfoChgCustom.getStatus())) {
			MchtInfoChg mchtInfoChg4Update = new MchtInfoChg();
			mchtInfoChg4Update.setStatus("2");
			mchtInfoChg4Update.setId(mchtInfoChgCustom.getId());
			mchtInfoChgService.updateByPrimaryKeySelective(mchtInfoChg4Update);
			mchtInfoChgCustom.setStatus("2");
		}*/
		model.addAttribute("mchtInfoChg", mchtInfoChgCustom);
		model.addAttribute("mchtInfo", mchtInfoCustom);
			
		//身份证
		List<Map<String, String>> corporationIdcardImgList = new ArrayList<Map<String,String>>();
		Map<String, String> pic1 = new HashMap<String, String>();
		Map<String, String> pic2 = new HashMap<String, String>();
		if(!StringUtil.isBlank(mchtInfoChgCustom.getCorporationIdcardImg1())){		
			pic1.put("PICTURE_PATH", mchtInfoChgCustom.getCorporationIdcardImg1());
			corporationIdcardImgList.add(pic1);
		}
		if(!StringUtil.isBlank(mchtInfoChgCustom.getCorporationIdcardImg2())){	
			pic2.put("PICTURE_PATH", mchtInfoChgCustom.getCorporationIdcardImg2());
			corporationIdcardImgList.add(pic2);
		}
		model.addAttribute("corporationIdcardImgList",corporationIdcardImgList);
		//营业执照副本
		List<Map<String, String>> mchtBlPicPics = new ArrayList<Map<String,String>>();
		Map<String, String> blPics = new HashMap<String, String>();
		if(!StringUtil.isBlank(mchtInfoChgCustom.getBlPic())){
			blPics.put("PICTURE_PATH", mchtInfoChgCustom.getBlPic());
			mchtBlPicPics.add(blPics);
		}
		model.addAttribute("mchtBlPicPics",mchtBlPicPics);
		//银行开户许可证
		List<Map<String, String>> mchtBoaalPicPics = new ArrayList<Map<String,String>>();
		Map<String, String> boaalPics = new HashMap<String, String>();
		if(!StringUtil.isBlank(mchtInfoChgCustom.getBoaalPic())){
			boaalPics.put("PICTURE_PATH", mchtInfoChgCustom.getBoaalPic());
			mchtBoaalPicPics.add(boaalPics);
		}
		model.addAttribute("mchtBoaalPicPics",mchtBoaalPicPics);
		// 营业执照
		// MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
		// mchtBlPicExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfoCustom.getId());
		// List<MchtBlPic> mchtBlPics =
		// mchtBlPicService.selectByExample(mchtBlPicExample);
		// model.addAttribute("mchtBlPics", mchtBlPics);
		//
		// MchtBlPicChgExample mchtBlPicChgExample = new MchtBlPicChgExample();
		// mchtBlPicChgExample.createCriteria().andDelFlagEqualTo("0").andMchtInfoChgIdEqualTo(mchtInfoChgCustom.getId());
		// List<MchtBlPicChg> mchtBlPicChgs =
		// mchtBlPicChgService.selectByExample(mchtBlPicChgExample);
		// model.addAttribute("mchtBlPicChgs", mchtBlPicChgs);

		model.addAttribute("statusList",
				DataDicUtil.getStatusList("BU_MCHT_INFO_CHG", "STATUS"));

		//获取当前系统用户是否是法务对接人或系统管理员
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdIn(Arrays.asList(1,43)).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			model.addAttribute("role43or1", true);
		}else{
			model.addAttribute("role43or1", false);
		}
		return "/mcht/mchtinfoChgApplayAudit";
	}

	/**
	 * 修改商家资质公司信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtInfoChgApplyAuditSave.shtml")
	@ResponseBody
	public Map<String, Object> mchtInfoChgApplyAuditSave(Model model,
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			String auditRemarks = request.getParameter("auditRemarks");
			String status = request.getParameter("status");
			int staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			JSONArray corporationIdcardImgPicstures = JSONArray.fromObject(request.getParameter("corporationIdcardImgPicstures"));
			JSONObject clPicPicsPicstures = JSONObject.fromObject(request.getParameter("clPicPicsPicstures"));
			MchtInfoChg mchtInfoChg = mchtInfoChgService.auditMchtCompanyInfoChg(id, status,auditRemarks,staffId,corporationIdcardImgPicstures,clPicPicsPicstures);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
    		//通过
    		if("3".equals(status)){
    			sendSMS(mchtInfoChg,status);
    		}
    		//驳回
    		if("4".equals(status)){
    			sendSMS(mchtInfoChg,status);
    		}
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;

	}

	public void sendSMS(MchtInfoChg mchtInfoChg,String status){
		if("3".equals(status)){
			// 发送站内信
		    String title = "关于公司资质变更审核通知";
		    String content = "您的公司资质变更申请已审核通过，请尽快将资质文件寄回平台";
		    platformMsgService.save(mchtInfoChg.getMchtId(), title, content, "TZ");
		    		    	
		    // 发送短信给商家端主账号
		    String mobile = mchtUserService.selectMobileByMchtId(mchtInfoChg.getMchtId());
		    content = "【醒购】您的店铺【" + mchtInfoChg.getMchtCode() + "】的【公司资质】已审核通过，请尽快将资质文件寄回平台";
		    SmsUtil.send(mobile, content, "4");
	        // 发送短信给商家店铺负责人
	        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtInfoChg.getMchtId(), 1);
	        if(!StringUtils.isEmpty(mobile)){
	        	SmsUtil.send(mobile, content, "4");
	        }
	        // 发送短信给商家运营专员
	        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtInfoChg.getMchtId(), 2);
	        if(!StringUtils.isEmpty(mobile)){
	        	SmsUtil.send(mobile, content, "4");
	        }
	        
		    // 发送短信给平台招商员
		    mobile = mchtPlatformContactService.findMobile(mchtInfoChg.getMchtId(), "1");
		    content = "【醒购】您对接的【" + mchtInfoChg.getMchtCode() + "】的公司资质变更已审核通过，请尽快联系商家将资料寄回平台";
		    SmsUtil.send(mobile, content, "4");
		}else{
			// 发送站内信
		    String title = "关于公司资质变更审核通知";
		    String content = "您的公司资质变更审核被驳回，请及时修改并重新上传";
		    platformMsgService.save(mchtInfoChg.getMchtId(), title, content, "TZ");
		    		    	
		    // 发送短信给商家端主账号
		    String mobile = mchtUserService.selectMobileByMchtId(mchtInfoChg.getMchtId());
		    content = "【醒购】您的店铺【" + mchtInfoChg.getMchtCode() + "】的公司资质变更审核被驳回，请及时修改并重新上传";
		    SmsUtil.send(mobile, content, "4");
	        // 发送短信给商家店铺负责人
	        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtInfoChg.getMchtId(), 1);
	        if(!StringUtils.isEmpty(mobile)){
	        	SmsUtil.send(mobile, content, "4");
	        }
	        // 发送短信给商家运营专员
	        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtInfoChg.getMchtId(), 2);
	        if(!StringUtils.isEmpty(mobile)){
	        	SmsUtil.send(mobile, content, "4");
	        }
	        
		    // 发送短信给平台招商员
		    mobile = mchtPlatformContactService.findMobile(mchtInfoChg.getMchtId(), "1");
		    content = "【醒购】您对接的【" + mchtInfoChg.getMchtCode() + "】的公司资质变更审核被驳回，请及时的联系商家修改并重新上传";
		    SmsUtil.send(mobile, content, "4");
		}	  
	 }
	  
	/**
	 * 商家财务信息页
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtFinanceInfoEdit.shtml")
	public String mchtFinanceInfoEdit(Model model, HttpServletRequest request) {
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		model.addAttribute("mchtInfo", mchtInfo);

		MchtDepositExample mchtDepositExample = new MchtDepositExample();
		mchtDepositExample.createCriteria().andMchtIdEqualTo(mchtId)
				.andDelFlagEqualTo("0");
		List<MchtDeposit> mchtDeposits = mchtDepositService
				.selectByExample(mchtDepositExample);
		MchtDeposit mchtDeposit;
		if (mchtDeposits != null && mchtDeposits.size() > 0) {
			mchtDeposit = mchtDeposits.get(0);
		} else {
			mchtDeposit = new MchtDeposit();
		}

		model.addAttribute("mchtDeposit", mchtDeposit);

		MchtTaxInvoiceInfoExample mchtTaxInvoiceInfoExample = new MchtTaxInvoiceInfoExample();
		mchtTaxInvoiceInfoExample.createCriteria().andMchtIdEqualTo(mchtId)
				.andDelFlagEqualTo("0");
		List<MchtTaxInvoiceInfo> mchtTaxInvoiceInfos = mchtTaxInvoiceInfoService
				.selectByExample(mchtTaxInvoiceInfoExample);
		MchtTaxInvoiceInfo mchtTaxInvoiceInfo;
		if (mchtTaxInvoiceInfos != null && mchtTaxInvoiceInfos.size() > 0) {
			mchtTaxInvoiceInfo = mchtTaxInvoiceInfos.get(0);
		} else {
			mchtTaxInvoiceInfo = new MchtTaxInvoiceInfo();
		}

		model.addAttribute("mchtTaxInvoiceInfo", mchtTaxInvoiceInfo);

		model.addAttribute("taxType", DataDicUtil.getStatusList(
				"BU_MCHT_TAX_INVOICE_INFO", "TAX_TYPE"));
		model.addAttribute("auditStatus", DataDicUtil.getStatusList(
				"BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS"));

		MchtBankAccountCustomExample mchtBankAccountExample = new MchtBankAccountCustomExample();
		mchtBankAccountExample.createCriteria().andMchtIdEqualTo(mchtId)
				.andDelFlagEqualTo("0");
		mchtBankAccountExample.setOrderByClause("id desc");
		mchtBankAccountExample.setLimitSize(10);

		List<MchtBankAccountCustom> mchtBankAccounts = mchtBankAccountService
				.selectMchtBankAccountCustomByExample(mchtBankAccountExample);

		if (mchtBankAccounts != null && mchtBankAccounts.size() > 0) {
			model.addAttribute("mchtBankAccount", mchtBankAccounts.get(0));
		} else {
			model.addAttribute("mchtBankAccount", new MchtBankAccount());
		}
		model.addAttribute("isDefaults",
				DataDicUtil.getStatusList("BU_MCHT_BANK_ACCOUNT", "IS_DEFAULT"));
		model.addAttribute("Starstd",
				DataDicUtil.getStatusList("BU_MCHT_BANK_ACCOUNT", "STATUS"));

		BankExample bankExample = new BankExample();
		List<Bank> banks = bankService.selectByExample(bankExample);
		model.addAttribute("banks", banks);

		// 对接人
		MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
		mchtPlatformContactExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andStatusEqualTo("1");
		mchtPlatformContactExample.setOrderByClause("create_date desc");
		List<MchtPlatformContactCustom> mchtPlatformContactCustoms = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
		int staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		// 法务对接人
		for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
			if ("7".equals(mchtPlatformContactCustom.getContactType())) {
				if (mchtPlatformContactCustom.getStaffId().equals(staffID)) {
					model.addAttribute("fwMchtPlatformContact",mchtPlatformContactCustom);
					break;
				}
			}
		}
		//财务对接人
		PlatformContactExample platformContactExample = new PlatformContactExample();
		platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID).andContactTypeEqualTo("5");
		List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExample);
		if (platformContacts!=null && platformContacts.size()>0) {
			model.addAttribute("isCw", true);
			model.addAttribute("cwPlatformContact",platformContacts.get(0));
			MchtPlatformContactExample mpce = new MchtPlatformContactExample();
			mpce.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtIdEqualTo(mchtId).andPlatformContactIdEqualTo(platformContacts.get(0).getId());
			List<MchtPlatformContact> mchtPlatformContacts = mchtPlatformContactService.selectByExample(mpce);
			if(mchtPlatformContacts!=null && mchtPlatformContacts.size()>0){
				model.addAttribute("canConfirm", true);
			}else{
				model.addAttribute("canConfirm", false);
			}
		} else {
			model.addAttribute("canConfirm", false);
		}
		if (mchtInfo.getContractDeposit() != null) {
			//DecimalFormat df = new DecimalFormat("#.##");
			//model.addAttribute("contractDeposit",df.format(mchtInfo.getContractDeposit()));
			// fix buy 12982
			model.addAttribute("contractDeposit",mchtInfo.getContractDeposit());
		}
		
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			model.addAttribute("role107", true);
		}else{
			model.addAttribute("role107", false);
		}
		return "/mcht/mchtFinanceInfoEdit";
	}

	/**
	 * 商家保证金明细
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtDepositDtl.shtml")
	public String mchtDepositDtl(Model model, HttpServletRequest request) {
		Integer depositId = Integer.valueOf(request.getParameter("depositId"));
		model.addAttribute("depositId", depositId);
		return "/mcht/mchtDepositDtl";
	}

	/**
	 * 商家保证金明细数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtDepositDtlList.shtml")
	@ResponseBody
	public Map<String, Object> mchtDepositDtlList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		int depositId = 0;
		try {
			MchtDepositDtlExample mchtDepositDtlExample = new MchtDepositDtlExample();
			MchtDepositDtlExample.Criteria mchtDepositDtlCriteria = mchtDepositDtlExample
					.createCriteria();
			mchtDepositDtlCriteria.andDelFlagEqualTo("0");

			if (!StringUtil.isEmpty(request.getParameter("depositId"))) {
				depositId = Integer.valueOf(request.getParameter("depositId"));
				mchtDepositDtlCriteria.andDepositIdEqualTo(depositId);
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
				mchtDepositDtlCriteria
						.andCreateDateGreaterThanOrEqualTo(dateFormat
								.parse(request.getParameter("date_begin")
										+ " 00:00:00"));
			}

			if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
				mchtDepositDtlCriteria
						.andCreateDateLessThanOrEqualTo(dateFormat
								.parse(request.getParameter("date_end")
										+ " 23:59:59"));
			}

			totalCount = mchtDepositDtlService
					.countByExample(mchtDepositDtlExample);

			paramMap = this.setPageParametersLiger(request, paramMap);
			paramMap.put("depositId", depositId);
			List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService
					.selectMchtDepositDtlList(paramMap);

			resMap.put("Rows", mchtDepositDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 商家保证金添加记录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/addMchtDepositDtl.shtml")
	public String addMchtDepositDtl(Model model, HttpServletRequest request) {
		Integer depositId = Integer.valueOf(request.getParameter("depositId"));
		model.addAttribute("depositId", depositId);
		model.addAttribute("mchtDepositDtlTxnType",
				DataDicUtil.getStatusList("BU_MCHT_DEPOSIT_DTL", "TXN_TYPE"));
		model.addAttribute("mchtDepositDtlTypeSub",
				DataDicUtil.getStatusList("BU_MCHT_DEPOSIT_DTL", "TYPE_SUB"));
		return "/mcht/addMchtDepositDtl";
	}

	@RequestMapping(value = "/mcht/getMchtDepositDtlTypeSubList.shtml")
	@ResponseBody
	public List<SysStatus> getMchtDepositDtlTypeSubList(
			HttpServletRequest request) {
		String txnType = request.getParameter("txnType");

		SysStatusExample sysStatusExample = new SysStatusExample();
		SysStatusExample.Criteria sysStatusCriteria = sysStatusExample
				.createCriteria();
		if (!StringUtil.isEmpty(txnType)) {
			sysStatusCriteria.andTableNameEqualTo("BU_MCHT_DEPOSIT_DTL");
			sysStatusCriteria.andColNameEqualTo("TYPE_SUB");
			sysStatusCriteria.andStatusValueLike(txnType + "%");
			sysStatusExample.setOrderByClause("status_order");
		}
		List<SysStatus> sysStatus = sysStatusService
				.selectByExample(sysStatusExample);
		return sysStatus;
	}

	@RequestMapping(value = "/mcht/addMchtDepositDtlSubmit.shtml")
	@ResponseBody
	public Map<String, Object> addMchtDepositDtlSubmit(Model model,
			HttpServletRequest request, MchtDepositDtl mchtDepositDtl) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer depositId = Integer.valueOf(request.getParameter("depositId"));
		String txnType = request.getParameter("txnType");
		BigDecimal txnAmt = new BigDecimal(request.getParameter("txnAmt"));
		BigDecimal totalAmt = new BigDecimal(0);
		BigDecimal payAmt = new BigDecimal(0);
		BigDecimal unpayAmt = new BigDecimal(0);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			MchtDeposit mchtDeposit = mchtDepositService
					.selectByPrimaryKey(depositId);

			if ("A".equals(txnType)) {
				totalAmt = mchtDeposit.getTotalAmt().add(txnAmt);
				mchtDeposit.setTotalAmt(totalAmt);
			} else {
				payAmt = mchtDeposit.getPayAmt().add(txnAmt);
				mchtDeposit.setPayAmt(payAmt);
			}

			unpayAmt = mchtDeposit.getTotalAmt().subtract(
					mchtDeposit.getPayAmt());
			mchtDeposit.setUnpayAmt(unpayAmt);
			mchtDepositService.updateByPrimaryKeySelective(mchtDeposit);

			mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt());
			mchtDepositDtl.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			mchtDepositDtl.setUpdateDate(new Date());

			mchtDepositDtlService.insertSelective(mchtDepositDtl);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 修改保证内容
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/changeDepositCont.shtml")
	public String changeBondCont(Model model, HttpServletRequest request) {
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		model.addAttribute("mchtId", mchtId);
		model.addAttribute("depositCont", mchtInfo.getDepositContent());
		return "/mcht/changeDepositCont";
	}

	@RequestMapping(value = "/mcht/mchtDepositContSubmit.shtml")
	@ResponseBody
	public Map<String, Object> mchtDepositContSubmit(Model model,
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if (request.getParameter("mchtId") == null) {
				throw new ArgException("用户Id不能为空");
			}
			Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
			MchtInfo mchtInfo = new MchtInfo();
			mchtInfo.setId(mchtId);
			mchtInfo.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(
					request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setDepositContent(request.getParameter("depositCont"));
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 税务开票信息修改与审核
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtTaxInvoiceInfoEdit.shtml")
	public String mchtTaxInvoiceInfoEdit(Model model, HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));

		MchtTaxInvoiceInfo mchtTaxInvoiceInfo = mchtTaxInvoiceInfoService
				.selectByPrimaryKey(id);

		if ("1".equals(mchtTaxInvoiceInfo.getAuditStatus())) {
			MchtTaxInvoiceInfo mchtTaxInvoiceInfo4Update = new MchtTaxInvoiceInfo();
			mchtTaxInvoiceInfo4Update.setId(mchtTaxInvoiceInfo.getId());
			mchtTaxInvoiceInfo4Update.setAuditStatus("2");
			mchtTaxInvoiceInfoService
					.updateByPrimaryKeySelective(mchtTaxInvoiceInfo4Update);
			mchtTaxInvoiceInfo.setAuditStatus("2");
		}

		model.addAttribute("mchtTaxInvoiceInfo", mchtTaxInvoiceInfo);

		MchtInfo mchtInfo = mchtInfoService
				.selectByPrimaryKey(mchtTaxInvoiceInfo.getMchtId());
		model.addAttribute("companyName", mchtInfo.getCompanyName());

		model.addAttribute("taxType", DataDicUtil.getStatusList(
				"BU_MCHT_TAX_INVOICE_INFO", "TAX_TYPE"));
		model.addAttribute("auditStatus", DataDicUtil.getStatusList(
				"BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS"));
		return "/mcht/mchtTaxInvoiceInfoEdit";
	}

	@RequestMapping(value = "/mcht/mchtTaxInvoiceInfoSubmit.shtml")
	@ResponseBody
	public Map<String, Object> mchtTaxInvoiceInfoSubmit(Model model,
			HttpServletRequest request, MchtTaxInvoiceInfo mchtTaxInvoiceInfo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			mchtTaxInvoiceInfo.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			mchtTaxInvoiceInfo.setUpdateDate(new Date());
			mchtTaxInvoiceInfoService
					.updateMchtTaxInvoiceInfo(mchtTaxInvoiceInfo);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 税务开票信息审核
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtTaxInvoiceInfoAudit.shtml")
	public String mchtTaxInvoiceInfoAudit(Model model,
			HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));

		MchtTaxInvoiceInfo mchtTaxInvoiceInfo = mchtTaxInvoiceInfoService
				.selectByPrimaryKey(id);
		model.addAttribute("id", id);
		model.addAttribute("auditStatus", mchtTaxInvoiceInfo.getAuditStatus());
		model.addAttribute("auditRemarks", mchtTaxInvoiceInfo.getAuditRemarks());

		model.addAttribute("auditStatusList", DataDicUtil.getStatusList(
				"BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS"));
		return "/mcht/mchtTaxInvoiceInfoAudit";
	}

	/**
	 * 税务信息审核首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtTaxCheck.shtml")
	public ModelAndView mchtTaxCheck(HttpServletRequest request) {
		String rtPage = "/mcht/mchtTaxCheck";
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			resMap.put("taxType", statusService.querytStatusList(
					"BU_MCHT_TAX_INVOICE_INFO", "TAX_TYPE"));
			resMap.put("auditStatus", statusService.querytStatusList(
					"BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 税务信息审核列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtTaxcheckData.shtml")
	@ResponseBody
	public Map<String, Object> mchtTaxcheckData(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		try {
			paramMap = this.setPageParametersLiger(request, paramMap);
			totalCount = mchtTaxInvoiceInfoService
					.mchtTaxInvoiceInfoCustomListCount(paramMap);
			List<MchtTaxInvoiceInfoCustom> mchtDepositCustoms = mchtTaxInvoiceInfoService
					.mchtTaxInvoiceInfoCustomList(paramMap);

			resMap.put("Rows", mchtDepositCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 保证金汇总
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/deposit.shtml")
	public String deposit(Model model, HttpServletRequest request) {
		return "/mcht/deposit";
	}

	/**
	 * 保证金汇总
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/depositList.shtml")
	@ResponseBody
	public Map<String, Object> depositList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		try {
			paramMap = this.setPageParametersLiger(request, paramMap);
			totalCount = mchtDepositService.mchtDepositListCount(paramMap);
			List<MchtDepositCustom> mchtDepositCustoms = mchtDepositService
					.selectMchtDepositList(paramMap);

			resMap.put("Rows", mchtDepositCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 保证金明细
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/depositDtl.shtml")
	public String depositDtl(Model model, HttpServletRequest request) {
		return "/mcht/depositDtl";
	}

	/**
	 * 保证金明细数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/depositDtlList.shtml")
	@ResponseBody
	public Map<String, Object> depositDtlList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		try {
			paramMap = this.setPageParametersLiger(request, paramMap);
			totalCount = mchtDepositDtlService
					.mchtDepositDtlListCount(paramMap);
			List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService
					.selectMchtDepositDtlList(paramMap);

			resMap.put("Rows", mchtDepositDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 税务信息更新审核首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtTaxUpdateCheck.shtml")
	public ModelAndView mchtTaxUpdateCheck(HttpServletRequest request) {
		String rtPage = "/mcht/mchtTaxUpdateCheck";
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			resMap.put("taxType", statusService.querytStatusList(
					"BU_MCHT_TAX_INVOICE_INFO", "TAX_TYPE"));
			resMap.put("auditStatus", statusService.querytStatusList(
					"BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 税务信息更新审核列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtTaxUpdatecheckData.shtml")
	@ResponseBody
	public Map<String, Object> mchtTaxUpdatecheckData(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		try {
			paramMap = this.setPageParametersLiger(request, paramMap);

			if ("1".equals(request.getParameter("is_my_audit"))) {
				PlatformContactExample platformContactExample = new PlatformContactExample();
				platformContactExample
						.createCriteria()
						.andDelFlagEqualTo("0")
						.andStaffIdEqualTo(
								Integer.valueOf(this.getSessionStaffBean(
										request).getStaffID()))
						.andContactTypeEqualTo("5").andStatusEqualTo("0");
				List<PlatformContact> platformContacts = platformContactService
						.selectByExample(platformContactExample);
				if (platformContacts != null && platformContacts.size() > 0) {
					paramMap.put("platformContactId", platformContacts.get(0)
							.getId());
				} else {
					paramMap.put("platformContactId", "99999999999");
				}
			}
			paramMap.put(
					"andWhereClause",
					"  EXISTS(select id from bu_mcht_info mi where mi.id=a.mcht_id and mi.del_flag='0' and mi.status in ('1','2'))");
			totalCount = mchtTaxInvoiceInfoChgService
					.mchtTaxInvoiceInfoChgCustomListCount(paramMap);
			List<MchtTaxInvoiceInfoChgCustom> mchtDepositCustoms = mchtTaxInvoiceInfoChgService
					.mchtTaxInvoiceInfoChgCustomList(paramMap);

			resMap.put("Rows", mchtDepositCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 税务开票信息更新审核
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtTaxInvoiceInfoChgAudit.shtml")
	public String mchtTaxInvoiceInfoChgAudit(Model model,
			HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		Integer type = Integer.valueOf(request.getParameter("type"));

		MchtTaxInvoiceInfoChg mchtTaxInvoiceInfoChg = mchtTaxInvoiceInfoChgService
				.selectByPrimaryKey(id);
		model.addAttribute("mchtTaxInvoiceInfoChg", mchtTaxInvoiceInfoChg);

		MchtInfo mchtInfoNew = mchtInfoService
				.selectByPrimaryKey(mchtTaxInvoiceInfoChg.getMchtId());
		model.addAttribute("companyNameNew", mchtInfoNew.getCompanyName());

		MchtTaxInvoiceInfo mchtTaxInvoiceInfo = mchtTaxInvoiceInfoService
				.selectByPrimaryKey(mchtTaxInvoiceInfoChg
						.getMchtTaxInvoiceInfoId());
		model.addAttribute("mchtTaxInvoiceInfo", mchtTaxInvoiceInfo);

		MchtInfo mchtInfoOld = mchtInfoService
				.selectByPrimaryKey(mchtTaxInvoiceInfo.getMchtId());
		model.addAttribute("companyNameOld", mchtInfoOld.getCompanyName());

		model.addAttribute("taxType", DataDicUtil.getStatusList(
				"BU_MCHT_TAX_INVOICE_INFO", "TAX_TYPE"));
		model.addAttribute("auditStatus", DataDicUtil.getStatusList(
				"BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS"));
		model.addAttribute("type", type);

		return "/mcht/mchtTaxInvoiceInfoChgAudit";
	}

	@RequestMapping(value = "/mcht/mchtTaxInvoiceInfoChgSubmit.shtml")
	@ResponseBody
	public Map<String, Object> mchtTaxInvoiceInfoChgSubmit(
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request)
					.getStaffID());
			Integer id = Integer.valueOf(request.getParameter("id"));
			Integer chgId = Integer.valueOf(request.getParameter("chgId"));
			String auditStatus = request.getParameter("auditStatus");
			String auditRemarks = request.getParameter("auditRemarks");

			MchtTaxInvoiceInfoChg mchtTaxInvoiceInfoChg = new MchtTaxInvoiceInfoChg();
			mchtTaxInvoiceInfoChg = mchtTaxInvoiceInfoChgService
					.selectByPrimaryKey(chgId);

			mchtTaxInvoiceInfoChg.setAuditStatus(auditStatus);
			mchtTaxInvoiceInfoChg.setAuditRemarks(auditRemarks);
			mchtTaxInvoiceInfoChg.setUpdateBy(staffId);
			mchtTaxInvoiceInfoChg.setUpdateDate(new Date());
			mchtTaxInvoiceInfoChgService
					.updateByPrimaryKey(mchtTaxInvoiceInfoChg);

			if ("3".equals(auditStatus)) {
				MchtTaxInvoiceInfo mchtTaxInvoiceInfo = new MchtTaxInvoiceInfo();
				mchtTaxInvoiceInfo.setId(id);
				mchtTaxInvoiceInfo.setTaxType(mchtTaxInvoiceInfoChg
						.getTaxType());
				mchtTaxInvoiceInfo.setTaxNumber(mchtTaxInvoiceInfoChg
						.getTaxNumber());
				mchtTaxInvoiceInfo.setAddress(mchtTaxInvoiceInfoChg
						.getAddress());
				mchtTaxInvoiceInfo.setTel(mchtTaxInvoiceInfoChg.getTel());
				mchtTaxInvoiceInfo.setDepositBank(mchtTaxInvoiceInfoChg
						.getDepositBank());
				mchtTaxInvoiceInfo.setAccountNumber(mchtTaxInvoiceInfoChg
						.getAccountNumber());
				mchtTaxInvoiceInfo.setUpdateBy(mchtTaxInvoiceInfoChg
						.getUpdateBy());
				mchtTaxInvoiceInfo.setUpdateDate(mchtTaxInvoiceInfoChg
						.getUpdateDate());
				mchtTaxInvoiceInfoService
						.updateByPrimaryKeySelective(mchtTaxInvoiceInfo);
			}

		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	// 商家联系人首页
	@RequestMapping(value = "/mcht/mchtContact.shtml")
	public ModelAndView mchtContactIndex(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mcht/mchtcontactindex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtId = paramMap.get("mchtId").toString();
		String contactType = paramMap.get("contactType") == null ? ""
				: paramMap.get("contactType").toString();
		resMap.put("MCHTID", mchtId);
		resMap.put("contactType", contactType);
		return new ModelAndView(rtPage, resMap);
	}

	// 商家联系人数据
	@RequestMapping(value = "/mcht/mcdata.shtml")
	@ResponseBody
	public Map<String, Object> getmchconData(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap, Page page) {

		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			List<String> contactTypes = new ArrayList<String>();
			StaffMchtcontactPermissionExample e = new StaffMchtcontactPermissionExample();
			e.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			List<StaffMchtcontactPermission> staffMchtcontactPermissions = staffMchtcontactPermissionService.selectByExample(e);
			if(staffMchtcontactPermissions==null || staffMchtcontactPermissions.size()==0){
				return resMap;
			}else{
				for(StaffMchtcontactPermission staffMchtcontactPermission:staffMchtcontactPermissions){
					contactTypes.add(staffMchtcontactPermission.getMchtContactType());
				}
			}
			if(contactTypes == null || contactTypes.size() == 0){
				return resMap;
			}
			MchtContactCustomExample mchtContactCustomExample=new MchtContactCustomExample();
			MchtContactCustomExample.MchtContactCustomCriteria createCriteria=mchtContactCustomExample.createCriteria();
			createCriteria.andDelFlagEqualTo("0");
			createCriteria.andMchtIdEqualTo(Integer.valueOf(paramMap.get("mchtId").toString()));
			createCriteria.andContactTypeIn(contactTypes);
			String contactTypeStr = paramMap.get("contactType") == null ? "":paramMap.get("contactType").toString();
			if (!StringUtil.isEmpty(contactTypeStr)) {
				List<String> contactTypeList = new ArrayList<String>();
				String[] typeStr = contactTypeStr.split(",");
				for (String str : typeStr) {
					contactTypeList.add(str);
				}
				createCriteria.andContactTypeIn(contactTypeList);
			}
			mchtContactCustomExample.setLimitStart(page.getLimitStart());
			mchtContactCustomExample.setLimitSize(page.getLimitSize());
			mchtContactCustomExample.setOrderByClause(" is_primary desc,id desc");
			List<MchContactCustom> mchContactCustomList = mchtContactService.selectByCustomExample(mchtContactCustomExample);
			resMap.put("Rows", mchContactCustomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	// 商家平台对接人首页
	@RequestMapping(value = "/mcht/mchtplatformcontact.shtml")
	public ModelAndView mchtplatformcontactIndex(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mcht/mchtpcindex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtId = paramMap.get("mchtId").toString();
		resMap.put("MCHTID", mchtId);
		return new ModelAndView(rtPage, resMap);
	}

	// 商家平台对接人数据
	@RequestMapping(value = "/mcht/mcpcontdata.shtml")
	@ResponseBody
	public Map<String, Object> getMcpcontData(HttpServletRequest request,
			Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int mchtId = Integer.valueOf(request.getParameter("mchtId"));
		int totalCount = 0;

		try {
			MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
			MchtPlatformContactExample.Criteria mchtPlatformContactCriteria = mchtPlatformContactExample
					.createCriteria();
			mchtPlatformContactCriteria.andDelFlagEqualTo("0")
					.andMchtIdEqualTo(mchtId);
			mchtPlatformContactExample
					.setOrderByClause("a.status,a.create_date desc");

			totalCount = mchtPlatformContactService
					.countMchtPlatformContactCustomByExample(mchtPlatformContactExample);

			mchtPlatformContactExample.setLimitStart(page.getLimitStart());
			mchtPlatformContactExample.setLimitSize(page.getLimitSize());
			List<MchtPlatformContactCustom> mchtPlatformContactCustoms = mchtPlatformContactService
					.selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
			resMap.put("Rows", mchtPlatformContactCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	// 添加平台对接人中间表数据
	@RequestMapping(value = "/mcht/addmchtplatformcontact.shtml")
	public ModelAndView addmchtplatformcontactindex(HttpServletRequest request,
			@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mcht/addmchtplatconindex";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("MCHTID", paramMap.get("mchtId"));
		return new ModelAndView(rtPage, resMap);
	}

	// 获取平台对接人
	@RequestMapping(value = "/mcht/getplatformcontact.shtml")
	@ResponseBody
	public Map<String, Object> getplatformcontact(HttpServletRequest request,
			Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;

		int mchtId = Integer.valueOf(request.getParameter("mchtId"));
		PlatformContactCustomExample platformContactExample = new PlatformContactCustomExample();
		PlatformContactCustomExample.PlatformContactCustomCriteria createCriteria = platformContactExample
				.createCriteria();
		createCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0");
		createCriteria.andMchtHasNoEqualTo(mchtId);
		createCriteria.andContactTypeHasNoEqualTo(mchtId);

		Object jsonvalue = request.getParameter("condition");
		if (jsonvalue != null) {
			JSONArray jsonArray = JSONArray.fromObject(jsonvalue);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String searchvalue = jsonObject.getString("value");
				String searchitem = jsonObject.getString("field");
				if (!StringUtil.isEmpty(searchvalue)) {
					if (searchitem.equals("contactName")) {
						createCriteria.andContactNameLike("%" + searchvalue
								+ "%");
					} else if (searchitem.equals("contactType")) {
						createCriteria.andContactTypeEqualTo(searchvalue);
					}
				}
			}
		}

		totalCount = platformContactService
				.countPlatformContactCustomByExample(platformContactExample);
		platformContactExample.setLimitStart(page.getLimitStart());
		platformContactExample.setLimitSize(page.getLimitSize());

		// 对接人数据
		List<PlatformContactCustom> platformContactCustoms = platformContactService
				.selectPlatformContactCustomByExample(platformContactExample);

		resMap.put("Rows", platformContactCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}

	// 保存添加
	@RequestMapping(value = "/mcht/saveplatformcontact.shtml")
	@ResponseBody
	public ModelAndView saveplatformcontact(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;

		int platformContactId = Integer.valueOf(request
				.getParameter("platformContactId"));
		int mchtId = Integer.valueOf(request.getParameter("mchtId"));
		int staffId = Integer.valueOf(this.getSessionStaffBean(request)
				.getStaffID());

		MchtPlatformContact mchContact = new MchtPlatformContact();
		mchContact.setMchtId(Integer.valueOf(mchtId));
		mchContact.setPlatformContactId(platformContactId);
		mchContact.setStatus("1");
		mchContact.setCreateBy(staffId);
		mchContact.setCreateDate(new Date());
		mchContact.setUpdateBy(staffId);
		mchContact.setUpdateDate(new Date());

		try {
			mchtPlatformContactService.insertSelective(mchContact);
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

	// 获取到对接人类别
	@RequestMapping(value = "/mcht/getcontacttype.shtml")
	@ResponseBody
	public List<SysStatus> getContactType(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap) {
		// 联系人类型
		List<SysStatus> platformType = DataDicUtil.getTableStatus(
				"BU_PLATFORM_CONTACT", "CONTACT_TYPE");
		return platformType;
	}

	/**
	 * 更改平台对接人的分配状态(开启变更为取消)
	 * 
	 * @param time
	 * @return
	 */
	@RequestMapping(value = "/mcht/changemchtplatconstatus.shtml")
	@ResponseBody
	public Map<String, Object> changemchtplatconstatus(
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int platformContactId = Integer.valueOf(request
				.getParameter("platformContactId"));
		int mchtId = Integer.valueOf(request.getParameter("mchtId"));

		MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
		MchtPlatformContactExample.Criteria mchtPlatformContactCriteria = mchtPlatformContactExample
				.createCriteria();
		mchtPlatformContactCriteria.andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId)
				.andPlatformContactIdEqualTo(platformContactId)
				.andStatusEqualTo("1");

		MchtPlatformContact mchtPlatformContact = new MchtPlatformContact();
		mchtPlatformContact.setUpdateBy(Integer.valueOf(this
				.getSessionStaffBean(request).getStaffID()));
		mchtPlatformContact.setUpdateDate(new Date());
		mchtPlatformContact.setStatus("2");

		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			mchtPlatformContactService.updateByExampleSelective(
					mchtPlatformContact, mchtPlatformContactExample);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	@RequestMapping(value = "/mcht/mchtmsgcheck.shtml")
	public ModelAndView mchtmsgcheckindex(HttpServletRequest request) {
		String resPage = "/mcht/mchtmsgcheckindex";
		Map<String, Object> resMap = new HashMap<String, Object>();

		// 公司状态
		List<SysStatus> isperfectList = DataDicUtil.getTableStatus(
				"BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT");
		// 模式
		List<SysStatus> mchttypeList = DataDicUtil.getTableStatus(
				"BU_MCHT_INFO", "MCHT_TYPE");
		// 合作状态
		List<SysStatus> statusList = DataDicUtil.getTableStatus("BU_MCHT_INFO",
				"STATUS");
		// 店铺状态
		List<SysStatus> shopNameList = DataDicUtil.getTableStatus(
				"BU_MCHT_INFO", "SHOP_NAME_AUDIT_STATUS");
		// 税务状态
		List<SysStatus> auditStatusList = DataDicUtil.getTableStatus(
				"BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS");
		// 银行状态
		List<SysStatus> accountStatusList = DataDicUtil.getTableStatus(
				"BU_MCHT_BANK_ACCOUNT", "STATUS");

		resMap.put("IS_COMPANY_INF_PERFECT", isperfectList);
		resMap.put("MCHT_TYPE", mchttypeList);
		resMap.put("STATUS", statusList);
		resMap.put("SHOP_NAME_AUDIT_STATUS", shopNameList);
		resMap.put("AUDIT_STATUS", auditStatusList);
		resMap.put("ACCOUNT_STATUS", accountStatusList);
		return new ModelAndView(resPage, resMap);
	}

	@RequestMapping(value = "/mcht/msgcheckdata.shtml")
	@ResponseBody
	public Map<String, Object> getmsglist(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> pHashMap, Page page) {
		pHashMap = this.setPageParametersLiger(request, pHashMap);
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		pHashMap.put("pagesize", 100);

		List<Map<String, Object>> selectByExample = mchtInfoService
				.selectMchtInfoAndPicCustomById(pHashMap);
		totalCount = mchtInfoService
				.selectCountMchtInfoAndPicCustomById(pHashMap);

		// 公司状态
		List<SysStatus> isperfectList = DataDicUtil.getTableStatus(
				"BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT");
		HashMap<String, String> isperfecthash = new HashMap<String, String>();
		// 模式
		List<SysStatus> mchttypeList = DataDicUtil.getTableStatus(
				"BU_MCHT_INFO", "MCHT_TYPE");
		HashMap<String, String> mchttypehash = new HashMap<String, String>();
		// 合作状态
		List<SysStatus> statusList = DataDicUtil.getTableStatus("BU_MCHT_INFO",
				"STATUS");
		HashMap<String, String> statushash = new HashMap<String, String>();
		// 店铺状态
		List<SysStatus> shopNameList = DataDicUtil.getTableStatus(
				"BU_MCHT_INFO", "SHOP_NAME_AUDIT_STATUS");
		HashMap<String, String> shopNamehash = new HashMap<String, String>();
		// 税务状态
		List<SysStatus> auditStatusList = DataDicUtil.getTableStatus(
				"BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS");
		HashMap<String, String> auditStatushash = new HashMap<String, String>();
		// 银行状态
		List<SysStatus> accountStatusList = DataDicUtil.getTableStatus(
				"BU_MCHT_BANK_ACCOUNT", "STATUS");
		HashMap<String, String> accountStatushash = new HashMap<String, String>();

		for (int i = 0; i < isperfectList.size(); i++) {
			SysStatus sysStatus = isperfectList.get(i);
			isperfecthash.put(sysStatus.getStatusValue(),
					sysStatus.getStatusDesc());
		}
		for (int i = 0; i < mchttypeList.size(); i++) {
			SysStatus sysStatus = mchttypeList.get(i);
			mchttypehash.put(sysStatus.getStatusValue(),
					sysStatus.getStatusDesc());
		}
		for (int i = 0; i < statusList.size(); i++) {
			SysStatus sysStatus = statusList.get(i);
			statushash.put(sysStatus.getStatusValue(),
					sysStatus.getStatusDesc());
		}
		for (int i = 0; i < shopNameList.size(); i++) {
			SysStatus sysStatus = shopNameList.get(i);
			shopNamehash.put(sysStatus.getStatusValue(),
					sysStatus.getStatusDesc());
		}
		for (int i = 0; i < auditStatusList.size(); i++) {
			SysStatus sysStatus = auditStatusList.get(i);
			auditStatushash.put(sysStatus.getStatusValue(),
					sysStatus.getStatusDesc());
		}
		for (int i = 0; i < accountStatusList.size(); i++) {
			SysStatus sysStatus = accountStatusList.get(i);
			accountStatushash.put(sysStatus.getStatusValue(),
					sysStatus.getStatusDesc());
		}
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria pcec = pce.createCriteria();
		pcec.andDelFlagEqualTo("0");
		pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(
				request).getStaffID()));
		pcec.andStatusEqualTo("0");
		List<PlatformContact> platformContacts = platformContactService
				.selectByExample(pce);
		boolean isCw = false;
		boolean isFw = false;
		if (platformContacts != null && platformContacts.size() > 0) {
			for (PlatformContact platformContact : platformContacts) {
				if (platformContact.getContactType().equals("5")) {// 财务
					isCw = true;
				} else if (platformContact.getContactType().equals("7")) {// 法务
					isFw = true;
				}
			}
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < selectByExample.size(); i++) {
			Map<String, Object> map = selectByExample.get(i);
			map.put("is_company_inf_perfect",
					isperfecthash.get(map.get("is_company_inf_perfect")));
			map.put("mcht_type", mchttypehash.get(map.get("mcht_type")));
			map.put("status", statushash.get(map.get("status")));
			map.put("shop_name_audit_status",
					shopNamehash.get(map.get("shop_name_audit_status")));
			map.put("auditStatus", auditStatushash.get(map.get("auditStatus")));
			map.put("accountStatus",
					accountStatushash.get(map.get("accountStatus")));
			map.put("isCw", isCw);
			map.put("isFw", isFw);
			listMap.add(map);
		}

		resMap.put("Rows", listMap);
		resMap.put("Total", totalCount);
		return resMap;
	}
	//招商确认品牌信息
	@RequestMapping(value = "/mcht/zsConfirmMchtProductBrandList.shtml")
	public ModelAndView zsConfirmMchtProductBrandList(HttpServletRequest request) {
		String rePage = "/mcht/zsConfirmMchtProductBrandList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SysStatus> mList = new ArrayList<SysStatus>();
		SysStatus sysItem = new SysStatus();
		// 审核状态
		List<SysStatus> aptitudetypeList = DataDicUtil.getTableStatus("BU_MCHT_PRODUCT_BRAND", "AUDIT_STATUS");
		// 资质类型
		List<SysStatus> aptitudeList = DataDicUtil.getTableStatus("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE");

		for (int i = 0; i < aptitudetypeList.size(); i++) {
			SysStatus sysStatus = aptitudetypeList.get(i);
			if (sysStatus.getStatusValue().equals("1")) {
				sysItem.setStatusDesc(sysStatus.getStatusDesc());
				sysItem.setStatusValue(sysStatus.getStatusValue());
			} else {
				mList.add(sysStatus);
			}
		}

		resMap.put("statusDescList",DataDicUtil.getTableStatus("BU_MCHT_PRODUCT_BRAND", "STATUS"));
		resMap.put("AUDIT_STATUS", mList);
		resMap.put("APTITUDE_TYPE", aptitudeList);
		resMap.put("Item_desc", sysItem.getStatusDesc());
		resMap.put("Item_value", sysItem.getStatusValue());

		PlatformContactExample platformContactExample = new PlatformContactExample();
		platformContactExample
				.createCriteria()
				.andDelFlagEqualTo("0")
				.andStaffIdEqualTo(
						Integer.valueOf(this.getSessionStaffBean(request)
								.getStaffID())).andContactTypeEqualTo("7")
				.andStatusEqualTo("0");
		if (platformContactService.countByExample(platformContactExample) > 0) {
			resMap.put("isCanGet", true);
		} else {
			resMap.put("isCanGet", false);
		}
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		resMap.put("isManagement", isManagement);
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria pcec = pce.createCriteria();
		pcec.andDelFlagEqualTo("0");
		pcec.andContactTypeEqualTo("1");// 招商对接人
		pcec.andStatusEqualTo("0");
		pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
		if (platformContacts != null && platformContacts.size() > 0) {// 是招商对接人
			resMap.put("myContactId", platformContacts.get(0).getId());
			PlatformContactExample e = new PlatformContactExample();
			PlatformContactExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("0");
			c.andAssistantIdEqualTo(platformContacts.get(0).getId());
			List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
			resMap.put("platformContacts", platformContactList);
			resMap.put("isAssistant", true);
		} else {
			PlatformContactCustomExample zsPlatformExample = new PlatformContactCustomExample();
			zsPlatformExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
			List<PlatformContactCustom> zsPlatformContactCustoms = platformContactService.selectPlatformContactCustomByExample(zsPlatformExample);
			resMap.put("zsPlatformContactCustomList",zsPlatformContactCustoms);
			resMap.put("isAssistant", false);
		}
		return new ModelAndView(rePage, resMap);
	}

	@RequestMapping(value = "/mcht/zsConfirmMchtProductBrandData.shtml")
	@ResponseBody
	public Map<String, Object> zsConfirmMchtProductBrandData(HttpServletRequest request,
			@RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;

		paramMap = this.setPageParametersLiger(request, paramMap);
		paramMap.put("brandSource", "2"); // 追加品牌
		paramMap.put("mchtStatus", "1,2"); // 合作状态 in （正常、暂停）
		if ("1".equals(request.getParameter("is_my_audit"))) {
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(
							Integer.valueOf(this.getSessionStaffBean(request)
									.getStaffID())).andContactTypeEqualTo("7")
					.andStatusEqualTo("0");
			List<PlatformContact> platformContacts = platformContactService
					.selectByExample(platformContactExample);
			if (platformContacts != null && platformContacts.size() > 0) {
				paramMap.put("platformContactId", platformContacts.get(0).getId());
			} else {
				paramMap.put("platformContactId", "99999999999");
			}
		}
		if(!StringUtils.isEmpty(request.getParameter("zs_commit_audit_date_begin"))){
			paramMap.put("zs_commit_audit_date_begin", request.getParameter("zs_commit_audit_date_begin")+" 00:00:00");
		}
		if(!StringUtils.isEmpty(request.getParameter("zs_commit_audit_date_end"))){
			paramMap.put("zs_commit_audit_date_end", request.getParameter("zs_commit_audit_date_end")+" 23:59:59");
		}
		List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.getMchtProductbrandInfoCustom(paramMap);
		
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		for (MchtProductBrandCustom mchtProductBrandCustom : mchtProductBrandCustoms) {
				PlatformContactExample platformContactExamples = new PlatformContactExample();
				platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId).andContactTypeEqualTo("1");
				List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExamples);

				if (platformContacts != null && platformContacts.size() > 0) {			
						Integer assistantId = platformContacts.get(0).getId();
						PlatformContactExample platformContactExample = new PlatformContactExample();
						platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(assistantId).andContactTypeEqualTo("1");
						List<PlatformContact> platformAssistantContact = platformContactService.selectByExample(platformContactExample);
                         if (platformAssistantContact!=null && platformAssistantContact.size()>0) {
                        	 mchtProductBrandCustom.setAssistantContact("1");
						 }
						/*for (PlatformContact platformcontacts : platformAssistantContact) {
							if (platformcontacts.getStaffId().equals(staffId)) {
								mchtProductBrandCustom.setAssistantContact("1");
							}
						}*/

				}
			 
		}
		totalCount = mchtProductBrandService.getMchtBrandCustomCount(paramMap);
		resMap.put("Rows", mchtProductBrandCustoms);
		resMap.put("Total", totalCount);

		return resMap;
	}
	
	//法务品牌信息审核 2018年9月7日17:15:58
	@RequestMapping(value = "/mcht/mchprandcheck.shtml")
	public ModelAndView mchprandcheck(HttpServletRequest request) {
		String rePage = "/mcht/mchprandcheck";
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SysStatus> mList = new ArrayList<SysStatus>();
		SysStatus sysItem = new SysStatus();
		// 审核状态
		List<SysStatus> aptitudetypeList = DataDicUtil.getTableStatus(
				"BU_MCHT_PRODUCT_BRAND", "AUDIT_STATUS");
		// 资质类型
		List<SysStatus> aptitudeList = DataDicUtil.getTableStatus(
				"BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE");

		for (int i = 0; i < aptitudetypeList.size(); i++) {
			SysStatus sysStatus = aptitudetypeList.get(i);
			if (sysStatus.getStatusValue().equals("1")) {
				sysItem.setStatusDesc(sysStatus.getStatusDesc());
				sysItem.setStatusValue(sysStatus.getStatusValue());
			} else {
				mList.add(sysStatus);
			}
		}

		resMap.put("statusDescList",
				DataDicUtil.getTableStatus("BU_MCHT_PRODUCT_BRAND", "STATUS"));
		resMap.put("AUDIT_STATUS", mList);
		resMap.put("APTITUDE_TYPE", aptitudeList);
		resMap.put("Item_desc", sysItem.getStatusDesc());
		resMap.put("Item_value", sysItem.getStatusValue());

		PlatformContactExample platformContactExample = new PlatformContactExample();
		platformContactExample
				.createCriteria()
				.andDelFlagEqualTo("0")
				.andStaffIdEqualTo(
						Integer.valueOf(this.getSessionStaffBean(request)
								.getStaffID())).andContactTypeEqualTo("7")
				.andStatusEqualTo("0");

		if (platformContactService.countByExample(platformContactExample) > 0) {
			resMap.put("isCanGet", true);
		} else {
			resMap.put("isCanGet", false);
		}

		return new ModelAndView(rePage, resMap);
	}
	
	//法务品牌信息审核 2018年9月7日17:16:14
	@RequestMapping(value = "/mcht/mchprandcheck/list.shtml")
	@ResponseBody
	public Map<String, Object> getMessage(HttpServletRequest request,
			@RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		
		paramMap = this.setPageParametersLiger(request, paramMap);
		paramMap.put("zsAuditStatus", "2"); // 招商确认审核通过
		paramMap.put("brandSource", "2"); // 追加品牌
		paramMap.put("mchtStatus", "1,2"); // 合作状态 in （正常、暂停）
		if ("1".equals(request.getParameter("is_my_audit"))) {
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(
							Integer.valueOf(this.getSessionStaffBean(request)
									.getStaffID())).andContactTypeEqualTo("7")
					.andStatusEqualTo("0");
			List<PlatformContact> platformContacts = platformContactService
					.selectByExample(platformContactExample);
			if (platformContacts != null && platformContacts.size() > 0) {
				paramMap.put("platformContactId", platformContacts.get(0)
						.getId());
			} else {
				paramMap.put("platformContactId", "99999999999");
			}
		}
		if(!StringUtils.isEmpty(request.getParameter("commit_audit_date_begin"))){
			paramMap.put("commit_audit_date_begin", request.getParameter("commit_audit_date_begin")+" 00:00:00");
		}
		if(!StringUtils.isEmpty(request.getParameter("commit_audit_date_end"))){
			paramMap.put("commit_audit_date_end", request.getParameter("commit_audit_date_end")+" 23:59:59");
		}
		List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.getMchtProductbrandInfoCustom(paramMap);
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		for (MchtProductBrandCustom mchtProductBrandCustom : mchtProductBrandCustoms) {
	
				PlatformContactExample platformContactExamples = new PlatformContactExample();
				platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId).andContactTypeEqualTo("7");
				List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExamples);

				if (platformContacts != null && platformContacts.size() > 0) {			
						Integer assistantId = platformContacts.get(0).getId();
						PlatformContactExample platformContactExample = new PlatformContactExample();
						platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(assistantId).andContactTypeEqualTo("7");
						List<PlatformContact> platformAssistantContact = platformContactService.selectByExample(platformContactExample);
						if (platformAssistantContact!=null && platformAssistantContact.size()>0) {
                       	    mchtProductBrandCustom.setAssistantContact("7");
						 }
						/*for (PlatformContact platformcontacts : platformAssistantContact) {
							if (platformcontacts.getStaffId().equals(staffId)) {
								mchtProductBrandCustom.setAssistantContact("7");
							}
						}*/

				}
			 
		}
		totalCount = mchtProductBrandService.getMchtBrandCustomCount(paramMap);
		resMap.put("Rows", mchtProductBrandCustoms);
		resMap.put("Total", totalCount);

		return resMap;
	}

	//品牌待开通
	@RequestMapping(value = "/mcht/mchtProductBrandWaitOpenList.shtml")
	public ModelAndView mchtProductBrandWaitOpenList(HttpServletRequest request) {
		String rePage = "/mcht/mchtProductBrandWaitOpenList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SysStatus> mList = new ArrayList<SysStatus>();
		SysStatus sysItem = new SysStatus();
		// 审核状态
		List<SysStatus> aptitudetypeList = DataDicUtil.getTableStatus("BU_MCHT_PRODUCT_BRAND", "AUDIT_STATUS");
		// 资质类型
		List<SysStatus> aptitudeList = DataDicUtil.getTableStatus("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE");

		for (int i = 0; i < aptitudetypeList.size(); i++) {
			SysStatus sysStatus = aptitudetypeList.get(i);
			if (sysStatus.getStatusValue().equals("1")) {
				sysItem.setStatusDesc(sysStatus.getStatusDesc());
				sysItem.setStatusValue(sysStatus.getStatusValue());
			} else {
				mList.add(sysStatus);
			}
		}
		resMap.put("statusDescList",DataDicUtil.getTableStatus("BU_MCHT_PRODUCT_BRAND", "STATUS"));
		resMap.put("AUDIT_STATUS", mList);
		resMap.put("APTITUDE_TYPE", aptitudeList);
		resMap.put("Item_desc", sysItem.getStatusDesc());
		resMap.put("Item_value", sysItem.getStatusValue());

		PlatformContactExample platformContactExample = new PlatformContactExample();
		platformContactExample.createCriteria().andDelFlagEqualTo("0")
				.andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID())).andContactTypeEqualTo("7")
				.andStatusEqualTo("0");
		if (platformContactService.countByExample(platformContactExample) > 0) {
			resMap.put("isCanGet", true);
		} else {
			resMap.put("isCanGet", false);
		}

		return new ModelAndView(rePage, resMap);
	}

	@RequestMapping(value = "/mcht/mchtProductBrandWaitOpenData.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductBrandWaitOpenData(HttpServletRequest request,	@RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;

		paramMap = this.setPageParametersLiger(request, paramMap);
		paramMap.put("brandSource", "2"); // 追加品牌
		paramMap.put("mchtInfoStatus", "1"); // 合作状态 正常
		paramMap.put("zsAuditStatus", "2"); //招商审核通过
		paramMap.put("AUDITSTATUS", "3"); //法务审核通过
		paramMap.put("status", "0"); //0.申请中
		
		if ("1".equals(request.getParameter("is_my_audit"))) {
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(
							Integer.valueOf(this.getSessionStaffBean(request)
									.getStaffID())).andContactTypeEqualTo("7")
					.andStatusEqualTo("0");
			List<PlatformContact> platformContacts = platformContactService
					.selectByExample(platformContactExample);
			if (platformContacts != null && platformContacts.size() > 0) {
				paramMap.put("platformContactId", platformContacts.get(0).getId());
			} else {
				paramMap.put("platformContactId", "99999999999");
			}
		}
		List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.getMchtProductbrandInfoCustom(paramMap);
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		for (MchtProductBrandCustom mchtProductBrandCustom : mchtProductBrandCustoms) {
	
				PlatformContactExample platformContactExamples = new PlatformContactExample();
				platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffId).andContactTypeEqualTo("2");
				List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExamples);

				if (platformContacts != null && platformContacts.size() > 0) {			
						Integer assistantId = platformContacts.get(0).getId();
						PlatformContactExample platformContactExample = new PlatformContactExample();
						platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(assistantId).andContactTypeEqualTo("2");
						List<PlatformContact> platformAssistantContact = platformContactService.selectByExample(platformContactExample);
						if (platformAssistantContact!=null && platformAssistantContact.size()>0) {
                       	    mchtProductBrandCustom.setAssistantContact("2");
						 }
						/*for (PlatformContact platformcontacts : platformAssistantContact) {
							if (platformcontacts.getStaffId().equals(staffId)) {
								mchtProductBrandCustom.setAssistantContact("2");
							}
						}*/

				}
			 
		}
		totalCount = mchtProductBrandService.getMchtBrandCustomCount(paramMap);
		resMap.put("Rows", mchtProductBrandCustoms);
		resMap.put("Total", totalCount);

		return resMap;
	}
	
	/**
	 * 店铺待开通--品牌信息--查看
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/viewMchtProductBrand.shtml")
	public String viewMchtProductBrand(Model model, HttpServletRequest request) {
		ProductBrand productBrand = new ProductBrand();
		String mchtProductBrandId = request.getParameter("mchtProductBrandId");
		MchtProductBrandCustom mchtProductBrand = mchtProductBrandService.getMchtProductbrandInfoCustomByID(Integer.valueOf(mchtProductBrandId));
		if(mchtProductBrand.getProductBrandId()!=null){
			productBrand = productBrandService.selectByPrimaryKey(mchtProductBrand.getProductBrandId());
		}
		// 资质
		MchtBrandAptitudeExample example = new MchtBrandAptitudeExample();
		MchtBrandAptitudeExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(example);
		for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
			MchtBrandAptitudePicExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
			MchtBrandAptitudePicExample.Criteria criteria = mchtBrandAptitudePicExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
			List<MchtBrandAptitudePic> mchtBrandAptitudePicList = mchtBrandAptitudePicService.selectByExample(mchtBrandAptitudePicExample);
			List<Map<String, Object>> mchtBrandAptitudePics = new ArrayList<Map<String, Object>>();
			for (MchtBrandAptitudePic mchtBrandAptitudePic : mchtBrandAptitudePicList) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandAptitudePic.getPic());
				mchtBrandAptitudePics.add(pic);
			}
			mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePics);
		}
		model.addAttribute("mchtBrandAptitudeCustoms", mchtBrandAptitudeCustoms);

		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		MchtPlatformAuthPicExample.Criteria mchtPlatformAuthCriteria = mchtPlatformAuthPicExample.createCriteria();
		mchtPlatformAuthCriteria.andDelFlagEqualTo("0");
		mchtPlatformAuthCriteria.andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtPlatformAuthPic> mchtPlatformAuthPicList = mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample);
		List<Map<String, Object>> mchtPlatformAuthPics = new ArrayList<Map<String, Object>>();
		for (MchtPlatformAuthPic mchtPlatformAuthPic : mchtPlatformAuthPicList) {
			Map<String, Object> pic = new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtPlatformAuthPic.getPic());
			mchtPlatformAuthPics.add(pic);
		}

		model.addAttribute("mchtPlatformAuthPics", mchtPlatformAuthPics);

		MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
		mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandInspectionPic> mchtBrandInspectionPicList = mchtBrandInspectionPicServer.selectByExample(mchtBrandInspectionPicExample);
		List<Map<String, Object>> mchtBrandInspectionPics = new ArrayList<Map<String, Object>>();
		for (MchtBrandInspectionPic mchtBrandInspectionPic : mchtBrandInspectionPicList) {
			Map<String, Object> pic = new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtBrandInspectionPic.getPic());
			mchtBrandInspectionPics.add(pic);
		}
		model.addAttribute("mchtBrandInspectionPics",mchtBrandInspectionPics);

		MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
		mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandInvoicePic> mchtBrandInvoicePicList = mchtBrandInvoicePicServer.selectByExample(mchtBrandInvoicePicExample);
		List<Map<String, Object>> mchtBrandInvoicePics = new ArrayList<Map<String, Object>>();
		for (MchtBrandInvoicePic mchtBrandInvoicePic : mchtBrandInvoicePicList) {
			Map<String, Object> pic = new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtBrandInvoicePic.getPic());
			mchtBrandInvoicePics.add(pic);
		}
		model.addAttribute("mchtBrandInvoicePics",mchtBrandInvoicePics);

		MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
		mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandOtherPic> mchtBrandOtherPicList = mchtBrandOtherPicServer.selectByExample(mchtBrandOtherPicExample);
		List<Map<String, Object>> mchtBrandOtherPics = new ArrayList<Map<String, Object>>();
		for (MchtBrandOtherPic mchtBrandOtherPic : mchtBrandOtherPicList) {
			Map<String, Object> pic = new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtBrandOtherPic.getPic());
			mchtBrandOtherPics.add(pic);
		}
		model.addAttribute("mchtBrandOtherPics", mchtBrandOtherPics);
		
		MchtBrandProductTypeExample mbpte = new MchtBrandProductTypeExample();
		mbpte.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId()).andTLevelEqualTo(2);
		List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms = mchtBrandProductTypeService.selectCustomByExample(mbpte);
		model.addAttribute("mchtBrandProductTypeCustoms", mchtBrandProductTypeCustoms);

		model.addAttribute("mchtProductBrand", mchtProductBrand);
		model.addAttribute("productBrand", productBrand);
		model.addAttribute("aptitudeTypeStatus", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE"));
		model.addAttribute("mchtProductBrandStatus",DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "STATUS"));
		model.addAttribute("auditStatus", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "AUDIT_STATUS"));
		model.addAttribute("priceModelStatusDesc", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "PRICE_MODEL"));
		model.addAttribute("mchtInfo", mchtInfoService.selectByPrimaryKey(mchtProductBrand.getMchtId()));
		SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		if ("0".equals(sysStaffInfo.getCanViewQualification())&& "3".equals(mchtProductBrand.getAuditStatus())){
			return "/mcht/noPermissionView";
		}
		return "/mcht/viewMchtProductBrand";
	}
	
	//品牌开通页面
	@RequestMapping(value = "/mcht/toOpenMchtProductBrand.shtml")
	public ModelAndView toOpenMchtProductBrand(HttpServletRequest request) {
		String rePage = "/mcht/toOpenMchtProductBrand";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mchtProductBrandId", request.getParameter("mchtProductBrandId"));
		return new ModelAndView(rePage, resMap);
	}
	
	//品牌开通
	@RequestMapping(value = "/mcht/openMchtProductBrand.shtml")
	@ResponseBody
	public Map<String, Object> openMchtProductBrand(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtProductBrandId = request.getParameter("mchtProductBrandId");
			String status = request.getParameter("status");
			String remarks = request.getParameter("remarks");
			MchtProductBrand mpb = mchtProductBrandService.selectByPrimaryKey(Integer.parseInt(mchtProductBrandId));
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			mpb.setUpdateBy(staffID);
			mpb.setUpdateDate(new Date());
			mpb.setStatus(status);
			mpb.setStatusBy(staffID);
			mpb.setStatusDate(new Date());
			mpb.setStatusRemarks(remarks);
			mpb.setRemarks(remarks);
			MchtInfoChangeLog micl = new MchtInfoChangeLog();
			micl.setMchtId(mpb.getMchtId());
			micl.setLogType("运营开通品牌");
			micl.setLogName(mpb.getProductBrandName());
			micl.setBeforeChange("申请中");
			if(status.equals("1")){//1.正常
				micl.setAfterChange("正常");
			}else if(status.equals("4")){//4.驳回申请
				micl.setAfterChange("驳回申请");
			}
			micl.setRemarks(remarks);
			micl.setCreateBy(staffID);
			micl.setCreateDate(new Date());
			micl.setDelFlag("0");
			mchtProductBrandService.openMchtProductBrand(mpb,micl);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	// 品牌资质变更审核
	@RequestMapping(value = "/mcht/mchprandupdatecheck.shtml")
	public ModelAndView mchprandupdatecheck(HttpServletRequest request) {
		String rePage = "/mcht/mchprandupdatecheck";
		Map<String, Object> resMap = new HashMap<String, Object>();
		// 审核状态
		List<SysStatus> aptitudetypeList = DataDicUtil.getTableStatus("BU_MCHT_BRAND_CHG", "AUDIT_STATUS");
		// 资质类型
		List<SysStatus> aptitudeList = DataDicUtil.getTableStatus("BU_MCHT_BRAND_CHG", "APTITUDE_TYPE");
		resMap.put("statusDescList",DataDicUtil.getTableStatus("BU_MCHT_PRODUCT_BRAND", "STATUS"));
		resMap.put("APTITUDE_TYPE", aptitudeList);
		resMap.put("aptitudetypeList", aptitudetypeList);
		//获取职员对应目录
		String staffID =this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> dataList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
	    resMap.put("Rows", dataList);
	    //对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）	    
	    String isManagement = this.getSessionStaffBean(request).getIsManagement();
	    resMap.put("isManagement", isManagement);
	    resMap.put("staffID", staffID);
	    SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
	    SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
	    sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
	    List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
	    resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		return new ModelAndView(rePage, resMap);
	}

	// 品牌资质变更审核--法务品牌资质审核
	@RequestMapping(value = "/mcht/mchprandupdatecheck/list.shtml")
	@ResponseBody
	public Map<String, Object> getprandMessage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;

		paramMap = this.setPageParametersLiger(request, paramMap);

		if ("1".equals(request.getParameter("is_my_audit"))) {
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(
							Integer.valueOf(this.getSessionStaffBean(request)
									.getStaffID())).andContactTypeEqualTo("7")
					.andStatusEqualTo("0");
			List<PlatformContact> platformContacts = platformContactService
					.selectByExample(platformContactExample);
			if (platformContacts != null && platformContacts.size() > 0) {
				paramMap.put("platformContactId", platformContacts.get(0)
						.getId());
			} else {
				paramMap.put("platformContactId", "99999999999");
			}
		}

		paramMap.put("andWhereClause", " a.status in ('1','2')");

		paramMap.put("orderBy", " order by t.commit_date desc, a.id desc");
		paramMap.put("pageLimit", " limit " + paramMap.get("MIN_NUM") + ", "
				+ paramMap.get("MAX_NUM"));

		List<MchtBrandChgCustom> mchtBrandChgCustoms = mchtBrandChgService.getMchtBrandChgCustom(paramMap);
		totalCount = mchtBrandChgService.getMchtBrandChgCustomCount(paramMap);
		resMap.put("Rows", mchtBrandChgCustoms);
		resMap.put("Total", totalCount);
		
		return resMap;
	}

	//品牌资质归档
	@RequestMapping(value = "/mcht/mchtBrandChgArchiveList.shtml")
	public ModelAndView mchtBrandChgArchiveList(HttpServletRequest request) {
		String rePage = "/mcht/mchtBrandChgArchiveList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		//获取职员对应目录
		String staffID =this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> dataList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
	    resMap.put("Rows", dataList);
	    //对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）     
	    String isManagement = this.getSessionStaffBean(request).getIsManagement();
	    resMap.put("isManagement", isManagement);
	    resMap.put("staffID", staffID);
	    SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
	    SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
	    sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
	    List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
	    resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		return new ModelAndView(rePage, resMap);
	}

	//品牌资质归档
	@RequestMapping(value = "/mcht/mchtBrandChgArchiveData.shtml")
	@ResponseBody
	public Map<String, Object> mchtBrandChgArchiveData(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		paramMap = this.setPageParametersLiger(request, paramMap);
		paramMap.put("AUDITSTATUS", "3");
		paramMap.put("andWhereClause", " a.status in ('0','1','2')");
		paramMap.put("orderBy", " order by t.id desc");
		paramMap.put("pageLimit", " limit " + paramMap.get("MIN_NUM") + ", "+ paramMap.get("MAX_NUM"));
		
		String archiveStatus = (String)paramMap.get("archiveStatus");
		if("2".equals(archiveStatus)){
			paramMap.remove("archiveStatus");
			paramMap.put("notEqualArchiveStatus", "3");
		}
		List<MchtBrandChgCustom> mchtBrandChgCustoms = mchtBrandChgService.getMchtBrandChgCustom(paramMap);
		totalCount = mchtBrandChgService.getMchtBrandChgCustomCount(paramMap);
		resMap.put("Rows", mchtBrandChgCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	//品牌资质归档导出
	@RequestMapping(value = "/mcht/mchtBrandChgArchiveDataExport.shtml")
	@ResponseBody
	public void mchtBrandChgArchiveDataExport(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap, Page page) {
		try{
		paramMap = this.setPageParametersLiger(request, paramMap);
		paramMap.put("AUDITSTATUS", "3");
		paramMap.put("andWhereClause", " a.status in ('0','1','2')");
		paramMap.put("orderBy", " order by t.id desc");
		
		String archiveStatus = (String)paramMap.get("archiveStatus");
		if("2".equals(archiveStatus)){
			paramMap.remove("archiveStatus");
			paramMap.put("notEqualArchiveStatus", "3");
		}
		List<MchtBrandChgCustom> mchtBrandChgCustoms = mchtBrandChgService.getMchtBrandChgCustom(paramMap);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String[] titles = { "申请日期", "招商对接人", "商家序号", "公司名称", "店铺名称","品牌名称","品牌信息","商家寄件状态","资料齐全情况","品牌归档处理","内部备注",
				"驳回原因", "法务对接人" };
		ExcelBean excelBean = new ExcelBean("导出品牌资质归档列表.xls", "品牌资质归档列表", titles);
		List<String[]> datas = new ArrayList<String[]>();
		for (MchtBrandChgCustom mchtBrandChgCustom : mchtBrandChgCustoms) {
			String sendAState="";//商家寄件状态
			String archiveStatusDesc="";//资质齐全情况
			String archiveDealStatusDesc="";//品牌归档处理
			if(StringUtils.isEmpty(mchtBrandChgCustom.getExpressId()) || StringUtils.isEmpty(mchtBrandChgCustom.getExpressNo())){
				sendAState="未寄出";
			}else{
				sendAState="已寄出";
			}
			if(!StringUtils.isEmpty(mchtBrandChgCustom.getArchiveDealStatus()) && mchtBrandChgCustom.getArchiveDealStatus().equals("3")){
				archiveStatusDesc="已齐全";
			}else{
				archiveStatusDesc="未齐全";
			}
			if(!StringUtils.isEmpty(mchtBrandChgCustom.getArchiveDealStatus()) && mchtBrandChgCustom.getArchiveDealStatus().equals("0")){
				archiveDealStatusDesc="未处理";
			}else if("1".equals(mchtBrandChgCustom.getArchiveDealStatus())){
				archiveDealStatusDesc="通过";
			}else if("2".equals(mchtBrandChgCustom.getArchiveDealStatus())){
				archiveDealStatusDesc="驳回";
			}
			String[] data = { 
					mchtBrandChgCustom.getCommitDate()==null?"":sf.format(mchtBrandChgCustom.getCommitDate()),
					mchtBrandChgCustom.getZsContactName(),
					mchtBrandChgCustom.getMchtCode(),
					mchtBrandChgCustom.getCompanyName(),
					mchtBrandChgCustom.getShopName(),
					mchtBrandChgCustom.getProductBrandName(),
					mchtBrandChgCustom.getStatusDesc(),
					sendAState,
					archiveStatusDesc,
					archiveDealStatusDesc,
					mchtBrandChgCustom.getArchiveDealInnerRemarks()==null?"":mchtBrandChgCustom.getArchiveDealInnerRemarks(),
					mchtBrandChgCustom.getArchiveDealRemarks()==null?"":mchtBrandChgCustom.getArchiveDealRemarks(),
					mchtBrandChgCustom.getFwContactName()==null?"":mchtBrandChgCustom.getFwContactName()		
					};
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 品牌资质归档情况
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandChgHandleArchive.shtml")
	public ModelAndView MchtBrandChgHandleArchive(HttpServletRequest request) {
		String rtPage = "/mcht/mchtBrandChgHandleArchive";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtBrandChg mchtBrandChg = mchtBrandChgService.selectByPrimaryKey(id);
		resMap.put("mchtBrandChg", mchtBrandChg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * 品牌资质内部备注情况
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandChgInnerRemarks.shtml")
	public ModelAndView MchtBrandChgInnerRemarks(HttpServletRequest request) {
		String rtPage = "/mcht/mchtBrandChgInnerRemarks";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtBrandChg mchtBrandChg = mchtBrandChgService.selectByPrimaryKey(id);
		resMap.put("mchtBrandChg", mchtBrandChg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 处理品牌资质归档情况
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandChgHandleArchiveUpdate.shtml")
	@ResponseBody
	public Map<String, Object> mchtBrandChgHandleArchiveUpdate(HttpServletRequest request, Page page) {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try{
			String id = request.getParameter("id");
			String archiveDealStatus = request.getParameter("archiveDealStatus");
			String archiveDealRemarks = request.getParameter("archiveDealRemarks");
			String archiveDealInnerRemarks = request.getParameter("archiveDealInnerRemarks");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			if(!StringUtils.isEmpty(id)){
				paramMap.put("id", Integer.parseInt(id));
			}
			if(!StringUtils.isEmpty(archiveDealStatus)){
				paramMap.put("archiveDealStatus", archiveDealStatus);
				if(archiveDealStatus.equals("2")){
					paramMap.put("expressIdIsNull", " express_id = Null");
					paramMap.put("expressNoIsNull", " express_no = Null");
				}
			}
			if(!StringUtils.isEmpty(archiveDealRemarks)){
				paramMap.put("archiveDealRemarks", archiveDealRemarks);
			}
			if(!StringUtils.isEmpty(archiveDealInnerRemarks)){
				paramMap.put("archiveDealInnerRemarks", archiveDealInnerRemarks);
			}
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			paramMap.put("updateBy", staffID);
			paramMap.put("updateDate", new Date());
			mchtBrandChgService.mchtBrandChgHandleArchiveUpdate(paramMap);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 处理品牌资质内部备注情况
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandChgInnerRemarksUpdate.shtml")
	@ResponseBody
	public Map<String, Object> mchtBrandChgInnerRemarksUpdate(HttpServletRequest request, Page page) {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try{
			String id = request.getParameter("id");
			String archiveDealInnerRemarks = request.getParameter("archiveDealInnerRemarks");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			if(!StringUtils.isEmpty(id)){
				paramMap.put("id", Integer.parseInt(id));
			}
			if(!StringUtils.isEmpty(archiveDealInnerRemarks)){
				paramMap.put("archiveDealInnerRemarks", archiveDealInnerRemarks);
			}else{
				paramMap.put("archiveDealInnerRemarksIsNull", "archive_deal_inner_remarks = null");
			}
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			paramMap.put("updateBy", staffID);
			paramMap.put("updateDate", new Date());
			mchtBrandChgService.mchtBrandChgHandleArchiveUpdate(paramMap);
				
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * 资质归档情况
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/viewBrandChgPics.shtml")
	public ModelAndView viewBrandChgPics(HttpServletRequest request) {
		String rtPage = "/mcht/viewBrandChgPics";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtBrandChg mchtBrandChg = mchtBrandChgService.selectByPrimaryKey(id);
		resMap.put("mchtBrandChgId", mchtBrandChg.getId());
		resMap.put("productBrandName", mchtBrandChg.getProductBrandName());
		resMap.put("auditStatus", mchtBrandChg.getAuditStatus());
		resMap.put("archiveStatus", mchtBrandChg.getArchiveStatus());
		MchtBrandAptitudeChgExample example = new MchtBrandAptitudeChgExample();
		MchtBrandAptitudeChgExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0").andMchtBrandChgIdEqualTo(id);
		List<MchtBrandAptitudeChgCustom> mchtBrandAptitudeChgCustoms = mchtBrandAptitudeChgService.selectCustomByExample(example);
		for(MchtBrandAptitudeChgCustom mchtBrandAptitudeChgCustom:mchtBrandAptitudeChgCustoms){
			MchtBrandAptitudePicChgExample e = new MchtBrandAptitudePicChgExample();
			MchtBrandAptitudePicChgExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0").andMchtBrandAptitudeChgIdEqualTo(mchtBrandAptitudeChgCustom.getId());
			List<MchtBrandAptitudePicChg> mchtBrandAptitudePicChgs = mchtBrandAptitudePicChgMapper.selectByExample(e);
			mchtBrandAptitudeChgCustom.setMchtBrandAptitudePicChgs(mchtBrandAptitudePicChgs);
		}
		resMap.put("mchtBrandAptitudeChgCustoms", mchtBrandAptitudeChgCustoms);
		
		MchtPlatformAuthPicChgExample mpapce = new MchtPlatformAuthPicChgExample();
		MchtPlatformAuthPicChgExample.Criteria mbapcec = mpapce.createCriteria();
		mbapcec.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtPlatformAuthPicChg> mchtPlatformAuthPicChgs = mchtPlatformAuthPicChgMapper.selectByExample(mpapce);
		resMap.put("mchtPlatformAuthPicChgs", mchtPlatformAuthPicChgs);
		
		MchtBrandInvoicePicChgExample mbipce = new MchtBrandInvoicePicChgExample();
		MchtBrandInvoicePicChgExample.Criteria mbipcec = mbipce.createCriteria();
		mbipcec.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandInvoicePicChg> mchtBrandInvoicePicChgs = mchtBrandInvoicePicChgMapper.selectByExample(mbipce);
		resMap.put("mchtBrandInvoicePicChgs", mchtBrandInvoicePicChgs);
		
		MchtBrandInspectionPicChgExample mbipce2 = new MchtBrandInspectionPicChgExample();
		MchtBrandInspectionPicChgExample.Criteria mbipcec2 = mbipce2.createCriteria();
		mbipcec2.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandInspectionPicChg> mchtBrandInspectionPicChgs = mchtBrandInspectionPicChgMapper.selectByExample(mbipce2);
		resMap.put("mchtBrandInspectionPicChgs", mchtBrandInspectionPicChgs);
		
		MchtBrandOtherPicChgExample mbopce = new MchtBrandOtherPicChgExample();
		MchtBrandOtherPicChgExample.Criteria mbopcec = mbopce.createCriteria();
		mbopcec.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandOtherPicChg> mchtBrandOtherPicChgs = mchtBrandOtherPicChgMapper.selectByExample(mbopce);
		resMap.put("mchtBrandOtherPicChgs", mchtBrandOtherPicChgs);
		resMap.put("hideRadio", request.getParameter("hideRadio"));
		return new ModelAndView(rtPage, resMap);
	}
	
	//资质归档情况--全部已归操作
	@RequestMapping(value = "/mcht/brandChgPicsArchive.shtml")
	@ResponseBody
	public Map<String, Object> brandChgPicsArchive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtBrandChgId = Integer.parseInt(request.getParameter("mchtBrandChgId"));
			String mchtBrandAptitudeChgId = request.getParameter("mchtBrandAptitudeChgId");
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String picType = request.getParameter("picType");
			mchtBrandChgService.brandChgPicsArchive(mchtBrandChgId,mchtBrandAptitudeChgId,staffID,picType);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	//资质归档情况--单个已归操作
	@RequestMapping(value = "/mcht/picArchive.shtml")
	@ResponseBody
	public Map<String, Object> picArchive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtBrandChgId = Integer.parseInt(request.getParameter("mchtBrandChgId"));
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String picId = request.getParameter("picId");
			String picType = request.getParameter("picType");
			String archiveStatus = request.getParameter("archiveStatus");
			mchtBrandChgService.picArchive(mchtBrandChgId,staffID,picId,picType,archiveStatus);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	//资质归档情况--更新表,主表归档状态修改
	@RequestMapping(value = "/mcht/editMchtBrandArchiveStatus.shtml")
	@ResponseBody
	public Map<String, Object> editMchtBrandArchiveStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtBrandChgId = Integer.parseInt(request.getParameter("mchtBrandChgId"));
			String archiveStatus = request.getParameter("archiveStatus");
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			mchtBrandChgService.editMchtBrandArchiveStatus(mchtBrandChgId,archiveStatus,staffID);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	//新增品牌--品牌资料归档
	@RequestMapping(value = "/mcht/mchtProductBrandArchiveList.shtml")
	public ModelAndView mchtProductBrandArchiveList(HttpServletRequest request) {
		String rePage = "/mcht/mchtProductBrandArchiveList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		resMap.put("isManagement", isManagement);
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria pcec = pce.createCriteria();
		pcec.andDelFlagEqualTo("0");
		pcec.andContactTypeEqualTo("1");// 招商对接人
		pcec.andStatusEqualTo("0");
		pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> pcs = platformContactService.selectByExample(pce);
		if (pcs != null && pcs.size() > 0) {// 是招商对接人
			resMap.put("myContactId", pcs.get(0).getId());
			PlatformContactExample example = new PlatformContactExample();
			PlatformContactExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("0");
			c.andAssistantIdEqualTo(pcs.get(0).getId());
			List<PlatformContact> pcList = platformContactService.selectByExample(example);
			resMap.put("platformContacts", pcList);
			resMap.put("isAssistant", true);
		} else {
			PlatformContactCustomExample zsPlatformExample = new PlatformContactCustomExample();
			zsPlatformExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
			List<PlatformContactCustom> zsPlatformContactCustoms = platformContactService.selectPlatformContactCustomByExample(zsPlatformExample);
			resMap.put("zsPlatformContactCustomList",zsPlatformContactCustoms);
			resMap.put("isAssistant", false);
		}
		return new ModelAndView(rePage, resMap);
	}

	//品牌资质归档
	@RequestMapping(value = "/mcht/mchtProductBrandArchiveData.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductBrandArchiveData(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		paramMap = this.setPageParametersLiger(request, paramMap);
		paramMap.put("AUDITSTATUS", "3"); // 法务审核通过
		paramMap.put("brandSource", "2"); // 追加品牌
		paramMap.put("mchtStatus", "1,2"); // 合作状态 in （正常、暂停）
		if ("1".equals(request.getParameter("is_my_audit"))) {
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(
							Integer.valueOf(this.getSessionStaffBean(request)
									.getStaffID())).andContactTypeEqualTo("7")
					.andStatusEqualTo("0");
			List<PlatformContact> platformContacts = platformContactService
					.selectByExample(platformContactExample);
			if (platformContacts != null && platformContacts.size() > 0) {
				paramMap.put("platformContactId", platformContacts.get(0).getId());
			} else {
				paramMap.put("platformContactId", "99999999999");
			}
		}
		List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.getMchtProductbrandInfoCustom(paramMap);
		totalCount = mchtProductBrandService.getMchtBrandCustomCount(paramMap);
		resMap.put("Rows", mchtProductBrandCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * 资料归档情况
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/viewMchtProductBrandPics.shtml")
	public ModelAndView viewMchtProductBrandPics(HttpServletRequest request) {
		String rtPage = "/mcht/viewMchtProductBrandPics";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(id);
		resMap.put("mchtProductBrandId", mchtProductBrand.getId());
		resMap.put("productBrandName", mchtProductBrand.getProductBrandName());
		resMap.put("auditStatus", mchtProductBrand.getAuditStatus());
		resMap.put("archiveStatus", mchtProductBrand.getArchiveStatus());
		resMap.put("brandSource", mchtProductBrand.getBrandSource());
		resMap.put("mchtProductBrandArchiveStatus", mchtProductBrand.getArchiveStatus());
		MchtBrandAptitudeExample example = new MchtBrandAptitudeExample();
		MchtBrandAptitudeExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(id);
		List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(example);
		for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
			MchtBrandAptitudePicExample e = new MchtBrandAptitudePicExample();
			MchtBrandAptitudePicExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0").andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
			List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(e);
			mchtBrandAptitudeCustom.setMchtBrandAptitudePicList(mchtBrandAptitudePics);
		}
		resMap.put("mchtBrandAptitudeCustoms", mchtBrandAptitudeCustoms);
		
		MchtPlatformAuthPicExample mpapce = new MchtPlatformAuthPicExample();
		MchtPlatformAuthPicExample.Criteria mbapcec = mpapce.createCriteria();
		mbapcec.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService.selectByExample(mpapce);
		resMap.put("mchtPlatformAuthPics", mchtPlatformAuthPics);
		
		MchtBrandInvoicePicExample mbipce = new MchtBrandInvoicePicExample();
		MchtBrandInvoicePicExample.Criteria mbipcec = mbipce.createCriteria();
		mbipcec.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicServer.selectByExample(mbipce);
		resMap.put("mchtBrandInvoicePics", mchtBrandInvoicePics);
		
		MchtBrandInspectionPicExample mbipce2 = new MchtBrandInspectionPicExample();
		MchtBrandInspectionPicExample.Criteria mbipcec2 = mbipce2.createCriteria();
		mbipcec2.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicServer.selectByExample(mbipce2);
		resMap.put("mchtBrandInspectionPics", mchtBrandInspectionPics);
		
		MchtBrandOtherPicExample mbopce = new MchtBrandOtherPicExample();
		MchtBrandOtherPicExample.Criteria mbopcec = mbopce.createCriteria();
		mbopcec.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicServer.selectByExample(mbopce);
		resMap.put("mchtBrandOtherPics", mchtBrandOtherPics);
		
		MchtBrandChangeAgreementPicExample mbcape = new MchtBrandChangeAgreementPicExample();
		mbcape.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandChangeAgreementPic> mchtBrandChangeAgreementPics = mchtBrandChangeAgreementPicMapper.selectByExample(mbcape);
		resMap.put("mchtBrandChangeAgreementPics", mchtBrandChangeAgreementPics);
		
		// hideRadio为隐藏radio标识，为1时页面只能作展示用
		if (!StringUtils.isEmpty(request.getParameter("hideRadio"))) {
			resMap.put("hideRadio", request.getParameter("hideRadio"));
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	//资料归档情况--全部已归操作
	@RequestMapping(value = "/mcht/mchtProductBrandPicsArchive.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductBrandPicsArchive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtProductBrandId = Integer.parseInt(request.getParameter("mchtProductBrandId"));
			String mchtBrandAptitudeId = request.getParameter("mchtBrandAptitudeId");
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String picType = request.getParameter("picType");
			mchtProductBrandService.mchtProductBrandPicsArchive(mchtProductBrandId,mchtBrandAptitudeId,staffID,picType);
			MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mchtProductBrandId);
			MchtProductBrandExample mpbe = new MchtProductBrandExample();
			mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtProductBrand.getMchtId()).andStatusEqualTo("1");
			List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
			int totalCount = 0;
			for(MchtProductBrand mpb:mchtProductBrands){
				if(!StringUtils.isEmpty(mpb.getArchiveStatus()) && mpb.getArchiveStatus().equals("3")){//3.已齐全
					totalCount++;
				}
			}
			MchtInfo mchtInfo = new MchtInfo();
			if(totalCount == mchtProductBrands.size()){
				mchtInfo.setMchtBrandArchiveStatus("1");//品牌资质归档状态：1.已归档
			}else{
				mchtInfo.setMchtBrandArchiveStatus("0");//品牌资质归档状态：0.未归档
			}
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtProductBrand.getMchtId());
			mchtInfoService.updateByExampleSelective(mchtInfo, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	//资料归档情况--单个已归操作
	@RequestMapping(value = "/mcht/mchtProductBrandPicArchive.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductBrandPicArchive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtProductBrandId = Integer.parseInt(request.getParameter("mchtProductBrandId"));
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String picId = request.getParameter("picId");
			String picType = request.getParameter("picType");
			String archiveStatus = request.getParameter("archiveStatus");
			mchtProductBrandService.picArchive(mchtProductBrandId,staffID,picId,picType,archiveStatus);
			MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mchtProductBrandId);
			MchtProductBrandExample mpbe = new MchtProductBrandExample();
			mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtProductBrand.getMchtId()).andStatusEqualTo("1");
			List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
			int totalCount = 0;
			for(MchtProductBrand mpb:mchtProductBrands){
				if(mpb.getArchiveStatus().equals("3")){//3.已齐全
					totalCount++;
				}
			}
			MchtInfo mchtInfo = new MchtInfo();
			if(totalCount == mchtProductBrands.size()){
				mchtInfo.setMchtBrandArchiveStatus("1");//品牌资质归档状态：1.已归档
			}else{
				mchtInfo.setMchtBrandArchiveStatus("0");//品牌资质归档状态：0.未归档
			}
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtProductBrand.getMchtId());
			mchtInfoService.updateByExampleSelective(mchtInfo, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	// 查看、审核
	@RequestMapping(value = "/mcht/editmchtprandapti.shtml")
	@ResponseBody
	public ModelAndView editmchtprandapti(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> pareMap) {
		MchtProductBrandCustom mchtProductBrandCustom = new MchtProductBrandCustom();
		String rePage = "/mcht/editmchtprandapti";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtBrandChgId = pareMap.get("mchtBrandChgId").toString();
		MchtBrandChgCustom mchtBrandChgCustom = mchtBrandChgService.getMchtBrandChgCustomByID(Integer.valueOf(mchtBrandChgId));

		// 一打开就将状态修改为审核中--->已去除审核中这个状态
		/*if ("1".equals(mchtBrandChgCustom.getAuditStatus())) {
			MchtBrandChg mchtBrandChg4Update = new MchtBrandChg();
			mchtBrandChg4Update.setId(mchtBrandChgCustom.getId());
			mchtBrandChg4Update.setAuditStatus("2");
			mchtBrandChgService.updateByPrimaryKeySelective(mchtBrandChg4Update);
			mchtBrandChgCustom.setAuditStatus("2");
		}*/

		mchtProductBrandCustom = mchtProductBrandService.getMchtProductbrandInfoCustomByID(mchtBrandChgCustom.getMchtProductBrandId());
		resMap.put("mchtBrandChg", mchtBrandChgCustom);
		resMap.put("mchtProductBrand", mchtProductBrandCustom);

		if (mchtProductBrandCustom != null) {
			MchtBrandAptitudeExample mbae = new MchtBrandAptitudeExample();
			mbae.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(mbae);
			for (MchtBrandAptitudeCustom mchtBrandAptitudeCustom : mchtBrandAptitudeCustoms) {
				Integer mchtBrandAptitudeId = mchtBrandAptitudeCustom.getId();
				MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
				mbape.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeId);
				List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
				List<Map<String, Object>> mchtBrandAptitudePicList = new ArrayList<Map<String, Object>>();
				for (MchtBrandAptitudePic mchtBrandAptitudePic : mchtBrandAptitudePics) {
					Map<String, Object> pic = new HashMap<String, Object>();
					pic.put("PICTURE_PATH", mchtBrandAptitudePic.getPic());
					mchtBrandAptitudePicList.add(pic);
				}
				mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePicList);
			}
			resMap.put("mchtBrandAptitudeCustoms",mchtBrandAptitudeCustoms);
			
			MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			resMap.put("mchtPlatformAuthPics", mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample));
			
			MchtBrandProductTypeExample mbpte = new MchtBrandProductTypeExample();
			mbpte.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId()).andTLevelEqualTo(2);
			List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms = mchtBrandProductTypeService.selectCustomByExample(mbpte);
			resMap.put("mchtBrandProductTypeCustoms",mchtBrandProductTypeCustoms);
		}

		MchtBrandAptitudeChgExample mchtBrandAptitudeChgExample = new MchtBrandAptitudeChgExample();
		mchtBrandAptitudeChgExample.createCriteria().andDelFlagEqualTo("0").andMchtBrandChgIdEqualTo(mchtBrandChgCustom.getId());
		List<MchtBrandAptitudeChgCustom> mchtBrandAptitudeChgCustoms = mchtBrandAptitudeChgService.selectCustomByExample(mchtBrandAptitudeChgExample);
		for(MchtBrandAptitudeChgCustom mchtBrandAptitudeChgCustom:mchtBrandAptitudeChgCustoms){
			Integer mchtBrandAptitudeChgId = mchtBrandAptitudeChgCustom.getId();
			MchtBrandAptitudePicChgExample mbapce = new MchtBrandAptitudePicChgExample();
			mbapce.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeChgIdEqualTo(mchtBrandAptitudeChgId);
			List<MchtBrandAptitudePicChg> mchtBrandAptitudePicChgs = mchtBrandAptitudePicChgMapper.selectByExample(mbapce);
			List<Map<String, Object>> mchtBrandAptitudePicChgList = new ArrayList<Map<String, Object>>();
			for (MchtBrandAptitudePicChg mchtBrandAptitudePicChg : mchtBrandAptitudePicChgs) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandAptitudePicChg.getPic());
				mchtBrandAptitudePicChgList.add(pic);
			}
			mchtBrandAptitudeChgCustom.setMchtBrandAptitudePicChgList(mchtBrandAptitudePicChgList);
			
		}
		resMap.put("mchtBrandAptitudeChgCustoms", mchtBrandAptitudeChgCustoms);
		
		MchtPlatformAuthPicChgExample mchtPlatformAuthPicChgExample = new MchtPlatformAuthPicChgExample();
		mchtPlatformAuthPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgCustom.getId());
		List<MchtPlatformAuthPicChg> mchtPlatformAuthPicChgList = mchtPlatformAuthPicChgServer.selectByExample(mchtPlatformAuthPicChgExample);
		List<Map<String, Object>> mchtPlatformAuthPicChgs = new ArrayList<Map<String, Object>>();
		for (MchtPlatformAuthPicChg mchtPlatformAuthPicChg : mchtPlatformAuthPicChgList) {
			Map<String, Object> pic = new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtPlatformAuthPicChg.getPic());
			mchtPlatformAuthPicChgs.add(pic);
		}
		resMap.put("mchtPlatformAuthPicChgs", mchtPlatformAuthPicChgs);

		// 进货发票
		if (mchtProductBrandCustom != null) {
			MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
			mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			resMap.put("mchtBrandInvoicePics", mchtBrandInvoicePicServer.selectByExample(mchtBrandInvoicePicExample));
		}
		MchtBrandInvoicePicChgExample mchtBrandInvoicePicChgExample = new MchtBrandInvoicePicChgExample();
		mchtBrandInvoicePicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgCustom.getId());
		List<MchtBrandInvoicePicChg> mchtBrandInvoicePicChgList = mchtBrandInvoicePicChgServer.selectByExample(mchtBrandInvoicePicChgExample);
		List<Map<String, Object>> mchtBrandInvoicePicChgs = new ArrayList<Map<String, Object>>();
		for(MchtBrandInvoicePicChg mchtBrandInvoicePicChg:mchtBrandInvoicePicChgList){
			Map<String, Object> pic = new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtBrandInvoicePicChg.getPic());
			mchtBrandInvoicePicChgs.add(pic);
		}
		resMap.put("mchtBrandInvoicePicChgs", mchtBrandInvoicePicChgs);

		// 质检报告
		if (mchtProductBrandCustom != null) {
			MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
			mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			resMap.put("mchtBrandInspectionPics", mchtBrandInspectionPicServer.selectByExample(mchtBrandInspectionPicExample));
		}
		MchtBrandInspectionPicChgExample mchtBrandInspectionPicChgExample = new MchtBrandInspectionPicChgExample();
		mchtBrandInspectionPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgCustom.getId());
		List<MchtBrandInspectionPicChg> mchtBrandInspectionPicChgList = mchtBrandInspectionPicChgServer.selectByExample(mchtBrandInspectionPicChgExample);
		List<Map<String, Object>> mchtBrandInspectionPicChgs = new ArrayList<Map<String, Object>>();
		for(MchtBrandInspectionPicChg mchtBrandInspectionPicChg:mchtBrandInspectionPicChgList){
			Map<String, Object> pic = new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtBrandInspectionPicChg.getPic());
			mchtBrandInspectionPicChgs.add(pic);
		}
		resMap.put("mchtBrandInspectionPicChgs",mchtBrandInspectionPicChgs);
		
		// 其他报告
		if (mchtProductBrandCustom != null) {
			MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
			mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			resMap.put("mchtBrandOtherPics", mchtBrandOtherPicServer.selectByExample(mchtBrandOtherPicExample));
		}
		MchtBrandOtherPicChgExample mchtBrandOtherPicChgExample = new MchtBrandOtherPicChgExample();
		mchtBrandOtherPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgCustom.getId());
		List<MchtBrandOtherPicChg> mchtBrandOtherPicChgList = mchtBrandOtherPicChgServer.selectByExample(mchtBrandOtherPicChgExample);
		List<Map<String, Object>> mchtBrandOtherPicChgs = new ArrayList<Map<String, Object>>();
		for(MchtBrandOtherPicChg mchtBrandOtherPicChg:mchtBrandOtherPicChgList){
			Map<String, Object> pic = new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtBrandOtherPicChg.getPic());
			mchtBrandOtherPicChgs.add(pic);
		}
		resMap.put("mchtBrandOtherPicChgs", mchtBrandOtherPicChgs);

		resMap.put("statusList",DataDicUtil.getStatusList("BU_MCHT_BRAND_CHG", "AUDIT_STATUS"));
		resMap.put("aptitudeTypeStatus", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE"));
		
		//TODO 品牌经营的类目
		MchtBrandChgProductTypeExample mbcpte = new MchtBrandChgProductTypeExample();
		mbcpte.createCriteria().andDelFlagEqualTo("0").andMchtBrandChgIdEqualTo(mchtBrandChgCustom.getId()).andTLevelEqualTo(2);
		List<MchtBrandChgProductTypeCustom> mchtBrandChgProductTypeCustoms = mchtBrandChgProductTypeService.selectCustomByExample(mbcpte);
		resMap.put("mchtBrandChgProductTypeCustoms",mchtBrandChgProductTypeCustoms);
		
		return new ModelAndView(rePage, resMap);
	}

	@RequestMapping(value = "/mcht/editbrandsave.shtml")
	@ResponseBody
	public Map<String, Object> editbrandsave(HttpServletRequest request,MchtBrandChg mchtBrandChg) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtPlatformAuthPicChgs = request.getParameter("mchtPlatformAuthPicChgs");
			String mchtBrandInvoicePicChgs = request.getParameter("mchtBrandInvoicePicChgs");
			String mchtBrandInspectionPicChgs = request.getParameter("mchtBrandInspectionPicChgs");
			String mchtBrandOtherPicChgs = request.getParameter("mchtBrandOtherPicChgs");
			String mchtBrandAptitudeChgJsonStr = request.getParameter("mchtBrandAptitudeChgJsonStr");
			
			mchtBrandChg.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			mchtBrandChg.setUpdateDate(new Date());
			mchtBrandChgService.auditMchtBrandChg(mchtPlatformAuthPicChgs,mchtBrandInvoicePicChgs,mchtBrandInspectionPicChgs,mchtBrandOtherPicChgs,mchtBrandAptitudeChgJsonStr,mchtBrandChg);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 商家帐号
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtUser.shtml")
	public String mchtUser(Model model, HttpServletRequest request) {
		String isManagement = this.getSessionStaffBean(request)
				.getIsManagement();
		model.addAttribute("isManagement", isManagement);
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria pcec = pce.createCriteria();
		pcec.andDelFlagEqualTo("0");
		pcec.andContactTypeEqualTo("1");// 招商对接人
		pcec.andStatusEqualTo("0");
		pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(
				request).getStaffID()));
		List<PlatformContact> platformContacts = platformContactService
				.selectByExample(pce);
		if (platformContacts != null && platformContacts.size() > 0) {// 是招商对接人
			model.addAttribute("myContactId", platformContacts.get(0).getId());
			PlatformContactExample e = new PlatformContactExample();
			PlatformContactExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("0");
			c.andAssistantIdEqualTo(platformContacts.get(0).getId());
			List<PlatformContact> platformContactList = platformContactService
					.selectByExample(e);
			model.addAttribute("platformContacts", platformContactList);
			model.addAttribute("isAssistant", true);
		} else {
			PlatformContactCustomExample yyPlatformExample = new PlatformContactCustomExample();
			yyPlatformExample.createCriteria().andDelFlagEqualTo("0")
					.andStatusEqualTo("0").andContactTypeEqualTo("2");
			List<PlatformContactCustom> yyPlatformContactCustoms = platformContactService
					.selectPlatformContactCustomByExample(yyPlatformExample);
			model.addAttribute("yyPlatformContactCustomList",
					yyPlatformContactCustoms);
			model.addAttribute("isAssistant", false);
		}
		return "/mcht/mchtUser";
	}

	// 商家帐号
	@RequestMapping(value = "/mcht/mchtUserDataList.shtml")
	@ResponseBody
	public Map<String, Object> mchtUserDataList(HttpServletRequest request,
			Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		int totalCount = 0;
		List<MchtUserCustom> mchtUserCustoms = new ArrayList<MchtUserCustom>();
		// 招商对接人
		PlatformContactExample platformContactExample = new PlatformContactExample();
		platformContactExample.createCriteria().andStaffIdEqualTo(staffId)
				.andDelFlagEqualTo("0").andContactTypeEqualTo("1")
				.andStatusEqualTo("0");
		List<PlatformContact> platformContacts = platformContactService
				.selectByExample(platformContactExample);
		if (platformContacts == null || platformContacts.size() == 0) {
			resMap.put("Rows", mchtUserCustoms);
			resMap.put("Total", totalCount);
			return resMap;
		}
		try {
			MchtUserCustomExample mchtUserCustomExample = new MchtUserCustomExample();
			MchtUserCustomCriteria mchtUserCustomExampleCriteria = mchtUserCustomExample.createCriteria();
			mchtUserCustomExampleCriteria.andMchtZsTotalAuditStatusIn("('4','0','2','3')");
			mchtUserCustomExampleCriteria.andMchtStatusEqualTo("0");
			if (!StringUtil.isEmpty(request.getParameter("search_id"))) {
				mchtUserCustomExampleCriteria.andMchtCodeEqualTo(request.getParameter("search_id"));
			}
			if (!StringUtil.isEmpty(request.getParameter("search_name"))) {
				mchtUserCustomExampleCriteria.andMchtNameLike(request.getParameter("search_name"));
			}

			if (!StringUtil.isEmpty(request.getParameter("search_password"))) {
				if ("1".equals(request.getParameter("search_password"))) {
					mchtUserCustomExampleCriteria.andPasswordNotEqualTo("e10adc3949ba59abbe56e057f20f883e");
				} else {
					mchtUserCustomExampleCriteria.andPasswordEqualTo("e10adc3949ba59abbe56e057f20f883e");
				}
			}
			if (!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				mchtUserCustomExampleCriteria.andZsContactEqualTo(Integer.parseInt(request.getParameter("platformContactId")));
			}
			if (!StringUtil.isEmpty(request.getParameter("settledType"))) {
				mchtUserCustomExampleCriteria.andSettledTypeEqualTo(request.getParameter("settledType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("isManageSelf"))) {
				mchtUserCustomExampleCriteria.andIsManageSelfEqualTo(request.getParameter("isManageSelf"));
			}
			totalCount = mchtUserService.countByExample(mchtUserCustomExample);
			mchtUserCustomExample.setLimitSize(page.getLimitSize());
			mchtUserCustomExample.setLimitStart(page.getLimitStart());
			mchtUserCustoms = mchtUserService.selectMchtUserCustomByExample(mchtUserCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtUserCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}

	// 校验商家录入信息
	@RequestMapping(value = "/mcht/sendPassword2MchtUser.shtml")
	@ResponseBody
	public Map<String, Object> sendPassword2MchtUser(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {

			if (StringUtil.isEmpty(request.getParameter("id"))) {
				throw new ArgException("发送失败");
			}

			MchtUser mchtUser = mchtUserService.selectByPrimaryKey(Integer
					.valueOf(request.getParameter("id")));

			if (mchtUser == null) {
				throw new ArgException("发送失败");
			}

			if (StringUtil.isEmpty(mchtUser.getMobile())) {
				throw new ArgException("用户手机号为空");
			}

			JSONObject param = new JSONObject();
			// 特定参数
			JSONObject reqData = new JSONObject();
			reqData.put("mobile", mchtUser.getMobile());
			reqData.put("content", "恭喜您的商家账号添加成功，用户名：" + mchtUser.getUserCode()
					+ "，初始密码：123456，您可以安排提交入驻资质以便开通醒购店铺。 ");
			reqData.put("smsType", "2");
			param.put("reqData", reqData);
			JSONObject result = JSONObject.fromObject(HttpUtil.sendPostRequest(
					SysConfig.msgServerUrl + "/api/sms/sendImmediately",
					CommonUtil.createReqData(param).toString()));
			if (!"0000".equals(result.getString("returnCode"))) {
				throw new ArgException("发送失败，请重新获取!");
			}
			String zsMobile = "";
			MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
			mchtPlatformContactExample.createCriteria().andDelFlagEqualTo("0")
					.andMchtIdEqualTo(mchtUser.getMchtId())
					.andStatusEqualTo("1");
			List<MchtPlatformContactCustom> mchtPlatformContacts = mchtPlatformContactService
					.selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
			if (mchtPlatformContacts != null && mchtPlatformContacts.size() > 0) {
				for (MchtPlatformContactCustom mchtPlatformContact : mchtPlatformContacts) {
					if ("1".equals(mchtPlatformContact.getContactType())) {
						zsMobile = mchtPlatformContact.getMobile();
						break;
					}
				}
			}
			// 发给招商对接人的手机号 bug #1672
			if (!StringUtils.isEmpty(zsMobile)) {
				JSONObject param2 = new JSONObject();
				// 特定参数
				JSONObject reqData2 = new JSONObject();
				reqData2.put("mobile", zsMobile);
				reqData2.put("content",
						"恭喜您的商家账号添加成功，用户名：" + mchtUser.getUserCode()
								+ "，初始密码：123456，您可以安排提交入驻资质以便开通醒购店铺。 ");
				reqData2.put("smsType", "2");
				param2.put("reqData", reqData2);
				JSONObject result2 = JSONObject.fromObject(HttpUtil
						.sendPostRequest(SysConfig.msgServerUrl
								+ "/api/sms/sendImmediately", CommonUtil
								.createReqData(param2).toString()));
				if (!"0000".equals(result2.getString("returnCode"))) {
					throw new ArgException("发送失败，请重新获取!");
				}
			}
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 法务资质总审核
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/totalAuditIndex.shtml")
	public ModelAndView totalAuditIndex(HttpServletRequest request) {
		String rtPage = "mcht/totalAuditIndex";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			resMap.put("mcht_type",statusService.querytStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
			resMap.put("status",statusService.querytStatusList("BU_MCHT_INFO", "STATUS"));
			resMap.put("is_company_inf_perfect", statusService.querytStatusList("BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT"));
			resMap.put("total_audit_status_list", statusService.querytStatusList("BU_MCHT_INFO", "TOTAL_AUDIT_STATUS"));
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()))
					.andContactTypeEqualTo("7")
					.andStatusEqualTo("0");
			if (platformContactService.countByExample(platformContactExample) > 0) {
				resMap.put("isCanGet", true);
			} else {
				resMap.put("isCanGet", false);
			}
			// 招商对接人
			String staffID = this.getSessionStaffBean(request).getStaffID();
			PlatformContactCustomExample zsPlatformContactCustomExample = new PlatformContactCustomExample();
			zsPlatformContactCustomExample.createCriteria()
					.andDelFlagEqualTo("0").andStatusEqualTo("0")
					.andContactTypeEqualTo("1")
					.andStaffIdEqualTo(Integer.parseInt(staffID));
			List<PlatformContactCustom> zsPlatformContactCustomList = platformContactService
					.selectPlatformContactCustomByExample(zsPlatformContactCustomExample);
			if (zsPlatformContactCustomList != null
					&& zsPlatformContactCustomList.size() > 0) {
				resMap.put("zsPlatformContactCustomList",
						zsPlatformContactCustomList);
				resMap.put("isAssistant", true);
			} else {
				PlatformContactCustomExample zsPlatformExample = new PlatformContactCustomExample();
				zsPlatformExample.createCriteria().andDelFlagEqualTo("0")
						.andStatusEqualTo("0").andContactTypeEqualTo("1");
				List<PlatformContactCustom> zsPlatformContactCustoms = platformContactService
						.selectPlatformContactCustomByExample(zsPlatformExample);
				resMap.put("zsPlatformContactCustomList",
						zsPlatformContactCustoms);
				resMap.put("isAssistant", false);
			}
			// 法务对接人
			PlatformContactCustomExample fwPlatformContactCustomExample = new PlatformContactCustomExample();
			fwPlatformContactCustomExample.createCriteria()
					.andDelFlagEqualTo("0").andStatusEqualTo("0")
					.andContactTypeEqualTo("7");
			List<PlatformContactCustom> fwPlatformContactCustomList = platformContactService
					.selectPlatformContactCustomByExample(fwPlatformContactCustomExample);
			resMap.put("fwPlatformContactCustomList",
					fwPlatformContactCustomList);
			ProductTypeExample example = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = example
					.createCriteria();
			productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1)
					.andStatusEqualTo("1");
			// 钟表运营部状态，只获取主营类目为钟表
			String isCwOrgStatus = this.getParamSession(request,
					"isCwOrgStatus");
			if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request,
						"isCwOrgProductTypeId");
				if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer
							.parseInt(isCwOrgProductTypeId));
				}
			}
			resMap.put("isCwOrgStatus", isCwOrgStatus);
			List<ProductType> productTypes = productTypeService
					.selectByExample(example);
			resMap.put("productTypes", productTypes);
			resMap.put("loginStaffID", this.getSessionStaffBean(request)
					.getStaffID());
			resMap.put("totalAuditStatus", "0");
			
			SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
			List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
			if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
				resMap.put("role107", true);
			}else{
				resMap.put("role107", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 法务资质总审核
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/totalAuditDataList.shtml")
	@ResponseBody
	public Map<String, Object> totalAuditDataList(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount = "0";
		try {
			paramMap.put("total_audit_status_notEqual", "2");
			paramMap.put("zs_total_audit_status", "2");
			List<String> statusList = new ArrayList<String>();
			statusList.add("0");
//			statusList.add("3");
			paramMap.put("statusList", statusList);
			paramMap.put("orderByClause", "commit_audit_date asc");
			if ("1".equals(request.getParameter("is_my_audit"))) {
				PlatformContactExample platformContactExample = new PlatformContactExample();
				platformContactExample.createCriteria().andDelFlagEqualTo("0").
				andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID())).
				andContactTypeEqualTo("7").andStatusEqualTo("0");
				List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExample);
				if (platformContacts != null && platformContacts.size() > 0) {
					paramMap.put("platContactId", platformContacts.get(0).getId());
				} else {
					paramMap.put("platContactId", "99999999999");
				}
			}
			paramMap = this.setPageParametersLiger(request, paramMap);
			if (!StringUtils.isEmpty(request.getParameter("commit_audit_date_begin"))) {
				paramMap.put("commit_audit_date_begin",request.getParameter("commit_audit_date_begin")+ " 00:00:00");
			}
			if (!StringUtils.isEmpty(request.getParameter("commit_audit_date_end"))) {
				paramMap.put("commit_audit_date_end",request.getParameter("commit_audit_date_end")+ " 23:59:59");
			}
			// 钟表运营部状态，只获取主营类目为钟表
			String isCwOrgStatus = this.getParamSession(request,"isCwOrgStatus");
			if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request,"isCwOrgProductTypeId");
				paramMap.put("productTypeId", isCwOrgProductTypeId);
			}
			// 招商对接人
			String staffID = this.getSessionStaffBean(request).getStaffID();
			PlatformContactCustomExample zsPlatformContactCustomExample = new PlatformContactCustomExample();
			zsPlatformContactCustomExample.createCriteria()
					.andDelFlagEqualTo("0").andStatusEqualTo("0")
					.andContactTypeEqualTo("1")
					.andStaffIdEqualTo(Integer.parseInt(staffID));
			List<PlatformContactCustom> zsPlatformContactCustomList = platformContactService
					.selectPlatformContactCustomByExample(zsPlatformContactCustomExample);
			StringBuffer inPlatformContactIds = new StringBuffer();
			if (zsPlatformContactCustomList != null
					&& zsPlatformContactCustomList.size() > 0) {
				for (PlatformContactCustom platformContactCustom : zsPlatformContactCustomList) {
					if (inPlatformContactIds.length() > 0) {
						inPlatformContactIds.append(",");
					}
					inPlatformContactIds.append(platformContactCustom.getId());
					if (platformContactCustom.getAssistantId() != null) {
						inPlatformContactIds.append(","	+ platformContactCustom.getAssistantId());
					}
				}
			}
			if (inPlatformContactIds.length() > 0) {
				paramMap.put("in_zs_platform_contact_ids",inPlatformContactIds.toString());
			}
			if (!StringUtils.isEmpty(request.getParameter("settledType"))) {
				paramMap.put("settledType",request.getParameter("settledType"));
			}
			if (!StringUtils.isEmpty(request.getParameter("isManageSelf"))) {
				paramMap.put("isManageSelf",request.getParameter("isManageSelf"));
			}
			totalCount = mchtInfoService.queryMchtCount(paramMap);
			dataList = mchtInfoService.selectMchtInfoList(paramMap);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
		return resMap;
	}

	// 法务审核领取
	@RequestMapping(value = "/mcht/getAudit.shtml")
	@ResponseBody
	public Map<String, Object> getAudit(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {

			if (StringUtil.isEmpty(request.getParameter("id"))) {
				throw new ArgException("领取失败");
			}

			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(
							Integer.valueOf(this.getSessionStaffBean(request)
									.getStaffID())).andContactTypeEqualTo("7")
					.andStatusEqualTo("0");

			List<PlatformContact> platformContacts = platformContactService
					.selectByExample(platformContactExample);
			if (platformContacts == null || platformContacts.size() == 0) {
				throw new ArgException("您不是法务对接人，不能领取");
			}

			MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
			mchtPlatformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andMchtIdEqualTo(
							Integer.valueOf(request.getParameter("id")))
					.andStatusEqualTo("1");
			List<MchtPlatformContactCustom> mchtPlatformContacts = mchtPlatformContactService
					.selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
			if (mchtPlatformContacts != null && mchtPlatformContacts.size() > 0) {
				for (MchtPlatformContactCustom mchtPlatformContact : mchtPlatformContacts) {
					if ("7".equals(mchtPlatformContact.getContactType())) {
						throw new ArgException("此商家已被领取。");
					}
				}
			}

			MchtPlatformContact mchtPlatformContact = new MchtPlatformContact();
			mchtPlatformContact.setCreateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			mchtPlatformContact.setCreateDate(new Date());
			mchtPlatformContact.setDelFlag("0");
			mchtPlatformContact.setMchtId(Integer.valueOf(request
					.getParameter("id")));
			mchtPlatformContact.setPlatformContactId(platformContacts.get(0)
					.getId());
			mchtPlatformContact.setStatus("1");
			mchtPlatformContactService.insertSelective(mchtPlatformContact);

			if (!StringUtil.isEmpty(request.getParameter("auditType"))) {
				if ("totalAudit".equals(request.getParameter("auditType"))) {// 法务总审核领取
					MchtInfo mchtInfo4Update = new MchtInfo();
					mchtInfo4Update.setId(Integer.valueOf(request
							.getParameter("id")));
					mchtInfo4Update.setTotalAuditStatus("1");
					mchtInfoService
							.updateByPrimaryKeySelective(mchtInfo4Update);
				}
			}

		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	// 财务审核领取
	@RequestMapping(value = "/mcht/getAuditCw.shtml")
	@ResponseBody
	public Map<String, Object> getAuditCW(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {

			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID())).andContactTypeEqualTo("5");
			
			List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExample);
			if (platformContacts == null || platformContacts.size() == 0) {
				throw new ArgException("您不是财务对接人，不能领取");
			 }
			
			String[] split = request.getParameter("id").split(",");
			for (int i = 0; i < split.length; i++) {
				 String mchtId = split[i];
				 
			  if (StringUtil.isEmpty(mchtId)) {
			  	throw new ArgException("领取失败");
			  } 


			/*MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
			mchtPlatformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andMchtIdEqualTo(
							Integer.valueOf(request.getParameter("id")))
					.andStatusEqualTo("1");
			List<MchtPlatformContactCustom> mchtPlatformContacts = mchtPlatformContactService
					.selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
			if (mchtPlatformContacts != null && mchtPlatformContacts.size() > 0) {
				for (MchtPlatformContactCustom mchtPlatformContact : mchtPlatformContacts) {
					if ("5".equals(mchtPlatformContact.getContactType())) {
						throw new ArgException("此商家已被领取。");
					}
				}
			}*/ 
			  		  

			/*MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
			mchtPlatformContactExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(Integer.valueOf(mchtId)).andStatusEqualTo("1");
			List<MchtPlatformContactCustom> mchtPlatformContacts = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
			
			if (mchtPlatformContacts != null && mchtPlatformContacts.size() > 0) {
				for (MchtPlatformContactCustom mchtPlatformContact : mchtPlatformContacts) {
					if ("5".equals(mchtPlatformContact.getContactType())) {
						
						MchtPlatformContact mchtPlatformContact1 = new MchtPlatformContact();
						mchtPlatformContact1.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
						mchtPlatformContact1.setUpdateDate(new Date());
						mchtPlatformContact1.setPlatformContactId(platformContacts.get(0).getId());
						mchtPlatformContactService.updateByExampleSelective(mchtPlatformContact1, mchtPlatformContactExample);
						
					}
			    }
			}
			*/
			
			    MchtPlatformContactCustomExample mchtPlatformContactCustomExample=new MchtPlatformContactCustomExample();
				MchtPlatformContactCustomExample.MchtPlatformContactCustomCriteria mchtPlatformContactCustomCriteria=mchtPlatformContactCustomExample.createCriteria();
				mchtPlatformContactCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
				mchtPlatformContactCustomCriteria.andMchtIdEqualTo(Integer.valueOf(mchtId));
				List<MchtPlatformContactCustom> mchtPlatformContacts = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactCustomExample);
				
				if (mchtPlatformContacts != null && mchtPlatformContacts.size() > 0) {									
					MchtPlatformContact mchtPlatformContact1 = new MchtPlatformContact();
					mchtPlatformContact1.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
					mchtPlatformContact1.setUpdateDate(new Date());
					
					mchtPlatformContact1.setPlatformContactId(platformContacts.get(0).getId());
					
					mchtPlatformContactService.updateByExampleSelective(mchtPlatformContact1, mchtPlatformContactCustomExample);

				}
				
				
			if(mchtPlatformContacts.size()==0){
				
				MchtPlatformContact mchtPlatformContact1 = new MchtPlatformContact();
				mchtPlatformContact1.setCreateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				mchtPlatformContact1.setCreateDate(new Date());
				mchtPlatformContact1.setDelFlag("0");
				/*mchtPlatformContact.setMchtId(Integer.valueOf(request
				         .getParameter("id"))); */
				mchtPlatformContact1.setMchtId(Integer.valueOf(mchtId));
				if(platformContacts!=null && platformContacts.size()>0){
					mchtPlatformContact1.setPlatformContactId(platformContacts.get(0).getId());
				}else{
					PlatformContactExample e = new PlatformContactExample();
					e.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
					List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
					if(platformContactList!=null && platformContactList.size()>0){
						mchtPlatformContact1.setPlatformContactId(platformContactList.get(0).getId());
					}
				}
				mchtPlatformContact1.setStatus("1");
				mchtPlatformContactService.insertSelective(mchtPlatformContact1);
				
			 }
			 				  
			  
		   }

		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	// 运营领取
	@RequestMapping(value = "/mcht/getAuditYY.shtml")
	@ResponseBody
	public Map<String, Object> getAuditYY(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {

			if (StringUtil.isEmpty(request.getParameter("id"))) {
				throw new ArgException("领取失败");
			}

			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(
							Integer.valueOf(this.getSessionStaffBean(request)
									.getStaffID())).andContactTypeEqualTo("2")
					.andStatusEqualTo("0");

			List<PlatformContact> platformContacts = platformContactService
					.selectByExample(platformContactExample);
			if (platformContacts == null || platformContacts.size() == 0) {
				throw new ArgException("您不是运营对接人，不能领取");
			}

			MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
			mchtPlatformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andMchtIdEqualTo(
							Integer.valueOf(request.getParameter("id")))
					.andStatusEqualTo("1");
			List<MchtPlatformContactCustom> mchtPlatformContacts = mchtPlatformContactService
					.selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
			if (mchtPlatformContacts != null && mchtPlatformContacts.size() > 0) {
				for (MchtPlatformContactCustom mchtPlatformContact : mchtPlatformContacts) {
					if ("2".equals(mchtPlatformContact.getContactType())) {
						throw new ArgException("此商家已被领取。");
					}
				}
			}

			MchtPlatformContact mchtPlatformContact = new MchtPlatformContact();
			mchtPlatformContact.setCreateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			mchtPlatformContact.setCreateDate(new Date());
			mchtPlatformContact.setDelFlag("0");
			mchtPlatformContact.setMchtId(Integer.valueOf(request
					.getParameter("id")));
			mchtPlatformContact.setPlatformContactId(platformContacts.get(0)
					.getId());
			mchtPlatformContact.setStatus("1");
			mchtPlatformContactService.insertSelective(mchtPlatformContact);

		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	// 商家银行帐号审核提交
	@RequestMapping(value = "/mcht/mchtBankAccountAuditSave.shtml")
	@ResponseBody
	public Map<String, Object> mchtBankAccountAuditSave(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			String status = request.getParameter("status");
			String auditRemarks = request.getParameter("auditRemarks");
			Integer auditBy = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			mchtBankAccountService.mchtbankAccountAuditSave(auditBy, status,auditRemarks, id);
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	// 银行帐号审核
	@RequestMapping(value = "/mcht/mchtBankUpdateCheckIndex.shtml")
	public ModelAndView mchtBankUpdateCheck(HttpServletRequest request) {
		String rePage = "/mcht/mchtBankUpdateCheckIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();

		resMap.put("statusDescList",
				DataDicUtil.getStatusList("BU_MCHT_BANK_ACCOUNT", "STATUS"));
		resMap.put("accTypeDescList",
				DataDicUtil.getStatusList("BU_MCHT_BANK_ACCOUNT", "ACC_TYPE"));

		return new ModelAndView(rePage, resMap);
	}

	// 银行帐号审核
	@RequestMapping(value = "/mcht/mchtBankUpdateCheckDataList.shtml")
	@ResponseBody
	public Map<String, Object> mchtBankUpdateCheckDataList(
			HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;

		MchtBankAccountCustomExample mchtBankAccountCustomExample = new MchtBankAccountCustomExample();
		MchtBankAccountCustomExample.MchtBankAccountCustomCriteria criteria = mchtBankAccountCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		if (!StringUtil.isEmpty(request.getParameter("search_status"))) {
			criteria.andStatusEqualTo(request.getParameter("search_status"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_accType"))) {
			criteria.andAccTypeEqualTo(request.getParameter("search_accType"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_companyName"))) {
			criteria.andCompanyNameLike(request
					.getParameter("search_companyName"));
		}

		if ("1".equals(request.getParameter("is_my_audit"))) {
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(
							Integer.valueOf(this.getSessionStaffBean(request)
									.getStaffID())).andContactTypeEqualTo("5")
					.andStatusEqualTo("0");
			List<PlatformContact> platformContacts = platformContactService
					.selectByExample(platformContactExample);
			if (platformContacts != null && platformContacts.size() > 0) {
				criteria.andPlatformContactIdEqualTo(platformContacts.get(0)
						.getId());
			} else {
				criteria.andPlatformContactIdEqualTo(99999999);
			}
		}
		criteria.andWhereClause(" EXISTS(select id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.status in ('0','1','2'))");

		totalCount = mchtBankAccountService
				.countMchtBankAccountCustomByExample(mchtBankAccountCustomExample);
		mchtBankAccountCustomExample.setLimitSize(page.getLimitSize());
		mchtBankAccountCustomExample.setLimitStart(page.getLimitStart());
		List<MchtBankAccountCustom> mchtBankAccounts = mchtBankAccountService
				.selectMchtBankAccountCustomByExample(mchtBankAccountCustomExample);
		resMap.put("Rows", mchtBankAccounts);
		resMap.put("Total", totalCount);

		return resMap;
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mcht/exportMchtBankAccount.shtml")
	public void exportMchtBankAccount(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			MchtBankAccountCustomExample mchtBankAccountCustomExample = new MchtBankAccountCustomExample();
			MchtBankAccountCustomExample.MchtBankAccountCustomCriteria criteria = mchtBankAccountCustomExample
					.createCriteria();
			if (!StringUtil.isEmpty(request.getParameter("search_status"))) {
				criteria.andStatusEqualTo(request.getParameter("search_status"));
			}
			if (!StringUtil.isEmpty(request.getParameter("search_accType"))) {
				criteria.andAccTypeEqualTo(request
						.getParameter("search_accType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("search_companyName"))) {
				criteria.andCompanyNameLike(request
						.getParameter("search_companyName"));
			}

			if ("1".equals(request.getParameter("is_my_audit"))) {
				PlatformContactExample platformContactExample = new PlatformContactExample();
				platformContactExample
						.createCriteria()
						.andDelFlagEqualTo("0")
						.andStaffIdEqualTo(
								Integer.valueOf(this.getSessionStaffBean(
										request).getStaffID()))
						.andContactTypeEqualTo("5").andStatusEqualTo("0");
				List<PlatformContact> platformContacts = platformContactService
						.selectByExample(platformContactExample);
				if (platformContacts != null && platformContacts.size() > 0) {
					criteria.andPlatformContactIdEqualTo(platformContacts
							.get(0).getId());
				} else {
					criteria.andPlatformContactIdEqualTo(99999999);
				}
			}
			criteria.andWhereClause(" EXISTS(select id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.status in ('0','1','2'))");

			List<MchtBankAccountCustom> mchtBankAccounts = mchtBankAccountService
					.selectMchtBankAccountCustomByExample(mchtBankAccountCustomExample);
			String[] titles = { "商家ID", "商家序号", "公司名称", "银行名称", "收款人开户行名称",
					"收款人账号", "收款人名称" };
			ExcelBean excelBean = new ExcelBean("导出银行账号.xls", "导出银行账号", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for (MchtBankAccountCustom mchtBankAccountCustom : mchtBankAccounts) {
				String[] data = { mchtBankAccountCustom.getMchtId().toString(),
						mchtBankAccountCustom.getMchtCode(),
						mchtBankAccountCustom.getCompanyName(),
						mchtBankAccountCustom.getBankName(),
						mchtBankAccountCustom.getDepositBank(),
						"`" + mchtBankAccountCustom.getAccNumber(),
						mchtBankAccountCustom.getAccName() };
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/toAuditmchtBankAccount.shtml")
	public String toAuditmchtBankAccount(Model model, HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		MchtBankAccount mchtBankAccount = mchtBankAccountService
				.selectMchtBankAccountCustomByPrimaryKey(id);

		MchtBankAccountHisExample mchtBankAccountHisExample = new MchtBankAccountHisExample();
		mchtBankAccountHisExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtBankAccount.getMchtId());
		int count = mchtBankAccountHisService
				.countByExample(mchtBankAccountHisExample);
		if (count > 0) {
			return "redirect:/mcht/mchtBankAccountAudit.shtml?id=" + id;
		} else {
			model.addAttribute("mchtId", mchtBankAccount.getMchtId());
			return "/mcht/toMchtBaseInfo";
		}

	}

	/**
	 * 
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBankAccountAudit.shtml")
	public String mchtBankAccountAudit(Model model, HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		MchtBankAccount mchtBankAccount = mchtBankAccountService
				.selectMchtBankAccountCustomByPrimaryKey(id);
		if ("0".equals(mchtBankAccount.getStatus())) {
			mchtBankAccount.setStatus("1");
			mchtBankAccountService.updateByPrimaryKeySelective(mchtBankAccount);
		}

		MchtBankAccountHisExample mchtBankAccountHisExample = new MchtBankAccountHisExample();
		mchtBankAccountHisExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtBankAccount.getMchtId());
		mchtBankAccountHisExample.setOrderByClause("id desc");
		mchtBankAccountHisExample.setLimitStart(0);
		mchtBankAccountHisExample.setLimitSize(1);
		List<MchtBankAccountHis> mchtBankAccountHis = mchtBankAccountHisService
				.selectByExample(mchtBankAccountHisExample);
		if (mchtBankAccountHis != null && mchtBankAccountHis.size() > 0) {
			model.addAttribute("mchtBankAccountHis", mchtBankAccountHis.get(0));
			model.addAttribute("mchtBankAccountHisAccTypeDesc", DataDicUtil
					.getStatusDesc("BU_MCHT_BANK_ACCOUNT", "ACC_TYPE",
							mchtBankAccountHis.get(0).getAccType()));
			BankExample bankExample = new BankExample();
			bankExample.createCriteria().andIdEqualTo(
					Integer.valueOf(mchtBankAccountHis.get(0).getBankCode()));
			List<Bank> banks = bankService.selectByExample(bankExample);
			model.addAttribute("mchtBankAccountHisBankName", banks.get(0)
					.getName());
		} else {
			model.addAttribute("mchtBankAccountHis", new MchtBankAccountHis());
			model.addAttribute("mchtBankAccountHisAccTypeDesc", "");
			model.addAttribute("mchtBankAccountHisBankName", "");
		}
		model.addAttribute("mchtBankAccount", mchtBankAccount);

		model.addAttribute("statusList",
				DataDicUtil.getStatusList("BU_MCHT_BANK_ACCOUNT", "STATUS"));

		return "/mcht/mchtBankAccountAudit";
	}

	/**
	 * 法务资质总审核
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/totalAudit.shtml")
	public String totalAudit(Model model, HttpServletRequest request) {
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);

		// 公司信息
		model.addAttribute("isCompanyInfPerfect", DataDicUtil.getStatusDesc(
				"BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT",
				mchtInfo.getIsCompanyInfPerfect()));
		// 公司信息
		model.addAttribute("shopNameAuditStatus", DataDicUtil.getStatusDesc(
				"BU_MCHT_INFO", "SHOP_NAME_AUDIT_STATUS",
				mchtInfo.getShopNameAuditStatus()));

		// 保证金类型
		model.addAttribute("depositType", DataDicUtil.getStatusDesc(
				"BU_MCHT_INFO", "DEPOSIT_TYPE", mchtInfo.getDepositType()==null?"":mchtInfo.getDepositType()));

		// 税务状态
		MchtTaxInvoiceInfoExample mchtTaxInvoiceInfoExample = new MchtTaxInvoiceInfoExample();
		mchtTaxInvoiceInfoExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId);
		List<MchtTaxInvoiceInfo> mchtTaxInvoiceInfos = mchtTaxInvoiceInfoService
				.selectByExample(mchtTaxInvoiceInfoExample);
		if (mchtTaxInvoiceInfos == null || mchtTaxInvoiceInfos.size() == 0) {
			model.addAttribute("taxInvoiceInfoStatus", DataDicUtil
					.getStatusDesc("BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS",
							"0"));
		} else {
			model.addAttribute("taxInvoiceInfoStatus", DataDicUtil
					.getStatusDesc("BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS",
							mchtTaxInvoiceInfos.get(0).getAuditStatus()));
		}

		// 银行帐号状态
		MchtBankAccountExample mchtBankAccountExample = new MchtBankAccountExample();
		mchtBankAccountExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId);
		List<MchtBankAccount> mchtBankAccounts = mchtBankAccountService
				.selectByExample(mchtBankAccountExample);
		if (mchtBankAccounts == null || mchtBankAccounts.size() == 0) {
			model.addAttribute("mchtBankStatus", DataDicUtil.getStatusDesc(
					"BU_MCHT_BANK_ACCOUNT", "STATUS", "0"));
		} else {
			model.addAttribute("mchtBankStatus", DataDicUtil.getStatusDesc(
					"BU_MCHT_BANK_ACCOUNT", "STATUS", mchtBankAccounts.get(0)
							.getStatus()));
		}

		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		mchtProductTypeExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId).andStatusEqualTo("1");
		int mchtProductTypeCount = mchtProductTypeService
				.countByExample(mchtProductTypeExample);
		model.addAttribute("mchtProductTypeCount", mchtProductTypeCount);

		MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
		mchtProductBrandExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId).andAuditStatusEqualTo("3");
		model.addAttribute("mchtBrandCount",
				mchtProductBrandService.countByExample(mchtProductBrandExample));

		// 如果是待审状态则更新为审核中
		if ("0".equals(mchtInfo.getTotalAuditStatus())) {
			MchtInfo mchtInfo2Update = new MchtInfo();
			mchtInfo2Update.setId(mchtInfo.getId());
			mchtInfo2Update.setTotalAuditStatus("1");
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo2Update);
		}
		if (mchtInfo.getContractDeposit() != null) {
			DecimalFormat df = new DecimalFormat("#.00");
			model.addAttribute("contractDeposit",
					df.format(mchtInfo.getContractDeposit()));
		}

		return "/mcht/totalAudit";
	}

	// 商家银行帐号审核提交
	@RequestMapping(value = "/mcht/totalAuditSave.shtml")
	@ResponseBody
	public Map<String, Object> totalAuditSave(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			String totalAuditStatus = request.getParameter("totalAuditStatus");
			if (StringUtil.isEmpty(totalAuditStatus)) {
				throw new ArgException("请选择审核状态");
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			MchtInfo mchtInfo4Update = new MchtInfo();
			mchtInfo4Update.setId(id);
			mchtInfo4Update.setTotalAuditStatus(totalAuditStatus);
			mchtInfo4Update.setUpdateBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			mchtInfo4Update.setUpdateDate(new Date());
			if (!StringUtils.isEmpty(request.getParameter("contractDeposit"))) {
				mchtInfo4Update.setContractDeposit(new BigDecimal(request
						.getParameter("contractDeposit")));
			}
			if (totalAuditStatus.equals("3")) {// 驳回
				String auditRemarks = request.getParameter("auditRemarks");
				mchtInfo4Update.setTotalAuditRemarks(auditRemarks);
				mchtInfoService.totalAudit(mchtInfo4Update, null);
			} else {// 2.通过
				Date agreementBeginDate = sdf.parse(request
						.getParameter("agreementBeginDate"));
				Date agreementEndDate = sdf.parse(request
						.getParameter("agreementEndDate"));
				String depositContent = request.getParameter("depositContent");

				BigDecimal totalAmt = null;
				if (!StringUtil.isEmpty(request.getParameter("totalAmt"))) {
					totalAmt = new BigDecimal(request.getParameter("totalAmt"));
				}

				mchtInfo4Update.setAgreementBeginDate(agreementBeginDate);
				mchtInfo4Update.setAgreementEndDate(agreementEndDate);
				mchtInfo4Update.setDepositContent(depositContent);
				mchtInfoService.totalAudit(mchtInfo4Update, totalAmt);
			}

		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 商家列表首页
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/shopToOprativeIndex.shtml")
	public ModelAndView shopToOprativeIndex(HttpServletRequest request) {
		String financeConfirmStatus = request.getParameter("financeConfirmStatus");
		String settledType = request.getParameter("settledType");
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		String rtPage = "";
		if (!StringUtils.isEmpty(financeConfirmStatus)) {
			if (financeConfirmStatus.equals("0")) {
				rtPage = "/mcht/financeWaitConfirm";
			} else {
				rtPage = "/mcht/shopToOperative";
			}
		}
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			
			SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
			sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(staffID);
			List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
			resMap.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
			
			resMap.put("mcht_type", statusService.querytStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
			resMap.put("status", statusService.querytStatusList("BU_MCHT_INFO", "STATUS"));
			resMap.put("is_company_inf_perfect", statusService.querytStatusList("BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT"));
			resMap.put("shop_name_audit_status_list", statusService.querytStatusList("BU_MCHT_INFO", "SHOP_NAME_AUDIT_STATUS"));
			resMap.put("total_audit_status_list", statusService.querytStatusList("BU_MCHT_INFO", "TOTAL_AUDIT_STATUS"));
			PlatformContactExample platformContactExample = new PlatformContactExample();
			if (financeConfirmStatus.equals("0")) {
				platformContactExample
						.createCriteria()
						.andDelFlagEqualTo("0")
						.andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()))
						.andContactTypeEqualTo("5").andStatusEqualTo("0");
			} else {
				platformContactExample
						.createCriteria()
						.andDelFlagEqualTo("0")
						.andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()))
						.andContactTypeEqualTo("2").andStatusEqualTo("0");
			}
			if (platformContactService.countByExample(platformContactExample) > 0) {
				resMap.put("isCanGet", true);
			} else {
				resMap.put("isCanGet", false);
			}
			resMap.put("financeConfirmStatus", financeConfirmStatus);
			resMap.put("settledType", settledType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 商家列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/shopToOprativeDataList.shtml")
	@ResponseBody
	public Map<String, Object> shopToOprativeDataList(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount = "0";
		try {
			String financeConfirmStatus = request.getParameter("financeConfirmStatus");
			String settledType = request.getParameter("settledType");
			paramMap.put("financeConfirmStatus", financeConfirmStatus);
			if(!StringUtils.isEmpty(settledType)){
				paramMap.put("settledType", settledType);
			}
			if (!StringUtils.isEmpty(financeConfirmStatus) && financeConfirmStatus.equals("0")) {// 财务待确认
				paramMap.put("not_equals_status", "3"); // 3 关闭 要过滤掉（合作状态为关闭）的商家
			} else {
				paramMap.put("status", "0");
			}
			paramMap.put(
					"andWhereClause",
					" EXISTS (select mc.id from  bu_mcht_contract mc where mc.del_flag='0' and mc.mcht_id=a.id and (mc.archive_status='1' or mc.audit_status='3'))");
			if ("1".equals(request.getParameter("is_my_audit"))) {
				PlatformContactExample platformContactExample = new PlatformContactExample();
				platformContactExample
						.createCriteria()
						.andDelFlagEqualTo("0")
						.andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()))
						.andContactTypeEqualTo("2").andStatusEqualTo("0");
				List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExample);
				if (platformContacts != null && platformContacts.size() > 0) {
					paramMap.put("platContactId", platformContacts.get(0).getId());
				} else {
					paramMap.put("platContactId", "99999999999");
				}
			}
			// 钟表运营部状态，只获取主营类目为钟表
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				paramMap.put("productTypeId", isCwOrgProductTypeId);
			}
			//当[只看本人领取]没有打勾，且 主营类目为空，此时条件限制为：本人负责的类目之内商家
			if(StringUtil.isEmpty(request.getParameter("productTypeId")) && StringUtil.isEmpty(request.getParameter("is_my_audit"))) {
				paramMap.put("my_audit_product_type_staff_id", this.getSessionStaffBean(request).getStaffID());
			}
			paramMap = this.setPageParametersLiger(request, paramMap);
			totalCount = mchtInfoService.queryMchtCount(paramMap);
			dataList = mchtInfoService.selectMchtInfoList(paramMap);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
		return resMap;
	}

	/**
	 * 关店
	 */
	@ResponseBody
	@RequestMapping(value = "/mcht/closeCommit.shtml")
	public Map<String, Object> closeCommit(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if (StringUtil.isEmpty(request.getParameter("mchtId"))) {
				throw new ArgException("商家不存在");
			}
			Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));

			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if (mchtInfo == null) {
				throw new ArgException("商家不存在");
			}

			String remarks = request.getParameter("remarks");
			mchtInfoService.close(getSessionStaffBean(request).getStaffID(),
					mchtId, remarks);

		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	// 品类审核

	@RequestMapping(value = "/mcht/mchtProductTypeCheckIndex.shtml")
	public ModelAndView mchtProductTypeCheckIndex(HttpServletRequest request) {
		String rePage = "/mcht/mchtProductTypeCheckIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		PlatformContactExample platformContactExample = new PlatformContactExample();
		platformContactExample
				.createCriteria()
				.andDelFlagEqualTo("0")
				.andStaffIdEqualTo(
						Integer.valueOf(this.getSessionStaffBean(request)
								.getStaffID())).andContactTypeEqualTo("7")
				.andStatusEqualTo("0");

		if (platformContactService.countByExample(platformContactExample) > 0) {
			resMap.put("isCanGet", true);
		} else {
			resMap.put("isCanGet", false);
		}
		return new ModelAndView(rePage, resMap);
	}

	@RequestMapping(value = "/mcht/mchtProductTypeCheckDataList.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductTypeCheckDataList(
			HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;

		MchtProductTypeCustomExample mchtProductTypeCustomExample = new MchtProductTypeCustomExample();
		MchtProductTypeCustomExample.MchtProductTypeCustomCriteria criteria = mchtProductTypeCustomExample
				.createCriteria();
		criteria.andDelFlagEqualTo("0").andStatusEqualTo("0");
		criteria.andMchtStatusIn("1,2");
		mchtProductTypeCustomExample
				.setOrderByClause(" create_date asc, id asc");
		if (!StringUtil.isEmpty(request.getParameter("companyName"))) {
			criteria.andCompanyNameLike(request.getParameter("companyName"));
		}

		if ("1".equals(request.getParameter("is_my_audit"))) {
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(
							Integer.valueOf(this.getSessionStaffBean(request)
									.getStaffID())).andContactTypeEqualTo("7")
					.andStatusEqualTo("0");
			List<PlatformContact> platformContacts = platformContactService
					.selectByExample(platformContactExample);
			if (platformContacts != null && platformContacts.size() > 0) {
				criteria.andPlatformContactIdEqualTo(platformContacts.get(0)
						.getId());
			} else {
				criteria.andPlatformContactIdEqualTo(999999999);
			}
		}

		totalCount = mchtProductTypeService
				.countMchtProductTypeCustomByExample(mchtProductTypeCustomExample);

		mchtProductTypeCustomExample.setLimitSize(page.getLimitSize());
		mchtProductTypeCustomExample.setLimitStart(page.getLimitStart());

		List<MchtProductTypeCustom> mchtProductTypeCustoms = mchtProductTypeService
				.selectMchtProductTypeCustomByExample(mchtProductTypeCustomExample);

		resMap.put("Rows", mchtProductTypeCustoms);
		resMap.put("Total", totalCount);

		return resMap;
	}

	private String getdata(Date time) {
		if (time == null) {
			return "";
		}
		// 时间戳转化为Sting或Date
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(time);
		return date;
	}

	/**
	 * 商家列表首页
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/batchAllocationContactindex.shtml")
	public ModelAndView batchAllocationContactindex(HttpServletRequest request) {
		String rtPage = "mcht/batchAllocationContactindex";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();

		try {
			resMap.put("mcht_type",
					DataDicUtil.getStatusList("BU_MCHT_INFO", "MCHT_TYPE"));
			resMap.put("status",
					DataDicUtil.getStatusList("BU_MCHT_INFO", "STATUS"));
			resMap.put("total_audit_status_list", DataDicUtil.getStatusList(
					"BU_MCHT_INFO", "TOTAL_AUDIT_STATUS"));
			resMap.put("contract_audit_status_list", DataDicUtil.getStatusList(
					"BU_MCHT_CONTRACT", "AUDIT_STATUS"));
			resMap.put("contract_archive_status_list", DataDicUtil
					.getStatusList("BU_MCHT_CONTRACT", "ARCHIVE_STATUS"));
			resMap.put("platform_contact_type_list", DataDicUtil.getStatusList(
					"BU_PLATFORM_CONTACT", "CONTACT_TYPE"));
			resMap.put("is_company_inf_perfect", DataDicUtil.getStatusList(
					"BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT"));
			resMap.put("is_manage_self",
					DataDicUtil.getStatusList("BU_MCHT_INFO", "IS_MANAGE_SELF"));

			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = productTypeExample
					.createCriteria();
			productTypeCriteria.andParentIdEqualTo(1).andDelFlagEqualTo("0");

			// 钟表运营部状态，只获取主营类目为钟表
			String isCwOrgStatus = this.getParamSession(request,
					"isCwOrgStatus");
			if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request,
						"isCwOrgProductTypeId");
				if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer
							.parseInt(isCwOrgProductTypeId));
				}
			}
			resMap.put("isCwOrgStatus", isCwOrgStatus);

			productTypeExample.setOrderByClause(" seq_no");
			List<ProductType> productTypes = productTypeService
					.selectByExample(productTypeExample);
			resMap.put("productTypes", productTypes);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 商家列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/batchAllocationContactDataList.shtml")
	@ResponseBody
	public Map<String, Object> batchAllocationContactDataList(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount = "0";
		try {
			paramMap = this.setPageParametersLiger(request, paramMap);

			if (!StringUtil.isEmpty(request.getParameter("myContactType"))) {
				PlatformContactExample platformContactExample = new PlatformContactExample();
				platformContactExample
						.createCriteria()
						.andDelFlagEqualTo("0")
						// .andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()))
						.andContactTypeEqualTo(
								request.getParameter("myContactType"))
						.andStatusEqualTo("0");
				List<PlatformContact> platformContacts = platformContactService
						.selectByExample(platformContactExample);
				if (platformContacts == null || platformContacts.size() == 0) {
					paramMap.put("platContactId", 99999999);
				} else {
					paramMap.put("platContactId", platformContacts.get(0)
							.getId());
				}
			}

			// 钟表运营部状态，只获取主营类目为钟表
			String isCwOrgStatus = this.getParamSession(request,
					"isCwOrgStatus");
			if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request,
						"isCwOrgProductTypeId");
				if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					paramMap.put("productTypeId", isCwOrgProductTypeId);
				}
			}
			
			//商家序号支持多个
			List<String> mchtCodeList=new ArrayList<String>();
			String[] mchtCode=request.getParameter("mchtcodelist").split(",");
			for (String mchtcodelist : mchtCode) {
				 mchtCodeList.add(mchtcodelist);
			}
			paramMap.put("mchtCodeList", mchtCodeList);

			totalCount = mchtInfoService.queryMchtCount(paramMap);
			dataList = mchtInfoService.selectMchtInfoList(paramMap);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
		return resMap;
	}

	/**
	 *
	 * @Title getBatchAllocationContact
	 * @Description TODO(获取对接联系人)
	 * @author pengl
	 * @date 2017年9月26日 下午4:49:53
	 */
	@RequestMapping(value = "/mcht/allotPlatformContact.shtml")
	public ModelAndView allotPlatformContact(HttpServletRequest request,
			String ids) {
		ModelAndView m = new ModelAndView("/mcht/allotPlatformContact");
		PlatformContactCustomExample platformContactExample = new PlatformContactCustomExample();
		PlatformContactCustomExample.PlatformContactCustomCriteria createCriteria = platformContactExample
				.createCriteria();
		createCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0")
				.andContactTypeEqualTo("1");// 默认对接联系人类型
		// .andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
		platformContactExample.setOrderByClause("id asc");
		// 对接人数据
		List<PlatformContactCustom> platformContactCustoms = platformContactService
				.selectPlatformContactCustomByExample(platformContactExample);
		m.addObject("ids", ids);
		m.addObject("contactTypeList", DataDicUtil.getStatusList(
				"BU_PLATFORM_CONTACT", "CONTACT_TYPE"));
		m.addObject("platformContactCustomList", platformContactCustoms);
		return m;
	}

	/**
	 *
	 * @Title getPlatformContactList
	 * @Description TODO(异步获取平台对接人信息)
	 * @author pengl
	 * @date 2017年9月27日 上午10:38:58
	 */
	@ResponseBody
	@RequestMapping(value = "/mcht/getPlatformContactList.shtml")
	public Map<String, Object> getPlatformContactList(
			HttpServletRequest request, String contactType) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		try {
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample.createCriteria().andDelFlagEqualTo("0")
					.andStatusEqualTo("0")
					// .andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()))
					.andContactTypeEqualTo(contactType);
			List<PlatformContact> platformContactList = platformContactService
					.selectByExample(platformContactExample);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			resMap.put("platformContactList", platformContactList);
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		return resMap;
	}

	/**
	 * 
	 * @Title updatePlatformContactList
	 * @Description TODO(批量更改商家平台对接人)
	 * @author pengl
	 * @date 2017年9月27日 下午2:10:20
	 */
	@RequestMapping(value = "/mcht/updatePlatformContactList.shtml")
	public ModelAndView updatePlatformContactList(HttpServletRequest request,
			String ids, String contactType, String platformContactId) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		Integer userId = Integer.valueOf(this.getSessionStaffBean(request)
				.getStaffID());
		Boolean flag = mchtPlatformContactService.updatePlatformContactList(
				request, ids, contactType, platformContactId, userId);
		if (flag) {
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} else {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 
	 * @Title getMchtListManager
	 * @Description TODO(这里用一句话描述这个方法的作用)
	 * @author pengl
	 * @throws ParseException 
	 * @date 2017年12月22日 上午10:07:15
	 */
	@RequestMapping(value = "/mcht/getMchtListManager.shtml")
	public ModelAndView getMchtListManager(HttpServletRequest request,
			String statusPage) throws ParseException {
		ModelAndView m = new ModelAndView();
		if (statusPage.equals("1")) { // 1.商家信息导出
			String DateBegin = request.getParameter("EachDay");
			String DateEnd = request.getParameter("EachDay");
			m.addObject("DateBegin", DateBegin);
			m.addObject("DateEnd", DateEnd);
			if ("NaN-aN-aN".equals(request.getParameter("EachDay"))) {// 查看所有合计总数
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String dateEnd = df.format(new Date());
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				String dateBegin = df.format(calendar.getTime());
				m.addObject("DateEnd", dateEnd);
				m.addObject("DateBegin", dateBegin);
			}
			m.setViewName("/mcht/getMchtInfoAuditList");
		}else if (statusPage.equals("3")) {		
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
			String DateBegin = request.getParameter("eachMonth");
			Date dateBegin = df.parse(DateBegin);
			
			Calendar c= Calendar.getInstance();//获取某月第一天
			c.setTime(dateBegin);
			c.set(Calendar.DAY_OF_MONTH,1);
			String datebegin = df.format(c.getTime());
			
			Calendar c1= Calendar.getInstance();//获取某月最后一天
			c1.setTime(dateBegin);
			c1.set(Calendar.DAY_OF_MONTH, c1.getActualMaximum(Calendar.DAY_OF_MONTH));
			String dateEnd=df.format(c1.getTime());
	
			m.addObject("DateEnd", dateEnd);
			m.addObject("DateBegin", datebegin);
			
			m.setViewName("/mcht/getMchtInfoAuditList");
		} else { // 2.商家信息查询
			m.setViewName("/mcht/getMchtInfoList");
		}

		m.addObject("statusPage", statusPage);
		m.addObject("mchtTypeList",
				DataDicUtil.getStatusList("BU_MCHT_INFO", "MCHT_TYPE")); // 模式
		m.addObject("statusList",
				DataDicUtil.getStatusList("BU_MCHT_INFO", "STATUS")); // 合作状态
		m.addObject("mchtproductbrandlist", DataDicUtil.getStatusList(
				"BU_MCHT_PRODUCT_BRAND", "IS_SPECIALLY_APPROVED"));// 招商标记

		List<String> endYearList = new ArrayList<String>();
		endYearList.add("2017");
		endYearList.add("2018");
		endYearList.add("2019");
		endYearList.add("2020");
		m.addObject("endYearList", endYearList); // 到期年份
		List<String> endMonthList = new ArrayList<String>();
		for (int i = 1; i < 13; i++) {
			if (i < 10) {
				endMonthList.add("0" + i);
			} else {
				endMonthList.add("" + i);
			}
		}
		m.addObject("endMonthList", endMonthList); // 到期月份
		m.addObject("contactTypeList", DataDicUtil.getStatusList(
				"BU_PLATFORM_CONTACT", "CONTACT_TYPE")); // 联系人类型
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample
				.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1")
				.andDelFlagEqualTo("0");

		// 钟表运营部状态，只获取主营类目为钟表
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request,
					"isCwOrgProductTypeId");
			if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer
						.parseInt(isCwOrgProductTypeId));
			}
		}
		m.addObject("isCwOrgStatus", isCwOrgStatus);

		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService
				.selectByExample(productTypeExample);
		m.addObject("productTypeList", productTypeList); // 1级类目
		PlatformContactExample platformContactExample = new PlatformContactExample();
		List<String> contactTypeList = new ArrayList<String>();
		contactTypeList.add("1");
		contactTypeList.add("2");
		contactTypeList.add("7");
		platformContactExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("0").andContactTypeIn(contactTypeList);
		List<PlatformContact> platformContactList = platformContactService
				.selectByExample(platformContactExample);
		List<PlatformContact> zsPlatformContact = new ArrayList<PlatformContact>();
		List<PlatformContact> zyPlatformContact = new ArrayList<PlatformContact>();
		List<PlatformContact> fwPlatformContact = new ArrayList<PlatformContact>();
		for (PlatformContact platformContact : platformContactList) {
			if (platformContact.getContactType().equals("1")) {
				zsPlatformContact.add(platformContact);
			} else if (platformContact.getContactType().equals("2")) {
				zyPlatformContact.add(platformContact);
			} else if (platformContact.getContactType().equals("7")) {
				fwPlatformContact.add(platformContact);
			}
		}

		m.addObject("zsPlatformContactList", zsPlatformContact); // 招商对接
		m.addObject("zyPlatformContactList", zyPlatformContact); // 运营对接
		m.addObject("fwPlatformContactList", fwPlatformContact); // 法务对接
		return m;
	}

	/**
	 *
	 * @Title getMchtList
	 * @Description TODO(这里用一句话描述这个方法的作用)
	 * @author pengl
	 * @date 2017年12月22日 下午5:42:50
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/mcht/getMchtList.shtml")
	public Map<String, Object> getMchtList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Object> dataList = new ArrayList<Object>();
		Integer totalCount = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample
					.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			mchtInfoCustomExample.setLimitSize(page.getLimitSize());
			mchtInfoCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoCustomExample.setOrderByClause(" id desc");
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) { // 序号
				mchtInfoCustomCriteria.andMchtCodeEqualTo(request
						.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtType"))) { // 模式
				mchtInfoCustomCriteria.andMchtTypeEqualTo(request
						.getParameter("mchtType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) { // 名称
				mchtInfoCustomCriteria.andMchtNameLike(request
						.getParameter("mchtName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) { // 合作状态
				mchtInfoCustomCriteria.andStatusEqualTo(request
						.getParameter("status"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endYear"))) { // 到期年份
				mchtInfoCustomCriteria.andEndYearEqualTo(request
						.getParameter("endYear"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endMonth"))) { // 到期月份
				mchtInfoCustomCriteria.andEndMonthEqualTo(request
						.getParameter("endMonth"));
			}
			if (!StringUtil.isEmpty(request.getParameter("contactType"))) { // 本人对接
				mchtInfoCustomCriteria.andContactTypeEqualTo(request
						.getParameter("contactType"), Integer.valueOf(this
						.getSessionStaffBean(request).getStaffID()));
			}
			if (!StringUtil.isEmpty(request.getParameter("brandName"))) { // 品牌
				mchtInfoCustomCriteria.andBrandNameEqualTo(request
						.getParameter("brandName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("startCreateDate"))) { // 创建日期
				mchtInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf
						.parse(request.getParameter("startCreateDate")
								+ " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				mchtInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf
						.parse(request.getParameter("endCreateDate")
								+ " 23:59:59"));
			}

			// 钟表运营部状态，只获取主营类目为钟表
			String isCwOrgStatus = this.getParamSession(request,
					"isCwOrgStatus");
			if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request,"isCwOrgProductTypeId");
				if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					mchtInfoCustomCriteria.andProductTypeIdEqualTo(isCwOrgProductTypeId);
				}
			} else {
				if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) { // 类目
					mchtInfoCustomCriteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
				}
			}

			if (!StringUtil.isEmpty(request.getParameter("zsPlatformContact"))) { //招商对接
				mchtInfoCustomCriteria.andPlatformContactEqualTo("1", request.getParameter("zsPlatformContact"));
			}
			if (!StringUtil.isEmpty(request.getParameter("zyPlatformContact"))) { //运营对接
				mchtInfoCustomCriteria.andPlatformContactEqualTo("2", request.getParameter("zyPlatformContact"));
			}
			if (!StringUtil.isEmpty(request.getParameter("fwPlatformContact"))) { //法务对接
				mchtInfoCustomCriteria.andPlatformContactEqualTo("7",
						request.getParameter("fwPlatformContact"));
			}
			if (!StringUtil
					.isEmpty(request.getParameter("startTotalAuditDate"))) { //法务审核日期
				mchtInfoCustomCriteria
						.andTotalAuditDateGreaterThanOrEqualTo(sdf
								.parse(request
										.getParameter("startTotalAuditDate")
										+ " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endTotalAuditDate"))) {
				mchtInfoCustomCriteria.andTotalAuditDateLessThanOrEqualTo(sdf
						.parse(request.getParameter("endTotalAuditDate")
								+ " 23:59:59"));
			}
			if (!StringUtil.isEmpty(request
					.getParameter("startCooperateBeginDate"))) { // 开通日期
				mchtInfoCustomCriteria
						.andCooperateBeginDateGreaterThanOrEqualTo(sdf.parse(request
								.getParameter("startCooperateBeginDate")
								+ " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request
					.getParameter("endCooperateBeginDate"))) {
				mchtInfoCustomCriteria
						.andCooperateBeginDateLessThanOrEqualTo(sdf
								.parse(request
										.getParameter("endCooperateBeginDate")
										+ " 23:59:59"));
			}
			if (!StringUtil.isEmpty(request
					.getParameter("isSpeciallyApprovedDesc"))) {
				mchtInfoCustomCriteria
						.andProductisspeciallyapprovedEqualTo((request
								.getParameter("isSpeciallyApprovedDesc")));
			}
			String totalauditstatus = request.getParameter("totalauditstatus");
			if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("0"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("0");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("1"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("1");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("2"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("2");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("3"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("3");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("4"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("4");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("5"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusNotEqualTo("4")
						.andTotalAuditStatusIsNotNull();
				;
			}
			List<MchtInfoCustom> mchtInfoCustomList = mchtInfoService
					.selectByExample(mchtInfoCustomExample);
			totalCount = mchtInfoService.countByExample(mchtInfoCustomExample);

			for (MchtInfoCustom mchtInfoCustom : mchtInfoCustomList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("mchtInfoCustom", mchtInfoCustom);

				MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample(); //品牌
				MchtProductBrandExample.Criteria MchtProductBrandCriteria = mchtProductBrandExample.createCriteria();
				MchtProductBrandCriteria.andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfoCustom.getId());
				MchtProductBrandCriteria.andStatusNotEqualTo("3");
				mchtProductBrandExample.setOrderByClause(" audit_status asc, status asc");
				map.put("mchtProductBrandList", mchtProductBrandService
						.selectByCustomExample(mchtProductBrandExample)); // 品牌

				MchtProductTypeCustomExample mchtProductTypeExample = new MchtProductTypeCustomExample(); // 类目
				MchtProductTypeCustomCriteria mchtProductTypeCustomCriteria = mchtProductTypeExample
						.createCriteria();
				mchtProductTypeCustomCriteria.andDelFlagEqualTo("0");
				mchtProductTypeCustomCriteria.andStatusNotEqualTo("2");
				mchtProductTypeExample.setOrderByClause(" status asc");
				mchtProductTypeCustomCriteria.andMchtIdEqualTo(mchtInfoCustom
						.getId());
				map.put("mchtProductTypeCustomList",
						mchtProductTypeService
								.selectMchtProductTypeCustomByExample(mchtProductTypeExample)); // 类目

				MchtInfoChangeLogExample mchtInfoChangeLogExample = new MchtInfoChangeLogExample(); // 审核日志
				MchtInfoChangeLogExample.Criteria mchtInfoChangeLogCriteria = mchtInfoChangeLogExample
						.createCriteria();
				mchtInfoChangeLogCriteria.andDelFlagEqualTo("0");
				mchtInfoChangeLogExample.setOrderByClause(" id desc");
				mchtInfoChangeLogExample.setLimitSize(5);
				mchtInfoChangeLogExample.setLimitStart(0);
				mchtInfoChangeLogCriteria.andMchtIdEqualTo(mchtInfoCustom
						.getId());
				map.put("mchtInfoChangeLogList",
						mchtInfoChangeLogService
								.selectMchtInfoChangeLogCustomByExample(mchtInfoChangeLogExample)); // 审核日志
				dataList.add(map);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataList = new ArrayList();
			totalCount = 0;
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * 
	 * @Title mchtListExcel
	 * @Description TODO(导出 Excel)
	 * @author pengl
	 * @date 2017年12月25日 下午2:49:27
	 */
	@ResponseBody
	@RequestMapping(value = "/mcht/exportMchtListExcel.shtml")
	public void exportMchtListExcel(HttpServletRequest request,
			HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample
					.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			mchtInfoCustomExample.setOrderByClause(" id desc");
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) { // 序号
				mchtInfoCustomCriteria.andMchtCodeEqualTo(request
						.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtType"))) { // 模式
				mchtInfoCustomCriteria.andMchtTypeEqualTo(request
						.getParameter("mchtType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) { // 名称
				mchtInfoCustomCriteria.andMchtNameLike(request
						.getParameter("mchtName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) { // 合作状态
				mchtInfoCustomCriteria.andStatusEqualTo(request
						.getParameter("status"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endYear"))) { // 到期年份
				mchtInfoCustomCriteria.andEndYearEqualTo(request
						.getParameter("endYear"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endMonth"))) { // 到期月份
				mchtInfoCustomCriteria.andEndMonthEqualTo(request
						.getParameter("endMonth"));
			}
			if (!StringUtil.isEmpty(request.getParameter("contactType"))) { // 本人对接
				mchtInfoCustomCriteria.andContactTypeEqualTo(request
						.getParameter("contactType"), Integer.valueOf(this
						.getSessionStaffBean(request).getStaffID()));
			}
			if (!StringUtil.isEmpty(request.getParameter("brandName"))) { // 品牌
				mchtInfoCustomCriteria.andBrandNameEqualTo(request
						.getParameter("brandName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("startCreateDate"))) { // 创建日期
				mchtInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf
						.parse(request.getParameter("startCreateDate")
								+ " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				mchtInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf
						.parse(request.getParameter("endCreateDate")
								+ " 23:59:59"));
			}

			// 钟表运营部状态，只获取主营类目为钟表
			String isCwOrgStatus = this.getParamSession(request,
					"isCwOrgStatus");
			if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request,
						"isCwOrgProductTypeId");
				if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					mchtInfoCustomCriteria
							.andProductTypeIdEqualTo(isCwOrgProductTypeId);
				}
			} else {
				if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) { // 类目
					mchtInfoCustomCriteria.andProductTypeIdEqualTo(request
							.getParameter("productTypeId"));
				}
			}

			if (!StringUtil.isEmpty(request.getParameter("zsPlatformContact"))) { // 招商对接
				mchtInfoCustomCriteria.andPlatformContactEqualTo("1",
						request.getParameter("zsPlatformContact"));
			}
			if (!StringUtil.isEmpty(request.getParameter("zyPlatformContact"))) { // 运营对接
				mchtInfoCustomCriteria.andPlatformContactEqualTo("2",
						request.getParameter("zyPlatformContact"));
			}
			if (!StringUtil.isEmpty(request.getParameter("fwPlatformContact"))) { // 法务对接
				mchtInfoCustomCriteria.andPlatformContactEqualTo("7",
						request.getParameter("fwPlatformContact"));
			}
			if (!StringUtil
					.isEmpty(request.getParameter("startTotalAuditDate"))) { // 法务审核日期
				mchtInfoCustomCriteria
						.andTotalAuditDateGreaterThanOrEqualTo(sdf
								.parse(request
										.getParameter("startTotalAuditDate")
										+ " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endTotalAuditDate"))) {
				mchtInfoCustomCriteria.andTotalAuditDateLessThanOrEqualTo(sdf
						.parse(request.getParameter("endTotalAuditDate")
								+ " 23:59:59"));
			}
			if (!StringUtil.isEmpty(request
					.getParameter("startCooperateBeginDate"))) { // 开通日期
				mchtInfoCustomCriteria
						.andCooperateBeginDateGreaterThanOrEqualTo(sdf.parse(request
								.getParameter("startCooperateBeginDate")
								+ " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request
					.getParameter("endCooperateBeginDate"))) {
				mchtInfoCustomCriteria
						.andCooperateBeginDateLessThanOrEqualTo(sdf
								.parse(request
										.getParameter("endCooperateBeginDate")
										+ " 23:59:59"));
			}
			if (!StringUtil.isEmpty(request
					.getParameter("isSpeciallyApprovedDesc"))) {
				mchtInfoCustomCriteria
						.andProductisspeciallyapprovedEqualTo((request
								.getParameter("isSpeciallyApprovedDesc")));
			}
			String totalauditstatus = request.getParameter("totalauditstatus");
			if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("0"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("0");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("1"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("1");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("2"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("2");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("3"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("3");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("4"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("4");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus
					.equals("5"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusNotEqualTo("4")
						.andTotalAuditStatusIsNotNull();
			}
			List<MchtInfoCustom> mchtInfoCustomList = mchtInfoService
					.selectByExample(mchtInfoCustomExample);
			if (this.getSessionStaffBean(request).getStaffCode()
					.equalsIgnoreCase("admin")) {
				String[] titles = { "店铺创建时间", "商家序号", "公司名称", "商家用户名", "联系人",
						"店铺名称", "品牌（佣金、状态）", "类目", "开通时间", "最后登录时间", "邮箱",
						"电话", "公司总机", "资质总审核状态", "合作状态", "客户信息", "平台对接人" };
				ExcelBean excelBean = new ExcelBean("导出商家审核列表.xls", "导出商家审核列表",
						titles);
				List<String[]> datas = new ArrayList<String[]>();

				for (MchtInfoCustom mchtInfoCustom : mchtInfoCustomList) {
					MchtProductTypeCustomExample mchtProductTypeExample = new MchtProductTypeCustomExample(); // 类目
					MchtProductTypeCustomCriteria mchtProductTypeCustomCriteria = mchtProductTypeExample
							.createCriteria();
					mchtProductTypeCustomCriteria.andDelFlagEqualTo("0");
					mchtProductTypeCustomCriteria.andStatusNotEqualTo("2");
					mchtProductTypeExample.setOrderByClause(" status asc");
					mchtProductTypeCustomCriteria
							.andMchtIdEqualTo(mchtInfoCustom.getId());
					List<MchtProductTypeCustom> mchtProductTypeList = mchtProductTypeService
							.selectMchtProductTypeCustomByExample(mchtProductTypeExample); // 类目
					String mchtProductTypeStr = "";
					for (MchtProductTypeCustom mchtProductTypeCustom : mchtProductTypeList) {
						if (!"".equals(mchtProductTypeStr)) {
							mchtProductTypeStr += "\n";
						}
						if (!StringUtil.isEmpty(mchtProductTypeCustom
								.getStatusDesc())) {
							mchtProductTypeStr += "["
									+ mchtProductTypeCustom.getStatusDesc()
									+ "]";
						}
						if (!StringUtil.isEmpty(mchtProductTypeCustom
								.getProductTypeName())) {
							mchtProductTypeStr += mchtProductTypeCustom
									.getProductTypeName();
						}
					}

					MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample(); // 品牌
					MchtProductBrandExample.Criteria MchtProductBrandCriteria = mchtProductBrandExample
							.createCriteria();
					MchtProductBrandCriteria.andDelFlagEqualTo("0")
							.andMchtIdEqualTo(mchtInfoCustom.getId());
					MchtProductBrandCriteria.andStatusNotEqualTo("3");
					mchtProductBrandExample
							.setOrderByClause(" audit_status asc, status asc");
					List<MchtProductBrandCustom> mchtProductBrandCustomList = mchtProductBrandService
							.selectByCustomExample(mchtProductBrandExample); // 品牌
					String mchtProductBrandStr = "";
					for (MchtProductBrandCustom mchtProductBrandCustom : mchtProductBrandCustomList) {
						if (!"".equals(mchtProductBrandStr)) {
							mchtProductBrandStr += "\n";
						}
						if (!StringUtil.isEmpty(mchtProductBrandCustom
								.getAuditStatusDesc())) {
							mchtProductBrandStr += "["
									+ mchtProductBrandCustom
											.getAuditStatusDesc() + "]";
						}
						if (!StringUtil.isEmpty(mchtProductBrandCustom
								.getStatusDesc())) {
							mchtProductBrandStr += "["
									+ mchtProductBrandCustom.getStatusDesc()
									+ "]";
						}
						if (!StringUtil.isEmpty(mchtProductBrandCustom
								.getBrandName())) {
							mchtProductBrandStr += mchtProductBrandCustom
									.getBrandName();
						} else {
							mchtProductBrandStr += mchtProductBrandCustom
									.getProductBrandName();
						}
						if (mchtProductBrandCustom.getPopCommissionRate() != null) {
							mchtProductBrandStr += "("
									+ mchtProductBrandCustom
											.getPopCommissionRate() + ")";
						}
					}
					String mchtContactStr = "公司信息\n"; // 商家对接人
					if (!StringUtil.isEmpty(mchtInfoCustom.getCompanyName()))
						mchtContactStr += "公司名称："
								+ mchtInfoCustom.getCompanyName() + "\n";
					if (mchtInfoCustom.getRegCapital() != null)
						mchtContactStr += "注册资金："
								+ mchtInfoCustom.getRegCapital() + "万元\n";
					if (!StringUtil
							.isEmpty(mchtInfoCustom.getCorporationName()))
						mchtContactStr += "法定代表人："
								+ mchtInfoCustom.getCorporationName() + "\n";
					if (!StringUtil.isEmpty(mchtInfoCustom.getContactAddress()))
						mchtContactStr += "公司联系地址："
								+ mchtInfoCustom.getContactAddress() + "\n";
					mchtContactStr += "\n其它网店网址\n";
					if (!StringUtil.isEmpty(mchtInfoCustom
							.getSettledContactName()))
						mchtContactStr += "[天猫]："
								+ mchtInfoCustom.getSettledContactName() + "\n";
					if (!StringUtil.isEmpty(mchtInfoCustom
							.getSettledTaobaoShop()))
						mchtContactStr += "[淘宝]："
								+ mchtInfoCustom.getSettledTaobaoShop() + "\n";
					if (!StringUtil.isEmpty(mchtInfoCustom.getSettledJdShop()))
						mchtContactStr += "[京东]："
								+ mchtInfoCustom.getSettledJdShop() + "\n";
					if (!StringUtil
							.isEmpty(mchtInfoCustom.getSettledVipsShop()))
						mchtContactStr += "[唯品会]："
								+ mchtInfoCustom.getSettledVipsShop() + "\n";
					if (!StringUtil.isEmpty(mchtInfoCustom
							.getSettledOtherShop()))
						mchtContactStr += "[其他]："
								+ mchtInfoCustom.getSettledOtherShop() + "\n";
					if (!StringUtil.isEmpty(mchtInfoCustom
							.getSettledContactName()))
						mchtContactStr += "申请人姓名："
								+ mchtInfoCustom.getSettledContactName() + "\n";
					if (!StringUtil.isEmpty(mchtInfoCustom.getSettledPhone()))
						mchtContactStr += "申请人手机："
								+ mchtInfoCustom.getSettledPhone() + "\n";
					if (!StringUtil.isEmpty(mchtInfoCustom.getSettledEmail()))
						mchtContactStr += "申请人邮箱："
								+ mchtInfoCustom.getSettledEmail() + "\n";
					mchtContactStr += "\n商家对接人信息\n";
					String dsMchtCodeStr = "[电商总负责人]";
					if (!StringUtil.isEmpty(mchtInfoCustom
							.getDsMchtContactName()))
						dsMchtCodeStr += mchtInfoCustom.getDsMchtContactName()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getDsMchtMobile()))
						dsMchtCodeStr += mchtInfoCustom.getDsMchtMobile()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getDsMchtEmail()))
						dsMchtCodeStr += mchtInfoCustom.getDsMchtEmail() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getDsMchtQq()))
						dsMchtCodeStr += mchtInfoCustom.getDsMchtQq() + "\n";
					if (!dsMchtCodeStr.equals("[电商总负责人]"))
						mchtContactStr += dsMchtCodeStr;

					String yyMchtCodeStr = "[运营对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom
							.getYyMchtContactName()))
						yyMchtCodeStr += mchtInfoCustom.getYyMchtContactName()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getYyMchtMobile()))
						yyMchtCodeStr += mchtInfoCustom.getYyMchtMobile()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getYyMchtEmail()))
						yyMchtCodeStr += mchtInfoCustom.getYyMchtEmail() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getYyMchtQq()))
						yyMchtCodeStr += mchtInfoCustom.getYyMchtQq() + "\n";
					if (!yyMchtCodeStr.equals("[运营对接人]"))
						mchtContactStr += yyMchtCodeStr;

					String ddMchtCodeStr = "[订单对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom
							.getDdMchtContactName()))
						ddMchtCodeStr += mchtInfoCustom.getDdMchtContactName()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getDdMchtMobile()))
						ddMchtCodeStr += mchtInfoCustom.getDdMchtMobile()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getDdMchtEmail()))
						ddMchtCodeStr += mchtInfoCustom.getDdMchtEmail() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getDdMchtQq()))
						ddMchtCodeStr += mchtInfoCustom.getDdMchtQq() + "\n";
					if (!ddMchtCodeStr.equals("[订单对接人]"))
						mchtContactStr += ddMchtCodeStr;

					String shMchtCodeStr = "[售后对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom
							.getShMchtContactName()))
						shMchtCodeStr += mchtInfoCustom.getShMchtContactName()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getShMchtMobile()))
						shMchtCodeStr += mchtInfoCustom.getShMchtMobile()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getShMchtEmail()))
						shMchtCodeStr += mchtInfoCustom.getShMchtEmail() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getShMchtQq()))
						shMchtCodeStr += mchtInfoCustom.getShMchtQq() + "\n";
					if (!shMchtCodeStr.equals("[售后对接人]"))
						mchtContactStr += shMchtCodeStr;

					String cwMchtCodeStr = "[财务对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom
							.getCwMchtContactName()))
						cwMchtCodeStr += mchtInfoCustom.getCwMchtContactName()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getCwMchtMobile()))
						cwMchtCodeStr += mchtInfoCustom.getCwMchtMobile()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getCwMchtEmail()))
						cwMchtCodeStr += mchtInfoCustom.getCwMchtEmail() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getCwMchtQq()))
						cwMchtCodeStr += mchtInfoCustom.getCwMchtQq() + "\n";
					if (!cwMchtCodeStr.equals("[财务对接人]"))
						mchtContactStr += cwMchtCodeStr;

					String kfMchtCodeStr = "[客服对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom
							.getKfMchtContactName()))
						kfMchtCodeStr += mchtInfoCustom.getKfMchtContactName()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getKfMchtMobile()))
						kfMchtCodeStr += mchtInfoCustom.getKfMchtMobile()
								+ "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getKfMchtEmail()))
						kfMchtCodeStr += mchtInfoCustom.getKfMchtEmail() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getKfMchtQq()))
						kfMchtCodeStr += mchtInfoCustom.getKfMchtQq();
					if (!kfMchtCodeStr.equals("[客服对接人]"))
						mchtContactStr += kfMchtCodeStr;

					String mchtPlatformContactStr = "";
					String zsCodeStr = "[招商对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getZsContactName()))
						zsCodeStr += mchtInfoCustom.getZsContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getZsMobile()))
						zsCodeStr += mchtInfoCustom.getZsMobile() + "\n";
					if (!zsCodeStr.equals("[招商对接人]"))
						mchtPlatformContactStr += zsCodeStr;
					String yyCodeStr = "[运营对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getYyContactName()))
						yyCodeStr += mchtInfoCustom.getYyContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getYyMobile()))
						yyCodeStr += mchtInfoCustom.getYyMobile() + "\n";
					if (!yyCodeStr.equals("[运营对接人]"))
						mchtPlatformContactStr += yyCodeStr;
					String ddCodeStr = "[订单对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getDdContactName()))
						ddCodeStr += mchtInfoCustom.getDdContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getDdMobile()))
						ddCodeStr += mchtInfoCustom.getDdMobile() + "\n";
					if (!ddCodeStr.equals("[订单对接人]"))
						mchtPlatformContactStr += ddCodeStr;
					String shCodeStr = "[售后对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getShContactName()))
						shCodeStr += mchtInfoCustom.getShContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getShMobile()))
						shCodeStr += mchtInfoCustom.getShMobile() + "\n";
					if (!shCodeStr.equals("[售后对接人]"))
						mchtPlatformContactStr += shCodeStr;
					String cwCodeStr = "[财务对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getCwContactName()))
						cwCodeStr += mchtInfoCustom.getCwContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getCwMobile()))
						cwCodeStr += mchtInfoCustom.getCwMobile() + "\n";
					if (!cwCodeStr.equals("[财务对接人]"))
						mchtPlatformContactStr += cwCodeStr;
					String kfCodeStr = "[客服对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getKfContactName()))
						kfCodeStr += mchtInfoCustom.getKfContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getKfMobile()))
						kfCodeStr += mchtInfoCustom.getKfMobile() + "\n";
					if (!kfCodeStr.equals("[客服对接人]"))
						mchtPlatformContactStr += kfCodeStr;
					String fwCodeStr = "[法务对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getFwContactName()))
						fwCodeStr += mchtInfoCustom.getFwContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getFwMobile()))
						fwCodeStr += mchtInfoCustom.getFwMobile() + "\n";
					if (!fwCodeStr.equals("[法务对接人]"))
						mchtPlatformContactStr += fwCodeStr;
					String sjCodeStr = "[商家服务专员]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getSjContactName()))
						sjCodeStr += mchtInfoCustom.getSjContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getSjMobile()))
						sjCodeStr += mchtInfoCustom.getSjMobile() + "\n";
					if (!sjCodeStr.equals("[商家服务专员]"))
						mchtPlatformContactStr += sjCodeStr;
					String[] data = {
							mchtInfoCustom.getCreateDate() == null ? "" : sdf
									.format(mchtInfoCustom.getCreateDate()),
							mchtInfoCustom.getMchtCode() == null ? ""
									: mchtInfoCustom.getMchtCode(),
							(mchtInfoCustom.getIsCompanyInfPerfectDesc() == null ? ""
									: ("["
											+ mchtInfoCustom
													.getIsCompanyInfPerfectDesc() + "]"))
									+ (mchtInfoCustom.getCompanyName() == null ? ""
											: mchtInfoCustom.getCompanyName()),
							mchtInfoCustom.getMchtUserCode() == null ? ""
									: mchtInfoCustom.getMchtUserCode(),
							mchtInfoCustom.getMchtUserName() == null ? ""
									: mchtInfoCustom.getMchtUserName(),
							(mchtInfoCustom.getShopNameAuditStatusDesc() == null ? ""
									: ("["
											+ mchtInfoCustom
													.getShopNameAuditStatusDesc() + "]"))
									+ mchtInfoCustom.getShopName(),
							mchtProductBrandStr,
							mchtProductTypeStr,
							mchtInfoCustom.getCooperateBeginDate() == null ? ""
									: sdf.format(mchtInfoCustom
											.getCooperateBeginDate()),
							"",
							mchtInfoCustom.getMchtEmail() == null ? ""
									: mchtInfoCustom.getMchtEmail(),
							mchtInfoCustom.getMchtMobile() == null ? ""
									: mchtInfoCustom.getMchtMobile(),
							mchtInfoCustom.getCompanyTel() == null ? ""
									: mchtInfoCustom.getCompanyTel(),
							mchtInfoCustom.getTotalAuditStatusdesc() == null ? ""
									: mchtInfoCustom.getTotalAuditStatusdesc(),
							mchtInfoCustom.getStatusDesc() == null ? ""
									: mchtInfoCustom.getStatusDesc(),
							mchtContactStr, mchtPlatformContactStr };
					datas.add(data);
				}
				excelBean.setDataList(datas);
				ExcelUtils.export(excelBean, response);
			} else {// 不是admin不显示客户信息这一列STORY #848
				String[] titles = { "店铺创建时间", "商家序号", "公司名称", "商家用户名", "联系人",
						"店铺名称", "品牌（佣金、状态）", "类目", "开通时间", "最后登录时间", "邮箱",
						"电话", "公司总机", "资质总审核状态", "合作状态", "平台对接人" };
				ExcelBean excelBean = new ExcelBean("导出商家审核列表.xls", "导出商家审核列表",
						titles);
				List<String[]> datas = new ArrayList<String[]>();

				for (MchtInfoCustom mchtInfoCustom : mchtInfoCustomList) {
					MchtProductTypeCustomExample mchtProductTypeExample = new MchtProductTypeCustomExample(); // 类目
					MchtProductTypeCustomCriteria mchtProductTypeCustomCriteria = mchtProductTypeExample
							.createCriteria();
					mchtProductTypeCustomCriteria.andDelFlagEqualTo("0");
					mchtProductTypeCustomCriteria.andStatusNotEqualTo("2");
					mchtProductTypeExample.setOrderByClause(" status asc");
					mchtProductTypeCustomCriteria
							.andMchtIdEqualTo(mchtInfoCustom.getId());
					List<MchtProductTypeCustom> mchtProductTypeList = mchtProductTypeService
							.selectMchtProductTypeCustomByExample(mchtProductTypeExample); // 类目
					String mchtProductTypeStr = "";
					for (MchtProductTypeCustom mchtProductTypeCustom : mchtProductTypeList) {
						if (!"".equals(mchtProductTypeStr)) {
							mchtProductTypeStr += "\n";
						}
						if (!StringUtil.isEmpty(mchtProductTypeCustom
								.getStatusDesc())) {
							mchtProductTypeStr += "["
									+ mchtProductTypeCustom.getStatusDesc()
									+ "]";
						}
						if (!StringUtil.isEmpty(mchtProductTypeCustom
								.getProductTypeName())) {
							mchtProductTypeStr += mchtProductTypeCustom
									.getProductTypeName();
						}
					}

					MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample(); // 品牌
					MchtProductBrandExample.Criteria MchtProductBrandCriteria = mchtProductBrandExample
							.createCriteria();
					MchtProductBrandCriteria.andDelFlagEqualTo("0")
							.andMchtIdEqualTo(mchtInfoCustom.getId());
					MchtProductBrandCriteria.andStatusNotEqualTo("3");
					mchtProductBrandExample
							.setOrderByClause(" audit_status asc, status asc");
					List<MchtProductBrandCustom> mchtProductBrandCustomList = mchtProductBrandService
							.selectByCustomExample(mchtProductBrandExample); // 品牌
					String mchtProductBrandStr = "";
					for (MchtProductBrandCustom mchtProductBrandCustom : mchtProductBrandCustomList) {
						if (!"".equals(mchtProductBrandStr)) {
							mchtProductBrandStr += "\n";
						}
						if (!StringUtil.isEmpty(mchtProductBrandCustom
								.getAuditStatusDesc())) {
							mchtProductBrandStr += "["
									+ mchtProductBrandCustom
											.getAuditStatusDesc() + "]";
						}
						if (!StringUtil.isEmpty(mchtProductBrandCustom
								.getStatusDesc())) {
							mchtProductBrandStr += "["
									+ mchtProductBrandCustom.getStatusDesc()
									+ "]";
						}
						if (!StringUtil.isEmpty(mchtProductBrandCustom
								.getBrandName())) {
							mchtProductBrandStr += mchtProductBrandCustom
									.getBrandName();
						} else {
							mchtProductBrandStr += mchtProductBrandCustom
									.getProductBrandName();
						}
						if (mchtProductBrandCustom.getPopCommissionRate() != null) {
							mchtProductBrandStr += "("
									+ mchtProductBrandCustom
											.getPopCommissionRate() + ")";
						}
					}

					String mchtPlatformContactStr = "";
					String zsCodeStr = "[招商对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getZsContactName()))
						zsCodeStr += mchtInfoCustom.getZsContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getZsMobile()))
						zsCodeStr += mchtInfoCustom.getZsMobile() + "\n";
					if (!zsCodeStr.equals("[招商对接人]"))
						mchtPlatformContactStr += zsCodeStr;
					String yyCodeStr = "[运营对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getYyContactName()))
						yyCodeStr += mchtInfoCustom.getYyContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getYyMobile()))
						yyCodeStr += mchtInfoCustom.getYyMobile() + "\n";
					if (!yyCodeStr.equals("[运营对接人]"))
						mchtPlatformContactStr += yyCodeStr;
					String ddCodeStr = "[订单对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getDdContactName()))
						ddCodeStr += mchtInfoCustom.getDdContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getDdMobile()))
						ddCodeStr += mchtInfoCustom.getDdMobile() + "\n";
					if (!ddCodeStr.equals("[订单对接人]"))
						mchtPlatformContactStr += ddCodeStr;
					String shCodeStr = "[售后对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getShContactName()))
						shCodeStr += mchtInfoCustom.getShContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getShMobile()))
						shCodeStr += mchtInfoCustom.getShMobile() + "\n";
					if (!shCodeStr.equals("[售后对接人]"))
						mchtPlatformContactStr += shCodeStr;
					String cwCodeStr = "[财务对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getCwContactName()))
						cwCodeStr += mchtInfoCustom.getCwContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getCwMobile()))
						cwCodeStr += mchtInfoCustom.getCwMobile() + "\n";
					if (!cwCodeStr.equals("[财务对接人]"))
						mchtPlatformContactStr += cwCodeStr;
					String kfCodeStr = "[客服对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getKfContactName()))
						kfCodeStr += mchtInfoCustom.getKfContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getKfMobile()))
						kfCodeStr += mchtInfoCustom.getKfMobile() + "\n";
					if (!kfCodeStr.equals("[客服对接人]"))
						mchtPlatformContactStr += kfCodeStr;
					String fwCodeStr = "[法务对接人]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getFwContactName()))
						fwCodeStr += mchtInfoCustom.getFwContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getFwMobile()))
						fwCodeStr += mchtInfoCustom.getFwMobile() + "\n";
					if (!fwCodeStr.equals("[法务对接人]"))
						mchtPlatformContactStr += fwCodeStr;
					String sjCodeStr = "[商家服务专员]";
					if (!StringUtil.isEmpty(mchtInfoCustom.getSjContactName()))
						sjCodeStr += mchtInfoCustom.getSjContactName() + "  ";
					if (!StringUtil.isEmpty(mchtInfoCustom.getSjMobile()))
						sjCodeStr += mchtInfoCustom.getSjMobile() + "\n";
					if (!sjCodeStr.equals("[商家服务专员]"))
						mchtPlatformContactStr += sjCodeStr;
					String[] data = {
							mchtInfoCustom.getCreateDate() == null ? "" : sdf
									.format(mchtInfoCustom.getCreateDate()),
							mchtInfoCustom.getMchtCode() == null ? ""
									: mchtInfoCustom.getMchtCode(),
							(mchtInfoCustom.getIsCompanyInfPerfectDesc() == null ? ""
									: ("["
											+ mchtInfoCustom
													.getIsCompanyInfPerfectDesc() + "]"))
									+ (mchtInfoCustom.getCompanyName() == null ? ""
											: mchtInfoCustom.getCompanyName()),
							mchtInfoCustom.getMchtUserCode() == null ? ""
									: mchtInfoCustom.getMchtUserCode(),
							mchtInfoCustom.getMchtUserName() == null ? ""
									: mchtInfoCustom.getMchtUserName(),
							(mchtInfoCustom.getShopNameAuditStatusDesc() == null ? ""
									: ("["
											+ mchtInfoCustom
													.getShopNameAuditStatusDesc() + "]"))
									+ mchtInfoCustom.getShopName(),
							mchtProductBrandStr,
							mchtProductTypeStr,
							mchtInfoCustom.getCooperateBeginDate() == null ? ""
									: sdf.format(mchtInfoCustom
											.getCooperateBeginDate()),
							"",
							mchtInfoCustom.getMchtEmail() == null ? ""
									: mchtInfoCustom.getMchtEmail(),
							mchtInfoCustom.getMchtMobile() == null ? ""
									: mchtInfoCustom.getMchtMobile(),
							mchtInfoCustom.getCompanyTel() == null ? ""
									: mchtInfoCustom.getCompanyTel(),
							mchtInfoCustom.getTotalAuditStatusdesc() == null ? ""
									: mchtInfoCustom.getTotalAuditStatusdesc(),
							mchtInfoCustom.getStatusDesc() == null ? ""
									: mchtInfoCustom.getStatusDesc(),
							mchtPlatformContactStr };
					datas.add(data);
				}
				excelBean.setDataList(datas);
				ExcelUtils.export(excelBean, response);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @MethodName: exportMchtNetlistExcel
	 * @Description: (导出网表)
	 * @author Pengl
	 * @date 2019年4月1日 下午4:25:48
	 */
	@ResponseBody
	@RequestMapping(value = "/mcht/exportMchtNetlistExcel.shtml")
	public void exportMchtNetlistExcel(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			mchtInfoCustomExample.setOrderByClause(" id desc");
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) { // 序号
				mchtInfoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtType"))) { // 模式
				mchtInfoCustomCriteria.andMchtTypeEqualTo(request.getParameter("mchtType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) { // 名称
				mchtInfoCustomCriteria.andMchtNameLike(request.getParameter("mchtName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) { // 合作状态
				mchtInfoCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endYear"))) { // 到期年份
				mchtInfoCustomCriteria.andEndYearEqualTo(request.getParameter("endYear"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endMonth"))) { // 到期月份
				mchtInfoCustomCriteria.andEndMonthEqualTo(request.getParameter("endMonth"));
			}
			if (!StringUtil.isEmpty(request.getParameter("contactType"))) { // 本人对接
				mchtInfoCustomCriteria.andContactTypeEqualTo(request.getParameter("contactType"), Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			}
			if (!StringUtil.isEmpty(request.getParameter("brandName"))) { // 品牌
				mchtInfoCustomCriteria.andBrandNameEqualTo(request.getParameter("brandName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("startCreateDate"))) { // 创建日期
				mchtInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("startCreateDate") + " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				mchtInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate") + " 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) { // 类目
				mchtInfoCustomCriteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			
			if (!StringUtil.isEmpty(request.getParameter("zsPlatformContact"))) { // 招商对接
				mchtInfoCustomCriteria.andPlatformContactEqualTo("1", request.getParameter("zsPlatformContact"));
			}
			if (!StringUtil.isEmpty(request.getParameter("zyPlatformContact"))) { // 运营对接
				mchtInfoCustomCriteria.andPlatformContactEqualTo("2", request.getParameter("zyPlatformContact"));
			}
			if (!StringUtil.isEmpty(request.getParameter("fwPlatformContact"))) { // 法务对接
				mchtInfoCustomCriteria.andPlatformContactEqualTo("7", request.getParameter("fwPlatformContact"));
			}
			if (!StringUtil.isEmpty(request.getParameter("startTotalAuditDate"))) { // 法务审核日期
				mchtInfoCustomCriteria.andTotalAuditDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("startTotalAuditDate") + " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endTotalAuditDate"))) {
				mchtInfoCustomCriteria.andTotalAuditDateLessThanOrEqualTo(sdf.parse(request.getParameter("endTotalAuditDate") + " 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("startCooperateBeginDate"))) { // 开通日期
				mchtInfoCustomCriteria.andCooperateBeginDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("startCooperateBeginDate") + " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endCooperateBeginDate"))) {
				mchtInfoCustomCriteria.andCooperateBeginDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCooperateBeginDate") + " 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("isSpeciallyApprovedDesc"))) {
				mchtInfoCustomCriteria.andProductisspeciallyapprovedEqualTo((request.getParameter("isSpeciallyApprovedDesc")));
			}
			String totalauditstatus = request.getParameter("totalauditstatus");
			if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus.equals("0"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("0");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus.equals("1"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("1");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus.equals("2"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("2");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus.equals("3"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("3");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus.equals("4"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusEqualTo("4");
			} else if ((!StringUtil.isEmpty(totalauditstatus) && totalauditstatus.equals("5"))) {
				mchtInfoCustomCriteria.andTotalAuditStatusNotEqualTo("4").andTotalAuditStatusIsNotNull();
			}
			List<MchtInfoCustom> mchtInfoCustomList = mchtInfoService.selectByExample(mchtInfoCustomExample);
			String[] titles = { "网店ID", "网店名称", "统一社会信用代码", "执照名称", "网址",
					"网店类型", "许可证类型", "平台备案许可证号", "商家联系地址"};
			ExcelBean excelBean = new ExcelBean("导出网表.xls", "导出网表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for (MchtInfoCustom mchtInfoCustom : mchtInfoCustomList) {
				StringBuffer contactAddress = new StringBuffer("");
				if(!StringUtil.isEmpty(mchtInfoCustom.getContactProvinceName()) ) {
					contactAddress.append(mchtInfoCustom.getContactProvinceName());
				}
				if(!StringUtil.isEmpty(mchtInfoCustom.getContactCityName()) ) {
					contactAddress.append(mchtInfoCustom.getContactCityName());
				}
				if(!StringUtil.isEmpty(mchtInfoCustom.getContactCountyName()) ) {
					contactAddress.append(mchtInfoCustom.getContactCountyName());
				}
				if(!StringUtil.isEmpty(mchtInfoCustom.getContactAddress()) ) {
					contactAddress.append(mchtInfoCustom.getContactAddress());
				}
				String[] data = {
						mchtInfoCustom.getMchtCode()==null?"":mchtInfoCustom.getMchtCode()+"\t",
						mchtInfoCustom.getShopName()==null?"":mchtInfoCustom.getShopName()+"\t",
						mchtInfoCustom.getUscc()==null?"":mchtInfoCustom.getUscc()+"\t",
						mchtInfoCustom.getCompanyName()==null?"":mchtInfoCustom.getCompanyName()+"\t",
						"",
						mchtInfoCustom.getProductTypeName()==null?"":mchtInfoCustom.getProductTypeName()+"\t",
						"",
						"",
						contactAddress.toString()+"\t"
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 品牌到期统计列表
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/brandExpireCount.shtml")
	public ModelAndView brandExpireCount(HttpServletRequest request) {
		String rtPage = "/mcht/brandExpireCount";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 品牌到期统计列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/brandExpireCountData.shtml")
	@ResponseBody
	public Map<String, Object> brandExpireCountData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			// Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -5);
			String beginMonth = sdf.format(calendar.getTime());
			String endMonth = DateUtil.getMonth(6);
			List<String> eachMonths = this
					.getMonthBetween(beginMonth, endMonth);
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			for (String eachMonth : eachMonths) {
				String beginDate = eachMonth + "-01";
				String endDate = eachMonth + "-"
						+ DateUtil.getDayMouth(eachMonth);
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("beginDate", beginDate);
				paramMap.put("endDate", endDate);
				int expireCount = mchtProductBrandService
						.countExpireBrand(paramMap);
				HashMap<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("yearMonth", eachMonth);
				resultMap.put("expireCount", expireCount);
				list.add(resultMap);
			}
			resMap.put("Rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	private List<String> getMonthBetween(String minDate, String maxDate)
			throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		return result;
	}

	/**
	 * 查看品牌列表
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/viewBrandList.shtml")
	public ModelAndView viewBrandList(HttpServletRequest request) {
		String rtPage = "/mcht/viewBrandList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String yearMonth = request.getParameter("yearMonth");
		resMap.put("yearMonth", yearMonth);
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 查看品牌列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/viewBrandListData.shtml")
	@ResponseBody
	public Map<String, Object> viewBrandListData(HttpServletRequest request,
			Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		String yearMonth = request.getParameter("yearMonth");
		String beginDate = yearMonth + "-01";
		String endDate = yearMonth + "-" + DateUtil.getDayMouth(yearMonth);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginDate", beginDate);
		paramMap.put("endDate", endDate);
		List<MchtProductBrandCustom> mchtProductbrandCustoms = mchtProductBrandService
				.getMchtProductBrandCustoms(paramMap);
		resMap.put("Rows", mchtProductbrandCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * 
	 * @Title updateMchtProductBrandApproved
	 * @Description TODO(修改特批)
	 * @author pengl
	 * @date 2017年12月28日 上午11:30:04
	 */
	@ResponseBody
	@RequestMapping(value = "/mcht/updateMchtProductBrandApproved.shtml")
	public Map<String, Object> updateMchtProductBrandApproved(
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String returnCode = "";
		String returnMsg = "";
		try {
			if (!StringUtil.isEmpty(request.getParameter("mchtProductBrandId"))) {
				MchtProductBrand mchtProductBrand = new MchtProductBrand();
				mchtProductBrand.setId(Integer.parseInt(request.getParameter("mchtProductBrandId")));
				if (!StringUtil.isEmpty(request.getParameter("isSpeciallyApproved"))){
					mchtProductBrand.setIsSpeciallyApproved(request.getParameter("isSpeciallyApproved"));
				}
				if (!StringUtil.isEmpty(request.getParameter("speciallyApprovedRemarks"))){
					mchtProductBrand.setSpeciallyApprovedRemarks(request.getParameter("speciallyApprovedRemarks"));
				}
				if(!StringUtil.isEmpty(request.getParameter("speciallyApprovedSource"))){
					mchtProductBrand.setSpeciallyApprovedSource(request.getParameter("speciallyApprovedSource"));
				}
				if(!StringUtil.isEmpty(request.getParameter("speciallyApprovedDate"))){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					mchtProductBrand.setSpeciallyApprovedDate(sdf.parse(request.getParameter("speciallyApprovedDate")));
				}
				mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
				returnMsg = "操作成功！";
			} else {
				returnMsg = "商家品牌ID为空！";
			}
			returnCode = "0000";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnCode = "4004";
			returnMsg = "系统错误！";
		}
		map.put("returnCode", returnCode);
		map.put("returnMsg", returnMsg);
		return map;
	}

	/**
	 * 
	 * @Title editMchtProductBrandApprovedManager
	 * @Description TODO(修改或查看特批管理)
	 * @author pengl
	 * @date 2017年12月28日 上午11:41:38
	 */
	@RequestMapping(value = "/mcht/editOrShowMchtProductBrandApproved.shtml")
	public ModelAndView editOrShowMchtProductBrandApprovedManager(
			HttpServletRequest request, String mchtProductBrandId,
			String statusPage) {
		ModelAndView m = new ModelAndView(
				"/mcht/editOrShowMchtProductBrandApproved");
		if (!StringUtil.isEmpty(mchtProductBrandId)) {
			MchtProductBrand mchtProductBrand = mchtProductBrandService
					.selectByPrimaryKey(Integer.parseInt(mchtProductBrandId));
			m.addObject("mchtProductBrand", mchtProductBrand);
		}
		m.addObject("statusPage", statusPage); // 1、修改 2、查看
		return m;
	}

	/**
	 * 
	 * @Title getMchtProductBrandApprovedManager
	 * @Description TODO(特批品牌管理)
	 * @author pengl
	 * @date 2017年12月28日 下午3:22:09
	 */
	@RequestMapping(value = "/mcht/getMchtProductBrandApprovedManager.shtml")
	public ModelAndView getMchtProductBrandApprovedManager(
			HttpServletRequest request) {
		ModelAndView m = new ModelAndView("mcht/getMchtProductBrandApprovedList");
		// 运营状态
		List<SysStatus> brandStatusList = DataDicUtil.getTableStatus("BU_MCHT_PRODUCT_BRAND", "STATUS");
		// 资质类型
		List<SysStatus> brandAptitudeList = DataDicUtil.getTableStatus("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE");
		m.addObject("brandStatusList", brandStatusList);
		m.addObject("brandAptitudeList", brandAptitudeList);
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(108);
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			m.addObject("zsMarked", 1);
		}else{
			m.addObject("zsMarked", 0);
		}
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		m.addObject("productTypes", productTypes);
		
		//对接人
		MchtProductBrandCustomExample mchtProductBrandCustomExample = new MchtProductBrandCustomExample();
		MchtProductBrandCustomCriteria criteria = mchtProductBrandCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andIsSpeciallyApprovedEqualTo("1");
		List<Map<String, Object>> mchtProductBrandCustomlistList=mchtProductBrandService.selectDockingByListExample(mchtProductBrandCustomExample);
		m.addObject("mchtProductBrandCustomlistList", mchtProductBrandCustomlistList);
		return m;
	}

	/**
	 * 
	 * @Title getMchtProductBrandApprovedList
	 * @Description TODO(特批品牌列表)
	 * @author pengl
	 * @date 2017年12月28日 下午3:30:14
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/mcht/getMchtProductBrandApprovedList.shtml")
	public Map<String, Object> getMchtProductBrandApprovedList(
			HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MchtProductBrandCustom> dataList = new ArrayList<MchtProductBrandCustom>();
		Integer totalCount = 0;
		try {
			MchtProductBrandCustomExample mchtProductBrandCustomExample = new MchtProductBrandCustomExample();
			MchtProductBrandCustomCriteria criteria = mchtProductBrandCustomExample
					.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andIsSpeciallyApprovedEqualTo("1");
			mchtProductBrandCustomExample.setOrderByClause(" id desc");
			mchtProductBrandCustomExample.setLimitSize(page.getLimitSize());
			mchtProductBrandCustomExample.setLimitStart(page.getLimitStart());
			if (!StringUtil.isEmpty(request.getParameter("name"))){
				criteria.andMchtNameLike(request.getParameter("name"));
			}
			if (!StringUtil.isEmpty(request.getParameter("aptitudeType"))){
				criteria.andAptitudeTypeEqualTo(request.getParameter("aptitudeType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))){
				criteria.andStatusEqualTo(request.getParameter("status"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				criteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				criteria.andProductBrandNameEqualTo(request.getParameter("productBrandName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))){
				criteria.andProductTypeIdEqualTo(Integer.parseInt(request.getParameter("productTypeId")));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtInfoStatus"))){
				criteria.andMchtStatusEqualTo(request.getParameter("mchtInfoStatus"));
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!StringUtil.isEmpty(request.getParameter("speciallyApprovedDateBegin"))){
				criteria.andSpeciallyApprovedDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("speciallyApprovedDateBegin")+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("speciallyApprovedDateEnd"))){
				criteria.andSpeciallyApprovedDateLessThanOrEqualTo(sdf.parse(request.getParameter("speciallyApprovedDateEnd")+" 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("fwStaffId"))){
				criteria.andfwStaffIdEqualTo(request.getParameter("fwStaffId"));
			}
			dataList = mchtProductBrandService.selectByCustomExample(mchtProductBrandCustomExample);
			totalCount = mchtProductBrandService.countByCustomExample(mchtProductBrandCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataList = new ArrayList();
			totalCount = 0;
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/mcht/exportMchtProductBrandApproved.shtml")
	public void exportMchtProductBrandApproved(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			List<MchtProductBrandCustom> dataList = new ArrayList<MchtProductBrandCustom>();
			MchtProductBrandCustomExample mchtProductBrandCustomExample = new MchtProductBrandCustomExample();
			MchtProductBrandCustomCriteria criteria = mchtProductBrandCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andIsSpeciallyApprovedEqualTo("1");
			mchtProductBrandCustomExample.setOrderByClause(" id desc");
			if (!StringUtil.isEmpty(request.getParameter("name"))){
				criteria.andMchtNameLike(request.getParameter("name"));
			}
			if (!StringUtil.isEmpty(request.getParameter("aptitudeType"))){
				criteria.andAptitudeTypeEqualTo(request.getParameter("aptitudeType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))){
				criteria.andStatusEqualTo(request.getParameter("status"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				criteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				criteria.andProductBrandNameEqualTo(request.getParameter("productBrandName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))){
				criteria.andProductTypeIdEqualTo(Integer.parseInt(request.getParameter("productTypeId")));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtInfoStatus"))){
				criteria.andMchtStatusEqualTo(request.getParameter("mchtInfoStatus"));
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!StringUtil.isEmpty(request.getParameter("speciallyApprovedDateBegin"))){
				criteria.andSpeciallyApprovedDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("speciallyApprovedDateBegin")+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("speciallyApprovedDateEnd"))){
				criteria.andSpeciallyApprovedDateLessThanOrEqualTo(sdf.parse(request.getParameter("speciallyApprovedDateEnd")+" 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("fwStaffId"))){
				criteria.andfwStaffIdEqualTo(request.getParameter("fwStaffId"));
			}
			dataList = mchtProductBrandService.selectByCustomExample(mchtProductBrandCustomExample);
			String[] titles = { "创建日期", "招商对接人", "商家序号", "公司名称", "店铺名称", "主营品类","品牌名称","授权有效期","资质审核状态","运营状态","招商标记","标记有效期","备注","法务对接人","来源"};
			ExcelBean excelBean = new ExcelBean("导出标记品牌.xls", "导出标记品牌", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for (int i = 0; i < dataList.size(); i++) {
				MchtProductBrandCustom mchtProductBrandCustom = dataList.get(i);
				String speciallyApprovedSourceDesc = "";
				if(!StringUtils.isEmpty(mchtProductBrandCustom.getSpeciallyApprovedSource()) && mchtProductBrandCustom.getSpeciallyApprovedSource().equals("1")){
					speciallyApprovedSourceDesc = "总经理";
				}else if(!StringUtils.isEmpty(mchtProductBrandCustom.getSpeciallyApprovedSource()) && mchtProductBrandCustom.getSpeciallyApprovedSource().equals("2")){
					speciallyApprovedSourceDesc = "副总经理";
				}else{
					speciallyApprovedSourceDesc = "其他人员";
				}
				String[] data = {
					df.format(mchtProductBrandCustom.getCreateDate()),
					mchtProductBrandCustom.getZsContactName(),
					mchtProductBrandCustom.getMchtCode(),
					mchtProductBrandCustom.getCompanyName(),
					mchtProductBrandCustom.getShopName(),
					mchtProductBrandCustom.getProductTypeName(),
					mchtProductBrandCustom.getProductBrandName(),
					dateFormat.format(mchtProductBrandCustom.getPlatformAuthExpDate()),
					mchtProductBrandCustom.getAuditStatusDesc(),
					mchtProductBrandCustom.getStatusDesc(),
					mchtProductBrandCustom.getIsSpeciallyApprovedDesc(),
					mchtProductBrandCustom.getSpeciallyApprovedDate()==null?"":dateFormat.format(mchtProductBrandCustom.getSpeciallyApprovedDate()),
					mchtProductBrandCustom.getSpeciallyApprovedRemarks(),
					mchtProductBrandCustom.getFwContactName(),
					speciallyApprovedSourceDesc
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 品牌到期统计--发短信
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/sendSms.shtml")
	@ResponseBody
	public Map<String, Object> sendSms(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		String yearMonth = request.getParameter("yearMonth");
		String beginDate = yearMonth + "-01";
		String endDate = yearMonth + "-" + DateUtil.getDayMouth(yearMonth);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginDate", beginDate);
		paramMap.put("endDate", endDate);
		List<HashMap<String, Object>> list = mchtProductBrandService
				.getMchtContacts(paramMap);
		List<JSONObject> sendList = new ArrayList<JSONObject>();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> eachMap = list.get(i);
			String mobile = "";
			if (eachMap.get("yy_mobile") != null) {
				mobile = eachMap.get("yy_mobile").toString();
			} else if (eachMap.get("ds_mobile") != null) {
				mobile = eachMap.get("ds_mobile").toString();
			}
			String brandName = eachMap.get("brand_name") != null ? eachMap.get(
					"brand_name").toString() : "";
			String platformAuthExpDate = eachMap.get("platform_auth_exp_date")
					.toString();
			if (!StringUtils.isEmpty(mobile) && !StringUtils.isEmpty(brandName)) {
				JSONObject param = new JSONObject();
				// 特定参数
				JSONObject reqData = new JSONObject();
				reqData.put("mobile", mobile);
				reqData.put("content", "您的{" + brandName + "}品牌授权有效期将于{"
						+ platformAuthExpDate + "}过期，请及时更新品牌授权。");
				reqData.put("smsType", "4");// 4.到期统计
				param.put("reqData", reqData);
				sendList.add(param);
			}
		}
		if (sendList != null && sendList.size() > 0) {
			int sendSuccessCount = mchtProductBrandService.sendSms(sendList);
			if (sendSuccessCount > 0) {
				resMap.put("sendSuccessCount", sendSuccessCount);
			} else {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "发送失败，请稍后重试");
			}
		} else {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "发送失败，商家没有设置联系人，无法发送");
		}
		return resMap;
	}

	// 加入黑名单
	@RequestMapping(value = "/mcht/editMchtBlacklist.shtml")
	public String mchtblacklist(Model model, HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("ids"));
		model.addAttribute("id", id);
		return "/mcht/mchtBlacklist";
	}

	@RequestMapping(value = "/mcht/mchtblacklistdata.shtml")
	@ResponseBody
	public Map<String, Object> mchtblacklistdatasumbit(Model model,
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId = Integer.valueOf(staffBean.getStaffID());
			String id = request.getParameter("id");
			String remarks = request.getParameter("remarks");
			String inblacklist = request.getParameter("inblacklist");
			if (inblacklist.equals("0")) {
				MchtInfoExample mchtInfoExample = new MchtInfoExample();
				MchtInfoExample.Criteria createCriteria = mchtInfoExample
						.createCriteria();
				createCriteria.andIdEqualTo(Integer.valueOf(id))
						.andDelFlagEqualTo("0").andInBlacklistEqualTo("0");
				MchtInfo mchtInfo = new MchtInfo();
				mchtInfo.setId(Integer.valueOf(id));
				mchtInfo.setRemarks(remarks);
				mchtInfo.setInBlacklist("1");
				mchtInfo.setUpdateBy(staffId);
				mchtInfo.setUpdateDate(new Date());
				mchtInfoService.updateByExampleSelective(mchtInfo,
						mchtInfoExample);
			}

		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	// 取消黑名单
	@RequestMapping(value = "/mcht/cancelmchtBlacklist.shtml")
	public ModelAndView edit(HttpServletRequest request) {
		String rtPage = "/mcht/cancelmchtBlacklist";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			if (!StringUtil.isEmpty(request.getParameter("ids"))) {
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer
						.valueOf(request.getParameter("ids")));
				resMap.put("mchtInfo", mchtInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	@RequestMapping(value = "/mcht/cancelmchtBlacklistdata.shtml")
	@ResponseBody
	public Map<String, Object> cancelmchtBlacklist(Model model,
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId = Integer.valueOf(staffBean.getStaffID());
			String id = request.getParameter("id");
			/* String remarks = request.getParameter("remarks"); */
			String inblacklist = request.getParameter("inblacklist");
			if (inblacklist.equals("1")) {
				MchtInfoExample mchtInfoExample = new MchtInfoExample();
				MchtInfoExample.Criteria createCriteria = mchtInfoExample
						.createCriteria();
				createCriteria.andIdEqualTo(Integer.valueOf(id))
						.andDelFlagEqualTo("0").andInBlacklistEqualTo("1");
				MchtInfo mchtInfo = new MchtInfo();
				mchtInfo.setId(Integer.valueOf(id));
				/* mchtInfo.setRemarks(remarks); */
				mchtInfo.setInBlacklist("0");
				mchtInfo.setUpdateBy(staffId);
				mchtInfo.setUpdateDate(new Date());
				mchtInfoService.updateByExampleSelective(mchtInfo,
						mchtInfoExample);
			}

		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	@RequestMapping(value = "/mcht/financeConfirm.shtml")
	@ResponseBody
	public Map<String, Object> financeConfirm(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if (request.getParameter("mchtId") == null) {
				throw new ArgException("用户Id不能为空");
			}
			Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
			MchtInfo mchtInfo = new MchtInfo();
			mchtInfo.setId(mchtId);
			mchtInfo.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(
					request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setFinanceConfirmStatus("1");// 已确认
			mchtInfo.setFinanceConfirmBy(Integer.valueOf(this
					.getSessionStaffBean(request).getStaffID()));
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	// 小能客服列表首页
	@RequestMapping(value = "/mcht/xiaonengcustomerservice.shtml")
	public ModelAndView appMngList(HttpServletRequest request) {
		String rtPage = "/mcht/xiaonengcustomerservice";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage, resMap);
	}

	// 小能客服列表数据
	@RequestMapping(value = "/mcht/xiaonengcustomerservice/data.shtml")
	@ResponseBody
	public Map<String, Object> styleAdministrationdata(
			HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			XiaonengcustomerserviceExample xiaonengcustomerserviceExample = new XiaonengcustomerserviceExample();
			XiaonengcustomerserviceExample.Criteria XiaonengcustomerserviceCriteria = xiaonengcustomerserviceExample
					.createCriteria();
			XiaonengcustomerserviceCriteria.andDelFlagEqualTo("0");
			if (!StringUtil.isEmpty(request.getParameter("id")))
				XiaonengcustomerserviceCriteria.andIdEqualTo(Integer
						.valueOf(request.getParameter("id")));
			if (!StringUtil.isEmpty(request.getParameter("busId")))
				XiaonengcustomerserviceCriteria.andBusIdEqualTo(Integer
						.valueOf(request.getParameter("busId")));
			if (!StringUtil.isEmpty(request.getParameter("mchtName")))
				XiaonengcustomerserviceCriteria.andMchtNameEqualTo(request
						.getParameter("mchtName"));
			if (!StringUtil.isEmpty(request.getParameter("status")))
				if (request.getParameter("status").equals("0")) {
					XiaonengcustomerserviceCriteria.andStatusEqualTo("0");
				} else if (request.getParameter("status").equals("1")) {
					XiaonengcustomerserviceCriteria.andStatusEqualTo("1");
				}

			totalCount = xiaonengcustomerserviceService
					.countByExample(xiaonengcustomerserviceExample);
			xiaonengcustomerserviceExample.setLimitStart(page.getLimitStart());
			xiaonengcustomerserviceExample.setLimitSize(page.getLimitSize());
			List<Xiaonengcustomerservice> Xiaonengcustomerservice = xiaonengcustomerserviceService
					.selectByExample(xiaonengcustomerserviceExample);
			resMap.put("Rows", Xiaonengcustomerservice);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	// 根据ID获取小能客服数据
	@RequestMapping(value = "/mcht/editxiaonengcustomer.shtml")
	public ModelAndView editxiaonengcustomer(HttpServletRequest request) {
		String rtPage = "/mcht/editxiaonengcustomer";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			if (!StringUtil.isEmpty(request.getParameter("id"))) {
				Xiaonengcustomerservice xiaonengCustomerservice = xiaonengcustomerserviceService
						.selectByPrimaryKey(Integer.valueOf(request
								.getParameter("id")));
				resMap.put("xiaonengCustomerservicelist",
						xiaonengCustomerservice);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	// 添加小能客服界面
	@RequestMapping(value = "/mcht/addxiaonengcustomer.shtml")
	public ModelAndView xiaonengcustomerservice(HttpServletRequest request) {
		String rtPage = "/mcht/addxiaonengcustomer";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage, resMap);
	}

	// 添加和修改小能客服数据
	@ResponseBody
	@RequestMapping(value = "/xiaonengcustomerservice/editsales.shtml")
	public ModelAndView editsales(HttpServletRequest request,
								  Xiaonengcustomerservice XiaonengCustomerservice) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			if (XiaonengCustomerservice.getId() == null) {
				// 添加数据
				xiaonengcustomerserviceService
						.insertsale(XiaonengCustomerservice);

			} else {
				// 更新数据
				xiaonengcustomerserviceService
						.updatesale(XiaonengCustomerservice);

			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}

	// 修改小能客服状态启用和停用
	@ResponseBody
	@RequestMapping(value = "/xiaonengcustomerservice/chgStatus.shtml")
	public Map<String, Object> xiaonengcustomerserviceStatus(
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request)
					.getStaffID());
			Integer id = Integer.valueOf(request.getParameter("id"));
			String status = request.getParameter("status");
			Xiaonengcustomerservice xiaonengCustomerService = new Xiaonengcustomerservice();
			xiaonengCustomerService.setId(id);
			xiaonengCustomerService.setUpdateBy(staffId);
			xiaonengCustomerService.setUpdateDate(new Date());
			xiaonengCustomerService.setStatus(status);
			xiaonengcustomerserviceService
					.updateByPrimaryKeySelective(xiaonengCustomerService);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 招商确认合作信息
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/zsSettledInfoConfirm.shtml")
	public ModelAndView zsSettledInfoConfirm(HttpServletRequest request) {
		String rtPage = "/mcht/zsSettledInfoConfirm";
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample
					.createCriteria()
					.andDelFlagEqualTo("0")
					.andStaffIdEqualTo(
							Integer.valueOf(this.getSessionStaffBean(request)
									.getStaffID())).andContactTypeEqualTo("7")
					.andStatusEqualTo("0");
			if (platformContactService.countByExample(platformContactExample) > 0) {
				resMap.put("isCanGet", true);
			} else {
				resMap.put("isCanGet", false);
			}
			String isManagement = this.getSessionStaffBean(request).getIsManagement();
			resMap.put("isManagement", isManagement);
			PlatformContactExample pce = new PlatformContactExample();
			PlatformContactExample.Criteria pcec = pce.createCriteria();
			pcec.andDelFlagEqualTo("0");
			pcec.andContactTypeEqualTo("1");// 招商对接人
			pcec.andStatusEqualTo("0");
			pcec.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
			if (platformContacts != null && platformContacts.size() > 0) {// 是招商对接人
				resMap.put("myContactId", platformContacts.get(0).getId());
				PlatformContactExample e = new PlatformContactExample();
				PlatformContactExample.Criteria c = e.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andStatusEqualTo("0");
				c.andAssistantIdEqualTo(platformContacts.get(0).getId());
				List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
				resMap.put("platformContacts", platformContactList);
				resMap.put("isAssistant", true);
			} else {
				PlatformContactCustomExample zsPlatformExample = new PlatformContactCustomExample();
				zsPlatformExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
				List<PlatformContactCustom> zsPlatformContactCustoms = platformContactService.selectPlatformContactCustomByExample(zsPlatformExample);
				resMap.put("zsPlatformContactCustomList",zsPlatformContactCustoms);
				resMap.put("isAssistant", false);
			}
			resMap.put("zsTotalAuditStatus", "0");
			
			SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
			List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
			if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
				resMap.put("role107", true);
			}else{
				resMap.put("role107", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 招商确认合作信息
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/zsSettledInfoConfirmData.shtml")
	@ResponseBody
	public Map<String, Object> zsSettledInfoConfirmData(
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount = "0";
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(request.getParameter("zs_commit_audit_date_begin"))) {
				paramMap.put("zs_commit_audit_date_begin",request.getParameter("zs_commit_audit_date_begin")+ " 00:00:00");
			}
			if (!StringUtils.isEmpty(request.getParameter("zs_commit_audit_date_end"))) {
				paramMap.put("zs_commit_audit_date_end",request.getParameter("zs_commit_audit_date_end")+ " 23:59:59");
			}
			if (!StringUtils.isEmpty(request.getParameter("mcht_code"))) {
				paramMap.put("mcht_code", request.getParameter("mcht_code"));
			}
			if (!StringUtils.isEmpty(request.getParameter("mcht_name"))) {
				paramMap.put("mcht_name", request.getParameter("mcht_name"));
			}
			paramMap.put("zs_total_audit_status_notEqual", "4");
			if(!StringUtils.isEmpty(request.getParameter("zs_total_audit_status"))){
				paramMap.put("zs_total_audit_status", request.getParameter("zs_total_audit_status"));
			}
			List<String> statusList = new ArrayList<String>();
			statusList.add("0");
//			statusList.add("3");
			paramMap.put("statusList", statusList);
			paramMap.put("orderByClause", "zs_commit_audit_date asc");

			// 钟表运营部状态，只获取主营类目为钟表
			String isCwOrgStatus = this.getParamSession(request,"isCwOrgStatus");
			if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request,"isCwOrgProductTypeId");
				paramMap.put("productTypeId", isCwOrgProductTypeId);
			}
			if (!StringUtils.isEmpty(request.getParameter("platContactId"))) {
				paramMap.put("platContactId",request.getParameter("platContactId"));
			}
			if (!StringUtils.isEmpty(request.getParameter("settledType"))) {
				paramMap.put("settledType",request.getParameter("settledType"));
			}
			if (!StringUtils.isEmpty(request.getParameter("isManageSelf"))) {
				paramMap.put("isManageSelf",request.getParameter("isManageSelf"));
			}

			paramMap = this.setPageParametersLiger(request, paramMap);
			//看到自己对接的品类商家
			SysStaffProductTypeExample example = new SysStaffProductTypeExample();
			example.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			List<SysStaffProductType> sysStaffProductTypes = sysStaffProductTypeService.selectByExample(example);
			List<Integer> productTypeIds = new ArrayList<Integer>();
			for(int i=0; i<sysStaffProductTypes.size();i++){
				productTypeIds.add(sysStaffProductTypes.get(i).getProductTypeId());
			}
			if(productTypeIds==null || productTypeIds.size()==0){
				resMap.put("Rows", dataList);
				resMap.put("Total", totalCount);
				return resMap;
			}
			paramMap.put("productTypeIds", productTypeIds);
			paramMap.put("MIN_NUM", paramMap.get("MIN_NUM"));
			paramMap.put("MAX_NUM", paramMap.get("MAX_NUM"));
			totalCount = mchtInfoService.queryMchtCount(paramMap);
			dataList = mchtInfoService.selectMchtInfoList(paramMap);

			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
		return resMap;
	}

	/**
	 * 修改商家简称页面
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/toEditMchtName.shtml")
	public ModelAndView toEditMchtName(HttpServletRequest request) {
		String rtPage = "/mcht/toEditMchtName";
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String mchtId = request.getParameter("mchtId");
			resMap.put("id", mchtId);
			MchtInfo mi = mchtInfoService.selectByPrimaryKey(Integer
					.parseInt(mchtId));
			resMap.put("shortName", mi.getShortName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	// 修改商家简称
	@RequestMapping(value = "/mcht/editMchtName.shtml")
	@ResponseBody
	public Map<String, Object> editMchtName(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.valueOf(request.getParameter("mchtId"));
			String shortName = request.getParameter("shortName");
			MchtInfoExample example = new MchtInfoExample();
			MchtInfoExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andIdEqualTo(id);
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request)
					.getStaffID()));
			mi.setUpdateDate(new Date());
			mi.setShortName(shortName);
			mchtInfoService.updateByExampleSelective(mi, example);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 招商确认页面
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/toZsConfirm.shtml")
	public ModelAndView toZsConfirm(HttpServletRequest request) {
		String rtPage = "/mcht/toZsConfirm";
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String mchtId = request.getParameter("mchtId");
			resMap.put("id", mchtId);
			MchtInfo mi = mchtInfoService.selectByPrimaryKey(Integer
					.parseInt(mchtId));
			resMap.put("remarks", mi.getRemarks());
			resMap.put("zsAuditStatus", mi.getZsAuditStatus());
			// 对接人
			MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
			mchtPlatformContactExample.createCriteria().andDelFlagEqualTo("0")
					.andMchtIdEqualTo(Integer.parseInt(mchtId))
					.andStatusEqualTo("1");
			mchtPlatformContactExample.setOrderByClause("create_date desc");
			List<MchtPlatformContactCustom> mchtPlatformContactCustoms = mchtPlatformContactService
					.selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
			// 招商对接人
			for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
				if ("1".equals(mchtPlatformContactCustom.getContactType())) {
					resMap.put("zsMchtPlatformContact",
							mchtPlatformContactCustom);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	// 招商确认
	@RequestMapping(value = "/mcht/zsConfirm.shtml")
	@ResponseBody
	public Map<String, Object> zsConfirm(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.valueOf(request.getParameter("mchtId"));
			String remarks = request.getParameter("remarks");
			MchtInfoExample example = new MchtInfoExample();
			MchtInfoExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andIdEqualTo(id);
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request)
					.getStaffID()));
			mi.setUpdateDate(new Date());
			mi.setRemarks(remarks);
			mi.setZsAuditStatus("1");
			mi.setZsAuditBy(Integer.parseInt(this.getSessionStaffBean(request)
					.getStaffID()));
			mchtInfoService.updateByExampleSelective(mi, example);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 不签约页面
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/toNotContract.shtml")
	public ModelAndView toNotContract(HttpServletRequest request) {
		String rtPage = "/mcht/toNotContract";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtId = request.getParameter("mchtId");
		resMap.put("id", mchtId);
		return new ModelAndView(rtPage, resMap);
	}

	// 不签约
	@RequestMapping(value = "/mcht/notContract.shtml")
	@ResponseBody
	public Map<String, Object> notContract(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.valueOf(request.getParameter("mchtId"));
			String remarks = request.getParameter("remarks");
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(id);
			mchtInfo.setStatus("3");// 关闭
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(
					request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
			mchtInfoChangeLog.setDelFlag("0");
			mchtInfoChangeLog.setMchtId(id);
			mchtInfoChangeLog.setCreateBy(Integer.parseInt(this
					.getSessionStaffBean(request).getStaffID()));
			mchtInfoChangeLog.setCreateDate(new Date());
			mchtInfoChangeLog.setRemarks(remarks);
			mchtInfoChangeLog.setCreatorType("1");
			mchtInfoChangeLog.setLogType("不签约");
			mchtInfoChangeLog.setLogName(mchtInfo.getCompanyName());
			mchtInfoChangeLog.setBeforeChange("入驻中");
			mchtInfoChangeLog.setAfterChange("关闭");
			mchtInfoService.save(mchtInfo, mchtInfoChangeLog);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 
	 * @Title viewMchtSettled
	 * @Description TODO(查看入驻资料)
	 * @author pengl
	 * @date 2018年5月28日 下午2:34:58
	 */
	@RequestMapping("/mcht/viewMchtSettled.shtml")
	public ModelAndView viewMchtSettled(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/viewMchtSettled");
		String mchtId = request.getParameter("mchtId");
		if (!StringUtil.isEmpty(mchtId)) {
			MchtSettledApplyCustomExample mchtSettledApplyCustomExample = new MchtSettledApplyCustomExample();
			MchtSettledApplyCustomExample.MchtSettledApplyCustomCriteria mchtSettledApplyCustomCriteria = mchtSettledApplyCustomExample
					.createCriteria();
			mchtSettledApplyCustomCriteria.andDelFlagEqualTo("0")
					.andMchtIdEqualTo(Integer.parseInt(mchtId));
			List<MchtSettledApplyCustom> mchtSettledApplyCustomList = mchtSettledApplyService
					.selectMchtSettledApplyCustomByExample(mchtSettledApplyCustomExample);
			if (mchtSettledApplyCustomList != null
					&& mchtSettledApplyCustomList.size() > 0) {
				MchtSettledApplyCustom mchtSettledApplyCustom = mchtSettledApplyCustomList
						.get(0);
				m.addObject("mchtSettledApplyCustom", mchtSettledApplyCustom); // 商家入驻申请
				if (!StringUtils.isEmpty(mchtSettledApplyCustom
						.getZsProductTypeIds())) {
					String[] zsProductTypeIdsArray = mchtSettledApplyCustom
							.getZsProductTypeIds().split(",");
					List<Integer> zsProductTypeIdList = new ArrayList<Integer>();
					for (String zsProductTypeId : zsProductTypeIdsArray) {
						zsProductTypeIdList.add(Integer
								.parseInt(zsProductTypeId));
					}
					ZsProductType zsProductType = zsProductTypeService
							.selectByPrimaryKey(zsProductTypeService
									.selectByPrimaryKey(
											zsProductTypeIdList.get(0))
									.getParentId());
					ProductType productType = productTypeService
							.selectByPrimaryKey(Integer.parseInt(zsProductType
									.getProductTypeIds()));
					m.addObject("productTypeName", productType.getName()); // 申请主类目
					List<String> zsProductTypeDetails = zsProductTypeService
							.getZsProductTypeDetails(zsProductTypeIdList);
					m.addObject("zsProductTypeDetails", zsProductTypeDetails); // 经营类目明细
				}
				// 商家入驻申请图片
				MchtSettledApplyPicExample mchtSettledApplyPicExample = new MchtSettledApplyPicExample();
				mchtSettledApplyPicExample
						.createCriteria()
						.andSettledApplyIdEqualTo(
								mchtSettledApplyCustom.getId())
						.andDelFlagEqualTo("0");
				List<MchtSettledApplyPic> mchtSettledApplyPics = mchtSettledApplyPicService
						.selectByExample(mchtSettledApplyPicExample);
				List<Map<String, Object>> mchtSettledApplyPicList = new ArrayList<Map<String, Object>>();
				for (MchtSettledApplyPic mchtSettledApplyPic : mchtSettledApplyPics) {
					Map<String, Object> pic = new HashMap<String, Object>();
					pic.put("PICTURE_PATH", mchtSettledApplyPic.getPic());
					mchtSettledApplyPicList.add(pic);
				}
				m.addObject("mchtSettledApplyPics", mchtSettledApplyPicList);
				m.addObject("regFeeTypes", DataDicUtil.getStatusList(
						"BU_MCHT_INFO", "REG_FEE_TYPE")); // 商家注册币种
			}
		}
		return m;
	}

	/**
	 * 修改保证金页面
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/toEditContractDeposit.shtml")
	public ModelAndView toEditContractDeposit(HttpServletRequest request) {
		String rtPage = "/mcht/toEditContractDeposit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String mchtId = request.getParameter("mchtId");
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(mchtId));
			resMap.put("mchtId", mchtId);
			resMap.put("mchtInfo",mchtInfo);
			resMap.put("depositType", mchtInfo.getDepositType());
			if (mchtInfo.getContractDeposit() != null) {
				DecimalFormat df = new DecimalFormat("#.00");
				resMap.put("contractDeposit",df.format(mchtInfo.getContractDeposit()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	// 修改保证金
	@RequestMapping(value = "/mcht/saveContractDeposit.shtml")
	@ResponseBody
	public Map<String, Object> saveContractDeposit(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.valueOf(request.getParameter("mchtId"));
			String depositType = request.getParameter("depositType");
			String contractDeposit = request.getParameter("contractDeposit");
			String mchtType = request.getParameter("mchtType");
            String isManageSelf = request.getParameter("isManageSelf");
            MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(id);
			if(!org.apache.commons.lang.StringUtils.equals(mchtType,mchtInfo.getMchtType())){
				//判断商品商家状态
				ProductExample productExample = new ProductExample();
				productExample.createCriteria().andMchtIdEqualTo(id).andStatusEqualTo("1").andDelFlagEqualTo("0");
				List<Product> products = productService.selectByExample(productExample);
				if(!products.isEmpty()){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "修改失败，存在商品未下架或活动未结束或存在有效的优惠券");
					return resMap;
				}
				//判断商家报名的活动状态
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ActivityAreaExample activityAreaExample = new ActivityAreaExample();
				activityAreaExample.createCriteria().andMchtIdEqualTo(id).andActivityBeginTimeLessThanOrEqualTo(sdf.parse(sdf.format(new Date()))).andActivityEndTimeGreaterThanOrEqualTo(sdf.parse(sdf.format(new Date()))).andDelFlagEqualTo("0");
				List<ActivityArea> activityAreas = activityAreaService.selectByExample(activityAreaExample);
				if(!activityAreas.isEmpty()){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "修改失败，存在商品未下架或活动未结束或存在有效的优惠券");
					return resMap;
				}
				//判断商家发布的优惠券状态
				CouponExample couponExample = new CouponExample();
				couponExample.createCriteria().andMchtIdEqualTo(id).andStatusEqualTo("1").andExpiryBeginDateLessThanOrEqualTo(sdf.parse(sdf.format(new Date()))).andExpiryEndDateGreaterThanOrEqualTo(sdf.parse(sdf.format(new Date()))).andDelFlagEqualTo("0");
				List<Coupon> coupons = couponService.selectByExample(couponExample);
				if(!coupons.isEmpty()){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "修改失败，存在商品未下架或活动未结束或存在有效的优惠券");
					return resMap;
				}
			}
			MchtInfo mi = new MchtInfo();
			mi.setId(id);
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			mi.setMchtType(mchtType);
			if ("1".equals(mchtType)){
			mi.setIsManageSelf(isManageSelf);
			}else {
				mi.setIsManageSelf("0");
			}
			mi.setDepositType(depositType);
			mi.setContractDeposit(new BigDecimal(contractDeposit));
			mchtInfoService.updateByPrimaryKeySelective(mi);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 商家反馈列表
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtFeedbacklist.shtml")
	public ModelAndView appMngFeedbackList(HttpServletRequest request) {
		String rtPage = "/mcht/mchtFeedbacklist";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("MchtfeedbackList",
				mchtFeedbackService.getMchtfeedbackList());
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 商家反馈反馈数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtfeedbackd/data.shtml")
	@ResponseBody
	public Map<String, Object> appMngFeedbackData(HttpServletRequest request,
			Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtFeedbackCustomExample mchtFeedbackCustomExample = new MchtFeedbackCustomExample();
			MchtFeedbackCustomCriteria criteria = mchtFeedbackCustomExample
					.createCriteria();
			criteria.andDelFlagEqualTo("0");
			/*
			 * mchtFeedbackCustomExample.setOrderByClause(
			 * " IFNULL(a.deal_date,DATE('2999-01-01')) desc,IFNULL(a.process_date,DATE('1970-01-01')) asc,a.create_date asc "
			 * );
			 */
			mchtFeedbackCustomExample
					.setOrderByClause("a.deal_status asc,a.create_date asc,a.deal_date desc");
			if (!StringUtil.isEmpty(request.getParameter("type"))) {
				String type = request.getParameter("type");
				if (type.equals("1")) {
					criteria.andTypeEqualTo("1");
				}
				if (type.equals("2")) {
					criteria.andTypeEqualTo("2");
				}
			}

			if (!StringUtil.isEmpty(request.getParameter("browser"))) {
				String browser = request.getParameter("browser");
				criteria.andBrowserLike("%" + browser + "%");
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
				criteria.andCreateDateGreaterThanOrEqualTo(dateFormat
						.parse(request.getParameter("date_begin") + " 00:00:00"));
			}

			if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
				criteria.andCreateDateLessThanOrEqualTo(dateFormat
						.parse(request.getParameter("date_end") + " 23:59:59"));
			}

			SimpleDateFormat processdateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			if (!StringUtil.isEmpty(request.getParameter("process_date_begin"))) {
				criteria.andProcessDateGreaterThanOrEqualTo(processdateFormat
						.parse(request.getParameter("process_date_begin")
								+ " 00:00:00"));
			}

			if (!StringUtil.isEmpty(request.getParameter("process_date_end"))) {
				criteria.andProcessDateLessThanOrEqualTo(processdateFormat
						.parse(request.getParameter("process_date_end")
								+ " 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("procesBy"))) {
				criteria.andProcesByEqualTo(Integer.valueOf(request
						.getParameter("procesBy")));

			}
			if (request.getParameter("dealstatus").equals("3")) {
				criteria.andDealStatusEqualTo("3");
			}
			if (request.getParameter("dealstatus").equals("1")) {
				criteria.andDealStatusEqualTo("1");
			}
			if (request.getParameter("dealstatus").equals("2")) {
				criteria.andDealStatusEqualTo("2");
			}
			if (request.getParameter("dealstatus").equals("0")) {
				criteria.andDealStatusEqualTo("0");
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtinfocode"))) {
				criteria.andMchtCodeEqualTo(request
						.getParameter("mchtinfocode"));
			}
			totalCount = mchtFeedbackService
					.countMchtFeedbackCustomByExample(mchtFeedbackCustomExample);

			mchtFeedbackCustomExample.setLimitStart(page.getLimitStart());
			mchtFeedbackCustomExample.setLimitSize(page.getLimitSize());
			List<MchtFeedbackCustom> mchtFeedbackCustoms = mchtFeedbackService
					.selectmchtFeedbackCustomByExample(mchtFeedbackCustomExample);

			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request)
					.getStaffID());

			// 本领取人的协助人可以操作修改处理
			for (MchtFeedbackCustom mchtFeedbackCustom : mchtFeedbackCustoms) {
				if (mchtFeedbackCustom.getProcesBy() != null) {
					Integer mchtFeedbackCustomProcesby = mchtFeedbackCustom
							.getProcesBy();
					PlatformContactExample platformContactExamples = new PlatformContactExample();
					platformContactExamples.createCriteria()
							.andDelFlagEqualTo("0").andStatusEqualTo("0")
							.andStaffIdEqualTo(mchtFeedbackCustomProcesby);
					List<PlatformContact> platformContacts = platformContactService
							.selectByExample(platformContactExamples);

					if (platformContacts != null && platformContacts.size() > 0) {
						for (PlatformContact platformContact : platformContacts) {
							Integer assistantId = platformContact.getId();
							PlatformContactExample platformContactExample = new PlatformContactExample();
							platformContactExample.createCriteria()
									.andDelFlagEqualTo("0")
									.andStatusEqualTo("0")
									.andAssistantIdEqualTo(assistantId);
							List<PlatformContact> platformAssistantContact = platformContactService
									.selectByExample(platformContactExample);

							for (PlatformContact platformcontacts : platformAssistantContact) {
								if (platformcontacts.getStaffId().equals(
										staffId)) {
									mchtFeedbackCustom.setAssistantContact("1");
								}
							}

						}

					}

				}

			}

			resMap.put("Rows", mchtFeedbackCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	// 保存领取人
	@RequestMapping(value = "/mcht/savemchtfeedbackdata.shtml")
	@ResponseBody
	public Map<String, Object> savemchtfeedbackdata(HttpServletRequest request,
			Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			MchtFeedback mchtFeedback = mchtFeedbackService
					.selectByPrimaryKey(Integer.parseInt(id));
			mchtFeedback.setProcesBy(Integer.parseInt(this.getSessionStaffBean(
					request).getStaffID()));
			mchtFeedback.setProcessDate(new Date());
			mchtFeedback.setDealStatus("1");
			mchtFeedbackService.updateByPrimaryKey(mchtFeedback);
			resMap.put("staffName", this.getSessionStaffBean(request)
					.getStaffName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	// 批量领取
	@RequestMapping(value = "/mcht/batchmchtfeedback.shtml")
	@ResponseBody
	public ModelAndView batchmchtfeedback(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap, String id) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg = "";
		try {
			String[] split = id.split(",");
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId = Integer.valueOf(staffBean.getStaffID());
			for (int i = 0; i < split.length; i++) {
				int procesbyid = Integer.valueOf(split[i]);
				MchtFeedbackExample mchtFeedbackExample = new MchtFeedbackExample();
				MchtFeedbackExample.Criteria mchtFeedbackCriteria = mchtFeedbackExample
						.createCriteria();
				mchtFeedbackCriteria.andDelFlagEqualTo("0").andIdEqualTo(
						procesbyid);
				MchtFeedback mchtberFeedback = mchtFeedbackService
						.selectByPrimaryKey(procesbyid);
				mchtberFeedback.setProcesBy(Integer.parseInt(this
						.getSessionStaffBean(request).getStaffID()));
				mchtberFeedback.setProcessDate(new Date());
				mchtberFeedback.setDealStatus("1");
				mchtberFeedback.setUpdateBy(staffId);
				mchtberFeedback.setUpdateDate(new Date());
				mchtFeedbackService.updateByExampleSelective(mchtberFeedback,
						mchtFeedbackExample);
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

	// 根据id获取处理数据信息
	@RequestMapping(value = "/mcht/mchtfeedbackedit.shtml")
	public ModelAndView mchtrFeedbackProcesBy(HttpServletRequest request) {
		String rtPage = "/mcht/editMchtFeedback";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			if (!StringUtil.isEmpty(request.getParameter("id"))) {
				MchtFeedback mchtFeedback = mchtFeedbackService
						.selectByPrimaryKey(Integer.valueOf(request
								.getParameter("id")));
				resMap.put("mchtFeedbacklist", mchtFeedback);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}

	// 修改处理数据
	@ResponseBody
	@RequestMapping(value = "/mcht/mchtfeedbackedits.shtml")
	public ModelAndView mchtFeedbackProcesBylist(HttpServletRequest request,
			String id, String dealStatus, String dealOpinion,
			String relatedOrder) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg = null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId = Integer.valueOf(staffBean.getStaffID());
			MchtFeedbackExample mchtfeedbackExample = new MchtFeedbackExample();
			MchtFeedbackExample.Criteria mchtFeedbackExampleCriteria = mchtfeedbackExample
					.createCriteria();
			mchtFeedbackExampleCriteria.andDelFlagEqualTo("0").andIdEqualTo(
					Integer.valueOf(id));
			MchtFeedback mchtfeedback = new MchtFeedback();
			mchtfeedback.setDealOpinion(dealOpinion);
			mchtfeedback.setRelatedOrder(relatedOrder);
			mchtfeedback.setDealStatus(dealStatus);
			mchtfeedback.setDealDate(new Date());
			mchtfeedback.setUpdateBy(staffId);
			mchtfeedback.setUpdateDate(new Date());
			mchtFeedbackService.updateByExampleSelective(mchtfeedback,
					mchtfeedbackExample);
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

	/**
	 * 获取商家反馈图片
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtfeedbackgetPics.shtml")
	@ResponseBody
	public List<MchtFeedbackPic> mchtFeedbackGetPics(HttpServletRequest request) {
		MchtFeedbackPicExample mchtFeedbackPicExample = new MchtFeedbackPicExample();
		MchtFeedbackPicExample.Criteria criteria = mchtFeedbackPicExample
				.createCriteria();
		criteria.andDelFlagEqualTo("0").andFeedbackIdEqualTo(
				Integer.valueOf(request.getParameter("id")));
		List<MchtFeedbackPic> mchtFeedbackPics = mchtFeedbackPicService
				.selectByExample(mchtFeedbackPicExample);
		return mchtFeedbackPics;
	}

	// 选择批量导出
	@RequestMapping(value = "/mcht/mchtfeedbackCheckout.shtml")
	public void mchtFeedbackdownloadListCheckout(HttpServletRequest request,
			String id, HttpServletResponse response) throws Exception {
		try {
			String[] split = id.split(",");
			List<String[]> datas = new ArrayList<String[]>();
			SimpleDateFormat dateFormat2 = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			for (int i = 0; i < split.length; i++) {
				int mchtberid = Integer.valueOf(split[i]);

				MchtFeedbackCustomExample mchtFeedbackCustomExample = new MchtFeedbackCustomExample();
				MchtFeedbackExample.Criteria mchtfeedbackCriteria = mchtFeedbackCustomExample
						.createCriteria();
				mchtfeedbackCriteria.andDelFlagEqualTo("0").andIdEqualTo(
						mchtberid);
				/* mchtFeedbackCustomExample.setOrderByClause("a.id desc"); */
				mchtFeedbackCustomExample
						.setOrderByClause("a.deal_status asc,a.create_date asc,a.deal_date desc");

				if (!StringUtil.isEmpty(request.getParameter("type"))) {
					String type = request.getParameter("type");
					mchtfeedbackCriteria.andTypeEqualTo(type);
				}

				if (!StringUtil.isEmpty(request.getParameter("browser"))) {
					String browser = request.getParameter("browser");
					mchtfeedbackCriteria.andBrowserLike("%" + browser + "%");
				}

				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
					mchtfeedbackCriteria
							.andCreateDateGreaterThanOrEqualTo(dateFormat
									.parse(request.getParameter("date_begin")
											+ " 00:00:00"));
				}

				if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
					mchtfeedbackCriteria
							.andCreateDateLessThanOrEqualTo(dateFormat
									.parse(request.getParameter("date_end")
											+ " 23:59:59"));
				}

				SimpleDateFormat processdateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				if (!StringUtil.isEmpty(request
						.getParameter("process_date_begin"))) {
					mchtfeedbackCriteria
							.andProcessDateGreaterThanOrEqualTo(processdateFormat
									.parse(request
											.getParameter("process_date_begin")
											+ " 00:00:00"));
				}

				if (!StringUtil.isEmpty(request
						.getParameter("process_date_end"))) {
					mchtfeedbackCriteria
							.andProcessDateLessThanOrEqualTo(processdateFormat
									.parse(request
											.getParameter("process_date_end")
											+ " 23:59:59"));
				}

				List<MchtFeedbackCustom> mchtberFeedbackCustoms = mchtFeedbackService
						.selectmchtFeedbackCustomByExample(mchtFeedbackCustomExample);

				for (MchtFeedbackCustom mchtberFeedbackCustom : mchtberFeedbackCustoms) {
					String dealStatus = "";
					if (mchtberFeedbackCustom.getDealStatus().equals("0")) {
						dealStatus = "未领取";
					} else if (mchtberFeedbackCustom.getDealStatus()
							.equals("1")) {
						dealStatus = "待处理";
					} else if (mchtberFeedbackCustom.getDealStatus()
							.equals("2")) {
						dealStatus = "已处理";
					} else if (mchtberFeedbackCustom.getDealStatus()
							.equals("3")) {
						dealStatus = "不需要处理";
					}
					String[] data = {
							mchtberFeedbackCustom.getMchtinfocode().toString(),
							mchtberFeedbackCustom.getMchtshopname() == null ? ""
									: mchtberFeedbackCustom.getMchtshopname(),
							mchtberFeedbackCustom.getType().equals("1") ? "功能异常 "
									: "其它问题",
							mchtberFeedbackCustom.getContent() == null ? ""
									: mchtberFeedbackCustom.getContent(),
							dateFormat.format(mchtberFeedbackCustom
									.getCreateDate()),
							mchtberFeedbackCustom.getSystem() == null ? ""
									: mchtberFeedbackCustom.getSystem(),
							mchtberFeedbackCustom.getBrowser() == null ? ""
									: mchtberFeedbackCustom.getBrowser(),
							dealStatus,
							mchtberFeedbackCustom.getDealOpinion() == null ? ""
									: mchtberFeedbackCustom.getDealOpinion(),
							mchtberFeedbackCustom.getRelatedOrder() == null ? ""
									: mchtberFeedbackCustom.getRelatedOrder(),
							mchtberFeedbackCustom.getDealDate() == null ? ""
									: dateFormat.format(mchtberFeedbackCustom
											.getDealDate())

					};
					datas.add(data);
				}
			}
			String[] titles = { "商家序号", "店铺名称", "反馈类型", "反馈内容", "反馈时间", "操作系统",
					"浏览器/浏览器版本/版本号", "领取人", "领取时间", "处理状态", "处理意见", "相关订单号",
					"处理时间" };
			ExcelBean excelBean = new ExcelBean(dateFormat2.format(new Date())
					+ "导出商家意见反馈表.xls", "商家意见反馈表", titles);
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 批量导出
	@RequestMapping(value = "/mcht/mcht/mchtfeedbackCheckouts.shtml")
	public void mchtfeedbackdownloadListCheckout(HttpServletRequest request,
			String id, HttpServletResponse response) throws Exception {
		try {

			MchtFeedbackCustomExample mchtFeedbackCustomExample = new MchtFeedbackCustomExample();
			MchtFeedbackExample.Criteria mchtfeedbackCriteria = mchtFeedbackCustomExample
					.createCriteria();
			mchtfeedbackCriteria.andDelFlagEqualTo("0");
			/* mchtFeedbackCustomExample.setOrderByClause("a.id desc"); */
			mchtFeedbackCustomExample
					.setOrderByClause("a.deal_status asc,a.create_date asc,a.deal_date desc");

			if (!StringUtil.isEmpty(request.getParameter("type"))) {
				String type = request.getParameter("type");
				mchtfeedbackCriteria.andTypeEqualTo(type);
			}

			if (!StringUtil.isEmpty(request.getParameter("browser"))) {
				String browser = request.getParameter("browser");
				mchtfeedbackCriteria.andBrowserLike("%" + browser + "%");
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
				mchtfeedbackCriteria
						.andCreateDateGreaterThanOrEqualTo(dateFormat
								.parse(request.getParameter("date_begin")
										+ " 00:00:00"));
			}

			if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
				mchtfeedbackCriteria.andCreateDateLessThanOrEqualTo(dateFormat
						.parse(request.getParameter("date_end") + " 23:59:59"));
			}

			SimpleDateFormat processdateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			if (!StringUtil.isEmpty(request.getParameter("process_date_begin"))) {
				mchtfeedbackCriteria
						.andProcessDateGreaterThanOrEqualTo(processdateFormat
								.parse(request
										.getParameter("process_date_begin")
										+ " 00:00:00"));
			}

			if (!StringUtil.isEmpty(request.getParameter("process_date_end"))) {
				mchtfeedbackCriteria
						.andProcessDateLessThanOrEqualTo(processdateFormat
								.parse(request.getParameter("process_date_end")
										+ " 23:59:59"));
			}

			List<MchtFeedbackCustom> mchtberFeedbackCustoms = mchtFeedbackService
					.selectmchtFeedbackCustomByExample(mchtFeedbackCustomExample);
			SimpleDateFormat dateFormats = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String[] titles = { "商家序号", "店铺名称", "反馈类型", "反馈内容", "反馈时间", "操作系统",
					"浏览器/浏览器版本/版本号", "领取人", "领取时间", "处理状态", "处理意见", "相关订单号",
					"处理时间" };
			ExcelBean excelBean = new ExcelBean(dateFormats.format(new Date())
					+ "导出商家意见反馈表.xls", "商家意见反馈表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for (MchtFeedbackCustom mchtberFeedbackCustom : mchtberFeedbackCustoms) {
				String dealStatus = "";
				if (mchtberFeedbackCustom.getDealStatus().equals("0")) {
					dealStatus = "未领取";
				} else if (mchtberFeedbackCustom.getDealStatus().equals("1")) {
					dealStatus = "待处理";
				} else if (mchtberFeedbackCustom.getDealStatus().equals("2")) {
					dealStatus = "已处理";
				} else if (mchtberFeedbackCustom.getDealStatus().equals("3")) {
					dealStatus = "不需要处理";
				}
				String[] data = {
						mchtberFeedbackCustom.getMchtinfocode().toString(),
						mchtberFeedbackCustom.getMchtshopname() == null ? ""
								: mchtberFeedbackCustom.getMchtshopname(),
						mchtberFeedbackCustom.getType().equals("1") ? "功能异常 "
								: "其它问题",
						mchtberFeedbackCustom.getContent() == null ? ""
								: mchtberFeedbackCustom.getContent(),
						dateFormat
								.format(mchtberFeedbackCustom.getCreateDate()),
						mchtberFeedbackCustom.getSystem() == null ? ""
								: mchtberFeedbackCustom.getSystem(),
						mchtberFeedbackCustom.getBrowser() == null ? ""
								: mchtberFeedbackCustom.getBrowser(),
						mchtberFeedbackCustom.getStaffName() == null ? ""
								: mchtberFeedbackCustom.getStaffName(),
						mchtberFeedbackCustom.getProcessDate() == null ? ""
								: dateFormat.format(mchtberFeedbackCustom
										.getProcessDate()),
						dealStatus,
						mchtberFeedbackCustom.getDealOpinion() == null ? ""
								: mchtberFeedbackCustom.getDealOpinion(),
						mchtberFeedbackCustom.getRelatedOrder() == null ? ""
								: mchtberFeedbackCustom.getRelatedOrder(),
						mchtberFeedbackCustom.getDealDate() == null ? ""
								: dateFormat.format(mchtberFeedbackCustom
										.getDealDate())

				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 商家名称列表
	@RequestMapping(value = "/mcht/mchtInfoNameList.shtml")
	public ModelAndView getMchtinfoNameList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "//mcht/mchtInfoNameList";
		Map<String, Object> resMap = new HashMap<String, Object>();

		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample
				.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1")
				.andDelFlagEqualTo("0");
		List<ProductType> productTypeList = productTypeService
				.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList); // 1级类目

		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request,
					"isCwOrgProductTypeId");
			if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer
						.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);

		return new ModelAndView(rtPage, resMap);
	}

	// 商家名称列表数据
	@ResponseBody
	@RequestMapping(value = "/mcht/getMchtinfoNameList.shtml")
	public Map<String, Object> getMchtinfoNameList(HttpServletRequest request,
			Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample
					.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			mchtInfoCustomCriteria.andStatusNotEqualTo("0");
			mchtInfoCustomExample
					.setOrderByClause("IFNULL(a.cooperate_begin_date,a.create_date) asc");
			if(!StringUtil.isEmpty(request.getParameter("activityAuthStatus"))){//活动开通状态
				mchtInfoCustomCriteria.andActivityAuthStatusEqualTo(request.getParameter("activityAuthStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("shopStatus"))){//商城开通状态
				mchtInfoCustomCriteria.andShopStatusEqualTo(request.getParameter("shopStatus"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) { // 序号
				mchtInfoCustomCriteria.andMchtCodeEqualTo(request
						.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) { // 名称
				mchtInfoCustomCriteria.andMchtNameLike(request
						.getParameter("mchtName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) { // 合作状态
				mchtInfoCustomCriteria.andStatusEqualTo(request
						.getParameter("status"));
			}

			// 钟表运营部状态，只获取主营类目为钟表
			String isCwOrgStatus = this.getParamSession(request,
					"isCwOrgStatus");
			if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request,
						"isCwOrgProductTypeId");
				if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					mchtInfoCustomCriteria
							.andProductTypeIdEqualTo(isCwOrgProductTypeId);
				}
			} else {
				if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) { // 类目
					mchtInfoCustomCriteria
							.andProductTypeIdIsMainEqualTo(request
									.getParameter("productTypeId"));
				}
			}

			if (!StringUtil.isEmpty(request
					.getParameter("startCooperateBeginDate"))) { // 开通日期
				mchtInfoCustomCriteria.andStartCooperateBeginDate(request
						.getParameter("startCooperateBeginDate") + " 00:00:00");
			}
			if (!StringUtil.isEmpty(request
					.getParameter("endCooperateBeginDate"))) {
				mchtInfoCustomCriteria.andEndCooperateBeginDate(request
						.getParameter("endCooperateBeginDate") + " 23:59:59");
			}
			totalCount = mchtInfoService.countByExample(mchtInfoCustomExample);
			mchtInfoCustomExample.setLimitSize(page.getLimitSize());
			mchtInfoCustomExample.setLimitStart(page.getLimitStart());
			List<MchtInfoCustom> mchtInfoCustomList = mchtInfoService
					.selectByExample(mchtInfoCustomExample);

			resMap.put("Rows", mchtInfoCustomList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resMap;
	}

	// 导出商家名称列表数据
	@RequestMapping(value = "/mcht/getMchtinfonameListexport.shtml")
	public void getMchtinfoNameListExport(HttpServletRequest request,String id, HttpServletResponse response) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			mchtInfoCustomCriteria.andStatusNotEqualTo("0");
			mchtInfoCustomExample.setOrderByClause("IFNULL(a.cooperate_begin_date,a.create_date) asc");
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) { // 序号
				mchtInfoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) { // 名称
				mchtInfoCustomCriteria.andMchtNameLike(request.getParameter("mchtName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) { // 合作状态
				mchtInfoCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}

			// 钟表运营部状态，只获取主营类目为钟表
			String isCwOrgStatus = this.getParamSession(request,"isCwOrgStatus");
			if (!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request,"isCwOrgProductTypeId");
				if (!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					mchtInfoCustomCriteria.andProductTypeIdEqualTo(isCwOrgProductTypeId);
				}
			} else {
				if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) { // 类目
					mchtInfoCustomCriteria.andProductTypeIdIsMainEqualTo(request.getParameter("productTypeId"));
				}
			}
			if (!StringUtil.isEmpty(request.getParameter("startCooperateBeginDate"))) { // 开通日期
				mchtInfoCustomCriteria.andCooperateBeginDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("startCooperateBeginDate")+ " 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("endCooperateBeginDate"))) {
				mchtInfoCustomCriteria.andCooperateBeginDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCooperateBeginDate")+ " 23:59:59"));
			}

			List<MchtInfoCustom> mchtInfoCustomList = mchtInfoService.selectByExample(mchtInfoCustomExample);

			SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String[] titles = { "商家序号", "公司名称", "合作状态", "店铺名称", "主营类目", "开通时间" };
			ExcelBean excelBean = new ExcelBean(dateFormats.format(new Date())+ "导出商家名称列表.xls", "商家名称列表", titles);
			List<String[]> datas = new ArrayList<String[]>();

			for (MchtInfoCustom mchtInfoCustom : mchtInfoCustomList) {
				String dealStatus = "";
				if (mchtInfoCustom.getStatus().equals("1")) {
					dealStatus = "正常";
				} else if (mchtInfoCustom.getStatus().equals("2")) {
					dealStatus = "暂停";
				} else if (mchtInfoCustom.getStatus().equals("3")) {
					dealStatus = "关闭";
				}
				String[] data = {
						mchtInfoCustom.getMchtCode().toString(),mchtInfoCustom.getCompanyName() == null ? "": mchtInfoCustom.getCompanyName(),
						dealStatus,
						mchtInfoCustom.getShopName() == null ? "": mchtInfoCustom.getShopName(),
						mchtInfoCustom.getProductTypeName() == null ? "": mchtInfoCustom.getProductTypeName(),
						mchtInfoCustom.getCooperateBeginDate() == null ? "": dateFormats.format(mchtInfoCustom.getCooperateBeginDate())
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 不合作页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/toNotCooperate.shtml")
	public String toNotCooperate(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return "/mcht/submitEnter/toNotCooperate";
	}

	// 确认不合作
	@RequestMapping(value = "/mcht/notCooperate.shtml")
	@ResponseBody
	public Map<String, Object> notCooperate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			String remarks = request.getParameter("remarks");
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(id);
			mchtInfo.setZsTotalAuditStatus("3");
			mchtInfo.setZsTotalAuditRemarks(remarks);
			mchtInfo.setZsTotalAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setRemarks(remarks);
			mchtInfo.setStatus("5");//不入驻,STORY #1170
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 招商审核页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/toMchtInfoZsAudit.shtml")
	public String toMchtInfoZsAudit(Model model, HttpServletRequest request) {
		Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
		model.addAttribute("mchtId", mchtId);
		MchtInfo mi = mchtInfoService.selectByPrimaryKey(mchtId);
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		mi.setZsTotalAuditBy(staffID);
		mchtInfoService.updateByPrimaryKeySelective(mi);
		
		MchtInfoCustom mchtInfo = mchtInfoService.selectMchtInfoCustomById(mchtId);
		MchtProductTypeExample mpte = new MchtProductTypeExample();
		mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andStatusEqualTo("1").andIsMainEqualTo("1");
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mpte);
		if (mchtProductTypes != null && mchtProductTypes.size() > 0) {
			ProductType productType = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
			model.addAttribute("productTypeName", productType.getName());
			model.addAttribute("mchtProductTypeStatus", mchtProductTypes.get(0).getStatus());
		}
		//商品主要品类
		if(org.apache.commons.lang.StringUtils.isNotBlank(String.valueOf(mchtInfo.getProductType2Id()))){
			ProductType productType = productTypeService.selectByPrimaryKey(mchtInfo.getProductType2Id());
			if(productType != null){
				model.addAttribute("productType2Name",productType.getName());
			}
			model.addAttribute("mchtInfo", mchtInfo);
		}
		if(mchtInfo.getFeeRate() == null){
			MchtSettledApplyExample msae = new MchtSettledApplyExample();
			msae.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
			List<MchtSettledApply> mchtSettledApplys = mchtSettledApplyService.selectByExample(msae);
			if(mchtSettledApplys!=null && mchtSettledApplys.size()>0){
				model.addAttribute("feeRate", mchtSettledApplys.get(0).getFeeRate());
			}
		}else{
			model.addAttribute("feeRate", mchtInfo.getFeeRate());
		}
		
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		List<String> zsAuditStatusList = new ArrayList<String>();
		zsAuditStatusList.add("5");
		zsAuditStatusList.add("6");
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andZsAuditStatusIn(zsAuditStatusList);
		List<MchtProductBrand> blackMchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
		model.addAttribute("blackMchtProductBrands", blackMchtProductBrands);

		MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
		List<String> zsAuditStatuList = new ArrayList<String>();
		zsAuditStatuList.add("1");
		zsAuditStatuList.add("4");
		mchtProductBrandExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andZsAuditStatusIn(zsAuditStatuList);
		List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.selectByCustomExample2(mchtProductBrandExample);
		for (MchtProductBrandCustom mchtProductBrandCustom : mchtProductBrandCustoms) {
			Integer mchtProductBrandId = mchtProductBrandCustom.getId();
			MchtProductBrand mpb = new MchtProductBrand();
			mpb.setZsAuditBy(staffID);
			mpb.setZsAuditDate(new Date());
			mpb.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mpb.setUpdateDate(new Date());
			MchtProductBrandExample example = new MchtProductBrandExample();
			example.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtProductBrandCustom.getId());
			mchtProductBrandService.updateByExampleSelective(mpb, example);

			MchtBrandAptitudeExample mbae = new MchtBrandAptitudeExample();
			mbae.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(mbae);
			for (MchtBrandAptitudeCustom mchtBrandAptitudeCustom : mchtBrandAptitudeCustoms) {
				Integer mchtBrandAptitudeId = mchtBrandAptitudeCustom.getId();
				MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
				mbape.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeId);
				List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
				List<Map<String, Object>> mchtBrandAptitudePicList = new ArrayList<Map<String, Object>>();
				for (MchtBrandAptitudePic mchtBrandAptitudePic : mchtBrandAptitudePics) {
					Map<String, Object> pic = new HashMap<String, Object>();
					pic.put("PICTURE_PATH", mchtBrandAptitudePic.getPic());
					mchtBrandAptitudePicList.add(pic);
				}
				mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePicList);
			}
			mchtProductBrandCustom.setMchtBrandAptitudeCustoms(mchtBrandAptitudeCustoms);

			MchtPlatformAuthPicExample mpape = new MchtPlatformAuthPicExample();
			mpape.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService.selectByExample(mpape);
			List<Map<String, Object>> mchtPlatformAuthPicList = new ArrayList<Map<String, Object>>();
			for (MchtPlatformAuthPic mchtPlatformAuthPic : mchtPlatformAuthPics) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtPlatformAuthPic.getPic());
				mchtPlatformAuthPicList.add(pic);
			}
			mchtProductBrandCustom.setMchtPlatformAuthPics(mchtPlatformAuthPicList);

			MchtBrandInvoicePicExample mbipe = new MchtBrandInvoicePicExample();
			mbipe.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicServer.selectByExample(mbipe);
			List<Map<String, Object>> mchtBrandInvoicePicList = new ArrayList<Map<String, Object>>();
			for (MchtBrandInvoicePic mchtBrandInvoicePic : mchtBrandInvoicePics) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandInvoicePic.getPic());
				mchtBrandInvoicePicList.add(pic);
			}
			mchtProductBrandCustom.setMchtBrandInvoicePics(mchtBrandInvoicePicList);

			MchtBrandInspectionPicExample mbipe2 = new MchtBrandInspectionPicExample();
			mbipe2.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicServer.selectByExample(mbipe2);
			List<Map<String, Object>> mchtBrandInspectionPicList = new ArrayList<Map<String, Object>>();
			for (MchtBrandInspectionPic mchtBrandInspectionPic : mchtBrandInspectionPics) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandInspectionPic.getPic());
				mchtBrandInspectionPicList.add(pic);
			}
			mchtProductBrandCustom.setMchtBrandInspectionPics(mchtBrandInspectionPicList);

			MchtBrandOtherPicExample mbope = new MchtBrandOtherPicExample();
			mbope.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicServer.selectByExample(mbope);
			List<Map<String, Object>> mchtBrandOtherPicList = new ArrayList<Map<String, Object>>();
			for (MchtBrandOtherPic mchtBrandOtherPic : mchtBrandOtherPics) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandOtherPic.getPic());
				mchtBrandOtherPicList.add(pic);
			}
			mchtProductBrandCustom.setMchtBrandOtherPics(mchtBrandOtherPicList);

			MchtBrandProductTypeExample mbpte = new MchtBrandProductTypeExample();
			mbpte.createCriteria().andDelFlagEqualTo("0")
					.andMchtProductBrandIdEqualTo(mchtProductBrandId)
					.andTLevelEqualTo(2);
			List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms = mchtBrandProductTypeService
					.selectCustomByExample(mbpte);
			mchtProductBrandCustom
					.setMchtBrandProductTypeCustoms(mchtBrandProductTypeCustoms);
		}
		model.addAttribute("mchtProductBrandCustoms", mchtProductBrandCustoms);
		return "mcht/submitEnter/toMchtInfoZsAudit";
	}

	// 招商审核
	@RequestMapping(value = "/mcht/mchtInfoZsAudit.shtml")
	@ResponseBody
	public Map<String, Object> mchtInfoZsAudit(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			String shopNameAuditStatus = request.getParameter("shopNameAuditStatus");
			String shopNameAuditRemarks = request.getParameter("shopNameAuditRemarks");
			String shopNameAuditInnerRemarks = request.getParameter("shopNameAuditInnerRemarks");
			String contractDeposit = request.getParameter("contractDeposit");
			String feeRate = request.getParameter("feeRate");
			String zsTotalAuditStatus = request.getParameter("zsTotalAuditStatus");
			String zsTotalAuditRemarks = request.getParameter("zsTotalAuditRemarks");
			String mchtProductBrandJsonStr = request.getParameter("mchtProductBrandJsonStr");
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setShopNameAuditStatus(shopNameAuditStatus);
			mchtInfo.setShopNameAuditRemarks(shopNameAuditRemarks);
			mchtInfo.setShopNameAuditInnerRemarks(shopNameAuditInnerRemarks);
			mchtInfo.setContractDeposit(new BigDecimal(contractDeposit));
			mchtInfo.setZsTotalAuditStatus(zsTotalAuditStatus);
			mchtInfo.setZsTotalAuditRemarks(zsTotalAuditRemarks);
			mchtInfo.setZsTotalAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if (zsTotalAuditStatus.equals("2")) {// 通过
				MchtProductBrandExample mpbe = new MchtProductBrandExample();
				mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andZsAuditStatusEqualTo("2");
				int count = mchtProductBrandService.countByExample(mpbe);
				if(count == 0){
					boolean alertError = true;
					JSONArray mchtProductBrandJsonArray = JSONArray.fromObject(mchtProductBrandJsonStr);
					for(int i=0;i<mchtProductBrandJsonArray.size();i++){
						JSONObject mchtProductBrandJsonObject = (JSONObject)mchtProductBrandJsonArray.get(i);
						String zsAuditStatus = mchtProductBrandJsonObject.getString("zsAuditStatus");
						if(zsAuditStatus.equals("2")){//2 审核通过
							alertError = false;
							break;
						}else{
							continue;
						}
					}
					if(alertError){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "当前商家还没有一个品牌是招商审核通过的，招商总确认无法通过");
						return resMap;
					}
				}
				mchtInfo.setZsTotalAuditDate(new Date());
				mchtInfo.setTotalAuditStatus("0");
				mchtInfo.setCommitAuditDate(new Date());
				if(StringUtils.isEmpty(mchtInfo.getIsTotalAuditReCommit())){
					mchtInfo.setIsTotalAuditReCommit("0");
				}
			} else {
				mchtInfo.setTotalAuditStatus("4");
				if(zsTotalAuditStatus.equals("5") || zsTotalAuditStatus.equals("6")){
					mchtInfo.setStatus("3");
					mchtInfo.setStatusDate(new Date());
				}
			}
			mchtInfo.setFeeRate(new BigDecimal(feeRate));
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			mchtInfo.setUpdateBy(staffID);
			List<MchtInfoChangeLog> mchtInfoChangeLogs = new ArrayList<MchtInfoChangeLog>();
			// 店铺信息审核
			MchtInfoChangeLog micl = new MchtInfoChangeLog();
			micl.setMchtId(mchtId);
			micl.setCreateDate(new Date());
			micl.setCreateBy(staffID);
			micl.setDelFlag("0");
			micl.setLogType("店铺名审核");
			micl.setBeforeChange("审核中");
			if (shopNameAuditStatus.equals("3")) {
				micl.setAfterChange("通过");
			} else if (shopNameAuditStatus.equals("4")) {
				micl.setAfterChange("驳回");
			}
			micl.setLogName(mchtInfo.getShopName());
			micl.setRemarks(shopNameAuditRemarks);
			mchtInfoChangeLogs.add(micl);
			// 招商总确认审核
			MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
			mchtInfoChangeLog.setMchtId(mchtId);
			mchtInfoChangeLog.setCreateDate(new Date());
			mchtInfoChangeLog.setCreateBy(staffID);
			mchtInfoChangeLog.setDelFlag("0");
			mchtInfoChangeLog.setLogType("招商确认信息");
			mchtInfoChangeLog.setBeforeChange("待审");
			if (zsTotalAuditStatus.equals("2")) {
				mchtInfoChangeLog.setAfterChange("通过");
			} else if (zsTotalAuditStatus.equals("3")) {
				mchtInfo.setStatus("0");//入驻中
				mchtInfoChangeLog.setAfterChange("驳回");
			} else if (zsTotalAuditStatus.equals("5")) {
				mchtInfoChangeLog.setAfterChange("不签约");
				mchtInfo.setStatus("5");//不入驻
			} else if (zsTotalAuditStatus.equals("6")) {
				mchtInfoChangeLog.setAfterChange("黑名单");
				mchtInfo.setStatus("5");//不入驻
			}
			mchtInfoChangeLog.setLogName(mchtInfo.getCompanyName());
			mchtInfoChangeLog.setRemarks(zsTotalAuditRemarks);
			mchtInfoChangeLogs.add(mchtInfoChangeLog);
			mchtProductBrandService.zsCheckMchtProductBrands(mchtInfo,mchtProductBrandJsonStr, mchtInfoChangeLogs);
			// 发送短信
			if (zsTotalAuditStatus.equals("3")) {// 驳回
				MchtUserExample mue = new MchtUserExample();
				mue.createCriteria().andDelFlagEqualTo("0")
						.andMchtIdEqualTo(mchtId);
				List<MchtUser> mchtUsers = mchtUserService.selectByExample(mue);
				if (mchtUsers != null && mchtUsers.size() > 0) {
					if (!StringUtils.isEmpty(mchtUsers.get(0).getMobile())) {
						JSONObject param = new JSONObject();
						JSONObject reqData = new JSONObject();
						reqData.put("mobile", mchtUsers.get(0).getMobile());
						reqData.put("content", "您提交的入驻信息与业务洽谈内容不一致，请及时登入后台修改。 ");
						reqData.put("smsType", "4");
						param.put("reqData", reqData);
						try {
							JSONObject result = JSONObject.fromObject(HttpUtil
									.sendPostRequest(SysConfig.msgServerUrl
											+ "/api/sms/sendImmediately",
											CommonUtil.createReqData(param)
													.toString()));
							if (!"0000".equals(result.getString("returnCode"))) {
								logger.info("短信发送用户失败！！！！！");
							}
						} catch (Exception e) {
							e.printStackTrace();
							return resMap;
						}
					}
				}
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 法务审核页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/toMchtInfoAudit.shtml")
	public String toMchtInfoAudit(Model model, HttpServletRequest request) {
		Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
		model.addAttribute("mchtId", mchtId);
		MchtInfo mi = mchtInfoService.selectByPrimaryKey(mchtId);
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		mi.setTotalAuditBy(staffID);
		mchtInfoService.updateByPrimaryKeySelective(mi);

		MchtInfoCustom mchtInfo = mchtInfoService.selectMchtInfoCustomById(mchtId);
		model.addAttribute("mchtInfo", mchtInfo);
		
		List<Map<String, Object>> idcardImgList = new ArrayList<Map<String, Object>>();
		Map<String, Object> pic1 = new HashMap<String, Object>();
		pic1.put("PICTURE_PATH", mchtInfo.getCorporationIdcardImg1());
		idcardImgList.add(pic1);
		Map<String, Object> pic2 = new HashMap<String, Object>();
		pic2.put("PICTURE_PATH", mchtInfo.getCorporationIdcardImg2());
		idcardImgList.add(pic2);
		model.addAttribute("idcardImgList", idcardImgList);
		
		MchtProductBrandExample zsmpbe = new MchtProductBrandExample();
		List<String> zsAuditStatusList = new ArrayList<String>();
		zsAuditStatusList.add("5");
		zsAuditStatusList.add("6");
		zsmpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andZsAuditStatusIn(zsAuditStatusList);
		List<MchtProductBrand> zsBlackMchtProductBrands = mchtProductBrandService.selectByExample(zsmpbe);
		
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		List<String> auditStatusList = new ArrayList<String>();
		auditStatusList.add("5");
		auditStatusList.add("6");
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andAuditStatusIn(auditStatusList);
		List<MchtProductBrand> blackMchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
		zsBlackMchtProductBrands.addAll(blackMchtProductBrands);
		model.addAttribute("blackMchtProductBrands", zsBlackMchtProductBrands);

		MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
		mchtProductBrandExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andZsAuditStatusEqualTo("2").andAuditStatusEqualTo("1");
		List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.selectByCustomExample2(mchtProductBrandExample);
		for (MchtProductBrandCustom mchtProductBrandCustom : mchtProductBrandCustoms) {
			Integer mchtProductBrandId = mchtProductBrandCustom.getId();
			MchtProductBrand mpb = new MchtProductBrand();
			mpb.setAuditBy(staffID);
			mpb.setAuditDate(new Date());
			mpb.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mpb.setUpdateDate(new Date());
			MchtProductBrandExample example = new MchtProductBrandExample();
			example.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtProductBrandCustom.getId());
			mchtProductBrandService.updateByExampleSelective(mpb, example);

			MchtBrandAptitudeExample mbae = new MchtBrandAptitudeExample();
			mbae.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(mbae);
			for (MchtBrandAptitudeCustom mchtBrandAptitudeCustom : mchtBrandAptitudeCustoms) {
				Integer mchtBrandAptitudeId = mchtBrandAptitudeCustom.getId();
				MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
				mbape.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeId);
				List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
				List<Map<String, Object>> mchtBrandAptitudePicList = new ArrayList<Map<String, Object>>();
				for (MchtBrandAptitudePic mchtBrandAptitudePic : mchtBrandAptitudePics) {
					Map<String, Object> pic = new HashMap<String, Object>();
					pic.put("PICTURE_PATH", mchtBrandAptitudePic.getPic());
					mchtBrandAptitudePicList.add(pic);
				}
				mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePicList);
			}
			mchtProductBrandCustom.setMchtBrandAptitudeCustoms(mchtBrandAptitudeCustoms);

			MchtPlatformAuthPicExample mpape = new MchtPlatformAuthPicExample();
			mpape.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService.selectByExample(mpape);
			List<Map<String, Object>> mchtPlatformAuthPicList = new ArrayList<Map<String, Object>>();
			for (MchtPlatformAuthPic mchtPlatformAuthPic : mchtPlatformAuthPics) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtPlatformAuthPic.getPic());
				mchtPlatformAuthPicList.add(pic);
			}
			mchtProductBrandCustom.setMchtPlatformAuthPics(mchtPlatformAuthPicList);

			MchtBrandInvoicePicExample mbipe = new MchtBrandInvoicePicExample();
			mbipe.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicServer.selectByExample(mbipe);
			List<Map<String, Object>> mchtBrandInvoicePicList = new ArrayList<Map<String, Object>>();
			for (MchtBrandInvoicePic mchtBrandInvoicePic : mchtBrandInvoicePics) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandInvoicePic.getPic());
				mchtBrandInvoicePicList.add(pic);
			}
			mchtProductBrandCustom.setMchtBrandInvoicePics(mchtBrandInvoicePicList);

			MchtBrandInspectionPicExample mbipe2 = new MchtBrandInspectionPicExample();
			mbipe2.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicServer.selectByExample(mbipe2);
			List<Map<String, Object>> mchtBrandInspectionPicList = new ArrayList<Map<String, Object>>();
			for (MchtBrandInspectionPic mchtBrandInspectionPic : mchtBrandInspectionPics) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandInspectionPic.getPic());
				mchtBrandInspectionPicList.add(pic);
			}
			mchtProductBrandCustom.setMchtBrandInspectionPics(mchtBrandInspectionPicList);

			MchtBrandOtherPicExample mbope = new MchtBrandOtherPicExample();
			mbope.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicServer.selectByExample(mbope);
			List<Map<String, Object>> mchtBrandOtherPicList = new ArrayList<Map<String, Object>>();
			for (MchtBrandOtherPic mchtBrandOtherPic : mchtBrandOtherPics) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("PICTURE_PATH", mchtBrandOtherPic.getPic());
				mchtBrandOtherPicList.add(pic);
			}
			mchtProductBrandCustom.setMchtBrandOtherPics(mchtBrandOtherPicList);

			MchtBrandProductTypeExample mbpte = new MchtBrandProductTypeExample();
			mbpte.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId).andTLevelEqualTo(2);
			List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms = mchtBrandProductTypeService.selectCustomByExample(mbpte);
			mchtProductBrandCustom.setMchtBrandProductTypeCustoms(mchtBrandProductTypeCustoms);
		}
		model.addAttribute("mchtProductBrandCustoms", mchtProductBrandCustoms);
		
		// 营业执照(兼容旧数据)
		MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId());
		List<MchtBlPic> mchtBlPics = mchtBlPicService.selectByExample(mchtBlPicExample);
		if(mchtBlPics == null || mchtBlPics.size() == 0){
			MchtBlPic mbp = new MchtBlPic();
			mbp.setPic(mi.getBlPic());
			mchtBlPics.add(mbp);
		}
		List<Map<String, Object>> blPicList = new ArrayList<Map<String, Object>>();
		for (MchtBlPic mchtBlPic : mchtBlPics) {
			Map<String, Object> pic = new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtBlPic.getPic());
			blPicList.add(pic);
		}
		model.addAttribute("blPicList", blPicList);
		//银行开户许可证
		List<Map<String, String>> mchtBoaalPicPics = new ArrayList<Map<String,String>>();
		Map<String, String> boaalPics = new HashMap<String, String>();
		if(!StringUtil.isBlank(mchtInfo.getBoaalPic())){
			boaalPics.put("PICTURE_PATH", mchtInfo.getBoaalPic());
			mchtBoaalPicPics.add(boaalPics);
		}
		model.addAttribute("mchtBoaalPicPics",mchtBoaalPicPics);
		PlatformContactExample pceFw = new PlatformContactExample();
		PlatformContactExample.Criteria pcecFw = pceFw.createCriteria();
		pcecFw.andDelFlagEqualTo("0");
		pcecFw.andContactTypeEqualTo("7");// 法务对接人
		pcecFw.andStatusEqualTo("0");
		pcecFw.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> pcsFw = platformContactService.selectByExample(pceFw);
		if(pcsFw!=null && pcsFw.size()>0){
			MchtPlatformContactExample e = new MchtPlatformContactExample();
			e.createCriteria().andDelFlagEqualTo("0").andPlatformContactIdEqualTo(pcsFw.get(0).getId()).andMchtIdEqualTo(mchtId);
			List<MchtPlatformContact> mchtPlatformContacts = mchtPlatformContactService.selectByExample(e);
			if(mchtPlatformContacts!=null && mchtPlatformContacts.size()>0){
				model.addAttribute("mchtFwContactId", mchtPlatformContacts.get(0).getPlatformContactId());
			}
		}
		
		//法务专员
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(43).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			model.addAttribute("role43", true);
		}else{
			model.addAttribute("role43", false);
		}
		
		//获取商家对接人信息
		List<String> auditStatusList2 = Arrays.asList("0","2");
		MchtContactExample mchtContactExample = new MchtContactExample();
		mchtContactExample.createCriteria().andDelFlagEqualTo("0").andAuditStatusIn(auditStatusList2).andMchtIdEqualTo(mchtId);
		mchtContactExample.setOrderByClause(" contact_type ASC");
		List<MchContactCustom> mchtContactList = mchtContactService.selectByCustomExample(mchtContactExample);
		model.addAttribute("mchtContactList",mchtContactList);
		for (MchContactCustom mchContactCustom : mchtContactList) {
			List<Area> address = areaService.getAddress(mchContactCustom);
			mchContactCustom.setCountyName(address.get(0).getAreaName());
			mchContactCustom.setProvinceName(address.get(1).getAreaName());
			mchContactCustom.setCityName(address.get(2).getAreaName());
		}
		//店铺总负责人身份证正反面
		List<Object> totalIdCardImgList = new ArrayList<Object>();
		if(!mchtContactList.isEmpty()){
			for (int i=0;i<mchtContactList.size();i++) {
				List<Map<String, Object>> itemIdcardImgList = new ArrayList<Map<String, Object>>();
				Map<String, Object> pic3 = new HashMap<String, Object>();
				if(org.apache.commons.lang.StringUtils.isNotBlank(mchtContactList.get(i).getIdcardImg1())){
					pic3.put("PICTURE_PATH", mchtContactList.get(i).getIdcardImg1());	
					itemIdcardImgList.add(pic3);
				}
				Map<String, Object> pic4 = new HashMap<String, Object>();
				if(org.apache.commons.lang.StringUtils.isNotBlank(mchtContactList.get(i).getIdcardImg2())){
					pic4.put("PICTURE_PATH", mchtContactList.get(i).getIdcardImg2());
					itemIdcardImgList.add(pic4);
				}				
				totalIdCardImgList.add(itemIdcardImgList);
			}
		model.addAttribute("totalIdCardImgList", totalIdCardImgList);
		List<Map<String, Object>> idcardImgList2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> pic5 = new HashMap<String, Object>();
		pic5.put("PICTURE_PATH", mchtContactList.get(0).getIdcardImg());
		idcardImgList2.add(pic5);
		model.addAttribute("contactIdcardImg", idcardImgList2);
		}
		return "mcht/submitEnter/toMchtInfoAudit";
	}

	//主要主营品牌类型修改（法务）
	@RequestMapping(value = "/mcht/modifyProductType2.shtml")
	@ResponseBody
	public ModelAndView getProductType2(HttpServletRequest request) {
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mchtInfo", mchtInfo);
		return new ModelAndView("mcht/submitEnter/modifyProductType2",map);
	}
	
	@RequestMapping(value = "/mcht/productType2Submit.shtml")
	@ResponseBody
	public Map<String, Object> modifyProductType2(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		 resMap.put("returnCode", "0000");
		 resMap.put("returnMsg", "修改成功");
	    try{
	    	MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
	    	mchtInfo.setBrandType(request.getParameter("brandType"));
	    	mchtInfo.setBrandTypeDesc(request.getParameter("brandTypeDesc"));
	    	mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		 return resMap;
		} catch (Exception e) {
		 resMap.put("returnCode", "4004");
		 resMap.put("returnMsg","修改失败");
		 e.printStackTrace();
		 return resMap;
		}
	}

	//重新加载法务审核页面
	@RequestMapping(value = "/mcht/freshenFwAudit.shtml")
	@ResponseBody
	public Map<String, Object> freshenFwAudit(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		Integer mchtId = Integer.parseInt(request.getParameter("id"));
		MchtInfo mi = mchtInfoService.selectByPrimaryKey(mchtId);
		map.put("mchtInfo", mi);
		return map;
	}
	
	// 法务审核
	@SuppressWarnings({ "deprecation", "unchecked" })
	@RequestMapping(value = "/mcht/mchtInfoAudit.shtml")
	@ResponseBody
	public Map<String, Object> mchtInfoAudit(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			String isCompanyInfPerfect = request.getParameter("isCompanyInfPerfect");
			String companyInfAuditRemarks = request.getParameter("companyInfAuditRemarks");
			String companyInfAuditInnerRemarks = request.getParameter("companyInfAuditInnerRemarks");
			String agreementBeginDate = request.getParameter("agreementBeginDate");
			String agreementEndDate = request.getParameter("agreementEndDate");
			String contractDeposit = request.getParameter("contractDeposit");
			String depositContent = request.getParameter("depositContent");
			String totalAuditStatus = request.getParameter("totalAuditStatus");
			String totalAuditRemarks = request.getParameter("totalAuditRemarks");
			String mchtProductBrandJsonStr = request.getParameter("mchtProductBrandJsonStr");
			String mchtIdcardImgs = request.getParameter("mchtIdcardImgs");
			String mchtBlPics = request.getParameter("mchtBlPics");
			String licenseIsMust = request.getParameter("licenseIsMust");
			String businessLicensePic = request.getParameter("businessLicensePic");
			String licenseStatus = request.getParameter("licenseStatus");
			String licenseRejectReason = request.getParameter("licenseRejectReason");
			String mchtContacts = request.getParameter("mchtContacts");
			String mchtContactPictures11 = request.getParameter("mchtContactPictures11");
			String mchtBoaalPicPicstures = request.getParameter("mchtBoaalPicPicstures");
			//TODO 身份证正反面和营业执照处理
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if(!StringUtils.isEmpty(mchtIdcardImgs)){
				JSONArray mchtIdcardImgArray = JSONArray.fromObject(mchtIdcardImgs);
				JSONObject firstJo = (JSONObject)mchtIdcardImgArray.get(0);
				mchtInfo.setCorporationIdcardImg1(firstJo.getString("picPath"));
				if(mchtIdcardImgArray.size()>1){
					JSONObject secondJo = (JSONObject)mchtIdcardImgArray.get(1);
					mchtInfo.setCorporationIdcardImg2(secondJo.getString("picPath"));
				}else{
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "请上传身份证正面和背面");
					return resMap;
				}
			}
			MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
			mchtBlPicExample.createCriteria().andMchtIdEqualTo(mchtId).andDelFlagEqualTo("0");
			MchtBlPic mchtBlPic = new MchtBlPic();
			mchtBlPic.setDelFlag("1");
			mchtBlPic.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtBlPic.setUpdateDate(new Date());
			mchtBlPicService.updateByExampleSelective(mchtBlPic, mchtBlPicExample);
			if(!StringUtils.isEmpty(mchtBlPics)){
				JSONArray mchtBlPicArray = JSONArray.fromObject(mchtBlPics); 
				Iterator<JSONObject> mchtBlPicIt = mchtBlPicArray.iterator();
				while (mchtBlPicIt.hasNext()) {
					JSONObject mchtBlPicObject = mchtBlPicIt.next();
					MchtBlPicExample mbpe = new MchtBlPicExample();
					mbpe.createCriteria().andPicEqualTo(mchtBlPicObject.getString("picPath")).andMchtIdEqualTo(mchtInfo.getId());
					mchtBlPic = new MchtBlPic();
					mchtBlPic.setDelFlag("0");
					int updateCount = mchtBlPicService.updateByExampleSelective(mchtBlPic, mbpe);
					if (updateCount > 0) {
						continue;
					}
					MchtBlPic mbp = new MchtBlPic();
					mbp.setMchtId(mchtInfo.getId());
					mbp.setPic(mchtBlPicObject.getString("picPath"));
					mbp.setCreateDate(new Date());
					mbp.setUpdateDate(new Date());
					mbp.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					mbp.setDelFlag("0");
					mchtBlPicService.insertSelective(mbp);
				}
			}
			mchtInfo.setBoaalPic(mchtBoaalPicPicstures);
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setIsCompanyInfPerfect(isCompanyInfPerfect);
			mchtInfo.setCompanyInfAuditRemarks(companyInfAuditRemarks);
			mchtInfo.setCompanyInfAuditInnerRemarks(companyInfAuditInnerRemarks);
			mchtInfo.setContractDeposit(new BigDecimal(contractDeposit));
			mchtInfo.setDepositContent(depositContent);
			mchtInfo.setTotalAuditStatus(totalAuditStatus);
			mchtInfo.setTotalAuditRemarks(totalAuditRemarks);
			mchtInfo.setTotalAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			MchtContract mchtContract = new MchtContract();
			if (totalAuditStatus.equals("2")) {// 通过
				MchtProductBrandExample mpbe = new MchtProductBrandExample();
				mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andAuditStatusEqualTo("3");
				int count = mchtProductBrandService.countByExample(mpbe);
				if(count == 0){
					boolean alertError = true;
					JSONArray mchtProductBrandJsonArray = JSONArray.fromObject(mchtProductBrandJsonStr);
					for(int i=0;i<mchtProductBrandJsonArray.size();i++){
						JSONObject mchtProductBrandJsonObject = (JSONObject)mchtProductBrandJsonArray.get(i);
						String auditStatus = mchtProductBrandJsonObject.getString("auditStatus");
						if(auditStatus.equals("3")){//3 审核通过
							alertError = false;
							break;
						}else{
							continue;
						}
					}
					if(alertError){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "当前商家还没有一个品牌是法务审核通过的，商家信息资质总审核无法通过");
						return resMap;
					}
				}
				mchtInfo.setTotalAuditDate(new Date());
				//TODO 通过生成合同
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				mchtContract.setCreateDate(new Date());
				mchtContract.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtContract.setDelFlag("0");
				mchtContract.setMchtId(mchtId);
				mchtContract.setContractCode(mchtContractService.genContractCode(mchtId));	//合同编号
				mchtContract.setBeginDate(sdf.parse(agreementBeginDate));
				mchtContract.setEndDate(sdf.parse(agreementEndDate));
				mchtInfo.setAgreementBeginDate(sdf.parse(agreementBeginDate));
				mchtInfo.setAgreementEndDate(sdf.parse(agreementEndDate));
				
				mchtContract.setContractType(Const.MCHT_CONTRACT_TYPE_NEW);	//新签
				mchtContract.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);	//审核状态：未上传
				mchtContract.setRenewStatus(Const.MCHT_CONTRACT_RENEW_STATUS_WAIT);
			}else if(totalAuditStatus.equals("5") || totalAuditStatus.equals("6")){//不签约， 黑名单
				mchtInfo.setStatus("3");
				mchtInfo.setStatusDate(new Date());
			}

			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			mchtInfo.setUpdateBy(staffID);
			List<MchtInfoChangeLog> mchtInfoChangeLogs = new ArrayList<MchtInfoChangeLog>();
			// 公司信息审核
			MchtInfoChangeLog micl = new MchtInfoChangeLog();
			micl.setMchtId(mchtId);
			micl.setCreateDate(new Date());
			micl.setCreateBy(staffID);
			micl.setDelFlag("0");
			micl.setLogType("公司信息");
			micl.setBeforeChange("待审");
			if (isCompanyInfPerfect.equals("1")) {
				micl.setAfterChange("通过");
			} else if (isCompanyInfPerfect.equals("4")) {
				micl.setAfterChange("驳回");
			}
			micl.setLogName(mchtInfo.getCompanyName());
			micl.setRemarks(companyInfAuditRemarks);
			mchtInfoChangeLogs.add(micl);
			// 法务总审核
			MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
			mchtInfoChangeLog.setMchtId(mchtId);
			mchtInfoChangeLog.setCreateDate(new Date());
			mchtInfoChangeLog.setCreateBy(staffID);
			mchtInfoChangeLog.setDelFlag("0");
			mchtInfoChangeLog.setLogType("资质总审核");
			mchtInfoChangeLog.setBeforeChange("待审");
			if (totalAuditStatus.equals("2")) {
				mchtInfoChangeLog.setAfterChange("通过");
			} else if (totalAuditStatus.equals("3")) {
				mchtInfo.setStatus("0");//入驻中
				mchtInfoChangeLog.setAfterChange("驳回");
			} else if (totalAuditStatus.equals("5")) {
				mchtInfoChangeLog.setAfterChange("不签约");
				mchtInfo.setStatus("5");//不入驻
			} else if (totalAuditStatus.equals("6")) {
				mchtInfoChangeLog.setAfterChange("黑名单");
				mchtInfo.setStatus("5");//不入驻
			}
			mchtInfoChangeLog.setLogName(mchtInfo.getCompanyName());
			mchtInfoChangeLog.setRemarks(totalAuditRemarks);
			mchtInfoChangeLogs.add(mchtInfoChangeLog);
			mchtInfo.setLicenseIsMust(licenseIsMust);
			mchtInfo.setLicenseStatus(licenseStatus);
			mchtInfo.setBusinessLicensePic(businessLicensePic);
			mchtInfo.setLicenseRejectReason(licenseRejectReason);
			
			//对接人审核
			List<Integer> contactIdList = new ArrayList<Integer>();
			JSONArray mchtContactArray = JSONArray.fromObject(mchtContacts);
			for(int i=0;i<mchtContactArray.size();i++){
				JSONObject mchtContactJsonObject = (JSONObject)mchtContactArray.get(i);
				contactIdList.add(Integer.valueOf(mchtContactJsonObject.getString("id")));
			}
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			if(!contactIdList.isEmpty()){
			MchtContactExample mchtContactExample = new MchtContactExample();
			Criteria criteria = mchtContactExample.createCriteria();
			criteria.andDelFlagEqualTo("0").andIdIn(contactIdList);
			List<MchtContact> mchtcontactList = mchtContactService.selectByExample(mchtContactExample);
			for (MchtContact mchtContact : mchtcontactList) {
				map.put(mchtContact.getId(), mchtContact);
			}
			}
			
			//店铺总负责人身份证正反面
			JSONObject idcard_img = null;
			//店铺总负责人手持身份证正反面
			if(!StringUtils.isEmpty(mchtContactPictures11)){
				JSONArray mchtIdcardImgArray = JSONArray.fromObject(mchtContactPictures11);
				idcard_img = (JSONObject)mchtIdcardImgArray.get(0);		
			}
			//收集要更新的对接人数据
			List<MchtContact> updateMchtContacts = new ArrayList<MchtContact>();
			for(int i=0;i<mchtContactArray.size();i++){
				JSONObject mchtContactJsonObject = (JSONObject)mchtContactArray.get(i);
				if(map.containsKey(Integer.valueOf(mchtContactJsonObject.getString("id")))){
					MchtContact mchtContact = (MchtContact) map.get(Integer.valueOf(mchtContactJsonObject.getString("id")));
					//如果是店铺总负责人
					if(org.apache.commons.lang.StringUtils.equals(mchtContact.getContactType(), "1")){
						mchtContact.setIdcardImg(idcard_img.getString("picPath"));
					}
					mchtContact.setAuditStatus(mchtContactJsonObject.getString("contactAuditStatus"));
					if(org.apache.commons.lang.StringUtils.equals(mchtContactJsonObject.getString("contactAuditStatus"), "2")){
						mchtContact.setRejectReasons(mchtContactJsonObject.getString("rejectReasons"));
						mchtContact.setInnerRemarks(mchtContactJsonObject.getString("innerRemarks"));
					}
					//对接人身份证正反面
					JSONArray string = mchtContactJsonObject.getJSONArray("mchtContactPicturesAB");
					List<String> list = JSONArray.toList(string);
					if(!list.isEmpty()){
						mchtContact.setIdcardImg1(list.get(0));
						mchtContact.setIdcardImg2(list.get(1));
					}
					updateMchtContacts.add(mchtContact);
				}
			}
			mchtProductBrandService.fwCheckMchtProductBrands(mchtInfo,mchtContract,mchtProductBrandJsonStr, mchtInfoChangeLogs,updateMchtContacts);
			
			// 发送短信
			if (totalAuditStatus.equals("3") || totalAuditStatus.equals("2")) {// 3.驳回2.通过
				MchtUserExample mue = new MchtUserExample();
				mue.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
				List<MchtUser> mchtUsers = mchtUserService.selectByExample(mue);
				if (mchtUsers != null && mchtUsers.size() > 0) {
					if (!StringUtils.isEmpty(mchtUsers.get(0).getMobile())) {
						JSONObject param = new JSONObject();
						JSONObject reqData = new JSONObject();
						reqData.put("mobile", mchtUsers.get(0).getMobile());
						if(totalAuditStatus.equals("3")){
							reqData.put("content", "您提交的入驻资质不符合要求，请及时登录平台查看并修改。 ");
						}else if(totalAuditStatus.equals("2")){
							reqData.put("content", "您提交的入驻资料已初审通过，请及时登录平台打印合同并签署，将合同按照要求盖章、扫描上传。");
						}
						reqData.put("smsType", "4");
						param.put("reqData", reqData);
						try {
							JSONObject result = JSONObject.fromObject(HttpUtil
									.sendPostRequest(SysConfig.msgServerUrl
											+ "/api/sms/sendImmediately",
											CommonUtil.createReqData(param)
													.toString()));
							if (!"0000".equals(result.getString("returnCode"))) {
								logger.info("短信发送用户失败！！！！！");
							}
						} catch (Exception e) {
							e.printStackTrace();
							return resMap;
						}
					}
				}
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//提交入驻-- 银行帐号审核
	@RequestMapping(value = "/mcht/mchtBankCheckIndex.shtml")
	public ModelAndView mchtBankCheckIndex(HttpServletRequest request) {
		String rePage = "/mcht/submitEnter/mchtBankCheckIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("statusDescList",DataDicUtil.getStatusList("BU_MCHT_BANK_ACCOUNT", "STATUS"));
		resMap.put("accTypeDescList",DataDicUtil.getStatusList("BU_MCHT_BANK_ACCOUNT", "ACC_TYPE"));
		
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			resMap.put("role107", true);
		}else{
			resMap.put("role107", false);
		}
		return new ModelAndView(rePage, resMap);
	}

	//提交入驻-- 银行帐号审核
	@RequestMapping(value = "/mcht/mchtBankCheckDataList.shtml")
	@ResponseBody
	public Map<String, Object> mchtBankCheckDataList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;

		MchtBankAccountCustomExample mchtBankAccountCustomExample = new MchtBankAccountCustomExample();
		MchtBankAccountCustomExample.MchtBankAccountCustomCriteria criteria = mchtBankAccountCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		/*criteria.andStatusNotEqualTo("2");*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!StringUtil.isEmpty(request.getParameter("commit_date_begin"))) {
			criteria.andCommitDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("commit_date_begin")+" 00:00:00"));
		}
		if (!StringUtil.isEmpty(request.getParameter("commit_date_end"))) {
			criteria.andCommitDateLessThanOrEqualTo(sdf.parse(request.getParameter("commit_date_end")+" 23:59:59"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_status"))) {
			criteria.andStatusEqualTo(request.getParameter("search_status"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_accType"))) {
			criteria.andAccTypeEqualTo(request.getParameter("search_accType"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_companyName"))) {
			criteria.andCompanyNameLike(request.getParameter("search_companyName"));
		}

		if ("1".equals(request.getParameter("is_my_audit"))) {
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID())).andContactTypeEqualTo("5").andStatusEqualTo("0");
			List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExample);
			if(platformContacts != null && platformContacts.size() > 0) {
				criteria.andPlatformContactIdEqualTo(platformContacts.get(0).getId());
			}else{
				criteria.andPlatformContactIdEqualTo(99999999);
			}
		}
		
		if (!StringUtil.isEmpty(request.getParameter("settledType"))) {
			criteria.andSettledTypeEqualTo(request.getParameter("settledType"));
		}

		if (!StringUtil.isEmpty(request.getParameter("isManageSelf"))) {
			criteria.andIsManageSelfEqualTo(request.getParameter("isManageSelf"));
		}
		
		criteria.andWhereClause(" EXISTS(select id from bu_mcht_info mi where mi.id=t.mcht_id and mi.del_flag='0' and mi.status='0')");
		totalCount = mchtBankAccountService.countMchtBankAccountCustomByExample(mchtBankAccountCustomExample);
		mchtBankAccountCustomExample.setLimitSize(page.getLimitSize());
		mchtBankAccountCustomExample.setLimitStart(page.getLimitStart());
		List<MchtBankAccountCustom> mchtBankAccounts = mchtBankAccountService.selectMchtBankAccountCustomByExample(mchtBankAccountCustomExample);
		resMap.put("Rows", mchtBankAccounts);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 公司信息更新记录列表
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtInfoChgList.shtml")
	public ModelAndView mchtInfoChgList(HttpServletRequest request) {
		String rtPage = "/mcht/submitEnter/mchtInfoChgList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mchtId",request.getParameter("mchtId"));
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 公司信息更新记录数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtInfoChgData.shtml")
	@ResponseBody
	public Map<String, Object> mchtInfoChgData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtInfoChgExample example = new MchtInfoChgExample();
			MchtInfoChgExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtIdEqualTo(Integer.parseInt(request.getParameter("mchtId")));
			totalCount = mchtInfoChgService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<MchtInfoChg> mchtInfoChgList = mchtInfoChgService.selectByExample(example);
			resMap.put("Rows", mchtInfoChgList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 公司信息更新记录详情
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/viewMchtInfoChg.shtml")
	public ModelAndView viewMchtInfoChg(HttpServletRequest request) {
		String rtPage = "/mcht/submitEnter/viewMchtInfoChg";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfoChgCustom  mchtInfoChg = mchtInfoChgService.selectMchtInfoChgCustomByPrimaryKey(id);
		resMap.put("mchtInfoChg", mchtInfoChg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 品牌信息更新记录列表
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandChgList.shtml")
	public ModelAndView mchtBrandChgList(HttpServletRequest request) {
		String rtPage = "/mcht/submitEnter/mchtBrandChgList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mchtProductBrandId",request.getParameter("mchtProductBrandId"));
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 品牌信息更新记录数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandChgData.shtml")
	@ResponseBody
	public Map<String, Object> mchtBrandChgData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtBrandChgExample example = new MchtBrandChgExample();
			MchtBrandChgExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtProductBrandIdEqualTo(Integer.parseInt(request.getParameter("mchtProductBrandId")));
			totalCount = mchtBrandChgService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<MchtBrandChg> mchtBrandChgList = mchtBrandChgService.selectByExample(example);
			resMap.put("Rows", mchtBrandChgList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/viewMchtBrandChg.shtml")
	public ModelAndView viewMchtBrandChg(HttpServletRequest request) {
		String rtPage = "/mcht/submitEnter/viewMchtBrandChg";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtBrandChg mchtBrandChg = mchtBrandChgService.selectByPrimaryKey(id);
		resMap.put("mchtBrandChg", mchtBrandChg);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtBrandChg.getMchtId());
		resMap.put("mchtInfo", mchtInfo);
		MchtProductBrand mpb = mchtProductBrandService.selectByPrimaryKey(mchtBrandChg.getMchtProductBrandId());
		resMap.put("mchtProductBrand", mpb);
		MchtBrandAptitudeChgExample example = new MchtBrandAptitudeChgExample();
		MchtBrandAptitudeChgExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0").andMchtBrandChgIdEqualTo(id);
		List<MchtBrandAptitudeChgCustom> mchtBrandAptitudeChgCustoms = mchtBrandAptitudeChgService.selectCustomByExample(example);
		for(MchtBrandAptitudeChgCustom mchtBrandAptitudeChgCustom:mchtBrandAptitudeChgCustoms){
			MchtBrandAptitudePicChgExample e = new MchtBrandAptitudePicChgExample();
			MchtBrandAptitudePicChgExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0").andMchtBrandAptitudeChgIdEqualTo(mchtBrandAptitudeChgCustom.getId());
			List<MchtBrandAptitudePicChg> mchtBrandAptitudePicChgs = mchtBrandAptitudePicChgMapper.selectByExample(e);
			mchtBrandAptitudeChgCustom.setMchtBrandAptitudePicChgs(mchtBrandAptitudePicChgs);
		}
		resMap.put("mchtBrandAptitudeChgCustoms", mchtBrandAptitudeChgCustoms);
		
		MchtPlatformAuthPicChgExample mpapce = new MchtPlatformAuthPicChgExample();
		MchtPlatformAuthPicChgExample.Criteria mbapcec = mpapce.createCriteria();
		mbapcec.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtPlatformAuthPicChg> mchtPlatformAuthPicChgs = mchtPlatformAuthPicChgMapper.selectByExample(mpapce);
		resMap.put("mchtPlatformAuthPicChgs", mchtPlatformAuthPicChgs);
		
		MchtBrandInvoicePicChgExample mbipce = new MchtBrandInvoicePicChgExample();
		MchtBrandInvoicePicChgExample.Criteria mbipcec = mbipce.createCriteria();
		mbipcec.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandInvoicePicChg> mchtBrandInvoicePicChgs = mchtBrandInvoicePicChgMapper.selectByExample(mbipce);
		resMap.put("mchtBrandInvoicePicChgs", mchtBrandInvoicePicChgs);
		
		MchtBrandInspectionPicChgExample mbipce2 = new MchtBrandInspectionPicChgExample();
		MchtBrandInspectionPicChgExample.Criteria mbipcec2 = mbipce2.createCriteria();
		mbipcec2.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandInspectionPicChg> mchtBrandInspectionPicChgs = mchtBrandInspectionPicChgMapper.selectByExample(mbipce2);
		resMap.put("mchtBrandInspectionPicChgs", mchtBrandInspectionPicChgs);
		
		MchtBrandOtherPicChgExample mbopce = new MchtBrandOtherPicChgExample();
		MchtBrandOtherPicChgExample.Criteria mbopcec = mbopce.createCriteria();
		mbopcec.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandOtherPicChg> mchtBrandOtherPicChgs = mchtBrandOtherPicChgMapper.selectByExample(mbopce);
		resMap.put("mchtBrandOtherPicChgs", mchtBrandOtherPicChgs);
		
		MchtBrandChgProductTypeExample mbcpte = new MchtBrandChgProductTypeExample();
		MchtBrandChgProductTypeExample.Criteria mbcptec = mbcpte.createCriteria();
		mbcptec.andDelFlagEqualTo("0").andMchtBrandChgIdEqualTo(mchtBrandChg.getId()).andTLevelEqualTo(2);
		List<MchtBrandChgProductTypeCustom> mchtBrandChgProductTypeCustoms = mchtBrandChgProductTypeService.selectCustomByExample(mbcpte);
		resMap.put("mchtBrandChgProductTypeCustoms", mchtBrandChgProductTypeCustoms);
		
		resMap.put("priceModelStatusDesc", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "PRICE_MODEL"));
		return new ModelAndView(rtPage, resMap);
	}
	
	//校验运营本人
	@RequestMapping(value = "/mcht/checkZsMchtPlatformContact.shtml")
	@ResponseBody
	public Map<String, Object> checkZsMchtPlatformContact(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String mchtId = request.getParameter("mchtId");
			PlatformContactExample pce = new PlatformContactExample();
			pce.createCriteria().andDelFlagEqualTo("0").andContactTypeEqualTo("2").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
			if(platformContacts!=null && platformContacts.size()>0){
				MchtPlatformContactExample mpce = new MchtPlatformContactExample();
				mpce.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(Integer.parseInt(mchtId)).andPlatformContactIdEqualTo(platformContacts.get(0).getId()).andStatusEqualTo("1");
				List<MchtPlatformContact> mchtPlatformContacts = mchtPlatformContactService.selectByExample(mpce);
				if(mchtPlatformContacts!=null && mchtPlatformContacts.size()>0){
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "成功");
				}else{
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "你不是该商家的运营对接人，没有操作权限");
				}
			}else{
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "你不是该商家的运营对接人，没有操作权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		}
		return resMap;
	}
	
	//店铺待开通-- 开通页面
	@RequestMapping(value = "/mcht/toOperate.shtml")
	public ModelAndView toOperate(HttpServletRequest request) {
		String rePage = "/mcht/toOperate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mchtId",request.getParameter("mchtId"));
		return new ModelAndView(rePage, resMap);
	}
	
	// 开通
	@RequestMapping(value = "/mcht/operate.shtml")
	@ResponseBody
	public Map<String, Object> operate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtId = request.getParameter("mchtId");
			String operateStatus = request.getParameter("operateStatus");
			String activityAuthStatus = request.getParameter("activityAuthStatus");
			String shopStatus = request.getParameter("shopStatus");
			String operateRemarks = request.getParameter("operateRemarks");
			Date now = new Date();
			
			//插入一条变更记录
			if (operateStatus.equals("1")) {
				MchtInfoCustom mchtInfoCustom=mchtInfoService.selectMchtInfoCustomById(Integer.valueOf(mchtId));
				MchtInfoChangeLog mchtInfoChangeLog=new MchtInfoChangeLog();
				mchtInfoChangeLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtInfoChangeLog.setCreateDate(new Date());
				mchtInfoChangeLog.setDelFlag("0");
				mchtInfoChangeLog.setLogName(mchtInfoCustom.getCompanyName());
				mchtInfoChangeLog.setCreatorType("1");
				mchtInfoChangeLog.setLogType("店铺开通");
				mchtInfoChangeLog.setBeforeChange(mchtInfoCustom.getStatusDesc());
				mchtInfoChangeLog.setAfterChange("已开通");
				mchtInfoChangeLog.setRemarks(mchtInfoCustom.getOperateRemarks());
				mchtInfoChangeLog.setMchtId(mchtInfoCustom.getId());
				mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);			
			}else {
				MchtInfoCustom mchtInfoCustom=mchtInfoService.selectMchtInfoCustomById(Integer.valueOf(mchtId));
				MchtInfoChangeLog mchtInfoChangeLog=new MchtInfoChangeLog();
				mchtInfoChangeLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtInfoChangeLog.setCreateDate(new Date());
				mchtInfoChangeLog.setDelFlag("0");
				mchtInfoChangeLog.setLogName(mchtInfoCustom.getCompanyName());
				mchtInfoChangeLog.setCreatorType("1");
				mchtInfoChangeLog.setLogType("店铺开通");
				mchtInfoChangeLog.setBeforeChange(mchtInfoCustom.getStatusDesc());
				mchtInfoChangeLog.setAfterChange("驳回");
				mchtInfoChangeLog.setRemarks(mchtInfoCustom.getOperateRemarks());
				mchtInfoChangeLog.setMchtId(mchtInfoCustom.getId());
				mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);
			}
			
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(mchtId));
			MchtInfo mi = new MchtInfo();
			mi.setOperateStatus(operateStatus);
			mi.setOperateStatusDate(now);
			mi.setOperateRemarks(operateRemarks);
			mi.setShopStatus(shopStatus);
			mi.setShopStatusDate(now);
			mi.setActivityAuthStatus(activityAuthStatus);
			mi.setActivityAuthStatusDate(now);
			if(operateStatus.equals("1")){//开通
				mi.setStatus("1");
				mi.setStatusDate(new Date());
				mi.setCooperateBeginDate(new Date());
			}
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(now);
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		}
		return resMap;
	}
	
	/**
	 * 银行账号更新记录列表
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBankAccountHisList.shtml")
	public ModelAndView mchtBankAccountHisList(HttpServletRequest request) {
		String rtPage = "/mcht/submitEnter/mchtBankAccountHisList";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("statusDescList",DataDicUtil.getStatusList("BU_MCHT_BANK_ACCOUNT", "STATUS"));
		resMap.put("accTypeDescList",DataDicUtil.getStatusList("BU_MCHT_BANK_ACCOUNT", "ACC_TYPE"));
		resMap.put("mchtId",request.getParameter("mchtId"));
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 银行账号更新记录列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBankAccountHisData.shtml")
	@ResponseBody
	public Map<String, Object> mchtBankAccountHisData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtBankAccountHisExample example = new MchtBankAccountHisExample();
			MchtBankAccountHisExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtIdEqualTo(Integer.parseInt(request.getParameter("mchtId")));
			totalCount = mchtBankAccountHisService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<MchtBankAccountHis> mchtBankAccountHisList = mchtBankAccountHisService.selectByExample(example);
			resMap.put("Rows", mchtBankAccountHisList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/viewMchtBankAccountHis.shtml")
	public String viewMchtBankAccountHis(Model model, HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		MchtBankAccountHis mchtBankAccountHis = mchtBankAccountHisService.selectByPrimaryKey(id);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtBankAccountHis.getMchtId());
		model.addAttribute("companyName", mchtInfo.getCompanyName());
		Bank bank = bankService.selectByPrimaryKey(Integer.parseInt(mchtBankAccountHis.getBankCode()));
		model.addAttribute("bankName", bank.getName());
		model.addAttribute("mchtBankAccountHis", mchtBankAccountHis);
		return "/mcht/submitEnter/viewMchtBankAccountHis";
	}
	
	//修改店铺名页面
	@RequestMapping(value = "/mcht/toEditShopName.shtml")
	public String toEditShopName(Model model, HttpServletRequest request) {
		model.addAttribute("mchtInfo", mchtInfoService.selectMchtInfoCustomById(Integer.parseInt(request.getParameter("mchtId"))));
		model.addAttribute("shopTypeStatusList",DataDicUtil.getStatusList("BU_MCHT_INFO", "SHOP_TYPE"));
		ProductTypeExample e = new ProductTypeExample();
		ProductTypeExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStatusEqualTo("1");
		c.andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(e);
		model.addAttribute("productTypes", productTypes);
		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		MchtProductTypeExample.Criteria mchtProductTypeCriteria = mchtProductTypeExample.createCriteria();
		mchtProductTypeCriteria.andMchtIdEqualTo(Integer.parseInt(request.getParameter("mchtId"))).andIsMainEqualTo("1").andDelFlagEqualTo("0");
		List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mchtProductTypeExample);
		if(mchtProductTypeList!=null && mchtProductTypeList.size()>0){
			model.addAttribute("mchtProductType", mchtProductTypeList.get(0));
		}
		return "/mcht/toEditShopName";
	}
	
	//修改店铺名
	@RequestMapping(value = "/mcht/editShopName.shtml")
	@ResponseBody
	public Map<String, Object> editShopName(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));
		MchtInfo mchtInfo4Update=new MchtInfo();
		mchtInfo4Update.setId(mchtId);
		mchtInfo4Update.setShopType(request.getParameter("shopType").trim());
		mchtInfo4Update.setBusinessFirms(request.getParameter("businessFirms").trim());
		mchtInfo4Update.setBrand(request.getParameter("brand").trim());
		mchtInfo4Update.setProductType(request.getParameter("productType").trim());
		mchtInfo4Update.setOther(request.getParameter("other").trim());
		String shopType=request.getParameter("shopType");
		if(shopType.equals("1")){
			mchtInfo4Update.setShopName(request.getParameter("brand").trim()+"官方旗舰店");
		}
		if(shopType.equals("2")){
			mchtInfo4Update.setShopName(request.getParameter("brand").trim()+request.getParameter("productType")+"旗舰店");
		}
		if(shopType.equals("3")){
			mchtInfo4Update.setShopName(request.getParameter("brand").trim()+request.getParameter("businessFirms").trim()+"专卖店");
		}
		if(shopType.equals("4")){
			mchtInfo4Update.setShopName(request.getParameter("businessFirms").trim()+request.getParameter("productType").trim()+"专营店");
		}
		if(shopType.equals("5")){
			mchtInfo4Update.setShopName(request.getParameter("businessFirms").trim()+"官方旗舰店");
		}
		if(shopType.equals("6") || shopType.equals("7")){
			mchtInfo4Update.setShopName(request.getParameter("shopName").trim());
		}
		
		MchtInfoExample mchtInfoExample=new MchtInfoExample();
		mchtInfoExample.createCriteria().andIdNotEqualTo(mchtInfo4Update.getId()).andShopNameEqualTo(mchtInfo4Update.getShopName()).andDelFlagEqualTo("0");
		int count=mchtInfoService.countByExample(mchtInfoExample);
		if(count>0){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "店铺名已经存在");
			return resMap;
		}
		
		mchtInfo4Update.setShopNameAuditStatus("1");//1.已填
		mchtInfo4Update.setUpdateDate(new Date());
		mchtInfo4Update.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		mchtInfoService.updateByPrimaryKeySelective(mchtInfo4Update);
		
		String productTypeId = request.getParameter("productTypeId");
		if(!StringUtil.isEmpty(productTypeId)){
			MchtProductTypeExample mpyExample = new MchtProductTypeExample();
			mpyExample.createCriteria().andDelFlagEqualTo("0")
			.andMchtIdEqualTo(mchtId);
			int mchtProductTypeCount = mchtProductTypeService.countByExample(mpyExample);
			if(mchtProductTypeCount > 15){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "最多只能添加15个类目");
				return resMap;
			}else {
				MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
				MchtProductTypeExample.Criteria mchtProductTypeCriteria = mchtProductTypeExample.createCriteria();
				mchtProductTypeCriteria.andMchtIdEqualTo(mchtId)
				.andProductTypeIdEqualTo(Integer.valueOf(productTypeId)).andIsMainEqualTo("1").andDelFlagEqualTo("0");
				List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mchtProductTypeExample);
				MchtProductType mchtProductType = new MchtProductType();
				if(mchtProductTypeList != null && mchtProductTypeList.size() > 0) {
					mchtProductType.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					mchtProductType.setUpdateDate(new Date());
					mchtProductTypeService.updateByExampleSelective(mchtProductType, mchtProductTypeExample);
				}else {
					mchtProductType.setDelFlag("0");
					mchtProductType.setCreateDate(new Date());
					mchtProductType.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					mchtProductType.setMchtId(mchtId);
					mchtProductType.setProductTypeId(Integer.valueOf(productTypeId));
					mchtProductType.setStatus("0");
					if(mchtProductTypeCount == 0){
						mchtProductType.setIsMain("1");//是主营
					}
					mchtProductTypeService.insertSelective(mchtProductType);
				}
			}
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title singleProductActivityCloseManager   
	 * @Description TODO(关闭单品活动)   
	 * @author pengl
	 * @date 2018年9月7日 下午4:17:48
	 */
	@RequestMapping("/mcht/singleProductActivityCloseManager.shtml")
	public ModelAndView singleProductActivityCloseManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/singleProductActivityCloseList");
		m.addObject("mchtId", request.getParameter("mchtId"));
		m.addObject("productBrandId", request.getParameter("productBrandId"));
		return m;
	}
	@ResponseBody
	@RequestMapping("/mcht/singleProductActivityCloseList.shtml")
	public Map<String, Object> singleProductActivityCloseList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SingleProductActivityCustom> singleProductActivityCustomList = null;
		Integer totalCount = 0;
		try {
			String mchtId = request.getParameter("mchtId");
			String productBrandId = request.getParameter("productBrandId");
			SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
			SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
			singleProductActivityCustomCriteria.andDelFlagEqualTo("0").andAuditStatusIn(Arrays.asList("0","1","3"));
			singleProductActivityCustomCriteria.andEndTimeGreaterThanOrEqualToOrIsNull();
			if(!StringUtil.isEmpty(mchtId) ) {
				singleProductActivityCustomCriteria.andMchtIdEqualTo(Integer.parseInt(mchtId));
			}
			if(!StringUtil.isEmpty(productBrandId) && !productBrandId.equals("0")) {
				singleProductActivityCustomCriteria.andProductBrandIdEqualTo(productBrandId);
			}
			singleProductActivityCustomExample.setLimitStart(page.getLimitStart());
			singleProductActivityCustomExample.setLimitSize(page.getLimitSize());
			totalCount = singleProductActivityService.countByCustomExample(singleProductActivityCustomExample);
			singleProductActivityCustomList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
			resMap.put("Rows", singleProductActivityCustomList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	@ResponseBody
	@RequestMapping("/mcht/singleProductActivityClose.shtml")
	public Map<String, Object> singleProductActivityClose(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtId = request.getParameter("mchtId");
			String productBrandId = request.getParameter("productBrandId");
			if(!StringUtil.isEmpty(mchtId) && !StringUtil.isEmpty(productBrandId) ) {
				String staffId = this.getSessionStaffBean(request).getStaffID();
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("mchtId", mchtId);
				paramMap.put("staffId", staffId);
				paramMap.put("productBrandId", productBrandId);
				singleProductActivityService.singleProductActivityClose(paramMap);
			}else {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "参数错误！");
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title activityCloseManager   
	 * @Description TODO(关闭品牌活动)   
	 * @author pengl
	 * @date 2018年9月7日 下午6:20:24
	 */
	@RequestMapping("/mcht/activityCloseManager.shtml")
	public ModelAndView activityCloseManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/activityCloseList");
		m.addObject("mchtId", request.getParameter("mchtId"));
		m.addObject("productBrandId", request.getParameter("productBrandId"));
		return m;
	}
	@ResponseBody
	@RequestMapping("/mcht/activityCloseList.shtml")
	public Map<String, Object> activityCloseList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ActivityCustom> activityCustomList = null;
		Integer totalCount = 0;
		try {
			String mchtId = request.getParameter("mchtId");
			String productBrandId = request.getParameter("productBrandId");
			ActivityCustomExample activityCustomExample = new ActivityCustomExample();
			ActivityCustomExample.ActivityCustomCriteria activityCustomCriteria = activityCustomExample.createCriteria();
			activityCustomCriteria.andDelFlagEqualTo("0")
				.andStatusNotEqualTo("4");
			activityCustomCriteria.andActivityEndTimeGreaterThanOrEqualToNow();
			activityCustomCriteria.andActivityAreaBysourceEqualTo();
			if(!StringUtil.isEmpty(mchtId) ) {
				activityCustomCriteria.andMchtIdEqualTo(Integer.parseInt(mchtId));
			}
			if(!StringUtil.isEmpty(productBrandId) && !productBrandId.equals("0")) {
				activityCustomCriteria.andProductBrandIdEqualTo(Integer.parseInt(productBrandId));
			}
			activityCustomExample.setLimitStart(page.getLimitStart());
			activityCustomExample.setLimitSize(page.getLimitSize());
			totalCount = activityService.countActivityCustomByExample(activityCustomExample);
			activityCustomList = activityService.selectActivityCustomByExample(activityCustomExample);
			resMap.put("Rows", activityCustomList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	@ResponseBody
	@RequestMapping("/mcht/activityClose.shtml")
	public Map<String, Object> activityClose(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtId = request.getParameter("mchtId");
			String productBrandId = request.getParameter("productBrandId");
			if(!StringUtil.isEmpty(mchtId) && !StringUtil.isEmpty(productBrandId) ) {
				String staffId = this.getSessionStaffBean(request).getStaffID();
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("mchtId", mchtId);
				paramMap.put("productBrandId", productBrandId);
				paramMap.put("staffId", staffId);
				//第二版需求（STORY#1058）
				activityService.activityClose(paramMap);
				
				
				/*//第一版需求
				ActivityCustomExample activityCustomExample = new ActivityCustomExample();
				ActivityCustomExample.ActivityCustomCriteria activityCustomCriteria = activityCustomExample.createCriteria();
				activityCustomCriteria.andDelFlagEqualTo("0")
					.andStatusNotEqualTo("4")
					.andMchtIdEqualTo(Integer.parseInt(mchtId));
				activityCustomCriteria.andActivityEndTimeGreaterThanOrEqualToNow();
				if(!productBrandId.equals("0")){
					activityCustomCriteria.andProductBrandIdEqualTo(Integer.parseInt(productBrandId));
				}
				activityCustomCriteria.andActivityAreaBysourceEqualTo();
				Activity activity = new Activity();
				activity.setStatus("4");
				activity.setUpdateBy(Integer.parseInt(staffId));
				activity.setUpdateDate(date);
				activityService.updateByCustomExampleSelective(activity, activityCustomExample);*/
				
			}else {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "参数错误！");
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
		
	/**
	 * 
	 * @Title productCloseManager   
	 * @Description TODO(商品全部下架)   
	 * @author pengl
	 * @date 2018年9月8日 上午10:04:15
	 */
	@RequestMapping("/mcht/productCloseManager.shtml")
	public ModelAndView productCloseManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/productCloseList");
		m.addObject("mchtId", request.getParameter("mchtId"));
		m.addObject("productBrandId", request.getParameter("productBrandId"));
		return m;
	}
	@ResponseBody
	@RequestMapping("/mcht/productCloseList.shtml")
	public Map<String, Object> productCloseList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Product> productList = null;
		Integer totalCount = 0;
		try {
			String mchtId = request.getParameter("mchtId");
			String productBrandId = request.getParameter("productBrandId");
			ProductExample productExample = new ProductExample();
			ProductExample.Criteria productCriteria = productExample.createCriteria();
			productCriteria.andDelFlagEqualTo("0")
				.andStatusEqualTo("1");
			if(!StringUtil.isEmpty(mchtId) ) {
				productCriteria.andMchtIdEqualTo(Integer.parseInt(mchtId));
			}
			if(!StringUtil.isEmpty(productBrandId) && !productBrandId.equals("0")) {
				productCriteria.andBrandIdEqualTo(Integer.parseInt(productBrandId));
			}
			productExample.setLimitStart(page.getLimitStart());
			productExample.setLimitSize(page.getLimitSize());
			totalCount = productService.countByExample(productExample);
			productList = productService.selectByExample(productExample);
			resMap.put("Rows", productList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	@ResponseBody
	@RequestMapping("/mcht/productClose.shtml")
	public Map<String, Object> productClose(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtId = request.getParameter("mchtId");
			String productBrandId = request.getParameter("productBrandId");
			if(!StringUtil.isEmpty(mchtId) && !StringUtil.isEmpty(productBrandId) ) {
				Date date = new Date();
				String staffId = this.getSessionStaffBean(request).getStaffID();
				ProductExample productExample = new ProductExample();
				ProductExample.Criteria productCriteria = productExample.createCriteria();
				productCriteria.andDelFlagEqualTo("0")
					.andStatusEqualTo("1")
					.andMchtIdEqualTo(Integer.parseInt(mchtId));
				if(!"0".equals(productBrandId)) {
					productCriteria.andBrandIdEqualTo(Integer.parseInt(productBrandId));
				}

				List<Product> productList = productService.selectByExample(productExample);

				Product product = new Product();
				product.setStatus("0");
				product.setUpdateBy(Integer.parseInt(staffId));
				product.setUpdateDate(date);
				productService.updateByExampleSelective(product, productExample);

				if(!productList.isEmpty()){
					for (Product productItem : productList){
						//商品上下架日志
						ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
						productUpperLowerLog.setProductId(productItem.getId());
						productUpperLowerLog.setStatus(product.getStatus());
						productUpperLowerLog.setType(Const.PLATFORM);
						productUpperLowerLog.setOffReason("平台强制下架");
						productUpperLowerLog.setCreateBy(product.getUpdateBy());
						productUpperLowerLog.setCreateDate(new Date());
						productUpperLowerLogService.insertSelective(productUpperLowerLog);
					}
				}

			}else {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "参数错误！");
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title singleProductActivityCloseManager   
	 * @Description TODO(添加/修改商家标识)   
	 * @author pengl
	 * @date 2018年9月26日 下午5:51:09
	 */
	@RequestMapping("/mcht/updateGradeManager.shtml")
	public ModelAndView updateGradeManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/updateGrade");
		String status = request.getParameter("status");
		if(!StringUtil.isEmpty(request.getParameter("mchtId"))) {
			m.addObject("mchtInfo", mchtInfoService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mchtId"))));
		}
		m.addObject("status", status);
		m.addObject("gradeList", DataDicUtil.getTableStatus("BU_MCHT_INFO", "GRADE"));
		return m;
	}
	
	/**
	 * 
	 * @Title updateGrade   
	 * @Description TODO(添加/修改商家标识)   
	 * @author pengl
	 * @date 2018年9月27日 上午9:34:40
	 */
	@RequestMapping("/mcht/updateGrade.shtml")
	public ModelAndView updateGrade(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			Date date = new Date();
			String mchtId = request.getParameter("mchtId");
			String grade = request.getParameter("grade");
			if(!StringUtil.isEmpty(mchtId)) {
				MchtInfo mchtInfo = new MchtInfo();
				mchtInfo.setId(Integer.parseInt(mchtId));;
				mchtInfo.setGrade(grade);
				mchtInfo.setUpdateBy(staffId);
				mchtInfo.setUpdateDate(date);
				mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
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
	
	/**
	 * 
	 * @Title showMchtShopManager   
	 * @Description TODO(查看商家)   
	 * @author pengl
	 * @date 2018年10月9日 上午9:42:48
	 */
	@RequestMapping("/mcht/showMchtShopManager.shtml")
	public ModelAndView showMchtShopManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/showMchtShopList");
		m.addObject("mchtId", request.getParameter("mchtId"));
		m.addObject("mchtType", request.getParameter("mchtType"));
		return m;
	}
	@ResponseBody
	@RequestMapping("/mcht/showMchtShopList.shtml")
	public Map<String, Object> showMchtShopList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ProductCustom> productCustomList = null;
		Integer totalCount = 0;
		try {
			String mchtId = request.getParameter("mchtId");
			ProductCustomExample productCustomExample = new ProductCustomExample();
			ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();
			productCustomCriteria.andDelFlagEqualTo("0").andMchtIdEqualTo(Integer.parseInt(mchtId)).andStatusEqualTo("1");
			productCustomExample.setOrderByClause(" t.id desc");
			totalCount = productService.countProductCustomByExample(productCustomExample);
			productCustomList = productService.selectProductCustomByExample(productCustomExample);
			resMap.put("Rows", productCustomList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//开放商家申请关店
	@RequestMapping(value = "/mcht/close/allowMchtApplyClose.shtml")
	@ResponseBody
	public Map<String, Object> allowMchtApplyClose(Model model,
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if (StringUtil.isEmpty(request.getParameter("mchtId"))) {
				throw new ArgException("商家不存在");
			}
			Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));

			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if (mchtInfo == null) {
				throw new ArgException("商家不存在");
			}

			mchtInfo.setAllowMchtApplyClose("1");
			mchtInfo.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * 申请关店页面   
	 */
	@RequestMapping("/mcht/close/toApplyClose.shtml")
	public ModelAndView toApplyClose(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/close/toApplyClose");
		m.addObject("mchtId", request.getParameter("mchtId"));
		return m;
	}
	
	//保存商家申请关店
	@RequestMapping(value = "/mcht/close/saveApply.shtml")
	@ResponseBody
	public Map<String, Object> saveApply(Model model,HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if (StringUtil.isEmpty(request.getParameter("mchtId"))) {
				throw new ArgException("商家不存在");
			}
			Integer mchtId = Integer.valueOf(request.getParameter("mchtId"));

			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if (mchtInfo == null) {
				throw new ArgException("商家不存在");
			}
			MchtCloseApplication mca = new MchtCloseApplication();
			mca.setMchtId(mchtId);
			mca.setApplyName(this.getSessionStaffBean(request).getStaffName());
			mca.setApplySource("2");
			mca.setApplyReason(request.getParameter("applyReason"));
			mca.setProgressStatus("0");
			mca.setZsConfirmStatus("0");
			mca.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mca.setCreateDate(new Date());
			mca.setDelFlag("0");
			mchtCloseApplicationService.insertSelective(mca);
			mchtInfo.setAllowMchtApplyClose("1");
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * 申请关店详情页面   
	 */
	@RequestMapping("/mcht/close/viewApply.shtml")
	public ModelAndView viewApply(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("mcht/close/viewApply");
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtCloseApplicationCustom mchtCloseApplicationCustom = mchtCloseApplicationService.selectCustomByPrimaryKey(id);
		m.addObject("mchtCloseApplicationCustom", mchtCloseApplicationCustom);
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
		m.addObject("mchtProductBrands", mchtProductBrands);
		
		MchtSettleOrderExample msoe = new MchtSettleOrderExample();
		List<String> payStatusList = new ArrayList<String>();
		payStatusList.add("1");
		payStatusList.add("2");
		msoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId()).andPayStatusIn(payStatusList);
		List<MchtSettleOrder> mchtSettleOrders = mchtSettleOrderService.selectByExample(msoe);
		BigDecimal total = new BigDecimal(0);
		for(MchtSettleOrder mchtSettleOrder:mchtSettleOrders){
			total.add(mchtSettleOrder.getNeedPayAmount());
		}
		m.addObject("total", total);
		
		SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
		SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
		singleProductActivityCustomCriteria.andDelFlagEqualTo("0").andAuditStatusIn(Arrays.asList("0","1","3")).andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
		singleProductActivityCustomCriteria.andEndTimeGreaterThanOrEqualToOrIsNull();
		int singleProductActivityCount = singleProductActivityService.countByCustomExample(singleProductActivityCustomExample);
		m.addObject("singleProductActivityCount", singleProductActivityCount);
		
		int activityCount = activityAreaService.countActivityByMchtId(mchtCloseApplicationCustom.getMchtId());
		m.addObject("activityCount", activityCount);
		
		ProductExample pe = new ProductExample();
		pe.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
		int productCount = productService.countByExample(pe);
		m.addObject("productCount", productCount);
		
		SubOrderExample subOrderExample = new SubOrderExample();
		List<String> subOrderStatusList = new ArrayList<String>();
		subOrderStatusList.add("1");
		subOrderStatusList.add("2");
		subOrderExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId()).andStatusIn(subOrderStatusList);
		int subOrderCount = subOrderService.countByExample(subOrderExample);
		m.addObject("subOrderCount", subOrderCount);
		
		CustomerServiceOrderCustomExample e = new CustomerServiceOrderCustomExample();
		CustomerServiceOrderCustomCriteria cc = e.createCriteria();
		cc.andDelFlagEqualTo("0");
		cc.andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
		cc.andStatusEqualTo("0");
		int customerServiceOrderCount = customerServiceOrderService.countCustomerServiceOrderCustomByExample(e);
		m.addObject("customerServiceOrderCount", customerServiceOrderCount);
		
		AppealOrderExample aoe = new AppealOrderExample();
		List<String> appealOrderStatusList = new ArrayList<String>();
		appealOrderStatusList.add("1");
		appealOrderStatusList.add("2");
		aoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId()).andStatusIn(appealOrderStatusList);
		int appealOrderCount = appealOrderService.countByExample(aoe);
		m.addObject("appealOrderCount", appealOrderCount);
		
		InterventionOrderExample ioe = new InterventionOrderExample();
		ioe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId()).andStatusNotEqualTo("8");
		int interventionOrderCount = interventionOrderService.countByExample(ioe);
		m.addObject("interventionOrderCount", interventionOrderCount);
		
		List<SysStatus> closeReasonList = DataDicUtil.getStatusList("BU_MCHT_CLOSE_APPLICATION", "CLOSE_REASON");
		m.addObject("closeReasonList", closeReasonList);
		return m;
	}
	
	/**
	 * 
	 * 添加备注页面   
	 */
	@RequestMapping("/mcht/close/toAddRemarks.shtml")
	public ModelAndView toAddRemarks(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/close/toAddRemarks");
		m.addObject("id", request.getParameter("id"));
		m.addObject("progressStatus", request.getParameter("progressStatus"));
		return m;
	}
	
	//保存备注
	@RequestMapping(value = "/mcht/close/saveMchtCloseApplyRemarks.shtml")
	@ResponseBody
	public Map<String, Object> saveMchtCloseApplyRemarks(Model model,HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String remarks = request.getParameter("remarks");
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(id);
			mchtCloseApplication.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtCloseApplication.setUpdateDate(new Date());
			String progressStatus = request.getParameter("progressStatus");
			if(progressStatus.equals("0")){
				mchtCloseApplication.setZsRemarks(remarks);
				mchtCloseApplication.setZsRemarksDate(new Date());
			}else if(progressStatus.equals("1")){
				mchtCloseApplication.setCommodityRemarks(remarks);
				mchtCloseApplication.setCommodityRemarksDate(new Date());
			}else if(progressStatus.equals("2")){
				mchtCloseApplication.setMchtArchiveRemarks(remarks);
				mchtCloseApplication.setMchtArchiveRemarksDate(new Date());
			}else if(progressStatus.equals("3")){
				mchtCloseApplication.setKfRemarks(remarks);
				mchtCloseApplication.setKfRemarksDate(new Date());
			}else if(progressStatus.equals("4")){
				mchtCloseApplication.setCwRemarks(remarks);
				mchtCloseApplication.setCwRemarksDate(new Date());
			}else if(progressStatus.equals("5")){
				mchtCloseApplication.setFwRemarks(remarks);
				mchtCloseApplication.setFwRemarksDate(new Date());
			}
			MchtCloseApplicationRemarks mchtCloseApplicationRemarks = new MchtCloseApplicationRemarks();
			mchtCloseApplicationRemarks.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtCloseApplicationRemarks.setCreateDate(new Date());
			mchtCloseApplicationRemarks.setDelFlag("0");
			mchtCloseApplicationRemarks.setMchtCloseApplicationId(id);
			mchtCloseApplicationRemarks.setRemarks(remarks);
			mchtCloseApplicationRemarks.setRemarksType(progressStatus);
			mchtCloseApplicationRemarksMapper.insertSelective(mchtCloseApplicationRemarks);
			mchtCloseApplicationService.updateByPrimaryKey(mchtCloseApplication);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
		
	//关店进度列表
	@RequestMapping(value = "/mcht/close/progressStatusList.shtml")
	public ModelAndView progressStatusList(HttpServletRequest request) {
		String rePage = "/mcht/close/progressStatusList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("progressStatusList",DataDicUtil.getStatusList("BU_MCHT_CLOSE_APPLICATION", "PROGRESS_STATUS"));
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		SysStaffRoleExample example = new SysStaffRoleExample();
		example.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SysStaffRole> sysStaffRoles = sysStaffRoleService.selectByExample(example);
		List<Integer> roleIds = new ArrayList<Integer>(); 
		for(SysStaffRole sysStaffRole:sysStaffRoles){
			roleIds.add(sysStaffRole.getRoleId());
		}
		if(roleIds!=null && roleIds.size()>0){
			if(roleIds.contains(76)){
				resMap.put("delApply", 1);
			}
		}
		return new ModelAndView(rePage, resMap);
	}

	//关店进度列表数据
	@RequestMapping(value = "/mcht/close/progressStatusData.shtml")
	@ResponseBody
	public Map<String, Object> progressStatusData(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		SysStaffProductTypeExample sspte = new SysStaffProductTypeExample();
		sspte.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SysStaffProductType> sysStaffProductTypes = sysStaffProductTypeService.selectByExample(sspte);
		List<Integer> productTypeIds = new ArrayList<Integer>();
		if(sysStaffProductTypes==null || sysStaffProductTypes.size()==0){
			return resMap;
		}else{
			for(SysStaffProductType sysStaffProductType:sysStaffProductTypes){
				productTypeIds.add(sysStaffProductType.getProductTypeId());
			}
		}
		MchtCloseApplicationCustomExample example = new MchtCloseApplicationCustomExample();
		MchtCloseApplicationCustomExample.MchtCloseApplicationCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		if(productTypeIds!=null && productTypeIds.size()>0 && StringUtil.isEmpty(request.getParameter("productTypeId"))){
			String productTypeIdsStr = "";
			for(int i=0;i<productTypeIds.size();i++){
				if(i!=productTypeIds.size()-1){
					productTypeIdsStr+=productTypeIds.get(i)+",";
				}else{
					productTypeIdsStr+=productTypeIds.get(i);
				}
			}
			criteria.andProductTypeIdsIn(productTypeIdsStr);
		}
        if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
            Integer productTypeId = Integer.parseInt(request.getParameter("productTypeId"));
            if(!productTypeIds.contains(productTypeId)){
                return resMap;
            }else{
                criteria.andProductTypeIdEqualTo(productTypeId);
            }
        }
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!StringUtil.isEmpty(request.getParameter("create_date_begin"))) {
			criteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("create_date_begin")+" 00:00:00"));
		}
		if (!StringUtil.isEmpty(request.getParameter("create_date_end"))) {
			criteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("create_date_end")+" 23:59:59"));
		}
		if (!StringUtil.isEmpty(request.getParameter("applyName"))) {
			criteria.andApplyNameEqualTo(request.getParameter("applyName"));
		}
		if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
			criteria.andMchtNameLike(request.getParameter("mchtName"));
		}
		if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
			criteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
		}
		if (!StringUtil.isEmpty(request.getParameter("progressStatus"))) {
			if(request.getParameter("progressStatus").equals("5")){
				criteria.andProgressStatusEqualTo("5");
				criteria.andCloseAplicationPicIsNotNull();
			}
			else if(request.getParameter("progressStatus").equals("11")){
				criteria.andProgressStatusEqualTo("5");
				criteria.andCloseAplicationPicIsNull();
			}else {
				criteria.andProgressStatusEqualTo(request.getParameter("progressStatus"));
			}
		}
		if (!StringUtil.isEmpty(request.getParameter("closeHangUpStatus"))) {
			criteria.andCloseHangUpStatusEqualTo(request.getParameter("closeHangUpStatus"));
		}
		totalCount = mchtCloseApplicationService.countByCustomExample(example);
		example.setLimitSize(page.getLimitSize());
		example.setLimitStart(page.getLimitStart());
		List<MchtCloseApplicationCustom> mchtCloseApplicationCustoms = mchtCloseApplicationService.selectByCustomExample(example);
		for(MchtCloseApplicationCustom mchtCloseApplicationCustom:mchtCloseApplicationCustoms){
			MchtProductBrandExample mpbe = new MchtProductBrandExample();
			mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
			List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
			String mchtProductBrandStr = "";
			for(MchtProductBrand mchtProductBrand:mchtProductBrands){
				String statusDesc = "";
				if(mchtProductBrand.getStatus().equals("0")){
					statusDesc = "申请中";
				}else if(mchtProductBrand.getStatus().equals("1")){
					statusDesc = "正常";
				}else if(mchtProductBrand.getStatus().equals("2")){
					statusDesc = "暂停";
				}else if(mchtProductBrand.getStatus().equals("3")){
					statusDesc = "关闭";
				}else if(mchtProductBrand.getStatus().equals("4")){
					statusDesc = "驳回申请";
				}
				String rate = "";
				if(mchtProductBrand.getPopCommissionRate()!=null){
					String tmpRate = mchtProductBrand.getPopCommissionRate().multiply(new BigDecimal(100)).toString();
					rate=tmpRate.subSequence(0, tmpRate.lastIndexOf("."))+"%";
				}
				mchtProductBrandStr+="<span style='color:red;'>["+statusDesc+"]</span>"+mchtProductBrand.getProductBrandName()+"<span style='color:green;'>["+rate+"]</span><br>";
			}
			mchtCloseApplicationCustom.setMchtProductBrands(mchtProductBrandStr);
		}
		resMap.put("Rows", mchtCloseApplicationCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	//取消关店
	@RequestMapping(value = "/mcht/close/delApply.shtml")
	@ResponseBody
	public Map<String, Object> delApply(Model model,HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(id);
			mchtCloseApplication.setDelFlag("1");
			mchtCloseApplication.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtCloseApplication.setUpdateDate(new Date());
			mchtCloseApplicationService.updateByPrimaryKey(mchtCloseApplication);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//确认列表
	@RequestMapping(value = "/mcht/close/confirmList.shtml")
	public ModelAndView confirmList(HttpServletRequest request) {
		String rePage = "/mcht/close/confirmList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("progressStatus", request.getParameter("progressStatus"));
		SysStaffProductTypeExample sspte = new SysStaffProductTypeExample();
		sspte.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SysStaffProductType> sysStaffProductTypes = sysStaffProductTypeService.selectByExample(sspte);
		List<Integer> productTypeIds = new ArrayList<Integer>();
		for(SysStaffProductType sysStaffProductType:sysStaffProductTypes){
			productTypeIds.add(sysStaffProductType.getProductTypeId());
		}
		if(productTypeIds!=null && productTypeIds.size()>0){
			ProductTypeExample pte = new ProductTypeExample();
			pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1").andIdIn(productTypeIds);
			List<ProductType> productTypes = productTypeService.selectByExample(pte);
			resMap.put("productTypes", productTypes);
		}
		
	    String staffID = getSessionStaffBean(request).getStaffID();
		List<ProductTypeCustom> productTypeListByStaffId = productTypeService.getProductTypeListByStaffId(Integer.parseInt(staffID));
		resMap.put("productTypeListByStaffId", productTypeListByStaffId);
		
		
		return new ModelAndView(rePage, resMap);
	}

	//确认列表数据
	@RequestMapping(value = "/mcht/close/confirmData.shtml")
	@ResponseBody
	public Map<String, Object> confirmData(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		SysStaffProductTypeExample sspte = new SysStaffProductTypeExample();
		sspte.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SysStaffProductType> sysStaffProductTypes = sysStaffProductTypeService.selectByExample(sspte);
		List<Integer> productTypeIds = new ArrayList<Integer>();
		if(sysStaffProductTypes==null || sysStaffProductTypes.size()==0){
			return resMap;
		}else{
			for(SysStaffProductType sysStaffProductType:sysStaffProductTypes){
				productTypeIds.add(sysStaffProductType.getProductTypeId());
			}
		}
		MchtCloseApplicationCustomExample example = new MchtCloseApplicationCustomExample();
		String progressStatus = request.getParameter("progressStatus");
		if(progressStatus.equals("6")){
			example.setOrderByClause("t.deposit_date asc");
		}
		MchtCloseApplicationCustomExample.MchtCloseApplicationCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		if(productTypeIds!=null && productTypeIds.size()>0){
			String productTypeIdsStr = "";
			for(int i=0;i<productTypeIds.size();i++){
				if(i!=productTypeIds.size()-1){
					productTypeIdsStr+=productTypeIds.get(i)+",";
				}else{
					productTypeIdsStr+=productTypeIds.get(i);
				}
				
			}
			criteria.andProductTypeIdsIn(productTypeIdsStr);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String confirmStatus = request.getParameter("confirmStatus");
		if (!StringUtil.isEmpty(progressStatus)) {
			if(progressStatus.equals("0")){
				if (!StringUtil.isEmpty(request.getParameter("create_date_begin"))) {
					criteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("create_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("create_date_end"))) {
					criteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("create_date_end")+" 23:59:59"));
				}
			}else if(progressStatus.equals("1")){
				if (!StringUtil.isEmpty(request.getParameter("zs_confirm_date_begin"))) {
					criteria.andZsConfirmDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("zs_confirm_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("zs_confirm_date_end"))) {
					criteria.andZsConfirmDateLessThanOrEqualTo(sdf.parse(request.getParameter("zs_confirm_date_end")+" 23:59:59"));
				}
			}else if(progressStatus.equals("2")){
				if (!StringUtil.isEmpty(request.getParameter("commodity_confirm_date_begin"))) {
					criteria.andCommodityConfirmDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("commodity_confirm_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("commodity_confirm_date_end"))) {
					criteria.andCommodityConfirmDateLessThanOrEqualTo(sdf.parse(request.getParameter("commodity_confirm_date_end")+" 23:59:59"));
				}
			}else if(progressStatus.equals("3")){
				if (!StringUtil.isEmpty(request.getParameter("mcht_archive_confirm_date_begin"))) {
					criteria.andMchtArchiveConfirmDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("mcht_archive_confirm_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("mcht_archive_confirm_date_end"))) {
					criteria.andMchtArchiveConfirmDateLessThanOrEqualTo(sdf.parse(request.getParameter("mcht_archive_confirm_date_end")+" 23:59:59"));
				}
			}else if(progressStatus.equals("4")){
				if (!StringUtil.isEmpty(request.getParameter("kf_confirm_date_begin"))) {
					criteria.andKfConfirmDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("kf_confirm_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("kf_confirm_date_end"))) {
					criteria.andKfConfirmDateLessThanOrEqualTo(sdf.parse(request.getParameter("kf_confirm_date_end")+" 23:59:59"));
				}

				if (!StringUtil.isEmpty(confirmStatus) && confirmStatus.equals("0")){
					criteria.andPaymentOfGoodsConfirmIsNull();
				}
				//货款是否已结清
				if (!StringUtil.isEmpty(request.getParameter("paymentOfGoodsConfirm"))&& "1".equals(request.getParameter("paymentOfGoodsConfirm"))){
					criteria.andSettled();
				}else if (!StringUtil.isEmpty(request.getParameter("paymentOfGoodsConfirm"))&& "0".equals(request.getParameter("paymentOfGoodsConfirm"))){
					criteria.andUnSettled();
				}
			}else if(progressStatus.equals("5")){
				if (!StringUtil.isEmpty(request.getParameter("cw_confirm_date_begin"))) {
					criteria.andCwConfirmDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("cw_confirm_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("cw_confirm_date_end"))) {
					criteria.andCwConfirmDateLessThanOrEqualTo(sdf.parse(request.getParameter("cw_confirm_date_end")+" 23:59:59"));
				}
			}else if(progressStatus.equals("6")){
				if (!StringUtil.isEmpty(request.getParameter("fw_confirm_date_begin"))) {
					criteria.andFwConfirmDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("fw_confirm_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("fw_confirm_date_end"))) {
					criteria.andFwConfirmDateLessThanOrEqualTo(sdf.parse(request.getParameter("fw_confirm_date_begin")+" 23:59:59"));
				}
			}else if(progressStatus.equals("7")){
				if (!StringUtil.isEmpty(request.getParameter("director_confirm_date_begin"))) {
					criteria.andDirectorConfirmDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("director_confirm_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("director_confirm_date_end"))) {
					criteria.andDirectorConfirmDateLessThanOrEqualTo(sdf.parse(request.getParameter("director_confirm_date_end")+" 23:59:59"));
				}
			}else if(progressStatus.equals("8")){
				if (!StringUtil.isEmpty(request.getParameter("product_confirm_date_begin"))) {
					criteria.andProductConfirmDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("product_confirm_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("product_confirm_date_end"))) {
					criteria.andProductConfirmDateLessThanOrEqualTo(sdf.parse(request.getParameter("product_confirm_date_end")+" 23:59:59"));
				}
			}else if(progressStatus.equals("9")){
				if (!StringUtil.isEmpty(request.getParameter("settlement_confirm_date_begin"))) {
					criteria.andSettlementConfirmDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("settlement_confirm_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("settlement_confirm_date_end"))) {
					criteria.andSettlementConfirmDateLessThanOrEqualTo(sdf.parse(request.getParameter("settlement_confirm_date_end")+" 23:59:59"));
				}
			}else if(progressStatus.equals("10")){
				if (!StringUtil.isEmpty(request.getParameter("op_confirm_date_begin"))) {
					criteria.andOpConfirmDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("op_confirm_date_begin")+" 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("op_confirm_date_end"))) {
					criteria.andOpConfirmDateLessThanOrEqualTo(sdf.parse(request.getParameter("op_confirm_date_end")+" 23:59:59"));
				}
			}
			
			if(StringUtil.isEmpty(confirmStatus)){
				if(progressStatus.equals("0")){
					criteria.andProgressStatusIn(Arrays.asList("0,1".split(",")));
				}else if(progressStatus.equals("1")){
					criteria.andProgressStatusIn(Arrays.asList("1,2".split(",")));
				}else if(progressStatus.equals("2")){
					criteria.andProgressStatusIn(Arrays.asList("2,3".split(",")));
				}else if(progressStatus.equals("3")){
					criteria.andProgressStatusIn(Arrays.asList("3,4".split(",")));
				}else if(progressStatus.equals("4")){
					criteria.andProgressStatusIn(Arrays.asList("4,5".split(",")));
				}else if(progressStatus.equals("5")){
					criteria.andProgressStatusIn(Arrays.asList("5,6".split(",")));
				}else if(progressStatus.equals("6")){
					criteria.andProgressStatusIn(Arrays.asList("6,7".split(",")));
				}else if(progressStatus.equals("7")){//现在匹配的就是操作结算的信息，结算审核不用
					criteria.andProgressStatusIn(Arrays.asList("7,9".split(",")));
				}else if(progressStatus.equals("8")){
					criteria.andProgressStatusIn(Arrays.asList("8,9".split(",")));
				}else if(progressStatus.equals("9")){
					criteria.andProgressStatusIn(Arrays.asList("9,10".split(",")));
				}
			}else if(!StringUtil.isEmpty(confirmStatus) && confirmStatus.equals("1")){//已确认
				if(progressStatus.equals("0")){
					criteria.andProgressStatusEqualTo("1");
				}else if(progressStatus.equals("1")){
					criteria.andProgressStatusEqualTo("2");
				}else if(progressStatus.equals("2")){
					criteria.andProgressStatusEqualTo("3");
				}else if(progressStatus.equals("3")){
					criteria.andProgressStatusEqualTo("4");
				}else if(progressStatus.equals("4")){
					criteria.andProgressStatusEqualTo("5");
				}else if(progressStatus.equals("5")){
					criteria.andProgressStatusEqualTo("6");
				}else if(progressStatus.equals("6")){
					criteria.andProgressStatusEqualTo("7");
				}else if(progressStatus.equals("7")){
					criteria.andProgressStatusEqualTo("9");
				}else if(progressStatus.equals("8")){
					criteria.andProgressStatusEqualTo("9");
				}else if(progressStatus.equals("9")){
					criteria.andProgressStatusEqualTo("10");
				}
			}else if(!StringUtil.isEmpty(confirmStatus) && confirmStatus.equals("0")){//未确认
				criteria.andProgressStatusEqualTo(progressStatus);
			}else if(!StringUtil.isEmpty(confirmStatus) && confirmStatus.equals("2")){//招商确认状态2.不同意关店
				criteria.andProgressStatusEqualTo(progressStatus);
				criteria.andZsConfirmStatusEqualTo("2");
			}
		}
		
		if (!StringUtil.isEmpty(request.getParameter("applyName"))) {
			criteria.andApplyNameEqualTo(request.getParameter("applyName"));
		}
		if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
			criteria.andMchtNameLike(request.getParameter("mchtName"));
		}
		if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
			criteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
		}
		if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
			Integer productTypeId = Integer.parseInt(request.getParameter("productTypeId"));
			if(!productTypeIds.contains(productTypeId)){
				return resMap;
			}else{
				criteria.andProductTypeIdEqualTo(productTypeId);
			}
		}
		
		totalCount = mchtCloseApplicationService.countByCustomExample(example);
		example.setLimitSize(page.getLimitSize());
		example.setLimitStart(page.getLimitStart());
		List<MchtCloseApplicationCustom> mchtCloseApplicationCustoms = mchtCloseApplicationService.selectByCustomExample(example);
		for(MchtCloseApplicationCustom mchtCloseApplicationCustom:mchtCloseApplicationCustoms){
			MchtProductBrandExample mpbe = new MchtProductBrandExample();
			mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
			List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
			String mchtProductBrandStr = "";
			for(MchtProductBrand mchtProductBrand:mchtProductBrands){
				String statusDesc = "";
				if(mchtProductBrand.getStatus().equals("0")){
					statusDesc = "申请中";
				}else if(mchtProductBrand.getStatus().equals("1")){
					statusDesc = "正常";
				}else if(mchtProductBrand.getStatus().equals("2")){
					statusDesc = "暂停";
				}else if(mchtProductBrand.getStatus().equals("3")){
					statusDesc = "关闭";
				}else if(mchtProductBrand.getStatus().equals("4")){
					statusDesc = "驳回申请";
				}
				String rate = "";
				if(mchtProductBrand.getPopCommissionRate()!=null){
					String tmpRate = mchtProductBrand.getPopCommissionRate().multiply(new BigDecimal(100)).toString();
					rate=tmpRate.subSequence(0, tmpRate.lastIndexOf("."))+"%";
				}
				mchtProductBrandStr+="<span style='color:red;'>["+statusDesc+"]</span>"+mchtProductBrand.getProductBrandName()+"<span style='color:green;'>["+rate+"]</span><br>";
			}
			mchtCloseApplicationCustom.setMchtProductBrands(mchtProductBrandStr);
		}
		resMap.put("Rows", mchtCloseApplicationCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	//备注列表
	@RequestMapping(value = "/mcht/close/viewMoreRemarksList.shtml")
	public ModelAndView viewMoreRemarks(HttpServletRequest request) {
		String rePage = "/mcht/close/viewMoreRemarksList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mchtCloseApplicationId", request.getParameter("mchtCloseApplicationId"));
//		resMap.put("remarksType", request.getParameter("remarksType"));
		return new ModelAndView(rePage, resMap);
	}

	//备注列表数据
	@RequestMapping(value = "/mcht/close/viewMoreRemarksDate.shtml")
	@ResponseBody
	public Map<String, Object> viewMoreRemarksDate(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		MchtCloseApplicationRemarksExample mcare = new MchtCloseApplicationRemarksExample();
		mcare.setOrderByClause("id desc");
		mcare.createCriteria().andDelFlagEqualTo("0").andMchtCloseApplicationIdEqualTo(Integer.parseInt(request.getParameter("mchtCloseApplicationId")));
		totalCount = mchtCloseApplicationRemarksCustomMapper.countByExample(mcare);
		mcare.setLimitSize(page.getLimitSize());
		mcare.setLimitStart(page.getLimitStart());
		List<MchtCloseApplicationRemarksCustom> mchtCloseApplicationRemarksCustoms = mchtCloseApplicationRemarksCustomMapper.selectByExample(mcare);
		resMap.put("Rows", mchtCloseApplicationRemarksCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	//关店确认操作
	@RequestMapping(value = "/mcht/close/confirm.shtml")
	@ResponseBody
	public Map<String, Object> confirm(Model model,HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(id);
			mchtCloseApplication.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtCloseApplication.setUpdateDate(new Date());
			String progressStatus = mchtCloseApplication.getProgressStatus();
			if(progressStatus.equals("0")){
				mchtCloseApplication.setProgressStatus("1");
				mchtCloseApplication.setZsConfirmStatus("1");
				mchtCloseApplication.setZsConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtCloseApplication.setZsConfirmDate(new Date());
				mchtCloseApplication.setCloseReason(request.getParameter("closeReason"));
				MchtInfo mi = new MchtInfo();
				mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mi.setUpdateDate(new Date());
				mi.setCloseReason(request.getParameter("closeReason"));
				MchtInfoExample mie = new MchtInfoExample();
				mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtCloseApplication.getMchtId());
				mchtInfoService.updateByExampleSelective(mi, mie);
			}else if(progressStatus.equals("1")){
				mchtCloseApplication.setProgressStatus("2");
				mchtCloseApplication.setActivityStatus("1");
				mchtCloseApplication.setCommodityStatus("1");
				mchtCloseApplication.setMarketingStatus("1");
				mchtCloseApplication.setCommodityConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtCloseApplication.setCommodityConfirmDate(new Date());
			}else if(progressStatus.equals("2")){
				mchtCloseApplication.setProgressStatus("3");
				String businessInformationStatus = request.getParameter("businessInformationStatus");
				String mchtContractStatus = request.getParameter("mchtContractStatus");
				String mchtArchiveStatus = request.getParameter("mchtArchiveStatus");
				String businessInformationRemarks = request.getParameter("businessInformationRemarks");
				mchtCloseApplication.setBusinessInformationStatus(businessInformationStatus);
				mchtCloseApplication.setMchtContractStatus(mchtContractStatus);
				mchtCloseApplication.setMchtArchiveStatus(mchtArchiveStatus);
				if(mchtContractStatus.equals("1") && mchtArchiveStatus.equals("1")){
                mchtCloseApplication.setFwCloseHangUpStatus("0");
				}
				mchtCloseApplication.setBusinessInformationRemarks(businessInformationRemarks);
				mchtCloseApplication.setMchtArchiveConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtCloseApplication.setMchtArchiveConfirmDate(new Date());
			}else if(progressStatus.equals("3")){
				mchtCloseApplication.setProgressStatus("4");
				mchtCloseApplication.setOrderConfirmStatus("1");
				mchtCloseApplication.setServiceOrderConfirmStatus("1");
				mchtCloseApplication.setAppealOrderConfirmStatus("1");
				mchtCloseApplication.setInterventionOrderConfirmStatus("1");
				mchtCloseApplication.setKfCloseHangUpStatus("0");
				mchtCloseApplication.setThreePackagePeriod(request.getParameter("threePackagePeriod"));
				mchtCloseApplication.setKfConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtCloseApplication.setKfConfirmDate(new Date());
			}else if(progressStatus.equals("4")){//财务货代结清确认
				mchtCloseApplication.setProgressStatus("5");//关店进度状态 
				mchtCloseApplication.setPaymentOfGoodsConfirm("1");//货款结清确认0.未结清1.已结清
				mchtCloseApplication.setCwConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));//财务确认人
				mchtCloseApplication.setCwConfirmDate(new Date());//财务确认时间
				mchtCloseApplication.setContractAuditStatus("0");//终止合同线上审核状态(未上传)
				//复用已退还保证金,因为还有一个deposit_return_status退还保证金状态 0表示未退,1表示已退
				mchtCloseApplication.setReturnedDepositAmount(new BigDecimal(request.getParameter("returnedDepositAmount")));
                MchtDepositExample mchtDepositExample = new MchtDepositExample();
                mchtDepositExample.createCriteria().andMchtIdEqualTo(mchtCloseApplication.getMchtId()).andDelFlagEqualTo("0");
                List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(mchtDepositExample);
                mchtDeposits.get(0).setPayAmt(new BigDecimal(request.getParameter("returnedDepositAmount")));
                mchtDeposits.get(0).setUnpayAmt(mchtDeposits.get(0).getTotalAmt().subtract(new BigDecimal(request.getParameter("returnedDepositAmount"))).setScale(2,BigDecimal.ROUND_HALF_UP));
                mchtDepositService.updateByPrimaryKey(mchtDeposits.get(0));
            }else if(progressStatus.equals("5")){
				mchtCloseApplication.setProgressStatus("6");
				mchtCloseApplication.setEndCooperationAgreement("1");
				String depositDate = request.getParameter("depositDate");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				mchtCloseApplication.setDepositDate(sdf.parse(depositDate));
				mchtCloseApplication.setFwConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtCloseApplication.setFwConfirmDate(new Date());
			}else if(progressStatus.equals("6")){
				mchtCloseApplication.setProgressStatus("7");
				mchtCloseApplication.setDirectorConfirmStatus("1");
				mchtCloseApplication.setDirectorConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtCloseApplication.setDirectorConfirmDate(new Date());
			}else if(progressStatus.equals("7")){
				mchtCloseApplication.setProgressStatus("9");
				mchtCloseApplication.setProductConfirmStatus("1");
				mchtCloseApplication.setSettlementAuditStatus("1");
				mchtCloseApplication.setProductConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtCloseApplication.setProductConfirmDate(new Date());
				//结算审核时间没了,操作关店时填充结算审核确认时间，操作结算状态时展示
				mchtCloseApplication.setSettlementConfirmDate(new Date());
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtCloseApplication.getMchtId());
				mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtInfo.setUpdateDate(new Date());
				mchtInfo.setStatus("3");//3.关闭
				mchtInfo.setStatusDate(new Date());
				mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
			}else if(progressStatus.equals("8")){
				mchtCloseApplication.setProgressStatus("9");
				mchtCloseApplication.setSettlementAuditStatus("1");
				mchtCloseApplication.setSettlementConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtCloseApplication.setSettlementConfirmDate(new Date());
				if(!StringUtils.isEmpty(request.getParameter("payAmt"))){
					mchtCloseApplication.setReturnedDepositAmount(new BigDecimal(request.getParameter("payAmt")));
				}
			}else if(progressStatus.equals("9")){
				mchtCloseApplication.setProgressStatus("10");
				mchtCloseApplication.setDepositReturnStatus("1");
				mchtCloseApplication.setDepositReturnDate(new Date());
				mchtCloseApplication.setNeedPayStatus("1");
				mchtCloseApplication.setOpConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtCloseApplication.setOpConfirmDate(new Date());
			}
			mchtCloseApplicationService.updateByPrimaryKey(mchtCloseApplication);
			if(progressStatus.equals("4")){//财务货代结清确认 生成PDF 发送短信
				//生成pdf
				mchtCloseApplicationService.createPDF(mchtCloseApplication.getId());
				
				/**
				 * 发送短信短信通知对象：发送对象：商家主账号的手机号码，店铺总负责人（ID最大且审核状态=通过）、商家的商家运营（ID最大ID最大且审核状态=通过）。
				 * 短信内容：【醒购】您的店铺【商家序号】的合作协议已生成，请登录平台下载盖章上传至平台。
				 */
				sendMessgaeToMcht(mchtCloseApplication.getMchtId());
			}
			if(progressStatus.equals("9")){//操作结算生成付款单
				mchtCloseApplicationService.createPDFOfPayMent(mchtCloseApplication.getId());
			}
			
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//关店回退操作
	@RequestMapping(value = "/mcht/close/returnBack.shtml")
	@ResponseBody
	public Map<String, Object> returnBack(Model model,HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String remarks = request.getParameter("remarks");
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(id);
			mchtCloseApplication.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtCloseApplication.setUpdateDate(new Date());
			String progressStatus = mchtCloseApplication.getProgressStatus();
			MchtCloseApplicationRemarks mcar = new MchtCloseApplicationRemarks();
			mcar.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mcar.setCreateDate(new Date());
			mcar.setDelFlag("0");
			mcar.setMchtCloseApplicationId(id);
			if(progressStatus.equals("0")){
				mchtCloseApplication.setZsConfirmStatus("2");
				mchtCloseApplication.setZsConfirmBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtCloseApplication.setZsConfirmDate(new Date());
				mchtCloseApplication.setZsRejectReason(request.getParameter("zsRejectReason"));
				mcar.setRemarks("<span style='color:red;'>【招商驳回】</span>"+request.getParameter("zsRejectReason"));
				mcar.setRemarksType("0");
			}else if(progressStatus.equals("1")){
				mchtCloseApplication.setProgressStatus("0");
				mchtCloseApplication.setZsConfirmStatus("0");
				mchtCloseApplication.setZsConfirmBy(null);
				mchtCloseApplication.setZsConfirmDate(null);
				mchtCloseApplication.setZsRemarks("<span style='color:red;'>【商品部回退】</span>"+remarks);
				mchtCloseApplication.setZsRemarksDate(new Date());
				mchtCloseApplication.setMchtInfoStatus("0");//店铺暂停：否
				mcar.setRemarks("<span style='color:red;'>【商品部回退】</span>"+remarks);
				mcar.setRemarksType("0");
				//mchtInfo 暂停状态改为正常
				MchtInfoExample mie = new MchtInfoExample();
				mie.createCriteria().andIdEqualTo(mchtCloseApplication.getMchtId()).andDelFlagEqualTo("0");
				MchtInfo mi = new MchtInfo();
				mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mi.setUpdateDate(new Date());
				mi.setStatus("1");//暂停改为正常
				mi.setStatusDate(new Date());
				mchtInfoService.updateByExampleSelective(mi, mie);
			}else if(progressStatus.equals("2")){
				mchtCloseApplication.setProgressStatus("1");
				mchtCloseApplication.setActivityStatus("0");
				mchtCloseApplication.setCommodityStatus("0");
				mchtCloseApplication.setMarketingStatus("0");
				mchtCloseApplication.setCommodityConfirmBy(null);
				mchtCloseApplication.setCommodityConfirmDate(null);
				mchtCloseApplication.setCommodityRemarks("<span style='color:red;'>【商家资料确认回退】</span>"+remarks);
				mchtCloseApplication.setCommodityRemarksDate(new Date());
				mcar.setRemarks("<span style='color:red;'>【商家资料确认回退】</span>"+remarks);
				mcar.setRemarksType("1");
			}else if(progressStatus.equals("3")){
				mchtCloseApplication.setProgressStatus("2");
				mchtCloseApplication.setMchtContractStatus("0");
				mchtCloseApplication.setMchtArchiveStatus("0");
				mchtCloseApplication.setBusinessInformationStatus("0");
				mchtCloseApplication.setBusinessInformationRemarks("");
				mchtCloseApplication.setMchtArchiveConfirmBy(null);
				mchtCloseApplication.setMchtArchiveConfirmDate(null);
				mchtCloseApplication.setMchtArchiveRemarks("<span style='color:red;'>【客服部回退】</span>"+remarks);
				mchtCloseApplication.setMchtArchiveRemarksDate(new Date());
				mcar.setRemarks("<span style='color:red;'>【客服部回退】</span>"+remarks);
				mcar.setRemarksType("2");
			}else if(progressStatus.equals("4")){
				mchtCloseApplication.setProgressStatus("3");
				mchtCloseApplication.setOrderConfirmStatus("0");
				mchtCloseApplication.setServiceOrderConfirmStatus("0");
				mchtCloseApplication.setAppealOrderConfirmStatus("0");
				mchtCloseApplication.setInterventionOrderConfirmStatus("0");
				mchtCloseApplication.setThreePackagePeriod("");
				mchtCloseApplication.setKfConfirmBy(null);
				mchtCloseApplication.setKfConfirmDate(null);
				mchtCloseApplication.setKfRemarks("<span style='color:red;'>【财务部回退】</span>"+remarks);
				mchtCloseApplication.setKfRemarksDate(new Date());
				mcar.setRemarks("<span style='color:red;'>【财务部回退】</span>"+remarks);
				mcar.setRemarksType("3");
			}
			mchtCloseApplicationService.updateByPrimaryKey(mchtCloseApplication,mcar);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//店铺暂停操作
	@RequestMapping(value = "/mcht/close/mchtInfoPause.shtml")
	@ResponseBody
	public Map<String, Object> mchtInfoPause(Model model,HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(id);
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtCloseApplication.getMchtId());
			mchtInfo.setStatus("2");//暂停
			mchtInfo.setStatusDate(new Date());
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setUpdateDate(new Date());
			
			mchtCloseApplication.setMchtInfoStatus("1");
			mchtCloseApplication.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtCloseApplication.setUpdateDate(new Date());
			
			MchtProductBrandExample mpbe = new MchtProductBrandExample();
			mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplication.getMchtId()).andStatusEqualTo("1");
			MchtProductBrand mpb = new MchtProductBrand();
			mpb.setStatus("2");
			mpb.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mpb.setUpdateDate(new Date());
			mchtCloseApplicationService.update(mchtCloseApplication,mchtInfo,mpb,mpbe);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//挂起操作
	@RequestMapping(value = "/mcht/close/hangUp.shtml")
	@ResponseBody
	public Map<String, Object> hangUp(Model model,HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String hangUpReason = request.getParameter("hangUpReason");
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(id);
			mchtCloseApplication.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtCloseApplication.setUpdateDate(new Date());
			if(mchtCloseApplication.getProgressStatus().equals("2")){//商家资料挂起
				mchtCloseApplication.setFwCloseHangUpStatus("1");
				mchtCloseApplication.setFwHangUpReason(hangUpReason);
				mchtCloseApplication.setFwHangUpDate(new Date());
			}else if(mchtCloseApplication.getProgressStatus().equals("3")){//客服挂起
				mchtCloseApplication.setKfCloseHangUpStatus("1");
				mchtCloseApplication.setKfHangUpReason(hangUpReason);
				mchtCloseApplication.setKfHangUpDate(new Date());
			}
			mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplication);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//操作权限检验
	@RequestMapping(value = "/mcht/close/checkAuth.shtml")
	@ResponseBody
	public Map<String, Object> checkAuth(Model model,HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(id);
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			PlatformContactExample pce = new PlatformContactExample();
			PlatformContactExample.Criteria c = pce.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStaffIdEqualTo(staffID);
			c.andStatusEqualTo("0");
			if(mchtCloseApplication.getProgressStatus().equals("0")){//招商操作
				//STORY #1083
//				c.andContactTypeEqualTo("1");
//				List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
//				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(staffID);
//				if(platformContacts == null || platformContacts.size() == 0 || sysStaffInfo.getIsManagement().equals("0")){
//					resMap.put("returnCode", "4004");
//					resMap.put("returnMsg", "只有招商部管理层才有操作权限");
//				}
			}else if(mchtCloseApplication.getProgressStatus().equals("2")){//商家资料
				c.andContactTypeEqualTo("7");//7.法务
				List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
				if(platformContacts == null || platformContacts.size() == 0 ){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "只有法务部才有操作权限");
				}
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * 申请关店确认页面   
	 */
	@RequestMapping("/mcht/close/toEditApply.shtml")
	public ModelAndView toEditApply(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/close/toEditApply");
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtCloseApplicationCustom mchtCloseApplicationCustom = mchtCloseApplicationService.selectCustomByPrimaryKey(id);
		m.addObject("mchtCloseApplicationCustom", mchtCloseApplicationCustom);
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
		m.addObject("mchtProductBrands", mchtProductBrands);
		
		MchtSettleOrderExample msoe = new MchtSettleOrderExample();
		List<String> payStatusList = new ArrayList<String>();
		payStatusList.add("1");
		payStatusList.add("2");
		msoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId()).andPayStatusIn(payStatusList);
		List<MchtSettleOrder> mchtSettleOrders = mchtSettleOrderService.selectByExample(msoe);
		BigDecimal total = new BigDecimal(0);
		for(MchtSettleOrder mchtSettleOrder:mchtSettleOrders){
			total.add(mchtSettleOrder.getNeedPayAmount());
		}
		m.addObject("total", total);
		
		SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
		SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
		singleProductActivityCustomCriteria.andDelFlagEqualTo("0").andAuditStatusIn(Arrays.asList("0","1","3")).andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
		singleProductActivityCustomCriteria.andEndTimeGreaterThanOrEqualToOrIsNull();
		int singleProductActivityCount = singleProductActivityService.countByCustomExample(singleProductActivityCustomExample);
		m.addObject("singleProductActivityCount", singleProductActivityCount);
		
		int activityCount = activityAreaService.countActivityByMchtId(mchtCloseApplicationCustom.getMchtId());
		m.addObject("activityCount", activityCount);
		
		ProductExample pe = new ProductExample();
		pe.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
		int productCount = productService.countByExample(pe);
		m.addObject("productCount", productCount);
		
		SubOrderExample subOrderExample = new SubOrderExample();
		subOrderExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId()).andStatusEqualTo("2");
		int subOrderCount = subOrderService.countByExample(subOrderExample);
		m.addObject("subOrderCount", subOrderCount);
		
		CustomerServiceOrderCustomExample e = new CustomerServiceOrderCustomExample();
		CustomerServiceOrderCustomCriteria cc = e.createCriteria();
		cc.andDelFlagEqualTo("0");
		cc.andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
		cc.andStatusEqualTo("0");
		int customerServiceOrderCount = customerServiceOrderService.countCustomerServiceOrderCustomByExample(e);
		m.addObject("customerServiceOrderCount", customerServiceOrderCount);
		
		AppealOrderExample aoe = new AppealOrderExample();
		List<String> appealOrderStatusList = new ArrayList<String>();
		appealOrderStatusList.add("1");
		appealOrderStatusList.add("2");
		aoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId()).andStatusIn(appealOrderStatusList);
		int appealOrderCount = appealOrderService.countByExample(aoe);
		m.addObject("appealOrderCount", appealOrderCount);
		
		InterventionOrderExample ioe = new InterventionOrderExample();
		ioe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId()).andStatusNotEqualTo("8");
		int interventionOrderCount = interventionOrderService.countByExample(ioe);
		m.addObject("interventionOrderCount", interventionOrderCount);
		
		List<SysStatus> closeReasonList = DataDicUtil.getStatusList("BU_MCHT_CLOSE_APPLICATION", "CLOSE_REASON");
		m.addObject("closeReasonList", closeReasonList);
		return m;
	}
	
	/**
	 * 
	 * 回退备注页面   
	 */
	@RequestMapping("/mcht/close/toReturnBack.shtml")
	public ModelAndView toReturnBack(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/close/toReturnBack");
		m.addObject("id", request.getParameter("id"));
		return m;
	}
	
	/**
	 * 子订单列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/close/viewSubOrderList.shtml")
	public ModelAndView viewSubOrderList(HttpServletRequest request) {
		String rtPage = "/mcht/close/viewSubOrderList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mchtId", request.getParameter("mchtId"));
		if (!StringUtils.isEmpty(request.getParameter("subOrderStatus"))) {
			resMap.put("subOrderStatus", request.getParameter("subOrderStatus"));
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 子订单列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/close/viewSubOrderData.shtml")
	@ResponseBody
	public Map<String, Object> viewSubOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SubOrderCustom> listMap = null;
		Integer totalCount = 0;
		try {
			SubOrderCustomExample subOrderCustomExample=new SubOrderCustomExample();
			SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria=subOrderCustomExample.createCriteria();
			subOrderCustomCriteria.andDelFlagEqualTo("0");
			subOrderCustomExample.setOrderByClause("a.id desc");
			String mchtId=request.getParameter("mchtId");
			subOrderCustomCriteria.andMchtIdEqualTo(Integer.parseInt(mchtId));
			List<String> subOrderStatusList = new ArrayList<String>();
			subOrderStatusList.add("1");
			subOrderStatusList.add("2");
			subOrderCustomCriteria.andStatusIn(subOrderStatusList);
			if(!StringUtil.isEmpty(request.getParameter("subOrderCode"))){
				String subOrderCode=request.getParameter("subOrderCode");
				subOrderCustomCriteria.andSubOrderCodeEqualTo(subOrderCode);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("productName")) ){
				String productName=request.getParameter("productName");
				subOrderCustomCriteria.andProductNameLikeTo(productName);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				subOrderCustomCriteria.andStatusEqualTo(status);
			}
			
			totalCount = subOrderService.countSubOrderCustomByExample(subOrderCustomExample);
			subOrderCustomExample.setLimitStart(page.getLimitStart());
			subOrderCustomExample.setLimitSize(page.getLimitSize());
			listMap = subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
			
			resMap.put("Rows", listMap);
			resMap.put("Total", totalCount);
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 售后列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/close/viewCustomerServiceOrderList.shtml")
	public ModelAndView viewCustomerServiceOrderList(HttpServletRequest request) {
		String rtPage = "/mcht/close/viewCustomerServiceOrderList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(request.getParameter("mchtId"))){
			String mchtId=request.getParameter("mchtId");
			resMap.put("mchtId", mchtId);
		}
		String status=request.getParameter("status");
		resMap.put("status", status);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 售后列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/close/viewCustomerServiceOrderData.shtml")
	@ResponseBody
	public Map<String, Object> viewCustomerServiceOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			CustomerServiceOrderCustomExample customerServiceOrderCustomExample=new CustomerServiceOrderCustomExample();
			CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria customerServiceOrderCustomCriteria=customerServiceOrderCustomExample.createCriteria();
			customerServiceOrderCustomCriteria.andDelFlagEqualTo("0");
			customerServiceOrderCustomExample.setOrderByClause("a.id desc");
			String mchtId=request.getParameter("mchtId");
			customerServiceOrderCustomCriteria.andMchtIdEqualTo(Integer.parseInt(mchtId));
			List<String> customerServiceOrderStatusList = new ArrayList<String>();
			customerServiceOrderStatusList.add("0");
			customerServiceOrderStatusList.add("2");
			customerServiceOrderCustomCriteria.andStatusIn(customerServiceOrderStatusList);
			if(!StringUtil.isEmpty(request.getParameter("orderCode")) ){
				String orderCode=request.getParameter("orderCode");
				customerServiceOrderCustomCriteria.andOrderCodeEqualTo(orderCode);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				customerServiceOrderCustomCriteria.andStatusEqualTo(status);
			}
			
			totalCount = customerServiceOrderService.countCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
			customerServiceOrderCustomExample.setLimitStart(page.getLimitStart());
			customerServiceOrderCustomExample.setLimitSize(page.getLimitSize());
			List<CustomerServiceOrderCustom> customerServiceOrderCustoms=customerServiceOrderService.selectCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
			resMap.put("Rows", customerServiceOrderCustoms);
			resMap.put("Total", totalCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 客户投诉管理列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/close/viewAppealOrderList.shtml")
	public ModelAndView viewAppealOrderList(HttpServletRequest request) {
		String rtPage = "/mcht/close/viewAppealOrderList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mchtId", request.getParameter("mchtId"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 客户投诉管理列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/close/viewAppealOrderData.shtml")
	@ResponseBody
	public Map<String, Object> viewAppealOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			AppealOrderCustomExample appealOrderCustomExample = new AppealOrderCustomExample();
			AppealOrderCustomExample.AppealOrderCustomCriteria appealOrderCustomCriteria = appealOrderCustomExample.createCriteria();
			appealOrderCustomCriteria.andDelFlagEqualTo("0");
			appealOrderCustomCriteria.andMchtIdEqualTo(Integer.parseInt(request.getParameter("mchtId")));
			List<String> appealOrderStausList = new ArrayList<String>();
			appealOrderStausList.add("1");
			appealOrderStausList.add("2");
			appealOrderCustomCriteria.andStatusIn(appealOrderStausList);
			String searchOrderCode = request.getParameter("orderCode");
			String searchStatus = request.getParameter("status");
			if(!StringUtil.isEmpty(searchOrderCode)){
				appealOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode);
			}
			if(!StringUtil.isEmpty(searchStatus)){
				appealOrderCustomCriteria.andStatusEqualTo(searchStatus);
			}
			totalCount = appealOrderService.countAppealOrderCustomByExample(appealOrderCustomExample);
			appealOrderCustomExample.setLimitStart(page.getLimitStart());
			appealOrderCustomExample.setLimitSize(page.getLimitSize());
			List<AppealOrderCustom> appealOrderCustoms = appealOrderService.selectAppealOrderCustomByExample(appealOrderCustomExample);
			resMap.put("Rows", appealOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	@RequestMapping("/mcht/close/viewInterventionOrderList.shtml")
	public ModelAndView viewInterventionOrderList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/mcht/close/viewInterventionOrderList");
		m.addObject("mchtId", request.getParameter("mchtId"));
		m.addObject("statusList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "STATUS")); //介入单状态
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/mcht/close/viewInterventionOrderData.shtml")
	public Map<String, Object> viewInterventionOrderData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<InterventionOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			InterventionOrderCustomExample interventionOrderCustomExample = new InterventionOrderCustomExample();
			InterventionOrderCustomExample.InterventionOrderCustomCriteria interventionOrderCustomCriteria = interventionOrderCustomExample.createCriteria();
			interventionOrderCustomCriteria.andDelFlagEqualTo("0");
			interventionOrderCustomCriteria.andMchtIdEqualTo(Integer.parseInt(request.getParameter("mchtId")));
			interventionOrderCustomCriteria.andStatusNotEqualTo("8");
			if(!StringUtil.isEmpty(request.getParameter("interventionCode"))) { //介入单号
				interventionOrderCustomCriteria.andInterventionCodeEqualTo(request.getParameter("interventionCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("customerServiceOrderCode"))) { //售后单号
				interventionOrderCustomCriteria.andCustomerServiceOrderCode(request.getParameter("customerServiceOrderCode"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status"))) { //介入单状态
				interventionOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			
			interventionOrderCustomExample.setLimitStart(page.getLimitStart());
			interventionOrderCustomExample.setLimitSize(page.getLimitSize());
			totalCount = interventionOrderService.countByCustomExample(interventionOrderCustomExample);
			dataList = interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
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
	 * 资料归档情况
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/close/newAddMchtProductBrandPics.shtml")
	public ModelAndView newAddMchtProductBrandPics(HttpServletRequest request) {
		String rtPage = "/mcht/close/newAddMchtProductBrandPics";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
		MchtProductBrandCustomExample mpbce = new MchtProductBrandCustomExample();
		mpbce.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andBrandSourceEqualTo("2");
		List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.selectByCustomExample(mpbce);
		if(mchtProductBrandCustoms!=null && mchtProductBrandCustoms.size()>0){
			for(MchtProductBrandCustom mchtProductBrandCustom:mchtProductBrandCustoms){
				MchtBrandAptitudeExample example = new MchtBrandAptitudeExample();
				MchtBrandAptitudeExample.Criteria criteria = example.createCriteria();
				criteria.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
				List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(example);
				for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
					MchtBrandAptitudePicExample e = new MchtBrandAptitudePicExample();
					MchtBrandAptitudePicExample.Criteria c = e.createCriteria();
					c.andDelFlagEqualTo("0").andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
					List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(e);
					mchtBrandAptitudeCustom.setMchtBrandAptitudePicList(mchtBrandAptitudePics);
				}
				mchtProductBrandCustom.setMchtBrandAptitudeCustoms(mchtBrandAptitudeCustoms);
				
				MchtPlatformAuthPicExample mpapce = new MchtPlatformAuthPicExample();
				MchtPlatformAuthPicExample.Criteria mbapcec = mpapce.createCriteria();
				mbapcec.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
				List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService.selectByExample(mpapce);
				mchtProductBrandCustom.setMchtPlatformAuthPicList(mchtPlatformAuthPics);
				
				MchtBrandInvoicePicExample mbipce = new MchtBrandInvoicePicExample();
				MchtBrandInvoicePicExample.Criteria mbipcec = mbipce.createCriteria();
				mbipcec.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
				List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicServer.selectByExample(mbipce);
				mchtProductBrandCustom.setMchtBrandInvoicePicList(mchtBrandInvoicePics);
				
				MchtBrandInspectionPicExample mbipce2 = new MchtBrandInspectionPicExample();
				MchtBrandInspectionPicExample.Criteria mbipcec2 = mbipce2.createCriteria();
				mbipcec2.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
				List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicServer.selectByExample(mbipce2);
				mchtProductBrandCustom.setMchtBrandInspectionPicList(mchtBrandInspectionPics);
				
				MchtBrandOtherPicExample mbopce = new MchtBrandOtherPicExample();
				MchtBrandOtherPicExample.Criteria mbopcec = mbopce.createCriteria();
				mbopcec.andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
				List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicServer.selectByExample(mbopce);
				mchtProductBrandCustom.setMchtBrandOtherPicList(mchtBrandOtherPics);
			}
		}
		resMap.put("mchtProductBrandCustoms", mchtProductBrandCustoms);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * 设置停止协议出具日期页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/close/toEndAgreement.shtml")
	public ModelAndView toEndAgreement(HttpServletRequest request) {
		String rtPage = "/mcht/close/toEndAgreement";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("id", request.getParameter("id"));
		MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("agreementIssueDate", mchtCloseApplication.getAgreementIssueDate());
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * 设置停止协议出具日期并生成协议pdf文件
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/mcht/close/saveEndAgreement.shtml")
	@ResponseBody
	public Map<String, Object> saveEndAgreement(HttpServletRequest request) throws ParseException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(id);
		String agreementIssueDate = request.getParameter("agreementIssueDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		mchtCloseApplication.setAgreementIssueDate(sdf.parse(agreementIssueDate));
		mchtCloseApplication.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		mchtCloseApplication.setUpdateDate(new Date());
		mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplication);
		//TODO 生成协议PDF(将生产pdf改到财务货款结清确认)
		//mchtCloseApplicationService.createPDF(mchtCloseApplication.getId());
		
		return resMap;
	}
	
	/**
	 * 
	 * @Title editRemarksManager   
	 * @Description TODO(编辑备注)   
	 * @author pengl
	 * @date 2018年10月18日 上午10:51:16
	 */
	@RequestMapping(value = "/mcht/editRemarksManager.shtml")
	public ModelAndView editRemarksManager(HttpServletRequest request) {
		String rtPage = "/mcht/editRemarks";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtId = request.getParameter("mchtId");
		if(!StringUtil.isEmpty(mchtId)) {
			MchtInfoCustom mchtInfoCustom = mchtInfoService.selectMchtInfoCustomById(Integer.parseInt(mchtId));
			resMap.put("mchtInfoCustom", mchtInfoCustom);
			resMap.put("status", request.getParameter("status"));
			resMap.put("remarksType", request.getParameter("remarksType"));
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @Title editRemarks   
	 * @Description TODO(编辑备注)   
	 * @author pengl
	 * @date 2018年10月18日 上午10:54:34
	 */
	@ResponseBody
	@RequestMapping(value = "/mcht/editRemarks.shtml")
	public ModelAndView editRemarks(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			String staffID = this.getSessionStaffBean(request).getStaffID();
			Date date = new Date();
			String mchtId = request.getParameter("mchtId");
			String remarksType = request.getParameter("remarksType");
			String operateRemarks = request.getParameter("operateRemarks");
			String companyInfAuditInnerRemarks = request.getParameter("companyInfAuditInnerRemarks");
			String mchtOptimizeDepositRemarks = request.getParameter("mchtOptimizeDepositRemarks");
			String mchtOptimizeGrossProfitRateRemarks = request.getParameter("mchtOptimizeGrossProfitRateRemarks");
			String mchtOptimizeProductRemarks = request.getParameter("mchtOptimizeProductRemarks");
			String mchtOptimizeServiceRemarks = request.getParameter("mchtOptimizeServiceRemarks");
			String mchtOptimizeWuliuRemarks = request.getParameter("mchtOptimizeWuliuRemarks");
			String mchtOptimizeSpreadRemarks = request.getParameter("mchtOptimizeSpreadRemarks");
			if("fw".equals(remarksType)) {
				MchtInfo mchtInfo = new MchtInfo();
				mchtInfo.setId(Integer.parseInt(mchtId));
				mchtInfo.setCompanyInfAuditInnerRemarks(companyInfAuditInnerRemarks);
				mchtInfo.setUpdateBy(Integer.parseInt(staffID));
				mchtInfo.setUpdateDate(date);
				mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
			}else {
				MchtOptimizeExample mchtOptimizeExample = new MchtOptimizeExample();
				mchtOptimizeExample.createCriteria().andMchtIdEqualTo(Integer.parseInt(mchtId));
				MchtOptimize mchtOptimize = new MchtOptimize();
				if("yy".equals(remarksType)) {
					mchtOptimize.setOperateRemarks(operateRemarks);
				}else if("bz".equals(remarksType)) {
					mchtOptimize.setDepositRemarks(mchtOptimizeDepositRemarks);
				}else if("ml".equals(remarksType)) {
					mchtOptimize.setGrossProfitRateRemarks(mchtOptimizeGrossProfitRateRemarks);
				}else if("sp".equals(remarksType)) {
					mchtOptimize.setProductRemarks(mchtOptimizeProductRemarks);
				}else if("ff".equals(remarksType)) {
					mchtOptimize.setServiceRemarks(mchtOptimizeServiceRemarks);
				}else if("wl".equals(remarksType)) {
					mchtOptimize.setWuliuRemarks(mchtOptimizeWuliuRemarks);
				}else if("tg".equals(remarksType)) {
					mchtOptimize.setSpreadRemarks(mchtOptimizeSpreadRemarks);
				}
				List<MchtOptimize> mchtOptimizeList = mchtOptimizeService.selectByExample(mchtOptimizeExample);
				if(mchtOptimizeList != null && mchtOptimizeList.size() > 0) {
					mchtOptimize.setUpdateBy(Integer.parseInt(staffID));
					mchtOptimize.setUpdateDate(date);
					mchtOptimizeService.updateByExampleSelective(mchtOptimize, mchtOptimizeExample);
				}else {
					mchtOptimize.setMchtId(Integer.parseInt(mchtId));
					mchtOptimize.setCreateBy(Integer.parseInt(staffID));
					mchtOptimize.setCreateDate(date);
					mchtOptimizeService.insertSelective(mchtOptimize);
				}
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	/**
	 * 
	 * @Title addMchtPlatformContact   
	 * @Description TODO(领取)   
	 * @author pengl
	 * @date 2018年10月18日 下午12:43:50
	 */
	@ResponseBody
	@RequestMapping("/mcht/addMchtPlatformContacts.shtml")
	public Map<String, Object> addMchtPlatformContact(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mchtId = request.getParameter("mchtId");
			String contactType = request.getParameter("contactType");
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0")
				.andContactTypeEqualTo(contactType).andStaffIdEqualTo(Integer.parseInt(staffID));
			List<PlatformContact> platformContactList = platformContactService.selectByExample(platformContactExample);
			if(platformContactList != null && platformContactList.size() > 0 && !StringUtil.isEmpty(mchtId)) {
				MchtPlatformContact mchtPlatformContact = new MchtPlatformContact();
				mchtPlatformContact.setMchtId(Integer.parseInt(mchtId));
				mchtPlatformContact.setPlatformContactId(platformContactList.get(0).getId());
				mchtPlatformContact.setStatus("1");
				mchtPlatformContact.setCreateBy(Integer.parseInt(staffID));
				mchtPlatformContact.setCreateDate(date);
				mchtPlatformContactService.insertSelective(mchtPlatformContact);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put("code", code);
		resMap.put("msg", msg);
		return resMap; 
	}
	
	/**
	 * 
	 * @Title updateMchtOptimizeGradeManager   
	 * @Description TODO(配合度、法务风险等级)   
	 * @author pengl
	 * @date 2018年10月18日 下午2:01:25
	 */
	@RequestMapping("/mcht/updateMchtOptimizeGradeManager.shtml")
	public ModelAndView updateMchtOptimizeGradeManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/updateMchtOptimizeGrade");
		String gradeType = request.getParameter("gradeType");
		if(!StringUtil.isEmpty(gradeType)) {
			if("1".equals(gradeType)) {
				m.addObject("auditRiskList", DataDicUtil.getTableStatus("BU_MCHT_OPTIMIZE", "AUDIT_RISK"));
			}else if ("2".equals(gradeType)) {
				m.addObject("degreeAdaptabilityList", DataDicUtil.getTableStatus("BU_MCHT_OPTIMIZE", "DEGREE_ADAPTABILITY"));
			}else {
				m.addObject("resourceGradeList", DataDicUtil.getTableStatus("BU_MCHT_OPTIMIZE", "RESOURCE_GRADE"));
			}
		}
		m.addObject("gradeType", gradeType);
		String mchtId = request.getParameter("mchtId");
		MchtInfoCustom mchtInfoCustom = mchtInfoService.selectMchtInfoCustomById(Integer.parseInt(mchtId));
		m.addObject("mchtInfoCustom", mchtInfoCustom);
		return m;
	}
	
	/**
	 * 
	 * @Title updateMchtOptimizeGrade   
	 * @Description TODO(配合度、法务风险等级)   
	 * @author pengl
	 * @date 2018年10月18日 下午2:01:41
	 */
	@RequestMapping("/mcht/updateMchtOptimizeGrade.shtml")
	public ModelAndView updateMchtOptimizeGrade(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String staffID = this.getSessionStaffBean(request).getStaffID();
			Date date = new Date();
			String mchtId = request.getParameter("mchtId");
			String auditRisk = request.getParameter("auditRisk");
			String degreeAdaptability = request.getParameter("degreeAdaptability");
			String resourceGrade = request.getParameter("resourceGrade");
			String gradeType = request.getParameter("gradeType");
			MchtOptimizeExample mchtOptimizeExample = new MchtOptimizeExample();
			mchtOptimizeExample.createCriteria().andMchtIdEqualTo(Integer.parseInt(mchtId));
			MchtOptimize mchtOptimize = new MchtOptimize();
			if("1".equals(gradeType)) { //法务风险
				mchtOptimize.setAuditRisk(auditRisk);
			}else if("2".equals(gradeType)) { //配合度
				mchtOptimize.setDegreeAdaptability(degreeAdaptability);
			}else { //站内资源等级
				mchtOptimize.setResourceGrade(resourceGrade);
			}
			List<MchtOptimize> mchtOptimizeList = mchtOptimizeService.selectByExample(mchtOptimizeExample);
			if(mchtOptimizeList != null && mchtOptimizeList.size() > 0) {
				mchtOptimize.setUpdateBy(Integer.parseInt(staffID));
				mchtOptimize.setUpdateDate(date);
				mchtOptimizeService.updateByExampleSelective(mchtOptimize, mchtOptimizeExample);
			}else {
				mchtOptimize.setMchtId(Integer.parseInt(mchtId));
				mchtOptimize.setCreateBy(Integer.parseInt(staffID));
				mchtOptimize.setCreateDate(date);
				mchtOptimizeService.insertSelective(mchtOptimize);
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
	
	/**
	 * 
	 * @Title mchtOptimizeManager   
	 * @Description TODO(店铺优化列表)   
	 * @author pengl
	 * @date 2018年10月18日 下午2:48:34
	 */
	@RequestMapping(value = "/mcht/mchtOptimizeManager.shtml")
	public ModelAndView mchtOptimizeManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/mchtOptimizeList");
		try {
			String staffID = this.getSessionStaffBean(request).getStaffID();
			m.addObject("mchtTypeList", DataDicUtil.getTableStatus("BU_MCHT_INFO", "MCHT_TYPE"));
			m.addObject("gradeList", DataDicUtil.getTableStatus("BU_MCHT_INFO", "GRADE"));
			m.addObject("auditRiskList", DataDicUtil.getTableStatus("BU_MCHT_OPTIMIZE", "AUDIT_RISK"));
			m.addObject("degreeAdaptabilityList", DataDicUtil.getTableStatus("BU_MCHT_OPTIMIZE", "DEGREE_ADAPTABILITY"));
			m.addObject("resourceGradeList", DataDicUtil.getTableStatus("BU_MCHT_OPTIMIZE", "RESOURCE_GRADE"));
			SysStaffProductTypeCustomExample staffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
			staffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0")
				.andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(staffProductTypeCustomExample);
			m.addObject("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
			//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
			String isManagement = this.getSessionStaffBean(request).getIsManagement();
			m.addObject("isManagement", isManagement);
			m.addObject("staffID", staffID);
			SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
			SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
			sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
			List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
			m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);
			
			boolean hasAuth = false;
			PlatformContactExample e = new PlatformContactExample();
			List<String> contactTypeList = new ArrayList<String>();
			contactTypeList.add("1");// 招商
			contactTypeList.add("7");// 法务
			e.createCriteria()
					.andDelFlagEqualTo("0")
					.andStatusEqualTo("0")
					.andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()))
					.andContactTypeIn(contactTypeList);
			List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
			if (platformContactList.size() > 0) {
				hasAuth = true;
			}
			m.addObject("hasAuth", hasAuth);
			
			//本对接人类型
			PlatformContactExample pContactExample = new PlatformContactExample();
			PlatformContactExample.Criteria pContactCriteria = pContactExample.createCriteria();
			pContactCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
			pContactExample.setOrderByClause(" id desc");
			pContactExample.setLimitSize(1);
			List<PlatformContact> pContactList = platformContactService.selectByExample(pContactExample);
			if(pContactList != null && pContactList.size() > 0) {
				request.getSession().setAttribute("contactType", pContactList.get(0).getContactType());
			}else {
				request.getSession().setAttribute("contactType", "000");
			}
			//角色控制
			SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo(77).andStaffIdEqualTo(Integer.parseInt(staffID));
			List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
			if(sysStaffRoleList != null && sysStaffRoleList.size() > 0) {
				request.getSession().setAttribute("sysStaffRole", "77");
			}else {
				request.getSession().setAttribute("sysStaffRole", "000");
			}
			SysStaffRoleExample example = new SysStaffRoleExample();
			example.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(87);
			List<SysStaffRole> sysStaffRoles = sysStaffRoleService.selectByExample(example);
			if(sysStaffRoles!=null && sysStaffRoles.size()>0){
				m.addObject("role87", sysStaffRoles.get(0).getRoleId());
			}
			m.addObject("settledType", request.getParameter("settledType"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	/**
	 * 
	 * @Title mchtOptimizeList   
	 * @Description TODO(店铺优化列表)   
	 * @author pengl
	 * @date 2018年10月18日 下午3:30:32
	 */
	@RequestMapping(value = "/mcht/mchtOptimizeList.shtml")
	@ResponseBody
	public Map<String, Object> mchtOptimizeList(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			if (!StringUtil.isEmpty(request.getParameter("endYear"))
					|| !StringUtil.isEmpty(request.getParameter("endMonth"))) {
				paramMap.put("orderByClause", "t.agreement_end_date desc, t.id desc");
			} else {
				paramMap.put("orderByClause", "t.cooperate_begin_date desc, t.id desc");
			}
			// 当主营类目、对接人为空时，限制搜索范围为： 本人负责的类目 与 本人对接的商家 并集
			if(StringUtil.isEmpty(request.getParameter("productTypeId")) && StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				paramMap.put("productTypeContactIsNull", this.getSessionStaffBean(request).getStaffID());
			}
			// 查看销售时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			/*paramMap.put("periodDateEnd", sdf.format(date));*/
			/*if("1".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDateBegin", DateUtil.getDate(-7)+" 00:00:00");
			}else if("2".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDateBegin", DateUtil.getDate(-30)+" 00:00:00");
			}else if("3".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDateBegin", DateUtil.getDate(-90)+" 00:00:00");
			}else {
				paramMap.put("periodDateBegin", paramMap.get("periodDateBegin")+" 00:00:00");
				paramMap.put("periodDateEnd", paramMap.get("periodDateEnd")+" 23:59:59");
			}*/
			if ("1".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDate",'1');
			}else if ("2".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDate",'2');
			}else if ("3".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDate",'3');
			}
			if (!StringUtil.isEmpty(request.getParameter("cooperateBeginDateBegin"))){
				paramMap.put("cooperateBeginDateBegin", request.getParameter("cooperateBeginDateBegin")+" 00:00:00");
			}
			if (!StringUtil.isEmpty(request.getParameter("cooperateBeginDateEnd"))){
				paramMap.put("cooperateBeginDateEnd", request.getParameter("cooperateBeginDateEnd")+" 23:59:59");
			}
			if (!StringUtil.isEmpty(request.getParameter("closeDateBegin"))){
				paramMap.put("closeDateBegin", request.getParameter("closeDateBegin")+" 00:00:00");
			}
			if (!StringUtil.isEmpty(request.getParameter("closeDateEnd"))){
				paramMap.put("closeDateEnd", request.getParameter("closeDateEnd")+" 23:59:59");
			}
			paramMap.put("MIN_NUM", page.getLimitStart());
			paramMap.put("MAX_NUM", page.getLimitSize());
			totalCount = mchtInfoService.selectMchtInfoCustomCount(paramMap);
			dataList = mchtInfoService.selectMchtInfoCustomList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateShopStatus   
	 * @Description TODO(商城入口)   
	 * @author pengl
	 * @date 2018年10月18日 下午8:36:49
	 */
	@ResponseBody
	@RequestMapping("/mcht/updateShopStatus.shtml")
	public Map<String, Object> updateShopStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mchtId = request.getParameter("mchtId");
			String shopStatus = request.getParameter("shopStatus");
			if(!StringUtil.isEmpty(mchtId)) {
				MchtInfo mchtInfo = new MchtInfo();
				mchtInfo.setId(Integer.parseInt(mchtId));
				mchtInfo.setShopStatus(shopStatus);
				mchtInfo.setShopStatusDate(date);
				mchtInfo.setUpdateBy(Integer.parseInt(staffID));
				mchtInfo.setUpdateDate(date);
				mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put("code", code);
		resMap.put("msg", msg);
		return resMap; 
	}
	
	/**
	 * 
	 * @Title updateActivityAuthStatus   
	 * @Description TODO(活动入口)   
	 * @author pengl
	 * @date 2018年10月18日 下午8:42:05
	 */
	@ResponseBody
	@RequestMapping("/mcht/updateActivityAuthStatus.shtml")
	public Map<String, Object> updateActivityAuthStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mchtId = request.getParameter("mchtId");
			String activityAuthStatus = request.getParameter("activityAuthStatus");
			if(!StringUtil.isEmpty(mchtId)) {
				MchtInfo mchtInfo = new MchtInfo();
				mchtInfo.setId(Integer.parseInt(mchtId));
				mchtInfo.setActivityAuthStatus(activityAuthStatus);
				mchtInfo.setActivityAuthStatusDate(date);
				mchtInfo.setUpdateBy(Integer.parseInt(staffID));
				mchtInfo.setUpdateDate(date);
				mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put("code", code);
		resMap.put("msg", msg);
		return resMap; 
	}
	
	/**
	 * 
	 * @Title mchtOptimizeExport   
	 * @Description TODO(导出excel)   
	 * @author pengl
	 * @date 2018年10月18日 下午9:47:24
	 */
	@RequestMapping("/mcht/mchtOptimizeExport.shtml")
	public void mchtOptimizeExport(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> paramMap) {
		try {
			if (!StringUtil.isEmpty(request.getParameter("endYear"))
					|| !StringUtil.isEmpty(request.getParameter("endMonth"))) {
				paramMap.put("orderByClause", "t.agreement_end_date desc, t.id desc");
			} else {
				paramMap.put("orderByClause", "t.cooperate_begin_date desc, t.id desc");
			}
			// 当主营类目、对接人为空时，限制搜索范围为： 本人负责的类目 与 本人对接的商家 并集
			if(StringUtil.isEmpty(request.getParameter("productTypeId")) && StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				paramMap.put("productTypeContactIsNull", this.getSessionStaffBean(request).getStaffID());
			}
			// 查看销售时间
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			paramMap.put("periodDateEnd", sdf.format(date));
			if("1".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDateBegin", DateUtil.getDate(-7)+" 00:00:00");
			}else if("2".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDateBegin", DateUtil.getDate(-30)+" 00:00:00");
			}else if("3".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDateBegin", DateUtil.getDate(-90)+" 00:00:00");
			}else {
				paramMap.put("periodDateBegin", paramMap.get("periodDateBegin")+" 00:00:00");
				paramMap.put("periodDateEnd", paramMap.get("periodDateEnd")+" 23:59:59");
			}*/
			if ("1".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDate",'1');
			}else if ("2".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDate",'2');
			}else if ("3".equals(paramMap.get("periodDate"))) {
				paramMap.put("periodDate",'3');
			}
			List<Map<String, Object>> dataList = mchtInfoService.selectMchtInfoCustomList(paramMap);
			String[] titles = {"创建日期", "商家序号", "开通日期", "公司名称", "店铺名称", "主营类目", "品牌", "应缴保证金", "已缴保证金", "使用的快递", "商家联系人信息", 
					"合作状态", "合同有效期", "入驻资质是否完善", "风险等级", "商品数量", "近月特卖数","销售额", "订单量", "退货率", "投诉率", "近期介入率","好评率", "评价平均分", "商家等级", "商城入口", "活动入口", 
					"商家配合度", "招商", "法务", "订单", "运营", "品类负责人", "保证金优化", "毛利率优化", "商品优化", "服务优化", "物流配送", "站内资源等级", "是否站外推广"};
			ExcelBean excelBean = new ExcelBean("店铺优化列表.xls","店铺优化列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			for(Map<String, Object> map : dataList) {
				String createDate = "";
				if(map.get("create_date") != null && !"".equals(map.get("create_date"))) {
					createDate = simpleDateFormat.format(map.get("create_date"));
				}
				String cooperateBeginDate = "";
				if(map.get("cooperate_begin_date") != null && !"".equals(map.get("cooperate_begin_date"))) {
					cooperateBeginDate = simpleDateFormat.format(map.get("cooperate_begin_date"));
				}
				StringBuffer mchtProductBrandName = new StringBuffer();
				if(map.get("mcht_product_brand_name") != null && !"".equals(map.get("mcht_product_brand_name"))) {
					String[] mchtProductBrandNames = map.get("mcht_product_brand_name").toString().split(",");
					for (int i = 0; i < mchtProductBrandNames.length; i++) {
						if(i != 0) {
							mchtProductBrandName.append(",");
						}
						mchtProductBrandName.append(mchtProductBrandNames[i].substring(0, mchtProductBrandNames[i].indexOf("）")+1));
					}
				}
				String agreementEndDate = "";
				if(map.get("agreement_end_date") != null && !"".equals(map.get("agreement_end_date"))) {
					agreementEndDate = simpleDateFormat.format(map.get("agreement_end_date"));
				}
				StringBuffer archiveStatus = new StringBuffer();
				archiveStatus.append("公司资质[");
				archiveStatus.append(map.get("archive_status_desc"));
				archiveStatus.append("],");
				archiveStatus.append("最新合同[");
				archiveStatus.append(map.get("contract_archive_status_desc"));
				archiveStatus.append("]");
				
				    String activityCountDays=null;
					if (map.get("activity_count_7_days")!=null && !"".equals(map.get("activity_count_7_days"))) {
						activityCountDays=map.get("activity_count_7_days").toString();
					}else if (map.get("activity_count_30_days")!=null && !"".equals(map.get("activity_count_30_days"))) {
						activityCountDays=map.get("activity_count_30_days").toString();
					}else if (map.get("activity_count_90_days")!=null && !"".equals(map.get("activity_count_90_days"))) {
						activityCountDays=map.get("activity_count_90_days").toString();
					}
					
					String payAmountSumdays=null;
					if (map.get("pay_amount_sum_7_days")!=null && !"".equals(map.get("pay_amount_sum_7_days"))) {
						payAmountSumdays=map.get("pay_amount_sum_7_days").toString();
					}else if (map.get("pay_amount_sum_30_days")!=null && !"".equals(map.get("pay_amount_sum_30_days"))) {
						payAmountSumdays=map.get("pay_amount_sum_30_days").toString();
					}else if (map.get("pay_amount_sum_90_days")!=null && !"".equals(map.get("pay_amount_sum_90_days"))) {
						payAmountSumdays=map.get("pay_amount_sum_90_days").toString();
					}
					
					String subOrderCountDays=null;
					if (map.get("sub_order_count_7_days")!=null && !"".equals(map.get("sub_order_count_7_days"))) {
						subOrderCountDays=map.get("sub_order_count_7_days").toString();
					}else if (map.get("sub_order_count_30_days")!=null && !"".equals(map.get("sub_order_count_30_days"))) {
						subOrderCountDays=map.get("sub_order_count_30_days").toString();
					}else if (map.get("sub_order_count_90_days")!=null && !"".equals(map.get("sub_order_count_90_days"))) {
						subOrderCountDays=map.get("sub_order_count_90_days").toString();
					}
					
					String returnRateDays=null;
					if (map.get("return_rate_7_days")!=null && !"".equals(map.get("return_rate_7_days"))) {
						returnRateDays=map.get("return_rate_7_days").toString();
					}else if (map.get("return_rate_30_days")!=null && !"".equals(map.get("return_rate_30_days"))) {
						returnRateDays=map.get("return_rate_30_days").toString();
					}else if (map.get("return_rate_90_days")!=null && !"".equals(map.get("return_rate_90_days"))) {
						returnRateDays=map.get("return_rate_90_days").toString();
					}
					
					String appealRateDays=null;
					if (map.get("appeal_rate_7_days")!=null && !"".equals(map.get("appeal_rate_7_days"))) {
						appealRateDays=map.get("appeal_rate_7_days").toString();
					}else if (map.get("appeal_rate_30_days")!=null && !"".equals(map.get("appeal_rate_30_days"))) {
						appealRateDays=map.get("appeal_rate_30_days").toString();
					}else if (map.get("appeal_rate_90_days")!=null && !"".equals(map.get("appeal_rate_90_days"))) {
						appealRateDays=map.get("appeal_rate_90_days").toString();
					}
					
					String interventionRateDays=null;
					if (map.get("intervention_rate_7_days")!=null && !"".equals(map.get("intervention_rate_7_days"))) {
						interventionRateDays=map.get("intervention_rate_7_days").toString();
					}else if (map.get("intervention_rate_30_days")!=null && !"".equals(map.get("intervention_rate_30_days"))) {
						interventionRateDays=map.get("intervention_rate_30_days").toString();
					}else if (map.get("intervention_rate_90_days")!=null && !"".equals(map.get("intervention_rate_90_days"))) {
						interventionRateDays=map.get("intervention_rate_90_days").toString();
					}
					
					
					
				String[] data = {
					createDate,
					map.get("mcht_code")==null?"":map.get("mcht_code").toString(),
					cooperateBeginDate,
					map.get("company_name")==null?"":map.get("company_name").toString(),
					map.get("shop_name")==null?"":map.get("shop_name").toString(),
					map.get("product_type_name")==null?"":map.get("product_type_name").toString(),		
					mchtProductBrandName.toString(),
					map.get("total_amt")==null?"":map.get("total_amt").toString(),
					map.get("pay_amt")==null?"":map.get("pay_amt").toString(),
					map.get("express_names")==null?"":map.get("express_names").toString(),
					map.get("contact_name_mobile")==null?"":map.get("contact_name_mobile").toString(),
					map.get("status_desc")==null?"":map.get("status_desc").toString(),
					agreementEndDate,
					archiveStatus.toString(),
					map.get("audit_risk_desc")==null?"":map.get("audit_risk_desc").toString(),
					map.get("mcht_product_count")==null?"":map.get("mcht_product_count").toString(),
					/*map.get("activity_count")==null?"":map.get("activity_count").toString(),
					map.get("pay_amount_sum")==null?"":map.get("pay_amount_sum").toString(),
					map.get("sub_order_count")==null?"":map.get("sub_order_count").toString(),
					map.get("customer_service_order_rate")==null?"":map.get("customer_service_order_rate").toString(),
					map.get("appeal_order_rate")==null?"":map.get("appeal_order_rate").toString(),
					map.get("intervention_order_rate").toString(),*/
					activityCountDays==null?"":activityCountDays,
					payAmountSumdays==null?"":payAmountSumdays,
					subOrderCountDays==null?"":subOrderCountDays,
					returnRateDays==null?"":returnRateDays,
					appealRateDays==null?"":appealRateDays,
					interventionRateDays==null?"":interventionRateDays,
					map.get("product_applause_rate")==null?"":map.get("product_applause_rate").toString(),
					map.get("product_score_avg")==null?"":map.get("product_score_avg").toString(),
					map.get("grade_desc")==null?"":map.get("grade_desc").toString(),
					map.get("shop_status_desc")==null?"":map.get("shop_status_desc").toString(),
					map.get("activity_auth_status_desc")==null?"":map.get("activity_auth_status_desc").toString(),
					map.get("degree_adaptability_desc")==null?"":map.get("degree_adaptability_desc").toString(),
					map.get("zs_contact_name")==null?"":map.get("zs_contact_name").toString(),
					map.get("fw_contact_name")==null?"":map.get("fw_contact_name").toString(),
					map.get("dd_contact_name")==null?"":map.get("dd_contact_name").toString(),
					map.get("yy_contact_name")==null?"":map.get("yy_contact_name").toString(),
					map.get("principal_staff_name")==null?"":map.get("principal_staff_name").toString(),
					map.get("mcht_optimize_deposit_remarks")==null?"":map.get("mcht_optimize_deposit_remarks").toString(),
					map.get("mcht_optimize_gross_profit_rate_remarks")==null?"":map.get("mcht_optimize_gross_profit_rate_remarks").toString(),
					map.get("mcht_optimize_product_remarks")==null?"":map.get("mcht_optimize_product_remarks").toString(),
					map.get("mcht_optimize_service_remarks")==null?"":map.get("mcht_optimize_service_remarks").toString(),
					map.get("mcht_optimize_wuliu_remarks")==null?"":map.get("mcht_optimize_wuliu_remarks").toString(),
					map.get("resource_grade_desc")==null?"":map.get("resource_grade_desc").toString(),
					map.get("mcht_optimize_spread_remarks")==null?"":map.get("mcht_optimize_spread_remarks").toString()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//修改是否参与特卖页面
	@RequestMapping(value = "/mcht/toEditIsActivity.shtml")
	public String toEditIsActivity(Model model, HttpServletRequest request, Integer mchtProductBrandId) {
		MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mchtProductBrandId);
		model.addAttribute("mchtProductBrandId",mchtProductBrandId);
		model.addAttribute("isActivity",mchtProductBrand.getIsActivity());
		return "/mcht/toEditIsActivity";
	}
	
	//修改是否参与特卖
	@RequestMapping(value = "/mcht/editIsActivity.shtml")
	@ResponseBody
	public Map<String, Object> editIsActivity(Model model, HttpServletRequest request, Integer id, String isActivity) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		MchtProductBrand mchtProductBrand = new MchtProductBrand();
		mchtProductBrand.setId(id);
		mchtProductBrand.setIsActivity(isActivity);
		mchtProductBrand.setUpdateDate(new Date());
		mchtProductBrand.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
		return resMap;
	}
	
	//商家银行帐号批量审核提交
	@RequestMapping(value = "/mcht/editmchtFinanceInfo.shtml")
	@ResponseBody
	public Map<String, Object> editmchtFinanceInfo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
		
			Integer auditBy = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			
			PlatformContactExample platformContactExample = new PlatformContactExample();
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(auditBy)).andContactTypeEqualTo("5");
			List<PlatformContact> platformContacts = platformContactService.selectByExample(platformContactExample);
			if (platformContacts == null || platformContacts.size() == 0) {
				throw new ArgException("您不是财务对接人，不能审核");
			}
			
			String[] split = request.getParameter("mchtId").split(",");
			for (int i = 0; i < split.length; i++) {
				 String mchtId = split[i];
				 
					MchtPlatformContactExample mchtPlatformContactExample = new MchtPlatformContactExample();
					mchtPlatformContactExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(Integer.valueOf(mchtId)).andStatusEqualTo("1");
					List<MchtPlatformContactCustom> mchtPlatformContacts = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactExample);
					for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContacts) {
						 if (!mchtPlatformContactCustom.getPlatformContactId().equals(platformContacts.get(0).getId())) {
							 throw new ArgException("审核内容包含非本人商家对接人");
						}
					}		  
				 
				MchtBankAccountExample mchtBankAccountExample=new MchtBankAccountExample();
				mchtBankAccountExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(Integer.valueOf(mchtId));
				List<MchtBankAccount> mchtBankAccount=mchtBankAccountService.selectByExample(mchtBankAccountExample);
				for (MchtBankAccount mchtBankAccount2 : mchtBankAccount) {						
					mchtBankAccount2.setStatus("2");
					mchtBankAccount2.setUpdateBy(auditBy);
					mchtBankAccount2.setUpdateDate(new Date());
					mchtBankAccountService.updateByPrimaryKeySelective(mchtBankAccount2);
					
					   if("2".equals(mchtBankAccount2.getStatus())){
						MchtBankAccountHis mchtBankAccountHis=new MchtBankAccountHis();
						BeanUtils.copyProperties(mchtBankAccount2, mchtBankAccountHis);
						mchtBankAccountHis.setId(null);
						mchtBankAccountHisService.insertSelective(mchtBankAccountHis);
						
						
						MchtInfoChangeLog micl = new MchtInfoChangeLog();
						micl.setMchtId(mchtBankAccount2.getMchtId());
						micl.setDelFlag("0");
						micl.setCreateDate(new Date());
						micl.setCreateBy(auditBy);
						micl.setLogType("商家银行账号审核");
						micl.setLogName(mchtBankAccount2.getAccNumber());
						micl.setBeforeChange("待审");
						if("2".equals(mchtBankAccount2.getStatus())){
							micl.setAfterChange("通过");
						}							
						mchtInfoChangeLogService.insertSelective(micl);
						
						
					}
					
				 }
				
				
			 }
			
			
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//编辑公司类型
	@RequestMapping(value = "/mcht/toEditCompanyType.shtml")
	public ModelAndView toEditCompanyType(HttpServletRequest request) {
		String rePage = "/mcht/submitEnter/toEditCompanyType";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(id);
		resMap.put("companyType", mchtInfo.getCompanyType());
		resMap.put("id", id);
		resMap.put("settledType", mchtInfo.getSettledType());
		return new ModelAndView(rePage, resMap);
	}
	
	
	    //编辑公司住所
		@RequestMapping(value = "/mcht/toEditcompanyAddress.shtml")
		public ModelAndView toEditcompanyAddress(HttpServletRequest request) {
			String rePage = "/mcht/submitEnter/toEditCompanyAddress";
			Map<String, Object> resMap = new HashMap<String, Object>();
			Integer id = Integer.parseInt(request.getParameter("id"));
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(id);
			resMap.put("companyAddress", mchtInfo.getCompanyAddress());
			resMap.put("id", id);
			return new ModelAndView(rePage, resMap);
		}
	
	//保存公司类型
	@RequestMapping(value = "/mcht/saveCompanyType.shtml")
	@ResponseBody
	public Map<String, Object> saveCompanyType(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String companyType = request.getParameter("companyType");
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			mi.setCompanyType(companyType);
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//编辑住所地址 
	@RequestMapping(value = "/mcht/toEditCompanyAddress.shtml")
	public ModelAndView toEditCompanyAddress(HttpServletRequest request) {
		String rePage = "/mcht/submitEnter/toEditCompanyAddress";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(id);
		resMap.put("companyAddress", mchtInfo.getCompanyAddress());
		resMap.put("id", id);
		resMap.put("settledType", mchtInfo.getSettledType());
		return new ModelAndView(rePage, resMap);
	}
	
	//保存住所地址
	@RequestMapping(value = "/mcht/saveCompanyAddress.shtml")
	@ResponseBody
	public Map<String, Object> saveCompanyAddress(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String companyAddress = request.getParameter("companyAddress");
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			mi.setCompanyAddress(companyAddress);
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//编辑发照时间
	@RequestMapping(value = "/mcht/toEditFoundedDate.shtml")
	public ModelAndView toEditFoundedDate(HttpServletRequest request) {
		String rePage = "/mcht/submitEnter/toEditFoundedDate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(id);
		resMap.put("foundedDate", mchtInfo.getFoundedDate());
		resMap.put("id", id);
		resMap.put("settledType", mchtInfo.getSettledType());
		return new ModelAndView(rePage, resMap);
	}
	
	//保存发照时间
	@RequestMapping(value = "/mcht/saveFoundedDate.shtml")
	@ResponseBody
	public Map<String, Object> saveFoundedDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String foundedDate = request.getParameter("foundedDate");
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mi.setFoundedDate(sdf.parse(foundedDate+" 00:00:00"));
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//编辑有效期
	@RequestMapping(value = "/mcht/toEditYearlyInspectionDate.shtml")
	public ModelAndView toEditYearlyInspectionDate(HttpServletRequest request) {
		String rePage = "/mcht/submitEnter/toEditYearlyInspectionDate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(id);
		resMap.put("yearlyInspectionDate", mchtInfo.getYearlyInspectionDate());
		resMap.put("id", id);
		return new ModelAndView(rePage, resMap);
	}
	
	//保存有效期
	@RequestMapping(value = "/mcht/saveYearlyInspectionDate.shtml")
	@ResponseBody
	public Map<String, Object> saveYearlyInspectionDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String yearlyInspectionDate = request.getParameter("yearlyInspectionDate");
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mi.setYearlyInspectionDate(sdf.parse(yearlyInspectionDate+" 00:00:00"));
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//编辑法人身份证有效期
	@RequestMapping(value = "/mcht/toEditCorporationIdcardDate.shtml")
	public ModelAndView toEditCorporationIdcardDate(HttpServletRequest request) {
		String rePage = "/mcht/submitEnter/toEditCorporationIdcardDate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(id);
		resMap.put("corporationIdcardDate", mchtInfo.getCorporationIdcardDate());
		resMap.put("id", id);
		resMap.put("settledType", mchtInfo.getSettledType());
		return new ModelAndView(rePage, resMap);
	}
	
	//保存法人身份证有效期
	@RequestMapping(value = "/mcht/saveCorporationIdcardDate.shtml")
	@ResponseBody
	public Map<String, Object> saveCorporationIdcardDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String corporationIdcardDate = request.getParameter("corporationIdcardDate");
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mi.setCorporationIdcardDate(sdf.parse(corporationIdcardDate+" 00:00:00"));
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//编辑经营范围
	@RequestMapping(value = "/mcht/toEditScopeOfBusiness.shtml")
	public ModelAndView toEdit(HttpServletRequest request) {
		String rePage = "/mcht/submitEnter/toEditScopeOfBusiness";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(id);
		resMap.put("scopeOfBusiness", mchtInfo.getScopeOfBusiness());
		resMap.put("id", id);
		return new ModelAndView(rePage, resMap);
	}
		
	//保存经营范围
	@RequestMapping(value = "/mcht/saveScopeOfBusiness.shtml")
	@ResponseBody
	public Map<String, Object> saveScopeOfBusiness(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String scopeOfBusiness = request.getParameter("scopeOfBusiness");
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			mi.setScopeOfBusiness(scopeOfBusiness);
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//编辑公司类型
	@RequestMapping(value = "/mcht/mchtInfoChg/toEditChgCompanyType.shtml")
	public ModelAndView toEditChgCompanyType(HttpServletRequest request) {
		String rePage = "/mcht/mchtInfoChg/toEditChgCompanyType";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfoChgCustom mchtInfoChg = mchtInfoChgService.selectMchtInfoChgCustomByPrimaryKey(id);
		resMap.put("companyType", mchtInfoChg.getCompanyType());
		resMap.put("id", id);
		resMap.put("settledType", mchtInfoChg.getSettledType());
		return new ModelAndView(rePage, resMap);
	}
		
	//保存公司类型
	@RequestMapping(value = "/mcht/mchtInfoChg/saveCompanyType.shtml")
	@ResponseBody
	public Map<String, Object> saveChgCompanyType(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String companyType = request.getParameter("companyType");
			MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(id);
			mchtInfoChg.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setCompanyType(companyType);
			mchtInfoChgService.updateByPrimaryKey(mchtInfoChg);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
		
	//编辑公司住所
	@RequestMapping(value = "/mcht/mchtInfoChg/toEditChgCompanyAddress.shtml")
	public ModelAndView toEditChgCompanyAddress(HttpServletRequest request) {
		String rePage = "/mcht/mchtInfoChg/toEditChgCompanyAddress";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfoChgCustom mchtInfoChg = mchtInfoChgService.selectMchtInfoChgCustomByPrimaryKey(id);
		resMap.put("companyAddress", mchtInfoChg.getCompanyAddress());
		resMap.put("id", id);
		resMap.put("settledType", mchtInfoChg.getSettledType());
		return new ModelAndView(rePage, resMap);
	}
	
	//保存公司住所
	@RequestMapping(value = "/mcht/mchtInfoChg/saveChgCompanyAddress.shtml")
	@ResponseBody
	public Map<String, Object> saveChgCompanyAddress(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String companyAddress = request.getParameter("companyAddress");
			MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(id);
			mchtInfoChg.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setCompanyAddress(companyAddress);
			mchtInfoChgService.updateByPrimaryKey(mchtInfoChg);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//编辑有效期
	@RequestMapping(value = "/mcht/mchtInfoChg/toEditYearlyInspectionDate.shtml")
	public ModelAndView toEditChgYearlyInspectionDate(HttpServletRequest request) {
		String rePage = "/mcht/mchtInfoChg/toEditYearlyInspectionDate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfoChgCustom mchtInfoChg = mchtInfoChgService.selectMchtInfoChgCustomByPrimaryKey(id);
		resMap.put("yearlyInspectionDate", mchtInfoChg.getYearlyInspectionDate());
		resMap.put("id", id);
		resMap.put("settledType", mchtInfoChg.getSettledType());
		return new ModelAndView(rePage, resMap);
	}
		
	//保存有效期
	@RequestMapping(value = "/mcht/mchtInfoChg/saveYearlyInspectionDate.shtml")
	@ResponseBody
	public Map<String, Object> saveChgYearlyInspectionDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String yearlyInspectionDate = request.getParameter("yearlyInspectionDate");
			MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(id);
			mchtInfoChg.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfoChg.setUpdateDate(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mchtInfoChg.setYearlyInspectionDate(sdf.parse(yearlyInspectionDate+" 00:00:00"));
			mchtInfoChgService.updateByPrimaryKey(mchtInfoChg);
			
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtInfoChg.getMchtId());
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			mi.setYearlyInspectionDate(sdf.parse(yearlyInspectionDate+" 00:00:00"));
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
		
	//编辑经营范围
	@RequestMapping(value = "/mcht/mchtInfoChg/toEditScopeOfBusiness.shtml")
	public ModelAndView toEditChgScopeOfBusiness(HttpServletRequest request) {
		String rePage = "/mcht/mchtInfoChg/toEditScopeOfBusiness";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfoChgCustom mchtInfoChg = mchtInfoChgService.selectMchtInfoChgCustomByPrimaryKey(id);
		resMap.put("scopeOfBusiness", mchtInfoChg.getScopeOfBusiness());
		resMap.put("id", id);
		resMap.put("settledType", mchtInfoChg.getSettledType());
		return new ModelAndView(rePage, resMap);
	}
			
	//保存经营范围
	@RequestMapping(value = "/mcht/mchtInfoChg/saveScopeOfBusiness.shtml")
	@ResponseBody
	public Map<String, Object> saveChgScopeOfBusiness(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String scopeOfBusiness = request.getParameter("scopeOfBusiness");
			MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(id);
			mchtInfoChg.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setScopeOfBusiness(scopeOfBusiness);
			mchtInfoChgService.updateByPrimaryKey(mchtInfoChg);
			
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtInfoChg.getMchtId());
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			mi.setScopeOfBusiness(scopeOfBusiness);
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//编辑法人身份证有效期
	@RequestMapping(value = "/mcht/mchtInfoChg/toEditChgCorporationIdcardDate.shtml")
	public ModelAndView toEditChgCorporationIdcardDate(HttpServletRequest request) {
		String rePage = "/mcht/mchtInfoChg/toEditChgCorporationIdcardDate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfoChgCustom mchtInfoChg = mchtInfoChgService.selectMchtInfoChgCustomByPrimaryKey(id);
		resMap.put("corporationIdcardDate", mchtInfoChg.getCorporationIdcardDate());
		resMap.put("id", id);
		resMap.put("settledType", mchtInfoChg.getSettledType());
		return new ModelAndView(rePage, resMap);
	}
	
	//保存法人身份证有效期
	@RequestMapping(value = "/mcht/mchtInfoChg/saveChgCorporationIdcardDate.shtml")
	@ResponseBody
	public Map<String, Object> saveChgCorporationIdcardDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String corporationIdcardDate = request.getParameter("corporationIdcardDate");
			MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(id);
			mchtInfoChg.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfoChg.setUpdateDate(new Date());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			mchtInfoChg.setCorporationIdcardDate(sf.parse(corporationIdcardDate));
			mchtInfoChgService.updateByPrimaryKey(mchtInfoChg);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//编辑主要经营品牌类型
	@RequestMapping(value = "/mcht/mchtInfoChg/toEditChgBrandType.shtml")
	@ResponseBody
	public ModelAndView toEditChgBrandType(HttpServletRequest request) {
		MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mchtInfoChg", mchtInfoChg);
		return new ModelAndView("mcht/mchtInfoChg/toEditChgBrandType",map);
	}
		
	//保存主要经营品牌类型
	@RequestMapping(value = "/mcht/mchtInfoChg/saveChgBrandType.shtml")
	@ResponseBody
	public Map<String, Object> saveChgBrandType(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "修改成功");
		try{
		    MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		    mchtInfoChg.setBrandType(request.getParameter("brandType"));
		    mchtInfoChg.setBrandTypeDesc(request.getParameter("brandTypeDesc"));
		    mchtInfoChgService.updateByPrimaryKeySelective(mchtInfoChg);
		    return resMap;
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg","修改失败");
			e.printStackTrace();
			return resMap;
		}
	}
		
	//编辑发照时间
	@RequestMapping(value = "/mcht/mchtInfoChg/toEditChgFoundedDate.shtml")
	public ModelAndView toEditChgFoundedDate(HttpServletRequest request) {
		String rePage = "/mcht/mchtInfoChg/toEditChgFoundedDate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtInfoChgCustom mchtInfoChg = mchtInfoChgService.selectMchtInfoChgCustomByPrimaryKey(id);
		resMap.put("foundedDate", mchtInfoChg.getFoundedDate());
		resMap.put("id", id);
		resMap.put("settledType", mchtInfoChg.getSettledType());
		return new ModelAndView(rePage, resMap);
	}
	
	//保存发照时间
	@RequestMapping(value = "/mcht/mchtInfoChg/saveChgFoundedDate.shtml")
	@ResponseBody
	public Map<String, Object> saveChgFoundedDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String foundedDate = request.getParameter("foundedDate");
			MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(id);
			mchtInfoChg.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfoChg.setUpdateDate(new Date());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			mchtInfoChg.setFoundedDate(sf.parse(foundedDate));
			mchtInfoChgService.updateByPrimaryKey(mchtInfoChg);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName expiredIndex
	 * @Description TODO(公司资质即将到期页面)
	 * @author chengh
	 * @date 2019年7月19日 下午2:50:28
	 */
	@RequestMapping(value = "/mcht/mchtDelay/soonDelayIndex.shtml")
	public ModelAndView expiredIndex(HttpServletRequest request) {
		String rtPage = "/mcht/mchtDelay/mchtSoonDelayIndex";
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
	 * @MethodName mchtSoonDelaylist
	 * @Description TODO(公司资质即将到期页面列表)
	 * @author chengh
	 * @date 2019年7月19日 上午11:39:19
	 */
	@RequestMapping(value = "/mcht/mchtDelay/mchtSoonDelaylist.shtml")
	@ResponseBody
	public Map<String, Object> mchtSoonDelaylist(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount = "0";
		try {
			paramMap = this.setPageParametersLiger(request, paramMap);
			Date limitDate = DateUtil.getDateAfter(new Date(), 30);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//营业执照授权有效期-30天<=当前时间
			paramMap.put("yearlyInspectionDateMoreThans", sdf.format(new Date()));
			paramMap.put("yearlyInspectionDateLessThans", sdf.format(limitDate));
			//法人身份证有效期-30天<=当前时间
			paramMap.put("corporationIdcardDateMoreThans", sdf.format(new Date()));
			paramMap.put("corporationIdcardDateLessThans", sdf.format(limitDate));
			paramMap.put("status", "1");
			paramMap.put("orderByClause", "a.id asc");
			//商家序号
			paramMap.put("mcht_code", request.getParameter("mchtCode"));
			//公司名称
			paramMap.put("company_name", request.getParameter("companyName"));
			//店铺名称
			paramMap.put("shop_name", request.getParameter("shopName"));
			//类目
			paramMap.put("productTypeId", request.getParameter("productTypeId"));
			//对接人
			paramMap.put("platContactStaffId", request.getParameter("platContactStaffId"));
			totalCount = mchtInfoService.queryMchtCount(paramMap);
			paramMap.put("MIN_NUM", page.getLimitStart());
			paramMap.put("MAX_NUM", page.getLimitSize());
			dataList = mchtInfoService.selectMchtInfoList(paramMap);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
		return resMap;
	}
		
	/**
	 * 
	 * @MethodName mchtCompanyInfoUpdateAuditIndex
	 * @Description TODO(公司资质更新审核页面)
	 * @author chengh
	 * @date 2019年7月19日 下午2:54:17
	 */
	@RequestMapping(value = "/mcht/mchtDelay/mchtCompanyInfoUpdateAuditIndex.shtml")
	public ModelAndView mchtCompanyInfoUpdateAuditIndex(HttpServletRequest request) {
		String rtPage = "/mcht/mchtDelay/mchtCompanyInfoUpdateAuditIndex";
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
	 * @MethodName mchtCompanyInfoUpdateAuditList
	 * @Description TODO(公司资质更新审核页面列表)
	 * @author chengh
	 * @date 2019年7月19日 下午4:44:36
	 */
	@RequestMapping(value = "/mcht/mchtDelay/mchtCompanyInfoUpdateAuditList.shtml")
	@ResponseBody
	public Map<String, Object> mchtCompanyInfoUpdateAuditList(
			HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<MchtInfoChgCustom> mchtInfoChgCustoms = new ArrayList<MchtInfoChgCustom>();
		try {
			MchtInfoChgCustomExample mchtInfoChgCustomExample = new MchtInfoChgCustomExample();
			MchtInfoChgCustomExample.MchtInfoChgCustomCriteria mchtCriteria = mchtInfoChgCustomExample
					.createCriteria();
			mchtCriteria.andDelFlagEqualTo("0");
			mchtCriteria
					.andWhereClause(" EXISTS(select id from bu_mcht_info mi where mi.id=a.mcht_id and mi.del_flag='0' and mi.status in ('1','2'))");
			//审核状态
			if (!StringUtil.isEmpty(request.getParameter("status"))) {
				mchtCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			//商家序列
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtCriteria.andMchtCodeEqualTo(request
						.getParameter("mchtCode"));
			}
			//名称
			if (!StringUtil.isEmpty(request.getParameter("companyOrShop"))) {
				mchtCriteria.andcompanyOrShopNameLike("'%"
						+ request.getParameter("companyOrShop") + "%'");
			}
			//对接人
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				mchtCriteria.andPlatContactStaffIdEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));
			}
			//类目
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtCriteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
			}
			
			totalCount = mchtInfoChgService
					.countMchtInfoChgCustomByExample(mchtInfoChgCustomExample);
			mchtInfoChgCustomExample
					.setOrderByClause(" commit_date desc");
			mchtInfoChgCustomExample.setLimitSize(page.getLimitSize());
			mchtInfoChgCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoChgCustoms = mchtInfoChgService
					.selectMchtInfoChgCustomByExample(mchtInfoChgCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", mchtInfoChgCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	/**
	 * 
	 * @MethodName newMchtDelayListIndex
	 * @Description TODO(公司资质到期页面(新))
	 * @author chengh
	 * @date 2019年7月22日 上午11:45:02
	 */
	@RequestMapping(value = "/mcht/mchtDelay/newMchtDelayIndex.shtml")
	public ModelAndView newMchtDelayListIndex(HttpServletRequest request) {
		String rtPage = "/mcht/mchtDelay/newMchtDelayIndex";
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
	 * @MethodName newMchtDelayList
	 * @Description TODO(公司资质到期数据(新))
	 * @author chengh
	 * @date 2019年7月22日 上午11:45:10
	 */
	@RequestMapping(value = "/mcht/mchtDelay/newMchtDelayList.shtml")
	@ResponseBody
	public Map<String, Object> newMchtDelayList(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount = "0";
		try {
			paramMap = this.setPageParametersLiger(request, paramMap);
			paramMap.put("orderByClause", "a.cooperate_begin_date asc");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//年检有效期限
			paramMap.put("yearlyInspectionDateLessThan", sdf.format(new Date()));
			//商家序号
			paramMap.put("mcht_code", request.getParameter("mchtCode"));
			//名称
			paramMap.put("companyOrShop", request.getParameter("companyOrShop"));
			//类目
			paramMap.put("productTypeId", request.getParameter("productTypeId"));
			//对接人
			paramMap.put("platContactStaffId", request.getParameter("platContactStaffId"));
			totalCount = mchtInfoService.queryMchtCount(paramMap);
			paramMap.put("MIN_NUM", page.getLimitStart());
			paramMap.put("MAX_NUM", page.getLimitSize());
			dataList = mchtInfoService.selectMchtInfoList(paramMap);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName soonStop
	 * @Description TODO(立即暂停)
	 * @author chengh
	 * @date 2019年7月22日 下午3:05:25
	 */
	@RequestMapping(value = "/mcht/mchtDelay/soonStop.shtml")
	@ResponseBody
	public Map<String, Object> soonStop(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			MchtInfo mi = new MchtInfo();
			mi.setStatus("2");
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			MchtInfoExample e = new MchtInfoExample();
			e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			mchtInfoService.updateByExampleSelective(mi, e);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 公司资质到期列表
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtDelay/list.shtml")
	public ModelAndView mchtDelayList(HttpServletRequest request) {
		String rtPage = "/mcht/mchtDelay/mchtDelay_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("staffID", this.getSessionStaffBean(request).getStaffID());
		//本对接人类型
		PlatformContactExample pContactExample = new PlatformContactExample();
		PlatformContactExample.Criteria pContactCriteria = pContactExample.createCriteria();
		pContactCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		pContactExample.setOrderByClause(" id desc");
		pContactExample.setLimitSize(1);
		List<PlatformContact> pContactList = platformContactService.selectByExample(pContactExample);
		if(pContactList != null && pContactList.size() > 0) {
			resMap.put("contactType", pContactList.get(0).getContactType());
		}else {
			resMap.put("contactType", "000");
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 公司资质到期列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtDelay/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtDelayData(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount = "0";
		try {
			paramMap = this.setPageParametersLiger(request, paramMap);
			Date limitDate = DateUtil.getDateAfter(new Date(), 35);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			paramMap.put("yearlyInspectionDateLessThan", sdf.format(limitDate));
			paramMap.put("status", "1");
			paramMap.put("orderByClause", "a.yearly_inspection_date asc");
			totalCount = mchtInfoService.queryMchtCount(paramMap);
			paramMap.put("MIN_NUM", page.getLimitStart());
			paramMap.put("MAX_NUM", page.getLimitSize());
			dataList = mchtInfoService.selectMchtInfoList(paramMap);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
		return resMap;
	}
	
	/**
	 * 选择延期时长页面
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtDelay/toDelay.shtml")
	public ModelAndView mchtDelayToDelay(HttpServletRequest request) {
		String rtPage = "mcht/mchtDelay/toDelay";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mchtId", request.getParameter("mchtId"));
		return new ModelAndView(rtPage, resMap);
	}
	
	//保存延期时长
	@RequestMapping(value = "/mcht/mchtDelay/saveDelayDays.shtml")
	@ResponseBody
	public Map<String, Object> saveDelayDays(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			String delayDays = request.getParameter("delayDays");
			MchtInfo mi = new MchtInfo();
			mi.setDelayStatus("1");
			mi.setDelayDays(Integer.parseInt(delayDays));
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
	 * 品牌资质到期列表
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/brandDelay/list.shtml")
	public ModelAndView brandDelayList(HttpServletRequest request) {
		String rtPage = "mcht/brandDelay/brandDelay_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("staffID", this.getSessionStaffBean(request).getStaffID());
		//本对接人类型
		PlatformContactExample pContactExample = new PlatformContactExample();
		PlatformContactExample.Criteria pContactCriteria = pContactExample.createCriteria();
		pContactCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		pContactExample.setOrderByClause(" id desc");
		pContactExample.setLimitSize(1);
		List<PlatformContact> pContactList = platformContactService.selectByExample(pContactExample);
		if(pContactList != null && pContactList.size() > 0) {
			resMap.put("contactType", pContactList.get(0).getContactType());
		}else {
			resMap.put("contactType", "000");
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 公司资质到期列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/brandDelay/data.shtml")
	@ResponseBody
	public Map<String, Object> brandDelayData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtProductBrandCustomExample e  = new MchtProductBrandCustomExample();
			e.setOrderByClause("t.platform_auth_exp_date asc ");
			MchtProductBrandCustomExample.MchtProductBrandCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMchtStatusEqualTo("1");
			c.andStatusEqualTo("1");
			Date limitDate = DateUtil.getDateAfter(new Date(), 35);
			c.andPlatformAuthExpDateLessThan(limitDate);
			String mchtCode = request.getParameter("mchtCode");
			if(!StringUtils.isEmpty(mchtCode)){
				c.andMchtCodeEqualTo(mchtCode);
			}
			String mchtName = request.getParameter("mchtName");
			if(!StringUtils.isEmpty(mchtName)){
				c.andMchtNameLike(mchtName);
			}
			String brandName = request.getParameter("brandName");
			if(!StringUtils.isEmpty(brandName)){
				c.andProductBrandNameEqualTo(brandName);
			}
			String delayStatus = request.getParameter("delayStatus");
			if(!StringUtils.isEmpty(delayStatus)){
				c.andDelayStatusEqualTo(delayStatus);
			}
			totalCount = mchtProductBrandService.countByCustomExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.selectByCustomExample(e);
			resMap.put("Rows", mchtProductBrandCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 选择延期时长页面
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/brandDelay/toDelay.shtml")
	public ModelAndView brandDelayToDelay(HttpServletRequest request) {
		String rtPage = "/mcht/brandDelay/toDelay";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mchtProductBrandId", request.getParameter("mchtProductBrandId"));
		return new ModelAndView(rtPage, resMap);
	}
	
	//保存延期时长
	@RequestMapping(value = "/mcht/brandDelay/saveDelayDays.shtml")
	@ResponseBody
	public Map<String, Object> saveBrandDelayDays(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtProductBrandId = Integer.parseInt(request.getParameter("mchtProductBrandId"));
			String delayDays = request.getParameter("delayDays");
			MchtProductBrand mpb = new MchtProductBrand();
			mpb.setDelayStatus("1");
			mpb.setDelayDays(Integer.parseInt(delayDays));
			mpb.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mpb.setUpdateDate(new Date());
			MchtProductBrandExample e = new MchtProductBrandExample();
			e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtProductBrandId);
			mchtProductBrandService.updateByExampleSelective(mpb, e);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//开放变更申请类型页面
	@RequestMapping(value = "/mcht/cooperationChangeApply/toChangeApply.shtml")
	public ModelAndView toChangeApply(HttpServletRequest request) {
		String rtPage = "/mcht/cooperationChangeApply/toChangeApply";
		Map<String, Object> resMap = new HashMap<String, Object>();
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("mchtInfo", mchtInfo);
		return new ModelAndView(rtPage, resMap);
	}
	
	//保存开放变更申请类型
	@RequestMapping(value = "/mcht/cooperationChangeApply/saveChangeApply.shtml")
	@ResponseBody
	public Map<String, Object> saveChangeApply(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			CooperationChangeApplyExample e = new CooperationChangeApplyExample();
			e.setOrderByClause("id desc");
			e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(id);
			List<CooperationChangeApply> cooperationChangeApplys = cooperationChangeApplyService.selectByExample(e);
			boolean update = false;
			if(cooperationChangeApplys!=null && cooperationChangeApplys.size()>0){
				String archiveStatus = cooperationChangeApplys.get(0).getArchiveStatus();
				if(!StringUtils.isEmpty(archiveStatus) && archiveStatus.equals("1")){//通过已归档的
					update = true;
				}else{
					update = false;
				}
			}else{
				update = true;
			}
			if(!update){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "商家合作变更函还未归档，无法提交。");
			}else{
				String changeApplyType = request.getParameter("changeApplyType");
				String changeEndDate = request.getParameter("changeEndDate");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				MchtInfo mi = new MchtInfo();
				mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mi.setUpdateDate(new Date());
				mi.setChangeApplyType(changeApplyType);
				mi.setChangeEndDate(sdf.parse(changeEndDate+" 23:59:59"));
				MchtInfoExample mie = new MchtInfoExample();
				mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
				mchtInfoService.updateByExampleSelective(mi, mie);
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//招商总审状态页面
	@RequestMapping(value = "/mcht/toEditZsTotalAuditStatus.shtml")
	public ModelAndView toEditZsTotalAuditStatus(HttpServletRequest request) {
		String rtPage = "/mcht/toEditZsTotalAuditStatus";
		Map<String, Object> resMap = new HashMap<String, Object>();
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("mchtInfo", mchtInfo);
		return new ModelAndView(rtPage, resMap);
	}
	
	//保存招商总审状态
	@RequestMapping(value = "/mcht/editZsTotalAuditStatus.shtml")
	@ResponseBody
	public Map<String, Object> editZsTotalAuditStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String zsTotalAuditStatus = request.getParameter("zsTotalAuditStatus");
			String zsTotalAuditRemarks = request.getParameter("zsTotalAuditRemarks");
			MchtInfo mi = new MchtInfo();
			mi.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mi.setUpdateDate(new Date());
			mi.setZsTotalAuditStatus(zsTotalAuditStatus);
			mi.setZsTotalAuditRemarks(zsTotalAuditRemarks);
			mi.setZsTotalAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			MchtInfoExample mie = new MchtInfoExample();
			mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			mchtInfoService.updateByExampleSelective(mi, mie);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	@RequestMapping("/mcht/close/downLoadPDF.shtml")
	public void downLoadPDF(HttpServletRequest request,HttpServletResponse response) throws Exception {
		InputStream stream = MchtController.class.getResourceAsStream("/base_config.properties");
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
		String mchtCloseApplicationId = request.getParameter("mchtCloseApplicationId");
		MchtCloseApplicationCustom mchtCloseApplicationCustom = mchtCloseApplicationService.selectCustomByPrimaryKey(Integer.parseInt(mchtCloseApplicationId));
		String filePath = PDFCreaterMchtCloseApplication.createPDF(mchtCloseApplicationCustom);
		if(!StringUtils.isEmpty(filePath)){
			//如果文件不存在
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
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtCloseApplicationCustom.getMchtId());
		String fileName = mchtInfo.getMchtCode()+"关店记录";
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
	
	//下载PDF
	@RequestMapping(value = "/mcht/close/downLoadPDF2.shtml")
	@ResponseBody
	public Map<String, Object> downLoadPDF2(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			InputStream stream = MchtController.class.getResourceAsStream("/base_config.properties");
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
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "下载失败，请稍后再试");
				return resMap;
			}
			String mchtCloseApplicationId = request.getParameter("mchtCloseApplicationId");
			MchtCloseApplicationCustom mchtCloseApplicationCustom = mchtCloseApplicationService.selectCustomByPrimaryKey(Integer.parseInt(mchtCloseApplicationId));
			String filePath = PDFCreaterMchtCloseApplication.createPDF(mchtCloseApplicationCustom);
			resMap.put("filePath", filePath);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	@RequestMapping(value = "/mcht/mchtLicenseStatusManager.shtml")
	public ModelAndView mchtLicenseStatusManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mcht/mchtLicenseStatus");
		m.addObject("licenseStatusList", DataDicUtil.getStatusList("BU_MCHT_INFO", "LICENSE_STATUS") );
		m.addObject("mchtInfo", mchtInfoService.selectMchtInfoCustomById(Integer.parseInt(request.getParameter("mchtId"))) );
		return m;
	}
	
	@ResponseBody
	@RequestMapping(value = "/mcht/mchtLicenseStatus.shtml")
	public Map<String, Object> mchtLicenseStatus(HttpServletRequest request, MchtInfo mchtInfo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Date date = new Date();
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(StringUtil.isEmpty(mchtInfo.getLicenseIsMust()) ){
				mchtInfo.setLicenseIsMust("0");
			}
			mchtInfo.setUpdateDate(date);
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//资料归档情况--整个品牌已归操作
	@RequestMapping(value = "/mcht/mchtProductBrandArchive.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductBrandArchive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtProductBrandId = Integer.parseInt(request.getParameter("mchtProductBrandId"));
			String archiveStatus = request.getParameter("archiveStatus");
			MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mchtProductBrandId);
			mchtProductBrand.setArchiveStatus(archiveStatus);
			mchtProductBrand.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtProductBrand.setUpdateDate(new Date());
			mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	@RequestMapping(value = "/mcht/toEditDate.shtml")
	public ModelAndView toEditDate(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("mcht/toEditDate");
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		m.addObject("id",id);
		m.addObject("type",type);
		if(type.equals("0")){
			MchtBrandAptitude mchtBrandAptitude = mchtBrandAptitudeService.selectByPrimaryKey(Integer.parseInt(id));
			m.addObject("date",mchtBrandAptitude.getAptitudeExpDate());
		}else if(type.equals("1")){
			MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(Integer.parseInt(id));
			m.addObject("date",mchtProductBrand.getPlatformAuthExpDate());
		}else if(type.equals("2")){
			MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(Integer.parseInt(id));
			m.addObject("date",mchtProductBrand.getInspectionExpDate());
		}
		return m;
	}
	
	@RequestMapping(value = "/mcht/editDate.shtml")
	@ResponseBody
	public Map<String, Object> editDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String type = request.getParameter("type");
			String date = request.getParameter("date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(type.equals("0")){
				MchtBrandAptitude mchtBrandAptitude = mchtBrandAptitudeService.selectByPrimaryKey(id);
				mchtBrandAptitude.setAptitudeExpDate(sdf.parse(date+" 23:59:59"));
				mchtBrandAptitude.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtBrandAptitude.setUpdateDate(new Date());
				mchtBrandAptitudeService.updateByPrimaryKeySelective(mchtBrandAptitude);
			}else if(type.equals("1")){
				MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(id);
				mchtProductBrand.setPlatformAuthExpDate(sdf.parse(date+" 23:59:59"));
				mchtProductBrand.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtProductBrand.setUpdateDate(new Date());
				mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
			}else if(type.equals("2")){
				MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(id);
				mchtProductBrand.setInspectionExpDate(sdf.parse(date+" 23:59:59"));
				mchtProductBrand.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtProductBrand.setUpdateDate(new Date());
				mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 对接人审核页面(法务资质总审=通过后)
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtDockingPersonAudit.shtml")
	public ModelAndView mchtDockingPersonAudit(HttpServletRequest request) {
		String rtPage = "/mcht/mchtDockingPersonAudit";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		//获取一级目录
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();		
		productTypeCriteria.andParentIdEqualTo(1).andDelFlagEqualTo("0");		
    	List<ProductType> dataList = productTypeService.selectByExample(productTypeExample);
		resMap.put("Rows", dataList);
		//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
		String staffID =this.getSessionStaffBean(request).getStaffID();
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		resMap.put("isManagement", isManagement);
		resMap.put("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 对接人审核列表(法务资质总审=通过后)
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtDockingPersonAuditList.shtml")
	@ResponseBody
	public Map<String, Object> mchtDockingPersonAuditList(HttpServletRequest request, Page page) {
		 return mchtContactService.mchtDockingPersonAuditList(request,page);
	}
	
	/**
	 * 法务审核对接人页面(法务资质总审=通过后)
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/legalReview.shtml")
	public ModelAndView legalReview(HttpServletRequest request) {
		String rtPage = "/mcht/legalReview";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String idString = request.getParameter("id");
		MchContactCustom mchContactCustom = new MchContactCustom();
		if(!StringUtils.isEmpty(idString)){
			MchtContactExample example = new MchtContactExample();
			example.createCriteria().andIdEqualTo(Integer.parseInt(idString));
			mchContactCustom = mchtContactService.selectByCustomExample(example).get(0);

			//身份证正反面
			List<Map<String, Object>> idcardImgList = new ArrayList<Map<String, Object>>();
			if(!StringUtils.isEmpty(mchContactCustom.getIdcardImg1())){
				Map<String, Object> pic1 = new HashMap<String, Object>();
				pic1.put("PICTURE_PATH", mchContactCustom.getIdcardImg1());
				idcardImgList.add(pic1);
			}
			if(!StringUtils.isEmpty(mchContactCustom.getIdcardImg2())){
				Map<String, Object> pic2 = new HashMap<String, Object>();
				pic2.put("PICTURE_PATH", mchContactCustom.getIdcardImg2());
				idcardImgList.add(pic2);
			}
			resMap.put("idcardImgList", idcardImgList);
			//手持身份证
			List<Map<String, Object>> handHeldIdCardList = new ArrayList<Map<String, Object>>();
			if(!StringUtils.isEmpty(mchContactCustom.getIdcardImg())){
				Map<String, Object> pic3 = new HashMap<String, Object>();
				pic3.put("PICTURE_PATH", mchContactCustom.getIdcardImg());
				handHeldIdCardList.add(pic3);
			}
			resMap.put("handHeldIdCardList", handHeldIdCardList);
		}
		resMap.put("mchtContact", mchContactCustom);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 *  法务审核对接人
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtContactAudit.shtml")
	@ResponseBody
	public Map<String, Object> mchtContactAudit(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String auditStatus = request.getParameter("auditStatus");
			String innerRemarks = request.getParameter("innerRemarks");
			String rejectReasons = request.getParameter("rejectReasons");
			String idcardImg = request.getParameter("mchtPrincipalidcardImg");
			String mchtIdcardImgs = request.getParameter("mchtIdcardImgs");
			MchtContact mchtContact = new MchtContact();
			if(!StringUtils.isEmpty(id)){
				mchtContact.setId(Integer.parseInt(id));
			}else{
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "系统维护,请稍后重试");
				return resMap;
			}
			//TODO 身份证正反面和手持身份证处理
			if(!StringUtils.isEmpty(mchtIdcardImgs)){
				JSONArray mchtIdcardImgArray = JSONArray.fromObject(mchtIdcardImgs);
				JSONObject firstJo = (JSONObject)mchtIdcardImgArray.get(0);
				mchtContact.setIdcardImg1(firstJo.getString("picPath"));
				if(mchtIdcardImgArray.size()>1){
					JSONObject secondJo = (JSONObject)mchtIdcardImgArray.get(1);
					mchtContact.setIdcardImg2(secondJo.getString("picPath"));
				}else{
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "请上传身份证正面和背面");
					return resMap;
				}
			}
			if(!StringUtils.isEmpty(idcardImg)){
				JSONArray idcardImgArray = JSONArray.fromObject(idcardImg);
				JSONObject idcardImgJson = (JSONObject)idcardImgArray.get(0);
				mchtContact.setIdcardImg(idcardImgJson.getString("picPath"));
			}
			if(!StringUtils.isEmpty(auditStatus)){
				mchtContact.setAuditStatus(auditStatus);
			}
			if(!StringUtils.isEmpty(innerRemarks)){
				mchtContact.setInnerRemarks(innerRemarks);
			}
			if(!StringUtils.isEmpty(rejectReasons)&&Integer.parseInt(auditStatus)==2){
				mchtContact.setRejectReasons(rejectReasons);
			}
			mchtContact.setAuditBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtContactService.updateByPrimaryKeySelective(mchtContact);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 品牌资质即将到期页面
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandAboutToExpire.shtml")
	public ModelAndView mchtBrandAboutToExpire(HttpServletRequest request) {
		String rtPage = "/mcht/mchtBrandAboutToExpire";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		//获取职员对应目录
		String staffID =this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> dataList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
	    resMap.put("Rows", dataList);
	    //对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）	    
	    String isManagement = this.getSessionStaffBean(request).getIsManagement();
	    resMap.put("isManagement", isManagement);
	    resMap.put("staffID", staffID);
	    SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
	    SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
	    sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
	    List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
	    resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 品牌资质即将到期
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandAboutToExpireList.shtml")
	@ResponseBody
	public Map<String, Object> mchtBrandAboutToExpireList(HttpServletRequest request, Page page) {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try{
			MchtProductBrandCustomExample example = new MchtProductBrandCustomExample();
			MchtProductBrandCustomCriteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			Date afterThirtyDays = cal.getTime();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			criteria.andProductBrandAboutToExpire(sf.format(new Date()), sf.format(afterThirtyDays));
			criteria.andMchtStatusEqualTo("1");
			if(!StringUtils.isEmpty(request.getParameter("mchtCode"))){
				criteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtils.isEmpty(request.getParameter("name"))){
				criteria.andNameEqualTo(request.getParameter("name"));
			}
			if(!StringUtils.isEmpty(request.getParameter("productBrandName"))){
				criteria.andProductBrandNameLike("%"+request.getParameter("productBrandName")+"%");
			}
			if(!StringUtils.isEmpty(request.getParameter("productType"))){
				criteria.andProductTypeIdEqualTo(Integer.parseInt(request.getParameter("productType")));
			}
			if(!StringUtils.isEmpty(request.getParameter("platformContact"))){
				criteria.andfwStaffIdEqualTo(request.getParameter("platformContact"));
			}
			int totalCount = mchtProductBrandService.countByCustomExample(example);
			example.setLimitSize(page.getLimitSize());
			example.setLimitStart(page.getLimitStart());
			List<MchtProductBrandCustom> MchtProductBrandCustoms = mchtProductBrandService.selectByCustomExample(example);
			resMap.put("Rows", MchtProductBrandCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 品牌资质已到期页面
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandHaveExpired.shtml")
	public ModelAndView mchtBrandHaveExpired(HttpServletRequest request) {
		String rtPage = "/mcht/mchtBrandHaveExpired";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		//获取职员对应目录
		String staffID =this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> dataList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
	    resMap.put("Rows", dataList);
	    //对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）	    
	    String isManagement = this.getSessionStaffBean(request).getIsManagement();
	    resMap.put("isManagement", isManagement);
	    resMap.put("staffID", staffID);
	    SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
	    SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
	    sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
	    List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
	    resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 品牌资质已到期
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandHaveExpiredList.shtml")
	@ResponseBody
	public Map<String, Object> mchtBrandHaveExpiredList(HttpServletRequest request, Page page) {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try{
			MchtProductBrandCustomExample example = new MchtProductBrandCustomExample();
			MchtProductBrandCustomCriteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			criteria.andProductBrandHaveExpired(sf.format(new Date()));
			criteria.andMchtStatusEqualTo("1");
			example.setOrderByClause("t.id ASC");
			if(!StringUtils.isEmpty(request.getParameter("mchtCode"))){
				criteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtils.isEmpty(request.getParameter("companyName"))){
				criteria.andCompanyNameEqualTo(request.getParameter("companyName"));
			}
			if(!StringUtils.isEmpty(request.getParameter("shopName"))){
				criteria.andshopNameEqualTo(request.getParameter("shopName"));
			}
			if(!StringUtils.isEmpty(request.getParameter("productType"))){
				criteria.andProductTypeIdEqualTo(Integer.parseInt(request.getParameter("productType")));
			}
			if(!StringUtils.isEmpty(request.getParameter("platformContact"))){
				criteria.andfwStaffIdEqualTo(request.getParameter("platformContact"));
			}
			int totalCount = mchtProductBrandService.countByCustomExample(example);
			example.setLimitSize(page.getLimitSize());
			example.setLimitStart(page.getLimitStart());
			List<MchtProductBrandCustom> MchtProductBrandCustoms = mchtProductBrandService.selectByCustomExample(example);
			resMap.put("Rows", MchtProductBrandCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 暂停商品牌的运营状态
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/immediatelySuspend.shtml")
	@ResponseBody
	public Map<String, Object> immediatelySuspend(HttpServletRequest request, Page page) {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try{
			String id = request.getParameter("id");
			MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(Integer.parseInt(id));
			if(mchtProductBrand.getPlatformAuthExpDate().before(new Date()) || mchtProductBrand.getOtherExpDate().before(new Date())){
				mchtProductBrand.setStatus("2");
				mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
			}else {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "操作失败,请获取最新数据");
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	//法务审核线上审核终止协议
	@RequestMapping(value = "/mcht/close/onlineAuditOfForensicAffairsList.shtml")
	public ModelAndView onlineAuditOfForensicAffairs(HttpServletRequest request) {
		String rePage = "/mcht/close/onlineAuditOfForensicAffairsList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("progressStatus", request.getParameter("progressStatus"));
		SysStaffProductTypeExample sspte = new SysStaffProductTypeExample();
		sspte.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SysStaffProductType> sysStaffProductTypes = sysStaffProductTypeService.selectByExample(sspte);
		List<Integer> productTypeIds = new ArrayList<Integer>();
		for(SysStaffProductType sysStaffProductType:sysStaffProductTypes){
			productTypeIds.add(sysStaffProductType.getProductTypeId());
		}
		if(productTypeIds!=null && productTypeIds.size()>0){
			ProductTypeExample pte = new ProductTypeExample();
			pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1").andIdIn(productTypeIds);
			List<ProductType> productTypes = productTypeService.selectByExample(pte);
			resMap.put("productTypes", productTypes);
		}
		
	    String staffID = getSessionStaffBean(request).getStaffID();
	    String isManagement = getSessionStaffBean(request).getIsManagement();
	    resMap.put("isManagement", isManagement);
	    resMap.put("staffID", staffID);
	    SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
	    SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
	    sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
	    List sysStaffInfoCustomList = this.sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
	    resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
	    
	    
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(107).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRoleList!=null && sysStaffRoleList.size()>0){
			resMap.put("role107", true);
		}else{
			resMap.put("role107", false);
		}
		
		
		List<ProductTypeCustom> productTypeListByStaffId = productTypeService.getProductTypeListByStaffId(Integer.parseInt(staffID));
		resMap.put("productTypeListByStaffId", productTypeListByStaffId);
		return new ModelAndView(rePage, resMap);
		
		
	}
	
	//法务审核线上审核终止协议数据
	@RequestMapping(value = "/mcht/close/onlineAuditOfForensicAffairsListData.shtml")
	@ResponseBody
	public Map<String, Object> onlineAuditOfForensicAffairsListData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		String productTypeId = request.getParameter("productTypeId");//主营类目
		String mchtName = request.getParameter("mchtName");//公司名称
		String mchtCode = request.getParameter("mchtCode");//商家序号
		String isManageSelf = request.getParameter("isManageSelf");
		String platContactStaffId = request.getParameter("platContactStaffId");//法务对接人
		String contractAuditStatus = request.getParameter("contractAuditStatus");//合同审核状态
		
		MchtCloseApplicationCustomExample example = new MchtCloseApplicationCustomExample();
		MchtCloseApplicationCustomExample.MchtCloseApplicationCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andProgressStatusEqualTo("5");
		
		if(!StringUtil.isEmpty(productTypeId)){
			criteria.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
		}
		if(!StringUtil.isEmpty(mchtName)){
			criteria.andMchtNameLike(mchtName);
		}
		if(!StringUtil.isEmpty(mchtCode)){
			criteria.andMchtCodeEqualTo(mchtCode);	
		}
		//是否自营
		if(!StringUtil.isEmpty(isManageSelf)){
			criteria.andIsManageSelfEqualTo(isManageSelf);
		}

		if(!StringUtil.isEmpty(contractAuditStatus)){
			criteria.andContractAuditStatusEqualTo(contractAuditStatus);
		}

      if (!StringUtil.isEmpty(platContactStaffId)) {
    	  criteria.andFwPlatformContractIdEqualTo(Integer.parseInt(platContactStaffId));
	      }
		
		totalCount = mchtCloseApplicationService.countByCustomExample(example);
		example.setLimitSize(page.getLimitSize());
		example.setLimitStart(page.getLimitStart());
		List<MchtCloseApplicationCustom> mchtCloseApplicationCustoms = mchtCloseApplicationService.selectByCustomExample(example);
		
		resMap.put("Rows", mchtCloseApplicationCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	//未上传,上传合同页面
	@RequestMapping(value = "/mcht/close/toUploadPic.shtml")
	public String toUploadPic(Model model,HttpServletRequest request) throws ParseException {
		String id= request.getParameter("id");
		MchtCloseApplicationPicExample example = new MchtCloseApplicationPicExample();
		example.createCriteria().andDelFlagEqualTo("0").andMchtCloseApplicationIdEqualTo(Integer.parseInt(id));
		List<MchtCloseApplicationPic> mchtCloseApplicationPics = mchtCloseApplicationPicService.selectByExample(example );
		model.addAttribute("id", id);
		model.addAttribute("mchtCloseApplicationPics", mchtCloseApplicationPics);
		return "/mcht/close/toUploadPic";	
	}
	
	//校验是否是法务及其协助人对接的商家
	@RequestMapping(value = "/mcht/close/checkFwPlatformContact.shtml")
	@ResponseBody
	public Map<String, Object> checkFwPlatformContact(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtCloseId = request.getParameter("mchtCloseId");
			MchtCloseApplication mchtClose = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(mchtCloseId));
			
			Integer mchtId = mchtClose.getMchtId();
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
			mpce.createCriteria().andMchtIdEqualTo(mchtId).andDelFlagEqualTo("0").andStatusEqualTo("1").andPlatformContactIdIn(platformContactIds);
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
	
	//保存未上传终止协议
	@RequestMapping(value = "/mcht/close/contractPicUpload.shtml")
	@ResponseBody
	public Map<String, Object> contractPicUpload(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(id);
		//MchtContract mchtContract = mchtContractService.selectByPrimaryKey(id);
		String contractPics = request.getParameter("contractPics");
		//String isSpecial = request.getParameter("isSpecial");
		mchtCloseApplicationService.mchtClosePicUpload(mchtCloseApplication,contractPics);
		return resMap;
	}
	
	
	/**
	 * 线上终止协议审核
	 */
	@RequestMapping(value = "/mcht/close/waitToAudit.shtml")
	public ModelAndView waitToAudit(HttpServletRequest request) {
		String page = "/mcht/close/waitToAudit";
		Map<String, Object> data = new HashMap<String, Object>();
		String mchtCloseId = request.getParameter("id");
		data.put("mchtCloseId", mchtCloseId);
		MchtCloseApplicationPicExample mcaps = new MchtCloseApplicationPicExample();
		mcaps.createCriteria().andDelFlagEqualTo("0").andMchtCloseApplicationIdEqualTo(Integer.parseInt(mchtCloseId));
		List<MchtCloseApplicationPic> mchtContractPics = mchtCloseApplicationPicService.selectByExample(mcaps);
		data.put("mchtContractPics", mchtContractPics);
		//关店申请涉及自营
		MchtCloseApplicationCustom mchtCloseApplicationCustom = mchtCloseApplicationService.selectCustomByPrimaryKey(Integer.parseInt(mchtCloseId));
		data.put("isNeedUpload", "1");//是否上传标识
		if ("2".equals(mchtCloseApplicationCustom.getMchtType())){
			data.put("mchtTag","POP");
		}else {
			if ("1".equals(mchtCloseApplicationCustom.getIsManageSelf())){
				data.put("mchtTag","自营SPOP");
				String isNeedUpload = mchtCloseApplicationCustom.getIsNeedUpload();//自营SPOP商家可以选择是否需要上传,无需上传,页面不用展示
				if ("0".equals(isNeedUpload)){
					data.put("isNeedUpload", "0");
				}
			}else {
				data.put("mchtTag","SPOP");
			}
		}
		return new ModelAndView(page, data);
	}
	
	//保存线上终止协议审核结果
	@RequestMapping(value = "/mcht/close/waitToAuditCommit.shtml")
	@ResponseBody
	public Map<String, Object> waitToAuditCommit(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer mchtCloseId = Integer.parseInt(request.getParameter("mchtCloseId"));
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(mchtCloseId);
			String contractAuditStatus = request.getParameter("contractAuditStatus");//状态
			String contractAuditRemarks = request.getParameter("contractAuditRemarks");//驳回原因
			String innerRemarks = request.getParameter("innerRemarks");//内部备注
			if(!StringUtil.isEmpty(contractAuditStatus)){
				mchtCloseApplication.setContractAuditStatus(contractAuditStatus);
			}
			if(!StringUtil.isEmpty(contractAuditRemarks)){
				mchtCloseApplication.setContractAuditRemarks(contractAuditRemarks);
			}
			
			if(!StringUtil.isEmpty(innerRemarks)){
				mchtCloseApplication.setInnerRemarks(innerRemarks);
			}
			mchtCloseApplication.setContractAuditBy(staffId);
			
			mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplication);
			
			//发送短信进行通知审核结果
			if("2".equals(mchtCloseApplication.getContractAuditStatus())){//通过
		
					MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtCloseApplication.getMchtId());
					//mchtContractService.renewAuditPass(currentUser.getStaffID(), model.getId(), param.getRemarks());
					// 发送短信给商家端主账号	
					String mobile1 = mchtUserService.selectMobileByMchtId(mchtCloseApplication.getMchtId());
					String content1 = "【醒购】您【" + mchtInfo.getMchtCode() + "】的终止合同线上审核已通过，请及时登录平台查看寄件内容并寄往平台。";
					SmsUtil.send(mobile1, content1, "4");
					// 发送短信给商家店铺负责人
			        String mobile2 = mchtUserService.selectMchtContactMobileByMchtId(mchtCloseApplication.getMchtId(), 1);
			        if(!StringUtils.isEmpty(mobile2)){
			        	SmsUtil.send(mobile2, content1, "4");
			        }
			        // 发送短信给商家运营专员
			        String mobile3 = mchtUserService.selectMchtContactMobileByMchtId(mchtCloseApplication.getMchtId(), 2);
			        if(!StringUtils.isEmpty(mobile3)){
			        	SmsUtil.send(mobile3, content1, "4");
			        }
					//站内信
					String title = "关于终止合同线上审核通知";
					String content2 = "您的终止合同线上审核已通过，请及时登录平台查看寄件内容并寄往平台。";
					platformMsgService.save(mchtCloseApplication.getMchtId(), title, content2, "TZ");
			
			}else if("3".equals(mchtCloseApplication.getContractAuditStatus())){//驳回
			
					MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtCloseApplication.getMchtId());
					//mchtContractService.renewAuditReject(currentUser.getStaffID(), model.getId(), param.getRemarks());
					// 发送短信给商家端主账号	
					String mobile1 = mchtUserService.selectMobileByMchtId(mchtCloseApplication.getMchtId());
					String content1 = "【醒购】您【" + mchtInfo.getMchtCode() + "】的终止合同线上审核被驳回，请及时登录平台查看原因并重新上传。";
					SmsUtil.send(mobile1, content1, "4");
					// 发送短信给商家店铺负责人
			        String mobile2 = mchtUserService.selectMchtContactMobileByMchtId(mchtCloseApplication.getMchtId(), 1);
			        if(!StringUtils.isEmpty(mobile2)){
			        	SmsUtil.send(mobile2, content1, "4");
			        }
			        // 发送短信给商家运营专员
			        String mobile3 = mchtUserService.selectMchtContactMobileByMchtId(mchtCloseApplication.getMchtId(), 2);
			        if(!StringUtils.isEmpty(mobile3)){
			        	SmsUtil.send(mobile3, content1, "4");
			        }
					//站内信
					String title = "关于终止合同线上审核通知";
					String content2 = "您的终止合同线上审核被驳回，请及时登录平台查看原因并重新上传。";
					platformMsgService.save(mchtCloseApplication.getMchtId(), title, content2, "TZ");
				
				//mchtContractService.auditReject(currentUser.getStaffID(), model.getId(), param.getRemarks());
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}	
	
	//短信通知对象：发送对象：商家主账号的手机号码，店铺总负责人（ID最大且审核状态=通过）、商家的商家运营（ID最大ID最大且审核状态=通过）.
	public void sendMessgaeToMcht(Integer mchtId){	

			MchtInfo mcht = mchtInfoService.selectByPrimaryKey(mchtId);
			// 发送站内信
		    String title = "关于店铺关店通知";
		    String content = "【醒购】您的店铺终止合作协议已生成，请登录平台下载盖章上传至平台。";
		    platformMsgService.save(mcht.getId(), title, content, "TZ");
		    
		    // 发送短信给商家端主账号
		    String mobile = mchtUserService.selectMobileByMchtId(mchtId);
		    content = "【醒购】您的店铺【" +  mcht.getMchtCode() + "】的终止合作协议已生成，请登录平台下载盖章上传至平台。";
		    SmsUtil.send(mobile, content, "4");
	        // 发送短信给商家店铺负责人
	        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtId, 1);
	        if(!StringUtils.isEmpty(mobile)){
	        	SmsUtil.send(mobile, content, "4");
	        }
	        // 发送短信给商家运营专员
	        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtId, 2);
	        if(!StringUtils.isEmpty(mobile)){
	        	SmsUtil.send(mobile, content, "4");
	        }
	          
	 }
	
	/**
	 * 终止协议归档
	 */
	@RequestMapping(value = "/mcht/terminationAgreementList.shtml")
	public ModelAndView terminationAgreementList(HttpServletRequest request) {
		String page = "/mcht/terminationAgreementList";
	
		return new ModelAndView(page);
	}
	
	
	/**
	 * 终止协议归档数据
	 */
	@RequestMapping(value = "/mcht/terminationAgreementListData.shtml")
	@ResponseBody
	public Map<String, Object> terminationAgreementListData(HttpServletRequest request ,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;

		MchtCloseApplicationCustomExample example = new MchtCloseApplicationCustomExample();
		MchtCloseApplicationCustomExample.MchtCloseApplicationCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andContractAuditStatusEqualTo("2");//线上审核通过
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String mchtCode = request.getParameter("mchtCode");//商家序号
		String companyName = request.getParameter("companyName");//公司名称
		String isMchtSend = request.getParameter("isMchtSend");//商家寄件状态
		String contractArchiveStatus = request.getParameter("contractArchiveStatus");//协议归档状态
		
		if(!StringUtil.isEmpty(mchtCode)){
			criteria.andMchtCodeEqualTo(mchtCode);
		}
		
		if(!StringUtil.isEmpty(companyName)){
			criteria.andMchtNameLike(companyName);
		}
		
		if(!StringUtil.isEmpty(isMchtSend)){
			if("0".equals(isMchtSend)){//未寄出  
				criteria.andNotSentOut();
			}else if("1".equals(isMchtSend)){//已寄出
				criteria.andExpressNoIsNotNull();
				criteria.andExpressNoNotEqualTo("");
			}
		}
		
		if(!StringUtil.isEmpty(contractArchiveStatus)){
			if("0".equals(contractArchiveStatus)){//未处理
				criteria.andNotUntreated();
			}else if("1".equals(contractArchiveStatus)){//已通过
				criteria.andContractArchiveStatusEqualTo("1");
			}else if("2".equals(contractArchiveStatus)){//驳回
				criteria.andContractArchiveStatusEqualTo("2");
			}
		}
		
		totalCount = mchtCloseApplicationService.countByCustomExample(example);
		example.setLimitSize(page.getLimitSize());
		example.setLimitStart(page.getLimitStart());
		List<MchtCloseApplicationCustom> mchtCloseApplicationCustoms = mchtCloseApplicationService.selectByCustomExample(example);
		
		resMap.put("Rows", mchtCloseApplicationCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	
	/**
	 *修改法务备注
	 */
	@RequestMapping(value = "/mcht/reviseRemarks.shtml")
	public ModelAndView reviseRemarks(HttpServletRequest request) {
		String page = "/mcht/close/reviseRemarks";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtCloseId = request.getParameter("mchtCloseId");
		if(!StringUtil.isEmpty(mchtCloseId)){
			MchtCloseApplication mca = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(mchtCloseId));
			resMap.put("mca", mca);
		}
		return new ModelAndView(page,resMap);
	}
	
	/**
	 *  保存法务备注
	 */
	@RequestMapping(value = "/mcht/saveReviseRemarks.shtml")
	@ResponseBody
	public Map<String, Object> saveReviseRemarks(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtCloseId = Integer.parseInt(request.getParameter("mchtCloseId"));
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(mchtCloseId);
			String fwRemarks = request.getParameter("fwRemarks");
			if(!StringUtil.isEmpty(fwRemarks)){
				mchtCloseApplication.setFwRemarks(fwRemarks);//法务备注
				mchtCloseApplication.setFwRemarksDate(new Date());//法务最新备注时间
			}
			mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplication);
		} catch (Exception e) {
			// TODO: handle exception
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	
	/**
	 * 归档未处理
	 */
	@RequestMapping(value = "/mcht/processingFiling.shtml")
	public ModelAndView processingFiling(HttpServletRequest request) {
		String page = "/mcht/close/processingFiling";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtCloseId = request.getParameter("mchtCloseId");
		if(!StringUtil.isEmpty(mchtCloseId)){
			MchtCloseApplication mca = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(mchtCloseId));
			resMap.put("mca", mca);
		}
		return new ModelAndView(page,resMap);
	}
	
	
	
	/**
	 *  保存归档处理结果
	 */
	@RequestMapping(value = "/mcht/saveProcessingFiling.shtml")
	@ResponseBody
	public Map<String, Object> saveProcessingFiling(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mchtCloseId = request.getParameter("mchtCloseId");
			String contractArchiveRemarks = request.getParameter("contractArchiveRemarks");//审核备注,驳回原因
			String contractArchiveStatus = request.getParameter("contractArchiveStatus");//审核审核状态
			
			if(!StringUtil.isEmpty(mchtCloseId)){
				MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(mchtCloseId));
				
				if(!StringUtil.isEmpty(contractArchiveRemarks)){
					mchtCloseApplication.setContractArchiveRemarks(contractArchiveRemarks);
				}
				if(!StringUtil.isEmpty(contractArchiveStatus)){
					mchtCloseApplication.setContractArchiveStatus(contractArchiveStatus);
					
					if("2".equals(contractArchiveStatus)){//驳回时,清空快递单号
						mchtCloseApplication.setExpressNo("");
					}
				}
				mchtCloseApplication.setContractArchiveBy(Integer.parseInt(staffID));
				mchtCloseApplication.setUpdateDate(new Date());
				
				mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplication);
				
				//驳回时发送短信
				if("2".equals(contractArchiveStatus)){
					Integer mchtId = mchtCloseApplication.getMchtId();
					MchtInfo mcht = mchtInfoService.selectByPrimaryKey(mchtId);
					// 发送站内信
				    String title = "关于店铺关店通知";
				    String content = "【醒购】您的店铺合作协议已生成，请登录平台下载盖章上传至平台。";
				    platformMsgService.save(mcht.getId(), title, content, "TZ");
				    
				    // 发送短信给商家端主账号
				    String mobile = mchtUserService.selectMobileByMchtId(mchtId);
				    content = "【醒购】您的店铺【" +  mcht.getMchtCode() + "】的【公司资质】已审核通过，请尽快将资质文件寄回平台";
				    SmsUtil.send(mobile, content, "4");
			        // 发送短信给商家店铺负责人
			        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtId, 1);
			        if(!StringUtils.isEmpty(mobile)){
			        	SmsUtil.send(mobile, content, "4");
			        }
			        // 发送短信给商家运营专员
			        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtId, 2);
			        if(!StringUtils.isEmpty(mobile)){
			        	SmsUtil.send(mobile, content, "4");
			        }
				}
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	//下载退保单PDF
	@RequestMapping(value = "/mcht/close/toDownLoadBillsPdf.shtml")
	@ResponseBody
	public Map<String, Object> toDownLoadBillsPdf(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			InputStream stream = MchtController.class.getResourceAsStream("/base_config.properties");
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
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "下载失败，请稍后再试");
				return resMap;
			}
			String mchtCloseId = request.getParameter("mchtCloseId");
			MchtCloseApplicationCustom mchtCloseApplicationCustom = mchtCloseApplicationService.selectCustomByPrimaryKey(Integer.parseInt(mchtCloseId));
			String filePath = mchtCloseApplicationCustom.getBillsPath();
			resMap.put("filePath", filePath);
			
			//修改下载状态
/*			mchtCloseApplicationCustom.setComfirmBillsStatus("1");//已下载
			mchtCloseApplicationCustom.setUpdateDate(new Date());
			mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplicationCustom);*/
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//确认已打单
	@RequestMapping(value = "/mcht/close/confirmOrder.shtml")
	@ResponseBody
	public Map<String, Object> confirmOrder(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
	
			String mchtCloseId = request.getParameter("mchtCloseId");
			MchtCloseApplicationCustom mchtCloseApplicationCustom = mchtCloseApplicationService.selectCustomByPrimaryKey(Integer.parseInt(mchtCloseId));		
			//修改下载状态
			mchtCloseApplicationCustom.setComfirmBillsStatus("1");//确认已下载
			mchtCloseApplicationCustom.setUpdateDate(new Date());
			mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplicationCustom);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	/**
	 * 修改内部备注
	 */
	@RequestMapping(value = "/mcht/close/toFixRemarks.shtml")
	public ModelAndView toFixRemarks(HttpServletRequest request) {
		String page = "/mcht/close/toFixRemarks";
		Map<String, Object> data = new HashMap<String, Object>();
		String mchtCloseId = request.getParameter("mchtCloseId");
		MchtCloseApplication mca = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(mchtCloseId));
		data.put("mca", mca);
		return new ModelAndView(page, data);
	}
	
	
	/**
	 *  保存内部备注
	 */
	@RequestMapping(value = "/mcht/close/saveInnerRemarks.shtml")
	@ResponseBody
	public Map<String, Object> saveInnerRemarks(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer mchtCloseId = Integer.parseInt(request.getParameter("mchtCloseId"));
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(mchtCloseId);
			String innerRemarks = request.getParameter("innerRemarks");
			if(!StringUtil.isEmpty(innerRemarks)){
				mchtCloseApplication.setInnerRemarks(innerRemarks);
			}
			mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplication);
		} catch (Exception e) {
			// TODO: handle exception
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	/**
	 * 
	 * @Title exportCouponExchangeCodeList   
	 * @Description TODO(导出excel)   
	 * @author pengl
	 * @date 2018年2月9日 下午6:36:13
	 */
	@RequestMapping("/mcht/close/exportTerminationAgreement.shtml")
	public void exportTerminationAgreement(HttpServletRequest request, HttpServletResponse response) {
		try {
			MchtCloseApplicationCustomExample example = new MchtCloseApplicationCustomExample();
			MchtCloseApplicationCustomExample.MchtCloseApplicationCriteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andContractAuditStatusEqualTo("2");//线上审核通过
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String mchtCode = request.getParameter("mchtCode");//商家序号
			String companyName = request.getParameter("companyName");//公司名称
			String isMchtSend = request.getParameter("isMchtSend");//商家寄件状态
			String contractArchiveStatus = request.getParameter("contractArchiveStatus");//协议归档状态
			
			if(!StringUtil.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			
			if(!StringUtil.isEmpty(companyName)){
				criteria.andMchtNameLike(companyName);
			}
			
			if(!StringUtil.isEmpty(isMchtSend)){
				if("0".equals(isMchtSend)){//未寄出  
					criteria.andNotSentOut();
				}else if("1".equals(isMchtSend)){//已寄出
					criteria.andExpressNoIsNotNull();
					criteria.andExpressNoNotEqualTo("");
				}
			}
			
			if(!StringUtil.isEmpty(contractArchiveStatus)){
				if("0".equals(contractArchiveStatus)){//未处理
					criteria.andNotUntreated();
				}else if("1".equals(contractArchiveStatus)){//已通过
					criteria.andContractArchiveStatusEqualTo("1");
				}else if("2".equals(contractArchiveStatus)){//驳回
					criteria.andContractArchiveStatusEqualTo("2");
				}
			}
			List<MchtCloseApplicationCustom> dataList = mchtCloseApplicationService.selectByCustomExample(example);
			
			String[] titles = {"开通日期","招商对接人","商家序号","公司名称","店铺名称","商家寄件状态","协议归档状态","法务备注","法务对接人"};
			ExcelBean excelBean = new ExcelBean("导出终止协议归档.xls","导出终止协议归档", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtCloseApplicationCustom mca : dataList) {
				
				String isMchtSendStatus = "";
				if (StringUtil.isEmpty(mca.getExpressNo()) || mca.getExpressNo()==null ) {
					isMchtSendStatus="未寄出";
				}else{
					isMchtSendStatus="已寄出";
				}
				
				String contractArchiveStatusString = "";
				
				if(StringUtil.isEmpty(mca.getExpressNo()) || mca.getExpressNo() == null){
					contractArchiveStatusString="";
				}else{
					if(StringUtil.isEmpty(mca.getContractArchiveStatus()) || mca.getContractArchiveStatus()==null ){
						contractArchiveStatusString="";
					}else {
					    if (mca.getContractArchiveStatus().equals("0") || mca.getContractArchiveStatus() == null || mca.getContractArchiveStatus().equals("") ) {
					    	contractArchiveStatusString="未处理";
						}
					    if (mca.getContractArchiveStatus().equals("1")) {
					    	contractArchiveStatusString="已归档";
						}
					    if (mca.getContractArchiveStatus().equals("2")) {
					    	contractArchiveStatusString="不通过驳回";
						}
					}
				}
				String[] data = {
						mca.getOpeningDate()==null?"":sdf.format(mca.getOpeningDate()),
						mca.getPlatformMerchantsContact(),	
						mca.getMchtCode(),
						mca.getCompanyName(),
						mca.getShopName(),
						isMchtSendStatus,
						contractArchiveStatusString,
						mca.getFwRemarks(),
						mca.getPlatformFawuContact(),
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

