package com.jf.dao;

import com.jf.entity.CouponCustom;
import com.jf.entity.ProductType;
import com.jf.entity.SourceProductType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SourceNicheCustomMapper {

	int updateSourceNicheSupportQuantity(Integer sourceNicheId);
	
	List<Map<String, Object>> getProductCouponList(Map<String, Object> paramMap);

	List<ProductType> getProductTypeList();

	List<Map<String, Object>> getProductCouponListByProductTypeId(Map<String, Object> map);

	List<CouponCustom> getActivityAreaCouponList(Map<String, Object> map);

	List<SourceProductType> getSourceProductTypeListByActivityAreaCoupon();

	List<CouponCustom> getCouponListByRecBeginDate(Map<String,Object> map);

    int decreaseLotteryProductStock(Map<String, Object> params);

}
