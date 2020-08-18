package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.TrackData;
import com.jf.entity.TrackDataExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackDataMapper extends BaseDao<TrackData, TrackDataExample> {
    int countByExample(TrackDataExample example);

    int deleteByExample(TrackDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TrackData record);

    int insertSelective(TrackData record);

    List<TrackData> selectByExample(TrackDataExample example);

    TrackData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TrackData record, @Param("example") TrackDataExample example);

    int updateByExample(@Param("record") TrackData record, @Param("example") TrackDataExample example);

    int updateByPrimaryKeySelective(TrackData record);

    int updateByPrimaryKey(TrackData record);
}
