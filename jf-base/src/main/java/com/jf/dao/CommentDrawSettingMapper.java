package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CommentDrawSetting;
import com.jf.entity.CommentDrawSettingExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
