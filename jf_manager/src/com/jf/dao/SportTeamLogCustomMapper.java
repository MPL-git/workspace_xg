package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SportTeamLog;
import com.jf.entity.SportTeamLogCustom;
import com.jf.entity.SportTeamLogCustomExample;
import com.jf.entity.SportTeamLogExample;

@Repository
public interface SportTeamLogCustomMapper extends BaseDao<SportTeamLog, SportTeamLogExample> {
    
	public int countByCustomExample(SportTeamLogCustomExample example);

    public List<SportTeamLogCustom> selectByCustomExample(SportTeamLogCustomExample example);

    public SportTeamLogCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") SportTeamLog record, @Param("example") SportTeamLogCustomExample example);


}