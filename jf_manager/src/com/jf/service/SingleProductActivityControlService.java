package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.common.exception.ArgException;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class SingleProductActivityControlService extends BaseService<SingleProductActivityControl,SingleProductActivityControlExample> {
	@Autowired
	private SingleProductActivityControlMapper singleProductActivityControlMapper;
	@Autowired
	private SingleProductActivityControlCustomMapper singleProductActivityControlCustomMapper;
	@Autowired
	private ProductItemCustomMapper productItemCustomMapper;
	@Autowired
	private ProductCustomMapper  productCustomMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductItemMapper productItemMapper;
	@Autowired
	private ProductItemService productItemService;
	@Autowired
	private ProductPriceChangeLogService productPriceChangeLogService;
	@Autowired
	private ProductService productService;


	@Autowired
	public void setSingleProductActivityControlMapper(SingleProductActivityControlMapper singleProductActivityControlMapper) {
		super.setDao(singleProductActivityControlMapper);
		this.singleProductActivityControlMapper = singleProductActivityControlMapper;
	}
	
	public int countCustomByExample(SingleProductActivityControlExample example){
		return singleProductActivityControlCustomMapper.countByExample(example);
	}
    public List<SingleProductActivityControlCustom> selectCustomByExample(SingleProductActivityControlExample example){
    	return singleProductActivityControlCustomMapper.selectByExample(example);
    }

	public SingleProductActivityControlCustom selectCustomByPrimaryKey(int id) {
		return singleProductActivityControlCustomMapper.selectCustomByPrimaryKey(id);
	}

	public String getMchtIdsByMchtCodes(String mchtCodes) {
		return singleProductActivityControlCustomMapper.getMchtIdsByMchtCodes(mchtCodes);
	}


	public ResponseMsg saveBatchChangePrice(HttpServletRequest request,Integer staffID) {
		String mallPrice = request.getParameter("mallPrice");//商城价标识
		String salePrice = request.getParameter("salePrice");//活动价标识
		String mode = request.getParameter("mode");
		String changePrice = request.getParameter("changePrice");
		String mantissa = request.getParameter("mantissa");
		String mantissaPrice = request.getParameter("mantissaPrice");
		ArrayList<ProductItem> productItemList = new ArrayList<>();
		String[] productIds=request.getParameter("productIds").split(",");
		if(productIds.length<=0){
			throw new ArgException("批量改价出错");
		}
		List<Integer> idList=new ArrayList<Integer>();
		for (int i = 0; i < productIds.length; i++) {
			if(!StringUtil.isEmpty(productIds[i])){
				idList.add(Integer.valueOf(productIds[i]));
			}
		}
		try {
			ProductItemExample productItemExample = new ProductItemExample();
			productItemExample.createCriteria().andProductIdIn(idList).andDelFlagEqualTo("0");
			List<ProductItemCustom> productItemCustoms = productItemCustomMapper.selectByExample(productItemExample);

			for (ProductItemCustom productItemCustom : productItemCustoms) {
				BigDecimal finalMallPrice=productItemCustom.getMallPrice();//查询的item商城价;
				BigDecimal finalSalePrice=productItemCustom.getSalePrice();//查询的item活动价
				BigDecimal tagPrice = productItemCustom.getTagPrice();  //吊牌价
				BigDecimal costPrice = productItemCustom.getCostPrice();//结算价
				if ("1".equals(productItemCustom.getMchtType())){
					if (!StringUtil.isEmpty(mallPrice) &&StringUtil.isEmpty(salePrice)){
						if ("1".equals(mode)){
							BigDecimal priceChange = finalMallPrice.add(new BigDecimal(changePrice));
							finalMallPrice=priceChange;
						}
						if ("2".equals(mode)){
							BigDecimal change = finalMallPrice.multiply(new BigDecimal(changePrice).divide(new BigDecimal(100)));
							BigDecimal priceChange = productItemCustom.getMallPrice().add(change);
							finalMallPrice=priceChange;
						}
						if ("3".equals(mode)){
							BigDecimal priceChange = finalMallPrice.subtract(new BigDecimal(changePrice));
							finalMallPrice=priceChange;

						}
						if ("4".equals(mode)){
							BigDecimal change =finalMallPrice.multiply(new BigDecimal(changePrice).divide(new BigDecimal(100)));
							BigDecimal priceChange = productItemCustom.getMallPrice().subtract(change);
							finalMallPrice=priceChange;
						}
						if ("1".equals(mantissa)){
							finalMallPrice= replaceSingleDigit(finalMallPrice, Integer.valueOf(mantissaPrice)).setScale(0,BigDecimal.ROUND_DOWN);
						}

						if (tagPrice.compareTo(finalMallPrice) >-1 && finalMallPrice.compareTo(finalSalePrice)==1&& finalSalePrice.compareTo(costPrice)==1){
							ProductItem productItem = new ProductItem();
							productItem.setId(productItemCustom.getId());
							productItem.setMallPrice(finalMallPrice);
							productItemList.add(productItem);
						}else {
								throw new ArgException("调价失败,商品价格需满足：吊牌价>=商城价>活动价>结算价\n");
						}
					}
					if (!StringUtil.isEmpty(salePrice) &&StringUtil.isEmpty(mallPrice)){
						if ("1".equals(mode)){
							BigDecimal priceChange = finalSalePrice.add(new BigDecimal(changePrice));
							finalSalePrice=priceChange;
						}
						if ("2".equals(mode)){
							BigDecimal change = finalSalePrice.multiply(new BigDecimal(changePrice).divide(new BigDecimal(100)));
							BigDecimal priceChange = productItemCustom.getSalePrice().add(change);
							finalSalePrice=priceChange;
						}
						if ("3".equals(mode)){
							BigDecimal priceChange = finalSalePrice.subtract(new BigDecimal(changePrice));
							finalSalePrice=priceChange;
						}
						if ("4".equals(mode)){
							BigDecimal change =finalSalePrice.multiply(new BigDecimal(changePrice).divide(new BigDecimal(100)));
							BigDecimal priceChange = productItemCustom.getSalePrice().subtract(change);
							finalSalePrice=priceChange;
						}
						if ("1".equals(mantissa)){
							finalSalePrice= replaceSingleDigit(finalSalePrice, Integer.valueOf(mantissaPrice)).setScale(0,BigDecimal.ROUND_DOWN);
						}

						if (tagPrice.compareTo(finalMallPrice) >-1 && finalMallPrice.compareTo(finalSalePrice)==1&& finalSalePrice.compareTo(costPrice)==1){
							ProductItem productItem = new ProductItem();
							productItem.setId(productItemCustom.getId());
							productItem.setSalePrice(finalSalePrice);
							productItemList.add(productItem);
						}else {
								throw new ArgException("调价失败,商品价格需满足：吊牌价>=商城价>活动价>结算价\n");
						}
					}
					if (!StringUtil.isEmpty(salePrice) &&!StringUtil.isEmpty(mallPrice)){
						if ("1".equals(mode)){
							BigDecimal priceChange = finalSalePrice.add(new BigDecimal(changePrice));
							finalSalePrice=priceChange;
							BigDecimal priceChange1 = finalMallPrice.add(new BigDecimal(changePrice));
							finalMallPrice=priceChange1;
						}
						if ("2".equals(mode)){
							BigDecimal change = finalSalePrice.multiply(new BigDecimal(changePrice).divide(new BigDecimal(100)));
							BigDecimal priceChange = productItemCustom.getSalePrice().add(change);
							finalSalePrice=priceChange;
							BigDecimal change1 = finalMallPrice.multiply(new BigDecimal(changePrice).divide(new BigDecimal(100)));
							BigDecimal priceChange1 = productItemCustom.getMallPrice().add(change1);
							finalMallPrice=priceChange1;
						}
						if ("3".equals(mode)){
							BigDecimal priceChange = finalSalePrice.subtract(new BigDecimal(changePrice));
							finalSalePrice=priceChange;
							BigDecimal priceChange1 = finalMallPrice.subtract(new BigDecimal(changePrice));
							finalMallPrice=priceChange1;
						}
						if ("4".equals(mode)){
							BigDecimal change =finalMallPrice.multiply(new BigDecimal(changePrice).divide(new BigDecimal(100)));
							BigDecimal priceChange = productItemCustom.getMallPrice().subtract(change);
							finalMallPrice=priceChange;
							BigDecimal change1 =finalSalePrice.multiply(new BigDecimal(changePrice).divide(new BigDecimal(100)));
							BigDecimal priceChange1 = productItemCustom.getSalePrice().subtract(change1);
							finalSalePrice=priceChange1;
						}
						if ("1".equals(mantissa)){
							finalSalePrice= replaceSingleDigit(finalSalePrice, Integer.valueOf(mantissaPrice)).setScale(0,BigDecimal.ROUND_DOWN);
							finalMallPrice= replaceSingleDigit(finalMallPrice, Integer.valueOf(mantissaPrice)).setScale(0,BigDecimal.ROUND_DOWN);
						}
						if (tagPrice.compareTo(finalMallPrice) >-1 && finalMallPrice.compareTo(finalSalePrice)==1&& finalSalePrice.compareTo(costPrice)==1){
							ProductItem productItem = new ProductItem();
							productItem.setId(productItemCustom.getId());
							productItem.setMallPrice(finalMallPrice);
							productItem.setSalePrice(finalSalePrice);
							productItemList.add(productItem);
						}else {
								throw new ArgException("调价失败,商品价格需满足：吊牌价>=商城价>活动价>结算价\n");
						}
					}
				}else {
						throw new ArgException("调价失败，只有商家合作类型为SPOP的商品才能调价\n");
				}
			}

		} catch (ArgException arge) { //提示
			arge.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		for (ProductItem productItem : productItemList) {
			 productItemMapper.updateByPrimaryKeySelective(productItem);
		}
		//将商城价最低的skuId设置到商品表，并记录价格变动记录
		for (Integer productId : idList) {
			savePriceChangeLog(productId,staffID);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,idList.size());
	}

	//改变尾数方法
	private    BigDecimal replaceSingleDigit(BigDecimal originNum, int replaceDigit) {
		int singleDigit = originNum.intValue() % 10;
		return originNum.subtract(new BigDecimal(singleDigit)).add(new BigDecimal(replaceDigit));
	}

	//调价方式的方法抽取


	//将商城价最低的sku id 设置到商品表，并记录一条价格变动记录
	private   void  savePriceChangeLog(Integer productId,Integer staffID) {
		if (productId != null) {
			ProductItem minPriceProductItem = productItemService.getMinMallPriceItem(productId);
			ProductItem minSalePriceProductItem = productItemService.getMinSalePriceItem(productId);
			ProductItem maxSalePriceProductItem =productItemService.getMaxSalePriceItem(productId);
			Product product4update = new Product();
			product4update.setId(productId);
			product4update.setMinPriceItemId(minPriceProductItem.getId());
			product4update.setMinMallPrice(minPriceProductItem.getMallPrice());
			product4update.setMinSalePrice(minPriceProductItem.getSalePrice());
			product4update.setMinTagPrice(minPriceProductItem.getTagPrice());
			product4update.setMinSalePriceItemId(minSalePriceProductItem.getId());
			productService.updateByPrimaryKeySelective(product4update);

			ProductPriceChangeLog productPriceChangeLog = new ProductPriceChangeLog();
			productPriceChangeLog.setDelFlag("0");
			productPriceChangeLog.setProductId(productId);
			productPriceChangeLog.setCreateDate(new Date());
			productPriceChangeLog.setCreateBy(staffID);
			productPriceChangeLog.setMinMallPrice(minPriceProductItem.getMallPrice());
			productPriceChangeLog.setMinMallPriceItemId(minPriceProductItem.getId());
			productPriceChangeLog.setMinSalePrice(minSalePriceProductItem.getSalePrice());
			productPriceChangeLog.setMinSalePriceItemId(minSalePriceProductItem.getId());
			productPriceChangeLog.setRemarks("最低价为"+minSalePriceProductItem.getSalePrice()+",最高价为"+maxSalePriceProductItem.getSalePrice());
			productPriceChangeLogService.insertSelective(productPriceChangeLog);

		}
	}
}
