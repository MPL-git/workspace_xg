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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.ext.core.ABaseController;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.SmsUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MchContactCustom;
import com.jf.entity.MchtContractCustom;
import com.jf.entity.MchtContractCustomExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoChg;
import com.jf.entity.MchtInfoChgCustom;
import com.jf.entity.MchtInfoChgCustomExample;
import com.jf.entity.MchtInfoChgExample;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.StateCode;
import com.jf.entity.SysStaffInfoCustom;
import com.jf.entity.SysStaffInfoCustomExample;
import com.jf.entity.SysStaffProductTypeCustom;
import com.jf.entity.SysStaffProductTypeCustomExample;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;
import com.jf.entity.SysStatus;
import com.jf.entity.WithdrawOrderCustom;
import com.jf.entity.WithdrawOrderCustomExample;
import com.jf.entity.WithdrawOrderCustomExample.WithdrawOrderCustomCriteria;
import com.jf.service.MchtContractService;
import com.jf.service.MchtInfoChgService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtPlatformContactService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.MchtUserService;
import com.jf.service.PlatformMsgService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.service.SysStaffRoleService;
import com.jf.vo.Page;

/**
 * @Description:合同及资质情况 控制层
 * @Author: zhen.li
 * @Date: 2019/1/11 10:49
 * 
 */
@Controller
@RequestMapping("/contractCertification")
public class ContractCertificationController extends ABaseController {

	private static final long serialVersionUID = 1L;

	@Resource
	private MchtContractService mchtContractService;
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private SysStaffProductTypeService sysStaffProductTypeService;
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	@Resource
	private MchtInfoChgService mchtInfoChgService;
	@Resource
	private SysStaffRoleService sysStaffRoleService;
	@Resource
	private PlatformMsgService platformMsgService;
	@Resource
	private MchtUserService mchtUserService;
	@Resource
	private MchtPlatformContactService mchtPlatformContactService;
	@Resource 
	private MchtInfoService mchtInfoService;
	/**
	 * 公司资质未完善
	 */
	@RequestMapping(value = "/companyUnfinished/list.shtml")
	public ModelAndView listCompany() {
		return new ModelAndView("/contractCertification/companyUnfinished");
	}
	
	/**
	 * 商家资料归档审核列表数据
	 */
	@RequestMapping(value = "/listArchiveProcessingData.shtml")
	@ResponseBody
	public String listArchiveProcessingData(HttpServletRequest request,Page page) {
		Map<String, Object> data = new HashMap<String, Object>();
		MchtContractCustomExample mcce = new MchtContractCustomExample();
		mcce.setOrderByClause("audit_date asc,id desc");
		MchtContractCustomExample.MchtContractCustomCriteria c = mcce.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtStatusIn("0,1,2");
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
	 * 公司资质未完善 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/companyUnfinished/export.shtml")
	public void export(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			MchtContractCustomExample example = new MchtContractCustomExample();
			example.setOrderByClause("audit_date asc,id desc");
			MchtContractCustomExample.MchtContractCustomCriteria criteria = example.createCriteria();
			criteria.andMchtStatusIn("0,1,2");// 商家合作状态!=关闭
			criteria.andDelFlagEqualTo("0");
			criteria.andCompanyInfoArchiveStatusEqualTo("0");

			String mchtCode = request.getParameter("mchtCode");
			String mchtName = request.getParameter("mchtName");
			if (!StringUtil.isEmpty(mchtCode)) {
				criteria.andMchtCodeByEqualTo(mchtCode);
			}
			if (!StringUtil.isEmpty(mchtName)) {
				criteria.andMchtNameLike(mchtName);
			}

			List<MchtContractCustom> mchtContractCustoms = mchtContractService.selectCustomByExample(example);
			String[] titles = { "开店日期", "招商对接人", "商家序号", "公司名称", "店铺名称",
					"公司资质归档情况", "法务对接人"};
			ExcelBean excelBean = new ExcelBean("导出公司资质未完善.xls", "导出公司资质未完善", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for (MchtContractCustom mchtContractCustom : mchtContractCustoms) {
				String companyInfoArchiveStatus = "";
				if (StringUtils.isEmpty(mchtContractCustom.getCompanyInfoArchiveStatus())
						|| "0".equals(mchtContractCustom.getCompanyInfoArchiveStatus())) {
					companyInfoArchiveStatus = "未齐全";
				}
				String cooperateBeginDate = "";
				if (mchtContractCustom.getCooperateBeginDate() != null) {
					cooperateBeginDate = df.format(mchtContractCustom.getCooperateBeginDate());
				}
				String[] data = { 
						cooperateBeginDate,
						mchtContractCustom.getZsContact(),
						mchtContractCustom.getMchtCode(),
						mchtContractCustom.getCompanyName(),
						mchtContractCustom.getShopName(),
						companyInfoArchiveStatus,
						mchtContractCustom.getFwContact()
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
	 * 品牌资质未完善
	 */
	@RequestMapping(value = "/brandUnfinished/list.shtml")
	public ModelAndView listBrand() {
		Map<String, Object> data = new HashMap<String, Object>();
		List<SysStatus> mchtInfoStatusList = DataDicUtil.getStatusList("BU_MCHT_INFO", "STATUS");
		data.put("mchtInfoStatusList", mchtInfoStatusList);
		return new ModelAndView("/contractCertification/brandUnfinished", data);
	}
	
	/**
	 * 品牌资质未完善 导出excel
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/brandUnfinished/export.shtml")
	public void brandExport(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			MchtContractCustomExample example = new MchtContractCustomExample();
			example.setOrderByClause("audit_date asc,id desc");
			MchtContractCustomExample.MchtContractCustomCriteria criteria = example.createCriteria();
			criteria.andMchtStatusIn("0,1,2");// 商家合作状态!=关闭
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtBrandArchiveStatusEqualTo("0");

			String mchtCode = request.getParameter("mchtCode");
			String mchtName = request.getParameter("mchtName");
			if (!StringUtil.isEmpty(mchtCode)) {
				criteria.andMchtCodeByEqualTo(mchtCode);
			}
			if (!StringUtil.isEmpty(mchtName)) {
				criteria.andMchtNameLike(mchtName);
			}

			List<MchtContractCustom> mchtContractCustoms = mchtContractService.selectCustomByExample(example);
			String[] titles = { "开店日期", "招商对接人", "商家序号", "公司名称", "店铺名称",
					"品牌资质归档情况", "法务对接人"};
			ExcelBean excelBean = new ExcelBean("导出品牌资质未完善.xls", "导出品牌资质未完善", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for (MchtContractCustom mchtContractCustom : mchtContractCustoms) {
				String cooperateBeginDate = "";
				if (mchtContractCustom.getCooperateBeginDate() != null) {
					cooperateBeginDate = df.format(mchtContractCustom.getCooperateBeginDate());
				}
				
				// 获取品牌资质归档情况
				MchtProductBrandExample mpbe = new MchtProductBrandExample();
				mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtContractCustom.getMchtId()).andStatusEqualTo("1");//1.正常
				List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
				String html = "";
				for(MchtProductBrand mchtProductBrand:mchtProductBrands){
					String tmp = "";
					String archiveStatusDesc = "";
					if(!StringUtils.isEmpty(mchtProductBrand.getArchiveStatus()) && mchtProductBrand.getArchiveStatus().equals("3")){
						archiveStatusDesc = "【已齐全】";
					}else{
						archiveStatusDesc = "【未齐全】";
					}
					tmp += archiveStatusDesc+"【"+mchtProductBrand.getProductBrandName()+"】\r\n";
					html += tmp;
				}
				
				String[] data = { 
						cooperateBeginDate,
						mchtContractCustom.getZsContact(),
						mchtContractCustom.getMchtCode(),
						mchtContractCustom.getCompanyName(),
						mchtContractCustom.getShopName(),
						html,
						mchtContractCustom.getFwContact()
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
	 * 合作未归档商家
	 */
	@RequestMapping(value = "/contractUnfinished/list.shtml")
	public ModelAndView listContract() {
		return new ModelAndView("/contractCertification/contractUnfinished");
	}
	
	/**
	 * 合作未归档商家  导出excel
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/contractUnfinished/export.shtml")
	public void contractExport(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			MchtContractCustomExample example = new MchtContractCustomExample();
			example.setOrderByClause("audit_date asc,id desc");
			MchtContractCustomExample.MchtContractCustomCriteria criteria = example.createCriteria();
			criteria.andMchtStatusIn("0,1,2");// 商家合作状态!=关闭
			criteria.andDelFlagEqualTo("0");
			List<String> values = new ArrayList<String>();
			values.add("0");
			values.add("2");
			values.add("4");
			criteria.andArchiveStatusIn(values);

			String mchtCode = request.getParameter("mchtCode");
			String mchtName = request.getParameter("mchtName");
			String contractCode = request.getParameter("contractCode");
			String isMchtSend = request.getParameter("isMchtSend");
			String archiveStatus = request.getParameter("archiveStatus");
			String companyInfoArchiveStatus = request.getParameter("companyInfoArchiveStatus");
			if (!StringUtil.isEmpty(mchtCode)) {
				criteria.andMchtCodeByEqualTo(mchtCode);
			}
			if (!StringUtil.isEmpty(mchtName)) {
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
			if (!StringUtil.isEmpty(companyInfoArchiveStatus)) {
				criteria.andCompanyInfoArchiveStatusEqualTo(companyInfoArchiveStatus);
			}

			List<MchtContractCustom> mchtContractCustoms = mchtContractService
					.selectCustomByExample(example);
			String[] titles = { "开店日期", "招商对接人", "商家序号", "公司名称", "店铺名称",
					"商家寄件状态", "合同归档状态", "合同编号", "法务对接人"};
			ExcelBean excelBean = new ExcelBean("导出合同未归档商家.xls", "导出合同未归档商家", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for (MchtContractCustom mchtContractCustom : mchtContractCustoms) {
				String cooperateBeginDate = "";
				if (mchtContractCustom.getCooperateBeginDate() != null) {
					cooperateBeginDate = df.format(mchtContractCustom.getCooperateBeginDate());
				}
				
				String mchtSend = "未寄出";
				if (!StringUtils.isEmpty(mchtContractCustom.getIsMchtSend())) {
					mchtSend = "0".equals(mchtContractCustom.getIsMchtSend())? "未寄出" : "已寄出";
				}
				
				String[] data = { 
						cooperateBeginDate,
						mchtContractCustom.getZsContact(),
						mchtContractCustom.getMchtCode(),
						mchtContractCustom.getCompanyName(),
						mchtContractCustom.getShopName(),
						mchtSend,
						mchtContractCustom.getArchiveStatusDesc(),
						mchtContractCustom.getContractCode(),
						mchtContractCustom.getFwContact()
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
	 * 资料齐全商家
	 */
	@RequestMapping(value = "/mchtComplete/list.shtml")
	public ModelAndView listMcht() {
		Map<String, Object> data = new HashMap<String, Object>();
		List<SysStatus> mchtInfoStatusList = DataDicUtil.getStatusList("BU_MCHT_INFO", "STATUS");
		data.put("mchtInfoStatusList", mchtInfoStatusList);
		return new ModelAndView("/contractCertification/mchtComplete",data);
	}
	
	/**
	 * 资料齐全商家  导出excel
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mchtComplete/export.shtml")
	public void mchtExport(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			MchtContractCustomExample example = new MchtContractCustomExample();
			example.setOrderByClause("audit_date asc,id desc");
			MchtContractCustomExample.MchtContractCustomCriteria criteria = example.createCriteria();
			criteria.andMchtStatusIn("0,1,2");// 商家合作状态!=关闭
			criteria.andDelFlagEqualTo("0");

			String mchtCode = request.getParameter("mchtCode");
			String mchtName = request.getParameter("mchtName");
			String contractCode = request.getParameter("contractCode");
			if (!StringUtil.isEmpty(mchtCode)) {
				criteria.andMchtCodeByEqualTo(mchtCode);
			}
			if (!StringUtil.isEmpty(mchtName)) {
				criteria.andMchtNameLike(mchtName);
			}
			if(!StringUtil.isEmpty(contractCode)){
				criteria.andContractCodeEqualTo(contractCode);
			}
			
			String companyInfoArchiveStatus = request.getParameter("companyInfoArchiveStatus");
			if(!StringUtils.isEmpty(companyInfoArchiveStatus)){
				criteria.andCompanyInfoArchiveStatusEqualTo(companyInfoArchiveStatus);
			}
			String mchtBrandArchiveStatus = request.getParameter("mchtBrandArchiveStatus");
			if(!StringUtils.isEmpty(mchtBrandArchiveStatus)){
				criteria.andMchtBrandArchiveStatusEqualTo(mchtBrandArchiveStatus);
			}
			String archiveStatus = request.getParameter("archiveStatus");
			if(!StringUtils.isEmpty(archiveStatus)){
				criteria.andArchiveStatusEqualTo(archiveStatus);
			}

			List<MchtContractCustom> mchtContractCustoms = mchtContractService
					.selectCustomByExample(example);
			String[] titles = { "开店日期", "招商对接人", "商家序号", "公司名称", "店铺名称",
					"合同归档状态", "合同编号", "法务对接人"};
			ExcelBean excelBean = new ExcelBean("导出资料齐全商家.xls", "导出资料齐全商家", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for (MchtContractCustom mchtContractCustom : mchtContractCustoms) {
				String cooperateBeginDate = "";
				if (mchtContractCustom.getCooperateBeginDate() != null) {
					cooperateBeginDate = df.format(mchtContractCustom.getCooperateBeginDate());
				}
				
				String[] data = { 
						cooperateBeginDate,
						mchtContractCustom.getZsContact(),
						mchtContractCustom.getMchtCode(),
						mchtContractCustom.getCompanyName(),
						mchtContractCustom.getShopName(),
						mchtContractCustom.getArchiveStatusDesc(),
						mchtContractCustom.getContractCode(),
						mchtContractCustom.getFwContact()
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
	 * @MethodName placeOnFileIndex
	 * @Description TODO(公司资质归档页面)
	 * @author chengh
	 * @date 2019年7月22日 下午5:07:17
	 */
	@RequestMapping(value = "/placeOnFileIndex.shtml")
	public ModelAndView placeOnFileIndex(HttpServletRequest request) {
		String rtPage = "/contractCertification/placeOnFileIndex";
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
		//当前系统用户是不是法务或者admin
		List<Integer> roleIds = new ArrayList<Integer>();
		roleIds.add(1);
		roleIds.add(43);
		SysStaffRoleExample sysStaffRoleExample1 = new SysStaffRoleExample();
		sysStaffRoleExample1.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdIn(roleIds).andStatusEqualTo("A");
		List<SysStaffRole> sysStaffRoleList1 = sysStaffRoleService.selectByExample(sysStaffRoleExample1);
		if(sysStaffRoleList1!=null && sysStaffRoleList1.size()>0){
			resMap.put("role431", true);
		}else{
			resMap.put("role431", false);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @MethodName placeOnFileList
	 * @Description TODO(公司资质归档列表)
	 * @author chengh
	 * @date 2019年7月22日 下午5:07:51
	 */
	@RequestMapping(value = "/placeOnFileList.shtml")
	@ResponseBody
	public Map<String, Object> placeOnFileList(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<MchtInfoChgCustom> mchtInfoChgCustoms = new ArrayList<MchtInfoChgCustom>();
		try {
			MchtInfoChgCustomExample mchtInfoChgCustomExample = new MchtInfoChgCustomExample();
			mchtInfoChgCustomExample.setOrderByClause("id desc");
			mchtInfoChgCustomExample.setOrderByClause(" create_date desc");
			MchtInfoChgCustomExample.MchtInfoChgCustomCriteria mchtCriteria = mchtInfoChgCustomExample
					.createCriteria();
			//更新表必须是审核通过的状态
			mchtCriteria.andStatusEqualTo("3").andDelFlagEqualTo("0");
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
			//公司资质归档状态
			if (!StringUtil.isEmpty(request.getParameter("companyInfoArchiveStatus"))) {
				mchtCriteria.andCompanyInfoArchiveStatusCustomEqualTo(request
						.getParameter("companyInfoArchiveStatus"));
			}
			//类目
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtCriteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
			}
			//归档处理状态	
			if (!StringUtil.isEmpty(request.getParameter("archiveDealStatus"))) {
				if("0".equals(request.getParameter("archiveDealStatus"))){
					mchtCriteria.andArchiveDealStatusEqualToCustom(request.getParameter("archiveDealStatus"));
				}else{
					mchtCriteria.andArchiveDealStatusEqualTo(request.getParameter("archiveDealStatus"));
				}			
			}
			//对接人
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				mchtCriteria.andPlatContactStaffIdEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));
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
	
	@RequestMapping("/exports.shtml")
	public void exports(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		List<MchtInfoChgCustom> mchtInfoChgCustoms = new ArrayList<MchtInfoChgCustom>();
		try {
			MchtInfoChgCustomExample mchtInfoChgCustomExample = new MchtInfoChgCustomExample();
			MchtInfoChgCustomExample.MchtInfoChgCustomCriteria mchtCriteria = mchtInfoChgCustomExample
					.createCriteria();
			//更新表必须是审核通过的状态
			mchtCriteria.andStatusEqualTo("3").andDelFlagEqualTo("0");
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
			//公司资质归档状态
			if (!StringUtil.isEmpty(request.getParameter("companyInfoArchiveStatus"))) {
				mchtCriteria.andCompanyInfoArchiveStatusCustomEqualTo(request
						.getParameter("companyInfoArchiveStatus"));
			}
			//类目
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtCriteria.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
			}
			//归档处理状态	
			if (!StringUtil.isEmpty(request.getParameter("archiveDealStatus"))) {
				mchtCriteria.andArchiveDealStatusEqualTo(request.getParameter("archiveDealStatus"));
			}
			//对接人
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				mchtCriteria.andPlatContactStaffIdEqualTo(Integer.valueOf(request.getParameter("platContactStaffId")));
			}
			mchtInfoChgCustomExample.setOrderByClause(" commit_date desc");
			mchtInfoChgCustoms = mchtInfoChgService.selectMchtInfoChgCustomByExample(mchtInfoChgCustomExample);
			
			SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
			String[] titles = { "开店日期", "招商对接人", "商家序号","公司名称","店铺名称","公司资质归档情况","商家寄件状态","公司归档处理","内部备注","驳回原因","法务对接人"};
			ExcelBean excelBean = new ExcelBean("公司资质归档列表.xls",
					"公司资质归档列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtInfoChgCustom mchtInfoChgCustom:mchtInfoChgCustoms){
				if (org.apache.commons.lang.StringUtils.isBlank(mchtInfoChgCustom.getCompanyInfoArchiveStatus()) || org.apache.commons.lang.StringUtils.equals("0", mchtInfoChgCustom.getCompanyInfoArchiveStatus())){
					mchtInfoChgCustom.setCompanyInfoArchiveStatus("未齐全");
				}else{
					mchtInfoChgCustom.setCompanyInfoArchiveStatus("已归档");
				}
				if (org.apache.commons.lang.StringUtils.isBlank(mchtInfoChgCustom.getIsMchtSend()) || org.apache.commons.lang.StringUtils.equals("0", mchtInfoChgCustom.getIsMchtSend())){
					mchtInfoChgCustom.setIsMchtSend("未寄出");
				}else{
					mchtInfoChgCustom.setIsMchtSend("已寄出");
				}
				if(org.apache.commons.lang.StringUtils.equals(mchtInfoChgCustom.getArchiveDealStatus(), "0")){
					mchtInfoChgCustom.setArchiveDealStatus("未处理");
				}
				if(org.apache.commons.lang.StringUtils.equals(mchtInfoChgCustom.getArchiveDealStatus(), "1")){
					mchtInfoChgCustom.setArchiveDealStatus("通过");
				}
				if(org.apache.commons.lang.StringUtils.equals(mchtInfoChgCustom.getArchiveDealStatus(), "2")){
					mchtInfoChgCustom.setArchiveDealStatus("驳回");
				}
				String[] data = {
						format.format(mchtInfoChgCustom.getCooperateBeginDate()),
						mchtInfoChgCustom.getZsContactName(),
						mchtInfoChgCustom.getMchtCode(),
						mchtInfoChgCustom.getCompanyName(),
						mchtInfoChgCustom.getShopName(),
						mchtInfoChgCustom.getCompanyInfoArchiveStatus(),
						mchtInfoChgCustom.getIsMchtSend(),
						mchtInfoChgCustom.getArchiveDealStatus(),
						mchtInfoChgCustom.getArchiveDealInnerRemarks(),
						mchtInfoChgCustom.getArchiveDealRemarks(),
						mchtInfoChgCustom.getFwContactName(),
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
	 * @MethodName filingProcessing
	 * @Description TODO(处理归档)
	 * @author chengh
	 * @date 2019年7月23日 上午10:30:11
	 */
	@RequestMapping(value = "/filingProcessing.shtml")
	public ModelAndView filingProcessing(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		resMap.put("mchtInfoChg", mchtInfoChg);
		resMap.put("realStatus", mchtInfoChg.getArchiveDealStatus());
		return new ModelAndView("/contractCertification/filingProcessing",resMap);
	}	
	
	
	/**
	 * 
	 * @MethodName auditSubmit
	 * @Description TODO(处理归档审核)
	 * @author chengh
	 * @date 2019年7月23日 上午11:20:56
	 */
	@RequestMapping(value = "/auditSubmit.shtml")
	@ResponseBody
	public Map<String, Object> auditSubmit(HttpServletRequest request,MchtInfoChg mchtInfoChg) {
		Map<String, Object> map = new HashMap<String, Object>();
		String realStatus = request.getParameter("realStatus");
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = "提交成功！";
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtInfoChg.getMchtId());
		try {
			mchtInfoChg.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			if(org.apache.commons.lang.StringUtils.equals(mchtInfoChg.getArchiveDealStatus(), "2")){
				if(org.apache.commons.lang.StringUtils.equals("", realStatus)){
					mchtInfoChgService.updateByPrimaryKeyCustoms(mchtInfoChg);
				}else{
					mchtInfoChgService.updateByPrimaryKeyCustom(mchtInfoChg);
				}
				
				//发送短信
				sendSMS(mchtInfoChg,mchtInfo);
			}else{
				mchtInfoChg.setArchiveDealInnerRemarks(mchtInfoChg.getArchiveDealRemarks());
				mchtInfoChg.setArchiveDealRemarks(null);
				mchtInfoChgService.updateByPrimaryKeySelective(mchtInfoChg);
			}
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
	
	public void sendSMS(MchtInfoChg mchtInfoChg,MchtInfo mchtInfo){
		// 发送站内信
		String title = "关于公司资质归档通知";
		String content = "您的公司资质归档被驳回，请在前往工商主体信息管理中查看驳回原因。";
		platformMsgService.save(mchtInfoChg.getMchtId(), title, content, "TZ");
		    		    	
		// 发送短信给商家端主账号
		String mobile = mchtUserService.selectMobileByMchtId(mchtInfoChg.getMchtId());
		content = "【醒购】您的店铺【" + mchtInfo.getMchtCode() + "】的公司资质归档驳回，请登录平台查看驳回原因。";
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
		content = "【醒购】您对接的【" + mchtInfo.getMchtCode() + "】的公司资质归档驳回，请尽快联系商家将资料寄回平台";
		SmsUtil.send(mobile, content, "4");  
	 }
	
	/**
	 * 
	 * @MethodName modifyArchiveDealInnerRemarks
	 * @Description TODO(修改备注页面)
	 * @author chengh
	 * @date 2019年7月23日 下午4:03:13
	 */
	@RequestMapping(value = "/modifyArchiveDealInnerRemarks.shtml")
	public ModelAndView modifyArchiveDealInnerRemarks(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		resMap.put("mchtInfoChg", mchtInfoChg);
		return new ModelAndView("/contractCertification/modifyArchiveDealInnerRemarks",resMap);
	}
		
	/**
	 * 
	 * @MethodName modify
	 * @Description TODO(修改备注)
	 * @author chengh
	 * @date 2019年7月23日 上午11:20:56
	 */
	@RequestMapping(value = "/modify.shtml")
	@ResponseBody
	public Map<String, Object> modify(HttpServletRequest request,MchtInfoChg mchtInfoChg) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = "提交成功！";
		try {
			mchtInfoChgService.updateByPrimaryKeySelective(mchtInfoChg);
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
}
