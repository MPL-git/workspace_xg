package com.jf.dao;

import com.jf.entity.SubOrderCustom;
import com.jf.entity.SubOrderCustomExample;
import com.jf.entity.SubOrderExample;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SubOrderCustomMapper{
    int countByExample(SubOrderExample example);
    List<SubOrderCustom> selectByExample(SubOrderExample example);
    List<SubOrderCustom> selectSubOrderByExample(SubOrderExample example);
    SubOrderCustom selectByPrimaryKey(Integer id);
	List<HashMap<String, Object>> deliveryOvertimeCount(HashMap<String,Object> map);
	List<HashMap<String, Object>> falseOrderCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> outOfStockOrderCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> otherExceptionOrderCount(HashMap<String, Object> paramMap);
	/*List<SubOrderCustom> customerserviceOrderCount(HashMap<String, Object> paramMap);*/
	List<HashMap<String, Object>> customerserviceOrderCount(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> getOrderStatisticsList(HashMap<String, Object> paramMap);
	Integer countOrderStatisticsList(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> selectDailyCollectionSummary(HashMap<String, Object> paramMap);
	Integer countDailyCollectionSummary(HashMap<String, Object> paramMap);

    SubOrderCustom selectStudy();

	Integer countReceiverName(HashMap<String,Object> parameMap);

	List<SubOrderCustom> selectSubOrderByExampleAndDate(SubOrderCustomExample example);
}