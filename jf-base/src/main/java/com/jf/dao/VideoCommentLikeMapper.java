package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.VideoCommentLike;
import com.jf.entity.VideoCommentLikeExample;

@Repository
public interface VideoCommentLikeMapper extends BaseDao<VideoCommentLike, VideoCommentLikeExample> {
    int countByExample(VideoCommentLikeExample example);

    int deleteByExample(VideoCommentLikeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoCommentLike record);

    int insertSelective(VideoCommentLike record);

    List<VideoCommentLike> selectByExample(VideoCommentLikeExample example);

    VideoCommentLike selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoCommentLike record, @Param("example") VideoCommentLikeExample example);

    int updateByExample(@Param("record") VideoCommentLike record, @Param("example") VideoCommentLikeExample example);

    int updateByPrimaryKeySelective(VideoCommentLike record);

    int updateByPrimaryKey(VideoCommentLike record);
}
