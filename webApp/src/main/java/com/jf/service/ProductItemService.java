package com.jf.service;

import com.google.common.collect.Maps;
import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductItemCustomMapper;
import com.jf.dao.ProductItemMapper;
import com.jf.entity.CloudProductItem;
import com.jf.entity.CloudProductItemCustom;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtSupplierUser;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemCustom;
import com.jf.entity.ProductItemExample;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月27日 下午1:38:30 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductItemService extends BaseService<ProductItem, ProductItemExample> {
	private static Logger logger = LoggerFactory.getLogger(ProductItemService.class);
	@Autowired
	private ProductItemMapper productItemMapper;
	@Autowired
	private ProductItemCustomMapper productItemCustomMapper;
	@Resource
	private ProductService productService;
	@Resource
	private CloudProductItemService cloudProductItemService;
	@Resource
	private MchtSupplierUserService mchtSupplierUserService;
	@Resource
	private MchtInfoService mchtInfoService;

	@Autowired
	public void setProductItemMapper(ProductItemMapper productItemMapper) {
		this.setDao(productItemMapper);
		this.productItemMapper = productItemMapper;
	}

	public ProductItemCustom getProductInfoByItemId(Integer productItemId) {

		return productItemCustomMapper.getProductInfoByItemId(productItemId);
	}

	/**
	 * 直接扣除库存
	 * 如果有供应商，就扣除供应商库存，更新该供应商对应的商品库存
	 */
	public boolean decreaseSkuStock(Integer productItemId, Integer quantity) {
		ProductItem item = productItemMapper.selectByPrimaryKey(productItemId);
		if (item == null) {
			return false;
		}
		if (item.getCloudProductItemId() != null && item.getCloudProductItemId() != 0) {
			//更新云宝库存
			CloudProductItem cloudProductItem = cloudProductItemService.findModel(item.getCloudProductItemId());
			Integer cloudStock = cloudProductItem.getStock();//供应商总库存
			int surplusCloudStock = cloudStock - quantity;//供应商剩余库存
			if (surplusCloudStock < 0) {
				surplusCloudStock = 0;
			}
			cloudProductItem.setStock(surplusCloudStock);
			cloudProductItemService.updateByPrimaryKeySelective(cloudProductItem);

			//跟云宝id相关联的更新库存
			ProductItemExample productItemExample = new ProductItemExample();
			productItemExample.createCriteria().andCloudProductItemIdEqualTo(item.getCloudProductItemId()).andDelFlagEqualTo("0");
			ProductItem productItem = new ProductItem();
			productItem.setStock(surplusCloudStock);
			return updateByExampleSelective(productItem, productItemExample) > 0;
		}
		return productItemCustomMapper.decreaseStock(productItemId, quantity) > 0;
	}


	/**
	 * 下单成功，扣除库存，扣除冻结库存
	 * 如果有供应商，就扣除供应商库存，更新该供应商对应的商品库存
	 * @param productItemId
	 * @param quantity
	 * @param cloudProductItemId
	 * @return
	 */
	public Integer updateProductSkuStock(Integer productItemId, Integer quantity, Integer cloudProductItemId) {
		ProductItem item = productItemMapper.selectByPrimaryKey(productItemId);
		if(item != null){
			Integer xgStock = item.getStock();//库存数量
			Integer xgLockedAmount = item.getLockedAmount();//冻结数量
			Integer surplusCloudStock = 0;//供应商剩余库存
			if(cloudProductItemId != null && cloudProductItemId != 0){
				CloudProductItem cloudProductItem = cloudProductItemService.findModel(cloudProductItemId);
				Integer cloudStock = cloudProductItem.getStock();//供应商总库存
				surplusCloudStock = cloudStock - quantity;//供应商剩余库存
				if(surplusCloudStock < 0){
					surplusCloudStock = 0;
				}
				cloudProductItem.setStock(surplusCloudStock);
				cloudProductItemService.updateByPrimaryKeySelective(cloudProductItem);
				//跟云宝id相关联的更新库存
				ProductItemExample productItemExample = new ProductItemExample();
				productItemExample.createCriteria().andCloudProductItemIdEqualTo(cloudProductItemId).andDelFlagEqualTo("0");
				ProductItem productItem = new ProductItem();
				productItem.setStock(surplusCloudStock);
				updateByExampleSelective(productItem, productItemExample);
			}else{
				surplusCloudStock = xgStock - quantity;//醒购商家剩余库存
				if(surplusCloudStock < 0){
					surplusCloudStock = 0;
				}
			}
			if(xgLockedAmount-quantity > 0){//支付成功  库存冻结要释放  冻结数量-购买数量
				item.setLockedAmount(xgLockedAmount-quantity);
			}else{
				item.setLockedAmount(0);
			}
			item.setStock(surplusCloudStock);
			productItemMapper.updateByPrimaryKeySelective(item);
		}
		return 1;
	}


	/**
	 * 冻结商品库存
	 * @param productItemId
	 * @param quantity
	 * @param mchtId
	 * @param activityType
	 * @return
	 */
	public Map<String, Object> updateSkuLockedAmount(Integer productItemId, Integer quantity, Integer mchtId, String type) {
		//type 是否减去冻结库存  1减 2 不减
		Map<String, Object> map = new HashMap<>();
		int count = 0;
		BigDecimal sellingPrice = null;
		boolean isCooperation = false; //是否合作 
		ProductItem item = selectByPrimaryKey(productItemId);
		if(item != null){
			Integer cloudProductItemId = item.getCloudProductItemId();//云宝skuid
			Integer xgStock = item.getStock();//醒购库存
			Integer xgLockedAmount = item.getLockedAmount();//醒购冻结库存
			Integer xgAvailableStock = xgStock - xgLockedAmount;//醒购可用库存
			Integer xglockedAmountSum = 0;//醒购云宝id所对应的总冻结库存数量
			if(xgAvailableStock > 0){
				if(cloudProductItemId != null && cloudProductItemId != 0){
					List<CloudProductItemCustom> cloudProductItems = cloudProductItemService.getCloudProducrItems(cloudProductItemId);
					MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
					if(CollectionUtils.isNotEmpty(cloudProductItems) && mchtInfo != null && "1".equals(mchtInfo.getSupplyChainStatus())){
						CloudProductItemCustom cloudProductItem = cloudProductItems.get(0);
						sellingPrice = cloudProductItem.getSellingPrice();
						Integer spOfficeId = cloudProductItem.getSpOfficeId();
						List<MchtSupplierUser> supplierUsers = mchtSupplierUserService.findModel(spOfficeId,mchtId);
						if(CollectionUtils.isNotEmpty(supplierUsers)){
							isCooperation = true;
							Map<String, Integer> params = new HashMap<String, Integer>();
							params.put("cloudProductItemId", cloudProductItemId);
							ProductItem productItem = productItemCustomMapper.getProductStockInfo(params);
							xglockedAmountSum = productItem.getLockedAmount();
							Integer cloudStock = cloudProductItem.getStock() == null ? 0 : cloudProductItem.getStock();//供应商总库存
							if(cloudStock > xglockedAmountSum){
								count = 1;
							}
						}else{
							count = 1;
						}
					}else{
						count = 1;
					}
				}else{
					count = 1;
				}
			}
			if(!"2".equals(type)){
				if(count > 0){
					Map<String, Integer> params = new HashMap<String, Integer>();
					params.put("productItemId", productItemId);
					params.put("quantity", quantity);
					params.put("cloudProductItemId", cloudProductItemId);
					count = productItemCustomMapper.updateSkuLockedAmount(params);
				}
				if(count < 0){
					throw new ArgException("商品库存不足");
				}
				// 当规格库存为零时执行商品最小价格的更新
				if (item.getStock() - item.getLockedAmount() <= quantity) {
					updateMinProductItemPrice(item.getProductId());
				}
			}
		}
		map.put("isCooperation", isCooperation);
		map.put("count", count);
		map.put("sellingPrice", sellingPrice);
		return map;
	}


	/**
	 * 取消释放商品冻结库存
	 * @param productItemId
	 * @param quantity
	 * @return
	 */
	public Integer cancelReleaseProductLockedAmount(Integer productItemId, Integer quantity,Integer cloudProductItemId) {
		int count = 0;
		if(productItemId != 0){
			ProductItem item = selectByPrimaryKey(productItemId);
			Integer lockedAmount = item.getLockedAmount();//冻结数量
			boolean needUpdateMinProductItemPrice = item.getStock().equals(item.getLockedAmount());
			if(lockedAmount > 0){
				//待付款订单取消了  库存冻结要释放  冻结数量-购买数量
//				if(lockedAmount-quantity > 0){
//					item.setLockedAmount(lockedAmount-quantity);
//				}else{
//					item.setLockedAmount(0);
//				}
//				updateByPrimaryKeySelective(item);

				Map<String, Object> params = Maps.newHashMap();
				params.put("quantity", quantity);
				params.put("productItemId", productItemId);
				productItemCustomMapper.deductSkuLockedAmount(params);

				// 若释放库存前规格库存为0，需要执行商品最小价格的更新
				if (needUpdateMinProductItemPrice) {
					updateMinProductItemPrice(item.getProductId());
				}
			}
		}
		return count;
	}

	public ProductItem findIdByForUpdate(Integer productItemId) {
		// TODO Auto-generated method stub
		return productItemCustomMapper.findIdByForUpdate(productItemId);
	}

	public List<ProductItem> findListByQuery(QueryObject queryObject){
		return productItemMapper.selectByExample(builderQuery(queryObject));
	}

	private ProductItemExample builderQuery(QueryObject queryObject) {
		ProductItemExample example = new ProductItemExample();
		ProductItemExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("productId") != null){
			criteria.andProductIdEqualTo(queryObject.getQueryToInt("productId"));
		}
		if(queryObject.getQuery("sort") != null){
			example.setOrderByClause(queryObject.getQueryToStr("sort"));
		}
		return example;
	}

	public ProductItem getSpecPic(Map<String, Object> specPicMap) {

		return productItemCustomMapper.getSpecPic(specPicMap);
	}

	public List<Map<String, Object>> getProductStandardList(String propValId, Integer id) {
		List<Map<String,Object>> standardMapList = new ArrayList<Map<String,Object>>();
		if(!StringUtil.isBlank(propValId)){
			Map<String,Object> params = new HashMap<String,Object>();
			List<Integer> propValIdList = new ArrayList<Integer>();
			String propVal = propValId.substring(0, propValId.length()-1);
			String[] propValStrs = propVal.split(",");
			//商品所有规格的id存入集合中
			for (String str : propValStrs) {
				propValIdList.add(Integer.valueOf(str));
			}
			//去重
			HashSet<Integer> h = new HashSet<Integer>(propValIdList);
			propValIdList.clear();
			propValIdList.addAll(h);
			params.put("propValIdList", propValIdList);
			params.put("id", id);

			//查询出过滤所有有库存的规格
			Map<String,Object> hasStockMap = new HashMap<String,Object>();
			String hasStockPropValueId = "";
			List<ProductItem> hasStockList = productService.getHasStockList(params);
			if(CollectionUtils.isNotEmpty(hasStockList)){
				for (ProductItem item : hasStockList) {
					hasStockPropValueId += item.getPropValId()+",";
				}
				hasStockPropValueId = hasStockPropValueId.substring(0, hasStockPropValueId.length()-1);
				for (String propValueId : hasStockPropValueId.split(",")) {
					hasStockMap.put(propValueId, propValueId);
				}
			}
			params.put("sorfType", "1");
			List<ProductCustom> standardList = productService.getStandardById(params);
			//把相同的规格类型存list
			List<Object> propNameList = new ArrayList<Object>();
			for (ProductCustom props : standardList) {
				if(!propNameList.contains(props.getPropName())){
					Map<String,Object> propMap = new HashMap<String,Object>();
					String propName =props.getPropName();
					Integer propId =props.getPropId();
					propNameList.add(propName);
					propMap.put("propName", propName);
					propMap.put("propId", propId);
					params.put("propId", propId);
					params.put("sorfType", "2");
					List<ProductCustom> propValueList = productService.getStandardById(params);
					List<Map<String,Object>> propValueMapList = new ArrayList<Map<String,Object>>();
					for (ProductCustom propValues : propValueList) {
						Map<String,Object> specPicMap = new HashMap<String,Object>();
						Map<String,Object> propValueMap = new HashMap<String,Object>();
						specPicMap.put("productId", id);
						specPicMap.put("propValId", propValues.getPropValueId());
						String specPic = "";
						//根据规格id和商品id去查找商品的sku图片(只有当规格有颜色的时候才去找)  1
						if(propId.toString().equals("1") || propName.equals("颜色")){
							ProductItem itemMode = getSpecPic(specPicMap);
							if(itemMode != null && !StringUtil.isBlank(itemMode.getPic())){
								specPic = FileUtil.getImageServiceUrl()+itemMode.getPic();
							}
						}
						propValueMap.put("propValue", propValues.getPropValue());
						propValueMap.put("propValId", propValues.getPropValueId());
						propValueMap.put("specPic", specPic);
						if(!hasStockMap.isEmpty()){
							if(hasStockMap.containsKey(propValues.getPropValueId().toString())){
								propValueMap.put("status", true);
								propValueMapList.add(propValueMap);
							}
						}

					}
					if(CollectionUtils.isNotEmpty(propValueMapList)){
						propMap.put("propValueMapList", propValueMapList);
						standardMapList.add(propMap);
					}
				}
			}

		}
		return standardMapList;
	}

	public String getProductItemPropDesc(Map<String, Object> paramsMap) {
		return productItemCustomMapper.getProductItemPropDesc(paramsMap);
	}

	public String getProductItemPropDesc(int productItemId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("productItemId", productItemId);
		params.put("mark", ",");
		return productItemCustomMapper.getProductItemPropDesc(params);
	}

	public void updateMinProductItemPrice(List<Integer> productIds) {
		try {
			for (Integer productId : productIds) {
				updateMinProductItemPrice(productId);
			}
		} catch (Exception e) {
			logger.info("更新最低sku价格失败");
		}

	}

	public void updateMinProductItemPrice(Integer productId) {
		List<ProductItem> items = productItemCustomMapper.getSkuByProductId(productId); //按醒购价进行升序排序(有库存)
		if (CollectionUtil.isEmpty(items)) {
			items = productItemCustomMapper.findSkuByProductId(productId); //按醒购价进行升序排序(不限制库存)
		}
		if(CollectionUtils.isNotEmpty(items)){ //若没有库存，则不在进行操作
			ProductItem item = items.get(0);
			com.jf.entity.Product p = productService.selectByPrimaryKey(productId);
			if("1".equals(p.getIsSingleProp())){
				return;
			}
			p.setMinSalePriceItemId(item.getId());
			//再次按商城价进行升序排序
			Collections.sort(items,new Comparator<ProductItem>() {
				@Override
				public int compare(ProductItem item1, ProductItem item2) {
					BigDecimal a1 = item1.getMallPrice();
					BigDecimal a2 = item2.getMallPrice();
					if(a1.compareTo(a2) > 0){
						return 1;
					}
					if(a1.compareTo(a2) == 0){
						return 0;
					}
					return -1;
				}
			});
			item = items.get(0);
			p.setMinPriceItemId(item.getId());
			p.setMinSalePrice(item.getSalePrice());
			p.setMinMallPrice(item.getMallPrice());
			p.setMinTagPrice(item.getTagPrice());
			productService.updateByPrimaryKeySelective(p);
		}
	}

	/**
	 * 获取供应商经销代码
	 * @param cloudProductItemId
	 * @return
	 */
	public BigDecimal getCloudSellingPrice(Integer cloudProductItemId) {
		BigDecimal sellingPrice = null;
		if(cloudProductItemId != null){
			List<ProductItemCustom> customs = productItemCustomMapper.getCloudSellingPrice(cloudProductItemId);
			if(CollectionUtils.isNotEmpty(customs)){
				sellingPrice = customs.get(0).getSellingPrice();
			}
		}
		return sellingPrice;
	}

	public int getProductStock(Integer productId) {
		return productItemCustomMapper.getProductStock(productId);
	}
}
