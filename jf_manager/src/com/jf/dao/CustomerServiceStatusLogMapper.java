package com.jf.dao;

import com.jf.entity.CustomerServiceStatusLog;
import com.jf.entity.CustomerServiceStatusLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceStatusLogMapper extends BaseDao<CustomerServiceStatusLog, CustomerServiceStatusLogExample> {
    int countByExample(CustomerServiceStatusLogExample example);

    int deleteByExample(CustomerServiceStatusLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerServiceStatusLog record);

    int insertSelective(CustomerServiceStatusLog record);

    List<CustomerServiceStatusLog> selectByExample(CustomerServiceStatusLogExample example);

    CustomerServiceStatusLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerServiceStatusLog record, @Param("example") CustomerServiceStatusLogExample example);

    int updateByExample(@Param("record") CustomerServiceStatusLog record, @Param("example") CustomerServiceStatusLogExample example);

    int updateByPrimaryKeySelective(CustomerServiceStatusLog record);

    int updateByPrimaryKey(CustomerServiceStatusLog record);
}