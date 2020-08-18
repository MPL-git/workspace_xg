package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorateModuleMapper extends BaseDao<DecorateModule, DecorateModuleExample> {
    int countByExample(DecorateModuleExample example);

    int deleteByExample(DecorateModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DecorateModule record);

    int insertSelective(DecorateModule record);

    List<DecorateModule> selectByExample(DecorateModuleExample example);

    DecorateModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DecorateModule record, @Param("example") DecorateModuleExample example);

    int updateByExample(@Param("record") DecorateModule record, @Param("example") DecorateModuleExample example);

    int updateByPrimaryKeySelective(DecorateModule record);

    int updateByPrimaryKey(DecorateModule record);

    List<DecorateModule> selectByExampleWithBLOBs(DecorateModuleExample example);
    int updateByPrimaryKeyWithBLOBs(DecorateModule record);
    int updateByExampleWithBLOBs(@Param("record") DecorateModule record, @Param("example") DecorateModuleExample example);
}
