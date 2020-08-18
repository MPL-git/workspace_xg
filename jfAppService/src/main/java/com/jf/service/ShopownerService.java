package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.dao.ShopownerMapper;
import com.jf.entity.Shopowner;
import com.jf.entity.ShopownerExample;
import com.jf.entity.SysParamCfg;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopownerService extends BaseService<Shopowner, ShopownerExample> {
	@Autowired
	private ShopownerMapper shopownerMapper;
	@Resource
	private OrderService orderService;
	
	@Autowired
	public void setShopownerMapper(ShopownerMapper shopownerMapper) {
		this.setDao(shopownerMapper);
		this.shopownerMapper = shopownerMapper;
	}
	public Shopowner findModelByMemberId(Integer memberId) {
		Shopowner shopowner = null;
		ShopownerExample shopownerExample = new ShopownerExample();
		shopownerExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		List<Shopowner> shopowners = selectByExample(shopownerExample);
		if(CollectionUtils.isNotEmpty(shopowners)){
			shopowner = shopowners.get(0);
		}
		return shopowner;
	}

	/**
	 * 计算店长优惠
	 *
	 * @param reqPRM
	 * @param memberId
	 * @param salePriceMapList	所有规格已有的优惠信息
	 * @param activityTotalAmountMap	各activityType对应的活动总金额（已扣除定金抵扣金额、单品优惠金额、会场或商家优惠、平台优惠），可能有多扣了一次定金
	 * @param tType
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 */
	public Map<String, Object> computingShopownerEquity(JSONObject reqPRM, Integer memberId, List<Map<String, Object>> salePriceMapList, Map<String, BigDecimal> activityTotalAmountMap, String tType) {
		BigDecimal zero = new BigDecimal("0"); 
		BigDecimal totalShopwnerEquityAmount = zero; 
		BigDecimal shopwnerEquityAmount = zero; 
		BigDecimal spreadAmountRate = zero;
		Shopowner shopowner = findModelByMemberId(memberId);
		if(shopowner != null){
			List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg("APP_DISTRIBUTION_RATE");
			if(CollectionUtils.isNotEmpty(cfgs)){
				spreadAmountRate = new BigDecimal(cfgs.get(0).getParamValue());
			}
			if (CollectionUtils.isNotEmpty(salePriceMapList) && spreadAmountRate.compareTo(zero) > 0) {
				for (Map<String, Object> map : salePriceMapList) {
					BigDecimal productPreAmountDtl = new BigDecimal(map.get("productPreAmountDtl").toString());
					BigDecimal salePrice = new BigDecimal(map.get("salePrice").toString());
					BigDecimal platformAmount = new BigDecimal(map.get("platformAmount").toString());
					BigDecimal quantity = new BigDecimal(map.get("quantity").toString());
					if (map.get("popFeeRate") == null) {
						continue;
					}
					BigDecimal popFeeRate = new BigDecimal(map.get("popFeeRate").toString());
					String activityType = map.get("activityType").toString();
					BigDecimal saleOrMallPrice = salePrice.multiply(quantity).subtract(productPreAmountDtl).subtract(platformAmount);
					shopwnerEquityAmount = saleOrMallPrice.multiply(popFeeRate).multiply(spreadAmountRate).setScale(2, BigDecimal.ROUND_DOWN);
					map.put("shopwnerEquityAmount", shopwnerEquityAmount);
					map.put("platformAmount", platformAmount.add(shopwnerEquityAmount));
					totalShopwnerEquityAmount = totalShopwnerEquityAmount.add(shopwnerEquityAmount);
					if("1".equals(tType)){
						Integer orderDtlId = Integer.parseInt(map.get("orderDtlId").toString());
						orderService.saveOrderPreferentialInfo("10", orderDtlId,orderDtlId, "1", "【店长权益】", shopwnerEquityAmount, Const.COUPON_BELONG_TYPE_PLATFORM, memberId);
					}
					activityTotalAmountMap.put(activityType, activityTotalAmountMap.get(activityType).subtract(shopwnerEquityAmount));
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("salePriceMapList", salePriceMapList);
		map.put("totalShopwnerEquityAmount", totalShopwnerEquityAmount);
		map.put("activityTotalAmountMap", activityTotalAmountMap);
		return map;
	}
	
	
}
