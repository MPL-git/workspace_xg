package com.jf.dao;

import com.jf.entity.CombineOrderCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年9月4日 下午3:51:53 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface CombineOrderCustomMapper {

	List<CombineOrderCustom> getNoPaidOrderList(Map<String, Object> paramsMap);

	CombineOrderCustom getOrderAddressInfo(Integer subOrderId);

	List<CombineOrderCustom> findSuperiorGetFristOrderReward();

	CombineOrderCustom getCombineOrderCustomByLogisticCode(@Param("kdnCode") String kdnCode,@Param("logisticCode") String logisticCode);

	List<CombineOrderCustom> getMemberList(Map<String, Object> paramsMap);
	
	List<CombineOrderCustom> getCombineOrderList(Map<String, Object> paramsMap);
}
