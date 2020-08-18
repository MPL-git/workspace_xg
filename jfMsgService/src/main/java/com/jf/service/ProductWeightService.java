package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductWeightCustomMapper;
import com.jf.dao.ProductWeightMapper;
import com.jf.entity.Product;
import com.jf.entity.ProductWeight;
import com.jf.entity.ProductWeightExample;
import com.jf.entity.PvWeightCnf;
import com.jf.entity.PvWeightCnfExample;
import com.jf.entity.SaleWeightCnf;
import com.jf.entity.SaleWeightCnfExample;
import com.jf.entity.ThirdPlatformProductInfo;
import com.jf.entity.ThirdPlatformProductInfoExample;

@Service
@Transactional
public class ProductWeightService extends BaseService<ProductWeight, ProductWeightExample> {

	@Autowired
	private ProductWeightMapper productWeightMapper;

	@Autowired
	private ProductWeightCustomMapper productWeightCustomMapper;

	@Autowired
	private SaleWeightCnfService saleWeightCnfService;

	@Autowired
	private PvWeightCnfService pvWeightCnfService;
	
	@Autowired
	private ThirdPlatformProductInfoService thirdPlatformProductInfoService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	public void setProductWeightMapper(ProductWeightMapper productWeightMapper) {
		this.setDao(productWeightMapper);
		this.productWeightMapper = productWeightMapper;
	}

	public int insertProductWeights() {
		return productWeightCustomMapper.insertProductWeights();
	}

	/**
	 * 更新商家等级权重值
	 * 
	 * @param currentMonth
	 * @return
	 */
	public int updateProductMchtGradeWeights() {
		return productWeightCustomMapper.updateProductMchtGradeWeights();
	}

	/**
	 * 更新品牌权重值
	 * 
	 * @param currentMonth
	 * @return
	 */
	public int updateProductBrandGradeWeights() {
		return productWeightCustomMapper.updateProductBrandGradeWeights();
	}

	/**
	 * 更新季节权重值
	 * 
	 * @param currentMonth
	 * @return
	 */
	public int updateProductSeasonWeights(int currentMonth) {
		return productWeightCustomMapper.updateProductSeasonWeights(currentMonth);
	}

	/**
	 * 更新商品总权重重值
	 * 
	 * @param currentMonth
	 * @return
	 */
	public int updateProductWeightsTotal() {
		return productWeightCustomMapper.updateProductWeightsTotal();
	}

	/**
	 * 更新销量权重值
	 */
	public void updateProductSaleWeight() {
		SaleWeightCnfExample saleWeightCnfExample = new SaleWeightCnfExample();
		saleWeightCnfExample.createCriteria().andDelFlagEqualTo("0").andWeightTypeEqualTo("1");
		List<SaleWeightCnf> saleWeightCnfs = saleWeightCnfService.selectByExample(saleWeightCnfExample);
		HashMap<String, Object> paramMap;
		Date now = new Date();
		Map<Integer, Integer> saleWeightMap = new HashMap<Integer, Integer>();
		List<Integer> productIdList = new ArrayList<Integer>();

		if (saleWeightCnfs != null && saleWeightCnfs.size() > 0) {

			SaleWeightCnf saleWeightCnf = saleWeightCnfs.get(0);
			Integer days = saleWeightCnf.getDays();
			Integer baseValue = saleWeightCnf.getBaseValue();
			paramMap = new HashMap<String, Object>();
			paramMap.put("beginDate", DateUtil.getDateAfterAndBeginTime(now, -days));
			paramMap.put("endDate", DateUtil.getDateAfterAndEndTime(now, -1));
			List<HashMap<String, Object>> productMap = productWeightCustomMapper.selectProductSaleCount(paramMap);

			// 销售数量前十名的数量平均值
			BigDecimal top10SaleCountTotal = new BigDecimal(0);
			BigDecimal top10SaleCountAverage = new BigDecimal(0);
			if (productMap != null && productMap.size() > 0) {
				for (int i = 0; i < productMap.size(); i++) {
					if (i <= 9) {
						top10SaleCountTotal = top10SaleCountTotal.add((BigDecimal) productMap.get(i).get("totalQuantity"));
					} else {
						break;
					}
				}

				if (productMap.size() > 10) {
					top10SaleCountAverage = top10SaleCountTotal.divide(new BigDecimal(10),2,BigDecimal.ROUND_FLOOR);
				} else {
					top10SaleCountAverage = top10SaleCountTotal.divide(new BigDecimal(productMap.size()),2,BigDecimal.ROUND_FLOOR);
				}
				
				if(top10SaleCountAverage.compareTo(new BigDecimal(0))<=0){
					top10SaleCountAverage=new BigDecimal(1);
				}
				
				for(HashMap<String, Object> product:productMap){
					Integer productId=(Integer)product.get("productId");
					Integer productWeight=(((BigDecimal)product.get("totalQuantity")).divide(top10SaleCountAverage,10,BigDecimal.ROUND_FLOOR)).multiply(new BigDecimal(baseValue)).intValue();
					if(productWeight>baseValue){
						productWeight=baseValue;
					}
					saleWeightMap.put(productId, productWeight);
					productIdList.add(productId);
				}

			}
			ProductWeightExample productWeightExample;
			ProductWeight productWeight4Update;
			for (Integer productId : saleWeightMap.keySet()) {
				productWeightExample = new ProductWeightExample();
				productWeightExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId);
				productWeight4Update = new ProductWeight();
				productWeight4Update.setSaleWeight(saleWeightMap.get(productId));
				productWeight4Update.setUpdateDate(now);
				int count = this.updateByExampleSelective(productWeight4Update, productWeightExample);
				if (count == 0) {
					productWeight4Update.setCreateDate(now);
					productWeight4Update.setUpdateDate(now);
					productWeight4Update.setDelFlag("0");
					this.insertSelective(productWeight4Update);
				}
			}

			// 没销量的全部置为0
			productWeightExample = new ProductWeightExample();
			if(productIdList!=null&&productIdList.size()>0){
				productWeightExample.createCriteria().andProductIdNotIn(productIdList);
			}
			productWeight4Update = new ProductWeight();
			productWeight4Update.setSaleWeight(0);
			productWeight4Update.setUpdateDate(now);
			this.updateByExampleSelective(productWeight4Update, productWeightExample);
		}

	}
	
	
	
	/**
	 * 更新销售额权重值
	 */
	public void updateProductSaleAmountWeight() {
		SaleWeightCnfExample saleWeightCnfExample = new SaleWeightCnfExample();
		saleWeightCnfExample.createCriteria().andDelFlagEqualTo("0").andWeightTypeEqualTo("2");
		List<SaleWeightCnf> saleWeightCnfs = saleWeightCnfService.selectByExample(saleWeightCnfExample);
		HashMap<String, Object> paramMap;
		Date now = new Date();
		Map<Integer, Integer> saleAmountWeightMap = new HashMap<Integer, Integer>();
		List<Integer> productIdList = new ArrayList<Integer>();

		if (saleWeightCnfs != null && saleWeightCnfs.size() > 0) {

			SaleWeightCnf saleWeightCnf = saleWeightCnfs.get(0);
			Integer days = saleWeightCnf.getDays();
			Integer baseValue = saleWeightCnf.getBaseValue();
			paramMap = new HashMap<String, Object>();
			paramMap.put("beginDate", DateUtil.getDateAfterAndBeginTime(now, -days));
			paramMap.put("endDate", DateUtil.getDateAfterAndEndTime(now, -1));
			List<HashMap<String, Object>> productMap = productWeightCustomMapper.selectProductSaleAmount(paramMap);

			// 销售数量前十名的数量平均值
			BigDecimal top10SaleAmountTotal = new BigDecimal(0);
			BigDecimal top10SaleAmountAverage = new BigDecimal(0);
			if (productMap != null && productMap.size() > 0) {
				for (int i = 0; i < productMap.size(); i++) {
					if (i <= 9) {
						top10SaleAmountTotal = top10SaleAmountTotal.add((BigDecimal) productMap.get(i).get("totalAmount"));
					} else {
						break;
					}
				}

				if (productMap.size() > 10) {
					top10SaleAmountAverage = top10SaleAmountTotal.divide(new BigDecimal(10),2,BigDecimal.ROUND_FLOOR);
				} else {
					top10SaleAmountAverage = top10SaleAmountTotal.divide(new BigDecimal(productMap.size()),2,BigDecimal.ROUND_FLOOR);
				}
				
				if(top10SaleAmountAverage.compareTo(new BigDecimal(0))<=0){
					top10SaleAmountAverage=new BigDecimal(1);
				}
				
				for (HashMap<String, Object> product : productMap) {
					Integer productId = (Integer) product.get("productId");
					Integer productWeight = (((BigDecimal) product.get("totalAmount")).divide(top10SaleAmountAverage, 10, BigDecimal.ROUND_FLOOR)).multiply(new BigDecimal(baseValue)).intValue();
					if (productWeight > baseValue) {
						productWeight = baseValue;
					}
					saleAmountWeightMap.put(productId, productWeight);
					productIdList.add(productId);
				}

			}
			ProductWeightExample productWeightExample;
			ProductWeight productWeight4Update;
			for (Integer productId : saleAmountWeightMap.keySet()) {
				productWeightExample = new ProductWeightExample();
				productWeightExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId);
				productWeight4Update = new ProductWeight();
				productWeight4Update.setSaleAmountWeight(saleAmountWeightMap.get(productId));
				productWeight4Update.setUpdateDate(now);
				int count = this.updateByExampleSelective(productWeight4Update, productWeightExample);
				if (count == 0) {
					productWeight4Update.setCreateDate(now);
					productWeight4Update.setUpdateDate(now);
					productWeight4Update.setDelFlag("0");
					this.insertSelective(productWeight4Update);
				}
			}

			// 没销量的全部置为0
			productWeightExample = new ProductWeightExample();
			if(productIdList!=null&&productIdList.size()>0){
				productWeightExample.createCriteria().andProductIdNotIn(productIdList);
			}
			productWeight4Update = new ProductWeight();
			productWeight4Update.setSaleAmountWeight(0);
			productWeight4Update.setUpdateDate(now);
			this.updateByExampleSelective(productWeight4Update, productWeightExample);
		}

	}
	
	
	
	
	

	/**
	 * 更新点击量权重值
	 */
	public void updateProductPvWeight() {
		PvWeightCnfExample pvWeightCnfExample = new PvWeightCnfExample();
		pvWeightCnfExample.createCriteria().andDelFlagEqualTo("0");
		pvWeightCnfExample.setOrderByClause("weight desc");
		List<PvWeightCnf> pvWeightCnfs = pvWeightCnfService.selectByExample(pvWeightCnfExample);
		HashMap<String, Object> paramMap;
		Date now = new Date();
		Map<Integer, Integer> pvWeightMap = new HashMap<Integer, Integer>();
		List<Integer> productIdList = new ArrayList<Integer>();
		for (PvWeightCnf pvWeightCnf : pvWeightCnfs) {
			Integer days = pvWeightCnf.getDays();
			Integer weight = pvWeightCnf.getWeight();
			paramMap = new HashMap<String, Object>();
			paramMap.put("beginDate", DateUtil.getDateAfterAndBeginTime(now, -days));
			paramMap.put("endDate", DateUtil.getDateAfterAndEndTime(now, -1));
			paramMap.put("pv", pvWeightCnf.getPv());
			List<HashMap<String, Object>> productMap = productWeightCustomMapper.selectProductPvCount(paramMap);
			if (productMap != null && productMap.size() > 0) {
				for (HashMap<String, Object> product : productMap) {
					Integer productId = (Integer) product.get("productId");
					if (pvWeightMap.get(productId) == null || weight > pvWeightMap.get(productId)) {
						pvWeightMap.put(productId, weight);
					}
					productIdList.add(productId);
				}
			}
		}
		ProductWeightExample productWeightExample;
		ProductWeight productWeight4Update;
		for (Integer productId : pvWeightMap.keySet()) {
			productWeightExample = new ProductWeightExample();
			productWeightExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId);
			productWeight4Update = new ProductWeight();
			productWeight4Update.setPvWeight(pvWeightMap.get(productId));
			int count = this.updateByExampleSelective(productWeight4Update, productWeightExample);
			if (count == 0) {
				productWeight4Update.setCreateDate(now);
				productWeight4Update.setDelFlag("0");
				this.insertSelective(productWeight4Update);
			}
		}

		// 没点击量的全部置为0
		productWeightExample = new ProductWeightExample();
		productWeightExample.createCriteria().andProductIdNotIn(productIdList);
		productWeight4Update = new ProductWeight();
		productWeight4Update.setPvWeight(0);
		productWeight4Update.setUpdateDate(now);
		this.updateByExampleSelective(productWeight4Update, productWeightExample);

	}
	
	
	/**
	 * 更新商品评价权重值
	 */
	public void updateProductCommentWeight() {
		productWeightCustomMapper.updateProductCommentWeight();
	}
	
	/**
	 * 更新第三方平台商品的权重
	 * 
	 */
	public void updateThirdPlatformProductWeight(){
		Date now = new Date();
		//淘客商品在30天更新的且上架的 销量前10的平均值
		Date beginDate = DateUtil.getDateAfter(now, -30);
		ThirdPlatformProductInfoExample thirdPlatformProductInfoExample = new ThirdPlatformProductInfoExample();
		thirdPlatformProductInfoExample.createCriteria().andDelFlagEqualTo("0").andUpdateDateGreaterThan(beginDate);
		thirdPlatformProductInfoExample.setOrderByClause(" volume desc");
		thirdPlatformProductInfoExample.setLimitStart(0);
		thirdPlatformProductInfoExample.setLimitSize(10);
		List<ThirdPlatformProductInfo> thirdPlatformProductInfoList = thirdPlatformProductInfoService.selectByExample(thirdPlatformProductInfoExample);
		
		int averagevolume = 0;//淘客商品在30天更新的且上架的 销量前10的平均值
		if(thirdPlatformProductInfoList!=null&&thirdPlatformProductInfoList.size()>0){
			long totalVolume = 0;
			for (ThirdPlatformProductInfo thirdPlatformProductInfo:thirdPlatformProductInfoList) {
				totalVolume = totalVolume + thirdPlatformProductInfo.getVolume();
			}
			averagevolume = (int)(totalVolume/thirdPlatformProductInfoList.size());
		}
		
		//更新商品权重值，只更新2天内有跟新的商品
		thirdPlatformProductInfoExample = new ThirdPlatformProductInfoExample();
		thirdPlatformProductInfoExample.createCriteria().andDelFlagEqualTo("0").andUpdateDateGreaterThan(DateUtil.getDateAfter(now, -2));
		thirdPlatformProductInfoList = thirdPlatformProductInfoService.selectByExample(thirdPlatformProductInfoExample);
		for(ThirdPlatformProductInfo thirdPlatformProductInfo:thirdPlatformProductInfoList){
			try {
				//销量权重 k1=INT( (m/A)*200 )   m：商品在30天销量   A:淘客商品在30天更新的且上架的 销量前10的平均值,即averagevolume
				BigDecimal k1 = BigDecimal.ZERO;
				if(averagevolume>0){
					k1 = new BigDecimal(thirdPlatformProductInfo.getVolume()).divide(new BigDecimal(averagevolume),2,BigDecimal.ROUND_FLOOR);
				}



//			优惠券金额：  K2= 优惠券金额*50/商品原价
//			优惠券有效期：K3=N天/5，封顶10分
//			优惠券剩余数量：K4=N张/50，封顶10分
				BigDecimal k2 = BigDecimal.ZERO;
				BigDecimal k3 = BigDecimal.ZERO;
				BigDecimal k4 = BigDecimal.ZERO;
				if(!StringUtil.isEmpty(thirdPlatformProductInfo.getCouponEndTime())){
					Date couponEndTime = DateUtil.getDate(thirdPlatformProductInfo.getCouponEndTime(),"yyyy-MM-dd");
					Date couponStartTime = DateUtil.getDate(thirdPlatformProductInfo.getCouponStartTime(),"yyyy-MM-dd");
					if(couponStartTime!=null&&couponEndTime!=null&&couponEndTime.getTime()>now.getTime()){

						if(thirdPlatformProductInfo.getCouponAmount()!=null&&thirdPlatformProductInfo.getCouponAmount().compareTo(BigDecimal.ZERO)==1){
							k2 = thirdPlatformProductInfo.getCouponAmount().multiply(new BigDecimal(50)).divide(thirdPlatformProductInfo.getZkFinalPrice(),2,BigDecimal.ROUND_FLOOR);
						}else{
							String couponInfo = thirdPlatformProductInfo.getCouponInfo();
							if(couponInfo!=null){
								String couponMoney = StringUtil.getSubMatchStr(couponInfo, "减(.*)元",1);
								if(!StringUtil.isEmpty(couponMoney)){
									k2 = new BigDecimal(couponMoney).multiply(new BigDecimal(50)).divide(thirdPlatformProductInfo.getZkFinalPrice(),2,BigDecimal.ROUND_FLOOR);
								}
							}
						}
						
						int days = (int) ((couponEndTime.getTime() - couponStartTime.getTime()) / (1000*3600*24));//有效期剩余天数
						k3 = new BigDecimal(days/5);
						if(k3.compareTo(new BigDecimal(10))==1){
							k3 = new BigDecimal(10);
						}
						
						
						if(thirdPlatformProductInfo.getCouponRemainCount()!=null){
							k4 = new BigDecimal(thirdPlatformProductInfo.getCouponRemainCount()/50);
							if(k4.compareTo(new BigDecimal(10))==1){
								k4 = new BigDecimal(10);
							}
						}
						
					}
				}
				


				
//			佣金比例：K5=佣金比例*50
				BigDecimal k5 = BigDecimal.ZERO;
				if(!StringUtil.isEmpty(thirdPlatformProductInfo.getCommissionRate())){
					k5 = new BigDecimal(thirdPlatformProductInfo.getCommissionRate()).divide(new BigDecimal(200),0,BigDecimal.ROUND_FLOOR);//数据库存的数值以万1为单位，所以除10000乘50
				}
				
				BigDecimal k = k1.add(k2).add(k3).add(k4).add(k5).setScale(0, BigDecimal.ROUND_FLOOR);
				ThirdPlatformProductInfo thirdPlatformProductInfo4Update = new ThirdPlatformProductInfo();
				thirdPlatformProductInfo4Update.setId(thirdPlatformProductInfo.getId());
				thirdPlatformProductInfo4Update.setWeights(k);
				thirdPlatformProductInfoService.updateByPrimaryKeySelective(thirdPlatformProductInfo4Update);
				
				Product product4Update = new Product();
				product4Update.setId(thirdPlatformProductInfo.getProductId());
				product4Update.setWeights(k.intValue());
				productService.updateByPrimaryKeySelective(product4Update);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
