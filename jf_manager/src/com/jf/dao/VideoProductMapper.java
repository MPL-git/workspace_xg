package com.jf.dao;

import com.jf.entity.VideoProduct;
import com.jf.entity.VideoProductExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoProductMapper extends BaseDao<VideoProduct, VideoProductExample> {
    int countByExample(VideoProductExample example);

    int deleteByExample(VideoProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoProduct record);

    int insertSelective(VideoProduct record);

    List<VideoProduct> selectByExample(VideoProductExample example);

    VideoProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoProduct record, @Param("example") VideoProductExample example);

    int updateByExample(@Param("record") VideoProduct record, @Param("example") VideoProductExample example);

    int updateByPrimaryKeySelective(VideoProduct record);

    int updateByPrimaryKey(VideoProduct record);
}
