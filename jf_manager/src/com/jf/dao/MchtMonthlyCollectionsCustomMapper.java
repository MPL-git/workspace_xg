package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.MchtMonthlyCollectionsCustom;
import com.jf.entity.MchtMonthlyCollectionsCustomExample;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.PayToMchtLog;
@Repository
public interface MchtMonthlyCollectionsCustomMapper{

	List<MchtMonthlyCollectionsCustom> selectByExample(MchtMonthlyCollectionsCustomExample example);

	int countByExample(MchtMonthlyCollectionsCustomExample example);

	List<OrderDtlCustom> selectNoMonthlyCollectionsOrderDtlList(HashMap<String, String> paramMap);

	List<CustomerServiceOrderCustom> selectCustomServiceOrderDList(HashMap<String, String> paramMap);

	List<PayToMchtLog> selectNoSituationPayLogList(HashMap<String, String> paramMap);

	List<OrderDtlCustom> selectOrderDtlList4CustomServiceOrderAB(HashMap<String, String> paramMap);

	List<MchtMonthlyCollectionsCustom> getListByYear(Map<String, String> paramMap);

	List<MchtMonthlyCollectionsCustom> getListByDay(Map<String, String> paramMap);

	List<MchtMonthlyCollectionsCustom> getMonthlyCollectionsByMonth(HashMap<String, String> paramMap);

}