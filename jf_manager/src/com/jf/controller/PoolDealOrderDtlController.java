package com.jf.controller;
import java.math.BigDecimal;
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
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.CustomerServiceOrderCustomExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtMonthlyCollections;
import com.jf.entity.MchtMonthlyCollectionsCustom;
import com.jf.entity.MchtMonthlyCollectionsCustomExample;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeExample;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlCustomExample;
import com.jf.entity.Product;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductType;
import com.jf.service.ActivityService;
import com.jf.service.AppealLogPicService;
import com.jf.service.AppealLogService;
import com.jf.service.AppealOrderService;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtMonthlyCollectionsService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.MchtProductTypeService;
import com.jf.service.OrderDtlService;
import com.jf.service.OrderPreferentialInfoService;
import com.jf.service.PlatformContactService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;
import com.jf.service.SubOrderService;
import com.jf.service.ViolateOrderService;
import com.jf.vo.Page;

@Controller
public class PoolDealOrderDtlController extends BaseController {
	
	@Resource
	private ViolateOrderService violateOrderService;
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private AppealOrderService appealOrderService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private OrderPreferentialInfoService orderPreferentialInfoService;
	
	@Resource
	private AppealLogService appealLogService;
	
	@Resource
	private AppealLogPicService appealLogPicService;
	
	@Resource
	private PlatformContactService platformContactService;
	
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

	private static final long serialVersionUID = 1L;
	
	/**
	 * SPOP交易商品明细列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/poolDealOrderDtl/list.shtml")
	public ModelAndView list(HttpServletRequest request) {
		String rtPage = "/poolDealOrderDtl/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(request.getParameter("mchtCode"))){
			resMap.put("mchtCode", "");
		}else{
			resMap.put("mchtCode", request.getParameter("mchtCode"));
		}
		if(StringUtils.isEmpty(request.getParameter("subOrderCode"))){
			resMap.put("subOrderCode", "");
		}else{
			resMap.put("subOrderCode", request.getParameter("subOrderCode"));
		}
		if(StringUtils.isEmpty(request.getParameter("brandName"))){
			resMap.put("brandName", "");
		}else{
			resMap.put("brandName", request.getParameter("brandName"));
		}
		if(StringUtils.isEmpty(request.getParameter("artNo"))){
			resMap.put("artNo", "");
		}else{
			resMap.put("artNo", request.getParameter("artNo"));
		}
		if(StringUtils.isEmpty(request.getParameter("productName"))){
			resMap.put("productName", "");
		}else{
			resMap.put("productName", request.getParameter("productName"));
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
			resMap.put("refund", request.getParameter("refund"));
			rtPage = "/poolDealOrderDtl/refundList";
		}
		if(StringUtil.isEmpty(request.getParameter("accept")) && StringUtil.isEmpty(request.getParameter("refund"))){
			resMap.put("accept", "1");
		}
		resMap.put("flag", "0");
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * SPOP交易商品明细列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/poolDealOrderDtl/data.shtml")
	@ResponseBody
	public Map<String, Object> data(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
			OrderDtlCustomExample acceptCountExample = new OrderDtlCustomExample();
			OrderDtlCustomExample refundCountExample = new OrderDtlCustomExample();
			OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria = orderDtlCustomExample.createCriteria();
			OrderDtlCustomExample.OrderDtlCustomCriteria acceptCountCriteria = acceptCountExample.createCriteria();
			OrderDtlCustomExample.OrderDtlCustomCriteria refundCountCriteria = refundCountExample.createCriteria();
			orderDtlCustomCriteria.andDelFlagEqualTo("0");
			acceptCountCriteria.andDelFlagEqualTo("0");
			refundCountCriteria.andDelFlagEqualTo("0");
			
			orderDtlCustomCriteria.andCombineOrderStatusEqualTo("1");//母订单已付款
			acceptCountCriteria.andCombineOrderStatusEqualTo("1");
			refundCountCriteria.andCombineOrderStatusEqualTo("1");
			orderDtlCustomCriteria.andMchtTypeEqualTo("1");//自营SPOP
			acceptCountCriteria.andMchtTypeEqualTo("1");
			refundCountCriteria.andMchtTypeEqualTo("1");
			String searchMchtCode = request.getParameter("mchtCode");
			if(!StringUtil.isEmpty(searchMchtCode)){
				orderDtlCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
				acceptCountCriteria.andMchtCodeEqualTo(searchMchtCode);
				refundCountCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			String searchSubOrderCode = request.getParameter("subOrderCode");
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				orderDtlCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
				acceptCountCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
				refundCountCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
			}
			String searchBrandName = request.getParameter("brandName");
			if(!StringUtil.isEmpty(searchBrandName)){
				orderDtlCustomCriteria.andBrandNameEqualTo(searchBrandName);
				acceptCountCriteria.andBrandNameEqualTo(searchBrandName);
				refundCountCriteria.andBrandNameEqualTo(searchBrandName);
			}
			String searchArtNo = request.getParameter("artNo");
			if(!StringUtil.isEmpty(searchArtNo)){
				orderDtlCustomCriteria.andArtNoLike("%"+searchArtNo+"%");
				acceptCountCriteria.andArtNoLike("%"+searchArtNo+"%");
				refundCountCriteria.andArtNoLike("%"+searchArtNo+"%");
			}
			String searchProductName = request.getParameter("productName");
			if(!StringUtil.isEmpty(searchProductName)){
				orderDtlCustomCriteria.andProductNameLike("%"+searchProductName+"%");
				acceptCountCriteria.andProductNameLike("%"+searchProductName+"%");
				refundCountCriteria.andProductNameLike("%"+searchProductName+"%");
			}
			String searchAccept = request.getParameter("accept");
			String dateBegin = request.getParameter("date_begin");
			String dateEnd = request.getParameter("date_end");
			if(!StringUtil.isEmpty(searchAccept) && searchAccept.equals("1")){
				if(!StringUtil.isEmpty(dateBegin)){
					orderDtlCustomCriteria.andPayDateBeginGreaterThanOrEqualTo(dateBegin+" 00:00:00");
				}
				if(!StringUtil.isEmpty(dateEnd)){
					orderDtlCustomCriteria.andPayDateEndLessThanOrEqualTo(dateEnd+" 23:59:59");
				}
			}
			String searchRefund = request.getParameter("refund");
			List<String> productStatusList = new ArrayList<String>();
			productStatusList.add("2");
			productStatusList.add("3");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(searchRefund) && searchRefund.equals("1")){
				orderDtlCustomExample.setOrderByClause("a.refund_date asc");
				orderDtlCustomCriteria.andProductStatusIn(productStatusList);
				if(!StringUtil.isEmpty(dateBegin)){
					orderDtlCustomCriteria.andRefundDateGreaterThanOrEqualTo(sf.parse(dateBegin+" 00:00:00"));
				}
				if(!StringUtil.isEmpty(dateEnd)){
					orderDtlCustomCriteria.andRefundDateLessThanOrEqualTo(sf.parse(dateEnd+" 23:59:59"));
				}
			}
			
			refundCountCriteria.andProductStatusIn(productStatusList);
			if(!StringUtil.isEmpty(dateBegin)){
				acceptCountCriteria.andPayDateBeginGreaterThanOrEqualTo(dateBegin+" 00:00:00");
				refundCountCriteria.andRefundDateGreaterThanOrEqualTo(sf.parse(dateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(dateEnd)){
				acceptCountCriteria.andPayDateEndLessThanOrEqualTo(dateEnd+" 23:59:59");
				refundCountCriteria.andRefundDateLessThanOrEqualTo(sf.parse(dateEnd+" 23:59:59"));
			}
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
			totalCount = orderDtlService.countOrderDtlCustomByExample(orderDtlCustomExample);
			orderDtlCustomExample.setLimitStart(page.getLimitStart());
			orderDtlCustomExample.setLimitSize(page.getLimitSize());
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
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
			resMap.put("Rows", orderDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 查看商家经营品类，品牌
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/poolDealOrderDtl/viewMcht.shtml")
	public ModelAndView viewMcht(HttpServletRequest request) {
		String rtPage = "/poolDealOrderDtl/viewMcht";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String orderDtlId = request.getParameter("orderDtlId");
		OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(Integer.parseInt(orderDtlId));
		Product product = productService.selectByPrimaryKey(orderDtl.getProductId());
		MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
		MchtProductBrandExample.Criteria mchtProductBrandCriteria = mchtProductBrandExample.createCriteria();
		mchtProductBrandCriteria.andDelFlagEqualTo("0");
		mchtProductBrandCriteria.andMchtIdEqualTo(product.getMchtId());
		mchtProductBrandCriteria.andProductBrandIdEqualTo(product.getBrandId());
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mchtProductBrandExample);
		String productBrandNameDesc = "";
		if(mchtProductBrands!=null && mchtProductBrands.size()>0){
			resMap.put("priceModelDesc",mchtProductBrands.get(0).getPriceModelDesc());
			ProductBrand productBrand = productBrandService.selectByPrimaryKey(mchtProductBrands.get(0).getProductBrandId());
			productBrandNameDesc = productBrand.getName();
		}
		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		MchtProductTypeExample.Criteria mchtProductTypeCriteria = mchtProductTypeExample.createCriteria();
		mchtProductTypeCriteria.andDelFlagEqualTo("0");
		mchtProductTypeCriteria.andMchtIdEqualTo(product.getMchtId());
		mchtProductTypeCriteria.andProductTypeIdEqualTo(product.getProductTypeId());
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mchtProductTypeExample);
		String productTypeNameDesc = "";
		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
			ProductType productType = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
			productTypeNameDesc = productType.getName();
		}
		resMap.put("productBrandNameDesc",productBrandNameDesc);
		resMap.put("productTypeNameDesc",productTypeNameDesc);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/poolDealOrderDtl/export.shtml")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
			OrderDtlCustomExample refundOrderDtlCustomExample = new OrderDtlCustomExample();
			refundOrderDtlCustomExample.setOrderByClause("a.refund_date asc");
			OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria = orderDtlCustomExample.createCriteria();
			OrderDtlCustomExample.OrderDtlCustomCriteria refundOrderDtlCustomCriteria = refundOrderDtlCustomExample.createCriteria();
			orderDtlCustomCriteria.andDelFlagEqualTo("0");
			refundOrderDtlCustomCriteria.andDelFlagEqualTo("0");
			orderDtlCustomCriteria.andCombineOrderStatusEqualTo("1");//母订单已付款
			refundOrderDtlCustomCriteria.andCombineOrderStatusEqualTo("1");//母订单已付款
			orderDtlCustomCriteria.andMchtTypeEqualTo("1");//SPOP
			refundOrderDtlCustomCriteria.andMchtTypeEqualTo("1");//SPOP
			String searchMchtCode = request.getParameter("mchtCode");
			if(!StringUtil.isEmpty(searchMchtCode)){
				orderDtlCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
				refundOrderDtlCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			String searchSubOrderCode = request.getParameter("subOrderCode");
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				orderDtlCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
				refundOrderDtlCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
			}
			String searchBrandName = request.getParameter("brandName");
			if(!StringUtil.isEmpty(searchBrandName)){
				orderDtlCustomCriteria.andBrandNameEqualTo(searchBrandName);
				refundOrderDtlCustomCriteria.andBrandNameEqualTo(searchBrandName);
			}
			String searchArtNo = request.getParameter("artNo");
			if(!StringUtil.isEmpty(searchArtNo)){
				orderDtlCustomCriteria.andArtNoLike("%"+searchArtNo+"%");
				refundOrderDtlCustomCriteria.andArtNoLike("%"+searchArtNo+"%");
			}
			String searchProductName = request.getParameter("productName");
			if(!StringUtil.isEmpty(searchProductName)){
				orderDtlCustomCriteria.andProductNameLike("%"+searchProductName+"%");
				refundOrderDtlCustomCriteria.andProductNameLike("%"+searchProductName+"%");
			}
			String dateBegin = request.getParameter("date_begin");
			String dateEnd = request.getParameter("date_end");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(dateBegin)){
				orderDtlCustomCriteria.andPayDateBeginGreaterThanOrEqualTo(dateBegin+" 00:00:00");
				refundOrderDtlCustomCriteria.andRefundDateGreaterThanOrEqualTo(sf.parse(dateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(dateEnd)){
				orderDtlCustomCriteria.andPayDateEndLessThanOrEqualTo(dateEnd+" 23:59:59");
				refundOrderDtlCustomCriteria.andRefundDateLessThanOrEqualTo(sf.parse(dateEnd+" 00:00:00"));
			}
			List<String> productStatusList = new ArrayList<String>();
			productStatusList.add("2");
			productStatusList.add("3");
			refundOrderDtlCustomCriteria.andProductStatusIn(productStatusList);
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
			List<OrderDtlCustom> refundOrderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(refundOrderDtlCustomExample);
			String[] titles = {"日期","商家序号","商家名称","子订单编号", "商品名称","品牌","货号","规格","醒购价","数量","销售商品金额","商家优惠","平台优惠","积分优惠","订单客户实付金额","结算单价","结算金额","SPOP结算单ID","最新状态","类型"};
			ExcelBean excelBean = new ExcelBean("导出SPOP交易商品明细.xls",
					"导出SPOP交易商品明细", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				String productStatusDesc = "";
				if(orderDtlCustom.getProductStatus() == null || orderDtlCustom.getProductStatus().equals("1")){
					if(orderDtlCustom.getProductStatus() == null){
						productStatusDesc = "未完成";
					}else{
						productStatusDesc = "完成";
					}
				}
				String[] data = {
						df.format(orderDtlCustom.getPayDate()),
						orderDtlCustom.getMchtCode(),
						orderDtlCustom.getCompanyName(),
						orderDtlCustom.getSubOrderCode(),
						orderDtlCustom.getProductName(),
						orderDtlCustom.getBrandName(),
						orderDtlCustom.getArtNo(),
						orderDtlCustom.getProductPropDesc(),
						orderDtlCustom.getSalePrice() == null ? "" : orderDtlCustom.getSalePrice().toString(),
						orderDtlCustom.getQuantity() == null ? "" : orderDtlCustom.getQuantity().toString(),
						orderDtlCustom.getQuantity() == null ? "" : orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity())).toString(),
						orderDtlCustom.getMchtPreferential() == null ? "" : orderDtlCustom.getMchtPreferential().toString(),
						orderDtlCustom.getPlatformPreferential() == null ? "" : orderDtlCustom.getPlatformPreferential().toString(),
						orderDtlCustom.getIntegralPreferential() == null ? "" : orderDtlCustom.getIntegralPreferential().toString(),
						orderDtlCustom.getPayAmount() == null ? "" : orderDtlCustom.getPayAmount().toString(),
						orderDtlCustom.getPopCommissionRate() == null ? "" : orderDtlCustom.getPopCommissionRate().toString(),
						orderDtlCustom.getCommissionAmount() == null ? "" : orderDtlCustom.getCommissionAmount().toString(),
						orderDtlCustom.getSettleAmount() == null ? "" : orderDtlCustom.getSettleAmount().toString(),
						orderDtlCustom.getMchtSettleOrderId() == null ? "" : orderDtlCustom.getMchtSettleOrderId().toString(),
						productStatusDesc,
						"收款"
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
				String[] data = {
						df.format(refundOrderDtlCustom.getRefundDate()),
						refundOrderDtlCustom.getMchtCode(),
						refundOrderDtlCustom.getCompanyName(),
						refundOrderDtlCustom.getSubOrderCode(),
						refundOrderDtlCustom.getProductName(),
						refundOrderDtlCustom.getBrandName(),
						refundOrderDtlCustom.getArtNo(),
						refundOrderDtlCustom.getProductPropDesc(),
						refundOrderDtlCustom.getSalePrice() == null ? "" : refundOrderDtlCustom.getSalePrice().toString(),
						refundOrderDtlCustom.getQuantity() == null ? "" : minus+refundOrderDtlCustom.getQuantity().toString(),
						refundOrderDtlCustom.getQuantity() == null ? "" : minus+refundOrderDtlCustom.getSalePrice().multiply(new BigDecimal(refundOrderDtlCustom.getQuantity())).toString(),
						refundOrderDtlCustom.getMchtPreferential() == null ? "" : minus+refundOrderDtlCustom.getMchtPreferential().toString(),
						refundOrderDtlCustom.getPlatformPreferential() == null ? "" : minus+refundOrderDtlCustom.getPlatformPreferential().toString(),
						refundOrderDtlCustom.getIntegralPreferential() == null ? "" : minus+refundOrderDtlCustom.getIntegralPreferential().toString(),
						refundOrderDtlCustom.getPayAmount() == null ? "" : minus+refundOrderDtlCustom.getPayAmount().toString(),
						refundOrderDtlCustom.getPopCommissionRate() == null ? "" : refundOrderDtlCustom.getPopCommissionRate().toString(),
						refundOrderDtlCustom.getCommissionAmount() == null ? "" : minus+refundOrderDtlCustom.getCommissionAmount().toString(),
						refundOrderDtlCustom.getSettleAmount() == null ? "" : minus+refundOrderDtlCustom.getSettleAmount().toString(),
						refundOrderDtlCustom.getMchtSettleOrderId() == null ? "" : refundOrderDtlCustom.getMchtSettleOrderId().toString(),
						productStatusDesc,
						"退款或退货"
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
	 * SPOP每月收款情况列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/poolDealOrderDtl/mchtMonthlyCollections/list.shtml")
	public ModelAndView mchtMonthlyCollectionsList(HttpServletRequest request) {
		String rtPage = "/poolDealOrderDtl/mchtMonthlyCollections/list";
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
	 * SPOP每月收款情况列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/poolDealOrderDtl/mchtMonthlyCollections/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtMonthlyCollectionsData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtMonthlyCollectionsCustomExample example = new MchtMonthlyCollectionsCustomExample();
			MchtMonthlyCollectionsCustomExample.MchtMonthlyCollectionsCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			criteria.andMchtTypeEqualTo("1");
			String searchMchtCode = request.getParameter("mchtCode");
			String name = request.getParameter("name");
			String searchYear = request.getParameter("year");
			String searchMonth = request.getParameter("month");
			if(!StringUtils.isEmpty(searchMchtCode)){
				criteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtils.isEmpty(name)){
				criteria.andNameLike("%"+name+"%");
			}
			Date now = new Date();
			Date preDate = DateUtil.getPreDate(now);
			if(StringUtils.isEmpty(searchYear)){
				searchYear = String.valueOf(preDate).substring(0,4);
			}
			if(StringUtils.isEmpty(searchMonth)){
				searchMonth = String.valueOf(preDate).substring(5, 7);
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
	 * 导出汇总表excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/poolDealOrderDtl/mchtMonthlyCollections/exportTotal.shtml")
	public void exportTotal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			MchtMonthlyCollectionsCustomExample example = new MchtMonthlyCollectionsCustomExample();
			MchtMonthlyCollectionsCustomExample.MchtMonthlyCollectionsCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			criteria.andMchtTypeEqualTo("1");
			String searchMchtCode = request.getParameter("mchtCode");
			String name = request.getParameter("name");
			String searchYear = request.getParameter("year");
			String searchMonth = request.getParameter("month");
			if(!StringUtils.isEmpty(searchMchtCode)){
				criteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtils.isEmpty(name)){
				criteria.andNameLike("%"+name+"%");
			}
			Date now = new Date();
			Date preDate = DateUtil.getPreDate(now);
			if(StringUtils.isEmpty(searchYear)){
				searchYear = String.valueOf(preDate).substring(0,4);
			}
			if(StringUtils.isEmpty(searchMonth)){
				searchMonth = String.valueOf(preDate).substring(5, 7);
			}
			criteria.andCollectionDateEqualTo(searchYear+"-"+searchMonth);
			List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustoms = mchtMonthlyCollectionsService.selectByExample(example);
			String[] titles = { "商家序号", "商家简称", "店铺名称","商家公司名称","期初结欠","本月代收","本月代收应结","本月代退","本月代退应扣","直赔单应扣","本月总应付","本月付款","期末实欠"};
			ExcelBean excelBean = new ExcelBean("导出SPOP每月收款情况汇总表.xls",
					"导出SPOP每月收款情况汇总表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtMonthlyCollectionsCustom mchtMonthlyCollectionsCustom:mchtMonthlyCollectionsCustoms){
				String[] data = {
					mchtMonthlyCollectionsCustom.getMchtCode(),
					mchtMonthlyCollectionsCustom.getMchtShortName(),
					mchtMonthlyCollectionsCustom.getMchtShopName(),
					mchtMonthlyCollectionsCustom.getMchtCompanyName(),
					mchtMonthlyCollectionsCustom.getBeginUnpay()==null?"":mchtMonthlyCollectionsCustom.getBeginUnpay().toString(),
					mchtMonthlyCollectionsCustom.getSettleAmount()==null?"":mchtMonthlyCollectionsCustom.getSettleAmount().toString(),
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
	@RequestMapping("/poolDealOrderDtl/mchtMonthlyCollections/exportByMchtId.shtml")
	public void exportByMchtId(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
			OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria = orderDtlCustomExample.createCriteria();
			orderDtlCustomCriteria.andDelFlagEqualTo("0");
			orderDtlCustomCriteria.andCombineOrderStatusEqualTo("1");//母订单已付款
			orderDtlCustomCriteria.andMchtTypeEqualTo("1");//SPOP
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
			odcCriteria.andMchtTypeEqualTo("1");//SPOP
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
			String[] titles = { "类型", "子订单编号", "日期","最新状态","商品名称","品牌","货号","规格","醒购价","数量","销售商品金额","商家优惠","平台优惠","积分优惠","订单客户实付金额","商家序号","商家名称","结算单价","结算金额","SPOP结算单ID","直赔单金额","付款金额"};
			ExcelBean excelBean = new ExcelBean("导出SPOP交易商品明细.xls",
					"导出SPOP交易商品明细", titles);
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
						orderDtlCustom.getSalePrice() == null ? "" : minus+orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity())).toString(),
						orderDtlCustom.getMchtPreferential() == null ? "" : minus+orderDtlCustom.getMchtPreferential().toString(),
						orderDtlCustom.getPlatformPreferential() == null ? "" : minus+orderDtlCustom.getPlatformPreferential().toString(),
						orderDtlCustom.getIntegralPreferential() == null ? "" : minus+orderDtlCustom.getIntegralPreferential().toString(),
						orderDtlCustom.getPayAmount() == null ? "" : minus+orderDtlCustom.getPayAmount().toString(),
						orderDtlCustom.getMchtCode(),
						orderDtlCustom.getMchtShortName(),
						orderDtlCustom.getSettlePrice() == null ? "" : minus+orderDtlCustom.getSettlePrice().toString(),
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
					"","",
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
				"","",
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
}
