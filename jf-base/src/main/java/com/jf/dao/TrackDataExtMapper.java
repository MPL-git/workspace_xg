package com.jf.dao;

import com.jf.entity.TrackDataExt;
import com.jf.entity.TrackDataExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackDataExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    TrackDataExt findById(int id);

    TrackDataExt find(TrackDataExtExample example);

    List<TrackDataExt> list(TrackDataExtExample example);

    List<Integer> listId(TrackDataExtExample example);

    int count(TrackDataExtExample example);

    long sum(@Param("field") String field, @Param("example") TrackDataExtExample example);

    int max(@Param("field") String field, @Param("example") TrackDataExtExample example);

    int min(@Param("field") String field, @Param("example") TrackDataExtExample example);

}

