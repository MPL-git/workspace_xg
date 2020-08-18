package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtProductBrandMapper;
import com.jf.entity.Activity;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.ProductFeeRate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月11日 上午10:29:53 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MchtProductBrandService extends BaseService<MchtProductBrand, MchtProductBrandExample> {
	
	@Autowired
	private MchtProductBrandMapper mchtProductBrandMapper;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private MchtInfoService mchtInfoService;
	@Autowired
	private ProductFeeRateService productFeeRateService;

	@Autowired
	public void setMchtProductBrandMapper(MchtProductBrandMapper mchtProductBrandMapper) {
		this.setDao(mchtProductBrandMapper);
		this.mchtProductBrandMapper = mchtProductBrandMapper;
	}
	/**
	 * 获取技术服务费流程
	 * 商品费率-->特卖活动表-->商家品牌表-->三级类目-->二级类目-->一级类目
	 * @param paramsMap
	 * @return
	 */
	public BigDecimal getPopFeeRate(Map<String, Object> paramsMap) {
		BigDecimal popFeeRate = new BigDecimal("0");
		BigDecimal zero = new BigDecimal("0");
		Integer activityId = null;
		Integer productId = null;
		Integer mchtId = Integer.parseInt(paramsMap.get("mchtId").toString());
		Integer brandId = Integer.parseInt(paramsMap.get("brandId").toString());
		Integer productType1Id = Integer.parseInt(paramsMap.get("productType1Id").toString());
		Integer productType2Id = Integer.parseInt(paramsMap.get("productType2Id").toString());
		Integer productType3Id = Integer.parseInt(paramsMap.get("productType3Id").toString());
		if(paramsMap.containsKey("productId") && paramsMap.get("productId") != null && !StringUtil.isBlank(paramsMap.get("productId").toString())){
			productId = Integer.parseInt(paramsMap.get("productId").toString());
			ProductFeeRate productFeeRate = productFeeRateService.findModelByProductId(productId);
			if(productFeeRate != null && productFeeRate.getFeeRate() != null && productFeeRate.getFeeRate().compareTo(zero) > 0){
				return productFeeRate.getFeeRate();
			}
		}
		if(paramsMap.containsKey("activityId") && paramsMap.get("activityId") != null && !StringUtil.isBlank(paramsMap.get("activityId").toString())){
			activityId = Integer.parseInt(paramsMap.get("activityId").toString());
			Activity activity = activityService.selectByPrimaryKey(activityId);
			if(activity != null && activity.getFeeRate() != null && activity.getFeeRate().compareTo(zero) > 0){
				popFeeRate = activity.getFeeRate();
				return popFeeRate;
			}
		}
		
		MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
		mchtProductBrandExample.createCriteria().andMchtIdEqualTo(mchtId)
		.andProductBrandIdEqualTo(brandId).andDelFlagEqualTo("0").andStatusEqualTo("1").andPopCommissionRateIsNotNull().andPopCommissionRateGreaterThan(zero);
		List<MchtProductBrand> mchtProductBrands = selectByExample(mchtProductBrandExample);
		if(CollectionUtils.isNotEmpty(mchtProductBrands)){
			popFeeRate = mchtProductBrands.get(0).getPopCommissionRate();
		}else{
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			String settledType = mchtInfo.getSettledType();//入驻类型1.企业公司2.个体工商
			popFeeRate = productTypeService.getCategoryPopRateBysettledType(productType3Id,settledType);//三级类目佣金比例
			if(popFeeRate == null || popFeeRate.compareTo(zero) <= 0){
				popFeeRate = productTypeService.getCategoryPopRateBysettledType(productType2Id,settledType);//二级类目佣金比例
				if(popFeeRate == null || popFeeRate.compareTo(zero) <= 0){
					popFeeRate = productTypeService.getCategoryPopRateBysettledType(productType1Id,settledType);//一级类目佣金比例
					if(popFeeRate == null || popFeeRate.compareTo(zero) <= 0){
						throw new ArgException("商品库存不足");
					}
				}
			}
		}
		return popFeeRate;
	}
	
}
