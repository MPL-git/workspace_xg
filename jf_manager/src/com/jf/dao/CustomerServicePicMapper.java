package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServicePic;
import com.jf.entity.CustomerServicePicExample;
@Repository
public interface CustomerServicePicMapper extends BaseDao<CustomerServicePic, CustomerServicePicExample>{
    int countByExample(CustomerServicePicExample example);

    int deleteByExample(CustomerServicePicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerServicePic record);

    int insertSelective(CustomerServicePic record);

    List<CustomerServicePic> selectByExample(CustomerServicePicExample example);

    CustomerServicePic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerServicePic record, @Param("example") CustomerServicePicExample example);

    int updateByExample(@Param("record") CustomerServicePic record, @Param("example") CustomerServicePicExample example);

    int updateByPrimaryKeySelective(CustomerServicePic record);

    int updateByPrimaryKey(CustomerServicePic record);
}