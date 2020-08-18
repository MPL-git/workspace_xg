package com.jf.dao;

import java.util.List;
import java.util.Map;

import com.jf.entity.ProductType;
import com.jf.vo.response.ProductTypeView;
import org.springframework.stereotype.Repository;

import com.jf.entity.ProductCustom;
import com.jf.entity.ProductItem;
@Repository
public interface ProductCustomMapper{

	List<ProductCustom> getProductInfoList(Map<String, Object> params);

	List<ProductCustom> getStandardById(Map<String, Object> params);

	List<ProductItem> getStandard(Map<String, Object> params);

	List<ProductItem> getProductItemsById(Map<String, Object> params);

	ProductCustom getProductInfo(Integer productId);

	ProductCustom getUserBuyCount(Map<String, Object> params);

	ProductCustom getProductModeById(Integer id);

	List<ProductItem> getHasStockList(Map<String, Object> params);

	Integer getShoppingCartProductNum(Map<String, Object> params);

	List<ProductCustom> getProductInfoListByShare(Map<String, Object> params);

	int getUserBuyCountBySingleActivityType(Map<String, Object> params);

	int getUserShoppingCartCountBySingleActivityType(Map<String, Object> params);

	String getProductactivityStatus(String id);

	List<ProductCustom> getProductByModuleItem(Map<String, Object> productParamsMap);

	List<ProductCustom> getProductByParamsMap(Map<String, Object> paramsMap);

	List<ProductCustom> getShoppingMallProductData(Map<String, Object> paramsMap);

	List<ProductCustom> getMchtShoppingProductList(Map<String, Object> paramsMap);
    
	int getActivityCount(Integer productId);

	int getSingleActivityCount(Integer productId);

	String getProductactivityStatus(Integer id);

	List<ProductCustom> getProductBaseInfo(Map<String, Object> pCustomMap);

	List<ProductCustom> getStoreActivityRecommend(Map<String, Object> paramsMap);

	List<ProductCustom> getProductAndItemInfo(Map<String, Object> paramsMap);

	List<ProductCustom> getShopMallProductListDefaultSort(Map<String, Object> paramsMap);

	List<ProductCustom> getShopMallProductBrandList(Map<String, Object> paramsMap);

	List<ProductCustom> getSignGiftProduct(Map<String, Object> paramsMap);
	/**
	 * 新版本店铺商品列表2019-01-03
	 */
	List<ProductCustom> getShopProductList(Map<String, Object> paramsMap);
	/**
	 * 新版本店铺商品品牌聚合2019-01-03
	 */
	List<ProductCustom> getShopProductBrandConverge(Integer mchtId);
	/**
	 * 新版本店铺商品类目聚合2019-01-03
	 */
	List<ProductCustom> getShopProductCategoryConverge(Integer mchtId);
	/**
	 * 新版本店铺商品分类聚合019-01-03
	 */
	List<ProductCustom> getShopProductClassConverge(Integer mchtId);

	ProductCustom getNormalOperationProduct(Integer productId);

	List<ProductCustom> getGoodsBasicsInfo(Map<String, Object> goodsMap);

	/**
	 * 每日好货商品列表
	 */
	List<ProductCustom> getEveryDayProductList(Map<String, Object> paramsMap);
	/**
	 * 每日好店店铺商品列表
	 */
	List<ProductCustom> getEveryDayShopProduct(Map<String, Object> productParamsMap);
	
	/**
	 * 
	 * @MethodName: getProductDynamicList
	 * @Description: (获取店铺动态绑定商品)
	 * @author Pengl
	 * @date 2019年5月27日 下午1:03:06
	 */
	public List<Map<String, Object>> getProductDynamicList(Map<String, Object> paramMap);
	/**
	 * 类目品牌团展示3个商品
	 */
	List<ProductCustom> getBaranGroupProducts(Map<String, Object> productParamsMap);
	
	List<ProductCustom> getLoginRecommendProductList(Map<String, Object> paramMap);

	List<ProductCustom> getNotLoginRecommendProductList(Map<String, Object> paramMap);

    List<ProductCustom> findHotRecommendProduct(Map<String, Object> params);

    List<ProductType> findMchtShopProductType(Map<String, Object> params);

	List<ProductCustom> findMchtShopProduct(Map<String, Object> paramsMap);
}