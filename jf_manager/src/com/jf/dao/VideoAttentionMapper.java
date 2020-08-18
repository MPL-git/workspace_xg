package com.jf.dao;

import com.jf.entity.VideoAttention;
import com.jf.entity.VideoAttentionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
