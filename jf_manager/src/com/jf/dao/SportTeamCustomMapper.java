package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SportTeam;
import com.jf.entity.SportTeamCustom;
import com.jf.entity.SportTeamCustomExample;

@Repository
public interface SportTeamCustomMapper {
    
	public int countByCustomExample(SportTeamCustomExample example);

    public List<SportTeamCustom> selectByCustomExample(SportTeamCustomExample example);

    public SportTeamCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") SportTeam record, @Param("example") SportTeamCustomExample example);

    
}