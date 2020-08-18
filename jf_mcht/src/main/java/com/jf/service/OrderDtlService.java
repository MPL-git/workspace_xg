package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.KdnWuliuInfoMapper;
import com.jf.dao.OrderDtlCustomMapper;
import com.jf.dao.OrderDtlMapper;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoExample;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlCustomExample;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderDtlService extends BaseService<OrderDtl,OrderDtlExample> {
	@Autowired
	private OrderDtlMapper dao;
	
	@Autowired
	private OrderDtlCustomMapper orderDtlCustomMapper;
	@Autowired
	private SubOrderService subOrderService;
	@Autowired
	private KdnWuliuInfoMapper kdnWuliuInfoMapper;

	@Autowired
	public void setOrderDtlMapper(OrderDtlMapper orderDtlMapper) {
		super.setDao(orderDtlMapper);
		this.dao = orderDtlMapper;
	}
	
	public List<OrderDtlCustom> getOrderDtlsBySubOrderId(OrderDtlExample e) {
		return orderDtlCustomMapper.getOrderDtlsBySubOrderId(e);
	}
	
	public int countOrderDtlByExample(OrderDtlCustomExample example) {
		return orderDtlCustomMapper.countOrderDtlByExample(example);
	}
	
	public List<OrderDtlCustom> selectOrderDtlCustomByExample(OrderDtlCustomExample example) {
		return orderDtlCustomMapper.selectByExample(example);
	}
	
	public List<OrderDtlCustom> selectOrderDtlCustomListByExample(OrderDtlCustomExample example) {
		return orderDtlCustomMapper.selectOrderDtlByExample(example);
	}
	
	public int countOrderDtlCustomByExample(OrderDtlCustomExample example) {
		return orderDtlCustomMapper.countOrderDtlCustomByExample(example);
	}


	/**
	 * 查询销量
     */
	public int querySaleQuantity(QueryObject queryObject){
		return orderDtlCustomMapper.querySaleQuantityByExample(builderQuery(queryObject));
	}

	public int countWaitDelivery(Integer mchtId, String status) {
		OrderDtlCustomExample example = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria criteria = example.createCriteria();
		example.setOrderByClause("t.id DESC");
		criteria.andSubOrderMchtIdEqualTo(mchtId);
		criteria.andProductStatusIsNull();
		criteria.andOrderDtlDelFlagEqualTo("0");
		criteria.andDeliveryStatusEqualTo("0");
		criteria.andDtlExpressNoIsNull();
		return orderDtlCustomMapper.countOrderDtlByExample(example);
	}
	
	public int  countHasDelivery(Integer mchtId, String status) {
		OrderDtlCustomExample example = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria criteria = example.createCriteria();
		example.setOrderByClause("t.id DESC");
		criteria.andSubOrderMchtIdEqualTo(mchtId);
		criteria.andProductStatusIsNull();
		criteria.andOrderDtlDelFlagEqualTo("0");
		criteria.andDeliveryStatusEqualTo("1");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			criteria.andOrderDtlDeliveryDateGreaterThanOrEqualTo(DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")+" 00:00:00");
			criteria.andOrderDtlDeliveryDateLessThanOrEqualTo(DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")+" 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderDtlCustomMapper.countOrderDtlByExample(example);
	}
	
	public int countConfirmDelivery(Integer mchtId, String status) {
		OrderDtlCustomExample example = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria criteria = example.createCriteria();
		example.setOrderByClause("t.id DESC");
		criteria.andSubOrderMchtIdEqualTo(mchtId);
		criteria.andProductStatusIsNull();
		criteria.andOrderDtlDelFlagEqualTo("0");
		criteria.andDeliveryStatusEqualTo("0");
		criteria.andDtlExpressNoIsNotNull();
		return orderDtlCustomMapper.countOrderDtlByExample(example);
	}
	
	public List<OrderDtl> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<OrderDtl> paginate(QueryObject queryObject) {
		OrderDtlExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<OrderDtl> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private OrderDtlCustomExample builderQuery(QueryObject queryObject) {
		OrderDtlCustomExample example = new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("subOrderIds") != null){
			List<Integer> subOrderIds = queryObject.getQuery("subOrderIds");
			criteria.andSubOrderIdIn(subOrderIds);
		}
		if(queryObject.getQuery("activityId") != null){
			criteria.andActivityIdEqualTo(queryObject.getQueryToInt("activityId"));
		}
		if(queryObject.getQuery("productId") != null){
			criteria.andProductIdEqualTo(queryObject.getQueryToInt("productId"));
		}
		if(queryObject.getQuery("productItemId") != null){
			criteria.andProductItemIdEqualTo(queryObject.getQueryToInt("productItemId"));
		}
		if(queryObject.getQuery("productItemIds") != null){
			List<Integer> productItemIds = queryObject.getQuery("productItemIds");
			criteria.andProductItemIdIn(productItemIds);
		}
		if(queryObject.getQuery("productName") != null){
			criteria.andProductNameLike("%" + queryObject.getQueryToStr("productName") + "%");
		}
		if(queryObject.getQuery("mchtSettleOrderId") != null){
			criteria.andMchtSettleOrderIdEqualTo(queryObject.getQueryToInt("mchtSettleOrderId"));
		}
		if(queryObject.getQuery("inDay") != null){
			int inDay = queryObject.getQueryToInt("inDay");
			criteria.andCreateDateBetween(DateTime.now().minusDays(inDay).toDate(), new Date());
		}
		if(queryObject.getQuery("payStatus") != null){
			criteria.andCombineOrderStatusEqualTo(queryObject.getQueryToStr("payStatus"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public OrderDtl fillInfo(OrderDtl info){
		SubOrder subOrder = subOrderService.selectByPrimaryKey(info.getSubOrderId());
		info.put("subOrder", subOrder);
		return info;
	}
	
	public List<Integer> getSubOrderIdsByActivityId(Integer activityId){
		return orderDtlCustomMapper.getSubOrderIdsByActivityId(activityId);
	}

	public List<OrderDtlCustom> eachDaySaleData(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.eachDaySaleData(paramMap);
	}

	public List<HashMap<String, Object>> getProductSaleData(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.getProductSaleData(paramMap);
	}

	public List<HashMap<String, Object>> getSingleProductActivitySaleData(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.getSingleProductActivitySaleData(paramMap);
	}

	public List<HashMap<String, Object>> activityAreaSaleData(HashMap<String, Object> paramMap) {
		return orderDtlCustomMapper.activityAreaSaleData(paramMap);
	}

	public BigDecimal sumPlatformAndIntegralPreferential(Integer mchtSettleOrderId) {
		return orderDtlCustomMapper.sumPlatformAndIntegralPreferential(mchtSettleOrderId);
	}

	public Integer countByCombineOrderId(Integer combineOrderId) {
		return orderDtlCustomMapper.countByCombineOrderId(combineOrderId);
	}

	public void updateOrderDtlsMerchantCode(List<OrderDtl> updateOrderDtlList) {
		Date now = new Date();
		for(OrderDtl od:updateOrderDtlList){
			od.setUpdateDate(now);
			this.updateByPrimaryKeySelective(od);
			OrderDtl orderDtl = new OrderDtl();
			orderDtl.setMerchantCode(od.getMerchantCode());
			OrderDtlExample e = new OrderDtlExample();
			e.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(od.getSubOrderId()).andMerchantCodeIsNull().andDtlExpressIdEqualTo(20);
//			List<OrderDtl> selectByExample = this.selectByExample(e);
			this.updateByExample(orderDtl, e);
		}
	}
	
	public void batchUpdateOrderDtls(List<OrderDtl> updateOrderDtlList) {
		Date now = new Date();
		for(OrderDtl od:updateOrderDtlList){
			OrderDtl orderDtl = this.selectByPrimaryKey(od.getId());
			SubOrder subOrder = subOrderService.selectByPrimaryKey(od.getSubOrderId());
			if(orderDtl.getDeliveryStatus().equals("1") && orderDtl.getDtlExpressId().equals(Integer.parseInt(subOrder.getExpressId())) && orderDtl.getDtlExpressNo().equals(subOrder.getExpressNo())){
				subOrder.setExpressId(od.getDtlExpressId()+"");
				subOrder.setExpressNo(subOrder.getExpressNo());
				subOrder.setMerchantCode(od.getMerchantCode());
			}
			od.setUpdateDate(now);
			this.updateByPrimaryKeySelective(od);
		}
	}
	
	public void updateOrderDtls(List<OrderDtl> updateOrderDtlList) {
		Date now = new Date();
		for(OrderDtl od:updateOrderDtlList){
			od.setUpdateDate(now);
			this.updateByPrimaryKeySelective(od);
		}
	}

	public void updateDeliveryDateAndStatus(Map<String, Object> paramMap) {
		orderDtlCustomMapper.updateDeliveryDateAndDeliveryStatus(paramMap);
	}
	
	public void updateDeliveryDateAndDeliveryStatusByIds(Map<String, Object> paramMap) {
		orderDtlCustomMapper.updateDeliveryDateAndDeliveryStatusByIds(paramMap);
	}

	public List<OrderDtl> getList(int subOrderId) {
		return orderDtlCustomMapper.getList(subOrderId);
	}
	
	public void supplyAgain(JSONArray ja,SubOrder subOrder,Integer mchtUserId) {
		//TODO 将发货信息更新到所有商品明细发货状态中（包括快递名称、快递单号、发货时间）
		Date now = new Date();
		for(int i=0;i<ja.size();i++){
			JSONObject jo = (JSONObject)ja.get(i);
			String eachExpressId = jo.getString("expressId");
			String eachExpressNo = jo.getString("expressNo");
			String eachMerchantCode = jo.getString("merchantCode");
			String orderDtlIds = jo.getString("orderDtlIds");
			OrderDtl od = new OrderDtl();
			od.setDtlExpressId(Integer.parseInt(eachExpressId));
			od.setDtlExpressNo(eachExpressNo);
			od.setMerchantCode(eachMerchantCode);
			od.setDeliveryStatus("1");
			od.setDeliveryDate(now);
			od.setMarkedOutOfStock("0");
			od.setUpdateDate(now);
			od.setUpdateBy(mchtUserId);
			OrderDtlExample e = new OrderDtlExample();
			List<Integer> idList = new ArrayList<Integer>();
			for(String orderDtlId:orderDtlIds.split(",")){
				idList.add(Integer.parseInt(orderDtlId));
			}
			e.createCriteria().andDelFlagEqualTo("0").andIdIn(idList);
			this.updateByExampleSelective(od, e);
			KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
			KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andExpressIdEqualTo(Integer.parseInt(eachExpressId));
			criteria.andLogisticCodeEqualTo(eachExpressNo.trim());
			List<KdnWuliuInfo> kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
			KdnWuliuInfo kdnWuliuInfo = null;
			if(kdnWuliuInfos==null || kdnWuliuInfos.size()==0){
				kdnWuliuInfo = new KdnWuliuInfo();
				kdnWuliuInfo.setCreateBy(mchtUserId);
				kdnWuliuInfo.setCreateDate(new Date());
				kdnWuliuInfo.setDelFlag("0");
				kdnWuliuInfo.setExpressId(Integer.parseInt(eachExpressId));
				kdnWuliuInfo.setLogisticCode(eachExpressNo.trim());
				kdnWuliuInfo.setMerchantCode(eachMerchantCode.trim());
				kdnWuliuInfo.setSubscribeStatus("0");
				kdnWuliuInfo.setSubOrderId(subOrder.getId());
				kdnWuliuInfoMapper.insertSelective(kdnWuliuInfo);
			}else{
				kdnWuliuInfo = kdnWuliuInfos.get(0);
				if(kdnWuliuInfo.getSubOrderId()!=null&&subOrder.getId()>kdnWuliuInfo.getSubOrderId()){
					kdnWuliuInfo.setSubOrderId(subOrder.getId());
					kdnWuliuInfo.setMerchantCode(eachMerchantCode);
					kdnWuliuInfo.setUpdateBy(mchtUserId);
					kdnWuliuInfo.setUpdateDate(new Date());
				}else{
					if(kdnWuliuInfo.getSubOrderId()==null){
						kdnWuliuInfo.setSubOrderId(subOrder.getId());
						kdnWuliuInfo.setMerchantCode(eachMerchantCode);
						kdnWuliuInfo.setUpdateBy(mchtUserId);
						kdnWuliuInfo.setUpdateDate(new Date());
					}
				}
				kdnWuliuInfoMapper.updateByPrimaryKeySelective(kdnWuliuInfo);
			}
		}
	}


	public void supplyAgainNew(JSONArray ja,SubOrder subOrder,Integer mchtUserId) {
		//TODO 将发货信息更新到所有商品明细发货状态中（包括快递名称、快递单号、发货时间）
		Date now = new Date();
		for(int i=0;i<ja.size();i++){
			JSONObject jo = (JSONObject)ja.get(i);
			String eachExpressId = jo.getString("expressId");
			String eachExpressNo = jo.getString("expressNo");
			String eachMerchantCode = jo.getString("merchantCode");
			String orderDtlIds = jo.getString("orderDtlIds");
			OrderDtl od = new OrderDtl();
			od.setDtlExpressId(Integer.parseInt(eachExpressId));
			od.setDtlExpressNo(eachExpressNo);
			if (!StringUtil.isEmpty(eachMerchantCode)) {
				od.setMerchantCode(eachMerchantCode);
			}
			od.setDeliveryStatus("1");
			od.setDeliveryDate(now);
			od.setMarkedOutOfStock("0");
			od.setUpdateDate(now);
			od.setUpdateBy(mchtUserId);
			OrderDtlExample e = new OrderDtlExample();
			List<Integer> idList = new ArrayList<Integer>();
			for(String orderDtlId:orderDtlIds.split(",")){
				idList.add(Integer.parseInt(orderDtlId));
			}
			e.createCriteria().andDelFlagEqualTo("0").andIdIn(idList);
			this.updateByExampleSelective(od, e);
			KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
			KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andExpressIdEqualTo(Integer.parseInt(eachExpressId));
			criteria.andLogisticCodeEqualTo(eachExpressNo.trim());
			if (!StringUtil.isEmpty(eachMerchantCode)) {
				criteria.andMerchantCodeEqualTo(eachMerchantCode);
			}
			List<KdnWuliuInfo> kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
			KdnWuliuInfo kdnWuliuInfo = null;
			if(kdnWuliuInfos==null || kdnWuliuInfos.size()==0){
				kdnWuliuInfo = new KdnWuliuInfo();
				kdnWuliuInfo.setCreateBy(mchtUserId);
				kdnWuliuInfo.setCreateDate(new Date());
				kdnWuliuInfo.setDelFlag("0");
				kdnWuliuInfo.setExpressId(Integer.parseInt(eachExpressId));
				kdnWuliuInfo.setLogisticCode(eachExpressNo.trim());
				kdnWuliuInfo.setMerchantCode(eachMerchantCode.trim());
				kdnWuliuInfo.setSubscribeStatus("0");
				kdnWuliuInfo.setSubOrderId(subOrder.getId());
				kdnWuliuInfoMapper.insertSelective(kdnWuliuInfo);
			}else{
				kdnWuliuInfo = kdnWuliuInfos.get(0);
				if(kdnWuliuInfo.getSubOrderId()!=null&&subOrder.getId()>kdnWuliuInfo.getSubOrderId()){
					kdnWuliuInfo.setSubOrderId(subOrder.getId());
					kdnWuliuInfo.setUpdateBy(mchtUserId);
					kdnWuliuInfo.setUpdateDate(new Date());
				}else{
					if(kdnWuliuInfo.getSubOrderId()==null){
						kdnWuliuInfo.setSubOrderId(subOrder.getId());
						kdnWuliuInfo.setUpdateBy(mchtUserId);
						kdnWuliuInfo.setUpdateDate(new Date());
					}
				}
				kdnWuliuInfoMapper.updateByPrimaryKeySelective(kdnWuliuInfo);
			}
		}
	}
	
	public void editDelivery(JSONArray ja, int subOrderId,int updateBy) {
		//TODO 将发货信息更新到所有商品明细发货状态中（包括快递名称、快递单号、发货时间）,同时将最后一个商品明细的快递信息更新至订单发货信息中
		Date now = new Date();
		for(int i=0;i<ja.size();i++){
			JSONObject jo = (JSONObject)ja.get(i);
			String eachExpressId = jo.getString("expressId");
			String eachExpressNo = jo.getString("expressNo").trim();
			String eachMerchantCode = jo.getString("merchantCode").trim();
			String orderDtlIds = jo.getString("orderDtlIds");
			OrderDtl od = new OrderDtl();
			od.setDtlExpressId(Integer.parseInt(eachExpressId));
			od.setDtlExpressNo(eachExpressNo);
			od.setMerchantCode(eachMerchantCode);
			od.setDeliveryStatus("1");
			od.setDeliveryDate(now);
			od.setMarkedOutOfStock("0");
			od.setUpdateDate(now);
			OrderDtlExample e = new OrderDtlExample();
			List<Integer> idList = new ArrayList<Integer>();
			for(String orderDtlId:orderDtlIds.split(",")){
				idList.add(Integer.parseInt(orderDtlId));
			}
			e.createCriteria().andDelFlagEqualTo("0").andIdIn(idList);
			this.updateByExampleSelective(od, e);
			if(i==ja.size()-1){
				SubOrder so = new SubOrder();
				so.setExpressId(eachExpressId);
				so.setExpressNo(eachExpressNo);
				so.setMerchantCode(eachMerchantCode);
				so.setUpdateDate(now);
				SubOrderExample soe = new SubOrderExample();
				soe.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(subOrderId);
				subOrderService.updateByExampleSelective(so, soe);
			}
		}
		SubOrder subOrder = subOrderService.selectByPrimaryKey(subOrderId);
		KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
		KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andExpressIdEqualTo(Integer.parseInt(subOrder.getExpressId()));
		criteria.andLogisticCodeEqualTo(subOrder.getExpressNo());
		List<KdnWuliuInfo> kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
		KdnWuliuInfo kdnWuliuInfo = null;
		if(kdnWuliuInfos==null || kdnWuliuInfos.size()==0){
			kdnWuliuInfo = new KdnWuliuInfo();
			kdnWuliuInfo.setCreateBy(updateBy);
			kdnWuliuInfo.setCreateDate(new Date());
			kdnWuliuInfo.setDelFlag("0");
			kdnWuliuInfo.setExpressId(Integer.parseInt(subOrder.getExpressId()));
			kdnWuliuInfo.setLogisticCode(subOrder.getExpressNo());
			kdnWuliuInfo.setMerchantCode(subOrder.getMerchantCode());
			kdnWuliuInfo.setSubscribeStatus("0");
			kdnWuliuInfo.setSubOrderId(subOrder.getId());
		}else{
			kdnWuliuInfo = kdnWuliuInfos.get(0);
			if(kdnWuliuInfo.getSubOrderId()!=null&&subOrder.getId()>kdnWuliuInfo.getSubOrderId()){
				kdnWuliuInfo.setSubOrderId(subOrder.getId());
				kdnWuliuInfo.setMerchantCode(subOrder.getMerchantCode());
				kdnWuliuInfo.setUpdateBy(updateBy);
				kdnWuliuInfo.setUpdateDate(new Date());
			}else{
				if(kdnWuliuInfo.getSubOrderId()==null){
					kdnWuliuInfo.setSubOrderId(subOrder.getId());
					kdnWuliuInfo.setMerchantCode(subOrder.getMerchantCode());
					kdnWuliuInfo.setUpdateBy(updateBy);
					kdnWuliuInfo.setUpdateDate(new Date());
				}
			}
		}
		if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() == null){
			kdnWuliuInfoMapper.insertSelective(kdnWuliuInfo);
		}else if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() != null){
			kdnWuliuInfoMapper.updateByPrimaryKeySelective(kdnWuliuInfo);
		}
	}

	public void editDeliveryNew(JSONArray ja, int orderDtlId,int updateBy) {
		//TODO 将发货信息更新到所有商品明细发货状态中（包括快递名称、快递单号、发货时间）,同时将最后一个商品明细的快递信息更新至订单发货信息中
		Date now = new Date();
		for(int i=0;i<ja.size();i++){
			JSONObject jo = (JSONObject)ja.get(i);
			String eachExpressId = jo.getString("expressId");
			String eachExpressNo = jo.getString("expressNo").trim();
			String eachMerchantCode = jo.getString("merchantCode").trim();
			String orderDtlIds = jo.getString("orderDtlIds");
			OrderDtl od = this.selectByPrimaryKey(orderDtlId);	
			//是否修改子订单上的明细
			SubOrder subOrder = subOrderService.selectByPrimaryKey(od.getSubOrderId());
			if(od.getDtlExpressId().toString().equals(subOrder.getExpressId()) && od.getDtlExpressNo().equals(subOrder.getExpressNo())){
				subOrder.setExpressId(eachExpressId);
				subOrder.setExpressNo(eachExpressNo);
				subOrder.setMerchantCode(eachMerchantCode);
				subOrder.setUpdateDate(now);
				subOrder.setUpdateBy(updateBy);
				subOrderService.updateByPrimaryKey(subOrder);
			}
			od.setDtlExpressId(Integer.parseInt(eachExpressId));
			od.setDtlExpressNo(eachExpressNo);
			od.setMerchantCode(eachMerchantCode);
			od.setMarkedOutOfStock("0");
			od.setUpdateDate(now);
			od.setUpdateBy(updateBy);
			this.updateByPrimaryKey(od);
			
			
		}
		OrderDtl orderDtl = this.selectByPrimaryKey(orderDtlId);
		KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
		KdnWuliuInfoExample.Criteria criteria = kdnWuliuInfoExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		
		criteria.andExpressIdEqualTo(orderDtl.getDtlExpressId());
		criteria.andLogisticCodeEqualTo(orderDtl.getDtlExpressNo());
		List<KdnWuliuInfo> kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(kdnWuliuInfoExample);
		KdnWuliuInfo kdnWuliuInfo = null;
		if(kdnWuliuInfos==null || kdnWuliuInfos.size()==0){
			if(orderDtl.getDeliveryStatus().toString().equals("1")){
				kdnWuliuInfo = new KdnWuliuInfo();
				kdnWuliuInfo.setCreateBy(updateBy);
				kdnWuliuInfo.setCreateDate(new Date());
				kdnWuliuInfo.setDelFlag("0");
				kdnWuliuInfo.setExpressId(orderDtl.getDtlExpressId());
				kdnWuliuInfo.setLogisticCode(orderDtl.getDtlExpressNo());
				kdnWuliuInfo.setMerchantCode(orderDtl.getMerchantCode());
				kdnWuliuInfo.setSubscribeStatus("0");
				kdnWuliuInfo.setSubOrderId(orderDtl.getSubOrderId());
			}
		}else{
			kdnWuliuInfo = kdnWuliuInfos.get(0);
			if(kdnWuliuInfo.getSubOrderId()!=null&&orderDtl.getSubOrderId()>kdnWuliuInfo.getSubOrderId()){
				kdnWuliuInfo.setSubOrderId(orderDtl.getSubOrderId());
				kdnWuliuInfo.setExpressId(orderDtl.getDtlExpressId());
				kdnWuliuInfo.setLogisticCode(orderDtl.getDtlExpressNo());
				kdnWuliuInfo.setMerchantCode(orderDtl.getMerchantCode());
				kdnWuliuInfo.setUpdateBy(updateBy);
				kdnWuliuInfo.setUpdateDate(new Date());
			}else{
				if(kdnWuliuInfo.getSubOrderId()==null){
					kdnWuliuInfo.setSubOrderId(orderDtl.getSubOrderId());
					kdnWuliuInfo.setUpdateBy(updateBy);
					kdnWuliuInfo.setUpdateDate(new Date());
				}
			}
		}
		if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() == null){
			kdnWuliuInfoMapper.insertSelective(kdnWuliuInfo);
		}else if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() != null){
			kdnWuliuInfoMapper.updateByPrimaryKey(kdnWuliuInfo);
		}
	}
	//优化订单管理的查询数据 用子订单id集合
    public List<OrderDtlCustom> selectOrderDtlsBySubOrderIds(ArrayList<Integer> subOrderIds) {
		return  orderDtlCustomMapper.selectOrderDtlsBySubOrderIds(subOrderIds);
    }

	public List<OrderDtlCustom> selectByExampleCustom(int mchtSettleOrderId) {
		return orderDtlCustomMapper.selectByExampleCustom(mchtSettleOrderId);
	}
}
