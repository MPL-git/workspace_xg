package com.jf.dao;

import com.jf.entity.NovaStrategy;
import com.jf.entity.NovaStrategyCustom;
import com.jf.entity.NovaStrategyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NovaStrategyCustomMapper extends BaseDao<NovaStrategy, NovaStrategyExample>{
    int countByExample(NovaStrategyExample example);

    int deleteByExample(NovaStrategyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NovaStrategy record);

    int insertSelective(NovaStrategy record);

    List<NovaStrategyCustom> selectByExampleCustom(NovaStrategyExample example);

    NovaStrategy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NovaStrategy record, @Param("example") NovaStrategyExample example);

    int updateByExample(@Param("record") NovaStrategy record, @Param("example") NovaStrategyExample example);

    int updateByPrimaryKeySelective(NovaStrategy record);

    int updateByPrimaryKey(NovaStrategy record);
    
    
}