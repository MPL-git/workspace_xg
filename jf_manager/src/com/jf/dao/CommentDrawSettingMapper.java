package com.jf.dao;

import com.jf.entity.CommentDrawSetting;
import com.jf.entity.CommentDrawSettingExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDrawSettingMapper extends BaseDao<CommentDrawSetting, CommentDrawSettingExample> {
    int countByExample(CommentDrawSettingExample example);

    int deleteByExample(CommentDrawSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentDrawSetting record);

    int insertSelective(CommentDrawSetting record);

    List<CommentDrawSetting> selectByExample(CommentDrawSettingExample example);

    CommentDrawSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentDrawSetting record, @Param("example") CommentDrawSettingExample example);

    int updateByExample(@Param("record") CommentDrawSetting record, @Param("example") CommentDrawSettingExample example);

    int updateByPrimaryKeySelective(CommentDrawSetting record);

    int updateByPrimaryKey(CommentDrawSetting record);
}