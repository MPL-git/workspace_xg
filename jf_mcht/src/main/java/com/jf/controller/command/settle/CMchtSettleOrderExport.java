package com.jf.controller.command.settle;

import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.entity.CombineOrder;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtUser;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.SubOrder;
import com.jf.service.CombineOrderService;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtSettleOrderService;
import com.jf.service.OrderDtlService;
import com.jf.service.SubOrderService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结算单-导出
 *
 * @author huangdl
 */
public class CMchtSettleOrderExport extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSettleOrderExport.class);

	@Resource
	private OrderDtlService orderDtlService;
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	@Resource
	private SubOrderService subOrderService;
	@Resource
	private CombineOrderService combineOrderService;
	@Resource
	private MchtSettleOrderService mchtSettleOrderService;
	@Resource
	private MchtInfoService mchtInfoService;

	private int mchtSettleOrderId;
//	private String subOrderCode;
//	private String productName;

	private MchtUser currentUser;


	boolean isPop;
	private Workbook xssfWorkbook;

	@Override
	public void init() {
		mchtSettleOrderId = getParaToInt("mchtSettleOrderId");
		Assert.moreThanZero(mchtSettleOrderId, "结算ID不能为空");

//		subOrderCode = getPara("subOrderCode");
//		productName = getPara("productName");

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.findById(mchtSettleOrderId);
		if(mchtSettleOrder == null || !mchtSettleOrder.getMchtId().equals(currentUser.getMchtId())){
			throw new BizException("未找到该结算单");
		}
		isPop = mchtSettleOrder.getMchtType().equals(Const.MCHT_TYPE_POP);

		QueryObject queryObject = new QueryObject();
//		if(StrKit.notBlank(subOrderCode)){
//			QueryObject subOrderQuery = new QueryObject();
//			subOrderQuery.addQuery("mchtId", currentUser.getMchtId());
//			subOrderQuery.addQuery("subOrderCode", subOrderCode);
//			List<SubOrder> subOrderList = subOrderService.list(subOrderQuery);
//			List<Integer> ids = new ArrayList<>();
//			ids.add(0);
//			for(SubOrder info : subOrderList){
//				ids.add(info.getId());
//			}
//			queryObject.addQuery("subOrderIds", ids);
//		}
//		if(StrKit.notBlank(productName)){
//			queryObject.addQuery("productName", productName);
//		}
		queryObject.addQuery("mchtSettleOrderId", mchtSettleOrderId);
		queryObject.addSort("create_date", QueryObject.SORT_DESC);

		// 导出订单
		try {
			xssfWorkbook = new XSSFWorkbook();
			// 表名
			String sheetName = "发货单";
			// 列名与数据对应关系
			List<Map<String, Object>> rowDataMapList = new ArrayList<Map<String, Object>>();
//			List<OrderDtl> orderList = orderDtlService.list(queryObject);
			List<OrderDtlCustom> orderList = orderDtlService.selectByExampleCustom(mchtSettleOrderId);
			String mchtType = "";
			String isManageSelf = "";
			if(!orderList.isEmpty()){
				mchtType = orderList.get(0).getMchtType();
				isManageSelf = orderList.get(0).getIsManageSelf();
			}
			// 列头
			String titles[] = isPop?getPopOrderTitles():getUnionOrderTitles(mchtType,isManageSelf);
			// 列头与列名对应关系
			Map<String, String> rowNameMap = isPop?getPopOrderRowNameMap():getUnionOrderRowNameMap(mchtType,isManageSelf);
			for (OrderDtlCustom info : orderList) {
				// 处理数据
				if(isPop){
					setPopOrderToMap(info, rowDataMapList);
				}else{
					setUnionOrderToMap(info, rowDataMapList);
				}
			}
			// 创建sheet页
			createSheet(sheetName, titles, rowNameMap, rowDataMapList);

			// 表名
			sheetName = "直赔单";
			titles = getRefundTitles();
			rowNameMap = getRefundRowNameMap();
			rowDataMapList = new ArrayList<Map<String, Object>>();
			List<CustomerServiceOrder> refundList = customerServiceOrderService.list(queryObject);
			for (CustomerServiceOrder info : refundList) {
				// 处理数据
				setRefundToMap(info, rowDataMapList);
			}
			createSheet(sheetName, titles, rowNameMap, rowDataMapList);


			StringBuilder fileName = new StringBuilder();
			fileName.append("结算单明细-");
			fileName.append(isPop ? "POP" : "SPOP");
			fileName.append("__" + context.getMchtInfo().getMchtCode());
			fileName.append("_" + new SimpleDateFormat("yyyyMMdd").format(mchtSettleOrder.getBeginDate()));
			fileName.append("到" + new SimpleDateFormat("yyyyMMdd").format(mchtSettleOrder.getEndDate()));
			fileName.append(".xlsx");

			data.put("fileName", fileName.toString());
			data.put("xssfWorkbook", xssfWorkbook);

		}catch (Exception e) {
			e.printStackTrace();
			throw new BizException("导出失败");

		}
	}


	private void createSheet(String sheetName, String[] titles, Map<String, String> rowNameMap, List<Map<String, Object>> rowDataMapList) throws Exception{
		XSSFSheet hssfSheet = (XSSFSheet) xssfWorkbook.createSheet(sheetName);

		// 创建表头
		writeToRow(hssfSheet, 0, titles, null, null);
		int rowIndex = 1;// 这里从1开始，因为第一行是 列头
		for (Map<String, Object> rowDataMap : rowDataMapList) {
			writeToRow(hssfSheet, rowIndex++, titles, rowNameMap, rowDataMap);
		}
	}

	private String[] getPopOrderTitles(){
		String titles[] = new String[] {"订单编号（子）", "订单的商品明细ID", "客户下单时间", "客户付款时间",
				"商家发货时间", "退款时间", "完成时间", "状态", "商品名称", "品牌", "货号", "规格", "SKU商家编码",
				"醒购价", "数量", "销售商品金额", "商家优惠","订单运费", "平台优惠", "积分优惠", "订单客户实付金额", "类型",
				"商家序号", "商家名称", "技术服务系数", "POP技术服务费", "POP结算金额", "POP结算单ID"};
		return titles;
	}

	private Map<String, String> getPopOrderRowNameMap(){
		Map<String, String> rowNameMap = new HashMap<String, String>();
		rowNameMap.put("订单编号（子）", "subOrder.subOrderCode");
		rowNameMap.put("订单的商品明细ID", "id");
		rowNameMap.put("客户下单时间", "combineOrder.createDate");
		rowNameMap.put("客户付款时间", "combineOrder.payDate");
		rowNameMap.put("商家发货时间", "subOrder.deliveryDate");
		rowNameMap.put("退款时间", "refundDate");
		rowNameMap.put("完成时间", "subOrder.completeDate");
		rowNameMap.put("状态", "productStatus");
		rowNameMap.put("商品名称", "productName");
		rowNameMap.put("品牌", "brandName");
		rowNameMap.put("货号", "artNo");
		rowNameMap.put("规格", "productPropDesc");
		rowNameMap.put("SKU商家编码", "sku");
		rowNameMap.put("醒购价", "salePrice");
		rowNameMap.put("数量", "quantity");
		rowNameMap.put("销售商品金额", "salePriceAll");
		rowNameMap.put("商家优惠", "mchtPreferential");
		rowNameMap.put("订单运费", "freight");
		rowNameMap.put("平台优惠", "platformPreferential");
		rowNameMap.put("积分优惠", "integralPreferential");
		rowNameMap.put("订单客户实付金额", "payAmount");
		rowNameMap.put("类型", "mchtType");
		rowNameMap.put("商家序号", "mchtInfo.mchtCode");
		rowNameMap.put("商家名称", "mchtInfo.shopName");
		rowNameMap.put("技术服务系数", "commissionRate");
		rowNameMap.put("POP技术服务费", "commissionAmount");
		rowNameMap.put("POP结算金额", "settleAmount");
		rowNameMap.put("POP结算单ID", "mchtSettleOrderId");
		return rowNameMap;
	}

	private void setPopOrderToMap(OrderDtl info, List<Map<String, Object>> listMap) {
		// excel里面的每一行
		SubOrder subOrder = subOrderService.selectByPrimaryKey(info.getSubOrderId());
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(subOrder.getMchtId());

		Map<String, Object> map = new HashMap<>();
		map.put("subOrder.subOrderCode", subOrder.getSubOrderCode());
		map.put("id", info.getId());
		map.put("combineOrder.createDate", combineOrder.getCreateDate());
		map.put("combineOrder.payDate", combineOrder.getPayDate());
		map.put("subOrder.deliveryDate", subOrder.getDeliveryDate());
		map.put("refundDate", info.getRefundDate());
		map.put("subOrder.completeDate", subOrder.getCompleteDate());
		String productStatus = "未知";
		if(info.getProductStatus() == null){
			productStatus = "未知";
		}else if(info.getProductStatus().equals("1")){
			productStatus = "完成";
		}else if(info.getProductStatus().equals("2")){
			productStatus = "退款";
		}else if(info.getProductStatus().equals("3")){
			productStatus = "退货";
		}
		map.put("productStatus", productStatus);
		map.put("productName", info.getProductName());
		map.put("brandName", info.getBrandName());
		map.put("artNo", info.getArtNo());
		map.put("productPropDesc", info.getProductPropDesc());
		map.put("sku", info.getSku());
		map.put("salePrice", info.getSalePrice());
		map.put("quantity", info.getQuantity());
		map.put("salePriceAll", info.getSalePrice().multiply(new BigDecimal(info.getQuantity())));
		map.put("mchtPreferential", info.getMchtPreferential());
		map.put("freight", info.getFreight());
		map.put("platformPreferential", info.getPlatformPreferential());
		map.put("integralPreferential", info.getIntegralPreferential());
		map.put("payAmount", info.getPayAmount());
		map.put("mchtType", "POP");
		map.put("mchtInfo.mchtCode", mchtInfo.getMchtCode());
		map.put("mchtInfo.shopName", mchtInfo.getShopName());
		map.put("commissionRate", info.getPopCommissionRate());
		if(info.getProductStatus() != null && info.getProductStatus().equals("1")){
			//只有完成的才有这两个值
			map.put("commissionAmount", info.getCommissionAmount());	//POP技术服务费 =（数量*醒购价-商家优惠）*（1-服务系数）
			map.put("settleAmount", info.getSettleAmount());	//POP结算金额 = 实付金额-结算金额
		}
		map.put("mchtSettleOrderId", info.getMchtSettleOrderId());

		listMap.add(map);
	}

	private String[] getUnionOrderTitles(String mchtType,String isManageSelf){
		String titles[] = new String[] {"订单编号（子）", "订单的商品明细ID", "客户下单时间", "客户付款时间",
				"商家发货时间", "退款时间", "完成时间", "状态", "商品名称", "品牌", "年份", "季节", "货号", "规格", "SKU商家编码",
				"吊牌价", "醒购价", "结算价", "数量", "销售商品金额", "销售商品商家优惠", "销售商品平台优惠", "销售商品积分优惠",
				"订单客户实付金额", "货款金额", "商家优惠", "订单运费","类型", "商家序号", "商家名称", "SPOP结算单ID"};
		if(StringUtils.equals(mchtType,"1") && StringUtils.equals(isManageSelf,"1")){
			ArrayList<String> titlesList = new ArrayList<>(Arrays.asList(titles));
			titlesList.add("自营运费");
			titles = titlesList.toArray(new String[0]);
		}
		return titles;
	}

	private Map<String, String> getUnionOrderRowNameMap(String mchtType,String isManageSelf){
		Map<String, String> rowNameMap = new HashMap<String, String>();
		rowNameMap.put("订单编号（子）", "subOrder.subOrderCode");
		rowNameMap.put("订单的商品明细ID", "id");
		rowNameMap.put("客户下单时间", "combineOrder.createDate");
		rowNameMap.put("客户付款时间", "combineOrder.payDate");
		rowNameMap.put("商家发货时间", "subOrder.deliveryDate");
		rowNameMap.put("退款时间", "refundDate");
		rowNameMap.put("完成时间", "subOrder.completeDate");
		rowNameMap.put("状态", "productStatus");
		rowNameMap.put("商品名称", "productName");
		rowNameMap.put("品牌", "brandName");
		rowNameMap.put("年份", "year");
		rowNameMap.put("季节", "season");
		rowNameMap.put("货号", "artNo");
		rowNameMap.put("规格", "productPropDesc");
		rowNameMap.put("SKU商家编码", "sku");
		rowNameMap.put("吊牌价", "tagPrice");
		rowNameMap.put("醒购价", "salePrice");
		rowNameMap.put("结算价", "settlePrice");
		rowNameMap.put("数量", "quantity");
		rowNameMap.put("销售商品金额", "salePriceAll");
		rowNameMap.put("销售商品商家优惠", "mchtPreferential");
		rowNameMap.put("销售商品平台优惠", "platformPreferential");
		rowNameMap.put("销售商品积分优惠", "integralPreferential");
		rowNameMap.put("订单客户实付金额", "payAmount");
		rowNameMap.put("货款金额", "payAmount");
		rowNameMap.put("商家优惠", "mchtPreferential");
		rowNameMap.put("订单运费","freight");
		rowNameMap.put("商家序号", "mchtInfo.mchtCode");
		rowNameMap.put("商家名称", "mchtInfo.shopName");
		rowNameMap.put("SPOP结算单ID", "mchtSettleOrderId");
		if(StringUtils.equals(mchtType,"1") && StringUtils.equals(isManageSelf,"1")){
			rowNameMap.put("自营运费", "manageSelfFreight");
		}
		return rowNameMap;
	}

	private void setUnionOrderToMap(OrderDtlCustom info, List<Map<String, Object>> listMap) {
		// excel里面的每一行
		SubOrder subOrder = subOrderService.selectByPrimaryKey(info.getSubOrderId());
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(subOrder.getMchtId());

		Map<String, Object> map = new HashMap<>();
		map.put("subOrder.subOrderCode", subOrder.getSubOrderCode());
		map.put("id", info.getId());
		map.put("combineOrder.createDate", combineOrder.getCreateDate());
		map.put("combineOrder.payDate", combineOrder.getPayDate());
		map.put("subOrder.deliveryDate", subOrder.getDeliveryDate());
		map.put("refundDate", info.getRefundDate());
		map.put("subOrder.completeDate", subOrder.getCompleteDate());
		String productStatus = "未知";
		if(info.getProductStatus() == null){
			productStatus = "未知";
		}else if(info.getProductStatus().equals("1")){
			productStatus = "完成";
		}else if(info.getProductStatus().equals("2")){
			productStatus = "退款";
		}else if(info.getProductStatus().equals("3")){
			productStatus = "退货";
		}
		map.put("productStatus", productStatus);
		map.put("productName", info.getProductName());
		map.put("brandName", info.getBrandName());
		map.put("year", "");
		map.put("season", "");
		map.put("artNo", info.getArtNo());
		map.put("productPropDesc", info.getProductPropDesc());
		map.put("sku", info.getSku());
		map.put("tagPrice", info.getTagPrice());
		map.put("salePrice", info.getSalePrice());
		map.put("settlePrice", info.getSettlePrice());
		map.put("quantity", info.getQuantity());
		map.put("salePriceAll", info.getSalePrice().multiply(new BigDecimal(info.getQuantity())));
		map.put("mchtPreferential", info.getMchtPreferential());
		map.put("platformPreferential", info.getPlatformPreferential());
		map.put("integralPreferential", info.getIntegralPreferential());
		map.put("payAmount", info.getPayAmount());
		if(info.getProductStatus() != null && info.getProductStatus().equals("1")){
			//只有完成的才有这两个值
			map.put("settleAmount", info.getSettlePrice().multiply(new BigDecimal(info.getQuantity())));	//货款金额
			map.put("mchtPreferentialAll", info.getMchtPreferential());	//商家优惠
		}
		map.put("mchtInfo.mchtCode", mchtInfo.getMchtCode());
		map.put("mchtInfo.shopName", mchtInfo.getShopName());
		map.put("mchtSettleOrderId", info.getMchtSettleOrderId());
		map.put("manageSelfFreight", info.getManageSelfFreight());
		listMap.add(map);
	}



	private String[] getRefundTitles(){
		String titles[] = new String[] {"售后类型", "订单编号（子）", "售后单号", "退款时间", "实退金额", "类型", "商家序号", "商家名称", isPop?"POP结算单ID":"SPOP结算单ID"};
		return titles;
	}

	private Map<String, String> getRefundRowNameMap(){
		Map<String, String> rowNameMap = new HashMap<String, String>();
		rowNameMap.put("售后类型", "refundType");
		rowNameMap.put("订单编号（子）", "subOrder.subOrderCode");
		rowNameMap.put("售后单号", "orderCode");
		rowNameMap.put("退款时间", "createDate");
		rowNameMap.put("实退金额", "amount$");
		rowNameMap.put("类型", "mchtType");
		rowNameMap.put("商家序号", "mchtInfo.mchtCode");
		rowNameMap.put("商家名称", "mchtInfo.shopName");
		rowNameMap.put("POP结算单ID", "mchtSettleOrderId");
		return rowNameMap;
	}

	private void setRefundToMap(CustomerServiceOrder info, List<Map<String, Object>> listMap) {
		SubOrder subOrder = subOrderService.selectByPrimaryKey(info.getSubOrderId());
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(subOrder.getMchtId());

		Map<String, Object> map = new HashMap<String, Object>();
		// excel里面的每一行
		map.put("refundType", "直赔单");
		map.put("subOrder.subOrderCode", subOrder.getSubOrderCode());
		map.put("orderCode", info.getOrderCode());
		map.put("createDate", info.getCreateDate());
		map.put("amount$", info.getAmount());
		map.put("mchtType", "POP");
		map.put("mchtInfo.mchtCode", mchtInfo.getMchtCode());
		map.put("mchtInfo.shopName", mchtInfo.getShopName());
		map.put("mchtSettleOrderId", info.getMchtSettleOrderId());

		listMap.add(map);
	}

	// 创建一行
	private void writeToRow(XSSFSheet hssfSheet, int rowsIndex, String stitle[],
							Map<String, String> excelColumnNamesMap, Map<String, Object> rowDataMap)
			throws Exception {
		XSSFRow row = hssfSheet.createRow(rowsIndex);
		XSSFCell cell = null;
		Object cellValue = null;
		for (int i = 0; i < stitle.length; i++) {
			// create cell
			String header = stitle[i];
			cell = row.createCell(i);
			if(rowsIndex == 0){
				cell.setCellValue(header);
				continue;
			}
			String key = excelColumnNamesMap.get(header);
			cellValue = rowDataMap.get(key);
			if (null != cellValue) {
				if(cellValue instanceof Date){
					cell.setCellValue(new SimpleDateFormat("yyyy/MM/dd").format(cellValue));
				}else if (cellValue instanceof BigDecimal) {
					BigDecimal b = (BigDecimal)cellValue;
					cell.setCellValue(b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				}else {
					cell.setCellValue(String.valueOf(cellValue));
				}
			}

		}
	}

}
