package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.BannerRecommendProductMapper;
import com.jf.entity.BannerRecommendProduct;
import com.jf.entity.BannerRecommendProductExample;
import com.jf.entity.ProductCustom;
import com.jf.entity.SourceProductType;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BannerRecommendProductService extends BaseService<BannerRecommendProduct, BannerRecommendProductExample> {
	@Autowired
	private BannerRecommendProductMapper bannerRecommendProductMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	public void setBannerRecommendProductMapper(BannerRecommendProductMapper bannerRecommendProductMapper) {
		this.setDao(bannerRecommendProductMapper);
		this.bannerRecommendProductMapper = bannerRecommendProductMapper;
	}
	public List<Map<String, Object>> getBannerProducts(Integer bannerId, SourceProductType sourceProductType) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<Integer> productIdList = new ArrayList<>();
		List<ProductCustom> productCustoms = productService.getBannerProducts(bannerId);
		if(CollectionUtils.isNotEmpty(productCustoms)){
			for (ProductCustom ps : productCustoms) {
				Map<String, Object> dataMap = new HashMap<>();
				Integer productId = ps.getId();
				productIdList.add(productId);
				Map<String, Object> activityTypeMap = productService.getProductActivityType(null, productId);
				BigDecimal salePrice = new BigDecimal(activityTypeMap.get("salePrice").toString());
				BigDecimal tagPrice = new BigDecimal(activityTypeMap.get("tagPrice").toString());
				dataMap.put("productId", productId);
				dataMap.put("productPic", StringUtil.getPic(ps.getPic(), ""));
				dataMap.put("salePrice", salePrice);
				dataMap.put("tagPrice", tagPrice);
				list.add(dataMap);
			}
		}
		if(productCustoms.size() <= 4){
			List<Integer> productType2Ids = new ArrayList<>();
			Integer productType1Id = sourceProductType.getProductType1Id();
			String productType2Id = sourceProductType.getProductType2Id();
			if(!StringUtil.isBlank(productType2Id)){
				productType1Id = null;
				for (String type2Id : productType2Id.split(",")) {
					if(!StringUtil.isBlank(type2Id)){
						productType2Ids.add(Integer.parseInt(type2Id));
					}
				}
			}
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("productIdList", productIdList);
			paramsMap.put("productType1Id", productType1Id);
			paramsMap.put("productType2Ids", productType2Ids);
			paramsMap.put("type", sourceProductType.getType());
			paramsMap.put("sorfType", "1");
			paramsMap.put("currentPage", 0);
			paramsMap.put("pageSize", 12 - productCustoms.size());
			List<ProductCustom> psCustom = productService.getEveryDayProductList(paramsMap);
			if(CollectionUtils.isNotEmpty(psCustom)){
				for (ProductCustom ps : psCustom) {
					Map<String, Object> dataMap = new HashMap<>();
					Integer productId = ps.getId();
					productIdList.add(productId);
					Map<String, Object> activityTypeMap = productService.getProductActivityType(null, productId);
					BigDecimal salePrice = new BigDecimal(activityTypeMap.get("salePrice").toString());
					BigDecimal tagPrice = new BigDecimal(activityTypeMap.get("tagPrice").toString());
					dataMap.put("productId", productId);
					dataMap.put("productPic", StringUtil.getPic(ps.getPic(), ""));
					dataMap.put("salePrice", salePrice);
					dataMap.put("tagPrice", tagPrice);
					list.add(dataMap);
				}
			}
		}
		return list;
	}
	
	
	
}
