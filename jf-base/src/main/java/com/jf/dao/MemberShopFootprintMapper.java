package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberShopFootprint;
import com.jf.entity.MemberShopFootprintExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberShopFootprintMapper extends BaseDao<MemberShopFootprint, MemberShopFootprintExample> {
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
