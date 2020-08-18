package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SportTeam;
import com.jf.entity.SportTeamExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportTeamMapper extends BaseDao<SportTeam, SportTeamExample> {
    int countByExample(SportTeamExample example);

    int deleteByExample(SportTeamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SportTeam record);

    int insertSelective(SportTeam record);

    List<SportTeam> selectByExample(SportTeamExample example);

    SportTeam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SportTeam record, @Param("example") SportTeamExample example);

    int updateByExample(@Param("record") SportTeam record, @Param("example") SportTeamExample example);

    int updateByPrimaryKeySelective(SportTeam record);

    int updateByPrimaryKey(SportTeam record);
}
