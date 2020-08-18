package com.jf.dao;

import com.jf.entity.VideoCommentReply;
import com.jf.entity.VideoCommentReplyExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoCommentReplyMapper extends BaseDao<VideoCommentReply, VideoCommentReplyExample> {
    int countByExample(VideoCommentReplyExample example);

    int deleteByExample(VideoCommentReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoCommentReply record);

    int insertSelective(VideoCommentReply record);

    List<VideoCommentReply> selectByExample(VideoCommentReplyExample example);

    VideoCommentReply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoCommentReply record, @Param("example") VideoCommentReplyExample example);

    int updateByExample(@Param("record") VideoCommentReply record, @Param("example") VideoCommentReplyExample example);

    int updateByPrimaryKeySelective(VideoCommentReply record);

    int updateByPrimaryKey(VideoCommentReply record);
}
