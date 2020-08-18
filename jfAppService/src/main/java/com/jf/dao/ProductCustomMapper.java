package com.jf.dao;

import com.jf.entity.ProductCustom;
import com.jf.entity.ProductItem;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductCustomMapper{

	List<ProductCustom> getProductInfoList(Map<String, Object> params);

	List<ProductCustom> getStandardById(Map<String, Object> params);

	List<ProductItem> getStandard(Map<String, Object> params);

	List<ProductItem> getProductItemsById(Map<String, Object> params);

	ProductCustom getProductInfo(Integer productId);

	ProductCustom getUserBuyCount(Map<String, Object> params);
	
	/**
	 * 获取用户同一类型单凭活动购买数量
	 * @param params
	 * @return
	 */
	int getUserBuyCountBySingleActivityType(Map<String, Object> params);
	
	/**
	 * 获取用户同一类型单凭活动购物车数量
	 * @param params
	 * @return
	 */
	int getUserShoppingCartCountBySingleActivityType(Map<String, Object> params);

	ProductCustom getProductModeById(Map<String, Object> params);

	List<ProductItem> getHasStockList(Map<String, Object> params);

	Integer getShoppingCartProductNum(Map<String, Object> params);

	String getProductactivityStatus(Integer id);

	List<ProductCustom> getShoppingMallProductData(Map<String, Object> paramsMap);

	List<ProductCustom> getMchtShoppingProductList(Map<String, Object> paramsMap);

	int getActivityCount(Integer productId);

	int getSingleActivityCount(Integer productId);

	List<ProductCustom> getProductBaseInfo(Integer id);

	List<ProductCustom> getShopMallProductListDefaultSort(Map<String, Object> paramsMap);

	List<ProductCustom> getShopMallProductBrandList(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @Title getProductBrandList   
	 * @Description TODO(获取商品品牌)   
	 * @author pengl
	 * @date 2018年9月17日 下午3:38:01
	 */
	List<Map<String, Object>> getProductBrandList(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @Title getProductTypeList   
	 * @Description TODO(获取商品分类)   
	 * @author pengl
	 * @date 2018年9月17日 下午3:38:23
	 */
	List<Map<String, Object>> getProductTypeList(Map<String, Object> paramsMap);

	/**
	 * 获取商品的正常运行状态（可以复用）
	 * 商家的合作状态=正常 且 商家的品牌的运营状态 =正常 且 商家的品牌资质状态=通过  且 商品的上架状态 =上架 且 商品的法务审核状态=通过
	 * @param params
	 * @return
	 */
	ProductCustom getNormalOperationProduct(Integer productId);
	/**
	 * 旧版本店铺商品列表2019-01-03废弃
	 */
	List<ProductCustom> getShopMallProductTypeList(Map<String, Object> paramsMap);

	List<ProductCustom> getGoodsBasicsInfo(Map<String, Object> goodsMap);
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
	/**
	 * 微淘商品商品列表
	 */
	List<ProductCustom> getWeiTaoProductList(Map<String, Object> paramsMap);
	/**
	 * 每日获取会员折扣力度大的，好评的前随机10个商品
	 */
	List<ProductCustom> getSvipProductList(Map<String, Object> paramsMap);
	/**
	 * 每日好货商品列表
	 */
	List<ProductCustom> getEveryDayProductList(Map<String, Object> paramsMap);

	/**
	 * 每日好店店铺商品列表
	 */
	List<ProductCustom> getEveryDayShopProduct(Map<String, Object> productParamsMap);
	/**
	 * 爆款推荐商品列表
	 */
	List<ProductCustom> findHotRecommendProduct(Map<String, Object> params);
	/**
	 * 类目品牌团展示3个商品
	 */
	List<ProductCustom> getBaranGroupProducts(Map<String, Object> productParamsMap);
	
	/**
	 * 
	 * @MethodName: getProductDynamicList
	 * @Description: (获取店铺动态绑定商品)
	 * @author Pengl
	 * @date 2019年5月27日 下午1:03:06
	 */
	public List<Map<String, Object>> getProductDynamicList(Map<String, Object> paramMap);
	/**
	 * 有好货banner商品
	 */
	List<ProductCustom> getBannerProducts(Integer bannerId);
	/**
	 * 断码特惠商品列表
	 */
	List<ProductCustom> getCodeBreakingPreferenceProductList(Map<String, Object> paramsMap);

	List<ProductCustom> getProductByModuleItem(Map<String, Object> productParamsMap);

	List<ProductCustom> getProductByParamsMap(Map<String, Object> paramsMap);

	List<ProductCustom> getEveryDayShopProducts(Map<String, Object> productParamsMap);

	List<ProductCustom> getLoginRecommendProductList(Map<String, Object> paramMap);

	List<ProductCustom> getNotLoginRecommendProductList(Map<String, Object> paramMap);

	List<Integer> findOnlineProductIds(Map<String, Object> paramMap);
}