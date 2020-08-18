package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ViolateOrder;
import com.jf.entity.ViolateOrderExample;
@Repository
public interface ViolateOrderMapper extends BaseDao<ViolateOrder, ViolateOrderExample>{
    int countByExample(ViolateOrderExample example);

    int deleteByExample(ViolateOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ViolateOrder record);

    int insertSelective(ViolateOrder record);

    List<ViolateOrder> selectByExample(ViolateOrderExample example);

    ViolateOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ViolateOrder record, @Param("example") ViolateOrderExample example);

    int updateByExample(@Param("record") ViolateOrder record, @Param("example") ViolateOrderExample example);

    int updateByPrimaryKeySelective(ViolateOrder record);

    int updateByPrimaryKey(ViolateOrder record);
}