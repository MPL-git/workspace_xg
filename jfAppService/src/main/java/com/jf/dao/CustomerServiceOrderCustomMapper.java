package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceOrderCustomMapper extends BaseDao<CustomerServiceOrder, CustomerServiceOrderExample> {

    List<CustomerServiceOrder> getByCombineOrderId(Integer combineOrderId);
    
}
