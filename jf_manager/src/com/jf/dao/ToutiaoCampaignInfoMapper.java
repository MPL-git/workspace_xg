package com.jf.dao;

import com.jf.entity.ToutiaoCampaignInfo;
import com.jf.entity.ToutiaoCampaignInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToutiaoCampaignInfoMapper extends BaseDao<ToutiaoCampaignInfo, ToutiaoCampaignInfoExample> {
    int countByExample(ToutiaoCampaignInfoExample example);

    int deleteByExample(ToutiaoCampaignInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ToutiaoCampaignInfo record);

    int insertSelective(ToutiaoCampaignInfo record);

    List<ToutiaoCampaignInfo> selectByExample(ToutiaoCampaignInfoExample example);

    ToutiaoCampaignInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ToutiaoCampaignInfo record, @Param("example") ToutiaoCampaignInfoExample example);

    int updateByExample(@Param("record") ToutiaoCampaignInfo record, @Param("example") ToutiaoCampaignInfoExample example);

    int updateByPrimaryKeySelective(ToutiaoCampaignInfo record);

    int updateByPrimaryKey(ToutiaoCampaignInfo record);
}