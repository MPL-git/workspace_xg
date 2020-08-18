package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.NovaStrategy;
import com.jf.entity.NovaStrategyExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovaStrategyMapper extends BaseDao<NovaStrategy, NovaStrategyExample> {
    int countByExample(NovaStrategyExample example);

    int deleteByExample(NovaStrategyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NovaStrategy record);

    int insertSelective(NovaStrategy record);

    List<NovaStrategy> selectByExample(NovaStrategyExample example);

    NovaStrategy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NovaStrategy record, @Param("example") NovaStrategyExample example);

    int updateByExample(@Param("record") NovaStrategy record, @Param("example") NovaStrategyExample example);

    int updateByPrimaryKeySelective(NovaStrategy record);

    int updateByPrimaryKey(NovaStrategy record);
}
