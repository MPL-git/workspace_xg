package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductType;
import com.jf.entity.SingleProductActivityCustom;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月29日 下午3:18:43 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface SingleProductActivityCustomMapper {

	List<SingleProductActivityCustom> getSeckillTimeTab();

	List<SingleProductActivityCustom> getSeckillTimeList(Map<String, Object> params);

	List<SingleProductActivityCustom> getSingleExplosionActivityList(Map<String, Object> params);

	List<SingleProductActivityCustom> getSingleNewEnjoyActivityList(Map<String, Object> params);

	List<SingleProductActivityCustom> getSingleActivityScreen(String type);

	List<SingleProductActivityCustom> getNewUserSeckillTimeList(Map<String, Object> params);

	List<SingleProductActivityCustom> getSingleBrokenCodeClearingActivityList(Map<String, Object> paramsMap);

	List<SingleProductActivityCustom> getSingleScreeningConditions6(Map<String, Object> paramsMap);

	List<SingleProductActivityCustom> getTwoCategoryProductSize(Map<String, Object> paramsMap);

	List<SingleProductActivityCustom> getSeckillBrandGroupList(Map<String, Object> params);

	List<SingleProductActivityCustom> getSingleBargainGoodsList(Map<String, Object> paramsMap);

	List<SingleProductActivityCustom> getSingleCutProductInfo(Map<String, Object> paramsMap);

	List<SingleProductActivityCustom> getCutOrderProductInfo(Map<String, Object> paramsMap);

	List<ProductType> getProductTypeList();

	List<SingleProductActivityCustom> getSingleProductActivityCustomsByProductTypeId(Map<String, Object> params);

	String getPropValIds(Map<String, Object> param);

}
