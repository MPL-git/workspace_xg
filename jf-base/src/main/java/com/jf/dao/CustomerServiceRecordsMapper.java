package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CustomerServiceRecords;
import com.jf.entity.CustomerServiceRecordsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceRecordsMapper extends BaseDao<CustomerServiceRecords, CustomerServiceRecordsExample> {
    int countByExample(CustomerServiceRecordsExample example);

    int deleteByExample(CustomerServiceRecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerServiceRecords record);

    int insertSelective(CustomerServiceRecords record);

    List<CustomerServiceRecords> selectByExample(CustomerServiceRecordsExample example);

    CustomerServiceRecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerServiceRecords record, @Param("example") CustomerServiceRecordsExample example);

    int updateByExample(@Param("record") CustomerServiceRecords record, @Param("example") CustomerServiceRecordsExample example);

    int updateByPrimaryKeySelective(CustomerServiceRecords record);

    int updateByPrimaryKey(CustomerServiceRecords record);
}
