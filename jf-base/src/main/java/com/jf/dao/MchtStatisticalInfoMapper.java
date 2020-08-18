package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtStatisticalInfo;
import com.jf.entity.MchtStatisticalInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtStatisticalInfoMapper extends BaseDao<MchtStatisticalInfo, MchtStatisticalInfoExample> {
    int countByExample(MchtStatisticalInfoExample example);

    int deleteByExample(MchtStatisticalInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtStatisticalInfo record);

    int insertSelective(MchtStatisticalInfo record);

    List<MchtStatisticalInfo> selectByExample(MchtStatisticalInfoExample example);

    MchtStatisticalInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtStatisticalInfo record, @Param("example") MchtStatisticalInfoExample example);

    int updateByExample(@Param("record") MchtStatisticalInfo record, @Param("example") MchtStatisticalInfoExample example);

    int updateByPrimaryKeySelective(MchtStatisticalInfo record);

    int updateByPrimaryKey(MchtStatisticalInfo record);
}
