package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductItemCustomMapper;
import com.jf.dao.ProductMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductItemMapper;
import com.jf.entity.CloudProductItem;
import com.jf.entity.OrderDtl;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月4日 上午10:59:38 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductItemService extends BaseService<ProductItem, ProductItemExample> {
	
	@Autowired
	private ProductItemMapper productItemMapper;
	@Autowired
	private SubDepositOrderSerivce subDepositOrderSerivce;
	@Autowired
	private CloudProductItemService cloudProductItemService;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductItemCustomMapper productItemCustomMapper;

	@Autowired
	public void setProductItemMapper(ProductItemMapper productItemMapper) {
		this.setDao(productItemMapper);
		this.productItemMapper = productItemMapper;
	}
	
	/**30分钟未支付系统自动取消，sku数量还原*/
	public void updateProductSkuByItemId(OrderDtl orderDtl) {
		//使用定金预售的订单库存
		int subDepositOrderQuantity = subDepositOrderSerivce.updateSubDepositOrder(orderDtl);
		Integer quantity = orderDtl.getQuantity();
		Integer productItemId = orderDtl.getProductItemId();
		updateSkuStock(productItemId,quantity,subDepositOrderQuantity);
	}

	public void updateSkuStock(Integer productItemId, Integer quantity, int subDepositOrderQuantity) {
		//获取订单明细的sku  id  进行查询
		ProductItem productItem = findById(productItemId);
		if(productItem != null){
			//库存
			Integer stock = productItem.getStock();
			//冻结库存
			Integer lockedAmount = productItem.getLockedAmount();
			//购买sku的数量
			if(quantity - subDepositOrderQuantity < 0) { //预售库存不能解冻
				quantity = 0;
			}else {
				quantity = quantity - subDepositOrderQuantity;
			}
			if(lockedAmount-quantity < 0){
				lockedAmount = 0;
			}else{
				lockedAmount = lockedAmount-quantity;
			}
			productItem.setLockedAmount(lockedAmount);
			updateModel(productItem);
		}
	}

	public void updateModel(ProductItem productItem) {
		
		productItemMapper.updateByPrimaryKeySelective(productItem);
	}

	public ProductItem findById(Integer id) {
		
		return productItemMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 成功下单库存-1 冻结库存-1（下单后状态为已付款）
	 * @param productItemId
	 * @return
	 */
	public ProductItem updateProductSkuStock(Integer productItemId, Integer quantity) {
		ProductItem item = productItemMapper.selectByPrimaryKey(productItemId);
		Integer cloudProductItemId = item.getCloudProductItemId();//云宝skuid
		Integer xgStock = item.getStock();//库存数量
		Integer xgLockedAmount = item.getLockedAmount();//冻结数量
		Integer surplusCloudStock = 0;//供应商剩余库存
		if(cloudProductItemId != null){
			CloudProductItem cloudProductItem = cloudProductItemService.findModel(cloudProductItemId);
			if(cloudProductItem != null){
				Integer cloudStock = cloudProductItem.getStock() == null ? 0 : cloudProductItem.getStock();//供应商总库存
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
			}
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
		return item;
	}

	public void updateMinProductItemPrice(Integer productId) {
		List<ProductItem> items = productItemCustomMapper.getSkuByProductId(productId);//按醒购价进行升序排序
		if (CollectionUtils.isNotEmpty(items)) { //若没有库存，则不在进行操作
			ProductItem item = items.get(0);
			com.jf.entity.Product p = productMapper.selectByPrimaryKey(productId);
			if ("1".equals(p.getIsSingleProp())) {
				return;
			}
			p.setMinSalePriceItemId(item.getId());
			//再次按商城价进行升序排序
			Collections.sort(items, new Comparator<ProductItem>() {
				@Override
				public int compare(ProductItem item1, ProductItem item2) {
					BigDecimal a1 = item1.getMallPrice();
					BigDecimal a2 = item2.getMallPrice();
					if (a1.compareTo(a2) > 0) {
						return 1;
					}
					if (a1.compareTo(a2) == 0) {
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
			productMapper.updateByPrimaryKeySelective(p);
		}
	}
}
