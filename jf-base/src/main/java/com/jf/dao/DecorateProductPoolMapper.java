package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DecorateProductPool;
import com.jf.entity.DecorateProductPoolExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorateProductPoolMapper extends BaseDao<DecorateProductPool, DecorateProductPoolExample> {
    int countByExample(DecorateProductPoolExample example);

    int deleteByExample(DecorateProductPoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DecorateProductPool record);

    int insertSelective(DecorateProductPool record);

    List<DecorateProductPool> selectByExample(DecorateProductPoolExample example);

    DecorateProductPool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DecorateProductPool record, @Param("example") DecorateProductPoolExample example);

    int updateByExample(@Param("record") DecorateProductPool record, @Param("example") DecorateProductPoolExample example);

    int updateByPrimaryKeySelective(DecorateProductPool record);

    int updateByPrimaryKey(DecorateProductPool record);
}
