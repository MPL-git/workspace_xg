package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SubOrderCustomMapper;
import com.jf.dao.SubOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.OrderLog;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderCustom;
import com.jf.entity.SubOrderCustomExample;
import com.jf.entity.SubOrderExample;
import com.jf.entity.SysAppMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class SubOrderService extends BaseService<SubOrder,SubOrderExample> {
	@Autowired
	private SubOrderMapper dao;
	
	@Autowired
	private SubOrderCustomMapper subOrderCustomMapper;
	
	@Autowired
	private OrderLogService orderLogService;
	
	@Autowired
	private KdnWuliuInfoService kdnWuliuInfoService;
	
	@Autowired
	private OrderDtlService orderDtlService;
	
	@Autowired
	private CombineOrderService combineOrderService;
	
	@Autowired
	private SysAppMessageService sysAppMessageService;
	
	@Autowired
	public void setSubOrderMapper(SubOrderMapper subOrderMapper) {
		super.setDao(subOrderMapper);
		this.dao = subOrderMapper;
	}

	public int countSubOrderCustomByExample(SubOrderCustomExample example) {
		return subOrderCustomMapper.countByExample(example);
	}

	public List<SubOrderCustom> selectSubOrderCustomByExample(SubOrderCustomExample example) {
		return subOrderCustomMapper.selectByExample(example);
	}

	public SubOrderCustom selectSubOrderCustomByPrimaryKey(Integer id) {
		return subOrderCustomMapper.selectByPrimaryKey(id);
	}
	
	public void updateSubOrderAndOrderLog(SubOrder subOrder, OrderLog orderLog,List<KdnWuliuInfo> kdnWuliuInfoList,SysAppMessage sysAppMessage,JSONArray ja) {
		this.updateByPrimaryKeySelective(subOrder);
		orderLogService.insertSelective(orderLog);
		for(KdnWuliuInfo kdnWuliuInfo:kdnWuliuInfoList){
			if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() == null){
				kdnWuliuInfoService.save(kdnWuliuInfo);
			}else if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() != null){
				kdnWuliuInfoService.updateByPrimaryKeySelective(kdnWuliuInfo);
			}
		}
//		sysAppMessageService.insertSelective(sysAppMessage);
		//TODO 将发货信息更新到所有商品明细发货状态中（包括快递名称、快递单号、发货时间）
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<ja.size();i++){
			JSONObject jo = (JSONObject)ja.get(i);
			String eachExpressId = jo.getString("expressId");
			String eachExpressNo = jo.getString("expressNo");
			String eachMerchantCode = jo.getString("merchantCode");
			String orderDtlIds = jo.getString("orderDtlIds");
			OrderDtl od = new OrderDtl();
			od.setDtlExpressId(Integer.parseInt(eachExpressId));
			od.setDtlExpressNo(eachExpressNo);
			od.setDeliveryStatus("1");
			od.setDeliveryDate(new Date());
			od.setMarkedOutOfStock("0");
			od.setUpdateDate(new Date());
			if (!StringUtil.isEmpty(eachMerchantCode)) {
				od.setMerchantCode(eachMerchantCode);
			}
			OrderDtlExample e = new OrderDtlExample();
			List<Integer> idList = new ArrayList<Integer>();
			for(String orderDtlId:orderDtlIds.split(",")){
				idList.add(Integer.parseInt(orderDtlId));
			}
			e.createCriteria().andDelFlagEqualTo("0").andIdIn(idList);
			orderDtlService.updateByExampleSelective(od, e);
		}
	}

	public List<SubOrder> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<SubOrder> paginate(QueryObject queryObject) {
		SubOrderExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<SubOrder> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private SubOrderExample builderQuery(QueryObject queryObject) {
		SubOrderExample example = new SubOrderExample();
		SubOrderExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("delFlag") != null){
			criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("mchtType") != null){
			criteria.andMchtTypeEqualTo(queryObject.getQueryToStr("mchtType"));
		}
		if(queryObject.getQuery("subOrderCode") != null){
			criteria.andSubOrderCodeLike("%" + queryObject.getQueryToStr("subOrderCode") + "%");
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public int countWaitDelivery(Integer mchtId, String status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mchtId", mchtId);
		map.put("status", status);
		return subOrderCustomMapper.countWaitDelivery(map);
	}

	public int countConfirmDelivery(Integer mchtId,	String status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mchtId", mchtId);
		map.put("status", status);
		return subOrderCustomMapper.countConfirmDelivery(map);
	}

	public int countHasDelivery(Integer mchtId, String status,String deliveryDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mchtId", mchtId);
		map.put("status", status);
		map.put("deliveryDate", deliveryDate);
		return subOrderCustomMapper.countHasDelivery(map);
	}

	public void updateExpressId(String expressId,String[] subOrderIdsArray) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("expressId", expressId);
		map.put("subOrderIdsArray", subOrderIdsArray);
		subOrderCustomMapper.updateExpressId(map);
	}

	public void updateDeliveryDateAndStatus(List<Integer> orderDtlIds,String status,String deliveryDate,List<KdnWuliuInfo> kdnWuliuInfos,List<SysAppMessage> sysAppMessages) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		for (Integer orderDtlId : orderDtlIds) {
			OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(orderDtlId);
			orderDtl.setDeliveryDate(date);
			orderDtl.setDeliveryStatus("1");
			orderDtl.setMarkedOutOfStock("0");
			orderDtlService.updateByPrimaryKeySelective(orderDtl);
			SubOrder subOrder = this.selectByPrimaryKey(orderDtl.getSubOrderId());

			if(subOrder.getStatus().equals("1")){
				subOrder.setStatus("2");
				subOrder.setStatusDate(new Date());
				if(StringUtil.isEmpty(subOrder.getExpressId())&&StringUtil.isEmpty(subOrder.getExpressNo())){
					subOrder.setExpressId(orderDtl.getDtlExpressId()+"");
					subOrder.setExpressNo(orderDtl.getDtlExpressNo());
					subOrder.setMerchantCode(orderDtl.getMerchantCode());
				}
				subOrder.setDeliveryDate(date);
				updateByPrimaryKeySelective(subOrder);
				
				OrderLog ol = new OrderLog();
				ol.setDelFlag("0");
				ol.setCreateDate(date);
				ol.setStatus("2");
				ol.setSubOrderId(subOrder.getId());
				orderLogService.insertSelective(ol);
			}		
			
		}
		
		for(KdnWuliuInfo kdnWuliuInfo:kdnWuliuInfos){
			if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() ==null){
				kdnWuliuInfoService.save(kdnWuliuInfo);
			}else if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() !=null){
				kdnWuliuInfoService.updateByPrimaryKeySelective(kdnWuliuInfo);
			}
		}
//		for(SysAppMessage sysAppMessage:sysAppMessages){
//			sysAppMessageService.insertSelective(sysAppMessage);
//		}
		
	}

	public void updateSubOrders(List<SubOrder> updateList) {
		Date now = new Date();
		for(SubOrder subOrder:updateList){
			this.updateByPrimaryKeySelective(subOrder);
			OrderDtl od = new OrderDtl();
			od.setUpdateDate(now);
			od.setDtlExpressId(Integer.parseInt(subOrder.getExpressId()));
			od.setDtlExpressNo(subOrder.getExpressNo());
			if(!StringUtil.isEmpty(subOrder.getMerchantCode())){
				od.setMerchantCode(subOrder.getMerchantCode());
			}
			OrderDtlExample e = new OrderDtlExample();
			e.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrder.getId());
			orderDtlService.updateByExampleSelective(od, e);
		}
	}

	public void updateSubOrder(SubOrder subOrder, KdnWuliuInfo kdnWuliuInfo) {
		this.updateByPrimaryKeySelective(subOrder);
		if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() == null){
			kdnWuliuInfoService.save(kdnWuliuInfo);
		}else if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() != null){
			kdnWuliuInfoService.updateByPrimaryKeySelective(kdnWuliuInfo);
		}
	}

	public void updateSubOrders(List<SubOrder> updateList,List<KdnWuliuInfo> kdnWuliuInfos) {
		for(SubOrder subOrder:updateList){
			this.updateByPrimaryKeySelective(subOrder);
			OrderDtlExample example = new OrderDtlExample();
			example.createCriteria().andSubOrderIdEqualTo(subOrder.getId()).andDelFlagEqualTo("0").andProductStatusDateIsNull();
			List<OrderDtl> list = orderDtlService.selectByExample(example);
			for (OrderDtl orderDtl : list) {
				orderDtl.setDtlExpressId(Integer.parseInt(subOrder.getExpressId()));
				orderDtl.setDtlExpressNo(subOrder.getExpressNo());
				orderDtl.setMerchantCode(subOrder.getMerchantCode());
				orderDtlService.updateByPrimaryKeySelective(orderDtl);
			}
		}
		for(KdnWuliuInfo kdnWuliuInfo:kdnWuliuInfos){
			if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() == null){
				kdnWuliuInfoService.save(kdnWuliuInfo);
			}else if(kdnWuliuInfo!=null && kdnWuliuInfo.getId() != null){
				kdnWuliuInfoService.updateByPrimaryKeySelective(kdnWuliuInfo);
			}
		}
	}

	public void save(OrderDtl orderDtl, SubOrder subOrder,CombineOrder combineOrder) {
		orderDtlService.updateByPrimaryKeySelective(orderDtl);
		this.updateByPrimaryKeySelective(subOrder);
		combineOrderService.updateByPrimaryKeySelective(combineOrder);
	}
	
	
	//订单号查子订单id集合
	public String selectBySubOrderCodes(List<String> list){
		return subOrderCustomMapper.selectBySubOrderCodes(list);
	};
	
	//订单号查询memberids集合
	public String selectMemberIdsBySubOrderCodes(@Param("socList")List<String> list){
		return subOrderCustomMapper.selectMemberIdsBySubOrderCodes(list);
	}
	
	//订单ids得到订单号集合subOrderIdsList
	public List<String>  selectSubOrderCodesBySubOrderIds(@Param("subOrderIdsList")List<String> list){
		return subOrderCustomMapper.selectSubOrderCodesBySubOrderIds(list);
	}

	//校验快递物流单号
    public Boolean verifyExpressNo(String expressNo, Integer expressId) {
		Boolean flag = false;
		String regex = expressNo.substring(0, 1) + "{" + expressNo.length() + "}";
		if(expressNo.matches("^[a-z0-9A-Z]+$") && !expressNo.matches("[a-zA-Z]+")
				&& !expressNo.matches(regex) && expressNo.length() > 5
				&& expressNo.length() <= 30){
			if(Objects.equals(4, expressId) && expressNo.length() < 8){//申通快递单号的长度，不可能小于8位
				flag = false;
			}else if(Objects.equals(20, expressId) && expressNo.length() < 9){//京东快递单号的长度，不可能小于9位
				flag = false;
			}else if(Objects.equals(10, expressId) && expressNo.length() < 12){//天天快递单号的长度，不可能小于12位
				flag = false;
			}else{
				flag = true;
			}
		}else{
			flag = false;
		}
		return flag;
    }
}
