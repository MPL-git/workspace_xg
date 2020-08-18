package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SpreadActivityGroupSet;
import com.jf.entity.SpreadActivityGroupSetCustom;
import com.jf.entity.SpreadActivityGroupSetCustomExample;
import com.jf.entity.SpreadActivityGroupSetExample;

@Repository
public interface SpreadActivityGroupSetCustomMapper {
    
	public int countByCustomExample(SpreadActivityGroupSetExample example);

	public List<SpreadActivityGroupSetCustom> selectByCustomExample(SpreadActivityGroupSetCustomExample example);

	public SpreadActivityGroupSetCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") SpreadActivityGroupSet record, @Param("example") SpreadActivityGroupSetCustomExample example);

}