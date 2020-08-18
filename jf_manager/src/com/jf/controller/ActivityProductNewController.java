package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@SuppressWarnings("serial")
@Controller
public class ActivityProductNewController extends BaseController {

	@Autowired
	private ActivityProductService activityProductService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private PlatformContactService platformContactService;

	@Autowired
	private SysStaffInfoService sysStaffInfoService;

	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductService productService;
	
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private ProductDescPicService productDescPicService;

	/**
	 * 
	 * @Title ActivityProductManager
	 * @Description TODO(此方法是共用活动商品管理层)
	 * @author pengl
	 * @date 2018年1月15日 下午3:54:26
	 */
	@RequestMapping("/activityProductNew/activityProductManager.shtml")
	public ModelAndView activityProductManager(HttpServletRequest request,String statusPage, String activityId) {
		ModelAndView m = new ModelAndView();
		String staffID = this.getSessionStaffBean(request).getStaffID();
		// statusFlag是为了区分，同一个页面不同功能（后期扩展功能添加）
		String statusFlag = request.getParameter("statusFlag");
		m.addObject("statusFlag", statusFlag); // 1:官方会场
		if (!StringUtil.isEmpty(statusFlag) && "1".equals(statusFlag)) {
			// 对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
			String isManagement = this.getSessionStaffBean(request)
					.getIsManagement();
			m.addObject("isManagement", isManagement);
			m.addObject("staffID", staffID);
			SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
			SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample
					.createCriteria();
			sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
			List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService
					.selectByCustomExample(sysStaffInfoCustomExample);
			m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);
		}
		try {
			InputStream stream = ActivityNewController.class
					.getResourceAsStream("/base_config.properties");
			Properties properties = new Properties();
			properties.load(stream);
			String mUrl = properties.getProperty("mUrl");
			stream.close();
			m.addObject("mUrl", mUrl + "/share.html?id="); // 手机预览路径
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!StringUtils.isEmpty(activityId)) {
			Activity activity = activityService.selectByPrimaryKey(Integer
					.parseInt(activityId));
			m.addObject("preSellAuditStatus", activity.getPreSellAuditStatus());
		}
		if (statusPage != null && "1".equals(statusPage)) { // 审核商品
			m.addObject("operateAuditStatusList", DataDicUtil.getTableStatus(
					"BU_ACTIVITY_PRODUCT", "OPERATE_AUDIT_STATUS")); // 专员审核状态
			m.addObject("lawAuditStatusList", DataDicUtil.getTableStatus(
					"BU_ACTIVITY_PRODUCT", "LAW_AUDIT_STATUS")); // 法务部审核状态

			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = productTypeExample
					.createCriteria();
			productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1")
					.andTLevelEqualTo(1);

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
			m.addObject("isCwOrgStatus", isCwOrgStatus);

			List<ProductType> productTypeList = productTypeService
					.selectByExample(productTypeExample);
			m.addObject("productTypeList", productTypeList);
			m.setViewName("/activityProductNew/activityProductAuditList");
		} else if (statusPage != null && "2".equals(statusPage)) { // 排期商品
			m.addObject("mchtType",request.getParameter("mchtType")); //设置商家类型
			m.addObject("cooAuditStatusList", DataDicUtil.getTableStatus(
					"BU_ACTIVITY_PRODUCT", "COO_AUDIT_STATUS")); // 总监审核状态
			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = productTypeExample
					.createCriteria();
			productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1")
					.andTLevelEqualTo(1);

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
			m.addObject("isCwOrgStatus", isCwOrgStatus);

			List<ProductType> productTypeList = productTypeService
					.selectByExample(productTypeExample);
			m.addObject("productTypeList", productTypeList);
			m.setViewName("/activityProductNew/activityProductScheduleList");
		} else if (statusPage != null && "3".equals(statusPage)) { // 会场查看商品
			m.addObject("operateAuditStatusList", DataDicUtil.getTableStatus(
					"BU_ACTIVITY_PRODUCT", "OPERATE_AUDIT_STATUS")); // 专员审核状态
			m.addObject("cooAuditStatusList", DataDicUtil.getTableStatus(
					"BU_ACTIVITY_PRODUCT", "COO_AUDIT_STATUS")); // 总监审核状态
			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = productTypeExample
					.createCriteria();
			productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1")
					.andTLevelEqualTo(1);

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
			m.addObject("isCwOrgStatus", isCwOrgStatus);

			List<ProductType> productTypeList = productTypeService
					.selectByExample(productTypeExample);
			m.addObject("productTypeList", productTypeList);
			m.setViewName("/activityProductNew/activityAreaProductList");
		} else if (!StringUtil.isEmpty(statusPage) && "4".equals(statusPage)) { // 推广会场商品管理
			m.addObject("cooAuditStatusList", DataDicUtil.getTableStatus(
					"BU_ACTIVITY_PRODUCT", "COO_AUDIT_STATUS")); // 总监审核状态
			m.setViewName("/activityProductNew/promotionActivityProductList");
		} else if (!StringUtil.isEmpty(statusPage) && "5".equals(statusPage)) { // 游离码管理（商品管理）
			m.setViewName("/activityProductNew/couponActivityProductList");
		} else {
			m.addObject("cooAuditStatusList", DataDicUtil.getTableStatus(
					"BU_ACTIVITY_PRODUCT", "COO_AUDIT_STATUS")); // 总监审核状态
			m.setViewName("/activityProductNew/activityProductList");
		}
		m.addObject("activityId", activityId);
		m.addObject("activityAreaId", request.getParameter("activityAreaId"));
		return m;
	}

	/**
	 * 
	 * @Title getActivityProductList
	 * @Description TODO(这里用一句话描述这个方法的作用)
	 * @author pengl
	 * @date 2018年1月15日 下午4:28:52
	 */
	@ResponseBody
	@RequestMapping("/activityProductNew/getActivityProductList.shtml")
	public Map<String, Object> getActivityProductList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ActivityProductCustomNew> dataList = null;
		Integer totalCount = 0;
		try {
			ActivityProductCustomExample activityProductCustomExample = new ActivityProductCustomExample();
			ActivityProductCustomExample.ActivityProductCustomCriteria activityProductCustomCriteria = activityProductCustomExample
					.createCriteria();
			activityProductCustomCriteria.andActivityProductDelFlagEqualTo("0",
					"0");
			if (!StringUtil.isEmpty(request.getParameter("statusAudit"))
					&& request.getParameter("statusAudit").equals("1")) { // 排期商品
																			// 专员审核状态=通过
				activityProductCustomCriteria.andOperateAuditStatusEqualTo("2"); // 运营专员审核状态
																					// 1：待审核
																					// 2：审核通过
																					// 3：已驳回
			} else if (!StringUtil.isEmpty(request.getParameter("statusAudit"))
					&& request.getParameter("statusAudit").equals("2")) {
				// 推广商品审核
			} else if (!StringUtil.isEmpty(request.getParameter("statusAudit"))
					&& request.getParameter("statusAudit").equals("3")) { // 游离码管理（商品管理）
				activityProductCustomCriteria.andCooAuditStatusEqualTo("2"); // 排期审核状态=通过
			} else { // 活动商品是否驳回=否
				activityProductCustomCriteria.andRefuseFlagEqualTo("0");
			}
			activityProductCustomExample.setOrderByClause(" ap.id asc");
			//当是批量操作调用的时候,不分页
			if(StringUtil.isBlank((String) request.getAttribute("allAperateTag"))){
				activityProductCustomExample.setLimitSize(page.getLimitSize());
				activityProductCustomExample.setLimitStart(page.getLimitStart());
			}	
			if (!StringUtil.isEmpty(request.getParameter("activityId"))) { // 活动ID
				activityProductCustomCriteria.andActivityIdEqualTo(Integer
						.parseInt(request.getParameter("activityId")));
			}
			if (!StringUtil.isEmpty(request.getParameter("activityAreaId"))) { // 活动会场ID
				activityProductCustomCriteria.andActivityAreaId(request
						.getParameter("activityAreaId"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productId"))) { // 系统
																			// ID
				activityProductCustomCriteria.andProductIdEqualTo(Integer
						.parseInt(request.getParameter("productId")));
			}
			if (!StringUtil.isEmpty(request.getParameter("productCode"))) { // 商品
																			// ID
				activityProductCustomCriteria
						.andActivityProductCodeByEqualTo(request
								.getParameter("productCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productName"))) { // 商品名称
				activityProductCustomCriteria.andProductNameLike("%"
						+ request.getParameter("productName") + "%");
			}
			if (!StringUtil.isEmpty(request.getParameter("operateAuditStatus"))) { // 专员审核状态
				if ("1".equals(request.getParameter("operateAuditStatus"))) {
					activityProductCustomCriteria.andOperateAuditStatus();
				} else {
					activityProductCustomCriteria
							.andOperateAuditStatusEqualTo(request
									.getParameter("operateAuditStatus"));
				}
			}
			if (!StringUtil.isEmpty(request.getParameter("lawAuditStatus"))) { // 法务部审核状态
				activityProductCustomCriteria.andLawAuditStatusEqualTo(request
						.getParameter("lawAuditStatus"));
			}
			if (!StringUtil.isEmpty(request.getParameter("cooAuditStatus"))) { // 总监审核状态
				activityProductCustomCriteria.andCooAuditStatusEqualTo(request
						.getParameter("cooAuditStatus"));
			}
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) { // 对接人
				Integer platContactStaffId = Integer.valueOf(request
						.getParameter("platContactStaffId"));
				// 我对接的商家/我协助的商家
				activityProductCustomCriteria
						.andPlatContactStaffIdEqualTo(platContactStaffId);
			}
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {// 一级类目
				activityProductCustomCriteria.andproductTypeIdByEqualTo(request
						.getParameter("productTypeId"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productBrandId"))) {// 品牌
				activityProductCustomCriteria
						.andproductBrandIdByEqualTo(request
								.getParameter("productBrandId"));
			}
			totalCount = activityProductService
					.countActivityProductCustomNewExample(activityProductCustomExample);
			dataList = activityProductService
					.selectActivityProductCustomNewExample(activityProductCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
        if (dataList.size()>0 &&dataList!=null) {
			caculateGrossMargin(dataList);
		}
        resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * 
	 * @Title atchAuditOperation
	 * @Description TODO(品牌特卖：审核、排期的商品批量通过审核功能)
	 * @author chengh
	 * @date 2018年5月6日 上午10:53:02
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/activityProductNew/batchAuditOperation.shtml")
	public Map<String,Object> atchAuditOperation(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resMap = new HashMap<String, Object>();
		request.setAttribute("allAperateTag", "1");
		//先获取全部未审核的商品信息
		resMap = getActivityProductList(request,new Page());
		List<ActivityProductCustomNew> dataList = (List<ActivityProductCustomNew>) resMap.get("Rows");
		if(dataList.size() == 0){
			map.put("statusCode", "555");
			return map;
		}
		//获取所有活动商品ID
		List<Integer> productIdList = new ArrayList<Integer>();
		for (ActivityProductCustomNew activityProductCustomNew : dataList) {
			productIdList.add(activityProductCustomNew.getId());
		}
		//判断是审核中还是排期（0：审核中，1：排期）
		if(("1").equals(request.getParameter("statusAudit"))){
			request.setAttribute("statusAudit", "2");		
		}else{		
			request.setAttribute("statusAudit", "1");	
		}
		request.setAttribute("productIdList", productIdList);
		request.setAttribute("audit", "2");
		//进行批量审核操作
		StaffBean staffBean = this.getSessionStaffBean(request);
		map = activityProductService.updateAllActivityProduct(request, staffBean);
		return map;
	}
	
	/**
	 * 
	 * @Title updateActivityProduct
	 * @Description TODO(审核)
	 * @author pengl
	 * @date 2018年1月16日 上午10:17:02
	 */
	@ResponseBody
	@RequestMapping("/activityProductNew/updateActivityProduct.shtml")
	public Map<String, Object> updateActivityProduct(HttpServletRequest request) {
		StaffBean staffBean = this.getSessionStaffBean(request);
		return activityProductService.updateActivityProduct(request, staffBean);
	}

	/**
	 * 
	 * @Title updatePromotionActivityProduct
	 * @Description TODO(推广商品审核)
	 * @author pengl
	 * @date 2018年2月8日 下午5:52:40
	 */
	@ResponseBody
	@RequestMapping("/activityProductNew/updatePromotionActivityProduct.shtml")
	public Map<String, Object> updatePromotionActivityProduct(
			HttpServletRequest request) {
		StaffBean staffBean = this.getSessionStaffBean(request);
		return activityProductService.updatePromotionActivityProduct(request,
				staffBean);
	}
	
	        //商品审核页面
			@RequestMapping(value="/activityProductNew/lawAuditProduct.shtml")
			public String lawAuditProduct(Model model,HttpServletRequest request){
				ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
				model.addAttribute("productCustom", productCustom);
				ProductItemExample productItemExample=new ProductItemExample();
				productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(Integer.valueOf(request.getParameter("id")));
				List<ProductItemCustom> productItemCustoms=productItemService.selectProductItemCustomByExample(productItemExample);
				for (ProductItemCustom productItemCustom:productItemCustoms) {
					productItemCustom.setPic(FileUtil.getSmallImageName(productItemCustom.getPic()));
				}
				model.addAttribute("productItems", productItemCustoms);
				List<ProductProp> productProps=productService.getProductPropByProductId(Integer.valueOf(request.getParameter("id")));
				model.addAttribute("productProps", productProps);
				model.addAttribute("auditStatusList", DataDicUtil.getStatusList("BU_PRODUCT", "AUDIT_STATUS"));

				//商品详情图
				ProductDescPicExample productDescPicExample = new ProductDescPicExample();
				productDescPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(Integer.valueOf(request.getParameter("id")));
				productDescPicExample.setOrderByClause("seq_no asc");
				List<ProductDescPic> productDescPicList = productDescPicService.selectByExample(productDescPicExample);
				for (ProductDescPic productDescPic : productDescPicList) {
					productDescPic.setPic(FileUtil.getQuality70ImageName(productDescPic.getPic()));
				}
				model.addAttribute("productDescPicList", productDescPicList);

				return "/activityProductNew/lawAuditProduct";
			}


	//特卖排期中计算展示毛利和毛利率
	private  List<ActivityProductCustomNew>   caculateGrossMargin(List<ActivityProductCustomNew> dataList){
		for (ActivityProductCustomNew productActivityCustom : dataList) {
			BigDecimal salePriceMin=productActivityCustom.getSalePriceMin(); //最小活动价
			BigDecimal salePriceMax=productActivityCustom.getSalePriceMax(); //最大活动价
			BigDecimal costPriceMin=productActivityCustom.getCostPriceMin(); //最小结算价
			BigDecimal costPriceMax=productActivityCustom.getCostPriceMax(); //最大结算价
			BigDecimal svipDiscount=productActivityCustom.getSvipDiscount(); //svip折扣率
			BigDecimal popCommissionRate=productActivityCustom.getPopCommissionRate(); //pop技术服务费率,商家品牌表
				if ("1".equals(productActivityCustom.getMchtType())){
					if (salePriceMin.compareTo(BigDecimal.ZERO)==1 && salePriceMax.compareTo(BigDecimal.ZERO)==1 ){
						BigDecimal grossProfitMin=salePriceMin.multiply(svipDiscount).subtract(costPriceMin);//spop商家直接按商品的结算价计算毛利
						BigDecimal grossProfitMax=salePriceMax.multiply(svipDiscount).subtract(costPriceMax);
						BigDecimal grossProfitRateMin=grossProfitMin.divide(salePriceMin,2,BigDecimal.ROUND_HALF_UP); //spop商家毛利率=毛利/对应活动价,两位，四舍五入
						BigDecimal grossProfitRateMax=grossProfitMax.divide(salePriceMax,2,BigDecimal.ROUND_HALF_UP);
						productActivityCustom.setGrossProfitMin(grossProfitMin.setScale(2, BigDecimal.ROUND_HALF_UP));//毛利保留两位小数，四舍五入
						productActivityCustom.setGrossProfitMax(grossProfitMax.setScale(2, BigDecimal.ROUND_HALF_UP));
						productActivityCustom.setGrossProfitRateMin(grossProfitRateMin.multiply(new BigDecimal("100")));
						productActivityCustom.setGrossProfitRateMax(grossProfitRateMax.multiply(new BigDecimal("100")));
					}
				}else{  //pop结算价是按活动价进行计算
					if (salePriceMin.compareTo(BigDecimal.ZERO)==1 && salePriceMax.compareTo(BigDecimal.ZERO)==1 ){
						if(svipDiscount == null){
							svipDiscount = new BigDecimal(1);
						}
						if(popCommissionRate == null){
							popCommissionRate = new BigDecimal(0);
						}
						BigDecimal grossProfitMin=salePriceMin.multiply(svipDiscount).subtract(salePriceMin.multiply((new BigDecimal("1").subtract(popCommissionRate))));
						BigDecimal grossProfitMax=salePriceMax.multiply(svipDiscount).subtract(salePriceMax.multiply((new BigDecimal("1").subtract(popCommissionRate))));
						productActivityCustom.setGrossProfitMin(grossProfitMin.setScale(2, BigDecimal.ROUND_HALF_UP));
						productActivityCustom.setGrossProfitMax(grossProfitMax.setScale(2, BigDecimal.ROUND_HALF_UP));
						productActivityCustom.setGrossProfitRateMin(popCommissionRate.multiply(new BigDecimal("100")));//pop商家毛利率直接取技术服务费率
						productActivityCustom.setGrossProfitRateMax(popCommissionRate.multiply(new BigDecimal("100")));
					}
				}
			}
		return dataList;
	}

}
