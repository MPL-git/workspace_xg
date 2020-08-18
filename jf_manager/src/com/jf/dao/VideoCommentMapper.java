package com.jf.dao;

import com.jf.entity.VideoComment;
import com.jf.entity.VideoCommentExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoCommentMapper extends BaseDao<VideoComment, VideoCommentExample> {
    int countByExample(VideoCommentExample example);

    int deleteByExample(VideoCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoComment record);

    int insertSelective(VideoComment record);

    List<VideoComment> selectByExample(VideoCommentExample example);

    VideoComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoComment record, @Param("example") VideoCommentExample example);

    int updateByExample(@Param("record") VideoComment record, @Param("example") VideoCommentExample example);

    int updateByPrimaryKeySelective(VideoComment record);

    int updateByPrimaryKey(VideoComment record);
}
