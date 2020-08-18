package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CutPriceOrder;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年8月2日 下午2:45:41 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface CutPriceOrderCustomMapper{
	
	/**
	 * 获取助力大减价超期的单据
	 */
	List<CutPriceOrder> getAssistanceExpireOrder();
	
}
