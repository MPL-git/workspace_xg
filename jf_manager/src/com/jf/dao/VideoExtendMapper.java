package com.jf.dao;

import com.jf.entity.VideoExtend;
import com.jf.entity.VideoExtendExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoExtendMapper extends BaseDao<VideoExtend, VideoExtendExample> {
    int countByExample(VideoExtendExample example);

    int deleteByExample(VideoExtendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoExtend record);

    int insertSelective(VideoExtend record);

    List<VideoExtend> selectByExample(VideoExtendExample example);

    VideoExtend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoExtend record, @Param("example") VideoExtendExample example);

    int updateByExample(@Param("record") VideoExtend record, @Param("example") VideoExtendExample example);

    int updateByPrimaryKeySelective(VideoExtend record);

    int updateByPrimaryKey(VideoExtend record);
}
