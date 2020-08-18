package com.jf.dao;

import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface DecorateModuleMapper  extends BaseDao<DecorateModule, DecorateModuleExample>{
    int countByExample(DecorateModuleExample example);

    int deleteByExample(DecorateModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DecorateModule record);

    int insertSelective(DecorateModule record);

    List<DecorateModule> selectByExampleWithBLOBs(DecorateModuleExample example);

    List<DecorateModule> selectByExample(DecorateModuleExample example);

    DecorateModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DecorateModule record, @Param("example") DecorateModuleExample example);

    int updateByExampleWithBLOBs(@Param("record") DecorateModule record, @Param("example") DecorateModuleExample example);

    int updateByExample(@Param("record") DecorateModule record, @Param("example") DecorateModuleExample example);

    int updateByPrimaryKeySelective(DecorateModule record);

    int updateByPrimaryKeyWithBLOBs(DecorateModule record);

    int updateByPrimaryKey(DecorateModule record);
}