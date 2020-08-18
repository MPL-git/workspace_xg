package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.AdPvStatistical;
import com.jf.entity.AdPvStatisticalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdPvStatisticalMapper extends BaseDao<AdPvStatistical, AdPvStatisticalExample> {
    int countByExample(AdPvStatisticalExample example);

    int deleteByExample(AdPvStatisticalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdPvStatistical record);

    int insertSelective(AdPvStatistical record);

    List<AdPvStatistical> selectByExample(AdPvStatisticalExample example);

    AdPvStatistical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdPvStatistical record, @Param("example") AdPvStatisticalExample example);

    int updateByExample(@Param("record") AdPvStatistical record, @Param("example") AdPvStatisticalExample example);

    int updateByPrimaryKeySelective(AdPvStatistical record);

    int updateByPrimaryKey(AdPvStatistical record);
}