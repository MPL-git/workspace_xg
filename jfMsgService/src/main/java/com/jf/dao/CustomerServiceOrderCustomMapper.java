package com.jf.dao;

import java.util.List;
import java.util.Map;

import com.jf.entity.CustomerServiceOrderCustom;
import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;

@Repository
public interface CustomerServiceOrderCustomMapper extends BaseDao<CustomerServiceOrder, CustomerServiceOrderExample> {
    int countByExample(CustomerServiceOrderExample example);

    List<CustomerServiceOrder> selectByExample(CustomerServiceOrderExample example);

    CustomerServiceOrder selectByPrimaryKey(Integer id);
    
    List<CustomerServiceOrder> getByCombineOrderId(Integer combineOrderId);
    
    List<Map<String, Object>> selectPhoneWhileB2C2();

    CustomerServiceOrderCustom selectMemberIdAndPhone(Integer id);
}
