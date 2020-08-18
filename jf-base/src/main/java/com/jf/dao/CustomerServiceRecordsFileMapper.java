package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CustomerServiceRecordsFile;
import com.jf.entity.CustomerServiceRecordsFileExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceRecordsFileMapper extends BaseDao<CustomerServiceRecordsFile, CustomerServiceRecordsFileExample> {
    int countByExample(CustomerServiceRecordsFileExample example);

    int deleteByExample(CustomerServiceRecordsFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerServiceRecordsFile record);

    int insertSelective(CustomerServiceRecordsFile record);

    List<CustomerServiceRecordsFile> selectByExample(CustomerServiceRecordsFileExample example);

    CustomerServiceRecordsFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerServiceRecordsFile record, @Param("example") CustomerServiceRecordsFileExample example);

    int updateByExample(@Param("record") CustomerServiceRecordsFile record, @Param("example") CustomerServiceRecordsFileExample example);

    int updateByPrimaryKeySelective(CustomerServiceRecordsFile record);

    int updateByPrimaryKey(CustomerServiceRecordsFile record);
}
