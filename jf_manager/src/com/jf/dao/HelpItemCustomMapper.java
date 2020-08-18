package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.HelpItem;
import com.jf.entity.HelpItemCustom;
import com.jf.entity.HelpItemCustomExample;

@Repository
public interface HelpItemCustomMapper {
    
	public int countByCustomExample(HelpItemCustomExample example);

	public List<HelpItemCustom> selectByCustomExample(HelpItemCustomExample example);

	public HelpItem selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") HelpItem record, @Param("example") HelpItemCustomExample example);

}