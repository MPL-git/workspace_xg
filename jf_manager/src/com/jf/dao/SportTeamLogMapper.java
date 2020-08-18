package com.jf.dao;

import com.jf.entity.SportTeamLog;
import com.jf.entity.SportTeamLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SportTeamLogMapper extends BaseDao<SportTeamLog, SportTeamLogExample> {
    int countByExample(SportTeamLogExample example);

    int deleteByExample(SportTeamLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SportTeamLog record);

    int insertSelective(SportTeamLog record);

    List<SportTeamLog> selectByExample(SportTeamLogExample example);

    SportTeamLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SportTeamLog record, @Param("example") SportTeamLogExample example);

    int updateByExample(@Param("record") SportTeamLog record, @Param("example") SportTeamLogExample example);

    int updateByPrimaryKeySelective(SportTeamLog record);

    int updateByPrimaryKey(SportTeamLog record);
}