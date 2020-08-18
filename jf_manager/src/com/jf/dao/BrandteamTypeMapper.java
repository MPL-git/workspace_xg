package com.jf.dao;

import com.jf.entity.BrandteamType;
import com.jf.entity.BrandteamTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface BrandteamTypeMapper extends BaseDao<BrandteamType, BrandteamTypeExample>{
    int countByExample(BrandteamTypeExample example);

    int deleteByExample(BrandteamTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BrandteamType record);

    int insertSelective(BrandteamType record);

    List<BrandteamType> selectByExample(BrandteamTypeExample example);

    BrandteamType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BrandteamType record, @Param("example") BrandteamTypeExample example);

    int updateByExample(@Param("record") BrandteamType record, @Param("example") BrandteamTypeExample example);

    int updateByPrimaryKeySelective(BrandteamType record);

    int updateByPrimaryKey(BrandteamType record);
}