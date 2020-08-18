package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SpreadName;
import com.jf.entity.SpreadNameExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpreadNameMapper extends BaseDao<SpreadName, SpreadNameExample> {
    int countByExample(SpreadNameExample example);

    int deleteByExample(SpreadNameExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpreadName record);

    int insertSelective(SpreadName record);

    List<SpreadName> selectByExample(SpreadNameExample example);

    SpreadName selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpreadName record, @Param("example") SpreadNameExample example);

    int updateByExample(@Param("record") SpreadName record, @Param("example") SpreadNameExample example);

    int updateByPrimaryKeySelective(SpreadName record);

    int updateByPrimaryKey(SpreadName record);
}
