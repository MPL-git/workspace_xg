package com.jf.dao;

import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceOrderMapper extends BaseDao<CustomerServiceOrder, CustomerServiceOrderExample> {
    int countByExample(CustomerServiceOrderExample example);

    int deleteByExample(CustomerServiceOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerServiceOrder record);

    int insertSelective(CustomerServiceOrder record);

    List<CustomerServiceOrder> selectByExample(CustomerServiceOrderExample example);

    CustomerServiceOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerServiceOrder record, @Param("example") CustomerServiceOrderExample example);

    int updateByExample(@Param("record") CustomerServiceOrder record, @Param("example") CustomerServiceOrderExample example);

    int updateByPrimaryKeySelective(CustomerServiceOrder record);

    int updateByPrimaryKey(CustomerServiceOrder record);
}