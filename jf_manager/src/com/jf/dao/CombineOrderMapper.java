package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderExample;
@Repository
public interface CombineOrderMapper extends BaseDao<CombineOrder, CombineOrderExample> {
    int countByExample(CombineOrderExample example);

    int deleteByExample(CombineOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CombineOrder record);

    int insertSelective(CombineOrder record);

    List<CombineOrder> selectByExample(CombineOrderExample example);

    CombineOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CombineOrder record, @Param("example") CombineOrderExample example);

    int updateByExample(@Param("record") CombineOrder record, @Param("example") CombineOrderExample example);

    int updateByPrimaryKeySelective(CombineOrder record);

    int updateByPrimaryKey(CombineOrder record);
}