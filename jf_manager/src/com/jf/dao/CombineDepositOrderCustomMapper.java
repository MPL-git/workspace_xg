package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServiceLogCustom;
import com.jf.entity.CustomerServiceLogExample;
@Repository
public interface CombineDepositOrderCustomMapper{
    int countByExample(CustomerServiceLogExample example);
    List<CustomerServiceLogCustom> selectByExample(CustomerServiceLogExample example);
    CustomerServiceLogCustom selectByPrimaryKey(Integer id);
	List<Map<String, Object>> combineDepositOrderCountEachDayList(HashMap<String, Object> paramMap);
}