package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.VideoAttention;
import com.jf.entity.VideoAttentionExample;

@Repository
public interface VideoAttentionMapper extends BaseDao<VideoAttention, VideoAttentionExample> {
    int countByExample(VideoAttentionExample example);

    int deleteByExample(VideoAttentionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoAttention record);

    int insertSelective(VideoAttention record);

    List<VideoAttention> selectByExample(VideoAttentionExample example);

    VideoAttention selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoAttention record, @Param("example") VideoAttentionExample example);

    int updateByExample(@Param("record") VideoAttention record, @Param("example") VideoAttentionExample example);

    int updateByPrimaryKeySelective(VideoAttention record);

    int updateByPrimaryKey(VideoAttention record);
}
