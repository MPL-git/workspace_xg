package com.jf.dao;

import com.jf.entity.WoRk;
import com.jf.entity.WoRkExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface WoRkMapper extends BaseDao<WoRk, WoRkExample>{
    int countByExample(WoRkExample example);

    int deleteByExample(WoRkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WoRk record);

    int insertSelective(WoRk record);

    List<WoRk> selectByExample(WoRkExample example);

    WoRk selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WoRk record, @Param("example") WoRkExample example);

    int updateByExample(@Param("record") WoRk record, @Param("example") WoRkExample example);

    int updateByPrimaryKeySelective(WoRk record);

    int updateByPrimaryKey(WoRk record);
}