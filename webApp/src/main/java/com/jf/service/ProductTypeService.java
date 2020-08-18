package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.ListHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductTypeCustomMapper;
import com.jf.dao.ProductTypeMapper;
import com.jf.entity.Product;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeCustom;
import com.jf.entity.ProductTypeExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月7日 下午3:26:42 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductTypeService extends BaseService<ProductType, ProductTypeExample> {
	@Autowired
	private ProductTypeMapper productTypeMapper;
	@Autowired
	private ProductTypeCustomMapper productTypeCustomMapper;

	@Autowired
	public void setProductTypeMapper(ProductTypeMapper productTypeMapper) {
		this.setDao(productTypeMapper);
		this.productTypeMapper = productTypeMapper;
	}

	public List<ProductTypeCustom> getThreeIdByIds(Map<String, Object> paramsMap) {
		
		return productTypeCustomMapper.getThreeIdByIds(paramsMap);
	}

	public Map<String, Object> getCategoryList(Integer productTypeId) {
		if(productTypeId == null){
			productTypeId = 1;//全部分类顶级
		}
		Map<String,Object> map = getCategoryTypeList(null, productTypeId,"2");
		return map;
	}
	
	public Map<String, Object> getCategoryTypeList(ProductType productType, Integer productTypeId, String type) {
		List<Map<String,Object>> categoryList = new ArrayList<Map<String,Object>>();
		String parentId = "";
		String parentName = "";
		String parenttLevel = "";
		if(productTypeId != null){
			if(productType == null){
				productType = selectByPrimaryKey(productTypeId);
			}
			ProductTypeExample typeExample = new ProductTypeExample();
			ProductTypeExample.Criteria criteria = typeExample.createCriteria();
			if("3".equals(productType.gettLevel().toString())){
				//如果前端首页点击的是三级品类，要查出该品类的父级
				productType = selectByPrimaryKey(productType.getParentId());
				parentName = productType.getName();
			}
			if(!"1".equals(type)){//1筛选页面 2全部品类页面
				parentName = productType.getName();
			}
			parentId = productType.getId().toString();
			parenttLevel = productType.gettLevel().toString();
			//获取该级的所有子集
			criteria.andStatusEqualTo("1").andDelFlagEqualTo("0").andParentIdEqualTo(productType.getId());
			typeExample.setOrderByClause("seq_no,id DESC");
			List<ProductType> productTypes = selectByExample(typeExample);
			int i = 0;
			if(CollectionUtil.isNotEmpty(productTypes)){
				for (ProductType pt : productTypes) {
					Map<String, Object> dataMap = new HashMap<String, Object>();
					String id = pt.getId().toString();
					String name = pt.getName();
					String pId = pt.getParentId().toString();
					String tLevel = pt.gettLevel().toString();
					if("1".equals(type)){
						i++;
						if(i == 9){
							id = productType.getId().toString();
							name = "全部品类";
							pId = productType.getParentId().toString();
							tLevel = productType.gettLevel().toString();
						}else if( i > 9){
							break;
						}
					}
					dataMap.put("productTypeId", id);
					dataMap.put("productTypeName", name);
					dataMap.put("parentId", pId);
					dataMap.put("tLevel", tLevel);
					categoryList.add(dataMap);
				}
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("categoryList", categoryList);
		map.put("parentId", parentId);
		map.put("parentName", parentName);
		map.put("parenttLevel", parenttLevel);
		return map;
	}
	
	/**
	 * 获取商家入驻类型相应品类佣金比例
	 */
	public BigDecimal getCategoryPopRateBysettledType(Integer productTypeId, String settledType) {
		BigDecimal zero = new BigDecimal("0");
		BigDecimal popFeeRate = new BigDecimal("0");
		ProductTypeExample ptExample = new ProductTypeExample();
		ProductTypeExample.Criteria criteria = ptExample.createCriteria();
		criteria.andIdEqualTo(productTypeId).andStatusEqualTo("1").andDelFlagEqualTo("0");
		if("1".equals(settledType)){
			criteria.andFeeRateIsNotNull().andFeeRateGreaterThan(zero);
		}else{
			criteria.andIndividualFeeRateIsNotNull().andIndividualFeeRateGreaterThan(zero);
		}
		List<ProductType> pts = selectByExample(ptExample);
		if(CollectionUtils.isNotEmpty(pts)){
			if("1".equals(settledType)){
				popFeeRate = pts.get(0).getFeeRate();
			}else{
				popFeeRate = pts.get(0).getIndividualFeeRate();
			}
		}
		return popFeeRate;
	}

	public boolean getProductTypeIsReturn7days(Product p) {
		ProductType productType = selectByPrimaryKey(p.getProductTypeId());
		String productType7days = productType.getIsReturn7days();
		String isReturn7days = p.getIsReturn7days();
		if(StringUtil.isBlank(productType7days)){
			productType = selectByPrimaryKey(productType.getParentId());
			productType7days = productType.getIsReturn7days();
			if(StringUtil.isBlank(productType7days)){
				productType = selectByPrimaryKey(productType.getParentId());
				productType7days = productType.getIsReturn7days();
			}
		}
		boolean b = isReturn7days(productType7days,isReturn7days);
		return b;
	}
	
	private boolean isReturn7days(String productType7days, String isReturn7days) {
		boolean b = false;
		if("0".equals(productType7days)){
			b = false;
		}else if("1".equals(productType7days)){
			b = true;
		}else if("2".equals(productType7days) || StringUtil.isBlank(productType7days)){
			if("0".equals(isReturn7days)){
				b = false;
			}else if("1".equals(isReturn7days)){
				b = true;
			}else{
				b = true;
			}
		}
		return b;
	}

	public List<ProductType> findModelsByIds(List<Integer> productTypeIds) {
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andIdIn(productTypeIds).andStatusEqualTo("1").andDelFlagEqualTo("0");
		return selectByExample(productTypeExample);
	}

	public List<Integer> findProductType1Ids() {
		ProductTypeExample example = new ProductTypeExample();
		example.createCriteria()
				.andTLevelEqualTo(1)
				.andStatusEqualTo("1")
				.andDelFlagEqualTo(StateConst.FALSE);
		List<ProductType> list = this.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}

		return ListHelper.extractIds(list, new ListHelper.IdExtractor<ProductType>() {
			@Override
			public Integer getId(ProductType source) {
				return source.getId();
			}
		});
	}
}
