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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MchtBrandAptitude;
import com.jf.entity.MchtBrandChg;
import com.jf.entity.MchtBrandChgCustom;
import com.jf.entity.MchtLicenseChg;
import com.jf.entity.MchtLicenseChgCustom;
import com.jf.entity.MchtLicenseChgCustomExample;
import com.jf.entity.MchtLicenseChgExample;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.SysStaffInfoCustom;
import com.jf.entity.SysStaffInfoCustomExample;
import com.jf.entity.SysStaffProductTypeCustom;
import com.jf.entity.SysStaffProductTypeCustomExample;
import com.jf.service.MchtLicenseChgService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.vo.Page;

@Controller
public class MchtLicenseChgController extends BaseController {
	
	@Resource
	private MchtLicenseChgService mchtLicenseChgService;
	
	@Resource
	private SysStaffProductTypeService sysStaffProductTypeService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * 行业经营许可证变更审核列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtLicenseChg/list.shtml")
	public ModelAndView mchtLicenseChgList(HttpServletRequest request) {
		String rtPage = "mchtLicenseChg/mchtLicenseChg_list";
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
	 * 行业经营许可证变更审核列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtLicenseChg/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtLicenseChgData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtLicenseChgCustomExample example = new MchtLicenseChgCustomExample();
			MchtLicenseChgCustomExample.MchtLicenseChgCustomCriteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			String mchtCode = request.getParameter("mchtCode");
			String name = request.getParameter("name");
			String productTypeId = request.getParameter("productTypeId");
			String auditStatus = request.getParameter("auditStatus");
			String platContactStaffId = request.getParameter("platContactStaffId");
			if(!StringUtil.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtil.isEmpty(name)){
				criteria.andNameLike("%"+name+"%");
			}
			if(!StringUtil.isEmpty(productTypeId)){
				criteria.andProductTypeIdTo(Integer.parseInt(productTypeId));
			}
			if(!StringUtil.isEmpty(auditStatus)){
				criteria.andAuditStatusEqualTo(auditStatus);
			}
			if(!StringUtils.isEmpty(platContactStaffId)){
				criteria.andPlatContactStaffIdEqualTo(Integer.parseInt(platContactStaffId));
			}
			totalCount = mchtLicenseChgService.countMchtLicenseChgCustomByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<MchtLicenseChgCustom> mchtLicenseChgCustoms = mchtLicenseChgService.selectMchtLicenseChgCustomByExample(example);
			resMap.put("Rows", mchtLicenseChgCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 审核页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtLicenseChg/toAudit.shtml")
	public ModelAndView toAudit(HttpServletRequest request) {
		String rtPage = "mchtLicenseChg/toAudit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			MchtLicenseChg mchtLicenseChg = mchtLicenseChgService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("mchtLicenseChg", mchtLicenseChg);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/mchtLicenseChg/audit.shtml")
	@ResponseBody
	public Map<String, Object> audit(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String businessLicensePic = request.getParameter("businessLicensePic");
			String auditStatus = request.getParameter("auditStatus");
			String licenseRejectReason = request.getParameter("licenseRejectReason");
			MchtLicenseChg mchtLicenseChg = new MchtLicenseChg();
			mchtLicenseChg.setBusinessLicensePic(businessLicensePic);
			mchtLicenseChg.setAuditStatus(auditStatus);
			mchtLicenseChg.setLicenseRejectReason(licenseRejectReason);
			mchtLicenseChg.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtLicenseChg.setUpdateDate(new Date());
			MchtLicenseChgExample e = new MchtLicenseChgExample();
			e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			if(auditStatus.equals("1")){
				mchtLicenseChgService.update(mchtLicenseChg,e,id);
			}else{
				mchtLicenseChgService.updateByExampleSelective(mchtLicenseChg, e);
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * 行业经营许可证归档审核列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtLicenseChg/archiveList.shtml")
	public ModelAndView archiveList(HttpServletRequest request) {
		String rtPage = "mchtLicenseChg/archiveList";
		Map<String, Object> resMap = new HashMap<String, Object>();  
	    //获取职员对应目录   
		String staffID = this.getSessionStaffBean(request).getStaffID();
		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		sysStaffProductTypeCustomExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffID));
		List<SysStaffProductTypeCustom> sysStaffProductTypeCustomList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
		resMap.put("sysStaffProductTypeCustomList", sysStaffProductTypeCustomList);
		//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
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
	 * 行业经营许可证归档审核列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtLicenseChg/archiveData.shtml")
	@ResponseBody
	public Map<String, Object> archiveData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtLicenseChgCustomExample example = new MchtLicenseChgCustomExample();
			MchtLicenseChgCustomExample.MchtLicenseChgCustomCriteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andAuditStatusEqualTo("1");//审核状态为已通过的
			example.setOrderByClause("id desc");
			String mchtCode = request.getParameter("mchtCode");
			String name = request.getParameter("name");
			String productTypeId = request.getParameter("productTypeId");
			String archiveStatus = request.getParameter("archiveStatus");
			String archiveDealStatus = request.getParameter("archiveDealStatus");
			String platContactStaffId = request.getParameter("platContactStaffId");
			if(!StringUtil.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtil.isEmpty(name)){
				criteria.andNameLike("%"+name+"%");
			}
			if(!StringUtil.isEmpty(productTypeId)){
				criteria.andProductTypeIdTo(Integer.parseInt(productTypeId));
			}
			if(!StringUtil.isEmpty(archiveStatus)){
				if(archiveStatus.equals("1")){
					criteria.andArchiveStatusEqualTo("3");
				}else{
					criteria.andArchiveStatusNotEqualTo("3");
				}
			}
			if(!StringUtil.isEmpty(archiveDealStatus)){
				criteria.andArchiveDealStatusEqualTo(archiveDealStatus);
			}
			if(!StringUtils.isEmpty(platContactStaffId)){
				criteria.andPlatContactStaffIdEqualTo(Integer.parseInt(platContactStaffId));
			}
			totalCount = mchtLicenseChgService.countMchtLicenseChgCustomByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<MchtLicenseChgCustom> mchtLicenseChgCustoms = mchtLicenseChgService.selectMchtLicenseChgCustomByExample(example);
			resMap.put("Rows", mchtLicenseChgCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 行业经营许可证归档审核列表数据导出
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtLicenseChg/archiveDataExport.shtml")
	@ResponseBody
	public void archiveDataExport(HttpServletRequest request,HttpServletResponse response) {
		try{
			MchtLicenseChgCustomExample example = new MchtLicenseChgCustomExample();
			MchtLicenseChgCustomExample.MchtLicenseChgCustomCriteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			String mchtCode = request.getParameter("mchtCode");
			String name = request.getParameter("name");
			String productTypeId = request.getParameter("productTypeId");
			String archiveStatus = request.getParameter("archiveStatus");
			String archiveDealStatus = request.getParameter("archiveDealStatus");
			String platContactStaffId = request.getParameter("platContactStaffId");
			if(!StringUtil.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtil.isEmpty(name)){
				criteria.andNameLike("%"+name+"%");
			}
			if(!StringUtil.isEmpty(productTypeId)){
				criteria.andProductTypeIdTo(Integer.parseInt(productTypeId));
			}
			if(!StringUtil.isEmpty(archiveStatus)){
				if(archiveStatus.equals("1")){
					criteria.andArchiveStatusEqualTo("3");
				}else{
					criteria.andArchiveStatusNotEqualTo("3");
				}
			}
			if(!StringUtil.isEmpty(archiveDealStatus)){
				criteria.andArchiveDealStatusEqualTo(archiveDealStatus);
			}
			if(!StringUtils.isEmpty(platContactStaffId)){
				criteria.andPlatContactStaffIdEqualTo(Integer.parseInt(platContactStaffId));
			}
			List<MchtLicenseChgCustom> mchtLicenseChgCustoms = mchtLicenseChgService.selectMchtLicenseChgCustomByExample(example);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String[] titles = { "提审日期", "商家序号", "名称", "店铺名称","主营类目","商家寄件状态","资料齐全情况","归档处理","内部备注",
					"驳回原因", "法务对接人" };
			ExcelBean excelBean = new ExcelBean("导出经营许可证归档列表.xls", "经营许可证归档列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for (MchtLicenseChgCustom mchtLicenseChgCustom : mchtLicenseChgCustoms) {
				String sendAState="";//商家寄件状态
				String archiveStatusDesc="";//资质齐全情况
				String archiveDealStatusDesc="";//品牌归档处理
				if(StringUtils.isEmpty(mchtLicenseChgCustom.getExpressId()) || StringUtils.isEmpty(mchtLicenseChgCustom.getExpressNo())){
					sendAState="未寄出";
				}else{
					sendAState="已寄出";
				}
				if(!StringUtils.isEmpty(mchtLicenseChgCustom.getArchiveDealStatus()) && mchtLicenseChgCustom.getArchiveDealStatus().equals("3")){
					archiveStatusDesc="已齐全";
				}else{
					archiveStatusDesc="未齐全";
				}
				if(!StringUtils.isEmpty(mchtLicenseChgCustom.getArchiveDealStatus()) && mchtLicenseChgCustom.getArchiveDealStatus().equals("0")){
					archiveDealStatusDesc="未处理";
				}else if("1".equals(mchtLicenseChgCustom.getArchiveDealStatus())){
					archiveDealStatusDesc="通过";
				}else if("2".equals(mchtLicenseChgCustom.getArchiveDealStatus())){
					archiveDealStatusDesc="驳回";
				}
				String[] data = { 
						mchtLicenseChgCustom.getCreateDate()==null?"":sf.format(mchtLicenseChgCustom.getCreateDate()),
						mchtLicenseChgCustom.getMchtCode(),
						mchtLicenseChgCustom.getCompanyName(),
						mchtLicenseChgCustom.getShopName(),
						mchtLicenseChgCustom.getProductTypeName(),
						sendAState,
						archiveStatusDesc,
						archiveDealStatusDesc,
						mchtLicenseChgCustom.getArchiveDealInnerRemarks()==null?"":mchtLicenseChgCustom.getArchiveDealInnerRemarks(),
						mchtLicenseChgCustom.getArchiveDealRemarks()==null?"":mchtLicenseChgCustom.getArchiveDealRemarks(),
						mchtLicenseChgCustom.getFwName()==null?"":mchtLicenseChgCustom.getFwName()		
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
	 * 行业许可证处理归档情况
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtLicenseChg/mchtLicenseChgHandleArchive.shtml")
	public ModelAndView MchtLicenseChgHandleArchive(HttpServletRequest request) {
		String rtPage = "/mchtLicenseChg/mchtLicenseChgHandleArchive";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtLicenseChg mchtLicenseChg = mchtLicenseChgService.selectByPrimaryKey(id);
		resMap.put("mchtLicenseChg", mchtLicenseChg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * 行业经营许可证内部备注情况
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtLicenseChg/mchtLicenseChgInnerRemarks.shtml")
	public ModelAndView MchtLicenseChgInnerRemarks(HttpServletRequest request) {
		String rtPage = "/mchtLicenseChg/mchtLicenseChgInnerRemarks";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtLicenseChg mchtLicenseChg = mchtLicenseChgService.selectByPrimaryKey(id);
		resMap.put("mchtLicenseChg", mchtLicenseChg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 行业许可证处理归档情况修改
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtLicenseChg/mchtLicenseChgHandleArchiveUpdate.shtml")
	@ResponseBody
	public Map<String, Object> mchtLicenseChgHandleArchiveUpdate(HttpServletRequest request, Page page) {
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
			mchtLicenseChgService.mchtLicenseChgHandleArchiveUpdate(paramMap);
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
	@RequestMapping(value = "/mchtLicenseChg/mchtLicenseChgInnerRemarksUpdate.shtml")
	@ResponseBody
	public Map<String, Object> mchtLicenseChgInnerRemarksUpdate(HttpServletRequest request, Page page) {
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
			mchtLicenseChgService.mchtLicenseChgHandleArchiveUpdate(paramMap);
				
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 归档页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtLicenseChg/toArchive.shtml")
	public ModelAndView toArchive(HttpServletRequest request) {
		String rtPage = "mchtLicenseChg/toArchive";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			MchtLicenseChg mchtLicenseChg = mchtLicenseChgService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("mchtLicenseChg", mchtLicenseChg);
		}
		resMap.put("hideRadio", request.getAttribute("hideRadio"));
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/mchtLicenseChg/archive.shtml")
	@ResponseBody
	public Map<String, Object> archive(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String archiveStatus = request.getParameter("archiveStatus");
			MchtLicenseChg mchtLicenseChg = new MchtLicenseChg();
			mchtLicenseChg.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtLicenseChg.setUpdateDate(new Date());
			MchtLicenseChgExample e = new MchtLicenseChgExample();
			e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			if(archiveStatus.equals("1")){
				mchtLicenseChgService.updateArchiveStatus(mchtLicenseChg,e,id);
			}else{
				mchtLicenseChgService.updateByExampleSelective(mchtLicenseChg, e);
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
}
