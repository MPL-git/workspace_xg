package com.jf.dao;

import com.jf.entity.Sport;
import com.jf.entity.SportExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SportMapper extends BaseDao<Sport, SportExample> {
    int countByExample(SportExample example);

    int deleteByExample(SportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sport record);

    int insertSelective(Sport record);

    List<Sport> selectByExample(SportExample example);

    Sport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sport record, @Param("example") SportExample example);

    int updateByExample(@Param("record") Sport record, @Param("example") SportExample example);

    int updateByPrimaryKeySelective(Sport record);

    int updateByPrimaryKey(Sport record);
}