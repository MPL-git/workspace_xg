package com.jf.dao;

import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SingleProductActivityCustomExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @ClassName SingleProductActivityCustomMapper
 * @Description TODO(单品活动表Custom)
 * @author pengl
 * @date 2017年9月30日 下午4:08:12
 */
@Repository
public interface SingleProductActivityCustomMapper {
    
	public List<SingleProductActivityCustom> selectByCustomExampl(SingleProductActivityCustomExample example);
	
	public Integer countByCustomExample(SingleProductActivityCustomExample example);
	
	public Integer updateByCustomExampleSelective(@Param("record") SingleProductActivity record, @Param("example") SingleProductActivityCustomExample example);
	
	
	/**
	 * 
	 * @Title selectBySingleProductActivityProductExampl   
	 * @Description TODO(取同商家同品牌同货号商品中历史活动价格的最低值（不包含当前活动价格）)   
	 * @author pengl
	 * @date 2018年7月26日 上午9:44:03
	 */
	public List<SingleProductActivityCustom> selectBySingleProductActivityProductExampl(SingleProductActivityCustomExample example);
	
	public Integer countBySingleProductActivityProductExample(SingleProductActivityCustomExample example);
	
	public List<SingleProductActivityCustom> selectProductQuantitySumsByProductIds(@Param("productIds") List<Integer> productIds);

    Integer countSingleProductActivityTrafficStatisticsRealTime(HashMap<String, Object> paraMap);

	List<HashMap<String,Object>> selectSingleProductActivityTrafficStatisticsRealTime(HashMap<String, Object> paraMap);
}