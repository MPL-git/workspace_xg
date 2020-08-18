package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceLogMapper extends BaseDao<CustomerServiceLog, CustomerServiceLogExample> {
    int countByExample(CustomerServiceLogExample example);

    int deleteByExample(CustomerServiceLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerServiceLog record);

    int insertSelective(CustomerServiceLog record);

    List<CustomerServiceLog> selectByExample(CustomerServiceLogExample example);

    CustomerServiceLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerServiceLog record, @Param("example") CustomerServiceLogExample example);

    int updateByExample(@Param("record") CustomerServiceLog record, @Param("example") CustomerServiceLogExample example);

    int updateByPrimaryKeySelective(CustomerServiceLog record);

    int updateByPrimaryKey(CustomerServiceLog record);
}
