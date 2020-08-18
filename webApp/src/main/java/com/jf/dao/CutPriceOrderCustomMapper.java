package com.jf.dao;

import java.util.List;
import java.util.Map;

import com.jf.entity.dto.CutPriceOrderDTO;
import org.springframework.stereotype.Repository;

import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderCustom;

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
	 * 更新砍价单主表砍价成功状态
	 */
	int updateCutOrderStatus(Integer cutOrderId);
	/**
	 * 更新砍价单主表下单成功状态且关联总订单
	 */
	int updateCutOrderSuccess(CutPriceOrder cutPriceOrder);
	/**
	 * 我领取的助力大减价列表
	 */
	List<CutPriceOrderCustom> getMyAssistanceGoodsTaskList(Map<String, Object> paramsMap);

    List<CutPriceOrderDTO> findAssistanceCutCompleteLog(Map<String,Object> paramsMap);

}
