package com.jf.controller;

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.exception.Assert;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ExpressMapper;
import com.jf.dao.MchtBrandRateChangeMapper;
import com.jf.entity.*;
import com.jf.entity.MchtContactExample.Criteria;
import com.jf.service.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CooperationChangeApplyController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(CooperationChangeApplyController.class);

	@Resource
	private MchtInfoService mchtInfoService;

	@Resource
	private CooperationChangeApplyService cooperationChangeApplyService;

	@Resource
	private CooperationChangeUploadPicService cooperationChangeUploadPicService;

	@Resource
	private ProductTypeService productTypeService;

	@Resource
	private MchtProductTypeService mchtProductTypeService;

	@Resource
	private MchtDepositService mchtDepositService;

	@Resource
	private MchtProductBrandService mchtProductBrandService;

	@Resource
	private ExpressMapper expressMapper;

	@Resource
	private MchtBrandRateChangeMapper mchtBrandRateChangeMapper	;

	@Resource
	MchtContactService mchtContactService;

	@Resource
	private AreaService areaService;
	/**
	 * 合作变更管理首页
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/cooperationChangeApply/cooperationChangeApplyIndex")
	public String cooperationChangeApplyIndex(Model model, HttpServletRequest request) {
		model.addAttribute("archiveStatusList", DataDicUtil.getStatusList("BU_COOPERATION_CHANGE_APPLY", "ARCHIVE_STATUS"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		if(mchtInfo.getChangeEndDate() == null){
			model.addAttribute("noAuth", 1);
		}else if(mchtInfo.getChangeEndDate().before(new Date())){
			model.addAttribute("noAuth", 1);
		}
		CooperationChangeApplyCustomExample e = new CooperationChangeApplyCustomExample();
		CooperationChangeApplyCustomExample.CooperationChangeApplyCustomCriteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		c.andArchiveStatusNotEqualOrNull();
		List<CooperationChangeApplyCustom> cooperationChangeApplyCustoms = cooperationChangeApplyService.selectByCustomExample(e);
		if(cooperationChangeApplyCustoms!=null && cooperationChangeApplyCustoms.size()>0){
			model.addAttribute("noAuth", 1);
		}
		return "cooperationChangeApply/cooperationChangeApplyIndex";
	}

	/**
	 * 数据列表
	 *
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/cooperationChangeApply/getCooperationChangeApplyList")
	@ResponseBody
	public ResponseMsg getCooperationChangeApplyList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		CooperationChangeApplyCustomExample e = new CooperationChangeApplyCustomExample();
		CooperationChangeApplyCustomExample.CooperationChangeApplyCustomCriteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getMchtInfo().getId());
		String changeApplyType = request.getParameter("changeApplyType");
		String auditStatus = request.getParameter("auditStatus");
		String archiveStatus = request.getParameter("archiveStatus");
		if(!StringUtil.isEmpty(changeApplyType)){
			c.andChangeApplyTypeEqualTo(changeApplyType);
		}
//		if(!StringUtil.isEmpty(auditStatus)){
//			if(auditStatus.equals("0")){
//				c.andZsAuditStatusEqualTo("0");
//			}else if(auditStatus.equals("1")){
//				c.andZsAuditStatusEqualTo("1");
//				c.andFwAuditStatusEqualTo("0");
//			}else if(auditStatus.equals("2")){
//				c.andFwAuditStatusEqualTo("1");
//			}else if(auditStatus.equals("3")){
//				c.andZsAuditStatusOrFwAuditStatusEquals("2");
//			}
//		}
		if (!StringUtil.isEmpty(auditStatus)){
			if (auditStatus.equals("0")){
				c.andZsAuditStatusEqualTo("0");
			}else if(auditStatus.equals("1")){
				c.andZsAuditStatusEqualTo("1").andFwAuditStatusEqualTo("0");
			}else if (auditStatus.equals("2")){
				c.andFwAuditStatusEqualTo("1");
			}else if (auditStatus.equals("3")){
				c.andZsAuditStatusOrFwAuditStatusEquals("2");
			}
		}
		if(!StringUtil.isEmpty(archiveStatus)){
			c.andArchiveStatusEqualTo(archiveStatus);
		}
		String createDateBegin = request.getParameter("createDateBegin");
		String createDateEnd = request.getParameter("createDateEnd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!StringUtil.isEmpty(createDateBegin)){
			c.andCreateDateGreaterThanOrEqualTo(sdf.parse((createDateBegin+" 00:00:00")));
		}
		if(!StringUtil.isEmpty(createDateEnd)){
			c.andCreateDateLessThanOrEqualTo(sdf.parse((createDateEnd+" 23:59:59")));
		}
		int totalCount = cooperationChangeApplyService.countByCustomExample(e);
		e.setLimitStart(page.getLimitStart());
		e.setLimitSize(page.getLimitSize());
		List<CooperationChangeApplyCustom> cooperationChangeApplyCustoms = cooperationChangeApplyService.selectByCustomExample(e);
		returnData.put("Rows", cooperationChangeApplyCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

	}

	/**
	 * 编辑页面
	 *
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/cooperationChangeApply/editCooperationChangeApply")
	public String editCooperationChangeApply(Model model,HttpServletRequest request) {
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		model.addAttribute("productTypes", productTypes);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		if(mchtInfo.getChangeApplyType().indexOf("1")>=0){//1.店铺名称变更
			model.addAttribute("shopNameAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("2")>=0){//2.店铺主营类目变更
			model.addAttribute("mchtProductTypeAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("3")>=0){//3.品牌技术服务费变更
			model.addAttribute("popCommissionRateAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("4")>=0){//4.保证金变更
			model.addAttribute("depositAuth", 1);
		}


		MchtDepositExample mde = new MchtDepositExample();
		mde.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(mde);

		MchtProductTypeExample mpte = new MchtProductTypeExample();
		mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1").andStatusEqualTo("1");
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mpte);

		CooperationChangeApply cooperationChangeApply = new CooperationChangeApply();
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty(id)){
			cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
			MchtBrandRateChangeExample mbrce = new MchtBrandRateChangeExample();
			mbrce.createCriteria().andDelFlagEqualTo("0").andCooperationChangeApplyIdEqualTo(cooperationChangeApply.getId()).andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			List<MchtBrandRateChange> mchtBrandRateChanges = mchtBrandRateChangeMapper.selectByExample(mbrce);
			model.addAttribute("mchtBrandRateChanges", mchtBrandRateChanges);
			model.addAttribute("oldShopName",cooperationChangeApply.getPreShopName());
			model.addAttribute("oldProductTypeName",cooperationChangeApply.getPreProductType());
			model.addAttribute("totalAmt",cooperationChangeApply.getPreDeposit());
		}else{
			cooperationChangeApply.setShopName(mchtInfo.getShopName());
			cooperationChangeApply.setProductTypeId(mchtProductTypes.get(0).getProductTypeId());
			cooperationChangeApply.setDeposit(mchtDeposits.get(0).getTotalAmt());
		}
		model.addAttribute("cooperationChangeApply", cooperationChangeApply);




//		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
//			ProductType productType = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
//			model.addAttribute("oldProductTypeName", productType.getName());
//		}
//
//		if(mchtDeposits!=null && mchtDeposits.size()>0){
//			model.addAttribute("totalAmt", mchtDeposits.get(0).getTotalAmt());
//		}
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusEqualTo("1");
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
//		model.addAttribute("oldShopName", mchtInfo.getShopName());
		model.addAttribute("mchtProductBrands", mchtProductBrands);
		model.addAttribute("mchtInfo", mchtInfo);
		model.addAttribute("shopTypeStatusList",DataDicUtil.getStatusList("BU_MCHT_INFO", "SHOP_TYPE"));

		return "cooperationChangeApply/editCooperationChangeApply";
	}

	/**
	 * 保存申请
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/cooperationChangeApply/saveCooperationChangeApply")
	@ResponseBody
	public ResponseMsg saveCooperationChangeApply(HttpServletRequest request) {
		String id = request.getParameter("id");
		CooperationChangeApply cooperationChangeApply = new CooperationChangeApply();
		//TODO set pre 1.pre_shop_name 2.pre_product_type 3.pre_deposit
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		if(mchtInfo != null){
			cooperationChangeApply.setPreShopName(mchtInfo.getShopName());
		}
		MchtProductTypeExample mpte = new MchtProductTypeExample();
		mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1").andStatusEqualTo("1");
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mpte);
		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
			Integer productTypeId = mchtProductTypes.get(0).getProductTypeId();
			ProductType productType = productTypeService.selectByPrimaryKey(productTypeId);
			cooperationChangeApply.setPreProductTypeId(productTypeId);
			cooperationChangeApply.setPreProductType(productType.getName());
		}
		MchtDepositExample mde = new MchtDepositExample();
		mde.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(mde);
		if(mchtDeposits!=null && mchtDeposits.size()>0){
			cooperationChangeApply.setPreDeposit(mchtDeposits.get(0).getTotalAmt());
		}
		if(StringUtil.isEmpty(id)){
			cooperationChangeApply.setCreateBy(this.getSessionUserInfo(request).getId());
			cooperationChangeApply.setCreateDate(new Date());
			cooperationChangeApply.setDelFlag("0");
			cooperationChangeApply.setMchtId(this.getSessionMchtInfo(request).getId());
			cooperationChangeApply.setZsAuditStatus("0");
		}else{
			cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
			if(!StringUtil.isEmpty(cooperationChangeApply.getZsAuditStatus()) && cooperationChangeApply.getZsAuditStatus().equals("2")){
				cooperationChangeApply.setZsAuditStatus("0");
			}
			if(!StringUtil.isEmpty(cooperationChangeApply.getFwAuditStatus()) && cooperationChangeApply.getFwAuditStatus().equals("2")){
				cooperationChangeApply.setZsAuditStatus("0");
			}

		}
		String shopType = request.getParameter("shopType");
		String businessFirms = request.getParameter("businessFirms");
		String brand = request.getParameter("brand");
		String productType = request.getParameter("productType");
		String shopName = request.getParameter("shopName");
		String productTypeId = request.getParameter("productTypeId");
		String deposit = request.getParameter("deposit");
		String mchtBrandRateChangeJsonStr = request.getParameter("mchtBrandRateChangeJsonStr");
		cooperationChangeApply.setUpdateBy(this.getSessionUserInfo(request).getId());
		cooperationChangeApply.setUpdateDate(new Date());
		if(!StringUtil.isEmpty(shopType)){
			cooperationChangeApply.setShopType(shopType);
		}else{
			cooperationChangeApply.setShopType(mchtInfo.getShopType());
		}
		if(!StringUtil.isEmpty(businessFirms)){
			cooperationChangeApply.setBusinessFirms(businessFirms);
		}else{
			cooperationChangeApply.setBusinessFirms(mchtInfo.getBusinessFirms());
		}
		if(!StringUtil.isEmpty(brand)){
			cooperationChangeApply.setBrand(brand);
		}else{
			cooperationChangeApply.setBrand(mchtInfo.getBrand());
		}
		if(!StringUtil.isEmpty(productType)){
			cooperationChangeApply.setProductType(productType);
		}else{
			cooperationChangeApply.setProductType(mchtInfo.getProductType());
		}
		if(!StringUtil.isEmpty(shopName)){
			cooperationChangeApply.setShopName(shopName);
		}else{
			cooperationChangeApply.setShopName(mchtInfo.getShopName());
		}
		if(!StringUtil.isEmpty(productTypeId)){
			cooperationChangeApply.setProductTypeId(Integer.parseInt(productTypeId));
		}
		if(!StringUtil.isEmpty(deposit)){
			cooperationChangeApply.setDeposit(new BigDecimal(deposit));
		}
		cooperationChangeApplyService.save(cooperationChangeApply,mchtBrandRateChangeJsonStr);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 查看页面
	 *
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/cooperationChangeApply/viewCooperationChangeApply")
	public String viewCooperationChangeApply(Model model,HttpServletRequest request) {
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		if(mchtInfo.getChangeApplyType().indexOf("1")>=0){//1.店铺名称变更
			model.addAttribute("shopNameAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("2")>=0){//2.店铺主营类目变更
			model.addAttribute("mchtProductTypeAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("3")>=0){//3.品牌技术服务费变更
			model.addAttribute("popCommissionRateAuth", 1);
		}
		if(mchtInfo.getChangeApplyType().indexOf("4")>=0){//4.保证金变更
			model.addAttribute("depositAuth", 1);
		}
		CooperationChangeApply cooperationChangeApply = new CooperationChangeApply();
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty(id)){
			cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
			ProductType pt = productTypeService.selectByPrimaryKey(cooperationChangeApply.getProductTypeId());
			if(pt!=null){
				model.addAttribute("newProductTypeName", pt.getName());
			}
			MchtBrandRateChangeExample mbrce = new MchtBrandRateChangeExample();
			mbrce.createCriteria().andDelFlagEqualTo("0").andCooperationChangeApplyIdEqualTo(cooperationChangeApply.getId()).andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			List<MchtBrandRateChange> mchtBrandRateChanges = mchtBrandRateChangeMapper.selectByExample(mbrce);
			model.addAttribute("mchtBrandRateChanges", mchtBrandRateChanges);
		}
//		MchtProductTypeExample mpte = new MchtProductTypeExample();
//		mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1").andStatusEqualTo("1");
//		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mpte);
//		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
//			ProductType productType = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
//			model.addAttribute("oldProductTypeName", productType.getName());
//		}
//		MchtDepositExample mde = new MchtDepositExample();
//		mde.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
//		List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(mde);
//		if(mchtDeposits!=null && mchtDeposits.size()>0){
//			model.addAttribute("totalAmt", mchtDeposits.get(0).getTotalAmt());
//		}
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusEqualTo("1");
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
//		model.addAttribute("oldShopName", mchtInfo.getShopName());
		model.addAttribute("oldShopName",cooperationChangeApply.getPreShopName());
		model.addAttribute("oldProductTypeName",cooperationChangeApply.getPreProductType());
		model.addAttribute("totalAmt",cooperationChangeApply.getPreDeposit());
		model.addAttribute("mchtProductBrands", mchtProductBrands);
		model.addAttribute("mchtInfo", mchtInfo);
		model.addAttribute("shopTypeStatusList",DataDicUtil.getStatusList("BU_MCHT_INFO", "SHOP_TYPE"));
		model.addAttribute("cooperationChangeApply", cooperationChangeApply);
		return "cooperationChangeApply/viewCooperationChangeApply";
	}

	/**
	 * 归档查看原因页面
	 *
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/cooperationChangeApply/toArchiveRemarks")
	public String toArchiveRemarks(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
		model.addAttribute("archiveRemarks", cooperationChangeApply.getArchiveRemarks());
		return "cooperationChangeApply/toArchiveRemarks";
	}

	/**
	 * 审核查看原因页面
	 *
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/cooperationChangeApply/toAuditRemarks")
	public String toAuditRemarks(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
		if(!StringUtil.isEmpty(cooperationChangeApply.getZsAuditStatus()) && cooperationChangeApply.getZsAuditStatus().equals("2")){
			model.addAttribute("auditRemarks", cooperationChangeApply.getZsAuditRemarks());
		}
		if(!StringUtil.isEmpty(cooperationChangeApply.getFwAuditStatus()) && cooperationChangeApply.getFwAuditStatus().equals("2")){
			model.addAttribute("auditRemarks", cooperationChangeApply.getFwAuditRemarks());
		}
		return "cooperationChangeApply/toAuditRemarks";
	}

	/**
	 * 立即寄件页面
	 *
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/cooperationChangeApply/toSend")
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
		return "cooperationChangeApply/toSend";
	}

	/**
	 * 立即寄件
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/cooperationChangeApply/send")
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
			if("0".equals(mchtContact.getAuditStatus()) || "".equals(mchtContact.getAuditStatus()) || mchtContact.getAuditStatus()==null ){
				resMap.put("returnCode", "404");
				resMap.put("returnMsg", "信息正在审核中,请稍后再试");
			}else {
				String id = request.getParameter("id");
				String expressId = request.getParameter("expressId");
				String expressNo = request.getParameter("expressNo");
				CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
				cooperationChangeApply.setSendStatus("1");
				cooperationChangeApply.setExpressId(Integer.parseInt(expressId));
				cooperationChangeApply.setExpressNo(expressNo.trim());
				cooperationChangeApply.setUpdateBy(this.getSessionMchtInfo(request).getId());
				cooperationChangeApply.setUpdateDate(new Date());
				cooperationChangeApply.setArchiveStatus("0");//未处理
				cooperationChangeApplyService.updateByPrimaryKeySelective(cooperationChangeApply);
			 }
		}else if("1".equals(mchtInfo.getIsManageSelf())){//自营
			String id = request.getParameter("id");
			String expressId = request.getParameter("expressId");
			String expressNo = request.getParameter("expressNo");
			CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
			cooperationChangeApply.setSendStatus("1");
			cooperationChangeApply.setExpressId(Integer.parseInt(expressId));
			cooperationChangeApply.setExpressNo(expressNo.trim());
			cooperationChangeApply.setUpdateBy(this.getSessionMchtInfo(request).getId());
			cooperationChangeApply.setUpdateDate(new Date());
			cooperationChangeApply.setArchiveStatus("0");//未处理
			cooperationChangeApplyService.updateByPrimaryKeySelective(cooperationChangeApply);
		}else{
			resMap.put("returnCode", "404");
			resMap.put("returnMsg", "请添加电商总负责人信息");
		}
		return resMap;
	}

	/**
	 * 终止合作协议预览
	 */
	@RequestMapping("/cooperationChangeApply/changeAgreementPreview")
	public String changeAgreementPreview(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		String id = request.getParameter("id");
		CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
		data.put("cooperationChangeApply", cooperationChangeApply);
		return page(data, "cooperationChangeApply/changeAgreementPreview");
	}

	/**
	 * 查看PDF
	 */
	@RequestMapping("/cooperationChangeApply/changeAgreementPDF")
	public void contractPDF(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
		Assert.notNull(cooperationChangeApply, "变更合作协议还没生成，不能查看协议扫描件");

		//设置响应内容类型为PDF类型
		response.setContentType("application/pdf");
		ServletOutputStream sos = response.getOutputStream();

		File file = FileUtil.getFile(cooperationChangeApply.getFilePath());
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

	/**
	 * 打印并上传页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/cooperationChangeApply/toPrintAndUpload")
	public String toPrintAndUpload(Model model,HttpServletRequest request) {
//		String id = request.getParameter("id");
//		model.addAttribute("id",id);
//		return "/cooperationChangeApply/toPrintAndUpload";
		Map<String, Object> data = new HashMap<>();
		String id = request.getParameter("id");
		CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
		data.put("cooperationChangeApply", cooperationChangeApply);
		return page(data, "/cooperationChangeApply/toPrintAndUpload");
	}

	/**
	 * 上传图片页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/cooperationChangeApply/toUpload")
	public String toUpload(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		String id = request.getParameter("id");
		CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
		data.put("cooperationChangeApply", cooperationChangeApply);
		return page(data, "/cooperationChangeApply/toUpload");
	}

	/**
	 * 上传图片
	 */
	@RequestMapping("/cooperationChangeApply/toUploadPicture")
	@ResponseBody
	public ResponseMsg toUploadPicture(HttpServletRequest request){
		try {
			String id = request.getParameter("id");
			CooperationChangeApply cooperationChangeApply = cooperationChangeApplyService.selectByPrimaryKey(Integer.parseInt(id));
			cooperationChangeApply.setUploadStatus("1");
			cooperationChangeApply.setFwAuditStatus("0");
			JSONArray cooperationChangeUploadPics = StringUtil.isEmpty(request.getParameter("cooperationChangeUploadPics"))?null:JSONArray.fromObject(request.getParameter("cooperationChangeUploadPics"));
			if(cooperationChangeUploadPics.size() >0) {
				cooperationChangeApplyService.toUploadPicture(cooperationChangeApply, cooperationChangeUploadPics);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
}
