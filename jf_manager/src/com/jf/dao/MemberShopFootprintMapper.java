package com.jf.dao;

import com.jf.entity.MemberShopFootprint;
import com.jf.entity.MemberShopFootprintExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MemberShopFootprintMapper extends BaseDao<MemberShopFootprint, MemberShopFootprintExample>{
    int countByExample(MemberShopFootprintExample example);

    int deleteByExample(MemberShopFootprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberShopFootprint record);

    int insertSelective(MemberShopFootprint record);

    List<MemberShopFootprint> selectByExample(MemberShopFootprintExample example);

    MemberShopFootprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberShopFootprint record, @Param("example") MemberShopFootprintExample example);

    int updateByExample(@Param("record") MemberShopFootprint record, @Param("example") MemberShopFootprintExample example);

    int updateByPrimaryKeySelective(MemberShopFootprint record);

    int updateByPrimaryKey(MemberShopFootprint record);
}