package com.jf.dao;

import com.jf.entity.VideoColumn;
import com.jf.entity.VideoColumnExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoColumnMapper extends BaseDao<VideoColumn, VideoColumnExample> {
    int countByExample(VideoColumnExample example);

    int deleteByExample(VideoColumnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoColumn record);

    int insertSelective(VideoColumn record);

    List<VideoColumn> selectByExample(VideoColumnExample example);

    VideoColumn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoColumn record, @Param("example") VideoColumnExample example);

    int updateByExample(@Param("record") VideoColumn record, @Param("example") VideoColumnExample example);

    int updateByPrimaryKeySelective(VideoColumn record);

    int updateByPrimaryKey(VideoColumn record);
}
