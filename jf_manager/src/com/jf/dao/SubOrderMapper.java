package com.jf.dao;

import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubOrderMapper extends BaseDao<SubOrder, SubOrderExample> {
    int countByExample(SubOrderExample example);

    int deleteByExample(SubOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SubOrder record);

    int insertSelective(SubOrder record);

    List<SubOrder> selectByExample(SubOrderExample example);

    SubOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SubOrder record, @Param("example") SubOrderExample example);

    int updateByExample(@Param("record") SubOrder record, @Param("example") SubOrderExample example);

    int updateByPrimaryKeySelective(SubOrder record);

    int updateByPrimaryKey(SubOrder record);
}