package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PlatformPvStatistical;
import com.jf.entity.PlatformPvStatisticalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformPvStatisticalMapper extends BaseDao<PlatformPvStatistical, PlatformPvStatisticalExample> {
    int countByExample(PlatformPvStatisticalExample example);

    int deleteByExample(PlatformPvStatisticalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformPvStatistical record);

    int insertSelective(PlatformPvStatistical record);

    List<PlatformPvStatistical> selectByExample(PlatformPvStatisticalExample example);

    PlatformPvStatistical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlatformPvStatistical record, @Param("example") PlatformPvStatisticalExample example);

    int updateByExample(@Param("record") PlatformPvStatistical record, @Param("example") PlatformPvStatisticalExample example);

    int updateByPrimaryKeySelective(PlatformPvStatistical record);

    int updateByPrimaryKey(PlatformPvStatistical record);
}