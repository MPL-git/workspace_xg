package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.dao.BgModuleCategoryMapper;
import com.jf.entity.BgModuleCategory;
import com.jf.entity.BgModuleCategoryExample;
import com.jf.entity.ProductCustom;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年7月19日 上午11:54:47 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class BgModuleCategoryService extends BaseService<BgModuleCategory,BgModuleCategoryExample> {
	@Autowired
	private BgModuleCategoryMapper bgModuleCategoryMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	public void setBgModuleCategoryMapper(BgModuleCategoryMapper bgModuleCategoryMapper) {
		this.setDao(bgModuleCategoryMapper);
		this.bgModuleCategoryMapper = bgModuleCategoryMapper;
	}
	public Map<String, Object> getBgModules(Integer decorateModuleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> bgModuleList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
		BgModuleCategoryExample bgModuleCategoryExample = new BgModuleCategoryExample();
		bgModuleCategoryExample.createCriteria().andDecorateModuleIdEqualTo(decorateModuleId).andDelFlagEqualTo("0").andProductIdsIsNotNull();
		bgModuleCategoryExample.setOrderByClause("ifnull(seq_no,99999),id desc");
		List<BgModuleCategory> bgModuleCategories = selectByExample(bgModuleCategoryExample);
		if(CollectionUtils.isNotEmpty(bgModuleCategories)){
			for (BgModuleCategory bgModuleCategory : bgModuleCategories) {
				Map<String, Object> bgModuleMap = new HashMap<String, Object>();
				bgModuleMap.put("bgModuleId", bgModuleCategory.getId());
				bgModuleMap.put("bgModuleName", bgModuleCategory.getCategoryName());
				bgModuleList.add(bgModuleMap);
			}
			productInfoList = getBgProductInfo(bgModuleCategories.get(0).getProductIds());
		}
		map.put("bgModuleList", bgModuleList);
		map.put("productInfoList", productInfoList);
		return map;
	}
	
	public List<Map<String, Object>> getBgProductInfo(String productIds) {
		List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
		if(!StringUtil.isBlank(productIds)){
			List<Integer> productList = new ArrayList<Integer>();
			for (String productId : productIds.split(",")) {
				if(!StringUtil.isBlank(productId)){
					productList.add(Integer.valueOf(productId));
				}
			}
			if(CollectionUtils.isNotEmpty(productList)){
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("productList", productList);
				List<ProductCustom> productCustoms = productService.getProductAndItemInfo(paramsMap);
				if(CollectionUtils.isNotEmpty(productCustoms)){
					for (ProductCustom productCustom : productCustoms) {
						Map<String, Object> pMap = new HashMap<String, Object>();
						BigDecimal salePrice = productCustom.getSalePrice();
						BigDecimal svipDiscount = productCustom.getSvipDiscount();
						BigDecimal svipSalePrice = new BigDecimal("0");
						if(svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0){
							svipSalePrice = productService.getProductSvipSalePrice(salePrice, svipDiscount, Const.PRODUCT_ACTIVITY_TYPE_AREA);
						}
						pMap.put("productId", productCustom.getId());
						pMap.put("productName", productCustom.getName());
						pMap.put("productPic", StringUtil.getPic(productCustom.getPic(), ""));
						pMap.put("salePrice", salePrice);
						pMap.put("tagPrice", productCustom.getTagPrice());
						pMap.put("svipSalePrice", svipSalePrice);
						productInfoList.add(pMap);
					}
				}
			}
		}
		return productInfoList;
	}
	
	
}
