package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ExpressMapper;
import com.jf.dao.MchtBrandChgMapper;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MchtController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MchtController.class);

	@Resource
	private MchtBlPicService mchtBlPicService;
	
	@Resource
	private MchtBlPicChgService mchtBlPicChgService;
	
	@Resource
	private MchtInfoChgService mchtInfoChgService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private MchtBrandChgMapper mchtBrandChgMapper;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private MchtBrandChgService mchtBrandChgService;
	
	@Resource
	private MchtContactService mchtContactService;
	
	@Resource
	private ExpressMapper expressMapper;
	
	@Resource
	private AreaService areaService;
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 商家信息页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/mcht/mchtInfo")
	public String mchtInfo(Model model, HttpServletRequest request) {
		MchtInfoCustom mchtInfoCustom=mchtInfoService.selectMchtInfoCustomById(this.getSessionMchtInfo(request).getId());
		model.addAttribute("mchtInfo", mchtInfoCustom);
		
		//营业执照是否上传
		MchtBlPicExample mchtBlPicExample=new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfoCustom.getId());
		int mchtBlPicCount=mchtBlPicService.countByExample(mchtBlPicExample);
		model.addAttribute("mchtBlPicCount", mchtBlPicCount);
		
		
		// 年检有效期是否到期
		Date now = new Date();
		if (mchtInfoCustom.getYearlyInspectionDate() != null && now.getTime() > mchtInfoCustom.getYearlyInspectionDate().getTime()) {
			model.addAttribute("yearlyInspectionDateExp", "1");
		} else {
			model.addAttribute("yearlyInspectionDateExp", "0");
		}
		
		//是否有申请更新
		MchtInfoChgExample mchtInfoChgExample=new MchtInfoChgExample();
		mchtInfoChgExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusNotEqualTo("3");
		int infoUpdateCount=mchtInfoChgService.countByExample(mchtInfoChgExample);
		model.addAttribute("infoUpdateCount", infoUpdateCount);
		
		return "mcht/mchtInfo";
	}

	
	/**
	 * 商家信息完善页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/mcht/mchtInfoPerfect")
	public String mchtInfoPerfect(Model model, HttpServletRequest request) {
		MchtInfoCustom mchtInfoCustom=mchtInfoService.selectMchtInfoCustomById(this.getSessionMchtInfo(request).getId());
		model.addAttribute("mchtInfo", mchtInfoCustom);
		
		//营业执照图
		MchtBlPicExample mchtBlPicExample=new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andDelFlagEqualTo("0");
		List<MchtBlPic> mchtBlPics=mchtBlPicService.selectByExample(mchtBlPicExample);
		model.addAttribute("mchtBlPics", mchtBlPics);
		model.addAttribute("companyTypeStatusList", DataDicUtil.getStatusList("BU_MCHT_INFO", "COMPANY_TYPE"));
		model.addAttribute("regfeeTypeStatusList", DataDicUtil.getStatusList("BU_MCHT_INFO", "REG_FEE_TYPE"));
		
		return "mcht/mchtInfoPerfect";
	}
	
	/**
	 * 二级类目
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/mcht/getClassTwo")
	public Map<String, Object> getClassTwo(String productTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			if(!StringUtil.isEmpty(productTypeId)) {
				ProductTypeExample productTypeExample = new ProductTypeExample();
				productTypeExample.createCriteria().andParentIdEqualTo(Integer.parseInt(productTypeId)).andStatusEqualTo("1").andDelFlagEqualTo("0");
				productTypeExample.setOrderByClause(" seq_no");
				List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
				map.put("productTypeList", productTypeList);
			}else {
				code = "9999";
				msg = "一级类目ID为空！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统错误！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 完善页保存暂不提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mcht/mchtInfoPerfectCommitSave")
	@ResponseBody
	public ResponseMsg mchtInfoPerfectCommitSave(Model model, HttpServletRequest request, MchtInfo mchtInfo) {
		try {
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtInfoService.perfectCommitSave(mchtInfo);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 * 完善页提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mcht/mchtInfoPerfectCommitAudit")
	@ResponseBody
	public ResponseMsg mchtInfoPerfectCommitAudit(Model model, HttpServletRequest request, MchtInfo mchtInfo) {
		try {
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtInfoService.perfectCommitAudit(mchtInfo);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	
	/**
	 * 商家信息修改申请首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/mcht/mchtInfoChgApplyIndex")
	public String mchtInfoChgApplyIndex(Model model, HttpServletRequest request) {
		
		MchtInfoChgExample mchtInfoChgExample=new MchtInfoChgExample();
		mchtInfoChgExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusNotEqualTo("3");
		int count=mchtInfoChgService.countByExample(mchtInfoChgExample);
		model.addAttribute("applyCount",count);
		return "mcht/mchtInfoChgApplyIndex";
	}
	
	
	/**
	 * 商家公司信息申请修改列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/mchtInfoChgApplyList")
	@ResponseBody
	public ResponseMsg mchtInfoChgApplyList( HttpServletRequest request, Page page) {
		Map<String, Object> returnData=new HashMap<String, Object>();
		MchtInfoChgExample mchtInfoChgExample=new MchtInfoChgExample();
		mchtInfoChgExample.createCriteria().andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andDelFlagEqualTo("0");
		int totalCount=mchtInfoChgService.countByExample(mchtInfoChgExample);
		
		mchtInfoChgExample.setLimitSize(page.getLimitSize());
		mchtInfoChgExample.setLimitStart(page.getLimitStart());
		List<MchtInfoChgCustom> mchtInfoChgs=mchtInfoChgService.selectMchtInfoChgCustomByExample(mchtInfoChgExample);
		returnData.put("Rows", mchtInfoChgs);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		
	}	 
	
	/**
	 * 
	 * @MethodName rejectReason
	 * @Description TODO(查看驳回原因)
	 * @author chengh
	 * @date 2019年7月29日 上午11:12:27
	 */
	@RequestMapping("/mcht/rejectReason")
	public String rejectReason(Model model, HttpServletRequest request) {	
		MchtInfoChgExample mchtInfoChgExample=new MchtInfoChgExample();
		mchtInfoChgExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusNotEqualTo("3");
		List<MchtInfoChg> mchtInfoChgs = mchtInfoChgService.selectByExample(mchtInfoChgExample);
		if(!mchtInfoChgs.isEmpty()){
			model.addAttribute("mchtInfoChg",mchtInfoChgs.get(0));
		}	
		model.addAttribute("type",request.getParameter("type"));
		return "mcht/rejectReason";
	}
	
	/**
	 * 
	 * @MethodName toSendContent
	 * @Description TODO(查看寄件内容)
	 * @author chengh
	 * @date 2019年7月29日 下午2:00:09
	 */
	@RequestMapping("/mcht/toSendContent")
	public String toSendContent(Model model, HttpServletRequest request) {	
		MchtInfoChgCustomExample mchtInfoChgCustomExample=new MchtInfoChgCustomExample();
		mchtInfoChgCustomExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusNotEqualTo("3");
		List<MchtInfoChgCustom> mchtInfoChgs = mchtInfoChgService.selectMchtInfoChgCustomByExample(mchtInfoChgCustomExample);
		if(!mchtInfoChgs.isEmpty()){
			model.addAttribute("mchtInfoChg",mchtInfoChgs.get(0));
		}	
		return "mcht/toSendContent";
	}
	
	/**
	 * 
	 * @MethodName toSend
	 * @Description TODO(工商主体信息更新记录立即寄件页面)
	 * @author chengh
	 * @date 2019年7月29日 上午11:30:57
	 */
	@RequestMapping("/mcht/toSend")
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
		return "mcht/toSend";
	}
	
	/**
	 * 品牌立即寄件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/send")
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
		 if("1".equals(mchtContact.getAuditStatus()) ){
				String id = request.getParameter("id");
				String expressId = request.getParameter("expressId");
				String expressNo = request.getParameter("expressNo");
				MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(Integer.parseInt(id));
				mchtInfoChg.setExpressId(Integer.parseInt(expressId));
				mchtInfoChg.setExpressNo(expressNo.trim());
				mchtInfoChg.setArchiveDealStatus("0");
				mchtInfoChg.setUpdateBy(this.getSessionMchtInfo(request).getId());
				mchtInfoChg.setUpdateDate(new Date());
				mchtInfoChgService.updateByPrimaryKeySelective(mchtInfoChg);
		  }else {
				 resMap.put("returnCode", "404");
					resMap.put("returnMsg", "收件地址信息相关信息审核中,请稍后再试");
			 }
		}else if("1".equals(mchtInfo.getIsManageSelf())){//自营
			String id = request.getParameter("id");
			String expressId = request.getParameter("expressId");
			String expressNo = request.getParameter("expressNo");
			MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(Integer.parseInt(id));
			mchtInfoChg.setExpressId(Integer.parseInt(expressId));
			mchtInfoChg.setExpressNo(expressNo.trim());
			mchtInfoChg.setArchiveDealStatus("0");
			mchtInfoChg.setUpdateBy(this.getSessionMchtInfo(request).getId());
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChgService.updateByPrimaryKeySelective(mchtInfoChg);

		}else{
			resMap.put("returnCode", "404");
			resMap.put("returnMsg", "请添加电商总负责人信息");
		}
		return resMap;
	}
	
	/**
	 * 商家公司信息申请修改
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/mchtInfoChgApply")
	public String mchtInfoChgApply(Model model, HttpServletRequest request) {

		MchtInfoChgExample mchtInfoChgExample=new MchtInfoChgExample();
		mchtInfoChgExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusNotEqualTo("3");
		int count=mchtInfoChgService.countByExample(mchtInfoChgExample);
		if (count>0){
			return null;
		}
		model.addAttribute("companyTypeStatusList", DataDicUtil.getStatusList("BU_MCHT_INFO", "COMPANY_TYPE"));
		model.addAttribute("regfeeTypeStatusList", DataDicUtil.getStatusList("BU_MCHT_INFO", "REG_FEE_TYPE"));
		// 没有新的申请更新的情况下，跳到新增页面（与编辑同个页面）
		MchtInfo mchtInfo=mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		MchtInfoChg mchtInfoChg = new MchtInfoChg();
		mchtInfoChg.setMchtId(mchtInfo.getId());
		mchtInfoChg.setCompanyName(mchtInfo.getCompanyName());
		mchtInfoChg.setCompanyType(mchtInfo.getCompanyType());
		mchtInfoChg.setUscc(mchtInfo.getUscc());
		mchtInfoChg.setRegCapital(mchtInfo.getRegCapital());
		mchtInfoChg.setRegFeeType(mchtInfo.getRegFeeType());
		mchtInfoChg.setCompanyAddress(mchtInfo.getCompanyAddress());
		mchtInfoChg.setFoundedDate(mchtInfo.getFoundedDate());
		mchtInfoChg.setYearlyInspectionDate(mchtInfo.getYearlyInspectionDate());
		mchtInfoChg.setCorporationName(mchtInfo.getCorporationName());
		mchtInfoChg.setCorporationIdcardNo(mchtInfo.getCorporationIdcardNo());
		mchtInfoChg.setCorporationIdcardImg1(mchtInfo.getCorporationIdcardImg1());
		mchtInfoChg.setCorporationIdcardImg2(mchtInfo.getCorporationIdcardImg2());
		mchtInfoChg.setCorporationMobile(mchtInfo.getCorporationMobile());
		mchtInfoChg.setCompanyTel(mchtInfo.getCompanyTel());
		mchtInfoChg.setContactProvince(mchtInfo.getContactProvince());
		mchtInfoChg.setContactCity(mchtInfo.getContactCity());
		mchtInfoChg.setContactCounty(mchtInfo.getContactCounty());
		mchtInfoChg.setContactAddress(mchtInfo.getContactAddress());
		mchtInfoChg.setBlPic(mchtInfo.getBlPic());
		mchtInfoChg.setOccPic(mchtInfo.getOccPic());
		mchtInfoChg.setTrcPic(mchtInfo.getTrcPic());
		mchtInfoChg.setAtqPic(mchtInfo.getAtqPic());
		mchtInfoChg.setBoaalPic(mchtInfo.getBoaalPic());
		mchtInfoChg.setBrandType(mchtInfo.getBrandType());
		mchtInfoChg.setCorporationIdcardDate(mchtInfo.getCorporationIdcardDate());
		mchtInfoChg.setBrandTypeDesc(mchtInfo.getBrandTypeDesc());
		mchtInfoChg.setScopeOfBusiness(mchtInfo.getScopeOfBusiness());
		
		model.addAttribute("mchtInfoChg", mchtInfoChg);
		return "mcht/mchtInfoChgApplyEdit";

	}
	
	
	/**
	 * 商家公司信息申请修改
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mcht/mchtInfoChgEdit")
	public String mchtInfoChgEdit(Model model, HttpServletRequest request) {
		Integer mchtInfoId = Integer.valueOf(this.getSessionMchtInfo(request).getId());
		MchtInfoCustom mchtInfoCustom = mchtInfoService.selectMchtInfoCustomById(mchtInfoId);
		model.addAttribute("mchtInfo", mchtInfoCustom);
		model.addAttribute("companyTypeStatusList", DataDicUtil.getStatusList("BU_MCHT_INFO", "COMPANY_TYPE"));
		model.addAttribute("regfeeTypeStatusList", DataDicUtil.getStatusList("BU_MCHT_INFO", "REG_FEE_TYPE"));
		MchtInfoChgCustom mchtInfoChg=mchtInfoChgService.selectMchtInfoChgCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("mchtInfoChg", mchtInfoChg);
		if("0".equals(mchtInfoChg.getStatus())||"4".equals(mchtInfoChg.getStatus())){
			return "mcht/mchtInfoChgApplyEdit";
		}else{
			return "mcht/mchtInfoChgApplyView";
		}
		
	}
//	/**
//	 * 商家公司信息申请修改
//	 * 
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/mcht/mchtInfoChgApply")
//	public String mchtInfoChgApply(Model model, HttpServletRequest request) {
//		
//		MchtInfoChg mchtInfoChg = null;
//		Integer mchtInfoId = Integer.valueOf(this.getSessionMchtInfo(request).getId());
//		MchtInfoCustom mchtInfoCustom = mchtInfoService.selectMchtInfoCustomById(mchtInfoId);
//		model.addAttribute("mchtInfo", mchtInfoCustom);
//		model.addAttribute("companyTypeStatusList", DataDicUtil.getStatusList("BU_MCHT_INFO", "COMPANY_TYPE"));
//		model.addAttribute("regfeeTypeStatusList", DataDicUtil.getStatusList("BU_MCHT_INFO", "REG_FEE_TYPE"));
//		
//		// 判断是否已提交申请更新,且为待审，待定状态
//		List<String> statusList = new ArrayList<>();
//		statusList.add("1");// 待审
//		statusList.add("2");// 待定
//		MchtInfoChgExample mchtInfoChgExample = new MchtInfoChgExample();
//		mchtInfoChgExample.createCriteria().andMchtIdEqualTo(mchtInfoId).andDelFlagEqualTo("0").andStatusIn(statusList);
//		List<MchtInfoChg> mchtInfoChgList = mchtInfoChgService.selectByExample(mchtInfoChgExample);
//		if (mchtInfoChgList != null && mchtInfoChgList.size() > 0) {// 已经提交申请，则跳到查看页面
//			model.addAttribute("mchtInfoChg", mchtInfoChgService.selectMchtInfoChgCustomByPrimaryKey(mchtInfoChgList.get(0).getId()));
//			//营业执照图
//			MchtBlPicChgExample mchtBlPicChgExample=new MchtBlPicChgExample();
//			mchtBlPicChgExample.createCriteria().andMchtInfoChgIdEqualTo(mchtInfoChgList.get(0).getId()).andDelFlagEqualTo("0");
//			List<MchtBlPicChg> mchtBlPicChgs=mchtBlPicChgService.selectByExample(mchtBlPicChgExample);
//			model.addAttribute("mchtBlPicChgs", mchtBlPicChgs);
//			return "mcht/mchtInfoChgApplyView";
//		}
//		
//		// 判断是有申请更新,且为未提审，驳回状态
//		statusList.clear();
//		statusList.add("0");// 未提审
//		statusList.add("4");// 驳回
//		mchtInfoChgExample = new MchtInfoChgExample();
//		mchtInfoChgExample.createCriteria().andMchtIdEqualTo(mchtInfoId).andDelFlagEqualTo("0").andStatusIn(statusList);
//		mchtInfoChgList = mchtInfoChgService.selectByExample(mchtInfoChgExample);
//		if (mchtInfoChgList != null && mchtInfoChgList.size() > 0) {// 未提审或驳回状态跳到修改页面
//			model.addAttribute("mchtInfoChg", mchtInfoChgList.get(0));
//			
//			//营业执照图
//			MchtBlPicChgExample mchtBlPicChgExample=new MchtBlPicChgExample();
//			mchtBlPicChgExample.createCriteria().andMchtInfoChgIdEqualTo(mchtInfoChgList.get(0).getId()).andDelFlagEqualTo("0");
//			List<MchtBlPicChg> mchtBlPicChgs=mchtBlPicChgService.selectByExample(mchtBlPicChgExample);
//			model.addAttribute("mchtBlPicChgs", mchtBlPicChgs);
//			
//			return "mcht/mchtInfoChgApplyEdit";
//		}
//		
//		// 没有新的申请更新的情况下，跳到新增页面（与编辑同个页面）
//		mchtInfoChg = new MchtInfoChg();
//		mchtInfoChg.setMchtId(mchtInfoCustom.getId());
//		mchtInfoChg.setCompanyName(mchtInfoCustom.getCompanyName());
//		mchtInfoChg.setCompanyType(mchtInfoCustom.getCompanyType());
//		mchtInfoChg.setUscc(mchtInfoCustom.getUscc());
//		mchtInfoChg.setRegCapital(mchtInfoCustom.getRegCapital());
//		mchtInfoChg.setRegFeeType(mchtInfoCustom.getRegFeeType());
//		mchtInfoChg.setCompanyAddress(mchtInfoCustom.getCompanyAddress());
//		mchtInfoChg.setFoundedDate(mchtInfoCustom.getFoundedDate());
//		mchtInfoChg.setYearlyInspectionDate(mchtInfoCustom.getYearlyInspectionDate());
//		mchtInfoChg.setCorporationName(mchtInfoCustom.getCorporationName());
//		mchtInfoChg.setCorporationIdcardNo(mchtInfoCustom.getCorporationIdcardNo());
//		mchtInfoChg.setCorporationMobile(mchtInfoCustom.getCorporationMobile());
//		mchtInfoChg.setCompanyTel(mchtInfoCustom.getCompanyTel());
//		mchtInfoChg.setContactProvince(mchtInfoCustom.getContactProvince());
//		mchtInfoChg.setContactCity(mchtInfoCustom.getContactCity());
//		mchtInfoChg.setContactCounty(mchtInfoCustom.getContactCounty());
//		mchtInfoChg.setContactAddress(mchtInfoCustom.getContactAddress());
//		model.addAttribute("mchtInfoChg", mchtInfoChg);
//		return "mcht/mchtInfoChgApplyEdit";
//		
//	}
	
	
	
	
	/**
	 * 公司信息更新申请页保存暂不提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mcht/mchtInfoChgCommitSave")
	@ResponseBody
	public ResponseMsg mchtInfoChgCommitSave(Model model, HttpServletRequest request,MchtInfoChg mchtInfoChg) {
		try{
			
			if(StringUtil.isEmpty(request.getParameter("blPics"))){
				throw new ArgException("请上传公司证件");
			}
			
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtInfoChgService.mchtInfoChgCommitSave(mchtInfoChg,request.getParameter("blPics"));
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 * 公司信息更新申请页提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mcht/mchtInfoChgCommitAudit")
	@ResponseBody
	public ResponseMsg mchtInfoChgCommitAudit(Model model, HttpServletRequest request,MchtInfoChg mchtInfoChg) {
		try {
			mchtInfoChg.setMchtId(this.getSessionMchtInfo(request).getId());
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtInfoChgService.mchtInfoChgCommitAudit(mchtInfoChg);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 * 公司更新申请信息删除
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mcht/delMchtInfoChg")
	@ResponseBody
	public ResponseMsg delMchtInfoChg( HttpServletRequest request) {
		try {
			MchtInfoChg mchtInfoChg4Update=new MchtInfoChg();
			mchtInfoChg4Update.setId(Integer.valueOf(request.getParameter("id")));
			mchtInfoChg4Update.setDelFlag("1");
			mchtInfoChgService.updateByPrimaryKeySelective(mchtInfoChg4Update);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	

}
