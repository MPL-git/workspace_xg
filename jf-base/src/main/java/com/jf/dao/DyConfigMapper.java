package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DyConfig;
import com.jf.entity.DyConfigExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DyConfigMapper extends BaseDao<DyConfig, DyConfigExample> {
    int countByExample(DyConfigExample example);

    int deleteByExample(DyConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DyConfig record);

    int insertSelective(DyConfig record);

    List<DyConfig> selectByExample(DyConfigExample example);

    DyConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DyConfig record, @Param("example") DyConfigExample example);

    int updateByExample(@Param("record") DyConfig record, @Param("example") DyConfigExample example);

    int updateByPrimaryKeySelective(DyConfig record);

    int updateByPrimaryKey(DyConfig record);
}
