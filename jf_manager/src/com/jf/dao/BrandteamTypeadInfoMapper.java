package com.jf.dao;

import com.jf.entity.BrandteamTypeadInfo;
import com.jf.entity.BrandteamTypeadInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface BrandteamTypeadInfoMapper extends BaseDao<BrandteamTypeadInfo, BrandteamTypeadInfoExample>{
    int countByExample(BrandteamTypeadInfoExample example);

    int deleteByExample(BrandteamTypeadInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BrandteamTypeadInfo record);

    int insertSelective(BrandteamTypeadInfo record);

    List<BrandteamTypeadInfo> selectByExample(BrandteamTypeadInfoExample example);

    BrandteamTypeadInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BrandteamTypeadInfo record, @Param("example") BrandteamTypeadInfoExample example);

    int updateByExample(@Param("record") BrandteamTypeadInfo record, @Param("example") BrandteamTypeadInfoExample example);

    int updateByPrimaryKeySelective(BrandteamTypeadInfo record);

    int updateByPrimaryKey(BrandteamTypeadInfo record);
}