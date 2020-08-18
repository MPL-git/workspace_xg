package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.FullCut;
import com.jf.entity.FullCutExample;
@Repository
public interface FullCutMapper extends BaseDao<FullCut, FullCutExample>{
    int countByExample(FullCutExample example);

    int deleteByExample(FullCutExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FullCut record);

    int insertSelective(FullCut record);

    List<FullCut> selectByExample(FullCutExample example);

    FullCut selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FullCut record, @Param("example") FullCutExample example);

    int updateByExample(@Param("record") FullCut record, @Param("example") FullCutExample example);

    int updateByPrimaryKeySelective(FullCut record);

    int updateByPrimaryKey(FullCut record);
}