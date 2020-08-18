package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServiceStatusLogCustom;
@Repository
public interface CustomerServiceStatusLogCustomMapper{

	List<CustomerServiceStatusLogCustom> getRefundDetailLog(Map<String, Object> params);
    
}