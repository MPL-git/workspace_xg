package com.jf.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.dao.*;
import com.jf.entity.*;
import org.apache.poi.ss.formula.functions.Now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @ClassName ProductAuditLogService
 * @Description TODO(商品审核流水表)
 * @author pengl
 * @date 2017年11月13日 下午6:15:29
 */
@Service
@Transactional
public class ProductAuditLogService extends BaseService<ProductAuditLog, ProductAuditLogExample> {

	@Autowired
	private ProductAuditLogMapper productAuditLogMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductAuditLogCustomMapper productAuditLogCustomMapper;

	@Autowired
	private ProductItemMapper productItemMapper;

	@Autowired
	private ProductPriceChangeLogMapper productPriceChangeLogMapper;

	@Autowired
	private ProductItemService productItemService;

	@Autowired
	private ProductUpperLowerLogService productUpperLowerLogService;
	@Autowired
	public void setProductAuditLogMapper(ProductAuditLogMapper productAuditLogMapper) {
		super.setDao(productAuditLogMapper);
		this.productAuditLogMapper = productAuditLogMapper;
	}
	
	/**
	 * 
	 * @Title updateProductAndSaveProductAuditLog   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2017年11月13日 下午6:22:14
	 */
	public void updateProductAndSaveProductAuditLog(Integer id, String auditRemarks, 
			String auditStatus, Integer staffID) {
		ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
		Product product=new Product();
		product.setId(id);
		product.setAuditRemarks(auditRemarks);
		product.setAuditStatus(auditStatus);
		product.setAuditBy(staffID);
		product.setUpdateBy(staffID);
		product.setUpdateDate(new Date());
		if ("2".equals(auditStatus)){
			product.setStatus("1");
			product.setOffReason("");
			productUpperLowerLog.setStatus("1");
		}else if ("3".equals(auditStatus)){
			product.setStatus("0");
			product.setOffReason("2");
			productUpperLowerLog.setStatus("0");
			productUpperLowerLog.setOffReason("平台强制下架");
		}
		productMapper.updateByPrimaryKeySelective(product);

		if(!"1".equals(auditStatus)){
			productUpperLowerLog.setProductId(id);
			productUpperLowerLog.setType(Const.PLATFORM);
			productUpperLowerLog.setCreateBy(staffID);
			productUpperLowerLog.setCreateDate(new Date());
			productUpperLowerLogService.insertSelective(productUpperLowerLog);
		}

		ProductAuditLog productAuditLog = new ProductAuditLog();
		productAuditLog.setCreateBy(staffID);
		productAuditLog.setCreateDate(new Date());
		productAuditLog.setProductId(id);
		productAuditLog.setRemarks(auditRemarks);
		productAuditLog.setStatus(auditStatus);
		productAuditLogMapper.insertSelective(productAuditLog);
	}
	
	public List<ProductAuditLogCustom> selectByCustomExample(ProductAuditLogCustomExample example) {
		return productAuditLogCustomMapper.selectByCustomExample(example);
	}
	
	public Integer countByCustomExample(ProductAuditLogCustomExample example) {
		return productAuditLogCustomMapper.countByCustomExample(example);
	}

	public void updateProductItem(Integer staffID, String mallPrice, String salePrice) {
		Date date = new Date();
		String productItemId = "";
		Map<String, String> mallPriceMap = new HashMap<String, String>();
		if(!StringUtil.isEmpty(mallPrice) ) {
			String[] mallPriceIds = mallPrice.split("-");
			for(String mallPriceIdStr : mallPriceIds ) {
				String[] mp = mallPriceIdStr.split(",");
				mallPriceMap.put(mp[0], mp[1]);
				if(StringUtil.isEmpty(productItemId) ) {
					productItemId = mp[0];
				}
			}
		}
		Map<String, String> salePriceMap = new HashMap<String, String>();
		if(!StringUtil.isEmpty(salePrice) ) {
			String[] salePriceIds = salePrice.split("-");
			for(String salePriceIdStr : salePriceIds ) {
				String[] sp = salePriceIdStr.split(",");
				salePriceMap.put(sp[0], sp[1]);
			}
		}
		for(Map.Entry<String, String> entry : mallPriceMap.entrySet() ) {
			ProductItem productItem = new ProductItem();
			productItem.setId(Integer.parseInt(entry.getKey()));
			productItem.setMallPrice(new BigDecimal(entry.getValue()));
			productItem.setSalePrice(new BigDecimal(salePriceMap.get(entry.getKey())));
			productItem.setUpdateDate(date);
			productItem.setUpdateBy(staffID);
			productItemMapper.updateByPrimaryKeySelective(productItem);
		}
		if(!StringUtil.isEmpty(productItemId) ) {
			ProductItem productItem = productItemMapper.selectByPrimaryKey(Integer.parseInt(productItemId));
			if(productItem != null ) {
				Product oldProduct = productMapper.selectByPrimaryKey(productItem.getProductId());
				ProductItem oldMinSalePriceItem = productItemMapper.selectByPrimaryKey(oldProduct.getMinSalePriceItemId());

				ProductItem minPriceProductItem = productItemService.getMinMallPriceItem(oldProduct.getId());
				ProductItem minSalePriceProductItem = productItemService.getMinSalePriceItem(oldProduct.getId());

				Product product4update = new Product();
				product4update.setId(oldProduct.getId());
				product4update.setMinPriceItemId(minPriceProductItem.getId());
				product4update.setMinMallPrice(minPriceProductItem.getMallPrice());
				product4update.setMinSalePrice(minPriceProductItem.getSalePrice());
				product4update.setMinTagPrice(minPriceProductItem.getTagPrice());
				product4update.setMinSalePriceItemId(minSalePriceProductItem.getId());
				product4update.setUpdateBy(staffID);
				product4update.setUpdateDate(date);
				productMapper.updateByPrimaryKeySelective(product4update);

				if(minPriceProductItem.getMallPrice().compareTo(oldProduct.getMinMallPrice()) != 0
						|| minSalePriceProductItem.getSalePrice().compareTo(oldMinSalePriceItem.getSalePrice()) != 0 ) {
					ProductPriceChangeLog productPriceChangeLog=new ProductPriceChangeLog();
					productPriceChangeLog.setDelFlag("0");
					productPriceChangeLog.setProductId(product4update.getId());
					productPriceChangeLog.setCreateDate(product4update.getUpdateDate());
					productPriceChangeLog.setCreateBy(product4update.getUpdateBy());
					productPriceChangeLog.setMinMallPrice(minPriceProductItem.getMallPrice());
					productPriceChangeLog.setMinMallPriceItemId(minPriceProductItem.getId());
					productPriceChangeLog.setMinSalePrice(minSalePriceProductItem.getSalePrice());
					productPriceChangeLog.setMinSalePriceItemId(minSalePriceProductItem.getId());
					productPriceChangeLogMapper.insertSelective(productPriceChangeLog);
				}

			}
		}


	}


	
}
