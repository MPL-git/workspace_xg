package com.jf.dao;

import com.jf.entity.VideoTipOff;
import com.jf.entity.VideoTipOffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoTipOffMapper extends BaseDao<VideoTipOff,VideoTipOffExample>{
    int countByExample(VideoTipOffExample example);

    int deleteByExample(VideoTipOffExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoTipOff record);

    int insertSelective(VideoTipOff record);

    List<VideoTipOff> selectByExample(VideoTipOffExample example);

    VideoTipOff selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoTipOff record, @Param("example") VideoTipOffExample example);

    int updateByExample(@Param("record") VideoTipOff record, @Param("example") VideoTipOffExample example);

    int updateByPrimaryKeySelective(VideoTipOff record);

    int updateByPrimaryKey(VideoTipOff record);
}