package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.VideoLike;
import com.jf.entity.VideoLikeExample;

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
