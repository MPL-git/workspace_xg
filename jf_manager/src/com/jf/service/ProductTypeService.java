package com.jf.service;

import com.gzs.common.beans.Menu;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductTypeCustomMapper;
import com.jf.dao.ProductTypeMapper;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeCustom;
import com.jf.entity.ProductTypeCustomExample;
import com.jf.entity.ProductTypeExample;

import org.aspectj.weaver.ArrayAnnotationValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductTypeService extends BaseService<ProductType,ProductTypeExample> {
	@Autowired
	private ProductTypeMapper productTypeMapper;
	
	@Resource
	private ProductTypeCustomMapper productTypeCustomMapper;
	
	@Autowired
	public void setProductTypeMapper(ProductTypeMapper productTypeMapper) {
		super.setDao(productTypeMapper);
		this.productTypeMapper = productTypeMapper;
	}
	
	public List<Menu> selectProductType(Map<String, Object> paramMap){
		
		List<Menu> list = productTypeCustomMapper.selectProductType(paramMap);
		return Menu.buildTree(list, 0);
	}
	public List<?> selectProductTypeList(Map<String, Object> paramMap){
		List<?> list = productTypeCustomMapper.selectProductTypeList(paramMap);
		return list;
	}
	
	public String queryDataCount(HashMap<String, Object> paramMap){
		String tatalNum=productTypeCustomMapper.queryDataCount(paramMap);
		return tatalNum;
	}

	public List<?> getprarentList(HashMap<String, Object> paramMap){
		List<?> getprarentList=productTypeCustomMapper.getprarentList(paramMap);
		return getprarentList;
	}
	public String getprarentId(HashMap<String, Object> paramMap){
		String getprarentId=productTypeCustomMapper.getprarentId(paramMap);
		return getprarentId;
	}
	
	public String getPrarentF(HashMap<String, Object> paramMap){
		String getPrarentF=productTypeCustomMapper.getPrarentF(paramMap);
		return getPrarentF;
	}
	
	public int getIsNotType(HashMap<String, Object> paramMap){
		int getIsNotType=productTypeCustomMapper.getIsNotType(paramMap);
		return getIsNotType;
	}

	public String getProdTypeName(HashMap<String, Object> paramMap){
		String getProdTypeName=productTypeCustomMapper.getProdTypeName(paramMap);
		return getProdTypeName;
	}
	 
	public int gettlevvel(Map<String, Object> paramMap) {
		return productTypeCustomMapper.gettlevvel(paramMap);
	}

	public List<?> selectProductTypeListByid(HashMap<String, Object> map) {
		List<?> list =productTypeCustomMapper.selectProductTypeListByid(map);
		return list;
	}





	public List<ProductType> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private ProductTypeExample builderQuery(QueryObject queryObject) {
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("productTypeIds") != null){
			List<Integer> ids = queryObject.getQuery("productTypeIds");
			criteria.andIdIn(ids);
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public String getNamesByIds(String ids) {
		return productTypeCustomMapper.getNamesByIds(ids);
	}
	
	/**
	 * 
	 * @Title addInsertSelective   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月3日 下午2:04:03
	 */
	public Map<String, Object> addInsertSelective(List<ProductType> productTypeList) {
		Map<String, Object> nameIdMap = new HashMap<String, Object>();
		StringBuffer ids = new StringBuffer();
		StringBuffer names = new StringBuffer();
		for(ProductType productType : productTypeList) {
			productTypeMapper.insertSelective(productType);
			int seqNo = productType.getId()*100;
			productType.setSeqNo(seqNo);
			productTypeMapper.updateByPrimaryKeySelective(productType);
			if(ids.length() == 0) {
				ids.append(productType.getId());
			}else {
				ids.append(","+productType.getId());
			}
			if(names.length() == 0) {
				names.append(productType.getName());
			}else {
				names.append(","+productType.getName());
			}
		}
		nameIdMap.put("ids", ids);
		nameIdMap.put("names", names);
		return nameIdMap;
	}
	
	public List<Map<String, Object>> getProductTypeList(Map<String, Object> map) {
		return productTypeCustomMapper.getProductTypeList(map);
	}
	public Integer getProductTypeCount(Map<String, Object> map) {
		return productTypeCustomMapper.getProductTypeCount(map);
	}
	public String getProductTypeIds(Map<String, Object> map) {
		return productTypeCustomMapper.getProductTypeIds(map);
	}
	public String getProductCount(Map<String, Object> map) {
		return productTypeCustomMapper.getProductCount(map);
	}

	public Integer countListByFirstProductTypeIds(HashMap<String, Object> map) {
		return productTypeCustomMapper.countListByFirstProductTypeIds(map);
	}
	
	public List<HashMap<String,Object>> getListByFirstProductTypeIds(HashMap<String, Object> map) {
		return productTypeCustomMapper.getListByFirstProductTypeIds(map);
	}

	public void update(ProductType productType) {
		if(productType.getIsSmallware().equals("1")){//1.是小商品,如果一级类目是小商品，二级类目未额外设置，则默认二级类目全部是小商品，有额外设置否，才不是小商品，三级分类同上
			if(!productType.gettLevel().equals(3)){//不是三级类目
				ProductTypeExample e = new ProductTypeExample();
				e.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(productType.getId()).andIsSmallwareIsNull().andStatusEqualTo("1");
				ProductType pt = new ProductType();
				pt.setIsSmallware("1");
				pt.setUpdateBy(productType.getUpdateBy());
				pt.setUpdateDate(productType.getUpdateDate());
				this.updateByExampleSelective(pt, e);
				if(productType.gettLevel().equals(1)){
					ProductTypeExample pte = new ProductTypeExample();
					pte.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(productType.getId()).andStatusEqualTo("1");
					List<ProductType> productTypes = this.selectByExample(pte);
					List<Integer> idList = new ArrayList<Integer>();
					for(ProductType item:productTypes){//2级类目id
						idList.add(item.getId());
					}
					ProductTypeExample example = new ProductTypeExample();
					example.createCriteria().andDelFlagEqualTo("0").andParentIdIn(idList).andIsSmallwareIsNull().andStatusEqualTo("1");
					ProductType updateProductType = new ProductType();
					updateProductType.setIsSmallware("1");
					updateProductType.setUpdateBy(productType.getUpdateBy());
					updateProductType.setUpdateDate(productType.getUpdateDate());
					this.updateByExampleSelective(updateProductType, example);
				}
			}
		}
		this.updateByPrimaryKeySelective(productType);
	}
	
	
	public List<ProductTypeCustom> getProductTypeListByStaffId(Integer staffId) {
		return productTypeCustomMapper.getProductTypeListByStaffId(staffId);
	}

    public List<ProductTypeCustom> getcategoryFlowReportData(Map<String, Object> paraMap) {
		return productTypeCustomMapper.getcategoryFlowReportData(paraMap);
    }
}
