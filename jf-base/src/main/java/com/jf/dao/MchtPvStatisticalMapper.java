package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtPvStatistical;
import com.jf.entity.MchtPvStatisticalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MchtPvStatisticalMapper extends BaseDao<MchtPvStatistical, MchtPvStatisticalExample> {
    int countByExample(MchtPvStatisticalExample example);

    int deleteByExample(MchtPvStatisticalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtPvStatistical record);

    int insertSelective(MchtPvStatistical record);

    List<MchtPvStatistical> selectByExample(MchtPvStatisticalExample example);

    MchtPvStatistical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtPvStatistical record, @Param("example") MchtPvStatisticalExample example);

    int updateByExample(@Param("record") MchtPvStatistical record, @Param("example") MchtPvStatisticalExample example);

    int updateByPrimaryKeySelective(MchtPvStatistical record);

    int updateByPrimaryKey(MchtPvStatistical record);
}