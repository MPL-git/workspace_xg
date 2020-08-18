package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductCustomMapper;
import com.jf.dao.ProductItemCustomMapper;
import com.jf.dao.ProductItemMapper;
import com.jf.dao.ProductMapper;
import com.jf.dao.ProductPriceChangeLogMapper;
import com.jf.entity.Product;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemCustom;
import com.jf.entity.ProductItemCustomExample;
import com.jf.entity.ProductItemExample;
import com.jf.entity.ProductPriceChangeLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductItemService extends BaseService<ProductItem, ProductItemExample> {
	@Autowired
	private ProductItemMapper dao;

	@Autowired
	private ProductItemCustomMapper productItemCustomMapper;

	@Autowired
	private ProductCustomMapper productCustomMapper;

	@Autowired
	private ProductItemMapper productItemMapper;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductPriceChangeLogMapper productPriceChangeLogMapper;

	@Autowired
	public void setProductItemMapper(ProductItemMapper productItemMapper) {
		super.setDao(productItemMapper);
		this.dao = productItemMapper;
	}

	public List<ProductItemCustom> selectProductItemCustomByExample(ProductItemExample example) {
		return productItemCustomMapper.selectByExample(example);
	}

	public List<ProductItem> selectProductItemOrderByPropValue(Map<String, Object> paramMap) {
		return productItemCustomMapper.selectProductItemOrderByPropValue(paramMap);
	}

	public ProductItemCustom selectByPrimaryKey(Integer id) {
		return productItemCustomMapper.selectByPrimaryKey(id);
	}
	
	public List<ProductItemCustom> selectBySkuExample(ProductItemCustomExample example) {
		return productItemCustomMapper.selectBySkuExample(example);
	}

	/**
	 * <!--此方法慎用慎用慎用慎用！！！！：因为此方法未过滤删除的sku-->
	 *
	 * @param paramMap
	 * @return
	 */
	public ProductItem queryProductItemByProductIdAndPropValueIds(Map<String, Object> paramMap) {
		return productItemCustomMapper.queryProductItemByProductIdAndPropValueIds(paramMap);
	}

	public List<ProductItem> findListByProductId(Integer productId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("productId", productId);
		return list(queryObject);
	}


	public ProductItem findById(int id) {
		return dao.selectByPrimaryKey(id);
	}

	public ProductItem save(ProductItem model) {
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public ProductItem update(ProductItem model) {
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id) {
		ProductItem model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public int queryStock(QueryObject queryObject) {
		return productItemCustomMapper.queryStockByExample(builderQuery(queryObject));
	}

	public List<ProductItem> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<ProductItem> paginate(QueryObject queryObject) {
		ProductItemExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<ProductItem> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private ProductItemExample builderQuery(QueryObject queryObject) {
		ProductItemExample example = new ProductItemExample();
		ProductItemExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if (queryObject.getQuery("productId") != null) {
			criteria.andProductIdEqualTo(queryObject.getQueryToInt("productId"));
		}
		if (queryObject.getQuery("productIds") != null) {
			List<Integer> productIds = queryObject.getQuery("productIds");
			criteria.andProductIdIn(productIds);
		}
		if (queryObject.getSort().size() > 0) {
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public List<ProductItemCustom> getExportProductItemCustom(int activityId) {
		return productItemCustomMapper.getExportProductItemCustom(activityId);
	}

	public List<HashMap<String, Object>> getPropDescList(Map<String, Object> paramMap) {
		return productItemCustomMapper.getPropDescList(paramMap);
	}

	public void setCloudProductItemIdNull(Integer id) {
		productItemCustomMapper.setCloudProductItemIdNull(id);
	}

	public ProductItem getMinMallPriceItem(Integer productId) {
		ProductItem minMallPriceItem = getMinPriceItem(productId, 10);
		if (minMallPriceItem == null) {
			minMallPriceItem = getMinPriceItem(productId, 11);
		}
		return minMallPriceItem;
	}

	public ProductItem getMinSalePriceItem(Integer productId) {
		ProductItem minSalePriceItem = getMinPriceItem(productId, 20);
		if (minSalePriceItem == null) {
			minSalePriceItem = getMinPriceItem(productId, 21);
		}
		return minSalePriceItem;
	}

	/**
	 * 查询商品最低价格的规格,searchType:
	 * 10 持有库存且商城价最低的规格
	 * 11 商城价最低的规格
	 * 20 持有库存且活动价最低的规格
	 * 21 活动价最低的规格
	 */
	public ProductItem getMinPriceItem(Integer productId, Integer searchType) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("searchType", searchType);
		return productItemCustomMapper.getMinPriceItem(params);
	}

	/**
	 * 批量调价
	 *
	 * @param request
	 */
	public ResponseMsg batchAdjustPrice(HttpServletRequest request) {
		List<ProductCustom> productCustoms = new ArrayList<>();
		try {
			String saleType = request.getParameter("saleType");
			List<String> productIds = Arrays.asList(request.getParameter("productIds").split(","));
			String choiceStr = request.getParameter("choiceStr");//调价模式
			String mode = request.getParameter("mode");//调价方式
			String changePrice = request.getParameter("changePrice");//调价价格
			String mantissa = request.getParameter("mantissa");//是否固定尾数
			String mantissaPrice = request.getParameter("mantissaPrice");//尾数
			List<Integer> productIdList = new ArrayList<>();
			for (String productId : productIds) {
				productIdList.add(Integer.parseInt(productId));
			}
			//勾选的商品中是否存在商品活动状态!=可报名的
			ProductCustomExample productCustomExample = new ProductCustomExample();
			productCustomExample.createCriteria().andIdIn(productIdList).andDelFlagEqualTo("0");
			productCustoms = productCustomMapper.selectByExample(productCustomExample);
			Map<Integer,ProductCustom> map = new HashMap<>();
			for (ProductCustom productCustom : productCustoms) {
				if("1".equals(saleType) && !"0".equals(productCustom.getAuditStatus()) && "0".equals(productCustom.getActivityStatusDesc())){
					map.put(productCustom.getId(),productCustom);
				} else if("2".equals(saleType) && "0".equals(productCustom.getActivityStatusDesc())){
					map.put(productCustom.getId(),productCustom);
				}else{
					return new ResponseMsg(ResponseMsg.ERROR, "调价失败，只有活动状态为可报名的才能调价");//存在不可报名的商品
				}
			}

			//批量调价
			ProductItemExample productItemExample = new ProductItemExample();
			productItemExample.createCriteria().andProductIdIn(productIdList).andDelFlagEqualTo("0");
			List<ProductItem> productItemList = productItemMapper.selectByExample(productItemExample);
			List<ProductItem> productItems = new ArrayList<>();
			for (ProductItem productItem : productItemList) {
				if (choiceStr.indexOf("1") != -1) {//商城价
					switch (mode) {//调价价格
						case "1":
							productItem.setMallPrice(productItem.getMallPrice().add(new BigDecimal(String.valueOf(Integer.parseInt(changePrice)))));
							break;
						case "2":
							productItem.setMallPrice(productItem.getMallPrice().multiply(new BigDecimal(String.valueOf((1 + (float) Integer.parseInt(changePrice) / 100)))));
							break;
						case "3":
							productItem.setMallPrice(productItem.getMallPrice().subtract(new BigDecimal(String.valueOf(Integer.parseInt(changePrice)))));
							break;
						case "4":
							productItem.setMallPrice(productItem.getMallPrice().multiply(new BigDecimal(String.valueOf(1 - ((float) Integer.parseInt(changePrice) / 100)))));
							break;
					}
					if ("1".equals(mantissa)) {//尾数
						String mallPrice = productItem.getMallPrice().toString();
						int index = mallPrice.indexOf(".");
						StringBuilder sb = new StringBuilder(mallPrice);
						if (index != -1) {//如果有小数
							productItem.setMallPrice(new BigDecimal(sb.replace(index - 1, index, mantissaPrice).toString()));
							//去掉小数
							productItem.setMallPrice(productItem.getMallPrice().setScale(0,BigDecimal.ROUND_DOWN));
						} else {
							productItem.setMallPrice(new BigDecimal(sb.substring(0, sb.length() - 1) + mantissaPrice));
						}
					}
				}
				if (choiceStr.indexOf("2") != -1) {//活动价
					switch (mode) {//调价价格
						case "1":
							productItem.setSalePrice(productItem.getSalePrice().add(new BigDecimal(String.valueOf(Integer.parseInt(changePrice)))));
							break;
						case "2":
							productItem.setSalePrice(productItem.getSalePrice().multiply(new BigDecimal(String.valueOf(1 + ((float) Integer.parseInt(changePrice) / 100)))));
							break;
						case "3":
							productItem.setSalePrice(productItem.getSalePrice().subtract(new BigDecimal(String.valueOf(Integer.parseInt(changePrice)))));
							break;
						case "4":
							productItem.setSalePrice(productItem.getSalePrice().multiply(new BigDecimal(String.valueOf(1 - ((float) Integer.parseInt(changePrice) / 100)))));
							break;
					}
					if ("1".equals(mantissa)) {//尾数
						String salePrice = productItem.getSalePrice().toString();
						int index = salePrice.indexOf(".");
						StringBuilder sb = new StringBuilder(salePrice);
						if (index != -1) {//如果有小数
							productItem.setSalePrice(new BigDecimal(sb.replace(index - 1, index, mantissaPrice).toString()));
							//去掉小数
							productItem.setSalePrice(productItem.getSalePrice().setScale(0,BigDecimal.ROUND_DOWN));
						} else {
							productItem.setSalePrice(new BigDecimal(sb.substring(0, sb.length() - 1) + mantissaPrice));
						}
					}
				}
				//吊牌价>=商城价>活动价>结算价
				if(productItem.getTagPrice().compareTo(productItem.getMallPrice()) > -1 &&
				productItem.getMallPrice().compareTo(productItem.getSalePrice()) == 1 &&
				productItem.getSalePrice().compareTo(productItem.getCostPrice()) ==1){
					productItems.add(productItem);
				}else{
					return new ResponseMsg(ResponseMsg.ERROR, "调价失败！商品价格需满足：\n 吊牌价>=商城价>活动价>结算价");//吊牌价>=商城价>活动价>结算价
				}
			}

			for (ProductItem productItem : productItems) {
				productItemMapper.updateByPrimaryKeySelective(productItem);
				//调价插入日志
				ProductItem minPriceProductItem = getMinMallPriceItem(productItem.getProductId());
				ProductItem minSalePriceProductItem = getMinSalePriceItem(productItem.getProductId());

				Product product4update = new Product();
				product4update.setId(productItem.getProductId());
				product4update.setMinPriceItemId(minPriceProductItem.getId());
				product4update.setMinMallPrice(minPriceProductItem.getMallPrice());
				product4update.setMinSalePrice(minPriceProductItem.getSalePrice());
				product4update.setMinTagPrice(minPriceProductItem.getTagPrice());
				product4update.setMinSalePriceItemId(minSalePriceProductItem.getId());
				productMapper.updateByPrimaryKeySelective(product4update);


				ProductPriceChangeLog productPriceChangeLog = new ProductPriceChangeLog();
				productPriceChangeLog.setDelFlag("0");
				productPriceChangeLog.setProductId(productItem.getProductId());
				productPriceChangeLog.setCreateDate(map.get(productItem.getProductId()).getCreateDate());
				productPriceChangeLog.setCreateBy(map.get(productItem.getProductId()).getCreateBy());
				productPriceChangeLog.setMinMallPrice(minPriceProductItem.getMallPrice());
				productPriceChangeLog.setMinMallPriceItemId(minPriceProductItem.getId());
				productPriceChangeLog.setMinSalePrice(minSalePriceProductItem.getSalePrice());
				productPriceChangeLog.setMinSalePriceItemId(minSalePriceProductItem.getId());
				productPriceChangeLogMapper.insertSelective(productPriceChangeLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS,"已成功调价"+productCustoms.size()+"个商品。");
	}
}
