package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ExpressMapper;
import com.jf.dao.MchtBrandAptitudePicChgMapper;
import com.jf.dao.MchtBrandChangeAgreementPicMapper;
import com.jf.entity.*;
import com.jf.entity.MchtContactExample.Criteria;
import com.jf.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MchtBrandController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MchtBrandController.class);

	@Resource
	private MchtProductBrandService mchtProductBrandService;

	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private MchtProductTypeService mchtProductTypeService;

	@Resource
	private MchtBrandAptitudePicService mchtBrandAptitudePicService;

	@Resource
	private MchtPlatformAuthPicService mchtPlatformAuthPicService;
	
	@Resource
	private MchtBrandAptitudePicChgServer mchtBrandAptitudePicChgServer;
	
	@Resource
	private MchtPlatformAuthPicChgServer mchtPlatformAuthPicChgServer;
	
	@Resource
	private MchtBrandChgServer mchtBrandChgServer;
	
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
	private MchtBrandProductTypeService mchtBrandProductTypeService;
	
	@Resource
	private MchtBrandAptitudeService mchtBrandAptitudeService;
	
	@Resource
	private MchtBrandAptitudeChgService mchtBrandAptitudeChgService;
	
	@Resource
	private MchtBrandChgProductTypeService mchtBrandChgProductTypeService;
	
	@Resource
	private MchtBrandChangeAgreementPicMapper mchtBrandChangeAgreementPicMapper;
	
	@Resource
	private MchtBrandAptitudePicChgMapper mchtBrandAptitudePicChgMapper;
	
	@Resource
	private ExpressMapper expressMapper;
	
	@Resource
	private MchtBrandInvoicePicService mchtBrandInvoicePicService;
	
	@Resource
	private MchtBrandInspectionPicService mchtBrandInspectionPicService;
	
	@Resource
	private MchtBrandOtherPicService mchtBrandOtherPicService;

	@Resource
	private MchtContactService mchtContactService;
	
	@Resource
	private AreaService areaService;

	@Resource
	private MchtInfoService mchtInfoService;
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 商家品牌管理列表页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/mcht/mchtBrandIndex")
	public String mchtBrandIndex(Model model, HttpServletRequest request) {
		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		mchtProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productTypes", mchtProductTypeService.selectMchtProductTypeCustomByExample(mchtProductTypeExample));

		model.addAttribute("mchtProductBrandStatusList", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "STATUS"));
		model.addAttribute("auditStatusList", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "AUDIT_STATUS"));

		return "mchtBrand/mchtBrand";
	}

	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mcht/getMchtBrandList")
	@ResponseBody
	public ResponseMsg getMchtBrandList(HttpServletRequest request, Page page) {
		
		Map<String, Object> returnData = new HashMap<String, Object>();
		MchtProductBrandCustomExample mchtProductBrandCustomExample = new MchtProductBrandCustomExample();
		MchtProductBrandCustomExample.MchtProductBrandCustomCriteria criteria = mchtProductBrandCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		criteria.andProductBrandIdIsNotEqualTo();
		if (!StringUtil.isEmpty(request.getParameter("productType"))) {
			criteria.andProductTypeEqualTo(Integer.valueOf(request.getParameter("productType")));
		}
		if (!StringUtil.isEmpty(request.getParameter("status"))) {
			criteria.andStatusEqualTo(request.getParameter("status"));
		}
		String auditStatus = request.getParameter("auditStatus");
		if (!StringUtil.isEmpty(auditStatus)) {
			if(auditStatus.equals("56")){
				List<String> auditStatusList = new ArrayList<String>();
				auditStatusList.add("5");
				auditStatusList.add("6");
				criteria.andAuditStatusIn(auditStatusList);
			}else{
				criteria.andAuditStatusEqualTo(auditStatus);
			}
		}
		if (!StringUtil.isEmpty(request.getParameter("searchKeyWord"))) {
			criteria.andSearchKeyWroldLikeTo("%" + request.getParameter("searchKeyWord").trim() + "%");
		}
		String zsAuditStatus = request.getParameter("zsAuditStatus");
		if (!StringUtil.isEmpty(zsAuditStatus)) {
			if(zsAuditStatus.equals("56")){
				List<String> zsAuditStatusList = new ArrayList<String>();
				zsAuditStatusList.add("5");
				zsAuditStatusList.add("6");
				criteria.andZsAuditStatusIn(zsAuditStatusList);
			}else if(zsAuditStatus.equals("01")){
				criteria.andIsZsAuditRecommitEqualTo("0");
				criteria.andZsAuditStatusEqualTo("1");
			}else if(zsAuditStatus.equals("11")){
				criteria.andIsZsAuditRecommitEqualTo("1");
				criteria.andZsAuditStatusEqualTo("1");
			}else{
				criteria.andZsAuditStatusEqualTo(zsAuditStatus);
			}
		}
		String archiveStatus = request.getParameter("archiveStatus");
		if(!StringUtil.isEmpty(archiveStatus)){
			criteria.andArchiveStatusEqualTo(archiveStatus);
		}
		int totalCount = mchtProductBrandService.countByExample(mchtProductBrandCustomExample);
		mchtProductBrandCustomExample.setLimitStart(page.getLimitStart());
		mchtProductBrandCustomExample.setLimitSize(page.getLimitSize());
		List<MchtProductBrandCustom> mchtProductBrands = mchtProductBrandService.selectMchtProductBrandCustomByExample(mchtProductBrandCustomExample);
		returnData.put("Rows", mchtProductBrands);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}

	/**
	 * 商家品牌查看
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/mchtBrandView")
	public String mchtBrandView(Model model, HttpServletRequest request) {
		Integer mchtBrandId = Integer.valueOf(request.getParameter("mchtBrandId"));
		MchtProductBrandCustom mchtProductBrandCustom = mchtProductBrandService.selectMchtProductBrandCustomByPrimaryKey(mchtBrandId);
		model.addAttribute("mchtProductBrand", mchtProductBrandCustom);

		// 资质否到期
		Date now = new Date();
		if (mchtProductBrandCustom.getAptitudeExpDate() != null && now.getTime() > mchtProductBrandCustom.getAptitudeExpDate().getTime()) {
			model.addAttribute("aptitudeExp", "1");
		} else {
			model.addAttribute("aptitudeExp", "0");
		}
		// 授权否到期
		if (mchtProductBrandCustom.getPlatformAuthExpDate() != null && now.getTime() > mchtProductBrandCustom.getPlatformAuthExpDate().getTime()) {
			model.addAttribute("platformAuthExp", "1");
		} else {
			model.addAttribute("platformAuthExp", "0");
		}

		// 授权图片是否上传
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtPlatformAuthPic> mchtPlatformAuthPics=mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample);
		model.addAttribute("mchtPlatformAuthPics", mchtPlatformAuthPics);
		
		
		MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
		mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandInvoicePic> mchtBrandInvoicePics=mchtBrandInvoicePicServer.selectByExample(mchtBrandInvoicePicExample);
		model.addAttribute("mchtBrandInvoicePics", mchtBrandInvoicePics);
		
		MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
		mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandInspectionPic> mchtBrandInspectionPics=mchtBrandInspectionPicServer.selectByExample(mchtBrandInspectionPicExample);
		model.addAttribute("mchtBrandInspectionPics", mchtBrandInspectionPics);

		
		MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
		mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandOtherPic> mchtBrandOtherPics=mchtBrandOtherPicServer.selectByExample(mchtBrandOtherPicExample);
		model.addAttribute("mchtBrandOtherPics", mchtBrandOtherPics);

		//品牌经营类目
		MchtBrandProductTypeExample example = new MchtBrandProductTypeExample();
		MchtBrandProductTypeExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtProductBrandIdEqualTo(mchtBrandId);
		c.andTLevelEqualTo(2);
		List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms = mchtBrandProductTypeService.selectCustomByExample(example);
		model.addAttribute("mchtBrandProductTypeCustoms", mchtBrandProductTypeCustoms);
		//商标注册证或受理通知书 
		MchtBrandAptitudeExample e = new MchtBrandAptitudeExample();
		MchtBrandAptitudeExample.Criteria criteria = e.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(e);
		for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
			MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
			MchtBrandAptitudePicExample.Criteria mbapec = mbape.createCriteria();
			mbapec.andDelFlagEqualTo("0");
			mbapec.andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
			List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
			mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePics);
		}
		model.addAttribute("mchtBrandAptitudeCustoms", mchtBrandAptitudeCustoms);
		return "mchtBrand/mchtBrandView";
	}

	
	/**
	 * 添加商家品牌
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/addMchtBrand")
	public String addMchtBrand(Model model, HttpServletRequest request) {
		
		model.addAttribute("mchtProductBrand", new MchtProductBrandCustom());
		model.addAttribute("aptitudeTypeList", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE"));
		model.addAttribute("brandSource", request.getParameter("brandSource"));
		model.addAttribute("mchtInfoStatus", this.getSessionMchtInfo(request).getStatus());
		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		mchtProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1");
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mchtProductTypeExample);
		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
			ProductType productType = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
			if(productType!=null){
				model.addAttribute("brandAptitude", productType.getBrandAptitude());
			}
		}
		return "mchtBrand/mchtBrandPerfect";
	}
	
	
	
	/**
	 * 商家品牌信息完善页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/mchtBrandPerfect")
	public String mchtBrandPerfect(Model model, HttpServletRequest request) {
		
		Integer mchtBrandId = Integer.valueOf(request.getParameter("mchtBrandId"));
		MchtProductBrandCustom mchtProductBrandCustom = mchtProductBrandService.selectMchtProductBrandCustomByPrimaryKey(mchtBrandId);
		model.addAttribute("mchtProductBrand", mchtProductBrandCustom);

		model.addAttribute("aptitudeTypeList", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE"));

		// 授权图片
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample);
		model.addAttribute("mchtPlatformAuthPics", mchtPlatformAuthPics);
		
		
		// 授权图片
		MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
		mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicServer.selectByExample(mchtBrandInspectionPicExample);
		model.addAttribute("mchtBrandInspectionPics", mchtBrandInspectionPics);
		// 授权图片
		MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
		mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicServer.selectByExample(mchtBrandInvoicePicExample);
		model.addAttribute("mchtBrandInvoicePics", mchtBrandInvoicePics);
		// 授权图片
		MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
		mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicServer.selectByExample(mchtBrandOtherPicExample);
		model.addAttribute("mchtBrandOtherPics", mchtBrandOtherPics);
		//品牌经营类目
		MchtBrandProductTypeExample example = new MchtBrandProductTypeExample();
		MchtBrandProductTypeExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtProductBrandIdEqualTo(mchtBrandId);
		c.andTLevelEqualTo(2);
		List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms = mchtBrandProductTypeService.selectCustomByExample(example);
		model.addAttribute("mchtBrandProductTypeCustoms", mchtBrandProductTypeCustoms);
		//商标注册证或受理通知书 
		MchtBrandAptitudeExample e = new MchtBrandAptitudeExample();
		MchtBrandAptitudeExample.Criteria criteria = e.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(e);
		for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
			MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
			MchtBrandAptitudePicExample.Criteria mbapec = mbape.createCriteria();
			mbapec.andDelFlagEqualTo("0");
			mbapec.andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
			List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
			mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePics);
		}
		model.addAttribute("mchtBrandAptitudeCustoms", mchtBrandAptitudeCustoms);
		//合作协议变更申请函图片
		MchtBrandChangeAgreementPicExample mchtBrandChangeAgreementPicExample = new MchtBrandChangeAgreementPicExample();
		mchtBrandChangeAgreementPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandChangeAgreementPic> mchtBrandChangeAgreementPics = mchtBrandChangeAgreementPicMapper.selectByExample(mchtBrandChangeAgreementPicExample);
		model.addAttribute("mchtBrandChangeAgreementPics", mchtBrandChangeAgreementPics);
		
		model.addAttribute("brandSource",mchtProductBrandCustom.getBrandSource());
		model.addAttribute("mchtInfoStatus", this.getSessionMchtInfo(request).getStatus());
		model.addAttribute("mchtInfo", this.getSessionMchtInfo(request));
		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		mchtProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1");
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mchtProductTypeExample);
		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
			ProductType productType = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
			if(productType!=null){
				model.addAttribute("brandAptitude", productType.getBrandAptitude());
			}
		}
		return "mchtBrand/mchtBrandPerfect";
	}

	/**
	 * 完善页保存暂不提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mcht/mchtBrandPerfectCommitSave")
	@ResponseBody
	public ResponseMsg mchtBrandPerfectSave(Model model, HttpServletRequest request, MchtProductBrand mchtProductBrand) {
		try {
			if (StringUtil.isEmpty(request.getParameter("aptitudePics"))) {
				throw new ArgException("请上传品牌资质图");
			}
			if (StringUtil.isEmpty(request.getParameter("platformAuthPics"))) {
				throw new ArgException("请上传销售授权图");
			}
			mchtProductBrand.setUpdateDate(new Date());
			mchtProductBrand.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtProductBrandService.perfectCommitSave(mchtProductBrand, request.getParameter("aptitudePics"), request.getParameter("platformAuthPics"));
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 商家品牌完善提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mcht/mchtBrandPerfectCommitAudit")
	@ResponseBody
	public ResponseMsg mchtBrandPerfectCommitAudit(Model model, HttpServletRequest request, MchtProductBrand mchtProductBrand) {
		try {
			MchtProductBrandExample mpbe = new MchtProductBrandExample();
			mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andProductBrandNameEqualTo(mchtProductBrand.getProductBrandName());
			List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
			if(mchtProductBrands!=null && mchtProductBrands.size()>0){
				if(mchtProductBrand.getId()==null){
					throw new ArgException("您已经添加过该品牌");
				}
				if(!mchtProductBrand.getId().equals(mchtProductBrands.get(0).getId())){
					throw new ArgException("您已经添加过该品牌");
				}
			}
			if (StringUtil.isEmpty(request.getParameter("platformAuthPics"))) {
				throw new ArgException("请上传销售授权图");
			}
			if (StringUtil.isEmpty(request.getParameter("inspectionPics"))) {
				throw new ArgException("请上传质检报告图");
			}
			if(mchtProductBrand.getId()==null){
				mchtProductBrand.setMchtId(this.getSessionMchtInfo(request).getId());
				mchtProductBrand.setCreateBy(this.getSessionUserInfo(request).getId());
				mchtProductBrand.setCreateDate(new Date());
			}
			mchtProductBrand.setUpdateDate(new Date());
			mchtProductBrand.setUpdateBy(this.getSessionUserInfo(request).getId());
			String mchtBrandAptitudeJsonStr = request.getParameter("mchtBrandAptitudeJsonStr");
			String mchtBrandProductTypeJsonStr = request.getParameter("mchtBrandProductTypeJsonStr");
			mchtProductBrandService.perfectCommitAudit(mchtProductBrand, mchtBrandAptitudeJsonStr, mchtBrandProductTypeJsonStr,request.getParameter("platformAuthPics"),request.getParameter("invoicePics"),request.getParameter("inspectionPics"),request.getParameter("otherPics"),request.getParameter("mchtBrandChangeAgreementPics"));
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 信息更新申请页保存暂不提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtBrandChg
	 * @return
	 */
	@RequestMapping("/mcht/mchtBrandChgCommitSave")
	@ResponseBody
	public ResponseMsg mchtBrandChgCommitSave(Model model, HttpServletRequest request, MchtBrandChg mchtBrandChg) {
		try {
			if (StringUtil.isEmpty(request.getParameter("aptitudePics"))) {
				throw new ArgException("请上传品牌资质图");
			}
			if (StringUtil.isEmpty(request.getParameter("platformAuthPics"))) {
				throw new ArgException("请上传销售授权图");
			}
			mchtBrandChg.setUpdateDate(new Date());
			mchtBrandChg.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtBrandChgServer.mchtBrandChgCommitSave(mchtBrandChg, request.getParameter("aptitudePics"), request.getParameter("platformAuthPics"));
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 信息更新申请页提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtBrandChg
	 * @return
	 */
	@RequestMapping("/mcht/mchtBrandChgCommitAudit")
	@ResponseBody
	public ResponseMsg mchtBrandChgCommitAudit(Model model, HttpServletRequest request, MchtBrandChg mchtBrandChg) {
		try {
			if (StringUtil.isEmpty(request.getParameter("platformAuthPics"))) {
				throw new ArgException("请上传销售授权图");
			}
			
			if (StringUtil.isEmpty(request.getParameter("inspectionPics"))) {
				throw new ArgException("请上传质检报告图");
			}
			if(mchtBrandChg.getId()==null){
				mchtBrandChg.setMchtId(this.getSessionMchtInfo(request).getId());
				mchtBrandChg.setCreateBy(this.getSessionUserInfo(request).getId());
				mchtBrandChg.setCreateDate(new Date());
				mchtBrandChg.setArchiveStatus("0");//未寄出
			}
			MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mchtBrandChg.getMchtProductBrandId());
			mchtBrandChg.setProductBrandId(mchtProductBrand.getProductBrandId());
			mchtBrandChg.setUpdateDate(new Date());
			mchtBrandChg.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtBrandChg.setCommitDate(new Date());
			mchtBrandChg.setAuditStatus("1");//待审
			String mchtBrandAptitudeJsonStr = request.getParameter("mchtBrandAptitudeJsonStr");
			String mchtBrandProductTypeJsonStr = request.getParameter("mchtBrandProductTypeJsonStr");
			mchtBrandChgServer.mchtBrandChgCommitAudit(mchtBrandChg, mchtBrandAptitudeJsonStr,mchtBrandProductTypeJsonStr, request.getParameter("platformAuthPics"),request.getParameter("invoicePics"),request.getParameter("inspectionPics"),request.getParameter("otherPics"),request.getParameter("mchtBrandChangeAgreementPics"));
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 商家品牌信息申请修改
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/addMchtBrandChgApply")
	public String addMchtBrandChgApply(Model model, HttpServletRequest request) {
		
		MchtBrandChg mchtBrandChg = null;
		Integer mchtBrandId = Integer.valueOf(request.getParameter("mchtBrandId"));
		MchtProductBrandCustom mchtProductBrand = mchtProductBrandService.selectMchtProductBrandCustomByPrimaryKey(mchtBrandId);
		model.addAttribute("mchtProductBrand", mchtProductBrand);
		model.addAttribute("aptitudeTypeList", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE"));
		
		
		// 没有新的申请更新的情况下，跳到新增页面（与编辑同个页面）
		mchtBrandChg = new MchtBrandChg();
		mchtBrandChg.setMchtProductBrandId(mchtProductBrand.getId());
		mchtBrandChg.setProductBrandId(mchtProductBrand.getProductBrandId());
		mchtBrandChg.setAptitudeType(mchtProductBrand.getAptitudeType());
		mchtBrandChg.setAptitudeExpDate(mchtProductBrand.getAptitudeExpDate());
		mchtBrandChg.setPlatformAuthExpDate(mchtProductBrand.getPlatformAuthExpDate());
		mchtBrandChg.setInspectionExpDate(mchtProductBrand.getInspectionExpDate());
		mchtBrandChg.setOtherExpDate(mchtProductBrand.getOtherExpDate());
		mchtBrandChg.setMchtId(this.getSessionMchtInfo(request).getId());
		mchtBrandChg.setLogo(mchtProductBrand.getLogo());
		mchtBrandChg.setProductBrandName(mchtProductBrand.getProductBrandName());
		model.addAttribute("mchtBrandChg", mchtBrandChg);
		
		// 授权图片
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtPlatformAuthPic> mchtPlatformAuthPicChgs = mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample);
		model.addAttribute("mchtPlatformAuthPicChgs", mchtPlatformAuthPicChgs);
		
		
		// 授权图片
		MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
		mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicServer.selectByExample(mchtBrandInspectionPicExample);
		model.addAttribute("mchtBrandInspectionPics", mchtBrandInspectionPics);
		// 授权图片
		MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
		mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicServer.selectByExample(mchtBrandInvoicePicExample);
		model.addAttribute("mchtBrandInvoicePics", mchtBrandInvoicePics);
		// 授权图片
		MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
		mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicServer.selectByExample(mchtBrandOtherPicExample);
		model.addAttribute("mchtBrandOtherPics", mchtBrandOtherPics);
		
		//品牌经营类目
		MchtBrandProductTypeExample example = new MchtBrandProductTypeExample();
		MchtBrandProductTypeExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtProductBrandIdEqualTo(mchtBrandId);
		c.andTLevelEqualTo(2);
		List<MchtBrandProductTypeCustom> mchtBrandProductTypeCustoms = mchtBrandProductTypeService.selectCustomByExample(example);
		model.addAttribute("mchtBrandProductTypeCustoms", mchtBrandProductTypeCustoms);
		//商标注册证或受理通知书 
		MchtBrandAptitudeExample e = new MchtBrandAptitudeExample();
		MchtBrandAptitudeExample.Criteria criteria = e.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(e);
			for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
				MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
				MchtBrandAptitudePicExample.Criteria mbapec = mbape.createCriteria();
				mbapec.andDelFlagEqualTo("0");
				mbapec.andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
				List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
				mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePics);
			}
		model.addAttribute("mchtBrandAptitudeCustoms", mchtBrandAptitudeCustoms);
		//合作协议变更申请函图片
		MchtBrandChangeAgreementPicExample mchtBrandChangeAgreementPicExample = new MchtBrandChangeAgreementPicExample();
		mchtBrandChangeAgreementPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandChangeAgreementPic> mchtBrandChangeAgreementPics = mchtBrandChangeAgreementPicMapper.selectByExample(mchtBrandChangeAgreementPicExample);
		model.addAttribute("mchtBrandChangeAgreementPics", mchtBrandChangeAgreementPics);
				
		model.addAttribute("brandSource",mchtProductBrand.getBrandSource());
		model.addAttribute("mchtInfoStatus", this.getSessionMchtInfo(request).getStatus());
		return "mchtBrand/mchtBrandChgApplyEdit";
	}
	/**
	 * 商家品牌信息申请修改
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/mchtBrandChgApplyEdit")
	public String mchtBrandChgApplyEdit(Model model, HttpServletRequest request) {

		MchtBrandChg mchtBrandChg = mchtBrandChgServer.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		MchtProductBrandCustom mchtProductBrand = mchtProductBrandService.selectMchtProductBrandCustomByPrimaryKey(mchtBrandChg.getMchtProductBrandId());
		model.addAttribute("mchtProductBrand", mchtProductBrand);
		model.addAttribute("aptitudeTypeList", DataDicUtil.getStatusList("BU_MCHT_PRODUCT_BRAND", "APTITUDE_TYPE"));
		model.addAttribute("mchtBrandChg", mchtBrandChg);
		model.addAttribute("auditStatusDesc", DataDicUtil.getStatusDesc("BU_MCHT_BRAND_CHG", "AUDIT_STATUS", mchtBrandChg.getAuditStatus()));

		// 授权图片
		MchtPlatformAuthPicChgExample mchtPlatformAuthPicChgExample = new MchtPlatformAuthPicChgExample();
		mchtPlatformAuthPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtPlatformAuthPicChg> mchtPlatformAuthPicChgs = mchtPlatformAuthPicChgServer.selectByExample(mchtPlatformAuthPicChgExample);
		model.addAttribute("mchtPlatformAuthPicChgs", mchtPlatformAuthPicChgs);
		
		MchtBrandInspectionPicChgExample mchtBrandInspectionPicChgExample = new MchtBrandInspectionPicChgExample();
		mchtBrandInspectionPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandInspectionPicChg> mchtBrandInspectionPics = mchtBrandInspectionPicChgServer.selectByExample(mchtBrandInspectionPicChgExample);
		model.addAttribute("mchtBrandInspectionPics", mchtBrandInspectionPics);
		// 授权图片
		MchtBrandInvoicePicChgExample mchtBrandInvoicePicChgExample = new MchtBrandInvoicePicChgExample();
		mchtBrandInvoicePicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandInvoicePicChg> mchtBrandInvoicePics = mchtBrandInvoicePicChgServer.selectByExample(mchtBrandInvoicePicChgExample);
		model.addAttribute("mchtBrandInvoicePics", mchtBrandInvoicePics);
		// 授权图片
		MchtBrandOtherPicChgExample mchtBrandOtherPicChgExample = new MchtBrandOtherPicChgExample();
		mchtBrandOtherPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandOtherPicChg> mchtBrandOtherPics = mchtBrandOtherPicChgServer.selectByExample(mchtBrandOtherPicChgExample);
		model.addAttribute("mchtBrandOtherPics", mchtBrandOtherPics);
		
		//品牌经营类目
		MchtBrandChgProductTypeExample example = new MchtBrandChgProductTypeExample();
		MchtBrandChgProductTypeExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtBrandChgIdEqualTo(mchtBrandChg.getId());
		c.andTLevelEqualTo(2);
		List<MchtBrandChgProductTypeCustom> mchtBrandChgProductTypeCustoms = mchtBrandChgProductTypeService.selectCustomByExample(example);
		model.addAttribute("mchtBrandProductTypeCustoms", mchtBrandChgProductTypeCustoms);
		//商标注册证或受理通知书 
		MchtBrandAptitudeChgExample e = new MchtBrandAptitudeChgExample();
		MchtBrandAptitudeChgExample.Criteria criteria = e.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandAptitudeChgCustom> mchtBrandAptitudeChgCustoms = mchtBrandAptitudeChgService.selectCustomByExample(e);
		for(MchtBrandAptitudeChgCustom mchtBrandAptitudeChgCustom:mchtBrandAptitudeChgCustoms){
			MchtBrandAptitudePicChgExample mbapce = new MchtBrandAptitudePicChgExample();
			MchtBrandAptitudePicChgExample.Criteria mbapcec = mbapce.createCriteria();
			mbapcec.andDelFlagEqualTo("0");
			mbapcec.andMchtBrandAptitudeChgIdEqualTo(mchtBrandAptitudeChgCustom.getId());
			List<MchtBrandAptitudePicChg> mchtBrandAptitudePicChgs = mchtBrandAptitudePicChgMapper.selectByExample(mbapce);
			mchtBrandAptitudeChgCustom.setMchtBrandAptitudePicChgs(mchtBrandAptitudePicChgs);
		}
		model.addAttribute("mchtBrandAptitudeCustoms", mchtBrandAptitudeChgCustoms);
		
		if("0".equals(mchtBrandChg.getAuditStatus())||"4".equals(mchtBrandChg.getAuditStatus())){
			return "mchtBrand/mchtBrandChgApplyEdit";
		}else{
			return "mchtBrand/mchtBrandChgApplyView";
		}

	}
	
	
	/**
	 * 商家品牌管理列表页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/mcht/mchtBrandChgApplyIndex")
	public String mchtBrandChgIndex(Model model, HttpServletRequest request) {
		model.addAttribute("mchtBrandId",request.getParameter("mchtBrandId"));
		model.addAttribute("mchtBrand",mchtProductBrandService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mchtBrandId"))));
		MchtBrandChgExample mchtBrandChgExample=new MchtBrandChgExample();
		mchtBrandChgExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(Integer.valueOf(request.getParameter("mchtBrandId"))).andAuditStatusNotEqualTo("3");
		model.addAttribute("applyCount",mchtBrandChgServer.countByExample(mchtBrandChgExample));
		return "mchtBrand/mchtBrandChgApplyIndex";
	}

	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mcht/getMchtBrandChgList")
	@ResponseBody
	public ResponseMsg getMchtBrandChgList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		MchtBrandChgExample mchtBrandChgExample = new MchtBrandChgExample();
		mchtBrandChgExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andMchtProductBrandIdEqualTo(Integer.valueOf(request.getParameter("mchtBrandId")));
		int totalCount = mchtBrandChgServer.countByExample(mchtBrandChgExample);
		mchtBrandChgExample.setLimitStart(page.getLimitStart());
		mchtBrandChgExample.setLimitSize(page.getLimitSize());
		List<MchtBrandChgCustom> mchtBrandChgCustoms = mchtBrandChgServer.selectMchtBrandChgCustomByExample(mchtBrandChgExample);
		returnData.put("Rows", mchtBrandChgCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	
	/**
	 * 品牌资质更新记录删除
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mcht/delMchtBrandChg")
	@ResponseBody
	public ResponseMsg delMchtBrandChg( HttpServletRequest request) {
		try {
			MchtBrandChg mchtBrandChg = mchtBrandChgServer.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
			mchtBrandChg.setDelFlag("1");
			mchtBrandChg.setUpdateBy(this.getSessionMchtInfo(request).getId());
			mchtBrandChg.setUpdateDate(new Date());
			mchtBrandChgServer.updateByPrimaryKeySelective(mchtBrandChg);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 跟据上级id获取下级品类
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mcht/getProductTypes")
	@ResponseBody
	public ResponseMsg getMchtProductTypes(HttpServletRequest request) {
		if(StringUtil.isEmpty(request.getParameter("parentId"))){
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
		}
		Integer parentId=Integer.valueOf(request.getParameter("parentId"));
		ProductTypeExample productTypeExample=new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(parentId).andStatusEqualTo("1");
		productTypeExample.setOrderByClause("seq_no asc");
		List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productTypes);
	}
	
	/**
	 * 品牌立即寄件页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mcht/mchtBrand/toSend")
	public String toSend(Model model,HttpServletRequest request) {
		//获取最新店铺总负责人
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		//criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andAuditStatusEqualTo("1").andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
		MchtContact contact = new MchtContact();
		if(!mchtContacts.isEmpty()){
		contact = mchtContactService.selectByExample(mchtContactExample).get(0);
		}
		model.addAttribute("mchtContact",contact);
		String id = request.getParameter("id");
		model.addAttribute("id",id);
		ExpressExample e = new ExpressExample();
		ExpressExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(e);
		model.addAttribute("expressList", expressList);
		//获取省市区名称
		List<Area> areas = (List<Area>) areaService.getAddress(contact);
		String address = "";
		for (Area area : areas) {
			address +=area.getAreaName();
		}
		address += contact.getAddress();
		if(StringUtils.equals(address, "null")){
			address = "";
		}
		model.addAttribute("address",address);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		model.addAttribute("mchtInfo",mchtInfo);
		return "mchtBrand/toSend";
	}
	
	/**
	 * 品牌立即寄件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/mchtBrand/send")
	@ResponseBody
	public Map<String, Object> send(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		Integer id2 = this.getSessionMchtInfo(request).getId();
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		if(!mchtContacts.isEmpty()){
			 MchtContact mchtContact = mchtContactService.selectByExample(mchtContactExample).get(0);
		 if("1".equals(mchtContact.getAuditStatus())){
				String id = request.getParameter("id");
				String expressId = request.getParameter("expressId");
				String expressNo = request.getParameter("expressNo");
				MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(Integer.parseInt(id));
				mchtProductBrand.setExpressId(Integer.parseInt(expressId));
				mchtProductBrand.setExpressNo(expressNo.trim());
				mchtProductBrand.setArchiveStatus("1");
				mchtProductBrand.setUpdateBy(this.getSessionMchtInfo(request).getId());
				mchtProductBrand.setUpdateDate(new Date());
				//更新表同时更新
				MchtBrandChgExample example = new MchtBrandChgExample();
				example.setOrderByClause("id desc");
				example.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
				MchtBrandChg mchtBrandChg = mchtBrandChgServer.selectByExample(example).get(0);
				mchtBrandChg.setExpressId(Integer.parseInt(expressId));
				mchtBrandChg.setExpressNo(expressNo.trim());
				mchtBrandChg.setArchiveStatus("1");
				mchtBrandChg.setArchiveDealStatus("0");//归档未处理
				mchtBrandChg.setUpdateBy(this.getSessionMchtInfo(request).getId());
				mchtBrandChg.setUpdateDate(new Date());
				
				mchtProductBrandService.sendAndCopy(mchtProductBrand,mchtBrandChg);

		  }else {
			  resMap.put("returnCode", "404");
				resMap.put("returnMsg", "信息正在审核中,请稍后再试");
			 }
		}else if("1".equals(mchtInfo.getIsManageSelf())){
			String id = request.getParameter("id");
			String expressId = request.getParameter("expressId");
			String expressNo = request.getParameter("expressNo");
			MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(Integer.parseInt(id));
			mchtProductBrand.setExpressId(Integer.parseInt(expressId));
			mchtProductBrand.setExpressNo(expressNo.trim());
			mchtProductBrand.setArchiveStatus("1");
			mchtProductBrand.setUpdateBy(this.getSessionMchtInfo(request).getId());
			mchtProductBrand.setUpdateDate(new Date());
			//更新表同时更新
			MchtBrandChgExample example = new MchtBrandChgExample();
			example.setOrderByClause("id desc");
			example.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			MchtBrandChg mchtBrandChg = mchtBrandChgServer.selectByExample(example).get(0);
			mchtBrandChg.setExpressId(Integer.parseInt(expressId));
			mchtBrandChg.setExpressNo(expressNo.trim());
			mchtBrandChg.setArchiveStatus("1");
			mchtBrandChg.setArchiveDealStatus("0");//归档未处理
			mchtBrandChg.setUpdateBy(this.getSessionMchtInfo(request).getId());
			mchtBrandChg.setUpdateDate(new Date());

			mchtProductBrandService.sendAndCopy(mchtProductBrand,mchtBrandChg);
		}else{
			resMap.put("returnCode", "404");
			resMap.put("returnMsg", "请添加电商总负责人信息");
		}
		return resMap;

	}
	
	/**
	 * 品牌资质更新记录立即寄件页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mcht/mchtBrandChg/toSend")
	public String mchtBrandChgToSend(Model model,HttpServletRequest request) {
		//获取最新店铺总负责人
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andAuditStatusEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
		MchtContact contact = new MchtContact();
		if(!mchtContacts.isEmpty()){
			contact = mchtContactService.selectByExample(mchtContactExample).get(0);
		}
		model.addAttribute("mchtContact",contact);
		String id = request.getParameter("id");
		model.addAttribute("id",id);
		ExpressExample e = new ExpressExample();
		ExpressExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(e);
		model.addAttribute("expressList", expressList);
		//获取省市区名称
		List<Area> areas = (List<Area>) areaService.getAddress(contact);
		String address = "";
		for (Area area : areas) {
			address +=area.getAreaName();
		}
		address += contact.getAddress();
		if(StringUtils.equals(address, "null")){
			address = "";
		}
		model.addAttribute("address",address);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		model.addAttribute("mchtInfo",mchtInfo);
		return "mchtBrand/mchtBrandChgToSend";
	}
	
	/**
	 * 品牌资质更新记录立即寄件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/mchtBrandChg/send")
	@ResponseBody
	public ResponseMsg mchtBrandChgSend(HttpServletRequest request) {
		String id = request.getParameter("id");
		String expressId = request.getParameter("expressId");
		String expressNo = request.getParameter("expressNo");
		MchtBrandChg mchtBrandChg = mchtBrandChgServer.selectByPrimaryKey(Integer.parseInt(id));
		mchtBrandChg.setExpressId(Integer.parseInt(expressId));
		mchtBrandChg.setExpressNo(expressNo.trim());
		mchtBrandChg.setUpdateBy(this.getSessionMchtInfo(request).getId());
		mchtBrandChg.setUpdateDate(new Date());
		mchtBrandChg.setArchiveStatus("1");//1.未处理
		mchtBrandChgServer.updateByPrimaryKeySelective(mchtBrandChg);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
	/**
	 * 
	 * 资质归档情况
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mcht/mchtBrandChg/viewBrandChgPics.shtml")
	public ModelAndView viewBrandChgPics(HttpServletRequest request) {
		String rtPage = "/mchtBrand/viewBrandChgPics";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer id = Integer.parseInt(request.getParameter("id"));
		MchtBrandChg mchtBrandChg = mchtBrandChgServer.selectByPrimaryKey(id);
		resMap.put("mchtProductBrandId", mchtBrandChg.getMchtProductBrandId());
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
		List<MchtPlatformAuthPicChg> mchtPlatformAuthPicChgs = mchtPlatformAuthPicChgServer.selectByExample(mpapce);
		resMap.put("mchtPlatformAuthPicChgs", mchtPlatformAuthPicChgs);
		
		MchtBrandInvoicePicChgExample mbipce = new MchtBrandInvoicePicChgExample();
		MchtBrandInvoicePicChgExample.Criteria mbipcec = mbipce.createCriteria();
		mbipcec.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandInvoicePicChg> mchtBrandInvoicePicChgs = mchtBrandInvoicePicChgServer.selectByExample(mbipce);
		resMap.put("mchtBrandInvoicePicChgs", mchtBrandInvoicePicChgs);
		
		MchtBrandInspectionPicChgExample mbipce2 = new MchtBrandInspectionPicChgExample();
		MchtBrandInspectionPicChgExample.Criteria mbipcec2 = mbipce2.createCriteria();
		mbipcec2.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandInspectionPicChg> mchtBrandInspectionPicChgs = mchtBrandInspectionPicChgServer.selectByExample(mbipce2);
		resMap.put("mchtBrandInspectionPicChgs", mchtBrandInspectionPicChgs);
		
		MchtBrandOtherPicChgExample mbopce = new MchtBrandOtherPicChgExample();
		MchtBrandOtherPicChgExample.Criteria mbopcec = mbopce.createCriteria();
		mbopcec.andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandOtherPicChg> mchtBrandOtherPicChgs = mchtBrandOtherPicChgServer.selectByExample(mbopce);
		resMap.put("mchtBrandOtherPicChgs", mchtBrandOtherPicChgs);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 新增品牌--查看品牌资质内容
	 */
	@RequestMapping("/mcht/mchtBrand/viewBrandAptitude")
	public String viewBrandAptitude(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		String mchtProductBrandId = request.getParameter("mchtProductBrandId");
		MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(Integer.parseInt(mchtProductBrandId));
		data.put("mchtProductBrand", mchtProductBrand);
		//商标注册证
		MchtBrandAptitudeExample e = new MchtBrandAptitudeExample();
		MchtBrandAptitudeExample.Criteria criteria = e.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(e);
		for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
			MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
			mbape.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
			List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
			mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePics);
		}
		data.put("mchtBrandAptitudeCustoms", mchtBrandAptitudeCustoms);
		
		// 授权图片
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample);
		data.put("mchtPlatformAuthPics", mchtPlatformAuthPics);
		
		//进货发票
		MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
		mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicService.selectByExample(mchtBrandInvoicePicExample);
		data.put("mchtBrandInvoicePics", mchtBrandInvoicePics);
		//质检
		MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
		mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicService.selectByExample(mchtBrandInspectionPicExample);
		data.put("mchtBrandInspectionPics", mchtBrandInspectionPics);
		//其他资质
		MchtBrandOtherPicExample mbope = new MchtBrandOtherPicExample();
		mbope.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicService.selectByExample(mbope);
		data.put("mchtBrandOtherPics", mchtBrandOtherPics);
		return page(data, "mchtBrand/viewBrandAptitude");
	}
	
	/**
	 * 校验品牌名称
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/mchtBrand/checkName")
	@ResponseBody
	public ResponseMsg checkName(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andProductBrandNameEqualTo(name);
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
		if(StringUtil.isEmpty(id)){
			if(mchtProductBrands!=null && mchtProductBrands.size()>0){
				return new ResponseMsg(ResponseMsg.ERROR, "您已经添加过该品牌");
			}
		}else{
			if(mchtProductBrands!=null && mchtProductBrands.size()>0){
				if(!mchtProductBrands.get(0).getId().equals(Integer.parseInt(id))){
					return new ResponseMsg(ResponseMsg.ERROR, "您已经添加过该品牌");
				}
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
}
