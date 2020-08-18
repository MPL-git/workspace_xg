package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServiceLogCustom;
import com.jf.entity.CustomerServiceLogExample;
@Repository
public interface CustomerServiceLogCustomMapper{
    int countByExample(CustomerServiceLogExample example);
    List<CustomerServiceLogCustom> selectByExample(CustomerServiceLogExample example);
    CustomerServiceLogCustom selectByPrimaryKey(Integer id);
}