package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaExample;
@Repository
public interface DecorateAreaMapper extends BaseDao<DecorateArea, DecorateAreaExample>{
    int countByExample(DecorateAreaExample example);

    int deleteByExample(DecorateAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DecorateArea record);

    int insertSelective(DecorateArea record);

    List<DecorateArea> selectByExample(DecorateAreaExample example);

    DecorateArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DecorateArea record, @Param("example") DecorateAreaExample example);

    int updateByExample(@Param("record") DecorateArea record, @Param("example") DecorateAreaExample example);

    int updateByPrimaryKeySelective(DecorateArea record);

    int updateByPrimaryKey(DecorateArea record);
}