package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CommentDrawSetting;
import com.jf.entity.CommentDrawSettingCustom;
import com.jf.entity.CommentDrawSettingCustomExample;

@Repository
public interface CommentDrawSettingCustomMapper {
    
	public int countByCustomExample(CommentDrawSettingCustomExample example);

	public List<CommentDrawSettingCustom> selectByCustomExample(CommentDrawSettingCustomExample example);

	public CommentDrawSettingCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") CommentDrawSetting record, @Param("example") CommentDrawSettingCustomExample example);

}