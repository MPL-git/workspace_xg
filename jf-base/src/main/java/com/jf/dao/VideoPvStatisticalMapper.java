package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.VideoPvStatistical;
import com.jf.entity.VideoPvStatisticalExample;

@Repository
public interface VideoPvStatisticalMapper extends BaseDao<VideoPvStatistical, VideoPvStatisticalExample> {
    int countByExample(VideoPvStatisticalExample example);

    int deleteByExample(VideoPvStatisticalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoPvStatistical record);

    int insertSelective(VideoPvStatistical record);

    List<VideoPvStatistical> selectByExample(VideoPvStatisticalExample example);

    VideoPvStatistical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoPvStatistical record, @Param("example") VideoPvStatisticalExample example);

    int updateByExample(@Param("record") VideoPvStatistical record, @Param("example") VideoPvStatisticalExample example);

    int updateByPrimaryKeySelective(VideoPvStatistical record);

    int updateByPrimaryKey(VideoPvStatistical record);
}
