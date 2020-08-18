
package com.jf.controller;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jf.common.AppContext;
import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.*;
import com.jf.controller.base.BaseController;
import com.jf.dao.ExpressMapper;
import com.jf.entity.*;
import com.jf.entity.dto.TactInfoModel;
import com.jf.service.*;
import com.jf.vo.request.ChangeOrderAddressRequest;
import com.jf.vo.request.GetOrderAddressPageInfoRequest;
import com.jf.vo.response.GetOrderAddressPageInfoResponse;
import com.jf.vo.response.OrderProductView;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

/**
  * 订单明细
  * @author  chenwf:
  * @date 创建时间：2017年4月22日 下午5:06:49
  * @version 1.0
  * @parameter
  * @return
*/
@Controller
public class OrderDtlController extends BaseController{

	@Resource
	private OrderDtlService orderDtlService;

	@Resource
	private CombineOrderService combineOrderService;

	@Resource
	private SubOrderService subOrderService;

	@Resource
	private CustomerServiceOrderService customerServiceOrderService;

	@Resource
	private CustomerServicePicService customerServicePicService;

	@Resource
	private CustomerServiceStatusLogService customerServiceStatusLogService;

	@Resource
	private CustomerServiceLogService customerServiceLogService;

	@Resource
	private ServiceLogPicService serviceLogPicService;

	@Resource
	private AppealOrderService appealOrderService;

	@Resource
	private AppealLogPicService appealLogPicService;

	@Resource
	private AppealLogService appealLogService;

	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private KdnWuliuInfoService kdnWuliuInfoService;
	@Resource
	private PlatformMsgService platformMsgService;
	@Resource
	private MsgTplService msgTplService;
	@Resource
	private InterventionOrderService interventionOrderService;
	@Resource
	private SobotCustomerServiceService sobotCustomerServiceService;
	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private CommentService commentService;
	@Resource
	private ButtonService buttonService;
	@Resource
	private EnumsNameService enumsNameService;
	@Autowired
	private SubDepositOrderService subDepositOrderService;
	@Autowired
	private CloudProductAfterTempletService cloudProductAfterTempletService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private TaskSuspendService taskSuspendService;
	@Autowired
	private ExpressMapper expressMapper;
	@Autowired
	private SvipOrderService svipOrderService;
	@Autowired
	private SysParamCfgService sysParamCfgService;
	@Autowired
	private AppContext appContext;
	@Autowired
	private MemberAddressService memberAddressService;

	@Autowired
	private MemberCollegeStudentCertificationService memberCollegeStudentCertificationService;

	/**
	 *
	 * 方法描述 ：获取订单明细列表
	 * @author  chenwf:
	 * @date 创建时间：2017年4月22日 下午5:11:23
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getOrderDtlList20180611")
	@ResponseBody
	public ResponseMsg getOrderDtlList20180611(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","pageSize","currentPage"};
			this.requiredStr(obj,request);
			Map<String,Object> map = new HashMap<String,Object>();
			Integer version = reqPRM.getInt("version");
			String system = reqPRM.getString("system");
			Date currentDate = new Date();
			String memberId = reqDataJson.getString("memberId");//会员标识id
			//子订单状态0 待付款1 待发货2 待收货3 完成4 取消(未付款客户取消,平台取消)5 关闭(退款后关闭)
			//订单明细状态1.完成 2.退款 3.退货
			String subOrderStatus = reqDataJson.getString("subOrderStatus");
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = reqDataJson.getInt("currentPage");//页码
			Integer taotalPage = 0;
			Map<String,Object> params = new HashMap<String,Object>();
			List<String> statusList = new ArrayList<String>();
			String subStatus = subOrderStatus;
			int subDepositOrderCount = 0;
			//待付款 或 全部
			if( !"1".equals(subOrderStatus) && !"2".equals(subOrderStatus) && !"3".equals(subOrderStatus)) {
				//预售总定金订单数
				SubDepositOrderCustomExample subDepositOrderCustomExample = new SubDepositOrderCustomExample();
				subDepositOrderCustomExample.createCriteria().andDelFlagEqualTo("0")
					.andMemberIdEqualTo(Integer.parseInt(memberId))
					.andStatusIn(Arrays.asList("1", "2", "8"));
				List<SubDepositOrderCustom> subDepositOrderList = subDepositOrderService.selectGroupByCombineDepositOrder(subDepositOrderCustomExample);
				if(subDepositOrderList != null && subDepositOrderList.size() > 0) {
					subDepositOrderCount = subDepositOrderList.size();
				}
			}
			if(StringUtil.isBlank(subOrderStatus)){
				statusList.add("0");
				statusList.add("1");
				statusList.add("2");
				statusList.add("3");
				statusList.add("4");
				statusList.add("5");
				statusList.add("6");
			}else{
				if(subOrderStatus.equals("3")){
					//待评价 is_comment 是否评论 0.否 1.是
					params.put("isComment", "0");
				}else{
					statusList.add(subOrderStatus);
				}
			}
			//该字段用来判断是否要执行  查询总订单的数据 空或为0需要执行
			params.put("subStatus", subStatus);
			params.put("subOrderStatus", statusList);
			params.put("memberId", Integer.valueOf(memberId));
			if((system.equals(Const.ANDROID) && version < 43) || (system.equals(Const.IOS) && version < 46)){
				List<OrderDtlCustom> countSize = orderDtlService.getOrderDtlList(params);
				Page page = new Page(currentPage, pageSize, countSize.size());
				taotalPage = page.getTotalPage();
			}
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			List<OrderDtlCustom> orderDtlCustomList = orderDtlService.getOrderDtlList(params);
			for (OrderDtlCustom odc : orderDtlCustomList) {
				//需要大学生认证
				boolean collegeStudentStatus = false;
				String collegeStudentUrl = "";
				subOrderStatus = odc.getStatus().toString();
				String isComment = odc.getIsComment() == null ? "" : odc.getIsComment();
				List<Map<String,Object>> dataMapList = new ArrayList<Map<String,Object>>();//封装订单数据集合
				List<OrderDtlCustom> orderDtlCustoms = null;
				if(odc.getStatus().toString().equals("0") || odc.getStatus().toString().equals("4")){
					params.put("combineOrderCode", odc.getCombineOrderCode());
					params.put("subOrderCode","");
					orderDtlCustoms = orderDtlService.getOrderProductInfoList(params);
				}else{
					params.put("subOrderCode", odc.getSubOrderCode());
					params.put("combineOrderCode","");
					orderDtlCustoms = orderDtlService.getOrderProductInfoList(params);
				}
				boolean seeLogisticsButton = false;//查看物流按钮
				Integer quantity = 0;
				for (OrderDtlCustom orderDtlCustom : orderDtlCustoms) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					String skuPic = orderDtlCustom.getSkuPic();
					String isGive = orderDtlCustom.getIsGive();
					String dtlExpressNo = orderDtlCustom.getDtlExpressNo();
					if("1".equals(isGive)){
						skuPic = StringUtil.getPic(orderDtlCustom.getSkuPic(), "");
					}else{
						skuPic = StringUtil.getPic(orderDtlCustom.getSkuPic(), "S");
					}
					if(!StringUtil.isBlank(dtlExpressNo)){
						seeLogisticsButton = true;
					}
					dataMap.put("productName", orderDtlCustom.getProductName());//商品描述
					dataMap.put("productItemId", orderDtlCustom.getId());//商品Id
					dataMap.put("productPropDesc", orderDtlCustom.getProductPropDesc());//规格
					dataMap.put("salePrice", orderDtlCustom.getSalePrice());//销售价格
					dataMap.put("skuPic", skuPic);//sku图片
					dataMap.put("productStatus", orderDtlCustom.getProductStatus());//退货状态
					dataMap.put("quantity", orderDtlCustom.getQuantity());//数量
					dataMap.put("combineOrderId", orderDtlCustom.getCombineOrderId());//总订单id
					dataMap.put("subOrderId", orderDtlCustom.getSubOrderId());//子订单id
					dataMap.put("orderDtlId", orderDtlCustom.getId());//订单明细id
					quantity += orderDtlCustom.getQuantity();
					dataMapList.add(dataMap);
				}
				SvipOrder svipOrder = null;
				// 未付款单 含有svip营销绑定，要在明细尾部追加一个svip虚拟商品
				if (subOrderStatus.equals("0") && odc.getSvipOrderId() != null) {
					svipOrder = svipOrderService.selectByPrimaryKey(odc.getSvipOrderId());
					Map<String, Object> virtualSvip = Maps.newHashMap();
					virtualSvip.put("productName", StringUtil.buildMsg("醒购SVIP年卡{}年", StringUtil.toCNNumber(svipOrder.getYearsOfPurchase())));//商品描述
					virtualSvip.put("productItemId", 0);//商品Id
					virtualSvip.put("productPropDesc", "");//规格
					virtualSvip.put("salePrice", svipOrder.getPayAmount());//销售价格
					virtualSvip.put("skuPic", StringUtil.getPic(sysParamCfgService.findValByCode("SVIP_MARKETING_PRODUCT_IMG"),""));//sku图片
					virtualSvip.put("quantity", 1);//数量
					virtualSvip.put("combineOrderId", odc.getCombineOrderId());//总订单id
					virtualSvip.put("subOrderId", 0);//子订单id
					virtualSvip.put("orderDtlId", 0);//订单明细id
					dataMapList.add(virtualSvip);
				}
				Map<String,Object> codeMap = new HashMap<String,Object>();
				String payAmount = "";
				long unpaidBeginTime = odc.getCreateDate().getTime();
				long unpaidEndTime = DateUtil.addMinute(odc.getCreateDate(), 4 * 60).getTime();
				String unpaidTimeType = "3"; //1ss 2mm:ss 3HH:mm:ss
				long unpaidDiffSeconds  = unpaidEndTime-DateUtil.getDate().getTime();
				String createDateStr = "00:00";
				if((system.equals(Const.ANDROID) && version < 44) || (system.equals(Const.IOS) && version < 47)){
					if (unpaidDiffSeconds > 0 && subOrderStatus.equals("0")) {
						long mm = unpaidDiffSeconds / (60 * 1000);
						long ss = (unpaidDiffSeconds - (mm * 60 * 1000)) / 1000;
						createDateStr = mm + ":" + ss;
					}
				}
				if(!odc.getCombineOrderStatus().equals(Const.COMBINE_ORDER_STATUS_PAID)){
					payAmount = odc.getCombinePayAmount().toString();//支付价格
				}else{
					payAmount = odc.getSubPayAmount().toString();//支付价格
				}
				if((system.equals(Const.ANDROID) && version < 53) || (system.equals(Const.IOS) && version < 56)){
					seeLogisticsButton = buttonService.getSeeLogisticsButton(null,subOrderStatus,"1");
				}else if(seeLogisticsButton){
					seeLogisticsButton = buttonService.getSeeLogisticsButton(null,subOrderStatus,"1");
				}
				//评价按钮
				Map<String, Object> evaluateButtonMap = buttonService.getEvaluateButtonButton(odc.getSubOrderId().toString(),dataMapList.size(),isComment,subOrderStatus);
				boolean evaluateButton = (boolean) evaluateButtonMap.get("button");
				//查看评价按钮
				boolean seeEvaluateButton = false;
				if("1".equals(isComment)){
					seeEvaluateButton = true;
				}
				//确认收货按钮
				Map<String, Object> receiptButtonMap = buttonService.getReceiptButton(subOrderStatus, odc.getSubOrderId().toString());
				boolean receiptMark = (boolean) receiptButtonMap.get("button");
				//删除按钮
				Map<String, Object> delButtonMap = buttonService.getDelButton(subOrderStatus);
				boolean delButton = (boolean) delButtonMap.get("button");
				//提醒发货按钮
				Map<String, Object> remindDeliveryButtonMap = buttonService.getRemindDeliveryButton(subOrderStatus,null);
				boolean remindDeliveryButton = (boolean) remindDeliveryButtonMap.get("button");
				String subOrderStatusName = enumsNameService.getSubOrderStatusName(subOrderStatus,isComment);
				String orderCode = "";
				if(subOrderStatus.equals(Const.ORDER_STATUS_NOT_PAID) || subOrderStatus.equals(Const.ORDER_STATUS_CANCEL)){
					orderCode = odc.getCombineOrderCode();
				}else{
					orderCode = odc.getSubOrderCode();
				}
				if((system.equals(Const.ANDROID) && version < 43) || (system.equals(Const.IOS) && version < 46)){
					if(subOrderStatus.equals(Const.ORDER_STATUS_RECEIVED_GOODS)){
						subOrderStatus = "2";
					}
				}

				if (svipOrder != null) {
					payAmount = new BigDecimal(payAmount).add(svipOrder.getPayAmount()).toString();
				}

				codeMap.put("unpaidBeginTime", unpaidBeginTime);
				codeMap.put("unpaidEndTime", unpaidEndTime);
				codeMap.put("currentTime", currentDate.getTime());
				codeMap.put("unpaidTimeType", unpaidTimeType);
				codeMap.put("remindDeliveryButton", remindDeliveryButton);
				codeMap.put("delButton", delButton);
				codeMap.put("payAmount", payAmount);//支付价格
				codeMap.put("createDate", createDateStr);
				codeMap.put("productNum", quantity);//商品数
				codeMap.put("seeLogisticsButton",seeLogisticsButton);
				codeMap.put("subOrderStatusName",subOrderStatusName);//子订单状态名称
				codeMap.put("subOrderStatus",subOrderStatus);//子订单状态
				if("0".equals(subOrderStatus)) {
					if("4".equals(odc.getAuditStatus())) {
						collegeStudentStatus = true;
						collegeStudentUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/certification/index.html";
					}else if("2".equals(odc.getAuditStatus())) {
						MemberCollegeStudentCertificationExample cscExample = new MemberCollegeStudentCertificationExample();
						cscExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(Integer.valueOf(memberId)).andStatusNotEqualTo("3");
						cscExample.setLimitStart(0);
						cscExample.setLimitSize(1);
						List<MemberCollegeStudentCertification> memberCollegeStudentCertification = memberCollegeStudentCertificationService.selectByExample(cscExample);
						if(memberCollegeStudentCertification.size() == 0) {
							collegeStudentStatus = true;
							collegeStudentUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/certification/index.html";
						}
					}
					codeMap.put("auditStatusDesc","");//子订单审核状态名称
				}else if("1".equals(subOrderStatus)) {
					if("4".equals(odc.getAuditStatus())) {
						codeMap.put("auditStatusDesc","(不通过)");//子订单审核状态名称
					}else if("3".equals(odc.getAuditStatus())) {
						codeMap.put("auditStatusDesc","(通过)");//子订单审核状态名称
					}else if("2".equals(odc.getAuditStatus())) {
						codeMap.put("auditStatusDesc","(待审核)");//子订单审核状态名称
					}
				}else {
					codeMap.put("auditStatusDesc","");//子订单审核状态名称
				}
				codeMap.put("collegeStudentStatus", collegeStudentStatus);
				codeMap.put("collegeStudentUrl", collegeStudentUrl);
				codeMap.put("subOrderId", odc.getSubOrderId());//子订单id
				codeMap.put("combineOrderId", odc.getCombineOrderId());//总订单id
				codeMap.put("expressId", odc.getExpressId());//物流id
				codeMap.put("expressNo", odc.getExpressNo());//物流编号
				codeMap.put("tatalPage", taotalPage);
				codeMap.put("receiptMark", receiptMark);//确认收货标识
				codeMap.put("evaluateButton", evaluateButton);//评价按钮
				codeMap.put("seeEvaluateButton", seeEvaluateButton);//查看评价按钮
				codeMap.put("combineOrderCode", orderCode);//总订单编号
				codeMap.put("subOrderCode", orderCode);//子订单编号
				codeMap.put("data", dataMapList);
				returnData.add(codeMap);
			}
			SysParamCfg sysParamCfg = DataDicUtil.getSysParamCfgModel("APP_ANTI_SPOOFING_PIC","");
			map.put("dataList", returnData);
			map.put("spoofingPic", StringUtil.getPic(sysParamCfg.getParamValue(), ""));
			map.put("subDepositOrderCount", subDepositOrderCount);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}


	/**
	 *
	 * 方法描述 ：获取商品订单详情列表
	 * @author  chenwf:
	 * @date 创建时间：2017年4月22日 下午5:11:23
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getOrderDtlInfoList20170720")
	@ResponseBody
	public ResponseMsg getOrderDtlInfoList20170720(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId"};
			this.requiredStr(obj,request);

			String memberId = reqDataJson.getString("memberId");//会员标识id
			String combineOrderId = reqDataJson.getString("combineOrderId");//总订单id
			String subOrderId = reqDataJson.getString("subOrderId");//总订单id
			BigDecimal totalFreight = new BigDecimal("0");//运费
			Integer version = reqPRM.getInt("version");
			String system = reqPRM.getString("system");
			Integer mchtId = null;
			Date currentDate = new Date();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("memberId", memberId);
			params.put("combineOrderId", combineOrderId);
			params.put("subOrderId", subOrderId);

			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();

			List<OrderDtlCustom> orderCodeList = orderDtlService.getOrderCode(params);
			List<String> specMchtCodeList = commonService.listSpecMchtCode();
			Integer giftIntegralNum = 0;
			for (OrderDtlCustom subOrder : orderCodeList) {
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(subOrder.getMchtId());
				if(!specMchtCodeList.contains(mchtInfo.getMchtCode())){
					giftIntegralNum += subOrder.getSubPayAmount().intValue();
				}
			}
			String remarks = "";
			String subOrderStatus = "";
			BigDecimal zero = new BigDecimal("0");
			//需要大学生认证
			boolean collegeStudentStatus = false;
			String collegeStudentUrl = "";
			String auditStatusDesc = "";
			for (OrderDtlCustom ods : orderCodeList) {
				if("0".equals(ods.getStatus().toString())) {
					if("4".equals(ods.getAuditStatus())) {
						collegeStudentStatus = true;
						collegeStudentUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/certification/index.html";
					}else if("2".equals(ods.getAuditStatus())) {
						MemberCollegeStudentCertificationExample cscExample = new MemberCollegeStudentCertificationExample();
						cscExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(Integer.valueOf(memberId)).andStatusNotEqualTo("3");
						cscExample.setLimitStart(0);
						cscExample.setLimitSize(1);
						List<MemberCollegeStudentCertification> memberCollegeStudentCertification = memberCollegeStudentCertificationService.selectByExample(cscExample);
						if(memberCollegeStudentCertification.size() == 0) {
							collegeStudentStatus = true;
							collegeStudentUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/certification/index.html";
						}
					}
					auditStatusDesc = "";
				}else if("1".equals(ods.getStatus().toString())) {
					if("4".equals(ods.getAuditStatus())) {
						auditStatusDesc = "(不通过)";
					}else if("3".equals(ods.getAuditStatus())) {
						auditStatusDesc = "(通过)";
					}else if("2".equals(ods.getAuditStatus())) {
						auditStatusDesc = "(待审核)";
					}
				}else {
					auditStatusDesc = "";
				}
				Date createDate = ods.getCreateDate();//订单创建时间
				Date payDate = ods.getPayDate();//订单支付时间
				Date deliveryDate = ods.getDeliveryDate();//订单发货时间
				Date completeDate = ods.getCompleteDate();//完成时间
				Date receiptDate = ods.getReceiptDate();//收货时间
				Date cancelDate = ods.getCancelDate();//取消时间
				Date closeDate = ods.getCloseDate();//订单关闭时间
				Date deliveryOrderDtlDate = null;
				boolean isViewLogisticsContent = true;
				boolean seeLogisticsButton = false;//查看物流按钮
				boolean automaticReceiptMark = true;
				List<OrderDtlCustom> orderDtlCustomList = orderDtlService.getOrderDtlInfoList(params);
				Map<String,Object> codeMap = new HashMap<String,Object>();//封装订单编码map
				Map<String,Object> dataMap = new HashMap<String,Object>();//封装订单数据map
				List<Map<String,Object>> dataMapList = new ArrayList<Map<String,Object>>();//封装订单数据集合
				for (OrderDtlCustom orderDtlCustom : orderDtlCustomList) {
					String markedOutOfStock = orderDtlCustom.getMarkedOutOfStock();//是否缺货0否 1是
					String deliveryStatus = orderDtlCustom.getDeliveryStatus();//是否已发货0否 1是
					String dtlExpressNo = orderDtlCustom.getDtlExpressNo();
					String saleType = orderDtlCustom.getSaleType();
					String orderType = orderDtlCustom.getOrderType() == null ? "" : orderDtlCustom.getOrderType();
					BigDecimal freight = orderDtlCustom.getFreight() == null ? zero : orderDtlCustom.getFreight();
					totalFreight = freight.add(totalFreight);
					subOrderStatus = orderDtlCustom.getStatus().toString();
					remarks = orderDtlCustom.getRemarks();
					mchtId = orderDtlCustom.getMchtId();
					String skuPic = orderDtlCustom.getSkuPic();
					String isGive = orderDtlCustom.getIsGive();
					String productStatus = orderDtlCustom.getProductStatus() == null ? "" : orderDtlCustom.getProductStatus();
					if("1".equals(isGive)){
						skuPic = StringUtil.getPic(orderDtlCustom.getSkuPic(), "");
					}else{
						skuPic = StringUtil.getPic(orderDtlCustom.getSkuPic(), "S");
					}
					if(subOrderStatus.equals(Const.ORDER_STATUS_NOT_PAID) || subOrderStatus.equals(Const.ORDER_STATUS_SUCCESS)
							|| subOrderStatus.equals(Const.ORDER_STATUS_CANCEL) || subOrderStatus.equals(Const.ORDER_STATUS_CLOSE)){
						markedOutOfStock = "0";
					}
					if(subOrderStatus.equals(Const.ORDER_STATUS_NOT_PAID) || subOrderStatus.equals(Const.ORDER_STATUS_SUCCESS)
							|| subOrderStatus.equals(Const.ORDER_STATUS_CANCEL) || subOrderStatus.equals(Const.ORDER_STATUS_CLOSE)
							|| subOrderStatus.equals(Const.ORDER_STATUS_PAID)){
						deliveryStatus = "1";
					}
					if(("1".equals(markedOutOfStock) || "0".equals(deliveryStatus)) && !"2".equals(productStatus) && !"3".equals(productStatus)){
						automaticReceiptMark = false;
					}else{
						Date deliveryDtlDate = orderDtlCustom.getDeliveryDate();
						if(deliveryDtlDate != null){
							if(deliveryOrderDtlDate != null){
								if(deliveryOrderDtlDate.before(deliveryDtlDate)){
									deliveryOrderDtlDate = deliveryDtlDate;
								}
							}else{
								deliveryOrderDtlDate = deliveryDtlDate;
							}
						}
					}
					if(!StringUtil.isBlank(dtlExpressNo)){
						seeLogisticsButton = true;
					}
					String isComment = ods.getIsComment();
					String customerServiceOrderStatus = "";
					//追加评价按钮
					boolean appendCommentButton = false;
					String activityType = "";
					if(orderDtlCustom.getSingleProductActivityId() != null){
						SingleProductActivity singleProductActivity = singleProductActivityService.selectByPrimaryKey(orderDtlCustom.getSingleProductActivityId());
						activityType = singleProductActivity.getType();
					}
					dataMap = new HashMap<String,Object>();
					//砍价与邀请享免单显示吊牌价
					BigDecimal salePrice = orderDtlCustom.getSalePrice();
					if(activityType.equals("7") || activityType.equals("8")){
						salePrice = orderDtlCustom.getTagPrice();
					}
					if("5".equals(orderType)){
						saleType = "";
					}
					//获取订单预售金额
					SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
					subDepositOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderDtlIdEqualTo(orderDtlCustom.getId());
					List<SubDepositOrder> subDepositOrderList = subDepositOrderService.selectByExample(subDepositOrderExample);
					String depositStatus = "0"; //是否为预售单 0：不是 1：是
					BigDecimal depositSum = new BigDecimal(0);
					BigDecimal deductAmountSum = new BigDecimal(0);
					if(subDepositOrderList != null && subDepositOrderList.size() > 0) {
						depositStatus = "1";
						for(SubDepositOrder subDepositOrder : subDepositOrderList) {
							depositSum = depositSum.add(subDepositOrder.getDeposit());
							deductAmountSum = deductAmountSum.add(subDepositOrder.getDeductAmount());
						}
					}
					dataMap.put("depositStatus", depositStatus);//是否为预售单 0：不是 1：是
					dataMap.put("depositSum", depositSum);//总定金
					dataMap.put("deductAmountSum", deductAmountSum);//总抵扣金
					dataMap.put("saleType",saleType);//销售类型1 品牌款 2 单品款
					dataMap.put("productName", orderDtlCustom.getProductName());//商品描述
					dataMap.put("productId", orderDtlCustom.getProductId());//商品描述
					dataMap.put("productPropDesc", orderDtlCustom.getProductPropDesc());//规格
					dataMap.put("quantity", orderDtlCustom.getQuantity());//数量
					dataMap.put("salePrice", salePrice);//销售价格
					dataMap.put("productPayAmount", orderDtlCustom.getPayAmount());//商品的实付金额
					dataMap.put("productStatus", orderDtlCustom.getProductStatus());//退货状态
					dataMap.put("productItemId", orderDtlCustom.getProductItemId());//商品Id
					dataMap.put("skuPic", skuPic);//sku图片
					dataMap.put("mchtId", orderDtlCustom.getMchtId());//商家id
					dataMap.put("orderDtlId", orderDtlCustom.getId());//订单明细id
					dataMap.put("isGive", isGive);//是否赠送0 否 1是
					dataMap.put("markedOutOfStock", markedOutOfStock);//是否缺货0 否 1是
					dataMap.put("deliveryStatus", deliveryStatus);//是否发货0.未发货1.已发货
					Integer orderDtlId = orderDtlCustom.getId();
					//查找是否有投诉单据
					AppealOrderExample appealOrderExample = new AppealOrderExample();
					appealOrderExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0").andCreateByEqualTo(Integer.valueOf(memberId));
					List<AppealOrder> appealOrders = appealOrderService.selectByExample(appealOrderExample);
					//判断创建时间>30天 || 存在投诉单据,  APP客户端投诉按钮不显示
					String isExist = "0";//0 投诉按钮不展示
					if(subOrderStatus.equals(Const.ORDER_STATUS_SUCCESS)){
						if(currentDate.after(DateUtil.addDay(completeDate, 90)) || isGive.equals("1") || CollectionUtils.isNotEmpty(appealOrders)){
							isExist = "0";
						}else{
							isExist = "1";
						}
					}

					//待发货
					String serviceOrderId = "";
					String statusName = "";
					String serviceType = "";
					String contactPhone = "";
					String reasonVal = "";
					String reason = "";
					String serviceRemarks = "";
					String isAllowMchtModify = "0";
					CustomerServiceOrder customerServiceOrder = null;
					List<InterventionOrder> interventionOrders =  null;
					List<String> picList = new ArrayList<String>();
					if(subOrderStatus.equals(Const.ORDER_STATUS_PAID)){
						List<CustomerServiceOrder> customerServiceOrderRefundList = customerServiceOrderService.findList(orderDtlId,Integer.valueOf(memberId),Const.CUSTOMER_ORDER_TYPE_A);
						if(CollectionUtils.isNotEmpty(customerServiceOrderRefundList)){
							customerServiceOrder = customerServiceOrderRefundList.get(0);
							//售后状态 0.进行中 1已完成 2已拒绝 3已撤销
							customerServiceOrderStatus = customerServiceOrder.getStatus();
							isAllowMchtModify = customerServiceOrder.getIsAllowMchtModify();
							serviceOrderId = customerServiceOrder.getId().toString();
							serviceType = customerServiceOrder.getServiceType();
							contactPhone = customerServiceOrder.getContactPhone();
							reasonVal = customerServiceOrder.getReason();
							String reasonStr = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", customerServiceOrder.getReason());
							reason = reasonStr;
							serviceRemarks = customerServiceOrder.getRemarks();
							List<CustomerServicePic> customerServicePics = customerServicePicService.findListByServiceOrderId(customerServiceOrder.getId(),Integer.valueOf(memberId));
							if(CollectionUtils.isNotEmpty(customerServicePics)){
								for (CustomerServicePic customerServicePic : customerServicePics) {
									String pic = customerServicePic.getPic();
									if(!StringUtil.isBlank(pic)){
										pic = FileUtil.getImageServiceUrl()+pic;
										picList.add(pic);
									}
								}
							}
						}
					}else if(subOrderStatus.equals(Const.ORDER_STATUS_SHIPPED) || subOrderStatus.equals(Const.ORDER_STATUS_RECEIVED_GOODS)){
						List<CustomerServiceOrder> customerServiceOrderRefundList = customerServiceOrderService.findList(orderDtlId,Integer.valueOf(memberId),"");
						if(CollectionUtils.isNotEmpty(customerServiceOrderRefundList)){
							for (CustomerServiceOrder cso : customerServiceOrderRefundList) {
								System.out.println(cso.getServiceType());
								System.out.println(cso.getStatus());

								if(cso.getServiceType().equals(Const.CUSTOMER_ORDER_TYPE_B) || cso.getServiceType().equals(Const.CUSTOMER_ORDER_TYPE_C)){
									customerServiceOrder = cso;
									continue;
								}else if(cso.getServiceType().equals(Const.CUSTOMER_ORDER_TYPE_A) && (cso.getStatus().equals(Const.CUSTOMER_ORDER_STATUS_REFUNDING) || cso.getStatus().equals(Const.CUSTOMER_ORDER_STATUS_SUCCESS))){
									customerServiceOrder = cso;
									continue;
								}
							}
						}
						if(customerServiceOrder != null){
							isAllowMchtModify = customerServiceOrder.getIsAllowMchtModify();
							//查询是否存在介入单，存在介入单且未结案的不能重新申请
							//查看下是否有平台介入
							InterventionOrderExample interventionOrderExample = new InterventionOrderExample();
							interventionOrderExample.createCriteria().andServiceOrderIdEqualTo(customerServiceOrder.getId()).andStatusNotEqualTo("8").andDelFlagEqualTo("0");
							interventionOrderExample.setOrderByClause("id desc");
							interventionOrders = interventionOrderService.selectByExample(interventionOrderExample);
							//售后状态 0.进行中 1已完成 2已拒绝 3已撤销
							customerServiceOrderStatus = customerServiceOrder.getStatus();
							serviceOrderId = customerServiceOrder.getId().toString();
							serviceType = customerServiceOrder.getServiceType();
							contactPhone = customerServiceOrder.getContactPhone();
							reasonVal = customerServiceOrder.getReason();
							String reasonStr = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", customerServiceOrder.getReason());
							reason = reasonStr;
							serviceRemarks = customerServiceOrder.getRemarks();
							List<CustomerServicePic> customerServicePics = customerServicePicService.findListByServiceOrderId(customerServiceOrder.getId(),Integer.valueOf(memberId));
							if(CollectionUtils.isNotEmpty(customerServicePics)){
								for (CustomerServicePic customerServicePic : customerServicePics) {
									String pic = customerServicePic.getPic();
									if(!StringUtil.isBlank(pic)){
										pic = FileUtil.getImageServiceUrl()+pic;
										picList.add(pic);
									}
								}
							}
						}
					}else if(subOrderStatus.equals(Const.ORDER_STATUS_SUCCESS)){
						List<CustomerServiceOrder> customerServiceOrderRefundList = customerServiceOrderService.findList(orderDtlId,Integer.valueOf(memberId),"AB");
					}else if(subOrderStatus.equals(Const.ORDER_STATUS_CLOSE)){
						List<CustomerServiceOrder> customerServiceOrderRefundList = customerServiceOrderService.findList(orderDtlId,Integer.valueOf(memberId),"");
						if(CollectionUtils.isNotEmpty(customerServiceOrderRefundList)){
							customerServiceOrder = customerServiceOrderRefundList.get(0);
							customerServiceOrderStatus = customerServiceOrder.getStatus();
							serviceOrderId = customerServiceOrder.getId().toString();
						}
					}
					if(!StringUtil.isBlank(customerServiceOrderStatus)){
						statusName = enumsNameService.getCustomerServiceOrderStatusName(customerServiceOrderStatus,isGive,customerServiceOrder.getServiceType(),subOrderStatus,system,version);
					}
					dataMap.put("serviceOrderId", serviceOrderId);
					dataMap.put("serviceType", serviceType);
					dataMap.put("contactPhone", contactPhone);
					dataMap.put("reason", reason);
					dataMap.put("reasonVal", reasonVal);
					dataMap.put("remarks", serviceRemarks);
					dataMap.put("picList", picList);
					dataMap.put("statusName", statusName);
					dataMap.put("customerServiceOrderStatus", customerServiceOrderStatus);
					dataMap.put("isAllowMchtModify", isAllowMchtModify);//是否允许商家换货改退款(0不允许 1允许)
					if(isGive.equals("1") || activityType.equals("7") || activityType.equals("8")){
						isExist = "0";
					}
					if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT)){
						isViewLogisticsContent = false;
					}
					boolean refundMarkAgain1 = false;//重新退款按钮
					boolean afterSalesApplyAgain1 = false;//重新售后申请按钮
					boolean customerServiceInfoButton = false;//售后跳转到售后详情按钮
					//退款按钮
					Map<String, Object> refundMarkButtonMap = buttonService.getRefundMarkButton(subOrderStatus, customerServiceOrderStatus, isGive,activityType);
					boolean refundMark1 = (boolean) refundMarkButtonMap.get("button");
					//售后申请按钮
					Map<String, Object> afterSalesApplyButtonMap = buttonService.getAfterSalesApplyButton(subOrderStatus, customerServiceOrderStatus, isGive,activityType,productStatus);
					boolean afterSalesApply1 = (boolean) afterSalesApplyButtonMap.get("button");
					if((system.equals(Const.ANDROID) && version <= 49) || (system.equals(Const.IOS) && version <= 52) || "3".equals(customerServiceOrderStatus)){
						Map<String, Object> refundMarkAgainButtonMap = buttonService.getRefundMarkAgainButton(subOrderStatus, customerServiceOrderStatus, isGive,activityType);
						refundMarkAgain1 = (boolean) refundMarkAgainButtonMap.get("button");
						Map<String, Object> afterSalesApplyAgainButtonMap = buttonService.getAfterSalesApplyAgainButton(subOrderStatus, customerServiceOrderStatus, isGive,activityType,interventionOrders,serviceType,productStatus);
						afterSalesApplyAgain1 = (boolean) afterSalesApplyAgainButtonMap.get("button");
					}else{
						if("0".equals(customerServiceOrderStatus) || "1".equals(customerServiceOrderStatus) || "2".equals(customerServiceOrderStatus)){
							customerServiceInfoButton = true;
						}
					}

					if("1".equals(isComment) && receiptDate != null && !subOrderStatus.equals(Const.ORDER_STATUS_CLOSE)
							&& DateUtil.addDay(receiptDate, 30).compareTo(currentDate) >= 0){
						//子订单已完成|已评价过|确认收货30内才可以追评
						//再次判断是否追评过，已追评不能在追评
						CommentExample commentExample = new CommentExample();
						commentExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andIsAppendCommentEqualTo("1").andDelFlagEqualTo("0");
						int appendCommentCount = commentService.countByExample(commentExample);
						if(appendCommentCount == 0){
							appendCommentButton = true;
						}
					}
					dataMap.put("serviceOrderId", serviceOrderId);
					dataMap.put("isExist", isExist);
					dataMap.put("refundMark", refundMark1);
					dataMap.put("refundMarkAgain", refundMarkAgain1);
					dataMap.put("afterSalesApply", afterSalesApply1);
					dataMap.put("afterSalesApplyAgain", afterSalesApplyAgain1);
					dataMap.put("appendCommentButton", appendCommentButton);
					dataMap.put("customerServiceInfoButton", customerServiceInfoButton);
					dataMapList.add(dataMap);
				}

				SvipOrder svipOrder = null;
				// 待支付订单，且svip营销绑定，尾部追加svip虚拟商品信息
				if (subOrderStatus.equals("0") && ods.getSvipOrderId() != null) {
					svipOrder = svipOrderService.selectByPrimaryKey(ods.getSvipOrderId());

					Map<String, Object> virtualSvip = Maps.newHashMap();
					virtualSvip.put("productName", StringUtil.buildMsg("醒购SVIP年卡{}年", StringUtil.toCNNumber(svipOrder.getYearsOfPurchase())));//商品描述
					virtualSvip.put("productItemId", 0);
					virtualSvip.put("productId", 0);
					virtualSvip.put("productPropDesc", "");//规格
					virtualSvip.put("salePrice", svipOrder.getPayAmount());//销售价格
					virtualSvip.put("productPayAmount", svipOrder.getPayAmount());//商品的实付金额
					virtualSvip.put("skuPic", StringUtil.getPic(sysParamCfgService.findValByCode("SVIP_MARKETING_PRODUCT_IMG"), ""));//sku图片
					virtualSvip.put("quantity", 1);//数量
					virtualSvip.put("combineOrderId", ods.getCombineOrderId());//总订单id
					virtualSvip.put("depositStatus", "0");//是否为预售单 0：不是 1：是
					virtualSvip.put("isGive", "0");//是否赠送0 否 1是
					virtualSvip.put("markedOutOfStock", "0");//是否缺货0 否 1是
					virtualSvip.put("isExist", "0");
					virtualSvip.put("appendCommentButton", false);

					virtualSvip.put("svipMarketing", "2"); //svip营销绑定：1、绑定的商品 2、svip虚拟商品
					virtualSvip.put("svipMarketingTip", "（SVIP年卡购买后不支持退款）");
					dataMapList.add(virtualSvip);
				}

				String expressId = ods.getExpressId();
				String expressNo = ods.getExpressNo();
				String tractInfo = "";
				String status = "";
				String isComment = ods.getIsComment() == null ? "" : ods.getIsComment();
				String receiverAddress = ods.getReceiverAddress();//详细地址 省+市+区+地址
				long unpaidBeginTime = ods.getCreateDate().getTime();
				long unpaidEndTime = DateUtil.addMinute(ods.getCreateDate(), 4 * 60).getTime();
				String unpaidTimeType = "3"; //1ss 2mm:ss 3HH:mm:ss 其他的类型没有去付款按钮
				long unpaidDiffSeconds = unpaidEndTime-DateUtil.getDate().getTime();//订单创建时间
				String createDateStr = ":";//未支付订单倒计时1小时时间
				String subDateStr = "";//订单提交时间
				String cancelDateStr = "";//订单取消时间
				String receiptDateStr = "";//订单收货时间
				String payDateStr = "";//订单支付时间
				String completeDateStr = "";//订单完成时间
				String deliveryDateStr = "";//订单发货时间
				String acceptTime = "";//订单发货时间
				String closeDateStr = "";//订单关闭时间
				if((system.equals(Const.ANDROID) && version < 44) || (system.equals(Const.IOS) && version < 47)){
					if (unpaidDiffSeconds > 0 && subOrderStatus.equals("0")) {
						long mm = unpaidDiffSeconds / (60 * 1000);
						long ss = (unpaidDiffSeconds - (mm * 60 * 1000)) / 1000;
						createDateStr = mm + ":" + ss;
					}
				}
				if(createDate != null){
					subDateStr = DateUtil.getFormatDate(createDate, "yyyy-MM-dd HH:mm:ss");
				}
				if(cancelDate != null){
					cancelDateStr = DateUtil.getFormatDate(cancelDate, "yyyy-MM-dd HH:mm:ss");
				}
				if(payDate != null){
					payDateStr = DateUtil.getFormatDate(payDate, "yyyy-MM-dd HH:mm:ss");
				}
				if(completeDate != null){
					completeDateStr = DateUtil.getFormatDate(completeDate, "yyyy-MM-dd HH:mm:ss");
				}
				if(receiptDate != null){
					receiptDateStr = DateUtil.getFormatDate(receiptDate, "yyyy-MM-dd HH:mm:ss");
				}
				if(deliveryDate != null){
					deliveryDateStr = DateUtil.getFormatDate(deliveryDate, "yyyy-MM-dd HH:mm:ss");
					acceptTime = DateUtil.getFormatDate(deliveryDate, "yyyy-MM-dd HH:mm:ss");
				}
				if(closeDate != null){
					closeDateStr = DateUtil.getFormatDate(closeDate, "yyyy-MM-dd HH:mm:ss");
				}
				List<TactInfoModel> listTacInfo = new ArrayList<TactInfoModel>();
				TactInfoModel tactInfoModel = new TactInfoModel();
				if(ods.getPayDate() != null && isViewLogisticsContent){
					Map<String,Object> kdnParams = new HashMap<String,Object>();
					kdnParams.put("expressId", expressId);
					kdnParams.put("expressNo", expressNo);
					KdnWuliuInfo kdnWuliuInfo = kdnWuliuInfoService.getExpressInfo(kdnParams);
					if(kdnWuliuInfo != null){
						tractInfo = kdnWuliuInfo.getTractInfo();
						if(!StringUtil.isBlank(tractInfo)){
							Gson gson = new Gson();
							Type listType = new TypeToken<List<TactInfoModel>>(){}.getType();
							listTacInfo = gson.fromJson(tractInfo, listType);
						}
						tactInfoModel.setAcceptStation("您的订单已导入,商家正在通知快递公司进行取件");
						tactInfoModel.setAcceptTime(acceptTime);
						listTacInfo.add(tactInfoModel);
						Collections.sort(listTacInfo, new SortClass());
						status = kdnWuliuInfo.getStatus();
					}else{
						tactInfoModel.setAcceptStation("您的订单已导入,商家正在通知快递公司进行取件");
						tactInfoModel.setAcceptTime(acceptTime);
						listTacInfo.add(tactInfoModel);
					}
				}else{
					expressId = "1";
					expressNo = "999999";
				}
				codeMap.put("receiverName", ods.getReceiverName());
				codeMap.put("receiverPhone", ods.getReceiverPhone());
//				Integer giftIntegralNum = 0;
//				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(ods.getMchtId());
//				for (OrderDtlCustom subOrder : orderCodeList) {
//					if(!specMchtCodeList.contains(mchtInfo.getMchtCode())){
//						giftIntegralNum += subOrder.getSubPayAmount().intValue();
//					}
//				}
				if(!ods.getCombineOrderStatus().equals(Const.COMBINE_ORDER_STATUS_PAID)){
					BigDecimal combinePayAmount = ods.getCombinePayAmount();
					if (subOrderStatus.equals("0") && ods.getSvipOrderId() != null) { //未支付，svip营销绑定
						codeMap.put("payAmount", combinePayAmount.add(svipOrder.getPayAmount()));//实付金额 = 母订单金额 + svip购卡金额
					}else{
						codeMap.put("payAmount", combinePayAmount.toString());//实付金额
					}
					BigDecimal discount = ods.getCombineAmount().subtract(combinePayAmount.subtract(totalFreight));//优惠金额 = 母订单商品总金额 - (母订单商品实付金额-运费)
					codeMap.put("discount", discount.toString());
					//giftIntegralNum = ods.getCombinePayAmount().intValue();
				}else{
					BigDecimal subPayAmount = ods.getSubPayAmount();
					BigDecimal discount = ods.getAmount().subtract(subPayAmount.subtract(totalFreight));//优惠金额 = 子订商品金额 - (子订商品实付金额-运费)
					codeMap.put("payAmount", subPayAmount.toString());//实付金额
					codeMap.put("discount", discount.toString());
					//giftIntegralNum = ods.getSubPayAmount().intValue();
				}
				StringBuffer confirmReceiptTimeStr = new StringBuffer("");
				if(deliveryOrderDtlDate != null && subOrderStatus.equals(Const.ORDER_STATUS_SHIPPED)){
					//还有14天23小时42分钟自动完成，收完成后不能进行申请售后
					if (!taskSuspendService.suspend("AUTO_CONFIRM_RECEIVE")) {
						List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.findListBySubOrderIdAndStatus(Integer.valueOf(subOrderId), Const.CUSTOMER_ORDER_STATUS_REFUNDING);
						Date confirmReceiptTime = DateUtil.addDay(deliveryOrderDtlDate, 15);
						long remainingTime = confirmReceiptTime.getTime() - currentDate.getTime();
						if (remainingTime > 0 && ods.getStatus().toString().equals(Const.ORDER_STATUS_SHIPPED) && CollectionUtils.isEmpty(customerServiceOrders) && automaticReceiptMark) {
							long day = 00;
							long hour = 00;
							long minute = 00;
							day = remainingTime / (60 * 60 * 24 * 1000);//换成天
							hour = (remainingTime - (60 * 60 * 24 * 1000 * day)) / (3600 * 1000);//总秒数-换算成天的秒数=剩余的秒数    剩余的秒数换算为小时
							minute = (remainingTime - 60 * 60 * 24 * 1000 * day - 3600 * 1000 * hour) / (60 * 1000);//总秒数-换算成天的秒数-换算成小时的秒数=剩余的秒数    剩余的秒数换算为分
							confirmReceiptTimeStr.append("自动确认收货时间剩余:");
							confirmReceiptTimeStr.append("<font color='red'>" + day + "</font>天");
							confirmReceiptTimeStr.append("<font color='red'>" + hour + "</font>时");
							confirmReceiptTimeStr.append("<font color='red'>" + minute + "</font>分钟");
						}
					}
				}else if(receiptDate != null && subOrderStatus.equals(Const.ORDER_STATUS_RECEIVED_GOODS)){
						//已收货，还未评价
						Date confirmReceiptTime = DateUtil.addDay(receiptDate, 7);
						long remainingTime = confirmReceiptTime.getTime() - currentDate.getTime();
						if (remainingTime > 0) {
							long day = 00;
							long hour = 00;
							long minute = 00;
							day = remainingTime / (60 * 60 * 24 * 1000);//换成天
							hour = (remainingTime - (60 * 60 * 24 * 1000 * day)) / (3600 * 1000);//总秒数-换算成天的秒数=剩余的秒数    剩余的秒数换算为小时
							minute = (remainingTime - 60 * 60 * 24 * 1000 * day - 3600 * 1000 * hour) / (60 * 1000);//总秒数-换算成天的秒数-换算成小时的秒数=剩余的秒数    剩余的秒数换算为分
							confirmReceiptTimeStr.append("申请退换货时间剩余:");
							confirmReceiptTimeStr.append("<font color='red'>" + day + "</font>天");
							confirmReceiptTimeStr.append("<font color='red'>" + hour + "</font>时");
							confirmReceiptTimeStr.append("<font color='red'>" + minute + "</font>分钟");
						}
				}
				if((system.equals(Const.ANDROID) && version < 53) || (system.equals(Const.IOS) && version < 56) || seeLogisticsButton){
					seeLogisticsButton = buttonService.getSeeLogisticsButton(deliveryDate,subOrderStatus,"2");
				}else if(seeLogisticsButton){
					seeLogisticsButton = buttonService.getSeeLogisticsButton(deliveryDate,subOrderStatus,"2");
				}
				//评价按钮
				Map<String, Object> evaluateButtonMap = buttonService.getEvaluateButtonButton(subOrderId,dataMapList.size(),isComment,subOrderStatus);
				boolean	evaluateButton = (boolean) evaluateButtonMap.get("button");
				//查看评价按钮
				boolean seeEvaluateButton = false;
				if("1".equals(isComment)){
					seeEvaluateButton = true;
				}

				//显示 修改收货地址按钮 (待付款、待发货、未审核)
				boolean seeChangeAddressButton = subOrderService.changeOrderAddressAble(ods.getCombineOrderId());

				//确认收货按钮
				Map<String, Object> buttonMap = buttonService.getReceiptButton(subOrderStatus, subOrderId);
				boolean receiptMark = (boolean) buttonMap.get("button");
				//删除按钮
				Map<String, Object> delButtonMap = buttonService.getDelButton(subOrderStatus);
				boolean delButton = (boolean) delButtonMap.get("button");
				//客服，判断版本，ios
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
				//店铺按钮
				boolean shopViewButton = false;
				if ((subOrderStatus.equals(Const.ORDER_STATUS_PAID) || subOrderStatus.equals(Const.ORDER_STATUS_SHIPPED)
						|| subOrderStatus.equals(Const.ORDER_STATUS_SUCCESS) || subOrderStatus.equals(Const.ORDER_STATUS_RECEIVED_GOODS))
						&& "1".equals(mchtInfo.getShopStatus()) && "1".equals(mchtInfo.getStatus())) {
					shopViewButton = true;
				}
				String customerServiceSoftType = sobotCustomerServiceService.getCustomerServiceSoftType(reqPRM,mchtInfo);
				SysParamCfg sysParamCfg = DataDicUtil.getSysParamCfgModel("APP_ANTI_SPOOFING_PIC","");
				String orderCode = "";
				if(subOrderStatus.equals(Const.ORDER_STATUS_NOT_PAID) || subOrderStatus.equals(Const.ORDER_STATUS_CANCEL)){
					orderCode = ods.getCombineOrderCode();
				}else{
					orderCode = ods.getSubOrderCode();
				}
				String reminderContent = "";
				if(evaluateButton || subOrderStatus.equals(Const.ORDER_STATUS_SHIPPED)){
					reminderContent = "温馨提示：前往评价，可参与抽奖活动哦~";
				}
				List<String> orderCancelReasonList = new ArrayList<String>();
				if(subOrderStatus.equals(Const.ORDER_STATUS_NOT_PAID)){
					//获取取消原因
					List<SysParamCfg> orderCancelReasons = DataDicUtil.getSysParamCfg("APP_ORDER_CANCEL_REASON");
					if(CollectionUtils.isNotEmpty(orderCancelReasons)){
						for (SysParamCfg orderCancelReason : orderCancelReasons) {
							orderCancelReasonList.add(orderCancelReason.getParamValue());
						}
					}
				}
				String subOrderStatusName = enumsNameService.getSubOrderStatusName(subOrderStatus,isComment);
				if((system.equals(Const.ANDROID) && version < 43) || (system.equals(Const.IOS) && version < 46)){
					if(subOrderStatus.equals(Const.ORDER_STATUS_RECEIVED_GOODS)){
						subOrderStatus = "2";
					}
				}
				codeMap.put("orderCancelReasonList", orderCancelReasonList);
				codeMap.put("reminderContent", reminderContent);
				codeMap.put("unpaidBeginTime", unpaidBeginTime);
				codeMap.put("unpaidEndTime", unpaidEndTime);
				codeMap.put("currentTime", currentDate.getTime());
				codeMap.put("unpaidTimeType", unpaidTimeType);
				codeMap.put("delButton", delButton);
				codeMap.put("receiptMark", receiptMark);
				codeMap.put("giftIntegralNum", giftIntegralNum);
				codeMap.put("subOrderCode", orderCode);
				codeMap.put("combineOrderCode", orderCode);
				codeMap.put("subOrderStatus", subOrderStatus);//子订单状态
				codeMap.put("subOrderStatusName", subOrderStatusName);//子订单状态名称
				codeMap.put("freight", totalFreight);//运费
				codeMap.put("combineOrderId", combineOrderId);//母订单id
				codeMap.put("subOrderId", subOrderId);//子订单id
				codeMap.put("tractInfo", listTacInfo);//物流信息
				codeMap.put("expressId", expressId);//物流id
				codeMap.put("expressNo", expressNo);//物流编号
				codeMap.put("status", status);//物流状态
				codeMap.put("receiverAddress", receiverAddress);
				codeMap.put("createDate", createDateStr);
				codeMap.put("payDate",payDateStr);
				codeMap.put("completeDate",completeDateStr);
				codeMap.put("deliveryDate",deliveryDateStr);
				codeMap.put("subDateStr",subDateStr);
				codeMap.put("cancelDateStr",cancelDateStr);
				codeMap.put("receiptDateStr",receiptDateStr);
				codeMap.put("closeDateStr",closeDateStr);
				codeMap.put("seeEvaluateButton", seeEvaluateButton);
				codeMap.put("evaluateButton", evaluateButton);
				codeMap.put("seeLogisticsButton", seeLogisticsButton);
				codeMap.put("seeChangeAddressButton", seeChangeAddressButton);
				codeMap.put("shopViewButton", shopViewButton);
				codeMap.put("remarks", remarks);
				codeMap.put("spoofingPic", StringUtil.getPic(sysParamCfg.getParamValue(), ""));
				codeMap.put("customerServiceSoftType", customerServiceSoftType);
				codeMap.put("orderCreateDate", DateUtil.getFormatDate(ods.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
				codeMap.put("confirmReceiptTimeStr",confirmReceiptTimeStr.toString());
				codeMap.put("data", dataMapList);
				codeMap.put("collegeStudentStatus", collegeStudentStatus);
				codeMap.put("collegeStudentUrl", collegeStudentUrl);
				codeMap.put("auditStatusDesc", auditStatusDesc);
				returnData.add(codeMap);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 * 获取订单收货页面信息
	 */
	@RequestMapping(value = "/api/y/order/addressPageInfo")
	@ResponseBody
	public ResponseMsg orderAddressPageInfo() {
		GetOrderAddressPageInfoRequest request = appContext.getRequestAndValidate(GetOrderAddressPageInfoRequest.class);

		CombineOrder combineOrder = combineOrderService.getMemberOrder(request.getMemberId(), request.getCombineOrderId());
		List<OrderDtlCustom> orderDtlCustomList = orderDtlService.findByCombineOrderId(request.getCombineOrderId());

		GetOrderAddressPageInfoResponse response = new GetOrderAddressPageInfoResponse();
		response.setAddressId(combineOrder.getAddressId());
		response.setReceiverName(combineOrder.getReceiverName());
		response.setReceiverPhone(combineOrder.getReceiverPhone());
		response.setReceiverAddress(combineOrder.getReceiverAddress());
		for (OrderDtlCustom dtl : orderDtlCustomList) {
			OrderProductView view = new OrderProductView();
			BeanUtils.copyProperties(dtl, view);
			if("1".equals(dtl.getIsGive())){
				view.setSkuPic(StringUtil.getPic(dtl.getSkuPic(), ""));
			}else{
				view.setSkuPic(StringUtil.getPic(dtl.getSkuPic(), "S"));
			}
			response.getProductList().add(view);
		}
		return ResponseMsg.success(response);
	}

	/**
	 * 修改订单收货地址
	 */
	@RequestMapping(value = "/api/y/order/changeAddress")
	@ResponseBody
	public ResponseMsg changeOrderAddress() {
		ChangeOrderAddressRequest request = appContext.getRequestAndValidate(ChangeOrderAddressRequest.class);

		MemberAddressCustom memberAddress = memberAddressService.getAddressById(request.getMemberId(), request.getAddressId());
		if (memberAddress == null) {
			throw new BizException("收货地址已被删除，请重新添加收货地址");
		}
		CombineOrder combineOrder = combineOrderService.getMemberOrder(request.getMemberId(), request.getCombineOrderId());
		if (combineOrder.getAddressId().equals(request.getAddressId())
				&& combineOrder.getReceiverAddress().equals(memberAddress.getFullAddressName())
				&& combineOrder.getReceiverName().equals(memberAddress.getRecipient())
				&& combineOrder.getReceiverPhone().equals(memberAddress.getPhone())) {
			throw new BizException("抱歉，该地址与原地址相同");
		}
		if (!subOrderService.changeOrderAddressAble(request.getCombineOrderId())) {
			throw new BizException("当前状态不允许修改收货地址");
		}

		combineOrder.setAddressId(memberAddress.getId());
		combineOrder.setReceiverName(memberAddress.getRecipient());
		combineOrder.setReceiverPhone(memberAddress.getPhone());
		combineOrder.setReceiverAddress(memberAddress.getFullAddressName());
		combineOrder.setUpdateBy(request.getMemberId());
		combineOrder.setUpdateDate(new Date());
		combineOrderService.updateByPrimaryKeySelective(combineOrder);
		return ResponseMsg.success();
	}

	/**
	 *
	 * 方法描述 ：取消订单(未付款客户取消,平台取消)
	 * @author  chenwf:
	 * @date 创建时间：2017年4月27日 下午2:04:41
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/cancelOrderById")
	@ResponseBody
	public ResponseMsg cancelOrderById(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","combineOrderId"};
			this.requiredStr(obj,request);

			orderDtlService.updateCancelOrderById(reqDataJson);

			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：删除订单（已完成交易）
	 * @author  chenwf:
	 * @date 创建时间：2017年4月27日 下午2:02:56
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/deleteOrderById")
	@ResponseBody
	public ResponseMsg deleteOrderById(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId"};
			this.requiredStr(obj,request);

			String combineOrderId = reqDataJson.getString("combineOrderId");//总订单id
			String subOrderId = reqDataJson.getString("subOrderId");//子订单id

			if(StringUtil.isBlank(combineOrderId) && StringUtil.isBlank(subOrderId)){
				throw new ArgException("数据异常");
			}
			boolean b = true;
			if(!StringUtil.isBlank(combineOrderId)){
				CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(Integer.parseInt(combineOrderId));
				if(combineOrder.getStatus().equals(Const.COMBINE_ORDER_STATUS_CANCEL)){
					b = false;
					combineOrder.setIsUserDel("1");
					combineOrderService.updateByPrimaryKeySelective(combineOrder);
					SubOrderExample subOrderExample = new SubOrderExample();
					subOrderExample.createCriteria().andCombineOrderIdEqualTo(combineOrder.getId()).andDelFlagEqualTo("0");
					SubOrder subOrder = new SubOrder();
					subOrder.setIsUserDel("1");
					subOrderService.updateByExampleSelective(subOrder, subOrderExample);
				}
			}
			if(!StringUtil.isBlank(subOrderId) && b){
				SubOrderExample subOrderExample = new SubOrderExample();
				subOrderExample.createCriteria().andIdEqualTo(Integer.parseInt(subOrderId)).andDelFlagEqualTo("0");
				List<SubOrder> subOrders = subOrderService.selectByExample(subOrderExample);
				if(CollectionUtils.isNotEmpty(subOrders)){
					SubOrder subOrder = subOrders.get(0);
					String status = subOrder.getStatus();
					if(status.equals(Const.ORDER_STATUS_CANCEL)){
						CombineOrder combineOrder = new CombineOrder();
						combineOrder.setId(subOrder.getCombineOrderId());
						combineOrder.setIsUserDel("1");
						combineOrderService.updateByPrimaryKeySelective(combineOrder);
						subOrderExample = new SubOrderExample();
						subOrderExample.createCriteria().andCombineOrderIdEqualTo(combineOrder.getId()).andDelFlagEqualTo("0");
						subOrder.setIsUserDel("1");
						subOrderService.updateByExampleSelective(subOrder, subOrderExample);
					}else if(status.equals(Const.ORDER_STATUS_SUCCESS) || status.equals(Const.ORDER_STATUS_CLOSE)){
						subOrder.setIsUserDel("1");
						subOrderService.updateByExampleSelective(subOrder, subOrderExample);
					}
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：确认收货
	 * @author  chenwf:
	 * @date 创建时间：2017年4月27日 下午2:02:56
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/confirmReceiptOrderById")
	@ResponseBody
	public ResponseMsg confirmReceiptOrderById(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","subOrderId"};
			this.requiredStr(obj,request);

			Integer subOrderId = reqDataJson.getInt("subOrderId");//总订单id
			Integer memberId = reqDataJson.getInt("memberId");//会员id
			subOrderService.confirmReceiptOrderById(subOrderId,memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：修改售后申请(废弃)
	 * @author  chenwf:
	 * @date 创建时间：2017年5月4日 下午2:33:31
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateCustomerService")
	@ResponseBody
	public ResponseMsg updateCustomerService(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","serviceOrderId","serviceType","contactPhone","reason","quantity"};
			this.requiredStr(obj,request);
			Integer memberId = reqDataJson.getInt("memberId");
			Integer serviceOrderId = reqDataJson.getInt("serviceOrderId");
			String serviceType = reqDataJson.getString("serviceType");

			String contactPhone = reqDataJson.getString("contactPhone");
			String reason = reqDataJson.getString("reason");
			String reasonStr = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", reason);
			String remarks = reqDataJson.getString("remarks");
			String isAllowMchtModify = "0";
			if(reqDataJson.containsKey("isAllowMchtModify")){
				isAllowMchtModify = reqDataJson.getString("isAllowMchtModify");
			}

			CustomerServiceOrder oldCustomerServiceOrder=customerServiceOrderService.selectByPrimaryKey(serviceOrderId);
			Integer orderDtlId = oldCustomerServiceOrder.getOrderDtlId();
			OrderDtl orderDtl = orderDtlService.findModelById(orderDtlId);
			SubOrder subOrder = subOrderService.selectByPrimaryKey(oldCustomerServiceOrder.getSubOrderId());
			Integer quantity = orderDtl.getQuantity();
			if(reqDataJson.containsKey("quantity")){
				quantity = reqDataJson.getInt("quantity");
			}
			if(!serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
				if(quantity > orderDtl.getQuantity()){
					throw new ArgException("申请售后的数量不能大于购买的数量");
				}
			}
			String pic = reqDataJson.getString("pic");
			customerServiceOrderService.updateCustomerService(memberId, serviceOrderId, serviceType, contactPhone,
					reason, reasonStr, remarks, quantity, pic,subOrder,orderDtl,isAllowMchtModify);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：修改售后申请
	 * @author  chenwf:
	 * @date 创建时间：2017年5月4日 下午2:33:31
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateCustomerService20170725")
	@ResponseBody
	public ResponseMsg updateCustomerService20170725(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","serviceOrderId","serviceType","contactPhone","reason","quantity","amount"};
			this.requiredStr(obj,request);
			Integer memberId = reqDataJson.getInt("memberId");
			Integer serviceOrderId = reqDataJson.getInt("serviceOrderId");
			String serviceType = reqDataJson.getString("serviceType");

			String contactPhone = reqDataJson.getString("contactPhone");
			String reason = reqDataJson.getString("reason");
			String reasonStr = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", reason);
			String remarks = reqDataJson.getString("remarks");


			CustomerServiceOrder oldCustomerServiceOrder=customerServiceOrderService.selectByPrimaryKey(serviceOrderId);
			BigDecimal amount = oldCustomerServiceOrder.getAmount();
			Integer quantity = oldCustomerServiceOrder.getQuantity();
//			if(!serviceType.equals(Const.CUSTOMER_ORDER_TYPE_C)){
//				amount = BigDecimal.valueOf(reqDataJson.getDouble("amount"));
//			}
			if("A7".equals(reason) && serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
				boolean isPreDepositOrder = subDepositOrderService.getIsPreDepositOrder(oldCustomerServiceOrder.getOrderDtlId());
				if(!isPreDepositOrder){
					throw new ArgException("退款原因选择有误，请重新选择！");
				}
			}
			String pic = reqDataJson.getString("pic");
			Date date = new Date();
			String context = "";
			String proStatus = serviceType+"1";
			//1售后单表
			CustomerServiceOrder customerServiceOrder = new CustomerServiceOrder();
			customerServiceOrder.setId(serviceOrderId);
			customerServiceOrder.setServiceType(serviceType);
			customerServiceOrder.setContactPhone(contactPhone);
			customerServiceOrder.setReason(reason);
			customerServiceOrder.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
			customerServiceOrder.setProStatus(proStatus);
			customerServiceOrder.setQuantity(quantity);
			customerServiceOrder.setAmount(amount);
			customerServiceOrder.setRemarks(remarks);
			customerServiceOrder.setUpdateBy(memberId);
			customerServiceOrder.setUpdateDate(date);
			customerServiceOrderService.updateByPrimaryKeySelective(customerServiceOrder);

			//2插入售后单状态流水日志表
			CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
			customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
			customerServiceStatusLog.setCreateBy(memberId);
			customerServiceStatusLog.setCreateDate(date);
			customerServiceStatusLog.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
			customerServiceStatusLog.setProStatus(proStatus);
			customerServiceStatusLog.setDelFlag("0");
			customerServiceStatusLog.setRemarks("修改售后单据");
			customerServiceStatusLogService.insertSelective(customerServiceStatusLog);

			//3售后记录表
			CustomerServiceLog customerServiceLog = new CustomerServiceLog();
			customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
			customerServiceLog.setOperatorType(Const.OPERATOR_TYPE_MEMBER);

			if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A) & proStatus.equals(Const.CUSTOMER_ORDER_PRO_STATUS_A1)){
				context = "<font>申请退款</font><br>"
								+ "申请数量："+quantity+"<br>"
								+ "退款金额："+amount+"<br>"
								+ "退款原因："+reasonStr+"<br>"
								+ "原因说明："+remarks;
			}else if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_B) & proStatus.equals(Const.CUSTOMER_ORDER_PRO_STATUS_B1)){
				context = "<font>申请退货</font><br>"
						+ "申请数量："+quantity+"<br>"
						+ "退款金额："+amount+"<br>"
						+ "退货原因："+reasonStr+"<br>"
						+ "原因说明："+remarks;
			}else if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_C) & proStatus.equals(Const.CUSTOMER_ORDER_PRO_STATUS_C1)){
				context = "<font>申请售后换货</font><br>"
						+ "申请数量："+quantity+"<br>"
						+ "退款原因："+reasonStr+"<br>"
						+ "原因说明："+remarks;
			}
			customerServiceLog.setContent(context);
			customerServiceLog.setRemarks(remarks);
			customerServiceLog.setCreateDate(date);
			customerServiceLog.setCreateBy(memberId);
			customerServiceLog.setUpdateDate(date);
			customerServiceLog.setDelFlag("0");
			customerServiceLogService.insertSelective(customerServiceLog);

			//把售后单图片设置为删除标志1
			CustomerServicePicExample customerServicePicExampleUpdateAll = new CustomerServicePicExample();
			customerServicePicExampleUpdateAll.createCriteria().andServiceOrderIdEqualTo(serviceOrderId).andCreateByEqualTo(memberId);
			CustomerServicePic customerServicePicUpdateAll = new CustomerServicePic();
			customerServicePicUpdateAll.setDelFlag("1");
			customerServicePicService.updateByExampleSelective(customerServicePicUpdateAll, customerServicePicExampleUpdateAll);
			//售后记录图片设置为删除标志1
			ServiceLogPicExample serviceLogPicExampleUpdateAll = new ServiceLogPicExample();
			serviceLogPicExampleUpdateAll.createCriteria().andServiceLogIdEqualTo(customerServiceLog.getId()).andCreateByEqualTo(memberId);
			ServiceLogPic serviceLogPicUpdateAll = new ServiceLogPic();
			serviceLogPicUpdateAll.setDelFlag("1");
			serviceLogPicService.updateByExampleSelective(serviceLogPicUpdateAll, serviceLogPicExampleUpdateAll);

			if(!StringUtil.isBlank(pic)){
				String[] picList = pic.split(",");
				for (String customPic : picList) {
					customPic = StringUtil.replace(customPic,"xgbuy.cc");

					//根据图片url查找是否存在,存在就把删除标志设置为0，不存在则 循环走下一次
					CustomerServicePicExample customerServicePicExampleUpdate = new CustomerServicePicExample();
					customerServicePicExampleUpdate.createCriteria().andPicEqualTo(customPic).andServiceOrderIdEqualTo(serviceOrderId).andCreateByEqualTo(memberId);
					CustomerServicePic customerServicePicUpdate = new CustomerServicePic();
					customerServicePicUpdate.setDelFlag("0");
					int updateCustomerServicePicCount = customerServicePicService.updateByExampleSelective(customerServicePicUpdate, customerServicePicExampleUpdate);
					if(updateCustomerServicePicCount > 0){
						continue;
					}
					//不存在则做插入操作
					CustomerServicePic customerServicePic = new CustomerServicePic();
					customerServicePic.setServiceOrderId(serviceOrderId);
					customerServicePic.setPic(customPic);
					customerServicePic.setCreateBy(memberId);
					customerServicePic.setCreateDate(date);
					customerServicePic.setRemarks(remarks);
					customerServicePic.setDelFlag("0");
					customerServicePicService.insertSelective(customerServicePic);

				}

				for (String str : picList) {
					str = StringUtil.replace(str,"xgbuy.cc");
					//根据图片url查找是否存在,存在就把删除标志设置为0，不存在则 循环走下一次
					ServiceLogPicExample serviceLogPicExampleUpdate = new ServiceLogPicExample();
					serviceLogPicExampleUpdate.createCriteria().andPicEqualTo(str).andServiceLogIdEqualTo(customerServiceLog.getId()).andCreateByEqualTo(memberId);
					ServiceLogPic serviceLogPicUpdate = new ServiceLogPic();
					serviceLogPicUpdate.setDelFlag("0");
					int updateServiceLogPicCount = serviceLogPicService.updateByExampleSelective(serviceLogPicUpdate, serviceLogPicExampleUpdate);
					if(updateServiceLogPicCount > 0){
						continue;
					}
					//不存在则做插入操作
					ServiceLogPic serviceLogPic = new ServiceLogPic();
					serviceLogPic.setServiceLogId(customerServiceLog.getId());
					serviceLogPic.setPic(str);
					serviceLogPic.setCreateBy(memberId);
					serviceLogPic.setCreateDate(date);
					serviceLogPic.setRemarks(remarks);
					serviceLogPic.setDelFlag("0");
					serviceLogPicService.insertSelective(serviceLogPic);

				}

			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：新增售后维修单
	 * @author  chenwf:
	 * @date 创建时间：2017年5月4日 下午3:18:16
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addCustomerService")
	@ResponseBody
	public ResponseMsg addCustomerService(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","subOrderId","orderDtlId","serviceType","contactPhone","reason","quantity"};
			this.requiredStr(obj,request);
			Integer memberId = reqDataJson.getInt("memberId");
			Integer subOrderId = reqDataJson.getInt("subOrderId");
			Integer orderDtlId = reqDataJson.getInt("orderDtlId");
			String serviceType = reqDataJson.getString("serviceType");

			String contactPhone = reqDataJson.getString("contactPhone");
			String reason = reqDataJson.getString("reason");
			String remarks = reqDataJson.getString("remarks");
			remarks=StringUtil.filterEmoji(remarks);
			Integer quantity = reqDataJson.getInt("quantity");
			String serviceOrderId = "";
			if(reqDataJson.containsKey("serviceOrderId")){
				serviceOrderId = reqDataJson.getString("serviceOrderId");
			}
			String isAllowMchtModify = "0";
			if(reqDataJson.containsKey("isAllowMchtModify")){
				isAllowMchtModify = reqDataJson.getString("isAllowMchtModify");
			}
			SubOrder subOrder = subOrderService.selectByPrimaryKey(subOrderId);
			if(subOrder == null){
				throw new ArgException("未找到该笔订单");
			}
			String subOrderStatus = subOrder.getStatus();
			if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
				if(!subOrderStatus.equals(Const.ORDER_STATUS_PAID)){
					throw new ArgException("商品已发货，不能进行退款，请走售后申请渠道。");
				}
			}
			if(!subOrderStatus.equals(Const.ORDER_STATUS_PAID)
					&& !subOrderStatus.equals(Const.ORDER_STATUS_SHIPPED)
					&& !subOrderStatus.equals(Const.ORDER_STATUS_RECEIVED_GOODS)){
				throw new ArgException("订单已完成，不能进行售后申请!");
			}
			if("A7".equals(reason)){
				boolean isPreDepositOrder = subDepositOrderService.getIsPreDepositOrder(orderDtlId);
				if(!isPreDepositOrder){
					throw new ArgException("退款原因选择有误，请重新选择！");
				}
			}
			OrderDtl orderDtl=orderDtlService.selectByPrimaryKey(orderDtlId);
			if(orderDtl == null){
				throw new ArgException("未找到该笔订单");
			}
			if(quantity <= 0){
				throw new ArgException("请正确填写售后数量");
			}
			if(!serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
				if(quantity > orderDtl.getQuantity()){
					throw new ArgException("申请售后的数量不能大于购买的数量");
				}
			}

			String pic = reqDataJson.getString("pic");

			String reasonStr = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", reason);
			List<Map<String,Object>> returnDate = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataMap = new HashMap<String,Object>();
			if(!StringUtil.isBlank(serviceOrderId)){
				customerServiceOrderService.updateCustomerService(memberId, Integer.valueOf(serviceOrderId), serviceType, contactPhone,
						reason, reasonStr, remarks, quantity, pic,subOrder,orderDtl,isAllowMchtModify);
				dataMap.put("serviceOrderId", serviceOrderId);
			}else{
				CustomerServiceOrder customerServiceOrder = customerServiceOrderService.addCustomerService(memberId,orderDtl,serviceType,contactPhone,reason,reasonStr,remarks,quantity,pic,isAllowMchtModify);
				dataMap.put("serviceOrderId", customerServiceOrder.getId());
			}
			returnDate.add(dataMap);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnDate);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}


	/**
	 *
	 * 方法描述 ：获取退款/退货列表
	 * @author  chenwf:
	 * @date 创建时间：2017年5月4日 下午3:18:16
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getCustomerServiceList")
	@ResponseBody
	public ResponseMsg getCustomerServiceList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","pageSize","currentPage"};
			this.requiredStr(obj,request);
			Integer memberId = reqDataJson.getInt("memberId");//会员标识id
			Integer pageSize = Integer.valueOf(reqDataJson.getString("pageSize"));//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码

			if(currentPage == null){
				currentPage = 0;
			}
			if(pageSize == null){
				pageSize = 5;
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("memberId", memberId);
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			Integer count = orderDtlService.getCustomerServiceCount(params);
			Page page = new Page(currentPage, pageSize, count);
			List<OrderDtlCustom> orderDtlCustomList = orderDtlService.getCustomerServiceList(params);
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> dataMapList = new ArrayList<Map<String,Object>>();
			if(orderDtlCustomList != null && orderDtlCustomList.size() > 0){
				for(OrderDtlCustom orderDtlCustom : orderDtlCustomList){
					Map<String,Object> map = new HashMap<String,Object>();
					String skuPic = orderDtlCustom.getSkuPic();
					if(!StringUtil.isBlank(skuPic)){
						skuPic = FileUtil.getImageServiceUrl()+skuPic;
					}else{
						skuPic = "";
					}
					map.put("pic", skuPic);
					map.put("customerServiceOrderId", orderDtlCustom.getCustomerServiceOrderId());
					map.put("productId", orderDtlCustom.getProductId());
					map.put("orderCode", orderDtlCustom.getOrderCode());
					map.put("subOrderCode", orderDtlCustom.getSubOrderCode());
					map.put("customerServiceOrderStatus", orderDtlCustom.getCustomerServiceOrderStatus());
					map.put("proStatus", orderDtlCustom.getProStatus());
					map.put("quantity", orderDtlCustom.getQuantity());
					map.put("productName", orderDtlCustom.getProductName());
					map.put("payAmount", orderDtlCustom.getPayAmount());
					map.put("productPropDesc", orderDtlCustom.getProductPropDesc());
					map.put("orderDtlId", orderDtlCustom.getOrderDtlId());
					map.put("subOrderId", orderDtlCustom.getSubOrderId());
					map.put("mchtId", orderDtlCustom.getMchtId());
					map.put("expressId", orderDtlCustom.getExpressId());
					map.put("expressNo", orderDtlCustom.getExpressNo());
					map.put("depositAmount", orderDtlCustom.getDepositAmount()==null?0:orderDtlCustom.getDepositAmount());
					dataMapList.add(map);
				}
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("dataMapList", dataMapList);
			dataMap.put("totalPage", page.getTotalPage());
			returnData.add(dataMap);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：取消退款/退货
	 * @author  chenwf:
	 * @date 创建时间：2017年5月4日 下午6:23:33
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/deleteCustomerService")
	@ResponseBody
	public ResponseMsg deleteCustomerService(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","serviceOrderId","status"};
			this.requiredStr(obj,request);

			Integer memberId = reqDataJson.getInt("memberId");//会员标识id
			Integer serviceOrderId = reqDataJson.getInt("serviceOrderId");//售后订单id
			//售后状态0进行中 1已完成 2已拒绝 3已撤销
			String status = "3";
			Date date = new Date();
			CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(serviceOrderId);
			String proStatus = customerServiceOrder.getProStatus();
			if((proStatus.equals("A1") || proStatus.equals("B1")
					|| proStatus.equals("C1") || proStatus.equals("B2")
					|| proStatus.equals("C2")) && customerServiceOrder.getStatus().equals(Const.CUSTOMER_ORDER_STATUS_REFUNDING)){
				customerServiceOrder.setId(serviceOrderId);
				customerServiceOrder.setStatus(status);
				customerServiceOrder.setCloudProductAfterTempletId(0);
				customerServiceOrder.setSupplierAddress("");
				customerServiceOrder.setSupplierRemarks("");
				customerServiceOrder.setSupplierRejectReason("");
				customerServiceOrder.setAddressType("");
				customerServiceOrder.setUpdateBy(memberId);
				customerServiceOrder.setUpdateDate(date);
				customerServiceOrderService.updateByPrimaryKeySelective(customerServiceOrder);

				//2售后记录表
				CustomerServiceLog customerServiceLog = new CustomerServiceLog();
				customerServiceLog.setServiceOrderId(serviceOrderId);
				customerServiceLog.setOperatorType(Const.OPERATOR_TYPE_MEMBER);
				customerServiceLog.setCreateBy(memberId);
				customerServiceLog.setCreateDate(date);
				customerServiceLog.setDelFlag("0");
				customerServiceLog.setContent("客户撤销售后单。");
				customerServiceLogService.insertSelective(customerServiceLog);

				//3售后单状态流水日志表
				CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
				customerServiceStatusLog.setServiceOrderId(serviceOrderId);
				customerServiceStatusLog.setStatus(status);
				customerServiceStatusLog.setCreateBy(memberId);
				customerServiceStatusLog.setCreateDate(date);
				customerServiceStatusLog.setProStatus(customerServiceOrder.getProStatus());
				customerServiceStatusLog.setDelFlag("0");
				customerServiceStatusLogService.insertSelective(customerServiceStatusLog);
			}else{
				throw new ArgException("售后正在处理中...");
			}

			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：获取退款/退货/换货信息
	 * @author  chenwf:
	 * @date 创建时间：2017年5月4日 下午6:23:33
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getRefundInfo")
	@ResponseBody
	public ResponseMsg getRefundInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","serviceOrderId"};
			this.requiredStr(obj,request);
			Date date = new Date();
			Integer version = reqPRM.getInt("version");
			String system = reqPRM.getString("system");
			Integer serviceOrderId = reqDataJson.getInt("serviceOrderId");//售后订单id
			Map<String,Object> dataMap = new HashMap<String,Object>();
			CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(serviceOrderId);
			String proStatus = customerServiceOrder.getProStatus();
			String stautus = customerServiceOrder.getStatus();
			Integer cloudProductAfterTempletId = customerServiceOrder.getCloudProductAfterTempletId();
			String supplierAddress = customerServiceOrder.getSupplierAddress();
			String addressType = customerServiceOrder.getAddressType();
			Date updateDate = customerServiceOrder.getUpdateDate();
			String mchtExpressCompany = "";
			String mchtExpressNo = "";
			String memberExpressCompany = "";
			String memberExpressNo = "";
			dataMap.put("serviceOrderId", serviceOrderId);
			dataMap.put("status", stautus);
			dataMap.put("proStatus", proStatus);
			dataMap.put("subOrderId", customerServiceOrder.getSubOrderId());
			dataMap.put("orderDtlId", customerServiceOrder.getOrderDtlId());
			dataMap.put("amount", customerServiceOrder.getAmount()==null?0:customerServiceOrder.getAmount());
			String serviceType = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "SERVICE_TYPE", customerServiceOrder.getServiceType());
			dataMap.put("serviceType", serviceType == null ? "" : serviceType);
			dataMap.put("serviceTypeVal", customerServiceOrder.getServiceType());
			String reason = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", customerServiceOrder.getReason());
			dataMap.put("reason", reason == null ? "" : reason);
			dataMap.put("reasonVal", customerServiceOrder.getReason());
			dataMap.put("remarks", customerServiceOrder.getRemarks());
			dataMap.put("quantity", customerServiceOrder.getQuantity());
			dataMap.put("refundDate", DateUtil.getFormatDate(customerServiceOrder.getCreateDate(), "yyyy-MM-dd HH:mm"));
			dataMap.put("contactPhone", customerServiceOrder.getContactPhone());
			dataMap.put("createDate", DateUtil.getFormatDate(customerServiceOrder.getCreateDate(),  "yyyy-MM-dd HH:mm:ss"));
			if(new BigDecimal(dataMap.get("amount").toString()).compareTo(new BigDecimal(0)) > 0) {
				dataMap.put("depositAmount", customerServiceOrder.getDepositAmount()==null?0:customerServiceOrder.getDepositAmount()); //定金
			}else {
				dataMap.put("depositAmount", 0); //定金
			}
			int jifenIntegral = 0;
			if(customerServiceOrder.getDepositAmount() != null && ("A2".equals(customerServiceOrder.getProStatus()) || "A4".equals(customerServiceOrder.getProStatus()))
					&& "A7".equals(customerServiceOrder.getReason()) && customerServiceOrder.getDepositAmount().intValue() > 0) {
				jifenIntegral = customerServiceOrder.getDepositAmount().intValue()*100;
			}
			dataMap.put("jifenIntegral", jifenIntegral); //补偿用户积分

			SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
			//状态，0 待付款，1 待发货，2 待收货，3 完成，4 取消，5 关闭
			String subOrderStatus = subOrder.getStatus();
			String expressId = subOrder.getExpressId();
			String expressNo = subOrder.getExpressNo();
			String mchId = subOrder.getMchtId().toString();
			String subOrderCode = subOrder.getSubOrderCode();
			dataMap.put("subOrderStatus", subOrderStatus);
			dataMap.put("expressId", expressId);
			dataMap.put("expressNo", expressNo);
			dataMap.put("mchId", mchId);
			dataMap.put("subOrderCode", subOrderCode);

			CustomerServiceLogExample customerServiceLogExample = new CustomerServiceLogExample();
			customerServiceLogExample.createCriteria().andServiceOrderIdEqualTo(serviceOrderId);
			customerServiceLogExample.setOrderByClause("create_date desc");
			List<CustomerServiceLog> customerServiceLogList = customerServiceLogService.selectByExample(customerServiceLogExample);
			String content = "";

			if(customerServiceLogList != null && customerServiceLogList.size() > 0){
				content = customerServiceLogList.get(0).getContent();
			}
			Date addDate = new Date();
			if (proStatus.equals("A1")
					|| proStatus.equals("B1")
					|| proStatus.equals("C1")) {
				addDate = DateUtil.addDay(updateDate, 2);
			} else if(proStatus.equals("B2")
					|| proStatus.equals("C2")){
				addDate = DateUtil.addDay(updateDate, 7);
			} else if(proStatus.equals("A3")){
				addDate = date;
			} else if(proStatus.equals("B3")
					|| proStatus.equals("B6")
					|| proStatus.equals("C3")
					|| proStatus.equals("C6")){
				addDate = DateUtil.addDay(updateDate, 2);
			} else {
				addDate = DateUtil.addDay(updateDate, 15);
			}
			long seconds = addDate.getTime()-date.getTime();
			long day = 00;
			long hour = 00;
			long minute = 00;
			if(seconds > 0){
				day=seconds/(60*60*24*1000);//换成天
				hour=(seconds-(60*60*24*1000*day))/(3600*1000);//总秒数-换算成天的秒数=剩余的秒数    剩余的秒数换算为小时
				minute=(seconds-60*60*24*1000*day-3600*1000*hour)/(60*1000);//总秒数-换算成天的秒数-换算成小时的秒数=剩余的秒数    剩余的秒数换算为分
				dataMap.put("restTime", day+"天"+hour+"时"+minute+"分");
			}else{
				dataMap.put("restTime", "00天00时00分");
			}

			StringBuffer sbf = new StringBuffer();
			String str = "";
			sbf.append("<HTML>");
			sbf.append("<BODY>");
			sbf.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			if(proStatus.equals("A1")){
				if (!taskSuspendService.suspend("AUTO_REFUND")) {
					//A1客户已申请（退款）
					sbf.append("您的的申请已经提交成功，");
					sbf.append("<font color='red'>" + day + "</font>天");
					sbf.append("<font color='red'>" + hour + "</font>时");
					sbf.append("<font color='red'>" + minute + "</font>分");
					sbf.append("内卖家未处理退款申请。将自动确认同意退款");
				}
			}else if(proStatus.equals("A2")){
				//A2商家同意申请（退款）
				sbf.append("商家已同意了您的退款申请。退款将原路返回支付账户。");
			}else if(proStatus.equals("A3")){
				//A3商家不同意申请（退款）
				sbf.append("您的申请已拒绝:"+content+"");
			}else if(proStatus.equals("B1")){
				if (!taskSuspendService.suspend("AUTO_REFUND")) {
					//B1客户已申请（退货）
					sbf.append("您的的申请已经提交成功，");
					sbf.append("<font color='red'>" + day + "</font>天");
					sbf.append("<font color='red'>" + hour + "</font>时");
					sbf.append("<font color='red'>" + minute + "</font>分");
					sbf.append("内卖家未处理退货申请。将默认同意退货");
				}
			}else if(proStatus.equals("B2")){
				if (!taskSuspendService.suspend("AUTO_REFUND")) {
					//B2商家同意申请（退货）3天
					sbf.append("商家同意了您的的退货申请 ，请您及时真实的退货物流信息 剩余填写时间：");
					sbf.append("<font color='red'>" + day + "</font>天");
					sbf.append("<font color='red'>" + hour + "</font>时");
					sbf.append("<font color='red'>" + minute + "</font>分");
					sbf.append("。若超过时间未填写物流信息则申请单将关闭。");
				}
			}else if(proStatus.equals("B3") || proStatus.equals("B6")){
				//B3商家不同意申请（退货）
				sbf.append("您的申请已拒绝:"+content+"<br/><br/>");
				sbf.append("售后单关闭剩余时间：");
				sbf.append("<font color='red'>"+day+"</font>天");
				sbf.append("<font color='red'>"+hour+"</font>时");
				sbf.append("<font color='red'>"+minute+"</font>分");
			}else if(proStatus.equals("B4")){
				//B4客户已寄件（退货）已经提交退货信息，卖家有14天23小时 59分处理退款，若逾期未处理退款，将自动退款给买家
				if(seconds <= 0){
					sbf.append("您已提交物流信息，请耐心等待商家处理");
				}else{
					if (!taskSuspendService.suspend("AUTO_REFUND")) {
						sbf.append("您已提交物流信息，请耐心等待商家处理，商家剩余处理时间：");
						sbf.append("<font color='red'>" + day + "</font>天");
						sbf.append("<font color='red'>" + hour + "</font>时");
						sbf.append("<font color='red'>" + minute + "</font>分");
						sbf.append("若超过时间则默认商家同意退款给您，退款将原路返回支付账户");
					}
				}
			}else if(proStatus.equals("B5")){
				//B4客户已寄件（退货）已经提交退货信息，卖家有14天23小时 59分处理退款，若逾期未处理退款，将自动退款给买家
				sbf.append("商家已经收到您的物品并同意退款。退款金额将原路返回原支付账号");
			}else if(proStatus.equals("C1")){
				//C1客户已申请（换货）
				sbf.append("您的的申请已经提交成功，");
				sbf.append("<font color='red'>"+day+"</font>天");
				sbf.append("<font color='red'>"+hour+"</font>时");
				sbf.append("<font color='red'>"+minute+"</font>分");
				sbf.append("内卖家未处理换货申请。将默认同意换货");
			}else if(proStatus.equals("C2")){
				if (!taskSuspendService.suspend("AUTO_REFUND")) {
					//C2商家同意申请（换货）7
					sbf.append("商家同意了您的的换货申请 ，请您及时真实的物流信息 剩余填写时间：");
					sbf.append("<font color='red'>" + day + "</font>天");
					sbf.append("<font color='red'>" + hour + "</font>时");
					sbf.append("<font color='red'>" + minute + "</font>分");
					sbf.append("。若超过时间未填写物流信息则申请单将关闭。");
				}
			}else if(proStatus.equals("C3")||proStatus.equals("C6")){
				//C3商家不同意申请（换货）
				sbf.append("您的申请已拒绝:"+content+"<br/><br/>");
				sbf.append("售后单关闭剩余时间：");
				sbf.append("<font color='red'>"+day+"</font>天");
				sbf.append("<font color='red'>"+hour+"</font>时");
				sbf.append("<font color='red'>"+minute+"</font>分");
			}else if(proStatus.equals("C4")){
				if (!taskSuspendService.suspend("AUTO_REFUND")) {
					//C4客户已寄件（换货）已经提交换货信息，卖家有14天23小时 59分处理换货，若逾期未处理、则可以投诉商家
					sbf.append("您已经提交物流信息，请耐心等待商家处理，商家剩余处理时间：");
					sbf.append("<font color='red'>" + day + "</font>天");
					sbf.append("<font color='red'>" + hour + "</font>时");
					sbf.append("<font color='red'>" + minute + "</font>分");
					sbf.append("若超过时间则默认商家同意退款给您，退款将原路返回支付账户");
				}
			}else if(proStatus.equals("C5")){
				sbf.append("商家已经收到您的物品并同意换货。请及时关注商家换货的物流信息");
				mchtExpressCompany = customerServiceOrder.getMchtExpressCompany() == null ? "" : customerServiceOrder.getMchtExpressCompany();
				mchtExpressNo = customerServiceOrder.getMchtExpressNo() == null ? "" : customerServiceOrder.getMchtExpressNo();
			}else if(proStatus.equals("C7")){
				//设置一个标识
				str = "1";
				mchtExpressCompany = customerServiceOrder.getMchtExpressCompany() == null ? "" : customerServiceOrder.getMchtExpressCompany();
				mchtExpressNo = customerServiceOrder.getMchtExpressNo() == null ? "" : customerServiceOrder.getMchtExpressNo();
			}else{
				//设置一个标识
				str = "1";
			}
			sbf.append("</BODY>");
			sbf.append("</HTML>");

			if(str.equals("1")){
				dataMap.put("content", "");
			}else{
				dataMap.put("content", sbf.toString());
			}
			Integer orderDtlId = customerServiceOrder.getOrderDtlId();
			List<OrderDtlCustom> mchtAddress = orderDtlService.findMchtAddress(orderDtlId);
			String address = "";
			String recipient = "";
			String recipientPhone = "";
			String remark = "";
			//TODO STORY #1764 退换货单的 寄回地址 优化
			if(!StringUtil.isEmpty(customerServiceOrder.getProductReturnAddress())){
				address = customerServiceOrder.getProductReturnAddress();
				recipient = customerServiceOrder.getProductReturnConsignee();
				recipientPhone = customerServiceOrder.getProductReturnContactPhone();
				remark = customerServiceOrder.getProductReturnRemarks();
			}else{
				if("1".equals(addressType)){
					if(cloudProductAfterTempletId != null && cloudProductAfterTempletId != 0){
						CloudProductAfterTemplet cloudProductAfterTemplet = cloudProductAfterTempletService.findModel(cloudProductAfterTempletId);
						address = supplierAddress;
						recipient = cloudProductAfterTemplet.getRecipient();
						recipientPhone = cloudProductAfterTemplet.getPhone();
						remark = cloudProductAfterTemplet.getRemarks();
					}
				}else{
					if(CollectionUtils.isNotEmpty(mchtAddress)){
						OrderDtlCustom custom = mchtAddress.get(0);
						address = custom.getProvinceName() + " " + custom.getCityName() + " " + custom.getCountyName() + " "
								+ custom.getReceiverAddress();
						recipient = custom.getReceiverName();
						recipientPhone = custom.getReceiverPhone();
						remark = custom.getRemarks();
					}
				}
			}
			dataMap.put("address", address);
			dataMap.put("recipient", recipient);
			dataMap.put("recipientPhone", recipientPhone);
			dataMap.put("remark", remark);

			//图片
			CustomerServicePicExample customerServicePicExample = new CustomerServicePicExample();
			customerServicePicExample.createCriteria().andServiceOrderIdEqualTo(serviceOrderId).andDelFlagEqualTo("0");
			List<String> picList = new ArrayList<String>();
			List<CustomerServicePic> customerServicePics = customerServicePicService.selectByExample(customerServicePicExample);
			if(CollectionUtils.isNotEmpty(customerServicePics)){
				for (CustomerServicePic customerServicePic : customerServicePics) {
					picList.add(FileUtil.getImageServiceUrl()+customerServicePic.getPic());
				}
			}
			dataMap.put("picList", picList);

			//按钮控制
			boolean withdrawButton = false;//取消申请
			boolean reapplyButton = false;//重新申请
			boolean modifyApplicationButton = false;//修改申请
			boolean logisticsApplicationButton = false;//物流申请
			boolean platformInterventionButton = false;//平台介入
			boolean interventionDetailButton = false;//介入详情
			//售后单进行中，B4,C4,订单未完成
			boolean modifyLogisticsButton = false;
			String platformInterventionInfo = "";
			String isComplaints = "0";
			String interventionOrderId = "";
			//查询是否被投诉  wrh 2018-04-26 16:46 售后界面不在有投诉按钮
//			AppealOrderExample appealOrderExample = new AppealOrderExample();
//			appealOrderExample.createCriteria().andSubOrderIdEqualTo(customerServiceOrder.getSubOrderId())
//					.andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
//			List<AppealOrder> appealOrders = appealOrderService.selectByExample(appealOrderExample);
//			if(CollectionUtils.isNotEmpty(appealOrders)){
//				//被投诉过了，不能在投诉
//				isComplaints = "0";
//			}else{
//				isComplaints = "1";
//			}
			//

			if(proStatus.equals("A1") || proStatus.equals("B1") || proStatus.equals("C1") && stautus.equals(Const.CUSTOMER_ORDER_STATUS_REFUNDING)){
				withdrawButton = true;
				modifyApplicationButton = true;
			}
			if((proStatus.equals("B2") || proStatus.equals("C2")) && stautus.equals(Const.CUSTOMER_ORDER_STATUS_REFUNDING)){
				withdrawButton = true;
				logisticsApplicationButton = true;
			}

			if(proStatus.equals("B3") || proStatus.equals("B6")
					|| proStatus.equals("C3") || proStatus.equals("C6")){
				reapplyButton = true;
				platformInterventionButton = true;
			}else if(proStatus.equals("A3") && subOrderStatus.equals(Const.ORDER_STATUS_PAID)){
				reapplyButton = true;
			}else if(proStatus.equals("C7") && !subOrderStatus.equals(Const.ORDER_STATUS_SUCCESS)){
				reapplyButton = true;
			}
			if(proStatus.equals("B4") || proStatus.equals("C4")){
				//买家填写物流信息，5天内不能介入
				platformInterventionButton = true;
				if(DateUtil.addDay(updateDate, 5).after(date)){
					platformInterventionInfo = "请给商家一定的处理时间（大约5天内）、给你造成不便深感抱歉、谢谢您的配合";
				}
				if(stautus.equals(Const.CUSTOMER_ORDER_STATUS_REFUNDING) && !subOrderStatus.equals(Const.ORDER_STATUS_SUCCESS)){
					memberExpressCompany = customerServiceOrder.getMemberExpressCompany() == null ? "" : customerServiceOrder.getMemberExpressCompany();
					memberExpressNo = customerServiceOrder.getMemberExpressNo() == null ? "" : customerServiceOrder.getMemberExpressNo();
					modifyLogisticsButton = true;
				}
			}
			//查看下是否有平台介入
			InterventionOrderExample interventionOrderExample = new InterventionOrderExample();
			interventionOrderExample.createCriteria().andServiceOrderIdEqualTo(serviceOrderId).andProStatusEqualTo(proStatus).andDelFlagEqualTo("0");
			interventionOrderExample.setOrderByClause("id desc");
			List<InterventionOrder> interventionOrders = interventionOrderService.selectByExample(interventionOrderExample);
			if(CollectionUtils.isNotEmpty(interventionOrders)){
				InterventionOrder interventionOrder = interventionOrders.get(0);
				interventionOrderId = interventionOrder.getId().toString();
				String interventionOrderStatus = interventionOrder.getStatus();
				if(!interventionOrderStatus.equals("8")){
					if(proStatus.equals("B3") || proStatus.equals("B6")
							|| proStatus.equals("C3") || proStatus.equals("C6")){
						reapplyButton = false;//重新申请
					}
					if(proStatus.equals("B4") || proStatus.equals("C4")){
						modifyApplicationButton = false;//修改申请
					}
				}
				platformInterventionButton = false;
				interventionDetailButton = true;
			}
			if(subOrderStatus.equals(Const.ORDER_STATUS_SUCCESS)
					|| subOrderStatus.equals(Const.ORDER_STATUS_CANCEL)
					|| subOrderStatus.equals(Const.ORDER_STATUS_CLOSE)){
				withdrawButton = false;//取消申请
				reapplyButton = false;//重新申请
				modifyApplicationButton = false;//修改申请
				logisticsApplicationButton = false;//物流申请
				platformInterventionButton = false;//平台介入
				interventionDetailButton = false;//介入详情
				modifyLogisticsButton = false;//修改物流
			}
			String isAllowMchtModify = customerServiceOrder.getIsAllowMchtModify();
			dataMap.put("isAllowMchtModify", isAllowMchtModify);
			dataMap.put("isAllowMchtModifyContent", "允许商家换货改退款");
			dataMap.put("withdrawButton", withdrawButton);
			dataMap.put("reapplyButton", reapplyButton);
			dataMap.put("modifyApplicationButton", modifyApplicationButton);
			dataMap.put("logisticsApplicationButton", logisticsApplicationButton);
			dataMap.put("platformInterventionButton", platformInterventionButton);
			dataMap.put("interventionDetailButton", interventionDetailButton);
			dataMap.put("modifyLogisticsButton", modifyLogisticsButton);
			dataMap.put("platformInterventionInfo", platformInterventionInfo);
			dataMap.put("interventionOrderId", interventionOrderId);
			dataMap.put("isComplaints", isComplaints);
			dataMap.put("mchtExpressNo", mchtExpressNo);
			dataMap.put("mchtExpressCompany", mchtExpressCompany);
			dataMap.put("memberExpressNo", memberExpressNo);
			dataMap.put("memberExpressCompany", memberExpressCompany);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：获取退款/退货/换货详情流水
	 * @author  chenwf: （new）
	 * @date 创建时间：2017年5月6日 上午10:40:21
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getRefundDetailLog")
	@ResponseBody
	public ResponseMsg getRefundDetailLog(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","serviceOrderId"};
			this.requiredStr(obj,request);

			Integer serviceOrderId = reqDataJson.getInt("serviceOrderId");//售后订单id
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("serviceOrderId", serviceOrderId);
			CustomerServiceOrder customerServiceOrder = customerServiceOrderService.findListById(serviceOrderId);
			String serviceType = customerServiceOrder.getServiceType();
			List<CustomerServiceLog> customerServiceLogs = customerServiceLogService.findListByServiceOrderId(serviceOrderId);
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = new HashMap<String,Object>();
			if(CollectionUtils.isNotEmpty(customerServiceLogs)){
				for (CustomerServiceLog customerServiceLog : customerServiceLogs) {
					List<String> picList = new ArrayList<String>();
					Map<String,Object> dataMap = new HashMap<String,Object>();
					//操作者类型 1 客户 2 商家 3 系统
					String operatorType = customerServiceLog.getOperatorType();
					if(operatorType.equals("1")){
						operatorType = "客户";
					}else if(operatorType.equals("2")){
						operatorType = "商家";
					}else{
						operatorType = "系统";
					}
					//图片
					List<ServiceLogPic> serviceLogPics = serviceLogPicService.findListByServiceLogId(customerServiceLog.getId());
					if(CollectionUtils.isNotEmpty(serviceLogPics)){
						for (ServiceLogPic serviceLogPic : serviceLogPics) {
							String pic = serviceLogPic.getPic();
							if(!StringUtil.isBlank(pic)){
								picList.add(FileUtil.getImageServiceUrl()+pic);
							}
						}
					}
					dataMap.put("id", customerServiceLog.getId());
					dataMap.put("serviceOrderId", customerServiceLog.getServiceOrderId());
					dataMap.put("operatorType", operatorType);
					dataMap.put("content", customerServiceLog.getContent());
					dataMap.put("picList", picList);
					String createDate = DateUtil.getFormatDate(customerServiceLog.getCreateDate(), "yyyy-MM-dd HH:mm");
					dataMap.put("createDate", createDate);
					returnData.add(dataMap);
				}
			}
			//客服，判断版本，ios
			SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(subOrder.getMchtId());
			String customerServiceSoftType = sobotCustomerServiceService.getCustomerServiceSoftType(reqPRM,mchtInfo);
			map.put("customerServiceSoftType", customerServiceSoftType);
			map.put("dataList", returnData);
			map.put("serviceType", serviceType);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

//	/**
//	 *
//	 * 方法描述 ：获取退款/退货/换货详情流水
//	 * @author  chenwf: (废弃)
//	 * @date 创建时间：2017年5月6日 上午10:40:21
//	 * @version
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/api/y/getRefundDetailLog20170718")
//	@ResponseBody
//	public ResponseMsg getRefundDetailLog20170718(HttpServletRequest request){
//		try {
//			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
//			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
//
//			Object[] obj = {"memberId","serviceOrderId"};
//			this.requiredStr(obj,request);
//
//			Integer serviceOrderId = reqDataJson.getInt("serviceOrderId");//售后订单id
//			Map<String,Object> params = new HashMap<String,Object>();
//			params.put("serviceOrderId", serviceOrderId);
//			List<CustomerServiceLog> customerServiceLogs = customerServiceLogService.findListByServiceOrderId(serviceOrderId);
//			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
//			if(CollectionUtils.isNotEmpty(customerServiceLogs)){
//				for (CustomerServiceLog customerServiceLog : customerServiceLogs) {
//					List<String> picList = new ArrayList<String>();
//					Map<String,Object> dataMap = new HashMap<String,Object>();
//					//操作者类型 1 客户 2 商家 3 系统
//					String operatorType = customerServiceLog.getOperatorType();
//					if(operatorType.equals("1")){
//						operatorType = "客户";
//					}else if(operatorType.equals("2") || operatorType.equals("4")){
//						operatorType = "商家";
//					}else{
//						operatorType = "系统";
//					}
//					//图片
//					List<ServiceLogPic> serviceLogPics = serviceLogPicService.findListByServiceLogId(customerServiceLog.getId());
//					if(CollectionUtils.isNotEmpty(serviceLogPics)){
//						for (ServiceLogPic serviceLogPic : serviceLogPics) {
//							String pic = serviceLogPic.getPic();
//							if(!StringUtil.isBlank(pic)){
//								picList.add(FileUtil.getImageServiceUrl()+pic);
//							}
//						}
//					}
//					dataMap.put("id", customerServiceLog.getId());
//					dataMap.put("serviceOrderId", customerServiceLog.getServiceOrderId());
//					dataMap.put("operatorType", operatorType);
//					dataMap.put("content", customerServiceLog.getContent());
//					dataMap.put("picList", picList);
//					String createDate = DateUtil.getFormatDate(customerServiceLog.getCreateDate(), "yyyy-MM-dd HH:mm");
//					dataMap.put("createDate", createDate);
//					returnData.add(dataMap);
//				}
//			}
//			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
//		} catch (ArgException args){
//			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
//		}
//	}


	/**
	 *
	 * 方法描述 ：新增投诉
	 * @author  chenwf:
	 * @date 创建时间：2017年5月6日 下午2:57:17
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addComplaint")
	@ResponseBody
	public ResponseMsg addComplaint(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","memberName","mchtId","subOrderId","orderDtlId","appealType","contactName","contactPhone"};
			this.requiredStr(obj,request);

			Integer memberId = reqDataJson.getInt("memberId");//会员标识id
			Integer mchtId = reqDataJson.getInt("mchtId");//商家id
			Integer subOrderId = reqDataJson.getInt("subOrderId");//子订单id
			Integer orderDtlId = reqDataJson.getInt("orderDtlId");//订单明细id

			String memberName = reqDataJson.getString("memberName");
			String appealType = reqDataJson.getString("appealType");
			String contactName = reqDataJson.getString("contactName");
			String contactPhone = reqDataJson.getString("contactPhone");
			String remarks = reqDataJson.getString("remarks");
			String pic = reqDataJson.getString("pic");
			appealOrderService.addComplaint(memberId, mchtId, subOrderId, orderDtlId, memberName, appealType,
					contactName, contactPhone, remarks, pic);

			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

//	/**
//	 *
//	 * 方法描述 ：修改售后申请(废弃)
//	 * @author  chenwf:
//	 * @date 创建时间：2017年5月4日 下午2:33:31
//	 * @version
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/api/y/updateCustomerService")
//	@ResponseBody
//	public ResponseMsg updateCustomerService(HttpServletRequest request){
//		try {
//			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
//			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
//
//			Object[] obj = {"memberId","serviceOrderId","serviceType","contactPhone","reason","quantity"};
//			this.requiredStr(obj,request);
//			Integer memberId = reqDataJson.getInt("memberId");
//			Integer serviceOrderId = reqDataJson.getInt("serviceOrderId");
//			String serviceType = reqDataJson.getString("serviceType");
//
//			String contactPhone = reqDataJson.getString("contactPhone");
//			String reason = reqDataJson.getString("reason");
//			String reasonStr = DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", reason);
//			String remarks = reqDataJson.getString("remarks");
//			String isAllowMchtModify = "0";
//			if(reqDataJson.containsKey("isAllowMchtModify")){
//				isAllowMchtModify = reqDataJson.getString("isAllowMchtModify");
//			}
//
//			CustomerServiceOrder oldCustomerServiceOrder=customerServiceOrderService.selectByPrimaryKey(serviceOrderId);
//			Integer orderDtlId = oldCustomerServiceOrder.getOrderDtlId();
//			OrderDtl orderDtl = orderDtlService.findModelById(orderDtlId);
//			SubOrder subOrder = subOrderService.selectByPrimaryKey(oldCustomerServiceOrder.getSubOrderId());
//			Integer quantity = orderDtl.getQuantity();
//			if(reqDataJson.containsKey("quantity")){
//				quantity = reqDataJson.getInt("quantity");
//			}
//			if(!serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
//				if(quantity > orderDtl.getQuantity()){
//					throw new ArgException("申请售后的数量不能大于购买的数量");
//				}
//			}
//			String pic = reqDataJson.getString("pic");
//			customerServiceOrderService.updateCustomerService(memberId, serviceOrderId, serviceType, contactPhone,
//					reason, reasonStr, remarks, quantity, pic,subOrder,orderDtl,isAllowMchtModify);
//			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
//		} catch (ArgException args){
//			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
//		}
//	}

	/**
	 *
	 * 方法描述 ：获取投诉信息
	 * @author  chenwf:
	 * @date 创建时间：2017年5月8日 下午5:23:50
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getComplaintList")
	@ResponseBody
	public ResponseMsg getComplaintList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","appealOrderId"};
			this.requiredStr(obj,request);

			Integer appealOrderId = reqDataJson.getInt("appealOrderId");//投诉单id

			AppealOrder appealOrder = appealOrderService.selectByPrimaryKey(appealOrderId);
			Map<String,Object> returnData = null;
			if(appealOrder != null){
				AppealLogExample appealLogExample = new AppealLogExample();
				appealLogExample.createCriteria().andAppealOrderIdEqualTo(appealOrder.getId()).andIsAppShowEqualTo("0").andDelFlagEqualTo("0");
				List<AppealLog> appealLogList = appealLogService.selectByExample(appealLogExample);
				List<Map<String,Object>> picMapList = null;
				List<Map<String,Object>> memberInfoList = new ArrayList<Map<String,Object>>();
				boolean isOfficialMessage = false;
				if(appealLogList != null && appealLogList.size() > 0){
					for (AppealLog appealLog : appealLogList) {
						//获取用户信息
						picMapList = new ArrayList<Map<String,Object>>();
						Map<String,Object> userInfoMap = new HashMap<String,Object>();
						if(!StringUtil.isBlank(appealLog.getUserType())){
							if(appealLog.getUserType().equals("1")){//会员
								MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(appealLog.getUserId());
								userInfoMap.put("memberPic", FileUtil.getImageServiceUrl()+memberInfo.getPic());
								userInfoMap.put("memberNick", memberInfo.getNick());
								userInfoMap.put("content", appealLog.getContent());
							}else if(appealLog.getUserType().equals("2")){//平台
								//SysStaffInfo sysStaffInfo = sysStaffInfoService.findModelByStaffId(appealLog.getUserId());
								userInfoMap.put("memberPic",FileUtil.getImageServiceUrl()+"/app/2017/platform_headPic.png");
								userInfoMap.put("memberNick", "醒购客服");
								userInfoMap.put("content", appealLog.getContent());
							}else if(appealLog.getUserType().equals("3")){//商家
								MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(appealLog.getUserId());
								userInfoMap.put("memberPic",FileUtil.getImageServiceUrl()+"/app/2017/mcht_headPic.png");
								userInfoMap.put("memberNick", mchtInfo.getShortName());
								userInfoMap.put("content", appealLog.getContent());
							}
						}
						//获取图片信息
						AppealLogPicExample appealLogPicExample= new AppealLogPicExample();
						appealLogPicExample.createCriteria().andAppealLogIdEqualTo(appealLog.getId()).andDelFlagEqualTo("0");
						List<AppealLogPic> appealLogPicList = appealLogPicService.selectByExample(appealLogPicExample);
						if(appealLogPicList != null && appealLogPicList.size() > 0){
							for (AppealLogPic appealLogPic : appealLogPicList) {
								Map<String,Object> picMap = new HashMap<String,Object>();
								picMap.put("pic", FileUtil.getImageServiceUrl()+appealLogPic.getPic());
								picMapList.add(picMap);
							}
						}
						userInfoMap.put("picMapList", picMapList);
						if(!StringUtil.isBlank(appealLog.getUserType()) && appealLog.getUserType().equals("2")){
							isOfficialMessage = true;
						}
						memberInfoList.add(userInfoMap);
					}
				}
				boolean isCloseComplaint = false;
				//获取商品信息
				Integer orderDtlId = appealOrder.getOrderDtlId();
				OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(orderDtlId);
				Map<String,Object> productMap = new HashMap<String,Object>();
				productMap.put("orderDtlId", orderDtl.getId());
				productMap.put("productId", orderDtl.getProductId());
				productMap.put("productName", orderDtl.getProductName());
				productMap.put("productPropDesc", orderDtl.getProductPropDesc());
				productMap.put("quantity", orderDtl.getQuantity());
				productMap.put("skuPic", FileUtil.getImageServiceUrl()+orderDtl.getSkuPic());
				productMap.put("salePrice", orderDtl.getSalePrice());

				String isExist = "0";
				//判断用户是否已经关闭投诉，关闭投诉将不在显示关闭投诉按钮
				if(appealOrder.getStatus().equals(Const.APPEAL_ORDER_STATUS_3)
						|| appealOrder.getStatus().equals(Const.APPEAL_ORDER_STATUS_4)
						|| appealOrder.getStatus().equals(Const.APPEAL_ORDER_STATUS_5)){
					isCloseComplaint = true;
				}
				returnData = new HashMap<String,Object>();
				returnData.put("memberInfoList", memberInfoList);
				returnData.put("productMap", productMap);
				returnData.put("isCloseComplaint", isCloseComplaint);
				returnData.put("status", appealOrder.getStatus());
				if(StringUtil.isBlank(appealOrder.getServiceSponsorType())){
					if(!appealOrder.getStatus().equals(Const.APPEAL_ORDER_STATUS_3)
						|| !appealOrder.getStatus().equals(Const.APPEAL_ORDER_STATUS_4)
						|| !appealOrder.getStatus().equals(Const.APPEAL_ORDER_STATUS_5)){
						if(!isOfficialMessage){
							isExist = "1";//可以申请介入
						}
					}
				}
				returnData.put("isExist", isExist);

				returnData.put("isOfficialMessage", isOfficialMessage);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：新增物流退货地址
	 * @author  chenwf:
	 * @date 创建时间：2017年5月8日 下午6:28:48
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addReturnInformation")
	@ResponseBody
	public ResponseMsg addReturnInformation(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId","serviceOrderId","memberExpressCompany","memberExpressNo"};
			this.requiredStr(obj,request);

			Integer memberId = reqDataJson.getInt("memberId");//会员id
			Integer serviceOrderId = reqDataJson.getInt("serviceOrderId");//投诉单id
			String memberExpressCompany = reqDataJson.getString("memberExpressCompany");//快递公司
			String memberExpressNo = reqDataJson.getString("memberExpressNo");//快递号码
			String type = "1";
			if(reqDataJson.containsKey("type")){
				type = reqDataJson.getString("type");
			}
			Date date = new Date();
			ExpressExample expressExample = new ExpressExample();
			expressExample.createCriteria().andNameEqualTo(memberExpressCompany).andDelFlagEqualTo(StateConst.FALSE);
			List<Express> expresses = expressMapper.selectByExample(expressExample);
			if (CollectionUtil.isEmpty(expresses)) {
				throw new ArgException("物流公司不存在。");
			}

			CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(serviceOrderId);
			String expressNo = customerServiceOrder.getMemberExpressNo() == null ? "" : customerServiceOrder.getMemberExpressNo();
			String proStatus = customerServiceOrder.getProStatus();
			String serviceType = customerServiceOrder.getServiceType();
			if(expressNo.equals(memberExpressNo) && "1".equals(type)){
				throw new ArgException("请勿重复填写物流信息。");
			}
			//失效时间
			Date failureTime = DateUtil.addDay(customerServiceOrder.getUpdateDate(), 7);
			String status = customerServiceOrder.getStatus();
			//失效时间 》 当前时间 不能在填写退货物流信息
			if(failureTime.before(date) || !status.equals(Const.CUSTOMER_ORDER_STATUS_REFUNDING)){
				throw new ArgException("由于您长时间未填写物流信息，申请单已经自动关闭。");
			}
			if(type.equals("1")){
				if(!("B2".equals(proStatus) && serviceType.equals(Const.CUSTOMER_ORDER_TYPE_B)) && !("C2".equals(proStatus) && serviceType.equals(Const.CUSTOMER_ORDER_TYPE_C))){
					throw new ArgException("请等待商家同意退换货申请......");
				}
				customerServiceOrder.setProStatus(customerServiceOrder.getServiceType()+"4");//客户已寄件
			}
			customerServiceOrder.setUpdateBy(memberId);
			customerServiceOrder.setUpdateDate(date);
			customerServiceOrder.setMemberExpressCompany(memberExpressCompany);
			customerServiceOrder.setMemberExpressNo(memberExpressNo);
			customerServiceOrderService.updateByPrimaryKeySelective(customerServiceOrder);

			StringBuilder sb = new StringBuilder();
			sb.append("<font>已寄件</font>");
			sb.append("<br>");
			sb.append("<font>快递公司:</font>"+memberExpressCompany);
			sb.append("<br>");
			sb.append("<font>快递单号:</font>"+memberExpressNo);
			//2插入售后单状态流水日志表
			customerServiceStatusLogService.add(customerServiceOrder.getId(), Const.CUSTOMER_ORDER_STATUS_REFUNDING,
					customerServiceOrder.getServiceType()+"4", memberId, sb.toString(), date);

			//3售后记录表
			CustomerServiceLog customerServiceLog = new CustomerServiceLog();
			customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
			customerServiceLog.setOperatorType(Const.OPERATOR_TYPE_MEMBER);//客户
			customerServiceLog.setContent(sb.toString());
			customerServiceLog.setRemarks("买家填写物流信息");
			customerServiceLog.setCreateBy(memberId);
			customerServiceLog.setCreateDate(date);
			customerServiceLog.setDelFlag("0");
			customerServiceLogService.saveModel(customerServiceLog);

			SubOrder subOrder = subOrderService.findListById(customerServiceOrder.getSubOrderId());
			if(type.equals("1")){
				//站内信息
				String title = "";
				String tplType = "";
				String msgType = "";
				if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_B)){
					title = Const.PLATFORM_MSG_TITLE_A21;
					tplType = Const.MSG_TLP_TYPE_A21;
					msgType = Const.PLATFORM_MSG_TYPE_A2;
				}else{
					title = Const.PLATFORM_MSG_TITLE_A31;
					tplType = Const.MSG_TLP_TYPE_A31;
					msgType = Const.PLATFORM_MSG_TYPE_A3;
				}
				String content = msgTplService.findMsgContent(tplType,customerServiceOrder.getOrderCode(),customerServiceOrder.getId(),subOrder.getSubOrderCode(),subOrder.getId(),customerServiceOrder.getAmount());
				platformMsgService.add(subOrder.getMchtId(),msgType,title,content,customerServiceOrder.getId(),Const.PLATFORM_MSG_STATUS_0);

			}

			//物流信息、单号记录
			saveOrUpdateKdnWuliuInfo(expresses.get(0), subOrder, memberExpressNo, memberId, date);

			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	private void saveOrUpdateKdnWuliuInfo(Express express, SubOrder subOrder, String logisticCode, Integer memberId, Date date) {
		KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
		KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
		criteria.andDelFlagEqualTo(StateConst.FALSE);
		criteria.andExpressIdEqualTo(express.getId());
		criteria.andLogisticCodeEqualTo(logisticCode);
		List<KdnWuliuInfo> kdnWuliuInfoList = kdnWuliuInfoService.selectByExample(kdnWuliuInfoExample);
		KdnWuliuInfo kdnWuliuInfo;
		if (kdnWuliuInfoList == null || kdnWuliuInfoList.size() == 0) {
			kdnWuliuInfo = new KdnWuliuInfo();
			kdnWuliuInfo.setCreateBy(memberId);
			kdnWuliuInfo.setCreateDate(date);
			kdnWuliuInfo.setDelFlag(StateConst.FALSE);
			kdnWuliuInfo.setExpressId(express.getId());
			kdnWuliuInfo.setLogisticCode(logisticCode);
			kdnWuliuInfo.setMerchantCode(subOrder.getMerchantCode());
			kdnWuliuInfo.setSubscribeStatus(StateConst.FALSE);
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
			kdnWuliuInfoService.insertSelective(kdnWuliuInfo);
		} else {
			kdnWuliuInfoService.updateByPrimaryKeySelective(kdnWuliuInfo);
		}
	}

	/**
	 *
	 * 方法描述 ：获取用户订单数量
	 * @author  chenwf:
	 * @date 创建时间：2017年7月11日 上午11:13:11
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getUserOrderCount")
	@ResponseBody
	public ResponseMsg getUserOrderCount(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Integer memberId = reqDataJson.getInt("memberId");//会员id
			//待付款
			Integer unpaidCount = 0;
			//待发货
			Integer shipmentPendingCount = 0;
			//待收货
			Integer afterReceivingCount = 0;
			//售后
			Integer customerServiceCount = 0;
			//待评价数量
			Integer unCommentCount = 0;

			Map<String,Object> params = new HashMap<String, Object>();
			params.put("memberId", memberId);

			//待付款
			CombineOrderExample combineOrderExample = new CombineOrderExample();
			combineOrderExample.createCriteria().andStatusEqualTo("0").andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
			unpaidCount = combineOrderService.countByExample(combineOrderExample);
			//待发货
			params.put("status", "1");
			shipmentPendingCount = orderDtlService.getAlreadyPaidCount(params);
			//待收货
			params.put("status", "2");
			afterReceivingCount = orderDtlService.getAlreadyPaidCount(params);
			//售后
			CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
			customerServiceOrderExample.createCriteria().andStatusEqualTo("0").andCreateByEqualTo(memberId).andDelFlagEqualTo("0");
			customerServiceCount = customerServiceOrderService.countByExample(customerServiceOrderExample);
			//待评价数量
			params.put("status", "3");
			unCommentCount = orderDtlService.getAlreadyPaidCount(params);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("unpaidCount", unpaidCount);
			map.put("shipmentPendingCount", shipmentPendingCount);
			map.put("afterReceivingCount", afterReceivingCount);
			map.put("customerServiceCount", customerServiceCount);
			map.put("unCommentCount", unCommentCount);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}


	/**
	 *
	 * 方法描述 ：提醒发货
	 * @author  chenwf:
	 * @date 创建时间：2018年9月18日 上午14:42:11
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addRemindDelivery")
	@ResponseBody
	public ResponseMsg addRemindDelivery(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Integer memberId = reqDataJson.getInt("memberId");//会员id
			Integer subOrderId = reqDataJson.getInt("subOrderId");//子订单id
			subOrderService.updateSubOrderRemind(memberId,subOrderId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：推广订单，分润订单(获取会员下级订单)
	 * @author  chenwf:
	 * @date 创建时间：2018年9月18日 上午14:42:11
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getSubMemberHistoryOrderList")
	@ResponseBody
	public ResponseMsg getMemberSubOrderList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer pageSize = Const.RETURN_SIZE_10;
			reqDataJson.put("pageSize", pageSize);
			Map<String, Object> map = orderDtlService.getSubMemberHistoryOrderList(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

}
