package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ConfigSpecialMcht;
import com.jf.entity.ConfigSpecialMchtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigSpecialMchtMapper extends BaseDao<ConfigSpecialMcht,ConfigSpecialMchtExample> {
    int countByExample(ConfigSpecialMchtExample example);

    int deleteByExample(ConfigSpecialMchtExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigSpecialMcht record);

    int insertSelective(ConfigSpecialMcht record);

    List<ConfigSpecialMcht> selectByExample(ConfigSpecialMchtExample example);

    ConfigSpecialMcht selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigSpecialMcht record, @Param("example") ConfigSpecialMchtExample example);

    int updateByExample(@Param("record") ConfigSpecialMcht record, @Param("example") ConfigSpecialMchtExample example);

    int updateByPrimaryKeySelective(ConfigSpecialMcht record);

    int updateByPrimaryKey(ConfigSpecialMcht record);
}