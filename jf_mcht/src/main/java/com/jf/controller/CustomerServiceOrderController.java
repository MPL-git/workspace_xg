package com.jf.controller;

import com.jf.bean.ExcelBean;
import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.SysConfig;
import com.jf.common.utils.*;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CustomerServiceOrderController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(CustomerServiceOrderController.class);

	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private OrderDtlService orderDtlService;

	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Resource
	private OrderLogService orderLogService;
	
	@Resource
	private OrderPreferentialInfoService orderPreferentialInfoService;
	
	@Resource
	private CouponService couponService;
	
	@Resource
	private FullCutService fullCutService;
	
	@Resource
	private FullDiscountService fullDiscountService;
	
	@Resource
	private FullGiveService fullGiveService;
	
	@Resource
	private RemarksLogService remarksLogService;
	
	@Resource
	private CustomerServiceLogService customerServiceLogService;
	
	@Resource
	private CustomerServicePicService customerServicePicService;
	
	@Resource
	private ServiceLogPicService serviceLogPicService;
	
	@Resource
	private ProductAfterTempletService productAfterTempletService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private SysParamCfgService sysParamCfgService;
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private SysPaymentService sysPaymentService;
	
	@Resource
	private ExpressMapper expressMapper;
	
	@Resource
	private KdnWuliuInfoMapper kdnWuliuInfoMapper;
	
	@Resource
	private RefundOrderService refundOrderService;
	
	@Resource
	private MemberAccountService memberAccountService;
	
	@Resource
	private InterventionOrderService interventionOrderService;
	
	@Resource
	private MemberInfoMapper memberInfoMapper;
	
	@Resource
	private SubDepositOrderMapper subDepositOrderMapper;
	
	@Resource
	private CombineDepositOrderMapper combineDepositOrderMapper;
	
	@Resource
	private MchtDepositMapper mchtDepositMapper;
	
	@Resource
	private MchtDepositDtlMapper mchtDepositDtlMapper;
	
	@Resource
	private CloudProductItemService cloudProductItemService;
	
	@Resource
	private CloudProductService cloudProductService;
	
	@Resource
	private CloudProductAfterTempletService cloudProductAfterTempletService;

	@Resource
	private ExpressCustomMapper expressCustomMapper;

	@Resource
	private MemberAllowanceMapper   memberAllowanceMapper;

	/**
	 * 1.平台
	 */
	public static String BELONG_PLATFORM="1";
	/**
	 * 2.商家
	 */
	public static String BELONG_MCHT="2";
	/**
	 * 3.积分
	 */
	public static String BELONG_INTEGRAL="3";
	
	public static String BELONGDESC_PLATFORM="平台优惠";
	
	public static String BELONGDESC_MCHT="商家优惠";
	
	public static String BELONGDESC_INTEGRAL="积分优惠";
	
	Map<String, Object> belongMap = new HashMap<String, Object>() {{
			put(BELONG_PLATFORM, BELONGDESC_PLATFORM);
			put(BELONG_MCHT, BELONGDESC_MCHT);
			put(BELONG_INTEGRAL, BELONGDESC_INTEGRAL);
		}
	};
	
	/**
	 * 0.发货前退款
	 */
	public static String REFUND_BEFORE_DELIVERY="0";
	
	/**
	 * 1.退换货申请
	 */
	public static String RETURN_EXCHANGE_APPLY="1";
	
	/**
	 * 2.退换货待收件
	 */
	public static String RETURN_EXCHANGE_WAITING_ACCEPT="2";
	
	/**
	 * 3.换货单待发件
	 */
	public static String EXCHANGE_WAITING_SEND="3";
	
	/**
	 * 4.商家关闭
	 */
	public static String MCHT_CLOSE="4";
	
	/**
	 * 5.商家直赔
	 */
	public static String MCHT_DIRECT_COMPENSATION="5";
	
	/**
	 * 6.全部售后
	 */
	public static String ALL_ORDER="6";
	/**
	 * 7.等待客户寄件
	 */
	public static String WAIT_MEMBER_SEND="7";
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 售后管理首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/customerServiceOrder/customerServiceOrderIndex")
	public String customerServiceOrderIndex(Model model, HttpServletRequest request) {
		String searchOrderType = request.getParameter("searchOrderType");
		if(StringUtil.isEmpty(searchOrderType)){
			searchOrderType = REFUND_BEFORE_DELIVERY;
		}
		List<ProductBrand> productBrandList = mchtProductBrandService.getMchtProductBrandList(this.getSessionMchtInfo(request).getId());
		model.addAttribute("searchOrderType", searchOrderType);
		model.addAttribute("productBrandList", productBrandList);
		model.addAttribute("customerServiceTypes", DataDicUtil.getStatusList("BU_CUSTOMER_SERVICE_ORDER", "SERVICE_TYPE"));
		//商家编号
		String mchtCode = this.getSessionMchtInfo(request).getMchtCode();
		model.addAttribute("mchtCode",mchtCode);
		return "customerServiceOrder/customerServiceOrderIndex";
	}
	
	/**
	 * 商家直赔
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/customerServiceOrder/directCompensationIndex")
	public String directCompensationIndex(Model model, HttpServletRequest request) {
		String searchOrderType = request.getParameter("searchOrderType");
		if(StringUtil.isEmpty(searchOrderType)){
			searchOrderType = MCHT_DIRECT_COMPENSATION;
		}
		List<ProductBrand> productBrandList = mchtProductBrandService.getMchtProductBrandList(this.getSessionMchtInfo(request).getId());
		model.addAttribute("searchOrderType", searchOrderType);
		model.addAttribute("productBrandList", productBrandList);
		model.addAttribute("customerServiceTypes", DataDicUtil.getStatusList("BU_CUSTOMER_SERVICE_ORDER", "SERVICE_TYPE"));
		return "customerServiceOrder/directCompensationIndex";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/customerServiceOrder/getCustomerServiceOrderList")
	@ResponseBody
	public ResponseMsg getCustomerServiceOrderList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		CustomerServiceOrderCustomExample customerServiceOrderCustomExample = new CustomerServiceOrderCustomExample();
		CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria customerServiceOrderCustomCriteria = customerServiceOrderCustomExample.createCriteria();
		customerServiceOrderCustomExample.setOrderByClause("t.id desc");
		if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
			customerServiceOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		}else{
			customerServiceOrderCustomCriteria.andDelFlagEqualTo("0");
		}
		customerServiceOrderCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		String searchOrderType = request.getParameter("searchOrderType");
		if(StringUtil.isEmpty(searchOrderType)){//默认：0.发货前退款
			searchOrderType = REFUND_BEFORE_DELIVERY;
		}
		List<String> serviceTypeList = new ArrayList<String>();
		serviceTypeList.add(CustomerServiceOrderCustom.ORDER_RETURN_GOODS);
		serviceTypeList.add(CustomerServiceOrderCustom.ORDER_EXCHANGE_GOODS);
		if(searchOrderType.equals(REFUND_BEFORE_DELIVERY)){//0.发货前退款
			customerServiceOrderCustomCriteria.andServiceTypeEqualTo(CustomerServiceOrderCustom.ORDER_REFUND);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_ING);
			customerServiceOrderCustomCriteria.andProStatusEqualTo(CustomerServiceStatusLogCustom.ORDER_HAS_APPLY_REFUND);
		}else if(searchOrderType.equals(RETURN_EXCHANGE_APPLY)){//1.退换货申请
			customerServiceOrderCustomCriteria.andServiceTypeIn(serviceTypeList);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_ING);
			List<String> proStatusList = new ArrayList<String>();
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_HAS_APPLY_RETURN_GOODS);
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_HAS_APPLY_EXCHANGE_GOODS);
			customerServiceOrderCustomCriteria.andProStatusIn(proStatusList);
		}else if(searchOrderType.equals(RETURN_EXCHANGE_WAITING_ACCEPT)){//2.退货待收件
//			customerServiceOrderCustomCriteria.andServiceTypeIn(serviceTypeList);
			customerServiceOrderCustomCriteria.andServiceTypeEqualTo(CustomerServiceOrderCustom.ORDER_RETURN_GOODS);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_ING);
//			List<String> proStatusList = new ArrayList<String>();
//			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_HAS_SEND_RETURN_GOODS);
//			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_CLIENT_HAS_SEND_EXCHANGE_GOODS);
//			customerServiceOrderCustomCriteria.andProStatusIn(proStatusList);
			customerServiceOrderCustomCriteria.andProStatusEqualTo(CustomerServiceStatusLogCustom.ORDER_HAS_SEND_RETURN_GOODS);
		}else if(searchOrderType.equals(EXCHANGE_WAITING_SEND)){//3.换货待收发件
			customerServiceOrderCustomCriteria.andServiceTypeEqualTo(CustomerServiceOrderCustom.ORDER_EXCHANGE_GOODS);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_ING);
//			customerServiceOrderCustomCriteria.andProStatusEqualTo(CustomerServiceStatusLogCustom.ORDER_HAS_ACCEPT_EXCHANGE_GOODS);
			List<String> proStatusList = new ArrayList<String>();
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_CLIENT_HAS_SEND_EXCHANGE_GOODS);
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_HAS_ACCEPT_EXCHANGE_GOODS);
			customerServiceOrderCustomCriteria.andProStatusIn(proStatusList);
		}else if(searchOrderType.equals(MCHT_CLOSE)){//4.售后完成
			customerServiceOrderCustomCriteria.andServiceTypeNotEqualTo(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_COMPLETE);
		}else if(searchOrderType.equals(MCHT_DIRECT_COMPENSATION)){//5.商家直赔
			customerServiceOrderCustomCriteria.andServiceTypeEqualTo(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
			customerServiceOrderCustomCriteria.andStatusNotEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_CANCLE);
			customerServiceOrderCustomCriteria.andStatusNotEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_COMPLETE);
		}else if(searchOrderType.equals(ALL_ORDER)){//6.全部售后
			
		}else if(searchOrderType.equals(WAIT_MEMBER_SEND)){//7.等待客户寄件
			customerServiceOrderCustomCriteria.andServiceTypeIn(serviceTypeList);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_ING);
			List<String> proStatusList = new ArrayList<String>();
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_RETURN_GOODS);
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_EXCHANGE_GOODS);
			customerServiceOrderCustomCriteria.andProStatusIn(proStatusList);
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_orderCode"))) {
			customerServiceOrderCustomCriteria.andOrderCodeEqualTo(request.getParameter("search_orderCode").trim());
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_receiverName"))) {
			customerServiceOrderCustomCriteria.andReceiverNameEqualTo(request.getParameter("search_receiverName").trim());
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_subOrderCode"))) {
			customerServiceOrderCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("search_subOrderCode").trim());
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_receiverPhone"))) {
			customerServiceOrderCustomCriteria.andReceiverPhoneEqualTo(request.getParameter("search_receiverPhone").trim());
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_productId"))) {
			customerServiceOrderCustomCriteria.andProductCodeEqualTo(request.getParameter("search_productId").trim());
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_brandId"))) {
			customerServiceOrderCustomCriteria.andBrandIdEqualTo(request.getParameter("search_brandId").trim());
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_expressNo"))) {
			customerServiceOrderCustomCriteria.andExpressNoEqualTo(request.getParameter("search_expressNo").trim());
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_memberExpressNo"))) {
			customerServiceOrderCustomCriteria.andMemberExpressNoEqualTo(request.getParameter("search_memberExpressNo").trim());
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_activityId"))) {
			customerServiceOrderCustomCriteria.andActivityIdEqualTo(Integer.parseInt(request.getParameter("search_activityId").trim()));
		}
		
		String searchServiceType = request.getParameter("search_serviceType");
		if (!StringUtil.isEmpty(searchServiceType)) {
			if(!searchServiceType.equals("-1")){
				customerServiceOrderCustomCriteria.andServiceTypeEqualTo(searchServiceType);
			}
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (!StringUtil.isEmpty(request.getParameter("createTimeBegin"))) {
				customerServiceOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sf.parse(request.getParameter("createTimeBegin")+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("createTimeEnd"))) {
				customerServiceOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sf.parse(request.getParameter("createTimeEnd")+" 23:59:59"));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int totalCount = customerServiceOrderService.countCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
		
		//发货前退款数量
		int refundBeforeDeliveryCount = customerServiceOrderService.countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(this.getSessionMchtInfo(request).getId(),CustomerServiceOrderCustom.ORDER_REFUND,CustomerServiceStatusLogCustom.ORDER_HAS_APPLY_REFUND,CustomerServiceOrderCustom.ORDER_STATUS_ING);
		
		//退货申请数量
		int returnGoodsApplyCount = customerServiceOrderService.countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(this.getSessionMchtInfo(request).getId(),CustomerServiceOrderCustom.ORDER_RETURN_GOODS,CustomerServiceStatusLogCustom.ORDER_HAS_APPLY_RETURN_GOODS,CustomerServiceOrderCustom.ORDER_STATUS_ING);
		//换货申请数量
		int exchangeGoodsApplyCount = customerServiceOrderService.countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(this.getSessionMchtInfo(request).getId(),CustomerServiceOrderCustom.ORDER_EXCHANGE_GOODS,CustomerServiceStatusLogCustom.ORDER_HAS_APPLY_EXCHANGE_GOODS,CustomerServiceOrderCustom.ORDER_STATUS_ING);
		
		//退货待收件
		int returnToReceiveCount = customerServiceOrderService.countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(this.getSessionMchtInfo(request).getId(),CustomerServiceOrderCustom.ORDER_RETURN_GOODS,CustomerServiceStatusLogCustom.ORDER_HAS_SEND_RETURN_GOODS,CustomerServiceOrderCustom.ORDER_STATUS_ING);
		//换货待收件
		int exchangeGoodsToReceiveCount = customerServiceOrderService.countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(this.getSessionMchtInfo(request).getId(),CustomerServiceOrderCustom.ORDER_EXCHANGE_GOODS,CustomerServiceStatusLogCustom.ORDER_CLIENT_HAS_SEND_EXCHANGE_GOODS,CustomerServiceOrderCustom.ORDER_STATUS_ING);
		
		//换货待发件
		int exchangeGoodsToSendCount = customerServiceOrderService.countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(this.getSessionMchtInfo(request).getId(),CustomerServiceOrderCustom.ORDER_EXCHANGE_GOODS,CustomerServiceStatusLogCustom.ORDER_HAS_ACCEPT_EXCHANGE_GOODS,CustomerServiceOrderCustom.ORDER_STATUS_ING);
		
		//退货单等待客户寄件
		int returnGoodsWaitMemberSendCount = customerServiceOrderService.countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(this.getSessionMchtInfo(request).getId(),CustomerServiceOrderCustom.ORDER_RETURN_GOODS,CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_RETURN_GOODS,CustomerServiceOrderCustom.ORDER_STATUS_ING);
		
		//换货单等待客户寄件
		int exchangeGoodsWaitMemberSendCount = customerServiceOrderService.countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(this.getSessionMchtInfo(request).getId(),CustomerServiceOrderCustom.ORDER_EXCHANGE_GOODS,CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_EXCHANGE_GOODS,CustomerServiceOrderCustom.ORDER_STATUS_ING);
		
		customerServiceOrderCustomExample.setLimitStart(page.getLimitStart());
		customerServiceOrderCustomExample.setLimitSize(page.getLimitSize());
		List<CustomerServiceOrderCustom> customerServiceOrderCustoms = customerServiceOrderService.selectCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
		//遍历customerServiceOrderCustoms，给每个customerServiceOrderCustom设置orderDtl
		for(CustomerServiceOrderCustom customerServiceOrderCustom:customerServiceOrderCustoms){
			OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(customerServiceOrderCustom.getOrderDtlId());
			if(orderDtl!=null && !StringUtil.isEmpty(orderDtl.getSkuPic())){
				orderDtl.setSkuPic(FileUtil.getSmallImageName(orderDtl.getSkuPic()));
			}
			customerServiceOrderCustom.setOrderDtl(orderDtl);
		}
		returnData.put("Rows", customerServiceOrderCustoms);
		returnData.put("Total", totalCount);
		returnData.put("refundBeforeDeliveryCount", refundBeforeDeliveryCount);
		returnData.put("returnOrExchangeApplyCount", returnGoodsApplyCount+exchangeGoodsApplyCount);
		returnData.put("returnOrExchangeReceiveCount", returnToReceiveCount);
		returnData.put("exchangeGoodsToSendCount", exchangeGoodsToSendCount+exchangeGoodsToReceiveCount);
		returnData.put("returnOrExchangeWaitMemberSendCount", returnGoodsWaitMemberSendCount+exchangeGoodsWaitMemberSendCount);
		returnData.put("searchOrderType", searchOrderType);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 售后单详情
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/customerServiceOrder/customerServiceOrderView")
	public String customerServiceOrderView(Model model,HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String orderDtlIdStr = request.getParameter("orderDtlId");
		String subOrderIdStr = request.getParameter("subOrderId");
		String serviceType = request.getParameter("serviceType");
		CustomerServiceOrder customerServiceOrder = new CustomerServiceOrder();
		OrderDtl orderDtl = new OrderDtl();
		if(!StringUtil.isEmpty(idStr)){
			Integer id = Integer.parseInt(idStr);
			customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(id);
			if (customerServiceOrder.getOrderDtlId()!=null) {
				orderDtl = orderDtlService.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());
				orderDtl.setSkuPic(FileUtil.getSmallImageName(orderDtl.getSkuPic()));
			}
		}else{
			if(!StringUtil.isEmpty(orderDtlIdStr)){
				orderDtl = orderDtlService.selectByPrimaryKey(Integer.parseInt(orderDtlIdStr));
				orderDtl.setSkuPic(FileUtil.getSmallImageName(orderDtl.getSkuPic()));
				customerServiceOrder = customerServiceOrderService.getCustomerServiceOrderByOrderDtlId(Integer.parseInt(orderDtlIdStr));
			}else {
				if (!StringUtil.isEmpty(subOrderIdStr)){  //直赔
					customerServiceOrder = customerServiceOrderService.getDirectCompensationOrder(Integer.parseInt(subOrderIdStr), serviceType);
				}
			}

		}
		if (customerServiceOrder.getServiceType().equals(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION)){ //直赔
				customerServiceOrder = customerServiceOrderService.getDirectCompensationOrder(customerServiceOrder.getSubOrderId(), customerServiceOrder.getServiceType());
		}
		CustomerServiceLogCustomExample customerServiceLogCustomExample = new CustomerServiceLogCustomExample();
		CustomerServiceLogCustomExample.CustomerServiceLogCustomCriteria customerServiceLogCustomCriteria = customerServiceLogCustomExample.createCriteria();
		customerServiceLogCustomCriteria.andDelFlagEqualTo("0");
		customerServiceLogCustomCriteria.andServiceOrderIdEqualTo(customerServiceOrder.getId());
		List<CustomerServiceLogCustom> customerServiceLogCustoms = customerServiceLogService.selectCustomerServiceLogCustomByExample(customerServiceLogCustomExample);
		for(CustomerServiceLogCustom customerServiceLogCustom:customerServiceLogCustoms){
			List<ServiceLogPicCustom> serviceLogPicCustoms = serviceLogPicService.getServiceLogPicsByServiceLogId(customerServiceLogCustom.getId());
			customerServiceLogCustom.setServiceLogPics(serviceLogPicCustoms);
		}
		String serviceTypeDesc = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "SERVICE_TYPE", customerServiceOrder.getServiceType());
		String proStatusDesc = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "PRO_STATUS", customerServiceOrder.getProStatus());
		String statusDesc = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "STATUS", customerServiceOrder.getStatus());
		String reasonDesc = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", customerServiceOrder.getReason());
		model.addAttribute("customerServiceLogCustoms", customerServiceLogCustoms);
		model.addAttribute("customerServiceOrder", customerServiceOrder);
		model.addAttribute("orderDtl", orderDtl);
		model.addAttribute("serviceTypeDesc", serviceTypeDesc);
		model.addAttribute("proStatusDesc", proStatusDesc);
		model.addAttribute("statusDesc", statusDesc);
		model.addAttribute("reasonDesc", reasonDesc);
		List<CustomerServicePicCustom> customerServicePics = customerServicePicService.getPicsByServiceOrderId(customerServiceOrder.getId());
		model.addAttribute("customerServicePics", customerServicePics);
		//介入单
		int interventionOrderCount = 0;
		InterventionOrderExample ioe = new InterventionOrderExample();
		InterventionOrderExample.Criteria ioec = ioe.createCriteria();
		ioec.andDelFlagEqualTo("0");
		ioec.andServiceOrderIdEqualTo(customerServiceOrder.getId());
		ioec.andProStatusEqualTo(customerServiceOrder.getProStatus());
		List<InterventionOrder> interventionOrders = interventionOrderService.selectByExample(ioe);
		if(interventionOrders!=null && interventionOrders.size()>0){
			interventionOrderCount = interventionOrders.size();
			InterventionOrder interventionOrder = interventionOrders.get(0);
			model.addAttribute("winType", interventionOrder.getWinType());
			model.addAttribute("interventionOrderStatus", interventionOrder.getStatus());
		}
		model.addAttribute("interventionOrderCount", interventionOrderCount);
		
		//物流信息
		SubOrderCustom subOrderCustom = subOrderService.selectSubOrderCustomByPrimaryKey(customerServiceOrder.getSubOrderId());
		if(customerServiceOrder.getServiceType().equals(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION)){
			OrderDtlExample ode = new OrderDtlExample();
			OrderDtlExample.Criteria odec = ode.createCriteria();
			odec.andDelFlagEqualTo("0");
			odec.andSubOrderIdEqualTo(customerServiceOrder.getSubOrderId());
			List<OrderDtl> orderDtls = orderDtlService.selectByExample(ode);
			String activityIds = "";
			for(OrderDtl od : orderDtls){
				if(od.getActivityId()!=null){
					if(activityIds.indexOf(od.getActivityId().toString())<0){
						activityIds+=od.getActivityId()+",";
					}
				}
			}
			if(!StringUtil.isEmpty(activityIds)){
				model.addAttribute("activityIds", activityIds.substring(0, activityIds.length()-1));
			}
		}
		if(!StringUtil.isEmpty(subOrderCustom.getExpressId()) && !StringUtil.isEmpty(subOrderCustom.getExpressNo())){
			KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
			KdnWuliuInfoExample.Criteria kdnWuliuInfoCriteria = kdnWuliuInfoExample.createCriteria();
			kdnWuliuInfoCriteria.andDelFlagEqualTo("0");
			kdnWuliuInfoCriteria.andExpressIdEqualTo(Integer.parseInt(subOrderCustom.getExpressId()));
			kdnWuliuInfoCriteria.andLogisticCodeEqualTo(subOrderCustom.getExpressNo());
			List<KdnWuliuInfo> kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
			if(kdnWuliuInfos!=null && kdnWuliuInfos.size()>0){
				if(!StringUtil.isEmpty(kdnWuliuInfos.get(0).getTractInfo())){
					JSONArray wuliuInfos = JSONArray.fromObject(kdnWuliuInfos.get(0).getTractInfo());
					model.addAttribute("hasWuliu", true);
					model.addAttribute("wuliuInfos", wuliuInfos);
				}else{
					model.addAttribute("hasWuliu", false);
				}
			}else{
				model.addAttribute("hasWuliu", false);
			}
		}else{
			model.addAttribute("hasWuliu", false);
		}

		//用户退换货寄件回来的物流信息
        if (!StringUtil.isEmpty(customerServiceOrder.getMemberExpressCompany())&& !StringUtil.isEmpty(customerServiceOrder.getMemberExpressNo())){
            //根据售后表中的快递公司查询出express的快递id
            ExpressExample expressExample = new ExpressExample();
            ExpressExample.Criteria expressExampleCriteria = expressExample.createCriteria();
            expressExampleCriteria.andNameEqualTo(customerServiceOrder.getMemberExpressCompany());
            expressExampleCriteria.andDelFlagEqualTo("0");
            List<Express> expresses = expressCustomMapper.getExpressByName(customerServiceOrder.getMemberExpressCompany());
            List<KdnWuliuInfo> kdnWuliuInfos = null;
            if(expresses != null && expresses.size() > 0){
                KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
                KdnWuliuInfoExample.Criteria kdnWuliuInfoCriteria = kdnWuliuInfoExample.createCriteria();
                kdnWuliuInfoCriteria.andDelFlagEqualTo("0");
                kdnWuliuInfoCriteria.andExpressIdEqualTo(expresses.get(0).getId());
                kdnWuliuInfoCriteria.andLogisticCodeEqualTo(customerServiceOrder.getMemberExpressNo());
                kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
            }
            if(kdnWuliuInfos!=null && kdnWuliuInfos.size()>0){
                if(!StringUtil.isEmpty(kdnWuliuInfos.get(0).getTractInfo())){
                    JSONArray wuliuInfos = JSONArray.fromObject(kdnWuliuInfos.get(0).getTractInfo());
                    model.addAttribute("hasReturnWuliu", true);
                    model.addAttribute("returnWuliuInfos", wuliuInfos);
                }else{
                    model.addAttribute("hasReturnWuliu", false);
                }
            }else{
                model.addAttribute("hasReturnWuliu", false);
            }

        }else {
            model.addAttribute("hasReturnWuliu", false);
        }


		model.addAttribute("subOrderCustom", subOrderCustom);
		if (customerServiceOrder.getOrderDtlId()!=null) {
			SubDepositOrderExample sdoe = new SubDepositOrderExample();
			sdoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
			List<SubDepositOrder> subDepositOrders = subDepositOrderMapper.selectByExample(sdoe);
			if (subDepositOrders != null && subDepositOrders.size() > 0) {
				model.addAttribute("subDepositOrder", subDepositOrders.get(0));
			}
		}
		
		if(customerServiceOrder.getServiceType().equals(CustomerServiceOrderCustom.ORDER_REFUND)){//退款单
			List<SysParamCfg> refundSysParamCfgList = sysParamCfgService.getSysParamCfgListByParamCode("JJTKSQ");
			model.addAttribute("refundSysParamCfgList", refundSysParamCfgList);
			return "customerServiceOrder/customerServiceOrderViewRefund";
		}else if(customerServiceOrder.getServiceType().equals(CustomerServiceOrderCustom.ORDER_RETURN_GOODS)){//退货单
			List<SysParamCfg> returnGoodsApplySysParamCfgList = sysParamCfgService.getSysParamCfgListByParamCode("JJTHSQ");
			List<SysParamCfg> returnGoodsRefundSysParamCfgList = sysParamCfgService.getSysParamCfgListByParamCode("JJTHTK");
			model.addAttribute("returnGoodsApplySysParamCfgList", returnGoodsApplySysParamCfgList);
			model.addAttribute("returnGoodsRefundSysParamCfgList", returnGoodsRefundSysParamCfgList);
			if(orderDtl.getCloudProductItemId() != null){
				CloudProductItem cloudProductItem = cloudProductItemService.selectByPrimaryKey(orderDtl.getCloudProductItemId());
				if(cloudProductItem!=null){
					CloudProduct cloudProduct = cloudProductService.selectByPrimaryKey(cloudProductItem.getCloudProductId());
					CloudProductAfterTempletCustom cloudProductAfterTempletCustom = cloudProductAfterTempletService.selectCustomByPrimaryKey(cloudProduct.getCloudProductAfterTempletId());
					model.addAttribute("cloudProductAfterTempletCustom", cloudProductAfterTempletCustom);
				}
			}
			ProductItem productItem = productItemService.findById(orderDtl.getProductItemId());
			Product product = productService.selectByPrimaryKey(productItem.getProductId());
			if(product.getCsTempletId()!=null){
				ProductAfterTemplet productAfterTemplet = productAfterTempletService.selectByPrimaryKey(product.getCsTempletId());
				if(productAfterTemplet!=null){
					model.addAttribute("productAfterTemplet", productAfterTemplet);
					if(productAfterTemplet.getProvince()!=null){
						Area provinceArea = areaService.selectByPrimaryKey(productAfterTemplet.getProvince());
						if(provinceArea!=null){
							model.addAttribute("province", provinceArea.getAreaName());
						}
					}
					if(productAfterTemplet.getCity()!=null){
						Area cityArea = areaService.selectByPrimaryKey(productAfterTemplet.getCity());
						if(cityArea!=null){
							model.addAttribute("city", cityArea.getAreaName());
						}
					}
					if(productAfterTemplet.getCounty()!=null){
						Area countyArea = areaService.selectByPrimaryKey(productAfterTemplet.getCounty());
						if(countyArea!=null){
							model.addAttribute("county", countyArea.getAreaName());
						}
					}
				}
			}
			ExpressExample example = new ExpressExample();
			ExpressExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			List<Express> expressList = expressMapper.selectByExample(example);
			model.addAttribute("expressList", expressList);
			return "customerServiceOrder/customerServiceOrderViewReturnGoods";
		}else if(customerServiceOrder.getServiceType().equals(CustomerServiceOrderCustom.ORDER_EXCHANGE_GOODS)){//换货单
			List<SysParamCfg> exchangeGoodsApplySysParamCfgList = sysParamCfgService.getSysParamCfgListByParamCode("JJHHSQ");
			List<SysParamCfg> exchangeGoodsSysParamCfgList = sysParamCfgService.getSysParamCfgListByParamCode("JJHH");
			ExpressExample example = new ExpressExample();
			ExpressExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			List<Express> expressList = expressMapper.selectByExample(example);
			model.addAttribute("expressList", expressList);
			model.addAttribute("exchangeGoodsApplySysParamCfgList", exchangeGoodsApplySysParamCfgList);
			model.addAttribute("exchangeGoodsSysParamCfgList", exchangeGoodsSysParamCfgList);
			
			ProductItem productItem = productItemService.findById(orderDtl.getProductItemId());
			Product product = productService.selectByPrimaryKey(productItem.getProductId());
			if(product.getCsTempletId()!=null){
				ProductAfterTemplet productAfterTemplet = productAfterTempletService.selectByPrimaryKey(product.getCsTempletId());
				if(productAfterTemplet!=null){
					model.addAttribute("productAfterTemplet", productAfterTemplet);
					if(productAfterTemplet.getProvince()!=null){
						Area provinceArea = areaService.selectByPrimaryKey(productAfterTemplet.getProvince());
						if(provinceArea!=null){
							model.addAttribute("province", provinceArea.getAreaName());
						}
					}
					if(productAfterTemplet.getCity()!=null){
						Area cityArea = areaService.selectByPrimaryKey(productAfterTemplet.getCity());
						if(cityArea!=null){
							model.addAttribute("city", cityArea.getAreaName());
						}
					}
					if(productAfterTemplet.getCounty()!=null){
						Area countyArea = areaService.selectByPrimaryKey(productAfterTemplet.getCounty());
						if(countyArea!=null){
							model.addAttribute("county", countyArea.getAreaName());
						}
					}
				}
			}
			return "customerServiceOrder/customerServiceOrderViewExchangeGoods";
		}else{//直赔单
			return "customerServiceOrder/customerServiceOrderViewDirectCompensation";
		}
	}
	
	/**
	 * 1.同意退款;2.同意退货申请--同意退款;3.同意换货申请--同意换货
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/customerServiceOrder/customerServiceOrderAgree")
	@ResponseBody
	public synchronized ResponseMsg customerServiceOrderAgree(HttpServletRequest request, Page page) {
		String customerServiceOrderId = request.getParameter("customerServiceOrderId");
		String proStatus = request.getParameter("proStatus");
		String isRefund = request.getParameter("isRefund");
		String content = request.getParameter("content");
		Date now = new Date();
		CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(Integer.parseInt(customerServiceOrderId));
		//TODO 有重复的售后单时，直接提示商家
		CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
		customerServiceOrderExample.createCriteria()
			.andDelFlagEqualTo("0").andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId())
			.andServiceTypeEqualTo(customerServiceOrder.getServiceType()).andStatusIn(Arrays.asList("0","1"));
		int count = customerServiceOrderService.countByExample(customerServiceOrderExample);
		if(count>1){
			return new ResponseMsg(ResponseMsg.ERROR, "该商品有多个售后单，请联系相关人员进行处理",null);
		}
		
		InterventionOrderExample ioe = new InterventionOrderExample();
		InterventionOrderExample.Criteria ioec = ioe.createCriteria();
		ioec.andDelFlagEqualTo("0");
		ioec.andServiceOrderIdEqualTo(customerServiceOrder.getId());
		ioec.andProStatusEqualTo(customerServiceOrder.getProStatus());
		List<InterventionOrder> interventionOrders = interventionOrderService.selectByExample(ioe);
		if(interventionOrders==null || interventionOrders.size()==0){//没有介入单的情况下
			if(!customerServiceOrder.getStatus().equals("0")){
				return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
			}
			
			if(proStatus.equals("A2")){
				if(!customerServiceOrder.getProStatus().equals("A1")){
					return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
				}
			}else if(proStatus.equals("B2")){
				if(!customerServiceOrder.getProStatus().equals("B1")){
					return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
				}
				String addressType = request.getParameter("addressType");
				String cloudProductAfterTempletId = request.getParameter("cloudProductAfterTempletId");
				String supplierAddress = request.getParameter("supplierAddress");
				if(!StringUtil.isEmpty(addressType)){
					customerServiceOrder.setAddressType(addressType);
				}
				if(!StringUtil.isEmpty(cloudProductAfterTempletId) && "1".equals(addressType)){
					customerServiceOrder.setCloudProductAfterTempletId(Integer.parseInt(cloudProductAfterTempletId));
				}
				if(!StringUtil.isEmpty(supplierAddress)  && "1".equals(addressType)){
					customerServiceOrder.setSupplierAddress(supplierAddress);
				}
			}else if(proStatus.equals("B5")){
				if(!customerServiceOrder.getProStatus().equals("B1") && !customerServiceOrder.getProStatus().equals("B4") && !customerServiceOrder.getProStatus().equals("C4")){
					return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
				}
			}else if(proStatus.equals("C2")){
				if(!customerServiceOrder.getProStatus().equals("C1")){
					return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
				}
			}else if(proStatus.equals("C5")){
				if(!customerServiceOrder.getProStatus().equals("C4")){
					return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
				}
			}
		}
		if(customerServiceOrder.getProStatus().equals(proStatus)){
			return new ResponseMsg(ResponseMsg.ERROR, "只能同意一次，请勿重复操作",null);
		}
		
		customerServiceOrder.setProStatus(proStatus);
		customerServiceOrder.setStatus("0");//进行中
		customerServiceOrder.setUpdateDate(now);
		//退还货单的寄回地址优化
		customerServiceOrder.setProductReturnAddress(request.getParameter("editAddress"));
		customerServiceOrder.setProductReturnContactPhone(request.getParameter("editPhone"));
		customerServiceOrder.setProductReturnConsignee(request.getParameter("editRecipient"));
		customerServiceOrder.setProductReturnRemarks(request.getParameter("editRemarks"));

		
		CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
		customerServiceStatusLog.setCreateBy(this.getSessionUserInfo(request).getId());
		customerServiceStatusLog.setCreateDate(now);
		customerServiceStatusLog.setUpdateDate(now);
		customerServiceStatusLog.setDelFlag("0");
		customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceStatusLog.setStatus("0");//进行中
		customerServiceStatusLog.setProStatus(proStatus);

		CustomerServiceLog customerServiceLog = new CustomerServiceLog();
		customerServiceLog.setCreateBy(this.getSessionUserInfo(request).getId());
		customerServiceLog.setCreateDate(now);
		customerServiceLog.setUpdateDate(now);
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceLog.setOperatorType(CustomerServiceLogCustom.OPERATOR_TYPE_MCHT);//商家
		customerServiceLog.setContent(StringUtil.unEscapeHtmlAndIllegal(content));
		
		SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
		
		SysAppMessage sysAppMessage = new SysAppMessage();
//		sysAppMessage.setCreateBy(this.getMchtInfo().getId());
//		sysAppMessage.setCreateDate(now);
//		sysAppMessage.setUpdateDate(now);
//		sysAppMessage.setDelFlag("0");
//		sysAppMessage.setType(SysAppMessageCustom.TYPE_WULIU);
//		sysAppMessage.setExpressNo(subOrder.getExpressNo());
//		sysAppMessage.setLinkType(SysAppMessageCustom.LINKTYPE_SERVICE_ORDER);
//		sysAppMessage.setLinkId(customerServiceOrder.getId());
//		sysAppMessage.setMemberId(combineOrder.getMemberId());
//		if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_REFUND)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_REFUND_APPLY);
//			sysAppMessage.setContent("商家已经同意了你的退款申请");
//		}else if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_RETURN_GOODS)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_SERVICE_APPLY);
//			sysAppMessage.setContent("商家已经同意了你的退货申请");
//		}else if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_HAS_ACCEPT_RETURN_GOODS)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_RETURNGOODS_REFUND);
//			sysAppMessage.setContent("商家已经同意了你的退款申请");
//		}else if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_EXCHANGE_GOODS)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_SERVICE_APPLY);
//			sysAppMessage.setContent("商家已经同意了你的换货申请");
//		}else if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_HAS_ACCEPT_EXCHANGE_GOODS)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_EXCHANGEGOODS_REMIND);
//			sysAppMessage.setContent("商家已经同意了你的换货");
//		}else if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_MCHT_HAS_SEND_EXCHANGE_GOODS)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_EXCHANGEGOODS_REMIND);
//			sysAppMessage.setContent("商家已寄件（换货）");
//		}else{
//			sysAppMessage.setTitle("商家已同意");
//			sysAppMessage.setContent("商家已处理");
//		}
		
		OrderDtl orderDtl = null;
		RefundOrder refundOrder = null;
		IntegralDtl integralDtl = null;
		MemberAccount memberAccount = null;
		MchtDeposit	mchtDeposit = null;
		MchtDepositDtl mdd = null;
		List<RefundOrder> refundOrderList = new ArrayList<>();
		if(isRefund.equals("1")){//同意退款操作
			BigDecimal amount = customerServiceOrder.getAmount();
			if(customerServiceOrder.getOrderDtlId()!=null){
				orderDtl = orderDtlService.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());
				if(amount.compareTo(new BigDecimal(0))<=0){//针对换货转退货退款
					amount = orderDtl.getPayAmount();
					customerServiceOrder.setAmount(amount);
				}
				if(customerServiceOrder.getServiceType().equals("A")){//A.退款单
					orderDtl.setProductStatus("2");//1 完成 2退款 3退货
				}else if(customerServiceOrder.getServiceType().equals("B")){//B.退货单
					orderDtl.setProductStatus("3");//1 完成 2退款 3退货
				}else if(customerServiceOrder.getServiceType().equals("C") && customerServiceOrder.getProStatus().equals("B5")){//换货改退货
					orderDtl.setProductStatus("3");//1 完成 2退款 3退货
				}
				
				//根据订单明细id获取所有的售后单，进而获取相应的退款单，若存在退款单，则不生成退款单，以免重复退款
				CustomerServiceOrderExample example = new CustomerServiceOrderExample();
				CustomerServiceOrderExample.Criteria criteria = example.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
			 	List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(example);
			 	List<Integer> customerServiceOrderIds = new ArrayList<Integer>();
			 	if(customerServiceOrders!=null && customerServiceOrders.size()>0){
			 		for(CustomerServiceOrder cso:customerServiceOrders){
			 			customerServiceOrderIds.add(cso.getId());
			 		}
			 	}else{
			 		customerServiceOrderIds.add(0);
			 	}
			 	RefundOrderExample e = new RefundOrderExample();
				RefundOrderExample.Criteria c = e.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andServiceOrderIdIn(customerServiceOrderIds);
				c.andOrderTypeEqualTo("1");//1.总订单
				List<RefundOrder> ros = refundOrderService.selectByExample(e);
				if(ros!=null && ros.size()>0){
					return new ResponseMsg(ResponseMsg.ERROR, "该商品已在退款处理中，无法再次退款，请联系平台进行处理",null);
				}


			}
			//往退款单表里插入数据,先判断退款单是否存在
			RefundOrderExample example = new RefundOrderExample();
			RefundOrderExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andServiceOrderIdEqualTo(Integer.parseInt(customerServiceOrderId));
			criteria.andOrderTypeEqualTo("1");//1.总订单
			List<RefundOrder> refundOrders = refundOrderService.selectByExample(example);
			if(refundOrders!=null && refundOrders.size()>0){
				return new ResponseMsg(ResponseMsg.ERROR, "只能同意一次，请勿重复操作",null);
			}
			refundOrder = new RefundOrder();
			refundOrder.setDelFlag("0");
			refundOrder.setCreateBy(this.getSessionUserInfo(request).getId());
			refundOrder.setCreateDate(now);
			refundOrder.setUpdateDate(now);
			refundOrder.setCombineOrderId(subOrder.getCombineOrderId());
			if(customerServiceOrder.getServiceType().equals("C") && customerServiceOrder.getProStatus().equals("B5")){//换货改退货
				refundOrder.setServiceType("B");
			}else{
				refundOrder.setServiceType(customerServiceOrder.getServiceType());
			}
			refundOrder.setServiceOrderId(customerServiceOrder.getId());
			refundOrder.setRefundAmount(amount);
			refundOrder.setRefundAgreeDate(new Date());
			refundOrder.setRefundMethod("1");
			refundOrder.setTryTimes(0);
			refundOrder.setStatus("0");
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			if(combineOrder.getPaymentId() == 1 || combineOrder.getPaymentId() == 6){
				refundOrder.setRefundCode("ZFB"+sf.format(new Date())+"T");
			}else if(combineOrder.getPaymentId() == 2 || combineOrder.getPaymentId() == 5){
				refundOrder.setRefundCode("WX"+sf.format(new Date())+"T");
			}else if(combineOrder.getPaymentId() == 3){
				refundOrder.setRefundCode("YL"+sf.format(new Date())+"T");
			}else if(combineOrder.getPaymentId() == 4){
				refundOrder.setRefundCode("GZH"+sf.format(new Date())+"T");
			}
			refundOrder.setOrderType("1");
			int result = orderDtl.getIntegralPreferential().compareTo(new BigDecimal(0));
			if(result > 0){
				MemberAccountExample mae = new MemberAccountExample();
				MemberAccountExample.Criteria c = mae.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andMemberIdEqualTo(customerServiceOrder.getCreateBy());
				List<MemberAccount> memberAccounts = memberAccountService.selectByExample(mae);
				if(memberAccounts!=null && memberAccounts.size()>0){
					Integer integral = Integer.parseInt(orderDtl.getIntegralPreferential().multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
					memberAccount = memberAccounts.get(0);
					memberAccount.setIntegral(memberAccount.getIntegral()+integral);
					memberAccount.setUpdateBy(this.getSessionUserInfo(request).getId());
					memberAccount.setUpdateDate(new Date());
					integralDtl = new IntegralDtl();
					integralDtl.setAccId(memberAccount.getId());
					integralDtl.setTallyMode("1");
					integralDtl.setType(7);
					integralDtl.setIntegral(integral);
					integralDtl.setBalance(memberAccount.getIntegral());
					integralDtl.setOrderId(orderDtl.getSubOrderId());
					integralDtl.setCreateBy(this.getSessionUserInfo(request).getId());
					integralDtl.setCreateDate(new Date());
					integralDtl.setDelFlag("0");
				}
			}
			// 返还津贴
			if (orderDtl.getAllowance() != null && orderDtl.getAllowance().compareTo(BigDecimal.ZERO) > 0) {
				MemberAllowance memberAllowance = new MemberAllowance();
				memberAllowance.setMemberId(combineOrder.getMemberId());
				memberAllowance.setSource("2");
				memberAllowance.setAllowanceAmount(orderDtl.getAllowance().setScale(0,BigDecimal.ROUND_DOWN));
				memberAllowance.setCreateDate(new Date());
				memberAllowance.setDelFlag("0");
				memberAllowanceMapper.insert(memberAllowance);
			}



			
			//TODO 定金退款单生成处理
			if(customerServiceOrder.getDepositAmount()!=null && customerServiceOrder.getDepositAmount().compareTo(new BigDecimal(0))>0){
				SubDepositOrderExample sdoe = new SubDepositOrderExample();
				sdoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
				List<SubDepositOrder> subDepositOrders = subDepositOrderMapper.selectByExample(sdoe);
				//循环插入退款单表数据
				for (SubDepositOrder subDepositOrder:subDepositOrders) {
					RefundOrder depositRefundOrder = new RefundOrder();
					depositRefundOrder.setDelFlag("0");
					depositRefundOrder.setCreateBy(this.getSessionUserInfo(request).getId());
					depositRefundOrder.setCreateDate(now);
					depositRefundOrder.setUpdateDate(now);
					depositRefundOrder.setCombineOrderId(subOrder.getCombineOrderId());
					Integer combineDepositOrderId = subDepositOrder.getCombineDepositOrderId();
					depositRefundOrder.setCombineDepositOrderId(combineDepositOrderId);
					CombineDepositOrder combineDepositOrder = combineDepositOrderMapper.selectByPrimaryKey(combineDepositOrderId);
					if(combineDepositOrder.getPaymentId() == 1 || combineDepositOrder.getPaymentId() == 6){
						depositRefundOrder.setRefundCode("ZFB"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 2 || combineDepositOrder.getPaymentId() == 5){
						depositRefundOrder.setRefundCode("WX"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 3){
						depositRefundOrder.setRefundCode("YL"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 4){
						depositRefundOrder.setRefundCode("GZH"+sf.format(new Date())+"T");
					}
					if(customerServiceOrder.getServiceType().equals("C") && customerServiceOrder.getProStatus().equals("B5")){//换货改退货
						depositRefundOrder.setServiceType("B");
					}else{
						depositRefundOrder.setServiceType(customerServiceOrder.getServiceType());
					}
					depositRefundOrder.setServiceOrderId(customerServiceOrder.getId());
					depositRefundOrder.setRefundAmount(subDepositOrder.getDeposit());
					depositRefundOrder.setRefundAgreeDate(new Date());
					depositRefundOrder.setRefundMethod("1");
					depositRefundOrder.setTryTimes(0);
					depositRefundOrder.setStatus("0");
					depositRefundOrder.setOrderType("2");
					refundOrderList.add(depositRefundOrder);
				}
			}
		}
		
		
		//TODO 定金，商家责任，扣除保证金（保证金金额为定金金额），送用户积分
		if(customerServiceOrder.getServiceType().equals("A") && proStatus.equals("A2") && customerServiceOrder.getDepositAmount()!=null && customerServiceOrder.getDepositAmount().compareTo(new BigDecimal(0))>0){
			if(customerServiceOrder.getReason().equals("A7")){//定金付款时的商家责任。
				BigDecimal totalDeposit = customerServiceOrder.getDepositAmount();
				MchtDepositExample mde = new MchtDepositExample();
				mde.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
				List<MchtDeposit> mchtDeposits = mchtDepositMapper.selectByExample(mde);
				mchtDeposit = mchtDeposits.get(0);
				mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().add(totalDeposit));
				mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().subtract(totalDeposit));
				mchtDeposit.setUpdateBy(this.getSessionUserInfo(request).getId());
				mchtDeposit.setUpdateDate(new Date());
				
				mdd = new MchtDepositDtl();
				mdd.setDepositId(mchtDeposit.getId());
				mdd.setTxnType("E");//E.违规扣款
				mdd.setTypeSub("E4");//售后
				mdd.setTxnAmt(totalDeposit.negate());
				mdd.setPayAmt(mchtDeposit.getPayAmt());
				mdd.setBizType("2");
				mdd.setRemarks("【售后】预售违规");
				mdd.setCreateBy(this.getSessionUserInfo(request).getId());
				mdd.setCreateDate(new Date());
				mdd.setDelFlag("0");
				
				MemberAccountExample mae = new MemberAccountExample();
				MemberAccountExample.Criteria c = mae.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andMemberIdEqualTo(customerServiceOrder.getCreateBy());
				List<MemberAccount> memberAccounts = memberAccountService.selectByExample(mae);
				Integer integral = Integer.parseInt(totalDeposit.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
				memberAccount = memberAccounts.get(0);
				memberAccount.setIntegral(memberAccount.getIntegral()+integral);
				memberAccount.setUpdateBy(this.getSessionUserInfo(request).getId());
				memberAccount.setUpdateDate(new Date());
				integralDtl = new IntegralDtl();
				integralDtl.setAccId(memberAccount.getId());
				integralDtl.setTallyMode("1");
				integralDtl.setType(7);
				integralDtl.setIntegral(integral);
				integralDtl.setBalance(memberAccount.getIntegral());
				integralDtl.setOrderId(orderDtl.getSubOrderId());
				integralDtl.setCreateBy(this.getSessionUserInfo(request).getId());
				integralDtl.setCreateDate(new Date());
				integralDtl.setDelFlag("0");
			}
		}
		customerServiceOrderService.updateCustomerServiceOrder(customerServiceOrder,customerServiceStatusLog,customerServiceLog,orderDtl,refundOrder,subOrder,sysAppMessage,integralDtl,memberAccount,mchtDeposit,mdd,refundOrderList);
		String toSendMobile = "";
		String contactPhone = customerServiceOrder.getContactPhone();
		if(StringUtil.isEmpty(contactPhone)){
			MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(combineOrder.getMemberId());
			String mobile = memberInfo.getMobile();
			if(StringUtil.isEmpty(mobile)){
				String receiverPhone = combineOrder.getReceiverPhone();
				if(!StringUtil.isEmpty(receiverPhone)){
					if(StringUtil.isMobileNO(receiverPhone)){
						toSendMobile = receiverPhone;
					}
				}
			}else{
				if(StringUtil.isMobileNO(mobile)){
					toSendMobile = mobile;
				}
			}
		}else{
			if(StringUtil.isMobileNO(contactPhone)){
				toSendMobile = contactPhone;
			}
		}
		if(!StringUtil.isEmpty(toSendMobile)){
			try {
				if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_RETURN_GOODS) || proStatus.equals(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_EXCHANGE_GOODS)){
					JSONObject param=new JSONObject();
					JSONObject reqData=new JSONObject();
					reqData.put("mobile", toSendMobile);
					reqData.put("content", "商家已同意您的申请，请尽快登录APP，在个人中心“退款/售后”找到相应的售后单填写寄回物流单号。");
					reqData.put("smsType", "4");
					param.put("reqData", reqData);
					JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				}else if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_MCHT_HAS_SEND_EXCHANGE_GOODS)){
					JSONObject param=new JSONObject();
					JSONObject reqData=new JSONObject();
					reqData.put("mobile", toSendMobile);
					reqData.put("content", "您申请的换货单，商家已发货，请及时的关注物流的变化。");
					reqData.put("smsType", "4");
					param.put("reqData", reqData);
					JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseMsg(ResponseMsg.SUCCESS, "操作成功",null);
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, "操作成功",null);
	}

	/**
	 * 批量退款
	 * @param request
	 * @return
	 */
	@RequestMapping("/customerServiceOrder/saveBatchRefund")
	@ResponseBody
	public ResponseMsg  saveBatchRefund(HttpServletRequest request){
		Integer id = this.getSessionUserInfo(request).getId();
		ResponseMsg responseMsg = customerServiceOrderService.saveBatchRefund(request,id);
		return responseMsg;
	}

	/**
	 * 批量退换货
	 * @param request
	 * @return
	 */
	@RequestMapping("customerServiceOrder/savaBatchReturn")
	@ResponseBody
	public ResponseMsg savaBatchReturn(HttpServletRequest request){
		Integer id = this.getSessionUserInfo(request).getId();
		ResponseMsg responseMsg = customerServiceOrderService.saveBatchReturn(request,id);
		return responseMsg;
	}




	
	/**
	 * 1.不同意退款;2.不同意退货申请--不同意退款;3.不同意换货申请--不同意换货
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/customerServiceOrder/customerServiceOrderDisAgree")
	@ResponseBody
	public ResponseMsg customerServiceOrderDisAgree(HttpServletRequest request, Page page) {
		String customerServiceOrderId = request.getParameter("customerServiceOrderId");
		String proStatus = request.getParameter("proStatus");
		String content = request.getParameter("content");
		String remarks = request.getParameter("remarks");
		String imgPaths = request.getParameter("imgPaths");
		Date now = new Date();
		CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(Integer.parseInt(customerServiceOrderId));
		
		if(proStatus.equals("A3")){
			if(!customerServiceOrder.getProStatus().equals("A1")){
				return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
			}
		}else if(proStatus.equals("B3")){
			if(!customerServiceOrder.getProStatus().equals("B1")){
				return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
			}
		}else if(proStatus.equals("B6")){
			if(!customerServiceOrder.getProStatus().equals("B4") && !customerServiceOrder.getProStatus().equals("B6")){
				return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
			}
		}else if(proStatus.equals("C3")){
			if(!customerServiceOrder.getProStatus().equals("C1")){
				return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
			}
		}else if(proStatus.equals("C6")){
			if(!customerServiceOrder.getProStatus().equals("C4") && !customerServiceOrder.getProStatus().equals("C6")){
				return new ResponseMsg(ResponseMsg.ERROR, "售后单状态有异，请刷新页面",null);
			}
		}
		customerServiceOrder.setProStatus(proStatus);
		customerServiceOrder.setStatus("2");
		customerServiceOrder.setUpdateDate(now);
		
		CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
		customerServiceStatusLog.setCreateBy(this.getSessionUserInfo(request).getId());
		customerServiceStatusLog.setCreateDate(now);
		customerServiceStatusLog.setUpdateDate(now);
		customerServiceStatusLog.setDelFlag("0");
		customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceStatusLog.setStatus(CustomerServiceOrderCustom.ORDER_STATUS_REFUSE);
		customerServiceStatusLog.setProStatus(proStatus);
		customerServiceStatusLog.setRemarks(StringUtil.unEscapeHtmlAndIllegal(content));
		
		CustomerServiceLog customerServiceLog = new CustomerServiceLog();
		customerServiceLog.setCreateBy(this.getSessionUserInfo(request).getId());
		customerServiceLog.setCreateDate(now);
		customerServiceLog.setUpdateDate(now);
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceLog.setOperatorType(CustomerServiceLogCustom.OPERATOR_TYPE_MCHT);//商家
		customerServiceLog.setContent(StringUtil.unEscapeHtmlAndIllegal(content));
		customerServiceLog.setRemarks(StringUtil.unEscapeHtmlAndIllegal(content)+remarks);
		List<ServiceLogPic> serviceLogPics = new ArrayList<ServiceLogPic>();
		if(!StringUtil.isEmpty(imgPaths)){
			String[] imgArray = imgPaths.split(",");
			for(int i=0;i<imgArray.length;i++){
				ServiceLogPic serviceLogPic = new ServiceLogPic();
				serviceLogPic.setCreateDate(now);
				serviceLogPic.setUpdateDate(now);
				serviceLogPic.setCreateBy(this.getSessionUserInfo(request).getId());
				serviceLogPic.setDelFlag("0");
				serviceLogPic.setPic(imgArray[i]);
				serviceLogPics.add(serviceLogPic);
			}
		}
		
		SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
		SysAppMessage sysAppMessage = new SysAppMessage();
//		sysAppMessage.setCreateBy(this.getMchtInfo().getId());
//		sysAppMessage.setCreateDate(now);
//		sysAppMessage.setUpdateDate(now);
//		sysAppMessage.setDelFlag("0");
//		sysAppMessage.setType(SysAppMessageCustom.TYPE_WULIU);
//		sysAppMessage.setExpressNo(subOrder.getExpressNo());
//		sysAppMessage.setLinkType(SysAppMessageCustom.LINKTYPE_SERVICE_ORDER);
//		sysAppMessage.setLinkId(customerServiceOrder.getId());
//		sysAppMessage.setMemberId(combineOrder.getMemberId());
//		if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_DISAGREE_APPLY_REFUND)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_REFUND_APPLY);
//			sysAppMessage.setContent("商家已经拒绝了你的退款申请");
//		}else if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_DISAGREE_APPLY_RETURN_GOODS)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_SERVICE_APPLY);
//			sysAppMessage.setContent("商家已经拒绝了你的退货申请");
//		}else if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_NOT_ACCEPT_RETURN_GOODS)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_RETURNGOODS_REFUND);
//			sysAppMessage.setContent("商家已经拒绝了你的退款");
//		}else if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_DISAGREE_APPLY_EXCHANGE_GOODS)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_EXCHANGEGOODS_REMIND);
//			sysAppMessage.setContent("商家已经拒绝了你的换货申请");
//		}else if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_NOT_ACCEPT_EXCHANGE_GOODS)){
//			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_EXCHANGEGOODS_REMIND);
//			sysAppMessage.setContent("因物品原因,商家已拒绝换货");
//		}
		customerServiceOrderService.updateCustomerServiceOrder(customerServiceOrder,customerServiceStatusLog,customerServiceLog,serviceLogPics,sysAppMessage);
		//短信提醒
		String toSendMobile = "";
		String contactPhone = customerServiceOrder.getContactPhone();
		if(StringUtil.isEmpty(contactPhone)){
			MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(combineOrder.getMemberId());
			String mobile = memberInfo.getMobile();
			if(StringUtil.isEmpty(mobile)){
				String receiverPhone = combineOrder.getReceiverPhone();
				if(!StringUtil.isEmpty(receiverPhone)){
					if(StringUtil.isMobileNO(receiverPhone)){
						toSendMobile = receiverPhone;
					}
				}
			}else{
				if(StringUtil.isMobileNO(mobile)){
					toSendMobile = mobile;
				}
			}
		}else{
			if(StringUtil.isMobileNO(contactPhone)){
				toSendMobile = contactPhone;
			}
		}
		if(!StringUtil.isEmpty(toSendMobile)){
			try {
				JSONObject param=new JSONObject();
				JSONObject reqData=new JSONObject();
				reqData.put("mobile", toSendMobile);
				reqData.put("smsType", "4");
				if(proStatus.equals("A3")){
					reqData.put("content", "商家已拒绝了您的{退款}申请,具体拒绝理由请尽快登录APP，在个人中心“退款/售后”找到相应的售后单进行查看");
					param.put("reqData", reqData);
					JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				}else if(proStatus.equals("B3")){
					reqData.put("content", "商家已拒绝了您的{退货}申请,具体拒绝理由请尽快登录APP，在个人中心“退款/售后”找到相应的售后单进行查看");
					param.put("reqData", reqData);
					JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				}else if(proStatus.equals("B6")){
					reqData.put("content", "商家不同意{退货退款},具体拒绝理由请尽快登录APP，在个人中心“退款/售后”找到相应的售后单进行查看");
					param.put("reqData", reqData);
					JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				}else if(proStatus.equals("C3")){
					reqData.put("content", "商家已拒绝了您的{换货}申请,具体拒绝理由请尽快登录APP，在个人中心“退款/售后”找到相应的售后单进行查看");
					param.put("reqData", reqData);
					JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				}else if(proStatus.equals("C6")){
					reqData.put("content", "商家不同意{换货},具体拒绝理由请尽快登录APP，在个人中心“退款/售后”找到相应的售后单进行查看");
					param.put("reqData", reqData);
					JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseMsg(ResponseMsg.SUCCESS, "操作成功",null);
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
	/**
	 * 换货--立即发货
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/customerServiceOrder/subOrderDelivery")
	@ResponseBody
	public ResponseMsg subOrderDelivery(HttpServletRequest request) {
		String expressId = request.getParameter("expressId");
		String expressNo = request.getParameter("expressNo");
		String subOrderId = request.getParameter("subOrderId");
		String orderDtlId = request.getParameter("orderDtlId");
		String merchantCode = request.getParameter("merchantCode");
		String customerServiceOrderId = request.getParameter("customerServiceOrderId");
		String proStatus = request.getParameter("proStatus");
		String content = request.getParameter("content");
		Date now = new Date();
		CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(Integer.parseInt(customerServiceOrderId));
		customerServiceOrder.setProStatus(proStatus);
		customerServiceOrder.setStatus(CustomerServiceOrderCustom.ORDER_STATUS_COMPLETE);
		customerServiceOrder.setUpdateDate(now);
		Express express = expressMapper.selectByPrimaryKey(Integer.parseInt(expressId));
		customerServiceOrder.setMchtExpressCompany(express.getName());
		customerServiceOrder.setMchtExpressNo(expressNo);
		
		CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
		customerServiceStatusLog.setCreateBy(this.getSessionUserInfo(request).getId());
		customerServiceStatusLog.setCreateDate(now);
		customerServiceStatusLog.setUpdateDate(now);
		customerServiceStatusLog.setDelFlag("0");
		customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceStatusLog.setStatus(customerServiceOrder.getStatus());
		customerServiceStatusLog.setProStatus(proStatus);
		customerServiceStatusLog.setRemarks(StringUtil.unEscapeHtmlAndIllegal(content));
		
		CustomerServiceLog customerServiceLog = new CustomerServiceLog();
		customerServiceLog.setCreateBy(this.getSessionUserInfo(request).getId());
		customerServiceLog.setCreateDate(now);
		customerServiceLog.setUpdateDate(now);
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceLog.setOperatorType(CustomerServiceLogCustom.OPERATOR_TYPE_MCHT);//商家
		customerServiceLog.setContent(StringUtil.unEscapeHtmlAndIllegal(content));
		
		SysAppMessage sysAppMessage = new SysAppMessage();
//		sysAppMessage.setCreateBy(this.getMchtInfo().getId());
//		sysAppMessage.setCreateDate(now);
//		sysAppMessage.setUpdateDate(now);
//		sysAppMessage.setDelFlag("0");
//		sysAppMessage.setType(SysAppMessageCustom.TYPE_WULIU);
//		sysAppMessage.setTitle(SysAppMessageCustom.TITLE_EXCHANGEGOODS_REMIND);
//		sysAppMessage.setContent("商家同意您换货并已寄件,运单号{"+express.getName()+","+expressNo+"},请注意查收");
//		sysAppMessage.setExpressNo(expressNo);
//		sysAppMessage.setLinkType(SysAppMessageCustom.LINKTYPE_SERVICE_ORDER);
//		sysAppMessage.setLinkId(customerServiceOrder.getId());
		SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
//		sysAppMessage.setMemberId(combineOrder.getMemberId());
		
		OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(Integer.parseInt(orderDtlId));
		orderDtl.setDeliveryStatus("1");
		orderDtl.setDtlExpressId(Integer.parseInt(expressId));
		orderDtl.setDtlExpressNo(expressNo);
		orderDtl.setMerchantCode(merchantCode);
		
		customerServiceOrderService.updateCustomerServiceOrder(customerServiceOrder,customerServiceStatusLog,customerServiceLog,orderDtl,null,null,sysAppMessage);
		String toSendMobile = "";
		String contactPhone = customerServiceOrder.getContactPhone();
		if(StringUtil.isEmpty(contactPhone)){
			MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(combineOrder.getMemberId());
			String mobile = memberInfo.getMobile();
			if(StringUtil.isEmpty(mobile)){
				String receiverPhone = combineOrder.getReceiverPhone();
				if(!StringUtil.isEmpty(receiverPhone)){
					if(StringUtil.isMobileNO(receiverPhone)){
						toSendMobile = receiverPhone;
					}
				}
			}else{
				if(StringUtil.isMobileNO(mobile)){
					toSendMobile = mobile;
				}
			}
		}else{
			if(StringUtil.isMobileNO(contactPhone)){
				toSendMobile = contactPhone;
			}
		}
		if(!StringUtil.isEmpty(toSendMobile)){
			if(proStatus.equals(CustomerServiceStatusLogCustom.ORDER_MCHT_HAS_SEND_EXCHANGE_GOODS)){
				JSONObject param=new JSONObject();
				JSONObject reqData=new JSONObject();
				reqData.put("mobile", toSendMobile);
				reqData.put("content", "您申请的换货单，商家已发货，请及时的关注物流的变化。");
				reqData.put("smsType", "4");
				param.put("reqData", reqData);
				try {
					JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				} catch (Exception e) {
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
				}
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
	/**
	 * 退货单--填写物流信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/customerServiceOrder/updateMemberExpress")
	@ResponseBody
	public ResponseMsg updateMemberExpress(HttpServletRequest request) {
		String expressName = request.getParameter("expressName");
		String expressNo = request.getParameter("expressNo");
		String customerServiceOrderId = request.getParameter("customerServiceOrderId");
		String proStatus = request.getParameter("proStatus");
		String content = request.getParameter("content");
		Date now = new Date();
		CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(Integer.parseInt(customerServiceOrderId));
		if(customerServiceOrder.getOrderDtlId()!=null){
			OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());
			if(orderDtl!=null && !StringUtil.isEmpty(orderDtl.getProductStatus())){
				return new ResponseMsg(ResponseMsg.ERROR, "售后单已完成或已撤销，不能替用户填写寄件信息!",null);
			}
		}
		ExpressExample expressExample = new ExpressExample();
		expressExample.createCriteria().andNameEqualTo(expressName).andDelFlagEqualTo("0");
		List<Express> expresses = expressMapper.selectByExample(expressExample);
		if (CollectionUtils.isEmpty(expresses)) {
			throw new ArgException("物流公司不存在");
		}

		customerServiceOrder.setMemberExpressCompany(expressName);
		customerServiceOrder.setMemberExpressNo(expressNo);
		customerServiceOrder.setProStatus(proStatus);
		customerServiceOrder.setStatus(CustomerServiceOrderCustom.ORDER_STATUS_ING);
		customerServiceOrder.setUpdateDate(now);
		
		CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
		customerServiceStatusLog.setCreateBy(this.getSessionUserInfo(request).getId());
		customerServiceStatusLog.setCreateDate(now);
		customerServiceStatusLog.setUpdateDate(now);
		customerServiceStatusLog.setDelFlag("0");
		customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceStatusLog.setStatus(customerServiceOrder.getStatus());
		customerServiceStatusLog.setProStatus(proStatus);
		customerServiceStatusLog.setRemarks(StringUtil.unEscapeHtmlAndIllegal(content));
		
		CustomerServiceLog customerServiceLog = new CustomerServiceLog();
		customerServiceLog.setCreateBy(this.getSessionUserInfo(request).getId());
		customerServiceLog.setCreateDate(now);
		customerServiceLog.setUpdateDate(now);
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceLog.setOperatorType(CustomerServiceLogCustom.OPERATOR_TYPE_MCHT);//商家
		customerServiceLog.setContent(StringUtil.unEscapeHtmlAndIllegal(content));
		
		customerServiceOrderService.updateCustomerServiceOrder(customerServiceOrder,customerServiceStatusLog,customerServiceLog,null,null,null,null);

		SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
		//物流信息、单号记录
		saveOrUpdateKdnWuliuInfo(expresses.get(0), subOrder, expressNo, this.getSessionUserInfo(request).getId(), now);

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}

	private void saveOrUpdateKdnWuliuInfo(Express express, SubOrder subOrder, String logisticCode, Integer memberId, Date date) {
		KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
		KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andExpressIdEqualTo(express.getId());
		criteria.andLogisticCodeEqualTo(logisticCode);
		List<KdnWuliuInfo> kdnWuliuInfoList = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
		KdnWuliuInfo kdnWuliuInfo;
		if (kdnWuliuInfoList == null || kdnWuliuInfoList.size() == 0) {
			kdnWuliuInfo = new KdnWuliuInfo();
			kdnWuliuInfo.setCreateBy(memberId);
			kdnWuliuInfo.setCreateDate(date);
			kdnWuliuInfo.setDelFlag("0");
			kdnWuliuInfo.setExpressId(express.getId());
			kdnWuliuInfo.setLogisticCode(logisticCode);
			kdnWuliuInfo.setMerchantCode(subOrder.getMerchantCode());
			kdnWuliuInfo.setSubscribeStatus("0");
			kdnWuliuInfo.setSubOrderId(subOrder.getId());
		} else {
			kdnWuliuInfo = kdnWuliuInfoList.get(0);
			if (kdnWuliuInfo.getSubOrderId() == null || subOrder.getId() > kdnWuliuInfo.getSubOrderId()) {
				kdnWuliuInfo.setSubOrderId(subOrder.getId());
				kdnWuliuInfo.setMerchantCode(subOrder.getMerchantCode());
				kdnWuliuInfo.setUpdateBy(memberId);
				kdnWuliuInfo.setUpdateDate(date);
			}
		}
		if (kdnWuliuInfo.getId() == null) {
			kdnWuliuInfoMapper.insertSelective(kdnWuliuInfo);
		} else {
			kdnWuliuInfoMapper.updateByPrimaryKeySelective(kdnWuliuInfo);
		}
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/customerServiceOrder/exportCustomerServiceOrder")
	public void exportCustomerServiceOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		CustomerServiceOrderCustomExample customerServiceOrderCustomExample = new CustomerServiceOrderCustomExample();
		CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria customerServiceOrderCustomCriteria = customerServiceOrderCustomExample.createCriteria();
		customerServiceOrderCustomExample.setOrderByClause("t.id desc");
		if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
			customerServiceOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		}else{
			customerServiceOrderCustomCriteria.andDelFlagEqualTo("0");
		}
		customerServiceOrderCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		String searchOrderType = request.getParameter("searchOrderType");
		if(StringUtil.isEmpty(searchOrderType)){//默认：0.发货前退款
			searchOrderType = REFUND_BEFORE_DELIVERY;
		}
		List<String> serviceTypeList = new ArrayList<String>();
		serviceTypeList.add(CustomerServiceOrderCustom.ORDER_RETURN_GOODS);
		serviceTypeList.add(CustomerServiceOrderCustom.ORDER_EXCHANGE_GOODS);
		if(searchOrderType.equals(REFUND_BEFORE_DELIVERY)){//0.发货前退款
			customerServiceOrderCustomCriteria.andServiceTypeEqualTo(CustomerServiceOrderCustom.ORDER_REFUND);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_ING);
			customerServiceOrderCustomCriteria.andProStatusEqualTo(CustomerServiceStatusLogCustom.ORDER_HAS_APPLY_REFUND);
		}else if(searchOrderType.equals(RETURN_EXCHANGE_APPLY)){//1.退换货申请
			customerServiceOrderCustomCriteria.andServiceTypeIn(serviceTypeList);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_ING);
			List<String> proStatusList = new ArrayList<String>();
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_HAS_APPLY_RETURN_GOODS);
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_HAS_APPLY_EXCHANGE_GOODS);
			customerServiceOrderCustomCriteria.andProStatusIn(proStatusList);
		}else if(searchOrderType.equals(RETURN_EXCHANGE_WAITING_ACCEPT)){//2.退换货待收件
			customerServiceOrderCustomCriteria.andServiceTypeIn(serviceTypeList);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_ING);
			List<String> proStatusList = new ArrayList<String>();
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_HAS_SEND_RETURN_GOODS);
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_CLIENT_HAS_SEND_EXCHANGE_GOODS);
			customerServiceOrderCustomCriteria.andProStatusIn(proStatusList);
		}else if(searchOrderType.equals(EXCHANGE_WAITING_SEND)){//3.换货单待发件
			customerServiceOrderCustomCriteria.andServiceTypeEqualTo(CustomerServiceOrderCustom.ORDER_EXCHANGE_GOODS);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_ING);
			customerServiceOrderCustomCriteria.andProStatusEqualTo(CustomerServiceStatusLogCustom.ORDER_HAS_ACCEPT_EXCHANGE_GOODS);
		}else if(searchOrderType.equals(MCHT_CLOSE)){//4.售后完成
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_COMPLETE);
		}else if(searchOrderType.equals(MCHT_DIRECT_COMPENSATION)){//5.商家直赔
			customerServiceOrderCustomCriteria.andServiceTypeEqualTo(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
			customerServiceOrderCustomCriteria.andStatusNotEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_CANCLE);
			customerServiceOrderCustomCriteria.andStatusNotEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_COMPLETE);
		}else if(searchOrderType.equals(ALL_ORDER)){//6.全部售后
			
		}else if(searchOrderType.equals(WAIT_MEMBER_SEND)){//7.等待客户寄件
			customerServiceOrderCustomCriteria.andServiceTypeIn(serviceTypeList);
			customerServiceOrderCustomCriteria.andStatusEqualTo(CustomerServiceOrderCustom.ORDER_STATUS_ING);
			List<String> proStatusList = new ArrayList<String>();
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_RETURN_GOODS);
			proStatusList.add(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_EXCHANGE_GOODS);
			customerServiceOrderCustomCriteria.andProStatusIn(proStatusList);
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_receiverName"))) {
			customerServiceOrderCustomCriteria.andReceiverNameEqualTo(request.getParameter("search_receiverName"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_subOrderCode"))) {
			customerServiceOrderCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("search_subOrderCode"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_receiverPhone"))) {
			customerServiceOrderCustomCriteria.andReceiverPhoneEqualTo(request.getParameter("search_receiverPhone"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_productId"))) {
			customerServiceOrderCustomCriteria.andProductIdEqualTo(Integer.parseInt(request.getParameter("search_productId")));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_brandId"))) {
			customerServiceOrderCustomCriteria.andBrandIdEqualTo(request.getParameter("search_brandId"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_expressNo"))) {
			customerServiceOrderCustomCriteria.andExpressNoEqualTo(request.getParameter("search_expressNo"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_memberExpressNo"))) {
			customerServiceOrderCustomCriteria.andMemberExpressNoEqualTo(request.getParameter("search_memberExpressNo"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_activityId"))) {
			customerServiceOrderCustomCriteria.andActivityIdEqualTo(Integer.parseInt(request.getParameter("search_activityId")));
		}
		
		String searchServiceType = request.getParameter("search_serviceType");
		if (!StringUtil.isEmpty(searchServiceType)) {
			if(!searchServiceType.equals("-1")){
				customerServiceOrderCustomCriteria.andServiceTypeEqualTo(searchServiceType);
			}
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (!StringUtil.isEmpty(request.getParameter("createTimeBegin"))) {
				customerServiceOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sf.parse(request.getParameter("createTimeBegin")+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("createTimeEnd"))) {
				customerServiceOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sf.parse(request.getParameter("createTimeEnd")+" 23:59:59"));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<CustomerServiceOrderCustom> customerServiceOrderCustoms = customerServiceOrderService.selectCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
		//遍历customerServiceOrderCustoms，给每个customerServiceOrderCustom设置orderDtl
		for(CustomerServiceOrderCustom customerServiceOrderCustom:customerServiceOrderCustoms){
			OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(customerServiceOrderCustom.getOrderDtlId());
			customerServiceOrderCustom.setOrderDtl(orderDtl);
		}
		String[] titles = { "售后编号", "申请日期", "订单编号","商品名称","规格","购买数量","售后数量","是否异常单","售后类型","售后状态","进度状态","实收金额","申请金额/换货","退款日期","最后操作记录","申请人联系电话","收货人","收货电话"};
		ExcelBean excelBean = new ExcelBean("导出订单" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls",
				"导出订单", titles);
		List<String[]> datas = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (CustomerServiceOrderCustom customerServiceOrderCustom : customerServiceOrderCustoms) {
			
			String[] data = {
					customerServiceOrderCustom.getOrderCode(),
					sdf.format(customerServiceOrderCustom.getCreateDate()),
					customerServiceOrderCustom.getSubOrderCode(),
					customerServiceOrderCustom.getOrderDtl().getProductName(),
					customerServiceOrderCustom.getOrderDtl().getProductPropDesc(),
					customerServiceOrderCustom.getOrderDtl().getQuantity().toString(),
					customerServiceOrderCustom.getQuantity().toString(),
					"P".equals(customerServiceOrderCustom.getMemberStatus())?"用户异常":"",
					customerServiceOrderCustom.getServiceTypeDesc(),
					customerServiceOrderCustom.getStatusDesc(),
					customerServiceOrderCustom.getProStatusDesc(),
					customerServiceOrderCustom.getOrderDtl().getPayAmount().toString(),
					customerServiceOrderCustom.getAmount().toString(),
					customerServiceOrderCustom.getRefundDate()==null?"":sdf.format(customerServiceOrderCustom.getRefundDate()),
					customerServiceOrderCustom.getLogContent()==null?"":StringUtil.clearHTMLTag(customerServiceOrderCustom.getLogContent()),
					"P".equals(customerServiceOrderCustom.getMemberStatus())?customerServiceOrderCustom.getContactPhone()+"（用户异常）":customerServiceOrderCustom.getContactPhone(),
					customerServiceOrderCustom.getReceiverName(),
					"P".equals(customerServiceOrderCustom.getMemberStatus())?customerServiceOrderCustom.getReceiverPhone()+"（用户异常）":customerServiceOrderCustom.getReceiverPhone()
				};
			if("P".equals(customerServiceOrderCustom.getMemberStatus())){
				datas.add(0,data);
			}else{
				datas.add(data);
			}
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	}
}
