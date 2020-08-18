package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CutPriceCnfMapper;
import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfDtl;
import com.jf.entity.CutPriceCnfExample;
import com.jf.entity.CutPriceOrder;

@Service
@Transactional
public class CutPriceCnfService extends BaseService<CutPriceCnf, CutPriceCnfExample> {
	@Autowired
	private CutPriceCnfMapper cutPriceCnfMapper;
	@Autowired
	private CutPriceCnfDtlService cutPriceCnfDtlService;
	@Autowired
	private CutPriceOrderService cutPriceOrderService;
	@Autowired
	private CutPriceOrderDtlService cutPriceOrderDtlService;
	@Autowired
	public void setCutPriceCnfMapper(CutPriceCnfMapper cutPriceCnfMapper) {
		this.setDao(cutPriceCnfMapper);
		this.cutPriceCnfMapper = cutPriceCnfMapper;
	}
	public Map<String, Object> getAssistanceCnfInfo(String memberId, Integer singleProductActivityId) {
		//判断当前活动用户是否以领取
		String taskStatus = "0";
		Date beginDate = null;
		Date endDate = null;
		Integer cutOrderId = null;
		String productItemId = "";
		int assistanceNum = 0;
		String status = "";
		List<CutPriceOrder> cutPriceOrders = new ArrayList<>();
		if(!StringUtil.isBlank(memberId)){
			cutPriceOrders = cutPriceOrderService.findModelBySingleActivityId(singleProductActivityId,Integer.valueOf(memberId));
			if(CollectionUtils.isNotEmpty(cutPriceOrders)){
				taskStatus = "0";
				beginDate = cutPriceOrders.get(0).getCreateDate();
				status = cutPriceOrders.get(0).getStatus();
				cutOrderId = cutPriceOrders.get(0).getId();
				productItemId = cutPriceOrders.get(0).getProductItemId().toString();
			}
		}
		List<CutPriceCnf> cnfs = findModelBySingleActivityId(singleProductActivityId);
		CutPriceCnf cnf = cnfs.get(0);
		List<CutPriceCnfDtl> cnfDtls = cutPriceCnfDtlService.findModelByCnfId(cnf.getId());
		CutPriceCnfDtl cnfDtl = cnfDtls.get(0);
		Integer maxInviteTimes = cnf.getMaxInviteTimes();
		Integer activeTime = cnf.getActiveTime();
		BigDecimal fixedAmount = cnfDtl.getFixedAmount();
		if(cutOrderId != null){
			endDate = DateUtil.addHour(beginDate, activeTime);
			Map<String,String> taskStatusMap = cutPriceOrderService.getAssistanceTaskStatus(cutPriceOrders.get(0),activeTime);
			taskStatus = taskStatusMap.get("taskStatus");
			if(!"2".equals(taskStatus) && !"4".equals(taskStatus)){
				Map<String, Object> paramsMap = new HashMap<>();
				paramsMap.put("activityType", Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE);
				paramsMap.put("cutOrderId", cutOrderId);
				paramsMap.put("type", "1");
				assistanceNum = cutPriceOrderDtlService.getAlreadyInvitedCount(paramsMap);
				if(assistanceNum > maxInviteTimes){
					assistanceNum = maxInviteTimes;
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("cutOrderId", cutOrderId);
		map.put("assistanceNum", assistanceNum);
		map.put("maxInviteTimes", maxInviteTimes);
		map.put("fixedAmount", fixedAmount);
		map.put("taskStatus", taskStatus);
		map.put("taskBeginDate", beginDate);
		map.put("taskEndDate", endDate);
		map.put("productItemId", productItemId);
		return map;
	}
	public List<CutPriceCnf> findModelBySingleActivityId(Integer singleProductActivityId) {
		CutPriceCnfExample cutPriceCnfExample = new CutPriceCnfExample();
		cutPriceCnfExample.createCriteria().andSingleProductActivityIdEqualTo(singleProductActivityId).andDelFlagEqualTo("0");
		
		return selectByExample(cutPriceCnfExample);
	}
	
}
