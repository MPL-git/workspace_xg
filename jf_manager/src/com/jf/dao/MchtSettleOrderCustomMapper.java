package com.jf.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MchtSettleOrderCustom;
import com.jf.entity.MchtSettleOrderCustomExample;
import com.jf.entity.MchtSettleOrderExample;
import com.jf.entity.MchtSettleSituation;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.PayToMchtLog;
@Repository
public interface MchtSettleOrderCustomMapper{
	int countByExample(MchtSettleOrderExample example);
    List<MchtSettleOrderCustom> selectByExample(MchtSettleOrderExample example);
	MchtSettleOrderCustom countCompleteSubOrder(Integer id);
	BigDecimal countCustomerServiceOrder(Integer id);
	List<OrderDtlCustom> selectNoSituationCustomOrderDtlList(HashMap<String, String> paramMap);
	List<CustomerServiceOrderCustom> selectNoSituationCustomOrderList(HashMap<String, String> paramMap);
	List<PayToMchtLog> selectNoSituationPayLogList(HashMap<String, String> paramMap);
	List<Map<String, Object>> selectSituationDepositTotal(HashMap<String, String> paramMap);
	MchtSettleSituation selectSituationByMchtIdAndDate(HashMap<String, Object> map);
	void batchConfirmConfirmStatus(List<Integer> idsList);
	void batchPayStatus(HashMap<String, Object> paramMap);
	BigDecimal getNeedPayAmountByIds(List<Integer> idsList);
	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月23日 下午2:14:52
	 */
	List<MchtSettleOrderCustom> selectByCustomExample(
			MchtSettleOrderCustomExample mchtSettleOrderCustomExample);
}