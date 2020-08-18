package com.jf.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2020-02-28 上午 10:24
 */
@Repository
public interface DeliveryOvertimeSpecialCnfCustomMapper {

	public int specialAreaDeliveryLastDate(Map<String, Object> paramMap);

	public int updateDeliveryOvertimeBackups(Map<String, Object> paramMap);

	public int insertDeliveryOvertimeBackups(Map<String, Object> paramMap);

	public List<Map<String, Object>> deliveryOvertimeSubOrderList(Map<String, Object> paramMap);

	public int updateSubOrder(Map<String, Object> paramMap);

}
