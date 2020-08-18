package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.BrandTeamTypeAdInfo;
import com.jf.entity.BrandTeamTypeAdInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandTeamTypeAdInfoMapper extends BaseDao<BrandTeamTypeAdInfo, BrandTeamTypeAdInfoExample> {
    int countByExample(BrandTeamTypeAdInfoExample example);

    int deleteByExample(BrandTeamTypeAdInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BrandTeamTypeAdInfo record);

    int insertSelective(BrandTeamTypeAdInfo record);

    List<BrandTeamTypeAdInfo> selectByExample(BrandTeamTypeAdInfoExample example);

    BrandTeamTypeAdInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BrandTeamTypeAdInfo record, @Param("example") BrandTeamTypeAdInfoExample example);

    int updateByExample(@Param("record") BrandTeamTypeAdInfo record, @Param("example") BrandTeamTypeAdInfoExample example);

    int updateByPrimaryKeySelective(BrandTeamTypeAdInfo record);

    int updateByPrimaryKey(BrandTeamTypeAdInfo record);
}
