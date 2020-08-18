package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SubOrderCustom;
import com.jf.entity.SubOrderCustomExample;
import com.jf.entity.SubOrderExample;
@Repository
public interface SubOrderCustomMapper{
    public List<SubOrderCustom>selectByExample(SubOrderExample subOrderExample);

	public int countByExample(SubOrderCustomExample example);

	public SubOrderCustom selectByPrimaryKey(Integer id);

	public int countWaitDelivery(Map<String, Object> map);

	public int countConfirmDelivery(Map<String, Object> map);

	public int countHasDelivery(Map<String, Object> map);

	public void updateExpressId(Map<String, Object> map);

	public void updateDeliveryDateAndStatus(Map<String, Object> map);
	
	//订单号查子订单集合
	public String selectBySubOrderCodes(@Param("socList")List<String> list);
	
	//memberids集合
	public String selectMemberIdsBySubOrderCodes(@Param("socList")List<String> list);

	//订单ids得到订单号集合
    public List<String> selectSubOrderCodesBySubOrderIds(@Param("subOrderIdsList")List<String> list);
}