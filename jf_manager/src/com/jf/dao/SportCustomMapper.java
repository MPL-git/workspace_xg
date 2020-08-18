package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.Sport;
import com.jf.entity.SportCustom;
import com.jf.entity.SportCustomExample;
import com.jf.entity.SportExample;

@Repository
public interface SportCustomMapper extends BaseDao<Sport, SportExample> {
    
	public int countByCustomExample(SportCustomExample example);

	public List<SportCustom> selectByCustomExample(SportCustomExample example);

	public SportCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") Sport record, @Param("example") SportCustomExample example);

}