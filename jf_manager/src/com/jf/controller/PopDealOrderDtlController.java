package com.jf.controller;
import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PopDealOrderDtlController extends BaseController {
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private MchtMonthlyCollectionsService mchtMonthlyCollectionsService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	
	@Resource
	private ProductBrandService productBrandService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private SysStaffRoleService sysStaffRoleService;
	
	@Resource
	private SysParamCfgService sysParamCfgService;

	@Resource
	private MchtSettleOrderService mchtSettleOrderService;

	@Resource
	private SubDepositOrderService subDepositOrderService;
	private static final long serialVersionUID = 1L;
	
	/**
	 * POP交易商品明细列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/popDealOrderDtl/list.shtml")
	public ModelAndView list(HttpServletRequest request) throws UnsupportedEncodingException{
		String rtPage = "/popDealOrderDtl/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("buttomType",request.getParameter("buttomType"));
		if(StringUtils.isEmpty(request.getParameter("mchtCode"))){
			resMap.put("mchtCode", "");
		}else{
			resMap.put("mchtCode", request.getParameter("mchtCode"));
		}
		String mchtType = request.getParameter("mchtType");
		if(StringUtil.isEmpty(mchtType)){
			resMap.put("mchtType", "");
		}else{
			resMap.put("mchtType", mchtType);
		}
		if(StringUtils.isEmpty(request.getParameter("subOrderCode"))){
			resMap.put("subOrderCode", "");
		}else{
			resMap.put("subOrderCode", request.getParameter("subOrderCode"));
		}
		if(StringUtils.isEmpty(request.getParameter("brandName"))){
			resMap.put("brandName", "");
		}else{
			String brandName=URLDecoder.decode(request.getParameter("brandName"), "UTF-8"); 
			resMap.put("brandName", brandName);
		}
		if(StringUtils.isEmpty(request.getParameter("artNo"))){
			resMap.put("artNo", "");
		}else{
			String artNo=URLDecoder.decode(request.getParameter("artNo"), "UTF-8"); 
			resMap.put("artNo", artNo);
		}
		if(StringUtils.isEmpty(request.getParameter("productName"))){
			resMap.put("productName", "");
		}else{
			String productName=URLDecoder.decode(request.getParameter("productName"), "UTF-8"); 
			resMap.put("productName", productName);
		}
		if(StringUtils.isEmpty(request.getParameter("productCode"))){
			resMap.put("productCode", "");
		}else{
			String productCode=URLDecoder.decode(request.getParameter("productCode"), "UTF-8"); 
			resMap.put("productCode", productCode);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtil.isEmpty(request.getParameter("date_begin"))){
			resMap.put("date_begin", df.format(new Date()));
		}else{
			resMap.put("date_begin", request.getParameter("date_begin"));
		}
		if(StringUtil.isEmpty(request.getParameter("date_end"))){
			resMap.put("date_end", df.format(new Date()));
		}else{
			resMap.put("date_end", request.getParameter("date_end"));
		}
		if(!StringUtil.isEmpty(request.getParameter("accept"))){
			resMap.put("accept", request.getParameter("accept"));
		}
		if(!StringUtil.isEmpty(request.getParameter("refund")) && request.getParameter("refund").equals("1")){
			resMap.put("refund", 1);
			rtPage = "/popDealOrderDtl/refundList";
		}
		if(StringUtil.isEmpty(request.getParameter("accept")) && StringUtil.isEmpty(request.getParameter("refund"))){
			resMap.put("accept", "1");
		}
		if(StringUtils.isEmpty(request.getParameter("mchtName"))){
			resMap.put("mchtName", "");
		}else{
			String mchtName=URLDecoder.decode(request.getParameter("mchtName"), "UTF-8"); 
			resMap.put("mchtName", mchtName);
		}
		if (StringUtils.isEmpty(request.getParameter("productTypeId"))) {
			resMap.put("productTypeId", "");
		}else {				
			resMap.put("productTypeId",request.getParameter("productTypeId"));
		}
		if (StringUtils.isEmpty(request.getParameter("orderType"))) {
			resMap.put("orderType", "");
		}else {
			String orderType=URLDecoder.decode(request.getParameter("orderType"), "UTF-8"); 
			resMap.put("orderType",orderType);
		}
		if(StringUtils.isEmpty(request.getParameter("mchtSettleOrderId"))){
			resMap.put("mchtSettleOrderId", "");
		}else{
			resMap.put("mchtSettleOrderId", request.getParameter("mchtSettleOrderId"));
		}
		if (StringUtils.isEmpty(request.getParameter("promotionType"))) {
			resMap.put("promotionType", "");
		}else {
			String promotionType=URLDecoder.decode(request.getParameter("promotionType"), "UTF-8"); 
			resMap.put("promotionType",promotionType);
		}
		 ProductTypeExample productTypeExample = new ProductTypeExample();
		 ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		 productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		 List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		 resMap.put("productTypeList", productTypeList); //1级类目
		 
		 String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		 if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			  String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			   if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		
		//角色id为60的不能看到特价订单
		SysStaffRoleExample sysStaffRoleExample=  new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo(60).andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
		if(sysStaffRoleService.countByExample(sysStaffRoleExample)>0){
			resMap.put("viewLimit", true);
		}else{
			resMap.put("viewLimit", false);
		}
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * POP交易商品明细列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/popDealOrderDtl/data.shtml")
	@ResponseBody
	public Map<String, Object> data(HttpServletRequest request,Page page) throws UnsupportedEncodingException{
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		List<OrderDtlCustom> orderDtlCustoms = new ArrayList<>();
		List<SubDepositOrderCustom> subDepositOrderCustomList = new ArrayList<>();
		try {
			OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
			OrderDtlCustomExample acceptCountExample = new OrderDtlCustomExample();
			OrderDtlCustomExample refundCountExample = new OrderDtlCustomExample();
			SubDepositOrderCustomExample subDepositOrderCustomExample = new SubDepositOrderCustomExample();
			OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria = orderDtlCustomExample.createCriteria();
			OrderDtlCustomExample.OrderDtlCustomCriteria acceptCountCriteria = acceptCountExample.createCriteria();
			OrderDtlCustomExample.OrderDtlCustomCriteria refundCountCriteria = refundCountExample.createCriteria();
			SubDepositOrderCustomExample.SubDepositOrderCustomCriteria subDepositOrderCustomCriteria = subDepositOrderCustomExample.createCriteria();
			orderDtlCustomCriteria.andDelFlagAndCombineOrderStatusAndMchtTypeEqualTo("0","1",null);//未删除、母订单已付款、POP
			acceptCountCriteria.andDelFlagAndCombineOrderStatusAndMchtTypeEqualTo("0","1",null);
			refundCountCriteria.andDelFlagAndCombineOrderStatusAndMchtTypeEqualTo("0","1",null);
			subDepositOrderCustomCriteria.andDelFlagEqualTo("0");
			subDepositOrderCustomCriteria.andCombineDepositOrderStatusEqualTo("2");//预售定金总订单状态为已付款
			
			//角色id为60的不能看到特价订单
			SysStaffRoleExample sysStaffRoleExample=  new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo(60).andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			if(sysStaffRoleService.countByExample(sysStaffRoleExample)>0){
				orderDtlCustomCriteria.andIsSpecialEqualTo("0");
				acceptCountCriteria.andIsSpecialEqualTo("0");
				refundCountCriteria.andIsSpecialEqualTo("0");
				if(org.apache.commons.lang.StringUtils.equals("0",request.getParameter("refund"))){
					orderDtlCustomCriteria.andProductStatusEqualTo("1");
				}
				acceptCountCriteria.andProductStatusEqualTo("1");
			}
			

			String searchMchtCode = request.getParameter("mchtCode");
			if(!StringUtil.isEmpty(searchMchtCode)){
				orderDtlCustomCriteria.andMchtCodeEqualTo2(searchMchtCode);
				acceptCountCriteria.andMchtCodeEqualTo2(searchMchtCode);
				refundCountCriteria.andMchtCodeEqualTo2(searchMchtCode);
				subDepositOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			String mchtType = request.getParameter("mchtType");
			if(!StringUtil.isEmpty(mchtType)){
				orderDtlCustomCriteria.andMchtTypeEqualTo(mchtType);
				acceptCountCriteria.andMchtTypeEqualTo(mchtType);
				refundCountCriteria.andMchtTypeEqualTo(mchtType);
				subDepositOrderCustomCriteria.andMchtTypeEqualTo(mchtType);
			}
			String searchSubOrderCode = request.getParameter("subOrderCode");
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				orderDtlCustomCriteria.andSubOrderCodeEqualTo2(searchSubOrderCode);
				acceptCountCriteria.andSubOrderCodeEqualTo2(searchSubOrderCode);
				refundCountCriteria.andSubOrderCodeEqualTo2(searchSubOrderCode);
				subDepositOrderCustomCriteria.andSubDepositOrderCodeEqualTo(searchSubOrderCode);
			}
			String searchBrandName = URLDecoder.decode(request.getParameter("brandName"), "UTF-8");
			if(!StringUtil.isEmpty(searchBrandName)){
				orderDtlCustomCriteria.andBrandNameEqualTo(searchBrandName);
				acceptCountCriteria.andBrandNameEqualTo(searchBrandName);
				refundCountCriteria.andBrandNameEqualTo(searchBrandName);
				subDepositOrderCustomCriteria.andBrandNameEqualTo(searchBrandName);
			}
			String searchArtNo = URLDecoder.decode(request.getParameter("artNo"), "UTF-8");
			if(!StringUtil.isEmpty(searchArtNo)){
				orderDtlCustomCriteria.andArtNoLikeCustom("%"+searchArtNo+"%");
				acceptCountCriteria.andArtNoLikeCustom("%"+searchArtNo+"%");
				refundCountCriteria.andArtNoLikeCustom("%"+searchArtNo+"%");
				subDepositOrderCustomCriteria.andArtNoLike("%"+searchArtNo+"%");
			}
			String searchProductName = URLDecoder.decode(request.getParameter("productName"), "UTF-8");
			if(!StringUtil.isEmpty(searchProductName)){
				orderDtlCustomCriteria.andProductNameLike("%"+searchProductName+"%");
				acceptCountCriteria.andProductNameLike("%"+searchProductName+"%");
				refundCountCriteria.andProductNameLike("%"+searchProductName+"%");
				subDepositOrderCustomCriteria.andProductNameLike("%"+searchProductName+"%");
			}
			String searchProductCode = request.getParameter("productCode");
			if(!StringUtil.isEmpty(searchProductCode)){
				orderDtlCustomCriteria.andProductCodeEqual(searchProductCode);
				acceptCountCriteria.andProductCodeEqual(searchProductCode);
				refundCountCriteria.andProductCodeEqual(searchProductCode);
				subDepositOrderCustomCriteria.andProductCodeEqualTo(searchProductCode);
			}
			String mchtName = URLDecoder.decode(request.getParameter("mchtName"), "UTF-8");
			if(!StringUtil.isEmpty(mchtName)){
				orderDtlCustomCriteria.andMchtNameLike(mchtName);
				acceptCountCriteria.andMchtNameLike(mchtName);
				refundCountCriteria.andMchtNameLike(mchtName);
				subDepositOrderCustomCriteria.andMchtNameLikeTo(mchtName);
			}
			String productTypeId =request.getParameter("productTypeId");
			if(!StringUtil.isEmpty(productTypeId)){
				orderDtlCustomCriteria.andProductTypeIdEqualTo(productTypeId);
				acceptCountCriteria.andProductTypeIdEqualTo(productTypeId);
				refundCountCriteria.andProductTypeIdEqualTo(productTypeId);
				subDepositOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}
			
			String orderType=request.getParameter("orderType");
			if (!StringUtil.isEmpty(orderType)) {
				orderDtlCustomCriteria.andOrderTypeEqual(orderType);
				acceptCountCriteria.andOrderTypeEqual(orderType);
				refundCountCriteria.andOrderTypeEqual(orderType);
			}
			
			String mchtSettleOrderId = request.getParameter("mchtSettleOrderId");
			if(!StringUtil.isEmpty(mchtSettleOrderId)){
				orderDtlCustomCriteria.andMchtSettleOrderIdEqualTo(Integer.valueOf(mchtSettleOrderId));
				acceptCountCriteria.andMchtSettleOrderIdEqualTo(Integer.valueOf(mchtSettleOrderId));
				refundCountCriteria.andMchtSettleOrderIdEqualTo(Integer.valueOf(mchtSettleOrderId));
				subDepositOrderCustomCriteria.andMchtSettleOrderIdEqualTo(Integer.valueOf(mchtSettleOrderId));
			}
			
			String promotionType=request.getParameter("promotionType");
			if (!StringUtil.isEmpty(promotionType)) {
				orderDtlCustomCriteria.andPromotionTypeEqual(promotionType);
				acceptCountCriteria.andPromotionTypeEqual(promotionType);
				refundCountCriteria.andPromotionTypeEqual(promotionType);
			}
			
			String searchAccept = request.getParameter("accept");
			String dateBegin = request.getParameter("date_begin");
			String dateEnd = request.getParameter("date_end");
			if(!StringUtil.isEmpty(searchAccept) && searchAccept.equals("1")){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				subDepositOrderCustomExample.setOrderByClause(" pay_date DESC");
				if(!StringUtil.isEmpty(dateBegin)){
					orderDtlCustomCriteria.andPayDateBeginGreaterThanOrEqualTo2(dateBegin+" 00:00:00");
					subDepositOrderCustomCriteria.andCombineDepositOrderPayDateGreaterThanOrEqualTo(dateBegin+" 00:00:00");
				}
				if(!StringUtil.isEmpty(dateEnd)){
					orderDtlCustomCriteria.andPayDateEndLessThanOrEqualTo2(dateEnd+" 23:59:59");
					subDepositOrderCustomCriteria.andCombineDepositOrderPayDateLessThanOrEqualTo(dateEnd+" 23:59:59");
				}
			}
			String searchRefund = request.getParameter("refund");
			List<String> productStatusList = new ArrayList<String>();
			productStatusList.add("2");
			productStatusList.add("3");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(searchRefund) && searchRefund.equals("1")){
				orderDtlCustomExample.setOrderByClause("a.refund_date asc");
				subDepositOrderCustomExample.setOrderByClause(" refund_date DESC");
				orderDtlCustomCriteria.andProductStatusIn(productStatusList);
				if(!StringUtil.isEmpty(dateBegin)){
					orderDtlCustomCriteria.andRefundDateGreaterThanOrEqualTo(sf.parse(dateBegin+" 00:00:00"));
					subDepositOrderCustomCriteria.andRefundDateAndStatusGreaterThanOrEqualTo(dateBegin+" 00:00:00");
					subDepositOrderCustomCriteria.andStatusEqualTo("6");
				}
				if(!StringUtil.isEmpty(dateEnd)){
					orderDtlCustomCriteria.andRefundDateLessThanOrEqualTo(sf.parse(dateEnd+" 23:59:59"));
					subDepositOrderCustomCriteria.andRefundDateAndStatusLessThanOrEqualTo(dateEnd+" 23:59:59");
					subDepositOrderCustomCriteria.andStatusEqualTo("6");
				}

			}
			
			refundCountCriteria.andProductStatusIn(productStatusList);
			if(!StringUtil.isEmpty(dateBegin)){
				acceptCountCriteria.andPayDateBeginGreaterThanOrEqualTo2(dateBegin+" 00:00:00");
				refundCountCriteria.andRefundDateGreaterThanOrEqualTo(sf.parse(dateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(dateEnd)){
				acceptCountCriteria.andPayDateEndLessThanOrEqualTo2(dateEnd+" 23:59:59");
				refundCountCriteria.andRefundDateLessThanOrEqualTo(sf.parse(dateEnd+" 23:59:59"));
			}

			//buttomType="0"||" "(订单)，才执行此代码
			String buttomType = request.getParameter("buttomType");
			if(StringUtils.isEmpty(buttomType) || "0".equals(buttomType)){
				List <?> acceptSum= orderDtlService.getSumOrderDtlByExample(acceptCountExample);
				Map<?, ?> acceptMap=(Map<?, ?>)acceptSum.get(0);
				List <?> refundSum= orderDtlService.getSumOrderDtlByExample(refundCountExample);
				Map<?, ?> refundMap=(Map<?, ?>)refundSum.get(0);

				int acceptQuantity = Integer.valueOf(StringUtil.valueOf(acceptMap.get("SumQ")));
				BigDecimal acceptPayAmount = new BigDecimal(StringUtil.valueOf(acceptMap.get("SumP")));
				BigDecimal acceptSettleAmount = new BigDecimal(StringUtil.valueOf(acceptMap.get("SumS")));
				BigDecimal acceptCommissionAmount = new BigDecimal(StringUtil.valueOf(acceptMap.get("SumC")));

				int refundQuantity = Integer.valueOf(StringUtil.valueOf(refundMap.get("SumQ")));
				BigDecimal refundPayAmount = new BigDecimal(StringUtil.valueOf(refundMap.get("SumP")));
				BigDecimal refundSettleAmount = new BigDecimal(StringUtil.valueOf(refundMap.get("SumS")));
				BigDecimal refundCommissionAmount = new BigDecimal(StringUtil.valueOf(refundMap.get("SumC")));

				BigDecimal needSettleAmount = acceptSettleAmount.subtract(refundSettleAmount);
				BigDecimal totalCommissionAmount = acceptCommissionAmount.subtract(refundCommissionAmount);

				totalCount = orderDtlService.countOrderDtlCustomByExample2(orderDtlCustomExample);
				orderDtlCustomExample.setLimitStart(page.getLimitStart());
				orderDtlCustomExample.setLimitSize(page.getLimitSize());
				orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample2(orderDtlCustomExample);

				if(orderDtlCustoms!=null && orderDtlCustoms.size()>0){
					OrderDtlCustom orderDtlCustomFirst = orderDtlCustoms.get(0);
					orderDtlCustomFirst.setAcceptQuantity(acceptQuantity);
					orderDtlCustomFirst.setRefundQuantity(refundQuantity);
					orderDtlCustomFirst.setAcceptPayAmount(acceptPayAmount);
					orderDtlCustomFirst.setRefundPayAmount(refundPayAmount);
					orderDtlCustomFirst.setAcceptSettleAmount(acceptSettleAmount);
					orderDtlCustomFirst.setRefundSettleAmount(refundSettleAmount);
					orderDtlCustomFirst.setAcceptCommissionAmount(acceptCommissionAmount);
					orderDtlCustomFirst.setRefundCommissionAmount(refundCommissionAmount);
					orderDtlCustomFirst.setNeedSettleAmount(needSettleAmount);
					orderDtlCustomFirst.setTotalCommissionAmount(totalCommissionAmount);
				}
				for (OrderDtlCustom orderDtlCustom:orderDtlCustoms) {
					orderDtlCustom.setSkuPic(FileUtil.getSmallImageName(orderDtlCustom.getSkuPic()));
				}
			}
			if(StringUtils.isEmpty(buttomType) || "1".equals(buttomType)){//buttomType="1"||" "(订单)，才执行此代码
				Integer startNumber = page.getLimitStart();
				Integer pageSize = page.getLimitSize();
				//如果startNumber<totalCount并且startNumber+pageSize<totalCount
				if(startNumber<totalCount && startNumber+pageSize<=totalCount){

				}else if(startNumber<totalCount && startNumber+pageSize>totalCount){//如果startNumber<totalCount并且startNumber+pageSize>totalCount
					//获取subDepositOrder开始查询的位置
					subDepositOrderCustomExample.setLimitStart(0);
					subDepositOrderCustomExample.setLimitSize(pageSize-orderDtlCustoms.size());
				}else if(startNumber>=totalCount){//如果startNumber>=totalCount
					subDepositOrderCustomExample.setLimitStart(startNumber-totalCount);
					subDepositOrderCustomExample.setLimitSize(page.getLimitSize());
				}

				//订单类型5、推广类型0的才有预售订单数据
				Integer subDepositOrderTtotalCount = 0;
				if((StringUtil.isEmpty(orderType) || "5".equals(orderType)) && (StringUtil.isEmpty(promotionType) || "0".equals(promotionType))) {
					subDepositOrderCustomList = subDepositOrderService.selectSubDepositOrderCustomByExample(subDepositOrderCustomExample);
					subDepositOrderTtotalCount = subDepositOrderService.countSubDepositOrderCustomByExample(subDepositOrderCustomExample);
				}
				voluation(subDepositOrderCustomList,orderDtlCustoms);
				totalCount = totalCount+subDepositOrderTtotalCount;
			}
			resMap.put("Rows", orderDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

    public void voluation(List<SubDepositOrderCustom> subDepositOrderCustomList,List<OrderDtlCustom> orderDtlCustoms){
        for (SubDepositOrderCustom subDepositOrderCustom:subDepositOrderCustomList) {
            OrderDtlCustom orderDtlCustom = new OrderDtlCustom();
            orderDtlCustom.setSkuPic(subDepositOrderCustom.getSkuPic());
            orderDtlCustom.setSubOrderCode(subDepositOrderCustom.getSubDepositOrderCode());
            orderDtlCustom.setPayDate(subDepositOrderCustom.getPayDate());
            orderDtlCustom.setRefundDate(subDepositOrderCustom.getRefundDate());
            orderDtlCustom.setProductStatus(subDepositOrderCustom.getStatus());
            //如果存在尾款单，则取尾款单的状态
			if(subDepositOrderCustom.getOrderDtlId() != null){
				OrderDtlCustom orderDtlCustom1 = orderDtlService.selectOrderDtlCustomByPrimaryKey(subDepositOrderCustom.getOrderDtlId());
				orderDtlCustom.setProductStatusDesc(orderDtlCustom1.getProductStatusDesc());
			}else{
				if("5".equals(orderDtlCustom.getProductStatus()) || "6".equals(orderDtlCustom.getProductStatus())){
					orderDtlCustom.setProductStatusDesc("已完成");
				}else{
					orderDtlCustom.setProductStatusDesc("未完成");
				}
			}

            orderDtlCustom.setMchtId(subDepositOrderCustom.getMchtId());
            orderDtlCustom.setMchtType(subDepositOrderCustom.getMchtType() == null?"2":subDepositOrderCustom.getMchtType());
            orderDtlCustom.setMchtCode(subDepositOrderCustom.getMchtCode());
            orderDtlCustom.setReceiverName(subDepositOrderCustom.getReceiverName());
            orderDtlCustom.setBrandName(subDepositOrderCustom.getBrandName());
            orderDtlCustom.setProductName(subDepositOrderCustom.getProductName());
            orderDtlCustom.setArtNo(subDepositOrderCustom.getArtNo());
            orderDtlCustom.setCode(subDepositOrderCustom.getCode());
            orderDtlCustom.setProductPropDesc(subDepositOrderCustom.getProductPropDesc());
            orderDtlCustom.setSalePrice(subDepositOrderCustom.getSalePrice());
            orderDtlCustom.setQuantity(subDepositOrderCustom.getQuantity());
            orderDtlCustom.setPayAmount(subDepositOrderCustom.getPayAmount());
            orderDtlCustom.setCompanyName(subDepositOrderCustom.getCompanyName());
            orderDtlCustom.setMchtType(subDepositOrderCustom.getMchtType());
            orderDtlCustom.setPopCommissionRate(subDepositOrderCustom.getPopCommissionRate());
            orderDtlCustom.setPaymentName(subDepositOrderCustom.getPaymentName());
            if(!"1".equals(subDepositOrderCustom.getMchtType())){
				orderDtlCustom.setSettleAmount(subDepositOrderCustom.getSettleAmount());
				orderDtlCustom.setCommissionAmount(subDepositOrderCustom.getPayAmount().multiply(subDepositOrderCustom.getPopCommissionRate()));
			}else{
				orderDtlCustom.setSettleAmount(new BigDecimal(0));
				orderDtlCustom.setCommissionAmount(subDepositOrderCustom.getPayAmount());
			}
            orderDtlCustom.setOrderType("S");
            orderDtlCustoms.add(orderDtlCustom);
        }
    }

	/**
	 * 查看商家经营品类，品牌
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/popDealOrderDtl/viewMcht.shtml")
	public ModelAndView viewMcht(HttpServletRequest request) {
		String rtPage = "/popDealOrderDtl/viewMcht";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer productId = Integer.valueOf(request.getParameter("productId"));
		Product product = productService.selectByPrimaryKey(productId);
		MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
		MchtProductBrandExample.Criteria mchtProductBrandCriteria = mchtProductBrandExample.createCriteria();
		mchtProductBrandCriteria.andDelFlagEqualTo("0");
		mchtProductBrandCriteria.andMchtIdEqualTo(product.getMchtId());
		mchtProductBrandCriteria.andProductBrandIdEqualTo(product.getBrandId());
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mchtProductBrandExample);
		String productBrandNameDesc = "";
		if(mchtProductBrands!=null && mchtProductBrands.size()>0){
			resMap.put("popCommissionRate",mchtProductBrands.get(0).getPopCommissionRate().multiply(new BigDecimal(100)).doubleValue());
			ProductBrand productBrand = productBrandService.selectByPrimaryKey(mchtProductBrands.get(0).getProductBrandId());
			productBrandNameDesc = productBrand.getName();
		}
		resMap.put("productBrandNameDesc",productBrandNameDesc);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/popDealOrderDtl/export.shtml")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
			OrderDtlCustomExample refundOrderDtlCustomExample = new OrderDtlCustomExample();
			SubDepositOrderCustomExample subDepositOrderCustomExample = new SubDepositOrderCustomExample();
			SubDepositOrderCustomExample refundSubDepositOrderCustomExample = new SubDepositOrderCustomExample();
			refundOrderDtlCustomExample.setOrderByClause("a.refund_date asc");
			OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria = orderDtlCustomExample.createCriteria();
			OrderDtlCustomExample.OrderDtlCustomCriteria refundOrderDtlCustomCriteria = refundOrderDtlCustomExample.createCriteria();
			SubDepositOrderCustomExample.SubDepositOrderCustomCriteria subDepositOrderCustomCriteria = subDepositOrderCustomExample.createCriteria();
			SubDepositOrderCustomExample.SubDepositOrderCustomCriteria refundSubDepositOrderCustomCriteria = refundSubDepositOrderCustomExample.createCriteria();
			orderDtlCustomCriteria.andDelFlagAndCombineOrderStatusAndMchtTypeEqualTo("0","1",null);
			refundOrderDtlCustomCriteria.andDelFlagAndCombineOrderStatusAndMchtTypeEqualTo("0","1",null);
			subDepositOrderCustomCriteria.andDelFlagEqualTo("0");
			subDepositOrderCustomCriteria.andCombineDepositOrderStatusEqualTo("2");//预售定金总订单状态为已付款
			subDepositOrderCustomExample.setOrderByClause(" pay_date DESC");
			refundSubDepositOrderCustomCriteria.andDelFlagEqualTo("0");
			refundSubDepositOrderCustomCriteria.andCombineDepositOrderStatusEqualTo("2");//预售定金总订单状态为已付款
			refundSubDepositOrderCustomExample.setOrderByClause(" refund_date DESC");

			//角色id为60的不能看到特价订单
			SysStaffRoleExample sysStaffRoleExample=  new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo(60).andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			if(sysStaffRoleService.countByExample(sysStaffRoleExample)>0){
				orderDtlCustomCriteria.andIsSpecialEqualTo("0");
				refundOrderDtlCustomCriteria.andIsSpecialEqualTo("0");
				orderDtlCustomCriteria.andProductStatusEqualTo("1");
			}

			String searchMchtCode = request.getParameter("mchtCode");
			if(!StringUtil.isEmpty(searchMchtCode)){
				orderDtlCustomCriteria.andMchtCodeEqualTo2(searchMchtCode);
				refundOrderDtlCustomCriteria.andMchtCodeEqualTo2(searchMchtCode);
				subDepositOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
				refundSubDepositOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			String mchtType = request.getParameter("mchtType");
			if(!StringUtil.isEmpty(mchtType)){
				orderDtlCustomCriteria.andMchtTypeEqualTo(mchtType);
				refundOrderDtlCustomCriteria.andMchtTypeEqualTo(mchtType);
				subDepositOrderCustomCriteria.andMchtTypeEqualTo(mchtType);
				refundSubDepositOrderCustomCriteria.andMchtTypeEqualTo(mchtType);
			}
			String searchSubOrderCode = request.getParameter("subOrderCode");
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				orderDtlCustomCriteria.andSubOrderCodeEqualTo2(searchSubOrderCode);
				refundOrderDtlCustomCriteria.andSubOrderCodeEqualTo2(searchSubOrderCode);
				subDepositOrderCustomCriteria.andSubDepositOrderCodeEqualTo(searchSubOrderCode);
				refundSubDepositOrderCustomCriteria.andSubDepositOrderCodeEqualTo(searchSubOrderCode);
			}
			String searchBrandName=URLDecoder.decode(request.getParameter("brandName"), "UTF-8"); 
			if(!StringUtil.isEmpty(searchBrandName)){
				orderDtlCustomCriteria.andBrandNameEqualTo(searchBrandName);
				refundOrderDtlCustomCriteria.andBrandNameEqualTo(searchBrandName);
				subDepositOrderCustomCriteria.andBrandNameEqualTo(searchBrandName);
				refundSubDepositOrderCustomCriteria.andBrandNameEqualTo(searchBrandName);
			}
			String searchArtNo = request.getParameter("artNo");
			if(!StringUtil.isEmpty(searchArtNo)){
				orderDtlCustomCriteria.andArtNoLikeCustom("%"+searchArtNo+"%");
				refundOrderDtlCustomCriteria.andArtNoLikeCustom("%"+searchArtNo+"%");
				subDepositOrderCustomCriteria.andArtNoLike("%"+searchArtNo+"%");
				refundSubDepositOrderCustomCriteria.andArtNoLike("%"+searchArtNo+"%");
			}
			String searchProductName = URLDecoder.decode(request.getParameter("productName"), "UTF-8"); 
			if(!StringUtil.isEmpty(searchProductName)){
				orderDtlCustomCriteria.andProductNameLike("%"+searchProductName+"%");
				refundOrderDtlCustomCriteria.andProductNameLike("%"+searchProductName+"%");
				subDepositOrderCustomCriteria.andProductNameLike("%"+searchProductName+"%");
				refundSubDepositOrderCustomCriteria.andProductNameLike("%"+searchProductName+"%");
			}
			String searchProductCode = request.getParameter("productCode");
			if(!StringUtil.isEmpty(searchProductCode)){
				orderDtlCustomCriteria.andProductCodeEqual(searchProductCode);
				refundOrderDtlCustomCriteria.andProductCodeEqual(searchProductCode);
				subDepositOrderCustomCriteria.andProductCodeEqualTo(searchProductCode);
				refundSubDepositOrderCustomCriteria.andProductCodeEqualTo(searchProductCode);
			}
			String dateBegin = request.getParameter("date_begin");
			String dateEnd = request.getParameter("date_end");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(dateBegin)){
				orderDtlCustomCriteria.andPayDateBeginGreaterThanOrEqualTo2(dateBegin+" 00:00:00");
				refundOrderDtlCustomCriteria.andRefundDateGreaterThanOrEqualTo(sf.parse(dateBegin+" 00:00:00"));
				subDepositOrderCustomCriteria.andCombineDepositOrderPayDateGreaterThanOrEqualTo(dateBegin+" 00:00:00");
				refundSubDepositOrderCustomCriteria.andRefundDateAndStatusGreaterThanOrEqualTo(dateBegin+" 00:00:00");
				refundSubDepositOrderCustomCriteria.andStatusEqualTo("6");
			}
			if(!StringUtil.isEmpty(dateEnd)){
				orderDtlCustomCriteria.andPayDateEndLessThanOrEqualTo2(dateEnd+" 23:59:59");
				refundOrderDtlCustomCriteria.andRefundDateLessThanOrEqualTo(sf.parse(dateEnd+" 23:59:59"));
				subDepositOrderCustomCriteria.andCombineDepositOrderPayDateLessThanOrEqualTo(dateEnd+" 23:59:59");
				refundSubDepositOrderCustomCriteria.andRefundDateAndStatusLessThanOrEqualTo(dateEnd+" 23:59:59");
				refundSubDepositOrderCustomCriteria.andStatusEqualTo("6");
			}
			String mchtName = URLDecoder.decode(request.getParameter("mchtName"), "UTF-8");
			if(!StringUtil.isEmpty(mchtName)){
				orderDtlCustomCriteria.andMchtNameLike(mchtName);
				refundOrderDtlCustomCriteria.andMchtNameLike(mchtName);
				subDepositOrderCustomCriteria.andMchtNameLikeTo(mchtName);
				refundSubDepositOrderCustomCriteria.andMchtNameLikeTo(mchtName);
			}
			String productTypeId =request.getParameter("productTypeId");
			if(!StringUtil.isEmpty(productTypeId)){
				orderDtlCustomCriteria.andProductTypeIdEqualTo(productTypeId);
				refundOrderDtlCustomCriteria.andProductTypeIdEqualTo(productTypeId);
				subDepositOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
				refundSubDepositOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}
			String orderTypes=request.getParameter("orderType");
			if (!StringUtil.isEmpty(orderTypes)) {
				orderDtlCustomCriteria.andOrderTypeEqual(orderTypes);
				refundOrderDtlCustomCriteria.andOrderTypeEqual(orderTypes);
			}
			String buttomType = request.getParameter("buttomType");

			String mchtSettleOrderId = request.getParameter("mchtSettleOrderId");
			if(!StringUtil.isEmpty(mchtSettleOrderId)){
				orderDtlCustomCriteria.andMchtSettleOrderIdEqualTo(Integer.valueOf(mchtSettleOrderId));
				refundOrderDtlCustomCriteria.andMchtSettleOrderIdEqualTo(Integer.valueOf(mchtSettleOrderId));
				subDepositOrderCustomCriteria.andMchtSettleOrderIdEqualTo(Integer.valueOf(mchtSettleOrderId));
				refundSubDepositOrderCustomCriteria.andMchtSettleOrderIdEqualTo(Integer.valueOf(mchtSettleOrderId));
			}
			
			String promotionType=request.getParameter("promotionType");
			if (!StringUtil.isEmpty(promotionType)) {
				orderDtlCustomCriteria.andPromotionTypeEqual(promotionType);
				refundOrderDtlCustomCriteria.andPromotionTypeEqual(promotionType);
			}
			List<String> productStatusList = new ArrayList<String>();
			productStatusList.add("2");
			productStatusList.add("3");
			refundOrderDtlCustomCriteria.andProductStatusIn(productStatusList);
			List<OrderDtlCustom> orderDtlCustoms = new ArrayList<>();
			List<OrderDtlCustom> refundOrderDtlCustoms= new ArrayList<>();
			if ((!StringUtil.isEmpty(buttomType)&& buttomType.equals("0")) || StringUtil.isEmpty(buttomType)) {
				 orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample2(orderDtlCustomExample);
				refundOrderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample2(refundOrderDtlCustomExample);
			}

			List<SubDepositOrderCustom> subDepositOrderCustomList = new ArrayList<>();
			List<SubDepositOrderCustom> refundSubDepositOrderCustomList = new ArrayList<>();
			List<OrderDtlCustom> depositOrderDtlCustoms = new ArrayList<>();
			List<OrderDtlCustom> refundDepositOrderDtlCustoms = new ArrayList<>();
			if ((!StringUtil.isEmpty(buttomType)&& buttomType.equals("1")) || StringUtil.isEmpty(buttomType)) {
				if ((StringUtil.isEmpty(orderTypes) || "5".equals(orderTypes)) && (StringUtil.isEmpty(promotionType) || "0".equals(promotionType))) {
					subDepositOrderCustomList = subDepositOrderService.selectSubDepositOrderCustomByExample(subDepositOrderCustomExample);
					refundSubDepositOrderCustomList = subDepositOrderService.selectSubDepositOrderCustomByExample(refundSubDepositOrderCustomExample);
				}
				voluation(subDepositOrderCustomList, depositOrderDtlCustoms);
				voluation(refundSubDepositOrderCustomList, refundDepositOrderDtlCustoms);
			}


			String[] titles = {"日期","商家序号","结算类型","商家名称","子订单编号","商品名称","品牌","货号","商品ID","规格","醒购价","数量","销售商品金额","商家优惠","订单运费","平台优惠","积分优惠","订单客户实付金额","技术服务系数","技术服务费","结算金额","结算单ID","最新状态","类型","结算与实收验证","价格与实收验证","价格与结算验证","订单类型","付款渠道","一级类目","二级类目","三级类目","收货人","联系电话","收货地址","付款时间","快递","母订单号","推广类型","推广分润","推广分润比率","店长权益","分润结算状态","分润结算时间","自营运费"};
			ExcelBean excelBean = new ExcelBean("导出交易商品明细.xls",
					"导出交易商品明细", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			
			SysParamCfgExample e = new SysParamCfgExample();
			e.createCriteria().andParamCodeEqualTo("APP_DISTRIBUTION_RATE");
			List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(e);
			BigDecimal bigDecimal=new BigDecimal(100);
			BigDecimal DISTRIBUTION_RATE=new BigDecimal(sysParamCfgs.get(0).getParamValue()).multiply(bigDecimal);
			
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				String productStatusDesc = "";
				if(orderDtlCustom.getProductStatus() == null || orderDtlCustom.getProductStatus().equals("1")){
					if(orderDtlCustom.getProductStatus() == null){
						productStatusDesc = "未完成";
					}else{
						productStatusDesc = "完成";
					}
				}else if(orderDtlCustom.getProductStatus().equals("2")){
					productStatusDesc = "退款";
				}else if(orderDtlCustom.getProductStatus().equals("3")){
					productStatusDesc = "退货";
				}
				String orderType = "普通订单";
				if(!StringUtils.isEmpty(orderDtlCustom.getSingleProductActivityType()) && orderDtlCustom.getSingleProductActivityType().equals("7")){
					orderType = "砍价免费拿";
				}else if(!StringUtils.isEmpty(orderDtlCustom.getSingleProductActivityType()) && orderDtlCustom.getSingleProductActivityType().equals("8")){
					orderType = "邀请享免单";
				}else if ((!StringUtils.isEmpty(orderDtlCustom.getSingleProductActivityType()) && orderDtlCustom.getSingleProductActivityType().equals("3") )||(!StringUtils.isEmpty(orderDtlCustom.getSingleProductActivityType()) && orderDtlCustom.getSingleProductActivityType().equals("4"))){
				    orderType="秒杀订单";
                }
				BigDecimal settleAmount = new BigDecimal(0);
				if(orderDtlCustom.getSettleAmount()!=null){
					settleAmount = orderDtlCustom.getSettleAmount();
				}
				BigDecimal commissionAmount = new BigDecimal(0);
				if(orderDtlCustom.getCommissionAmount()!=null){
					commissionAmount = orderDtlCustom.getCommissionAmount();
				}
				BigDecimal platformPreferential = new BigDecimal(0);
				if(orderDtlCustom.getPlatformPreferential()!=null){
					platformPreferential = orderDtlCustom.getPlatformPreferential();
				}
				BigDecimal integralPreferential = new BigDecimal(0);
				if(orderDtlCustom.getIntegralPreferential()!=null){
					integralPreferential = orderDtlCustom.getIntegralPreferential();
				}
				BigDecimal payAmount = new BigDecimal(0);
				if(orderDtlCustom.getPayAmount()!=null){
					payAmount = orderDtlCustom.getPayAmount();
				}
				BigDecimal salePrice = new BigDecimal(0);
				if(orderDtlCustom.getSalePrice()!=null){
					salePrice = orderDtlCustom.getSalePrice();
				}
				BigDecimal quantity = new BigDecimal(0);
				if(orderDtlCustom.getQuantity()!=null){
					quantity = new BigDecimal(orderDtlCustom.getQuantity());
				}
				BigDecimal mchtPreferential = new BigDecimal(0);
				if(orderDtlCustom.getMchtPreferential()!=null){
					mchtPreferential = orderDtlCustom.getMchtPreferential();
				}
				String promotionType1="";
				String distributionSettleStatus="";
				if (orderDtlCustom.getPromotionType()!=null && orderDtlCustom.getPromotionType().equals("1")) {
						promotionType1="推广分润";
				}else {
						promotionType1="无";
				}

				if (orderDtlCustom.getDistributionSettleStatus()!=null && orderDtlCustom.getDistributionSettleStatus().equals("1")) {
					distributionSettleStatus = "已结算";
				}else{
					distributionSettleStatus="未结算";
				}

				SimpleDateFormat dfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String[] data = {
						df.format(orderDtlCustom.getPayDate()),
						orderDtlCustom.getMchtCode()+"-"+orderDtlCustom.getMchtId().toString(),
						orderDtlCustom.getMchtType().equals("1")?"SPOP":"POP",
						orderDtlCustom.getCompanyName(),
						orderDtlCustom.getSubOrderCode(),
						orderDtlCustom.getProductName(),
						orderDtlCustom.getBrandName(),
						orderDtlCustom.getArtNo(),
						"`"+orderDtlCustom.getCode(),
						orderDtlCustom.getProductPropDesc(),
						orderDtlCustom.getSalePrice() == null ? "" : orderDtlCustom.getSalePrice().toString(),
						orderDtlCustom.getQuantity() == null ? "" : orderDtlCustom.getQuantity().toString(),
						orderDtlCustom.getQuantity() == null ? "" : orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity())).toString(),
						orderDtlCustom.getMchtPreferential() == null ? "" : orderDtlCustom.getMchtPreferential().toString(),
						orderDtlCustom.getFreight() == null ? "" : orderDtlCustom.getFreight().toString(),
						orderDtlCustom.getPlatformPreferential() == null ? "" : orderDtlCustom.getPlatformPreferential().toString(),
						orderDtlCustom.getIntegralPreferential() == null ? "" : orderDtlCustom.getIntegralPreferential().toString(),
						orderDtlCustom.getPayAmount() == null ? "" : orderDtlCustom.getPayAmount().toString(),
						orderDtlCustom.getMchtType().equals("1")?"-": orderDtlCustom.getPopCommissionRate() == null ? "" : orderDtlCustom.getPopCommissionRate().toString(),
						orderDtlCustom.getCommissionAmount() == null ? "" : orderDtlCustom.getCommissionAmount().toString(),
						orderDtlCustom.getSettleAmount() == null ? "" : orderDtlCustom.getSettleAmount().toString(),
						orderDtlCustom.getMchtSettleOrderId() == null ? "" : orderDtlCustom.getMchtSettleOrderId().toString(),
						productStatusDesc,		
						"收款",
						(settleAmount.add(commissionAmount).subtract(platformPreferential).subtract(integralPreferential).subtract(payAmount)).toString(),
						(salePrice.multiply(quantity).subtract(mchtPreferential).subtract(platformPreferential).subtract(integralPreferential).subtract(payAmount)).toString(),
						((settleAmount.add(commissionAmount)).subtract((salePrice.multiply(quantity)).subtract(mchtPreferential))).toString(),
						orderType,
						orderDtlCustom.getPaymentName(),
						orderDtlCustom.getProducttypeName1(),
						orderDtlCustom.getProducttypeName2(),
						orderDtlCustom.getProducttypeName3(),
						orderDtlCustom.getReceiverName(),
						orderDtlCustom.getReceiverPhone(),
						orderDtlCustom.getReceiverAddress(),
						orderDtlCustom.getPayDate() == null ? "":dfDateFormat.format(orderDtlCustom.getPayDate()),
						orderDtlCustom.getExName()==null?"":orderDtlCustom.getExName()+orderDtlCustom.getExpressNo(),
						orderDtlCustom.getCombineOrdercode(),
						promotionType1==null?"":promotionType1,
						orderDtlCustom.getDistributionAmount() == null ? "" : orderDtlCustom.getDistributionAmount().toString(),
						DISTRIBUTION_RATE.stripTrailingZeros().toPlainString().toString()+"%",	
						orderDtlCustom.getShopownerAmount() ==null? "0" : orderDtlCustom.getShopownerAmount(),
						distributionSettleStatus==null?"":distributionSettleStatus,
						orderDtlCustom.getDistributionSettleDate()==null?"":df.format(orderDtlCustom.getDistributionSettleDate()),
						org.apache.commons.lang.StringUtils.equals(orderDtlCustom.getMchtType(),"1") && org.apache.commons.lang.StringUtils.equals(orderDtlCustom.getIsManageSelf(),"1")?orderDtlCustom.getManageSelfFreight().toString():"--"
			};
				datas.add(data);
			}

			for(OrderDtlCustom refundOrderDtlCustom:refundOrderDtlCustoms){
				String productStatusDesc = "";
				String minus = "-";
				 if(refundOrderDtlCustom.getProductStatus() == null || refundOrderDtlCustom.getProductStatus().equals("1")){
					
				}else{
					if(refundOrderDtlCustom.getProductStatus().equals("2")){
						productStatusDesc = "退款";
					}else{
						productStatusDesc = "退货";
					}
				}
				String orderType = "普通订单";
				if(!StringUtils.isEmpty(refundOrderDtlCustom.getSingleProductActivityType()) && refundOrderDtlCustom.getSingleProductActivityType().equals("7")){
					orderType = "砍价免费拿";
				}else if(!StringUtils.isEmpty(refundOrderDtlCustom.getSingleProductActivityType()) && refundOrderDtlCustom.getSingleProductActivityType().equals("8")){
					orderType = "邀请享免单";
				}else if ((!StringUtils.isEmpty(refundOrderDtlCustom.getSingleProductActivityType()) && refundOrderDtlCustom.getSingleProductActivityType().equals("3")) ||(!StringUtils.isEmpty(refundOrderDtlCustom.getSingleProductActivityType()) && refundOrderDtlCustom.getSingleProductActivityType().equals("4"))){
                    orderType="秒杀订单";
                }
				BigDecimal settleAmount = new BigDecimal(0);
				if(refundOrderDtlCustom.getSettleAmount()!=null){
					settleAmount = refundOrderDtlCustom.getSettleAmount();
				}
				BigDecimal commissionAmount = new BigDecimal(0);
				if(refundOrderDtlCustom.getCommissionAmount()!=null){
					commissionAmount = refundOrderDtlCustom.getCommissionAmount();
				}
				BigDecimal platformPreferential = new BigDecimal(0);
				if(refundOrderDtlCustom.getPlatformPreferential()!=null){
					platformPreferential = refundOrderDtlCustom.getPlatformPreferential();
				}
				BigDecimal integralPreferential = new BigDecimal(0);
				if(refundOrderDtlCustom.getIntegralPreferential()!=null){
					integralPreferential = refundOrderDtlCustom.getIntegralPreferential();
				}
				BigDecimal payAmount = new BigDecimal(0);
				if(refundOrderDtlCustom.getPayAmount()!=null){
					payAmount = refundOrderDtlCustom.getPayAmount();
				}
				BigDecimal salePrice = new BigDecimal(0);
				if(refundOrderDtlCustom.getSalePrice()!=null){
					salePrice = refundOrderDtlCustom.getSalePrice();
				}
				BigDecimal quantity = new BigDecimal(0);
				if(refundOrderDtlCustom.getQuantity()!=null){
					quantity = new BigDecimal(refundOrderDtlCustom.getQuantity());
				}
				BigDecimal mchtPreferential = new BigDecimal(0);
				if(refundOrderDtlCustom.getMchtPreferential()!=null){
					mchtPreferential = refundOrderDtlCustom.getMchtPreferential();
				}
				String promotionType1="";
				String distributionSettleStatus="";
				if (refundOrderDtlCustom.getPromotionType()!=null && refundOrderDtlCustom.getPromotionType().equals("1")) {
					promotionType1="推广分润";
				}else {
					promotionType1="无";
				}
				if (refundOrderDtlCustom.getDistributionSettleStatus()!=null && refundOrderDtlCustom.getDistributionSettleStatus().equals("1")) {
					distributionSettleStatus = "已结算";
				}else{
					distributionSettleStatus="未结算";
				}
				
				SimpleDateFormat dfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String[] data = {
						df.format(refundOrderDtlCustom.getRefundDate()),
						refundOrderDtlCustom.getMchtCode()+"-"+refundOrderDtlCustom.getMchtId().toString(),
						refundOrderDtlCustom.getMchtType().equals("1")?"SPOP":"POP",
						refundOrderDtlCustom.getCompanyName(),
						refundOrderDtlCustom.getSubOrderCode(),
						refundOrderDtlCustom.getProductName(),
						refundOrderDtlCustom.getBrandName(),
						refundOrderDtlCustom.getArtNo(),
						"`"+refundOrderDtlCustom.getCode(),
						refundOrderDtlCustom.getProductPropDesc(),
						refundOrderDtlCustom.getSalePrice() == null ? "" : refundOrderDtlCustom.getSalePrice().toString(),
						refundOrderDtlCustom.getQuantity() == null ? "" : minus+refundOrderDtlCustom.getQuantity().toString(),
						refundOrderDtlCustom.getQuantity() == null ? "" : minus+refundOrderDtlCustom.getSalePrice().multiply(new BigDecimal(refundOrderDtlCustom.getQuantity())).toString(),
						refundOrderDtlCustom.getMchtPreferential() == null ? "" : minus+refundOrderDtlCustom.getMchtPreferential().toString(),
						refundOrderDtlCustom.getFreight() == null ? "" : minus+refundOrderDtlCustom.getFreight().toString(),
						refundOrderDtlCustom.getPlatformPreferential() == null ? "" : minus+refundOrderDtlCustom.getPlatformPreferential().toString(),
						refundOrderDtlCustom.getIntegralPreferential() == null ? "" : minus+refundOrderDtlCustom.getIntegralPreferential().toString(),
						refundOrderDtlCustom.getPayAmount() == null ? "" : minus+refundOrderDtlCustom.getPayAmount().toString(),
						refundOrderDtlCustom.getMchtType().equals("1")?"-": refundOrderDtlCustom.getPopCommissionRate() == null ? "" : refundOrderDtlCustom.getPopCommissionRate().toString(),
						refundOrderDtlCustom.getCommissionAmount() == null ? "" : minus+refundOrderDtlCustom.getCommissionAmount().toString(),
						refundOrderDtlCustom.getSettleAmount() == null ? "" : minus+refundOrderDtlCustom.getSettleAmount().toString(),
						refundOrderDtlCustom.getMchtSettleOrderId() == null ? "" : refundOrderDtlCustom.getMchtSettleOrderId().toString(),
						productStatusDesc,
						"退款或退货",
						(settleAmount.add(commissionAmount).subtract(platformPreferential).subtract(integralPreferential).subtract(payAmount)).toString(),
						(salePrice.multiply(quantity).subtract(mchtPreferential).subtract(platformPreferential).subtract(integralPreferential).subtract(payAmount)).toString(),
						((settleAmount.add(commissionAmount)).subtract((salePrice.multiply(quantity)).subtract(mchtPreferential))).toString(),
						orderType,
						"",
						refundOrderDtlCustom.getProducttypeName1(),
						refundOrderDtlCustom.getProducttypeName2(),
						refundOrderDtlCustom.getProducttypeName3(),
						refundOrderDtlCustom.getReceiverName(),
						refundOrderDtlCustom.getReceiverPhone(),
						refundOrderDtlCustom.getReceiverAddress(),
						refundOrderDtlCustom.getPayDate() == null ? "":dfDateFormat.format(refundOrderDtlCustom.getPayDate()),
						refundOrderDtlCustom.getExName()==null?"":refundOrderDtlCustom.getExName()+refundOrderDtlCustom.getExpressNo(),
						refundOrderDtlCustom.getCombineOrdercode(),
						promotionType1==null?"":promotionType1,
						refundOrderDtlCustom.getDistributionAmount() == null ? "" : refundOrderDtlCustom.getDistributionAmount().toString(),
						DISTRIBUTION_RATE.stripTrailingZeros().toPlainString().toString()+"%",
						refundOrderDtlCustom.getShopownerAmount() ==null? "0" : refundOrderDtlCustom.getShopownerAmount(),
						distributionSettleStatus==null?"":distributionSettleStatus,
						refundOrderDtlCustom.getDistributionSettleDate()==null?"":df.format(refundOrderDtlCustom.getDistributionSettleDate()),
						org.apache.commons.lang.StringUtils.equals(refundOrderDtlCustom.getMchtType(),"1") && org.apache.commons.lang.StringUtils.equals(refundOrderDtlCustom.getIsManageSelf(),"1")?refundOrderDtlCustom.getManageSelfFreight().toString():"--"

				};
				datas.add(data);
			}

			for(OrderDtlCustom orderDtlCustom:depositOrderDtlCustoms){
				String productStatusDesc = "";
				if("已完成".equals(orderDtlCustom.getOrderType())){
					productStatusDesc = "完成";
				}else{
					productStatusDesc = "未完成";
				}

				SimpleDateFormat dfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String[] data = {
						orderDtlCustom.getPayDate() == null ?"":df.format(orderDtlCustom.getPayDate()),
						orderDtlCustom.getMchtCode()+"-"+orderDtlCustom.getMchtId().toString(),
						("1").equals(orderDtlCustom.getMchtType())?"SPOP":"POP",
						orderDtlCustom.getCompanyName() == null?"":orderDtlCustom.getCompanyName(),
						orderDtlCustom.getSubOrderCode() == null?"":orderDtlCustom.getSubOrderCode(),
						orderDtlCustom.getProductName() == null?"":orderDtlCustom.getProductName(),
						orderDtlCustom.getBrandName() == null?"":orderDtlCustom.getBrandName(),
						orderDtlCustom.getArtNo() == null?"":orderDtlCustom.getArtNo(),
						orderDtlCustom.getCode() == null?"":"`"+orderDtlCustom.getCode(),
						orderDtlCustom.getProductPropDesc(),
						orderDtlCustom.getSalePrice() == null ? "" : orderDtlCustom.getSalePrice().toString(),
						orderDtlCustom.getQuantity() == null ? "" : orderDtlCustom.getQuantity().toString(),
						orderDtlCustom.getQuantity() == null ? "" : orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity())).toString(),
						orderDtlCustom.getMchtPreferential() == null ? "" : orderDtlCustom.getMchtPreferential().toString(),
						orderDtlCustom.getFreight() == null ? "" : orderDtlCustom.getFreight().toString(),
						orderDtlCustom.getPlatformPreferential() == null ? "" : orderDtlCustom.getPlatformPreferential().toString(),
						orderDtlCustom.getIntegralPreferential() == null ? "" : orderDtlCustom.getIntegralPreferential().toString(),
						orderDtlCustom.getPayAmount() == null ? "" : orderDtlCustom.getPayAmount().toString(),
						("1").equals(orderDtlCustom.getMchtType())?"-": orderDtlCustom.getPopCommissionRate() == null ? "" : orderDtlCustom.getPopCommissionRate().toString(),
						orderDtlCustom.getCommissionAmount() == null ? "" : orderDtlCustom.getCommissionAmount().toString(),
						orderDtlCustom.getSettleAmount() == null ? "" : orderDtlCustom.getSettleAmount().toString(),
						orderDtlCustom.getMchtSettleOrderId() == null ? "" : orderDtlCustom.getMchtSettleOrderId().toString(),
						productStatusDesc,
						"收款",
						"",
						"",
						"",
						"预售订单",
						orderDtlCustom.getPaymentName() == null ? "" :orderDtlCustom.getPaymentName(),
						"",
						"",
						"",
						orderDtlCustom.getReceiverName()== null?"":orderDtlCustom.getReceiverName(),
						"",
						"",
						orderDtlCustom.getPayDate() == null ? "":dfDateFormat.format(orderDtlCustom.getPayDate()),
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"--"
				};
				datas.add(data);
			}

			for(OrderDtlCustom orderDtlCustom:refundDepositOrderDtlCustoms){
				String productStatusDesc = "";
				if("已完成".equals(orderDtlCustom.getOrderType())){
					productStatusDesc = "完成";
				}else{
					productStatusDesc = "未完成";
				}

				SimpleDateFormat dfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String[] data = {
						orderDtlCustom.getRefundDate() == null?"":df.format(orderDtlCustom.getRefundDate()),
						orderDtlCustom.getMchtCode()+"-"+orderDtlCustom.getMchtId().toString(),
						("1").equals(orderDtlCustom.getMchtType())?"SPOP":"POP",
						orderDtlCustom.getCompanyName() == null?"":orderDtlCustom.getCompanyName(),
						orderDtlCustom.getSubOrderCode() == null?"":orderDtlCustom.getSubOrderCode(),
						orderDtlCustom.getProductName() == null?"":orderDtlCustom.getProductName(),
						orderDtlCustom.getBrandName() == null?"":orderDtlCustom.getBrandName(),
						orderDtlCustom.getArtNo() == null?"":orderDtlCustom.getArtNo(),
						orderDtlCustom.getCode() == null?"":"`"+orderDtlCustom.getCode(),
						orderDtlCustom.getProductPropDesc(),
						orderDtlCustom.getSalePrice() == null ? "" : orderDtlCustom.getSalePrice().toString(),
						orderDtlCustom.getQuantity() == null ? "" : orderDtlCustom.getQuantity().toString(),
						orderDtlCustom.getQuantity() == null ? "" : orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity())).toString(),
						orderDtlCustom.getMchtPreferential() == null ? "" : orderDtlCustom.getMchtPreferential().toString(),
						orderDtlCustom.getFreight() == null ? "" : orderDtlCustom.getFreight().toString(),
						orderDtlCustom.getPlatformPreferential() == null ? "" : orderDtlCustom.getPlatformPreferential().toString(),
						orderDtlCustom.getIntegralPreferential() == null ? "" : orderDtlCustom.getIntegralPreferential().toString(),
						orderDtlCustom.getPayAmount() == null ? "" : "-"+orderDtlCustom.getPayAmount().toString(),
						("1").equals(orderDtlCustom.getMchtType())?"-": orderDtlCustom.getPopCommissionRate() == null ? "" : orderDtlCustom.getPopCommissionRate().toString(),
						orderDtlCustom.getCommissionAmount() == null ? "" : "-"+orderDtlCustom.getCommissionAmount().toString(),
						orderDtlCustom.getSettleAmount() == null ? "" : "-"+orderDtlCustom.getSettleAmount().toString(),
						orderDtlCustom.getMchtSettleOrderId() == null ? "" : orderDtlCustom.getMchtSettleOrderId().toString(),
						productStatusDesc,
						"退款或退货",
						"",
						"",
						"",
						"预售订单",
						"",
						"",
						"",
						"",
						orderDtlCustom.getReceiverName() == null?"":orderDtlCustom.getReceiverName(),
						"",
						"",
						orderDtlCustom.getPayDate() == null ? "":dfDateFormat.format(orderDtlCustom.getPayDate()),
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"--"
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
	 * POP每月收款情况列表
	 * @param requesth
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/list.shtml")
	public ModelAndView mchtMonthlyCollectionsList(HttpServletRequest request) {
		String rtPage = "/popDealOrderDtl/mchtMonthlyCollections/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(request.getParameter("year")) && !StringUtils.isEmpty(request.getParameter("month"))){
			resMap.put("year", request.getParameter("year"));
			resMap.put("month", request.getParameter("month"));
		}else{
			Date now = new Date();
			Date preDate = DateUtil.getPreDate(now);
			String year = String.valueOf(preDate).substring(0,4);
			String month = String.valueOf(preDate).substring(5, 7);
			resMap.put("year", year);
			resMap.put("month", month);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * POP每月收款情况列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtMonthlyCollectionsData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowDate = df.format(now);
			String nowYear = nowDate.substring(0,4);
			String nowMonth = nowDate.substring(5, 7);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchYear = request.getParameter("year");
			String searchMonth = request.getParameter("month");
			String name = request.getParameter("name");
			if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
				searchYear = nowYear;
				searchMonth = nowMonth;
			}
			if(searchYear.equals(nowYear) && searchMonth.equals(nowMonth)){
				HashMap<String,String> paramMap = new HashMap<String,String>();
				if(!StringUtils.isEmpty(searchMchtCode)){
					paramMap.put("mchtCode", searchMchtCode);
				}
				paramMap.put("collectionDate", searchYear+"-"+searchMonth);
				paramMap.put("mchtType", "2");
				paramMap.put("name", name);
				paramMap.put("beginDate", searchYear+"-"+searchMonth+"-01");
				paramMap.put("endDate", nowDate);
				List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.getMonthlyCollectionsByMonth(paramMap);
				List<MchtMonthlyCollectionsCustom> result = new ArrayList<MchtMonthlyCollectionsCustom>();
				if (page.getPage() * page.getPagesize() > mchtMonthlyCollectionsCustoms.size()) {
					result = (mchtMonthlyCollectionsCustoms.subList(page.getPagesize() * (page.getPage() - 1), mchtMonthlyCollectionsCustoms.size()));
				} else {
					result = (mchtMonthlyCollectionsCustoms.subList(page.getPagesize() * (page.getPage() - 1), page.getPage() * page.getPagesize()));
				}
				resMap.put("Rows", result);
				resMap.put("Total", mchtMonthlyCollectionsCustoms.size());
			}else{
				MchtMonthlyCollectionsCustomExample example = new MchtMonthlyCollectionsCustomExample();
				MchtMonthlyCollectionsCustomExample.MchtMonthlyCollectionsCustomCriteria criteria = example.createCriteria();
				if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
					criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
				}else{
					criteria.andDelFlagEqualTo("0");
				}
				criteria.andMchtTypeEqualTo("2");
				if(!StringUtils.isEmpty(searchMchtCode)){
					criteria.andMchtCodeEqualTo(searchMchtCode);
				}
				if(!StringUtils.isEmpty(name)){
					criteria.andNameLike("%"+name+"%");
				}
				criteria.andCollectionDateEqualTo(searchYear+"-"+searchMonth);
				totalCount = mchtMonthlyCollectionsService.countByExample(example);
				example.setLimitStart(page.getLimitStart());
				example.setLimitSize(page.getLimitSize());
				List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.selectByExample(example);
				resMap.put("Rows", mchtMonthlyCollectionsCustoms);
				resMap.put("Total", totalCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * POP每月收款情况合计
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/count.shtml")
	public ModelAndView mchtMonthlyCollectionsCount(HttpServletRequest request) {
		String rtPage = "/popDealOrderDtl/mchtMonthlyCollections/count";
		List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = new ArrayList<MchtMonthlyCollectionsCustom>();
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowDate = df.format(now);
		String nowYear = nowDate.substring(0,4);
		String nowMonth = nowDate.substring(5, 7);
		String searchMchtCode = request.getParameter("mchtCode");
		String searchYear = request.getParameter("year");
		String searchMonth = request.getParameter("month");
		String name = request.getParameter("name");
		if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
			searchYear = nowYear;
			searchMonth = nowMonth;
		}
		if(searchYear.equals(nowYear) && searchMonth.equals(nowMonth)){
			HashMap<String,String> paramMap = new HashMap<String,String>();
			if(!StringUtils.isEmpty(searchMchtCode)){
				paramMap.put("mchtCode", searchMchtCode);
			}
			paramMap.put("collectionDate", searchYear+"-"+searchMonth);
			paramMap.put("mchtType", "2");
			paramMap.put("name", name);
			paramMap.put("beginDate", searchYear+"-"+searchMonth+"-01");
			paramMap.put("endDate", nowDate);
			mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.getMonthlyCollectionsByMonth(paramMap);
		}else{
			MchtMonthlyCollectionsCustomExample example = new MchtMonthlyCollectionsCustomExample();
			MchtMonthlyCollectionsCustomExample.MchtMonthlyCollectionsCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			criteria.andMchtTypeEqualTo("2");
			if(!StringUtils.isEmpty(searchMchtCode)){
				criteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtils.isEmpty(name)){
				criteria.andNameLike("%"+name+"%");
			}
			criteria.andCollectionDateEqualTo(searchYear+"-"+searchMonth);
			mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.selectByExample(example);
		}
		BigDecimal beginUnpay = new BigDecimal(0);
		BigDecimal orderAmount = new BigDecimal(0);
		BigDecimal settleAmount = new BigDecimal(0);
		BigDecimal returnAmount = new BigDecimal(0);
		BigDecimal returnOrderAmount = new BigDecimal(0);
		BigDecimal refundAmount = new BigDecimal(0);
		BigDecimal needPayAmount = new BigDecimal(0);
		BigDecimal payAmount = new BigDecimal(0);
		BigDecimal endUnpay = new BigDecimal(0);
		for(MchtMonthlyCollectionsCustom mchtMonthlyCollectionsCustom:mchtMonthlyCollectionsCustoms){
			beginUnpay = beginUnpay.add(mchtMonthlyCollectionsCustom.getBeginUnpay());
			orderAmount = orderAmount.add(mchtMonthlyCollectionsCustom.getOrderAmount());
			settleAmount = settleAmount.add(mchtMonthlyCollectionsCustom.getSettleAmount());
			returnAmount = returnAmount.add(mchtMonthlyCollectionsCustom.getReturnAmount());
			returnOrderAmount = returnOrderAmount.add(mchtMonthlyCollectionsCustom.getReturnOrderAmount());
			refundAmount = refundAmount.add(mchtMonthlyCollectionsCustom.getRefundAmount());
			needPayAmount = needPayAmount.add(mchtMonthlyCollectionsCustom.getNeedPayAmount());
			payAmount = payAmount.add(mchtMonthlyCollectionsCustom.getPayAmount());
			endUnpay = endUnpay.add(mchtMonthlyCollectionsCustom.getEndUnpay());
		}
		resMap.put("beginUnpay", beginUnpay);
		resMap.put("orderAmount", orderAmount);
		resMap.put("settleAmount", settleAmount);
		resMap.put("returnAmount", returnAmount);
		resMap.put("returnOrderAmount", returnOrderAmount);
		resMap.put("refundAmount", refundAmount);
		resMap.put("needPayAmount", needPayAmount);
		resMap.put("payAmount", payAmount);
		resMap.put("endUnpay", endUnpay);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 导出汇总表excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/popDealOrderDtl/mchtMonthlyCollections/exportTotal.shtml")
	public void exportTotal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = new ArrayList<MchtMonthlyCollectionsCustom>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowDate = df.format(now);
			String nowYear = nowDate.substring(0,4);
			String nowMonth = nowDate.substring(5, 7);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchYear = request.getParameter("year");
			String searchMonth = request.getParameter("month");
			String name = request.getParameter("name");
			if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
				searchYear = nowYear;
				searchMonth = nowMonth;
			}
			if(searchYear.equals(nowYear) && searchMonth.equals(nowMonth)){
				HashMap<String,String> paramMap = new HashMap<String,String>();
				if(!StringUtils.isEmpty(searchMchtCode)){
					paramMap.put("mchtCode", searchMchtCode);
				}
				paramMap.put("collectionDate", searchYear+"-"+searchMonth);
				paramMap.put("mchtType", "2");
				paramMap.put("name", name);
				paramMap.put("beginDate", searchYear+"-"+searchMonth+"-01");
				paramMap.put("endDate", nowDate);
				mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.getMonthlyCollectionsByMonth(paramMap);
			}else{
				MchtMonthlyCollectionsCustomExample example = new MchtMonthlyCollectionsCustomExample();
				MchtMonthlyCollectionsCustomExample.MchtMonthlyCollectionsCustomCriteria criteria = example.createCriteria();
				if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
					criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
				}else{
					criteria.andDelFlagEqualTo("0");
				}
				criteria.andMchtTypeEqualTo("2");
				if(!StringUtils.isEmpty(searchMchtCode)){
					criteria.andMchtCodeEqualTo(searchMchtCode);
				}
				if(!StringUtils.isEmpty(name)){
					criteria.andNameLike("%"+name+"%");
				}
				criteria.andCollectionDateEqualTo(searchYear+"-"+searchMonth);
				mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.selectByExample(example);
			}
			String[] titles = { "商家序号", "商家简称", "店铺名称","商家公司名称","期初结欠","本月代收","本月代收应结","本月代退","本月代退应扣","直赔单应扣","本月总应付","本月付款","期末实欠"};
			ExcelBean excelBean = new ExcelBean("导出POP每月收款情况汇总表.xls",
					"导出POP每月收款情况汇总表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtMonthlyCollectionsCustom mchtMonthlyCollectionsCustom:mchtMonthlyCollectionsCustoms){
				String[] data = {
					mchtMonthlyCollectionsCustom.getMchtCode(),
					mchtMonthlyCollectionsCustom.getMchtShortName(),
					mchtMonthlyCollectionsCustom.getMchtShopName(),
					mchtMonthlyCollectionsCustom.getMchtCompanyName(),
					mchtMonthlyCollectionsCustom.getBeginUnpay()==null?"":mchtMonthlyCollectionsCustom.getBeginUnpay().toString(),
					mchtMonthlyCollectionsCustom.getOrderAmount()==null?"":mchtMonthlyCollectionsCustom.getOrderAmount().toString(),
					mchtMonthlyCollectionsCustom.getSettleAmount()==null?"":mchtMonthlyCollectionsCustom.getSettleAmount().toString(),
					mchtMonthlyCollectionsCustom.getReturnAmount()==null?"":mchtMonthlyCollectionsCustom.getReturnAmount().toString(),
					mchtMonthlyCollectionsCustom.getReturnOrderAmount()==null?"":mchtMonthlyCollectionsCustom.getReturnOrderAmount().toString(),
					mchtMonthlyCollectionsCustom.getRefundAmount()==null?"":mchtMonthlyCollectionsCustom.getRefundAmount().toString(),
					mchtMonthlyCollectionsCustom.getNeedPayAmount()==null?"":mchtMonthlyCollectionsCustom.getNeedPayAmount().toString(),
					mchtMonthlyCollectionsCustom.getPayAmount()==null?"":mchtMonthlyCollectionsCustom.getPayAmount().toString(),
					mchtMonthlyCollectionsCustom.getEndUnpay()==null?"":mchtMonthlyCollectionsCustom.getEndUnpay().toString()
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
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/popDealOrderDtl/mchtMonthlyCollections/exportByMchtId.shtml")
	public void exportByMchtId(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
			OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria = orderDtlCustomExample.createCriteria();
			orderDtlCustomCriteria.andDelFlagEqualTo("0");
			orderDtlCustomCriteria.andCombineOrderStatusEqualTo("1");//母订单已付款
			orderDtlCustomCriteria.andMchtTypeEqualTo("2");//POP
			String searchMchtId = request.getParameter("mchtId");
			if(!StringUtil.isEmpty(searchMchtId)){
				orderDtlCustomCriteria.andMchtIdEqualTo(Integer.parseInt(searchMchtId));
			}
			String dateBegin = request.getParameter("date_begin");
			if(!StringUtil.isEmpty(dateBegin)){
				orderDtlCustomCriteria.andPayDateBeginGreaterThanOrEqualTo(dateBegin+" 00:00:00");
			}
			String dateEnd = request.getParameter("date_end");
			if(!StringUtil.isEmpty(dateEnd)){
				orderDtlCustomCriteria.andPayDateEndLessThanOrEqualTo(dateEnd+" 23:59:59");
			}
			orderDtlCustomCriteria.andProductStatusIsNullOrComplete();
			List<OrderDtlCustom> acceptOrderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
			
			OrderDtlCustomExample odcExample = new OrderDtlCustomExample();
			OrderDtlCustomExample.OrderDtlCustomCriteria odcCriteria = odcExample.createCriteria();
			odcCriteria.andDelFlagEqualTo("0");
			odcCriteria.andCombineOrderStatusEqualTo("1");//母订单已付款
			odcCriteria.andMchtTypeEqualTo("2");//POP
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(searchMchtId)){
				odcCriteria.andMchtIdEqualTo(Integer.parseInt(searchMchtId));
			}
			if(!StringUtil.isEmpty(dateBegin)){
				odcCriteria.andRefundDateGreaterThanOrEqualTo(sf.parse(dateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(dateEnd)){
				odcCriteria.andRefundDateLessThanOrEqualTo(sf.parse(dateEnd+" 23:59:59"));
			}
			List<String> productStatusList = new ArrayList<String>();
			productStatusList.add("2");
			productStatusList.add("3");
			odcCriteria.andProductStatusIn(productStatusList);
			List<OrderDtlCustom> refundOrderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(odcExample);
			acceptOrderDtlCustoms.addAll(refundOrderDtlCustoms);
			String[] titles = { "类型", "子订单编号", "日期","最新状态","商品名称","品牌","货号","规格","醒购价","数量","销售商品金额","商家优惠","平台优惠","积分优惠","订单客户实付金额","商家序号","商家名称","技术服务系数","POP技术服务费","POP结算金额","POP结算单ID","直赔单金额","付款金额"};
			ExcelBean excelBean = new ExcelBean("导出POP交易商品明细.xls",
					"导出POP交易商品明细", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for(OrderDtlCustom orderDtlCustom:acceptOrderDtlCustoms){
				String typeDesc = "";
				String dateStr = "";
				String productStatusDesc = "";
				String minus = "";
				if(orderDtlCustom.getProductStatus() == null || orderDtlCustom.getProductStatus().equals("1")){
					typeDesc = "收款";
					dateStr = df.format(orderDtlCustom.getPayDate());
					if(orderDtlCustom.getProductStatus() == null){
						productStatusDesc = "未完成";
					}else{
						productStatusDesc = "完成";
					}
				}else{
					typeDesc = "退款或退货";
					dateStr = df.format(orderDtlCustom.getRefundDate());
					if(orderDtlCustom.getProductStatus().equals("2")){
						productStatusDesc = "退款";
					}else{
						productStatusDesc = "退货";
					}
					minus = "-";
				}
				String[] data = {
						typeDesc,
						orderDtlCustom.getSubOrderCode(),
						dateStr,
						productStatusDesc,
						orderDtlCustom.getProductName(),
						orderDtlCustom.getBrandName(),
						orderDtlCustom.getArtNo(),
						orderDtlCustom.getProductPropDesc(),
						orderDtlCustom.getSalePrice()== null ? "" : orderDtlCustom.getSalePrice().toString(),
						orderDtlCustom.getQuantity() == null ? "" : minus+orderDtlCustom.getQuantity().toString(),
						orderDtlCustom.getQuantity() == null ? "" : minus+orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity())).toString(),
						orderDtlCustom.getMchtPreferential() == null ? "" : minus+orderDtlCustom.getMchtPreferential().toString(),
						orderDtlCustom.getPlatformPreferential() == null ? "" : minus+orderDtlCustom.getPlatformPreferential().toString(),
						orderDtlCustom.getIntegralPreferential() == null ? "" : minus+orderDtlCustom.getIntegralPreferential().toString(),
						orderDtlCustom.getPayAmount() == null ? "" : minus+orderDtlCustom.getPayAmount().toString(),
						orderDtlCustom.getMchtCode(),
						orderDtlCustom.getMchtShortName(),
						orderDtlCustom.getPopCommissionRate() == null ? "" : orderDtlCustom.getPopCommissionRate().toString(),
						orderDtlCustom.getCommissionAmount() == null ? "" : minus+orderDtlCustom.getCommissionAmount().toString(),
						orderDtlCustom.getSettleAmount() == null ? "" : minus+orderDtlCustom.getSettleAmount().toString(),
						orderDtlCustom.getMchtSettleOrderId() == null ? "" : orderDtlCustom.getMchtSettleOrderId().toString(),
						"",
						""
			};
				datas.add(data);
			}
			//获取直赔单
			CustomerServiceOrderCustomExample customerServiceOrderCustomExample=new CustomerServiceOrderCustomExample();
			CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria customerServiceOrderCustomCriteria=customerServiceOrderCustomExample.createCriteria();
			customerServiceOrderCustomCriteria.andDelFlagEqualTo("0");
			customerServiceOrderCustomExample.setOrderByClause("a.id desc");
			
			if(!StringUtil.isEmpty(searchMchtId)){
				customerServiceOrderCustomCriteria.andMchtIdEqualTo(Integer.parseInt(searchMchtId));
			}
		
			customerServiceOrderCustomCriteria.andServiceTypeEqualTo("D");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				customerServiceOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				customerServiceOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			
			List<CustomerServiceOrderCustom> customerServiceOrderCustoms=customerServiceOrderService.selectCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
			for(CustomerServiceOrderCustom customerServiceOrderCustom:customerServiceOrderCustoms){
				String[] data = {
					"直赔单",
					customerServiceOrderCustom.getSubOrderCode(),
					df.format(customerServiceOrderCustom.getCreateDate()),
					"","","","","","","","","","","","",
					customerServiceOrderCustom.getMchtCode(),
					customerServiceOrderCustom.getShortName(),
					"","","",
					customerServiceOrderCustom.getMchtSettleOrderId()==null?"":customerServiceOrderCustom.getMchtSettleOrderId().toString(),
					customerServiceOrderCustom.getAmount()==null?"":customerServiceOrderCustom.getAmount().toString(),
					""		
				};
				datas.add(data);
			}
			String mchtMonthlyCollectionsId = request.getParameter("mchtMonthlyCollectionsId");
			MchtMonthlyCollections mchtMonthlyCollections = mchtMonthlyCollectionsService.selectByPrimaryKey(Integer.parseInt(mchtMonthlyCollectionsId));
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtMonthlyCollections.getMchtId());
			String[] data = {
				"本月付款",
				"",
				mchtMonthlyCollections.getCollectionDate(),
				"","","","","","","","","","","","",
				mchtInfo.getMchtCode(),
				mchtInfo.getShortName(),
				"","","",
				"",//结算单ID
				"",
				mchtMonthlyCollections.getPayAmount()==null?"":mchtMonthlyCollections.getPayAmount().toString()
			};
			datas.add(data);
			String[] beginUnpayData = {
					"期初欠款：",
					mchtMonthlyCollections.getBeginUnpay()==null?"":mchtMonthlyCollections.getBeginUnpay().toString(),
			};
			datas.add(beginUnpayData);
			String[] needPayAmountData = {
					"本期应付：",
					mchtMonthlyCollections.getNeedPayAmount()==null?"":mchtMonthlyCollections.getNeedPayAmount().toString(),
			};
			datas.add(needPayAmountData);
			String[] payAmountData = {
					"本期已付：",
					mchtMonthlyCollections.getPayAmount()==null?"":mchtMonthlyCollections.getPayAmount().toString(),
			};
			datas.add(payAmountData);
			String[] endUnpayData = {
					"期末欠款：",
					mchtMonthlyCollections.getEndUnpay()==null?"":mchtMonthlyCollections.getEndUnpay().toString(),
			};
			datas.add(endUnpayData);
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 每月POP收款及应付列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/monthList.shtml")
	public ModelAndView monthList(HttpServletRequest request) {
		String rtPage = "/popDealOrderDtl/mchtMonthlyCollections/monthList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(request.getParameter("year")) && !StringUtils.isEmpty(request.getParameter("month"))){
			resMap.put("year", request.getParameter("year"));
			resMap.put("month", request.getParameter("month"));
		}else{
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = df.format(now);
			String year = nowDate.substring(0,4);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String month = format.format(c.getTime());
			resMap.put("year", year);
			resMap.put("month", month);
		}
		 ProductTypeExample productTypeExample = new ProductTypeExample();
		 ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		 productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		 List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		 resMap.put("productTypeList", productTypeList); //1级类目
		 
		 String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		 if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			  String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			   if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 每月POP收款及应付列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/monthData.shtml")
	@ResponseBody
	public Map<String, Object> monthData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowDate = df.format(now);
			String nowYear = nowDate.substring(0,4);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String searchYear = request.getParameter("year");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String nowMonth = format.format(c.getTime());
			
			String searchMonth = request.getParameter("month");
			if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
				searchYear = nowYear;
				searchMonth = nowMonth;
			}
			MchtMonthlyCollectionsCustomExample example = new MchtMonthlyCollectionsCustomExample();
			MchtMonthlyCollectionsCustomExample.MchtMonthlyCollectionsCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			criteria.andMchtTypeEqualTo("2");
			if(!StringUtils.isEmpty(searchMchtCode)){
				criteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtils.isEmpty(searchName)){
				criteria.andNameLikeTo(searchName);
			}
			if (!StringUtils.isEmpty(request.getParameter("productTypeId"))) {
				criteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			criteria.andCollectionDateEqualTo(searchYear+"-"+searchMonth);
			totalCount = mchtMonthlyCollectionsService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.selectByExample(example);
			resMap.put("Rows", mchtMonthlyCollectionsCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 每月POP收款及应付导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/popDealOrderDtl/mchtMonthlyCollections/monthExport.shtml")
	public void monthExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowDate = df.format(now);
			String nowYear = nowDate.substring(0,4);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String searchYear = request.getParameter("year");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String nowMonth = format.format(c.getTime());
			
			String searchMonth = request.getParameter("month");
			if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
				searchYear = nowYear;
				searchMonth = nowMonth;
			}
			MchtMonthlyCollectionsCustomExample example = new MchtMonthlyCollectionsCustomExample();
			MchtMonthlyCollectionsCustomExample.MchtMonthlyCollectionsCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			criteria.andMchtTypeEqualTo("2");
			if(!StringUtils.isEmpty(searchMchtCode)){
				criteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtils.isEmpty(searchName)){
				criteria.andNameLikeTo(searchName);
			}
			if (!StringUtils.isEmpty(request.getParameter("productTypeId"))) {
				criteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			criteria.andCollectionDateEqualTo(searchYear+"-"+searchMonth);
			List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.selectByExample(example);
			String[] titles = {"商家序号","公司名称","店铺名称","期初结欠","数量","商品金额","商家优惠","平台优惠","积分优惠","订单客户实付","POP技术服务费","POP结算金额","直赔单扣款","实付款","抵缴保证金","折让","期末应付","折让原因"};
			ExcelBean excelBean = new ExcelBean("每月POP收款及应付_"+searchYear+searchMonth+".xls","每月POP收款及应付_"+searchYear+searchMonth, titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtMonthlyCollectionsCustom mchtMonthlyCollectionsCustom:mchtMonthlyCollectionsCustoms){
				Integer number = mchtMonthlyCollectionsCustom.getCollectionProductCount()-mchtMonthlyCollectionsCustom.getRefundProductCount();
				String[] data = {
					mchtMonthlyCollectionsCustom.getMchtCode(),
					mchtMonthlyCollectionsCustom.getMchtCompanyName(),
					mchtMonthlyCollectionsCustom.getMchtShopName(),
					mchtMonthlyCollectionsCustom.getBeginUnpay()==null?"":mchtMonthlyCollectionsCustom.getBeginUnpay().toString(),
					number.toString(),
					mchtMonthlyCollectionsCustom.getCollectionProductAmount()==null?"":mchtMonthlyCollectionsCustom.getRefundProductAmount()==null?mchtMonthlyCollectionsCustom.getCollectionProductAmount().toString():mchtMonthlyCollectionsCustom.getCollectionProductAmount().subtract(mchtMonthlyCollectionsCustom.getRefundProductAmount()).toString(),
					mchtMonthlyCollectionsCustom.getCollectionMchtPreferential()==null?"":mchtMonthlyCollectionsCustom.getRefundMchtPreferential()==null?mchtMonthlyCollectionsCustom.getCollectionMchtPreferential().toString():mchtMonthlyCollectionsCustom.getCollectionMchtPreferential().subtract(mchtMonthlyCollectionsCustom.getRefundMchtPreferential()).toString(),
					mchtMonthlyCollectionsCustom.getCollectionPlatformPreferential()==null?"":mchtMonthlyCollectionsCustom.getRefundPlatformPreferential()==null?mchtMonthlyCollectionsCustom.getCollectionPlatformPreferential().toString():mchtMonthlyCollectionsCustom.getCollectionPlatformPreferential().subtract(mchtMonthlyCollectionsCustom.getRefundPlatformPreferential()).toString(),
					mchtMonthlyCollectionsCustom.getCollectionIntegralPreferential()==null?"":mchtMonthlyCollectionsCustom.getRefundIntegralPreferential()==null?mchtMonthlyCollectionsCustom.getCollectionIntegralPreferential().toString():mchtMonthlyCollectionsCustom.getCollectionIntegralPreferential().subtract(mchtMonthlyCollectionsCustom.getRefundIntegralPreferential()).toString(),
					mchtMonthlyCollectionsCustom.getOrderAmount()==null?"":mchtMonthlyCollectionsCustom.getReturnAmount()==null?mchtMonthlyCollectionsCustom.getOrderAmount().toString():mchtMonthlyCollectionsCustom.getOrderAmount().subtract(mchtMonthlyCollectionsCustom.getReturnAmount()).toString(),
					mchtMonthlyCollectionsCustom.getCollectionCommissionAmount()==null?"":mchtMonthlyCollectionsCustom.getRefundCommissionAmount()==null?mchtMonthlyCollectionsCustom.getCollectionCommissionAmount().toString():mchtMonthlyCollectionsCustom.getCollectionCommissionAmount().subtract(mchtMonthlyCollectionsCustom.getRefundCommissionAmount()).toString(),
					mchtMonthlyCollectionsCustom.getSettleAmount()==null?"":mchtMonthlyCollectionsCustom.getReturnOrderAmount()==null?mchtMonthlyCollectionsCustom.getSettleAmount().toString():mchtMonthlyCollectionsCustom.getSettleAmount().subtract(mchtMonthlyCollectionsCustom.getReturnOrderAmount()).toString(),
					mchtMonthlyCollectionsCustom.getRefundAmount()==null?"":mchtMonthlyCollectionsCustom.getRefundAmount().toString(),
					mchtMonthlyCollectionsCustom.getPayAmount()==null?"":mchtMonthlyCollectionsCustom.getPayAmount().toString(),
					mchtMonthlyCollectionsCustom.getDeductionDepositTotal()==null?"":mchtMonthlyCollectionsCustom.getDeductionDepositTotal().toString(),
					mchtMonthlyCollectionsCustom.getDiscount()==null?"":mchtMonthlyCollectionsCustom.getDiscount().toString(),
					mchtMonthlyCollectionsCustom.getDiscountedEndNeedPay()==null?"":mchtMonthlyCollectionsCustom.getDiscountedEndNeedPay().toString(),
					mchtMonthlyCollectionsCustom.getDiscountDesc()
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
	 * 每年POP收款及应付列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/yearList.shtml")
	public ModelAndView yearList(HttpServletRequest request) {
		String rtPage = "/popDealOrderDtl/mchtMonthlyCollections/yearList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(request.getParameter("year")) && !StringUtils.isEmpty(request.getParameter("month"))){
			resMap.put("year", request.getParameter("year"));
			resMap.put("month", request.getParameter("month"));
		}else{
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = df.format(now);
			String year = nowDate.substring(0,4);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String month = format.format(c.getTime());
			resMap.put("year", year);
			resMap.put("month", month);
		}
		ProductTypeExample productTypeExample = new ProductTypeExample();
		 ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		 productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		 List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		 resMap.put("productTypeList", productTypeList); //1级类目
		 
		 String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		 if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			  String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			   if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 每年POP收款及应付列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/yearData.shtml")
	@ResponseBody
	public Map<String, Object> yearData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowDate = df.format(now);
			String nowYear = nowDate.substring(0,4);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String searchYear = request.getParameter("year");
			String productTypeId = request.getParameter("productTypeId");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String nowMonth = format.format(c.getTime());
			String searchMonth = request.getParameter("month");
			if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
				searchYear = nowYear;
				searchMonth = nowMonth;
			}
			Map<String,String> paramMap = new HashMap<String,String>();
			if(!StringUtils.isEmpty(searchMchtCode)){
				paramMap.put("mchtCode", searchMchtCode);
			}
			if(!StringUtils.isEmpty(searchName)){
				paramMap.put("name", searchName);
			}
			if(!StringUtils.isEmpty("productTypeId")){
				paramMap.put("productTypeId", productTypeId);
			}
			paramMap.put("beginDate", searchYear+"-"+"01");
			paramMap.put("endDate", searchYear+"-"+searchMonth);
			List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.getListByYear(paramMap);
			List<MchtMonthlyCollectionsCustom> result = new ArrayList<MchtMonthlyCollectionsCustom>();
			if (page.getPage() * page.getPagesize() > mchtMonthlyCollectionsCustoms.size()) {
				result = (mchtMonthlyCollectionsCustoms.subList(page.getPagesize() * (page.getPage() - 1), mchtMonthlyCollectionsCustoms.size()));
			} else {
				result = (mchtMonthlyCollectionsCustoms.subList(page.getPagesize() * (page.getPage() - 1), page.getPage() * page.getPagesize()));
			}
			resMap.put("Rows", result);
			resMap.put("Total", mchtMonthlyCollectionsCustoms.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 每年POP收款及应付导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/popDealOrderDtl/mchtMonthlyCollections/yearExport.shtml")
	public void yearExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowDate = df.format(now);
			String nowYear = nowDate.substring(0,4);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String searchYear = request.getParameter("year");
			String productTypeId=request.getParameter("productTypeId");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String nowMonth = format.format(c.getTime());
			String searchMonth = request.getParameter("month");
			if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
				searchYear = nowYear;
				searchMonth = nowMonth;
			}
			Map<String,String> paramMap = new HashMap<String,String>();
			if(!StringUtils.isEmpty(searchMchtCode)){
				paramMap.put("mchtCode", searchMchtCode);
			}
			if(!StringUtils.isEmpty(searchName)){
				paramMap.put("name", searchName);
			}
			if(!StringUtils.isEmpty("productTypeId")){
				paramMap.put("productTypeId", productTypeId);
			}
			paramMap.put("beginDate", searchYear+"-"+"01");
			paramMap.put("endDate", searchYear+"-"+searchMonth);
			List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.getListByYear(paramMap);
			String[] titles = {"商家序号","公司名称","店铺名称","期初结欠","数量","商品金额","商家优惠","平台优惠","积分优惠","订单客户实付","POP技术服务费","POP结算金额","直赔单扣款","实付款","抵缴保证金","折让","期末应付","折让原因"};
			ExcelBean excelBean = new ExcelBean("每月POP收款及应付_"+searchYear+searchMonth+".xls","每月POP收款及应付_"+searchYear+searchMonth, titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtMonthlyCollectionsCustom mchtMonthlyCollectionsCustom:mchtMonthlyCollectionsCustoms){
				String[] data = {
					mchtMonthlyCollectionsCustom.getMchtCode(),
					mchtMonthlyCollectionsCustom.getMchtCompanyName(),
					mchtMonthlyCollectionsCustom.getMchtShopName(),
					mchtMonthlyCollectionsCustom.getBeginUnpay()==null?"0.00":mchtMonthlyCollectionsCustom.getBeginUnpay().toString(),
					mchtMonthlyCollectionsCustom.getProductCount()==null?"0":mchtMonthlyCollectionsCustom.getProductCount().toString(),
					mchtMonthlyCollectionsCustom.getProductAmount()==null?"0.00":mchtMonthlyCollectionsCustom.getProductAmount().toString(),
					mchtMonthlyCollectionsCustom.getMchtPreferential()==null?"0.00":mchtMonthlyCollectionsCustom.getMchtPreferential().toString(),
					mchtMonthlyCollectionsCustom.getPlatformPreferential()==null?"0.00":mchtMonthlyCollectionsCustom.getPlatformPreferential().toString(),
					mchtMonthlyCollectionsCustom.getIntegralPreferential()==null?"0.00":mchtMonthlyCollectionsCustom.getIntegralPreferential().toString(),
					mchtMonthlyCollectionsCustom.getOrderClientPayAmount()==null?"0.00":mchtMonthlyCollectionsCustom.getOrderClientPayAmount().toString(),
					mchtMonthlyCollectionsCustom.getCommissionAmount()==null?"0.00":mchtMonthlyCollectionsCustom.getCommissionAmount().toString(),
					mchtMonthlyCollectionsCustom.getSettleAmount()==null?"0.00":mchtMonthlyCollectionsCustom.getSettleAmount().toString(),
					mchtMonthlyCollectionsCustom.getRefundAmount()==null?"0.00":mchtMonthlyCollectionsCustom.getRefundAmount().toString(),
					mchtMonthlyCollectionsCustom.getPayAmount()==null?"0.00":mchtMonthlyCollectionsCustom.getPayAmount().toString(),
					mchtMonthlyCollectionsCustom.getDeductionDepositTotal()==null?"0.00":mchtMonthlyCollectionsCustom.getDeductionDepositTotal().toString(),
					mchtMonthlyCollectionsCustom.getDiscount()==null?"0.00":mchtMonthlyCollectionsCustom.getDiscount().toString(),
					mchtMonthlyCollectionsCustom.getDiscountedEndNeedPay()==null?"0.00":mchtMonthlyCollectionsCustom.getDiscountedEndNeedPay().toString(),
					mchtMonthlyCollectionsCustom.getDiscountDesc()
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
	 * 本月POP收款及应付列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/dayList.shtml")
	public ModelAndView dayList(HttpServletRequest request) {
		String rtPage = "/popDealOrderDtl/mchtMonthlyCollections/dayList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowDate = df.format(now);
		resMap.put("nowDate", nowDate);
		
		 ProductTypeExample productTypeExample = new ProductTypeExample();
		 ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		 productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		 List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		 resMap.put("productTypeList", productTypeList); //1级类目
		 
		 String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		 if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			  String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			   if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 本月POP收款及应付列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/dayData.shtml")
	@ResponseBody
	public Map<String, Object> dayData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			Date now = new Date();
			String nowDate = df.format(now);
			String beginDate = nowDate+"-01";
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String endDate = request.getParameter("endDate");
			String productTypeId=request.getParameter("productTypeId");
			Map<String,String> paramMap = new HashMap<String,String>();
			if(!StringUtils.isEmpty(searchMchtCode)){
				paramMap.put("mchtCode", searchMchtCode);
			}
			if(!StringUtils.isEmpty(searchName)){
				paramMap.put("name", searchName);
			}
			if (!StringUtils.isEmpty(productTypeId)) {
				paramMap.put("productTypeId", productTypeId);
			}
			paramMap.put("beginDate", beginDate);
			if(StringUtils.isEmpty(endDate)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				endDate = sdf.format(new Date());
			}
			paramMap.put("endDate", endDate);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String prevMonth = format.format(c.getTime());
			String nowYear = nowDate.substring(0,4);
			paramMap.put("collectionDate", nowYear+"-"+prevMonth);
			List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.getListByDay(paramMap);
			List<MchtMonthlyCollectionsCustom> result = new ArrayList<MchtMonthlyCollectionsCustom>();
			if (page.getPage() * page.getPagesize() > mchtMonthlyCollectionsCustoms.size()) {
				result = (mchtMonthlyCollectionsCustoms.subList(page.getPagesize() * (page.getPage() - 1), mchtMonthlyCollectionsCustoms.size()));
			} else {
				result = (mchtMonthlyCollectionsCustoms.subList(page.getPagesize() * (page.getPage() - 1), page.getPage() * page.getPagesize()));
			}
			resMap.put("Rows", result);
			resMap.put("Total", mchtMonthlyCollectionsCustoms.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 本月POP收款及应付导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/popDealOrderDtl/mchtMonthlyCollections/dayExport.shtml")
	public void dayExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			Date now = new Date();
			String nowDate = df.format(now);
			String beginDate = nowDate+"-01";
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String endDate = request.getParameter("endDate");
			String productTypeId=request.getParameter("productTypeId");
			Map<String,String> paramMap = new HashMap<String,String>();
			if(!StringUtils.isEmpty(searchMchtCode)){
				paramMap.put("mchtCode", searchMchtCode);
			}
			if(!StringUtils.isEmpty(searchName)){
				paramMap.put("name", searchName);
			}
			if (!StringUtils.isEmpty(productTypeId)) {
				paramMap.put("productTypeId", productTypeId);
			}
			if(StringUtils.isEmpty(endDate)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				endDate = sdf.format(new Date());
			}
			paramMap.put("beginDate", beginDate);
			paramMap.put("endDate", endDate);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String prevMonth = format.format(c.getTime());
			String nowYear = nowDate.substring(0,4);
			paramMap.put("collectionDate", nowYear+"-"+prevMonth);
			List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.getListByDay(paramMap);
			String[] titles = {"商家序号","公司名称","店铺名称","期初结欠","数量","商品金额","商家优惠","平台优惠","积分优惠","订单客户实付","POP技术服务费","POP结算金额","直赔单扣款","实付款","抵缴保证金","折让","期末应付","折让原因"};
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
			String date = f.format(new Date());
			ExcelBean excelBean = new ExcelBean("本月POP收款及应付预计_"+date+".xls","本月POP收款及应付预计_"+date, titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtMonthlyCollectionsCustom mchtMonthlyCollectionsCustom:mchtMonthlyCollectionsCustoms){
				Integer number = mchtMonthlyCollectionsCustom.getCollectionProductCount()-mchtMonthlyCollectionsCustom.getRefundProductCount();
				String[] data = {
					mchtMonthlyCollectionsCustom.getMchtCode(),
					mchtMonthlyCollectionsCustom.getMchtCompanyName(),
					mchtMonthlyCollectionsCustom.getMchtShopName(),
					mchtMonthlyCollectionsCustom.getBeginUnpay()==null?"":mchtMonthlyCollectionsCustom.getBeginUnpay().toString(),
					number.toString(),
					mchtMonthlyCollectionsCustom.getCollectionProductAmount()==null?"":mchtMonthlyCollectionsCustom.getRefundProductAmount()==null?mchtMonthlyCollectionsCustom.getCollectionProductAmount().toString():mchtMonthlyCollectionsCustom.getCollectionProductAmount().subtract(mchtMonthlyCollectionsCustom.getRefundProductAmount()).toString(),
					mchtMonthlyCollectionsCustom.getCollectionMchtPreferential()==null?"":mchtMonthlyCollectionsCustom.getRefundMchtPreferential()==null?mchtMonthlyCollectionsCustom.getCollectionMchtPreferential().toString():mchtMonthlyCollectionsCustom.getCollectionMchtPreferential().subtract(mchtMonthlyCollectionsCustom.getRefundMchtPreferential()).toString(),
					mchtMonthlyCollectionsCustom.getCollectionPlatformPreferential()==null?"":mchtMonthlyCollectionsCustom.getRefundPlatformPreferential()==null?mchtMonthlyCollectionsCustom.getCollectionPlatformPreferential().toString():mchtMonthlyCollectionsCustom.getCollectionPlatformPreferential().subtract(mchtMonthlyCollectionsCustom.getRefundPlatformPreferential()).toString(),
					mchtMonthlyCollectionsCustom.getCollectionIntegralPreferential()==null?"":mchtMonthlyCollectionsCustom.getRefundIntegralPreferential()==null?mchtMonthlyCollectionsCustom.getCollectionIntegralPreferential().toString():mchtMonthlyCollectionsCustom.getCollectionIntegralPreferential().subtract(mchtMonthlyCollectionsCustom.getRefundIntegralPreferential()).toString(),
					mchtMonthlyCollectionsCustom.getOrderAmount()==null?"":mchtMonthlyCollectionsCustom.getReturnAmount()==null?mchtMonthlyCollectionsCustom.getOrderAmount().toString():mchtMonthlyCollectionsCustom.getOrderAmount().subtract(mchtMonthlyCollectionsCustom.getReturnAmount()).toString(),
					mchtMonthlyCollectionsCustom.getCollectionCommissionAmount()==null?"":mchtMonthlyCollectionsCustom.getRefundCommissionAmount()==null?mchtMonthlyCollectionsCustom.getCollectionCommissionAmount().toString():mchtMonthlyCollectionsCustom.getCollectionCommissionAmount().subtract(mchtMonthlyCollectionsCustom.getRefundCommissionAmount()).toString(),
					mchtMonthlyCollectionsCustom.getSettleAmount()==null?"":mchtMonthlyCollectionsCustom.getReturnOrderAmount()==null?mchtMonthlyCollectionsCustom.getSettleAmount().toString():mchtMonthlyCollectionsCustom.getSettleAmount().subtract(mchtMonthlyCollectionsCustom.getReturnOrderAmount()).toString(),
					mchtMonthlyCollectionsCustom.getRefundAmount()==null?"":mchtMonthlyCollectionsCustom.getRefundAmount().toString(),
					mchtMonthlyCollectionsCustom.getPayAmount()==null?"":mchtMonthlyCollectionsCustom.getPayAmount().toString(),
					mchtMonthlyCollectionsCustom.getDeductionDepositTotal()==null?""
							+ "":mchtMonthlyCollectionsCustom.getDeductionDepositTotal().toString(),
					"0",
					mchtMonthlyCollectionsCustom.getDiscountedEndNeedPay()==null?"":mchtMonthlyCollectionsCustom.getDiscountedEndNeedPay().toString(),
					mchtMonthlyCollectionsCustom.getDiscountDesc()
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
	 * 修改折让页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/toEdit.shtml")
	public ModelAndView toEdit(HttpServletRequest request) throws UnsupportedEncodingException{
		String rtPage = "/popDealOrderDtl/mchtMonthlyCollections/toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		MchtMonthlyCollections mchtMonthlyCollections = mchtMonthlyCollectionsService.selectByPrimaryKey(Integer.parseInt(id));
		resMap.put("mchtMonthlyCollections", mchtMonthlyCollections);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 更新状态
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/popDealOrderDtl/mchtMonthlyCollections/update.shtml")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			String discount = request.getParameter("discount");
			String discountDesc = request.getParameter("discountDesc");
			MchtMonthlyCollections mchtMonthlyCollections = mchtMonthlyCollectionsService.selectByPrimaryKey(Integer.parseInt(id));
			mchtMonthlyCollections.setDiscount(new BigDecimal(discount));
			mchtMonthlyCollections.setDiscountDesc(discountDesc);
			mchtMonthlyCollectionsService.updateByPrimaryKeySelective(mchtMonthlyCollections);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 每日收款汇总
	 * Create by XDD on 2019年8月30日
	 */
	@RequestMapping(value = "/popDealOrderDtl/dailyCollectionSummary.shtml")
	public ModelAndView dailyCollectionSummary(HttpServletRequest request) {
		String rtPage = "/popDealOrderDtl/dailyCollectionSummary";
		Map<String, Object> resMap = new HashMap<String, Object>();
		/*Date date = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String receipt_date = sdFormat.format(date);*/
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date time = calendar.getTime();
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(time);
		resMap.put("receipt_date", yesterday);
		//一级分类
		ProductTypeExample example = new ProductTypeExample();
		example.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(example);
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 每日收款汇总列表
	 * Create by XDD on 2019年8月30日
	 */
	@RequestMapping(value = "/popDealOrderDtl/dailyCollectionSummaryList.shtml")
	@ResponseBody
	public Map<String, Object> dailyCollectionSummaryList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;

		paramMap = this.setPageParametersLiger(request, paramMap);
		if(!StringUtils.isEmpty(paramMap.get("receipt_date"))){
			String receiptDate = paramMap.get("receipt_date").toString();
			paramMap.put("receipt_date_begin", receiptDate+" 00:00:00");
			paramMap.put("receipt_date_end", receiptDate+" 23:59:59");
		}else{
			resMap.put("Rows",new ArrayList<HashMap<String, Object>>());
			resMap.put("Total", 0);
			return resMap;
		}
		if(!StringUtils.isEmpty(paramMap.get("mchtCode"))){
			String[] mchtCode = (paramMap.get("mchtCode")).toString().split("\n");
			paramMap.put("mchtCode", mchtCode);
		}
		if(!StringUtils.isEmpty(paramMap.get("companyName"))){
			String companyName = paramMap.get("companyName").toString();
			paramMap.put("companyName", "%"+companyName+"%");
		}
		
		
		paramMap.put("orderBy", " order by t.mcht_id desc");
		paramMap.put("pageLimit", " limit " + paramMap.get("MIN_NUM") + ", "
				+ paramMap.get("MAX_NUM"));

		List<HashMap<String, Object>> Rows = subOrderService.selectDailyCollectionSummary(paramMap);
		totalCount = subOrderService.countDailyCollectionSummary(paramMap);
		resMap.put("Rows", Rows);
		resMap.put("Total", totalCount);
		
		return resMap;
	}
	
	/**
	 * dailyCollectionSummaryExport
	 * Create by XDD on 2019年9月2日
	 */
	@RequestMapping(value="/popDealOrderDtl/dailyCollectionSummaryExport.shtml")
	public void dailyCollectionSummaryExport(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) throws Exception {
		try {
		paramMap = this.setPageParametersLiger(request, paramMap);
		String receiptDate ="";
		if(!StringUtils.isEmpty(paramMap.get("receipt_date"))){
			receiptDate = paramMap.get("receipt_date").toString();
			paramMap.put("receipt_date_begin", receiptDate+" 00:00:00");
			paramMap.put("receipt_date_end", receiptDate+" 23:59:59");
		}
		if(!StringUtils.isEmpty(paramMap.get("mchtCode"))){
			String[] mchtCode = (paramMap.get("mchtCode")).toString().split("\n");
			paramMap.put("mchtCode", mchtCode);
		}
		if(!StringUtils.isEmpty(paramMap.get("companyName"))){
			String companyName = paramMap.get("companyName").toString();
			paramMap.put("companyName", "%"+companyName+"%");
		}
		
		paramMap.put("orderBy", " order by t.mcht_id desc");
		paramMap.put("pageLimit", " limit " + paramMap.get("MIN_NUM") + ", "
				+ paramMap.get("MAX_NUM"));

		List<HashMap<String, Object>> Rows = subOrderService.selectDailyCollectionSummary(paramMap);
		String[] titles = { "主营类目", "商品序号", "公司名称", "店铺名称", receiptDate+"金额"};
		ExcelBean excelBean = new ExcelBean("当日收货金额统计列表.xls",
				"当日收货金额统计列表", titles);
		List<String[]> datas = new ArrayList<String[]>();
		for (HashMap<String, Object> hashMap : Rows) {
			String[] data = {
					hashMap.get("main_product_type").toString(),
					hashMap.get("mcht_code").toString(),
					hashMap.get("company_name").toString(),
					hashMap.get("shop_name").toString(),
					hashMap.get("sum_pay_amount").toString()
		};
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
