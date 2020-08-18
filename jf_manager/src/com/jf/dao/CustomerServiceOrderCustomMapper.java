package com.jf.dao;

import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.CustomerServiceOrderExample;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public interface CustomerServiceOrderCustomMapper{
    int countByExample(CustomerServiceOrderExample example);
    List<CustomerServiceOrderCustom> selectByExample(CustomerServiceOrderExample example);
    int countCustomerOrderList(HashMap paramMap);
    List<CustomerServiceOrderCustom> selectCustomerOrderList(HashMap paramMap);
    CustomerServiceOrderCustom selectByPrimaryKey(Integer id);
	List<CustomerServiceOrderCustom> selectCustomServiceOrderCountList(Map<String, Object> paramMap);
	List<CustomerServiceOrderCustom> selectCustomServiceOrderAmountCountList(Map<String, Object> paramMap);
	List<HashMap<String, Object>> mchtCustomServiceQuantityList(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> mchtCustomServiceAmountList(HashMap<String, Object> paramMap);
}