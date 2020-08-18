package com.jf.controller;

import com.jf.bean.ExcelBean;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.*;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SubOrderController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(SubOrderController.class);

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
	private CombineOrderService combineOrderService;
	
	@Resource
	private MemberAddressService memberAddressService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private ExpressMapper expressMapper;
	
	@Resource
	private ExpressCustomMapper expressCustomMapper;
	
	@Resource
	private KdnWuliuInfoMapper kdnWuliuInfoMapper;
	
	@Resource
	private SysAppMessageService sysAppMessageService;
	
	@Resource
	private ExportExcelRecordMapper exportExcelRecordMapper;
	
	@Resource
	private MchtRemarksLogService mchtRemarksLogService;
	
	@Resource
	private SubOrderAttachmentMapper subOrderAttachmentMapper;
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private CommentPicMapper commentPicMapper;
	
	@Resource
	private ShopScoreService shopScoreService;
	
	@Resource
	private MchtInfoService mchtInfoService;
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
	
	public static int MAXCOUNT = 50000;
	
	/**
	 * -1.所有订单
	 */
	private static String STATUS_ALL = "-1";
	
	/**
	 * -2.近一个月订单
	 */
	private static String STATUS_LAST_MONTH = "-2";
	/**
	 * -3.取消与关闭的订单
	 */
	private static String STATUS_CANCLE_CLOSE = "-3";
	
	/**
	 * 1.已有直赔单
	 */
	private static String HASDIRECTCOMPENSATION_YES = "1";
	/**
	 * 0.没有创建直赔单
	 */
	private static String HASDIRECTCOMPENSATION_NO = "0";
	
	/**
	 * 1.已退款
	 */
	private static String HASREFUND_YES = "1";
	/**
	 * 批量发货新版上线时间
	 */
	private static String onLineStr = "2019-07-02 00:00:00";

	
	
	Map<String, Object> belongMap = new HashMap<String, Object>() {{
			put(BELONG_PLATFORM, BELONGDESC_PLATFORM);
			put(BELONG_MCHT, BELONGDESC_MCHT);
			put(BELONG_INTEGRAL, BELONGDESC_INTEGRAL);
		}
	};
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 订单管理首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/subOrderIndex")
	public String subOrderIndex(Model model, HttpServletRequest request) {
		List<ProductBrand> productBrandList = mchtProductBrandService.getMchtProductBrandList(this.getSessionMchtInfo(request).getId());
		String status = request.getParameter("status");
		if(StringUtil.isEmpty(status)){
			status = STATUS_LAST_MONTH;
		}
		model.addAttribute("status", status);
		model.addAttribute("productBrandList", productBrandList);
		model.addAttribute("subOrderStatusList", DataDicUtil.getStatusList("BU_SUB_ORDER", "STATUS"));
		ExpressExample example = new ExpressExample();
		ExpressExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(example);
		model.addAttribute("expressList", expressList);
		return "subOrder/subOrderIndex";
	}

	/**
	 * 数据列表
	 *
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/getSubOrderList")
	@ResponseBody
	public ResponseMsg getSubOrderList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
		SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
		subOrderCustomExample.setOrderByClause("t.id desc");
		subOrderCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		String status = request.getParameter("status");
		//待商家发货仅存放订单审核状态为通过的数据
		if ("1".equals(status)) {
			subOrderCustomCriteria.andAuditStatusEqualTo("3");
		} else if (!status.equals(STATUS_ALL) && !status.equals(STATUS_LAST_MONTH)) { //全部与近一个月订单不筛选审核状态
			subOrderCustomCriteria.andAuditDefautStauts();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (!StringUtil.isEmpty(request.getParameter("delFlag"))) {
			subOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		} else {
			subOrderCustomCriteria.andDelFlagEqualTo("0");
		}
		//	subOrderCustomCriteria.andServiceTypeNotEqualTo(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
		if (!StringUtil.isEmpty(status)) {
			if (!status.equals(STATUS_ALL) && !status.equals(STATUS_LAST_MONTH) && !status.equals(STATUS_CANCLE_CLOSE)) {//不限制状态和近一个月订单除外
				subOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			} else if (status.equals(STATUS_LAST_MONTH)) {
				Date now = new Date();
				Date ago = DateUtil.getMonthsAgo(now, -1);
				subOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(ago);
				subOrderCustomCriteria.andCreateDateLessThanOrEqualTo(now);
			} else if (status.equals(STATUS_CANCLE_CLOSE)) {
				ArrayList<String> statusList = new ArrayList<String>();
				statusList.add(OrderLogCustom.ORDER_STATUS_CANCLE);
				statusList.add(OrderLogCustom.ORDER_STATUS_CLOSE);
				subOrderCustomCriteria.andStatusIn(statusList);
			}
		}

		//订单编号进行搜索时仅按照订单编号进行搜索,情况其他筛选
		if (!StringUtil.isEmpty(request.getParameter("search_subOrderCode"))) {
			subOrderCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("search_subOrderCode").trim());
		} else {
			String search_status = request.getParameter("search_status");
			if (!StringUtil.isEmpty(search_status)) {
				if (!search_status.equals(STATUS_ALL) && !search_status.equals(STATUS_LAST_MONTH)) {//不限制状态和近一个月订单除外
					subOrderCustomCriteria.andStatusEqualTo(request.getParameter("search_status"));
				} else if (search_status.equals(STATUS_LAST_MONTH)) {
					Date now = new Date();
					Date ago = DateUtil.getMonthsAgo(now, -1);
					subOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(ago);
					subOrderCustomCriteria.andCreateDateLessThanOrEqualTo(now);
				}
			}

			if (!StringUtil.isEmpty(request.getParameter("search_productId"))) {
				subOrderCustomCriteria.andProductCodeEqualTo(request.getParameter("search_productId").trim());
			}
			if (!StringUtil.isEmpty(request.getParameter("search_brandId"))) {
				subOrderCustomCriteria.andBrandIdEqualTo(request.getParameter("search_brandId"));
			}

			if (!StringUtil.isEmpty(request.getParameter("search_activityId"))) {
				subOrderCustomCriteria.andActivityIdEqualTo(Integer.parseInt(request.getParameter("search_activityId").trim()));
			}

			if (!StringUtil.isEmpty(request.getParameter("search_expressNo"))) {
				subOrderCustomCriteria.andExpressNoEqualTo(request.getParameter("search_expressNo").trim());
			}


			if (!StringUtil.isEmpty(request.getParameter("search_receiverName"))) {
				subOrderCustomCriteria.andReceiverNameEqualTo(request.getParameter("search_receiverName").trim());
			}
			if (!StringUtil.isEmpty(request.getParameter("search_receiverPhone"))) {
				subOrderCustomCriteria.andReceiverPhoneEqualTo(request.getParameter("search_receiverPhone").trim());
			}
			if (!StringUtil.isEmpty(request.getParameter("search_remarksColor"))) {
				subOrderCustomCriteria.andRemarksColorEqualTo(request.getParameter("search_remarksColor"));
			}
			try {

				if (!StringUtil.isEmpty(request.getParameter("payDateBegin"))) {
					subOrderCustomCriteria.andAuditDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin") + " 00:00:00"));
				}
				if (!StringUtil.isEmpty(request.getParameter("payDateEnd"))) {
					subOrderCustomCriteria.andAuditDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd") + " 23:59:59"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (!StringUtil.isEmpty(request.getParameter("search_productName"))) {
				subOrderCustomCriteria.andProductNameLikeTo(request.getParameter("search_productName").trim());
			}

			if (!StringUtil.isEmpty(request.getParameter("search_productArtNo"))) {
				subOrderCustomCriteria.andProductArtNoLikeTo(request.getParameter("search_productArtNo").trim());
			}
		}


		int totalCount = subOrderService.countSubOrderCustomByExample(subOrderCustomExample);
		subOrderCustomExample.setLimitStart(page.getLimitStart());
		subOrderCustomExample.setLimitSize(page.getLimitSize());
		/*List<SubOrderCustom> subOrderCustoms = subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
		//遍历subOrderCustoms，给每个subOrderCustom设置orderDtlCustoms
		for(SubOrderCustom subOrderCustom:subOrderCustoms){
			Integer subOrderId = subOrderCustom.getId();
			OrderDtlExample ode = new OrderDtlExample();
			OrderDtlExample.Criteria odec = ode.createCriteria();
			odec.andDelFlagEqualTo("0");
			odec.andSubOrderIdEqualTo(subOrderId);
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.getOrderDtlsBySubOrderId(ode);
			String activityIds = "";
			for(OrderDtlCustom orderDtlCustom : orderDtlCustoms){
				if(orderDtlCustom.getActivityId()!=null){
					if(activityIds.indexOf(orderDtlCustom.getActivityId().toString())<0){
						activityIds+=orderDtlCustom.getActivityId()+",";
					}
				}
			}
			if(!StringUtil.isEmpty(activityIds)){
				subOrderCustom.setActivityIds(activityIds.substring(0, activityIds.length()-1));
			}
			subOrderCustom.setOrderDtlCustoms(orderDtlCustoms);
		}*/

		//查询出子订单集合  优化查询速度
		List<SubOrderCustom> subOrderCustoms = subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
		if (subOrderCustoms != null && subOrderCustoms.size() > 0) {
			//子订单id集合
			ArrayList<Integer> subOrderIds = new ArrayList<>();
			for (SubOrderCustom subOrderCustom : subOrderCustoms) {
				Integer subOrderId = subOrderCustom.getId();
				subOrderIds.add(subOrderId);
			}
			//根据子订单id集合查询订单详情集合
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlsBySubOrderIds(subOrderIds);
			for (SubOrderCustom subOrderCustom : subOrderCustoms) {
				String activityIds = "";
				//构建订单详情集合,添加匹配id的订单详情
				ArrayList<OrderDtlCustom> orderDtlCustomList = new ArrayList<>();
				for (OrderDtlCustom orderDtlCustom : orderDtlCustoms) {
					//判断
					if (orderDtlCustom.getSubOrderId().equals(subOrderCustom.getId())) {
						orderDtlCustomList.add(orderDtlCustom);
						if (orderDtlCustom.getActivityId() != null) {
							if (activityIds.indexOf(orderDtlCustom.getActivityId().toString()) < 0) {
								activityIds += orderDtlCustom.getActivityId() + ",";
							}
						}
					}

				}
				if (!StringUtil.isEmpty(activityIds)) {
					subOrderCustom.setActivityIds(activityIds.substring(0, activityIds.length() - 1));
				}
				//set
				subOrderCustom.setOrderDtlCustoms(orderDtlCustomList);
			}
		}
		returnData.put("Rows", subOrderCustoms);
		returnData.put("Total", totalCount);

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

	}

	/**
	 * 订单详情
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/subOrder/subOrderView")
	public String subOrderView(Model model,HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		SubOrderCustom subOrderCustom=subOrderService.selectSubOrderCustomByPrimaryKey(id);
		OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria = orderDtlCustomExample.createCriteria();
		orderDtlCustomCriteria.andDelFlagEqualTo("0");
		orderDtlCustomCriteria.andSubOrderIdEqualTo(subOrderCustom.getId());
		List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
		//遍历orderDtlCustoms，给每个orderDtlCustom设置退款类型和状态
		List<Integer> orderDtlIds = new ArrayList<Integer>();
		int quantity = 0;
		double mchtPreferential = 0.00;
		double platformPreferential = 0.00;
		double integralPreferential = 0.0;
		double payAmount = 0.00;
		boolean hasSettle = false;
		List<Integer> activityIdList = new ArrayList<Integer>();
		String activityIds = "";
		for(OrderDtlCustom orderDtlCustom : orderDtlCustoms){
			orderDtlCustom.setSkuPic(FileUtil.getSmallImageName(orderDtlCustom.getSkuPic()));
			if(orderDtlCustom.getActivityId()!=null){
				if(!activityIdList.contains(orderDtlCustom.getActivityId())){
					activityIdList.add(orderDtlCustom.getActivityId());
					activityIds += orderDtlCustom.getActivityId().toString()+",";
				}
			}
			Integer mchtSettleOrderId = orderDtlCustom.getMchtSettleOrderId();
			if(mchtSettleOrderId!=null){
				hasSettle = true;
				model.addAttribute("mchtSettleOrderId", mchtSettleOrderId);
			}
			Integer orderDtlId = orderDtlCustom.getId();
			orderDtlIds.add(orderDtlId);
			CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectCustomerServiceOrderBySubOrderIdAndOrderDtlId(id, orderDtlId);
			if(customerServiceOrder!=null){
				if(customerServiceOrder.getServiceType().equals(CustomerServiceOrderCustom.ORDER_REFUND) && customerServiceOrder.getStatus().equals(CustomerServiceOrderCustom.ORDER_STATUS_ING)){
					model.addAttribute("notDelivery", true);
				}
				String serviceTypeDesc = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "SERVICE_TYPE", customerServiceOrder.getServiceType());
				String statusDesc = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "STATUS", customerServiceOrder.getStatus());
				orderDtlCustom.setCustomerServiceType(serviceTypeDesc);
				orderDtlCustom.setCustomerServiceStatusDesc(statusDesc);
				orderDtlCustom.setCustomerServiceStatus(customerServiceOrder.getStatus());
			}
			quantity += orderDtlCustom.getQuantity();
			mchtPreferential += orderDtlCustom.getMchtPreferential().doubleValue();
			platformPreferential += orderDtlCustom.getPlatformPreferential().doubleValue();
			integralPreferential += orderDtlCustom.getIntegralPreferential().doubleValue();
			payAmount += orderDtlCustom.getPayAmount().doubleValue();
		}
		if(!StringUtil.isEmpty(activityIds)){
			subOrderCustom.setActivityIds(activityIds.substring(0, activityIds.length()-1));
		}
		//获取客户付款时间
		model.addAttribute("payDateTime", subOrderCustom.getOrderPayDate());
		//订单完成，取出商家发货时间
		model.addAttribute("sendDateTime", subOrderCustom.getDeliveryDate());
		//获取最新的订单状态流水
		OrderLog currentOrderLog = orderLogService.getOrderLogBySubOrderIdAndStatus(id, subOrderCustom.getStatus());
		//获取所有的订单优惠信息
		List<OrderPreferentialInfoCustom> orderPreferentialInfoCustoms = orderPreferentialInfoService.getOrderPreferentialInfosByorderDtlIds(orderDtlIds);
		//物流信息
		if(!StringUtil.isEmpty(subOrderCustom.getExpressId()) && !StringUtil.isEmpty(subOrderCustom.getExpressNo())){
			KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
			kdnWuliuInfoExample.setOrderByClause("id desc");
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
		//获取直赔单
		CustomerServiceOrder customerServiceOrder = customerServiceOrderService.getDirectCompensationOrder(subOrderCustom.getId(),CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
		if(customerServiceOrder!=null){
			model.addAttribute("hasDirectCompensation", HASDIRECTCOMPENSATION_YES);
			if(customerServiceOrder.getProStatus().equals(CustomerServiceStatusLogCustom.ORDER_PLATFORM_HAS_REFUND_DIRECT_COMPENSATION)){//已直赔
				model.addAttribute("hasRefund", HASREFUND_YES);
			}
		}else{
			model.addAttribute("hasDirectCompensation", HASDIRECTCOMPENSATION_NO);
		}
		//发货快递列表
		ExpressExample example = new ExpressExample();
		ExpressExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(example);
		//母订单备注
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrderCustom.getCombineOrderId());
		for(OrderDtlCustom orderDtlCustom : orderDtlCustoms){
			CustomerServiceOrder cso = customerServiceOrderService.selectCustomerServiceOrderBySubOrderIdAndOrderDtlId(id, orderDtlCustom.getId());
			if(cso!=null && cso.getStatus().equals(CustomerServiceOrderCustom.ORDER_STATUS_ING)){
				model.addAttribute("hasCustomerServiceOrder", true);
				break;
			}else if(cso!=null && cso.getStatus().equals(CustomerServiceOrderCustom.ORDER_STATUS_COMPLETE) && cso.getServiceType().equals(CustomerServiceOrderCustom.ORDER_EXCHANGE_GOODS)){
				model.addAttribute("hasCustomerServiceOrder", true);
				break;
			}
		}
		model.addAttribute("combineOrderStatus", combineOrder.getStatus());
		model.addAttribute("cancelType", combineOrder.getCancelType());
		model.addAttribute("cancelReason", combineOrder.getCancelReason());
		model.addAttribute("remarks", combineOrder.getRemarks());
		model.addAttribute("expressList", expressList);
		model.addAttribute("status", subOrderCustom.getStatus());
		model.addAttribute("currentOrderLog", currentOrderLog);
		model.addAttribute("subOrderCustom", subOrderCustom);
		model.addAttribute("orderDtlCustoms", orderDtlCustoms);
		model.addAttribute("orderPreferentialInfoCustoms", orderPreferentialInfoCustoms);
		model.addAttribute("quantity", quantity);
		model.addAttribute("mchtPreferential", mchtPreferential);
		model.addAttribute("platformPreferential", platformPreferential);
		model.addAttribute("integralPreferential", integralPreferential);
		model.addAttribute("payAmount", payAmount);
		model.addAttribute("hasSettle", hasSettle);
		
		MchtRemarksLogExample mrle = new MchtRemarksLogExample();
		mrle.setOrderByClause("a.id desc");
		MchtRemarksLogExample.Criteria c = mrle.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andSubOrderIdEqualTo(id);
		List<MchtRemarksLogCustom> mchtRemarksLogCustoms = mchtRemarksLogService.selectByCustomExample(mrle);
		model.addAttribute("mchtRemarksLogCustoms", mchtRemarksLogCustoms);
		
		SubOrderAttachmentExample soae = new SubOrderAttachmentExample();
		SubOrderAttachmentExample.Criteria soaec = soae.createCriteria();
		soaec.andDelFlagEqualTo("0");
		soaec.andSubOrderIdEqualTo(id);
		soaec.andCreatorTypeEqualTo("2");//2.商家
		List<SubOrderAttachment> subOrderAttachments = subOrderAttachmentMapper.selectByExample(soae);
		model.addAttribute("subOrderAttachments", subOrderAttachments);
		model.addAttribute("userCode", this.getSessionUserInfo(request).getUserCode());

		CommentExample ce = new CommentExample();
		CommentExample.Criteria cec = ce.createCriteria();
		cec.andDelFlagEqualTo("0");
		cec.andSubOrderIdEqualTo(id);
		int commentCount = commentService.countByExample(ce);
		model.addAttribute("commentCount", commentCount);
		//商家编号
		String mchtCode = this.getSessionMchtInfo(request).getMchtCode();
		model.addAttribute("mchtCode",mchtCode);

		return "subOrder/subOrderView";
	}
	
	
	/**
	 * 备注
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/subOrder/subOrderRemarks")
	public String subOrderRemarks(Model model,HttpServletRequest request) {
		String ids = request.getParameter("ids");
		if(ids.split(",").length == 1){
			SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(ids));
			model.addAttribute("remarksColor", subOrder.getRemarksColor());
			model.addAttribute("remarks", subOrder.getRemarks());
		}
		model.addAttribute("ids", ids);
		return "subOrder/subOrderRemarks";
	}
	
	
	/**
	 * 
	 * 批量保存商家备注
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/remarksBatchSave")
	@ResponseBody
	public ResponseMsg remarksBatchSave(HttpServletRequest request) {
		String subOrderIds = request.getParameter("subOrderIds");
		String remarks = request.getParameter("remarks");
		String remarksColor = request.getParameter("remarksColor");
		if(subOrderIds.split(",").length == 1){
			SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderIds));
			subOrder.setRemarks(remarks);
			subOrder.setRemarksColor(remarksColor);
			subOrderService.updateByPrimaryKey(subOrder);
			MchtRemarksLog mchtRemarksLog = new MchtRemarksLog();
			mchtRemarksLog.setDelFlag("0");
			mchtRemarksLog.setCreateBy(this.getSessionMchtInfo(request).getId());
			mchtRemarksLog.setCreateDate(new Date());
			mchtRemarksLog.setSubOrderId(subOrder.getId());
			mchtRemarksLog.setOperatorType("1");//商家
			mchtRemarksLog.setOperatorId(this.getSessionMchtInfo(request).getId());
			mchtRemarksLog.setRemarksColor(remarksColor);
			mchtRemarksLog.setRemarks(remarks);
			mchtRemarksLogService.insertSelective(mchtRemarksLog);
		}else{
			String[] subOrderIdsArray = subOrderIds.split(",");
			for(int i=0;i<subOrderIdsArray.length;i++){
				Integer subOrderId = Integer.parseInt(subOrderIdsArray[i]);
				SubOrder subOrder = subOrderService.selectByPrimaryKey(subOrderId);
				subOrder.setRemarks(remarks);
				subOrder.setRemarksColor(remarksColor);
				subOrderService.updateByPrimaryKey(subOrder);
				
				MchtRemarksLog mchtRemarksLog = new MchtRemarksLog();
				mchtRemarksLog.setDelFlag("0");
				mchtRemarksLog.setCreateBy(this.getSessionMchtInfo(request).getId());
				mchtRemarksLog.setCreateDate(new Date());
				mchtRemarksLog.setSubOrderId(subOrder.getId());
				mchtRemarksLog.setOperatorType("1");//商家
				mchtRemarksLog.setOperatorId(this.getSessionMchtInfo(request).getId());
				mchtRemarksLog.setRemarksColor(remarksColor);
				mchtRemarksLog.setRemarks(remarks);
				mchtRemarksLogService.insertSelective(mchtRemarksLog);
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
	/**
	 * 立即发货
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/subOrderDelivery")
	@ResponseBody
	public ResponseMsg subOrderDelivery(HttpServletRequest request) {
		String subOrderId = request.getParameter("subOrderId");
		String jsonStr = request.getParameter("jsonStr");
		SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
		if(!subOrder.getStatus().equals("1")){//不等于1，不是待发货
			return new ResponseMsg(ResponseMsg.ERROR, "订单已发货，请刷新页面");
		}
		CustomerServiceOrderExample csoe = new CustomerServiceOrderExample();
		csoe.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrder.getId()).andStatusEqualTo("0");
		List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(csoe);
		if(customerServiceOrders!=null && customerServiceOrders.size()>0){
			return new ResponseMsg(ResponseMsg.ERROR, "用户已发起售后，无法发货，请刷新页面");
		}
		String expressNo = "";
		String expressId = "";
		String merchantCode= "";
		JSONArray ja = JSONArray.fromObject(jsonStr);
		List<KdnWuliuInfo> kdnWuliuInfoList = new ArrayList<KdnWuliuInfo>();
		for(int i=0;i<ja.size();i++){
			JSONObject jo = (JSONObject)ja.get(i);
			String eachExpressId = jo.getString("expressId");
			String eachExpressNo = jo.getString("expressNo").trim();
			String eachMerchantCode = jo.getString("merchantCode").trim();
			Boolean flag = subOrderService.verifyExpressNo(eachExpressNo, Integer.parseInt(eachExpressId));
			if(!flag){
				return new ResponseMsg(ResponseMsg.ERROR, "发货失败，快递单号异常");
			}
			if(i == 0){
				expressNo = eachExpressNo;
				expressId = eachExpressId;
				merchantCode = eachMerchantCode;
			}
			
			KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
			KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andExpressIdEqualTo(Integer.parseInt(eachExpressId));
			criteria.andLogisticCodeEqualTo(eachExpressNo.trim());
			List<KdnWuliuInfo> kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
			KdnWuliuInfo kdnWuliuInfo = null;
			if(kdnWuliuInfos==null || kdnWuliuInfos.size()==0){
				kdnWuliuInfo = new KdnWuliuInfo();
				kdnWuliuInfo.setCreateBy(this.getMchtInfo().getId());
				kdnWuliuInfo.setCreateDate(new Date());
				kdnWuliuInfo.setDelFlag("0");
				kdnWuliuInfo.setExpressId(Integer.parseInt(eachExpressId));
				kdnWuliuInfo.setLogisticCode(eachExpressNo.trim());
				kdnWuliuInfo.setMerchantCode(eachMerchantCode);
				kdnWuliuInfo.setSubscribeStatus("0");
				kdnWuliuInfo.setSubOrderId(subOrder.getId());
			}else{
				kdnWuliuInfo = kdnWuliuInfos.get(0);
				if(kdnWuliuInfo.getSubOrderId()!=null&&subOrder.getId()>kdnWuliuInfo.getSubOrderId()){
					kdnWuliuInfo.setSubOrderId(subOrder.getId());
					kdnWuliuInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
					kdnWuliuInfo.setUpdateDate(new Date());
				}else{
					if(kdnWuliuInfo.getSubOrderId()==null){
						kdnWuliuInfo.setSubOrderId(subOrder.getId());
						kdnWuliuInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
						kdnWuliuInfo.setUpdateDate(new Date());
					}
				}
			}
			kdnWuliuInfoList.add(kdnWuliuInfo);
		}
		subOrder.setExpressNo(expressNo.trim());
		subOrder.setExpressId(expressId);
		subOrder.setMerchantCode(merchantCode);
		subOrder.setDeliveryDate(new Date());
		subOrder.setStatus(OrderLogCustom.ORDER_STATUS_WAIT_RECEIPT);
		subOrder.setStatusDate(new Date());
		subOrder.setUpdateDate(new Date());
		subOrder.setUpdateBy(this.getSessionMchtInfo(request).getId());
		
		OrderLog orderLog = new OrderLog();
		orderLog.setCreateDate(new Date());
		orderLog.setCreateBy(this.getSessionMchtInfo(request).getId());
		orderLog.setDelFlag("0");
		orderLog.setSubOrderId(Integer.parseInt(subOrderId));
		orderLog.setStatus(OrderLogCustom.ORDER_STATUS_WAIT_RECEIPT);
		
		SysAppMessage sysAppMessage = new SysAppMessage();
//		sysAppMessage.setCreateBy(this.getSessionMchtInfo(request).getId());
//		sysAppMessage.setCreateDate(new Date());
//		sysAppMessage.setDelFlag("0");
//		sysAppMessage.setType(SysAppMessageCustom.TYPE_WULIU);
//		sysAppMessage.setTitle(SysAppMessageCustom.TITLE_DELIVERY);
//		sysAppMessage.setContent("商家已发货");
//		sysAppMessage.setExpressNo(expressNo.trim());
//		sysAppMessage.setLinkType(SysAppMessageCustom.LINKTYPE_SUBORDER);
//		sysAppMessage.setLinkId(subOrder.getId());
//		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
//		sysAppMessage.setMemberId(combineOrder.getMemberId());
		try {
			subOrderService.updateSubOrderAndOrderLog(subOrder,orderLog,kdnWuliuInfoList,sysAppMessage,ja);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "发货失败，请稍后重试");
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
	/**
	 * 创建直赔单
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/createDirectCompensation")
	@ResponseBody
	public ResponseMsg createDirectCompensation(HttpServletRequest request) {
		String subOrderId = request.getParameter("subOrderId");
		String amount = request.getParameter("amount");
		String reason = request.getParameter("reason");
		String remarks = request.getParameter("remarks");
		String contactPhone = request.getParameter("contactPhone");
		CustomerServiceOrder directCompensationOrder = customerServiceOrderService.getDirectCompensationOrder(Integer.parseInt(subOrderId), CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
		if(directCompensationOrder!=null){
			return new ResponseMsg(ResponseMsg.ERROR, "创建失败，已创建过直赔单，一个订单只能创建一次直赔",null);
		}
		CustomerServiceOrder customerServiceOrder = new CustomerServiceOrder();
		customerServiceOrder.setCreateDate(new Date());
		customerServiceOrder.setCreateBy(this.getSessionMchtInfo(request).getId());
		customerServiceOrder.setSubOrderId(Integer.parseInt(subOrderId));
		customerServiceOrder.setServiceType(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
		customerServiceOrder.setStatus(CustomerServiceOrderCustom.ORDER_STATUS_ING);
		customerServiceOrder.setOrderCode(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION+CommonUtil.getOrderCode());
		customerServiceOrder.setProStatus(CustomerServiceStatusLogCustom.ORDER_MCHT_CREATE_DIRECT_COMPENSATION);
		customerServiceOrder.setContactPhone(contactPhone);
		customerServiceOrder.setReason(reason);
		customerServiceOrder.setRemarks(remarks);
		customerServiceOrder.setDelFlag("0");
		customerServiceOrder.setAmount(new BigDecimal(amount));
		
		CustomerServiceLog customerServiceLog = new CustomerServiceLog();
		customerServiceLog.setCreateBy(this.getSessionMchtInfo(request).getId());
		customerServiceLog.setCreateDate(new Date());
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setOperatorType(CustomerServiceLogCustom.OPERATOR_TYPE_MCHT);
		SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
		reason = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", reason);
		customerServiceLog.setContent("<span>商家创建直赔</span><br>订单号："+subOrder.getSubOrderCode()+"<br>直赔金额："+new BigDecimal(amount)+"元<br>直赔原因："+reason+"<br>直赔说明："+remarks+"<br>由平台安排退款，请耐心等待");
		customerServiceLog.setRemarks("由平台安排退款，请耐心等待");
		
		CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
		customerServiceStatusLog.setCreateBy(this.getSessionMchtInfo(request).getId());
		customerServiceStatusLog.setCreateDate(new Date());
		customerServiceStatusLog.setDelFlag("0");
		customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceStatusLog.setStatus("0");
		customerServiceStatusLog.setProStatus(CustomerServiceStatusLogCustom.ORDER_MCHT_CREATE_DIRECT_COMPENSATION);
		customerServiceStatusLog.setRemarks("<span>商家创建直赔</span><br>订单号："+subOrder.getSubOrderCode()+"<br>直赔金额："+new BigDecimal(amount)+"元<br>直赔原因："+reason+"<br>直赔说明："+remarks);
		
		RefundOrder refundOrder = new RefundOrder();
		refundOrder.setDelFlag("0");
		refundOrder.setCreateBy(this.getSessionMchtInfo(request).getId());
		refundOrder.setCreateDate(new Date());
		refundOrder.setCombineOrderId(subOrder.getCombineOrderId());
		refundOrder.setServiceType(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
		refundOrder.setRefundAmount(new BigDecimal(amount));
		refundOrder.setRefundAgreeDate(new Date());
		refundOrder.setRefundMethod("1");
		refundOrder.setTryTimes(0);
		refundOrder.setStatus("0");
		refundOrder.setOrderType("1");
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		if(combineOrder.getPaymentId() == 1){
			refundOrder.setRefundCode("ZFB"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 2){
			refundOrder.setRefundCode("WX"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 3){
			refundOrder.setRefundCode("YL"+sf.format(new Date())+"T");
		}
		customerServiceOrderService.insertCustomerServiceOrder(customerServiceOrder,customerServiceLog,customerServiceStatusLog,refundOrder);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
	/**
	 * 更新状态
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/updateStatus")
	@ResponseBody
	public ResponseMsg updateStatus(HttpServletRequest request) {
		Integer subOrderId = Integer.parseInt(request.getParameter("subOrderId"));
		String status = request.getParameter("status");
		SubOrder subOrder = subOrderService.selectByPrimaryKey(subOrderId);
		subOrder.setStatus(status);
		subOrder.setStatusDate(new Date());
		subOrderService.updateByPrimaryKey(subOrder);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
	/**
	 * 校验是否超过50000条
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/checkOrderCount")
	@ResponseBody
	public ResponseMsg checkOrderCount(HttpServletRequest request) {
		//导出的频率限制为：2次之间必须大于5分钟
		ExportExcelRecordExample example = new ExportExcelRecordExample();
		ExportExcelRecordExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		c.andTypeEqualTo("1");//子订单导出
		List<ExportExcelRecord> exportExcelRecords = exportExcelRecordMapper.selectByExample(example);
		if(exportExcelRecords!=null && exportExcelRecords.size()>0){
			ExportExcelRecord exportExcelRecord = exportExcelRecords.get(0);
			Date now = new Date();
			long minute = (now.getTime()-exportExcelRecord.getLastExportDate().getTime())/1000/60;
			if(minute <= 5){
//				return new ResponseMsg(ResponseMsg.ERROR, "两次导出间隔时间必须大于5分钟",null);
			}
		}
		OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria criteria = orderDtlCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andAuditDefautStauts();
		criteria.andMchtIdEqualTo(this.getMchtInfo().getId());
		String search_status = request.getParameter("search_status");
		if (!StringUtil.isEmpty(search_status)) {
			if(!search_status.equals(STATUS_ALL) && !search_status.equals(STATUS_LAST_MONTH)){//不限制状态和近一个月订单除外
				criteria.andSubOrderStatusEqualTo(request.getParameter("search_status"));
			}else if(search_status.equals(STATUS_LAST_MONTH)){
				Date now = new Date();
				Date ago = DateUtil.getMonthsAgo(now,-1);
				criteria.andCreateDateGreaterThanOrEqualTo(ago);
				criteria.andCreateDateLessThanOrEqualTo(now);
			}else if(search_status.equals(STATUS_CANCLE_CLOSE)){
				criteria.andSubOrderStatusIn(OrderLogCustom.ORDER_STATUS_CANCLE,OrderLogCustom.ORDER_STATUS_CLOSE);
			}
		}
		if (!StringUtil.isEmpty(request.getParameter("search_productId"))) {
			criteria.andProductCodeEqualTo(request.getParameter("search_productId"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_brandName"))) {
			criteria.andBrandNameEqualTo(request.getParameter("search_brandName"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_activityId"))) {
			criteria.andActivityIdEqualTo(Integer.parseInt(request.getParameter("search_activityId")));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_expressNo"))) {
			criteria.andSubOrderExpressNoEqualTo(request.getParameter("search_expressNo"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_subOrderCode"))) {
			criteria.andSubOrderCodeEqualTo(request.getParameter("search_subOrderCode"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_receiverName"))) {
			criteria.andReceiverNameEqualTo(request.getParameter("search_receiverName"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_receiverPhone"))) {
			criteria.andReceiverPhoneEqualTo(request.getParameter("search_receiverPhone"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("payDateBegin"))) {
			criteria.andAuditDateGreaterThanOrEqualTo(request.getParameter("payDateBegin")+" 00:00:00");
		}
		if (!StringUtil.isEmpty(request.getParameter("payDateEnd"))) {
			criteria.andAuditDateLessThanOrEqualTo(request.getParameter("payDateEnd")+" 23:59:59");
		}
		int totalCount = orderDtlService.countOrderDtlCustomByExample(orderDtlCustomExample);


		if(totalCount>=MAXCOUNT){
			return new ResponseMsg(ResponseMsg.ERROR, "超过"+MAXCOUNT+"条不可导出",null);
		}else{
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
		}
	}


	/**
	 * 导出订单excel
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/subOrder/exportSubOrder")
	public void exportSubOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria criteria = orderDtlCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getMchtInfo().getId());
		criteria.andServiceTypeNotEqualTo(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
		criteria.andAuditDefautStauts();
		orderDtlCustomExample.setOrderByClause("t.sub_order_id desc");
		//导出时订单编号不为空时情况其他筛选条件
		if (!StringUtil.isEmpty(request.getParameter("search_subOrderCode"))) {
			criteria.andSubOrderCodeEqualTo(request.getParameter("search_subOrderCode"));
		} else {
			String search_status = request.getParameter("search_status");
			if (!StringUtil.isEmpty(search_status)) {
				if (!search_status.equals(STATUS_ALL) && !search_status.equals(STATUS_LAST_MONTH)) {//不限制状态和近一个月订单除外
					criteria.andSubOrderStatusEqualTo(request.getParameter("search_status"));
				} else if (search_status.equals(STATUS_LAST_MONTH)) {
					Date now = new Date();
					Date ago = DateUtil.getMonthsAgo(now, -1);
					criteria.andCreateDateGreaterThanOrEqualTo(ago);
					criteria.andCreateDateLessThanOrEqualTo(now);
				} else if (search_status.equals(STATUS_CANCLE_CLOSE)) {
					criteria.andSubOrderStatusIn(OrderLogCustom.ORDER_STATUS_CANCLE, OrderLogCustom.ORDER_STATUS_CLOSE);
				}
			}
			if (!StringUtil.isEmpty(request.getParameter("search_productId"))) {
				criteria.andProductCodeEqualTo(request.getParameter("search_productId"));
			}
			if (!StringUtil.isEmpty(request.getParameter("search_brandName"))) {
				criteria.andBrandNameEqualTo(request.getParameter("search_brandName"));
			}

			if (!StringUtil.isEmpty(request.getParameter("search_activityId"))) {
				criteria.andActivityIdEqualTo(Integer.parseInt(request.getParameter("search_activityId")));
			}

			if (!StringUtil.isEmpty(request.getParameter("search_expressNo"))) {
				criteria.andSubOrderExpressNoEqualTo(request.getParameter("search_expressNo"));
			}


			if (!StringUtil.isEmpty(request.getParameter("search_receiverName"))) {
				criteria.andReceiverNameEqualTo(request.getParameter("search_receiverName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("search_receiverPhone"))) {
				criteria.andReceiverPhoneEqualTo(request.getParameter("search_receiverPhone"));
			}

			if (!StringUtil.isEmpty(request.getParameter("payDateBegin"))) {
				criteria.andAuditDateGreaterThanOrEqualTo(request.getParameter("payDateBegin") + " 00:00:00");
			}
			if (!StringUtil.isEmpty(request.getParameter("payDateEnd"))) {
				criteria.andAuditDateLessThanOrEqualTo(request.getParameter("payDateEnd") + " 23:59:59");
			}
		}
		List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
		String[] titles = {"订单编号", "快递名称", "快递单号", "快递商户编号", "是否异常单", "商品售后状态", "收件人", "收件人电话", "省", "市", "区", "收件地址", "客户付款状态", "客户付款时间", "发货时间", "退款时间", "完成时间", "商品最终状态", "品牌", "货号", "SKU编码", "商品名称", "规格", "数量", "单价", "商家优惠", "运费", "平台优惠", "积分优惠", "订单支付金额", "客户留言", "商家备注", "售后模板", "订单审核日期"};
		ExcelBean excelBean = new ExcelBean("导出订单" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls",
				"导出订单", titles);
		List<String[]> datas = new ArrayList<>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (OrderDtlCustom orderDtlCustom : orderDtlCustoms) {
			String receiverPhone = "";
			if (orderDtlCustom.getSubOrderStatus().equals(OrderLogCustom.ORDER_STATUS_WAIT_DELIVERY)) {//待发货
				receiverPhone = orderDtlCustom.getReceiverPhone();
			} else {
				if (orderDtlCustom.getReceiverPhone() != null) {
					receiverPhone = orderDtlCustom.getReceiverPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
				}
			}
			String hybridNumber = "";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date onLine = simpleDateFormat.parse(onLineStr);
			if (orderDtlCustom.getPaymentDate().getTime() > onLine.getTime()) {
				hybridNumber = orderDtlCustom.getSubOrderCode() + "-" + orderDtlCustom.getId();
			} else {
				hybridNumber = orderDtlCustom.getSubOrderCode();
			}
			String[] data = {
					hybridNumber,
					orderDtlCustom.getExpressName(),
					orderDtlCustom.getExpressNo() == null ? "" : "`" + orderDtlCustom.getExpressNo(),
					orderDtlCustom.getMerchantCode() == null ? "" : "`" + orderDtlCustom.getMerchantCode(),
					"P".equals(orderDtlCustom.getMemberStatus()) ? "异常单" : "",
					(orderDtlCustom.getCustomerServiceStatus() == null || orderDtlCustom.getCustomerServiceStatus().equals(CustomerServiceOrderCustom.ORDER_STATUS_REFUSE) || orderDtlCustom.getCustomerServiceStatus().equals(CustomerServiceOrderCustom.ORDER_STATUS_CANCLE)) ? "" : orderDtlCustom.getCustomerServiceType() + orderDtlCustom.getCustomerServiceStatusDesc(),
					orderDtlCustom.getReceiverName(),
					"P".equals(orderDtlCustom.getMemberStatus()) ? receiverPhone + "（异常单）" : receiverPhone,
					orderDtlCustom.getProvince(),
					orderDtlCustom.getCity(),
					orderDtlCustom.getCounty(),
					orderDtlCustom.getReceiverAddress(),
					orderDtlCustom.getPaymentDesc(),
					orderDtlCustom.getPaymentDate() == null ? "" : sf.format(orderDtlCustom.getPaymentDate()),
					orderDtlCustom.getDeliveryDate() == null ? "" : sf.format(orderDtlCustom.getDeliveryDate()),
					orderDtlCustom.getRefundDate() == null ? "" : sf.format(orderDtlCustom.getRefundDate()),
					orderDtlCustom.getCompleteDate() == null ? "" : sf.format(orderDtlCustom.getCompleteDate()),
					orderDtlCustom.getProductStatusDesc(),
					orderDtlCustom.getBrandName(),
					orderDtlCustom.getArtNo(),
					orderDtlCustom.getSku(),
					orderDtlCustom.getProductName(),
					orderDtlCustom.getProductPropDesc(),
					orderDtlCustom.getQuantity() == null ? "" : orderDtlCustom.getQuantity().toString(),
					orderDtlCustom.getSalePrice() == null ? "" : orderDtlCustom.getSalePrice().toString(),
					orderDtlCustom.getMchtPreferential() == null ? "" : orderDtlCustom.getMchtPreferential().toString(),
					orderDtlCustom.getFreight() == null ? "" : orderDtlCustom.getFreight().toString(),
					orderDtlCustom.getPlatformPreferential() == null ? "" : orderDtlCustom.getPlatformPreferential().toString(),
					orderDtlCustom.getIntegralPreferential() == null ? "" : orderDtlCustom.getIntegralPreferential().toString(),
					orderDtlCustom.getPayAmount() == null ? "" : orderDtlCustom.getPayAmount().toString(),
					orderDtlCustom.getMemberRemarks(),
					orderDtlCustom.getMchtRemarks(),
					orderDtlCustom.getTempletName() == null ? "" : orderDtlCustom.getTempletName(),
					orderDtlCustom.getAuditDate() == null ? "" : sf.format(orderDtlCustom.getAuditDate())
			};
			if ("P".equals(orderDtlCustom.getMemberStatus())) {
				datas.add(0, data);
			} else {
				datas.add(data);
			}

		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean, response);
		ExportExcelRecordExample example = new ExportExcelRecordExample();
		ExportExcelRecordExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		c.andTypeEqualTo("1");//子订单导出
		List<ExportExcelRecord> exportExcelRecords = exportExcelRecordMapper.selectByExample(example);
		if (exportExcelRecords != null && exportExcelRecords.size() > 0) {
			ExportExcelRecord exportExcelRecord = exportExcelRecords.get(0);
			exportExcelRecord.setLastExportDate(new Date());
			exportExcelRecord.setExportCount(exportExcelRecord.getExportCount() + 1);
			exportExcelRecord.setUpdateBy(this.getSessionMchtInfo(request).getId());
			exportExcelRecord.setUpdateDate(new Date());
			exportExcelRecordMapper.updateByPrimaryKeySelective(exportExcelRecord);
		} else {
			ExportExcelRecord eer = new ExportExcelRecord();
			eer.setDelFlag("0");
			eer.setMchtId(this.getSessionMchtInfo(request).getId());
			eer.setType("1");//子订单导出
			eer.setLastExportDate(new Date());
			eer.setExportCount(1);
			eer.setCreateBy(this.getSessionMchtInfo(request).getId());
			eer.setCreateDate(new Date());
			exportExcelRecordMapper.insertSelective(eer);
		}
	}
	
	
	/**
	 * 批量发货管理列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/deliveryOrderIndex")
	public String deliveryOrderIndex(Model model, HttpServletRequest request) {
		List<ProductBrand> productBrandList = mchtProductBrandService.getMchtProductBrandList(this.getSessionMchtInfo(request).getId());
		String searchStatus = request.getParameter("searchStatus");
		if(StringUtil.isEmpty(searchStatus)){
			searchStatus = "0";
		}
		model.addAttribute("searchStatus", searchStatus);
		model.addAttribute("productBrandList", productBrandList);
		ExpressExample example = new ExpressExample();
		ExpressExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(example);
		model.addAttribute("expressList", expressList);
		return "subOrder/deliveryOrderIndex";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/getDeliveryOrderList")
	@ResponseBody
	public ResponseMsg getDeliveryOrderList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
		SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
		subOrderCustomExample.setOrderByClause("t.id desc");
		subOrderCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		subOrderCustomCriteria.andAuditDefautStauts();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
			subOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		}else{
			subOrderCustomCriteria.andDelFlagEqualTo("0");
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String searchStatus = request.getParameter("searchStatus");
		if (!StringUtil.isEmpty(searchStatus)) {
			if(searchStatus.equals("0")){//待发货: 子订单状态=待发货  && 快递单号为空
				subOrderCustomCriteria.andStatusEqualTo("1");
				subOrderCustomCriteria.andExpressNoIsNull();
			}else if(searchStatus.equals("1")){//发货待确认: 子订单状态=待发货  && 快递单号不为空
				subOrderCustomCriteria.andStatusEqualTo("1");
				subOrderCustomCriteria.andExpressNoIsNotNull();
			}else if(searchStatus.equals("2")){//今天已发货: 子订单状态=待收货  && 发货时间等今天
				subOrderCustomCriteria.andStatusEqualTo("2");
				try {
					subOrderCustomCriteria.andDeliveryDateGreaterThanOrEqualTo(sf.parse(DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")+" 00:00:00"));
					subOrderCustomCriteria.andDeliveryDateLessThanOrEqualTo(sf.parse(DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")+" 23:59:59"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		if (!StringUtil.isEmpty(request.getParameter("search_productId"))) {
			subOrderCustomCriteria.andProductCodeEqualTo(request.getParameter("search_productId"));
		}

		if (!StringUtil.isEmpty(request.getParameter("search_expressId"))) {
			subOrderCustomCriteria.andExpressIdEqualTo(request.getParameter("search_expressId"));
		}

		if (!StringUtil.isEmpty(request.getParameter("search_brandId"))) {
			subOrderCustomCriteria.andBrandIdEqualTo(request.getParameter("search_brandId"));
		}

		if (!StringUtil.isEmpty(request.getParameter("search_activityId"))) {
			subOrderCustomCriteria.andActivityIdEqualTo(Integer.parseInt(request.getParameter("search_activityId")));
		}

		if (!StringUtil.isEmpty(request.getParameter("search_expressNo"))) {
			subOrderCustomCriteria.andExpressNoEqualTo(request.getParameter("search_expressNo"));
		}

		if (!StringUtil.isEmpty(request.getParameter("search_subOrderCode"))) {
			subOrderCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("search_subOrderCode"));
		}

		if (!StringUtil.isEmpty(request.getParameter("search_receiverName"))) {
			subOrderCustomCriteria.andReceiverNameEqualTo(request.getParameter("search_receiverName"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_receiverPhone"))) {
			subOrderCustomCriteria.andReceiverPhoneEqualTo(request.getParameter("search_receiverPhone"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_remarksColor"))) {
			subOrderCustomCriteria.andRemarksColorEqualTo(request.getParameter("search_remarksColor"));
		}

		try{

			if (!StringUtil.isEmpty(request.getParameter("payDateBegin"))) {
				subOrderCustomCriteria.andAuditDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("payDateEnd"))) {
				subOrderCustomCriteria.andAuditDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		int totalCount = subOrderService.countSubOrderCustomByExample(subOrderCustomExample);
		subOrderCustomExample.setLimitStart(page.getLimitStart());
		subOrderCustomExample.setLimitSize(page.getLimitSize());
		List<SubOrderCustom> subOrderCustoms = subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
		//遍历subOrderCustoms，给每个subOrderCustom设置orderDtlCustoms
		for(SubOrderCustom subOrderCustom:subOrderCustoms){
			Integer subOrderId = subOrderCustom.getId();
			OrderDtlExample ode = new OrderDtlExample();
			OrderDtlExample.Criteria odec = ode.createCriteria();
			odec.andDelFlagEqualTo("0");
			odec.andSubOrderIdEqualTo(subOrderId);
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.getOrderDtlsBySubOrderId(ode);
			String activityIds = "";
			for(OrderDtlCustom orderDtlCustom : orderDtlCustoms){
				if(orderDtlCustom.getActivityId()!=null){
					if(activityIds.indexOf(orderDtlCustom.getActivityId().toString())<0){
						activityIds+=orderDtlCustom.getActivityId()+",";
					}
				}
			}
			if(!StringUtil.isEmpty(activityIds)){
				subOrderCustom.setActivityIds(activityIds.substring(0, activityIds.length()-1));
			}
			subOrderCustom.setOrderDtlCustoms(orderDtlCustoms);
		}
		int waitDeliveryCount = subOrderService.countWaitDelivery(this.getSessionMchtInfo(request).getId(),OrderLogCustom.ORDER_STATUS_WAIT_DELIVERY);
		int confirmDeliveryCount = subOrderService.countConfirmDelivery(this.getSessionMchtInfo(request).getId(),OrderLogCustom.ORDER_STATUS_WAIT_DELIVERY);
		int hasDeliveryCount = subOrderService.countHasDelivery(this.getSessionMchtInfo(request).getId(),OrderLogCustom.ORDER_STATUS_WAIT_RECEIPT,DateUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
		returnData.put("waitDeliveryCount",waitDeliveryCount);
		returnData.put("confirmDeliveryCount",confirmDeliveryCount);
		returnData.put("hasDeliveryCount",hasDeliveryCount);
		returnData.put("Rows", subOrderCustoms);
		returnData.put("Total", totalCount);



		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

	}
	
	/**
	 * 导出选中的发货订单excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/subOrder/exportSelectedDeliverySubOrder")
	public void exportSelectedDeliverySubOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String subOrderIdsStr = request.getParameter("subOrderIds");
		String[] subOrderIdsArray = subOrderIdsStr.split(",");
		List<Integer> subOrderIdsList = new ArrayList<Integer>();
		for(int i=0;i<subOrderIdsArray.length;i++){
			subOrderIdsList.add(Integer.parseInt(subOrderIdsArray[i]));
		}
		OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria criteria = orderDtlCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getMchtInfo().getId());
		criteria.andServiceTypeNotEqualTo(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
		criteria.andAuditDefautStauts();
		orderDtlCustomExample.setOrderByClause("t.sub_order_id desc");
		criteria.andSubOrderIdIn(subOrderIdsList);
		List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
		String[] titles = { "订单号", "快递名称", "快递单号","是否异常单","商品售后状态","收件人","收件人电话","省","市","区","收件地址","客户付款状态","客户付款时间","发货时间","退款时间","完成时间","商品最终状态","品牌","货号","SKU编码","商品名称","规格","数量","单价","商家优惠","平台优惠","积分优惠","订单支付金额","客户留言","商家备注","售后模板","发货方","审核时间"};
		ExcelBean excelBean = new ExcelBean("导出订单" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls",
				"导出订单", titles);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String[]> datas = new ArrayList<>();
		for (OrderDtlCustom orderDtlCustom : orderDtlCustoms) {
			if(orderDtlCustom.getCustomerServiceStatus()!=null && (orderDtlCustom.getCustomerServiceStatusDesc().equals("进行中") || orderDtlCustom.getCustomerServiceStatusDesc().equals("已完成"))){
				continue;
			}else{
				if(orderDtlCustom.getProductStatus()!=null && orderDtlCustom.getProductStatus().equals("2")){
					continue;
				}
				String receiverPhone = "";
				if(orderDtlCustom.getSubOrderStatus().equals(OrderLogCustom.ORDER_STATUS_WAIT_DELIVERY)){//待发货
					receiverPhone = orderDtlCustom.getReceiverPhone();
				}else{
					if(orderDtlCustom.getReceiverPhone()!=null){
						receiverPhone = orderDtlCustom.getReceiverPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
					}
				}
				String[] data = {
						orderDtlCustom.getSubOrderCode(),
						orderDtlCustom.getExpressName(),
						orderDtlCustom.getExpressNo()==null?"":"`"+orderDtlCustom.getExpressNo(),
						"P".equals(orderDtlCustom.getMemberStatus())?"异常单":"",
					    (orderDtlCustom.getCustomerServiceStatus() == null||orderDtlCustom.getCustomerServiceStatus().equals(CustomerServiceOrderCustom.ORDER_STATUS_REFUSE)||orderDtlCustom.getCustomerServiceStatus().equals(CustomerServiceOrderCustom.ORDER_STATUS_CANCLE))? "" : orderDtlCustom.getCustomerServiceType()+orderDtlCustom.getCustomerServiceStatusDesc(),
						orderDtlCustom.getReceiverName(),
						"P".equals(orderDtlCustom.getMemberStatus())?receiverPhone+"（异常单）":receiverPhone,
						orderDtlCustom.getProvince(),
						orderDtlCustom.getCity(),
						orderDtlCustom.getCounty(),
						orderDtlCustom.getReceiverAddress(),
						orderDtlCustom.getPaymentDesc(),
						orderDtlCustom.getPaymentDate() == null? "" : sf.format(orderDtlCustom.getPaymentDate()),   
						orderDtlCustom.getDeliveryDate() == null? "" : sf.format(orderDtlCustom.getDeliveryDate()), 
						orderDtlCustom.getRefundDate() == null? "" : sf.format(orderDtlCustom.getRefundDate()),    
						orderDtlCustom.getCompleteDate() == null? "" : sf.format(orderDtlCustom.getCompleteDate()),   
						orderDtlCustom.getProductStatusDesc(),   
						orderDtlCustom.getBrandName(),   
						orderDtlCustom.getArtNo(),   
						orderDtlCustom.getSku(),   
						orderDtlCustom.getProductName(),   
						orderDtlCustom.getProductPropDesc(),   
						orderDtlCustom.getQuantity() == null? "" : orderDtlCustom.getQuantity().toString(),   
						orderDtlCustom.getSalePrice() == null? "" : orderDtlCustom.getSalePrice().toString(),   
						orderDtlCustom.getMchtPreferential() == null? "" : orderDtlCustom.getMchtPreferential().toString(),   
						orderDtlCustom.getPlatformPreferential() == null? "" : orderDtlCustom.getPlatformPreferential().toString(),   
						orderDtlCustom.getIntegralPreferential() == null? "" : orderDtlCustom.getIntegralPreferential().toString(),   
						orderDtlCustom.getPayAmount() == null? "" : orderDtlCustom.getPayAmount().toString(),   
						orderDtlCustom.getMemberRemarks(),
						orderDtlCustom.getMchtRemarks(),
						orderDtlCustom.getTempletName() == null? "" : orderDtlCustom.getTempletName(),
						orderDtlCustom.getCloudProductItemId() == null?"商家"	:"供应商",
						orderDtlCustom.getAuditDate() == null? "" : sf.format(orderDtlCustom.getAuditDate())
				};
				if("P".equals(orderDtlCustom.getMemberStatus())){
					datas.add(0,data);
				}else{
					datas.add(data);
				}
			}
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	}
	/**
	 * 导出全部批量发货订单excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/subOrder/exportDeliverySubOrder")
	public void exportDeliverySubOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria criteria = orderDtlCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getMchtInfo().getId());
		criteria.andServiceTypeNotEqualTo(CustomerServiceOrderCustom.ORDER_DIRECT_COMPENSATION);
		criteria.andAuditDefautStauts();
		orderDtlCustomExample.setOrderByClause("t.sub_order_id desc");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String searchStatus = request.getParameter("searchStatus");
		if (!StringUtil.isEmpty(searchStatus)) {
			if(searchStatus.equals("0")){//待发货: 子订单状态=待发货  && 快递单号为空
				criteria.andSubOrderStatusEqualTo(OrderLogCustom.ORDER_STATUS_WAIT_DELIVERY);
				criteria.andSubOrderExpressNoIsNull();
			}else if(searchStatus.equals("1")){//发货待确认: 子订单状态=待发货  && 快递单号不为空
				criteria.andSubOrderStatusEqualTo(OrderLogCustom.ORDER_STATUS_WAIT_DELIVERY);
				criteria.andSubOrderExpressNoIsNotNull();
			}else if(searchStatus.equals("2")){//今天已发货: 子订单状态=待收货  && 发货时间等今天
				criteria.andSubOrderStatusEqualTo(OrderLogCustom.ORDER_STATUS_WAIT_RECEIPT);
				try {
					criteria.andDeliveryDateGreaterThanOrEqualTo(DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")+" 00:00:00");
					criteria.andDeliveryDateLessThanOrEqualTo(DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")+" 23:59:59");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_productId"))) {
			criteria.andProductCodeEqualTo(request.getParameter("search_productId"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_expressId"))) {
			criteria.andSubOrderExpressIdEqualTo(request.getParameter("search_expressId"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_brandName"))) {
			criteria.andBrandNameEqualTo(request.getParameter("search_brandName"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_activityId"))) {
			criteria.andActivityIdEqualTo(Integer.parseInt(request.getParameter("search_activityId")));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_expressNo"))) {
			criteria.andSubOrderExpressNoEqualTo(request.getParameter("search_expressNo"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_subOrderCode"))) {
			criteria.andSubOrderCodeEqualTo(request.getParameter("search_subOrderCode"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_receiverName"))) {
			criteria.andReceiverNameEqualTo(request.getParameter("search_receiverName"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_receiverPhone"))) {
			criteria.andReceiverPhoneEqualTo(request.getParameter("search_receiverPhone"));
		}
		
		try {
			if (!StringUtil.isEmpty(request.getParameter("payDateBegin"))) {
				criteria.andAuditDateGreaterThanOrEqualTo(request.getParameter("payDateBegin")+" 00:00:00");
			}
			if (!StringUtil.isEmpty(request.getParameter("payDateEnd"))) {
				criteria.andAuditDateLessThanOrEqualTo(request.getParameter("payDateEnd")+" 23:59:59");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
		String[] titles = { "订单号", "快递名称", "快递单号","是否异常单","商品售后状态","收件人","收件人电话","省","市","区","收件地址","客户付款状态","客户付款时间","发货时间","退款时间","完成时间","商品最终状态","品牌","货号","SKU编码","商品名称","规格","数量","单价","商家优惠","平台优惠","积分优惠","订单支付金额","客户留言","商家备注","售后模板","发货方","审核时间"};
		ExcelBean excelBean = new ExcelBean("导出订单" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls",
				"导出订单", titles);
		List<String[]> datas = new ArrayList<>();
		for (OrderDtlCustom orderDtlCustom : orderDtlCustoms) {
			if(orderDtlCustom.getCustomerServiceStatus()!=null && (orderDtlCustom.getCustomerServiceStatusDesc().equals("进行中") || orderDtlCustom.getCustomerServiceStatusDesc().equals("已完成"))){
				continue;
			}else{
				if(orderDtlCustom.getProductStatus()!=null && orderDtlCustom.getProductStatus().equals("2")){
					continue;
				}
				String receiverPhone = "";
				if(orderDtlCustom.getSubOrderStatus().equals(OrderLogCustom.ORDER_STATUS_WAIT_DELIVERY)){//待发货
					receiverPhone = orderDtlCustom.getReceiverPhone();
	            }else{
	            	if(orderDtlCustom.getReceiverPhone()!=null){
	            		receiverPhone = orderDtlCustom.getReceiverPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
	            	}
	            }
				String[] data = {
						orderDtlCustom.getSubOrderCode(),
						orderDtlCustom.getExpressName(),
						orderDtlCustom.getExpressNo()==null?"":"`"+orderDtlCustom.getExpressNo(),
						"P".equals(orderDtlCustom.getMemberStatus())?"异常单":"",
						(orderDtlCustom.getCustomerServiceStatus() == null||orderDtlCustom.getCustomerServiceStatus().equals(CustomerServiceOrderCustom.ORDER_STATUS_REFUSE)||orderDtlCustom.getCustomerServiceStatus().equals(CustomerServiceOrderCustom.ORDER_STATUS_CANCLE))? "" : orderDtlCustom.getCustomerServiceType()+orderDtlCustom.getCustomerServiceStatusDesc(),
						orderDtlCustom.getReceiverName(),
						"P".equals(orderDtlCustom.getMemberStatus())?receiverPhone+"（异常单）":receiverPhone,
						orderDtlCustom.getProvince(),
						orderDtlCustom.getCity(),
						orderDtlCustom.getCounty(),
						orderDtlCustom.getReceiverAddress(),
						orderDtlCustom.getPaymentDesc(),
						orderDtlCustom.getPaymentDate() == null? "" : sf.format(orderDtlCustom.getPaymentDate()),   
						orderDtlCustom.getDeliveryDate() == null? "" : sf.format(orderDtlCustom.getDeliveryDate()), 
						orderDtlCustom.getRefundDate() == null? "" : sf.format(orderDtlCustom.getRefundDate()),    
						orderDtlCustom.getCompleteDate() == null? "" : sf.format(orderDtlCustom.getCompleteDate()),   
						orderDtlCustom.getProductStatusDesc(),   
						orderDtlCustom.getBrandName(),   
						orderDtlCustom.getArtNo(),   
						orderDtlCustom.getSku(),   
						orderDtlCustom.getProductName(),   
						orderDtlCustom.getProductPropDesc(),   
						orderDtlCustom.getQuantity() == null? "" : orderDtlCustom.getQuantity().toString(),   
						orderDtlCustom.getSalePrice() == null? "" : orderDtlCustom.getSalePrice().toString(),   
						orderDtlCustom.getMchtPreferential() == null? "" : orderDtlCustom.getMchtPreferential().toString(),   
						orderDtlCustom.getPlatformPreferential() == null? "" : orderDtlCustom.getPlatformPreferential().toString(),   
						orderDtlCustom.getIntegralPreferential() == null? "" : orderDtlCustom.getIntegralPreferential().toString(),   
						orderDtlCustom.getPayAmount() == null? "" : orderDtlCustom.getPayAmount().toString(),   
						orderDtlCustom.getMemberRemarks(),
						orderDtlCustom.getMchtRemarks(),
						orderDtlCustom.getTempletName() == null? "" : orderDtlCustom.getTempletName(),
						orderDtlCustom.getCloudProductItemId() == null?"商家"	:"供应商",
						orderDtlCustom.getAuditDate() == null? "" : sf.format(orderDtlCustom.getAuditDate())
					};
				datas.add(data);
			}
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	}
	
	/**
	 * 批量设置快递
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/batchExpress")
	@ResponseBody
	public ResponseMsg batchExpress(HttpServletRequest request) {
		String subOrderIdsStr = request.getParameter("subOrderIds");
		String expressId = request.getParameter("expressId");
		String[] subOrderIdsArray = subOrderIdsStr.split(",");
		subOrderService.updateExpressId(expressId,subOrderIdsArray);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
	/**
	 * 确认选中的
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/confirmSelected")
	@ResponseBody
	public ResponseMsg confirmSelected(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String subOrderIdsStr = request.getParameter("subOrderIds");
		String[] subOrderIdsArray = subOrderIdsStr.split(",");
		String deliveryDate = DateUtil.getStandardDateTime(new Date());
		List<KdnWuliuInfo> kdnWuliuInfos = new ArrayList<KdnWuliuInfo>();
		List<SysAppMessage> sysAppMessages = new ArrayList<SysAppMessage>();
		List<Integer> subOrderIds = new ArrayList<Integer>();
		HashMap<String,Object> map = new HashMap<String,Object>();
		int i=0;
		for(String subOrderIdStr:subOrderIdsArray){
			SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderIdStr));
			CustomerServiceOrderExample csoe = new CustomerServiceOrderExample();
			csoe.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrder.getId()).andStatusEqualTo("0");
			List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(csoe);
			if(customerServiceOrders!=null && customerServiceOrders.size()>0){
				i++;
				continue;
			}
			if(subOrder.getExpressNo().trim().length()<10 || subOrder.getExpressNo().trim().length()>30){
				i++;
				continue;
			}
			if(subOrder.getExpressNo().trim().matches(".*[^a-zA-Z0-9]+.*")){
				i++;
				continue;
			}
			if(!StringUtil.isEmpty(subOrder.getExpressId())){
				subOrderIds.add(subOrder.getId());
				
				KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
				KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andExpressIdEqualTo(Integer.parseInt(subOrder.getExpressId()));
				criteria.andLogisticCodeEqualTo(subOrder.getExpressNo());
				List<KdnWuliuInfo> kdnWuliuInfoList = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
				KdnWuliuInfo kdnWuliuInfo = null;
				if(kdnWuliuInfoList==null || kdnWuliuInfoList.size()==0){
					kdnWuliuInfo = new KdnWuliuInfo();
					kdnWuliuInfo.setCreateBy(this.getMchtInfo().getId());
					kdnWuliuInfo.setCreateDate(new Date());
					kdnWuliuInfo.setDelFlag("0");
					kdnWuliuInfo.setExpressId(Integer.parseInt(subOrder.getExpressId()));
					kdnWuliuInfo.setLogisticCode(subOrder.getExpressNo().trim());
					kdnWuliuInfo.setSubscribeStatus("0");
					kdnWuliuInfo.setStatus("0");
					kdnWuliuInfo.setTryTimes(0);
					kdnWuliuInfo.setSubOrderId(subOrder.getId());
					if(!map.containsKey(subOrder.getExpressId()+"-"+subOrder.getExpressNo())){
						map.put(subOrder.getExpressId()+"-"+subOrder.getExpressNo(), kdnWuliuInfo);
						kdnWuliuInfos.add(kdnWuliuInfo);
					}
				}else{
					kdnWuliuInfo = kdnWuliuInfoList.get(0);
					if(kdnWuliuInfo.getSubOrderId()!=null&&subOrder.getId()>kdnWuliuInfo.getSubOrderId()){
						kdnWuliuInfo.setSubOrderId(subOrder.getId());
						kdnWuliuInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
						kdnWuliuInfo.setUpdateDate(new Date());
					}else{
						if(kdnWuliuInfo.getSubOrderId()==null){
							kdnWuliuInfo.setSubOrderId(subOrder.getId());
							kdnWuliuInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
							kdnWuliuInfo.setUpdateDate(new Date());
						}
					}
					kdnWuliuInfos.add(kdnWuliuInfo);
				}
				
//				SysAppMessage sysAppMessage = new SysAppMessage();
//				sysAppMessage.setCreateBy(this.getSessionMchtInfo(request).getId());
//				sysAppMessage.setCreateDate(new Date());
//				sysAppMessage.setDelFlag("0");
//				sysAppMessage.setType(SysAppMessageCustom.TYPE_WULIU);
//				sysAppMessage.setTitle(SysAppMessageCustom.TITLE_DELIVERY);
//				sysAppMessage.setContent("商家已发货");
//				sysAppMessage.setExpressNo(subOrder.getExpressNo());
//				sysAppMessage.setLinkType(SysAppMessageCustom.LINKTYPE_SUBORDER);
//				sysAppMessage.setLinkId(subOrder.getId());
//				sysAppMessages.add(sysAppMessage);
			}else{//快递有误的订单
				i++;
			}
		}
		if(subOrderIds!=null && subOrderIds.size()>0){
			try {
				subOrderService.updateDeliveryDateAndStatus(subOrderIds,OrderLogCustom.ORDER_STATUS_WAIT_RECEIPT,deliveryDate,kdnWuliuInfos,sysAppMessages);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseMsg(ResponseMsg.ERROR, "操作失败，请稍后重试");
			}
		}
		returnData.put("successCount", subOrderIds.size());
		return new ResponseMsg(ResponseMsg.SUCCESS, "成功发货"+subOrderIds.size()+"单，发货失败"+i+"单，请检测快递单号是否有误。",returnData);
	}
	
	/**
	 * 确认全部
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/confirmAll")
	@ResponseBody
	public ResponseMsg confirmAll(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
		SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
		subOrderCustomExample.setOrderByClause("t.id desc");
		subOrderCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		subOrderCustomCriteria.andAuditDefautStauts();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
			subOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		}else{
			subOrderCustomCriteria.andDelFlagEqualTo("0");
		}
		subOrderCustomCriteria.andStatusEqualTo("1");
		subOrderCustomCriteria.andExpressNoIsNotNull();
		
		if (!StringUtil.isEmpty(request.getParameter("search_productId"))) {
			subOrderCustomCriteria.andProductCodeEqualTo(request.getParameter("search_productId"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_expressId"))) {
			subOrderCustomCriteria.andExpressIdEqualTo(request.getParameter("search_expressId"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_brandId"))) {
			subOrderCustomCriteria.andBrandIdEqualTo(request.getParameter("search_brandId"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_activityId"))) {
			subOrderCustomCriteria.andActivityIdEqualTo(Integer.parseInt(request.getParameter("search_activityId")));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_expressNo"))) {
			subOrderCustomCriteria.andExpressNoEqualTo(request.getParameter("search_expressNo"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_subOrderCode"))) {
			subOrderCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("search_subOrderCode"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_receiverName"))) {
			subOrderCustomCriteria.andReceiverNameEqualTo(request.getParameter("search_receiverName"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_receiverPhone"))) {
			subOrderCustomCriteria.andReceiverPhoneEqualTo(request.getParameter("search_receiverPhone"));
		}
		if (!StringUtil.isEmpty(request.getParameter("search_remarksColor"))) {
			subOrderCustomCriteria.andRemarksColorEqualTo(request.getParameter("search_remarksColor"));
		}

		try{

			if (!StringUtil.isEmpty(request.getParameter("payDateBegin"))) {
				subOrderCustomCriteria.andAuditDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("payDateEnd"))) {
				subOrderCustomCriteria.andAuditDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<SubOrderCustom> subOrderCustoms = subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
		if(subOrderCustoms.size()>2000){
        	return new ResponseMsg(ResponseMsg.ERROR, "确认的数据不能超过2000条！",null);
        }
		List<KdnWuliuInfo> kdnWuliuInfos = new ArrayList<KdnWuliuInfo>();
		List<SysAppMessage> sysAppMessages = new ArrayList<SysAppMessage>();
		List<Integer> subOrderIds = new ArrayList<Integer>();
		HashMap<String,Object> map = new HashMap<String,Object>();
		int i = 0;
		for(SubOrderCustom subOrderCustom:subOrderCustoms){
			CustomerServiceOrderExample csoe = new CustomerServiceOrderExample();
			csoe.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrderCustom.getId()).andStatusEqualTo("0");
			List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(csoe);
			if(customerServiceOrders!=null && customerServiceOrders.size()>0){
				i++;
				continue;
			}
			if(subOrderCustom.getExpressNo().trim().length()<10 || subOrderCustom.getExpressNo().trim().length()>30){
				i++;
				continue;
			}
			if(subOrderCustom.getExpressNo().trim().matches(".*[^a-zA-Z0-9]+.*")){
				i++;
				continue;
			}
			if(!StringUtil.isEmpty(subOrderCustom.getExpressId())){
				subOrderIds.add(subOrderCustom.getId());
				
				KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
				KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andExpressIdEqualTo(Integer.parseInt(subOrderCustom.getExpressId()));
				criteria.andLogisticCodeEqualTo(subOrderCustom.getExpressNo());
				List<KdnWuliuInfo> kdnWuliuInfoList = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
				KdnWuliuInfo kdnWuliuInfo = null;
				if(kdnWuliuInfoList==null || kdnWuliuInfoList.size()==0){
					kdnWuliuInfo = new KdnWuliuInfo();
					kdnWuliuInfo.setCreateBy(this.getMchtInfo().getId());
					kdnWuliuInfo.setCreateDate(new Date());
					kdnWuliuInfo.setDelFlag("0");
					kdnWuliuInfo.setExpressId(Integer.parseInt(subOrderCustom.getExpressId()));
					kdnWuliuInfo.setLogisticCode(subOrderCustom.getExpressNo().trim());
					kdnWuliuInfo.setSubscribeStatus("0");
					kdnWuliuInfo.setSubOrderId(subOrderCustom.getId());
					if(!map.containsKey(subOrderCustom.getExpressId()+"-"+subOrderCustom.getExpressNo())){
						map.put(subOrderCustom.getExpressId()+"-"+subOrderCustom.getExpressNo(), kdnWuliuInfo);
						kdnWuliuInfos.add(kdnWuliuInfo);
					}
				}else{
					kdnWuliuInfo = kdnWuliuInfoList.get(0);
					if(kdnWuliuInfo.getSubOrderId()!=null&&subOrderCustom.getId()>kdnWuliuInfo.getSubOrderId()){
						kdnWuliuInfo.setSubOrderId(subOrderCustom.getId());
						kdnWuliuInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
						kdnWuliuInfo.setUpdateDate(new Date());
					}else{
						if(kdnWuliuInfo.getSubOrderId()==null){
							kdnWuliuInfo.setSubOrderId(subOrderCustom.getId());
							kdnWuliuInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
							kdnWuliuInfo.setUpdateDate(new Date());
						}
					}
					kdnWuliuInfos.add(kdnWuliuInfo);
				}
				
//				SysAppMessage sysAppMessage = new SysAppMessage();
//				sysAppMessage.setCreateBy(this.getSessionMchtInfo(request).getId());
//				sysAppMessage.setCreateDate(new Date());
//				sysAppMessage.setDelFlag("0");
//				sysAppMessage.setType(SysAppMessageCustom.TYPE_WULIU);
//				sysAppMessage.setTitle(SysAppMessageCustom.TITLE_DELIVERY);
//				sysAppMessage.setContent("商家已发货");
//				sysAppMessage.setExpressNo(subOrderCustom.getExpressNo());
//				sysAppMessage.setLinkType(SysAppMessageCustom.LINKTYPE_SUBORDER);
//				sysAppMessage.setLinkId(subOrderCustom.getId());
//				sysAppMessages.add(sysAppMessage);
			}else{
				i++;
			}
		}
		String deliveryDate = DateUtil.getStandardDateTime(new Date());
		if(subOrderIds!=null && subOrderIds.size()>0){
			try {
				subOrderService.updateDeliveryDateAndStatus(subOrderIds,OrderLogCustom.ORDER_STATUS_WAIT_RECEIPT,deliveryDate,kdnWuliuInfos,sysAppMessages);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseMsg(ResponseMsg.ERROR, "操作失败，请稍后重试");
			}
		}
		returnData.put("successCount", subOrderIds.size());
		return new ResponseMsg(ResponseMsg.SUCCESS, "成功发货"+subOrderIds.size()+"单，发货失败"+i+"单，请检测快递单号是否有误。",returnData);
	}
	
	/**
	 * 导入快递单号
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/importExpressNo")
	@ResponseBody
	public ResponseMsg importExpressNo(HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
        MultipartFile file = multipartRequest.getFile("file");  
        if(file.isEmpty()){  
            throw new Exception("文件不存在！");  
        }  
        List<ArrayList<String>> dataList = ExcelUtils.read(file, file.getOriginalFilename(), 3);//3表示取excel表格的前三列
        if (dataList == null || dataList.size() < 1) {
        	return new ResponseMsg(ResponseMsg.ERROR, "导入文件格式只支持.xls或.xlsx",null);
		}
        if(dataList.size()>2001){
        	return new ResponseMsg(ResponseMsg.ERROR, "导入的数据不能超过2000条！",null);
        }
        List<SubOrder> updateList = new ArrayList<SubOrder>();
        List<SubOrder> removeList = new ArrayList<SubOrder>();
        for (int i = 1; i < dataList.size(); i++) {  
            List<String> data = dataList.get(i);
            String subOrderCode = data.get(0);
            String expressName = data.get(1);
            String expressNo = data.get(2).trim();
            if(StringUtil.isEmpty(subOrderCode)){
            	continue;
            }else{
            	if(StringUtil.isEmpty(expressNo)){
            		continue;
            	}else{
            		SubOrderExample example = new SubOrderExample();
            		SubOrderExample.Criteria criteria = example.createCriteria();
            		criteria.andDelFlagEqualTo("0");
            		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
            		criteria.andSubOrderCodeEqualTo(subOrderCode.trim());
            		criteria.andStatusEqualTo("1");//待发货
            		List<SubOrder> subOrders = subOrderService.selectByExample(example);
            		if(subOrders!=null && subOrders.size()>0){
            			SubOrder subOrder = subOrders.get(0);
            			Express expresss = new Express();
            			if(!StringUtil.isEmpty(expressName)){
            				List<Express> expresssList = expressCustomMapper.getExpressByName(expressName);
            				if(expresssList!=null && expresssList.size()>0){
            					expresss = expresssList.get(0);
            				}
            			}
            			if(subOrder.getExpressNo()!=null && subOrder.getExpressNo().equals(expressNo) && subOrder.getExpressId()!=null && expresss.getId()!=null && subOrder.getExpressId().equals(expresss.getId().toString())){
            				removeList.add(subOrder);
            			}else{
            				if(expresss.getId()!=null){
            					subOrder.setExpressId(expresss.getId().toString());
            				}
            				subOrder.setExpressNo(expressNo.trim());
            				updateList.add(subOrder);
            			}
            		}
            	}
            }
        }
        updateList.removeAll(removeList);
        if(updateList!=null && updateList.size()>0){
        	subOrderService.updateSubOrders(updateList);
        }
        List<Integer> subOrderIds = new ArrayList<Integer>();
        for(SubOrder subOrder:updateList){
        	if(!subOrderIds.contains(subOrder.getId())){
        		subOrderIds.add(subOrder.getId());
        	}
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("importSuccess", subOrderIds.size());
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}
	
	/**
	 * 根据子订单id获取快递信息
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/getExpressInfo")
	@ResponseBody
	public ResponseMsg getExpressInfo(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String subOrderId = request.getParameter("subOrderId");
		SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
		returnData.put("expressId", subOrder.getExpressId());
		returnData.put("expressNo", subOrder.getExpressNo());
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 修改快递单号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/editExpress")
	@ResponseBody
	public ResponseMsg editExpress(HttpServletRequest request) {
		String subOrderId = request.getParameter("subOrderId");
		String expressId = request.getParameter("expressId");
		String expressNo = request.getParameter("expressNo");
		SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
		Date deliveryDate = subOrder.getDeliveryDate();
		Date date = DateUtil.getYesterDayDate();
		if(deliveryDate.before(date)){
			return new ResponseMsg(ResponseMsg.ERROR, "发货时间小于昨天，无法修改",null);
		}
		subOrder.setExpressNo(expressNo.trim());
		subOrder.setExpressId(expressId);
		subOrder.setUpdateDate(new Date());
		subOrder.setUpdateBy(this.getSessionMchtInfo(request).getId());
		
		KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
		KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andExpressIdEqualTo(Integer.parseInt(expressId));
		criteria.andLogisticCodeEqualTo(expressNo.trim());
		List<KdnWuliuInfo> kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
		KdnWuliuInfo kdnWuliuInfo = null;
		if(kdnWuliuInfos==null || kdnWuliuInfos.size()==0){
			kdnWuliuInfo = new KdnWuliuInfo();
			kdnWuliuInfo.setCreateBy(this.getMchtInfo().getId());
			kdnWuliuInfo.setCreateDate(new Date());
			kdnWuliuInfo.setDelFlag("0");
			kdnWuliuInfo.setExpressId(Integer.parseInt(expressId));
			kdnWuliuInfo.setLogisticCode(expressNo.trim());
			kdnWuliuInfo.setSubscribeStatus("0");
			kdnWuliuInfo.setSubOrderId(subOrder.getId());
		}else{
			kdnWuliuInfo = kdnWuliuInfos.get(0);
			if(kdnWuliuInfo.getSubOrderId()!=null&&subOrder.getId()>kdnWuliuInfo.getSubOrderId()){
				kdnWuliuInfo.setSubOrderId(subOrder.getId());
				kdnWuliuInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
				kdnWuliuInfo.setUpdateDate(new Date());
			}else{
				if(kdnWuliuInfo.getSubOrderId()==null){
					kdnWuliuInfo.setSubOrderId(subOrder.getId());
					kdnWuliuInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
					kdnWuliuInfo.setUpdateDate(new Date());
				}
			}
		}
		try {
			subOrderService.updateSubOrder(subOrder,kdnWuliuInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "操作失败，请稍后重试");
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
	/**
	 * 批量更新
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/subOrder/batchUpdateExpress")
	@ResponseBody
	public ResponseMsg batchUpdateExpress(HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
        MultipartFile file = multipartRequest.getFile("updateFile");  
        if(file.isEmpty()){  
            throw new Exception("文件不存在！");  
        }  
        List<ArrayList<String>> dataList = ExcelUtils.read(file, file.getOriginalFilename(), 3);//3表示取excel表格的前三列
        if (dataList == null || dataList.size() < 1) {
        	return new ResponseMsg(ResponseMsg.ERROR, "导入文件格式只支持.xls或.xlsx",null);
		}
        if(dataList.size()>2001){
        	return new ResponseMsg(ResponseMsg.ERROR, "导入的数据不能超过2000条！",null);
        }
        List<SubOrder> updateList = new ArrayList<SubOrder>();
        for (int i = 1; i < dataList.size(); i++) {  
            List<String> data = dataList.get(i);
            String subOrderCode = data.get(0);
            String expressName = data.get(1);
            String expressNo = data.get(2).trim();
            if(StringUtil.isEmpty(subOrderCode)){
            	continue;
            }else{
            	if(StringUtil.isEmpty(expressNo)){
            		continue;
            	}else{
            		SubOrderExample example = new SubOrderExample();
            		SubOrderExample.Criteria criteria = example.createCriteria();
            		criteria.andDelFlagEqualTo("0");
            		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
            		criteria.andSubOrderCodeEqualTo(subOrderCode);
            		criteria.andStatusEqualTo("2");//已发货
            		criteria.andDeliveryDateGreaterThanOrEqualTo(DateUtil.getYesterDayDate());
            		List<SubOrder> subOrders = subOrderService.selectByExample(example);
            		if(subOrders!=null && subOrders.size()>0){
            			SubOrder subOrder = subOrders.get(0);
            			Date deliveryDate = subOrder.getDeliveryDate();
            			Date date = DateUtil.getYesterDayDate();
            			if(deliveryDate.before(date)){
            				continue;
            			}
            			Express expresss = new Express();
            			if(!StringUtil.isEmpty(expressName)){
            				List<Express> expresssList = expressCustomMapper.getExpressByName(expressName);
            				if(expresssList!=null && expresssList.size()>0){
            					expresss = expresssList.get(0);
            				}else{
            					return new ResponseMsg(ResponseMsg.ERROR, "快递名称有误，找不到相应的快递物流",null);
            				}
            			}else{
            				return new ResponseMsg(ResponseMsg.ERROR, "快递名称不能为空",null);
            			}
            			if(subOrder.getExpressNo().equals(expressNo) && subOrder.getExpressId().equals(expresss.getId().toString())){
            				continue;
            			}else{
            				subOrder.setExpressId(expresss.getId().toString());
            				subOrder.setExpressNo(expressNo.trim());
            				updateList.add(subOrder);
            			}
            		}
            	}
            }
        }
        List<KdnWuliuInfo> kdnWuliuInfos = new ArrayList<KdnWuliuInfo>();
		HashMap<String,Object> map = new HashMap<String,Object>();
        if(updateList!=null && updateList.size()>0){
        	for(SubOrder subOrder:updateList){
        		KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
        		KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
        		criteria.andDelFlagEqualTo("0");
        		criteria.andExpressIdEqualTo(Integer.parseInt(subOrder.getExpressId()));
        		criteria.andLogisticCodeEqualTo(subOrder.getExpressNo());
        		List<KdnWuliuInfo> kdnWuliuInfoList = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
        		KdnWuliuInfo kdnWuliuInfo = null;
        		if(kdnWuliuInfoList==null || kdnWuliuInfoList.size()==0){
        			kdnWuliuInfo = new KdnWuliuInfo();
        			kdnWuliuInfo.setCreateBy(this.getMchtInfo().getId());
        			kdnWuliuInfo.setCreateDate(new Date());
        			kdnWuliuInfo.setDelFlag("0");
        			kdnWuliuInfo.setExpressId(Integer.parseInt(subOrder.getExpressId()));
        			kdnWuliuInfo.setLogisticCode(subOrder.getExpressNo().trim());
        			kdnWuliuInfo.setSubscribeStatus("0");
        			kdnWuliuInfo.setSubOrderId(subOrder.getId());
        			if(!map.containsKey(subOrder.getExpressId()+"-"+subOrder.getExpressNo())){
        				map.put(subOrder.getExpressId()+"-"+subOrder.getExpressNo(), kdnWuliuInfo);
        				kdnWuliuInfos.add(kdnWuliuInfo);
        			}
        		}else{
					kdnWuliuInfo = kdnWuliuInfos.get(0);
					if(kdnWuliuInfo.getSubOrderId()!=null&&subOrder.getId()>kdnWuliuInfo.getSubOrderId()){
						kdnWuliuInfo.setSubOrderId(subOrder.getId());
						kdnWuliuInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
						kdnWuliuInfo.setUpdateDate(new Date());
					}else{
						if(kdnWuliuInfo.getSubOrderId()==null){
							kdnWuliuInfo.setSubOrderId(subOrder.getId());
							kdnWuliuInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
							kdnWuliuInfo.setUpdateDate(new Date());
						}
					}
					kdnWuliuInfos.add(kdnWuliuInfo);
				}
        		
        	}
        	try {
        		subOrderService.updateSubOrders(updateList,kdnWuliuInfos);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseMsg(ResponseMsg.ERROR, "操作失败，请稍后重试");
			}
        }else{
        	return new ResponseMsg(ResponseMsg.ERROR, "当前没有需要更新的订单",null);
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("importSuccess", updateList.size());
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}
	
	@RequestMapping("/subOrder/saveSubOrderAttachment")
	@ResponseBody
	public ResponseMsg saveSubOrderAttachment(HttpServletRequest request) {
		String id = request.getParameter("id");
		String filePath = request.getParameter("filePath");
		String fileName = request.getParameter("fileName");
		SubOrderAttachment subOrderAttachment = new SubOrderAttachment();
		subOrderAttachment.setDelFlag("0");
		subOrderAttachment.setCreateDate(new Date());
		subOrderAttachment.setCreateBy(this.getSessionMchtInfo(request).getId());
		subOrderAttachment.setSubOrderId(Integer.parseInt(id));
		subOrderAttachment.setName(fileName);
		subOrderAttachment.setAttachmentPath(filePath);
		subOrderAttachment.setCreatorType("2");//2.商家
		subOrderAttachmentMapper.insertSelective(subOrderAttachment);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,subOrderAttachment);
	}
	
	@RequestMapping("/subOrder/checkFileExists")
	@ResponseBody
	public ResponseMsg checkFileExists(HttpServletRequest request) {
		String subOrderAttachmentId = request.getParameter("subOrderAttachmentId");
		SubOrderAttachment subOrderAttachment = subOrderAttachmentMapper.selectByPrimaryKey(Integer.parseInt(subOrderAttachmentId));
		if(subOrderAttachment != null){
			InputStream stream = SubOrderController.class.getResourceAsStream("/config.properties");
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
				return new ResponseMsg(ResponseMsg.ERROR, "文件目录不存在");
			}
			File file = new File(defaultPath+subOrderAttachment.getAttachmentPath());
			//如果文件不存在
			if(!file.exists()){
				return new ResponseMsg(ResponseMsg.ERROR, "附件不存在或已被删除");
			}
		}else{
			return new ResponseMsg(ResponseMsg.ERROR, "附件不存在或已被删除",null);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	@RequestMapping("/subOrder/downLoadSubOrderAttachment")
	public void downLoadSubOrderAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String filePath = request.getParameter("filePath");
		String fileName = request.getParameter("fileName");
		InputStream stream = SubOrderController.class.getResourceAsStream("/config.properties");
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
		File file = new File(defaultPath+filePath);
		//如果文件不存在
		if(!file.exists()){
		    return;
		}
		//设置响应头，控制浏览器下载该文件
		String downloadFileName = "";
		String agent = request.getHeader("USER-AGENT");  
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
	
	/**
	 * 获取评论
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/getComments")
	@ResponseBody
	public ResponseMsg getComments(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String subOrderId = request.getParameter("subOrderId");
		SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
		if(subOrder.getStatus().equals("3")){//3.完成
			OrderDtlExample ode = new OrderDtlExample();
			OrderDtlExample.Criteria odec = ode.createCriteria();
			odec.andDelFlagEqualTo("0");
			odec.andSubOrderIdEqualTo(Integer.parseInt(subOrderId));
			List<OrderDtl> orderDtls = orderDtlService.selectByExample(ode);
			List<CommentCustom> commentCustomList = new ArrayList<CommentCustom>();
			for(OrderDtl orderDtl:orderDtls){
				CommentCustomExample cce = new CommentCustomExample();
				CommentCustomExample.CommentCustomCriteria ccec = cce.createCriteria();
				ccec.andDelFlagEqualTo("0");
				ccec.andSubOrderIdEqualTo(Integer.parseInt(subOrderId));
				ccec.andOrderDtlIdEqualTo(orderDtl.getId());
				ccec.andIsAppendCommentEqualTo("0");
				List<CommentCustom> commentCustoms = commentService.selectCommentCustomByExample(cce);
				if(commentCustoms!=null && commentCustoms.size()>0){
					CommentCustom commentCustom = commentCustoms.get(0);
					if(commentCustom.getProductScore()!=null){
						commentCustom.setProductScoreStarWidth(commentCustom.getProductScore()*24);
					}else{
						commentCustom.setProductScoreStarWidth(0);
					}
					CommentPicExample cpe = new CommentPicExample();
					CommentPicExample.Criteria cpec = cpe.createCriteria();
					cpec.andDelFlagEqualTo("0");
					cpec.andCommentIdEqualTo(commentCustom.getId());
					cpec.andPicTypeEqualTo("1");
					List<CommentPic> commentPics = commentPicMapper.selectByExample(cpe);
					commentCustom.setCommentPics(commentPics);
					
					CommentCustomExample appendCce = new CommentCustomExample();
					CommentCustomExample.CommentCustomCriteria appendCcec = appendCce.createCriteria();
					appendCcec.andDelFlagEqualTo("0");
					appendCcec.andSubOrderIdEqualTo(Integer.parseInt(subOrderId));
					appendCcec.andOrderDtlIdEqualTo(orderDtl.getId());
					appendCcec.andIsAppendCommentEqualTo("1");
					List<Comment> appendComments = commentService.selectByExample(appendCce);
					if(appendComments!=null && appendComments.size()>0){
						Comment appendComment = appendComments.get(0);
						commentCustom.setAppendContent(appendComment.getContent());
						CommentPicExample appendCpe = new CommentPicExample();
						CommentPicExample.Criteria appendCpec = appendCpe.createCriteria();
						appendCpec.andDelFlagEqualTo("0");
						appendCpec.andCommentIdEqualTo(appendComment.getId());
						appendCpec.andPicTypeEqualTo("1");
						List<CommentPic> appendCommentPics = commentPicMapper.selectByExample(appendCpe);
						commentCustom.setAppendCommentPics(appendCommentPics);
						long betweenDays = DateUtil.getDayBetween(commentCustom.getCreateDate(), appendComment.getCreateDate());
						commentCustom.setBetweenDays(betweenDays);
					}
					commentCustomList.add(commentCustom);
				}
			}
			returnData.put("commentCustomList", commentCustomList);
			ShopScoreExample e = new ShopScoreExample();
			ShopScoreExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			c.andSubOrderIdEqualTo(Integer.parseInt(subOrderId));
			List<ShopScore> shopScores = shopScoreService.selectByExample(e);
			if(shopScores!=null && shopScores.size()>0){
				ShopScore shopScore = shopScores.get(0);
				returnData.put("mchtScoreStarWidth", shopScore.getMchtScore()*24);
				returnData.put("wuliuScoreStarWidth", shopScore.getWuliuScore()*24);
			}else{
				returnData.put("mchtScoreStarWidth", 0);
				returnData.put("wuliuScoreStarWidth", 0);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
		}else{
			return new ResponseMsg(ResponseMsg.ERROR, "暂无评价");
		}
	}
	
	/**
	 * 修改运费
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/editFreight")
	@ResponseBody
	public synchronized ResponseMsg editFreight(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			String subOrderId = request.getParameter("subOrderId");
			String orderDtlId = request.getParameter("orderDtlId");
			String editFreight = request.getParameter("editFreight");
			BigDecimal newEditFreight = new BigDecimal(editFreight);
			if(newEditFreight.compareTo(BigDecimal.ZERO)<0){
				return new ResponseMsg(ResponseMsg.ERROR, "运费不能小于0",returnData);
			}
			SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
			Integer mchtId = this.getSessionMchtInfo(request).getId();
			if(!subOrder.getMchtId().equals(mchtId)){
				return new ResponseMsg(ResponseMsg.ERROR, "异常，无法修改",returnData);
			}
			
			OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(Integer.parseInt(orderDtlId));
			BigDecimal oldEditFreight = new BigDecimal(0);
			if(orderDtl.getFreight()!=null){
				oldEditFreight = orderDtl.getFreight();
			}
			if(newEditFreight.compareTo(oldEditFreight)==0){
				return new ResponseMsg(ResponseMsg.SUCCESS, "修改运费成功",returnData);
			}
			
			BigDecimal diff = newEditFreight.subtract(oldEditFreight);
			orderDtl.setPayAmount(orderDtl.getPayAmount().add(diff));
			orderDtl.setFreight(newEditFreight);
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(subOrder.getMchtId());
			if(mchtInfo.getMchtType().equals("2")){//2.POP
				BigDecimal tmp = orderDtl.getSalePrice().multiply(new BigDecimal(orderDtl.getQuantity())).add(newEditFreight).subtract(orderDtl.getMchtPreferential());
				BigDecimal commissionAmount = tmp.multiply(orderDtl.getPopCommissionRate());
				orderDtl.setCommissionAmount(commissionAmount);//技术服务费
				BigDecimal settleAmount = (new BigDecimal(1).subtract(orderDtl.getPopCommissionRate())).multiply(tmp);
				orderDtl.setSettleAmount(settleAmount);
			}else{//1.SPOP
				orderDtl.setSettleAmount(orderDtl.getSettlePrice().multiply(new BigDecimal(orderDtl.getQuantity())).subtract(orderDtl.getMchtPreferential()).add(newEditFreight));
			}
			orderDtl.setUpdateBy(this.getSessionMchtInfo(request).getId());
			orderDtl.setUpdateDate(new Date());
			
			BigDecimal total = new BigDecimal(0);
			total = total.add(new BigDecimal(editFreight));
			OrderDtlExample ode = new OrderDtlExample();
			OrderDtlExample.Criteria odec = ode.createCriteria();
			odec.andDelFlagEqualTo("0");
			odec.andSubOrderIdEqualTo(Integer.parseInt(subOrderId));
			odec.andIdNotEqualTo(Integer.parseInt(orderDtlId));
			List<OrderDtl> orderDtls = orderDtlService.selectByExample(ode);
			for(OrderDtl od:orderDtls){
				if(od.getFreight()!=null){
					total = total.add(od.getFreight());
				}
			}
			
			subOrder.setFreight(total);
			subOrder.setPayAmount(subOrder.getPayAmount().add(diff));
			subOrder.setUpdateBy(this.getSessionMchtInfo(request).getId());
			subOrder.setUpdateDate(new Date());
			
			BigDecimal combineTotalFreight = new BigDecimal(0);
			combineTotalFreight = combineTotalFreight.add(subOrder.getFreight());
			SubOrderExample soe = new SubOrderExample();
			SubOrderExample.Criteria soec = soe.createCriteria();
			soec.andDelFlagEqualTo("0");
			soec.andCombineOrderIdEqualTo(subOrder.getCombineOrderId());
			soec.andIdNotEqualTo(subOrder.getId());
			List<SubOrder> subOrders = subOrderService.selectByExample(soe);
			for(SubOrder so:subOrders){
				if(so.getFreight()!=null){
					combineTotalFreight = combineTotalFreight.add(so.getFreight());
				}
			}
			CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
			combineOrder.setFreight(combineTotalFreight);
			combineOrder.setTotalPayAmount(combineOrder.getTotalPayAmount().add(diff));
			combineOrder.setCombineOrderCode(combineOrder.getCombineOrderCode().replace("XG", "XG8"));
			combineOrder.setUpdateBy(this.getSessionMchtInfo(request).getId());
			combineOrder.setUpdateDate(new Date());
			if(!subOrder.getStatus().equals("0") || !combineOrder.getStatus().equals("0")){
				return new ResponseMsg(ResponseMsg.ERROR, "修改失败，当前订单状态有变化",returnData);
			}else{
				subOrderService.save(orderDtl,subOrder,combineOrder);
				returnData.put("subOrderFreight", subOrder.getFreight());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "修改运费失败，请稍后重试",returnData);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}
	
	@RequestMapping("/subOrder/allowModifyComment")
	@ResponseBody
	public ResponseMsg allowModifyComment(HttpServletRequest request) {
		String subOrderId = request.getParameter("subOrderId");
		SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
		subOrder.setIsAllowModifyComment("1");
		subOrder.setUpdateDate(new Date());
		subOrder.setUpdateBy(this.getSessionMchtInfo(request).getId());
		subOrderService.updateByPrimaryKeySelective(subOrder);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 立即发货页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/toDelivery")
	public String toDelivery(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		OrderDtlCustomExample odce = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria c = odce.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andSubOrderIdEqualTo(Integer.parseInt(id));
		c.andDeliveryStatusEqualTo("0");
		c.andNotCustomerServiceOrder();
		List<OrderDtlCustom> orderDtlList = orderDtlService.selectOrderDtlCustomByExample(odce);
		model.addAttribute("orderDtlList", orderDtlList);
		ExpressExample example = new ExpressExample();
		ExpressExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(example);
		model.addAttribute("expressList", expressList);
		model.addAttribute("subOrderId", id);
		return "subOrder/toDelivery";
	}
	
	/**
	 * 标记缺货页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/toMarkedOutOfStock")
	public String toOutOfStock(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		OrderDtlExample ode = new OrderDtlExample();
		ode.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(Integer.parseInt(id)).andMarkedOutOfStockEqualTo("0");
		List<OrderDtl> orderDtlList = orderDtlService.selectByExample(ode);
		model.addAttribute("orderDtlList", orderDtlList);
		model.addAttribute("subOrderId", id);
		return "subOrder/toMarkedOutOfStock";
	}
	
	/**
	 * 标记缺货
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/markedOutOfStock")
	@ResponseBody
	public ResponseMsg markedOutOfStock(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			String subOrderId = request.getParameter("subOrderId");
			SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
			CustomerServiceOrderExample csoe = new CustomerServiceOrderExample();
			csoe.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrder.getId()).andStatusEqualTo("0");
			List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(csoe);
			if(customerServiceOrders!=null && customerServiceOrders.size()>0){
				return new ResponseMsg(ResponseMsg.ERROR, "用户已发起售后，无法发货，请刷新页面");
			}
			String orderDtlIds = request.getParameter("orderDtlIds");
			List<Integer> idList = new ArrayList<Integer>();
			if(!StringUtil.isEmpty(orderDtlIds)){
				for(String orderDtlId:orderDtlIds.split(",")){
					idList.add(Integer.parseInt(orderDtlId));
				}
			}
			if(idList.size()>0){
				OrderDtl od = new OrderDtl();
				od.setUpdateBy(this.getSessionUserInfo(request).getId());
				od.setUpdateDate(new Date());
				od.setMarkedOutOfStock("1");
				OrderDtlExample ode = new OrderDtlExample();
				ode.createCriteria().andDelFlagEqualTo("0").andIdIn(idList).andMarkedOutOfStockEqualTo("0");
				orderDtlService.updateByExampleSelective(od, ode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "操作失败，请稍后重试",returnData);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}
	
	/**
	 * 检验是否有标记缺货的订单明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/checkMarkedOutOfStock")
	@ResponseBody
	public ResponseMsg checkMarkedOutOfStock(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			String subOrderId = request.getParameter("id");
			OrderDtlExample ode = new OrderDtlExample();
			ode.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(Integer.parseInt(subOrderId)).andMarkedOutOfStockEqualTo("0");
			List<OrderDtl> orderDtlList = orderDtlService.selectByExample(ode);
			if(orderDtlList!=null && orderDtlList.size()>0){
				return new ResponseMsg(ResponseMsg.SUCCESS, "",returnData);
			}else{
				return new ResponseMsg(ResponseMsg.ERROR, "没有可标记缺货的商品",returnData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "操作失败，请稍后重试",returnData);
		}
	}
	
	/**
	 * 补发页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/toSupplyAgain")
	public String toSupplyAgain(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		List<OrderDtl> orderDtlList = orderDtlService.getList(Integer.parseInt(id));
		model.addAttribute("orderDtlList", orderDtlList);
		ExpressExample example = new ExpressExample();
		ExpressExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(example);
		model.addAttribute("expressList", expressList);
		model.addAttribute("subOrderId", id);
		return "subOrder/toSupplyAgain";
	}
	
	/**
	 * 补发
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/supplyAgain")
	@ResponseBody
	public ResponseMsg supplyAgain(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			String subOrderId = request.getParameter("subOrderId");
			String jsonStr = request.getParameter("jsonStr");
			SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
			CustomerServiceOrderExample csoe = new CustomerServiceOrderExample();
			csoe.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrder.getId()).andStatusEqualTo("0");
			List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(csoe);
			if(customerServiceOrders!=null && customerServiceOrders.size()>0){
				return new ResponseMsg(ResponseMsg.ERROR, "用户已发起售后，无法发货，请刷新页面");
			}
			JSONArray ja = JSONArray.fromObject(jsonStr);
			for(int i=0;i<ja.size();i++){
				JSONObject jo = (JSONObject)ja.get(i);
				String eachExpressId = jo.getString("expressId").trim();
				String eachExpressNo = jo.getString("expressNo").trim();
				Boolean flag = subOrderService.verifyExpressNo(eachExpressNo, Integer.parseInt(eachExpressId));
				if(!flag){
					return new ResponseMsg(ResponseMsg.ERROR, "操作失败，快递单号异常",returnData);
				}
			}
			orderDtlService.supplyAgain(ja,subOrder,this.getSessionUserInfo(request).getId());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "操作失败，请稍后重试",returnData);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 修改快递页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/toEditDelivery")
	public String toEditDelivery(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		OrderDtlCustomExample odce = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria c = odce.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andSubOrderIdEqualTo(Integer.parseInt(id));
		c.andDeliveryStatusEqualTo("1");
		c.andNotCustomerServiceOrder();
		List<OrderDtlCustom> orderDtlCustomList = orderDtlService.selectOrderDtlCustomByExample(odce);
		model.addAttribute("orderDtlList", orderDtlCustomList);
		ExpressExample example = new ExpressExample();
		ExpressExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(example);
		model.addAttribute("expressList", expressList);
		model.addAttribute("subOrderId", id);
		return "subOrder/toEditDelivery";
	}
	
	/**
	 * 修改快递
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/editDelivery")
	@ResponseBody
	public ResponseMsg editDelivery(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			String subOrderId = request.getParameter("subOrderId");
			String jsonStr = request.getParameter("jsonStr");
			SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
			CustomerServiceOrderExample csoe = new CustomerServiceOrderExample();
			csoe.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrder.getId()).andStatusEqualTo("0");
			List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(csoe);
			if(customerServiceOrders!=null && customerServiceOrders.size()>0){
				return new ResponseMsg(ResponseMsg.ERROR, "用户已发起售后，无法发货，请刷新页面");
			}
			JSONArray ja = JSONArray.fromObject(jsonStr);
			for(int i=0;i<ja.size();i++){
				JSONObject jo = (JSONObject)ja.get(i);
				String eachExpressId = jo.getString("expressId").trim();
				String eachExpressNo = jo.getString("expressNo").trim();
				Boolean flag = subOrderService.verifyExpressNo(eachExpressNo, Integer.parseInt(eachExpressId));
				if(!flag){
					return new ResponseMsg(ResponseMsg.ERROR, "操作失败，快递单号异常",returnData);
				}
			}
			orderDtlService.editDelivery(ja,Integer.parseInt(subOrderId),this.getSessionUserInfo(request).getId());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "操作失败，请稍后重试",returnData);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 根据明细快递信息获取物流
	 * @param request
	 * @return
	 */
	@RequestMapping("/subOrder/getWuliuByOrderDtlId")
	@ResponseBody
	public ResponseMsg getWuliuByOrderDtlId(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String orderDtlId = request.getParameter("orderDtlId");
		OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(Integer.parseInt(orderDtlId));
		KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
		kdnWuliuInfoExample.setOrderByClause("id desc");
		KdnWuliuInfoExample.Criteria kdnWuliuInfoCriteria = kdnWuliuInfoExample.createCriteria();
		kdnWuliuInfoCriteria.andDelFlagEqualTo("0");
		kdnWuliuInfoCriteria.andExpressIdEqualTo(orderDtl.getDtlExpressId());
		kdnWuliuInfoCriteria.andLogisticCodeEqualTo(orderDtl.getDtlExpressNo());
		List<KdnWuliuInfo> kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
		if(kdnWuliuInfos!=null && kdnWuliuInfos.size()>0){
			if(!StringUtil.isEmpty(kdnWuliuInfos.get(0).getTractInfo())){
				JSONArray wuliuInfos = JSONArray.fromObject(kdnWuliuInfos.get(0).getTractInfo());
				returnData.put("hasWuliu", true);
				returnData.put("wuliuInfos", wuliuInfos);
			}else{
				returnData.put("hasWuliu", false);
			}
		}else{
			returnData.put("hasWuliu", false);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}
	
}
