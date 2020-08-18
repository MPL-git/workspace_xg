package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.Sport;
import com.jf.entity.SportExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
