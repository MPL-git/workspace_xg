package com.jf.dao;

import com.jf.entity.VideoLike;
import com.jf.entity.VideoLikeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoLikeMapper extends BaseDao<VideoLike, VideoLikeExample> {
    int countByExample(VideoLikeExample example);

    int deleteByExample(VideoLikeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoLike record);

    int insertSelective(VideoLike record);

    List<VideoLike> selectByExample(VideoLikeExample example);

    VideoLike selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoLike record, @Param("example") VideoLikeExample example);

    int updateByExample(@Param("record") VideoLike record, @Param("example") VideoLikeExample example);

    int updateByPrimaryKeySelective(VideoLike record);

    int updateByPrimaryKey(VideoLike record);
}
